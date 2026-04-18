package net.p3pp3rf1y.sophisticatedcore.crafting;

import java.util.Optional;
import net.minecraft.class_1799;
import net.minecraft.class_1865;
import net.minecraft.class_1869;
import net.minecraft.class_9694;
import net.minecraft.class_7225.class_7874;
import net.p3pp3rf1y.sophisticatedcore.init.ModRecipes;
import net.p3pp3rf1y.sophisticatedcore.upgrades.IUpgradeItem;

public class UpgradeNextTierRecipe extends class_1869 implements IWrapperRecipe<class_1869> {
   private final class_1869 compose;

   public UpgradeNextTierRecipe(class_1869 compose) {
      super(compose.method_8112(), compose.method_45441(), compose.field_47320, compose.field_9053);
      this.compose = compose;
   }

   public class_1869 getCompose() {
      return this.compose;
   }

   public class_1799 method_17727(class_9694 inv, class_7874 registries) {
      class_1799 nextTier = super.method_17727(inv, registries);
      this.getUpgrade(inv).ifPresent(upgrade -> nextTier.field_49270.method_57933(upgrade.method_57353()));
      return nextTier;
   }

   private Optional<class_1799> getUpgrade(class_9694 inv) {
      for (int slot = 0; slot < inv.method_59983(); slot++) {
         class_1799 slotStack = inv.method_59984(slot);
         if (slotStack.method_7909() instanceof IUpgradeItem) {
            return Optional.of(slotStack);
         }
      }

      return Optional.empty();
   }

   public boolean method_8118() {
      return true;
   }

   public class_1865<?> method_8119() {
      return ModRecipes.UPGRADE_NEXT_TIER_SERIALIZER.get();
   }

   public static class Serializer extends RecipeWrapperSerializer<class_1869, UpgradeNextTierRecipe> {
      public Serializer() {
         super(UpgradeNextTierRecipe::new, class_1865.field_9035);
      }
   }
}
