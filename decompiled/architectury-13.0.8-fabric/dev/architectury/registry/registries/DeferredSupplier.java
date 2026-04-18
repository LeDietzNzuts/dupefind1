package dev.architectury.registry.registries;

import dev.architectury.utils.OptionalSupplier;
import net.minecraft.class_2378;
import net.minecraft.class_2960;
import net.minecraft.class_5321;

public interface DeferredSupplier<T> extends OptionalSupplier<T> {
   class_2960 getRegistryId();

   default class_5321<class_2378<T>> getRegistryKey() {
      return class_5321.method_29180(this.getRegistryId());
   }

   class_2960 getId();

   default class_5321<T> getKey() {
      return class_5321.method_29179(this.getRegistryKey(), this.getId());
   }
}
