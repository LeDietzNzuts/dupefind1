package dev.architectury.registry.registries.fabric;

import com.google.common.base.Suppliers;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import dev.architectury.impl.RegistrySupplierImpl;
import dev.architectury.registry.registries.Registrar;
import dev.architectury.registry.registries.RegistrarBuilder;
import dev.architectury.registry.registries.RegistrarManager;
import dev.architectury.registry.registries.RegistrySupplier;
import dev.architectury.registry.registries.options.DefaultIdRegistrarOption;
import dev.architectury.registry.registries.options.RegistrarOption;
import dev.architectury.registry.registries.options.StandardRegistrarOption;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.Map.Entry;
import java.util.function.Consumer;
import java.util.function.Supplier;
import net.fabricmc.fabric.api.event.registry.FabricRegistryBuilder;
import net.fabricmc.fabric.api.event.registry.RegistryAttribute;
import net.fabricmc.fabric.api.event.registry.RegistryEntryAddedCallback;
import net.minecraft.class_2370;
import net.minecraft.class_2378;
import net.minecraft.class_2960;
import net.minecraft.class_5321;
import net.minecraft.class_6880;
import net.minecraft.class_7923;
import org.jetbrains.annotations.Nullable;

public class RegistrarManagerImpl {
   private static final Multimap<RegistrarManagerImpl.RegistryEntryId<?>, Consumer<?>> LISTENERS = HashMultimap.create();
   private static final Set<class_5321<?>> LISTENED_REGISTRIES = new HashSet<>();

   private static void listen(class_5321<?> resourceKey, class_2960 id, Consumer<?> listener) {
      if (LISTENED_REGISTRIES.add(resourceKey)) {
         class_2378<?> registry = Objects.requireNonNull(
            (class_2378<?>)class_7923.field_41167.method_10223(resourceKey.method_29177()), "Registry " + resourceKey + " not found!"
         );
         RegistryEntryAddedCallback.event(registry).register((RegistryEntryAddedCallback)(rawId, entryId, object) -> {
            RegistrarManagerImpl.RegistryEntryId<?> registryEntryId = new RegistrarManagerImpl.RegistryEntryId(resourceKey, entryId);

            for (Consumer<?> consumer : LISTENERS.get(registryEntryId)) {
               ((Consumer<Object>)consumer).accept(object);
            }

            LISTENERS.removeAll(registryEntryId);
         });
      }

      LISTENERS.put(new RegistrarManagerImpl.RegistryEntryId(resourceKey, id), listener);
   }

   public static RegistrarManager.RegistryProvider _get(String modId) {
      return new RegistrarManagerImpl.RegistryProviderImpl(modId);
   }

   public static class RegistrarBuilderWrapper<T> implements RegistrarBuilder<T> {
      private final String modId;
      private final Class<T> type;
      private final class_2960 registryId;
      private final List<Consumer<FabricRegistryBuilder<T, ? extends class_2370<T>>>> apply = new ArrayList<>();
      @Nullable
      private class_2960 defaultId;

      public RegistrarBuilderWrapper(String modId, Class<T> type, class_2960 registryId) {
         this.modId = modId;
         this.type = type;
         this.registryId = registryId;
      }

      @Override
      public Registrar<T> build() {
         FabricRegistryBuilder<T, ? extends class_2370<T>> builder = this.defaultId == null
            ? FabricRegistryBuilder.createSimple(this.type, this.registryId)
            : FabricRegistryBuilder.createDefaulted(this.type, this.registryId, this.defaultId);
         this.apply.forEach(consumer -> consumer.accept(builder));
         return RegistrarManager.get(this.modId).get(builder.buildAndRegister());
      }

      @Override
      public RegistrarBuilder<T> option(RegistrarOption option) {
         if (option == StandardRegistrarOption.SYNC_TO_CLIENTS) {
            this.apply.add(builder -> builder.attribute(RegistryAttribute.SYNCED));
         } else if (option instanceof DefaultIdRegistrarOption opt) {
            this.defaultId = opt.defaultId();
         }

         return this;
      }
   }

   public static class RegistrarImpl<T> implements Registrar<T> {
      private final String modId;
      private class_2378<T> delegate;

      public RegistrarImpl(String modId, class_2378<T> delegate) {
         this.modId = modId;
         this.delegate = delegate;
      }

