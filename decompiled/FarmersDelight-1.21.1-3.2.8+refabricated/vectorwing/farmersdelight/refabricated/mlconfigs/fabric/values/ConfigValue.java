package vectorwing.farmersdelight.refabricated.mlconfigs.fabric.values;

import java.util.Objects;
import java.util.function.Supplier;
import net.minecraft.class_2561;
import org.jetbrains.annotations.Nullable;
import vectorwing.farmersdelight.refabricated.mlconfigs.fabric.ConfigEntry;

public abstract class ConfigValue<T> extends ConfigEntry implements Supplier<T> {
   protected final T defaultValue;
   protected T value;
   private String translationKey;
   private String descriptionKey;

   protected ConfigValue(String name, T defaultValue) {
      super(name);
      this.defaultValue = defaultValue;
      if (!(this instanceof ObjectConfigValue) && !(this instanceof JsonConfigValue)) {
         Objects.requireNonNull(defaultValue, "default value cant be null");
      }
   }

   public T getDefaultValue() {
      return this.defaultValue;
   }

   public abstract boolean isValid(T var1);

   public void set(T newValue) {
      this.value = newValue;
   }

   @Override
   public T get() {
      return this.value;
   }

   public void setDescriptionKey(String descriptionKey) {
      this.descriptionKey = descriptionKey;
   }

   public void setTranslationKey(String translationKey) {
      this.translationKey = translationKey;
   }

   public class_2561 getTranslation() {
      return class_2561.method_43471(this.translationKey);
   }

   @Nullable
   public class_2561 getDescription() {
      return this.descriptionKey == null ? null : class_2561.method_43471(this.descriptionKey);
   }
}
