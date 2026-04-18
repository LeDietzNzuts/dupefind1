package me.shedaniel.clothconfig2.gui.entries;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.google.common.collect.UnmodifiableIterator;
import com.mojang.blaze3d.systems.RenderSystem;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import me.shedaniel.clothconfig2.ClothConfigInitializer;
import me.shedaniel.clothconfig2.api.ScissorsHandler;
import me.shedaniel.clothconfig2.api.ScrollingContainer;
import me.shedaniel.math.Rectangle;
import me.shedaniel.math.impl.PointHelper;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.class_1041;
import net.minecraft.class_2561;
import net.minecraft.class_287;
import net.minecraft.class_289;
import net.minecraft.class_290;
import net.minecraft.class_310;
import net.minecraft.class_327;
import net.minecraft.class_332;
import net.minecraft.class_342;
import net.minecraft.class_3532;
import net.minecraft.class_362;
import net.minecraft.class_364;
import net.minecraft.class_4068;
import net.minecraft.class_4185;
import net.minecraft.class_6379;
import net.minecraft.class_757;
import net.minecraft.class_8016;
import net.minecraft.class_8023;
import net.minecraft.class_293.class_5596;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.ApiStatus.Internal;

@Environment(EnvType.CLIENT)
public class DropdownBoxEntry<T> extends TooltipListEntry<T> {
   protected class_4185 resetButton;
   protected DropdownBoxEntry.SelectionElement<T> selectionElement;
   @NotNull
   private final Supplier<T> defaultValue;
   private boolean suggestionMode = true;

   @Deprecated
   @Internal
   public DropdownBoxEntry(
      class_2561 fieldName,
      @NotNull class_2561 resetButtonKey,
      @Nullable Supplier<Optional<class_2561[]>> tooltipSupplier,
      boolean requiresRestart,
      @Nullable Supplier<T> defaultValue,
      @Nullable Consumer<T> saveConsumer,
      @Nullable Iterable<T> selections,
      @NotNull DropdownBoxEntry.SelectionTopCellElement<T> topRenderer,
      @NotNull DropdownBoxEntry.SelectionCellCreator<T> cellCreator
   ) {
      super(fieldName, tooltipSupplier, requiresRestart);
      this.defaultValue = defaultValue;
      this.saveCallback = saveConsumer;
      this.resetButton = class_4185.method_46430(resetButtonKey, widget -> this.selectionElement.topRenderer.setValue(defaultValue.get()))
         .method_46434(0, 0, class_310.method_1551().field_1772.method_27525(resetButtonKey) + 6, 20)
         .method_46431();
      this.selectionElement = new DropdownBoxEntry.SelectionElement<>(
         this,
         new Rectangle(0, 0, 150, 20),
         new DropdownBoxEntry.DefaultDropdownMenuElement<>(selections == null ? ImmutableList.of() : ImmutableList.copyOf(selections)),
         topRenderer,
         cellCreator
      );
   }

   @Override
   public void render(class_332 graphics, int index, int y, int x, int entryWidth, int entryHeight, int mouseX, int mouseY, boolean isHovered, float delta) {
      super.render(graphics, index, y, x, entryWidth, entryHeight, mouseX, mouseY, isHovered, delta);
      class_1041 window = class_310.method_1551().method_22683();
      this.resetButton.field_22763 = this.isEditable()
         && this.getDefaultValue().isPresent()
         && (!this.defaultValue.get().equals(this.getValue()) || this.getConfigError().isPresent());
      this.resetButton.method_46419(y);
      this.selectionElement.active = this.isEditable();
      this.selectionElement.bounds.y = y;
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
         this.selectionElement.bounds.x = x + this.resetButton.method_25368() + 1;
      } else {
         graphics.method_35720(class_310.method_1551().field_1772, displayedFieldName.method_30937(), x, y + 6, this.getPreferredTextColor());
         this.resetButton.method_46421(x + entryWidth - this.resetButton.method_25368());
         this.selectionElement.bounds.x = x + entryWidth - 150 + 1;
      }

