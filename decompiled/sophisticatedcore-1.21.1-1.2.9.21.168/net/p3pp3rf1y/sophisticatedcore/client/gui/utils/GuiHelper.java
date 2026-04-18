package net.p3pp3rf1y.sophisticatedcore.client.gui.utils;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.datafixers.util.Either;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.annotation.Nullable;
import net.minecraft.class_1058;
import net.minecraft.class_1087;
import net.minecraft.class_128;
import net.minecraft.class_129;
import net.minecraft.class_1309;
import net.minecraft.class_148;
import net.minecraft.class_1799;
import net.minecraft.class_1921;
import net.minecraft.class_2561;
import net.minecraft.class_286;
import net.minecraft.class_287;
import net.minecraft.class_289;
import net.minecraft.class_290;
import net.minecraft.class_2960;
import net.minecraft.class_308;
import net.minecraft.class_310;
import net.minecraft.class_327;
import net.minecraft.class_332;
import net.minecraft.class_437;
import net.minecraft.class_4587;
import net.minecraft.class_4588;
import net.minecraft.class_4608;
import net.minecraft.class_5348;
import net.minecraft.class_5481;
import net.minecraft.class_5632;
import net.minecraft.class_5684;
import net.minecraft.class_757;
import net.minecraft.class_768;
import net.minecraft.class_7833;
import net.minecraft.class_8001;
import net.minecraft.class_811;
import net.minecraft.class_918;
import net.minecraft.class_293.class_5596;
import net.minecraft.class_4597.class_4598;
import net.p3pp3rf1y.sophisticatedcore.SophisticatedCore;
import net.p3pp3rf1y.sophisticatedcore.client.gui.controls.ToggleButton;
import org.joml.Matrix4f;

public class GuiHelper {
   public static final class_2960 GUI_CONTROLS = SophisticatedCore.getRL("textures/gui/gui_controls.png");
   private static final int GUI_CONTROLS_TEXTURE_WIDTH = 256;
   private static final int GUI_CONTROLS_TEXTURE_HEIGHT = 256;
   public static final TextureBlitData BAR_BACKGROUND_BOTTOM = new TextureBlitData(GUI_CONTROLS, Dimension.SQUARE_256, new UV(29, 66), Dimension.SQUARE_18);
   public static final TextureBlitData BAR_BACKGROUND_MIDDLE = new TextureBlitData(GUI_CONTROLS, Dimension.SQUARE_256, new UV(29, 48), Dimension.SQUARE_18);
   public static final TextureBlitData BAR_BACKGROUND_TOP = new TextureBlitData(GUI_CONTROLS, Dimension.SQUARE_256, new UV(29, 30), Dimension.SQUARE_18);
   public static final class_2960 ICONS = SophisticatedCore.getRL("textures/gui/icons.png");
   public static final TextureBlitData CRAFTING_RESULT_SLOT = new TextureBlitData(GUI_CONTROLS, new UV(71, 216), new Dimension(26, 26));
   public static final TextureBlitData DEFAULT_BUTTON_HOVERED_BACKGROUND = new TextureBlitData(GUI_CONTROLS, new UV(47, 0), Dimension.SQUARE_18);
   public static final TextureBlitData DEFAULT_BUTTON_BACKGROUND = new TextureBlitData(GUI_CONTROLS, new UV(29, 0), Dimension.SQUARE_18);
   public static final TextureBlitData SMALL_BUTTON_BACKGROUND = new TextureBlitData(GUI_CONTROLS, Dimension.SQUARE_256, new UV(29, 18), Dimension.SQUARE_12);
   public static final TextureBlitData SMALL_BUTTON_HOVERED_BACKGROUND = new TextureBlitData(
      GUI_CONTROLS, Dimension.SQUARE_256, new UV(41, 18), Dimension.SQUARE_12
   );
   public static final class_2960 SLOTS_BACKGROUND = SophisticatedCore.getRL("textures/gui/slots_background.png");
   private static final Map<Integer, TextureBlitData> SLOTS_BACKGROUNDS = new HashMap<>();

   private GuiHelper() {
   }

