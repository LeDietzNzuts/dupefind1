package net.caffeinemc.mods.sodium.mixin.features.textures.mipmaps;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.caffeinemc.mods.sodium.client.util.NativeImageHelper;
import net.caffeinemc.mods.sodium.client.util.color.ColorSRGB;
import net.minecraft.class_1011;
import net.minecraft.class_7764;
import net.minecraft.class_5253.class_8045;
import org.lwjgl.system.MemoryUtil;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(class_7764.class)
public class SpriteContentsMixin {
   @Mutable
   @Shadow
   @Final
   private class_1011 field_40539;

   @WrapOperation(
      method = "<init>(Lnet/minecraft/class_2960;Lnet/minecraft/class_7771;Lnet/minecraft/class_1011;Lnet/minecraft/class_7368;)V",
      at = @At(value = "FIELD", target = "Lnet/minecraft/class_7764;field_40539:Lnet/minecraft/class_1011;", opcode = 181)
   )
   private void sodium$beforeGenerateMipLevels(class_7764 instance, class_1011 nativeImage, Operation<Void> original) {
      sodium$fillInTransparentPixelColors(nativeImage);
      original.call(new Object[]{instance, nativeImage});
   }

   @Unique
   private static void sodium$fillInTransparentPixelColors(class_1011 nativeImage) {
      long ppPixel = NativeImageHelper.getPointerRGBA(nativeImage);
      int pixelCount = nativeImage.method_4323() * nativeImage.method_4307();
      float r = 0.0F;
      float g = 0.0F;
      float b = 0.0F;
      float totalWeight = 0.0F;

      for (int pixelIndex = 0; pixelIndex < pixelCount; pixelIndex++) {
         long pPixel = ppPixel + pixelIndex * 4L;
         int color = MemoryUtil.memGetInt(pPixel);
         int alpha = class_8045.method_48342(color);
         if (alpha != 0) {
            float weight = alpha;
            r += ColorSRGB.srgbToLinear(class_8045.method_48345(color)) * weight;
            g += ColorSRGB.srgbToLinear(class_8045.method_48346(color)) * weight;
            b += ColorSRGB.srgbToLinear(class_8045.method_48347(color)) * weight;
            totalWeight += weight;
         }
      }

      if (totalWeight != 0.0F) {
         r /= totalWeight;
         g /= totalWeight;
         b /= totalWeight;
         int averageColor = ColorSRGB.linearToSrgb(r, g, b, 0);

         for (int pixelIndexx = 0; pixelIndexx < pixelCount; pixelIndexx++) {
            long pPixel = ppPixel + pixelIndexx * 4;
            int color = MemoryUtil.memGetInt(pPixel);
            int alpha = class_8045.method_48342(color);
            if (alpha == 0) {
               MemoryUtil.memPutInt(pPixel, averageColor);
            }
         }
      }
   }
}
