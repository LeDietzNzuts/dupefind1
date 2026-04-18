package net.p3pp3rf1y.sophisticatedcore.extensions.item.component;

import net.minecraft.class_1799;

public interface SophisticatedItemContainerContents {
   default int sophisticatedCore_getSlots() {
      throw new RuntimeException("Should have been overriden by mixin.");
   }

   default class_1799 sophisticatedCore_getStackInSlot(int slot) {
      throw new RuntimeException("Should have been overriden by mixin.");
   }
}
