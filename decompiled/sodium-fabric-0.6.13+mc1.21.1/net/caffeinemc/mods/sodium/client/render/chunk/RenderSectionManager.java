package net.caffeinemc.mods.sodium.client.render.chunk;

import com.mojang.blaze3d.systems.RenderSystem;
import it.unimi.dsi.fastutil.longs.Long2ReferenceMap;
import it.unimi.dsi.fastutil.longs.Long2ReferenceMaps;
import it.unimi.dsi.fastutil.longs.Long2ReferenceOpenHashMap;
import it.unimi.dsi.fastutil.objects.ObjectIterator;
import it.unimi.dsi.fastutil.objects.Reference2ReferenceLinkedOpenHashMap;
import it.unimi.dsi.fastutil.objects.ReferenceOpenHashSet;
import it.unimi.dsi.fastutil.objects.ReferenceSet;
import it.unimi.dsi.fastutil.objects.ReferenceSets;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentLinkedDeque;
import net.caffeinemc.mods.sodium.api.texture.SpriteUtil;
import net.caffeinemc.mods.sodium.client.SodiumClientMod;
import net.caffeinemc.mods.sodium.client.gl.arena.GlBufferArena;
import net.caffeinemc.mods.sodium.client.gl.device.CommandList;
import net.caffeinemc.mods.sodium.client.gl.device.RenderDevice;
import net.caffeinemc.mods.sodium.client.render.chunk.compile.BuilderTaskOutput;
import net.caffeinemc.mods.sodium.client.render.chunk.compile.ChunkBuildOutput;
import net.caffeinemc.mods.sodium.client.render.chunk.compile.ChunkSortOutput;
import net.caffeinemc.mods.sodium.client.render.chunk.compile.executor.ChunkBuilder;
import net.caffeinemc.mods.sodium.client.render.chunk.compile.executor.ChunkJobCollector;
import net.caffeinemc.mods.sodium.client.render.chunk.compile.executor.ChunkJobResult;
import net.caffeinemc.mods.sodium.client.render.chunk.compile.executor.ChunkJobTyped;
import net.caffeinemc.mods.sodium.client.render.chunk.compile.tasks.ChunkBuilderMeshingTask;
import net.caffeinemc.mods.sodium.client.render.chunk.compile.tasks.ChunkBuilderSortingTask;
import net.caffeinemc.mods.sodium.client.render.chunk.compile.tasks.ChunkBuilderTask;
import net.caffeinemc.mods.sodium.client.render.chunk.data.BuiltSectionInfo;
import net.caffeinemc.mods.sodium.client.render.chunk.lists.ChunkRenderList;
import net.caffeinemc.mods.sodium.client.render.chunk.lists.SortedRenderLists;
import net.caffeinemc.mods.sodium.client.render.chunk.lists.VisibleChunkCollector;
import net.caffeinemc.mods.sodium.client.render.chunk.occlusion.GraphDirection;
import net.caffeinemc.mods.sodium.client.render.chunk.occlusion.OcclusionCuller;
import net.caffeinemc.mods.sodium.client.render.chunk.region.RenderRegion;
import net.caffeinemc.mods.sodium.client.render.chunk.region.RenderRegionManager;
import net.caffeinemc.mods.sodium.client.render.chunk.terrain.TerrainRenderPass;
import net.caffeinemc.mods.sodium.client.render.chunk.translucent_sorting.SortBehavior;
import net.caffeinemc.mods.sodium.client.render.chunk.translucent_sorting.data.DynamicTopoData;
import net.caffeinemc.mods.sodium.client.render.chunk.translucent_sorting.data.NoData;
import net.caffeinemc.mods.sodium.client.render.chunk.translucent_sorting.data.TranslucentData;
import net.caffeinemc.mods.sodium.client.render.chunk.translucent_sorting.trigger.CameraMovement;
import net.caffeinemc.mods.sodium.client.render.chunk.translucent_sorting.trigger.SortTriggering;
import net.caffeinemc.mods.sodium.client.render.chunk.vertex.format.ChunkMeshFormats;
import net.caffeinemc.mods.sodium.client.render.util.RenderAsserts;
import net.caffeinemc.mods.sodium.client.render.viewport.CameraTransform;
import net.caffeinemc.mods.sodium.client.render.viewport.Viewport;
import net.caffeinemc.mods.sodium.client.util.MathUtil;
import net.caffeinemc.mods.sodium.client.util.iterator.ByteIterator;
import net.caffeinemc.mods.sodium.client.util.task.CancellationToken;
import net.caffeinemc.mods.sodium.client.world.LevelSlice;
import net.caffeinemc.mods.sodium.client.world.cloned.ChunkRenderContext;
import net.caffeinemc.mods.sodium.client.world.cloned.ClonedChunkSectionCache;
import net.minecraft.class_1058;
import net.minecraft.class_2338;
import net.minecraft.class_2791;
import net.minecraft.class_2826;
import net.minecraft.class_310;
import net.minecraft.class_3532;
import net.minecraft.class_4076;
import net.minecraft.class_4184;
import net.minecraft.class_638;
import org.apache.commons.lang3.ArrayUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.joml.Vector3dc;

