package vectorwing.farmersdelight.common.registry;

import java.util.function.Supplier;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.class_1761;
import net.minecraft.class_1799;
import net.minecraft.class_1935;
import net.minecraft.class_2561;
import vectorwing.farmersdelight.refabricated.RegUtils;

public class ModCreativeTabs {
   public static final Supplier<class_1761> TAB_FARMERS_DELIGHT = RegUtils.regTab(
      "farmersdelight",
      () -> FabricItemGroup.builder()
         .method_47321(class_2561.method_43471("itemGroup.farmersdelight"))
         .method_47320(() -> new class_1799((class_1935)ModBlocks.STOVE.get()))
         .method_47317((parameters, output) -> ModItems.CREATIVE_TAB_ITEMS.forEach(item -> output.method_45421((class_1935)item.get())))
         .method_47324()
   );

   public static void touch() {
   }
}
