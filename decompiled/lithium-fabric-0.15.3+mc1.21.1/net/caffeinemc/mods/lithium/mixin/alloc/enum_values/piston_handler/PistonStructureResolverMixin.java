package net.caffeinemc.mods.lithium.mixin.alloc.enum_values.piston_handler;

import net.caffeinemc.mods.lithium.common.util.DirectionConstants;
import net.minecraft.class_2350;
import net.minecraft.class_2674;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(class_2674.class)
public class PistonStructureResolverMixin {
   @Redirect(
      method = "method_11538(Lnet/minecraft/class_2338;)Z",
      at = @At(value = "INVOKE", target = "Lnet/minecraft/class_2350;values()[Lnet/minecraft/class_2350;")
   )
   private class_2350[] removeAllocation() {
      return DirectionConstants.ALL;
   }
}
