package net.p3pp3rf1y.sophisticatedcore.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import net.minecraft.class_173;
import net.minecraft.class_1799;
import net.minecraft.class_181;
import net.minecraft.class_2338;
import net.minecraft.class_243;
import net.minecraft.class_2960;
import net.minecraft.class_3218;
import net.minecraft.class_52;
import net.minecraft.class_5321;
import net.minecraft.class_5819;
import net.minecraft.class_7924;
import net.minecraft.class_47.class_48;
import net.minecraft.class_8567.class_8568;
import net.minecraft.server.MinecraftServer;
import net.p3pp3rf1y.sophisticatedcore.SophisticatedCore;
import net.p3pp3rf1y.sophisticatedcore.inventory.IItemHandlerSimpleInserter;

public class LootHelper {
   private LootHelper() {
   }

   public static List<class_1799> getLoot(class_2960 lootTableName, MinecraftServer server, class_3218 level, class_2338 pos) {
      class_52 lootTable = server.method_58576().method_58295(class_5321.method_29179(class_7924.field_50079, lootTableName));
      class_48 lootBuilder = new class_48(
            new class_8568(level).method_51874(class_181.field_24424, class_243.method_24953(pos)).method_51875(class_173.field_1179)
         )
         .method_304(level.field_9229.method_43055());
      List<class_1799> lootStacks = new ArrayList<>();
      lootTable.method_320(lootBuilder.method_309(Optional.empty()), lootStacks::add);
      return lootStacks;
   }

   public static void fillWithLoot(class_5819 rand, List<class_1799> loot, IItemHandlerSimpleInserter inventory) {
      List<Integer> slots = InventoryHelper.getEmptySlotsRandomized(inventory);
      InventoryHelper.shuffleItems(loot, slots.size(), rand);

      for (class_1799 lootStack : loot) {
         if (slots.isEmpty()) {
            SophisticatedCore.LOGGER.warn("Too much loot to add to container. Overflow is voided.");
            return;
         }

         if (!lootStack.method_7960()) {
            inventory.setStackInSlot(slots.remove(slots.size() - 1), lootStack);
         }
      }
   }
}
