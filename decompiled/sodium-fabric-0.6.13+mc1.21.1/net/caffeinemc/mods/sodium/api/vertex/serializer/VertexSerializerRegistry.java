package net.caffeinemc.mods.sodium.api.vertex.serializer;

import net.caffeinemc.mods.sodium.api.internal.DependencyInjection;
import net.minecraft.class_293;

public interface VertexSerializerRegistry {
   VertexSerializerRegistry INSTANCE = DependencyInjection.load(
      VertexSerializerRegistry.class, "net.caffeinemc.mods.sodium.client.render.vertex.serializers.VertexSerializerRegistryImpl"
   );

   static VertexSerializerRegistry instance() {
      return INSTANCE;
   }

   VertexSerializer get(class_293 var1, class_293 var2);

   void registerSerializer(class_293 var1, class_293 var2, VertexSerializer var3);
}
