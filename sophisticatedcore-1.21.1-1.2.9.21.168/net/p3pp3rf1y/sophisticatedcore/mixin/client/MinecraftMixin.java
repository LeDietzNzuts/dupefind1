package net.p3pp3rf1y.sophisticatedcore.mixin.client;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import javax.annotation.Nullable;
import net.minecraft.class_2338;
import net.minecraft.class_2350;
import net.minecraft.class_239;
import net.minecraft.class_2680;
import net.minecraft.class_310;
import net.minecraft.class_638;
import net.minecraft.class_702;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(class_310.class)
public class MinecraftMixin {
   @Shadow
   @Nullable
   public class_638 field_1687;
   @Shadow
   @Nullable
   public class_239 field_1765;

   @WrapOperation(
      method = "continueAttack",
      at = @At(value = "INVOKE", target = "Lnet/minecraft/client/particle/ParticleEngine;crack(Lnet/minecraft/core/BlockPos;Lnet/minecraft/core/Direction;)V")
   )
   private void sophisticatedcore$addBlockHitEffects(class_702 instance, class_2338 pos, class_2350 side, Operation<Void> original) {
      class_2680 state = this.field_1687.method_8320(pos);
      if (!state.sophisticatedCore_addHitEffects(this.field_1687, this.field_1765, instance)) {
         original.call(new Object[]{instance, pos, side});
      }
   }
}
