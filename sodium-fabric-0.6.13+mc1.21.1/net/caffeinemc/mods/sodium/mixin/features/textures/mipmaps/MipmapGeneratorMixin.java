package net.caffeinemc.mods.sodium.mixin.features.textures.mipmaps;

import net.caffeinemc.mods.sodium.client.util.color.ColorSRGB;
import net.minecraft.class_4725;
import net.minecraft.class_5253.class_8045;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Unique;

@Mixin(class_4725.class)
public class MipmapGeneratorMixin {
   @Overwrite
   private static int method_24101(int one, int two, int three, int four, boolean checkAlpha) {
      return weightedAverageColor(weightedAverageColor(one, two), weightedAverageColor(three, four));
   }

   @Unique
   private static int weightedAverageColor(int one, int two) {
      int alphaOne = class_8045.method_48342(one);
      int alphaTwo = class_8045.method_48342(two);
      if (alphaOne == alphaTwo) {
         return averageRgb(one, two, alphaOne);
      } else if (alphaOne == 0) {
         return two & 16777215 | alphaTwo >> 2 << 24;
      } else if (alphaTwo == 0) {
         return one & 16777215 | alphaOne >> 2 << 24;
      } else {
         float scale = 1.0F / (alphaOne + alphaTwo);
         float relativeWeightOne = alphaOne * scale;
         float relativeWeightTwo = alphaTwo * scale;
         float oneR = ColorSRGB.srgbToLinear(class_8045.method_48345(one)) * relativeWeightOne;
         float oneG = ColorSRGB.srgbToLinear(class_8045.method_48346(one)) * relativeWeightOne;
         float oneB = ColorSRGB.srgbToLinear(class_8045.method_48347(one)) * relativeWeightOne;
         float twoR = ColorSRGB.srgbToLinear(class_8045.method_48345(two)) * relativeWeightTwo;
         float twoG = ColorSRGB.srgbToLinear(class_8045.method_48346(two)) * relativeWeightTwo;
         float twoB = ColorSRGB.srgbToLinear(class_8045.method_48347(two)) * relativeWeightTwo;
         float linearR = oneR + twoR;
         float linearG = oneG + twoG;
         float linearB = oneB + twoB;
         int averageAlpha = alphaOne + alphaTwo >> 1;
         return ColorSRGB.linearToSrgb(linearR, linearG, linearB, averageAlpha);
      }
   }

   @Unique
   private static int averageRgb(int a, int b, int alpha) {
      float ar = ColorSRGB.srgbToLinear(class_8045.method_48345(a));
      float ag = ColorSRGB.srgbToLinear(class_8045.method_48346(a));
      float ab = ColorSRGB.srgbToLinear(class_8045.method_48347(a));
      float br = ColorSRGB.srgbToLinear(class_8045.method_48345(b));
      float bg = ColorSRGB.srgbToLinear(class_8045.method_48346(b));
      float bb = ColorSRGB.srgbToLinear(class_8045.method_48347(b));
      return ColorSRGB.linearToSrgb((ar + br) * 0.5F, (ag + bg) * 0.5F, (ab + bb) * 0.5F, alpha);
   }
}
