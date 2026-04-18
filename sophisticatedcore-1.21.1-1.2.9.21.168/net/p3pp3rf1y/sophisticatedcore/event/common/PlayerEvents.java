package net.p3pp3rf1y.sophisticatedcore.event.common;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.class_1263;
import net.minecraft.class_1657;
import net.minecraft.class_1799;

public interface PlayerEvents {
   Event<PlayerEvents.ItemCrafted> ITEM_CRAFTED = EventFactory.createArrayBacked(PlayerEvents.ItemCrafted.class, callbacks -> (player, stack, craftMatrix) -> {
      for (PlayerEvents.ItemCrafted callback : callbacks) {
         callback.onItemCrafted(player, stack, craftMatrix);
      }
   });

   @FunctionalInterface
   public interface ItemCrafted {
      void onItemCrafted(class_1657 var1, class_1799 var2, class_1263 var3);
   }
}
