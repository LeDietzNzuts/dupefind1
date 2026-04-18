package net.caffeinemc.mods.sodium.client.gui.options.control;

import net.caffeinemc.mods.sodium.client.gui.options.Option;
import net.caffeinemc.mods.sodium.client.util.Dim2i;
import net.minecraft.class_124;
import net.minecraft.class_2583;
import net.minecraft.class_332;
import net.minecraft.class_3532;
import net.minecraft.class_5250;
import net.minecraft.class_768;
import org.apache.commons.lang3.Validate;

public class SliderControl implements Control<Integer> {
   private final Option<Integer> option;
   private final int min;
   private final int max;
   private final int interval;
   private final ControlValueFormatter mode;

   public SliderControl(Option<Integer> option, int min, int max, int interval, ControlValueFormatter mode) {
      Validate.isTrue(max > min, "The maximum value must be greater than the minimum value", new Object[0]);
      Validate.isTrue(interval > 0, "The slider interval must be greater than zero", new Object[0]);
      Validate.isTrue((max - min) % interval == 0, "The maximum value must be divisable by the interval", new Object[0]);
      Validate.notNull(mode, "The slider mode must not be null", new Object[0]);
      this.option = option;
      this.min = min;
      this.max = max;
      this.interval = interval;
      this.mode = mode;
   }

   @Override
   public ControlElement<Integer> createElement(Dim2i dim) {
      return new SliderControl.Button(this.option, dim, this.min, this.max, this.interval, this.mode);
   }

   @Override
   public Option<Integer> getOption() {
      return this.option;
   }

   @Override
   public int getMaxWidth() {
      return 170;
   }

   private static class Button extends ControlElement<Integer> {
      private static final int THUMB_WIDTH = 2;
      private static final int TRACK_HEIGHT = 1;
      private final class_768 sliderBounds;
      private int contentWidth;
      private final ControlValueFormatter formatter;
      private final int min;
      private final int max;
      private final int range;
      private final int interval;
      private double thumbPosition;
      private boolean sliderHeld;

      public Button(Option<Integer> option, Dim2i dim, int min, int max, int interval, ControlValueFormatter formatter) {
         super(option, dim);
         this.min = min;
         this.max = max;
         this.range = max - min;
         this.interval = interval;
         this.thumbPosition = this.getThumbPositionForValue(option.getValue());
         this.formatter = formatter;
         this.sliderBounds = new class_768(dim.getLimitX() - 96, dim.getCenterY() - 5, 90, 10);
         this.sliderHeld = false;
      }

      @Override
      public void method_25394(class_332 graphics, int mouseX, int mouseY, float delta) {
         int sliderX = this.sliderBounds.method_3321();
         int sliderY = this.sliderBounds.method_3322();
         int sliderWidth = this.sliderBounds.method_3319();
         int sliderHeight = this.sliderBounds.method_3320();
         class_5250 label = this.formatter.format(this.option.getValue()).method_27661();
         if (!this.option.isAvailable()) {
            label.method_10862(class_2583.field_24360.method_10977(class_124.field_1080).method_10978(true));
         }

         int labelWidth = this.font.method_27525(label);
         boolean drawSlider = this.option.isAvailable() && (this.hovered || this.method_25370());
         if (drawSlider) {
            this.contentWidth = sliderWidth + labelWidth;
         } else {
            this.contentWidth = labelWidth;
         }

         super.method_25394(graphics, mouseX, mouseY, delta);
         if (drawSlider) {
            this.thumbPosition = this.getThumbPositionForValue(this.option.getValue());
            double thumbOffset = class_3532.method_15350((double)(this.getIntValue() - this.min) / this.range * sliderWidth, 0.0, sliderWidth);
            int thumbX = (int)(sliderX + thumbOffset - 2.0);
            int trackY = (int)(sliderY + sliderHeight / 2.0F - 0.5);
            this.drawRect(graphics, thumbX, sliderY, thumbX + 4, sliderY + sliderHeight, -1);
            this.drawRect(graphics, sliderX, trackY, sliderX + sliderWidth, trackY + 1, -1);
            this.drawString(graphics, label, sliderX - labelWidth - 6, sliderY + sliderHeight / 2 - 4, -1);
         } else {
            this.drawString(graphics, label, sliderX + sliderWidth - labelWidth, sliderY + sliderHeight / 2 - 4, -1);
         }
      }

      @Override
      public int getContentWidth() {
         return this.contentWidth;
      }

      public int getIntValue() {
         return this.min + this.interval * (int)Math.round(this.getSnappedThumbPosition() / this.interval);
      }

      public double getSnappedThumbPosition() {
         return this.thumbPosition / (1.0 / this.range);
      }

      public double getThumbPositionForValue(int value) {
         return (value - this.min) * (1.0 / this.range);
      }

      public boolean method_25402(double mouseX, double mouseY, int button) {
         this.sliderHeld = false;
         if (this.option.isAvailable() && button == 0 && this.dim.containsCursor(mouseX, mouseY)) {
            if (this.sliderBounds.method_3318((int)mouseX, (int)mouseY)) {
               this.setValueFromMouse(mouseX);
               this.sliderHeld = true;
            }

            return true;
         } else {
            return false;
         }
      }

      private void setValueFromMouse(double d) {
         this.setValue((d - this.sliderBounds.method_3321()) / this.sliderBounds.method_3319());
      }

      public void setValue(double d) {
         this.thumbPosition = class_3532.method_15350(d, 0.0, 1.0);
         int value = this.getIntValue();
         if (this.option.getValue() != value) {
            this.option.setValue(value);
         }
      }

      public boolean method_25404(int keyCode, int scanCode, int modifiers) {
         if (!this.method_25370()) {
            return false;
         } else if (keyCode == 263) {
            this.option.setValue(class_3532.method_15340(this.option.getValue() - this.interval, this.min, this.max));
            return true;
         } else if (keyCode == 262) {
            this.option.setValue(class_3532.method_15340(this.option.getValue() + this.interval, this.min, this.max));
            return true;
         } else {
            return false;
         }
      }

      public boolean method_25403(double mouseX, double mouseY, int button, double deltaX, double deltaY) {
         if (this.option.isAvailable() && button == 0) {
            if (this.sliderHeld) {
               this.setValueFromMouse(mouseX);
            }

            return true;
         } else {
            return false;
         }
      }
   }
}
