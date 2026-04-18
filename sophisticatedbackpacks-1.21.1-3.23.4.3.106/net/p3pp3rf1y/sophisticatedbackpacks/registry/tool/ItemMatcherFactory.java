package net.p3pp3rf1y.sophisticatedbackpacks.registry.tool;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.util.Optional;
import java.util.function.Predicate;
import net.minecraft.class_1799;
import net.minecraft.class_3518;

abstract class ItemMatcherFactory {
   private final String typeName;

   public ItemMatcherFactory(String typeName) {
      this.typeName = typeName;
   }

   public boolean appliesTo(JsonElement jsonElement) {
      return jsonElement.isJsonObject() && class_3518.method_15265(jsonElement.getAsJsonObject(), "type").equals(this.typeName);
   }

   public Optional<Predicate<class_1799>> getPredicate(JsonElement jsonElement) {
      return this.getPredicateFromObject(jsonElement.getAsJsonObject());
   }

   protected abstract Optional<Predicate<class_1799>> getPredicateFromObject(JsonObject var1);
}
