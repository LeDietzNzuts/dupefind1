package me.shedaniel.clothconfig2.gui.entries;

import com.google.common.collect.Iterators;
import com.google.common.collect.Lists;
import com.mojang.blaze3d.systems.RenderSystem;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import me.shedaniel.clothconfig2.api.AbstractConfigEntry;
import me.shedaniel.clothconfig2.api.AbstractConfigListEntry;
import me.shedaniel.clothconfig2.api.Expandable;
import me.shedaniel.math.Rectangle;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.class_1109;
import net.minecraft.class_2561;
import net.minecraft.class_2960;
import net.minecraft.class_310;
import net.minecraft.class_332;
import net.minecraft.class_3417;
import net.minecraft.class_364;
import net.minecraft.class_6379;
import net.minecraft.class_6381;
import net.minecraft.class_6382;
import net.minecraft.class_6379.class_6380;
import org.jetbrains.annotations.Nullable;

@Environment(EnvType.CLIENT)
public class SubCategoryListEntry extends TooltipListEntry<List<AbstractConfigListEntry>> implements Expandable {
   private static final class_2960 CONFIG_TEX = class_2960.method_60655("cloth-config2", "textures/gui/cloth_config.png");
   private final List<AbstractConfigListEntry> entries;
   private final SubCategoryListEntry.CategoryLabelWidget widget;
   private final List<Object> children;
   private boolean expanded;

   @Deprecated
   public SubCategoryListEntry(class_2561 categoryName, List<AbstractConfigListEntry> entries, boolean defaultExpanded) {
      super(categoryName, null);
      this.entries = entries;
      this.expanded = defaultExpanded;
      this.widget = new SubCategoryListEntry.CategoryLabelWidget();
      this.children = Lists.newArrayList(new Object[]{this.widget});
      this.children.addAll(entries);
      this.setReferenceProviderEntries(entries);
   }

