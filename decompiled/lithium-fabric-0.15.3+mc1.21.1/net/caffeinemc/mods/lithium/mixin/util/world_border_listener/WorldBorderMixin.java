package net.caffeinemc.mods.lithium.mixin.util.world_border_listener;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.caffeinemc.mods.lithium.common.world.listeners.WorldBorderListenerOnce;
import net.caffeinemc.mods.lithium.common.world.listeners.WorldBorderPositionListenerMulti;
import net.minecraft.class_2780;
import net.minecraft.class_2784;
import net.minecraft.class_2784.class_2785;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(class_2784.class)
public abstract class WorldBorderMixin {
   @Shadow
   private class_2785 field_12736;
   @Unique
   private final WorldBorderPositionListenerMulti worldBorderPositionListenerMulti = new WorldBorderPositionListenerMulti();

   @Shadow
   public abstract void method_11983(class_2780 var1);

   @Inject(method = "<init>()V", at = @At("RETURN"))
   private void registerSimpleWorldBorderListenerMulti(CallbackInfo ci) {
      this.method_11983(this.worldBorderPositionListenerMulti);
   }

   @Inject(method = "method_11983(Lnet/minecraft/class_2780;)V", at = @At("HEAD"), cancellable = true)
   private void addSimpleListenerOnce(class_2780 listener, CallbackInfo ci) {
      if (listener instanceof WorldBorderListenerOnce simpleListener) {
         ci.cancel();
         this.worldBorderPositionListenerMulti.add(simpleListener);
      }
   }

   @WrapOperation(
      method = "method_11982()V",
      at = @At(value = "INVOKE", target = "Lnet/minecraft/class_2784$class_2785;method_11986()Lnet/minecraft/class_2784$class_2785;")
   )
   public class_2785 getUpdatedArea(class_2785 instance, Operation<class_2785> original) {
      class_2785 prevExtent = this.field_12736;
      class_2785 newExtent = (class_2785)original.call(new Object[]{instance});
      if (newExtent != prevExtent) {
         this.worldBorderPositionListenerMulti.onAreaReplaced((class_2784)this);
      }

      return newExtent;
   }
}
