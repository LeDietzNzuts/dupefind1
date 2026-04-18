package net.p3pp3rf1y.sophisticatedcore.client.gui.controls;

import java.util.List;
import java.util.Optional;
import java.util.function.IntConsumer;
import java.util.function.Supplier;
import javax.annotation.Nullable;
import net.minecraft.class_2561;
import net.minecraft.class_310;
import net.minecraft.class_332;
import net.minecraft.class_437;
import net.p3pp3rf1y.sophisticatedcore.client.gui.utils.Dimension;
import net.p3pp3rf1y.sophisticatedcore.client.gui.utils.Position;

public class ColorButton extends ButtonBase {
   private final Supplier<Integer> colorGetter;
   private final List<class_2561> tooltip;

   public ColorButton(Position position, Dimension dimension, Supplier<Integer> colorGetter, IntConsumer onClick, @Nullable class_2561 tooltip) {
      super(position, dimension, onClick);
      this.colorGetter = colorGetter;
      this.tooltip = tooltip == null ? List.of() : List.of(tooltip);
   }

   @Override
   protected void renderBg(class_332 guiGraphics, class_310 minecraft, int mouseX, int mouseY) {
      int color = this.method_25405(mouseX, mouseY) ? -1 : -3355444;
      guiGraphics.method_25294(this.x, this.y, this.x + this.getWidth(), this.y + this.getHeight(), color);
   }

   @Override
   protected void renderWidget(class_332 guiGraphics, int mouseX, int mouseY, float partialTicks) {
      int color = this.colorGetter.get();
      if (color == -1) {
         for (int row = 0; row < this.getHeight() - 2; row++) {
            for (int column = 0; column < this.getWidth() - 2; column++) {
               guiGraphics.method_25294(
                  this.x + column + 1, this.y + row + 1, this.x + column + 2, this.y + row + 2, (row + column) % 2 == 0 ? 268225740 : -7829368
               );
            }
         }
      } else {
         guiGraphics.method_25294(this.x + 1, this.y + 1, this.x + this.getWidth() - 1, this.y + this.getHeight() - 1, this.colorGetter.get());
      }
   }

   @Override
   public void renderTooltip(class_437 screen, class_332 guiGraphics, int mouseX, int mouseY) {
      super.renderTooltip(screen, guiGraphics, mouseX, mouseY);
      if (this.visible && this.method_25405(mouseX, mouseY) && !this.tooltip.isEmpty()) {
         guiGraphics.method_51437(screen.field_22793, this.tooltip, Optional.empty(), mouseX, mouseY);
      }
   }
}
