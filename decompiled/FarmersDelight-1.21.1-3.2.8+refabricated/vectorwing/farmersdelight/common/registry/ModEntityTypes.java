package vectorwing.farmersdelight.common.registry;

import java.util.function.Supplier;
import net.minecraft.class_1299;
import net.minecraft.class_1311;
import net.minecraft.class_1299.class_1300;
import vectorwing.farmersdelight.common.entity.RottenTomatoEntity;
import vectorwing.farmersdelight.refabricated.RegUtils;

public class ModEntityTypes {
   public static final Supplier<class_1299<RottenTomatoEntity>> ROTTEN_TOMATO = RegUtils.regEntity(
      "rotten_tomato",
      () -> class_1300.method_5903(RottenTomatoEntity::new, class_1311.field_17715).method_17687(0.25F, 0.25F).method_27299(4).method_27300(10).build()
   );

   public static void touch() {
   }
}
