package net.caffeinemc.mods.lithium.mixin.util.item_component_and_count_tracking;

import net.caffeinemc.mods.lithium.common.util.change_tracking.ChangePublisher;
import net.caffeinemc.mods.lithium.common.util.change_tracking.ChangeSubscriber;
import net.minecraft.class_1799;
import net.minecraft.class_9331;
import net.minecraft.class_9334;
import net.minecraft.class_9335;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(class_1799.class)
public abstract class ItemStackMixin implements ChangePublisher<class_1799>, ChangeSubscriber<class_9335> {
   @Shadow
   @Final
   class_9335 field_49270;
   @Shadow
   private int field_8031;
   @Unique
   private ChangeSubscriber<class_1799> subscriber;
   @Unique
   private int subscriberData;

   @Shadow
   public abstract boolean method_7960();

   @Override
   public void lithium$subscribe(ChangeSubscriber<class_1799> subscriber, int subscriberData) {
      if (this.method_7960()) {
         throw new IllegalStateException("Cannot subscribe to an empty ItemStack!");
      } else {
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
   }

   @Override
   public int lithium$unsubscribe(ChangeSubscriber<class_1799> subscriber) {
      if (this.method_7960()) {
         throw new IllegalStateException("Cannot unsubscribe from an empty ItemStack!");
      } else {
         int retval = ChangeSubscriber.dataOf(this.subscriber, subscriber, this.subscriberData);
         this.subscriberData = ChangeSubscriber.dataWithout(this.subscriber, subscriber, this.subscriberData);
         this.subscriber = ChangeSubscriber.without(this.subscriber, subscriber);
         if (this.subscriber == null) {
            ((ChangePublisher)this.field_49270).lithium$unsubscribe(this);
         }

         return retval;
      }
   }

   @Override
   public void lithium$unsubscribeWithData(ChangeSubscriber<class_1799> subscriber, int subscriberData) {
      if (this.method_7960()) {
         throw new IllegalStateException("Cannot unsubscribe from an empty ItemStack!");
      } else {
         this.subscriberData = ChangeSubscriber.dataWithout(this.subscriber, subscriber, this.subscriberData, subscriberData, true);
         this.subscriber = ChangeSubscriber.without(this.subscriber, subscriber, subscriberData, true);
         if (this.subscriber == null) {
            ((ChangePublisher)this.field_49270).lithium$unsubscribe(this);
         }
      }
   }

   @Override
   public boolean lithium$isSubscribedWithData(ChangeSubscriber<class_1799> subscriber, int subscriberData) {
      if (this.method_7960()) {
         throw new IllegalStateException("Cannot be subscribed to an empty ItemStack!");
      } else {
         return ChangeSubscriber.containsSubscriber(this.subscriber, this.subscriberData, subscriber, subscriberData);
      }
   }

   public void lithium$forceUnsubscribe(class_9335 publisher, int subscriberData) {
      if (publisher != this.field_49270) {
         throw new IllegalStateException("Invalid publisher, expected " + this.field_49270 + " but got " + publisher);
      } else {
         this.subscriber.lithium$forceUnsubscribe((class_1799)this, this.subscriberData);
         this.subscriber = null;
         this.subscriberData = 0;
      }
   }

   @Unique
   private void startTrackingChanges() {
      ((ChangePublisher)this.field_49270).lithium$subscribe(this, 0);
   }

   @Inject(method = "method_7939(I)V", at = @At("HEAD"))
   private void beforeChangeCount(int count, CallbackInfo ci) {
      if (count != this.field_8031) {
         if (this.subscriber instanceof ChangeSubscriber.CountChangeSubscriber<class_1799> countChangeSubscriber) {
            countChangeSubscriber.lithium$notifyCount((class_1799)this, this.subscriberData, count);
         }

         if (count == 0) {
            ((ChangePublisher)this.field_49270).lithium$unsubscribe(this);
            if (this.subscriber != null) {
               this.subscriber.lithium$forceUnsubscribe((class_1799)this, this.subscriberData);
               this.subscriber = null;
               this.subscriberData = 0;
            }
         }
      }
   }

   public void lithium$notify(class_9335 publisher, int subscriberData) {
      if (publisher != this.field_49270) {
         throw new IllegalStateException("Invalid publisher, expected " + this.field_49270 + " but got " + publisher);
      } else {
         if (this.subscriber != null) {
            this.subscriber.lithium$notify((class_1799)this, this.subscriberData);
         }
      }
   }

   @Inject(method = "method_57379(Lnet/minecraft/class_9331;Ljava/lang/Object;)Ljava/lang/Object;", at = @At("RETURN"))
   private <T> void onSetComponent(class_9331<? super T> type, @Nullable T value, CallbackInfoReturnable<T> cir) {
      if (type == class_9334.field_49633 && this.subscriber instanceof ChangeSubscriber.EnchantmentSubscriber<class_1799> enchantmentSubscriber) {
         enchantmentSubscriber.lithium$notifyAfterEnchantmentChange((class_1799)this, this.subscriberData);
      }
   }
}
