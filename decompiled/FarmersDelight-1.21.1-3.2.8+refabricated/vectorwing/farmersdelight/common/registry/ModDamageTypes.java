package vectorwing.farmersdelight.common.registry;

import net.minecraft.class_1282;
import net.minecraft.class_1937;
import net.minecraft.class_2960;
import net.minecraft.class_5321;
import net.minecraft.class_7924;
import net.minecraft.class_8110;

public class ModDamageTypes {
   public static final class_5321<class_8110> STOVE_BURN = class_5321.method_29179(
      class_7924.field_42534, class_2960.method_60655("farmersdelight", "stove_burn")
   );

   public static class_1282 getSimpleDamageSource(class_1937 level, class_5321<class_8110> type) {
      return new class_1282(level.method_30349().method_30530(class_7924.field_42534).method_40290(type));
   }
}
