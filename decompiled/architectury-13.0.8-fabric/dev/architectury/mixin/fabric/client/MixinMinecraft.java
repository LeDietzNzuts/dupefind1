package dev.architectury.mixin.fabric.client;

import dev.architectury.event.CompoundEventResult;
import dev.architectury.event.events.client.ClientGuiEvent;
import dev.architectury.event.events.client.ClientPlayerEvent;
import dev.architectury.event.events.common.InteractionEvent;
import net.minecraft.class_1268;
import net.minecraft.class_1799;
import net.minecraft.class_239;
import net.minecraft.class_310;
import net.minecraft.class_437;
import net.minecraft.class_746;
import net.minecraft.class_239.class_240;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.At.Shift;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Unique
@Mixin(class_310.class)
public abstract class MixinMinecraft {
   @Shadow
   @Nullable
   public class_746 field_1724;
   @Shadow
   @Nullable
   public class_239 field_1765;
   @Unique
   private ThreadLocal<Boolean> setScreenCancelled = new ThreadLocal<>();

   @Shadow
   public abstract void method_1507(@Nullable class_437 var1);

   @Inject(
      method = "disconnect(Lnet/minecraft/client/gui/screens/Screen;Z)V",
      at = @At(value = "INVOKE", target = "Lnet/minecraft/client/GameNarrator;clear()V")
   )
   private void handleLogin(class_437 screen, boolean retainDownloadedPacks, CallbackInfo ci) {
      ClientPlayerEvent.CLIENT_PLAYER_QUIT.invoker().quit(this.field_1724);
   }

   @Inject(
      method = "startUseItem",
      at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/ItemStack;isEmpty()Z", ordinal = 1),
      locals = LocalCapture.CAPTURE_FAILHARD
   )
   private void rightClickAir(CallbackInfo ci, class_1268[] var1, int var2, int var3, class_1268 interactionHand, class_1799 itemStack) {
      if (itemStack.method_7960() && (this.field_1765 == null || this.field_1765.method_17783() == class_240.field_1333)) {
         InteractionEvent.CLIENT_RIGHT_CLICK_AIR.invoker().click(this.field_1724, interactionHand);
      }
   }

   @Inject(method = "startAttack", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/player/LocalPlayer;resetAttackStrengthTicker()V", ordinal = 0))
   private void leftClickAir(CallbackInfoReturnable<Boolean> ci) {
      InteractionEvent.CLIENT_LEFT_CLICK_AIR.invoker().click(this.field_1724, class_1268.field_5808);
   }

   @ModifyVariable(
      method = "setScreen",
      at = @At(
         value = "FIELD",
         opcode = 181,
         target = "Lnet/minecraft/client/Minecraft;screen:Lnet/minecraft/client/gui/screens/Screen;",
         shift = Shift.BY,
         by = -1
      ),
      argsOnly = true
   )
   public class_437 modifyScreen(class_437 screen) {
      class_437 old = screen;
      CompoundEventResult<class_437> event = ClientGuiEvent.SET_SCREEN.invoker().modifyScreen(screen);
      if (event.isPresent()) {
         if (event.isFalse()) {
            this.setScreenCancelled.set(true);
            return screen;
         }

         screen = event.object();
         if (old != null && screen != old) {
            old.method_25432();
         }
      }

      this.setScreenCancelled.set(false);
      return screen;
   }

   @Inject(
      method = "setScreen",
      at = @At(
         value = "FIELD",
         opcode = 181,
         target = "Lnet/minecraft/client/Minecraft;screen:Lnet/minecraft/client/gui/screens/Screen;",
         shift = Shift.BY,
         by = -1
      ),
      cancellable = true
   )
   public void cancelSetScreen(@Nullable class_437 screen, CallbackInfo ci) {
      if (this.setScreenCancelled.get()) {
         ci.cancel();
         this.setScreenCancelled.set(false);
      }
   }
}
