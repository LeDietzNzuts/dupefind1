package dev.architectury.mixin.fabric;

import dev.architectury.event.events.common.ExplosionEvent;
import net.minecraft.class_1282;
import net.minecraft.class_1297;
import net.minecraft.class_1927;
import net.minecraft.class_1937;
import net.minecraft.class_2394;
import net.minecraft.class_3414;
import net.minecraft.class_5362;
import net.minecraft.class_6880;
import net.minecraft.class_1927.class_4179;
import net.minecraft.class_1937.class_7867;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(class_1937.class)
public class MixinLevel {
   @Inject(
      method = "explode(Lnet/minecraft/world/entity/Entity;Lnet/minecraft/world/damagesource/DamageSource;Lnet/minecraft/world/level/ExplosionDamageCalculator;DDDFZLnet/minecraft/world/level/Level$ExplosionInteraction;ZLnet/minecraft/core/particles/ParticleOptions;Lnet/minecraft/core/particles/ParticleOptions;Lnet/minecraft/core/Holder;)Lnet/minecraft/world/level/Explosion;",
      at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/Explosion;explode()V"),
      cancellable = true,
      locals = LocalCapture.CAPTURE_FAILHARD
   )
   private void explodePre(
      class_1297 entity,
      class_1282 damageSource,
      class_5362 explosionDamageCalculator,
      double d,
      double e,
      double f,
      float g,
      boolean bl,
      class_7867 explosionInteraction,
      boolean bl2,
      class_2394 particleOptions,
      class_2394 particleOptions2,
      class_6880<class_3414> soundEvent,
      CallbackInfoReturnable<class_1927> cir,
      class_4179 blockInteraction,
      class_1927 explosion
   ) {
      if (ExplosionEvent.PRE.invoker().explode((class_1937)this, explosion).isFalse()) {
         cir.setReturnValue(explosion);
      }
   }
}
