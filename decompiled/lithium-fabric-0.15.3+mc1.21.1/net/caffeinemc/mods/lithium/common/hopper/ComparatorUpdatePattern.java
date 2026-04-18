package net.caffeinemc.mods.lithium.common.hopper;

import net.minecraft.class_2586;

public enum ComparatorUpdatePattern {
   NO_UPDATE {
      @Override
      public ComparatorUpdatePattern thenUpdate() {
         return UPDATE;
      }

      @Override
      public ComparatorUpdatePattern thenDecrementUpdateIncrementUpdate() {
         return DECREMENT_UPDATE_INCREMENT_UPDATE;
      }
   },
   UPDATE {
      @Override
      public void apply(class_2586 blockEntity, LithiumStackList stackList) {
         blockEntity.method_5431();
      }

      @Override
      public ComparatorUpdatePattern thenDecrementUpdateIncrementUpdate() {
         return UPDATE_DECREMENT_UPDATE_INCREMENT_UPDATE;
      }
   },
   DECREMENT_UPDATE_INCREMENT_UPDATE {
      @Override
      public void apply(class_2586 blockEntity, LithiumStackList stackList) {
         stackList.setReducedSignalStrengthOverride();
         blockEntity.method_5431();
         stackList.clearSignalStrengthOverride();
         blockEntity.method_5431();
      }

      @Override
      public boolean isChainable() {
         return false;
      }
   },
   UPDATE_DECREMENT_UPDATE_INCREMENT_UPDATE {
      @Override
      public void apply(class_2586 blockEntity, LithiumStackList stackList) {
         blockEntity.method_5431();
         stackList.setReducedSignalStrengthOverride();
         blockEntity.method_5431();
         stackList.clearSignalStrengthOverride();
         blockEntity.method_5431();
      }

      @Override
      public boolean isChainable() {
         return false;
      }
   };

   public void apply(class_2586 blockEntity, LithiumStackList stackList) {
   }

   public ComparatorUpdatePattern thenUpdate() {
      return this;
   }

   public ComparatorUpdatePattern thenDecrementUpdateIncrementUpdate() {
      return this;
   }

   public boolean isChainable() {
      return true;
   }
}
