package vectorwing.farmersdelight.common.registry;

import java.util.function.Supplier;
import net.minecraft.class_5339;
import vectorwing.farmersdelight.common.loot.function.CopySkilletFunction;
import vectorwing.farmersdelight.common.loot.function.SmokerCookFunction;
import vectorwing.farmersdelight.refabricated.RegUtils;

public class ModLootFunctions {
   public static final Supplier<class_5339<CopySkilletFunction>> COPY_SKILLET = RegUtils.regLootFunc(
      "copy_skillet", () -> new class_5339(CopySkilletFunction.CODEC)
   );
   public static final Supplier<class_5339<SmokerCookFunction>> SMOKER_COOK = RegUtils.regLootFunc(
      "smoker_cook", () -> new class_5339(SmokerCookFunction.CODEC)
   );

   public static void touch() {
   }
}
