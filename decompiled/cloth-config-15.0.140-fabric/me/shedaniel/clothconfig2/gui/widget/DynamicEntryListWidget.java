package me.shedaniel.clothconfig2.gui.widget;

import com.google.common.collect.Lists;
import com.mojang.blaze3d.systems.RenderSystem;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;
import me.shedaniel.clothconfig2.api.DisableableWidget;
import me.shedaniel.clothconfig2.api.HideableWidget;
import me.shedaniel.clothconfig2.api.Requirement;
import me.shedaniel.clothconfig2.api.ScissorsHandler;
import me.shedaniel.clothconfig2.api.TickableWidget;
import me.shedaniel.math.Rectangle;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.class_2561;
import net.minecraft.class_287;
import net.minecraft.class_289;
import net.minecraft.class_290;
import net.minecraft.class_2960;
import net.minecraft.class_310;
import net.minecraft.class_332;
import net.minecraft.class_3532;
import net.minecraft.class_362;
import net.minecraft.class_364;
import net.minecraft.class_4068;
import net.minecraft.class_437;
import net.minecraft.class_525;
import net.minecraft.class_6379;
import net.minecraft.class_6381;
import net.minecraft.class_6382;
import net.minecraft.class_757;
import net.minecraft.class_8028;
import net.minecraft.class_8030;
import net.minecraft.class_293.class_5596;
import net.minecraft.class_437.class_6390;
import net.minecraft.class_6379.class_6380;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.ApiStatus.Experimental;
import org.joml.Matrix4f;

@Environment(EnvType.CLIENT)
public abstract class DynamicEntryListWidget<E extends DynamicEntryListWidget.Entry<E>> extends class_362 implements class_4068, class_6379 {
   public static final class_2960 VERTICAL_HEADER_SEPARATOR = class_2960.method_60654("cloth-config2:textures/gui/vertical_header_separator.png");
   public static final class_2960 VERTICAL_FOOTER_SEPARATOR = class_2960.method_60654("cloth-config2:textures/gui/vertical_footer_separator.png");
   protected static final int DRAG_OUTSIDE = -2;
   protected final class_310 client;
   private final List<E> entries = new DynamicEntryListWidget.Entries();
   private float totalTicks = 1.0F;
   private List<E> visibleEntries = Collections.emptyList();
   public int width;
   public int height;
   public int top;
   public int bottom;
   public int right;
   public int left;
   protected boolean verticallyCenter = true;
   protected int yDrag = -2;
   protected boolean selectionVisible = true;
   protected boolean renderSelection;
   protected int headerHeight;
   protected double scroll;
   protected boolean scrolling;
   @Nullable
   protected E hoveredItem;
   protected E selectedItem;
   protected class_2960 backgroundLocation;

   public DynamicEntryListWidget(class_310 client, int width, int height, int top, int bottom, class_2960 backgroundLocation) {
      this.client = client;
      this.width = width;
      this.height = height;
      this.top = top;
      this.bottom = bottom;
      this.left = 0;
      this.right = width;
      this.backgroundLocation = backgroundLocation;
   }

   @Experimental
   public List<E> visibleChildren() {
      return this.visibleEntries;
   }

   private void updateVisibleChildren() {
      this.visibleEntries = this.method_25396().stream().filter(HideableWidget::isDisplayed).toList();
   }

   public void setRenderSelection(boolean boolean_1) {
      this.selectionVisible = boolean_1;
   }

   protected void setRenderHeader(boolean boolean_1, int headerHeight) {
      this.renderSelection = boolean_1;
      this.headerHeight = headerHeight;
      if (!boolean_1) {
         this.headerHeight = 0;
      }
   }

   public class_6380 method_37018() {
      if (this.method_25370()) {
         return class_6380.field_33786;
      } else {
         return this.hoveredItem != null ? class_6380.field_33785 : class_6380.field_33784;
      }
   }

   public void method_37020(class_6382 narrationElementOutput) {
      E entry = this.hoveredItem;
      if (entry != null) {
         entry.method_37020(narrationElementOutput.method_37031());
         this.narrateListElementPosition(narrationElementOutput, entry);
      } else {
         E entry2 = this.getFocused();
         if (entry2 != null) {
            entry2.method_37020(narrationElementOutput.method_37031());
            this.narrateListElementPosition(narrationElementOutput, entry2);
         }
      }

      narrationElementOutput.method_37034(class_6381.field_33791, class_2561.method_43471("narration.component_list.usage"));
   }

