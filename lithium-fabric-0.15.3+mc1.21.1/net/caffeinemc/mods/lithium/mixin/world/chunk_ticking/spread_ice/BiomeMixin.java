package net.caffeinemc.mods.lithium.mixin.world.chunk_ticking.spread_ice;

import net.minecraft.class_1959;
import net.minecraft.class_2248;
import net.minecraft.class_2338;
import net.minecraft.class_2680;
import net.minecraft.class_3610;
import net.minecraft.class_3611;
import net.minecraft.class_3612;
import net.minecraft.class_4538;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(class_1959.class)
public class BiomeMixin {
   @Redirect(
      method = "method_8685(Lnet/minecraft/class_4538;Lnet/minecraft/class_2338;Z)Z",
      at = @At(value = "INVOKE", target = "Lnet/minecraft/class_4538;method_8316(Lnet/minecraft/class_2338;)Lnet/minecraft/class_3610;")
   )
   private class_3610 getNull(class_4538 instance, class_2338 blockPos) {
      return null;
   }

   @Redirect(
      method = "method_8685(Lnet/minecraft/class_4538;Lnet/minecraft/class_2338;Z)Z",
      at = @At(value = "INVOKE", target = "Lnet/minecraft/class_3610;method_15772()Lnet/minecraft/class_3611;")
   )
   private class_3611 skipFluidCheck(class_3610 fluidState) {
      return class_3612.field_15910;
   }

   @Redirect(
      method = "method_8685(Lnet/minecraft/class_4538;Lnet/minecraft/class_2338;Z)Z",
      at = @At(value = "INVOKE", target = "Lnet/minecraft/class_2680;method_26204()Lnet/minecraft/class_2248;")
   )
   private class_2248 fluidCheckAndGetBlock(class_2680 blockState) {
      return blockState.method_26227().method_15772() == class_3612.field_15910 ? blockState.method_26204() : null;
   }
}
