package me.shedaniel.clothconfig2.impl.builders;

import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import me.shedaniel.clothconfig2.gui.entries.LongSliderEntry;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.class_2561;
import org.jetbrains.annotations.NotNull;

@Environment(EnvType.CLIENT)
public class LongSliderBuilder extends AbstractSliderFieldBuilder<Long, LongSliderEntry, LongSliderBuilder> {
   public LongSliderBuilder(class_2561 resetButtonKey, class_2561 fieldNameKey, long value, long min, long max) {
      super(resetButtonKey, fieldNameKey);
      this.value = value;
      this.max = max;
      this.min = min;
   }

   public LongSliderBuilder setErrorSupplier(Function<Long, Optional<class_2561>> errorSupplier) {
      return (LongSliderBuilder)super.setErrorSupplier(errorSupplier);
   }

   public LongSliderBuilder requireRestart() {
      return (LongSliderBuilder)super.requireRestart();
   }

   public LongSliderBuilder setTextGetter(Function<Long, class_2561> textGetter) {
      return (LongSliderBuilder)super.setTextGetter(textGetter);
   }

   public LongSliderBuilder setSaveConsumer(Consumer<Long> saveConsumer) {
      return (LongSliderBuilder)super.setSaveConsumer(saveConsumer);
   }

   public LongSliderBuilder setDefaultValue(Supplier<Long> defaultValue) {
      return (LongSliderBuilder)super.setDefaultValue(defaultValue);
   }

   public LongSliderBuilder setDefaultValue(long defaultValue) {
      this.defaultValue = () -> defaultValue;
      return this;
   }

   public LongSliderBuilder setTooltipSupplier(Function<Long, Optional<class_2561[]>> tooltipSupplier) {
      return (LongSliderBuilder)super.setTooltipSupplier(tooltipSupplier);
   }

   public LongSliderBuilder setTooltipSupplier(Supplier<Optional<class_2561[]>> tooltipSupplier) {
      return (LongSliderBuilder)super.setTooltipSupplier(tooltipSupplier);
   }

   public LongSliderBuilder setTooltip(Optional<class_2561[]> tooltip) {
      return (LongSliderBuilder)super.setTooltip(tooltip);
   }

   public LongSliderBuilder setTooltip(class_2561... tooltip) {
      return (LongSliderBuilder)super.setTooltip(tooltip);
   }

   @NotNull
   public LongSliderEntry build() {
      LongSliderEntry entry = new LongSliderEntry(
         this.getFieldNameKey(),
         this.min,
         this.max,
         this.value,
         this.getSaveConsumer(),
         this.getResetButtonKey(),
         this.defaultValue,
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
