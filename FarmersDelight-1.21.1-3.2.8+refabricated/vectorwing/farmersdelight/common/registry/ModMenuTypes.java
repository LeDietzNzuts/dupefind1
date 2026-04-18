package vectorwing.farmersdelight.common.registry;

import java.util.function.Supplier;
import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerType;
import net.minecraft.class_2338;
import net.minecraft.class_3917;
import vectorwing.farmersdelight.common.block.entity.container.CookingPotMenu;
import vectorwing.farmersdelight.refabricated.RegUtils;

public class ModMenuTypes {
   public static final Supplier<class_3917<CookingPotMenu>> COOKING_POT = RegUtils.regMenu(
      "cooking_pot", () -> new ExtendedScreenHandlerType(CookingPotMenu::new, class_2338.field_48404)
   );

   public static void touch() {
   }
}