      this.selectionElement.bounds.width = 150 - this.resetButton.method_25368() - 4;
      this.resetButton.method_25394(graphics, mouseX, mouseY, delta);
      this.selectionElement.method_25394(graphics, mouseX, mouseY, delta);
   }

   @Override
   public boolean isEdited() {
      return this.selectionElement.topRenderer.isEdited();
   }

   public boolean isSuggestionMode() {
      return this.suggestionMode;
   }

   public void setSuggestionMode(boolean suggestionMode) {
      this.suggestionMode = suggestionMode;
   }

   @Override
   public void updateSelected(boolean isSelected) {
      this.selectionElement.topRenderer.isSelected = isSelected;
      this.selectionElement.menu.isSelected = isSelected;
   }

   @NotNull
   public ImmutableList<T> getSelections() {
      return this.selectionElement.menu.getSelections();
   }

   @Override
   public T getValue() {
      return this.selectionElement.getValue();
   }

   @Deprecated
   public DropdownBoxEntry.SelectionElement<T> getSelectionElement() {
      return this.selectionElement;
   }

   @Override
   public Optional<T> getDefaultValue() {
      return this.defaultValue == null ? Optional.empty() : Optional.ofNullable(this.defaultValue.get());
   }

   public List<? extends class_364> method_25396() {
      return Lists.newArrayList(new class_364[]{this.selectionElement, this.resetButton});
   }

   @Override
   public List<? extends class_6379> narratables() {
      return Collections.singletonList(this.resetButton);
   }

   @Override
   public Optional<class_2561> getError() {
      return this.selectionElement.topRenderer.getError();
   }

   @Override
   public void lateRender(class_332 graphics, int mouseX, int mouseY, float delta) {
      this.selectionElement.lateRender(graphics, mouseX, mouseY, delta);
   }

   @Override
   public int getMorePossibleHeight() {
      return this.selectionElement.getMorePossibleHeight();
   }

   @Override
   public boolean method_25401(double double_1, double double_2, double amountX, double amountY) {
      return this.selectionElement.method_25401(double_1, double_2, amountX, amountY);
   }

   public static class DefaultDropdownMenuElement<R> extends DropdownBoxEntry.DropdownMenuElement<R> {
      @NotNull
      protected ImmutableList<R> selections;
      @NotNull
      protected List<DropdownBoxEntry.SelectionCellElement<R>> cells;
      @NotNull
      protected List<DropdownBoxEntry.SelectionCellElement<R>> currentElements;
      protected class_2561 lastSearchKeyword = class_2561.method_43473();
      protected Rectangle lastRectangle;
      protected boolean scrolling;
      protected double scroll;
      protected double target;
      protected long start;
      protected long duration;

      public DefaultDropdownMenuElement(@NotNull ImmutableList<R> selections) {
         this.selections = selections;
         this.cells = Lists.newArrayList();
         this.currentElements = Lists.newArrayList();
      }

      public double getMaxScroll() {
         return this.getCellCreator().getCellHeight() * this.currentElements.size();
      }

      protected double getMaxScrollPosition() {
         return Math.max(0.0, this.getMaxScroll() - this.getHeight());
      }

      @NotNull
      @Override
      public ImmutableList<R> getSelections() {
         return this.selections;
      }

      @Override
      public void initCells() {
         UnmodifiableIterator var1 = this.getSelections().iterator();

         while (var1.hasNext()) {
            R selection = (R)var1.next();
            this.cells.add(this.getCellCreator().create(selection));
         }

         for (DropdownBoxEntry.SelectionCellElement<R> cell : this.cells) {
            cell.entry = this.getEntry();
         }

         this.search();
      }

      public void search() {
         if (this.isSuggestionMode()) {
            this.currentElements.clear();
            String keyword = this.lastSearchKeyword.getString().toLowerCase();

            for (DropdownBoxEntry.SelectionCellElement<R> cell : this.cells) {
               class_2561 key = cell.getSearchKey();
               if (key == null || key.getString().toLowerCase().contains(keyword)) {
                  this.currentElements.add(cell);
               }
            }

            if (!keyword.isEmpty()) {
               Comparator<DropdownBoxEntry.SelectionCellElement<?>> c = Comparator.comparingDouble(
                  i -> i.getSearchKey() == null ? Double.MAX_VALUE : this.similarity(i.getSearchKey().getString(), keyword)
               );
               this.currentElements.sort(c.reversed());
            }

            this.scrollTo(0.0, false);
         } else {
            this.currentElements.clear();
            this.currentElements.addAll(this.cells);
         }
      }

      protected int editDistance(String s1, String s2) {
         s1 = s1.toLowerCase();
         s2 = s2.toLowerCase();
         int[] costs = new int[s2.length() + 1];

         for (int i = 0; i <= s1.length(); i++) {
            int lastValue = i;

            for (int j = 0; j <= s2.length(); j++) {
               if (i == 0) {
                  costs[j] = j;
               } else if (j > 0) {
                  int newValue = costs[j - 1];
                  if (s1.charAt(i - 1) != s2.charAt(j - 1)) {
                     newValue = Math.min(Math.min(newValue, lastValue), costs[j]) + 1;
                  }

                  costs[j - 1] = lastValue;
                  lastValue = newValue;
               }
            }

            if (i > 0) {
               costs[s2.length()] = lastValue;
            }
         }

         return costs[s2.length()];
      }

      protected double similarity(String s1, String s2) {
         String longer = s1;
         String shorter = s2;
         if (s1.length() < s2.length()) {
            longer = s2;
            shorter = s1;
         }

         int longerLength = longer.length();
         return longerLength == 0 ? 1.0 : (double)(longerLength - this.editDistance(longer, shorter)) / longerLength;
      }

      @Override
      public void render(class_332 graphics, int mouseX, int mouseY, Rectangle rectangle, float delta) {
         if (!this.getEntry().selectionElement.topRenderer.getSearchTerm().equals(this.lastSearchKeyword)) {
            this.lastSearchKeyword = this.getEntry().selectionElement.topRenderer.getSearchTerm();
            this.search();
         }

         this.updatePosition(delta);
         this.lastRectangle = rectangle.clone();
         this.lastRectangle.translate(0, -1);
      }

      private void updatePosition(float delta) {
         double[] target = new double[]{this.target};
         this.scroll = ScrollingContainer.handleScrollingPosition(target, this.scroll, this.getMaxScrollPosition(), delta, this.start, this.duration);
         this.target = target[0];
      }

      @Override
      public void lateRender(class_332 graphics, int mouseX, int mouseY, float delta) {
         int last10Height = this.getHeight();
         int cWidth = this.getCellCreator().getCellWidth();
         graphics.method_25294(
            this.lastRectangle.x,
            this.lastRectangle.y + this.lastRectangle.height,
            this.lastRectangle.x + cWidth,
            this.lastRectangle.y + this.lastRectangle.height + last10Height + 1,
            this.isExpanded() ? -1 : -6250336
         );
         graphics.method_25294(
            this.lastRectangle.x + 1,
            this.lastRectangle.y + this.lastRectangle.height + 1,
            this.lastRectangle.x + cWidth - 1,
            this.lastRectangle.y + this.lastRectangle.height + last10Height,
            -16777216
         );
         graphics.method_51448().method_22903();
         graphics.method_51448().method_46416(0.0F, 0.0F, 300.0F);
         ScissorsHandler.INSTANCE
            .scissor(new Rectangle(this.lastRectangle.x, this.lastRectangle.y + this.lastRectangle.height + 1, cWidth - 6, last10Height - 1));
         double yy = this.lastRectangle.y + this.lastRectangle.height - this.scroll;

         for (DropdownBoxEntry.SelectionCellElement<R> cell : this.currentElements) {
            if (yy + this.getCellCreator().getCellHeight() >= this.lastRectangle.y + this.lastRectangle.height
               && yy <= this.lastRectangle.y + this.lastRectangle.height + last10Height + 1) {
               graphics.method_25294(
                  this.lastRectangle.x + 1,
                  (int)yy,
                  this.lastRectangle.x + this.getCellCreator().getCellWidth(),
                  (int)yy + this.getCellCreator().getCellHeight(),
                  -16777216
               );
               cell.render(
                  graphics,
                  mouseX,
                  mouseY,
                  this.lastRectangle.x,
                  (int)yy,
                  this.getMaxScrollPosition() > 6.0 ? this.getCellCreator().getCellWidth() - 6 : this.getCellCreator().getCellWidth(),
                  this.getCellCreator().getCellHeight(),
                  delta
               );
            } else {
               cell.dontRender(graphics, delta);
            }

            yy += this.getCellCreator().getCellHeight();
         }

         ScissorsHandler.INSTANCE.removeLastScissor();
         if (this.currentElements.isEmpty()) {
            class_327 textRenderer = class_310.method_1551().field_1772;
            class_2561 text = class_2561.method_43471("text.cloth-config.dropdown.value.unknown");
            graphics.method_35720(
               textRenderer,
               text.method_30937(),
               (int)(this.lastRectangle.x + this.getCellCreator().getCellWidth() / 2.0F - textRenderer.method_27525(text) / 2.0F),
               this.lastRectangle.y + this.lastRectangle.height + 3,
               -1
            );
         }

         if (this.getMaxScrollPosition() > 6.0) {
            RenderSystem.setShader(class_757::method_34543);
            int scrollbarPositionMinX = this.lastRectangle.x + this.getCellCreator().getCellWidth() - 6;
            int scrollbarPositionMaxX = scrollbarPositionMinX + 6;
            int height = (int)(last10Height * last10Height / this.getMaxScrollPosition());
            height = class_3532.method_15340(height, 32, last10Height - 8);
            height = (int)(
               height
                  - Math.min(
                     this.scroll < 0.0
                        ? (int)(-this.scroll)
                        : (this.scroll > this.getMaxScrollPosition() ? (int)this.scroll - this.getMaxScrollPosition() : 0.0),
                     height * 0.95
                  )
            );
            height = Math.max(10, height);
            int minY = (int)Math.min(
               Math.max(
                  (int)this.scroll * (last10Height - height) / this.getMaxScrollPosition() + (this.lastRectangle.y + this.lastRectangle.height + 1),
                  (double)(this.lastRectangle.y + this.lastRectangle.height + 1)
               ),
               (double)(this.lastRectangle.y + this.lastRectangle.height + 1 + last10Height - height)
            );
            int bottomc = new Rectangle(scrollbarPositionMinX, minY, scrollbarPositionMaxX - scrollbarPositionMinX, height).contains(PointHelper.ofMouse())
               ? 168
               : 128;
            int topc = new Rectangle(scrollbarPositionMinX, minY, scrollbarPositionMaxX - scrollbarPositionMinX, height).contains(PointHelper.ofMouse())
               ? 222
               : 172;
            class_289 tesselator = class_289.method_1348();
            class_287 buffer = tesselator.method_60827(class_5596.field_27382, class_290.field_1576);
            buffer.method_22912(scrollbarPositionMinX, minY + height, 0.0F).method_1336(bottomc, bottomc, bottomc, 255);
            buffer.method_22912(scrollbarPositionMaxX, minY + height, 0.0F).method_1336(bottomc, bottomc, bottomc, 255);
            buffer.method_22912(scrollbarPositionMaxX, minY, 0.0F).method_1336(bottomc, bottomc, bottomc, 255);
            buffer.method_22912(scrollbarPositionMinX, minY, 0.0F).method_1336(bottomc, bottomc, bottomc, 255);
            buffer.method_22912(scrollbarPositionMinX, minY + height - 1, 0.0F).method_1336(topc, topc, topc, 255);
            buffer.method_22912(scrollbarPositionMaxX - 1, minY + height - 1, 0.0F).method_1336(topc, topc, topc, 255);
            buffer.method_22912(scrollbarPositionMaxX - 1, minY, 0.0F).method_1336(topc, topc, topc, 255);
            buffer.method_22912(scrollbarPositionMinX, minY, 0.0F).method_1336(topc, topc, topc, 255);
         }

         graphics.method_51448().method_22909();
      }

      @Override
      public int getHeight() {
         return Math.max(Math.min(this.getCellCreator().getDropBoxMaxHeight(), (int)this.getMaxScroll()), 14);
      }

      public boolean method_25405(double mouseX, double mouseY) {
         return this.isExpanded()
            && mouseX >= this.lastRectangle.x
            && mouseX <= this.lastRectangle.x + this.getCellCreator().getCellWidth()
            && mouseY >= this.lastRectangle.y + this.lastRectangle.height
            && mouseY <= this.lastRectangle.y + this.lastRectangle.height + this.getHeight() + 1;
      }

      public boolean method_25403(double double_1, double double_2, int int_1, double double_3, double double_4) {
         if (!this.isExpanded()) {
            return false;
         } else if (int_1 == 0 && this.scrolling) {
            if (double_2 < (double)this.lastRectangle.y + this.lastRectangle.height) {
               this.scrollTo(0.0, false);
            } else if (double_2 > (double)this.lastRectangle.y + this.lastRectangle.height + this.getHeight()) {
               this.scrollTo(this.getMaxScrollPosition(), false);
            } else {
               double double_5 = Math.max(1.0, this.getMaxScrollPosition());
               int int_2 = this.getHeight();
               int int_3 = class_3532.method_15340((int)(int_2 * int_2 / (float)this.getMaxScrollPosition()), 32, int_2 - 8);
               double double_6 = Math.max(1.0, double_5 / (int_2 - int_3));
               this.offset(double_4 * double_6, false);
            }

            this.target = class_3532.method_15350(this.target, 0.0, this.getMaxScrollPosition());
            return true;
         } else {
            return false;
         }
      }

      public boolean method_25401(double mouseX, double mouseY, double amountX, double amountY) {
         if (this.method_25405(mouseX, mouseY) && amountY != 0.0) {
            this.offset(ClothConfigInitializer.getScrollStep() * -amountY, true);
            return true;
         } else {
            return false;
         }
      }

      protected void updateScrollingState(double double_1, double double_2, int int_1) {
         this.scrolling = this.isExpanded()
            && this.lastRectangle != null
            && int_1 == 0
            && double_1 >= (double)this.lastRectangle.x + this.getCellCreator().getCellWidth() - 6.0
            && double_1 < this.lastRectangle.x + this.getCellCreator().getCellWidth();
      }

      public boolean method_25402(double double_1, double double_2, int int_1) {
         if (!this.isExpanded()) {
            return false;
         } else {
            this.updateScrollingState(double_1, double_2, int_1);
            return super.method_25402(double_1, double_2, int_1) || this.scrolling;
         }
      }

      public void offset(double value, boolean animated) {
         this.scrollTo(this.target + value, animated);
      }

      public void scrollTo(double value, boolean animated) {
         this.scrollTo(value, animated, ClothConfigInitializer.getScrollDuration());
      }

      public void scrollTo(double value, boolean animated, long duration) {
         this.target = ScrollingContainer.clampExtension(value, this.getMaxScrollPosition());
         if (animated) {
            this.start = System.currentTimeMillis();
            this.duration = duration;
         } else {
            this.scroll = this.target;
         }
      }

      @Override
      public List<DropdownBoxEntry.SelectionCellElement<R>> method_25396() {
         return this.currentElements;
      }
   }

   public static class DefaultSelectionCellCreator<R> extends DropdownBoxEntry.SelectionCellCreator<R> {
      protected Function<R, class_2561> toTextFunction;

      public DefaultSelectionCellCreator(Function<R, class_2561> toTextFunction) {
         this.toTextFunction = toTextFunction;
      }

      public DefaultSelectionCellCreator() {
         this(r -> class_2561.method_43470(r.toString()));
      }

      @Override
      public DropdownBoxEntry.SelectionCellElement<R> create(R selection) {
         return new DropdownBoxEntry.DefaultSelectionCellElement<>(selection, this.toTextFunction);
      }

      @Override
      public int getCellHeight() {
         return 14;
      }

      @Override
      public int getDropBoxMaxHeight() {
         return this.getCellHeight() * 7;
      }
   }

   public static class DefaultSelectionCellElement<R> extends DropdownBoxEntry.SelectionCellElement<R> {
      protected R r;
      protected int x;
      protected int y;
      protected int width;
      protected int height;
      protected boolean rendering;
      protected Function<R, class_2561> toTextFunction;

      public DefaultSelectionCellElement(R r, Function<R, class_2561> toTextFunction) {
         this.r = r;
         this.toTextFunction = toTextFunction;
      }

      @Override
      public void render(class_332 graphics, int mouseX, int mouseY, int x, int y, int width, int height, float delta) {
         this.rendering = true;
         this.x = x;
         this.y = y;
         this.width = width;
         this.height = height;
         boolean b = mouseX >= x && mouseX <= x + width && mouseY >= y && mouseY <= y + height;
         if (b) {
            graphics.method_25294(x + 1, y + 1, x + width - 1, y + height - 1, -15132391);
         }

         graphics.method_35720(class_310.method_1551().field_1772, this.toTextFunction.apply(this.r).method_30937(), x + 6, y + 3, b ? 16777215 : 8947848);
      }

      @Override
      public void dontRender(class_332 graphics, float delta) {
         this.rendering = false;
      }

      @Nullable
      @Override
      public class_2561 getSearchKey() {
         return this.toTextFunction.apply(this.r);
      }

      @Nullable
      @Override
      public R getSelection() {
         return this.r;
      }

      public List<? extends class_364> method_25396() {
         return Collections.emptyList();
      }

      public boolean method_25402(double mouseX, double mouseY, int int_1) {
         boolean b = this.rendering && mouseX >= this.x && mouseX <= this.x + this.width && mouseY >= this.y && mouseY <= this.y + this.height;
         if (b) {
            this.getEntry().selectionElement.topRenderer.setValue(this.r);
            this.getEntry().selectionElement.method_25395(null);
            this.getEntry().selectionElement.dontReFocus = true;
            return true;
         } else {
            return false;
         }
      }
   }

   public static class DefaultSelectionTopCellElement<R> extends DropdownBoxEntry.SelectionTopCellElement<R> {
      protected class_342 textFieldWidget;
      protected Function<String, R> toObjectFunction;
      protected Function<R, class_2561> toTextFunction;
      protected final R original;
      protected R value;

      public DefaultSelectionTopCellElement(R value, Function<String, R> toObjectFunction, Function<R, class_2561> toTextFunction) {
         this.original = Objects.requireNonNull(value);
         this.value = Objects.requireNonNull(value);
         this.toObjectFunction = Objects.requireNonNull(toObjectFunction);
         this.toTextFunction = Objects.requireNonNull(toTextFunction);
         this.textFieldWidget = new class_342(class_310.method_1551().field_1772, 0, 0, 148, 18, class_2561.method_43473()) {
            public void method_48579(class_332 graphics, int mouseX, int mouseY, float delta) {
               this.method_25365(
                  DefaultSelectionTopCellElement.this.isSuggestionMode()
                     && DefaultSelectionTopCellElement.this.isSelected
                     && DefaultSelectionTopCellElement.this.getParent().method_25399() == DefaultSelectionTopCellElement.this.getParent().selectionElement
                     && DefaultSelectionTopCellElement.this.getParent().selectionElement.method_25399() == DefaultSelectionTopCellElement.this
                     && DefaultSelectionTopCellElement.this.method_25399() == this
               );
               super.method_48579(graphics, mouseX, mouseY, delta);
            }

            public boolean method_25404(int int_1, int int_2, int int_3) {
               if (int_1 != 257 && int_1 != 335) {
                  return DefaultSelectionTopCellElement.this.isSuggestionMode() && super.method_25404(int_1, int_2, int_3);
               } else {
                  DefaultSelectionTopCellElement.this.selectFirstRecommendation();
                  return true;
               }
            }

            public boolean method_25400(char chr, int keyCode) {
               return DefaultSelectionTopCellElement.this.isSuggestionMode() && super.method_25400(chr, keyCode);
            }
         };
         this.textFieldWidget.method_1858(false);
         this.textFieldWidget.method_1880(999999);
         this.textFieldWidget.method_1852(toTextFunction.apply(value).getString());
      }

      @Override
      public boolean isEdited() {
         return super.isEdited() || !this.getValue().equals(this.original);
      }

      @Override
      public void render(class_332 graphics, int mouseX, int mouseY, int x, int y, int width, int height, float delta) {
         this.textFieldWidget.method_46421(x + 4);
         this.textFieldWidget.method_46419(y + 6);
         this.textFieldWidget.method_25358(width - 8);
         this.textFieldWidget.method_1888(this.getParent().isEditable());
         this.textFieldWidget.method_1868(this.getPreferredTextColor());
         this.textFieldWidget.method_25394(graphics, mouseX, mouseY, delta);
      }

      @Override
      public R getValue() {
         return this.hasError() ? this.value : this.toObjectFunction.apply(this.textFieldWidget.method_1882());
      }

      @Override
      public void setValue(R value) {
         this.textFieldWidget.method_1852(this.toTextFunction.apply(value).getString());
         this.textFieldWidget.method_1883(0, false);
      }

      @Override
      public class_2561 getSearchTerm() {
         return class_2561.method_43470(this.textFieldWidget.method_1882());
      }

      @Override
      public Optional<class_2561> getError() {
         return this.toObjectFunction.apply(this.textFieldWidget.method_1882()) != null
            ? Optional.empty()
            : Optional.of(class_2561.method_43470("Invalid Value!"));
      }

      public List<? extends class_364> method_25396() {
         return Collections.singletonList(this.textFieldWidget);
      }
   }

   public abstract static class DropdownMenuElement<R> extends class_362 {
      @Deprecated
      @NotNull
      private DropdownBoxEntry.SelectionCellCreator<R> cellCreator;
      @Deprecated
      @NotNull
      private DropdownBoxEntry<R> entry;
      private boolean isSelected;

      @NotNull
      public DropdownBoxEntry.SelectionCellCreator<R> getCellCreator() {
         return this.cellCreator;
      }

      @NotNull
      public final DropdownBoxEntry<R> getEntry() {
         return this.entry;
      }

      @Nullable
      public class_8016 method_48205(class_8023 focusNavigationEvent) {
         return null;
      }

      @NotNull
      public abstract ImmutableList<R> getSelections();

      public abstract void initCells();

      public abstract void render(class_332 var1, int var2, int var3, Rectangle var4, float var5);

      public abstract void lateRender(class_332 var1, int var2, int var3, float var4);

      public abstract int getHeight();

      public final boolean isExpanded() {
         return this.isSelected && this.getEntry().method_25399() == this.getEntry().selectionElement;
      }

      public final boolean isSuggestionMode() {
         return this.entry.isSuggestionMode();
      }

      public abstract List<DropdownBoxEntry.SelectionCellElement<R>> method_25396();
   }

   public abstract static class SelectionCellCreator<R> {
      public abstract DropdownBoxEntry.SelectionCellElement<R> create(R var1);

      public abstract int getCellHeight();

      public abstract int getDropBoxMaxHeight();

      public int getCellWidth() {
         return 132;
      }
   }

   public abstract static class SelectionCellElement<R> extends class_362 {
      @Deprecated
      @NotNull
      private DropdownBoxEntry<R> entry;

      @NotNull
      public final DropdownBoxEntry<R> getEntry() {
         return this.entry;
      }

      public abstract void render(class_332 var1, int var2, int var3, int var4, int var5, int var6, int var7, float var8);

      public abstract void dontRender(class_332 var1, float var2);

      @Nullable
      public abstract class_2561 getSearchKey();

      @Nullable
      public abstract R getSelection();
   }

   public static class SelectionElement<R> extends class_362 implements class_4068 {
      protected Rectangle bounds;
      protected boolean active;
      protected DropdownBoxEntry.SelectionTopCellElement<R> topRenderer;
      protected DropdownBoxEntry<R> entry;
      protected DropdownBoxEntry.DropdownMenuElement<R> menu;
      protected boolean dontReFocus = false;

      public SelectionElement(
         DropdownBoxEntry<R> entry,
         Rectangle bounds,
         DropdownBoxEntry.DropdownMenuElement<R> menu,
         DropdownBoxEntry.SelectionTopCellElement<R> topRenderer,
         DropdownBoxEntry.SelectionCellCreator<R> cellCreator
      ) {
         this.bounds = bounds;
         this.entry = entry;
         this.menu = Objects.requireNonNull(menu);
         this.menu.entry = entry;
         this.menu.cellCreator = Objects.requireNonNull(cellCreator);
         this.menu.initCells();
         this.topRenderer = Objects.requireNonNull(topRenderer);
         this.topRenderer.entry = entry;
      }

      public void method_25394(class_332 graphics, int mouseX, int mouseY, float delta) {
         graphics.method_25294(
            this.bounds.x, this.bounds.y, this.bounds.x + this.bounds.width, this.bounds.y + this.bounds.height, this.topRenderer.isSelected ? -1 : -6250336
         );
         graphics.method_25294(this.bounds.x + 1, this.bounds.y + 1, this.bounds.x + this.bounds.width - 1, this.bounds.y + this.bounds.height - 1, -16777216);
         this.topRenderer.render(graphics, mouseX, mouseY, this.bounds.x, this.bounds.y, this.bounds.width, this.bounds.height, delta);
         if (this.menu.isExpanded()) {
            this.menu.render(graphics, mouseX, mouseY, this.bounds, delta);
         }
      }

      @Deprecated
      public DropdownBoxEntry.SelectionTopCellElement<R> getTopRenderer() {
         return this.topRenderer;
      }

      public boolean method_25401(double double_1, double double_2, double amountX, double amountY) {
         return this.menu.isExpanded() ? this.menu.method_25401(double_1, double_2, amountX, amountY) : false;
      }

      public void lateRender(class_332 graphics, int mouseX, int mouseY, float delta) {
         if (this.menu.isExpanded()) {
            this.menu.lateRender(graphics, mouseX, mouseY, delta);
         }
      }

      public int getMorePossibleHeight() {
         return this.menu.isExpanded() ? this.menu.getHeight() : -1;
      }

      public R getValue() {
         return this.topRenderer.getValue();
      }

      public List<? extends class_364> method_25396() {
         return Lists.newArrayList(new class_362[]{this.topRenderer, this.menu});
      }

      public boolean method_25402(double double_1, double double_2, int int_1) {
         this.dontReFocus = false;
         boolean b = super.method_25402(double_1, double_2, int_1);
         if (this.dontReFocus) {
            this.method_25395(null);
            this.dontReFocus = false;
         }

         return b;
      }
   }

   public abstract static class SelectionTopCellElement<R> extends class_362 {
      @Deprecated
      private DropdownBoxEntry<R> entry;
      protected boolean isSelected = false;

      public abstract R getValue();

      public abstract void setValue(R var1);

      public abstract class_2561 getSearchTerm();

      public boolean isEdited() {
         return this.getConfigError().isPresent();
      }

      public abstract Optional<class_2561> getError();

      public final Optional<class_2561> getConfigError() {
         return this.entry.getConfigError();
      }

      public DropdownBoxEntry<R> getParent() {
         return this.entry;
      }

      public final boolean hasConfigError() {
         return this.getConfigError().isPresent();
      }

      public final boolean hasError() {
         return this.getError().isPresent();
      }

      public final int getPreferredTextColor() {
         return this.getConfigError().isPresent() ? 16733525 : 16777215;
      }

      public final boolean isSuggestionMode() {
         return this.getParent().isSuggestionMode();
      }

      public void selectFirstRecommendation() {
         for (DropdownBoxEntry.SelectionCellElement<R> child : this.getParent().selectionElement.menu.method_25396()) {
            if (child.getSelection() != null) {
               this.setValue(child.getSelection());
               this.getParent().selectionElement.method_25395(null);
               break;
            }
         }
      }

      public abstract void render(class_332 var1, int var2, int var3, int var4, int var5, int var6, int var7, float var8);
   }
}
