package net.p3pp3rf1y.sophisticatedbackpacks.crafting;

import java.util.Optional;
import net.minecraft.class_1799;
import net.minecraft.class_1865;
import net.minecraft.class_1869;
import net.minecraft.class_9694;
import net.minecraft.class_7225.class_7874;
import net.p3pp3rf1y.sophisticatedbackpacks.backpack.BackpackItem;
import net.p3pp3rf1y.sophisticatedbackpacks.backpack.wrapper.BackpackWrapper;
import net.p3pp3rf1y.sophisticatedbackpacks.backpack.wrapper.IBackpackWrapper;
import net.p3pp3rf1y.sophisticatedbackpacks.init.ModItems;
import net.p3pp3rf1y.sophisticatedcore.crafting.IWrapperRecipe;
import net.p3pp3rf1y.sophisticatedcore.crafting.RecipeWrapperSerializer;

public class BackpackUpgradeRecipe extends class_1869 implements IWrapperRecipe<class_1869> {
   private final class_1869 compose;

   public BackpackUpgradeRecipe(class_1869 compose) {
      super(compose.method_8112(), compose.method_45441(), compose.field_47320, compose.field_9053);
      this.compose = compose;
   }

   public class_1869 getCompose() {
      return this.compose;
   }

   public boolean method_8118() {
      return true;
   }

   public class_1799 method_17727(class_9694 inv, class_7874 registries) {
      class_1799 upgradedBackpack = super.method_17727(inv, registries);
      this.getBackpack(inv).map(class_1799::method_57353).ifPresent(upgradedBackpack::method_57365);
      IBackpackWrapper wrapper = BackpackWrapper.fromStack(upgradedBackpack);
      BackpackItem backpackItem = (BackpackItem)upgradedBackpack.method_7909();
      wrapper.setSlotNumbers(backpackItem.getNumberOfSlots(), backpackItem.getNumberOfUpgradeSlots());
      return upgradedBackpack;
   }

   private Optional<class_1799> getBackpack(class_9694 inv) {
      for (int slot = 0; slot < inv.method_59983(); slot++) {
         class_1799 slotStack = inv.method_59984(slot);
         if (slotStack.method_7909() instanceof BackpackItem) {
            return Optional.of(slotStack);
         }
      }

      return Optional.empty();
   }

   public class_1865<?> method_8119() {
      return ModItems.BACKPACK_UPGRADE_RECIPE_SERIALIZER.get();
   }

   public static class Serializer extends RecipeWrapperSerializer<class_1869, BackpackUpgradeRecipe> {
      public Serializer() {
         super(BackpackUpgradeRecipe::new, class_1865.field_9035);
      }
   }
}
