package vectorwing.farmersdelight.common.mixin.refabricated;

import net.minecraft.class_1282;
import net.minecraft.class_1297;
import net.minecraft.class_1299;
import net.minecraft.class_1309;
import net.minecraft.class_1657;
import net.minecraft.class_1937;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import vectorwing.farmersdelight.common.item.SkilletItem;

@Mixin(class_1657.class)
public abstract class PlayerMixin extends class_1309 {
   @Shadow
   public abstract float method_7261(float var1);

   protected PlayerMixin(class_1299<? extends class_1309> entityType, class_1937 level) {
      super(entityType, level);
   }

   @Inject(method = "method_7324(Lnet/minecraft/class_1297;)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/class_1657;method_7350()V"))
   private void captureAttackStrengthScale(class_1297 target, CallbackInfo ci) {
      SkilletItem.SkilletEvents.attackPower = this.method_7261(0.0F);
   }

   @Inject(method = "method_5643(Lnet/minecraft/class_1282;F)Z", at = @At("HEAD"))
   private void handleSkilletAttackSound(class_1282 source, float amount, CallbackInfoReturnable<Boolean> cir) {
      SkilletItem.SkilletEvents.playSkilletAttackSound(this, source);
   }
}
