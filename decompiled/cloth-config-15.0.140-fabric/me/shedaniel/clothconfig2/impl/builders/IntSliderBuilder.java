package me.shedaniel.clothconfig2.impl.builders;

import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import me.shedaniel.clothconfig2.gui.entries.IntegerSliderEntry;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.class_2561;
import org.jetbrains.annotations.NotNull;

@Environment(EnvType.CLIENT)
public class IntSliderBuilder extends AbstractSliderFieldBuilder<Integer, IntegerSliderEntry, IntSliderBuilder> {
   public IntSliderBuilder(class_2561 resetButtonKey, class_2561 fieldNameKey, int value, int min, int max) {
      super(resetButtonKey, fieldNameKey);
      this.value = value;
      this.max = max;
      this.min = min;
   }

   public IntSliderBuilder setErrorSupplier(Function<Integer, Optional<class_2561>> errorSupplier) {
      return (IntSliderBuilder)super.setErrorSupplier(errorSupplier);
   }

   public IntSliderBuilder requireRestart() {
      return (IntSliderBuilder)super.requireRestart();
   }

   public IntSliderBuilder setTextGetter(Function<Integer, class_2561> textGetter) {
      return (IntSliderBuilder)super.setTextGetter(textGetter);
   }

   public IntSliderBuilder setSaveConsumer(Consumer<Integer> saveConsumer) {
      return (IntSliderBuilder)super.setSaveConsumer(saveConsumer);
   }

   public IntSliderBuilder setDefaultValue(Supplier<Integer> defaultValue) {
      return (IntSliderBuilder)super.setDefaultValue(defaultValue);
   }

   public IntSliderBuilder setDefaultValue(int defaultValue) {
      this.defaultValue = () -> defaultValue;
      return this;
   }

   public IntSliderBuilder setTooltipSupplier(Function<Integer, Optional<class_2561[]>> tooltipSupplier) {
      return (IntSliderBuilder)super.setTooltipSupplier(tooltipSupplier);
   }

   public IntSliderBuilder setTooltipSupplier(Supplier<Optional<class_2561[]>> tooltipSupplier) {
      return (IntSliderBuilder)super.setTooltipSupplier(tooltipSupplier);
   }

   public IntSliderBuilder setTooltip(Optional<class_2561[]> tooltip) {
      return (IntSliderBuilder)super.setTooltip(tooltip);
   }

   public IntSliderBuilder setTooltip(class_2561... tooltip) {
      return (IntSliderBuilder)super.setTooltip(tooltip);
   }

   public IntSliderBuilder setMax(int max) {
      this.max = max;
      return this;
   }

   public IntSliderBuilder setMin(int min) {
      this.min = min;
      return this;
   }

   public IntSliderBuilder removeMin() {
      return this;
   }

   public IntSliderBuilder removeMax() {
      return this;
   }

   @NotNull
   public IntegerSliderEntry build() {
      IntegerSliderEntry entry = new IntegerSliderEntry(
         this.getFieldNameKey(),
         this.min,
         this.max,
         this.value,
         this.getResetButtonKey(),
         this.defaultValue,
         this.getSaveConsumer(),
         null,
         this.isRequireRestart()
      );
      if (this.textGetter != null) {
         entry.setTextGetter(this.textGetter);
      }

      entry.setTooltipSupplier(() -> this.getTooltipSupplier().apply(entry.getValue()));
      if (this.errorSupplier != null) {
         entry.setErrorSupplier(() -> this.errorSupplier.apply(entry.getValue()));
      }

      return this.finishBuilding(entry);
   }
}
