package net.caffeinemc.mods.sodium.client.render;

import com.llamalad7.mixinextras.sugar.ref.LocalBooleanRef;
import com.mojang.blaze3d.systems.RenderSystem;
import it.unimi.dsi.fastutil.longs.Long2ObjectMap;
import java.util.Collection;
import java.util.SortedSet;
import java.util.function.Consumer;
import net.caffeinemc.mods.sodium.client.SodiumClientMod;
import net.caffeinemc.mods.sodium.client.gl.device.CommandList;
import net.caffeinemc.mods.sodium.client.gl.device.RenderDevice;
import net.caffeinemc.mods.sodium.client.render.chunk.ChunkRenderMatrices;
import net.caffeinemc.mods.sodium.client.render.chunk.RenderSection;
import net.caffeinemc.mods.sodium.client.render.chunk.RenderSectionManager;
import net.caffeinemc.mods.sodium.client.render.chunk.lists.ChunkRenderList;
import net.caffeinemc.mods.sodium.client.render.chunk.map.ChunkTracker;
import net.caffeinemc.mods.sodium.client.render.chunk.map.ChunkTrackerHolder;
import net.caffeinemc.mods.sodium.client.render.chunk.region.RenderRegion;
import net.caffeinemc.mods.sodium.client.render.chunk.terrain.DefaultTerrainRenderPasses;
import net.caffeinemc.mods.sodium.client.render.chunk.translucent_sorting.trigger.CameraMovement;
import net.caffeinemc.mods.sodium.client.render.viewport.Viewport;
import net.caffeinemc.mods.sodium.client.services.PlatformBlockAccess;
import net.caffeinemc.mods.sodium.client.util.NativeBuffer;
import net.caffeinemc.mods.sodium.client.util.iterator.ByteIterator;
import net.caffeinemc.mods.sodium.client.world.LevelRendererExtension;
import net.minecraft.class_1088;
import net.minecraft.class_1297;
import net.minecraft.class_1921;
import net.minecraft.class_2338;
import net.minecraft.class_238;
import net.minecraft.class_243;
import net.minecraft.class_2586;
import net.minecraft.class_310;
import net.minecraft.class_3191;
import net.minecraft.class_3532;
import net.minecraft.class_3695;
import net.minecraft.class_4076;
import net.minecraft.class_4184;
import net.minecraft.class_4583;
import net.minecraft.class_4587;
import net.minecraft.class_4588;
import net.minecraft.class_4597;
import net.minecraft.class_4599;
import net.minecraft.class_4720;
import net.minecraft.class_638;
import net.minecraft.class_746;
import net.minecraft.class_824;
import net.minecraft.class_4587.class_4665;
import net.minecraft.class_4597.class_4598;
import org.joml.Matrix4f;
import org.joml.Vector3d;

public class SodiumWorldRenderer {
   private final class_310 client;
   private class_638 level;
   private int renderDistance;
   private Vector3d lastCameraPos;
   private double lastCameraPitch;
   private double lastCameraYaw;
   private float lastFogDistance;
   private Matrix4f lastProjectionMatrix;
   private boolean useEntityCulling;
   private RenderSectionManager renderSectionManager;
   private static final double MAX_ENTITY_CHECK_VOLUME = 61440.0;

   public static SodiumWorldRenderer instance() {
      SodiumWorldRenderer instance = instanceNullable();
      if (instance == null) {
         throw new IllegalStateException("No renderer attached to active level");
      } else {
         return instance;
      }
   }

   public static SodiumWorldRenderer instanceNullable() {
      return class_310.method_1551().field_1769 instanceof LevelRendererExtension extension ? extension.sodium$getWorldRenderer() : null;
   }

   public SodiumWorldRenderer(class_310 client) {
      this.client = client;
   }

   public void setLevel(class_638 level) {
      if (this.level != level) {
         if (this.level != null) {
            this.unloadLevel();
         }

         if (level != null) {
            this.loadLevel(level);
         }
      }
   }

   private void loadLevel(class_638 level) {
      this.level = level;

      try (CommandList commandList = RenderDevice.INSTANCE.createCommandList()) {
         this.initRenderer(commandList);
      }
   }

   private void unloadLevel() {
      if (this.renderSectionManager != null) {
         this.renderSectionManager.destroy();
         this.renderSectionManager = null;
      }

      this.level = null;
   }

   public int getVisibleChunkCount() {
      return this.renderSectionManager.getVisibleChunkCount();
   }

   public void scheduleTerrainUpdate() {
      if (this.renderSectionManager != null) {
         this.renderSectionManager.markGraphDirty();
      }
   }

   public boolean isTerrainRenderComplete() {
      return this.renderSectionManager.getBuilder().isBuildQueueEmpty();
   }

