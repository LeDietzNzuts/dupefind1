package cn.enaium.onekeyminer.callback

import kotlin.jvm.functions.Function1
import net.fabricmc.fabric.api.event.Event
import net.fabricmc.fabric.api.event.EventFactory
import net.minecraft.class_1269
import net.minecraft.class_2338
import net.minecraft.class_3218
import net.minecraft.class_3222

public fun interface FinishMiningCallback {
   public abstract fun interact(world: class_3218, player: class_3222, pos: class_2338, tryBreak: (class_2338) -> Unit): class_1269 {
   }

   public companion object {
      public final val EVENT: Event<FinishMiningCallback> =
         EventFactory.createArrayBacked(FinishMiningCallback.class, FinishMiningCallback.Companion::EVENT$lambda$1)

      @JvmStatic
      fun `EVENT$lambda$1$lambda$0`(`$listeners`: Array<FinishMiningCallback>, world: class_3218, player: class_3222, pos: class_2338, tryBreak: Function1): class_1269 {
         for (FinishMiningCallback listener : $listeners) {
            val result: class_1269 = listener.interact(world, player, pos, tryBreak);
            if (result != class_1269.field_5811) {
               return result;
            }
         }

         return class_1269.field_5811;
      }

      @JvmStatic
      fun `EVENT$lambda$1`(listeners: Array<FinishMiningCallback>): FinishMiningCallback {
         return FinishMiningCallback.Companion::EVENT$lambda$1$lambda$0;
      }
   }
}
