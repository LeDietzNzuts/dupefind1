package net.caffeinemc.mods.sodium.mixin.features.render.world.sky;

import net.caffeinemc.mods.sodium.client.util.color.FastCubicSampler;
import net.minecraft.class_1959;
import net.minecraft.class_243;
import net.minecraft.class_3532;
import net.minecraft.class_4184;
import net.minecraft.class_638;
import net.minecraft.class_758;
import net.minecraft.class_6491.class_4859;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(class_758.class)
public class FogRendererMixin {
   @Redirect(
      method = "method_3210(Lnet/minecraft/class_4184;FLnet/minecraft/class_638;IF)V",
      at = @At(
         value = "INVOKE",
         target = "Lnet/minecraft/class_6491;method_24895(Lnet/minecraft/class_243;Lnet/minecraft/class_6491$class_4859;)Lnet/minecraft/class_243;"
      )
   )
   private static class_243 redirectSampleColor(class_243 pos, class_4859 fetcher, class_4184 camera, float tickDelta, class_638 level, int i, float f) {
      float u = class_3532.method_15363(class_3532.method_15362(level.method_30274(tickDelta) * (float) (Math.PI * 2)) * 2.0F + 0.5F, 0.0F, 1.0F);
      return FastCubicSampler.sampleColor(
         pos, (x, y, z) -> ((class_1959)level.method_22385().method_24854(x, y, z).comp_349()).method_24376(), v -> level.method_28103().method_28112(v, u)
      );
   }
}