public class RenderSectionManager {
   private final ChunkBuilder builder;
   private final RenderRegionManager regions;
   private final ClonedChunkSectionCache sectionCache;
   private final Long2ReferenceMap<RenderSection> sectionByPosition = new Long2ReferenceOpenHashMap();
   private final ConcurrentLinkedDeque<ChunkJobResult<? extends BuilderTaskOutput>> buildResults = new ConcurrentLinkedDeque<>();
   private final ChunkRenderer chunkRenderer;
   private final class_638 level;
   private final ReferenceSet<RenderSection> sectionsWithGlobalEntities = new ReferenceOpenHashSet();
   private final OcclusionCuller occlusionCuller;
   private final int renderDistance;
   private final SortTriggering sortTriggering;
   private ChunkJobCollector lastBlockingCollector;
   @NotNull
   private SortedRenderLists renderLists;
   @NotNull
   private Map<ChunkUpdateType, ArrayDeque<RenderSection>> taskLists;
   private int lastUpdatedFrame;
   private boolean needsGraphUpdate;
   @Nullable
   private class_2338 cameraBlockPos;
   @Nullable
   private Vector3dc cameraPosition;
   private static final float NEARBY_REBUILD_DISTANCE = class_3532.method_27285(16.0F);
   private static final float NEARBY_SORT_DISTANCE = class_3532.method_27285(25.0F);

   public RenderSectionManager(class_638 level, int renderDistance, CommandList commandList) {
      this.chunkRenderer = new DefaultChunkRenderer(RenderDevice.INSTANCE, ChunkMeshFormats.COMPACT);
      this.level = level;
      this.builder = new ChunkBuilder(level, ChunkMeshFormats.COMPACT);
      this.needsGraphUpdate = true;
      this.renderDistance = renderDistance;
      this.sortTriggering = new SortTriggering();
      this.regions = new RenderRegionManager(commandList);
      this.sectionCache = new ClonedChunkSectionCache(this.level);
      this.renderLists = SortedRenderLists.empty();
      this.occlusionCuller = new OcclusionCuller(Long2ReferenceMaps.unmodifiable(this.sectionByPosition), this.level);
      this.taskLists = new EnumMap<>(ChunkUpdateType.class);

      for (ChunkUpdateType type : ChunkUpdateType.values()) {
         this.taskLists.put(type, new ArrayDeque<>());
      }
   }

   public void updateCameraState(Vector3dc cameraPosition, class_4184 camera) {
      this.cameraBlockPos = camera.method_19328();
      this.cameraPosition = cameraPosition;
   }

   public void update(class_4184 camera, Viewport viewport, boolean spectator) {
      this.lastUpdatedFrame++;
      this.createTerrainRenderList(camera, viewport, this.lastUpdatedFrame, spectator);
      this.needsGraphUpdate = false;
   }

