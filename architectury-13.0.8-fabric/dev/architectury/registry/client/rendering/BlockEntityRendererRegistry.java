package dev.architectury.registry.client.rendering;

import dev.architectury.injectables.annotations.ExpectPlatform;
import dev.architectury.injectables.annotations.ExpectPlatform.Transformed;
import dev.architectury.registry.client.rendering.fabric.BlockEntityRendererRegistryImpl;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.class_2586;
import net.minecraft.class_2591;
import net.minecraft.class_5614;

@Environment(EnvType.CLIENT)
public final class BlockEntityRendererRegistry {
   private BlockEntityRendererRegistry() {
   }

   @ExpectPlatform
   @Transformed
   public static <T extends class_2586> void register(class_2591<T> type, class_5614<? super T> provider) {
      BlockEntityRendererRegistryImpl.register(type, provider);
   }
}
