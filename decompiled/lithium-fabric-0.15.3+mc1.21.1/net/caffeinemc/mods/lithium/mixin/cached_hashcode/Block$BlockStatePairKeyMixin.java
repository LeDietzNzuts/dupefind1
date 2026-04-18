package net.caffeinemc.mods.lithium.mixin.cached_hashcode;

import net.minecraft.class_2350;
import net.minecraft.class_2680;
import net.minecraft.class_2248.class_2249;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(class_2249.class)
public class Block$BlockStatePairKeyMixin {
   @Shadow
   @Final
   private class_2680 field_10652;
   @Shadow
   @Final
   private class_2680 field_10654;
   @Shadow
   @Final
   private class_2350 field_10653;
   private int hash;

   @Inject(method = "<init>(Lnet/minecraft/class_2680;Lnet/minecraft/class_2680;Lnet/minecraft/class_2350;)V", at = @At("RETURN"))
   private void generateHash(class_2680 blockState_1, class_2680 blockState_2, class_2350 direction_1, CallbackInfo ci) {
      int hash = this.field_10652.hashCode();
      hash = 31 * hash + this.field_10654.hashCode();
      hash = 31 * hash + this.field_10653.hashCode();
      this.hash = hash;
   }

   @Overwrite(remap = false)
   @Override
   public int hashCode() {
      return this.hash;
   }
}