   private void createTerrainRenderList(class_4184 camera, Viewport viewport, int frame, boolean spectator) {
      this.resetRenderLists();
      float searchDistance = this.getSearchDistance();
      boolean useOcclusionCulling = this.shouldUseOcclusionCulling(camera, spectator);
      VisibleChunkCollector visitor = new VisibleChunkCollector(frame);
      this.occlusionCuller.findVisible(visitor, viewport, searchDistance, useOcclusionCulling, frame);
      this.renderLists = visitor.createRenderLists(viewport);
      this.taskLists = visitor.getRebuildLists();
   }

   private float getSearchDistance() {
      float distance;
      if (SodiumClientMod.options().performance.useFogOcclusion) {
         distance = this.getEffectiveRenderDistance();
      } else {
         distance = this.getRenderDistance();
      }

      return distance;
   }

   private boolean shouldUseOcclusionCulling(class_4184 camera, boolean spectator) {
      class_2338 origin = camera.method_19328();
      boolean useOcclusionCulling;
      if (spectator && this.level.method_8320(origin).method_26216(this.level, origin)) {
         useOcclusionCulling = false;
      } else {
         useOcclusionCulling = class_310.method_1551().field_1730;
      }

      return useOcclusionCulling;
   }

   private void resetRenderLists() {
      this.renderLists = SortedRenderLists.empty();

      for (ArrayDeque<RenderSection> list : this.taskLists.values()) {
         list.clear();
      }
   }

   public void onSectionAdded(int x, int y, int z) {
      long key = class_4076.method_18685(x, y, z);
      if (!this.sectionByPosition.containsKey(key)) {
         RenderRegion region = this.regions.createForChunk(x, y, z);
         RenderSection renderSection = new RenderSection(region, x, y, z);
         region.addSection(renderSection);
         this.sectionByPosition.put(key, renderSection);
         class_2791 chunk = this.level.method_8497(x, z);
         class_2826 section = chunk.method_12006()[this.level.method_31603(y)];
         if (section.method_38292()) {
            this.updateSectionInfo(renderSection, BuiltSectionInfo.EMPTY);
         } else {
            renderSection.setPendingUpdate(ChunkUpdateType.INITIAL_BUILD);
         }

         this.connectNeighborNodes(renderSection);
         this.needsGraphUpdate = true;
      }
   }

   public void onSectionRemoved(int x, int y, int z) {
      long sectionPos = class_4076.method_18685(x, y, z);
      RenderSection section = (RenderSection)this.sectionByPosition.remove(sectionPos);
      if (section != null) {
         if (section.getTranslucentData() != null) {
            this.sortTriggering.removeSection(section.getTranslucentData(), sectionPos);
         }

         RenderRegion region = section.getRegion();
         if (region != null) {
            region.removeSection(section);
         }

         this.disconnectNeighborNodes(section);
         this.updateSectionInfo(section, null);
         section.delete();
         this.needsGraphUpdate = true;
      }
   }

   public void renderLayer(ChunkRenderMatrices matrices, TerrainRenderPass pass, double x, double y, double z) {
      RenderDevice device = RenderDevice.INSTANCE;
      CommandList commandList = device.createCommandList();
      this.chunkRenderer.render(matrices, commandList, this.renderLists, pass, new CameraTransform(x, y, z));
      commandList.flush();
   }

   public void tickVisibleRenders() {
      for (ChunkRenderList renderList : this.renderLists) {
         RenderRegion region = renderList.getRegion();
         ByteIterator iterator = renderList.sectionsWithSpritesIterator();
         if (iterator != null) {
            while (iterator.hasNext()) {
               RenderSection section = region.getSection(iterator.nextByteAsInt());
               if (section != null) {
                  class_1058[] sprites = section.getAnimatedSprites();
                  if (sprites != null) {
                     for (class_1058 sprite : sprites) {
                        SpriteUtil.INSTANCE.markSpriteActive(sprite);
                     }
                  }
               }
            }
         }
      }
   }

