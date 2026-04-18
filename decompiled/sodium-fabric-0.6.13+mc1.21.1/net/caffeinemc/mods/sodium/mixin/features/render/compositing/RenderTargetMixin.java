package net.caffeinemc.mods.sodium.mixin.features.render.compositing;

import net.caffeinemc.mods.sodium.client.compatibility.workarounds.Workarounds;
import net.minecraft.class_276;
import org.lwjgl.opengl.GL32C;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(class_276.class)
public class RenderTargetMixin {
   @Shadow
   public int field_1476;
   @Shadow
   public int field_1482;
   @Shadow
   public int field_1481;

   @Inject(method = "method_22594(IIZ)V", at = @At("HEAD"), cancellable = true)
   public void blitToScreen(int width, int height, boolean disableBlend, CallbackInfo ci) {
      if (!Workarounds.isWorkaroundEnabled(Workarounds.Reference.INTEL_FRAMEBUFFER_BLIT_CRASH_WHEN_UNFOCUSED)) {
         if (disableBlend) {
            ci.cancel();
            GL32C.glBindFramebuffer(36008, this.field_1476);
            GL32C.glBlitFramebuffer(0, 0, width, height, 0, 0, width, height, 16384, 9729);
            GL32C.glBindFramebuffer(36008, 0);
         }
      }
   }
}