   protected void narrateListElementPosition(class_6382 narrationElementOutput, E entry) {
      List<E> list = this.visibleChildren();
      if (list.size() > 1) {
         int i = list.indexOf(entry);
         if (i != -1) {
            narrationElementOutput.method_37034(class_6381.field_33789, class_2561.method_43469("narrator.position.list", new Object[]{i + 1, list.size()}));
         }
      }
   }

   public int getItemWidth() {
      return 220;
   }

   public E getSelectedItem() {
      return this.selectedItem;
   }

   public void selectItem(E item) {
      this.selectedItem = item;
   }

   public E getFocused() {
      return (E)super.method_25399();
   }

   public List<E> method_25396() {
      return this.entries;
   }

   protected final void clearItems() {
      this.method_25396().clear();
   }

   protected E getItem(int index) {
      return this.visibleChildren().get(index);
   }

   protected int addItem(E item) {
      this.method_25396().add(item);
      return this.method_25396().size() - 1;
   }

   protected int getItemCount() {
      return this.visibleChildren().size();
   }

   protected boolean isSelected(int index) {
      return Objects.equals(this.getSelectedItem(), this.getItem(index));
   }

   protected final E getItemAtPosition(double mouseX, double mouseY) {
      int listMiddleX = this.left + this.width / 2;
      int minX = listMiddleX - this.getItemWidth() / 2;
      int maxX = listMiddleX + this.getItemWidth() / 2;
      int currentY = class_3532.method_15357(mouseY - this.top) - this.headerHeight + (int)this.getScroll() - 4;
      if (this.getScrollbarPosition() <= mouseX) {
         return null;
      } else if (mouseX < minX) {
         return null;
      } else if (mouseX > maxX) {
         return null;
      } else if (currentY < 0) {
         return null;
      } else {
         E itemAtPosition = null;
         int itemY = 0;

         for (E item : this.visibleChildren()) {
            itemY += item.getItemHeight();
            if (itemY > currentY) {
               itemAtPosition = item;
               break;
            }
         }

         return itemAtPosition;
      }
   }

   public void updateSize(int width, int height, int top, int bottom) {
      this.width = width;
      this.height = height;
      this.top = top;
      this.bottom = bottom;
      this.left = 0;
      this.right = width;
   }

   public void setLeftPos(int left) {
      this.left = left;
      this.right = left + this.width;
   }

   protected int getMaxScrollPosition() {
      List<Integer> list = new ArrayList<>();
      int i = this.headerHeight;

      for (E entry : this.visibleChildren()) {
         i += entry.getItemHeight();
         if (entry.getMorePossibleHeight() >= 0) {
            list.add(i + entry.getMorePossibleHeight());
         }
      }

      list.add(i);
      return list.stream().max(Integer::compare).orElse(0);
   }

   protected void clickedHeader(int int_1, int int_2) {
   }

   public void tickList() {
      this.updateVisibleChildren();

      for (E child : this.method_25396()) {
         child.tick();
      }
   }

   protected void renderHeader(class_332 graphics, int rowLeft, int startY, class_289 tessellator) {
   }

   protected void drawBackground() {
   }

   protected void renderDecorations(class_332 graphics, int mouseX, int mouseY) {
   }

   @Deprecated
   protected void renderBackBackground(class_332 graphics, class_287 buffer, class_289 tessellator) {
      RenderSystem.setShader(class_757::method_34543);
      RenderSystem.setShaderTexture(0, this.backgroundLocation);
      RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
      Matrix4f matrix = graphics.method_51448().method_23760().method_23761();
      float float_2 = 32.0F;
      tessellator.method_60827(class_5596.field_27382, class_290.field_1575);
      buffer.method_22918(matrix, this.left, this.bottom, 0.0F)
         .method_22913(this.left / 32.0F, (this.bottom + (int)this.getScroll()) / 32.0F)
         .method_1336(32, 32, 32, 255);
      buffer.method_22918(matrix, this.right, this.bottom, 0.0F)
         .method_22913(this.right / 32.0F, (this.bottom + (int)this.getScroll()) / 32.0F)
         .method_1336(32, 32, 32, 255);
      buffer.method_22918(matrix, this.right, this.top, 0.0F)
         .method_22913(this.right / 32.0F, (this.top + (int)this.getScroll()) / 32.0F)
         .method_1336(32, 32, 32, 255);
      buffer.method_22918(matrix, this.left, this.top, 0.0F)
         .method_22913(this.left / 32.0F, (this.top + (int)this.getScroll()) / 32.0F)
         .method_1336(32, 32, 32, 255);
   }

