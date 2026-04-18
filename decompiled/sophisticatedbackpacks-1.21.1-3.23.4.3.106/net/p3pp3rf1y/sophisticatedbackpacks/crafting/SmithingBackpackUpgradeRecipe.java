package net.p3pp3rf1y.sophisticatedbackpacks.crafting;

import java.util.Optional;
import net.minecraft.class_1799;
import net.minecraft.class_1865;
import net.minecraft.class_8060;
import net.minecraft.class_9697;
import net.minecraft.class_7225.class_7874;
import net.p3pp3rf1y.sophisticatedbackpacks.backpack.BackpackItem;
import net.p3pp3rf1y.sophisticatedbackpacks.backpack.wrapper.BackpackWrapper;
import net.p3pp3rf1y.sophisticatedbackpacks.backpack.wrapper.IBackpackWrapper;
import net.p3pp3rf1y.sophisticatedbackpacks.init.ModItems;
import net.p3pp3rf1y.sophisticatedcore.SophisticatedCore;
import net.p3pp3rf1y.sophisticatedcore.crafting.IWrapperRecipe;
import net.p3pp3rf1y.sophisticatedcore.crafting.RecipeWrapperSerializer;

public class SmithingBackpackUpgradeRecipe extends class_8060 implements IWrapperRecipe<class_8060> {
   private final class_8060 compose;

   public SmithingBackpackUpgradeRecipe(class_8060 compose) {
      super(compose.field_42030, compose.field_42031, compose.field_42032, compose.field_42033);
      this.compose = compose;
   }

   public boolean method_8118() {
      return true;
   }

   public class_1799 method_60000(class_9697 inv, class_7874 registryAccess) {
      class_1799 upgradedBackpack = this.field_42033.method_7972();
      if (SophisticatedCore.isLogicalServerThread()) {
         this.getBackpack(inv).map(class_1799::method_57353).ifPresent(upgradedBackpack::method_57365);
         IBackpackWrapper wrapper = BackpackWrapper.fromStack(upgradedBackpack);
         BackpackItem backpackItem = (BackpackItem)upgradedBackpack.method_7909();
         wrapper.setSlotNumbers(backpackItem.getNumberOfSlots(), backpackItem.getNumberOfUpgradeSlots());
      }

      return upgradedBackpack;
   }

   private Optional<class_1799> getBackpack(class_9697 inv) {
      class_1799 slotStack = inv.method_59984(1);
      return slotStack.method_7909() instanceof BackpackItem ? Optional.of(slotStack) : Optional.empty();
   }

   public class_1865<?> method_8119() {
      return ModItems.SMITHING_BACKPACK_UPGRADE_RECIPE_SERIALIZER.get();
   }

   public class_8060 getCompose() {
      return this.compose;
   }

   public static class Serializer extends RecipeWrapperSerializer<class_8060, SmithingBackpackUpgradeRecipe> {
      public Serializer() {
         super(SmithingBackpackUpgradeRecipe::new, class_1865.field_42027);
      }
   }
}