   public static void renderItemInGUI(class_332 guiGraphics, class_310 minecraft, class_1799 stack, int xPosition, int yPosition) {
      renderItemInGUI(guiGraphics, minecraft, stack, xPosition, yPosition, false);
   }

   public static void renderSlotsBackground(class_332 guiGraphics, int x, int y, int slotWidth, int slotHeight) {
      int currentY = y;

      for (int remainingSlotHeight = slotHeight; remainingSlotHeight > 0; remainingSlotHeight -= Math.min(slotHeight, 12)) {
         int finalRemainingSlotHeight = remainingSlotHeight;
         int key = getSlotsBackgroundKey(slotWidth, remainingSlotHeight);
         blit(
            guiGraphics,
            x,
            currentY,
            SLOTS_BACKGROUNDS.computeIfAbsent(
               key,
               k -> new TextureBlitData(SLOTS_BACKGROUND, Dimension.SQUARE_256, new UV(0, 0), new Dimension(slotWidth * 18, finalRemainingSlotHeight * 18))
            )
         );
         currentY += 216;
      }
   }

   private static int getSlotsBackgroundKey(int slotWidth, int slotHeight) {
      return slotWidth * 31 + slotHeight;
   }

   public static void renderItemInGUI(class_332 guiGraphics, class_310 minecraft, class_1799 stack, int xPosition, int yPosition, boolean renderOverlay) {
      renderItemInGUI(guiGraphics, minecraft, stack, xPosition, yPosition, renderOverlay, null);
   }

   public static void renderItemInGUI(
      class_332 guiGraphics, class_310 minecraft, class_1799 stack, int xPosition, int yPosition, boolean renderOverlay, @Nullable String countText
   ) {
      RenderSystem.enableDepthTest();
      guiGraphics.method_51427(stack, xPosition, yPosition);
      if (renderOverlay) {
         guiGraphics.method_51432(minecraft.field_1772, stack, xPosition, yPosition, countText);
      }
   }

   public static void blit(class_332 guiGraphics, int x, int y, TextureBlitData texData) {
      guiGraphics.method_25290(
         texData.getTextureName(),
         x + texData.getXOffset(),
         y + texData.getYOffset(),
         texData.getU(),
         texData.getV(),
         texData.getWidth(),
         texData.getHeight(),
         texData.getTextureWidth(),
         texData.getTextureHeight()
      );
   }

   public static void blit(class_332 guiGraphics, int x, int y, TextureBlitData texData, int width, int height) {
      int halfWidth = width / 2;
      int secondHalfWidth = width - halfWidth;
      int halfHeight = height / 2;
      int secondHalfHeight = height - halfHeight;
      guiGraphics.method_25290(
         texData.getTextureName(),
         x + texData.getXOffset(),
         y + texData.getYOffset(),
         texData.getU(),
         texData.getV(),
         halfWidth,
         halfHeight,
         texData.getTextureWidth(),
         texData.getTextureHeight()
      );
      guiGraphics.method_25290(
         texData.getTextureName(),
         x + texData.getXOffset() + halfWidth,
         y + texData.getYOffset(),
         (float)texData.getU() + texData.getWidth() - secondHalfWidth,
         texData.getV(),
         secondHalfWidth,
         halfHeight,
         texData.getTextureWidth(),
         texData.getTextureHeight()
      );
      guiGraphics.method_25290(
         texData.getTextureName(),
         x + texData.getXOffset(),
         y + texData.getYOffset() + halfHeight,
         texData.getU(),
         (float)texData.getV() + texData.getHeight() - secondHalfHeight,
         halfWidth,
         secondHalfHeight,
         texData.getTextureWidth(),
         texData.getTextureHeight()
      );
      guiGraphics.method_25290(
         texData.getTextureName(),
         x + texData.getXOffset() + halfWidth,
         y + texData.getYOffset() + halfHeight,
         (float)texData.getU() + texData.getWidth() - secondHalfWidth,
         (float)texData.getV() + texData.getHeight() - secondHalfHeight,
         secondHalfWidth,
         secondHalfHeight,
         texData.getTextureWidth(),
         texData.getTextureHeight()
      );
   }

