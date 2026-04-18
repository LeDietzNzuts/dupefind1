package dev.architectury.mixin.fabric.client;

import dev.architectury.event.events.client.ClientGuiEvent;
import dev.architectury.hooks.client.screen.ScreenAccess;
import dev.architectury.impl.ScreenAccessImpl;
import dev.architectury.impl.fabric.ScreenInputDelegate;
import java.util.List;
import net.minecraft.class_310;
import net.minecraft.class_364;
import net.minecraft.class_437;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.At.Shift;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(class_437.class)
public abstract class MixinScreen implements ScreenInputDelegate {
   @Unique
   private ScreenAccessImpl access;
   @Unique
   private class_437 inputDelegate;

   @Shadow
   public abstract List<? extends class_364> method_25396();

   @Unique
   private ScreenAccess getAccess() {
      if (this.access == null) {
         return this.access = new ScreenAccessImpl((class_437)this);
      } else {
         this.access.setScreen((class_437)this);
         return this.access;
      }
   }

   @Override
   public class_437 architectury_delegateInputs() {
      if (this.inputDelegate == null) {
         this.inputDelegate = new ScreenInputDelegate.DelegateScreen((class_437)this);
      }

      return this.inputDelegate;
   }

   @Inject(
      method = "init(Lnet/minecraft/client/Minecraft;II)V",
      at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/screens/Screen;init()V"),
      cancellable = true
   )
   private void preInit(class_310 minecraft, int width, int height, CallbackInfo ci) {
      if (ClientGuiEvent.INIT_PRE.invoker().init((class_437)this, this.getAccess()).isFalse()) {
         ci.cancel();
      }
   }

   @Inject(
      method = "init(Lnet/minecraft/client/Minecraft;II)V",
      at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/screens/Screen;init()V", shift = Shift.AFTER)
   )
   private void postInit(CallbackInfo ci) {
      ClientGuiEvent.INIT_POST.invoker().init((class_437)this, this.getAccess());
   }

   @Inject(method = "rebuildWidgets", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/screens/Screen;init()V"), cancellable = true)
   private void preInit2(CallbackInfo ci) {
      if (ClientGuiEvent.INIT_PRE.invoker().init((class_437)this, this.getAccess()).isFalse()) {
         ci.cancel();
      }
   }

   @Inject(method = "rebuildWidgets", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/screens/Screen;init()V", shift = Shift.AFTER))
   private void postInit2(CallbackInfo ci) {
      ClientGuiEvent.INIT_POST.invoker().init((class_437)this, this.getAccess());
   }
}
