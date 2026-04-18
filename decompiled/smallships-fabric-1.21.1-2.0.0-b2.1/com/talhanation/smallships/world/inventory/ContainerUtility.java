package com.talhanation.smallships.world.inventory;

import com.talhanation.smallships.world.entity.ship.ContainerShip;
import com.talhanation.smallships.world.inventory.fabric.ContainerUtilityImpl;
import dev.architectury.injectables.annotations.ExpectPlatform;
import dev.architectury.injectables.annotations.ExpectPlatform.Transformed;
import net.minecraft.class_1657;
import net.minecraft.class_1799;
import net.minecraft.class_2371;
import net.minecraft.class_2487;
import net.minecraft.class_2499;
import net.minecraft.class_7225.class_7874;

public class ContainerUtility {
   @ExpectPlatform
   @Transformed
   public static void openShipMenu(class_1657 player, ContainerShip containerShip) {
      ContainerUtilityImpl.openShipMenu(player, containerShip);
   }

   public static void loadAllItems(class_2487 tag, class_2371<class_1799> itemStacks, class_7874 levelRegistry) {
      class_2499 listTag = tag.method_10554("Items", 10);

      for (int i = 0; i < listTag.size(); i++) {
         class_2487 compoundTag = listTag.method_10602(i);
         short slot = compoundTag.method_10568("Slot");
         if (slot < itemStacks.size()) {
            itemStacks.set(slot, class_1799.method_57360(levelRegistry, compoundTag).orElse(class_1799.field_8037));
         }
      }
   }

   public static void saveAllItems(class_2487 tag, class_2371<class_1799> itemStacks, class_7874 levelRegistry) {
      class_2499 listTag = new class_2499();

      for (int i = 0; i < itemStacks.size(); i++) {
         class_1799 itemStack = (class_1799)itemStacks.get(i);
         if (!itemStack.method_7960()) {
            class_2487 compoundTag = new class_2487();
            compoundTag.method_10575("Slot", (short)i);
            listTag.add(itemStack.method_57376(levelRegistry, compoundTag));
         }
      }

      tag.method_10566("Items", listTag);
   }
}
