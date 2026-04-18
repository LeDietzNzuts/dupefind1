package net.p3pp3rf1y.sophisticatedcore.extensions.client.gui.screens.inventory;

import javax.annotation.Nullable;
import net.minecraft.class_1735;

public interface SophisticatedAbstractContainerScreen {
   int slotColor = -2130706433;

   default int sophisticatedCore_getSlotColor(int slotId) {
      return -2130706433;
   }

   default int sophisticatedCore_getXSize() {
      throw new RuntimeException("Should have been overriden by mixin.");
   }

   default int sophisticatedCore_getGuiLeft() {
      throw new RuntimeException("Should have been overriden by mixin.");
   }

   default int sophisticatedCore_getGuiTop() {
      throw new RuntimeException("Should have been overriden by mixin.");
   }

   @Nullable
   default class_1735 sophisticatedCore_getSlotUnderMouse() {
      throw new RuntimeException("Should have been overriden by mixin.");
   }
}
