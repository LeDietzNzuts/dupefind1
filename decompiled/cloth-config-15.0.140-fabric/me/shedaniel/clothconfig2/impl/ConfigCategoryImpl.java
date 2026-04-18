package me.shedaniel.clothconfig2.impl;

import com.google.common.collect.Lists;
import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;
import me.shedaniel.clothconfig2.api.AbstractConfigListEntry;
import me.shedaniel.clothconfig2.api.ConfigBuilder;
import me.shedaniel.clothconfig2.api.ConfigCategory;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.class_2561;
import net.minecraft.class_2960;
import net.minecraft.class_5348;
import org.jetbrains.annotations.Nullable;

@Environment(EnvType.CLIENT)
public class ConfigCategoryImpl implements ConfigCategory {
   private final ConfigBuilder builder;
   private final List<Object> data;
   @Nullable
   private class_2960 background;
   private final class_2561 categoryKey;
   @Nullable
   private Supplier<Optional<class_5348[]>> description = Optional::empty;

   ConfigCategoryImpl(ConfigBuilder builder, class_2561 categoryKey) {
      this.builder = builder;
      this.data = Lists.newArrayList();
      this.categoryKey = categoryKey;
   }

   @Override
   public class_2561 getCategoryKey() {
      return this.categoryKey;
   }

   @Override
   public List<Object> getEntries() {
      return this.data;
   }

   @Override
   public ConfigCategory addEntry(AbstractConfigListEntry entry) {
      this.data.add(entry);
      return this;
   }

   @Override
   public ConfigCategory setCategoryBackground(class_2960 identifier) {
      this.background = identifier;
      return this;
   }

   @Override
   public void removeCategory() {
      this.builder.removeCategory(this.categoryKey);
   }

   @Override
   public void setBackground(@Nullable class_2960 background) {
      this.background = background;
   }

   @Nullable
   @Override
   public class_2960 getBackground() {
      return this.background;
   }

   @Nullable
   @Override
   public Supplier<Optional<class_5348[]>> getDescription() {
      return this.description;
   }

   @Override
   public void setDescription(@Nullable Supplier<Optional<class_5348[]>> description) {
      this.description = description;
   }
}
