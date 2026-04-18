package net.caffeinemc.mods.lithium.common.hopper;

import net.caffeinemc.mods.lithium.api.inventory.LithiumInventory;
import net.minecraft.class_1799;
import net.minecraft.class_2371;

public class InventoryHelper {
   public static LithiumStackList getLithiumStackList(LithiumInventory inventory) {
      return inventory.getInventoryLithium() instanceof LithiumStackList lithiumStackList ? lithiumStackList : upgradeToLithiumStackList(inventory);
   }

   public static LithiumStackList getLithiumStackListOrNull(LithiumInventory inventory) {
      return inventory.getInventoryLithium() instanceof LithiumStackList lithiumStackList ? lithiumStackList : null;
   }

   private static LithiumStackList upgradeToLithiumStackList(LithiumInventory inventory) {
      inventory.generateLootLithium();
      class_2371<class_1799> stackList = inventory.getInventoryLithium();
      LithiumStackList lithiumStackList = new LithiumStackList(stackList, inventory.method_5444());
      inventory.setInventoryLithium(lithiumStackList);
      return lithiumStackList;
   }
}
