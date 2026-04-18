package dev.architectury.mixin.fabric;

import dev.architectury.event.EventResult;
import dev.architectury.event.events.common.InteractionEvent;
import dev.architectury.event.events.common.PlayerEvent;
import dev.architectury.event.events.common.TickEvent;
import net.minecraft.class_1268;
import net.minecraft.class_1269;
import net.minecraft.class_1297;
import net.minecraft.class_1542;
import net.minecraft.class_1657;
import net.minecraft.class_1799;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(class_1657.class)
public class MixinPlayer {
   @Inject(method = "tick", at = @At("HEAD"))
   private void preTick(CallbackInfo ci) {
      TickEvent.PLAYER_PRE.invoker().tick((class_1657)this);
   }

   @Inject(method = "tick", at = @At("RETURN"))
   private void postTick(CallbackInfo ci) {
      TickEvent.PLAYER_POST.invoker().tick((class_1657)this);
   }

   @Inject(method = "drop(Lnet/minecraft/world/item/ItemStack;ZZ)Lnet/minecraft/world/entity/item/ItemEntity;", at = @At("RETURN"), cancellable = true)
   private void drop(class_1799 itemStack, boolean bl, boolean bl2, CallbackInfoReturnable<class_1542> cir) {
      if (cir.getReturnValue() != null && PlayerEvent.DROP_ITEM.invoker().drop((class_1657)this, (class_1542)cir.getReturnValue()).isFalse()) {
         cir.setReturnValue(null);
      }
   }

   @Inject(
      method = "interactOn",
      at = @At(
         value = "INVOKE",
         target = "Lnet/minecraft/world/entity/player/Player;getItemInHand(Lnet/minecraft/world/InteractionHand;)Lnet/minecraft/world/item/ItemStack;",
         ordinal = 0
      ),
      cancellable = true
   )
   private void entityInteract(class_1297 entity, class_1268 interactionHand, CallbackInfoReturnable<class_1269> cir) {
      EventResult result = InteractionEvent.INTERACT_ENTITY.invoker().interact((class_1657)this, entity, interactionHand);
      if (result.isPresent()) {
         cir.setReturnValue(result.asMinecraft());
      }
   }
}
