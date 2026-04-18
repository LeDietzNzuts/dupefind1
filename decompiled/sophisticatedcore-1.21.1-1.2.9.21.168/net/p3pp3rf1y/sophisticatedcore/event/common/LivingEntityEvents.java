package net.p3pp3rf1y.sophisticatedcore.event.common;

import java.util.Collection;
import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.class_1282;
import net.minecraft.class_1309;
import net.minecraft.class_1542;

public interface LivingEntityEvents {
   Event<LivingEntityEvents.Drops> DROPS = EventFactory.createArrayBacked(
      LivingEntityEvents.Drops.class, callbacks -> (target, source, drops, recentlyHit) -> {
         for (LivingEntityEvents.Drops callback : callbacks) {
            if (callback.onLivingEntityDrops(target, source, drops, recentlyHit)) {
               return true;
            }
         }

         return false;
      }
   );
   Event<LivingEntityEvents.Tick> TICK = EventFactory.createArrayBacked(LivingEntityEvents.Tick.class, callbacks -> entity -> {
      for (LivingEntityEvents.Tick callback : callbacks) {
         callback.onLivingEntityTick(entity);
      }
   });

   @FunctionalInterface
   public interface Drops {
      boolean onLivingEntityDrops(class_1309 var1, class_1282 var2, Collection<class_1542> var3, boolean var4);
   }

   @FunctionalInterface
   public interface Tick {
      void onLivingEntityTick(class_1309 var1);
   }
}
