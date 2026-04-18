package dev.architectury.registry.client.particle;

import dev.architectury.registry.registries.RegistrySupplier;
import java.util.List;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.class_1058;
import net.minecraft.class_1059;
import net.minecraft.class_2394;
import net.minecraft.class_2396;
import net.minecraft.class_4002;
import net.minecraft.class_707;

@Environment(EnvType.CLIENT)
public final class ParticleProviderRegistry {
   public static <T extends class_2394> void register(RegistrySupplier<? extends class_2396<T>> supplier, class_707<T> provider) {
      supplier.listen(it -> register((class_2396<T>)it, provider));
   }

   public static <T extends class_2394> void register(
      RegistrySupplier<? extends class_2396<T>> supplier, ParticleProviderRegistry.DeferredParticleProvider<T> provider
   ) {
      supplier.listen(it -> register((class_2396<T>)it, provider));
   }

   public static <T extends class_2394> void register(class_2396<T> type, class_707<T> provider) {
   }

   public static <T extends class_2394> void register(class_2396<T> type, ParticleProviderRegistry.DeferredParticleProvider<T> provider) {
   }

   @FunctionalInterface
   public interface DeferredParticleProvider<T extends class_2394> {
      class_707<T> create(ParticleProviderRegistry.ExtendedSpriteSet var1);
   }

   public interface ExtendedSpriteSet extends class_4002 {
      class_1059 getAtlas();

      List<class_1058> getSprites();
   }
}
