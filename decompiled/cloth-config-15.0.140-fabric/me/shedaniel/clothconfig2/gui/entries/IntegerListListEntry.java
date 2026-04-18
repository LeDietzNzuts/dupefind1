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
public class IntegerListListEntry extends AbstractTextFieldListListEntry<Integer, IntegerListListEntry.IntegerListCell, IntegerListListEntry> {
   private int minimum = Integer.MIN_VALUE;
   private int maximum = Integer.MAX_VALUE;

   @Deprecated
   @Internal
   public IntegerListListEntry(
      class_2561 fieldName,
      List<Integer> value,
      boolean defaultExpanded,
      Supplier<Optional<class_2561[]>> tooltipSupplier,
      Consumer<List<Integer>> saveConsumer,
      Supplier<List<Integer>> defaultValue,
      class_2561 resetButtonKey
   ) {
      this(fieldName, value, defaultExpanded, tooltipSupplier, saveConsumer, defaultValue, resetButtonKey, false);
   }

   @Deprecated
   @Internal
   public IntegerListListEntry(
      class_2561 fieldName,
      List<Integer> value,
      boolean defaultExpanded,
      Supplier<Optional<class_2561[]>> tooltipSupplier,
      Consumer<List<Integer>> saveConsumer,
      Supplier<List<Integer>> defaultValue,
      class_2561 resetButtonKey,
      boolean requiresRestart
   ) {
      this(fieldName, value, defaultExpanded, tooltipSupplier, saveConsumer, defaultValue, resetButtonKey, requiresRestart, true, true);
   }

   @Deprecated
   @Internal
   public IntegerListListEntry(
      class_2561 fieldName,
      List<Integer> value,
      boolean defaultExpanded,
      Supplier<Optional<class_2561[]>> tooltipSupplier,
      Consumer<List<Integer>> saveConsumer,
      Supplier<List<Integer>> defaultValue,
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
         IntegerListListEntry.IntegerListCell::new
      );
   }

   public IntegerListListEntry setMaximum(int maximum) {
      this.maximum = maximum;
      return this;
   }

   public IntegerListListEntry setMinimum(int minimum) {
      this.minimum = minimum;
      return this;
   }

   public IntegerListListEntry self() {
      return this;
   }

   public static class IntegerListCell
      extends AbstractTextFieldListListEntry.AbstractTextFieldListCell<Integer, IntegerListListEntry.IntegerListCell, IntegerListListEntry> {
      public IntegerListCell(Integer value, IntegerListListEntry listListEntry) {
         super(value, listListEntry);
      }

      @Nullable
      protected Integer substituteDefault(@Nullable Integer value) {
         return value == null ? 0 : value;
      }

      @Override
      protected boolean isValidText(@NotNull String text) {
         return text.chars().allMatch(c -> Character.isDigit(c) || c == 45);
      }

      public Integer getValue() {
         try {
            return Integer.valueOf(this.widget.method_1882());
         } catch (NumberFormatException var2) {
            return 0;
         }
      }

      @Override
      public Optional<class_2561> getError() {
         try {
            int i = Integer.parseInt(this.widget.method_1882());
            if (i > this.listListEntry.maximum) {
               return Optional.of(class_2561.method_43469("text.cloth-config.error.too_large", new Object[]{this.listListEntry.maximum}));
            }

            if (i < this.listListEntry.minimum) {
               return Optional.of(class_2561.method_43469("text.cloth-config.error.too_small", new Object[]{this.listListEntry.minimum}));
            }
         } catch (NumberFormatException var2) {
            return Optional.of(class_2561.method_43471("text.cloth-config.error.not_valid_number_int"));
         }

         return Optional.empty();
      }
   }
}
