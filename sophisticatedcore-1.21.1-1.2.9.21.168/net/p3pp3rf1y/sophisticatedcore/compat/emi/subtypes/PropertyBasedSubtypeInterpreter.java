package net.p3pp3rf1y.sophisticatedcore.compat.emi.subtypes;

import dev.emi.emi.api.stack.Comparison;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.function.Function;
import net.minecraft.class_1799;
import net.minecraft.class_7923;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class PropertyBasedSubtypeInterpreter {
   private final List<PropertyBasedSubtypeInterpreter.IPropertyDefinition<?>> propertyDefinitions = new ArrayList<>();
   private final Comparison comparator = Comparison.compareData(emiStack -> this.getSubtypeData(emiStack.getItemStack()));

   protected <T> void addOptionalProperty(Function<class_1799, Optional<T>> propertyGetter, String propertyName, Function<T, String> propertyValueSerializer) {
      Function<class_1799, T> nullableGetter = propertyGetter.andThen(i -> i.orElse(null));
      this.addDefinition(nullableGetter, propertyName, propertyValueSerializer);
   }

   private <T> void addDefinition(Function<class_1799, @Nullable T> getter, String propertyName, Function<T, String> propertyValueSerializer) {
      this.propertyDefinitions.add(new PropertyBasedSubtypeInterpreter.IPropertyDefinition<T>() {
         @Nullable
         @Override
         public T getPropertyValue(class_1799 itemStack) {
            return getter.apply(itemStack);
         }

         @Override
         public String getPropertyName() {
            return propertyName;
         }

         @Override
         public String serializePropertyValue(@Nullable T property) {
            return property != null ? propertyValueSerializer.apply(property) : "";
         }
      });
   }

   protected <T> void addProperty(Function<class_1799, @Nullable T> propertyGetter, String propertyName, Function<T, String> propertyValueSerializer) {
      this.addDefinition(propertyGetter, propertyName, propertyValueSerializer);
   }

   private <T> String getSerializedPropertyValue(PropertyBasedSubtypeInterpreter.IPropertyDefinition<T> definition, Object value) {
      return definition.serializePropertyValue((T)value);
   }

   public String getRegistrySanitizedItemString(class_1799 stack) {
      StringBuilder result = new StringBuilder();

      for (PropertyBasedSubtypeInterpreter.IPropertyDefinition<?> definition : this.propertyDefinitions) {
         Object value = definition.getPropertyValue(stack);
         if (value != null) {
            String serializedValue = this.sanitize(this.getSerializedPropertyValue(definition, value));
            if (!result.isEmpty()) {
               result.append('_');
            }

            result.append(definition.getPropertyName().toLowerCase(Locale.ROOT)).append('_').append(serializedValue);
         }
      }

      return getItemPath(stack) + "_" + result;
   }

   private String sanitize(String value) {
      return value.replaceAll(":", "_").toLowerCase(Locale.ROOT);
   }

   @NotNull
   private static String getItemPath(class_1799 stack) {
      return class_7923.field_41178.method_10221(stack.method_7909()).method_12832();
   }

   @Nullable
   public final Object getSubtypeData(class_1799 ingredient) {
      boolean allNulls = true;
      List<Object> results = new ArrayList<>(this.propertyDefinitions.size());

      for (PropertyBasedSubtypeInterpreter.IPropertyDefinition<?> definition : this.propertyDefinitions) {
         Object value = definition.getPropertyValue(ingredient);
         if (value != null) {
            allNulls = false;
         }

         results.add(value);
      }

      return allNulls ? null : results;
   }

   public Comparison comparator() {
      return this.comparator;
   }

   public interface IPropertyDefinition<T> {
      @Nullable
      T getPropertyValue(class_1799 var1);

      String getPropertyName();

      String serializePropertyValue(@Nullable T var1);
   }
}
