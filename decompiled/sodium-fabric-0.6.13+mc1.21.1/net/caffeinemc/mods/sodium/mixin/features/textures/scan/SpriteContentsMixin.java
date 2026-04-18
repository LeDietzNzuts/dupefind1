package net.caffeinemc.mods.sodium.mixin.features.textures.scan;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.caffeinemc.mods.sodium.api.util.ColorABGR;
import net.caffeinemc.mods.sodium.client.render.chunk.compile.pipeline.SpriteContentsExtension;
import net.caffeinemc.mods.sodium.client.util.NativeImageHelper;
import net.minecraft.class_1011;
import net.minecraft.class_7764;
import org.lwjgl.system.MemoryUtil;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(class_7764.class)
public class SpriteContentsMixin implements SpriteContentsExtension {
   @Mutable
   @Shadow
   @Final
   private class_1011 field_40539;
   @Unique
   public boolean sodium$hasTransparentPixels = false;
   @Unique
   public boolean sodium$hasTranslucentPixels = false;

   @WrapOperation(
      method = "<init>(Lnet/minecraft/class_2960;Lnet/minecraft/class_7771;Lnet/minecraft/class_1011;Lnet/minecraft/class_7368;)V",
      at = @At(value = "FIELD", target = "Lnet/minecraft/class_7764;field_40539:Lnet/minecraft/class_1011;", opcode = 181)
   )
   private void sodium$beforeGenerateMipLevels(class_7764 instance, class_1011 nativeImage, Operation<Void> original) {
      this.scanSpriteContents(nativeImage);
      original.call(new Object[]{instance, nativeImage});
   }

   @Unique
   private void scanSpriteContents(class_1011 nativeImage) {
      long ppPixel = NativeImageHelper.getPointerRGBA(nativeImage);
      int pixelCount = nativeImage.method_4323() * nativeImage.method_4307();

      for (int pixelIndex = 0; pixelIndex < pixelCount; pixelIndex++) {
         int color = MemoryUtil.memGetInt(ppPixel + pixelIndex * 4L);
         int alpha = ColorABGR.unpackAlpha(color);
         if (alpha <= 25) {
            this.sodium$hasTransparentPixels = true;
         } else if (alpha < 255) {
            this.sodium$hasTranslucentPixels = true;
         }
      }

      this.sodium$hasTransparentPixels = this.sodium$hasTransparentPixels | this.sodium$hasTranslucentPixels;
   }

   @Override
   public boolean sodium$hasTransparentPixels() {
      return this.sodium$hasTransparentPixels;
   }

   @Override
   public boolean sodium$hasTranslucentPixels() {
      return this.sodium$hasTranslucentPixels;
   }
}
