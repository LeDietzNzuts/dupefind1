package org.embeddedt.modernfix.entity;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.collect.Iterators;
import com.google.common.collect.Maps;
import java.util.AbstractCollection;
import java.util.AbstractSet;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import net.minecraft.class_1299;
import net.minecraft.class_5617;
import net.minecraft.class_5619;
import net.minecraft.class_7923;
import net.minecraft.class_897;
import net.minecraft.class_5617.class_5618;
import org.embeddedt.modernfix.ModernFix;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class EntityRendererMap implements Map<class_1299<?>, class_897<?>> {
   private final Map<class_1299<?>, class_5617<?>> rendererProviders;
   private final LoadingCache<class_1299<?>, Optional<class_897<?>>> rendererMap;
   private final class_5618 context;

   public EntityRendererMap(Map<class_1299<?>, class_5617<?>> rendererProviders, class_5618 context) {
      this.rendererProviders = rendererProviders;
      this.context = context;
      this.rendererMap = CacheBuilder.newBuilder().build(new EntityRendererMap.RenderConstructor());
   }

   @Override
   public int size() {
      return this.rendererProviders.size();
   }

   @Override
   public boolean isEmpty() {
      return this.rendererProviders.isEmpty();
   }

   @Override
   public boolean containsKey(Object o) {
      return this.rendererProviders.containsKey(o);
   }

   @Override
   public boolean containsValue(Object o) {
      return false;
   }

   public class_897<?> get(Object o) {
      try {
         Optional<class_897<?>> renderer = (Optional<class_897<?>>)this.rendererMap.get((class_1299)o);
         return renderer.orElse(null);
      } catch (IllegalStateException var3) {
         return null;
      } catch (ExecutionException var4) {
         throw new RuntimeException(var4);
      }
   }

   @Nullable
   public class_897<?> put(class_1299<?> entityType, class_897<?> entityRenderer) {
      Optional<class_897<?>> old = (Optional<class_897<?>>)this.rendererMap.getIfPresent(entityType);
      this.rendererMap.put(entityType, Optional.ofNullable(entityRenderer));
      return old != null ? old.orElse(null) : null;
   }

   public class_897<?> remove(Object o) {
      Optional<class_897<?>> old = (Optional<class_897<?>>)this.rendererMap.getIfPresent(o);
      this.rendererMap.invalidate(o);
      return old != null ? old.orElse(null) : null;
   }

   @Override
   public void putAll(@NotNull Map<? extends class_1299<?>, ? extends class_897<?>> map) {
      this.rendererMap.putAll(Maps.transformValues(map, Optional::ofNullable));
   }

   @Override
   public void clear() {
      this.rendererMap.invalidateAll();
   }

   @NotNull
   @Override
   public Set<class_1299<?>> keySet() {
      return this.rendererProviders.keySet();
   }

   @NotNull
   @Override
   public Collection<class_897<?>> values() {
      return new AbstractCollection<class_897<?>>() {
         @Override
         public Iterator<class_897<?>> iterator() {
            return Iterators.transform(
               Iterators.unmodifiableIterator(EntityRendererMap.this.rendererProviders.keySet().iterator()), EntityRendererMap.this::get
            );
         }

         @Override
         public int size() {
            return EntityRendererMap.this.rendererProviders.size();
         }
      };
   }

   @NotNull
   @Override
   public Set<Entry<class_1299<?>, class_897<?>>> entrySet() {
      return new AbstractSet<java.util.Map.Entry<class_1299<?>, class_897<?>>>() {
         @Override
         public Iterator<java.util.Map.Entry<class_1299<?>, class_897<?>>> iterator() {
            return Iterators.transform(
               Iterators.unmodifiableIterator(EntityRendererMap.this.rendererProviders.keySet().iterator()), x$0 -> EntityRendererMap.this.new Entry(x$0)
            );
         }

         @Override
         public boolean contains(Object o) {
            return !(o instanceof java.util.Map.Entry<?, ?> e)
               ? false
               : EntityRendererMap.this.rendererProviders.containsKey(e.getKey()) && Objects.equals(EntityRendererMap.this.get(e.getKey()), e.getValue());
         }

         @Override
         public int size() {
            return EntityRendererMap.this.rendererProviders.size();
         }
      };
   }

   private class Entry implements java.util.Map.Entry<class_1299<?>, class_897<?>> {
      private final class_1299<?> key;

      private Entry(class_1299<?> key) {
         this.key = key;
      }

      public class_1299<?> getKey() {
         return this.key;
      }

      public class_897<?> getValue() {
         return EntityRendererMap.this.get(this.key);
      }

      public class_897<?> setValue(class_897<?> value) {
         return EntityRendererMap.this.put(this.key, value);
      }
   }

   class RenderConstructor extends CacheLoader<class_1299<?>, Optional<class_897<?>>> {
      public Optional<class_897<?>> load(class_1299<?> key) throws Exception {
         class_5617<?> provider = EntityRendererMap.this.rendererProviders.get(key);
         if (provider == null) {
            return Optional.empty();
         } else {
            synchronized (class_5619.class) {
               class_897<?> renderer;
               try {
                  renderer = provider.create(EntityRendererMap.this.context);
                  ModernFix.LOGGER.info("Loaded entity {}", class_7923.field_41177.method_10221(key));
               } catch (RuntimeException var7) {
                  ModernFix.LOGGER.error("Failed to create entity model for " + class_7923.field_41177.method_10221(key) + ":", var7);
                  renderer = new ErroredEntityRenderer(EntityRendererMap.this.context);
               }

               return Optional.ofNullable(renderer);
            }
         }
      }
   }
}