   public static void coloredBlit(Matrix4f matrix, int x, int y, TextureBlitData texData, int color) {
      float red = (color >> 16 & 0xFF) / 255.0F;
      float green = (color >> 8 & 0xFF) / 255.0F;
      float blue = (color & 0xFF) / 255.0F;
      float alpha = (color >> 24 & 0xFF) / 255.0F;
      int xMin = x + texData.getXOffset();
      int yMin = y + texData.getYOffset();
      int xMax = xMin + texData.getWidth();
      int yMax = yMin + texData.getHeight();
      float minU = (float)texData.getU() / texData.getTextureWidth();
      float maxU = minU + (float)texData.getWidth() / texData.getTextureWidth();
      float minV = (float)texData.getV() / texData.getTextureHeight();
      float maxV = minV + (float)texData.getHeight() / texData.getTextureWidth();
      RenderSystem.setShader(class_757::method_34543);
      class_287 bufferbuilder = class_289.method_1348().method_60827(class_5596.field_27382, class_290.field_1575);
      bufferbuilder.method_22918(matrix, xMin, yMax, 0.0F).method_22913(minU, maxV).method_22915(red, green, blue, alpha);
      bufferbuilder.method_22918(matrix, xMax, yMax, 0.0F).method_22913(maxU, maxV).method_22915(red, green, blue, alpha);
      bufferbuilder.method_22918(matrix, xMax, yMin, 0.0F).method_22913(maxU, minV).method_22915(red, green, blue, alpha);
      bufferbuilder.method_22918(matrix, xMin, yMin, 0.0F).method_22913(minU, minV).method_22915(red, green, blue, alpha);
      class_286.method_43433(bufferbuilder.method_60800());
   }

   public static void renderTooltipBackground(
      Matrix4f matrix4f, int tooltipWidth, int leftX, int topY, int tooltipHeight, int backgroundColor, int borderColorStart, int borderColorEnd
   ) {
      class_289 tessellator = class_289.method_1348();
      class_287 bufferbuilder = tessellator.method_60827(class_5596.field_27382, class_290.field_1576);
      RenderSystem.setShader(class_757::method_34540);
      fillGradient(matrix4f, bufferbuilder, leftX - 3, topY - 4, leftX + tooltipWidth + 3, topY - 3, backgroundColor, backgroundColor);
      fillGradient(
         matrix4f, bufferbuilder, leftX - 3, topY + tooltipHeight + 3, leftX + tooltipWidth + 3, topY + tooltipHeight + 4, backgroundColor, backgroundColor
      );
      fillGradient(matrix4f, bufferbuilder, leftX - 3, topY - 3, leftX + tooltipWidth + 3, topY + tooltipHeight + 3, backgroundColor, backgroundColor);
      fillGradient(matrix4f, bufferbuilder, leftX - 4, topY - 3, leftX - 3, topY + tooltipHeight + 3, backgroundColor, backgroundColor);
      fillGradient(
         matrix4f, bufferbuilder, leftX + tooltipWidth + 3, topY - 3, leftX + tooltipWidth + 4, topY + tooltipHeight + 3, backgroundColor, backgroundColor
      );
      fillGradient(matrix4f, bufferbuilder, leftX - 3, topY - 3 + 1, leftX - 3 + 1, topY + tooltipHeight + 3 - 1, borderColorStart, borderColorEnd);
      fillGradient(
         matrix4f,
         bufferbuilder,
         leftX + tooltipWidth + 2,
         topY - 3 + 1,
         leftX + tooltipWidth + 3,
         topY + tooltipHeight + 3 - 1,
         borderColorStart,
         borderColorEnd
      );
      fillGradient(matrix4f, bufferbuilder, leftX - 3, topY - 3, leftX + tooltipWidth + 3, topY - 3 + 1, borderColorStart, borderColorStart);
      fillGradient(
         matrix4f, bufferbuilder, leftX - 3, topY + tooltipHeight + 2, leftX + tooltipWidth + 3, topY + tooltipHeight + 3, borderColorEnd, borderColorEnd
      );
      RenderSystem.enableDepthTest();
      RenderSystem.enableBlend();
      RenderSystem.defaultBlendFunc();
      class_286.method_43433(bufferbuilder.method_60800());
      RenderSystem.disableBlend();
   }

