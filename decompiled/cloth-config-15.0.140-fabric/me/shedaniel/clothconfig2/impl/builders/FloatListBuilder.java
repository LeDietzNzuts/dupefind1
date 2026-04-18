package me.shedaniel.clothconfig2.impl.builders;

import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import me.shedaniel.clothconfig2.gui.entries.FloatListListEntry;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.class_2561;
import org.jetbrains.annotations.NotNull;

@Environment(EnvType.CLIENT)
public class FloatListBuilder extends AbstractRangeListBuilder<Float, FloatListListEntry, FloatListBuilder> {
   private Function<FloatListListEntry, FloatListListEntry.FloatListCell> createNewInstance;

   public FloatListBuilder(class_2561 resetButtonKey, class_2561 fieldNameKey, List<Float> value) {
      super(resetButtonKey, fieldNameKey);
      this.value = (List<T>)value;
   }

   @Override
   public Function<Float, Optional<class_2561>> getCellErrorSupplier() {
      return super.getCellErrorSupplier();
   }

   public FloatListBuilder setCellErrorSupplier(Function<Float, Optional<class_2561>> cellErrorSupplier) {
      return (FloatListBuilder)super.setCellErrorSupplier(cellErrorSupplier);
   }

   public FloatListBuilder setDeleteButtonEnabled(boolean deleteButtonEnabled) {
      return (FloatListBuilder)super.setDeleteButtonEnabled(deleteButtonEnabled);
   }

   public FloatListBuilder setErrorSupplier(Function<List<Float>, Optional<class_2561>> errorSupplier) {
      return (FloatListBuilder)super.setErrorSupplier(errorSupplier);
   }

   public FloatListBuilder setInsertInFront(boolean insertInFront) {
      return (FloatListBuilder)super.setInsertInFront(insertInFront);
   }

   public FloatListBuilder setAddButtonTooltip(class_2561 addTooltip) {
      return (FloatListBuilder)super.setAddButtonTooltip(addTooltip);
   }

   public FloatListBuilder setRemoveButtonTooltip(class_2561 removeTooltip) {
      return (FloatListBuilder)super.setRemoveButtonTooltip(removeTooltip);
   }

   public FloatListBuilder requireRestart() {
      return (FloatListBuilder)super.requireRestart();
   }

   public FloatListBuilder setCreateNewInstance(Function<FloatListListEntry, FloatListListEntry.FloatListCell> createNewInstance) {
      this.createNewInstance = createNewInstance;
      return this;
   }

   public FloatListBuilder setExpanded(boolean expanded) {
      return (FloatListBuilder)super.setExpanded(expanded);
   }

   public FloatListBuilder setSaveConsumer(Consumer<List<Float>> saveConsumer) {
      return (FloatListBuilder)super.setSaveConsumer(saveConsumer);
   }

   public FloatListBuilder setDefaultValue(Supplier<List<Float>> defaultValue) {
      return (FloatListBuilder)super.setDefaultValue(defaultValue);
   }

   public FloatListBuilder setMin(float min) {
      this.min = min;
      return this;
   }

   public FloatListBuilder setMax(float max) {
      this.max = max;
      return this;
   }

   public FloatListBuilder removeMin() {
      return (FloatListBuilder)super.removeMin();
   }

   public FloatListBuilder removeMax() {
      return (FloatListBuilder)super.removeMax();
   }

   public FloatListBuilder setDefaultValue(List<Float> defaultValue) {
      return (FloatListBuilder)super.setDefaultValue(defaultValue);
   }

   public FloatListBuilder setTooltipSupplier(Function<List<Float>, Optional<class_2561[]>> tooltipSupplier) {
      return (FloatListBuilder)super.setTooltipSupplier(tooltipSupplier);
   }

   public FloatListBuilder setTooltipSupplier(Supplier<Optional<class_2561[]>> tooltipSupplier) {
      return (FloatListBuilder)super.setTooltipSupplier(tooltipSupplier);
   }

   public FloatListBuilder setTooltip(Optional<class_2561[]> tooltip) {
      return (FloatListBuilder)super.setTooltip(tooltip);
   }

   public FloatListBuilder setTooltip(class_2561... tooltip) {
      return (FloatListBuilder)super.setTooltip(tooltip);
   }

   @NotNull
   public FloatListListEntry build() {
      FloatListListEntry entry = new FloatListListEntry(
         this.getFieldNameKey(),
         this.value,
         this.isExpanded(),
         null,
         this.getSaveConsumer(),
         this.defaultValue,
         this.getResetButtonKey(),
         this.isRequireRestart(),
         this.isDeleteButtonEnabled(),
         this.isInsertInFront()
      );
      if (this.min != null) {
         entry.setMinimum(this.min);
      }

      if (this.max != null) {
         entry.setMaximum(this.max);
      }

      if (this.createNewInstance != null) {
         entry.setCreateNewInstance(this.createNewInstance);
      }

      entry.setInsertButtonEnabled(this.isInsertButtonEnabled());
      entry.setCellErrorSupplier(this.cellErrorSupplier);
      entry.setTooltipSupplier(() -> (Optional<class_2561[]>)this.getTooltipSupplier().apply(entry.getValue()));
      entry.setAddTooltip(this.getAddTooltip());
      entry.setRemoveTooltip(this.getRemoveTooltip());
      if (this.errorSupplier != null) {
         entry.setErrorSupplier(() -> this.errorSupplier.apply(entry.getValue()));
      }

      return this.finishBuilding(entry);
   }
}
