package net.caffeinemc.mods.lithium.mixin.block.hopper;

import net.caffeinemc.mods.lithium.common.block.entity.ShapeUpdateHandlingBlockBehaviour;
import net.minecraft.class_1936;
import net.minecraft.class_2338;
import net.minecraft.class_2350;
import net.minecraft.class_2680;
import net.minecraft.class_4970;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(class_4970.class)
public class BlockBehaviourMixin implements ShapeUpdateHandlingBlockBehaviour {
   @Inject(
      method = "method_9559(Lnet/minecraft/class_2680;Lnet/minecraft/class_2350;Lnet/minecraft/class_2680;Lnet/minecraft/class_1936;Lnet/minecraft/class_2338;Lnet/minecraft/class_2338;)Lnet/minecraft/class_2680;",
      at = @At("HEAD")
   )
   private void notifyOnShapeUpdate(
      class_2680 myBlockState,
      class_2350 direction,
      class_2680 newState,
      class_1936 world,
      class_2338 myPos,
      class_2338 posFrom,
      CallbackInfoReturnable<class_2680> cir
   ) {
      this.lithium$handleShapeUpdate(world, myBlockState, myPos, posFrom, newState);
   }
}
