package dev.architectury.registry.registries;

import dev.architectury.injectables.annotations.ExpectPlatform;
import dev.architectury.injectables.annotations.ExpectPlatform.Transformed;
import dev.architectury.registry.registries.fabric.RegistrarManagerImpl;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Consumer;
import net.minecraft.class_2378;
import net.minecraft.class_2960;
import net.minecraft.class_5321;
import net.minecraft.class_7923;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.ApiStatus.Internal;

public final class RegistrarManager {
   private static final Map<String, RegistrarManager> MANAGER = new ConcurrentHashMap<>();
   private final RegistrarManager.RegistryProvider provider;
   private final String modId;

   public static RegistrarManager get(String modId) {
      return MANAGER.computeIfAbsent(modId, RegistrarManager::new);
   }

   private RegistrarManager(String modId) {
      this.provider = _get(modId);
      this.modId = modId;
   }

   public <T> Registrar<T> get(class_5321<class_2378<T>> key) {
      return this.provider.get(key);
   }

   @Deprecated
   public <T> Registrar<T> get(class_2378<T> registry) {
      return this.provider.get(registry);
   }

   public <T> void forRegistry(class_5321<class_2378<T>> key, Consumer<Registrar<T>> callback) {
      this.provider.forRegistry(key, callback);
   }

   @SafeVarargs
   public final <T> RegistrarBuilder<T> builder(class_2960 registryId, T... typeGetter) {
      if (typeGetter.length != 0) {
         throw new IllegalStateException("array must be empty!");
      } else {
         return this.provider.builder((Class<T>)typeGetter.getClass().getComponentType(), registryId);
      }
   }

   @Nullable
   public static <T> class_2960 getId(T object, @Nullable class_5321<class_2378<T>> fallback) {
      return fallback == null ? null : getId(object, (class_2378<T>)class_7923.field_41167.method_10223(fallback.method_29177()));
   }

   @Deprecated
   @Nullable
   public static <T> class_2960 getId(T object, @Nullable class_2378<T> fallback) {
      return fallback == null ? null : fallback.method_10221(object);
   }

   @ExpectPlatform
   @Transformed
   private static RegistrarManager.RegistryProvider _get(String modId) {
      return RegistrarManagerImpl._get(modId);
   }

   public String getModId() {
      return this.modId;
   }

   @Internal
   public interface RegistryProvider {
      <T> Registrar<T> get(class_5321<class_2378<T>> var1);

      @Deprecated
      <T> Registrar<T> get(class_2378<T> var1);

      <T> void forRegistry(class_5321<class_2378<T>> var1, Consumer<Registrar<T>> var2);

      <T> RegistrarBuilder<T> builder(Class<T> var1, class_2960 var2);
   }
}