   public void setupTerrain(class_4184 camera, Viewport viewport, boolean spectator, boolean updateChunksImmediately) {
      NativeBuffer.reclaim(false);
      this.processChunkEvents();
      this.useEntityCulling = SodiumClientMod.options().performance.useEntityCulling;
      if (this.client.field_1690.method_38521() != this.renderDistance) {
         this.reload();
      }

      class_3695 profiler = this.client.method_16011();
      profiler.method_15396("camera_setup");
      class_746 player = this.client.field_1724;
      if (player == null) {
         throw new IllegalStateException("Client instance has no active player entity");
      } else {
         class_243 posRaw = camera.method_19326();
         Vector3d pos = new Vector3d(posRaw.method_10216(), posRaw.method_10214(), posRaw.method_10215());
         Matrix4f projectionMatrix = new Matrix4f(RenderSystem.getProjectionMatrix());
         float pitch = camera.method_19329();
         float yaw = camera.method_19330();
         float fogDistance = RenderSystem.getShaderFogEnd();
         if (this.lastCameraPos == null) {
            this.lastCameraPos = new Vector3d(pos);
         }

         if (this.lastProjectionMatrix == null) {
            this.lastProjectionMatrix = new Matrix4f(projectionMatrix);
         }

         boolean cameraLocationChanged = !pos.equals(this.lastCameraPos);
         boolean cameraAngleChanged = pitch != this.lastCameraPitch || yaw != this.lastCameraYaw || fogDistance != this.lastFogDistance;
         boolean cameraProjectionChanged = !projectionMatrix.equals(this.lastProjectionMatrix);
         this.lastProjectionMatrix = projectionMatrix;
         this.lastCameraPitch = pitch;
         this.lastCameraYaw = yaw;
         if (cameraLocationChanged || cameraAngleChanged || cameraProjectionChanged) {
            this.renderSectionManager.markGraphDirty();
         }

         this.lastFogDistance = fogDistance;
         this.renderSectionManager.updateCameraState(pos, camera);
         if (cameraLocationChanged) {
            profiler.method_15405("translucent_triggering");
            this.renderSectionManager.processGFNIMovement(new CameraMovement(this.lastCameraPos, pos));
            this.lastCameraPos = new Vector3d(pos);
         }

         int maxChunkUpdates = updateChunksImmediately ? this.renderDistance : 1;

         for (int i = 0; i < maxChunkUpdates; i++) {
            if (this.renderSectionManager.needsUpdate()) {
               profiler.method_15405("chunk_render_lists");
               this.renderSectionManager.update(camera, viewport, spectator);
            }

            profiler.method_15405("chunk_update");
            this.renderSectionManager.cleanupAndFlip();
            this.renderSectionManager.updateChunks(updateChunksImmediately);
            profiler.method_15405("chunk_upload");
            this.renderSectionManager.uploadChunks();
            if (!this.renderSectionManager.needsUpdate()) {
               break;
            }
         }

         profiler.method_15405("chunk_render_tick");
         this.renderSectionManager.tickVisibleRenders();
         profiler.method_15407();
         class_1297.method_5840(
            class_3532.method_15350(this.client.field_1690.method_38521() / 8.0, 1.0, 2.5) * (Double)this.client.field_1690.method_42517().method_41753()
         );
      }
   }

   private void processChunkEvents() {
      ChunkTracker tracker = ChunkTrackerHolder.get(this.level);
      tracker.forEachEvent(this.renderSectionManager::onChunkAdded, this.renderSectionManager::onChunkRemoved);
   }

   public void drawChunkLayer(class_1921 renderLayer, ChunkRenderMatrices matrices, double x, double y, double z) {
      if (renderLayer == class_1921.method_23577()) {
         this.renderSectionManager.renderLayer(matrices, DefaultTerrainRenderPasses.SOLID, x, y, z);
         this.renderSectionManager.renderLayer(matrices, DefaultTerrainRenderPasses.CUTOUT, x, y, z);
      } else if (renderLayer == class_1921.method_23583()) {
         this.renderSectionManager.renderLayer(matrices, DefaultTerrainRenderPasses.TRANSLUCENT, x, y, z);
      }
   }

   public void reload() {
      if (this.level != null) {
         try (CommandList commandList = RenderDevice.INSTANCE.createCommandList()) {
            this.initRenderer(commandList);
         }
      }
   }

   private void initRenderer(CommandList commandList) {
      if (this.renderSectionManager != null) {
         this.renderSectionManager.destroy();
         this.renderSectionManager = null;
      }

      this.renderDistance = this.client.field_1690.method_38521();
      this.renderSectionManager = new RenderSectionManager(this.level, this.renderDistance, commandList);
      ChunkTracker tracker = ChunkTrackerHolder.get(this.level);
      ChunkTracker.forEachChunk(tracker.getReadyChunks(), this.renderSectionManager::onChunkAdded);
   }

