package vectorwing.farmersdelight.common.block.entity.inventory;

import net.minecraft.class_1799;
import vectorwing.farmersdelight.common.block.entity.Basket;
import vectorwing.farmersdelight.refabricated.inventory.InvWrapper;

public class BasketInvWrapper extends InvWrapper {
   protected final Basket basket;

   public BasketInvWrapper(Basket basket) {
      super(basket);
      this.basket = basket;
   }

   @Override
   public class_1799 insertItem(int slot, class_1799 stack, boolean simulate) {
      if (simulate) {
         return super.insertItem(slot, stack, true);
      } else {
         boolean wasEmpty = this.basket.method_5442();
         int originalCount = stack.method_7947();
         stack = super.insertItem(slot, stack, false);
         if (wasEmpty && originalCount > stack.method_7947() && !this.basket.isOnCustomCooldown()) {
            this.basket.setCooldown(8);
         }

         return stack;
      }
   }
}
