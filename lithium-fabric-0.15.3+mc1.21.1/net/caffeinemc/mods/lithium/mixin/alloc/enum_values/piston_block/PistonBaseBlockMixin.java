package net.caffeinemc.mods.lithium.mixin.alloc.enum_values.piston_block;

import net.caffeinemc.mods.lithium.common.util.DirectionConstants;
import net.minecraft.class_2350;
import net.minecraft.class_2665;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(class_2665.class)
public class PistonBaseBlockMixin {
   @Redirect(
      method = "method_11482(Lnet/minecraft/class_8235;Lnet/minecraft/class_2338;Lnet/minecraft/class_2350;)Z",
      at = @At(value = "INVOKE", target = "Lnet/minecraft/class_2350;values()[Lnet/minecraft/class_2350;")
   )
   private class_2350[] removeAllocation() {
      return DirectionConstants.ALL;
   }
}
