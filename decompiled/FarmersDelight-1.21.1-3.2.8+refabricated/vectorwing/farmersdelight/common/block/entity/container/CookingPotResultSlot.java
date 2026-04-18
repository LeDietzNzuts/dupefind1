package vectorwing.farmersdelight.common.block.entity.container;

import net.minecraft.class_1657;
import net.minecraft.class_1799;
import org.jetbrains.annotations.NotNull;
import vectorwing.farmersdelight.common.block.entity.CookingPotBlockEntity;
import vectorwing.farmersdelight.refabricated.inventory.ItemHandlerSlot;
import vectorwing.farmersdelight.refabricated.inventory.ItemStackHandler;

public class CookingPotResultSlot extends ItemHandlerSlot {
   public final CookingPotBlockEntity tileEntity;
   private final class_1657 player;
   private int removeCount;

   public CookingPotResultSlot(class_1657 player, CookingPotBlockEntity tile, ItemStackHandler inventoryIn, int index, int xPosition, int yPosition) {
      super(inventoryIn, index, xPosition, yPosition);
      this.tileEntity = tile;
      this.player = player;
   }

   @Override
   public boolean method_7680(class_1799 stack) {
      return false;
   }

   @NotNull
   @Override
   public class_1799 method_7671(int amount) {
      if (this.method_7681()) {
         this.removeCount = this.removeCount + Math.min(amount, this.method_7677().method_7947());
      }

      return super.method_7671(amount);
   }

   public void method_7667(class_1657 thePlayer, class_1799 stack) {
      this.method_7669(stack);
      super.method_7667(thePlayer, stack);
   }

   protected void method_7678(class_1799 stack, int amount) {
      this.removeCount += amount;
      this.method_7669(stack);
   }

   protected void method_7669(class_1799 stack) {
      stack.method_7982(this.player.method_37908(), this.player, this.removeCount);
      if (!this.player.method_37908().field_9236) {
         this.tileEntity.method_7664(this.player, this.tileEntity.getDroppableInventory());
      }

      this.removeCount = 0;
   }
}
