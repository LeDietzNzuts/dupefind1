package dev.architectury.registry.client.level.entity;

import dev.architectury.injectables.annotations.ExpectPlatform;
import dev.architectury.injectables.annotations.ExpectPlatform.Transformed;
import dev.architectury.registry.client.level.entity.fabric.EntityModelLayerRegistryImpl;
import java.util.function.Supplier;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.class_5601;
import net.minecraft.class_5607;

@Environment(EnvType.CLIENT)
public class EntityModelLayerRegistry {
   @ExpectPlatform
   @Transformed
   public static void register(class_5601 location, Supplier<class_5607> definition) {
      EntityModelLayerRegistryImpl.register(location, definition);
   }
}
