package me.shedaniel.clothconfig2.gui.entries;

import com.google.common.collect.Lists;
import com.mojang.blaze3d.systems.RenderSystem;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import me.shedaniel.clothconfig2.api.Expandable;
import me.shedaniel.clothconfig2.api.ReferenceProvider;
import me.shedaniel.math.Rectangle;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.class_1109;
import net.minecraft.class_2561;
import net.minecraft.class_2960;
import net.minecraft.class_310;
import net.minecraft.class_332;
import net.minecraft.class_339;
import net.minecraft.class_3417;
import net.minecraft.class_364;
import net.minecraft.class_4185;
import net.minecraft.class_6379;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.ApiStatus.Internal;

@Environment(EnvType.CLIENT)
public abstract class BaseListEntry<T, C extends BaseListCell, SELF extends BaseListEntry<T, C, SELF>> extends TooltipListEntry<List<T>> implements Expandable {
   protected static final class_2960 CONFIG_TEX = class_2960.method_60655("cloth-config2", "textures/gui/cloth_config.png");
   @NotNull
   protected final List<C> cells;
   @NotNull
   protected final List<class_364> widgets;
   @NotNull
   protected final List<class_6379> narratables;
   protected boolean expanded;
   protected boolean insertButtonEnabled = true;
   protected boolean deleteButtonEnabled;
   protected boolean insertInFront;
   protected BaseListEntry<T, C, SELF>.ListLabelWidget labelWidget;
   protected class_339 resetWidget;
   @NotNull
   protected Function<SELF, C> createNewInstance;
   @NotNull
   protected Supplier<List<T>> defaultValue;
   @Nullable
   protected class_2561 addTooltip = class_2561.method_43471("text.cloth-config.list.add");
   @Nullable
   protected class_2561 removeTooltip = class_2561.method_43471("text.cloth-config.list.remove");

   @Internal
   public BaseListEntry(
      @NotNull class_2561 fieldName,
      @Nullable Supplier<Optional<class_2561[]>> tooltipSupplier,
      @Nullable Supplier<List<T>> defaultValue,
      @NotNull Function<SELF, C> createNewInstance,
      @Nullable Consumer<List<T>> saveConsumer,
      class_2561 resetButtonKey
   ) {
      this(fieldName, tooltipSupplier, defaultValue, createNewInstance, saveConsumer, resetButtonKey, false);
   }

   @Internal
   public BaseListEntry(
      @NotNull class_2561 fieldName,
      @Nullable Supplier<Optional<class_2561[]>> tooltipSupplier,
      @Nullable Supplier<List<T>> defaultValue,
      @NotNull Function<SELF, C> createNewInstance,
      @Nullable Consumer<List<T>> saveConsumer,
      class_2561 resetButtonKey,
      boolean requiresRestart
   ) {
      this(fieldName, tooltipSupplier, defaultValue, createNewInstance, saveConsumer, resetButtonKey, requiresRestart, true, true);
   }

   @Internal
   public BaseListEntry(
      @NotNull class_2561 fieldName,
      @Nullable Supplier<Optional<class_2561[]>> tooltipSupplier,
      @Nullable Supplier<List<T>> defaultValue,
      @NotNull Function<SELF, C> createNewInstance,
      @Nullable Consumer<List<T>> saveConsumer,
      class_2561 resetButtonKey,
      boolean requiresRestart,
      boolean deleteButtonEnabled,
      boolean insertInFront
   ) {
      super(fieldName, tooltipSupplier, requiresRestart);
      this.deleteButtonEnabled = deleteButtonEnabled;
      this.insertInFront = insertInFront;
      this.cells = Lists.newArrayList();
      this.labelWidget = new BaseListEntry.ListLabelWidget();
      this.widgets = Lists.newArrayList(new class_364[]{this.labelWidget});
      this.narratables = Lists.newArrayList();
      this.resetWidget = class_4185.method_46430(resetButtonKey, widget -> {
         this.widgets.removeAll(this.cells);
         this.narratables.removeAll(this.cells);

         for (C cell : this.cells) {
            cell.onDelete();
         }

         this.cells.clear();
         defaultValue.get().stream().map(this::getFromValue).forEach(this.cells::add);

         for (C cell : this.cells) {
            cell.onAdd();
         }

         this.widgets.addAll(this.cells);
         this.narratables.addAll(this.cells);
      }).method_46434(0, 0, class_310.method_1551().field_1772.method_27525(resetButtonKey) + 6, 20).method_46431();
      this.widgets.add(this.resetWidget);
      this.narratables.add(this.resetWidget);
      this.saveCallback = saveConsumer;
      this.createNewInstance = createNewInstance;
      this.defaultValue = defaultValue;
   }

