package net.caffeinemc.mods.lithium.mixin.shapes.precompute_shape_arrays;

import it.unimi.dsi.fastutil.doubles.DoubleList;
import net.minecraft.class_246;
import net.minecraft.class_249;
import net.minecraft.class_251;
import net.minecraft.class_2350.class_2351;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(class_249.class)
public class CubeVoxelShapeMixin {
   private static final class_2351[] AXIS = class_2351.values();
   private DoubleList[] list;

   @Inject(method = "<init>(Lnet/minecraft/class_251;)V", at = @At("RETURN"))
   private void onConstructed(class_251 voxels, CallbackInfo ci) {
      this.list = new DoubleList[AXIS.length];

      for (class_2351 axis : AXIS) {
         this.list[axis.ordinal()] = new class_246(voxels.method_1051(axis));
      }
   }

   @Overwrite
   public DoubleList method_1109(class_2351 axis) {
      return this.list[axis.ordinal()];
   }
}
