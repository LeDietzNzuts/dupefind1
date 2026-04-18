package me.shedaniel.clothconfig2.impl.builders;

import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import me.shedaniel.clothconfig2.gui.entries.BooleanListEntry;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.class_2561;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Environment(EnvType.CLIENT)
public class BooleanToggleBuilder extends AbstractFieldBuilder<Boolean, BooleanListEntry, BooleanToggleBuilder> {
   @Nullable
   private Function<Boolean, class_2561> yesNoTextSupplier = null;

   public BooleanToggleBuilder(class_2561 resetButtonKey, class_2561 fieldNameKey, boolean value) {
      super(resetButtonKey, fieldNameKey);
      this.value = value;
   }

   public BooleanToggleBuilder setErrorSupplier(Function<Boolean, Optional<class_2561>> errorSupplier) {
      return (BooleanToggleBuilder)super.setErrorSupplier(errorSupplier);
   }

   public BooleanToggleBuilder requireRestart() {
      return (BooleanToggleBuilder)super.requireRestart();
   }

   public BooleanToggleBuilder setSaveConsumer(Consumer<Boolean> saveConsumer) {
      return (BooleanToggleBuilder)super.setSaveConsumer(saveConsumer);
   }

   public BooleanToggleBuilder setDefaultValue(Supplier<Boolean> defaultValue) {
      return (BooleanToggleBuilder)super.setDefaultValue(defaultValue);
   }

   public BooleanToggleBuilder setDefaultValue(boolean defaultValue) {
      this.defaultValue = () -> defaultValue;
      return this;
   }

   public BooleanToggleBuilder setTooltipSupplier(Function<Boolean, Optional<class_2561[]>> tooltipSupplier) {
      return (BooleanToggleBuilder)super.setTooltipSupplier(tooltipSupplier);
   }

   public BooleanToggleBuilder setTooltipSupplier(Supplier<Optional<class_2561[]>> tooltipSupplier) {
      return (BooleanToggleBuilder)super.setTooltipSupplier(tooltipSupplier);
   }

   public BooleanToggleBuilder setTooltip(Optional<class_2561[]> tooltip) {
      return (BooleanToggleBuilder)super.setTooltip(tooltip);
   }

   public BooleanToggleBuilder setTooltip(class_2561... tooltip) {
      return (BooleanToggleBuilder)super.setTooltip(tooltip);
   }

   @Nullable
   public Function<Boolean, class_2561> getYesNoTextSupplier() {
      return this.yesNoTextSupplier;
   }

   public BooleanToggleBuilder setYesNoTextSupplier(@Nullable Function<Boolean, class_2561> yesNoTextSupplier) {
      this.yesNoTextSupplier = yesNoTextSupplier;
      return this;
   }

   @NotNull
   public BooleanListEntry build() {
      BooleanListEntry entry = new BooleanListEntry(
         this.getFieldNameKey(), this.value, this.getResetButtonKey(), this.defaultValue, this.getSaveConsumer(), null, this.isRequireRestart()
      ) {
         @Override
         public class_2561 getYesNoText(boolean bool) {
            return BooleanToggleBuilder.this.yesNoTextSupplier == null ? super.getYesNoText(bool) : BooleanToggleBuilder.this.yesNoTextSupplier.apply(bool);
         }
      };
      entry.setTooltipSupplier(() -> this.getTooltipSupplier().apply(entry.getValue()));
      if (this.errorSupplier != null) {
         entry.setErrorSupplier(() -> this.errorSupplier.apply(entry.getValue()));
      }

      return this.finishBuilding(entry);
   }
}