   public boolean isSectionVisible(int x, int y, int z) {
      RenderSection render = this.getRenderSection(x, y, z);
      return render == null ? false : render.getLastVisibleFrame() == this.lastUpdatedFrame;
   }

   public void uploadChunks() {
      ArrayList<BuilderTaskOutput> results = this.collectChunkBuildResults();
      if (!results.isEmpty()) {
         this.needsGraphUpdate = this.needsGraphUpdate | this.processChunkBuildResults(results);

         for (BuilderTaskOutput result : results) {
            result.destroy();
         }
      }
   }

   private boolean processChunkBuildResults(ArrayList<BuilderTaskOutput> results) {
      List<BuilderTaskOutput> filtered = filterChunkBuildResults(results);
      this.regions.uploadResults(RenderDevice.INSTANCE.createCommandList(), filtered);
      boolean touchedSectionInfo = false;

      for (BuilderTaskOutput result : filtered) {
         TranslucentData oldData = result.render.getTranslucentData();
         if (result instanceof ChunkBuildOutput chunkBuildOutput) {
            touchedSectionInfo |= this.updateSectionInfo(result.render, chunkBuildOutput.info);
            if (chunkBuildOutput.translucentData != null) {
               this.sortTriggering.integrateTranslucentData(oldData, chunkBuildOutput.translucentData, this.cameraPosition, this::scheduleSort);
               result.render.setTranslucentData(chunkBuildOutput.translucentData);
            }
         } else if (result instanceof ChunkSortOutput sortOutput
            && sortOutput.getTopoSorter() != null
            && result.render.getTranslucentData() instanceof DynamicTopoData data) {
            this.sortTriggering.applyTriggerChanges(data, sortOutput.getTopoSorter(), result.render.getPosition(), this.cameraPosition);
         }

         CancellationToken job = result.render.getTaskCancellationToken();
         if (job != null && result.submitTime >= result.render.getLastSubmittedFrame()) {
            result.render.setTaskCancellationToken(null);
         }

         result.render.setLastUploadFrame(result.submitTime);
      }

      return touchedSectionInfo;
   }

   private boolean updateSectionInfo(RenderSection render, BuiltSectionInfo info) {
      boolean infoChanged = render.setInfo(info);
      return info != null && !ArrayUtils.isEmpty(info.globalBlockEntities)
         ? this.sectionsWithGlobalEntities.add(render) || infoChanged
         : this.sectionsWithGlobalEntities.remove(render) || infoChanged;
   }

   private static List<BuilderTaskOutput> filterChunkBuildResults(ArrayList<BuilderTaskOutput> outputs) {
      Reference2ReferenceLinkedOpenHashMap<RenderSection, BuilderTaskOutput> map = new Reference2ReferenceLinkedOpenHashMap();

      for (BuilderTaskOutput output : outputs) {
         if (!output.render.isDisposed() && output.render.getLastUploadFrame() <= output.submitTime) {
            RenderSection render = output.render;
            BuilderTaskOutput previous = (BuilderTaskOutput)map.get(render);
            if (previous == null || previous.submitTime < output.submitTime) {
               map.put(render, output);
            }
         }
      }

      return new ArrayList<>(map.values());
   }

   private ArrayList<BuilderTaskOutput> collectChunkBuildResults() {
      ArrayList<BuilderTaskOutput> results = new ArrayList<>();

      ChunkJobResult<? extends BuilderTaskOutput> result;
      while ((result = this.buildResults.poll()) != null) {
         results.add(result.unwrap());
      }

      return results;
   }

   public void cleanupAndFlip() {
      this.sectionCache.cleanup();
      this.regions.update();
   }

