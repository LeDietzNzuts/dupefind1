package vectorwing.farmersdelight.common.block.entity;

import java.util.Optional;
import net.minecraft.class_1799;
import net.minecraft.class_1863;
import net.minecraft.class_1937;
import net.minecraft.class_2248;
import net.minecraft.class_2338;
import net.minecraft.class_2350;
import net.minecraft.class_2398;
import net.minecraft.class_241;
import net.minecraft.class_247;
import net.minecraft.class_2487;
import net.minecraft.class_259;
import net.minecraft.class_265;
import net.minecraft.class_2680;
import net.minecraft.class_3532;
import net.minecraft.class_3920;
import net.minecraft.class_3956;
import net.minecraft.class_8786;
import net.minecraft.class_9696;
import net.minecraft.class_1863.class_7266;
import net.minecraft.class_7225.class_7874;
import vectorwing.farmersdelight.common.block.StoveBlock;
import vectorwing.farmersdelight.common.registry.ModBlockEntityTypes;
import vectorwing.farmersdelight.common.utility.ItemUtils;
import vectorwing.farmersdelight.refabricated.inventory.ItemStackHandler;

public class StoveBlockEntity extends SyncedBlockEntity {
   private static final class_265 GRILLING_AREA = class_2248.method_9541(3.0, 0.0, 3.0, 13.0, 1.0, 13.0);
   private static final int INVENTORY_SLOT_COUNT = 6;
   private final ItemStackHandler inventory = this.createHandler();
   private final int[] cookingTimes = new int[6];
   private final int[] cookingTimesTotal = new int[6];
   private final class_7266<class_9696, class_3920> quickCheck = class_1863.method_42302(class_3956.field_17549);

   public StoveBlockEntity(class_2338 pos, class_2680 state) {
      super(ModBlockEntityTypes.STOVE.get(), pos, state);
   }

   public void method_11014(class_2487 tag, class_7874 registries) {
      super.method_11014(tag, registries);
      if (tag.method_10545("Inventory")) {
         this.inventory.deserializeNBT(registries, tag.method_10562("Inventory"));
      } else {
         this.inventory.deserializeNBT(registries, tag);
      }

      if (tag.method_10573("CookingTimes", 11)) {
         int[] arrayCookingTimes = tag.method_10561("CookingTimes");
         System.arraycopy(arrayCookingTimes, 0, this.cookingTimes, 0, Math.min(this.cookingTimesTotal.length, arrayCookingTimes.length));
      }

      if (tag.method_10573("CookingTotalTimes", 11)) {
         int[] arrayCookingTimesTotal = tag.method_10561("CookingTotalTimes");
         System.arraycopy(arrayCookingTimesTotal, 0, this.cookingTimesTotal, 0, Math.min(this.cookingTimesTotal.length, arrayCookingTimesTotal.length));
      }
   }

   public void method_11007(class_2487 compound, class_7874 registries) {
      this.writeItems(compound, registries);
      compound.method_10539("CookingTimes", this.cookingTimes);
      compound.method_10539("CookingTotalTimes", this.cookingTimesTotal);
   }

   private class_2487 writeItems(class_2487 compound, class_7874 registries) {
      super.method_11007(compound, registries);
      compound.method_10566("Inventory", this.inventory.serializeNBT(registries));
      return compound;
   }

   public static void cookingTick(class_1937 level, class_2338 pos, class_2680 state, StoveBlockEntity stove) {
      boolean isStoveLit = (Boolean)state.method_11654(StoveBlock.LIT);
      if (stove.isStoveBlockedAbove()) {
         if (!ItemUtils.isInventoryEmpty(stove.inventory)) {
            ItemUtils.dropItems(level, pos, stove.inventory);
            stove.inventoryChanged();
         }
      } else if (isStoveLit) {
         stove.cookAndOutputItems();
      } else {
         for (int i = 0; i < stove.inventory.getSlotCount(); i++) {
            if (stove.cookingTimes[i] > 0) {
               stove.cookingTimes[i] = class_3532.method_15340(stove.cookingTimes[i] - 2, 0, stove.cookingTimesTotal[i]);
            }
         }
      }
   }

