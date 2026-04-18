package me.shedaniel.clothconfig2.gui.entries;

import java.util.Optional;
import java.util.AbstractMap.SimpleEntry;
import java.util.Map.Entry;
import java.util.function.Consumer;
import java.util.function.Supplier;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.class_2561;
import org.jetbrains.annotations.ApiStatus.Internal;

@Environment(EnvType.CLIENT)
public class DoubleListEntry extends AbstractNumberListEntry<Double> {
   @Deprecated
   @Internal
   public DoubleListEntry(class_2561 fieldName, Double value, class_2561 resetButtonKey, Supplier<Double> defaultValue, Consumer<Double> saveConsumer) {
      super(fieldName, value, resetButtonKey, defaultValue);
      this.saveCallback = saveConsumer;
   }

   @Deprecated
   @Internal
   public DoubleListEntry(
      class_2561 fieldName,
      Double value,
      class_2561 resetButtonKey,
      Supplier<Double> defaultValue,
      Consumer<Double> saveConsumer,
      Supplier<Optional<class_2561[]>> tooltipSupplier
   ) {
      this(fieldName, value, resetButtonKey, defaultValue, saveConsumer, tooltipSupplier, false);
   }

   @Deprecated
   @Internal
   public DoubleListEntry(
      class_2561 fieldName,
      Double value,
      class_2561 resetButtonKey,
      Supplier<Double> defaultValue,
      Consumer<Double> saveConsumer,
      Supplier<Optional<class_2561[]>> tooltipSupplier,
      boolean requiresRestart
   ) {
      super(fieldName, value, resetButtonKey, defaultValue, tooltipSupplier, requiresRestart);
      this.saveCallback = saveConsumer;
   }

   @Override
   protected Entry<Double, Double> getDefaultRange() {
      return new SimpleEntry<>(-Double.MAX_VALUE, Double.MAX_VALUE);
   }

   public DoubleListEntry setMinimum(double minimum) {
      this.minimum = minimum;
      return this;
   }

   public DoubleListEntry setMaximum(double maximum) {
      this.maximum = maximum;
      return this;
   }

   public Double getValue() {
      try {
         return Double.valueOf(this.textFieldWidget.method_1882());
      } catch (Exception var2) {
         return 0.0;
      }
   }

   @Override
   public Optional<class_2561> getError() {
      try {
         double i = Double.parseDouble(this.textFieldWidget.method_1882());
         if (i > this.maximum) {
            return Optional.of(class_2561.method_43469("text.cloth-config.error.too_large", new Object[]{this.maximum}));
         }

         if (i < this.minimum) {
            return Optional.of(class_2561.method_43469("text.cloth-config.error.too_small", new Object[]{this.minimum}));
         }
      } catch (NumberFormatException var3) {
         return Optional.of(class_2561.method_43471("text.cloth-config.error.not_valid_number_double"));
      }

      return super.getError();
   }
}
