package net.caffeinemc.mods.sodium.client.gui.options.control;

import net.caffeinemc.mods.sodium.client.gui.options.Option;
import net.caffeinemc.mods.sodium.client.util.Dim2i;
import net.minecraft.class_332;
import net.minecraft.class_768;
import net.minecraft.class_8494;

public class TickBoxControl implements Control<Boolean> {
   private final Option<Boolean> option;

   public TickBoxControl(Option<Boolean> option) {
      this.option = option;
   }

   @Override
   public ControlElement<Boolean> createElement(Dim2i dim) {
      return new TickBoxControl.TickBoxControlElement(this.option, dim);
   }

   @Override
   public int getMaxWidth() {
      return 30;
   }

   @Override
   public Option<Boolean> getOption() {
      return this.option;
   }

   private static class TickBoxControlElement extends ControlElement<Boolean> {
      private final class_768 button;

      public TickBoxControlElement(Option<Boolean> option, Dim2i dim) {
         super(option, dim);
         this.button = new class_768(dim.getLimitX() - 16, dim.getCenterY() - 5, 10, 10);
      }

      @Override
      public void method_25394(class_332 graphics, int mouseX, int mouseY, float delta) {
         super.method_25394(graphics, mouseX, mouseY, delta);
         int x = this.button.method_3321();
         int y = this.button.method_3322();
         int w = x + this.button.method_3319();
         int h = y + this.button.method_3320();
         boolean enabled = this.option.isAvailable();
         boolean ticked = enabled && this.option.getValue();
         int color;
         if (enabled) {
            color = ticked ? -7019309 : -1;
         } else {
            color = -5592406;
         }

         if (ticked) {
            this.drawRect(graphics, x + 2, y + 2, w - 2, h - 2, color);
         }

         this.drawBorder(graphics, x, y, w, h, color);
      }

      public boolean method_25402(double mouseX, double mouseY, int button) {
         if (this.option.isAvailable() && button == 0 && this.dim.containsCursor(mouseX, mouseY)) {
            this.toggleControl();
            this.playClickSound();
            return true;
         } else {
            return false;
         }
      }

      public boolean method_25404(int keyCode, int scanCode, int modifiers) {
         if (!this.method_25370()) {
            return false;
         } else if (class_8494.method_51255(keyCode)) {
            this.toggleControl();
            this.playClickSound();
            return true;
         } else {
            return false;
         }
      }

      public void toggleControl() {
         this.option.setValue(!this.option.getValue());
      }
   }
}
