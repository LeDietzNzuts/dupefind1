package net.p3pp3rf1y.sophisticatedcore.mixin.common;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
import java.util.ArrayList;
import java.util.Collection;
import net.minecraft.class_1282;
import net.minecraft.class_1297;
import net.minecraft.class_1299;
import net.minecraft.class_1309;
import net.minecraft.class_1542;
import net.minecraft.class_1937;
import net.minecraft.class_2338;
import net.minecraft.class_2394;
import net.minecraft.class_2680;
import net.minecraft.class_3218;
import net.p3pp3rf1y.sophisticatedcore.event.common.LivingEntityEvents;
import net.p3pp3rf1y.sophisticatedcore.util.MixinHelper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = class_1309.class, priority = 500)
public abstract class LivingEntityMixin extends class_1297 {
   @Shadow
   protected int field_6238;

   public LivingEntityMixin(class_1299<?> entityType, class_1937 world) {
      super(entityType, world);
   }

   @Inject(method = "dropAllDeathLoot", at = @At("HEAD"))
   private void sophisticatedcore$captureDrops(class_3218 level, class_1282 damageSource, CallbackInfo ci) {
      this.sophisticatedCaptureDrops(new ArrayList());
   }

   @Inject(method = "dropAllDeathLoot", at = @At("RETURN"))
   private void sophisticatedcore$dropCapturedDrops(class_3218 level, class_1282 damageSource, CallbackInfo ci) {
      Collection<class_1542> drops = this.sophisticatedCaptureDrops(null);
      if (!((LivingEntityEvents.Drops)LivingEntityEvents.DROPS.invoker()).onLivingEntityDrops(MixinHelper.cast(this), damageSource, drops, this.field_6238 > 0)
         )
       {
         drops.forEach(level::method_8649);
      }
   }

   @Inject(method = "tick", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/Entity;tick()V"))
   private void sophisticatedcore$tick(CallbackInfo ci) {
      ((LivingEntityEvents.Tick)LivingEntityEvents.TICK.invoker()).onLivingEntityTick(MixinHelper.cast(this));
   }

   @WrapOperation(
      method = "checkFallDamage",
      at = @At(value = "INVOKE", target = "Lnet/minecraft/server/level/ServerLevel;sendParticles(Lnet/minecraft/core/particles/ParticleOptions;DDDIDDDD)I")
   )
   public <T extends class_2394> int sophisticatedcore$addLandingEffects(
      class_3218 level,
      T type,
      double posX,
      double posY,
      double posZ,
      int particleCount,
      double xOffset,
      double yOffset,
      double zOffset,
      double speed,
      Operation<Integer> original,
      @Local(argsOnly = true) class_2680 state,
      @Local(argsOnly = true) class_2338 pos
   ) {
      return !state.sophisticatedCore_addLandingEffects(level, pos, state, MixinHelper.cast(this), particleCount)
         ? (Integer)original.call(new Object[]{level, type, posX, posY, posZ, particleCount, xOffset, yOffset, zOffset, speed})
         : particleCount;
   }
}
