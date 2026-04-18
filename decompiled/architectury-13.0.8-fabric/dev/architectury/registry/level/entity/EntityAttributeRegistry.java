package dev.architectury.registry.level.entity;

import dev.architectury.injectables.annotations.ExpectPlatform;
import dev.architectury.injectables.annotations.ExpectPlatform.Transformed;
import dev.architectury.registry.level.entity.fabric.EntityAttributeRegistryImpl;
import java.util.function.Supplier;
import net.minecraft.class_1299;
import net.minecraft.class_1309;
import net.minecraft.class_5132.class_5133;

public final class EntityAttributeRegistry {
   private EntityAttributeRegistry() {
   }

   @ExpectPlatform
   @Transformed
   public static void register(Supplier<? extends class_1299<? extends class_1309>> type, Supplier<class_5133> attribute) {
      EntityAttributeRegistryImpl.register(type, attribute);
   }
}
