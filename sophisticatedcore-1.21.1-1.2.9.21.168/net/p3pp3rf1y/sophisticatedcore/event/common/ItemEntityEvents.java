package net.p3pp3rf1y.sophisticatedcore.event.common;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.class_1269;
import net.minecraft.class_1542;
import net.minecraft.class_1657;
import net.minecraft.class_1799;

public interface ItemEntityEvents {
   Event<ItemEntityEvents.CanPickup> CAN_PICKUP = EventFactory.createArrayBacked(
      ItemEntityEvents.CanPickup.class, callbacks -> (player, itemEntity, stack) -> {
         for (ItemEntityEvents.CanPickup event : callbacks) {
            class_1269 result = event.canPickup(player, itemEntity, stack);
            if (result != class_1269.field_5811) {
               return result;
            }
         }

         return class_1269.field_5811;
      }
   );
   Event<ItemEntityEvents.PostPickup> POST_PICKUP = EventFactory.createArrayBacked(
      ItemEntityEvents.PostPickup.class, callbacks -> (player, itemEntity, stack) -> {
         for (ItemEntityEvents.PostPickup event : callbacks) {
            event.postPickup(player, itemEntity, stack);
         }
      }
   );

   @FunctionalInterface
   public interface CanPickup {
      class_1269 canPickup(class_1657 var1, class_1542 var2, class_1799 var3);
   }

   @FunctionalInterface
   public interface PostPickup {
      void postPickup(class_1657 var1, class_1542 var2, class_1799 var3);
   }
}
