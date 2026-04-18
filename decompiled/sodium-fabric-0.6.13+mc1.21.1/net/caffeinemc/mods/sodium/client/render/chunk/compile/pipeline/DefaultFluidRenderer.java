package net.caffeinemc.mods.sodium.client.render.chunk.compile.pipeline;

import net.caffeinemc.mods.sodium.api.util.ColorARGB;
import net.caffeinemc.mods.sodium.api.util.NormI8;
import net.caffeinemc.mods.sodium.client.model.color.ColorProvider;
import net.caffeinemc.mods.sodium.client.model.light.LightMode;
import net.caffeinemc.mods.sodium.client.model.light.LightPipeline;
import net.caffeinemc.mods.sodium.client.model.light.LightPipelineProvider;
import net.caffeinemc.mods.sodium.client.model.light.data.QuadLightData;
import net.caffeinemc.mods.sodium.client.model.quad.ModelQuad;
import net.caffeinemc.mods.sodium.client.model.quad.ModelQuadView;
import net.caffeinemc.mods.sodium.client.model.quad.ModelQuadViewMutable;
import net.caffeinemc.mods.sodium.client.model.quad.properties.ModelQuadFacing;
import net.caffeinemc.mods.sodium.client.render.chunk.compile.buffers.ChunkModelBuilder;
import net.caffeinemc.mods.sodium.client.render.chunk.terrain.material.Material;
import net.caffeinemc.mods.sodium.client.render.chunk.translucent_sorting.TranslucentGeometryCollector;
import net.caffeinemc.mods.sodium.client.render.chunk.vertex.builder.ChunkMeshBufferBuilder;
import net.caffeinemc.mods.sodium.client.render.chunk.vertex.format.ChunkVertexEncoder;
import net.caffeinemc.mods.sodium.client.services.PlatformBlockAccess;
import net.caffeinemc.mods.sodium.client.util.DirectionUtil;
import net.caffeinemc.mods.sodium.client.world.LevelSlice;
import net.minecraft.class_1058;
import net.minecraft.class_1920;
import net.minecraft.class_2338;
import net.minecraft.class_2350;
import net.minecraft.class_243;
import net.minecraft.class_259;
import net.minecraft.class_265;
import net.minecraft.class_2680;
import net.minecraft.class_310;
import net.minecraft.class_3486;
import net.minecraft.class_3532;
import net.minecraft.class_3610;
import net.minecraft.class_3611;
import net.minecraft.class_2338.class_2339;
import net.minecraft.class_2350.class_2351;
import org.apache.commons.lang3.mutable.MutableFloat;
import org.apache.commons.lang3.mutable.MutableInt;

public class DefaultFluidRenderer {
   public static final float EPSILON = 0.001F;
   private static final float ALIGNED_EQUALS_EPSILON = 0.011F;
   private final class_2339 scratchPos = new class_2339();
   private final MutableFloat scratchHeight = new MutableFloat(0.0F);
   private final MutableInt scratchSamples = new MutableInt();
   private final BlockOcclusionCache occlusionCache = new BlockOcclusionCache();
   private final ModelQuadViewMutable quad = new ModelQuad();
   private final LightPipelineProvider lighters;
   private final QuadLightData quadLightData = new QuadLightData();
   private final int[] quadColors = new int[4];
   private final float[] brightness = new float[4];
   private final ChunkVertexEncoder.Vertex[] vertices = ChunkVertexEncoder.Vertex.uninitializedQuad();

   public DefaultFluidRenderer(LightPipelineProvider lighters) {
      this.quad.setLightFace(class_2350.field_11036);
      this.lighters = lighters;
   }

   private boolean isFullBlockFluidOccluded(class_1920 world, class_2338 pos, class_2350 dir, class_2680 blockState, class_3610 fluid) {
      return !this.occlusionCache.shouldDrawFullBlockFluidSide(blockState, world, pos, dir, fluid, class_259.method_1077());
   }

   private boolean isSideExposed(class_1920 world, int x, int y, int z, class_2350 dir, float height) {
      class_2338 pos = this.scratchPos.method_10103(x + dir.method_10148(), y + dir.method_10164(), z + dir.method_10165());
      class_2680 blockState = world.method_8320(pos);
      if (blockState.method_26225()) {
         class_265 shape = blockState.method_26201(world, pos);
         if (shape.method_1110()) {
            return true;
         } else {
            class_265 threshold = class_259.method_1081(0.0, 0.0, 0.0, 1.0, height, 1.0);
            return !class_259.method_1083(threshold, shape, dir);
         }
      } else {
         return true;
      }
   }

