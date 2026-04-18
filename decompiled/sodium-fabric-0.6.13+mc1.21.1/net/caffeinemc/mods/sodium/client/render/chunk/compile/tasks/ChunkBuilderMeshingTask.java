package net.caffeinemc.mods.sodium.client.render.chunk.compile.tasks;

import it.unimi.dsi.fastutil.objects.Reference2ReferenceOpenHashMap;
import java.util.Map;
import net.caffeinemc.mods.sodium.client.SodiumClientMod;
import net.caffeinemc.mods.sodium.client.render.chunk.ExtendedBlockEntityType;
import net.caffeinemc.mods.sodium.client.render.chunk.RenderSection;
import net.caffeinemc.mods.sodium.client.render.chunk.compile.ChunkBuildBuffers;
import net.caffeinemc.mods.sodium.client.render.chunk.compile.ChunkBuildContext;
import net.caffeinemc.mods.sodium.client.render.chunk.compile.ChunkBuildOutput;
import net.caffeinemc.mods.sodium.client.render.chunk.compile.pipeline.BlockRenderCache;
import net.caffeinemc.mods.sodium.client.render.chunk.compile.pipeline.BlockRenderer;
import net.caffeinemc.mods.sodium.client.render.chunk.data.BuiltSectionInfo;
import net.caffeinemc.mods.sodium.client.render.chunk.data.BuiltSectionMeshParts;
import net.caffeinemc.mods.sodium.client.render.chunk.terrain.DefaultTerrainRenderPasses;
import net.caffeinemc.mods.sodium.client.render.chunk.terrain.TerrainRenderPass;
import net.caffeinemc.mods.sodium.client.render.chunk.terrain.material.DefaultMaterials;
import net.caffeinemc.mods.sodium.client.render.chunk.translucent_sorting.SortBehavior;
import net.caffeinemc.mods.sodium.client.render.chunk.translucent_sorting.SortType;
import net.caffeinemc.mods.sodium.client.render.chunk.translucent_sorting.TranslucentGeometryCollector;
import net.caffeinemc.mods.sodium.client.render.chunk.translucent_sorting.data.PresentTranslucentData;
import net.caffeinemc.mods.sodium.client.render.chunk.translucent_sorting.data.Sorter;
import net.caffeinemc.mods.sodium.client.render.chunk.translucent_sorting.data.TranslucentData;
import net.caffeinemc.mods.sodium.client.services.PlatformLevelRenderHooks;
import net.caffeinemc.mods.sodium.client.util.task.CancellationToken;
import net.caffeinemc.mods.sodium.client.world.LevelSlice;
import net.caffeinemc.mods.sodium.client.world.cloned.ChunkRenderContext;
import net.minecraft.class_1087;
import net.minecraft.class_128;
import net.minecraft.class_129;
import net.minecraft.class_148;
import net.minecraft.class_2338;
import net.minecraft.class_2464;
import net.minecraft.class_2586;
import net.minecraft.class_2680;
import net.minecraft.class_310;
import net.minecraft.class_3610;
import net.minecraft.class_827;
import net.minecraft.class_852;
import net.minecraft.class_2338.class_2339;
import org.joml.Vector3dc;

public class ChunkBuilderMeshingTask extends ChunkBuilderTask<ChunkBuildOutput> {
   private final ChunkRenderContext renderContext;

   public ChunkBuilderMeshingTask(RenderSection render, int buildTime, Vector3dc absoluteCameraPos, ChunkRenderContext renderContext) {
      super(render, buildTime, absoluteCameraPos);
      this.renderContext = renderContext;
   }

