package me.shedaniel.clothconfig2.api;

import net.minecraft.class_2561;
import net.minecraft.class_2960;

public interface TabbedConfigScreen extends ConfigScreen {
   void registerCategoryBackground(String var1, class_2960 var2);

   void registerCategoryTransparency(String var1, boolean var2);

   class_2561 getSelectedCategory();
}
