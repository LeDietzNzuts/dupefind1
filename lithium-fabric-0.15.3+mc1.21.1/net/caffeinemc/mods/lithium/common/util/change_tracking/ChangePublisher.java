package net.caffeinemc.mods.lithium.common.util.change_tracking;

import net.minecraft.class_1799;

public interface ChangePublisher<T> {
   void lithium$subscribe(ChangeSubscriber<T> var1, int var2);

   int lithium$unsubscribe(ChangeSubscriber<T> var1);

   default void lithium$unsubscribeWithData(ChangeSubscriber<T> subscriber, int index) {
      throw new UnsupportedOperationException("Only implemented for ItemStacks");
   }

   default boolean lithium$isSubscribedWithData(ChangeSubscriber<class_1799> subscriber, int subscriberData) {
      throw new UnsupportedOperationException("Only implemented for ItemStacks");
   }
}
