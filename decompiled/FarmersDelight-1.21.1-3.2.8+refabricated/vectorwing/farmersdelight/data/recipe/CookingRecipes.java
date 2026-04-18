package vectorwing.farmersdelight.data.recipe;

import net.fabricmc.fabric.api.recipe.v1.ingredient.DefaultCustomIngredients;
import net.fabricmc.fabric.api.tag.convention.v2.ConventionalItemTags;
import net.minecraft.class_1802;
import net.minecraft.class_1856;
import net.minecraft.class_1935;
import net.minecraft.class_2246;
import net.minecraft.class_8790;
import vectorwing.farmersdelight.client.recipebook.CookingPotRecipeBookTab;
import vectorwing.farmersdelight.common.registry.ModItems;
import vectorwing.farmersdelight.common.tag.CommonTags;
import vectorwing.farmersdelight.common.tag.ConventionalTags;
import vectorwing.farmersdelight.common.tag.ModTags;
import vectorwing.farmersdelight.data.builder.CookingPotRecipeBuilder;

public class CookingRecipes {
   public static final int FAST_COOKING = 100;
   public static final int NORMAL_COOKING = 200;
   public static final int SLOW_COOKING = 400;
   public static final float SMALL_EXP = 0.35F;
   public static final float MEDIUM_EXP = 1.0F;
   public static final float LARGE_EXP = 2.0F;

   public static void register(class_8790 output) {
      cookMiscellaneous(output);
      cookMinecraftSoups(output);
      cookMeals(output);
   }

   private static void cookMiscellaneous(class_8790 output) {
      CookingPotRecipeBuilder.cookingPotRecipe((class_1935)ModItems.HOT_COCOA.get(), 1, 200, 1.0F)
         .addIngredient(ConventionalTags.DRINKS_MILK)
         .addIngredient(class_1802.field_8479)
         .addIngredient(class_1802.field_8116)
         .addIngredient(class_1802.field_8116)
         .unlockedByAnyIngredient(class_1802.field_8116, class_1802.field_8103, (class_1935)ModItems.MILK_BOTTLE.get())
         .setRecipeBookTab(CookingPotRecipeBookTab.DRINKS)
         .build(output);
      CookingPotRecipeBuilder.cookingPotRecipe((class_1935)ModItems.APPLE_CIDER.get(), 1, 200, 1.0F)
         .addIngredient(class_1802.field_8279)
         .addIngredient(class_1802.field_8279)
         .addIngredient(class_1802.field_8479)
         .unlockedByItems("has_apple", class_1802.field_8279)
         .setRecipeBookTab(CookingPotRecipeBookTab.DRINKS)
         .build(output);
      CookingPotRecipeBuilder.cookingPotRecipe((class_1935)ModItems.TOMATO_SAUCE.get(), 1, 100, 0.35F)
         .addIngredient(CommonTags.CROPS_TOMATO)
         .addIngredient(CommonTags.CROPS_TOMATO)
         .unlockedByItems("has_tomato", (class_1935)ModItems.TOMATO.get())
         .setRecipeBookTab(CookingPotRecipeBookTab.MISC)
         .build(output);
      CookingPotRecipeBuilder.cookingPotRecipe((class_1935)ModItems.DOG_FOOD.get(), 1, 200, 1.0F)
         .addIngredient(class_1802.field_8511)
         .addIngredient(class_1802.field_8324)
         .addIngredient(ConventionalItemTags.RAW_MEAT_FOODS)
         .addIngredient(CommonTags.CROPS_RICE)
         .unlockedByAnyIngredient(class_1802.field_8511, class_1802.field_8324, (class_1935)ModItems.RICE.get())
         .setRecipeBookTab(CookingPotRecipeBookTab.MISC)
         .build(output);
      CookingPotRecipeBuilder.cookingPotRecipe((class_1935)ModItems.GLOW_BERRY_CUSTARD.get(), 1, 200, 1.0F)
         .addIngredient(class_1802.field_28659)
         .addIngredient(ConventionalTags.DRINKS_MILK)
         .addIngredient(ConventionalItemTags.EGGS)
         .addIngredient(class_1802.field_8479)
         .unlockedByAnyIngredient(class_1802.field_28659, class_1802.field_8103, (class_1935)ModItems.MILK_BOTTLE.get())
         .setRecipeBookTab(CookingPotRecipeBookTab.MISC)
         .build(output);
   }

