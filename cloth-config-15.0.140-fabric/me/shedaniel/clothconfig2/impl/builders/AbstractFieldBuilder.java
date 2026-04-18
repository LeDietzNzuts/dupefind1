package me.shedaniel.clothconfig2.impl.builders;

import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import me.shedaniel.clothconfig2.api.AbstractConfigListEntry;
import net.minecraft.class_2561;

public abstract class AbstractFieldBuilder<T, A extends AbstractConfigListEntry, SELF extends FieldBuilder<T, A, SELF>> extends FieldBuilder<T, A, SELF> {
   private Consumer<T> saveConsumer = null;
   private Function<T, Optional<class_2561[]>> tooltipSupplier = list -> Optional.empty();
   protected T value;

   protected AbstractFieldBuilder(class_2561 resetButtonKey, class_2561 fieldNameKey) {
      super(resetButtonKey, fieldNameKey);
   }

   public SELF requireRestart() {
      this.requireRestart(true);
      return (SELF)this;
   }

   public SELF setDefaultValue(Supplier<T> defaultValue) {
      this.defaultValue = defaultValue;
      return (SELF)this;
   }

   public SELF setDefaultValue(T defaultValue) {
      this.defaultValue = () -> defaultValue;
      return (SELF)this;
   }

   public SELF setErrorSupplier(Function<T, Optional<class_2561>> errorSupplier) {
      this.errorSupplier = errorSupplier;
      return (SELF)this;
   }

   public SELF setSaveConsumer(Consumer<T> saveConsumer) {
      this.saveConsumer = saveConsumer;
      return (SELF)this;
   }

   public SELF setTooltipSupplier(Function<T, Optional<class_2561[]>> tooltipSupplier) {
      this.tooltipSupplier = tooltipSupplier;
      return (SELF)this;
   }

   public SELF setTooltipSupplier(Supplier<Optional<class_2561[]>> tooltipSupplier) {
      this.tooltipSupplier = list -> tooltipSupplier.get();
      return (SELF)this;
   }

   public SELF setTooltip(Optional<class_2561[]> tooltip) {
      this.tooltipSupplier = list -> tooltip;
      return (SELF)this;
   }

   public SELF setTooltip(class_2561... tooltip) {
      this.tooltipSupplier = list -> Optional.ofNullable(tooltip);
      return (SELF)this;
   }

   public Consumer<T> getSaveConsumer() {
      return this.saveConsumer;
   }

   public Function<T, Optional<class_2561[]>> getTooltipSupplier() {
      return this.tooltipSupplier;
   }
}
