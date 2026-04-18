package me.shedaniel.clothconfig2.gui.entries;

import com.google.common.collect.Lists;
import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.class_1041;
import net.minecraft.class_2561;
import net.minecraft.class_310;
import net.minecraft.class_332;
import net.minecraft.class_339;
import net.minecraft.class_342;
import net.minecraft.class_364;
import net.minecraft.class_4185;
import net.minecraft.class_6379;
import org.jetbrains.annotations.ApiStatus.Internal;

@Environment(EnvType.CLIENT)
public abstract class TextFieldListEntry<T> extends TooltipListEntry<T> {
   protected class_342 textFieldWidget;
   protected class_4185 resetButton;
   protected Supplier<T> defaultValue;
   protected T original;
   protected List<class_339> widgets;
   private boolean isSelected = false;

   @Deprecated
   @Internal
   protected TextFieldListEntry(class_2561 fieldName, T original, class_2561 resetButtonKey, Supplier<T> defaultValue) {
      this(fieldName, original, resetButtonKey, defaultValue, null);
   }

   @Deprecated
   @Internal
   protected TextFieldListEntry(
      class_2561 fieldName, T original, class_2561 resetButtonKey, Supplier<T> defaultValue, Supplier<Optional<class_2561[]>> tooltipSupplier
   ) {
      this(fieldName, original, resetButtonKey, defaultValue, tooltipSupplier, false);
   }

   @Deprecated
   @Internal
   protected TextFieldListEntry(
      class_2561 fieldName,
      T original,
      class_2561 resetButtonKey,
      Supplier<T> defaultValue,
      Supplier<Optional<class_2561[]>> tooltipSupplier,
      boolean requiresRestart
   ) {
      super(fieldName, tooltipSupplier, requiresRestart);
      this.defaultValue = defaultValue;
      this.original = original;
      this.textFieldWidget = new class_342(class_310.method_1551().field_1772, 0, 0, 148, 18, class_2561.method_43473()) {
         public void method_48579(class_332 graphics, int int_1, int int_2, float float_1) {
            this.method_25365(TextFieldListEntry.this.isSelected && TextFieldListEntry.this.method_25399() == this);
            TextFieldListEntry.this.textFieldPreRender(this);
            super.method_48579(graphics, int_1, int_2, float_1);
         }

         public void method_1867(String string_1) {
            super.method_1867(TextFieldListEntry.this.stripAddText(string_1));
         }
      };
      this.textFieldWidget.method_1880(999999);
      this.textFieldWidget.method_1852(String.valueOf(original));
      this.resetButton = class_4185.method_46430(resetButtonKey, widget -> this.textFieldWidget.method_1852(String.valueOf(defaultValue.get())))
         .method_46434(0, 0, class_310.method_1551().field_1772.method_27525(resetButtonKey) + 6, 20)
         .method_46431();
      this.widgets = Lists.newArrayList(new class_339[]{this.textFieldWidget, this.resetButton});
   }

   @Override
   public boolean isEdited() {
      return this.isChanged(this.original, this.textFieldWidget.method_1882());
   }

   protected boolean isChanged(T original, String s) {
      return !String.valueOf(original).equals(s);
   }

   protected static void setTextFieldWidth(class_342 widget, int width) {
      widget.method_25358(width);
   }

   @Deprecated
   public void setValue(String s) {
      this.textFieldWidget.method_1852(String.valueOf(s));
   }

   protected String stripAddText(String s) {
      return s;
   }

   protected void textFieldPreRender(class_342 widget) {
      widget.method_1868(this.getConfigError().isPresent() ? 16733525 : 14737632);
   }

   @Override
   public void updateSelected(boolean isSelected) {
      this.isSelected = isSelected;
   }

   @Override
   public void render(class_332 graphics, int index, int y, int x, int entryWidth, int entryHeight, int mouseX, int mouseY, boolean isHovered, float delta) {
      super.render(graphics, index, y, x, entryWidth, entryHeight, mouseX, mouseY, isHovered, delta);
      class_1041 window = class_310.method_1551().method_22683();
      this.resetButton.field_22763 = this.isEditable() && this.getDefaultValue().isPresent() && !this.isMatchDefault(this.textFieldWidget.method_1882());
      this.resetButton.method_46419(y);
      this.textFieldWidget.method_1888(this.isEditable());
      this.textFieldWidget.method_46419(y + 1);
      class_2561 displayedFieldName = this.getDisplayedFieldName();
      if (class_310.method_1551().field_1772.method_1726()) {
         graphics.method_35720(
            class_310.method_1551().field_1772,
            displayedFieldName.method_30937(),
            window.method_4486() - x - class_310.method_1551().field_1772.method_27525(displayedFieldName),
            y + 6,
            this.getPreferredTextColor()
         );
         this.resetButton.method_46421(x);
         this.textFieldWidget.method_46421(x + this.resetButton.method_25368());
      } else {
         graphics.method_35720(class_310.method_1551().field_1772, displayedFieldName.method_30937(), x, y + 6, this.getPreferredTextColor());
         this.resetButton.method_46421(x + entryWidth - this.resetButton.method_25368());
         this.textFieldWidget.method_46421(x + entryWidth - 148);
      }

      setTextFieldWidth(this.textFieldWidget, 148 - this.resetButton.method_25368() - 4);
      this.resetButton.method_25394(graphics, mouseX, mouseY, delta);
      this.textFieldWidget.method_25394(graphics, mouseX, mouseY, delta);
   }

   protected boolean isMatchDefault(String text) {
      Optional<T> defaultValue = this.getDefaultValue();
      return defaultValue.isPresent() && text.equals(defaultValue.get().toString());
   }

   @Override
   public Optional<T> getDefaultValue() {
      return this.defaultValue == null ? Optional.empty() : Optional.ofNullable(this.defaultValue.get());
   }

   public List<? extends class_364> method_25396() {
      return this.widgets;
   }

   @Override
   public List<? extends class_6379> narratables() {
      return this.widgets;
   }
}
