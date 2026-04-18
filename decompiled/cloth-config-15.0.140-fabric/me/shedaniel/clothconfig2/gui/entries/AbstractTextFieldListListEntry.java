package me.shedaniel.clothconfig2.gui.entries;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Supplier;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.class_2561;
import net.minecraft.class_310;
import net.minecraft.class_332;
import net.minecraft.class_342;
import net.minecraft.class_364;
import net.minecraft.class_6382;
import net.minecraft.class_6379.class_6380;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.ApiStatus.Internal;

@Environment(EnvType.CLIENT)
public abstract class AbstractTextFieldListListEntry<T, C extends AbstractTextFieldListListEntry.AbstractTextFieldListCell<T, C, SELF>, SELF extends AbstractTextFieldListListEntry<T, C, SELF>>
   extends AbstractListListEntry<T, C, SELF> {
   @Internal
   public AbstractTextFieldListListEntry(
      class_2561 fieldName,
      List<T> value,
      boolean defaultExpanded,
      Supplier<Optional<class_2561[]>> tooltipSupplier,
      Consumer<List<T>> saveConsumer,
      Supplier<List<T>> defaultValue,
      class_2561 resetButtonKey,
      boolean requiresRestart,
      boolean deleteButtonEnabled,
      boolean insertInFront,
      BiFunction<T, SELF, C> createNewCell
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
         createNewCell
      );
   }

   @Internal
   public abstract static class AbstractTextFieldListCell<T, SELF extends AbstractTextFieldListListEntry.AbstractTextFieldListCell<T, SELF, OUTER_SELF>, OUTER_SELF extends AbstractTextFieldListListEntry<T, SELF, OUTER_SELF>>
      extends AbstractListListEntry.AbstractListCell<T, SELF, OUTER_SELF> {
      protected class_342 widget;
      private boolean isSelected;
      private boolean isHovered;

      public AbstractTextFieldListCell(@Nullable T value, OUTER_SELF listListEntry) {
         super(value, listListEntry);
         T finalValue = this.substituteDefault(value);
         this.widget = new class_342(class_310.method_1551().field_1772, 0, 0, 100, 18, class_2561.method_43473()) {
            public void method_48579(class_332 graphics, int mouseX, int mouseY, float delta) {
               this.method_25365(AbstractTextFieldListCell.this.isSelected);
               super.method_48579(graphics, mouseX, mouseY, delta);
            }
         };
         this.widget.method_1890(this::isValidText);
         this.widget.method_1880(Integer.MAX_VALUE);
         this.widget.method_1858(false);
         this.widget.method_1852(Objects.toString(finalValue));
         this.widget.method_1870(false);
         this.widget.method_1863(s -> this.widget.method_1868(this.getPreferredTextColor()));
      }

      @Override
      public void updateSelected(boolean isSelected) {
         this.isSelected = isSelected;
      }

      @Nullable
      protected abstract T substituteDefault(@Nullable T var1);

      protected abstract boolean isValidText(@NotNull String var1);

      @Override
      public int getCellHeight() {
         return 20;
      }

      @Override
      public void render(class_332 graphics, int index, int y, int x, int entryWidth, int entryHeight, int mouseX, int mouseY, boolean isSelected, float delta) {
         this.widget.method_25358(entryWidth - 12);
         this.widget.method_46421(x);
         this.widget.method_46419(y + 1);
         this.widget.method_1888(this.listListEntry.isEditable());
         this.widget.method_25394(graphics, mouseX, mouseY, delta);
         this.isHovered = this.widget.method_25405(mouseX, mouseY);
         if (isSelected && this.listListEntry.isEditable()) {
            graphics.method_25294(x, y + 12, x + entryWidth - 12, y + 13, this.getConfigError().isPresent() ? -43691 : -2039584);
         }
      }

      public List<? extends class_364> method_25396() {
         return Collections.singletonList(this.widget);
      }

      public class_6380 method_37018() {
         return this.isSelected ? class_6380.field_33786 : (this.isHovered ? class_6380.field_33785 : class_6380.field_33784);
      }

      public void method_37020(class_6382 narrationElementOutput) {
         this.widget.method_37020(narrationElementOutput);
      }
   }
}
