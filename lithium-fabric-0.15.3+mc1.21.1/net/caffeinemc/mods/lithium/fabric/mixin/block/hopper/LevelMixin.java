package net.caffeinemc.mods.lithium.fabric.mixin.block.hopper;

import com.llamalad7.mixinextras.sugar.Local;
import net.caffeinemc.mods.lithium.common.hopper.HopperHelper;
import net.minecraft.class_1937;
import net.minecraft.class_2248;
import net.minecraft.class_2338;
import net.minecraft.class_2680;
import net.minecraft.class_2818;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(class_1937.class)
public class LevelMixin {
   @Inject(
      method = "method_30092(Lnet/minecraft/class_2338;Lnet/minecraft/class_2680;II)Z",
      at = @At(
         value = "INVOKE",
         target = "Lnet/minecraft/class_1937;method_19282(Lnet/minecraft/class_2338;Lnet/minecraft/class_2680;Lnet/minecraft/class_2680;)V"
      )
   )
   private void updateHopperOnUpdateSuppression(
      class_2338 pos,
      class_2680 state,
      int flags,
      int maxUpdateDepth,
      CallbackInfoReturnable<Boolean> cir,
      @Local class_2818 worldChunk,
      @Local class_2248 block,
      @Local(ordinal = 1) class_2680 blockState,
      @Local(ordinal = 2) class_2680 blockState2
   ) {
      HopperHelper.updateHopperOnUpdateSuppression((class_1937)this, pos, flags, worldChunk, blockState != blockState2);
   }
}
