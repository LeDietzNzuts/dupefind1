package me.shedaniel.clothconfig2.gui.widget;

import me.shedaniel.clothconfig2.ClothConfigInitializer;
import me.shedaniel.clothconfig2.api.ScrollingContainer;
import me.shedaniel.math.Rectangle;
import me.shedaniel.math.impl.PointHelper;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.class_287;
import net.minecraft.class_289;
import net.minecraft.class_2960;
import net.minecraft.class_310;
import net.minecraft.class_332;
import net.minecraft.class_3532;
import net.minecraft.class_5253.class_5254;

@Deprecated
@Environment(EnvType.CLIENT)
public abstract class DynamicNewSmoothScrollingEntryListWidget<E extends DynamicEntryListWidget.Entry<E>> extends DynamicEntryListWidget<E> {
   protected double target;
   protected boolean smoothScrolling = true;
   protected long start;
   protected long duration;

   public DynamicNewSmoothScrollingEntryListWidget(class_310 client, int width, int height, int top, int bottom, class_2960 backgroundLocation) {
      super(client, width, height, top, bottom, backgroundLocation);
   }

   public boolean isSmoothScrolling() {
      return this.smoothScrolling;
   }

   public void setSmoothScrolling(boolean smoothScrolling) {
      this.smoothScrolling = smoothScrolling;
   }

   @Override
   public void capYPosition(double double_1) {
      if (!this.smoothScrolling) {
         this.scroll = class_3532.method_15350(double_1, 0.0, this.getMaxScroll());
      } else {
         this.scroll = ScrollingContainer.clampExtension(double_1, this.getMaxScroll());
         this.target = ScrollingContainer.clampExtension(double_1, this.getMaxScroll());
      }
   }

   @Override
   public boolean method_25403(double mouseX, double mouseY, int button, double deltaX, double deltaY) {
      if (!this.smoothScrolling) {
         return super.method_25403(mouseX, mouseY, button, deltaX, deltaY);
      } else if (this.getFocused() != null && this.method_25397() && button == 0 && this.getFocused().method_25403(mouseX, mouseY, button, deltaX, deltaY)) {
         return true;
      } else if (button == 0 && this.scrolling) {
         if (mouseY < this.top) {
            this.capYPosition(0.0);
         } else if (mouseY > this.bottom) {
            this.capYPosition(this.getMaxScroll());
         } else {
            double double_5 = Math.max(1, this.getMaxScroll());
            int int_2 = this.bottom - this.top;
            int int_3 = class_3532.method_15340((int)((float)(int_2 * int_2) / this.getMaxScrollPosition()), 32, int_2 - 8);
            double double_6 = Math.max(1.0, double_5 / (int_2 - int_3));
            this.capYPosition(class_3532.method_15350(this.getScroll() + deltaY * double_6, 0.0, this.getMaxScroll()));
         }

         return true;
      } else {
         return false;
      }
   }

   @Override
   public boolean method_25401(double mouseX, double mouseY, double amountX, double amountY) {
      for (E entry : this.visibleChildren()) {
         if (entry.method_25401(mouseX, mouseY, amountX, amountY)) {
            return true;
         }
      }

      if (amountY == 0.0) {
         return false;
      } else if (!this.smoothScrolling && amountY != 0.0) {
         this.scroll += 16.0 * -amountY;
         this.scroll = class_3532.method_15350(amountY, 0.0, this.getMaxScroll());
         return true;
      } else {
         this.offset(ClothConfigInitializer.getScrollStep() * -amountY, true);
         return true;
      }
   }

   public void offset(double value, boolean animated) {
      this.scrollTo(this.target + value, animated);
   }

   public void scrollTo(double value, boolean animated) {
      this.scrollTo(value, animated, ClothConfigInitializer.getScrollDuration());
   }

   public void scrollTo(double value, boolean animated, long duration) {
      this.target = ScrollingContainer.clampExtension(value, this.getMaxScroll());
      if (animated) {
         this.start = System.currentTimeMillis();
         this.duration = duration;
      } else {
         this.scroll = this.target;
      }
   }

   @Override
   public void method_25394(class_332 graphics, int mouseX, int mouseY, float delta) {
      double[] target = new double[]{this.target};
      this.scroll = ScrollingContainer.handleScrollingPosition(target, this.scroll, this.getMaxScroll(), delta, this.start, this.duration);
      this.target = target[0];
      super.method_25394(graphics, mouseX, mouseY, delta);
   }

   @Override
   protected void renderScrollBar(
      class_332 graphics, class_289 tessellator, class_287 buffer, int maxScroll, int scrollbarPositionMinX, int scrollbarPositionMaxX
   ) {
      if (!this.smoothScrolling) {
         super.renderScrollBar(graphics, tessellator, buffer, maxScroll, scrollbarPositionMinX, scrollbarPositionMaxX);
      } else if (maxScroll > 0) {
         int height = (this.bottom - this.top) * (this.bottom - this.top) / this.getMaxScrollPosition();
         height = class_3532.method_15340(height, 32, this.bottom - this.top - 8);
         height = (int)(
            height
               - Math.min(
                  (double)(this.scroll < 0.0 ? (int)(-this.scroll) : (this.scroll > this.getMaxScroll() ? (int)this.scroll - this.getMaxScroll() : 0)),
                  height * 0.95
               )
         );
         height = Math.max(10, height);
         int minY = Math.min(Math.max((int)this.getScroll() * (this.bottom - this.top - height) / maxScroll + this.top, this.top), this.bottom - height);
         int bottomc = new Rectangle(scrollbarPositionMinX, minY, scrollbarPositionMaxX - scrollbarPositionMinX, height).contains(PointHelper.ofMouse())
            ? 168
            : 128;
         int topc = new Rectangle(scrollbarPositionMinX, minY, scrollbarPositionMaxX - scrollbarPositionMinX, height).contains(PointHelper.ofMouse())
            ? 222
            : 172;
         graphics.method_25294(scrollbarPositionMinX, this.top, scrollbarPositionMaxX, this.bottom, -16777216);
         graphics.method_25294(scrollbarPositionMinX, minY, scrollbarPositionMaxX, minY + height, class_5254.method_27764(255, bottomc, bottomc, bottomc));
         graphics.method_25294(scrollbarPositionMinX, minY, scrollbarPositionMaxX - 1, minY + height - 1, class_5254.method_27764(255, topc, topc, topc));
      }
   }

   public static class Interpolation {
      public static double expoEase(double start, double end, double amount) {
         return start + (end - start) * ClothConfigInitializer.getEasingMethod().apply(amount);
      }
   }

   public static class Precision {
      public static final float FLOAT_EPSILON = 0.001F;
      public static final double DOUBLE_EPSILON = 1.0E-7;

      public static boolean almostEquals(float value1, float value2, float acceptableDifference) {
         return Math.abs(value1 - value2) <= acceptableDifference;
      }

      public static boolean almostEquals(double value1, double value2, double acceptableDifference) {
         return Math.abs(value1 - value2) <= acceptableDifference;
      }
   }
}
