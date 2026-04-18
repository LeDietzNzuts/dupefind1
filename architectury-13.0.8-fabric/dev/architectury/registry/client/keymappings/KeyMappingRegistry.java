package dev.architectury.registry.client.keymappings;

import dev.architectury.injectables.annotations.ExpectPlatform;
import dev.architectury.injectables.annotations.ExpectPlatform.Transformed;
import dev.architectury.registry.client.keymappings.fabric.KeyMappingRegistryImpl;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.class_304;

@Environment(EnvType.CLIENT)
public final class KeyMappingRegistry {
   private KeyMappingRegistry() {
   }

   @ExpectPlatform
   @Transformed
   public static void register(class_304 mapping) {
      KeyMappingRegistryImpl.register(mapping);
   }
}
