package net.caffeinemc.mods.lithium.common.block.entity;

import net.caffeinemc.mods.lithium.mixin.world.block_entity_ticking.sleeping.WrappedBlockEntityTickInvokerAccessor;
import net.minecraft.class_1937;
import net.minecraft.class_2338;
import net.minecraft.class_2586;
import net.minecraft.class_5562;

public interface SleepingBlockEntity {
   class_5562 SLEEPING_BLOCK_ENTITY_TICKER = new class_5562() {
      public void method_31703() {
      }

      public boolean method_31704() {
         return false;
      }

      public class_2338 method_31705() {
         return null;
      }

      public String method_31706() {
         return "<lithium_sleeping>";
      }
   };

   WrappedBlockEntityTickInvokerAccessor lithium$getTickWrapper();

   void lithium$setTickWrapper(WrappedBlockEntityTickInvokerAccessor var1);

   class_5562 lithium$getSleepingTicker();

   void lithium$setSleepingTicker(class_5562 var1);

   default boolean lithium$startSleeping() {
      if (this.isSleeping()) {
         return false;
      } else {
         WrappedBlockEntityTickInvokerAccessor tickWrapper = this.lithium$getTickWrapper();
         if (tickWrapper == null) {
            return false;
         } else {
            this.lithium$setSleepingTicker(tickWrapper.getWrapped());
            tickWrapper.callSetWrapped(SLEEPING_BLOCK_ENTITY_TICKER);
            return true;
         }
      }
   }

   default void sleepOnlyCurrentTick() {
      class_5562 sleepingTicker = this.lithium$getSleepingTicker();
      WrappedBlockEntityTickInvokerAccessor tickWrapper = this.lithium$getTickWrapper();
      if (sleepingTicker == null) {
         sleepingTicker = tickWrapper.getWrapped();
      }

      class_1937 world = ((class_2586)this).method_10997();
      tickWrapper.callSetWrapped(new SleepUntilTimeBlockEntityTickInvoker((class_2586)this, world.method_8510() + 1L, sleepingTicker));
      this.lithium$setSleepingTicker(null);
   }

   default void wakeUpNow() {
      class_5562 sleepingTicker = this.lithium$getSleepingTicker();
      if (sleepingTicker != null) {
         this.setTicker(sleepingTicker);
         this.lithium$setSleepingTicker(null);
      }
   }

   default void setTicker(class_5562 delegate) {
      WrappedBlockEntityTickInvokerAccessor tickWrapper = this.lithium$getTickWrapper();
      if (tickWrapper != null) {
         tickWrapper.callSetWrapped(delegate);
      }
   }

   default boolean isSleeping() {
      return this.lithium$getSleepingTicker() != null;
   }
}