   private static void cookMinecraftSoups(class_8790 output) {
      CookingPotRecipeBuilder.cookingPotRecipe(class_1802.field_8208, 1, 200, 1.0F, class_1802.field_8428)
         .addIngredient(class_1802.field_17516)
         .addIngredient(class_1802.field_17517)
         .unlockedByAnyIngredient(class_2246.field_10251, class_2246.field_10559)
         .setRecipeBookTab(CookingPotRecipeBookTab.MEALS)
         .build(output);
      CookingPotRecipeBuilder.cookingPotRecipe(class_1802.field_8515, 1, 200, 1.0F, class_1802.field_8428)
         .addIngredient(class_1802.field_8186)
         .addIngredient(class_1802.field_8186)
         .addIngredient(class_1802.field_8186)
         .unlockedByItems("has_beetroot", class_1802.field_8186)
         .setRecipeBookTab(CookingPotRecipeBookTab.MEALS)
         .build(output);
      CookingPotRecipeBuilder.cookingPotRecipe(class_1802.field_8308, 1, 200, 1.0F, class_1802.field_8428)
         .addIngredient(class_1802.field_8512)
         .addIngredient(class_1802.field_8504)
         .addIngredient(class_1802.field_8179)
         .addIngredient(class_1856.method_8091(new class_1935[]{class_1802.field_17516, class_1802.field_17517}))
         .unlockedByAnyIngredient(class_1802.field_8504, class_1802.field_17516, class_1802.field_17517, class_1802.field_8179, class_1802.field_8512)
         .setRecipeBookTab(CookingPotRecipeBookTab.MEALS)
         .build(output);
   }

