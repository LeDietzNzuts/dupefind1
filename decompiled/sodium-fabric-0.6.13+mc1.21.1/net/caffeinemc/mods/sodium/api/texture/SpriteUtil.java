package net.caffeinemc.mods.sodium.api.texture;

import net.caffeinemc.mods.sodium.api.internal.DependencyInjection;
import net.minecraft.class_1058;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.ApiStatus.Experimental;

@Experimental
public interface SpriteUtil {
   SpriteUtil INSTANCE = DependencyInjection.load(SpriteUtil.class, "net.caffeinemc.mods.sodium.client.render.texture.SpriteUtilImpl");

   void markSpriteActive(@NotNull class_1058 var1);

   boolean hasAnimation(@NotNull class_1058 var1);
}
