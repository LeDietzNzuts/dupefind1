package dev.architectury.registry.client.gui;

import dev.architectury.injectables.annotations.ExpectPlatform;
import dev.architectury.injectables.annotations.ExpectPlatform.Transformed;
import dev.architectury.registry.client.gui.fabric.ClientTooltipComponentRegistryImpl;
import java.util.function.Function;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.class_5632;
import net.minecraft.class_5684;

@Environment(EnvType.CLIENT)
public final class ClientTooltipComponentRegistry {
   private ClientTooltipComponentRegistry() {
   }

   @ExpectPlatform
   @Transformed
   public static <T extends class_5632> void register(Class<T> clazz, Function<? super T, ? extends class_5684> factory) {
      ClientTooltipComponentRegistryImpl.register(clazz, factory);
   }
}