   @Override
   public Iterator<String> getSearchTags() {
      return Iterators.concat(super.getSearchTags(), Iterators.concat(this.entries.stream().map(AbstractConfigEntry::getSearchTags).iterator()));
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
   public boolean isRequiresRestart() {
      for (AbstractConfigListEntry entry : this.entries) {
         if (entry.isRequiresRestart()) {
            return true;
         }
      }

      return false;
   }

   @Override
   public void setRequiresRestart(boolean requiresRestart) {
   }

   public class_2561 getCategoryName() {
      return this.getFieldName();
   }

   public List<AbstractConfigListEntry> getValue() {
      return this.entries;
   }

   public List<AbstractConfigListEntry> filteredEntries() {
      return new AbstractList<AbstractConfigListEntry>() {
         @Override
         public Iterator<AbstractConfigListEntry> iterator() {
            return Iterators.filter(
               SubCategoryListEntry.this.entries.iterator(),
               entry -> entry.isDisplayed()
                  && SubCategoryListEntry.this.getConfigScreen() != null
                  && SubCategoryListEntry.this.getConfigScreen().matchesSearch(entry.getSearchTags())
            );
         }

         public AbstractConfigListEntry get(int index) {
            return (AbstractConfigListEntry)Iterators.get(this.iterator(), index);
         }

         @Override
         public int size() {
            return Iterators.size(this.iterator());
         }
      };
   }

   @Override
   public Optional<List<AbstractConfigListEntry>> getDefaultValue() {
      return Optional.empty();
   }

   @Override
   public void render(class_332 graphics, int index, int y, int x, int entryWidth, int entryHeight, int mouseX, int mouseY, boolean isHovered, float delta) {
      super.render(graphics, index, y, x, entryWidth, entryHeight, mouseX, mouseY, isHovered, delta);
      RenderSystem.setShaderTexture(0, CONFIG_TEX);
      RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
      boolean insideWidget = this.widget.rectangle.contains(mouseX, mouseY);
      graphics.method_25302(CONFIG_TEX, x - 15, y + 5, 24, (this.isEnabled() ? (insideWidget ? 18 : 0) : 36) + (this.isExpanded() ? 9 : 0), 9, 9);
      graphics.method_35720(class_310.method_1551().field_1772, this.getDisplayedFieldName().method_30937(), x, y + 6, insideWidget ? -1638890 : -1);

      for (AbstractConfigListEntry<?> entry : this.entries) {
         entry.setParent(this.getParent());
         entry.setScreen(this.getConfigScreen());
      }

      if (this.isExpanded()) {
         int yy = y + 24;

         for (AbstractConfigListEntry<?> entry : this.filteredEntries()) {
            entry.render(graphics, -1, yy, x + 14, entryWidth - 14, entry.getItemHeight(), mouseX, mouseY, isHovered && this.method_25399() == entry, delta);
            yy += entry.getItemHeight();
         }
      }
   }

   @Override
   public void tick() {
      super.tick();

      for (AbstractConfigListEntry<?> entry : this.entries) {
         entry.tick();
      }
   }

   @Override
   public void updateSelected(boolean isSelected) {
      for (AbstractConfigListEntry<?> entry : this.entries) {
         entry.updateSelected(
            this.isExpanded()
               && isSelected
               && this.method_25399() == entry
               && entry.isDisplayed()
               && this.getConfigScreen().matchesSearch(entry.getSearchTags())
         );
      }
   }

   @Override
   public void method_25395(@Nullable class_364 guiEventListener) {
      super.method_25395(guiEventListener);
   }

   @Override
   public boolean isEdited() {
      for (AbstractConfigListEntry<?> entry : this.entries) {
         if (entry.isEdited()) {
            return true;
         }
      }

      return false;
   }

   @Override
   public void lateRender(class_332 graphics, int mouseX, int mouseY, float delta) {
      if (this.isExpanded()) {
         for (AbstractConfigListEntry<?> entry : this.filteredEntries()) {
            entry.lateRender(graphics, mouseX, mouseY, delta);
         }
      }
   }

   @Override
   public int getMorePossibleHeight() {
      if (!this.isExpanded()) {
         return -1;
      } else {
         List<Integer> list = new ArrayList<>();
         int i = 24;

         for (AbstractConfigListEntry<?> entry : this.filteredEntries()) {
            i += entry.getItemHeight();
            if (entry.getMorePossibleHeight() >= 0) {
               list.add(i + entry.getMorePossibleHeight());
            }
         }

         list.add(i);
         return list.stream().max(Integer::compare).orElse(0) - this.getItemHeight();
      }
   }

   @Override
   public Rectangle getEntryArea(int x, int y, int entryWidth, int entryHeight) {
      this.widget.rectangle.x = x - 15;
      this.widget.rectangle.y = y;
      this.widget.rectangle.width = entryWidth + 15;
      this.widget.rectangle.height = 24;
      return new Rectangle(this.getParent().left, y, this.getParent().right - this.getParent().left, 20);
   }

   @Override
   public int getItemHeight() {
      if (!this.isExpanded()) {
         return 24;
      } else {
         int i = 24;

         for (AbstractConfigListEntry<?> entry : this.filteredEntries()) {
            i += entry.getItemHeight();
         }

         return i;
      }
   }

   @Override
   public int getInitialReferenceOffset() {
      return 24;
   }

   public List<? extends class_364> method_25396() {
      return this.isExpanded() ? this.children : Collections.singletonList(this.widget);
   }

   @Override
   public List<? extends class_6379> narratables() {
      return this.isExpanded() ? this.children : Collections.singletonList(this.widget);
   }

   @Override
   public void save() {
      this.entries.forEach(AbstractConfigEntry::save);
   }

   @Override
   public Optional<class_2561> getError() {
      class_2561 error = null;

      for (AbstractConfigListEntry<?> entry : this.entries) {
         Optional<class_2561> configError = entry.getConfigError();
         if (configError.isPresent()) {
            if (error != null) {
               return Optional.ofNullable(class_2561.method_43471("text.cloth-config.multi_error"));
            }

            return configError;
         }
      }

      return Optional.ofNullable(error);
   }

   public class CategoryLabelWidget implements class_364, class_6379 {
      private final Rectangle rectangle = new Rectangle();
      private boolean isHovered;

      public boolean method_25402(double mouseX, double mouseY, int int_1) {
         if (SubCategoryListEntry.this.isEnabled() && this.rectangle.contains(mouseX, mouseY)) {
            SubCategoryListEntry.this.setExpanded(!SubCategoryListEntry.this.expanded);
            class_310.method_1551().method_1483().method_4873(class_1109.method_47978(class_3417.field_15015, 1.0F));
            return this.isHovered = true;
         } else {
            return this.isHovered = false;
         }
      }

      public void method_25365(boolean bl) {
      }

      public boolean method_25370() {
         return false;
      }

      public class_6380 method_37018() {
         return this.isHovered ? class_6380.field_33785 : class_6380.field_33784;
      }

      public void method_37020(class_6382 narrationElementOutput) {
         narrationElementOutput.method_37034(class_6381.field_33788, SubCategoryListEntry.this.getFieldName());
      }
   }
}
