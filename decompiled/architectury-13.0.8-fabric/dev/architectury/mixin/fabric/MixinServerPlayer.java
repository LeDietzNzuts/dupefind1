package dev.architectury.mixin.fabric;

import dev.architectury.event.events.common.PlayerEvent;
import java.util.OptionalInt;
import net.minecraft.class_1263;
import net.minecraft.class_1496;
import net.minecraft.class_3218;
import net.minecraft.class_3222;
import net.minecraft.class_3908;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.At.Shift;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(class_3222.class)
public class MixinServerPlayer {
   @Inject(method = "restoreFrom", at = @At("RETURN"))
   private void restoreFrom(class_3222 serverPlayer, boolean bl, CallbackInfo ci) {
      PlayerEvent.PLAYER_CLONE.invoker().clone(serverPlayer, (class_3222)this, bl);
   }

   @Inject(method = "openMenu", at = @At("RETURN"))
   private void openMenu(class_3908 menuProvider, CallbackInfoReturnable<OptionalInt> cir) {
      if (((OptionalInt)cir.getReturnValue()).isPresent()) {
         PlayerEvent.OPEN_MENU.invoker().open((class_3222)this, ((class_3222)this).field_7512);
      }
   }

   @Inject(method = "openHorseInventory", at = @At("RETURN"))
   private void openHorseInventory(class_1496 abstractHorse, class_1263 container, CallbackInfo ci) {
      PlayerEvent.OPEN_MENU.invoker().open((class_3222)this, ((class_3222)this).field_7512);
   }

   @Inject(
      method = "doCloseContainer",
      at = @At(
         value = "INVOKE",
         target = "Lnet/minecraft/world/inventory/AbstractContainerMenu;removed(Lnet/minecraft/world/entity/player/Player;)V",
         shift = Shift.AFTER
      )
   )
   private void doCloseContainer(CallbackInfo ci) {
      PlayerEvent.CLOSE_MENU.invoker().close((class_3222)this, ((class_3222)this).field_7512);
   }

   @Inject(method = "triggerDimensionChangeTriggers", at = @At("HEAD"))
   private void changeDimension(class_3218 serverLevel, CallbackInfo ci) {
      PlayerEvent.CHANGE_DIMENSION.invoker().change((class_3222)this, serverLevel.method_27983(), ((class_3222)this).method_37908().method_27983());
   }
}
