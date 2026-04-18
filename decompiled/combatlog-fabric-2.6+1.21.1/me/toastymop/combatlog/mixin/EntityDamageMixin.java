package me.toastymop.combatlog.mixin;

import me.toastymop.combatlog.CombatCheck;
import net.minecraft.class_1282;
import net.minecraft.class_1297;
import net.minecraft.class_1299;
import net.minecraft.class_1309;
import net.minecraft.class_1937;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(class_1309.class)
public abstract class EntityDamageMixin extends class_1297 {
   public EntityDamageMixin(class_1299<?> type, class_1937 world) {
      super(type, world);
   }

   @Shadow
   public abstract boolean method_5643(class_1282 var1, float var2);

   @Inject(method = "method_5643(Lnet/minecraft/class_1282;F)Z", at = @At("TAIL"))
   protected void injectCheckMethod(class_1282 source, float amount, CallbackInfoReturnable<Boolean> cir) {
      CombatCheck.CheckCombat(this);
   }
}
