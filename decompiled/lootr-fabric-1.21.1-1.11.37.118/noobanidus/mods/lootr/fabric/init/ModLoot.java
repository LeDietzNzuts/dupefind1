package noobanidus.mods.lootr.fabric.init;

import net.minecraft.class_2378;
import net.minecraft.class_5342;
import net.minecraft.class_7923;
import noobanidus.mods.lootr.common.api.LootrAPI;
import noobanidus.mods.lootr.common.loot.conditions.LootCount;

public class ModLoot {
   public static final class_5342 LOOT_COUNT = new class_5342(LootCount.CODEC);

   public static void registerLoot() {
      class_2378.method_10230(class_7923.field_41135, LootrAPI.rl("loot_count"), LOOT_COUNT);
   }
}
