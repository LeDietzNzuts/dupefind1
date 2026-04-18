package vectorwing.farmersdelight.refabricated.inventory;

import it.unimi.dsi.fastutil.ints.IntListIterator;
import net.minecraft.class_1662;
import net.minecraft.class_1799;
import net.minecraft.class_9695;

public class RecipeWrapper implements class_9695 {
   private final ItemHandler handler;
   private final class_1662 stackedContents;
   private final int ingredientAmount;

   public RecipeWrapper(ItemHandler handler) {
      this.handler = handler;
      this.stackedContents = new class_1662();
      int ingredientAmount = 0;
      IntListIterator var3 = handler.getInputSlotIndexes().iterator();

      while (var3.hasNext()) {
         int value = (Integer)var3.next();
         class_1799 itemstack = handler.getStackInSlot(value);
         if (!itemstack.method_7960()) {
            ingredientAmount++;
            this.stackedContents.method_20478(itemstack, 1);
         }
      }

      this.ingredientAmount = ingredientAmount;
   }

   public class_1662 stackedContents() {
      return this.stackedContents;
   }

   public int ingredientAmount() {
      return this.ingredientAmount;
   }

   public class_1799 method_59984(int slot) {
      return this.handler.getStackInSlot(slot);
   }

   public int method_59983() {
      return this.handler.getSlotCount();
   }
}
