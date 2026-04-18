package dev.architectury.registry.item.fabric;

import net.fabricmc.fabric.api.object.builder.v1.client.model.FabricModelPredicateProviderRegistry;
import net.minecraft.class_1935;
import net.minecraft.class_2960;
import net.minecraft.class_6395;

public class ItemPropertiesRegistryImpl {
   public static class_6395 registerGeneric(class_2960 propertyId, class_6395 function) {
      FabricModelPredicateProviderRegistry.register(propertyId, function);
      return function;
   }

   public static class_6395 register(class_1935 item, class_2960 propertyId, class_6395 function) {
      FabricModelPredicateProviderRegistry.register(item.method_8389(), propertyId, function);
      return function;
   }
}
