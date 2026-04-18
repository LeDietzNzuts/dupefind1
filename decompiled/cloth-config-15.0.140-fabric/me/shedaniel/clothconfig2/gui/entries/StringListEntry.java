package me.shedaniel.clothconfig2.gui.entries;

import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Supplier;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.class_2561;
import org.jetbrains.annotations.ApiStatus.Internal;

@Environment(EnvType.CLIENT)
public class StringListEntry extends TextFieldListEntry<String> {
   @Deprecated
   @Internal
   public StringListEntry(class_2561 fieldName, String value, class_2561 resetButtonKey, Supplier<String> defaultValue, Consumer<String> saveConsumer) {
      super(fieldName, value, resetButtonKey, defaultValue);
      this.saveCallback = saveConsumer;
   }

   @Deprecated
   @Internal
   public StringListEntry(
      class_2561 fieldName,
      String value,
      class_2561 resetButtonKey,
      Supplier<String> defaultValue,
      Consumer<String> saveConsumer,
      Supplier<Optional<class_2561[]>> tooltipSupplier
   ) {
      this(fieldName, value, resetButtonKey, defaultValue, saveConsumer, tooltipSupplier, false);
   }

   @Deprecated
   @Internal
   public StringListEntry(
      class_2561 fieldName,
      String value,
      class_2561 resetButtonKey,
      Supplier<String> defaultValue,
      Consumer<String> saveConsumer,
      Supplier<Optional<class_2561[]>> tooltipSupplier,
      boolean requiresRestart
   ) {
      super(fieldName, value, resetButtonKey, defaultValue, tooltipSupplier, requiresRestart);
      this.saveCallback = saveConsumer;
   }

   public String getValue() {
      return this.textFieldWidget.method_1882();
   }
}
