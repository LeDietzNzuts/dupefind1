package net.caffeinemc.mods.sodium.mixin.core.render.world;

import net.caffeinemc.mods.sodium.client.render.chunk.NonStoringBuilderPool;
import net.minecraft.class_4599;
import net.minecraft.class_8901;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(class_4599.class)
public class RenderBuffersMixin {
   @Redirect(method = "<init>(I)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/class_8901;method_54643(I)Lnet/minecraft/class_8901;"))
   private class_8901 sodium$doNotAllocateChunks(int i) {
      return new NonStoringBuilderPool();
   }
}
