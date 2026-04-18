package dev.architectury.registry.level.entity.fabric;

import java.util.function.Supplier;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.minecraft.class_1299;
import net.minecraft.class_1309;
import net.minecraft.class_5132.class_5133;

public class EntityAttributeRegistryImpl {
   public static void register(Supplier<? extends class_1299<? extends class_1309>> type, Supplier<class_5133> attribute) {
      FabricDefaultAttributeRegistry.register(type.get(), attribute.get());
   }
}
