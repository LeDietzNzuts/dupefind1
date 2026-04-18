package dev.architectury.registry.client.level.entity.fabric;

import java.util.function.Supplier;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.class_1297;
import net.minecraft.class_1299;
import net.minecraft.class_5617;

public class EntityRendererRegistryImpl {
   public static <T extends class_1297> void register(Supplier<? extends class_1299<? extends T>> type, class_5617<T> provider) {
      EntityRendererRegistry.register(type.get(), provider);
   }
}
