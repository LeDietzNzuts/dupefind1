package me.shedaniel.clothconfig2.gui.entries;

import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.class_2561;
import org.jetbrains.annotations.ApiStatus.Internal;

@Environment(EnvType.CLIENT)
public class EnumListEntry<T extends Enum<?>> extends SelectionListEntry<T> {
   public static final Function<Enum, class_2561> DEFAULT_NAME_PROVIDER = t -> class_2561.method_43471(
      t instanceof SelectionListEntry.Translatable ? ((SelectionListEntry.Translatable)t).getKey() : t.toString()
   );

   @Deprecated
   @Internal
   public EnumListEntry(class_2561 fieldName, Class<T> clazz, T value, class_2561 resetButtonKey, Supplier<T> defaultValue, Consumer<T> saveConsumer) {
      super(fieldName, clazz.getEnumConstants(), value, resetButtonKey, defaultValue, saveConsumer, DEFAULT_NAME_PROVIDER::apply);
   }

   @Deprecated
   @Internal
   public EnumListEntry(
      class_2561 fieldName,
      Class<T> clazz,
      T value,
      class_2561 resetButtonKey,
      Supplier<T> defaultValue,
      Consumer<T> saveConsumer,
      Function<Enum, class_2561> enumNameProvider
   ) {
      super(fieldName, clazz.getEnumConstants(), value, resetButtonKey, defaultValue, saveConsumer, enumNameProvider::apply, null);
   }

   @Deprecated
   @Internal
   public EnumListEntry(
      class_2561 fieldName,
      Class<T> clazz,
      T value,
      class_2561 resetButtonKey,
      Supplier<T> defaultValue,
      Consumer<T> saveConsumer,
      Function<Enum, class_2561> enumNameProvider,
      Supplier<Optional<class_2561[]>> tooltipSupplier
   ) {
      super(fieldName, clazz.getEnumConstants(), value, resetButtonKey, defaultValue, saveConsumer, enumNameProvider::apply, tooltipSupplier, false);
   }

   @Deprecated
   @Internal
   public EnumListEntry(
      class_2561 fieldName,
      Class<T> clazz,
      T value,
      class_2561 resetButtonKey,
      Supplier<T> defaultValue,
      Consumer<T> saveConsumer,
      Function<Enum, class_2561> enumNameProvider,
      Supplier<Optional<class_2561[]>> tooltipSupplier,
      boolean requiresRestart
   ) {
      super(fieldName, clazz.getEnumConstants(), value, resetButtonKey, defaultValue, saveConsumer, enumNameProvider::apply, tooltipSupplier, requiresRestart);
   }
}
