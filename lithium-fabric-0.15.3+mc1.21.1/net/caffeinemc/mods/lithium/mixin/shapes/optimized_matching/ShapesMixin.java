package net.caffeinemc.mods.lithium.mixin.shapes.optimized_matching;

import net.caffeinemc.mods.lithium.common.shapes.VoxelShapeMatchesAnywhere;
import net.minecraft.class_247;
import net.minecraft.class_259;
import net.minecraft.class_265;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.At.Shift;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(class_259.class)
public class ShapesMixin {
   @Inject(
      method = "method_1074(Lnet/minecraft/class_265;Lnet/minecraft/class_265;Lnet/minecraft/class_247;)Z",
      at = @At(
         shift = Shift.BEFORE,
         value = "INVOKE",
         target = "Lnet/minecraft/class_265;method_1109(Lnet/minecraft/class_2350$class_2351;)Lit/unimi/dsi/fastutil/doubles/DoubleList;",
         ordinal = 0
      ),
      cancellable = true
   )
   private static void cuboidMatchesAnywhere(class_265 shapeA, class_265 shapeB, class_247 predicate, CallbackInfoReturnable<Boolean> cir) {
      VoxelShapeMatchesAnywhere.cuboidMatchesAnywhere(shapeA, shapeB, predicate, cir);
   }
}
