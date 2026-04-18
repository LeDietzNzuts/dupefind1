package org.embeddedt.modernfix.fabric.mixin.safety;

import net.minecraft.class_1011;
import net.minecraft.class_1043;
import org.embeddedt.modernfix.ModernFix;
import org.embeddedt.modernfix.annotation.ClientOnlyMixin;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(class_1043.class)
@ClientOnlyMixin
public class DynamicTextureMixin {
   @Shadow
   @Nullable
   private class_1011 field_5200;
   private Exception closeTrace;

   @Inject(
      method = "method_22793",
      at = @At(value = "FIELD", target = "Lnet/minecraft/client/renderer/texture/DynamicTexture;pixels:Lcom/mojang/blaze3d/platform/NativeImage;", ordinal = 0)
   )
   private void checkNullPixels(CallbackInfo ci) {
      if (this.field_5200 == null) {
         ModernFix.LOGGER.error("Attempted to upload null texture! This is not allowed, closed here", this.closeTrace);
      }
   }

   @Inject(method = "close", at = @At("HEAD"))
   private void storeCloseTrace(CallbackInfo ci) {
      this.closeTrace = new Exception();
   }
}
