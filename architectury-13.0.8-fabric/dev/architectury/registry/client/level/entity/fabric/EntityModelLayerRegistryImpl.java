package dev.architectury.registry.client.level.entity.fabric;

import java.util.function.Supplier;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.minecraft.class_5601;
import net.minecraft.class_5607;

public class EntityModelLayerRegistryImpl {
   public static void register(class_5601 location, Supplier<class_5607> definition) {
      EntityModelLayerRegistry.registerModelLayer(location, definition::get);
   }
}
