package net.p3pp3rf1y.sophisticatedbackpacks.registry.tool;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.util.Optional;
import java.util.function.Predicate;
import net.minecraft.class_3518;

abstract class TypedMatcherFactory<T> implements IMatcherFactory<T> {
   private final String typeName;

   protected TypedMatcherFactory(String typeName) {
      this.typeName = typeName;
   }

   @Override
   public boolean appliesTo(JsonElement jsonElement) {
      return jsonElement.isJsonObject() && class_3518.method_15265(jsonElement.getAsJsonObject(), "type").equals(this.typeName);
   }

   @Override
   public Optional<Predicate<T>> getPredicate(JsonElement jsonElement) {
      return this.getPredicateFromObject(jsonElement.getAsJsonObject());
   }

   protected abstract Optional<Predicate<T>> getPredicateFromObject(JsonObject var1);
}
