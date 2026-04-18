package me.shedaniel.clothconfig2.impl.builders;

import java.util.Objects;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import me.shedaniel.clothconfig2.gui.entries.ColorEntry;
import me.shedaniel.math.Color;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.class_2561;
import net.minecraft.class_5251;
import org.jetbrains.annotations.NotNull;

@Environment(EnvType.CLIENT)
public class ColorFieldBuilder extends AbstractFieldBuilder<Integer, ColorEntry, ColorFieldBuilder> {
   private boolean alpha = false;

   public ColorFieldBuilder(class_2561 resetButtonKey, class_2561 fieldNameKey, int value) {
      super(resetButtonKey, fieldNameKey);
      this.value = value;
   }

   public ColorFieldBuilder setErrorSupplier(Function<Integer, Optional<class_2561>> errorSupplier) {
      return (ColorFieldBuilder)super.setErrorSupplier(errorSupplier);
   }

   public ColorFieldBuilder requireRestart() {
      return (ColorFieldBuilder)super.requireRestart();
   }

   public ColorFieldBuilder setSaveConsumer(Consumer<Integer> saveConsumer) {
      return (ColorFieldBuilder)super.setSaveConsumer(saveConsumer);
   }

   public ColorFieldBuilder setSaveConsumer2(Consumer<Color> saveConsumer) {
      return (ColorFieldBuilder)super.setSaveConsumer(integer -> saveConsumer.accept(this.alpha ? Color.ofTransparent(integer) : Color.ofOpaque(integer)));
   }

   public ColorFieldBuilder setSaveConsumer3(Consumer<class_5251> saveConsumer) {
      return (ColorFieldBuilder)super.setSaveConsumer(integer -> saveConsumer.accept(class_5251.method_27717(integer)));
   }

   public ColorFieldBuilder setDefaultValue(Supplier<Integer> defaultValue) {
      return (ColorFieldBuilder)super.setDefaultValue(defaultValue);
   }

   public ColorFieldBuilder setDefaultValue2(Supplier<Color> defaultValue) {
      this.defaultValue = () -> defaultValue.get().getColor();
      return this;
   }

   public ColorFieldBuilder setDefaultValue3(Supplier<class_5251> defaultValue) {
      this.defaultValue = () -> defaultValue.get().method_27716();
      return this;
   }

   public ColorFieldBuilder setAlphaMode(boolean withAlpha) {
      this.alpha = withAlpha;
      return this;
   }

   public ColorFieldBuilder setDefaultValue(int defaultValue) {
      this.defaultValue = () -> defaultValue;
      return this;
   }

   public ColorFieldBuilder setDefaultValue(class_5251 defaultValue) {
      this.defaultValue = () -> Objects.requireNonNull(defaultValue).method_27716();
      return this;
   }

   public ColorFieldBuilder setTooltipSupplier(Supplier<Optional<class_2561[]>> tooltipSupplier) {
      return (ColorFieldBuilder)super.setTooltipSupplier(tooltipSupplier);
   }

   public ColorFieldBuilder setTooltipSupplier(Function<Integer, Optional<class_2561[]>> tooltipSupplier) {
      return (ColorFieldBuilder)super.setTooltipSupplier(tooltipSupplier);
   }

   public ColorFieldBuilder setTooltip(Optional<class_2561[]> tooltip) {
      return (ColorFieldBuilder)super.setTooltip(tooltip);
   }

   public ColorFieldBuilder setTooltip(class_2561... tooltip) {
      return (ColorFieldBuilder)super.setTooltip(tooltip);
   }

   @NotNull
   public ColorEntry build() {
      ColorEntry entry = new ColorEntry(
         this.getFieldNameKey(), this.value, this.getResetButtonKey(), this.defaultValue, this.getSaveConsumer(), null, this.isRequireRestart()
      );
      if (this.alpha) {
         entry.withAlpha();
      } else {
         entry.withoutAlpha();
      }

      entry.setTooltipSupplier(() -> this.getTooltipSupplier().apply(entry.getValue()));
      if (this.errorSupplier != null) {
         entry.setErrorSupplier(() -> this.errorSupplier.apply(entry.getValue()));
      }

      return this.finishBuilding(entry);
   }
}
