package me.shedaniel.clothconfig2.gui.entries;

import com.google.common.collect.Lists;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.class_1041;
import net.minecraft.class_2561;
import net.minecraft.class_310;
import net.minecraft.class_332;
import net.minecraft.class_339;
import net.minecraft.class_3532;
import net.minecraft.class_357;
import net.minecraft.class_364;
import net.minecraft.class_4185;
import net.minecraft.class_6379;
import org.jetbrains.annotations.ApiStatus.Internal;

@Environment(EnvType.CLIENT)
public class LongSliderEntry extends TooltipListEntry<Long> {
   protected LongSliderEntry.Slider sliderWidget;
   protected class_4185 resetButton;
   protected AtomicLong value;
   protected final long orginial;
   private long minimum;
   private long maximum;
   private final Supplier<Long> defaultValue;
   private Function<Long, class_2561> textGetter = valuex -> class_2561.method_43470(String.format("Value: %d", valuex));
   private final List<class_339> widgets;

   @Deprecated
   @Internal
   public LongSliderEntry(
      class_2561 fieldName, long minimum, long maximum, long value, Consumer<Long> saveConsumer, class_2561 resetButtonKey, Supplier<Long> defaultValue
   ) {
      this(fieldName, minimum, maximum, value, saveConsumer, resetButtonKey, defaultValue, null);
   }

   @Deprecated
   @Internal
   public LongSliderEntry(
      class_2561 fieldName,
      long minimum,
      long maximum,
      long value,
      Consumer<Long> saveConsumer,
      class_2561 resetButtonKey,
      Supplier<Long> defaultValue,
      Supplier<Optional<class_2561[]>> tooltipSupplier
   ) {
      this(fieldName, minimum, maximum, value, saveConsumer, resetButtonKey, defaultValue, tooltipSupplier, false);
   }

   @Deprecated
   @Internal
   public LongSliderEntry(
      class_2561 fieldName,
      long minimum,
      long maximum,
      long value,
      Consumer<Long> saveConsumer,
      class_2561 resetButtonKey,
      Supplier<Long> defaultValue,
      Supplier<Optional<class_2561[]>> tooltipSupplier,
      boolean requiresRestart
   ) {
      super(fieldName, tooltipSupplier, requiresRestart);
      this.orginial = value;
      this.defaultValue = defaultValue;
      this.value = new AtomicLong(value);
      this.saveCallback = saveConsumer;
      this.maximum = maximum;
      this.minimum = minimum;
      this.sliderWidget = new LongSliderEntry.Slider(0, 0, 152, 20, ((double)this.value.get() - minimum) / Math.abs(maximum - minimum));
      this.resetButton = class_4185.method_46430(resetButtonKey, widget -> this.setValue(defaultValue.get()))
         .method_46434(0, 0, class_310.method_1551().field_1772.method_27525(resetButtonKey) + 6, 20)
         .method_46431();
      this.sliderWidget.method_25355(this.textGetter.apply(this.value.get()));
      this.widgets = Lists.newArrayList(new class_339[]{this.sliderWidget, this.resetButton});
   }

   public Function<Long, class_2561> getTextGetter() {
      return this.textGetter;
   }

   public LongSliderEntry setTextGetter(Function<Long, class_2561> textGetter) {
      this.textGetter = textGetter;
      this.sliderWidget.method_25355(textGetter.apply(this.value.get()));
      return this;
   }

   public Long getValue() {
      return this.value.get();
   }

   @Deprecated
   public void setValue(long value) {
      this.sliderWidget.setValue((double)(class_3532.method_53062(value, this.minimum, this.maximum) - this.minimum) / Math.abs(this.maximum - this.minimum));
      this.value.set(Math.min(Math.max(value, this.minimum), this.maximum));
      this.sliderWidget.method_25346();
   }

   @Override
   public Optional<Long> getDefaultValue() {
      return this.defaultValue == null ? Optional.empty() : Optional.ofNullable(this.defaultValue.get());
   }

   public List<? extends class_364> method_25396() {
      return this.widgets;
   }

   @Override
   public List<? extends class_6379> narratables() {
      return this.widgets;
   }

   @Override
   public boolean isEdited() {
      return super.isEdited() || this.getValue() != this.orginial;
   }

   public LongSliderEntry setMaximum(long maximum) {
      this.maximum = maximum;
      return this;
   }

   public LongSliderEntry setMinimum(long minimum) {
      this.minimum = minimum;
      return this;
   }

   @Override
   public void render(class_332 graphics, int index, int y, int x, int entryWidth, int entryHeight, int mouseX, int mouseY, boolean isHovered, float delta) {
      super.render(graphics, index, y, x, entryWidth, entryHeight, mouseX, mouseY, isHovered, delta);
      class_1041 window = class_310.method_1551().method_22683();
      this.resetButton.field_22763 = this.isEditable() && this.getDefaultValue().isPresent() && this.defaultValue.get() != this.value.get();
      this.resetButton.method_46419(y);
      this.sliderWidget.field_22763 = this.isEditable();
      this.sliderWidget.method_46419(y);
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
         this.sliderWidget.method_46421(x + this.resetButton.method_25368() + 1);
      } else {
         graphics.method_35720(class_310.method_1551().field_1772, displayedFieldName.method_30937(), x, y + 6, this.getPreferredTextColor());
         this.resetButton.method_46421(x + entryWidth - this.resetButton.method_25368());
         this.sliderWidget.method_46421(x + entryWidth - 150);
      }

      this.sliderWidget.method_25358(150 - this.resetButton.method_25368() - 2);
      this.resetButton.method_25394(graphics, mouseX, mouseY, delta);
      this.sliderWidget.method_25394(graphics, mouseX, mouseY, delta);
   }

   private class Slider extends class_357 {
      protected Slider(int int_1, int int_2, int int_3, int int_4, double double_1) {
         super(int_1, int_2, int_3, int_4, class_2561.method_43473(), double_1);
      }

      public void method_25346() {
         this.method_25355(LongSliderEntry.this.textGetter.apply(LongSliderEntry.this.value.get()));
      }

      protected void method_25344() {
         LongSliderEntry.this.value
            .set((long)(LongSliderEntry.this.minimum + Math.abs(LongSliderEntry.this.maximum - LongSliderEntry.this.minimum) * this.field_22753));
      }

      public boolean method_25404(int int_1, int int_2, int int_3) {
         return !LongSliderEntry.this.isEditable() ? false : super.method_25404(int_1, int_2, int_3);
      }

      public boolean method_25403(double double_1, double double_2, int int_1, double double_3, double double_4) {
         return !LongSliderEntry.this.isEditable() ? false : super.method_25403(double_1, double_2, int_1, double_3, double_4);
      }

      public double getValue() {
         return this.field_22753;
      }

      public void setValue(double integer) {
         this.field_22753 = integer;
      }
   }
}
