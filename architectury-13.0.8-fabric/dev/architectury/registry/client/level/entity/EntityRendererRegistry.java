package dev.architectury.registry.client.level.entity;

import dev.architectury.injectables.annotations.ExpectPlatform;
import dev.architectury.injectables.annotations.ExpectPlatform.Transformed;
import dev.architectury.registry.client.level.entity.fabric.EntityRendererRegistryImpl;
import java.util.function.Supplier;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.class_1297;
import net.minecraft.class_1299;
import net.minecraft.class_5617;

@Environment(EnvType.CLIENT)
public final class EntityRendererRegistry {
   private EntityRendererRegistry() {
   }

   @ExpectPlatform
   @Transformed
   public static <T extends class_1297> void register(Supplier<? extends class_1299<? extends T>> type, class_5617<T> provider) {
      EntityRendererRegistryImpl.register(type, provider);
   }
}
