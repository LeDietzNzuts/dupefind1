package net.p3pp3rf1y.sophisticatedcore.crafting;

import net.minecraft.class_1799;
import net.minecraft.class_1852;
import net.minecraft.class_1865;
import net.minecraft.class_1937;
import net.minecraft.class_7710;
import net.minecraft.class_9694;
import net.minecraft.class_7225.class_7874;
import net.p3pp3rf1y.sophisticatedcore.init.ModRecipes;
import net.p3pp3rf1y.sophisticatedcore.upgrades.UpgradeItemBase;

public class UpgradeClearRecipe extends class_1852 {
   public UpgradeClearRecipe(class_7710 category) {
      super(category);
   }

   public boolean matches(class_9694 inventory, class_1937 level) {
      boolean upgradePresent = false;

      for (int i = 0; i < inventory.method_59983(); i++) {
         class_1799 stack = inventory.method_59984(i);
         if (!stack.method_7960()) {
            if (!(stack.method_7909() instanceof UpgradeItemBase) || stack.method_57353().method_57837() || upgradePresent) {
               return false;
            }

            upgradePresent = true;
         }
      }

      return upgradePresent;
   }

   public class_1799 assemble(class_9694 inventory, class_7874 registries) {
      class_1799 upgrade = class_1799.field_8037;

      for (int i = 0; i < inventory.method_59983(); i++) {
         class_1799 stack = inventory.method_59984(i);
         if (!stack.method_7960() && stack.method_7909() instanceof UpgradeItemBase) {
            upgrade = stack;
         }
      }

      return new class_1799(upgrade.method_7909(), 1);
   }

   public boolean method_8113(int width, int height) {
      return width >= 1 && height >= 1;
   }

   public class_1865<?> method_8119() {
      return (class_1865<?>)ModRecipes.UPGRADE_CLEAR_SERIALIZER.get();
   }
}
