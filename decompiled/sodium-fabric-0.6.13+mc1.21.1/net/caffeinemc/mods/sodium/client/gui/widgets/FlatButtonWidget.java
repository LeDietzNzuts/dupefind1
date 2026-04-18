package net.caffeinemc.mods.sodium.client.gui.widgets;

import java.util.Objects;
import net.caffeinemc.mods.sodium.client.util.Dim2i;
import net.minecraft.class_2561;
import net.minecraft.class_332;
import net.minecraft.class_4068;
import net.minecraft.class_8016;
import net.minecraft.class_8023;
import net.minecraft.class_8030;
import net.minecraft.class_8494;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class FlatButtonWidget extends AbstractWidget implements class_4068 {
   private final Dim2i dim;
   private final Runnable action;
   @NotNull
   private FlatButtonWidget.Style style = FlatButtonWidget.Style.defaults();
   private boolean selected;
   private boolean enabled = true;
   private boolean visible = true;
   private class_2561 label;

   public FlatButtonWidget(Dim2i dim, class_2561 label, Runnable action) {
      this.dim = dim;
      this.label = label;
      this.action = action;
   }

   public void method_25394(class_332 graphics, int mouseX, int mouseY, float delta) {
      if (this.visible) {
         this.hovered = this.dim.containsCursor(mouseX, mouseY);
         int backgroundColor = this.enabled ? (this.hovered ? this.style.bgHovered : this.style.bgDefault) : this.style.bgDisabled;
         int textColor = this.enabled ? this.style.textDefault : this.style.textDisabled;
         int strWidth = this.font.method_27525(this.label);
         this.drawRect(graphics, this.dim.x(), this.dim.y(), this.dim.getLimitX(), this.dim.getLimitY(), backgroundColor);
         this.drawString(graphics, this.label, this.dim.getCenterX() - strWidth / 2, this.dim.getCenterY() - 4, textColor);
         if (this.enabled && this.selected) {
            this.drawRect(graphics, this.dim.x(), this.dim.getLimitY() - 1, this.dim.getLimitX(), this.dim.getLimitY(), -7019309);
         }

         if (this.enabled && this.method_25370()) {
            this.drawBorder(graphics, this.dim.x(), this.dim.y(), this.dim.getLimitX(), this.dim.getLimitY(), -1);
         }
      }
   }

   public void setStyle(@NotNull FlatButtonWidget.Style style) {
      Objects.requireNonNull(style);
      this.style = style;
   }

   public void setSelected(boolean selected) {
      this.selected = selected;
   }

   public boolean method_25402(double mouseX, double mouseY, int button) {
      if (!this.enabled || !this.visible) {
         return false;
      } else if (button == 0 && this.dim.containsCursor(mouseX, mouseY)) {
         this.doAction();
         return true;
      } else {
         return false;
      }
   }

   public boolean method_25404(int keyCode, int scanCode, int modifiers) {
      if (!this.method_25370()) {
         return false;
      } else if (class_8494.method_51255(keyCode)) {
         this.doAction();
         return true;
      } else {
         return false;
      }
   }

   private void doAction() {
      this.action.run();
      this.playClickSound();
   }

   public void setEnabled(boolean enabled) {
      this.enabled = enabled;
   }

   public void setVisible(boolean visible) {
      this.visible = visible;
   }

   public void setLabel(class_2561 text) {
      this.label = text;
   }

   public class_2561 getLabel() {
      return this.label;
   }

   @Nullable
   @Override
   public class_8016 method_48205(class_8023 event) {
      return this.enabled && this.visible ? super.method_48205(event) : null;
   }

   public class_8030 method_48202() {
      return new class_8030(this.dim.x(), this.dim.y(), this.dim.width(), this.dim.height());
   }

   public static class Style {
      public int bgHovered;
      public int bgDefault;
      public int bgDisabled;
      public int textDefault;
      public int textDisabled;

      public static FlatButtonWidget.Style defaults() {
         FlatButtonWidget.Style style = new FlatButtonWidget.Style();
         style.bgHovered = -536870912;
         style.bgDefault = -1879048192;
         style.bgDisabled = 1610612736;
         style.textDefault = -1;
         style.textDisabled = -1862270977;
         return style;
      }
   }
}