   public void method_25394(class_332 graphics, int mouseX, int mouseY, float delta) {
      this.totalTicks += delta;
      if (this.totalTicks >= 1.0F) {
         this.totalTicks %= 1.0F;
         this.tickList();
      }

      this.drawBackground();
      int scrollbarPosition = this.getScrollbarPosition();
      int int_4 = scrollbarPosition + 6;
      class_289 tesselator = class_289.method_1348();
      class_287 buffer = tesselator.method_60827(class_5596.field_27382, class_290.field_1575);
      this.renderBackBackground(graphics, buffer, tesselator);
      int rowLeft = this.getRowLeft();
      int startY = this.top + 4 - (int)this.getScroll();
      if (this.renderSelection) {
         this.renderHeader(graphics, rowLeft, startY, tesselator);
      }

      ScissorsHandler.INSTANCE.scissor(new Rectangle(this.left, this.top, this.width, this.bottom - this.top));
      this.renderList(graphics, rowLeft, startY, mouseX, mouseY, delta);
      ScissorsHandler.INSTANCE.removeLastScissor();
      RenderSystem.disableDepthTest();
      this.renderHoleBackground(graphics, 0, this.top, 255, 255);
      this.renderHoleBackground(graphics, this.bottom, this.height, 255, 255);
      RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
      RenderSystem.enableBlend();
      graphics.method_25290(class_525.field_49895, this.left, this.top - 2, 0.0F, 0.0F, this.width, 2, 32, 2);
      graphics.method_25290(class_525.field_49896, this.left, this.bottom, 0.0F, 0.0F, this.width, 2, 32, 2);
      RenderSystem.disableBlend();
      int maxScroll = this.getMaxScroll();
      this.renderScrollBar(graphics, tesselator, buffer, maxScroll, scrollbarPosition, int_4);
      this.renderDecorations(graphics, mouseX, mouseY);
      RenderSystem.disableBlend();
   }

   protected void renderScrollBar(
      class_332 graphics, class_289 tessellator, class_287 buffer, int maxScroll, int scrollbarPositionMinX, int scrollbarPositionMaxX
   ) {
      if (maxScroll > 0) {
         int int_9 = (this.bottom - this.top) * (this.bottom - this.top) / this.getMaxScrollPosition();
         int_9 = class_3532.method_15340(int_9, 32, this.bottom - this.top - 8);
         int int_10 = (int)this.getScroll() * (this.bottom - this.top - int_9) / maxScroll + this.top;
         if (int_10 < this.top) {
            int_10 = this.top;
         }

         graphics.method_25294(scrollbarPositionMinX, this.top, scrollbarPositionMaxX, this.bottom, -16777216);
         graphics.method_25294(scrollbarPositionMinX, int_10, scrollbarPositionMaxX, int_10 + int_9, -8355712);
         graphics.method_25294(scrollbarPositionMinX, int_10, scrollbarPositionMaxX - 1, int_10 + int_9 - 1, -4144960);
      }
   }

   protected void centerScrollOn(E item) {
      List<E> children = this.visibleChildren();
      double halfway = (this.bottom - this.top) / -2.0;
      int itemIndex = children.indexOf(item);
      int i = 0;

      for (E elm : children) {
         if (i++ >= itemIndex) {
            break;
         }

         halfway += elm.getItemHeight();
      }

      this.capYPosition(halfway);
   }

   protected void ensureVisible(E item) {
      this.ensureVisible((int)this.getScroll() - this.top - this.headerHeight - 4 + this.getRowTop(this.visibleChildren().indexOf(item)), item.getItemHeight());
   }

   public void ensureVisible(int rowTop, int itemHeight) {
      int rowBottom = rowTop + itemHeight;
      double scroll = this.getScroll();
      if (rowTop < scroll) {
         this.capYPosition(rowTop);
      } else if (rowBottom > scroll + this.height) {
         this.capYPosition(rowBottom);
      }
   }

   protected void scroll(int int_1) {
      this.capYPosition(this.getScroll() + int_1);
      this.yDrag = -2;
   }

   public double getScroll() {
      return this.scroll;
   }

   public void capYPosition(double double_1) {
      this.scroll = class_3532.method_15350(double_1, 0.0, this.getMaxScroll());
   }