   public void render(
      LevelSlice level,
      class_2680 blockState,
      class_3610 fluidState,
      class_2338 blockPos,
      class_2338 offset,
      TranslucentGeometryCollector collector,
      ChunkModelBuilder meshBuilder,
      Material material,
      ColorProvider<class_3610> colorProvider,
      class_1058[] sprites
   ) {
      int posX = blockPos.method_10263();
      int posY = blockPos.method_10264();
      int posZ = blockPos.method_10260();
      class_3611 fluid = fluidState.method_15772();
      boolean cullUp = this.isFullBlockFluidOccluded(level, blockPos, class_2350.field_11036, blockState, fluidState);
      boolean cullDown = this.isFullBlockFluidOccluded(level, blockPos, class_2350.field_11033, blockState, fluidState)
         || !this.isSideExposed(level, posX, posY, posZ, class_2350.field_11033, 0.8888889F);
      boolean cullNorth = this.isFullBlockFluidOccluded(level, blockPos, class_2350.field_11043, blockState, fluidState);
      boolean cullSouth = this.isFullBlockFluidOccluded(level, blockPos, class_2350.field_11035, blockState, fluidState);
      boolean cullWest = this.isFullBlockFluidOccluded(level, blockPos, class_2350.field_11039, blockState, fluidState);
      boolean cullEast = this.isFullBlockFluidOccluded(level, blockPos, class_2350.field_11034, blockState, fluidState);
      if (!cullUp || !cullDown || !cullEast || !cullWest || !cullNorth || !cullSouth) {
         boolean isWater = fluidState.method_15767(class_3486.field_15517);
         float fluidHeight = this.fluidHeight(level, fluid, blockPos, class_2350.field_11036);
         float northWestHeight;
         float southWestHeight;
         float southEastHeight;
         float northEastHeight;
         if (fluidHeight >= 1.0F) {
            northWestHeight = 1.0F;
            southWestHeight = 1.0F;
            southEastHeight = 1.0F;
            northEastHeight = 1.0F;
         } else {
            class_2339 scratchPos = new class_2339();
            float heightNorth = this.fluidHeight(level, fluid, scratchPos.method_25505(blockPos, class_2350.field_11043), class_2350.field_11043);
            float heightSouth = this.fluidHeight(level, fluid, scratchPos.method_25505(blockPos, class_2350.field_11035), class_2350.field_11035);
            float heightEast = this.fluidHeight(level, fluid, scratchPos.method_25505(blockPos, class_2350.field_11034), class_2350.field_11034);
            float heightWest = this.fluidHeight(level, fluid, scratchPos.method_25505(blockPos, class_2350.field_11039), class_2350.field_11039);
            northWestHeight = this.fluidCornerHeight(
               level,
               fluid,
               fluidHeight,
               heightNorth,
               heightWest,
               scratchPos.method_10101(blockPos).method_10098(class_2350.field_11043).method_10098(class_2350.field_11039)
            );
            southWestHeight = this.fluidCornerHeight(
               level,
               fluid,
               fluidHeight,
               heightSouth,
               heightWest,
               scratchPos.method_10101(blockPos).method_10098(class_2350.field_11035).method_10098(class_2350.field_11039)
            );
            southEastHeight = this.fluidCornerHeight(
               level,
               fluid,
               fluidHeight,
               heightSouth,
               heightEast,
               scratchPos.method_10101(blockPos).method_10098(class_2350.field_11035).method_10098(class_2350.field_11034)
            );
            northEastHeight = this.fluidCornerHeight(
               level,
               fluid,
               fluidHeight,
               heightNorth,
               heightEast,
               scratchPos.method_10101(blockPos).method_10098(class_2350.field_11043).method_10098(class_2350.field_11034)
            );
         }

         float yOffset = cullDown ? 0.0F : 0.001F;
         ModelQuadViewMutable quad = this.quad;
         LightMode lightMode = isWater && class_310.method_1588() ? LightMode.SMOOTH : LightMode.FLAT;
         LightPipeline lighter = this.lighters.getLighter(lightMode);
         quad.setFlags(0);
         if (!cullUp
            && this.isSideExposed(
               level,
               posX,
               posY,
               posZ,
               class_2350.field_11036,
               Math.min(Math.min(northWestHeight, southWestHeight), Math.min(southEastHeight, northEastHeight))
            )) {
            northWestHeight -= 0.001F;
            southWestHeight -= 0.001F;
            southEastHeight -= 0.001F;
            northEastHeight -= 0.001F;
            class_243 velocity = fluidState.method_15758(level, blockPos);
            class_1058 sprite;
            float u1;
            float u2;
            float u3;
            float u4;
            float v1;
            float v2;
            float v3;
            float v4;
            if (velocity.field_1352 == 0.0 && velocity.field_1350 == 0.0) {
               sprite = sprites[0];
               u1 = sprite.method_4580(0.0F);
               v1 = sprite.method_4570(0.0F);
               u2 = u1;
               v2 = sprite.method_4570(1.0F);
               u3 = sprite.method_4580(1.0F);
               v3 = v2;
               u4 = u3;
               v4 = v1;
            } else {
               sprite = sprites[1];
               float dir = (float)class_3532.method_15349(velocity.field_1350, velocity.field_1352) - (float) (Math.PI / 2);
               float sin = class_3532.method_15374(dir) * 0.25F;
               float cos = class_3532.method_15362(dir) * 0.25F;
               u1 = sprite.method_4580(0.5F + (-cos - sin));
               v1 = sprite.method_4570(0.5F + -cos + sin);
               u2 = sprite.method_4580(0.5F + -cos + sin);
               v2 = sprite.method_4570(0.5F + cos + sin);
               u3 = sprite.method_4580(0.5F + cos + sin);
               v3 = sprite.method_4570(0.5F + (cos - sin));
               u4 = sprite.method_4580(0.5F + (cos - sin));
               v4 = sprite.method_4570(0.5F + (-cos - sin));
            }

            float uAvg = (u1 + u2 + u3 + u4) / 4.0F;
            float vAvg = (v1 + v2 + v3 + v4) / 4.0F;
            float s3 = sprites[0].method_23842();
            u1 = class_3532.method_16439(s3, u1, uAvg);
            u2 = class_3532.method_16439(s3, u2, uAvg);
            u3 = class_3532.method_16439(s3, u3, uAvg);
            u4 = class_3532.method_16439(s3, u4, uAvg);
            v1 = class_3532.method_16439(s3, v1, vAvg);
            v2 = class_3532.method_16439(s3, v2, vAvg);
            v3 = class_3532.method_16439(s3, v3, vAvg);
            v4 = class_3532.method_16439(s3, v4, vAvg);
            quad.setSprite(sprite);
            boolean aligned = isAlignedEquals(northEastHeight, northWestHeight)
               && isAlignedEquals(northWestHeight, southEastHeight)
               && isAlignedEquals(southEastHeight, southWestHeight)
               && isAlignedEquals(southWestHeight, northEastHeight);
            boolean creaseNorthEastSouthWest = aligned
               || northEastHeight > northWestHeight && northEastHeight > southEastHeight
               || northEastHeight < northWestHeight && northEastHeight < southEastHeight
               || southWestHeight > northWestHeight && southWestHeight > southEastHeight
               || southWestHeight < northWestHeight && southWestHeight < southEastHeight;
            if (creaseNorthEastSouthWest) {
               setVertex(quad, 1, 0.0F, northWestHeight, 0.0F, u1, v1);
               setVertex(quad, 2, 0.0F, southWestHeight, 1.0F, u2, v2);
               setVertex(quad, 3, 1.0F, southEastHeight, 1.0F, u3, v3);
               setVertex(quad, 0, 1.0F, northEastHeight, 0.0F, u4, v4);
            } else {
               setVertex(quad, 0, 0.0F, northWestHeight, 0.0F, u1, v1);
               setVertex(quad, 1, 0.0F, southWestHeight, 1.0F, u2, v2);
               setVertex(quad, 2, 1.0F, southEastHeight, 1.0F, u3, v3);
               setVertex(quad, 3, 1.0F, northEastHeight, 0.0F, u4, v4);
            }

            this.updateQuad(quad, level, blockPos, lighter, class_2350.field_11036, ModelQuadFacing.POS_Y, 1.0F, colorProvider, fluidState);
            this.writeQuad(meshBuilder, collector, material, offset, quad, aligned ? ModelQuadFacing.POS_Y : ModelQuadFacing.UNASSIGNED, false);
            if (fluidState.method_15756(level, this.scratchPos.method_10103(posX, posY + 1, posZ))) {
               this.writeQuad(meshBuilder, collector, material, offset, quad, aligned ? ModelQuadFacing.NEG_Y : ModelQuadFacing.UNASSIGNED, true);
            }
         }

         if (!cullDown) {
            class_1058 spritex = sprites[0];
            float minU = spritex.method_4594();
            float maxU = spritex.method_4577();
            float minV = spritex.method_4593();
            float maxV = spritex.method_4575();
            quad.setSprite(spritex);
            setVertex(quad, 0, 0.0F, yOffset, 1.0F, minU, maxV);
            setVertex(quad, 1, 0.0F, yOffset, 0.0F, minU, minV);
            setVertex(quad, 2, 1.0F, yOffset, 0.0F, maxU, minV);
            setVertex(quad, 3, 1.0F, yOffset, 1.0F, maxU, maxV);
            this.updateQuad(quad, level, blockPos, lighter, class_2350.field_11033, ModelQuadFacing.NEG_Y, 1.0F, colorProvider, fluidState);
            this.writeQuad(meshBuilder, collector, material, offset, quad, ModelQuadFacing.NEG_Y, false);
         }

         quad.setFlags(6);

         for (class_2350 dir : DirectionUtil.HORIZONTAL_DIRECTIONS) {
            float c1;
            float c2;
            float x1;
            float z1;
            float x2;
            float z2;
            switch (dir) {
               case field_11043:
                  if (cullNorth) {
                     continue;
                  }

                  c1 = northWestHeight;
                  c2 = northEastHeight;
                  x1 = 0.0F;
                  x2 = 1.0F;
                  z1 = 0.001F;
                  z2 = z1;
                  break;
               case field_11035:
                  if (cullSouth) {
                     continue;
                  }

                  c1 = southEastHeight;
                  c2 = southWestHeight;
                  x1 = 1.0F;
                  x2 = 0.0F;
                  z1 = 0.999F;
                  z2 = z1;
                  break;
               case field_11039:
                  if (cullWest) {
                     continue;
                  }

                  c1 = southWestHeight;
                  c2 = northWestHeight;
                  x1 = 0.001F;
                  x2 = x1;
                  z1 = 1.0F;
                  z2 = 0.0F;
                  break;
               case field_11034:
                  if (!cullEast) {
                     c1 = northEastHeight;
                     c2 = southEastHeight;
                     x1 = 0.999F;
                     x2 = x1;
                     z1 = 0.0F;
                     z2 = 1.0F;
                     break;
                  }
               default:
                  continue;
            }

            if (this.isSideExposed(level, posX, posY, posZ, dir, Math.max(c1, c2))) {
               int adjX = posX + dir.method_10148();
               int adjY = posY + dir.method_10164();
               int adjZ = posZ + dir.method_10165();
               class_1058 spritex = sprites[1];
               boolean isOverlay = false;
               if (sprites.length > 2 && sprites[2] != null) {
                  class_2338 adjPos = this.scratchPos.method_10103(adjX, adjY, adjZ);
                  class_2680 adjBlock = level.method_8320(adjPos);
                  if (PlatformBlockAccess.getInstance().shouldShowFluidOverlay(adjBlock, level, adjPos, fluidState)) {
                     spritex = sprites[2];
                     isOverlay = true;
                  }
               }

               float u1x = spritex.method_4580(0.0F);
               float u2x = spritex.method_4580(0.5F);
               float v1x = spritex.method_4570((1.0F - c1) * 0.5F);
               float v2x = spritex.method_4570((1.0F - c2) * 0.5F);
               float v3x = spritex.method_4570(0.5F);
               quad.setSprite(spritex);
               setVertex(quad, 0, x2, c2, z2, u2x, v2x);
               setVertex(quad, 1, x2, yOffset, z2, u2x, v3x);
               setVertex(quad, 2, x1, yOffset, z1, u1x, v3x);
               setVertex(quad, 3, x1, c1, z1, u1x, v1x);
               float br = dir.method_10166() == class_2351.field_11051 ? 0.8F : 0.6F;
               ModelQuadFacing facing = ModelQuadFacing.fromDirection(dir);
               this.updateQuad(quad, level, blockPos, lighter, dir, facing, br, colorProvider, fluidState);
               this.writeQuad(meshBuilder, collector, material, offset, quad, facing, false);
               if (!isOverlay) {
                  this.writeQuad(meshBuilder, collector, material, offset, quad, facing.getOpposite(), true);
               }
            }
         }
      }
   }

