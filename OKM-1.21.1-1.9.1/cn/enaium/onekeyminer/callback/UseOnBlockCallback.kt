package cn.enaium.onekeyminer.callback

import net.fabricmc.fabric.api.event.Event
import net.fabricmc.fabric.api.event.EventFactory
import net.minecraft.class_1268
import net.minecraft.class_1799
import net.minecraft.class_1937
import net.minecraft.class_3222
import net.minecraft.class_3965

public fun interface UseOnBlockCallback {
   public abstract fun interact(player: class_3222, world: class_1937, stack: class_1799, hand: class_1268, hitResult: class_3965) {
   }

   public companion object {
      public final val EVENT: Event<UseOnBlockCallback> =
         EventFactory.createArrayBacked(UseOnBlockCallback.class, UseOnBlockCallback.Companion::EVENT$lambda$1)

      @JvmStatic
      fun `EVENT$lambda$1$lambda$0`(
         `$listeners`: Array<UseOnBlockCallback>, player: class_3222, world: class_1937, stack: class_1799, hand: class_1268, hitResult: class_3965
      ) {
         for (UseOnBlockCallback listener : $listeners) {
            listener.interact(player, world, stack, hand, hitResult);
         }
      }

      @JvmStatic
      fun `EVENT$lambda$1`(listeners: Array<UseOnBlockCallback>): UseOnBlockCallback {
         return UseOnBlockCallback.Companion::EVENT$lambda$1$lambda$0;
      }
   }
}