   protected int getMaxScroll() {
      return Math.max(0, this.getMaxScrollPosition() - (this.bottom - this.top - 4));
   }

   public int getScrollBottom() {
      return (int)this.getScroll() - this.height - this.headerHeight;
   }

   protected void updateScrollingState(double double_1, double double_2, int int_1) {
      this.scrolling = int_1 == 0 && double_1 >= this.getScrollbarPosition() && double_1 < this.getScrollbarPosition() + 6;
   }

   protected int getScrollbarPosition() {
      return this.width / 2 + 124;
   }

   public boolean method_25402(double double_1, double double_2, int int_1) {
      this.updateScrollingState(double_1, double_2, int_1);
      if (!this.method_25405(double_1, double_2)) {
         return false;
      } else {
         E item = this.getItemAtPosition(double_1, double_2);
         if (item != null) {
            if (item.method_25402(double_1, double_2, int_1)) {
               this.method_25395(item);
               this.method_25398(true);
               return true;
            }
         } else if (int_1 == 0) {
            this.clickedHeader((int)(double_1 - (this.left + this.width / 2 - this.getItemWidth() / 2)), (int)(double_2 - this.top) + (int)this.getScroll() - 4);
            return true;
         }

         return this.scrolling;
      }
   }

   public class_8030 method_48202() {
      return new class_8030(this.left, this.top, this.right - this.left, this.bottom - this.top);
   }

   public void method_25395(@Nullable class_364 guiEventListener) {
      super.method_25395(guiEventListener);
      int i = this.entries.indexOf(guiEventListener);
      if (i >= 0) {
         E entry = this.entries.get(i);
         this.selectItem(entry);
         if (this.client.method_48186().method_48183()) {
            this.ensureVisible(entry);
         }
      }
   }

   @Nullable
   protected E nextEntry(class_8028 screenDirection) {
      return this.nextEntry(screenDirection, entry -> true);
   }

   @Nullable
   protected E nextEntry(class_8028 screenDirection, Predicate<E> predicate) {
      return this.nextEntry(screenDirection, predicate, this.getSelectedItem());
   }

   @Nullable
   protected E nextEntry(class_8028 screenDirection, Predicate<E> predicate, @Nullable E entry) {
      int var10000 = switch (screenDirection) {
         case field_41829, field_41828 -> 0;
         case field_41826 -> -1;
         case field_41827 -> 1;
         default -> throw new MatchException(null, null);
      };
      if (!this.method_25396().isEmpty() && var10000 != 0) {
         int j;
         if (entry == null) {
            j = var10000 > 0 ? 0 : this.method_25396().size() - 1;
         } else {
            j = this.method_25396().indexOf(entry) + var10000;
         }

         for (int k = j; k >= 0 && k < this.entries.size(); k += var10000) {
            E entry2 = this.entries.get(k);
            if (predicate.test(entry2)) {
               return entry2;
            }
         }
      }

      return null;
   }

   public boolean method_25406(double double_1, double double_2, int int_1) {
      if (this.getFocused() != null) {
         this.getFocused().method_25406(double_1, double_2, int_1);
      }

      return false;
   }

   public boolean method_25403(double mouseX, double mouseY, int button, double deltaX, double deltaY) {
      if (super.method_25403(mouseX, mouseY, button, deltaX, deltaY)) {
         return true;
      } else if (button == 0 && this.scrolling) {
         if (mouseY < this.top) {
            this.capYPosition(0.0);
         } else if (mouseY > this.bottom) {
            this.capYPosition(this.getMaxScroll());
         } else {
            double double_5 = Math.max(1, this.getMaxScroll());
            int int_2 = this.bottom - this.top;
            int int_3 = class_3532.method_15340((int)((float)(int_2 * int_2) / this.getMaxScrollPosition()), 32, int_2 - 8);
            double double_6 = Math.max(1.0, double_5 / (int_2 - int_3));
            this.capYPosition(this.getScroll() + deltaY * double_6);
         }

         return true;
      } else {
         return false;
      }
   }

   public boolean method_25401(double double_1, double double_2, double amountX, double amountY) {
      for (E entry : this.visibleChildren()) {
         if (entry.method_25401(double_1, double_2, amountX, amountY)) {
            return true;
         }
      }

      this.capYPosition(this.getScroll() - amountY * (this.getMaxScroll() / this.getItemCount()) / 2.0);
      return amountY != 0.0;
   }

