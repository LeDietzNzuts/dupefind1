package net.caffeinemc.mods.lithium.mixin.math.sine_lut;

import net.caffeinemc.mods.lithium.common.util.math.CompactSineLUT;
import net.minecraft.class_3532;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(class_3532.class)
public class MthMixin {
   @Shadow
   @Final
   @Mutable
   public static float[] field_15725;

   @Inject(method = "<clinit>()V", at = @At("RETURN"))
   private static void onClassInit(CallbackInfo ci) {
      CompactSineLUT.init();
      field_15725 = null;
   }

   @Overwrite
   public static float method_15374(float f) {
      return CompactSineLUT.sin(f);
   }

   @Overwrite
   public static float method_15362(float f) {
      return CompactSineLUT.cos(f);
   }
}
