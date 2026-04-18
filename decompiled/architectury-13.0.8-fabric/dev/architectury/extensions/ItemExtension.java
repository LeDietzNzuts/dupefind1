package dev.architectury.extensions;

import net.minecraft.class_1304;
import net.minecraft.class_1657;
import net.minecraft.class_1799;
import org.jetbrains.annotations.Nullable;

public interface ItemExtension {
   default void tickArmor(class_1799 stack, class_1657 player) {
   }

   @Nullable
   default class_1304 getCustomEquipmentSlot(class_1799 stack) {
      return null;
   }
}
