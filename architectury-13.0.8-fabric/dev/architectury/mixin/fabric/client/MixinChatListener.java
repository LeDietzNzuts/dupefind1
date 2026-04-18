package dev.architectury.mixin.fabric.client;

import com.mojang.authlib.GameProfile;
import dev.architectury.event.CompoundEventResult;
import dev.architectury.event.events.client.ClientChatEvent;
import dev.architectury.event.events.client.ClientSystemMessageEvent;
import java.util.Objects;
import net.minecraft.class_2561;
import net.minecraft.class_7471;
import net.minecraft.class_7594;
import net.minecraft.class_2556.class_7602;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(class_7594.class)
public class MixinChatListener {
   @Unique
   class_7602 boundChatType;
   @Unique
   private ThreadLocal<class_2561> cancelNextChat = new ThreadLocal<>();
   @Unique
   private ThreadLocal<class_2561> cancelNextSystem = new ThreadLocal<>();

   @Inject(method = "handlePlayerChatMessage", at = @At(value = "INVOKE", target = "Ljava/time/Instant;now()Ljava/time/Instant;"))
   private void handlePlayerChatMessage(class_7471 playerChatMessage, GameProfile gameProfile, class_7602 bound, CallbackInfo ci) {
      this.boundChatType = bound;
   }

   @ModifyVariable(
      method = "handlePlayerChatMessage",
      at = @At(value = "INVOKE", target = "Lnet/minecraft/network/chat/PlayerChatMessage;signature()Lnet/minecraft/network/chat/MessageSignature;")
   )
   private class_2561 modifyMessage(class_2561 value) {
      this.cancelNextChat.remove();
      CompoundEventResult<class_2561> process = ClientChatEvent.RECEIVED.invoker().process(this.boundChatType, value);
      this.boundChatType = null;
      if (process.isPresent()) {
         if (process.isFalse()) {
            this.cancelNextChat.set(value);
         } else if (process.object() != null) {
            return process.object();
         }
      }

      return value;
   }

   @Inject(
      method = "handlePlayerChatMessage",
      at = @At(
         value = "INVOKE",
         target = "Lnet/minecraft/client/multiplayer/chat/ChatListener;handleMessage(Lnet/minecraft/network/chat/MessageSignature;Ljava/util/function/BooleanSupplier;)V"
      ),
      cancellable = true,
      locals = LocalCapture.CAPTURE_FAILHARD
   )
   private void handleChatPre(
      class_7471 playerChatMessage,
      GameProfile gameProfile,
      class_7602 bound,
      CallbackInfo ci,
      boolean onlyShowSecureChat,
      class_7471 filtered,
      class_2561 component
   ) {
      if (Objects.equals(this.cancelNextChat.get(), component)) {
         ci.cancel();
      }

      this.cancelNextChat.remove();
   }

   @ModifyVariable(method = "handleSystemMessage", at = @At("HEAD"), argsOnly = true)
   private class_2561 modifySystemMessage(class_2561 message) {
      this.cancelNextSystem.remove();
      CompoundEventResult<class_2561> process = ClientSystemMessageEvent.RECEIVED.invoker().process(message);
      if (process.isPresent()) {
         if (process.isFalse()) {
            this.cancelNextSystem.set(message);
         } else if (process.object() != null) {
            return process.object();
         }
      }

      return message;
   }

   @Inject(
      method = "handleSystemMessage",
      at = @At(value = "INVOKE", target = "Lnet/minecraft/client/Options;hideMatchedNames()Lnet/minecraft/client/OptionInstance;"),
      cancellable = true
   )
   private void handleSystemMessage(class_2561 component, boolean bl, CallbackInfo ci) {
      if (Objects.equals(this.cancelNextSystem.get(), component)) {
         ci.cancel();
      }

      this.cancelNextSystem.remove();
   }
}
