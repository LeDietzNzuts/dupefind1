package net.caffeinemc.mods.lithium.mixin.util.item_component_and_count_tracking;

import net.caffeinemc.mods.lithium.common.util.change_tracking.ChangePublisher;
import net.caffeinemc.mods.lithium.common.util.change_tracking.ChangeSubscriber;
import net.minecraft.class_9335;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(class_9335.class)
public class PatchedDataComponentMapMixin implements ChangePublisher<class_9335> {
   @Unique
   private ChangeSubscriber<class_9335> subscriber;

   @Override
   public void lithium$subscribe(ChangeSubscriber<class_9335> subscriber, int subscriberData) {
      if (subscriberData != 0) {
         throw new UnsupportedOperationException("ComponentMapImpl does not support subscriber data");
      } else {
         this.subscriber = ChangeSubscriber.combine(this.subscriber, 0, subscriber, 0);
      }
   }

   @Override
   public int lithium$unsubscribe(ChangeSubscriber<class_9335> subscriber) {
      this.subscriber = ChangeSubscriber.without(this.subscriber, subscriber);
      return 0;
   }

   @Inject(method = "method_57942()V", at = @At("HEAD"))
   private void trackBeforeChange(CallbackInfo ci) {
      if (this.subscriber != null) {
         this.subscriber.lithium$notify((class_9335)this, 0);
      }
   }
}
