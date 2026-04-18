package net.caffeinemc.mods.sodium.client.util;

import java.util.Locale;
import net.caffeinemc.mods.sodium.mixin.features.textures.NativeImageAccessor;
import net.minecraft.class_1011;
import net.minecraft.class_1011.class_1012;

public class NativeImageHelper {
   public static long getPointerRGBA(class_1011 nativeImage) {
      if (nativeImage.method_4318() != class_1012.field_4997) {
         throw new IllegalArgumentException(
            String.format(Locale.ROOT, "Tried to get pointer to RGBA pixel data on NativeImage of wrong format; have %s", nativeImage.method_4318())
         );
      } else {
         return ((NativeImageAccessor)nativeImage).getField_4988();
      }
   }
}
