package dev.architectury.registry.registries;

import com.google.common.base.Suppliers;
import dev.architectury.impl.RegistrySupplierImpl;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.function.Supplier;
import net.minecraft.class_2378;
import net.minecraft.class_2960;
import net.minecraft.class_5321;
import net.minecraft.class_6880;
import org.jetbrains.annotations.Nullable;

public class DeferredRegister<T> implements Iterable<RegistrySupplier<T>> {
   private final Supplier<RegistrarManager> registriesSupplier;
   private final class_5321<class_2378<T>> key;
   private final List<DeferredRegister<T>.Entry<T>> entries = new ArrayList<>();
   private final List<RegistrySupplier<T>> entryView = Collections.unmodifiableList(this.entries);
   private boolean registered = false;
   @Nullable
   private String modId;

   private DeferredRegister(Supplier<RegistrarManager> registriesSupplier, class_5321<class_2378<T>> key, @Nullable String modId) {
      this.registriesSupplier = Objects.requireNonNull(registriesSupplier);
      this.key = Objects.requireNonNull(key);
      this.modId = modId;
   }

   public static <T> DeferredRegister<T> create(String modId, class_5321<class_2378<T>> key) {
      Supplier<RegistrarManager> value = Suppliers.memoize(() -> RegistrarManager.get(modId));
      return new DeferredRegister<>(value, key, Objects.requireNonNull(modId));
   }

   public <R extends T> RegistrySupplier<R> register(String id, Supplier<? extends R> supplier) {
      if (this.modId == null) {
         throw new NullPointerException("You must create the deferred register with a mod id to register entries without the namespace!");
      } else {
         return this.register(class_2960.method_60655(this.modId, id), supplier);
      }
   }

   public <R extends T> RegistrySupplier<R> register(class_2960 id, Supplier<? extends R> supplier) {
      DeferredRegister<T>.Entry<T> entry = new DeferredRegister.Entry<>(id, (Supplier<T>)supplier);
      this.entries.add(entry);
      if (this.registered) {
         Registrar<T> registrar = this.getRegistrar();
         entry.value = registrar.register(entry.id, entry.supplier);
      }

      return entry;
   }

   public void register() {
      if (this.registered) {
         throw new IllegalStateException("Cannot register a deferred register twice!");
      } else {
         this.registered = true;
         Registrar<T> registrar = this.getRegistrar();

         for (DeferredRegister<T>.Entry<T> entry : this.entries) {
            entry.value = registrar.register(entry.id, entry.supplier);
         }
      }
   }

   @Override
   public Iterator<RegistrySupplier<T>> iterator() {
      return this.entryView.iterator();
   }

   public RegistrarManager getRegistrarManager() {
      return this.registriesSupplier.get();
   }

   public Registrar<T> getRegistrar() {
      return this.registriesSupplier.get().get(this.key);
   }

   private class Entry<R> implements RegistrySupplierImpl<R> {
      private final class_2960 id;
      private final Supplier<R> supplier;
      private RegistrySupplier<R> value;
      @Nullable
      private class_6880<R> holder = null;

      public Entry(class_2960 id, Supplier<R> supplier) {
         this.id = id;
         this.supplier = supplier;
      }

      @Nullable
      @Override
      public class_6880<R> getHolder() {
         return this.holder != null ? this.holder : (this.holder = this.getRegistrar().getHolder(this.getId()));
      }

      @Override
      public RegistrarManager getRegistrarManager() {
         return DeferredRegister.this.getRegistrarManager();
      }

      @Override
      public Registrar<R> getRegistrar() {
         return (Registrar<R>)DeferredRegister.this.getRegistrar();
      }

      @Override
      public class_5321<R> getKey() {
         return class_5321.method_29179(this.getRegistryKey(), this.getId());
      }

      @Override
      public class_2960 getRegistryId() {
         return DeferredRegister.this.key.method_29177();
      }

      @Override
      public class_2960 getId() {
         return this.id;
      }

      @Override
      public boolean isPresent() {
         return this.value != null && this.value.isPresent();
      }

      @Override
      public R get() {
         if (this.isPresent()) {
            return this.value.get();
         } else {
            throw new NullPointerException("Registry Object not present: " + this.id);
         }
      }

      @Override
      public int hashCode() {
         return Objects.hash(this.getRegistryId(), this.getId());
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
         return this.getRegistryId().toString() + "@" + this.id.toString();
      }
   }
}