      @Override
      public RegistrySupplier<T> delegate(class_2960 id) {
         final Supplier<T> value = Suppliers.memoize(() -> this.get(id));
         final RegistrarManagerImpl.RegistrarImpl<T> registrar = this;
         return new RegistrySupplierImpl<T>() {
            @Nullable
            class_6880<T> holder = null;

            @Nullable
            @Override
            public class_6880<T> getHolder() {
               return this.holder != null ? this.holder : (this.holder = registrar.getHolder(this.getId()));
            }

            @Override
            public RegistrarManager getRegistrarManager() {
               return RegistrarManager.get(RegistrarImpl.this.modId);
            }

            @Override
            public Registrar<T> getRegistrar() {
               return registrar;
            }

            @Override
            public class_2960 getRegistryId() {
               return RegistrarImpl.this.delegate.method_30517().method_29177();
            }

            @Override
            public class_2960 getId() {
               return id;
            }

            @Override
            public boolean isPresent() {
               return RegistrarImpl.this.contains(id);
            }

            @Override
            public T get() {
               return value.get();
            }

            @Override
            public int hashCode() {
               return com.google.common.base.Objects.hashCode(new Object[]{this.getRegistryId(), this.getId()});
            }

            @Override
            public boolean equals(Object obj) {
               if (this == obj) {
                  return true;
               } else {
                  return !(obj instanceof RegistrySupplier<?> other)
                     ? false
                     : other.getRegistryId().equals(this.getRegistryId()) && other.getId().equals(this.getId());
               }
            }

            @Override
            public String toString() {
               return this.getRegistryId().toString() + "@" + id.toString();
            }
         };
      }

      @Override
      public <E extends T> RegistrySupplier<E> register(class_2960 id, Supplier<E> supplier) {
         class_2378.method_10230(this.delegate, id, supplier.get());
         return this.delegate(id);
      }

      @Nullable
      @Override
      public class_2960 getId(T obj) {
         return this.delegate.method_10221(obj);
      }

      @Override
      public int getRawId(T obj) {
         return this.delegate.method_10206(obj);
      }

      @Override
      public Optional<class_5321<T>> getKey(T obj) {
         return this.delegate.method_29113(obj);
      }

      @Nullable
      @Override
      public T get(class_2960 id) {
         return (T)this.delegate.method_10223(id);
      }

      @Override
      public T byRawId(int rawId) {
         return (T)this.delegate.method_10200(rawId);
      }

      @Override
      public boolean contains(class_2960 id) {
         return this.delegate.method_10235().contains(id);
      }

      @Override
      public boolean containsValue(T obj) {
         return this.delegate.method_29113(obj).isPresent();
      }

      @Override
      public Set<class_2960> getIds() {
         return this.delegate.method_10235();
      }

      @Override
      public Set<Entry<class_5321<T>, T>> entrySet() {
         return this.delegate.method_29722();
      }

      @Override
      public class_5321<? extends class_2378<T>> key() {
         return this.delegate.method_30517();
      }

      @Nullable
      @Override
      public class_6880<T> getHolder(class_5321<T> key) {
         return (class_6880<T>)this.delegate.method_40264(key).orElse(null);
      }

      @Override
      public Iterator<T> iterator() {
         return this.delegate.iterator();
      }

      @Override
      public void listen(class_2960 id, Consumer<T> callback) {
         if (this.contains(id)) {
            callback.accept(this.get(id));
         } else {
            RegistrarManagerImpl.listen(this.key(), id, callback);
         }
      }
   }

   public static class RegistryEntryId<T> {
      private final class_5321<T> registryKey;
      private final class_2960 id;

      public RegistryEntryId(class_5321<T> registryKey, class_2960 id) {
         this.registryKey = registryKey;
         this.id = id;
      }

      @Override
      public boolean equals(Object o) {
         if (this == o) {
            return true;
         } else {
            return !(o instanceof RegistrarManagerImpl.RegistryEntryId<?> that)
               ? false
               : Objects.equals(this.registryKey, that.registryKey) && Objects.equals(this.id, that.id);
         }
      }

      @Override
      public int hashCode() {
         return Objects.hash(this.registryKey, this.id);
      }
   }

   public static class RegistryProviderImpl implements RegistrarManager.RegistryProvider {
      private final String modId;

      public RegistryProviderImpl(String modId) {
         this.modId = modId;
      }

      @Override
      public <T> Registrar<T> get(class_5321<class_2378<T>> key) {
         return new RegistrarManagerImpl.RegistrarImpl<>(
            this.modId, Objects.requireNonNull((class_2378<T>)class_7923.field_41167.method_10223(key.method_29177()), "Registry " + key + " not found!")
         );
      }

      @Override
      public <T> Registrar<T> get(class_2378<T> registry) {
         return new RegistrarManagerImpl.RegistrarImpl<>(this.modId, registry);
      }

      @Override
      public <T> void forRegistry(class_5321<class_2378<T>> key, Consumer<Registrar<T>> consumer) {
         consumer.accept(this.get(key));
      }

      @Override
      public <T> RegistrarBuilder<T> builder(Class<T> type, class_2960 registryId) {
         return new RegistrarManagerImpl.RegistrarBuilderWrapper<>(this.modId, type, registryId);
      }
   }
}