   public static void writeTooltipLines(
      class_332 guiGraphics, List<class_5481> textLines, class_327 font, float leftX, int topY, Matrix4f matrix4f, class_4598 renderTypeBuffer, int color
   ) {
      for (int i = 0; i < textLines.size(); i++) {
         class_5481 line = textLines.get(i);
         if (line != null) {
            guiGraphics.method_51430(font, line, (int)leftX, topY, color, true);
         }

         if (i == 0) {
            topY += 2;
         }

         topY += 10;
      }
   }

   private static void fillGradient(Matrix4f matrix, class_287 builder, int x1, int y1, int x2, int y2, int colorA, int colorB) {
      float f = (colorA >> 24 & 0xFF) / 255.0F;
      float f1 = (colorA >> 16 & 0xFF) / 255.0F;
      float f2 = (colorA >> 8 & 0xFF) / 255.0F;
      float f3 = (colorA & 0xFF) / 255.0F;
      float f4 = (colorB >> 24 & 0xFF) / 255.0F;
      float f5 = (colorB >> 16 & 0xFF) / 255.0F;
      float f6 = (colorB >> 8 & 0xFF) / 255.0F;
      float f7 = (colorB & 0xFF) / 255.0F;
      builder.method_22918(matrix, x2, y1, 400.0F).method_22915(f1, f2, f3, f);
      builder.method_22918(matrix, x1, y1, 400.0F).method_22915(f1, f2, f3, f);
      builder.method_22918(matrix, x1, y2, 400.0F).method_22915(f5, f6, f7, f4);
      builder.method_22918(matrix, x2, y2, 400.0F).method_22915(f5, f6, f7, f4);
   }

   public static void fill(class_332 guiGraphics, float minX, float minY, float maxX, float maxY, int color) {
      fill(guiGraphics, class_1921.method_51784(), minX, minY, maxX, maxY, 0.0F, color);
   }

   public static void fill(class_332 guiGraphics, class_1921 renderType, float minX, float minY, float maxX, float maxY, float z, int color) {
      Matrix4f matrix4f = guiGraphics.method_51448().method_23760().method_23761();
      if (minX < maxX) {
         float j = minX;
         minX = maxX;
         maxX = j;
      }

      if (minY < maxY) {
         float j = minY;
         minY = maxY;
         maxY = j;
      }

      class_4588 vertexconsumer = guiGraphics.method_51450().getBuffer(renderType);
      vertexconsumer.method_22918(matrix4f, minX, minY, z).method_39415(color);
      vertexconsumer.method_22918(matrix4f, minX, maxY, z).method_39415(color);
      vertexconsumer.method_22918(matrix4f, maxX, maxY, z).method_39415(color);
      vertexconsumer.method_22918(matrix4f, maxX, minY, z).method_39415(color);
      guiGraphics.method_51452();
   }

   public static ToggleButton.StateData getButtonStateData(UV uv, Dimension dimension, Position offset, class_2561... tooltip) {
      return getButtonStateData(uv, dimension, offset, Arrays.asList(tooltip));
   }

   public static ToggleButton.StateData getButtonStateData(UV uv, String tooltip, Dimension dimension) {
      return getButtonStateData(uv, tooltip, dimension, new Position(0, 0));
   }

   public static ToggleButton.StateData getButtonStateData(UV uv, String tooltip, Dimension dimension, Position offset) {
      return new ToggleButton.StateData(new TextureBlitData(ICONS, offset, Dimension.SQUARE_256, uv, dimension), class_2561.method_43471(tooltip));
   }

   public static ToggleButton.StateData getButtonStateData(UV uv, Dimension dimension, Position offset, List<class_2561> tooltip) {
      return new ToggleButton.StateData(new TextureBlitData(ICONS, offset, Dimension.SQUARE_256, uv, dimension), tooltip);
   }

