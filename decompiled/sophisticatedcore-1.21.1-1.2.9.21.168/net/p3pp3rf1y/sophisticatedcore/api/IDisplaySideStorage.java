package net.p3pp3rf1y.sophisticatedcore.api;

import net.minecraft.class_2680;

public interface IDisplaySideStorage {
   default boolean canChangeDisplaySide(class_2680 state) {
      return false;
   }
}