   private static boolean isAlignedEquals(float a, float b) {
      return Math.abs(a - b) <= 0.011F;
   }

   private void updateQuad(
      ModelQuadViewMutable quad,
      LevelSlice level,
      class_2338 pos,
      LightPipeline lighter,
      class_2350 dir,
      ModelQuadFacing facing,
      float brightness,
      ColorProvider<class_3610> colorProvider,
      class_3610 fluidState
   ) {
      int normal;
      if (facing.isAligned()) {
         normal = facing.getPackedAlignedNormal();
      } else {
         normal = quad.calculateNormal();
      }

      quad.setFaceNormal(normal);
      QuadLightData light = this.quadLightData;
      lighter.calculate(quad, pos, light, null, dir, false, false);
      colorProvider.getColors(level, pos, this.scratchPos, fluidState, quad, this.quadColors);

      for (int i = 0; i < 4; i++) {
         this.quadColors[i] = ColorARGB.toABGR(this.quadColors[i]);
         this.brightness[i] = light.br[i] * brightness;
      }
   }

   private void writeQuad(
      ChunkModelBuilder builder,
      TranslucentGeometryCollector collector,
      Material material,
      class_2338 offset,
      ModelQuadView quad,
      ModelQuadFacing facing,
      boolean flip
   ) {
      ChunkVertexEncoder.Vertex[] vertices = this.vertices;

      for (int i = 0; i < 4; i++) {
         ChunkVertexEncoder.Vertex out = vertices[flip ? 3 - i + 1 & 3 : i];
         out.x = offset.method_10263() + quad.getX(i);
         out.y = offset.method_10264() + quad.getY(i);
         out.z = offset.method_10260() + quad.getZ(i);
         out.color = this.quadColors[i];
         out.ao = this.brightness[i];
         out.u = quad.getTexU(i);
         out.v = quad.getTexV(i);
         out.light = this.quadLightData.lm[i];
      }

      class_1058 sprite = quad.getSprite();
      if (sprite != null) {
         builder.addSprite(sprite);
      }

      if (material.isTranslucent() && collector != null) {
         int normal;
         if (facing.isAligned()) {
            normal = facing.getPackedAlignedNormal();
         } else {
            normal = quad.getFaceNormal();
         }

         if (flip) {
            normal = NormI8.flipPacked(normal);
         }

         collector.appendQuad(normal, vertices, facing);
      }

      ChunkMeshBufferBuilder vertexBuffer = builder.getVertexBuffer(facing);
      vertexBuffer.push(vertices, material);
   }