   @Override
   public boolean isExpanded() {
      return this.expanded && this.isEnabled();
   }

   @Override
   public void setExpanded(boolean expanded) {
      this.expanded = expanded;
   }

   @Override
   public boolean isEdited() {
      return super.isEdited() ? true : this.cells.stream().anyMatch(BaseListCell::isEdited);
   }

   public boolean isMatchDefault() {
      Optional<List<T>> defaultValueOptional = this.getDefaultValue();
      if (defaultValueOptional.isPresent()) {
         List<T> value = this.getValue();
         List<T> defaultValue = defaultValueOptional.get();
         if (value.size() != defaultValue.size()) {
            return false;
         } else {
            for (int i = 0; i < value.size(); i++) {
               if (!Objects.equals(value.get(i), defaultValue.get(i))) {
                  return false;
               }
            }

            return true;
         }
      } else {
         return false;
      }
   }

   @Override
   public boolean isRequiresRestart() {
      return this.cells.stream().anyMatch(BaseListCell::isRequiresRestart);
   }

   @Override
   public void setRequiresRestart(boolean requiresRestart) {
   }

   public abstract SELF self();

   public boolean isDeleteButtonEnabled() {
      return this.deleteButtonEnabled && this.isEnabled();
   }

   public boolean isInsertButtonEnabled() {
      return this.insertButtonEnabled && this.isEnabled();
   }

   public void setDeleteButtonEnabled(boolean deleteButtonEnabled) {
      this.deleteButtonEnabled = deleteButtonEnabled;
   }

   public void setInsertButtonEnabled(boolean insertButtonEnabled) {
      this.insertButtonEnabled = insertButtonEnabled;
   }

   protected abstract C getFromValue(T var1);

   @NotNull
   public Function<SELF, C> getCreateNewInstance() {
      return this.createNewInstance;
   }

   public void setCreateNewInstance(@NotNull Function<SELF, C> createNewInstance) {
      this.createNewInstance = createNewInstance;
   }

   @Nullable
   public class_2561 getAddTooltip() {
      return this.addTooltip;
   }

   public void setAddTooltip(@Nullable class_2561 addTooltip) {
      this.addTooltip = addTooltip;
   }

   @Nullable
   public class_2561 getRemoveTooltip() {
      return this.removeTooltip;
   }

   public void setRemoveTooltip(@Nullable class_2561 removeTooltip) {
      this.removeTooltip = removeTooltip;
   }

   @Override
   public Optional<List<T>> getDefaultValue() {
      return this.defaultValue == null ? Optional.empty() : Optional.ofNullable(this.defaultValue.get());
   }

   @Override
   public int getItemHeight() {
      if (!this.isExpanded()) {
         return 24;
      } else {
         int i = 24;

         for (BaseListCell entry : this.cells) {
            i += entry.getCellHeight();
         }

         return i;
      }
   }

   public List<? extends class_364> method_25396() {
      if (!this.isExpanded()) {
         List<class_364> elements = new ArrayList<>(this.widgets);
         elements.removeAll(this.cells);
         return elements;
      } else {
         return this.widgets;
      }
   }

   @Override
   public List<? extends class_6379> narratables() {
      return this.narratables;
   }