   public void updateChunks(boolean updateImmediately) {
      ChunkJobCollector thisFrameBlockingCollector = this.lastBlockingCollector;
      this.lastBlockingCollector = null;
      if (thisFrameBlockingCollector == null) {
         thisFrameBlockingCollector = new ChunkJobCollector(this.buildResults::add);
      }

      if (updateImmediately) {
         this.submitSectionTasks(thisFrameBlockingCollector, thisFrameBlockingCollector, thisFrameBlockingCollector);
         thisFrameBlockingCollector.awaitCompletion(this.builder);
      } else {
         ChunkJobCollector nextFrameBlockingCollector = new ChunkJobCollector(this.buildResults::add);
         ChunkJobCollector deferredCollector = new ChunkJobCollector(
            this.builder.getHighEffortSchedulingBudget(), this.builder.getLowEffortSchedulingBudget(), this.buildResults::add
         );
         if (SodiumClientMod.options().debug.getSortBehavior().getDeferMode() == SortBehavior.DeferMode.ZERO_FRAMES) {
            this.submitSectionTasks(thisFrameBlockingCollector, nextFrameBlockingCollector, deferredCollector);
         } else {
            this.submitSectionTasks(nextFrameBlockingCollector, nextFrameBlockingCollector, deferredCollector);
         }

         thisFrameBlockingCollector.awaitCompletion(this.builder);
         this.lastBlockingCollector = nextFrameBlockingCollector;
      }
   }

   private void submitSectionTasks(ChunkJobCollector importantCollector, ChunkJobCollector semiImportantCollector, ChunkJobCollector deferredCollector) {
      this.submitSectionTasks(importantCollector, ChunkUpdateType.IMPORTANT_SORT, true);
      this.submitSectionTasks(semiImportantCollector, ChunkUpdateType.IMPORTANT_REBUILD, true);
      this.submitSectionTasks(deferredCollector, ChunkUpdateType.REBUILD, false);
      this.submitSectionTasks(deferredCollector, ChunkUpdateType.INITIAL_BUILD, false);
      this.submitSectionTasks(deferredCollector, ChunkUpdateType.SORT, true);
   }

   private void submitSectionTasks(ChunkJobCollector collector, ChunkUpdateType type, boolean ignoreEffortCategory) {
      ArrayDeque<RenderSection> queue = this.taskLists.get(type);

      while (!queue.isEmpty() && collector.hasBudgetFor(type.getTaskEffort(), ignoreEffortCategory)) {
         RenderSection section = queue.remove();
         if (!section.isDisposed()) {
            ChunkUpdateType pendingUpdate = section.getPendingUpdate();
            if (pendingUpdate == null || pendingUpdate == type) {
               int frame = this.lastUpdatedFrame;
               ChunkBuilderTask<? extends BuilderTaskOutput> task;
               if (type != ChunkUpdateType.SORT && type != ChunkUpdateType.IMPORTANT_SORT) {
                  task = this.createRebuildTask(section, frame);
                  if (task == null) {
                     ChunkJobResult<ChunkBuildOutput> result = ChunkJobResult.successfully(
                        new ChunkBuildOutput(section, frame, NoData.forEmptySection(section.getPosition()), BuiltSectionInfo.EMPTY, Collections.emptyMap())
                     );
                     this.buildResults.add(result);
                     section.setTaskCancellationToken(null);
                  }
               } else {
                  task = this.createSortTask(section, frame);
                  if (task == null) {
                     continue;
                  }
               }

               if (task != null) {
                  ChunkJobTyped<? extends ChunkBuilderTask<?>, ?> job = this.builder.scheduleTask(task, type.isImportant(), collector::onJobFinished);
                  collector.addSubmittedJob(job);
                  section.setTaskCancellationToken(job);
               }

               section.setLastSubmittedFrame(frame);
               section.setPendingUpdate(null);
            }
         }
      }
   }

   @Nullable
   public ChunkBuilderMeshingTask createRebuildTask(RenderSection render, int frame) {
      ChunkRenderContext context = LevelSlice.prepare(this.level, render.getPosition(), this.sectionCache);
      return context == null ? null : new ChunkBuilderMeshingTask(render, frame, this.cameraPosition, context);
   }

