package net.caffeinemc.mods.lithium.common.block.entity.inventory_change_tracking;

import net.minecraft.class_1263;

public interface InventoryChangeListener {
   default void handleStackListReplaced(class_1263 inventory) {
      this.lithium$handleInventoryRemoved(inventory);
   }

   void lithium$handleInventoryContentModified(class_1263 var1);

   void lithium$handleInventoryRemoved(class_1263 var1);

   boolean lithium$handleComparatorAdded(class_1263 var1);
}
