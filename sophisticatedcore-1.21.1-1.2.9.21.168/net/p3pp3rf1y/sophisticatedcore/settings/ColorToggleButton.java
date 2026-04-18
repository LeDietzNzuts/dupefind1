package net.p3pp3rf1y.sophisticatedcore.settings;

import com.google.common.collect.ImmutableList.Builder;
import com.mojang.blaze3d.systems.RenderSystem;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Supplier;
import net.minecraft.class_124;
import net.minecraft.class_1767;
import net.minecraft.class_2561;
import net.minecraft.class_310;
import net.minecraft.class_332;
import net.minecraft.class_437;
import net.minecraft.class_6382;
import net.p3pp3rf1y.sophisticatedcore.client.gui.controls.ButtonBase;
import net.p3pp3rf1y.sophisticatedcore.client.gui.utils.Dimension;
import net.p3pp3rf1y.sophisticatedcore.client.gui.utils.GuiHelper;
import net.p3pp3rf1y.sophisticatedcore.client.gui.utils.Position;
import net.p3pp3rf1y.sophisticatedcore.client.gui.utils.TranslationHelper;

public class ColorToggleButton extends ButtonBase {
   private static final class_1767[] DYE_VALUES = class_1767.values();
   private static final List<class_2561> TOOLTIP = new Builder()
      .add(class_2561.method_43471(TranslationHelper.INSTANCE.translSettingsButton("toggle_color")))
      .addAll(TranslationHelper.INSTANCE.getTranslatedLines(TranslationHelper.INSTANCE.translSettingsButton("toggle_color_detail"), null, class_124.field_1080))
      .build();
   private final Supplier<class_1767> getColor;
   private final Consumer<class_1767> setColor;

   public ColorToggleButton(Position position, Supplier<class_1767> getColor, Consumer<class_1767> setColor) {
      super(position, Dimension.SQUARE_18, b -> {});
      this.getColor = getColor;
      this.setColor = setColor;
      this.setOnClick(this::onClick);
   }

   private void onClick(int button) {
      this.toggleColor(button);
   }

   private void toggleColor(int button) {
      if (button == 0) {
         this.setColor.accept(this.nextColor(this.getColor.get()));
      } else if (button == 1) {
         this.setColor.accept(this.previousColor(this.getColor.get()));
      }
   }

   private class_1767 nextColor(class_1767 color) {
      return DYE_VALUES[(color.ordinal() + 1) % DYE_VALUES.length];
   }

   private class_1767 previousColor(class_1767 color) {
      return DYE_VALUES[(color.ordinal() - 1 + DYE_VALUES.length) % DYE_VALUES.length];
   }

   @Override
   protected void renderBg(class_332 guiGraphics, class_310 minecraft, int mouseX, int mouseY) {
      if (this.method_25405(mouseX, mouseY)) {
         GuiHelper.blit(guiGraphics, this.x, this.y, GuiHelper.DEFAULT_BUTTON_HOVERED_BACKGROUND);
      } else {
         GuiHelper.blit(guiGraphics, this.x, this.y, GuiHelper.DEFAULT_BUTTON_BACKGROUND);
      }
   }

   @Override
   protected void renderWidget(class_332 guiGraphics, int mouseX, int mouseY, float partialTicks) {
      RenderSystem.disableDepthTest();
      RenderSystem.colorMask(true, true, true, false);
      int color = this.getColor.get().method_7787() | -939524096;
      guiGraphics.method_25296(this.x + 3, this.y + 3, this.x + 15, this.y + 15, color, color);
      RenderSystem.colorMask(true, true, true, true);
      RenderSystem.enableDepthTest();
   }

   @Override
   public void renderTooltip(class_437 screen, class_332 guiGraphics, int mouseX, int mouseY) {
      super.renderTooltip(screen, guiGraphics, mouseX, mouseY);
      if (this.method_25405(mouseX, mouseY)) {
         guiGraphics.method_51437(screen.field_22793, TOOLTIP, Optional.empty(), mouseX, mouseY);
      }
   }

   @Override
   public void method_37020(class_6382 narrationElementOutput) {
   }
}
