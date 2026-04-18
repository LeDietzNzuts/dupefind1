package net.caffeinemc.mods.lithium.mixin.chunk.no_locking;

import net.minecraft.class_2680;
import net.minecraft.class_2826;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(class_2826.class)
public abstract class LevelChunkSectionMixin {
   @Shadow
   public abstract class_2680 method_12256(int var1, int var2, int var3, class_2680 var4, boolean var5);

   @Redirect(
      method = "method_16675(IIILnet/minecraft/class_2680;)Lnet/minecraft/class_2680;",
      at = @At(value = "INVOKE", target = "Lnet/minecraft/class_2826;method_12256(IIILnet/minecraft/class_2680;Z)Lnet/minecraft/class_2680;")
   )
   private class_2680 setBlockStateNoLocking(class_2826 chunkSection, int x, int y, int z, class_2680 state, boolean lock) {
      return this.method_12256(x, y, z, state, false);
   }
}
