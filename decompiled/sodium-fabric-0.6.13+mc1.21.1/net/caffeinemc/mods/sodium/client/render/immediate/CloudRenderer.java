package net.caffeinemc.mods.sodium.client.render.immediate;

import com.mojang.blaze3d.platform.GlStateManager.class_4534;
import com.mojang.blaze3d.platform.GlStateManager.class_4535;
import com.mojang.blaze3d.systems.RenderSystem;
import java.io.InputStream;
import java.util.Objects;
import net.caffeinemc.mods.sodium.api.util.ColorABGR;
import net.caffeinemc.mods.sodium.api.util.ColorARGB;
import net.caffeinemc.mods.sodium.api.util.ColorU8;
import net.caffeinemc.mods.sodium.api.vertex.buffer.VertexBufferWriter;
import net.caffeinemc.mods.sodium.api.vertex.format.common.ColorVertex;
import net.minecraft.class_1011;
import net.minecraft.class_243;
import net.minecraft.class_287;
import net.minecraft.class_289;
import net.minecraft.class_290;
import net.minecraft.class_291;
import net.minecraft.class_2960;
import net.minecraft.class_310;
import net.minecraft.class_3298;
import net.minecraft.class_3532;
import net.minecraft.class_4063;
import net.minecraft.class_4184;
import net.minecraft.class_4587;
import net.minecraft.class_5912;
import net.minecraft.class_5944;
import net.minecraft.class_638;
import net.minecraft.class_6854;
import net.minecraft.class_758;
import net.minecraft.class_9801;
import net.minecraft.class_291.class_8555;
import net.minecraft.class_293.class_5596;
import net.minecraft.class_4587.class_4665;
import net.minecraft.class_758.class_4596;
import org.apache.commons.lang3.Validate;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.joml.Matrix4f;
import org.lwjgl.system.MemoryStack;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CloudRenderer {
   private static final Logger LOGGER = LoggerFactory.getLogger("Sodium-CloudRenderer");
   private static final class_2960 CLOUDS_TEXTURE_ID = class_2960.method_60656("textures/environment/clouds.png");
   private static final float CLOUD_HEIGHT = 4.0F;
   private static final float CLOUD_WIDTH = 12.0F;
   private static final int FACE_MASK_NEG_Y = 1;
   private static final int FACE_MASK_POS_Y = 2;
   private static final int FACE_MASK_NEG_X = 4;
   private static final int FACE_MASK_POS_X = 8;
   private static final int FACE_MASK_NEG_Z = 16;
   private static final int FACE_MASK_POS_Z = 32;
   private static final int BRIGHTNESS_POS_Y = ColorU8.normalizedFloatToByte(1.0F);
   private static final int BRIGHTNESS_NEG_Y = ColorU8.normalizedFloatToByte(0.7F);
   private static final int BRIGHTNESS_X_AXIS = ColorU8.normalizedFloatToByte(0.9F);
   private static final int BRIGHTNESS_Z_AXIS = ColorU8.normalizedFloatToByte(0.8F);
   @Nullable
   private class_5944 shaderProgram;
   @Nullable
   private CloudRenderer.CloudTextureData textureData;
   @Nullable
   private CloudRenderer.CloudGeometry builtGeometry;

   public CloudRenderer(class_5912 resourceProvider) {
      this.reload(resourceProvider);
   }

   public void render(class_4184 camera, class_638 level, Matrix4f projectionMatrix, class_4587 poseStack, float ticks, float tickDelta) {
      float height = level.method_28103().method_28108() + 0.33F;
      if (!Float.isNaN(height)) {
         if (this.textureData != null && this.shaderProgram != null) {
            class_243 cameraPos = camera.method_19326();
            int renderDistance = getCloudRenderDistance();
            class_4063 renderMode = class_310.method_1551().field_1690.method_1632();
            double cloudTime = (ticks + tickDelta) * 0.03F;
            float worldX = (float)(cameraPos.method_10216() + cloudTime);
            float worldZ = (float)(cameraPos.method_10215() + 0.33);
            int cellX = class_3532.method_15375(worldX / 12.0F);
            int cellZ = class_3532.method_15375(worldZ / 12.0F);
            CloudRenderer.ViewOrientation orientation;
            if (renderMode == class_4063.field_18164) {
               orientation = CloudRenderer.ViewOrientation.getOrientation(cameraPos, height, height + 4.0F);
            } else {
               orientation = null;
            }

            CloudRenderer.CloudGeometryParameters parameters = new CloudRenderer.CloudGeometryParameters(cellX, cellZ, renderDistance, orientation, renderMode);
            CloudRenderer.CloudGeometry geometry = this.builtGeometry;
            if (geometry == null || !Objects.equals(geometry.params(), parameters)) {
               this.builtGeometry = geometry = rebuildGeometry(geometry, parameters, this.textureData);
            }

            class_291 vertexBuffer = geometry.vertexBuffer();
            if (vertexBuffer != null) {
               float viewPosX = worldX - cellX * 12.0F;
               float viewPosY = (float)cameraPos.method_10214() - height;
               float viewPosZ = worldZ - cellZ * 12.0F;
               poseStack.method_22903();
               class_4665 poseEntry = poseStack.method_23760();
               Matrix4f modelViewMatrix = poseEntry.method_23761();
               modelViewMatrix.translate(-viewPosX, -viewPosY, -viewPosZ);
               class_6854 prevShaderFogShape = RenderSystem.getShaderFogShape();
               float prevShaderFogEnd = RenderSystem.getShaderFogEnd();
               float prevShaderFogStart = RenderSystem.getShaderFogStart();
               class_758.method_3211(camera, class_4596.field_20946, renderDistance * 8, shouldUseWorldFog(level, cameraPos), tickDelta);
               boolean fastClouds = geometry.params().renderMode() == class_4063.field_18163;
               boolean fabulous = class_310.method_29611();
               if (fastClouds) {
                  RenderSystem.disableCull();
               }

               if (fabulous) {
                  class_310.method_1551().field_1769.method_29364().method_1235(false);
               }

               class_243 colorModulator = level.method_23785(tickDelta);
               RenderSystem.setShaderColor((float)colorModulator.field_1352, (float)colorModulator.field_1351, (float)colorModulator.field_1350, 0.8F);
               vertexBuffer.method_1353();
               RenderSystem.enableBlend();
               RenderSystem.enableDepthTest();
               RenderSystem.blendFuncSeparate(class_4535.SRC_ALPHA, class_4534.ONE_MINUS_SRC_ALPHA, class_4535.ONE, class_4534.ONE_MINUS_SRC_ALPHA);
               RenderSystem.depthFunc(513);
               vertexBuffer.method_34427(modelViewMatrix, projectionMatrix, this.shaderProgram);
               RenderSystem.depthFunc(515);
               RenderSystem.disableBlend();
               class_291.method_1354();
               if (fastClouds) {
                  RenderSystem.enableCull();
               }

               if (fabulous) {
                  class_310.method_1551().method_1522().method_1235(false);
               }

               RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
               RenderSystem.setShaderFogShape(prevShaderFogShape);
               RenderSystem.setShaderFogEnd(prevShaderFogEnd);
               RenderSystem.setShaderFogStart(prevShaderFogStart);
               poseStack.method_22909();
            }
         }
      }
   }

   @NotNull
   private static CloudRenderer.CloudGeometry rebuildGeometry(
      @Nullable CloudRenderer.CloudGeometry existingGeometry, CloudRenderer.CloudGeometryParameters parameters, CloudRenderer.CloudTextureData textureData
   ) {
      class_287 bufferBuilder = class_289.method_1348().method_60827(class_5596.field_27382, class_290.field_1576);
      VertexBufferWriter writer = VertexBufferWriter.of(bufferBuilder);
      int radius = parameters.radius();
      CloudRenderer.ViewOrientation orientation = parameters.orientation();
      boolean flat = parameters.renderMode() == class_4063.field_18163;
      CloudRenderer.CloudTextureData.Slice slice = textureData.slice(parameters.originX(), parameters.originZ(), radius);
      addCellGeometryToBuffer(writer, slice, 0, 0, orientation, flat);

      for (int layer = 1; layer <= radius; layer++) {
         for (int z = -layer; z < layer; z++) {
            int x = Math.abs(z) - layer;
            addCellGeometryToBuffer(writer, slice, x, z, orientation, flat);
         }

         for (int z = layer; z > -layer; z--) {
            int x = layer - Math.abs(z);
            addCellGeometryToBuffer(writer, slice, x, z, orientation, flat);
         }
      }

      for (int layer = radius + 1; layer <= 2 * radius; layer++) {
         int l = layer - radius;

         for (int z = -radius; z <= -l; z++) {
            int x = -z - layer;
            addCellGeometryToBuffer(writer, slice, x, z, orientation, flat);
         }

         for (int z = l; z <= radius; z++) {
            int x = z - layer;
            addCellGeometryToBuffer(writer, slice, x, z, orientation, flat);
         }

         for (int z = radius; z >= l; z--) {
            int x = layer - z;
            addCellGeometryToBuffer(writer, slice, x, z, orientation, flat);
         }

         for (int z = -l; z >= -radius; z--) {
            int x = layer + z;
            addCellGeometryToBuffer(writer, slice, x, z, orientation, flat);
         }
      }

      class_9801 meshData = bufferBuilder.method_60794();
      class_291 vertexBuffer = null;
      if (existingGeometry != null) {
         vertexBuffer = existingGeometry.vertexBuffer();
      }

      if (meshData != null) {
         if (vertexBuffer == null) {
            vertexBuffer = new class_291(class_8555.field_44794);
         }

         uploadToVertexBuffer(vertexBuffer, meshData);
      } else if (vertexBuffer != null) {
         vertexBuffer.close();
         vertexBuffer = null;
      }

      class_289.method_1348().method_60828();
      return new CloudRenderer.CloudGeometry(vertexBuffer, parameters);
   }

   private static void addCellGeometryToBuffer(
      VertexBufferWriter writer,
      CloudRenderer.CloudTextureData.Slice textureData,
      int x,
      int z,
      @Nullable CloudRenderer.ViewOrientation orientation,
      boolean flat
   ) {
      int index = textureData.getCellIndex(x, z);
      int faces = textureData.getCellFaces(index) & getVisibleFaces(x, z, orientation);
      if (faces != 0) {
         int color = textureData.getCellColor(index);
         if (!isTransparent(color)) {
            if (flat) {
               emitCellGeometryFlat(writer, color, x, z);
            } else {
               emitCellGeometryExterior(writer, faces, color, x, z);
               if (taxicabDistance(x, z) <= 1) {
                  emitCellGeometryInterior(writer, color, x, z);
               }
            }
         }
      }
   }

   private static int getVisibleFaces(int x, int z, CloudRenderer.ViewOrientation orientation) {
      int faces = 0;
      if (x <= 0) {
         faces |= 8;
      }

      if (z <= 0) {
         faces |= 32;
      }

      if (x >= 0) {
         faces |= 4;
      }

      if (z >= 0) {
         faces |= 16;
      }

      if (orientation != CloudRenderer.ViewOrientation.BELOW_CLOUDS) {
         faces |= 2;
      }

      if (orientation != CloudRenderer.ViewOrientation.ABOVE_CLOUDS) {
         faces |= 1;
      }

      return faces;
   }

   private static void emitCellGeometryFlat(VertexBufferWriter writer, int texel, int x, int z) {
      MemoryStack stack = MemoryStack.stackPush();

      try {
         long vertexBuffer = stack.nmalloc(64);
         float x0 = x * 12.0F;
         float x1 = x0 + 12.0F;
         float z0 = z * 12.0F;
         float z1 = z0 + 12.0F;
         int color = ColorABGR.mulRGB(texel, BRIGHTNESS_POS_Y);
         long ptr = writeVertex(vertexBuffer, x1, 0.0F, z1, color);
         ptr = writeVertex(ptr, x0, 0.0F, z1, color);
         ptr = writeVertex(ptr, x0, 0.0F, z0, color);
         ptr = writeVertex(ptr, x1, 0.0F, z0, color);
         writer.push(stack, vertexBuffer, 4, ColorVertex.FORMAT);
      } catch (Throwable var15) {
         if (stack != null) {
            try {
               stack.close();
            } catch (Throwable var14) {
               var15.addSuppressed(var14);
            }
         }

         throw var15;
      }

      if (stack != null) {
         stack.close();
      }
   }

   private static void emitCellGeometryExterior(VertexBufferWriter writer, int cellFaces, int cellColor, int cellX, int cellZ) {
      MemoryStack stack = MemoryStack.stackPush();

      try {
         long vertexBuffer = stack.nmalloc(384);
         int vertexCount = 0;
         long ptr = vertexBuffer;
         float x0 = cellX * 12.0F;
         float y0 = 0.0F;
         float z0 = cellZ * 12.0F;
         float x1 = x0 + 12.0F;
         float y1 = 4.0F;
         float z1 = z0 + 12.0F;
         if ((cellFaces & 1) != 0) {
            int vertexColor = ColorABGR.mulRGB(cellColor, BRIGHTNESS_NEG_Y);
            ptr = writeVertex(vertexBuffer, x1, 0.0F, z1, vertexColor);
            ptr = writeVertex(ptr, x0, 0.0F, z1, vertexColor);
            ptr = writeVertex(ptr, x0, 0.0F, z0, vertexColor);
            ptr = writeVertex(ptr, x1, 0.0F, z0, vertexColor);
            vertexCount += 4;
         }

         if ((cellFaces & 2) != 0) {
            int vertexColor = ColorABGR.mulRGB(cellColor, BRIGHTNESS_POS_Y);
            ptr = writeVertex(ptr, x0, 4.0F, z1, vertexColor);
            ptr = writeVertex(ptr, x1, 4.0F, z1, vertexColor);
            ptr = writeVertex(ptr, x1, 4.0F, z0, vertexColor);
            ptr = writeVertex(ptr, x0, 4.0F, z0, vertexColor);
            vertexCount += 4;
         }

         if ((cellFaces & 12) != 0) {
            int vertexColor = ColorABGR.mulRGB(cellColor, BRIGHTNESS_X_AXIS);
            if ((cellFaces & 4) != 0) {
               ptr = writeVertex(ptr, x0, 0.0F, z1, vertexColor);
               ptr = writeVertex(ptr, x0, 4.0F, z1, vertexColor);
               ptr = writeVertex(ptr, x0, 4.0F, z0, vertexColor);
               ptr = writeVertex(ptr, x0, 0.0F, z0, vertexColor);
               vertexCount += 4;
            }

            if ((cellFaces & 8) != 0) {
               ptr = writeVertex(ptr, x1, 4.0F, z1, vertexColor);
               ptr = writeVertex(ptr, x1, 0.0F, z1, vertexColor);
               ptr = writeVertex(ptr, x1, 0.0F, z0, vertexColor);
               ptr = writeVertex(ptr, x1, 4.0F, z0, vertexColor);
               vertexCount += 4;
            }
         }

         if ((cellFaces & 48) != 0) {
            int vertexColorx = ColorABGR.mulRGB(cellColor, BRIGHTNESS_Z_AXIS);
            if ((cellFaces & 16) != 0) {
               ptr = writeVertex(ptr, x1, 4.0F, z0, vertexColorx);
               ptr = writeVertex(ptr, x1, 0.0F, z0, vertexColorx);
               ptr = writeVertex(ptr, x0, 0.0F, z0, vertexColorx);
               ptr = writeVertex(ptr, x0, 4.0F, z0, vertexColorx);
               vertexCount += 4;
            }

            if ((cellFaces & 32) != 0) {
               ptr = writeVertex(ptr, x1, 0.0F, z1, vertexColorx);
               ptr = writeVertex(ptr, x1, 4.0F, z1, vertexColorx);
               ptr = writeVertex(ptr, x0, 4.0F, z1, vertexColorx);
               ptr = writeVertex(ptr, x0, 0.0F, z1, vertexColorx);
               vertexCount += 4;
            }
         }

         writer.push(stack, vertexBuffer, vertexCount, ColorVertex.FORMAT);
      } catch (Throwable var19) {
         if (stack != null) {
            try {
               stack.close();
            } catch (Throwable var18) {
               var19.addSuppressed(var18);
            }
         }

         throw var19;
      }

      if (stack != null) {
         stack.close();
      }
   }

   private static void emitCellGeometryInterior(VertexBufferWriter writer, int baseColor, int cellX, int cellZ) {
      MemoryStack stack = MemoryStack.stackPush();

      try {
         long vertexBuffer = stack.nmalloc(384);
         float x0 = cellX * 12.0F;
         float y0 = 0.0F;
         float z0 = cellZ * 12.0F;
         float x1 = x0 + 12.0F;
         float y1 = 4.0F;
         float z1 = z0 + 12.0F;
         int vertexColor = ColorABGR.mulRGB(baseColor, BRIGHTNESS_NEG_Y);
         long ptr = writeVertex(vertexBuffer, x1, 0.0F, z0, vertexColor);
         ptr = writeVertex(ptr, x0, 0.0F, z0, vertexColor);
         ptr = writeVertex(ptr, x0, 0.0F, z1, vertexColor);
         ptr = writeVertex(ptr, x1, 0.0F, z1, vertexColor);
         vertexColor = ColorABGR.mulRGB(baseColor, BRIGHTNESS_POS_Y);
         ptr = writeVertex(ptr, x0, 4.0F, z0, vertexColor);
         ptr = writeVertex(ptr, x1, 4.0F, z0, vertexColor);
         ptr = writeVertex(ptr, x1, 4.0F, z1, vertexColor);
         ptr = writeVertex(ptr, x0, 4.0F, z1, vertexColor);
         vertexColor = ColorABGR.mulRGB(baseColor, BRIGHTNESS_X_AXIS);
         ptr = writeVertex(ptr, x0, 0.0F, z0, vertexColor);
         ptr = writeVertex(ptr, x0, 4.0F, z0, vertexColor);
         ptr = writeVertex(ptr, x0, 4.0F, z1, vertexColor);
         ptr = writeVertex(ptr, x0, 0.0F, z1, vertexColor);
         ptr = writeVertex(ptr, x1, 4.0F, z0, vertexColor);
         ptr = writeVertex(ptr, x1, 0.0F, z0, vertexColor);
         ptr = writeVertex(ptr, x1, 0.0F, z1, vertexColor);
         ptr = writeVertex(ptr, x1, 4.0F, z1, vertexColor);
         vertexColor = ColorABGR.mulRGB(baseColor, BRIGHTNESS_Z_AXIS);
         ptr = writeVertex(ptr, x0, 4.0F, z0, vertexColor);
         ptr = writeVertex(ptr, x0, 0.0F, z0, vertexColor);
         ptr = writeVertex(ptr, x1, 0.0F, z0, vertexColor);
         ptr = writeVertex(ptr, x1, 4.0F, z0, vertexColor);
         ptr = writeVertex(ptr, x0, 0.0F, z1, vertexColor);
         ptr = writeVertex(ptr, x0, 4.0F, z1, vertexColor);
         ptr = writeVertex(ptr, x1, 4.0F, z1, vertexColor);
         ptr = writeVertex(ptr, x1, 0.0F, z1, vertexColor);
         writer.push(stack, vertexBuffer, 24, ColorVertex.FORMAT);
      } catch (Throwable var17) {
         if (stack != null) {
            try {
               stack.close();
            } catch (Throwable var16) {
               var17.addSuppressed(var16);
            }
         }

         throw var17;
      }

      if (stack != null) {
         stack.close();
      }
   }

   private static long writeVertex(long buffer, float x, float y, float z, int color) {
      ColorVertex.put(buffer, x, y, z, color);
      return buffer + 16L;
   }

   private static void uploadToVertexBuffer(class_291 vertexBuffer, class_9801 builtBuffer) {
      vertexBuffer.method_1353();
      vertexBuffer.method_1352(builtBuffer);
      class_291.method_1354();
   }

   public void reload(class_5912 resourceProvider) {
      this.destroy();
      this.textureData = loadTextureData(resourceProvider);
      this.shaderProgram = loadShaderProgram(resourceProvider);
   }

   public void destroy() {
      if (this.shaderProgram != null) {
         this.shaderProgram.close();
         this.shaderProgram = null;
      }

      if (this.builtGeometry != null) {
         class_291 vertexBuffer = this.builtGeometry.vertexBuffer();
         if (vertexBuffer != null) {
            vertexBuffer.close();
         }

         this.builtGeometry = null;
      }
   }

   @Nullable
   private static class_5944 loadShaderProgram(class_5912 resourceProvider) {
      try {
         return new class_5944(resourceProvider, "clouds", class_290.field_1576);
      } catch (Throwable var2) {
         LOGGER.error(
            "Failed to compile shader program for cloud rendering. The rendering of clouds in the skybox will be disabled. This may be caused by an incompatible resource pack.",
            var2
         );
         return null;
      }
   }

   @Nullable
   private static CloudRenderer.CloudTextureData loadTextureData(class_5912 resourceProvider) {
      class_3298 resource = (class_3298)resourceProvider.method_14486(CLOUDS_TEXTURE_ID).orElseThrow();

      try {
         CloudRenderer.CloudTextureData var4;
         try (InputStream inputStream = resource.method_14482()) {
            class_1011 nativeImage = class_1011.method_4309(inputStream);

            try {
               var4 = CloudRenderer.CloudTextureData.load(nativeImage);
            } catch (Throwable var8) {
               if (nativeImage != null) {
                  try {
                     nativeImage.close();
                  } catch (Throwable var7) {
                     var8.addSuppressed(var7);
                  }
               }

               throw var8;
            }

            if (nativeImage != null) {
               nativeImage.close();
            }
         }

         return var4;
      } catch (Throwable var10) {
         LOGGER.error(
            "Failed to load texture '{}'. The rendering of clouds in the skybox will be disabled. This may be caused by an incompatible resource pack.",
            CLOUDS_TEXTURE_ID,
            var10
         );
         return null;
      }
   }

   private static boolean shouldUseWorldFog(class_638 level, class_243 pos) {
      return level.method_28103().method_28110(class_3532.method_15357(pos.method_10216()), class_3532.method_15357(pos.method_10215()))
         || class_310.method_1551().field_1705.method_1740().method_1800();
   }

   private static int getCloudRenderDistance() {
      return Math.max(32, class_310.method_1551().field_1690.method_38521() * 2 + 9);
   }

   private static boolean isTransparent(int argb) {
      return ColorARGB.unpackAlpha(argb) < 10;
   }

   private static int taxicabDistance(int x, int z) {
      return Math.abs(x) + Math.abs(z);
   }

   public record CloudGeometry(@Nullable class_291 vertexBuffer, CloudRenderer.CloudGeometryParameters params) {
   }

   public record CloudGeometryParameters(int originX, int originZ, int radius, @Nullable CloudRenderer.ViewOrientation orientation, class_4063 renderMode) {
   }

   private static class CloudTextureData {
      private final byte[] faces;
      private final int[] colors;
      private final int width;
      private final int height;

      private CloudTextureData(int width, int height) {
         this.faces = new byte[width * height];
         this.colors = new int[width * height];
         this.width = width;
         this.height = height;
      }

      public CloudRenderer.CloudTextureData.Slice slice(int originX, int originY, int radius) {
         CloudRenderer.CloudTextureData src = this;
         CloudRenderer.CloudTextureData.Slice dst = new CloudRenderer.CloudTextureData.Slice(radius);

         for (int dstY = 0; dstY < dst.height; dstY++) {
            int srcX = Math.floorMod(originX - radius, this.width);
            int srcY = Math.floorMod(originY - radius + dstY, this.height);
            int dstX = 0;

            while (dstX < dst.width) {
               int length = Math.min(src.width - srcX, dst.width - dstX);
               int srcPos = getCellIndex(srcX, srcY, src.width);
               int dstPos = getCellIndex(dstX, dstY, dst.width);
               System.arraycopy(this.faces, srcPos, dst.faces, dstPos, length);
               System.arraycopy(this.colors, srcPos, dst.colors, dstPos, length);
               srcX = 0;
               dstX += length;
            }
         }

         return dst;
      }

      @Nullable
      public static CloudRenderer.CloudTextureData load(class_1011 image) {
         int width = image.method_4307();
         int height = image.method_4323();
         CloudRenderer.CloudTextureData data = new CloudRenderer.CloudTextureData(width, height);
         return !data.loadTextureData(image, width, height) ? null : data;
      }

      private boolean loadTextureData(class_1011 texture, int width, int height) {
         Validate.isTrue(this.width == width);
         Validate.isTrue(this.height == height);
         boolean containsData = false;

         for (int x = 0; x < width; x++) {
            for (int z = 0; z < height; z++) {
               int color = texture.method_4315(x, z);
               if (!CloudRenderer.isTransparent(color)) {
                  int index = getCellIndex(x, z, width);
                  this.colors[index] = color;
                  this.faces[index] = (byte)getOpenFaces(texture, color, x, z);
                  containsData = true;
               }
            }
         }

         return containsData;
      }

      private static int getOpenFaces(class_1011 image, int color, int x, int z) {
         int faces = 3;
         int neighbor = getNeighborTexel(image, x - 1, z);
         if (color != neighbor) {
            faces |= 4;
         }

         neighbor = getNeighborTexel(image, x + 1, z);
         if (color != neighbor) {
            faces |= 8;
         }

         neighbor = getNeighborTexel(image, x, z - 1);
         if (color != neighbor) {
            faces |= 16;
         }

         neighbor = getNeighborTexel(image, x, z + 1);
         if (color != neighbor) {
            faces |= 32;
         }

         return faces;
      }

      private static int getNeighborTexel(class_1011 image, int x, int z) {
         x = wrapTexelCoord(x, 0, image.method_4307() - 1);
         z = wrapTexelCoord(z, 0, image.method_4323() - 1);
         return image.method_4315(x, z);
      }

      private static int wrapTexelCoord(int coord, int min, int max) {
         if (coord < min) {
            coord = max;
         }

         if (coord > max) {
            coord = min;
         }

         return coord;
      }

      private static int getCellIndex(int x, int z, int pitch) {
         return z * pitch + x;
      }

      public static class Slice {
         private final int width;
         private final int height;
         private final int radius;
         private final byte[] faces;
         private final int[] colors;

         public Slice(int radius) {
            this.width = 1 + radius * 2;
            this.height = 1 + radius * 2;
            this.radius = radius;
            this.faces = new byte[this.width * this.height];
            this.colors = new int[this.width * this.height];
         }

         public int getCellIndex(int x, int z) {
            return CloudRenderer.CloudTextureData.getCellIndex(x + this.radius, z + this.radius, this.width);
         }

         public int getCellFaces(int index) {
            return Byte.toUnsignedInt(this.faces[index]);
         }

         public int getCellColor(int index) {
            return this.colors[index];
         }
      }
   }

   private static enum ViewOrientation {
      BELOW_CLOUDS,
      INSIDE_CLOUDS,
      ABOVE_CLOUDS;

      @NotNull
      public static CloudRenderer.ViewOrientation getOrientation(class_243 camera, float minY, float maxY) {
         if (camera.method_10214() <= minY + 0.125F) {
            return BELOW_CLOUDS;
         } else {
            return camera.method_10214() >= maxY - 0.125F ? ABOVE_CLOUDS : INSIDE_CLOUDS;
         }
      }
   }
}
