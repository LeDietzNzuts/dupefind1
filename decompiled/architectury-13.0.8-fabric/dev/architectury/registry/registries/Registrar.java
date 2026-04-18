package dev.architectury.registry.registries;

import java.util.Optional;
import java.util.Set;
import java.util.Map.Entry;
import java.util.function.Consumer;
import java.util.function.Supplier;
import net.minecraft.class_2378;
import net.minecraft.class_2960;
import net.minecraft.class_5321;
import net.minecraft.class_6880;
import org.jetbrains.annotations.Nullable;

public interface Registrar<T> extends Iterable<T> {
   RegistrySupplier<T> delegate(class_2960 var1);

   default <R extends T> RegistrySupplier<R> wrap(R obj) {
      class_2960 id = this.getId((T)obj);
      if (id == null) {
         throw new IllegalArgumentException("Cannot wrap an object without an id: " + obj);
      } else {
         return this.delegate(id);
      }
   }

   <E extends T> RegistrySupplier<E> register(class_2960 var1, Supplier<E> var2);

   @Nullable
   class_2960 getId(T var1);

   int getRawId(T var1);

   Optional<class_5321<T>> getKey(T var1);

   @Nullable
   T get(class_2960 var1);

   @Nullable
   T byRawId(int var1);

   boolean contains(class_2960 var1);

   boolean containsValue(T var1);

   Set<class_2960> getIds();

   Set<Entry<class_5321<T>, T>> entrySet();

   class_5321<? extends class_2378<T>> key();

   @Nullable
   class_6880<T> getHolder(class_5321<T> var1);

   @Nullable
   default class_6880<T> getHolder(class_2960 id) {
      return this.getHolder(class_5321.method_29179(this.key(), id));
   }

   default <R extends T> void listen(RegistrySupplier<R> supplier, Consumer<R> callback) {
      this.listen(supplier.getId(), obj -> callback.accept((R)obj));
   }

   void listen(class_2960 var1, Consumer<T> var2);
}