   public static void renderSlotsBackground(class_332 guiGraphics, int x, int y, int slotsInRow, int fullSlotRows, int extraRowSlots) {
      renderSlotsBackground(guiGraphics, x, y, slotsInRow, fullSlotRows);
      if (extraRowSlots > 0) {
         renderSlotsBackground(guiGraphics, x, y + fullSlotRows * 18, extraRowSlots, 1);
      }
   }

   public static void renderTiledFluidTextureAtlas(class_332 guiGraphics, class_1058 sprite, int color, int x, int y, int height) {
      RenderSystem.setShader(class_757::method_34543);
      RenderSystem.setShaderTexture(0, sprite.method_45852());
      class_287 builder = class_289.method_1348().method_60827(class_5596.field_27382, class_290.field_1575);
      float u0 = sprite.method_4594();
      float v0 = sprite.method_4593();
      int spriteHeight = sprite.method_45851().method_45815();
      int startY = y;
      float red = (color >> 16 & 0xFF) / 255.0F;
      float green = (color >> 8 & 0xFF) / 255.0F;
      float blue = (color & 0xFF) / 255.0F;

      do {
         int renderHeight = Math.min(spriteHeight, height);
         height -= renderHeight;
         float v1 = sprite.method_4570((float)renderHeight / spriteHeight);
         Matrix4f matrix = guiGraphics.method_51448().method_23760().method_23761();
         float u1 = sprite.method_4577();
         builder.method_22918(matrix, x, (float)startY + renderHeight, 100.0F).method_22913(u0, v1).method_22915(red, green, blue, 1.0F);
         builder.method_22918(matrix, x + 16.0F, (float)startY + renderHeight, 100.0F).method_22913(u1, v1).method_22915(red, green, blue, 1.0F);
         builder.method_22918(matrix, x + 16.0F, startY, 100.0F).method_22913(u1, v0).method_22915(red, green, blue, 1.0F);
         builder.method_22918(matrix, x, startY, 100.0F).method_22913(u0, v0).method_22915(red, green, blue, 1.0F);
         startY += renderHeight;
      } while (height > 0);

      class_286.method_43433(builder.method_60800());
   }

   public static void renderControlBackground(class_332 guiGraphics, int x, int y, int renderWidth, int renderHeight) {
      int u = 29;
      int v = 146;
      int textureBgWidth = 66;
      int textureBgHeight = 56;
      renderControlBackground(guiGraphics, x, y, renderWidth, renderHeight, u, v, textureBgWidth, textureBgHeight);
   }

   public static void renderControlBackground(
      class_332 guiGraphics, int x, int y, int renderWidth, int renderHeight, int u, int v, int textureBgWidth, int textureBgHeight
   ) {
      int halfWidth = renderWidth / 2;
      int halfHeight = renderHeight / 2;
      guiGraphics.method_25290(GUI_CONTROLS, x, y, u, v, halfWidth, halfHeight, 256, 256);
      guiGraphics.method_25290(GUI_CONTROLS, x, y + halfHeight, u, (float)v + textureBgHeight - halfHeight, halfWidth, halfHeight, 256, 256);
      guiGraphics.method_25290(GUI_CONTROLS, x + halfWidth, y, (float)u + textureBgWidth - halfWidth, v, halfWidth, halfHeight, 256, 256);
      guiGraphics.method_25290(
         GUI_CONTROLS,
         x + halfWidth,
         y + halfHeight,
         (float)u + textureBgWidth - halfWidth,
         (float)v + textureBgHeight - halfHeight,
         halfWidth,
         halfHeight,
         256,
         256
      );
   }

   public static void tryRenderGuiItem(
      class_332 guiGraphics, class_918 itemRenderer, @Nullable class_1309 livingEntity, class_1799 stack, int x, int y, int rotation
   ) {
      if (!stack.method_7960()) {
         class_1087 bakedmodel = itemRenderer.method_4019(stack, null, livingEntity, 0);

         try {
            renderGuiItem(guiGraphics, itemRenderer, stack, x, y, bakedmodel, rotation);
         } catch (Throwable var11) {
            class_128 crashreport = class_128.method_560(var11, "Rendering item");
            class_129 crashreportcategory = crashreport.method_562("Item being rendered");
            crashreportcategory.method_577("Item Type", () -> String.valueOf(stack.method_7909()));
            crashreportcategory.method_577("Item Components", () -> String.valueOf(stack.method_57353()));
            crashreportcategory.method_577("Item Foil", () -> String.valueOf(stack.method_7958()));
            throw new class_148(crashreport);
         }
      }
   }

