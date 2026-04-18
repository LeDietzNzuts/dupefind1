package dev.architectury.registry.client.gui.fabric;

import java.util.function.Function;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendering.v1.TooltipComponentCallback;
import net.minecraft.class_5632;
import net.minecraft.class_5684;
import org.jetbrains.annotations.ApiStatus.Internal;

@Environment(EnvType.CLIENT)
@Internal
public class ClientTooltipComponentRegistryImpl {
   public static <T extends class_5632> void register(Class<T> clazz, Function<? super T, ? extends class_5684> factory) {
      TooltipComponentCallback.EVENT
         .register((TooltipComponentCallback)tooltipComponent -> clazz.isInstance(tooltipComponent) ? factory.apply(clazz.cast(tooltipComponent)) : null);
   }
}
