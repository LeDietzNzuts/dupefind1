package me.shedaniel.clothconfig2.api;

import java.util.function.Consumer;
import me.shedaniel.clothconfig2.impl.ConfigBuilderImpl;
import me.shedaniel.clothconfig2.impl.ConfigEntryBuilderImpl;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.class_2561;
import net.minecraft.class_2960;
import net.minecraft.class_437;

@Environment(EnvType.CLIENT)
public interface ConfigBuilder {
   static ConfigBuilder create() {
      return new ConfigBuilderImpl();
   }

   ConfigBuilder setFallbackCategory(ConfigCategory var1);

   class_437 getParentScreen();

   ConfigBuilder setParentScreen(class_437 var1);

   class_2561 getTitle();

   ConfigBuilder setTitle(class_2561 var1);

   boolean isEditable();

   ConfigBuilder setEditable(boolean var1);

   ConfigCategory getOrCreateCategory(class_2561 var1);

   ConfigBuilder removeCategory(class_2561 var1);

   ConfigBuilder removeCategoryIfExists(class_2561 var1);

   boolean hasCategory(class_2561 var1);

   ConfigBuilder setShouldTabsSmoothScroll(boolean var1);

   boolean isTabsSmoothScrolling();

   ConfigBuilder setShouldListSmoothScroll(boolean var1);

   boolean isListSmoothScrolling();

   ConfigBuilder setDoesConfirmSave(boolean var1);

   boolean doesConfirmSave();

   @Deprecated
   default ConfigBuilder setDoesProcessErrors(boolean processErrors) {
      return this;
   }

   @Deprecated
   default boolean doesProcessErrors() {
      return false;
   }

   class_2960 getDefaultBackgroundTexture();

   ConfigBuilder setDefaultBackgroundTexture(class_2960 var1);

   Runnable getSavingRunnable();

   ConfigBuilder setSavingRunnable(Runnable var1);

   Consumer<class_437> getAfterInitConsumer();

   ConfigBuilder setAfterInitConsumer(Consumer<class_437> var1);

   default ConfigBuilder alwaysShowTabs() {
      return this.setAlwaysShowTabs(true);
   }

   void setGlobalized(boolean var1);

   void setGlobalizedExpanded(boolean var1);

   boolean isAlwaysShowTabs();

   ConfigBuilder setAlwaysShowTabs(boolean var1);

   ConfigBuilder setTransparentBackground(boolean var1);

   default ConfigBuilder transparentBackground() {
      return this.setTransparentBackground(true);
   }

   default ConfigBuilder solidBackground() {
      return this.setTransparentBackground(false);
   }

   @Deprecated
   default ConfigEntryBuilderImpl getEntryBuilder() {
      return (ConfigEntryBuilderImpl)this.entryBuilder();
   }

   default ConfigEntryBuilder entryBuilder() {
      return ConfigEntryBuilderImpl.create();
   }

   class_437 build();

   boolean hasTransparentBackground();
}
