package dev.architectury.mixin.fabric.client;

import dev.architectury.event.EventResult;
import dev.architectury.event.events.client.ClientChatEvent;
import dev.architectury.event.events.client.ClientPlayerEvent;
import dev.architectury.event.events.client.ClientRecipeUpdateEvent;
import net.minecraft.class_1863;
import net.minecraft.class_2535;
import net.minecraft.class_2678;
import net.minecraft.class_2724;
import net.minecraft.class_2788;
import net.minecraft.class_310;
import net.minecraft.class_634;
import net.minecraft.class_638;
import net.minecraft.class_746;
import net.minecraft.class_8673;
import net.minecraft.class_8675;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.At.Shift;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(class_634.class)
public abstract class MixinClientPacketListener extends class_8673 {
   @Shadow
   @Final
   private class_1863 field_3688;
   @Shadow
   private class_638 field_3699;
   @Unique
   private class_746 tmpPlayer;

   protected MixinClientPacketListener(class_310 minecraft, class_2535 connection, class_8675 commonListenerCookie) {
      super(minecraft, connection, commonListenerCookie);
   }

   @Inject(method = "handleLogin", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/Options;setServerRenderDistance(I)V", shift = Shift.AFTER))
   private void handleLogin(class_2678 packet, CallbackInfo ci) {
      ClientPlayerEvent.CLIENT_PLAYER_JOIN.invoker().join(this.field_45588.field_1724);
   }

   @Inject(method = "handleRespawn", at = @At("HEAD"))
   private void handleRespawnPre(class_2724 packet, CallbackInfo ci) {
      this.tmpPlayer = this.field_45588.field_1724;
   }

   @Inject(
      method = "handleRespawn",
      at = @At(value = "INVOKE", target = "Lnet/minecraft/client/multiplayer/ClientLevel;addEntity(Lnet/minecraft/world/entity/Entity;)V")
   )
   private void handleRespawn(class_2724 packet, CallbackInfo ci) {
      ClientPlayerEvent.CLIENT_PLAYER_RESPAWN.invoker().respawn(this.tmpPlayer, this.field_45588.field_1724);
      this.tmpPlayer = null;
   }

   @Inject(method = "handleUpdateRecipes", at = @At("RETURN"))
   private void handleUpdateRecipes(class_2788 clientboundUpdateRecipesPacket, CallbackInfo ci) {
      ClientRecipeUpdateEvent.EVENT.invoker().update(this.field_3688);
   }

   @Inject(method = "sendChat(Ljava/lang/String;)V", at = @At("HEAD"), cancellable = true)
   private void chat(String string, CallbackInfo ci) {
      EventResult process = ClientChatEvent.SEND.invoker().send(string, null);
      if (process.isFalse()) {
         ci.cancel();
      }
   }
}