   public ChunkBuildOutput execute(ChunkBuildContext buildContext, CancellationToken cancellationToken) {
      BuiltSectionInfo.Builder renderData = new BuiltSectionInfo.Builder();
      class_852 occluder = new class_852();
      ChunkBuildBuffers buffers = buildContext.buffers;
      buffers.init(renderData, this.render.getSectionIndex());
      BlockRenderCache cache = buildContext.cache;
      cache.init(this.renderContext);
      LevelSlice slice = cache.getWorldSlice();
      int minX = this.render.getOriginX();
      int minY = this.render.getOriginY();
      int minZ = this.render.getOriginZ();
      int maxX = minX + 16;
      int maxY = minY + 16;
      int maxZ = minZ + 16;
      class_2339 blockPos = new class_2339(minX, minY, minZ);
      class_2339 modelOffset = new class_2339();
      TranslucentGeometryCollector collector;
      if (SodiumClientMod.options().debug.getSortBehavior() != SortBehavior.OFF) {
         collector = new TranslucentGeometryCollector(this.render.getPosition());
      } else {
         collector = null;
      }

      BlockRenderer blockRenderer = cache.getBlockRenderer();
      blockRenderer.prepare(buffers, slice, collector);

      try {
         for (int y = minY; y < maxY; y++) {
            if (cancellationToken.isCancelled()) {
               return null;
            }

            for (int z = minZ; z < maxZ; z++) {
               for (int x = minX; x < maxX; x++) {
                  class_2680 blockState = slice.getBlockState(x, y, z);
                  if (!blockState.method_26215() || blockState.method_31709()) {
                     blockPos.method_10103(x, y, z);
                     modelOffset.method_10103(x & 15, y & 15, z & 15);
                     if (blockState.method_26217() == class_2464.field_11458) {
                        class_1087 model = cache.getBlockModels().method_3335(blockState);
                        blockRenderer.renderModel(model, blockState, blockPos, modelOffset);
                     }

                     class_3610 fluidState = blockState.method_26227();
                     if (!fluidState.method_15769()) {
                        cache.getFluidRenderer().render(slice, blockState, fluidState, blockPos, modelOffset, collector, buffers);
                     }

                     if (blockState.method_31709()) {
                        class_2586 entity = slice.method_8321(blockPos);
                        if (entity != null && ExtendedBlockEntityType.shouldRender(entity.method_11017(), slice, blockPos, entity)) {
                           class_827<class_2586> renderer = class_310.method_1551().method_31975().method_3550(entity);
                           if (renderer != null) {
                              renderData.addBlockEntity(entity, !renderer.method_3563(entity));
                           }
                        }
                     }

                     if (blockState.method_26216(slice, blockPos)) {
                        occluder.method_3682(blockPos);
                     }
                  }
               }
            }
         }
      } catch (class_148 var25) {
         throw this.fillCrashInfo(var25.method_631(), slice, blockPos);
      } catch (Exception var26) {
         throw this.fillCrashInfo(class_128.method_560(var26, "Encountered exception while building chunk meshes"), slice, blockPos);
      }

      PlatformLevelRenderHooks.INSTANCE
         .runChunkMeshAppenders(
            this.renderContext.getRenderers(),
            type -> buffers.get(DefaultMaterials.forRenderLayer(type)).asFallbackVertexConsumer(DefaultMaterials.forRenderLayer(type), collector),
            slice
         );
      blockRenderer.release();
      SortType sortType = SortType.NONE;
      if (collector != null) {
         sortType = collector.finishRendering();
      }

      Map<TerrainRenderPass, BuiltSectionMeshParts> meshes = new Reference2ReferenceOpenHashMap();

      for (TerrainRenderPass pass : DefaultTerrainRenderPasses.ALL) {
         BuiltSectionMeshParts mesh = buffers.createMesh(pass, pass.isTranslucent() && sortType.needsDirectionMixing);
         if (mesh != null) {
            meshes.put(pass, mesh);
            renderData.addRenderPass(pass);
         }
      }

      if (cancellationToken.isCancelled()) {
         meshes.forEach((passx, mesh) -> mesh.getVertexData().free());
         return null;
      } else {
         renderData.setOcclusionData(occluder.method_3679());
         boolean reuseUploadedData = false;
         TranslucentData translucentData = null;
         if (collector != null) {
            TranslucentData oldData = this.render.getTranslucentData();
            translucentData = collector.getTranslucentData(oldData, meshes.get(DefaultTerrainRenderPasses.TRANSLUCENT), this);
            reuseUploadedData = translucentData == oldData;
         }

         ChunkBuildOutput output = new ChunkBuildOutput(this.render, this.submitTime, translucentData, renderData.build(), meshes);
         if (collector != null) {
            if (reuseUploadedData) {
               output.markAsReusingUploadedData();
            } else if (translucentData instanceof PresentTranslucentData present) {
               Sorter sorter = present.getSorter();
               sorter.writeIndexBuffer(this, true);
               output.copyResultFrom(sorter);
            }
         }

         return output;
      }
   }

   private class_148 fillCrashInfo(class_128 report, LevelSlice slice, class_2338 pos) {
      class_129 crashReportSection = report.method_556("Block being rendered", 1);
      class_2680 state = null;

      try {
         state = slice.method_8320(pos);
      } catch (Exception var7) {
      }

      class_129.method_586(crashReportSection, slice, pos, state);
      crashReportSection.method_578("Chunk section", this.render);
      if (this.renderContext != null) {
         crashReportSection.method_578("Render context volume", this.renderContext.getVolume());
      }

      return new class_148(report);
   }

   @Override
   public int getEffort() {
      return 10;
   }
}
