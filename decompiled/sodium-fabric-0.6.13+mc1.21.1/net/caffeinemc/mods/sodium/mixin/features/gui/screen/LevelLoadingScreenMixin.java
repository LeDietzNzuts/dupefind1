package net.caffeinemc.mods.sodium.mixin.features.gui.screen;

import com.mojang.blaze3d.systems.RenderSystem;
import it.unimi.dsi.fastutil.objects.Object2IntMap;
import it.unimi.dsi.fastutil.objects.Reference2IntOpenHashMap;
import net.caffeinemc.mods.sodium.api.util.ColorABGR;
import net.caffeinemc.mods.sodium.api.util.ColorARGB;
import net.caffeinemc.mods.sodium.api.vertex.buffer.VertexBufferWriter;
import net.caffeinemc.mods.sodium.api.vertex.format.common.ColorVertex;
import net.minecraft.class_2806;
import net.minecraft.class_286;
import net.minecraft.class_287;
import net.minecraft.class_289;
import net.minecraft.class_290;
import net.minecraft.class_332;
import net.minecraft.class_3928;
import net.minecraft.class_3953;
import net.minecraft.class_757;
import net.minecraft.class_9801;
import net.minecraft.class_293.class_5596;
import org.joml.Matrix4f;
import org.lwjgl.system.MemoryStack;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;

@Mixin(class_3928.class)
public class LevelLoadingScreenMixin {
   @Mutable
   @Shadow
   @Final
   private static Object2IntMap<class_2806> field_17407;
   @Unique
   private static Reference2IntOpenHashMap<class_2806> STATUS_TO_COLOR_FAST;
   @Unique
   private static final int NULL_STATUS_COLOR = ColorABGR.pack(0, 0, 0, 255);
   @Unique
   private static final int DEFAULT_STATUS_COLOR = ColorARGB.pack(0, 17, 255, 255);

   @Overwrite
   public static void method_17538(class_332 graphics, class_3953 tracker, int mapX, int mapY, int mapScale, int mapPadding) {
      if (STATUS_TO_COLOR_FAST == null) {
         STATUS_TO_COLOR_FAST = new Reference2IntOpenHashMap(field_17407.size());
         STATUS_TO_COLOR_FAST.put(null, NULL_STATUS_COLOR);
         field_17407.object2IntEntrySet().forEach(entry -> STATUS_TO_COLOR_FAST.put((class_2806)entry.getKey(), ColorARGB.toABGR(entry.getIntValue(), 255)));
      }

      RenderSystem.setShader(class_757::method_34540);
      Matrix4f matrix = graphics.method_51448().method_23760().method_23761();
      class_289 tessellator = class_289.method_1348();
      RenderSystem.enableBlend();
      RenderSystem.defaultBlendFunc();
      class_287 bufferBuilder = tessellator.method_60827(class_5596.field_27382, class_290.field_1576);
      VertexBufferWriter writer = VertexBufferWriter.of(bufferBuilder);
      int centerSize = tracker.method_17677();
      int size = tracker.method_17678();
      int tileSize = mapScale + mapPadding;
      if (mapPadding != 0) {
         int mapRenderCenterSize = centerSize * tileSize - mapPadding;
         int radius = mapRenderCenterSize / 2 + 1;
         addRect(writer, matrix, mapX - radius, mapY - radius, mapX - radius + 1, mapY + radius, DEFAULT_STATUS_COLOR);
         addRect(writer, matrix, mapX + radius - 1, mapY - radius, mapX + radius, mapY + radius, DEFAULT_STATUS_COLOR);
         addRect(writer, matrix, mapX - radius, mapY - radius, mapX + radius, mapY - radius + 1, DEFAULT_STATUS_COLOR);
         addRect(writer, matrix, mapX - radius, mapY + radius - 1, mapX + radius, mapY + radius, DEFAULT_STATUS_COLOR);
      }

      int mapRenderSize = size * tileSize - mapPadding;
      int mapStartX = mapX - mapRenderSize / 2;
      int mapStartY = mapY - mapRenderSize / 2;
      class_2806 prevStatus = null;
      int prevColor = NULL_STATUS_COLOR;

      for (int x = 0; x < size; x++) {
         int tileX = mapStartX + x * tileSize;

         for (int z = 0; z < size; z++) {
            int tileY = mapStartY + z * tileSize;
            class_2806 status = tracker.method_17676(x, z);
            int color;
            if (prevStatus == status) {
               color = prevColor;
            } else {
               color = STATUS_TO_COLOR_FAST.getInt(status);
               prevStatus = status;
               prevColor = color;
            }

            addRect(writer, matrix, tileX, tileY, tileX + mapScale, tileY + mapScale, color);
         }
      }

      class_9801 data = bufferBuilder.method_60794();
      if (data != null) {
         class_286.method_43433(data);
      }

      class_289.method_1348().method_60828();
      RenderSystem.disableBlend();
   }

   @Unique
   private static void addRect(VertexBufferWriter writer, Matrix4f matrix, int x1, int y1, int x2, int y2, int color) {
      MemoryStack stack = MemoryStack.stackPush();

      try {
         long buffer = stack.nmalloc(64);
         ColorVertex.put(buffer, matrix, x1, y2, 0.0F, color);
         long ptr = buffer + 16L;
         ColorVertex.put(ptr, matrix, x2, y2, 0.0F, color);
         ptr += 16L;
         ColorVertex.put(ptr, matrix, x2, y1, 0.0F, color);
         ptr += 16L;
         ColorVertex.put(ptr, matrix, x1, y1, 0.0F, color);
         ptr += 16L;
         writer.push(stack, buffer, 4, ColorVertex.FORMAT);
      } catch (Throwable var13) {
         if (stack != null) {
            try {
               stack.close();
            } catch (Throwable var12) {
               var13.addSuppressed(var12);
            }
         }

         throw var13;
      }

      if (stack != null) {
         stack.close();
      }
   }
}
