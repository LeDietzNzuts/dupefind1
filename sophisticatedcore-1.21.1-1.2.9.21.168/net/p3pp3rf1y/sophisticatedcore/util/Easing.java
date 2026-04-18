package net.p3pp3rf1y.sophisticatedcore.util;

import java.util.function.Function;

public class Easing {
   public static final Easing EASE_IN_OUT_CUBIC = new Easing(
      number -> (float)(number.floatValue() < 0.5 ? 4.0F * number * number * number : 1.0 - Math.pow(-2.0F * number + 2.0F, 3.0) / 2.0)
   );
   private final Function<Float, Float> easingFunction;

   private Easing(Function<Float, Float> easingFunction) {
      this.easingFunction = easingFunction;
   }

   public float ease(float number) {
      return this.easingFunction.apply(number);
   }
}
