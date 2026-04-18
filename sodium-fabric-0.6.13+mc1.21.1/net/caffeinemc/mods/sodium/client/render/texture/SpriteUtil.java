package net.caffeinemc.mods.sodium.client.render.texture;

import net.minecraft.class_1058;
import org.jetbrains.annotations.Nullable;

@Deprecated(forRemoval = true)
public class SpriteUtil {
   @Deprecated(forRemoval = true)
   public static void markSpriteActive(@Nullable class_1058 sprite) {
      if (sprite != null) {
         net.caffeinemc.mods.sodium.api.texture.SpriteUtil.INSTANCE.markSpriteActive(sprite);
      }
   }

   @Deprecated(forRemoval = true)
   public static boolean hasAnimation(@Nullable class_1058 sprite) {
      return sprite != null ? net.caffeinemc.mods.sodium.api.texture.SpriteUtil.INSTANCE.hasAnimation(sprite) : false;
   }
}
