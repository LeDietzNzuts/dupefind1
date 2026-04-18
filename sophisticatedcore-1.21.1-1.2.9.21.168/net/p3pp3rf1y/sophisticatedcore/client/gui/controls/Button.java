package net.p3pp3rf1y.sophisticatedcore.client.gui.controls;

import java.util.List;
import java.util.Optional;
import java.util.function.IntConsumer;
import javax.annotation.Nullable;
import net.minecraft.class_2561;
import net.minecraft.class_310;
import net.minecraft.class_332;
import net.minecraft.class_437;
import net.minecraft.class_6382;
import net.p3pp3rf1y.sophisticatedcore.client.gui.utils.GuiHelper;
import net.p3pp3rf1y.sophisticatedcore.client.gui.utils.Position;
import net.p3pp3rf1y.sophisticatedcore.client.gui.utils.TextureBlitData;

public class Button extends ButtonBase {
   private final TextureBlitData backgroundTexture;
   @Nullable
   private final TextureBlitData hoveredBackgroundTexture;
   @Nullable
   private final TextureBlitData foregroundTexture;
   private List<class_2561> tooltip;
   private boolean hovered = false;

   public Button(Position position, ButtonDefinition buttonDefinition, IntConsumer onClick) {
      super(position, buttonDefinition.getDimension(), onClick);
      this.backgroundTexture = buttonDefinition.getBackgroundTexture();
      this.foregroundTexture = buttonDefinition.getForegroundTexture();
      this.hoveredBackgroundTexture = buttonDefinition.getHoveredBackgroundTexture();
      this.tooltip = buttonDefinition.getTooltip();
   }

   public boolean isHovered() {
      return this.hovered;
   }

   @Override
   protected void renderBg(class_332 guiGraphics, class_310 minecraft, int mouseX, int mouseY) {
      if (this.method_25405(mouseX, mouseY)) {
         this.hovered = true;
         if (this.hoveredBackgroundTexture != null) {
            GuiHelper.blit(guiGraphics, this.x, this.y, this.hoveredBackgroundTexture);
         }
      } else {
         this.hovered = false;
         GuiHelper.blit(guiGraphics, this.x, this.y, this.backgroundTexture);
      }
   }

   protected List<class_2561> getTooltip() {
      return this.tooltip;
   }

   @Override
   protected void renderWidget(class_332 guiGraphics, int mouseX, int mouseY, float partialTicks) {
      if (this.foregroundTexture != null) {
         GuiHelper.blit(guiGraphics, this.x, this.y, this.foregroundTexture);
      }
   }

   @Override
   public void method_37020(class_6382 narrationElementOutput) {
   }

   public void setTooltip(List<class_2561> tooltip) {
      this.tooltip = tooltip;
   }

   @Override
   public void renderTooltip(class_437 screen, class_332 guiGraphics, int mouseX, int mouseY) {
      super.renderTooltip(screen, guiGraphics, mouseX, mouseY);
      if (this.visible && this.method_25405(mouseX, mouseY)) {
         guiGraphics.method_51437(screen.field_22793, this.getTooltip(), Optional.empty(), mouseX, mouseY);
      }
   }
}
