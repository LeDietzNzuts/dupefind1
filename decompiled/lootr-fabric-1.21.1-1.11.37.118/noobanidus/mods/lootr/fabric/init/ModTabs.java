package noobanidus.mods.lootr.fabric.init;

import net.minecraft.class_1761;
import net.minecraft.class_1799;
import net.minecraft.class_2378;
import net.minecraft.class_2561;
import net.minecraft.class_7923;
import net.minecraft.class_1761.class_7915;
import noobanidus.mods.lootr.common.api.LootrAPI;

public class ModTabs {
   public static final class_1761 LOOTR_TAB = class_1761.method_47307(class_7915.field_41049, 0)
      .method_47321(class_2561.method_43471("itemGroup.lootr"))
      .method_47320(() -> new class_1799(ModBlocks.TROPHY))
      .method_47317((p, output) -> output.method_45421(ModBlocks.TROPHY))
      .method_47324();

   public static void registerTabs() {
      class_2378.method_10230(class_7923.field_44687, LootrAPI.rl("lootr"), LOOTR_TAB);
   }
}
