package net.caffeinemc.mods.sodium.client.render.texture;

import java.util.Objects;
import net.minecraft.class_1058;
import org.jetbrains.annotations.NotNull;

public class SpriteUtilImpl implements net.caffeinemc.mods.sodium.api.texture.SpriteUtil {
   @Override
   public void markSpriteActive(@NotNull class_1058 sprite) {
      Objects.requireNonNull(sprite);
      ((SpriteContentsExtension)sprite.method_45851()).sodium$setActive(true);
   }

   @Override
   public boolean hasAnimation(@NotNull class_1058 sprite) {
      Objects.requireNonNull(sprite);
      return ((SpriteContentsExtension)sprite.method_45851()).sodium$hasAnimation();
   }
}
