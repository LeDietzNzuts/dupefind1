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
public class FloatListEntry extends AbstractNumberListEntry<Float> {
   @Deprecated
   @Internal
   public FloatListEntry(class_2561 fieldName, Float value, class_2561 resetButtonKey, Supplier<Float> defaultValue, Consumer<Float> saveConsumer) {
      super(fieldName, value, resetButtonKey, defaultValue);
      this.saveCallback = saveConsumer;
   }

   @Deprecated
   @Internal
   public FloatListEntry(
      class_2561 fieldName,
      Float value,
      class_2561 resetButtonKey,
      Supplier<Float> defaultValue,
      Consumer<Float> saveConsumer,
      Supplier<Optional<class_2561[]>> tooltipSupplier
   ) {
      this(fieldName, value, resetButtonKey, defaultValue, saveConsumer, tooltipSupplier, false);
   }

   @Deprecated
   @Internal
   public FloatListEntry(
      class_2561 fieldName,
      Float value,
      class_2561 resetButtonKey,
      Supplier<Float> defaultValue,
      Consumer<Float> saveConsumer,
      Supplier<Optional<class_2561[]>> tooltipSupplier,
      boolean requiresRestart
   ) {
      super(fieldName, value, resetButtonKey, defaultValue, tooltipSupplier, requiresRestart);
      this.saveCallback = saveConsumer;
   }

   @Override
   protected Entry<Float, Float> getDefaultRange() {
      return new SimpleEntry<>(-Float.MAX_VALUE, Float.MAX_VALUE);
   }

   public FloatListEntry setMinimum(float minimum) {
      this.minimum = minimum;
      return this;
   }

   public FloatListEntry setMaximum(float maximum) {
      this.maximum = maximum;
      return this;
   }

   public Float getValue() {
      try {
         return Float.valueOf(this.textFieldWidget.method_1882());
      } catch (Exception var2) {
         return 0.0F;
      }
   }

   @Override
   public Optional<class_2561> getError() {
      try {
         float i = Float.parseFloat(this.textFieldWidget.method_1882());
         if (i > this.maximum) {
            return Optional.of(class_2561.method_43469("text.cloth-config.error.too_large", new Object[]{this.maximum}));
         }

         if (i < this.minimum) {
            return Optional.of(class_2561.method_43469("text.cloth-config.error.too_small", new Object[]{this.minimum}));
         }
      } catch (NumberFormatException var2) {
         return Optional.of(class_2561.method_43471("text.cloth-config.error.not_valid_number_float"));
      }

      return super.getError();
   }
}
