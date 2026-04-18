package net.caffeinemc.mods.lithium.mixin.chunk.no_locking;

import net.minecraft.class_2841;
import net.minecraft.class_5798;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(class_2841.class)
public class PalettedContainerMixin {
   @Shadow
   @Final
   @Mutable
   private class_5798 field_36300;

   @Inject(
      method = {
            "<init>(Lnet/minecraft/class_2359;Ljava/lang/Object;Lnet/minecraft/class_2841$class_6563;)V",
            "<init>(Lnet/minecraft/class_2359;Lnet/minecraft/class_2841$class_6563;Lnet/minecraft/class_2841$class_6560;Lnet/minecraft/class_6490;Ljava/util/List;)V",
            "<init>(Lnet/minecraft/class_2359;Lnet/minecraft/class_2841$class_6563;Lnet/minecraft/class_2841$class_6561;)V"
      },
      at = @At("TAIL")
   )
   public void removeLockHelper(CallbackInfo ci) {
      this.field_36300 = null;
   }

   @Overwrite
   public void method_12334() {
   }

   @Overwrite
   public void method_12335() {
   }
}