   public static void animationTick(class_1937 level, class_2338 pos, class_2680 state, StoveBlockEntity stove) {
      for (int i = 0; i < stove.inventory.getSlotCount(); i++) {
         if (!stove.inventory.getStackInSlot(i).method_7960() && level.field_9229.method_43057() < 0.2F) {
            class_241 stoveItemVector = stove.getStoveItemOffset(i);
            class_2350 direction = (class_2350)state.method_11654(StoveBlock.FACING);
            int directionIndex = direction.method_10161();
            class_241 offset = directionIndex % 2 == 0 ? stoveItemVector : new class_241(stoveItemVector.field_1342, stoveItemVector.field_1343);
            double x = pos.method_10263() + 0.5 - direction.method_10148() * offset.field_1343 + direction.method_10170().method_10148() * offset.field_1343;
            double y = pos.method_10264() + 1.0;
            double z = pos.method_10260() + 0.5 - direction.method_10165() * offset.field_1342 + direction.method_10170().method_10165() * offset.field_1342;

            for (int k = 0; k < 3; k++) {
               level.method_8406(class_2398.field_11251, x, y, z, 0.0, 5.0E-4, 0.0);
            }
         }
      }
   }

   private void cookAndOutputItems() {
      boolean didInventoryChange = false;

      for (int i = 0; i < this.inventory.getSlotCount(); i++) {
         class_1799 stoveStack = this.inventory.getStackInSlot(i);
         if (!stoveStack.method_7960()) {
            this.cookingTimes[i]++;
            if (this.cookingTimes[i] >= this.cookingTimesTotal[i]) {
               Optional<class_8786<class_3920>> recipe = this.getMatchingRecipe(stoveStack);
               if (recipe.isPresent()) {
                  class_1799 resultStack = ((class_3920)recipe.get().comp_1933()).method_8110(this.field_11863.method_30349());
                  if (!resultStack.method_7960()) {
                     ItemUtils.spawnItemEntity(
                        this.field_11863,
                        resultStack.method_7972(),
                        this.field_11867.method_10263() + 0.5,
                        this.field_11867.method_10264() + 1.0,
                        this.field_11867.method_10260() + 0.5,
                        this.field_11863.field_9229.method_43059() * 0.01F,
                        0.1F,
                        this.field_11863.field_9229.method_43059() * 0.01F
                     );
                  }
               }

               this.inventory.setStackInSlot(i, class_1799.field_8037);
               didInventoryChange = true;
            }
         }
      }

      if (didInventoryChange) {
         this.inventoryChanged();
      }
   }

   public int getNextEmptySlot() {
      for (int i = 0; i < this.inventory.getSlotCount(); i++) {
         class_1799 slotStack = this.inventory.getStackInSlot(i);
         if (slotStack.method_7960()) {
            return i;
         }
      }

      return -1;
   }

   public boolean addItem(class_1799 itemStackIn, class_8786<class_3920> recipe, int slot) {
      if (0 <= slot && slot < this.inventory.getSlotCount()) {
         class_1799 slotStack = this.inventory.getStackInSlot(slot);
         if (slotStack.method_7960()) {
            this.cookingTimesTotal[slot] = ((class_3920)recipe.comp_1933()).method_8167();
            this.cookingTimes[slot] = 0;
            this.inventory.setStackInSlot(slot, itemStackIn.method_7971(1));
            this.inventoryChanged();
            return true;
         }
      }

      return false;
   }

   public Optional<class_8786<class_3920>> getMatchingRecipe(class_1799 stack) {
      return this.quickCheck.method_42303(new class_9696(stack), this.field_11863);
   }

   public ItemStackHandler getInventory() {
      return this.inventory;
   }

   public boolean isStoveBlockedAbove() {
      if (this.field_11863 != null) {
         class_2680 above = this.field_11863.method_8320(this.field_11867.method_10084());
         return class_259.method_1074(GRILLING_AREA, above.method_26218(this.field_11863, this.field_11867.method_10084()), class_247.field_16896);
      } else {
         return false;
      }
   }

   public class_241 getStoveItemOffset(int index) {
      float X_OFFSET = 0.3F;
      float Y_OFFSET = 0.2F;
      class_241[] OFFSETS = new class_241[]{
         new class_241(0.3F, 0.2F),
         new class_241(0.0F, 0.2F),
         new class_241(-0.3F, 0.2F),
         new class_241(0.3F, -0.2F),
         new class_241(0.0F, -0.2F),
         new class_241(-0.3F, -0.2F)
      };
      return OFFSETS[index];
   }

   @Override
   public class_2487 method_16887(class_7874 registries) {
      return this.writeItems(new class_2487(), registries);
   }

   private ItemStackHandler createHandler() {
      return new ItemStackHandler(6) {
         @Override
         public int getSlotLimit(int slot) {
            return 1;
         }
      };
   }
}