   private static void renderGuiItem(class_332 guiGraphics, class_918 itemRenderer, class_1799 stack, int x, int y, class_1087 bakedModel, int rotation) {
      class_4587 posestack = guiGraphics.method_51448();
      posestack.method_22903();
      posestack.method_46416(x + 8.0F, y + 8.0F, 150.0F);
      if (rotation != 0) {
         posestack.method_22907(class_7833.field_40718.rotationDegrees(rotation));
      }

      posestack.method_22905(1.0F, -1.0F, 1.0F);
      posestack.method_22905(16.0F, 16.0F, 16.0F);
      RenderSystem.applyModelViewMatrix();
      class_4598 bufferSource = class_310.method_1551().method_22940().method_23000();
      boolean flag = !bakedModel.method_24304();
      if (flag) {
         class_308.method_24210();
      }

      itemRenderer.method_23179(stack, class_811.field_4317, false, posestack, bufferSource, 15728880, class_4608.field_21444, bakedModel);
      RenderSystem.disableDepthTest();
      bufferSource.method_22993();
      RenderSystem.enableDepthTest();
      if (flag) {
         class_308.method_24211();
      }

      posestack.method_22909();
      RenderSystem.applyModelViewMatrix();
   }

   public static void renderTooltip(class_437 screen, class_332 guiGraphics, List<class_2561> components, int x, int y) {
      List<class_5684> list = gatherTooltipComponents(components, x, screen.field_22789, screen.field_22790, screen.field_22793);
      guiGraphics.method_51435(screen.field_22793, list, x, y, class_8001.field_41687);
   }

   public static List<class_5684> gatherTooltipComponents(
      List<? extends class_5348> textElements, int mouseX, int screenWidth, int screenHeight, class_327 font
   ) {
      List<Either<class_5348, class_5632>> elements = textElements.stream()
         .<Either<class_5348, class_5632>>map(Either::left)
         .collect(Collectors.toCollection(ArrayList::new));
      int tooltipTextWidth = elements.stream().mapToInt(either -> (Integer)either.map(font::method_27525, component -> 0)).max().orElse(0);
      int tooltipX = mouseX + 12;
      if (tooltipX + tooltipTextWidth + 4 > screenWidth) {
         tooltipX = mouseX - 16 - tooltipTextWidth;
         if (tooltipX < 4) {
            if (mouseX > screenWidth / 2) {
               tooltipTextWidth = mouseX - 12 - 8;
            } else {
               tooltipTextWidth = screenWidth - 16 - mouseX;
            }
         }
      }

      int tooltipTextWidthF = tooltipTextWidth;
      return elements.stream()
         .flatMap(
            either -> (Stream<? extends class_5684>)either.map(
               text -> font.method_1728(text, tooltipTextWidthF).stream().map(class_5684::method_32662),
               component -> Stream.of(class_5684.method_32663(component))
            )
         )
         .toList();
   }

   public static Optional<class_768> getPositiveRectangle(int x, int y, int width, int height) {
      if (x + width > 0 && y + height > 0) {
         int positiveX = Math.max(0, x);
         int positiveY = Math.max(0, y);
         int positiveWidth = width + Math.min(0, x);
         int positiveHeight = height + Math.min(0, y);
         return Optional.of(new class_768(positiveX, positiveY, positiveWidth, positiveHeight));
      } else {
         return Optional.empty();
      }
   }

   public static void renderSlotHighlight(class_332 guiGraphics, int x, int y, int blitOffset, int color) {
      guiGraphics.method_51740(class_1921.method_51785(), x, y, x + 16, y + 16, color, color, blitOffset);
   }
}
