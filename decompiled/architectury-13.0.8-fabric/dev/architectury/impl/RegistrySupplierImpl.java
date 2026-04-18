package dev.architectury.impl;

import com.mojang.datafixers.util.Either;
import dev.architectury.registry.registries.RegistrySupplier;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Stream;
import net.minecraft.class_2960;
import net.minecraft.class_5321;
import net.minecraft.class_6862;
import net.minecraft.class_6880;
import net.minecraft.class_7876;
import net.minecraft.class_6880.class_6882;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.ApiStatus.Internal;

@Internal
public interface RegistrySupplierImpl<T> extends RegistrySupplier<T> {
   @Nullable
   class_6880<T> getHolder();

   default T comp_349() {
      return this.get();
   }

   default boolean method_40227() {
      return this.isPresent();
   }

   default boolean method_40226(class_2960 resourceLocation) {
      return this.getId().equals(resourceLocation);
   }

   default boolean method_40225(class_5321<T> resourceKey) {
      return this.getKey().equals(resourceKey);
   }

   default boolean method_40224(Predicate<class_5321<T>> predicate) {
      return predicate.test(this.getKey());
   }

   default boolean method_40220(class_6862<T> tagKey) {
      class_6880<T> holder = this.getHolder();
      return holder != null && holder.method_40220(tagKey);
   }

   default boolean method_55838(class_6880<T> holder) {
      return holder.method_40225(this.getKey());
   }

   default Stream<class_6862<T>> method_40228() {
      class_6880<T> holder = this.getHolder();
      return holder != null ? holder.method_40228() : Stream.empty();
   }

   default Either<class_5321<T>, T> method_40229() {
      return Either.left(this.getKey());
   }

   default Optional<class_5321<T>> method_40230() {
      return Optional.of(this.getKey());
   }

   default class_6882 method_40231() {
      return class_6882.field_36446;
   }

   default boolean method_46745(class_7876<T> holderOwner) {
      class_6880<T> holder = this.getHolder();
      return holder != null && holder.method_46745(holderOwner);
   }
}