   public void renderBlockEntities(
      class_4587 matrices,
      class_4599 bufferBuilders,
      Long2ObjectMap<SortedSet<class_3191>> blockBreakingProgressions,
      class_4184 camera,
      float tickDelta,
      LocalBooleanRef isGlowing
   ) {
      class_4598 immediate = bufferBuilders.method_23000();
      class_243 cameraPos = camera.method_19326();
      double x = cameraPos.method_10216();
      double y = cameraPos.method_10214();
      double z = cameraPos.method_10215();
      class_746 player = this.client.field_1724;
      if (player == null) {
         throw new IllegalStateException("Client instance has no active player entity");
      } else {
         class_824 blockEntityRenderer = class_310.method_1551().method_31975();
         this.renderBlockEntities(matrices, bufferBuilders, blockBreakingProgressions, tickDelta, immediate, x, y, z, blockEntityRenderer, player, isGlowing);
         this.renderGlobalBlockEntities(
            matrices, bufferBuilders, blockBreakingProgressions, tickDelta, immediate, x, y, z, blockEntityRenderer, player, isGlowing
         );
      }
   }

   private void renderBlockEntities(
      class_4587 matrices,
      class_4599 bufferBuilders,
      Long2ObjectMap<SortedSet<class_3191>> blockBreakingProgressions,
      float tickDelta,
      class_4598 immediate,
      double x,
      double y,
      double z,
      class_824 blockEntityRenderer,
      class_746 player,
      LocalBooleanRef isGlowing
   ) {
      for (ChunkRenderList renderList : this.renderSectionManager.getRenderLists()) {
         RenderRegion renderRegion = renderList.getRegion();
         ByteIterator renderSectionIterator = renderList.sectionsWithEntitiesIterator();
         if (renderSectionIterator != null) {
            while (renderSectionIterator.hasNext()) {
               int renderSectionId = renderSectionIterator.nextByteAsInt();
               RenderSection renderSection = renderRegion.getSection(renderSectionId);
               class_2586[] blockEntities = renderSection.getCulledBlockEntities();
               if (blockEntities != null) {
                  for (class_2586 blockEntity : blockEntities) {
                     renderBlockEntity(
                        matrices, bufferBuilders, blockBreakingProgressions, tickDelta, immediate, x, y, z, blockEntityRenderer, blockEntity, player, isGlowing
                     );
                  }
               }
            }
         }
      }
   }

   private void renderGlobalBlockEntities(
      class_4587 matrices,
      class_4599 bufferBuilders,
      Long2ObjectMap<SortedSet<class_3191>> blockBreakingProgressions,
      float tickDelta,
      class_4598 immediate,
      double x,
      double y,
      double z,
      class_824 blockEntityRenderer,
      class_746 player,
      LocalBooleanRef isGlowing
   ) {
      for (RenderSection renderSection : this.renderSectionManager.getSectionsWithGlobalEntities()) {
         class_2586[] blockEntities = renderSection.getGlobalBlockEntities();
         if (blockEntities != null) {
            for (class_2586 blockEntity : blockEntities) {
               renderBlockEntity(
                  matrices, bufferBuilders, blockBreakingProgressions, tickDelta, immediate, x, y, z, blockEntityRenderer, blockEntity, player, isGlowing
               );
            }
         }
      }
   }

   private static void renderBlockEntity(
      class_4587 matrices,
      class_4599 bufferBuilders,
      Long2ObjectMap<SortedSet<class_3191>> blockBreakingProgressions,
      float tickDelta,
      class_4598 immediate,
      double x,
      double y,
      double z,
      class_824 dispatcher,
      class_2586 entity,
      class_746 player,
      LocalBooleanRef isGlowing
   ) {
      class_2338 pos = entity.method_11016();
      matrices.method_22903();
      matrices.method_22904(pos.method_10263() - x, pos.method_10264() - y, pos.method_10260() - z);
      class_4597 consumer = immediate;
      SortedSet<class_3191> breakingInfo = (SortedSet<class_3191>)blockBreakingProgressions.get(pos.method_10063());
      if (breakingInfo != null && !breakingInfo.isEmpty()) {
         int stage = breakingInfo.last().method_13988();
         if (stage >= 0) {
            class_4588 bufferBuilder = bufferBuilders.method_23001().getBuffer((class_1921)class_1088.field_21772.get(stage));
            class_4665 entry = matrices.method_23760();
            class_4588 transformer = new class_4583(bufferBuilder, entry, 1.0F);
            consumer = layer -> layer.method_23037() ? class_4720.method_24037(transformer, immediate.getBuffer(layer)) : immediate.getBuffer(layer);
         }
      }

      dispatcher.method_3555(entity, tickDelta, matrices, consumer);
      if (isGlowing != null && PlatformBlockAccess.getInstance().shouldBlockEntityGlow(entity, player)) {
         isGlowing.set(true);
      }

      matrices.method_22909();
   }

