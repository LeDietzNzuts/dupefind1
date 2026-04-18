package net.caffeinemc.mods.sodium.mixin.core.render;

import net.caffeinemc.mods.sodium.client.render.texture.SpriteFinderCache;
import net.minecraft.class_1059;
import net.minecraft.class_2960;
import net.minecraft.class_7766.class_7767;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(class_1059.class)
public class TextureAtlasMixin {
   @Shadow
   @Final
   private class_2960 field_21749;

   @Inject(method = "method_45848(Lnet/minecraft/class_7766$class_7767;)V", at = @At("RETURN"))
   private void sodium$deleteSpriteFinder(class_7767 preparations, CallbackInfo ci) {
      if (this.field_21749.equals(class_1059.field_5275)) {
         SpriteFinderCache.resetSpriteFinder();
      }
   }
}
