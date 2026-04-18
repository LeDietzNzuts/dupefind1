package dev.architectury.mixin.fabric;

import dev.architectury.event.EventResult;
import dev.architectury.event.events.common.PlayerEvent;
import net.minecraft.class_1542;
import net.minecraft.class_1657;
import net.minecraft.class_1799;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(class_1542.class)
public abstract class MixinItemEntity {
   @Unique
   private class_1799 cache;

   @Shadow
   public abstract class_1799 method_6983();

   @Inject(method = "playerTouch", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/ItemStack;getCount()I"), cancellable = true)
   private void prePickup(class_1657 player, CallbackInfo ci) {
      this.cache = this.method_6983().method_7972();
      EventResult canPickUp = PlayerEvent.PICKUP_ITEM_PRE.invoker().canPickup(player, (class_1542)this, this.method_6983());
      if (canPickUp.isFalse()) {
         ci.cancel();
      }
   }

   @Inject(
      method = "playerTouch",
      at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/player/Player;take(Lnet/minecraft/world/entity/Entity;I)V")
   )
   private void pickup(class_1657 player, CallbackInfo ci) {
      if (this.cache != null) {
         PlayerEvent.PICKUP_ITEM_POST.invoker().pickup(player, (class_1542)this, this.cache);
      }

      this.cache = null;
   }
}
