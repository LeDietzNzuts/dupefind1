package me.shedaniel.clothconfig2.gui.widget;

import com.google.common.collect.Iterators;
import java.util.AbstractList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import me.shedaniel.clothconfig2.api.AbstractConfigEntry;
import me.shedaniel.clothconfig2.api.AbstractConfigListEntry;
import me.shedaniel.clothconfig2.api.ConfigScreen;
import me.shedaniel.clothconfig2.gui.ClothConfigScreen;
import net.minecraft.class_2561;
import net.minecraft.class_310;
import net.minecraft.class_332;
import net.minecraft.class_342;
import net.minecraft.class_3532;
import net.minecraft.class_364;
import net.minecraft.class_6379;

public class SearchFieldEntry extends AbstractConfigListEntry<Object> {
   private final class_342 editBox = new class_342(class_310.method_1551().field_1772, 0, 0, 100, 18, class_2561.method_43473());
   private String[] lowerCases = this.editBox.method_1882().isEmpty() ? new String[0] : this.editBox.method_1882().toLowerCase(Locale.ROOT).split(" ");

   public SearchFieldEntry(ConfigScreen screen, ClothConfigScreen.ListWidget<AbstractConfigEntry<AbstractConfigEntry<?>>> listWidget) {
      super(class_2561.method_43473(), false);
      this.editBox.method_1863(s -> this.lowerCases = s.isEmpty() ? new String[0] : s.toLowerCase(Locale.ROOT).split(" "));
      listWidget.entriesTransformer = entries -> new AbstractList<AbstractConfigEntry<AbstractConfigEntry<?>>>() {
         @Override
         public Iterator<AbstractConfigEntry<AbstractConfigEntry<?>>> iterator() {
            return (Iterator<AbstractConfigEntry<AbstractConfigEntry<?>>>)(SearchFieldEntry.this.editBox.method_1882().isEmpty()
               ? entries.iterator()
               : Iterators.filter(entries.iterator(), entry -> entry.isDisplayed() && screen.matchesSearch(entry.getSearchTags())));
         }

         public AbstractConfigEntry<AbstractConfigEntry<?>> get(int index) {
            return (AbstractConfigEntry<AbstractConfigEntry<?>>)Iterators.get(this.iterator(), index);
         }

         public void add(int index, AbstractConfigEntry<AbstractConfigEntry<?>> element) {
            entries.add(index, element);
         }

         public AbstractConfigEntry<AbstractConfigEntry<?>> remove(int index) {
            AbstractConfigEntry<AbstractConfigEntry<?>> entry = this.get(index);
            return entries.remove(entry) ? entry : null;
         }

         @Override
         public boolean remove(Object o) {
            return entries.remove(o);
         }

         @Override
         public void clear() {
            entries.clear();
         }

         @Override
         public int size() {
            return Iterators.size(this.iterator());
         }
      };
   }

   public boolean matchesSearch(Iterator<String> tags) {
      if (this.lowerCases.length == 0) {
         return true;
      } else if (!tags.hasNext()) {
         return true;
      } else {
         for (String lowerCase : this.lowerCases) {
            boolean found = false;

            for (String tag : () -> tags) {
               if (tag.toLowerCase(Locale.ROOT).contains(lowerCase)) {
                  found = true;
                  break;
               }
            }

            if (!found) {
               return false;
            }
         }

         return true;
      }
   }

   @Override
   public void render(class_332 graphics, int index, int y, int x, int entryWidth, int entryHeight, int mouseX, int mouseY, boolean isHovered, float delta) {
      this.editBox.method_25358(class_3532.method_15340(entryWidth - 10, 0, 500));
      this.editBox.method_46421(x + entryWidth / 2 - this.editBox.method_25368() / 2);
      this.editBox.method_46419(y + entryHeight / 2 - 9);
      this.editBox.method_25394(graphics, mouseX, mouseY, delta);
      if (this.editBox.method_1882().isEmpty()) {
         this.editBox.method_1887("Search...");
      } else {
         this.editBox.method_1887(null);
      }

      super.render(graphics, index, y, x, entryWidth, entryHeight, mouseX, mouseY, isHovered, delta);
   }

   @Override
   public Object getValue() {
      return null;
   }

   @Override
   public Optional<Object> getDefaultValue() {
      return Optional.empty();
   }

   @Override
   public List<? extends class_6379> narratables() {
      return Collections.singletonList(this.editBox);
   }

   public List<? extends class_364> method_25396() {
      return Collections.singletonList(this.editBox);
   }
}