   private static void cookMeals(class_8790 output) {
      CookingPotRecipeBuilder.cookingPotRecipe((class_1935)ModItems.BAKED_COD_STEW.get(), 1, 200, 1.0F)
         .addIngredient(CommonTags.FOODS_RAW_COD)
         .addIngredient(class_1802.field_8567)
         .addIngredient(ConventionalItemTags.EGGS)
         .addIngredient(CommonTags.CROPS_TOMATO)
         .unlockedByAnyIngredient(class_1802.field_8429, class_1802.field_8567, (class_1935)ModItems.TOMATO.get(), class_1802.field_8803)
         .setRecipeBookTab(CookingPotRecipeBookTab.MEALS)
         .build(output);
      CookingPotRecipeBuilder.cookingPotRecipe((class_1935)ModItems.BEEF_STEW.get(), 1, 200, 1.0F)
         .addIngredient(CommonTags.FOODS_RAW_BEEF)
         .addIngredient(class_1802.field_8179)
         .addIngredient(class_1802.field_8567)
         .unlockedByAnyIngredient(class_1802.field_8046, class_1802.field_8179, class_1802.field_8567)
         .setRecipeBookTab(CookingPotRecipeBookTab.MEALS)
         .build(output);
      CookingPotRecipeBuilder.cookingPotRecipe((class_1935)ModItems.BONE_BROTH.get(), 1, 200, 0.35F)
         .addIngredient(ConventionalItemTags.BONES)
         .addIngredient(
            DefaultCustomIngredients.any(
               new class_1856[]{
                  class_1856.method_8091(new class_1935[]{class_1802.field_28659}),
                  class_1856.method_8106(ConventionalItemTags.MUSHROOMS),
                  class_1856.method_8091(new class_1935[]{class_1802.field_28656}),
                  class_1856.method_8091(new class_1935[]{class_1802.field_28409})
               }
            )
         )
         .unlockedByItems("has_bone", class_1802.field_8606)
         .setRecipeBookTab(CookingPotRecipeBookTab.MEALS)
         .build(output);
      CookingPotRecipeBuilder.cookingPotRecipe((class_1935)ModItems.CABBAGE_ROLLS.get(), 1, 100, 0.35F)
         .addIngredient(CommonTags.CROPS_CABBAGE)
         .addIngredient(ModTags.CABBAGE_ROLL_INGREDIENTS)
         .unlockedByAnyIngredient((class_1935)ModItems.CABBAGE.get(), (class_1935)ModItems.CABBAGE_LEAF.get())
         .setRecipeBookTab(CookingPotRecipeBookTab.MISC)
         .build(output);
      CookingPotRecipeBuilder.cookingPotRecipe((class_1935)ModItems.CHICKEN_SOUP.get(), 1, 200, 1.0F)
         .addIngredient(CommonTags.FOODS_RAW_CHICKEN)
         .addIngredient(class_1802.field_8179)
         .addIngredient(CommonTags.FOODS_LEAFY_GREEN)
         .addIngredient(ConventionalItemTags.VEGETABLE_FOODS)
         .unlockedByAnyIngredient(class_1802.field_8726, class_1802.field_8179)
         .setRecipeBookTab(CookingPotRecipeBookTab.MEALS)
         .build(output);
      CookingPotRecipeBuilder.cookingPotRecipe((class_1935)ModItems.COOKED_RICE.get(), 1, 100, 0.35F)
         .addIngredient(CommonTags.CROPS_RICE)
         .unlockedByItems("has_rice", (class_1935)ModItems.RICE.get())
         .setRecipeBookTab(CookingPotRecipeBookTab.MISC)
         .build(output);
      CookingPotRecipeBuilder.cookingPotRecipe((class_1935)ModItems.DUMPLINGS.get(), 2, 200, 1.0F)
         .addIngredient(CommonTags.FOODS_DOUGH)
         .addIngredient(CommonTags.CROPS_CABBAGE)
         .addIngredient(CommonTags.CROPS_ONION)
         .addIngredient(
            DefaultCustomIngredients.any(
               new class_1856[]{
                  class_1856.method_8106(CommonTags.FOODS_RAW_CHICKEN),
                  class_1856.method_8106(CommonTags.FOODS_RAW_PORK),
                  class_1856.method_8106(CommonTags.FOODS_RAW_BEEF),
                  class_1856.method_8091(new class_1935[]{class_1802.field_17516})
               }
            )
         )
         .unlockedByAnyIngredient((class_1935)ModItems.WHEAT_DOUGH.get(), (class_1935)ModItems.CABBAGE.get(), (class_1935)ModItems.ONION.get())
         .setRecipeBookTab(CookingPotRecipeBookTab.MISC)
         .build(output);
      CookingPotRecipeBuilder.cookingPotRecipe((class_1935)ModItems.FISH_STEW.get(), 1, 200, 1.0F)
         .addIngredient(CommonTags.FOODS_SAFE_RAW_FISH)
         .addIngredient((class_1935)ModItems.TOMATO_SAUCE.get())
         .addIngredient(CommonTags.CROPS_ONION)
         .unlockedByAnyIngredient(
            class_1802.field_8209, class_1802.field_8429, class_1802.field_8846, (class_1935)ModItems.TOMATO_SAUCE.get(), (class_1935)ModItems.ONION.get()
         )
         .setRecipeBookTab(CookingPotRecipeBookTab.MEALS)
         .build(output);
      CookingPotRecipeBuilder.cookingPotRecipe((class_1935)ModItems.FRIED_RICE.get(), 1, 200, 1.0F)
         .addIngredient(CommonTags.CROPS_RICE)
         .addIngredient(ConventionalItemTags.EGGS)
         .addIngredient(class_1802.field_8179)
         .addIngredient(CommonTags.CROPS_ONION)
         .unlockedByAnyIngredient((class_1935)ModItems.RICE.get(), class_1802.field_8803, class_1802.field_8179, (class_1935)ModItems.ONION.get())
         .setRecipeBookTab(CookingPotRecipeBookTab.MEALS)
         .build(output);
      CookingPotRecipeBuilder.cookingPotRecipe((class_1935)ModItems.MUSHROOM_RICE.get(), 1, 200, 1.0F)
         .addIngredient(class_1802.field_17516)
         .addIngredient(class_1802.field_17517)
         .addIngredient(CommonTags.CROPS_RICE)
         .addIngredient(class_1856.method_8091(new class_1935[]{class_1802.field_8179, class_1802.field_8567}))
         .unlockedByAnyIngredient(class_2246.field_10251, class_2246.field_10559, (class_1935)ModItems.RICE.get())
         .setRecipeBookTab(CookingPotRecipeBookTab.MEALS)
         .build(output);
      CookingPotRecipeBuilder.cookingPotRecipe((class_1935)ModItems.NOODLE_SOUP.get(), 1, 200, 1.0F)
         .addIngredient(CommonTags.FOODS_PASTA)
         .addIngredient(CommonTags.FOODS_COOKED_EGG)
         .addIngredient(class_1802.field_8551)
         .addIngredient(CommonTags.FOODS_RAW_PORK)
         .unlockedByAnyIngredient((class_1935)ModItems.RAW_PASTA.get(), class_1802.field_8551, class_1802.field_8389)
         .setRecipeBookTab(CookingPotRecipeBookTab.MEALS)
         .build(output);
      CookingPotRecipeBuilder.cookingPotRecipe((class_1935)ModItems.PASTA_WITH_MEATBALLS.get(), 1, 200, 1.0F)
         .addIngredient((class_1935)ModItems.MINCED_BEEF.get())
         .addIngredient(CommonTags.FOODS_PASTA)
         .addIngredient((class_1935)ModItems.TOMATO_SAUCE.get())
         .unlockedByAnyIngredient((class_1935)ModItems.RAW_PASTA.get(), class_1802.field_8046, (class_1935)ModItems.TOMATO_SAUCE.get())
         .setRecipeBookTab(CookingPotRecipeBookTab.MEALS)
         .build(output);
      CookingPotRecipeBuilder.cookingPotRecipe((class_1935)ModItems.PASTA_WITH_MUTTON_CHOP.get(), 1, 200, 1.0F)
         .addIngredient(CommonTags.FOODS_RAW_MUTTON)
         .addIngredient(CommonTags.FOODS_PASTA)
         .addIngredient((class_1935)ModItems.TOMATO_SAUCE.get())
         .unlockedByAnyIngredient((class_1935)ModItems.RAW_PASTA.get(), class_1802.field_8748, (class_1935)ModItems.TOMATO_SAUCE.get())
         .setRecipeBookTab(CookingPotRecipeBookTab.MEALS)
         .build(output);
      CookingPotRecipeBuilder.cookingPotRecipe((class_1935)ModItems.PUMPKIN_SOUP.get(), 1, 200, 1.0F)
         .addIngredient((class_1935)ModItems.PUMPKIN_SLICE.get())
         .addIngredient(CommonTags.FOODS_LEAFY_GREEN)
         .addIngredient(CommonTags.FOODS_RAW_PORK)
         .addIngredient(ConventionalTags.DRINKS_MILK)
         .unlockedByAnyIngredient(
            class_1802.field_17518,
            (class_1935)ModItems.PUMPKIN_SLICE.get(),
            class_1802.field_8389,
            class_1802.field_8103,
            (class_1935)ModItems.MILK_BOTTLE.get()
         )
         .setRecipeBookTab(CookingPotRecipeBookTab.MEALS)
         .build(output);
      CookingPotRecipeBuilder.cookingPotRecipe((class_1935)ModItems.RATATOUILLE.get(), 1, 200, 1.0F)
         .addIngredient(CommonTags.CROPS_TOMATO)
         .addIngredient(CommonTags.CROPS_ONION)
         .addIngredient(class_1802.field_8186)
         .addIngredient(ConventionalItemTags.VEGETABLE_FOODS)
         .unlockedByAnyIngredient((class_1935)ModItems.TOMATO.get(), (class_1935)ModItems.ONION.get(), class_1802.field_8186)
         .setRecipeBookTab(CookingPotRecipeBookTab.MEALS)
         .build(output);
      CookingPotRecipeBuilder.cookingPotRecipe((class_1935)ModItems.SQUID_INK_PASTA.get(), 1, 200, 1.0F)
         .addIngredient(CommonTags.FOODS_SAFE_RAW_FISH)
         .addIngredient(CommonTags.FOODS_PASTA)
         .addIngredient(CommonTags.CROPS_TOMATO)
         .addIngredient(class_1802.field_8794)
         .unlockedByAnyIngredient((class_1935)ModItems.RAW_PASTA.get(), class_1802.field_8794, (class_1935)ModItems.TOMATO.get())
         .setRecipeBookTab(CookingPotRecipeBookTab.MEALS)
         .build(output);
      CookingPotRecipeBuilder.cookingPotRecipe((class_1935)ModItems.STUFFED_PUMPKIN_BLOCK.get(), 1, 400, 2.0F, class_1802.field_17518)
         .addIngredient(CommonTags.CROPS_RICE)
         .addIngredient(CommonTags.CROPS_ONION)
         .addIngredient(class_1802.field_17516)
         .addIngredient(class_1802.field_8567)
         .addIngredient(ConventionalItemTags.BERRY_FOODS)
         .addIngredient(ConventionalItemTags.VEGETABLE_FOODS)
         .unlockedByItems("has_pumpkin", class_2246.field_46282)
         .setRecipeBookTab(CookingPotRecipeBookTab.MEALS)
         .build(output);
      CookingPotRecipeBuilder.cookingPotRecipe((class_1935)ModItems.VEGETABLE_NOODLES.get(), 1, 200, 1.0F)
         .addIngredient(class_1802.field_8179)
         .addIngredient(class_1802.field_17516)
         .addIngredient(CommonTags.FOODS_PASTA)
         .addIngredient(CommonTags.FOODS_LEAFY_GREEN)
         .addIngredient(ConventionalItemTags.VEGETABLE_FOODS)
         .unlockedByAnyIngredient((class_1935)ModItems.RAW_PASTA.get(), class_1802.field_17516, class_1802.field_8179)
         .setRecipeBookTab(CookingPotRecipeBookTab.MEALS)
         .build(output);
      CookingPotRecipeBuilder.cookingPotRecipe((class_1935)ModItems.VEGETABLE_SOUP.get(), 1, 200, 1.0F)
         .addIngredient(class_1802.field_8179)
         .addIngredient(class_1802.field_8567)
         .addIngredient(class_1802.field_8186)
         .addIngredient(CommonTags.FOODS_LEAFY_GREEN)
         .unlockedByAnyIngredient(class_1802.field_8179, (class_1935)ModItems.ONION.get(), class_1802.field_8186)
         .setRecipeBookTab(CookingPotRecipeBookTab.MEALS)
         .build(output);
   }
}
