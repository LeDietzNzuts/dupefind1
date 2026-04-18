package net.caffeinemc.mods.lithium.mixin.math.fast_blockpos;

import net.minecraft.class_2350;
import net.minecraft.class_2382;
import net.minecraft.class_2350.class_2351;
import net.minecraft.class_2350.class_2352;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(class_2350.class)
public class DirectionMixin {
   private int offsetX;
   private int offsetY;
   private int offsetZ;

   @Inject(
      method = "<init>(Ljava/lang/String;IIIILjava/lang/String;Lnet/minecraft/class_2350$class_2352;Lnet/minecraft/class_2350$class_2351;Lnet/minecraft/class_2382;)V",
      at = @At("RETURN")
   )
   private void reinit(
      String enumName,
      int ordinal,
      int id,
      int idOpposite,
      int idHorizontal,
      String name,
      class_2352 direction,
      class_2351 axis,
      class_2382 vector,
      CallbackInfo ci
   ) {
      this.offsetX = vector.method_10263();
      this.offsetY = vector.method_10264();
      this.offsetZ = vector.method_10260();
   }

   @Overwrite
   public int method_10148() {
      return this.offsetX;
   }

   @Overwrite
   public int method_10164() {
      return this.offsetY;
   }

   @Overwrite
   public int method_10165() {
      return this.offsetZ;
   }
}
