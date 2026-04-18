package me.shedaniel.clothconfig2.gui.entries;

import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Supplier;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.class_2561;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.ApiStatus.Internal;

@Environment(EnvType.CLIENT)
public class DoubleListListEntry extends AbstractTextFieldListListEntry<Double, DoubleListListEntry.DoubleListCell, DoubleListListEntry> {
   private double minimum = Double.NEGATIVE_INFINITY;
   private double maximum = Double.POSITIVE_INFINITY;

   @Deprecated
   @Internal
   public DoubleListListEntry(
      class_2561 fieldName,
      List<Double> value,
      boolean defaultExpanded,
      Supplier<Optional<class_2561[]>> tooltipSupplier,
      Consumer<List<Double>> saveConsumer,
      Supplier<List<Double>> defaultValue,
      class_2561 resetButtonKey
   ) {
      this(fieldName, value, defaultExpanded, tooltipSupplier, saveConsumer, defaultValue, resetButtonKey, false);
   }

   @Deprecated
   @Internal
   public DoubleListListEntry(
      class_2561 fieldName,
      List<Double> value,
      boolean defaultExpanded,
      Supplier<Optional<class_2561[]>> tooltipSupplier,
      Consumer<List<Double>> saveConsumer,
      Supplier<List<Double>> defaultValue,
      class_2561 resetButtonKey,
      boolean requiresRestart
   ) {
      this(fieldName, value, defaultExpanded, tooltipSupplier, saveConsumer, defaultValue, resetButtonKey, requiresRestart, true, true);
   }

   @Deprecated
   @Internal
   public DoubleListListEntry(
      class_2561 fieldName,
      List<Double> value,
      boolean defaultExpanded,
      Supplier<Optional<class_2561[]>> tooltipSupplier,
      Consumer<List<Double>> saveConsumer,
      Supplier<List<Double>> defaultValue,
      class_2561 resetButtonKey,
      boolean requiresRestart,
      boolean deleteButtonEnabled,
      boolean insertInFront
   ) {
      super(
         fieldName,
         value,
         defaultExpanded,
         tooltipSupplier,
         saveConsumer,
         defaultValue,
         resetButtonKey,
         requiresRestart,
         deleteButtonEnabled,
         insertInFront,
         DoubleListListEntry.DoubleListCell::new
      );
   }

   public DoubleListListEntry setMaximum(Double maximum) {
      this.maximum = maximum;
      return this;
   }

   public DoubleListListEntry setMinimum(Double minimum) {
      this.minimum = minimum;
      return this;
   }

   public DoubleListListEntry self() {
      return this;
   }

   public static class DoubleListCell
      extends AbstractTextFieldListListEntry.AbstractTextFieldListCell<Double, DoubleListListEntry.DoubleListCell, DoubleListListEntry> {
      public DoubleListCell(Double value, DoubleListListEntry listListEntry) {
         super(value, listListEntry);
      }

      @Nullable
      protected Double substituteDefault(@Nullable Double value) {
         return value == null ? 0.0 : value;
      }

      @Override
      protected boolean isValidText(@NotNull String text) {
         return text.chars().allMatch(c -> Character.isDigit(c) || c == 45 || c == 46);
      }

      public Double getValue() {
         try {
            return Double.valueOf(this.widget.method_1882());
         } catch (NumberFormatException var2) {
            return 0.0;
         }
      }

      @Override
      public Optional<class_2561> getError() {
         try {
            double i = Double.parseDouble(this.widget.method_1882());
            if (i > this.listListEntry.maximum) {
               return Optional.of(class_2561.method_43469("text.cloth-config.error.too_large", new Object[]{this.listListEntry.maximum}));
            }

            if (i < this.listListEntry.minimum) {
               return Optional.of(class_2561.method_43469("text.cloth-config.error.too_small", new Object[]{this.listListEntry.minimum}));
            }
         } catch (NumberFormatException var3) {
            return Optional.of(class_2561.method_43471("text.cloth-config.error.not_valid_number_double"));
         }

         return Optional.empty();
      }
   }
}
