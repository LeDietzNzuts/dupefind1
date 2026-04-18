package net.caffeinemc.mods.sodium.client.gui.options.control;

import net.caffeinemc.mods.sodium.client.gui.options.Option;
import net.caffeinemc.mods.sodium.client.gui.widgets.AbstractWidget;
import net.caffeinemc.mods.sodium.client.util.Dim2i;
import net.minecraft.class_124;
import net.minecraft.class_332;
import net.minecraft.class_8016;
import net.minecraft.class_8023;
import net.minecraft.class_8030;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class ControlElement<T> extends AbstractWidget {
   protected final Option<T> option;
   protected final Dim2i dim;

   public ControlElement(Option<T> option, Dim2i dim) {
      this.option = option;
      this.dim = dim;
   }

   public int getContentWidth() {
      return this.option.getControl().getMaxWidth();
   }

   public void method_25394(class_332 graphics, int mouseX, int mouseY, float delta) {
      String name = this.option.getName().getString();
      if (this.option.isAvailable() && this.option.hasChanged()) {
         name = name + " *";
      }

      if (this.hovered || this.method_25370()) {
         name = this.truncateLabelToFit(name);
      }

      String label;
      if (this.option.isAvailable()) {
         if (this.option.hasChanged()) {
            label = class_124.field_1056 + name;
         } else {
            label = class_124.field_1068 + name;
         }
      } else {
         label = "" + class_124.field_1080 + class_124.field_1055 + name;
      }

      this.hovered = this.dim.containsCursor(mouseX, mouseY);
      this.drawRect(graphics, this.dim.x(), this.dim.y(), this.dim.getLimitX(), this.dim.getLimitY(), this.hovered ? -536870912 : -1879048192);
      this.drawString(graphics, label, this.dim.x() + 6, this.dim.getCenterY() - 4, -1);
      if (this.method_25370()) {
         this.drawBorder(graphics, this.dim.x(), this.dim.y(), this.dim.getLimitX(), this.dim.getLimitY(), -1);
      }
   }

   @NotNull
   private String truncateLabelToFit(String name) {
      String suffix = "...";
      int suffixWidth = this.font.method_1727(suffix);
      int nameFontWidth = this.font.method_1727(name);
      int targetWidth = this.dim.width() - this.getContentWidth() - 20;
      if (nameFontWidth > targetWidth) {
         targetWidth -= suffixWidth;
         int maxLabelChars = name.length() - 3;
         int minLabelChars = 1;

         while (maxLabelChars - minLabelChars > 1) {
            int mid = (maxLabelChars + minLabelChars) / 2;
            String midName = name.substring(0, mid);
            int midWidth = this.font.method_1727(midName);
            if (midWidth > targetWidth) {
               maxLabelChars = mid;
            } else {
               minLabelChars = mid;
            }
         }

         name = name.substring(0, minLabelChars).trim() + suffix;
      }

      return name;
   }

   public Option<T> getOption() {
      return this.option;
   }

   public Dim2i getDimensions() {
      return this.dim;
   }

   @Nullable
   @Override
   public class_8016 method_48205(class_8023 event) {
      return !this.option.isAvailable() ? null : super.method_48205(event);
   }

   public class_8030 method_48202() {
      return new class_8030(this.dim.x(), this.dim.y(), this.dim.width(), this.dim.height());
   }
}
