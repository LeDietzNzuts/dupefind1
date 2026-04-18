package net.caffeinemc.mods.lithium.mixin.util.item_component_and_count_tracking;

import net.caffeinemc.mods.lithium.common.util.change_tracking.ChangePublisher;
import net.caffeinemc.mods.lithium.common.util.change_tracking.ChangeSubscriber;
import net.minecraft.class_1542;
import net.minecraft.class_1799;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(class_1542.class)
public abstract class ItemEntityMixin implements ChangePublisher<class_1542>, ChangeSubscriber.CountChangeSubscriber<class_1799> {
   @Unique
   private ChangeSubscriber<class_1542> subscriber;
   @Unique
   private int subscriberData;

   @Shadow
   public abstract class_1799 method_6983();

   @Unique
   private void startTrackingChanges() {
      class_1799 stack = this.method_6983();
      if (!stack.method_7960()) {
         ((ChangePublisher)stack).lithium$subscribe(this, 0);
      }
   }

   @Override
   public void lithium$subscribe(ChangeSubscriber<class_1542> subscriber, int subscriberData) {
      if (this.subscriber == null) {
         this.startTrackingChanges();
      }

      this.subscriber = ChangeSubscriber.combine(this.subscriber, this.subscriberData, subscriber, subscriberData);
      if (this.subscriber instanceof ChangeSubscriber.Multi) {
         this.subscriberData = 0;
      } else {
         this.subscriberData = subscriberData;
      }
   }

   @Override
   public int lithium$unsubscribe(ChangeSubscriber<class_1542> subscriber) {
      int retval = ChangeSubscriber.dataOf(this.subscriber, subscriber, this.subscriberData);
      this.subscriberData = ChangeSubscriber.dataWithout(this.subscriber, subscriber, this.subscriberData);
      this.subscriber = ChangeSubscriber.without(this.subscriber, subscriber);
      if (this.subscriber == null) {
         class_1799 stack = this.method_6983();
         if (!stack.method_7960()) {
            ((ChangePublisher)stack).lithium$unsubscribe(this);
         }
      }

      return retval;
   }

   public void lithium$notify(class_1799 publisher, int subscriberData) {
      if (publisher != this.method_6983()) {
         throw new IllegalStateException("Received notification from an unexpected publisher");
      } else {
         if (this.subscriber != null) {
            this.subscriber.lithium$notify((class_1542)this, this.subscriberData);
         }
      }
   }

   public void lithium$forceUnsubscribe(class_1799 publisher, int subscriberData) {
      if (this.subscriber != null) {
         this.subscriber.lithium$forceUnsubscribe((class_1542)this, this.subscriberData);
         this.subscriber = null;
         this.subscriberData = 0;
      }
   }

   public void lithium$notifyCount(class_1799 publisher, int subscriberData, int newCount) {
      if (publisher != this.method_6983()) {
         throw new IllegalStateException("Received notification from an unexpected publisher");
      } else {
         if (this.subscriber instanceof ChangeSubscriber.CountChangeSubscriber<class_1542> countChangeSubscriber) {
            countChangeSubscriber.lithium$notifyCount((class_1542)this, this.subscriberData, newCount);
         }
      }
   }

   @Inject(method = "method_6979(Lnet/minecraft/class_1799;)V", at = @At("HEAD"))
   private void beforeSetStack(class_1799 newStack, CallbackInfo ci) {
      if (this.subscriber != null) {
         class_1799 oldStack = this.method_6983();
         if (oldStack != newStack) {
            if (!oldStack.method_7960()) {
               ((ChangePublisher)oldStack).lithium$unsubscribe(this);
            }

            if (!newStack.method_7960()) {
               ((ChangePublisher)newStack).lithium$subscribe(this, this.subscriberData);
               this.subscriber.lithium$notify((class_1542)this, this.subscriberData);
            } else {
               this.subscriber.lithium$forceUnsubscribe((class_1542)this, this.subscriberData);
               this.subscriber = null;
               this.subscriberData = 0;
            }
         }
      }
   }
}
