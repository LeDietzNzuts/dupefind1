package net.raphimc.immediatelyfast.injection.mixins.hud_batching.compat;

import com.llamalad7.mixinextras.injector.v2.WrapWithCondition;
import net.minecraft.class_1921;
import net.minecraft.class_332;
import net.minecraft.class_4587;
import net.minecraft.class_4597.class_4598;
import net.raphimc.immediatelyfast.feature.core.BatchableBufferSource;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.Slice;
import org.spongepowered.asm.mixin.injection.At.Shift;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(class_332.class)
public abstract class MixinDrawContext {
   @Shadow
   public class_4598 field_44658;
   @Shadow
   @Final
   private class_4587 field_44657;

   @Shadow
   public abstract void method_51452();

   @Shadow
   protected abstract void method_51887();

   @Inject(
      method = "method_51432(Lnet/minecraft/class_327;Lnet/minecraft/class_1799;IILjava/lang/String;)V",
      slice = @Slice(from = @At(value = "INVOKE", target = "Lnet/minecraft/class_1796;method_7905(Lnet/minecraft/class_1792;F)F")),
      at = @At(value = "INVOKE", target = "Lnet/minecraft/class_332;method_51739(Lnet/minecraft/class_1921;IIIII)V")
   )
   private void forceDraw(CallbackInfo ci) {
      if (this.field_44658 instanceof BatchableBufferSource) {
         this.method_51452();
      }
   }

   @Redirect(method = "method_49698(Lnet/minecraft/class_8030;)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/class_332;method_51887()V"))
   private void drawIfBatching(class_332 instance) {
      if (this.field_44658 instanceof BatchableBufferSource) {
         this.method_51452();
      } else {
         this.method_51887();
      }
   }

   @Inject(
      method = "method_51432(Lnet/minecraft/class_327;Lnet/minecraft/class_1799;IILjava/lang/String;)V",
      at = @At(value = "INVOKE", target = "Lnet/minecraft/class_4587;method_22903()V", shift = Shift.AFTER)
   )
   private void translateZForAllItemOverlays(CallbackInfo ci) {
      if (this.field_44658 instanceof BatchableBufferSource) {
         this.field_44657.method_46416(0.0F, 0.0F, 200.0F);
      }
   }

   @WrapWithCondition(
      method = "method_51432(Lnet/minecraft/class_327;Lnet/minecraft/class_1799;IILjava/lang/String;)V",
      at = @At(value = "INVOKE", target = "Lnet/minecraft/class_4587;method_46416(FFF)V")
   )
   private boolean translateZEarlier(class_4587 instance, float x, float y, float z) {
      return !(this.field_44658 instanceof BatchableBufferSource);
   }

   @Redirect(
      method = "method_51432(Lnet/minecraft/class_327;Lnet/minecraft/class_1799;IILjava/lang/String;)V",
      at = @At(value = "INVOKE", target = "Lnet/minecraft/class_1921;method_51785()Lnet/minecraft/class_1921;")
   )
   private class_1921 useGuiRenderLayer() {
      return this.field_44658 instanceof BatchableBufferSource ? class_1921.method_51784() : class_1921.method_51785();
   }
}
