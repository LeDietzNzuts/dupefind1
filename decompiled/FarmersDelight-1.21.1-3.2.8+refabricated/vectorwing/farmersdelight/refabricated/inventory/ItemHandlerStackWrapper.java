package vectorwing.farmersdelight.refabricated.inventory;

import net.fabricmc.fabric.api.transfer.v1.item.ItemVariant;
import net.fabricmc.fabric.api.transfer.v1.item.base.SingleStackStorage;
import net.minecraft.class_1799;

public class ItemHandlerStackWrapper extends SingleStackStorage {
   private final ItemHandler handler;
   private final int slot;

   public ItemHandlerStackWrapper(ItemHandler handler, int slot) {
      this.handler = handler;
      this.slot = slot;
   }

   protected class_1799 getStack() {
      return this.handler.getStackInSlot(this.slot);
   }

   protected void setStack(class_1799 stack) {
      this.handler.setStackInSlot(this.slot, stack);
   }

   protected int getCapacity(ItemVariant itemVariant) {
      return itemVariant.isBlank() ? this.handler.getSlotLimit(this.slot) : Math.min(this.handler.getSlotLimit(this.slot), itemVariant.toStack().method_7914());
   }
}
