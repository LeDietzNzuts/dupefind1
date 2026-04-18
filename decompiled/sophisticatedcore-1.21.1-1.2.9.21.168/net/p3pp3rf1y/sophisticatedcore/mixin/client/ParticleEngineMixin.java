package net.p3pp3rf1y.sophisticatedcore.mixin.client;

import net.minecraft.class_2338;
import net.minecraft.class_2680;
import net.minecraft.class_638;
import net.minecraft.class_702;
import net.minecraft.class_703;
import net.minecraft.class_727;
import net.p3pp3rf1y.sophisticatedcore.util.MixinHelper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

@Mixin(class_702.class)
public class ParticleEngineMixin {
   @Shadow
   protected class_638 field_3834;

   @Redirect(method = "destroy", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/block/state/BlockState;shouldSpawnTerrainParticles()Z"))
   private boolean sophisticatedcore$addDestroyEffects(class_2680 blockState, class_2338 pos) {
      return !blockState.sophisticatedCore_addDestroyEffects(this.field_3834, pos, MixinHelper.cast(this));
   }

   @ModifyArgs(
      method = "method_34020",
      at = @At(value = "INVOKE", target = "Lnet/minecraft/client/particle/ParticleEngine;add(Lnet/minecraft/client/particle/Particle;)V")
   )
   private void sophisticatedcore$updateSprite(Args args, class_2338 pos, class_2680 state, double i, double j, double k, double l, double m, double n) {
      class_703 p = (class_703)args.get(0);
      if (p instanceof class_727 tp) {
         tp.sophisticatedCore_updateSprite(state, pos);
      }
   }
}
