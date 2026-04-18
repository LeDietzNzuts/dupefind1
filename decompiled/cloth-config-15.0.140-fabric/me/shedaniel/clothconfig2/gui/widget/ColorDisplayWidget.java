package me.shedaniel.clothconfig2.gui.widget;

import net.minecraft.class_2561;
import net.minecraft.class_332;
import net.minecraft.class_339;
import net.minecraft.class_342;
import net.minecraft.class_6382;

public class ColorDisplayWidget extends class_339 {
   protected class_342 textFieldWidget;
   protected int color;
   protected int size;

   public ColorDisplayWidget(class_342 textFieldWidget, int x, int y, int size, int color) {
      super(x, y, size, size, class_2561.method_43473());
      this.textFieldWidget = textFieldWidget;
      this.color = color;
      this.size = size;
   }

   public void method_48579(class_332 graphics, int mouseX, int mouseY, float delta) {
      graphics.method_25296(
         this.method_46426(),
         this.method_46427(),
         this.method_46426() + this.size,
         this.method_46427() + this.size,
         this.textFieldWidget.method_25370() ? -1 : -6250336,
         this.textFieldWidget.method_25370() ? -1 : -6250336
      );
      graphics.method_25296(this.method_46426() + 1, this.method_46427() + 1, this.method_46426() + this.size - 1, this.method_46427() + this.size - 1, -1, -1);
      graphics.method_25296(
         this.method_46426() + 1, this.method_46427() + 1, this.method_46426() + this.size - 1, this.method_46427() + this.size - 1, this.color, this.color
      );
   }

   public void method_25348(double mouseX, double mouseY) {
   }

   public void method_25357(double mouseX, double mouseY) {
   }

   public void method_47399(class_6382 narrationElementOutput) {
   }

   public void setColor(int color) {
      this.color = color;
   }
}