   public ChunkBuilderSortingTask createSortTask(RenderSection render, int frame) {
      return ChunkBuilderSortingTask.createTask(render, frame, this.cameraPosition);
   }

   public void processGFNIMovement(CameraMovement movement) {
      this.sortTriggering.triggerSections(this::scheduleSort, movement);
   }

   public void markGraphDirty() {
      this.needsGraphUpdate = true;
   }

   public boolean needsUpdate() {
      return this.needsGraphUpdate;
   }

   public ChunkBuilder getBuilder() {
      return this.builder;
   }

   public void destroy() {
      this.builder.shutdown();

      for (BuilderTaskOutput result : this.collectChunkBuildResults()) {
         result.destroy();
      }

      ObjectIterator var6 = this.sectionByPosition.values().iterator();

      while (var6.hasNext()) {
         RenderSection section = (RenderSection)var6.next();
         section.delete();
      }

      this.sectionsWithGlobalEntities.clear();
      this.resetRenderLists();

      try (CommandList commandList = RenderDevice.INSTANCE.createCommandList()) {
         this.regions.delete(commandList);
         this.chunkRenderer.delete(commandList);
      }
   }

   public int getTotalSections() {
      return this.sectionByPosition.size();
   }

   public int getVisibleChunkCount() {
      int sections = 0;

      for (ChunkRenderList renderList : this.renderLists) {
         sections += renderList.getSectionsWithGeometryCount();
      }

      return sections;
   }

   public void scheduleSort(long sectionPos, boolean isDirectTrigger) {
      RenderSection section = (RenderSection)this.sectionByPosition.get(sectionPos);
      if (section != null) {
         ChunkUpdateType pendingUpdate = ChunkUpdateType.SORT;
         SortBehavior.PriorityMode priorityMode = SodiumClientMod.options().debug.getSortBehavior().getPriorityMode();
         if (priorityMode == SortBehavior.PriorityMode.ALL
            || priorityMode == SortBehavior.PriorityMode.NEARBY && this.shouldPrioritizeTask(section, NEARBY_SORT_DISTANCE)) {
            pendingUpdate = ChunkUpdateType.IMPORTANT_SORT;
         }

         pendingUpdate = ChunkUpdateType.getPromotionUpdateType(section.getPendingUpdate(), pendingUpdate);
         if (pendingUpdate != null) {
            section.setPendingUpdate(pendingUpdate);
            section.prepareTrigger(isDirectTrigger);
         }
      }
   }

   public void scheduleRebuild(int x, int y, int z, boolean important) {
      RenderAsserts.validateCurrentThread();
      this.sectionCache.invalidate(x, y, z);
      RenderSection section = (RenderSection)this.sectionByPosition.get(class_4076.method_18685(x, y, z));
      if (section != null && section.isBuilt()) {
         ChunkUpdateType pendingUpdate;
         if (!allowImportantRebuilds() || !important && !this.shouldPrioritizeTask(section, NEARBY_REBUILD_DISTANCE)) {
            pendingUpdate = ChunkUpdateType.REBUILD;
         } else {
            pendingUpdate = ChunkUpdateType.IMPORTANT_REBUILD;
         }

         pendingUpdate = ChunkUpdateType.getPromotionUpdateType(section.getPendingUpdate(), pendingUpdate);
         if (pendingUpdate != null) {
            section.setPendingUpdate(pendingUpdate);
            this.needsGraphUpdate = true;
         }
      }
   }

   private boolean shouldPrioritizeTask(RenderSection section, float distance) {
      return this.cameraBlockPos != null && section.getSquaredDistance(this.cameraBlockPos) < distance;
   }

   private static boolean allowImportantRebuilds() {
      return !SodiumClientMod.options().performance.alwaysDeferChunkUpdates;
   }

