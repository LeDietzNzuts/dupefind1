package vectorwing.farmersdelight.common.block.entity.container;

import net.minecraft.class_1657;
import net.minecraft.class_1799;
import vectorwing.farmersdelight.refabricated.inventory.ItemHandlerSlot;
import vectorwing.farmersdelight.refabricated.inventory.ItemStackHandler;

public class CookingPotMealSlot extends ItemHandlerSlot {
   public CookingPotMealSlot(ItemStackHandler inventoryIn, int index, int xPosition, int yPosition) {
      super(inventoryIn, index, xPosition, yPosition);
   }

   @Override
   public boolean method_7680(class_1799 stack) {
      return false;
   }

   public boolean method_7674(class_1657 playerIn) {
      return false;
   }
}
