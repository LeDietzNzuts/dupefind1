package vectorwing.farmersdelight.common.utility;

import net.minecraft.class_1264;
import net.minecraft.class_1542;
import net.minecraft.class_1799;
import net.minecraft.class_1937;
import net.minecraft.class_2338;
import vectorwing.farmersdelight.refabricated.inventory.ItemHandler;
import vectorwing.farmersdelight.refabricated.inventory.ItemStackHandler;

public class ItemUtils {
   public static void dropItems(class_1937 level, class_2338 pos, ItemStackHandler inventory) {
      for (int slot = 0; slot < inventory.getSlotCount(); slot++) {
         class_1264.method_5449(
            level, pos.method_10263(), pos.method_10264(), pos.method_10260(), inventory.extractItem(slot, inventory.getSlotLimit(slot), false)
         );
      }
   }

   public static boolean isInventoryEmpty(ItemHandler inventory) {
      for (int i = 0; i < inventory.getSlotCount(); i++) {
         if (!inventory.getStackInSlot(i).method_7960()) {
            return false;
         }
      }

      return true;
   }

   public static void spawnItemEntity(class_1937 level, class_1799 stack, double x, double y, double z, double xMotion, double yMotion, double zMotion) {
      class_1542 entity = new class_1542(level, x, y, z, stack);
      entity.method_18800(xMotion, yMotion, zMotion);
      level.method_8649(entity);
   }
}