   @Override
   public Optional<class_2561> getError() {
      List<class_2561> errors = this.cells
         .stream()
         .map(BaseListCell::getConfigError)
         .filter(Optional::isPresent)
         .map(Optional::get)
         .collect(Collectors.toList());
      return errors.size() > 1 ? Optional.of(class_2561.method_43471("text.cloth-config.multi_error")) : errors.stream().findFirst();
   }

   @Override
   public void save() {
      for (C cell : this.cells) {
         if (cell instanceof ReferenceProvider) {
            ((ReferenceProvider)cell).provideReferenceEntry().save();
         }
      }

      super.save();
   }

   @Override
   public Rectangle getEntryArea(int x, int y, int entryWidth, int entryHeight) {
      this.labelWidget.rectangle.x = x - 15;
      this.labelWidget.rectangle.y = y;
      this.labelWidget.rectangle.width = entryWidth + 15;
      this.labelWidget.rectangle.height = 24;
      return new Rectangle(this.getParent().left, y, this.getParent().right - this.getParent().left, 20);
   }

   protected boolean isInsideCreateNew(double mouseX, double mouseY) {
      return this.isInsertButtonEnabled()
         && mouseX >= this.labelWidget.rectangle.x + 12
         && mouseY >= this.labelWidget.rectangle.y + 3
         && mouseX <= this.labelWidget.rectangle.x + 12 + 11
         && mouseY <= this.labelWidget.rectangle.y + 3 + 11;
   }

   protected boolean isInsideDelete(double mouseX, double mouseY) {
      return this.isDeleteButtonEnabled()
         && mouseX >= this.labelWidget.rectangle.x + (this.isInsertButtonEnabled() ? 25 : 12)
         && mouseY >= this.labelWidget.rectangle.y + 3
         && mouseX <= this.labelWidget.rectangle.x + (this.isInsertButtonEnabled() ? 25 : 12) + 11
         && mouseY <= this.labelWidget.rectangle.y + 3 + 11;
   }

   @Override
   public Optional<class_2561[]> getTooltip(int mouseX, int mouseY) {
      if (this.addTooltip != null && this.isInsideCreateNew(mouseX, mouseY)) {
         return Optional.of(new class_2561[]{this.addTooltip});
      } else {
         return this.removeTooltip != null && this.isInsideDelete(mouseX, mouseY)
            ? Optional.of(new class_2561[]{this.removeTooltip})
            : super.getTooltip(mouseX, mouseY);
      }
   }

   @Override
   public void render(class_332 graphics, int index, int y, int x, int entryWidth, int entryHeight, int mouseX, int mouseY, boolean isHovered, float delta) {
      super.render(graphics, index, y, x, entryWidth, entryHeight, mouseX, mouseY, isHovered, delta);
      RenderSystem.setShaderTexture(0, CONFIG_TEX);
      RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
      BaseListCell focused = this.isExpanded() && this.method_25399() != null && this.method_25399() instanceof BaseListCell
         ? (BaseListCell)this.method_25399()
         : null;
      boolean insideLabel = this.labelWidget.rectangle.contains(mouseX, mouseY);
      boolean insideCreateNew = this.isInsideCreateNew(mouseX, mouseY);
      boolean insideDelete = this.isInsideDelete(mouseX, mouseY);
      graphics.method_25302(
         CONFIG_TEX,
         x - 15,
         y + 5,
         33,
         (this.isEnabled() ? (insideLabel && !insideCreateNew && !insideDelete ? 18 : 0) : 36) + (this.isExpanded() ? 9 : 0),
         9,
         9
      );
      if (this.isInsertButtonEnabled()) {
         graphics.method_25302(CONFIG_TEX, x - 15 + 13, y + 5, 42, insideCreateNew ? 9 : 0, 9, 9);
      }

      if (this.isDeleteButtonEnabled()) {
         graphics.method_25302(CONFIG_TEX, x - 15 + (this.isInsertButtonEnabled() ? 26 : 13), y + 5, 51, focused == null ? 0 : (insideDelete ? 18 : 9), 9, 9);
      }

      this.resetWidget.method_46421(x + entryWidth - this.resetWidget.method_25368());
      this.resetWidget.method_46419(y);
      this.resetWidget.field_22763 = this.isEditable() && this.getDefaultValue().isPresent() && !this.isMatchDefault();
      this.resetWidget.method_25394(graphics, mouseX, mouseY, delta);
      int offset = (!this.isInsertButtonEnabled() && !this.isDeleteButtonEnabled() ? 0 : 6)
         + (this.isInsertButtonEnabled() ? 9 : 0)
         + (this.isDeleteButtonEnabled() ? 9 : 0);
      graphics.method_35720(
         class_310.method_1551().field_1772,
         this.getDisplayedFieldName().method_30937(),
         x + offset,
         y + 6,
         insideLabel && !this.resetWidget.method_25405(mouseX, mouseY) && !insideDelete && !insideCreateNew ? -1638890 : this.getPreferredTextColor()
      );
      if (this.isExpanded()) {
         int yy = y + 24;

         for (BaseListCell cell : this.cells) {
            cell.render(
               graphics,
               -1,
               yy,
               x + 14,
               entryWidth - 14,
               cell.getCellHeight(),
               mouseX,
               mouseY,
               this.getParent().getFocused() != null
                  && this.getParent().getFocused().equals(this)
                  && this.method_25399() != null
                  && this.method_25399().equals(cell),
               delta
            );
            yy += cell.getCellHeight();
         }
      }
   }

