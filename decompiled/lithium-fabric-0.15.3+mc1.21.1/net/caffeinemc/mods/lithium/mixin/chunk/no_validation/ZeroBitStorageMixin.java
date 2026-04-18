package net.caffeinemc.mods.lithium.mixin.chunk.no_validation;

import net.minecraft.class_6502;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(class_6502.class)
public class ZeroBitStorageMixin {
   @Redirect(
      method = {"method_15214(II)I", "method_15210(II)V", "method_15211(I)I"},
      at = @At(value = "INVOKE", target = "Lorg/apache/commons/lang3/Validate;inclusiveBetween(JJJ)V", remap = false)
   )
   public void skipValidation(long start, long end, long value) {
   }
}