   public boolean method_25404(int int_1, int int_2, int int_3) {
      if (super.method_25404(int_1, int_2, int_3)) {
         return true;
      } else if (int_1 == 264) {
         this.moveSelection(1);
         return true;
      } else if (int_1 == 265) {
         this.moveSelection(-1);
         return true;
      } else {
         return false;
      }
   }

   protected void moveSelection(int shift) {
      List<E> children = this.visibleChildren();
      if (!children.isEmpty()) {
         int selected = children.indexOf(this.getSelectedItem());
         int index = class_3532.method_15340(selected + shift, 0, this.getItemCount() - 1);
         E item = this.getItem(index);
         this.selectItem(item);
         this.ensureVisible(item);
      }
   }

   public boolean method_25405(double double_1, double double_2) {
      return double_2 >= this.top && double_2 <= this.bottom && double_1 >= this.left && double_1 <= this.right;
   }

   protected void renderList(class_332 graphics, int startX, int startY, int mouseX, int mouseY, float delta) {
      class_289 tesselator = class_289.method_1348();
      class_287 buffer = tesselator.method_60827(class_5596.field_27382, class_290.field_1575);
      this.hoveredItem = this.method_25405(mouseX, mouseY) ? this.getItemAtPosition(mouseX, mouseY) : null;
      int heights = 0;
      int renderIndex = 0;

      for (E item : this.visibleChildren()) {
         int itemY = startY + this.headerHeight + heights;
         int itemHeight = item.getItemHeight() - 4;
         int itemWidth = this.getItemWidth();
         boolean itemHovered = Objects.equals(this.hoveredItem, item);
         if (this.selectionVisible && Objects.equals(this.selectedItem, item)) {
            int itemMinX = this.left + (this.width - itemWidth) / 2;
            int itemMaxX = this.left + (this.width + itemWidth) / 2;
            graphics.method_25294(itemMinX, itemY - 2, itemMaxX, itemY + itemHeight + 2, this.method_25370() ? -1 : -8355712);
            graphics.method_25294(itemMinX + 1, itemY - 1, itemMaxX - 1, itemY + itemHeight + 1, -16777216);
         }

         int y = this.getRowTop(renderIndex);
         int x = this.getRowLeft();
         this.renderItem(graphics, item, renderIndex, y, x, itemWidth, itemHeight, mouseX, mouseY, itemHovered, delta);
         heights += item.getItemHeight();
         renderIndex++;
      }
   }

   protected void renderItem(
      class_332 graphics, E item, int index, int y, int x, int entryWidth, int entryHeight, int mouseX, int mouseY, boolean isSelected, float delta
   ) {
      item.render(graphics, index, y, x, entryWidth, entryHeight, mouseX, mouseY, isSelected, delta);
   }

   protected int getRowLeft() {
      return this.left + this.width / 2 - this.getItemWidth() / 2 + 2;
   }

   public int getRowTop(int index) {
      int top = this.top + 4 - (int)this.getScroll() + this.headerHeight;
      int i = 0;

      for (E item : this.visibleChildren()) {
         if (index <= i++) {
            break;
         }

         top += item.getItemHeight();
      }

      return top;
   }

   public boolean method_25370() {
      return false;
   }

   protected void renderHoleBackground(class_332 graphics, int y1, int y2, int alpha1, int alpha2) {
      class_289 tesselator = class_289.method_1348();
      class_287 buffer = tesselator.method_60827(class_5596.field_27382, class_290.field_1575);
      Matrix4f matrix = graphics.method_51448().method_23760().method_23761();
      RenderSystem.setShader(class_757::method_34543);
      RenderSystem.setShaderTexture(0, this.backgroundLocation);
      RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
      buffer.method_22918(matrix, this.left, y2, 0.0F).method_22913(0.0F, y2 / 32.0F).method_1336(64, 64, 64, alpha2);
      buffer.method_22918(matrix, this.left + this.width, y2, 0.0F).method_22913(this.width / 32.0F, y2 / 32.0F).method_1336(64, 64, 64, alpha2);
      buffer.method_22918(matrix, this.left + this.width, y1, 0.0F).method_22913(this.width / 32.0F, y1 / 32.0F).method_1336(64, 64, 64, alpha1);
      buffer.method_22918(matrix, this.left, y1, 0.0F).method_22913(0.0F, y1 / 32.0F).method_1336(64, 64, 64, alpha1);
   }

