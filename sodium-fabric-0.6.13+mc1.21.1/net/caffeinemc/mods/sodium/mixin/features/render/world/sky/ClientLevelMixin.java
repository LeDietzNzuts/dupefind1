package net.caffeinemc.mods.sodium.mixin.features.render.world.sky;

import java.util.function.Function;
import net.caffeinemc.mods.sodium.client.util.color.FastCubicSampler;
import net.minecraft.class_1937;
import net.minecraft.class_1959;
import net.minecraft.class_243;
import net.minecraft.class_638;
import net.minecraft.class_6491.class_4859;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(class_638.class)
public class ClientLevelMixin {
   @Redirect(
      method = "method_23777(Lnet/minecraft/class_243;F)Lnet/minecraft/class_243;",
      at = @At(
         value = "INVOKE",
         target = "Lnet/minecraft/class_6491;method_24895(Lnet/minecraft/class_243;Lnet/minecraft/class_6491$class_4859;)Lnet/minecraft/class_243;"
      )
   )
   private class_243 redirectSampleColor(class_243 pos, class_4859 rgbFetcher) {
      class_1937 level = (class_1937)this;
      return FastCubicSampler.sampleColor(pos, (x, y, z) -> ((class_1959)level.method_16359(x, y, z).comp_349()).method_8697(), Function.identity());
   }
}
