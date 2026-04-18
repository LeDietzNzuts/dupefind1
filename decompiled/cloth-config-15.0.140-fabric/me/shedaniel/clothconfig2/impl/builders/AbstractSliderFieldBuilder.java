package me.shedaniel.clothconfig2.impl.builders;

import java.util.Objects;
import java.util.function.Function;
import me.shedaniel.clothconfig2.api.AbstractConfigListEntry;
import net.minecraft.class_2561;

public abstract class AbstractSliderFieldBuilder<T, A extends AbstractConfigListEntry, SELF extends FieldBuilder<T, A, SELF>>
   extends AbstractRangeFieldBuilder<T, A, SELF> {
   protected Function<T, class_2561> textGetter = null;

   protected AbstractSliderFieldBuilder(class_2561 resetButtonKey, class_2561 fieldNameKey) {
      super(resetButtonKey, fieldNameKey);
   }

   public SELF setTextGetter(Function<T, class_2561> textGetter) {
      this.textGetter = textGetter;
      return (SELF)this;
   }

   @Override
   public SELF setMin(T min) {
      Objects.requireNonNull(min, "min cannot be null");
      return super.setMin(min);
   }

   @Override
   public SELF setMax(T max) {
      Objects.requireNonNull(max, "max cannot be null");
      return super.setMax(max);
   }
}
