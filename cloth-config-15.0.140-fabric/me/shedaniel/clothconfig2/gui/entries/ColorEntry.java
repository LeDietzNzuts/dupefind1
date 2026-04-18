package me.shedaniel.clothconfig2.gui.entries;

import java.util.Locale;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Supplier;
import me.shedaniel.clothconfig2.gui.widget.ColorDisplayWidget;
import net.minecraft.class_2561;
import net.minecraft.class_310;
import net.minecraft.class_332;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.ApiStatus.Internal;

public class ColorEntry extends TextFieldListEntry<Integer> {
   private final ColorDisplayWidget colorDisplayWidget;
   private boolean alpha = true;

   @Deprecated
   @Internal
   public ColorEntry(
      class_2561 fieldName,
      int value,
      class_2561 resetButtonKey,
      Supplier<Integer> defaultValue,
      Consumer<Integer> saveConsumer,
      Supplier<Optional<class_2561[]>> tooltipSupplier,
      boolean requiresRestart
   ) {
      super(fieldName, 0, resetButtonKey, defaultValue, tooltipSupplier, requiresRestart);
      ColorEntry.ColorValue colorValue = this.getColorValue(String.valueOf(value));
      if (colorValue.hasError()) {
         throw new IllegalArgumentException("Invalid Color: " + colorValue.getError().name());
      } else {
         this.alpha = false;
         this.saveCallback = saveConsumer;
         this.original = value;
         this.textFieldWidget.method_1852(this.getHexColorString(value));
         this.colorDisplayWidget = new ColorDisplayWidget(this.textFieldWidget, 0, 0, 20, this.getColorValueColor(this.textFieldWidget.method_1882()));
         this.resetButton.field_22767 = button -> this.textFieldWidget.method_1852(this.getHexColorString(defaultValue.get()));
      }
   }

   protected boolean isChanged(Integer original, String s) {
      ColorEntry.ColorValue colorValue = this.getColorValue(s);
      return colorValue.hasError() || this.original != colorValue.color;
   }

   @Override
   public void render(class_332 graphics, int index, int y, int x, int entryWidth, int entryHeight, int mouseX, int mouseY, boolean isHovered, float delta) {
      super.render(graphics, index, y, x, entryWidth, entryHeight, mouseX, mouseY, isHovered, delta);
      this.colorDisplayWidget.method_46419(y);
      ColorEntry.ColorValue value = this.getColorValue(this.textFieldWidget.method_1882());
      if (!value.hasError()) {
         this.colorDisplayWidget.setColor(this.alpha ? value.getColor() : 0xFF000000 | value.getColor());
      }

      if (class_310.method_1551().field_1772.method_1726()) {
         this.colorDisplayWidget.method_46421(x + this.resetButton.method_25368() + this.textFieldWidget.method_25368());
      } else {
         this.colorDisplayWidget.method_46421(this.textFieldWidget.method_46426() - 23);
      }

      this.colorDisplayWidget.method_25394(graphics, mouseX, mouseY, delta);
   }

   @Override
   protected boolean isMatchDefault(String text) {
      if (!this.getDefaultValue().isPresent()) {
         return false;
      } else {
         ColorEntry.ColorValue colorValue = this.getColorValue(text);
         return !colorValue.hasError() && colorValue.color == this.getDefaultValue().get();
      }
   }

   @Override
   public boolean isEdited() {
      ColorEntry.ColorValue colorValue = this.getColorValue(this.textFieldWidget.method_1882());
      return colorValue.hasError() || colorValue.color != this.original;
   }

   public Integer getValue() {
      return this.getColorValueColor(this.textFieldWidget.method_1882());
   }

   @Deprecated
   public void setValue(int color) {
      this.textFieldWidget.method_1852(this.getHexColorString(color));
   }

   @Override
   public Optional<class_2561> getError() {
      ColorEntry.ColorValue colorValue = this.getColorValue(this.textFieldWidget.method_1882());
      return colorValue.hasError()
         ? Optional.of(class_2561.method_43471("text.cloth-config.error.color." + colorValue.getError().name().toLowerCase(Locale.ROOT)))
         : super.getError();
   }

   public void withAlpha() {
      if (!this.alpha) {
         this.alpha = true;
         this.textFieldWidget.method_1852(this.getHexColorString(this.original));
      }
   }

   public void withoutAlpha() {
      if (this.alpha) {
         this.alpha = false;
         this.textFieldWidget.method_1852(this.getHexColorString(this.original));
      }
   }

   protected String stripHexStarter(String hex) {
      return hex.startsWith("#") ? hex.substring(1) : hex;
   }

   protected boolean isValidColorString(String str) {
      return !this.getColorValue(str).hasError();
   }

   protected int getColorValueColor(String str) {
      return this.getColorValue(str).getColor();
   }

   protected ColorEntry.ColorValue getColorValue(String str) {
      try {
         int color;
         if (str.startsWith("#")) {
            String stripHexStarter = this.stripHexStarter(str);
            if (stripHexStarter.length() > 8) {
               return ColorEntry.ColorError.INVALID_COLOR.toValue();
            }

            if (!this.alpha && stripHexStarter.length() > 6) {
               return ColorEntry.ColorError.NO_ALPHA_ALLOWED.toValue();
            }

            color = (int)Long.parseLong(stripHexStarter, 16);
         } else {
            color = (int)Long.parseLong(str);
         }

         int a = color >> 24 & 0xFF;
         if (!this.alpha && a > 0) {
            return ColorEntry.ColorError.NO_ALPHA_ALLOWED.toValue();
         } else if (a >= 0 && a <= 255) {
            int r = color >> 16 & 0xFF;
            if (r >= 0 && r <= 255) {
               int g = color >> 8 & 0xFF;
               if (g >= 0 && g <= 255) {
                  int b = color & 0xFF;
                  return b >= 0 && b <= 255 ? new ColorEntry.ColorValue(color) : ColorEntry.ColorError.INVALID_BLUE.toValue();
               } else {
                  return ColorEntry.ColorError.INVALID_GREEN.toValue();
               }
            } else {
               return ColorEntry.ColorError.INVALID_RED.toValue();
            }
         } else {
            return ColorEntry.ColorError.INVALID_ALPHA.toValue();
         }
      } catch (NumberFormatException var7) {
         return ColorEntry.ColorError.INVALID_COLOR.toValue();
      }
   }

   protected String getHexColorString(int color) {
      return "#" + StringUtils.leftPad(Integer.toHexString(color), this.alpha ? 8 : 6, '0');
   }

   protected static enum ColorError {
      NO_ALPHA_ALLOWED,
      INVALID_ALPHA,
      INVALID_RED,
      INVALID_GREEN,
      INVALID_BLUE,
      INVALID_COLOR;

      private final ColorEntry.ColorValue value = new ColorEntry.ColorValue(this);

      public ColorEntry.ColorValue toValue() {
         return this.value;
      }
   }

   protected static class ColorValue {
      private int color = -1;
      @Nullable
      private ColorEntry.ColorError error = null;

      public ColorValue(int color) {
         this.color = color;
      }

      public ColorValue(ColorEntry.ColorError error) {
         this.error = error;
      }

      public int getColor() {
         return this.color;
      }

      @Nullable
      public ColorEntry.ColorError getError() {
         return this.error;
      }

      public boolean hasError() {
         return this.getError() != null;
      }
   }
}
