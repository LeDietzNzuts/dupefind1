package com.blamejared.clumps.api.events;

import net.minecraft.class_1657;

public class ValueEvent implements IValueEvent {
   private final class_1657 player;
   private int value;

   public ValueEvent(class_1657 player, int value) {
      this.player = player;
      this.value = value;
   }

   @Override
   public void setValue(int value) {
      this.value = value;
   }

   @Override
   public int getValue() {
      return this.value;
   }

   @Override
   public class_1657 getPlayer() {
      return this.player;
   }
}