   private static void setVertex(ModelQuadViewMutable quad, int i, float x, float y, float z, float u, float v) {
      quad.setX(i, x);
      quad.setY(i, y);
      quad.setZ(i, z);
      quad.setTexU(i, u);
      quad.setTexV(i, v);
   }

   private float fluidCornerHeight(class_1920 world, class_3611 fluid, float fluidHeight, float fluidHeightX, float fluidHeightY, class_2338 blockPos) {
      if (!(fluidHeightY >= 1.0F) && !(fluidHeightX >= 1.0F)) {
         if (fluidHeightY > 0.0F || fluidHeightX > 0.0F) {
            float height = this.fluidHeight(world, fluid, blockPos, class_2350.field_11036);
            if (height >= 1.0F) {
               return 1.0F;
            }

            this.modifyHeight(this.scratchHeight, this.scratchSamples, height);
         }

         this.modifyHeight(this.scratchHeight, this.scratchSamples, fluidHeight);
         this.modifyHeight(this.scratchHeight, this.scratchSamples, fluidHeightY);
         this.modifyHeight(this.scratchHeight, this.scratchSamples, fluidHeightX);
         float result = this.scratchHeight.floatValue() / this.scratchSamples.intValue();
         this.scratchHeight.setValue(0.0F);
         this.scratchSamples.setValue(0);
         return result;
      } else {
         return 1.0F;
      }
   }

   private void modifyHeight(MutableFloat totalHeight, MutableInt samples, float target) {
      if (target >= 0.8F) {
         totalHeight.add(target * 10.0F);
         samples.add(10);
      } else if (target >= 0.0F) {
         totalHeight.add(target);
         samples.increment();
      }
   }

   private float fluidHeight(class_1920 world, class_3611 fluid, class_2338 blockPos, class_2350 direction) {
      class_2680 blockState = world.method_8320(blockPos);
      class_3610 fluidState = blockState.method_26227();
      if (fluid.method_15780(fluidState.method_15772())) {
         class_3610 fluidStateUp = world.method_8316(blockPos.method_10084());
         return fluid.method_15780(fluidStateUp.method_15772()) ? 1.0F : fluidState.method_20785();
      } else {
         return !blockState.method_51367() ? 0.0F : -1.0F;
      }
   }
}
