package net.p3pp3rf1y.sophisticatedcore.util.model;

import java.util.Collections;
import java.util.IdentityHashMap;
import java.util.Map;
import java.util.Set;
import org.jetbrains.annotations.Nullable;

public class ModelData {
   public static final ModelData EMPTY = builder().build();
   private final Map<ModelProperty<?>, Object> properties;

   private ModelData(Map<ModelProperty<?>, Object> properties) {
      this.properties = properties;
   }

   public Set<ModelProperty<?>> getProperties() {
      return this.properties.keySet();
   }

   public boolean has(ModelProperty<?> property) {
      return this.properties.containsKey(property);
   }

   @Nullable
   public <T> T get(ModelProperty<T> property) {
      return (T)this.properties.get(property);
   }

   public static ModelData.Builder derive(ModelData data) {
      return new ModelData.Builder(data.properties);
   }

   public static ModelData.Builder builder() {
      return new ModelData.Builder(new IdentityHashMap<>());
   }

   public static final class Builder {
      private final Map<ModelProperty<?>, Object> properties = new IdentityHashMap<>();

      private Builder(@Nullable Map<ModelProperty<?>, Object> properties) {
         if (properties != null) {
            this.properties.putAll(properties);
         }
      }

      public <T> ModelData.Builder with(ModelProperty<T> property, T value) {
         this.properties.put(property, value);
         return this;
      }

      public ModelData build() {
         return new ModelData(Collections.unmodifiableMap(this.properties));
      }
   }
}
