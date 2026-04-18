package me.shedaniel.clothconfig2.impl.builders;

import java.util.Optional;
import java.util.function.Supplier;
import me.shedaniel.clothconfig2.gui.entries.TextListEntry;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.class_2561;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Environment(EnvType.CLIENT)
public class TextDescriptionBuilder extends FieldBuilder<class_2561, TextListEntry, TextDescriptionBuilder> {
   private int color = -1;
   @Nullable
   private Supplier<Optional<class_2561[]>> tooltipSupplier = null;
   private final class_2561 value;

   public TextDescriptionBuilder(class_2561 resetButtonKey, class_2561 fieldNameKey, class_2561 value) {
      super(resetButtonKey, fieldNameKey);
      this.value = value;
   }

   @Override
   public void requireRestart(boolean requireRestart) {
      throw new UnsupportedOperationException();
   }

   public TextDescriptionBuilder setTooltipSupplier(Supplier<Optional<class_2561[]>> tooltipSupplier) {
      this.tooltipSupplier = tooltipSupplier;
      return this;
   }

   public TextDescriptionBuilder setTooltip(Optional<class_2561[]> tooltip) {
      this.tooltipSupplier = () -> tooltip;
      return this;
   }

   public TextDescriptionBuilder setTooltip(class_2561... tooltip) {
      this.tooltipSupplier = () -> Optional.ofNullable(tooltip);
      return this;
   }

   public TextDescriptionBuilder setColor(int color) {
      this.color = color;
      return this;
   }

   @NotNull
   public TextListEntry build() {
      return this.finishBuilding(new TextListEntry(this.getFieldNameKey(), this.value, this.color, this.tooltipSupplier));
   }
}
