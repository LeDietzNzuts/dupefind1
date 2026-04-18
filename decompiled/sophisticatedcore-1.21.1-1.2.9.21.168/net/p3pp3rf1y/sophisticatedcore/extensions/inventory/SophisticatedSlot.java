package net.p3pp3rf1y.sophisticatedcore.extensions.inventory;

import net.minecraft.class_1735;
import net.minecraft.class_2960;

public interface SophisticatedSlot {
   default boolean sophisticatedCore_isSameInventory(class_1735 other) {
      return ((class_1735)this).field_7871 == other.field_7871;
   }

   default int sophisticatedCore_getSlotIndex() {
      return 0;
   }

   default class_1735 sophisticatedCore_setBackground(class_2960 atlas, class_2960 sprite) {
      throw new RuntimeException("Should have been overriden by mixin.");
   }
}
