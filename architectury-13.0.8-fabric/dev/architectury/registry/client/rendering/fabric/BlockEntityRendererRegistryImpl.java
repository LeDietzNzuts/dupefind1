package dev.architectury.registry.client.rendering.fabric;

import net.fabricmc.fabric.api.client.rendering.v1.BlockEntityRendererRegistry;
import net.minecraft.class_2586;
import net.minecraft.class_2591;
import net.minecraft.class_5614;

public class BlockEntityRendererRegistryImpl {
   public static <T extends class_2586> void register(class_2591<T> type, class_5614<? super T> provider) {
      BlockEntityRendererRegistry.register(type, provider);
   }
}
