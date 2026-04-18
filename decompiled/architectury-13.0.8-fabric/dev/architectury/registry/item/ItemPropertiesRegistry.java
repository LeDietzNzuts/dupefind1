package dev.architectury.registry.item;

import dev.architectury.injectables.annotations.ExpectPlatform;
import dev.architectury.injectables.annotations.ExpectPlatform.Transformed;
import dev.architectury.registry.item.fabric.ItemPropertiesRegistryImpl;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.class_1935;
import net.minecraft.class_2960;
import net.minecraft.class_6395;

@Environment(EnvType.CLIENT)
public final class ItemPropertiesRegistry {
   private ItemPropertiesRegistry() {
   }

   @ExpectPlatform
   @Transformed
   public static class_6395 registerGeneric(class_2960 propertyId, class_6395 function) {
      return ItemPropertiesRegistryImpl.registerGeneric(propertyId, function);
   }

   @ExpectPlatform
   @Transformed
   public static class_6395 register(class_1935 item, class_2960 propertyId, class_6395 function) {
      return ItemPropertiesRegistryImpl.register(item, propertyId, function);
   }
}