   protected E remove(int index) {
      E item = this.getItem(index);
      return this.removeEntry(item) ? item : null;
   }

   protected boolean removeEntry(E entry) {
      boolean removed = this.method_25396().remove(entry);
      if (removed && entry == this.getSelectedItem()) {
         this.selectItem(null);
      }

      return removed;
   }

   @Environment(EnvType.CLIENT)
   class Entries extends AbstractList<E> {
      private final ArrayList<E> items = Lists.newArrayList();

      private Entries() {
      }

      @Override
      public void clear() {
         this.items.clear();
      }

      public E get(int int_1) {
         return this.items.get(int_1);
      }

      @Override
      public int size() {
         return this.items.size();
      }

      public E set(int int_1, E itemListWidget$Item_1) {
         E itemListWidget$Item_2 = this.items.set(int_1, itemListWidget$Item_1);
         itemListWidget$Item_1.parent = DynamicEntryListWidget.this;
         return itemListWidget$Item_2;
      }

      public void add(int int_1, E itemListWidget$Item_1) {
         this.items.add(int_1, itemListWidget$Item_1);
         itemListWidget$Item_1.parent = DynamicEntryListWidget.this;
      }

      public E remove(int int_1) {
         return this.items.remove(int_1);
      }
   }

   @Environment(EnvType.CLIENT)
   public abstract static class Entry<E extends DynamicEntryListWidget.Entry<E>> implements class_364, TickableWidget, HideableWidget, DisableableWidget {
      @Deprecated
      DynamicEntryListWidget<E> parent;
      @Nullable
      private class_6379 lastNarratable;
      @Nullable
      protected Requirement enableRequirement = null;
      @Nullable
      protected Requirement displayRequirement = null;
      protected boolean enabled = true;
      protected boolean displayed = true;

      public abstract void render(class_332 var1, int var2, int var3, int var4, int var5, int var6, int var7, int var8, boolean var9, float var10);

      public boolean method_25405(double double_1, double double_2) {
         return Objects.equals(this.parent.getItemAtPosition(double_1, double_2), this);
      }

      public DynamicEntryListWidget<E> getParent() {
         return this.parent;
      }

      public void setParent(DynamicEntryListWidget<E> parent) {
         this.parent = parent;
      }

      @Override
      public boolean isEnabled() {
         return this.isDisplayed() && this.enabled;
      }

      @Override
      public boolean isDisplayed() {
         return this.displayed;
      }

      @Override
      public void setRequirement(@Nullable Requirement requirement) {
         this.enableRequirement = requirement;
      }

      @Nullable
      @Override
      public Requirement getRequirement() {
         return this.enableRequirement;
      }

      @Override
      public void setDisplayRequirement(@Nullable Requirement requirement) {
         this.displayRequirement = requirement;
      }

      @Nullable
      @Override
      public Requirement getDisplayRequirement() {
         return this.displayRequirement;
      }

      public abstract int getItemHeight();

      @Deprecated
      public int getMorePossibleHeight() {
         return -1;
      }

      public abstract List<? extends class_6379> narratables();

      @Override
      public void tick() {
         this.enabled = this.getRequirement() == null || this.getRequirement().check();
         this.displayed = this.getDisplayRequirement() == null || this.getDisplayRequirement().check();
      }

      void method_37020(class_6382 narrationElementOutput) {
         List<? extends class_6379> list = this.narratables();
         class_6390 narratableSearchResult = class_437.method_37061(list, this.lastNarratable);
         if (narratableSearchResult != null) {
            if (narratableSearchResult.field_33827.method_37028()) {
               this.lastNarratable = narratableSearchResult.field_33825;
            }

            if (list.size() > 1) {
               narrationElementOutput.method_37034(
                  class_6381.field_33789,
                  class_2561.method_43469("narrator.position.object_list", new Object[]{narratableSearchResult.field_33826 + 1, list.size()})
               );
               if (narratableSearchResult.field_33827 == class_6380.field_33786) {
                  narrationElementOutput.method_37034(class_6381.field_33791, class_2561.method_43471("narration.component_list.usage"));
               }
            }

            narratableSearchResult.field_33825.method_37020(narrationElementOutput.method_37031());
         }
      }
   }

   public static final class SmoothScrollingSettings {
      public static final double CLAMP_EXTENSION = 200.0;

      private SmoothScrollingSettings() {
      }
   }
}