   public void iterateVisibleBlockEntities(Consumer<class_2586> blockEntityConsumer) {
      for (ChunkRenderList renderList : this.renderSectionManager.getRenderLists()) {
         RenderRegion renderRegion = renderList.getRegion();
         ByteIterator renderSectionIterator = renderList.sectionsWithEntitiesIterator();
         if (renderSectionIterator != null) {
            while (renderSectionIterator.hasNext()) {
               int renderSectionId = renderSectionIterator.nextByteAsInt();
               RenderSection renderSection = renderRegion.getSection(renderSectionId);
               class_2586[] blockEntities = renderSection.getCulledBlockEntities();
               if (blockEntities != null) {
                  for (class_2586 blockEntity : blockEntities) {
                     blockEntityConsumer.accept(blockEntity);
                  }
               }
            }
         }
      }

      for (RenderSection renderSection : this.renderSectionManager.getSectionsWithGlobalEntities()) {
         class_2586[] blockEntities = renderSection.getGlobalBlockEntities();
         if (blockEntities != null) {
            for (class_2586 blockEntity : blockEntities) {
               blockEntityConsumer.accept(blockEntity);
            }
         }
      }
   }

   public boolean isEntityVisible(class_1297 entity) {
      if (!this.useEntityCulling) {
         return true;
      } else if (!this.client.method_27022(entity) && !entity.method_5733()) {
         class_238 bb = entity.method_5830();
         double entityVolume = (bb.field_1320 - bb.field_1323) * (bb.field_1325 - bb.field_1322) * (bb.field_1324 - bb.field_1321);
         return entityVolume > 61440.0 ? true : this.isBoxVisible(bb.field_1323, bb.field_1322, bb.field_1321, bb.field_1320, bb.field_1325, bb.field_1324);
      } else {
         return true;
      }
   }

   public boolean isBoxVisible(double x1, double y1, double z1, double x2, double y2, double z2) {
      if (!(y2 < this.level.method_31607() + 0.5) && !(y1 > this.level.method_31600() - 0.5)) {
         int minX = class_4076.method_32204(x1 - 0.5);
         int minY = class_4076.method_32204(y1 - 0.5);
         int minZ = class_4076.method_32204(z1 - 0.5);
         int maxX = class_4076.method_32204(x2 + 0.5);
         int maxY = class_4076.method_32204(y2 + 0.5);
         int maxZ = class_4076.method_32204(z2 + 0.5);

         for (int x = minX; x <= maxX; x++) {
            for (int z = minZ; z <= maxZ; z++) {
               for (int y = minY; y <= maxY; y++) {
                  if (this.renderSectionManager.isSectionVisible(x, y, z)) {
                     return true;
                  }
               }
            }
         }

         return false;
      } else {
         return true;
      }
   }

   public String getChunksDebugString() {
      return String.format(
         "C: %d/%d D: %d", this.renderSectionManager.getVisibleChunkCount(), this.renderSectionManager.getTotalSections(), this.renderDistance
      );
   }

   public void scheduleRebuildForBlockArea(int minX, int minY, int minZ, int maxX, int maxY, int maxZ, boolean important) {
      this.scheduleRebuildForChunks(minX >> 4, minY >> 4, minZ >> 4, maxX >> 4, maxY >> 4, maxZ >> 4, important);
   }

   public void scheduleRebuildForChunks(int minX, int minY, int minZ, int maxX, int maxY, int maxZ, boolean important) {
      for (int chunkX = minX; chunkX <= maxX; chunkX++) {
         for (int chunkY = minY; chunkY <= maxY; chunkY++) {
            for (int chunkZ = minZ; chunkZ <= maxZ; chunkZ++) {
               this.scheduleRebuildForChunk(chunkX, chunkY, chunkZ, important);
            }
         }
      }
   }

   public void scheduleRebuildForChunk(int x, int y, int z, boolean important) {
      this.renderSectionManager.scheduleRebuild(x, y, z, important);
   }

   public Collection<String> getDebugStrings() {
      return this.renderSectionManager.getDebugStrings();
   }

   public boolean isSectionReady(int x, int y, int z) {
      return this.renderSectionManager.isSectionBuilt(x, y, z);
   }
}
