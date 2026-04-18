package vectorwing.farmersdelight.refabricated.inventory;

import net.minecraft.class_1263;
import net.minecraft.class_1277;
import net.minecraft.class_1735;
import net.minecraft.class_1799;

public class ItemHandlerSlot extends class_1735 {
   private static final class_1263 EMPTY_INVENTORY = new class_1277(0);
   private final ItemHandler itemHandler;

   public ItemHandlerSlot(ItemHandler inventoryIn, int index, int xPosition, int yPosition) {
      super(EMPTY_INVENTORY, index, xPosition, yPosition);
      this.itemHandler = inventoryIn;
   }

   public boolean method_7680(class_1799 stack) {
      return !stack.method_7960() && this.itemHandler.isItemValid(this.method_34266(), stack);
   }

   public class_1799 method_7677() {
      return this.itemHandler.getStackInSlot(this.method_34266());
   }

   public void method_7673(class_1799 stack) {
      this.itemHandler.setStackInSlot(this.method_34266(), stack);
      this.method_7668();
   }

   public class_1799 method_7671(int amount) {
      class_1799 stack = this.itemHandler.extractItem(this.method_34266(), amount, false);
      this.method_7668();
      return stack;
   }

   public int method_7675() {
      return this.itemHandler.getSlotLimit(this.method_34266());
   }

   public ItemHandler getItemHandler() {
      return this.itemHandler;
   }
}
