package net.p3pp3rf1y.sophisticatedbackpacks.compat.jei;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import net.fabricmc.fabric.api.tag.convention.v2.ConventionalItemTags;
import net.minecraft.class_1767;
import net.minecraft.class_1799;
import net.minecraft.class_1856;
import net.minecraft.class_1869;
import net.minecraft.class_1935;
import net.minecraft.class_2371;
import net.minecraft.class_2960;
import net.minecraft.class_3955;
import net.minecraft.class_6862;
import net.minecraft.class_7710;
import net.minecraft.class_7924;
import net.minecraft.class_8786;
import net.minecraft.class_8957;
import net.p3pp3rf1y.sophisticatedbackpacks.backpack.wrapper.BackpackWrapper;
import net.p3pp3rf1y.sophisticatedbackpacks.init.ModItems;
import net.p3pp3rf1y.sophisticatedcore.util.ColorHelper;

public class DyeRecipesMaker {
   private DyeRecipesMaker() {
   }

   public static List<class_8786<class_3955>> getRecipes() {
      List<class_8786<class_3955>> recipes = new ArrayList<>();
      addSingleColorRecipes(recipes);
      addMultipleColorsRecipe(recipes);
      return recipes;
   }

   private static void addMultipleColorsRecipe(List<class_8786<class_3955>> recipes) {
      class_2371<class_1856> ingredients = class_2371.method_10211();
      ingredients.add(class_1856.method_8106(ConventionalItemTags.YELLOW_DYES));
      ingredients.add(class_1856.method_8091(new class_1935[]{(class_1935)ModItems.BACKPACK.get()}));
      ingredients.add(class_1856.field_9017);
      ingredients.add(class_1856.method_8106(ConventionalItemTags.LIME_DYES));
      ingredients.add(class_1856.method_8106(ConventionalItemTags.BLUE_DYES));
      ingredients.add(class_1856.method_8106(ConventionalItemTags.BLACK_DYES));
      class_1799 backpackOutput = new class_1799((class_1935)ModItems.BACKPACK.get());
      int clothColor = ColorHelper.calculateColor(-3382982, -3382982, List.of(class_1767.field_7947, class_1767.field_7961));
      int trimColor = ColorHelper.calculateColor(-10342886, -10342886, List.of(class_1767.field_7966, class_1767.field_7963));
      BackpackWrapper.fromStack(backpackOutput).setColors(clothColor, trimColor);
      class_8957 pattern = new class_8957(3, 1, ingredients, Optional.empty());
      class_2960 id = class_2960.method_60655("sophisticatedbackpacks", "multiple_colors");
      recipes.add(new class_8786(id, new class_1869("", class_7710.field_40251, pattern, backpackOutput)));
   }

   private static void addSingleColorRecipes(List<class_8786<class_3955>> recipes) {
      for (class_1767 color : class_1767.values()) {
         class_1799 backpackOutput = new class_1799((class_1935)ModItems.BACKPACK.get());
         BackpackWrapper.fromStack(backpackOutput).setColors(color.method_7787(), color.method_7787());
         class_2371<class_1856> ingredients = class_2371.method_10211();
         ingredients.add(class_1856.method_8091(new class_1935[]{(class_1935)ModItems.BACKPACK.get()}));
         ingredients.add(class_1856.method_8106(class_6862.method_40092(class_7924.field_41197, class_2960.method_60655("c", "dyes/" + color.method_7792()))));
         class_8957 pattern = new class_8957(1, 2, ingredients, Optional.empty());
         class_2960 id = class_2960.method_60655("sophisticatedbackpacks", "single_color_" + color.method_15434());
         recipes.add(new class_8786(id, new class_1869("", class_7710.field_40251, pattern, backpackOutput)));
      }
   }
}
