package me.shedaniel.clothconfig2.gui.entries;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.class_1041;
import net.minecraft.class_2561;
import net.minecraft.class_310;
import net.minecraft.class_332;
import net.minecraft.class_339;
import net.minecraft.class_364;
import net.minecraft.class_4185;
import net.minecraft.class_6379;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.ApiStatus.Internal;

@Environment(EnvType.CLIENT)
public class SelectionListEntry<T> extends TooltipListEntry<T> {
   private final ImmutableList<T> values;
   private final AtomicInteger index;
   private final int original;
   private final class_4185 buttonWidget;
   private final class_4185 resetButton;
   private final Supplier<T> defaultValue;
   private final List<class_339> widgets;
   private final Function<T, class_2561> nameProvider;

   @Deprecated
   @Internal
   public SelectionListEntry(class_2561 fieldName, T[] valuesArray, T value, class_2561 resetButtonKey, Supplier<T> defaultValue, Consumer<T> saveConsumer) {
      this(fieldName, valuesArray, value, resetButtonKey, defaultValue, saveConsumer, null);
   }

   @Deprecated
   @Internal
   public SelectionListEntry(
      class_2561 fieldName,
      T[] valuesArray,
      T value,
      class_2561 resetButtonKey,
      Supplier<T> defaultValue,
      Consumer<T> saveConsumer,
      Function<T, class_2561> nameProvider
   ) {
      this(fieldName, valuesArray, value, resetButtonKey, defaultValue, saveConsumer, nameProvider, null);
   }

   @Deprecated
   @Internal
   public SelectionListEntry(
      class_2561 fieldName,
      T[] valuesArray,
      T value,
      class_2561 resetButtonKey,
      Supplier<T> defaultValue,
      Consumer<T> saveConsumer,
      Function<T, class_2561> nameProvider,
      Supplier<Optional<class_2561[]>> tooltipSupplier
   ) {
      this(fieldName, valuesArray, value, resetButtonKey, defaultValue, saveConsumer, nameProvider, tooltipSupplier, false);
   }

   @Deprecated
   @Internal
   public SelectionListEntry(
      class_2561 fieldName,
      T[] valuesArray,
      T value,
      class_2561 resetButtonKey,
      Supplier<T> defaultValue,
      Consumer<T> saveConsumer,
      Function<T, class_2561> nameProvider,
      Supplier<Optional<class_2561[]>> tooltipSupplier,
      boolean requiresRestart
   ) {
      super(fieldName, tooltipSupplier, requiresRestart);
      if (valuesArray != null) {
         this.values = ImmutableList.copyOf(valuesArray);
      } else {
         this.values = ImmutableList.of(value);
      }

      this.defaultValue = defaultValue;
      this.index = new AtomicInteger(this.values.indexOf(value));
      this.index.compareAndSet(-1, 0);
      this.original = this.values.indexOf(value);
      this.buttonWidget = class_4185.method_46430(class_2561.method_43473(), widget -> {
         this.index.incrementAndGet();
         this.index.compareAndSet(this.values.size(), 0);
      }).method_46434(0, 0, 150, 20).method_46431();
      this.resetButton = class_4185.method_46430(resetButtonKey, widget -> this.index.set(this.getDefaultIndex()))
         .method_46434(0, 0, class_310.method_1551().field_1772.method_27525(resetButtonKey) + 6, 20)
         .method_46431();
      this.saveCallback = saveConsumer;
      this.widgets = Lists.newArrayList(new class_339[]{this.buttonWidget, this.resetButton});
      this.nameProvider = nameProvider == null
         ? t -> class_2561.method_43471(t instanceof SelectionListEntry.Translatable ? ((SelectionListEntry.Translatable)t).getKey() : t.toString())
         : nameProvider;
   }

   @Override
   public boolean isEdited() {
      return super.isEdited() || !Objects.equals(this.index.get(), this.original);
   }

   @Override
   public T getValue() {
      return (T)this.values.get(this.index.get());
   }

   @Override
   public Optional<T> getDefaultValue() {
      return this.defaultValue == null ? Optional.empty() : Optional.ofNullable(this.defaultValue.get());
   }

   @Override
   public void render(class_332 graphics, int index, int y, int x, int entryWidth, int entryHeight, int mouseX, int mouseY, boolean isHovered, float delta) {
      super.render(graphics, index, y, x, entryWidth, entryHeight, mouseX, mouseY, isHovered, delta);
      class_1041 window = class_310.method_1551().method_22683();
      this.resetButton.field_22763 = this.isEditable() && this.getDefaultValue().isPresent() && this.getDefaultIndex() != this.index.get();
      this.resetButton.method_46419(y);
      this.buttonWidget.field_22763 = this.isEditable();
      this.buttonWidget.method_46419(y);
      this.buttonWidget.method_25355(this.nameProvider.apply(this.getValue()));
      class_2561 displayedFieldName = this.getDisplayedFieldName();
      if (class_310.method_1551().field_1772.method_1726()) {
         graphics.method_35720(
            class_310.method_1551().field_1772,
            displayedFieldName.method_30937(),
            window.method_4486() - x - class_310.method_1551().field_1772.method_27525(displayedFieldName),
            y + 6,
            this.getPreferredTextColor()
         );
         this.resetButton.method_46421(x);
         this.buttonWidget.method_46421(x + this.resetButton.method_25368() + 2);
      } else {
         graphics.method_35720(class_310.method_1551().field_1772, displayedFieldName.method_30937(), x, y + 6, this.getPreferredTextColor());
         this.resetButton.method_46421(x + entryWidth - this.resetButton.method_25368());
         this.buttonWidget.method_46421(x + entryWidth - 150);
      }

      this.buttonWidget.method_25358(150 - this.resetButton.method_25368() - 2);
      this.resetButton.method_25394(graphics, mouseX, mouseY, delta);
      this.buttonWidget.method_25394(graphics, mouseX, mouseY, delta);
   }

   private int getDefaultIndex() {
      return Math.max(0, this.values.indexOf(this.defaultValue.get()));
   }

   public List<? extends class_364> method_25396() {
      return this.widgets;
   }

   @Override
   public List<? extends class_6379> narratables() {
      return this.widgets;
   }

   public interface Translatable {
      @NotNull
      String getKey();
   }
}