   @Override
   public void updateSelected(boolean isSelected) {
      for (C cell : this.cells) {
         cell.updateSelected(isSelected && this.method_25399() == cell && this.isExpanded());
      }
   }

   @Override
   public int getInitialReferenceOffset() {
      return 24;
   }

   public boolean insertInFront() {
      return this.insertInFront;
   }

   public class ListLabelWidget implements class_364 {
      protected Rectangle rectangle = new Rectangle();

      public boolean method_25402(double mouseX, double mouseY, int int_1) {
         if (!BaseListEntry.this.isEnabled()) {
            return false;
         } else if (BaseListEntry.this.resetWidget.method_25405(mouseX, mouseY)) {
            return false;
         } else if (BaseListEntry.this.isInsideCreateNew(mouseX, mouseY)) {
            BaseListEntry.this.setExpanded(true);
            C cell;
            if (BaseListEntry.this.insertInFront()) {
               BaseListEntry.this.cells.add(0, cell = BaseListEntry.this.createNewInstance.apply(BaseListEntry.this.self()));
               BaseListEntry.this.widgets.add(0, cell);
            } else {
               BaseListEntry.this.cells.add(cell = BaseListEntry.this.createNewInstance.apply(BaseListEntry.this.self()));
               BaseListEntry.this.widgets.add(cell);
            }

            cell.onAdd();
            class_310.method_1551().method_1483().method_4873(class_1109.method_47978(class_3417.field_15015, 1.0F));
            return true;
         } else if (BaseListEntry.this.isDeleteButtonEnabled() && BaseListEntry.this.isInsideDelete(mouseX, mouseY)) {
            class_364 focused = BaseListEntry.this.method_25399();
            if (BaseListEntry.this.isExpanded() && focused instanceof BaseListCell) {
               ((BaseListCell)focused).onDelete();
               BaseListEntry.this.cells.remove(focused);
               BaseListEntry.this.widgets.remove(focused);
               class_310.method_1551().method_1483().method_4873(class_1109.method_47978(class_3417.field_15015, 1.0F));
            }

            return true;
         } else if (this.rectangle.contains(mouseX, mouseY)) {
            BaseListEntry.this.setExpanded(!BaseListEntry.this.expanded);
            class_310.method_1551().method_1483().method_4873(class_1109.method_47978(class_3417.field_15015, 1.0F));
            return true;
         } else {
            return false;
         }
      }

      public void method_25365(boolean bl) {
      }

      public boolean method_25370() {
         return false;
      }
   }
}
