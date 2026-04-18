package vectorwing.farmersdelight.common.registry;

import net.minecraft.class_1291;
import net.minecraft.class_2378;
import net.minecraft.class_6880;
import net.minecraft.class_7923;
import vectorwing.farmersdelight.FarmersDelight;
import vectorwing.farmersdelight.common.effect.ComfortEffect;
import vectorwing.farmersdelight.common.effect.NourishmentEffect;

public class ModEffects {
   public static final class_6880<class_1291> NOURISHMENT = class_2378.method_47985(
      class_7923.field_41174, FarmersDelight.res("nourishment"), new NourishmentEffect()
   );
   public static final class_6880<class_1291> COMFORT = class_2378.method_47985(class_7923.field_41174, FarmersDelight.res("comfort"), new ComfortEffect());

   public static void register() {
   }

   public static void touch() {
   }
}
