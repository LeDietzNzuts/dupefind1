package me.shedaniel.clothconfig2.impl;

import com.google.common.collect.Maps;
import java.util.Map;
import java.util.Objects;
import java.util.function.Consumer;
import me.shedaniel.clothconfig2.api.ConfigBuilder;
import me.shedaniel.clothconfig2.api.ConfigCategory;
import me.shedaniel.clothconfig2.api.Expandable;
import me.shedaniel.clothconfig2.gui.AbstractConfigScreen;
import me.shedaniel.clothconfig2.gui.ClothConfigScreen;
import me.shedaniel.clothconfig2.gui.GlobalizedClothConfigScreen;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.class_2561;
import net.minecraft.class_2960;
import net.minecraft.class_437;
import org.jetbrains.annotations.ApiStatus.Internal;

@Environment(EnvType.CLIENT)
@Internal
public class ConfigBuilderImpl implements ConfigBuilder {
   private Runnable savingRunnable;
   private class_437 parent;
   private class_2561 title = class_2561.method_43471("text.cloth-config.config");
   private boolean globalized = false;
   private boolean globalizedExpanded = true;
   private boolean editable = true;
   private boolean tabsSmoothScroll = true;
   private boolean listSmoothScroll = true;
   private boolean doesConfirmSave = true;
   private boolean transparentBackground = true;
   private class_2960 defaultBackground = class_2960.method_60656("textures/block/dirt.png");
   private Consumer<class_437> afterInitConsumer = screen -> {};
   private final Map<String, ConfigCategory> categoryMap = Maps.newLinkedHashMap();
   private String fallbackCategory = null;
   private boolean alwaysShowTabs = false;

   @Override
   public void setGlobalized(boolean globalized) {
      this.globalized = globalized;
   }

   @Override
   public void setGlobalizedExpanded(boolean globalizedExpanded) {
      this.globalizedExpanded = globalizedExpanded;
   }

   @Override
   public boolean isAlwaysShowTabs() {
      return this.alwaysShowTabs;
   }

   @Override
   public ConfigBuilder setAlwaysShowTabs(boolean alwaysShowTabs) {
      this.alwaysShowTabs = alwaysShowTabs;
      return this;
   }

   @Override
   public ConfigBuilder setTransparentBackground(boolean transparentBackground) {
      this.transparentBackground = transparentBackground;
      return this;
   }

   @Override
   public boolean hasTransparentBackground() {
      return this.transparentBackground;
   }

   @Override
   public ConfigBuilder setAfterInitConsumer(Consumer<class_437> afterInitConsumer) {
      this.afterInitConsumer = afterInitConsumer;
      return this;
   }

   @Override
   public ConfigBuilder setFallbackCategory(ConfigCategory fallbackCategory) {
      this.fallbackCategory = Objects.requireNonNull(fallbackCategory).getCategoryKey().getString();
      return this;
   }

   @Override
   public class_437 getParentScreen() {
      return this.parent;
   }

   @Override
   public ConfigBuilder setParentScreen(class_437 parent) {
      this.parent = parent;
      return this;
   }

   @Override
   public class_2561 getTitle() {
      return this.title;
   }

   @Override
   public ConfigBuilder setTitle(class_2561 title) {
      this.title = title;
      return this;
   }

   @Override
   public boolean isEditable() {
      return this.editable;
   }

   @Override
   public ConfigBuilder setEditable(boolean editable) {
      this.editable = editable;
      return this;
   }

   @Override
   public ConfigCategory getOrCreateCategory(class_2561 categoryKey) {
      if (this.categoryMap.containsKey(categoryKey.getString())) {
         return this.categoryMap.get(categoryKey.getString());
      } else {
         if (this.fallbackCategory == null) {
            this.fallbackCategory = categoryKey.getString();
         }

         return this.categoryMap.computeIfAbsent(categoryKey.getString(), key -> new ConfigCategoryImpl(this, categoryKey));
      }
   }

   @Override
   public ConfigBuilder removeCategory(class_2561 category) {
      if (this.categoryMap.containsKey(category.getString()) && Objects.equals(this.fallbackCategory, category.getString())) {
         this.fallbackCategory = null;
      }

      if (!this.categoryMap.containsKey(category.getString())) {
         throw new NullPointerException("Category doesn't exist!");
      } else {
         this.categoryMap.remove(category.getString());
         return this;
      }
   }

   @Override
   public ConfigBuilder removeCategoryIfExists(class_2561 category) {
      if (this.categoryMap.containsKey(category.getString()) && Objects.equals(this.fallbackCategory, category.getString())) {
         this.fallbackCategory = null;
      }

      this.categoryMap.remove(category.getString());
      return this;
   }

   @Override
   public boolean hasCategory(class_2561 category) {
      return this.categoryMap.containsKey(category.getString());
   }

   @Override
   public ConfigBuilder setShouldTabsSmoothScroll(boolean shouldTabsSmoothScroll) {
      this.tabsSmoothScroll = shouldTabsSmoothScroll;
      return this;
   }

   @Override
   public boolean isTabsSmoothScrolling() {
      return this.tabsSmoothScroll;
   }

   @Override
   public ConfigBuilder setShouldListSmoothScroll(boolean shouldListSmoothScroll) {
      this.listSmoothScroll = shouldListSmoothScroll;
      return this;
   }

   @Override
   public boolean isListSmoothScrolling() {
      return this.listSmoothScroll;
   }

   @Override
   public ConfigBuilder setDoesConfirmSave(boolean confirmSave) {
      this.doesConfirmSave = confirmSave;
      return this;
   }

   @Override
   public boolean doesConfirmSave() {
      return this.doesConfirmSave;
   }

   @Override
   public class_2960 getDefaultBackgroundTexture() {
      return this.defaultBackground;
   }

   @Override
   public ConfigBuilder setDefaultBackgroundTexture(class_2960 texture) {
      this.defaultBackground = texture;
      return this;
   }

   @Override
   public ConfigBuilder setSavingRunnable(Runnable runnable) {
      this.savingRunnable = runnable;
      return this;
   }

   @Override
   public Consumer<class_437> getAfterInitConsumer() {
      return this.afterInitConsumer;
   }

   @Override
   public class_437 build() {
      if (!this.categoryMap.isEmpty() && this.fallbackCategory != null) {
         AbstractConfigScreen screen;
         if (this.globalized) {
            screen = new GlobalizedClothConfigScreen(this.parent, this.title, this.categoryMap, this.defaultBackground);
         } else {
            screen = new ClothConfigScreen(this.parent, this.title, this.categoryMap, this.defaultBackground);
         }

         screen.setSavingRunnable(this.savingRunnable);
         screen.setEditable(this.editable);
         screen.setFallbackCategory(this.fallbackCategory == null ? null : class_2561.method_43470(this.fallbackCategory));
         screen.setTransparentBackground(this.transparentBackground);
         screen.setAlwaysShowTabs(this.alwaysShowTabs);
         screen.setConfirmSave(this.doesConfirmSave);
         screen.setAfterInitConsumer(this.afterInitConsumer);
         if (screen instanceof Expandable) {
            ((Expandable)screen).setExpanded(this.globalizedExpanded);
         }

         return screen;
      } else {
         throw new NullPointerException("There cannot be no categories or fallback category!");
      }
   }

   @Override
   public Runnable getSavingRunnable() {
      return this.savingRunnable;
   }
}
