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
public class LongListEntry extends AbstractNumberListEntry<Long> {
   @Deprecated
   @Internal
   public LongListEntry(class_2561 fieldName, Long value, class_2561 resetButtonKey, Supplier<Long> defaultValue, Consumer<Long> saveConsumer) {
      super(fieldName, value, resetButtonKey, defaultValue);
      this.saveCallback = saveConsumer;
   }

   @Deprecated
   @Internal
   public LongListEntry(
      class_2561 fieldName,
      Long value,
      class_2561 resetButtonKey,
      Supplier<Long> defaultValue,
      Consumer<Long> saveConsumer,
      Supplier<Optional<class_2561[]>> tooltipSupplier
   ) {
      this(fieldName, value, resetButtonKey, defaultValue, saveConsumer, tooltipSupplier, false);
   }

   @Deprecated
   @Internal
   public LongListEntry(
      class_2561 fieldName,
      Long value,
      class_2561 resetButtonKey,
      Supplier<Long> defaultValue,
      Consumer<Long> saveConsumer,
      Supplier<Optional<class_2561[]>> tooltipSupplier,
      boolean requiresRestart
   ) {
      super(fieldName, value, resetButtonKey, defaultValue, tooltipSupplier, requiresRestart);
      this.saveCallback = saveConsumer;
   }

   @Override
   protected Entry<Long, Long> getDefaultRange() {
      return new SimpleEntry<>(-9223372036854775807L, Long.MAX_VALUE);
   }

   public LongListEntry setMinimum(long minimum) {
      this.minimum = minimum;
      return this;
   }

   public LongListEntry setMaximum(long maximum) {
      this.maximum = maximum;
      return this;
   }

   public Long getValue() {
      try {
         return Long.valueOf(this.textFieldWidget.method_1882());
      } catch (Exception var2) {
         return 0L;
      }
   }

   @Override
   public Optional<class_2561> getError() {
      try {
         long i = Long.parseLong(this.textFieldWidget.method_1882());
         if (i > this.maximum) {
            return Optional.of(class_2561.method_43469("text.cloth-config.error.too_large", new Object[]{this.maximum}));
         }

         if (i < this.minimum) {
            return Optional.of(class_2561.method_43469("text.cloth-config.error.too_small", new Object[]{this.minimum}));
         }
      } catch (NumberFormatException var3) {
         return Optional.of(class_2561.method_43471("text.cloth-config.error.not_valid_number_long"));
      }

      return super.getError();
   }
}