   private float getEffectiveRenderDistance() {
      float[] color = RenderSystem.getShaderFogColor();
      float distance = RenderSystem.getShaderFogEnd();
      float renderDistance = this.getRenderDistance();
      return !class_3532.method_15347(color[3], 1.0F) ? renderDistance : Math.min(renderDistance, distance + 0.5F);
   }

   private float getRenderDistance() {
      return this.renderDistance * 16.0F;
   }

   private void connectNeighborNodes(RenderSection render) {
      for (int direction = 0; direction < 6; direction++) {
         RenderSection adj = this.getRenderSection(
            render.getChunkX() + GraphDirection.x(direction),
            render.getChunkY() + GraphDirection.y(direction),
            render.getChunkZ() + GraphDirection.z(direction)
         );
         if (adj != null) {
            adj.setAdjacentNode(GraphDirection.opposite(direction), render);
            render.setAdjacentNode(direction, adj);
         }
      }
   }

   private void disconnectNeighborNodes(RenderSection render) {
      for (int direction = 0; direction < 6; direction++) {
         RenderSection adj = render.getAdjacent(direction);
         if (adj != null) {
            adj.setAdjacentNode(GraphDirection.opposite(direction), null);
            render.setAdjacentNode(direction, null);
         }
      }
   }

   private RenderSection getRenderSection(int x, int y, int z) {
      return (RenderSection)this.sectionByPosition.get(class_4076.method_18685(x, y, z));
   }

   public Collection<String> getDebugStrings() {
      List<String> list = new ArrayList<>();
      int count = 0;
      long deviceUsed = 0L;
      long deviceAllocated = 0L;

      for (RenderRegion region : this.regions.getLoadedRegions()) {
         RenderRegion.DeviceResources resources = region.getResources();
         if (resources != null) {
            GlBufferArena buffer = resources.getGeometryArena();
            deviceUsed += buffer.getDeviceUsedMemory();
            deviceAllocated += buffer.getDeviceAllocatedMemory();
            count++;
         }
      }

      list.add(String.format("Geometry Pool: %d/%d MiB (%d buffers)", MathUtil.toMib(deviceUsed), MathUtil.toMib(deviceAllocated), count));
      list.add(String.format("Transfer Queue: %s", this.regions.getStagingBuffer().toString()));
      list.add(
         String.format(
            "Chunk Builder: Permits=%02d (E %03d) | Busy=%02d | Total=%02d",
            this.builder.getScheduledJobCount(),
            this.builder.getScheduledEffort(),
            this.builder.getBusyThreadCount(),
            this.builder.getTotalThreadCount()
         )
      );
      list.add(
         String.format(
            "Chunk Queues: U=%02d (P0=%03d | P1=%03d | P2=%03d)",
            this.buildResults.size(),
            this.taskLists.get(ChunkUpdateType.IMPORTANT_REBUILD).size() + this.taskLists.get(ChunkUpdateType.IMPORTANT_SORT).size(),
            this.taskLists.get(ChunkUpdateType.REBUILD).size() + this.taskLists.get(ChunkUpdateType.SORT).size(),
            this.taskLists.get(ChunkUpdateType.INITIAL_BUILD).size()
         )
      );
      this.sortTriggering.addDebugStrings(list);
      return list;
   }

   @NotNull
   public SortedRenderLists getRenderLists() {
      return this.renderLists;
   }

   public boolean isSectionBuilt(int x, int y, int z) {
      RenderSection section = this.getRenderSection(x, y, z);
      return section != null && section.isBuilt();
   }

   public void onChunkAdded(int x, int z) {
      for (int y = this.level.method_32891(); y < this.level.method_31597(); y++) {
         this.onSectionAdded(x, y, z);
      }
   }

   public void onChunkRemoved(int x, int z) {
      for (int y = this.level.method_32891(); y < this.level.method_31597(); y++) {
         this.onSectionRemoved(x, y, z);
      }
   }

   public Collection<RenderSection> getSectionsWithGlobalEntities() {
      return ReferenceSets.unmodifiable(this.sectionsWithGlobalEntities);
   }
}
