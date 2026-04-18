package me.shedaniel.clothconfig2.impl.builders;

import java.util.Objects;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import me.shedaniel.clothconfig2.gui.entries.StringListEntry;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.class_2561;
import org.jetbrains.annotations.NotNull;

@Environment(EnvType.CLIENT)
public class TextFieldBuilder extends AbstractFieldBuilder<String, StringListEntry, TextFieldBuilder> {
   public TextFieldBuilder(class_2561 resetButtonKey, class_2561 fieldNameKey, String value) {
      super(resetButtonKey, fieldNameKey);
      Objects.requireNonNull(value);
      this.value = value;
   }

   public TextFieldBuilder setErrorSupplier(Function<String, Optional<class_2561>> errorSupplier) {
      return (TextFieldBuilder)super.setErrorSupplier(errorSupplier);
   }

   public TextFieldBuilder requireRestart() {
      return (TextFieldBuilder)super.requireRestart();
   }

   public TextFieldBuilder setSaveConsumer(Consumer<String> saveConsumer) {
      return (TextFieldBuilder)super.setSaveConsumer(saveConsumer);
   }

   public TextFieldBuilder setDefaultValue(Supplier<String> defaultValue) {
      return (TextFieldBuilder)super.setDefaultValue(defaultValue);
   }

   public TextFieldBuilder setDefaultValue(String defaultValue) {
      return (TextFieldBuilder)super.setDefaultValue(defaultValue);
   }

   public TextFieldBuilder setTooltipSupplier(Function<String, Optional<class_2561[]>> tooltipSupplier) {
      return (TextFieldBuilder)super.setTooltipSupplier(tooltipSupplier);
   }

   public TextFieldBuilder setTooltipSupplier(Supplier<Optional<class_2561[]>> tooltipSupplier) {
      return (TextFieldBuilder)super.setTooltipSupplier(tooltipSupplier);
   }

   public TextFieldBuilder setTooltip(Optional<class_2561[]> tooltip) {
      return (TextFieldBuilder)super.setTooltip(tooltip);
   }

   public TextFieldBuilder setTooltip(class_2561... tooltip) {
      return (TextFieldBuilder)super.setTooltip(tooltip);
   }

   @NotNull
   public StringListEntry build() {
      StringListEntry entry = new StringListEntry(
         this.getFieldNameKey(), this.value, this.getResetButtonKey(), this.defaultValue, this.getSaveConsumer(), null, this.isRequireRestart()
      );
      entry.setTooltipSupplier(() -> this.getTooltipSupplier().apply(entry.getValue()));
      if (this.errorSupplier != null) {
         entry.setErrorSupplier(() -> this.errorSupplier.apply(entry.getValue()));
      }

      return this.finishBuilding(entry);
   }
}
