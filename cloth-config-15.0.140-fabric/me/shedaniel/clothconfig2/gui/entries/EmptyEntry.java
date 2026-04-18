package me.shedaniel.clothconfig2.gui.entries;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import me.shedaniel.clothconfig2.api.AbstractConfigListEntry;
import net.minecraft.class_2561;
import net.minecraft.class_332;
import net.minecraft.class_364;
import net.minecraft.class_6379;
import net.minecraft.class_8016;
import net.minecraft.class_8023;
import org.jetbrains.annotations.Nullable;

public class EmptyEntry extends AbstractConfigListEntry<Object> {
   private final int height;

   public EmptyEntry(int height) {
      super(class_2561.method_43470(UUID.randomUUID().toString()), false);
      this.height = height;
   }

   @Override
   public int getItemHeight() {
      return this.height;
   }

   @Nullable
   @Override
   public class_8016 method_48205(class_8023 focusNavigationEvent) {
      return null;
   }

   @Override
   public List<? extends class_6379> narratables() {
      return Collections.emptyList();
   }

   @Override
   public Iterator<String> getSearchTags() {
      return Collections.emptyIterator();
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
   public boolean isMouseInside(int mouseX, int mouseY, int x, int y, int entryWidth, int entryHeight) {
      return false;
   }

   @Override
   public void render(class_332 graphics, int index, int y, int x, int entryWidth, int entryHeight, int mouseX, int mouseY, boolean isHovered, float delta) {
   }

   public List<? extends class_364> method_25396() {
      return Collections.emptyList();
   }
}
