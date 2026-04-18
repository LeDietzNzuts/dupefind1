package me.toastymop.combatlog.mixin;

import me.toastymop.combatlog.CombatConfig;
import me.toastymop.combatlog.util.IEntityDataSaver;
import me.toastymop.combatlog.util.TagData;
import net.minecraft.class_1657;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(class_1657.class)
public class PlayerMixin {
   @Inject(method = "method_23668()Z", at = @At(value = "INVOKE", target = "Lnet/minecraft/class_1657;method_23669()V"), cancellable = true)
   private void preventFlying(CallbackInfoReturnable<Boolean> cir) {
      if (CombatConfig.Config.disableElytra && TagData.getCombat((IEntityDataSaver)this)) {
         cir.setReturnValue(false);
      }
   }
}
