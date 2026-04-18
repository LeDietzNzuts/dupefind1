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
public class StringFieldBuilder extends AbstractFieldBuilder<String, StringListEntry, StringFieldBuilder> {
   public StringFieldBuilder(class_2561 resetButtonKey, class_2561 fieldNameKey, String value) {
      super(resetButtonKey, fieldNameKey);
      Objects.requireNonNull(value);
      this.value = value;
   }

   public StringFieldBuilder setErrorSupplier(Function<String, Optional<class_2561>> errorSupplier) {
      return (StringFieldBuilder)super.setErrorSupplier(errorSupplier);
   }

   public StringFieldBuilder requireRestart() {
      return (StringFieldBuilder)super.requireRestart();
   }

   public StringFieldBuilder setSaveConsumer(Consumer<String> saveConsumer) {
      return (StringFieldBuilder)super.setSaveConsumer(saveConsumer);
   }

   public StringFieldBuilder setDefaultValue(Supplier<String> defaultValue) {
      return (StringFieldBuilder)super.setDefaultValue(defaultValue);
   }

   public StringFieldBuilder setDefaultValue(String defaultValue) {
      return (StringFieldBuilder)super.setDefaultValue(defaultValue);
   }

   public StringFieldBuilder setTooltipSupplier(Function<String, Optional<class_2561[]>> tooltipSupplier) {
      return (StringFieldBuilder)super.setTooltipSupplier(tooltipSupplier);
   }

   public StringFieldBuilder setTooltipSupplier(Supplier<Optional<class_2561[]>> tooltipSupplier) {
      return (StringFieldBuilder)super.setTooltipSupplier(tooltipSupplier);
   }

   public StringFieldBuilder setTooltip(Optional<class_2561[]> tooltip) {
      return (StringFieldBuilder)super.setTooltip(tooltip);
   }

   public StringFieldBuilder setTooltip(class_2561... tooltip) {
      return (StringFieldBuilder)super.setTooltip(tooltip);
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
