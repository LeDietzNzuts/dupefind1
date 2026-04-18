package net.p3pp3rf1y.sophisticatedcore.mixin.common;

import com.llamalad7.mixinextras.sugar.Share;
import com.llamalad7.mixinextras.sugar.ref.LocalRef;
import net.minecraft.class_1269;
import net.minecraft.class_1542;
import net.minecraft.class_1657;
import net.minecraft.class_1799;
import net.p3pp3rf1y.sophisticatedcore.event.common.ItemEntityEvents;
import net.p3pp3rf1y.sophisticatedcore.util.MixinHelper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(class_1542.class)
public abstract class ItemEntityMixin {
   @Shadow
   public abstract class_1799 method_6983();

   @Inject(method = "playerTouch", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/ItemStack;getCount()I"), cancellable = true)
   private void sophisticatedcore$playerTouch(class_1657 player, CallbackInfo ci, @Share("cachedStack") LocalRef<class_1799> cachedStack) {
      cachedStack.set(this.method_6983().method_7972());
      class_1269 canPickup = ((ItemEntityEvents.CanPickup)ItemEntityEvents.CAN_PICKUP.invoker()).canPickup(player, MixinHelper.cast(this), this.method_6983());
      if (canPickup != class_1269.field_5811) {
         ci.cancel();
      }
   }

   @Inject(
      method = "playerTouch",
      at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/player/Player;take(Lnet/minecraft/world/entity/Entity;I)V")
   )
   private void sophisticatedcore$playerTouchPickup(class_1657 player, CallbackInfo ci, @Share("cachedStack") LocalRef<class_1799> cachedStack) {
      if (cachedStack.get() != null) {
         ((ItemEntityEvents.PostPickup)ItemEntityEvents.POST_PICKUP.invoker()).postPickup(player, MixinHelper.cast(this), (class_1799)cachedStack.get());
      }
   }
}
