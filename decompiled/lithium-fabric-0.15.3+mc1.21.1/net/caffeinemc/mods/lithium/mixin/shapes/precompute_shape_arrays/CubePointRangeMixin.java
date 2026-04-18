package net.caffeinemc.mods.lithium.mixin.shapes.precompute_shape_arrays;

import net.minecraft.class_246;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(class_246.class)
public class CubePointRangeMixin {
   @Shadow
   @Final
   private int field_1365;
   private double scale;

   @Inject(method = "<init>(I)V", at = @At("RETURN"))
   public void initScale(int sectionCount, CallbackInfo ci) {
      this.scale = 1.0 / this.field_1365;
   }

   @Overwrite
   public double getDouble(int position) {
      return position * this.scale;
   }
}
