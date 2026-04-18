package me.toastymop.combatlog.mixin;

import me.toastymop.combatlog.CombatDisconnect;
import net.minecraft.class_3222;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(class_3222.class)
public abstract class DisconnectMixin {
   @Inject(method = "method_14231()V", at = @At("HEAD"))
   private void injectDisconnectMethod(CallbackInfo ci) {
      CombatDisconnect.OnPlayerDisconnect((class_3222)this);
   }
}
