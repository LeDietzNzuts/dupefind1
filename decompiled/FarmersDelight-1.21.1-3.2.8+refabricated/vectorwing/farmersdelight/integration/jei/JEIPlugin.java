package vectorwing.farmersdelight.integration.jei;

import com.google.common.collect.ImmutableList;
import java.util.List;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.constants.RecipeTypes;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.category.IRecipeCategory;
import mezz.jei.api.registration.IGuiHandlerRegistration;
import mezz.jei.api.registration.IRecipeCatalystRegistration;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import mezz.jei.api.registration.IRecipeTransferRegistration;
import net.minecraft.class_1799;
import net.minecraft.class_1802;
import net.minecraft.class_1935;
import net.minecraft.class_2561;
import net.minecraft.class_2960;
import net.minecraft.class_6328;
import vectorwing.farmersdelight.client.gui.CookingPotScreen;
import vectorwing.farmersdelight.common.block.entity.container.CookingPotMenu;
import vectorwing.farmersdelight.common.registry.ModBlocks;
import vectorwing.farmersdelight.common.registry.ModItems;
import vectorwing.farmersdelight.common.registry.ModMenuTypes;
import vectorwing.farmersdelight.common.utility.TextUtils;
import vectorwing.farmersdelight.integration.jei.category.CookingRecipeCategory;
import vectorwing.farmersdelight.integration.jei.category.CuttingRecipeCategory;
import vectorwing.farmersdelight.integration.jei.category.DecompositionRecipeCategory;
import vectorwing.farmersdelight.integration.jei.resource.DecompositionDummy;

@class_6328
@JeiPlugin
public class JEIPlugin implements IModPlugin {
   private static final class_2960 ID = class_2960.method_60655("farmersdelight", "jei_plugin");

   public void registerCategories(IRecipeCategoryRegistration registry) {
      registry.addRecipeCategories(new IRecipeCategory[]{new CookingRecipeCategory(registry.getJeiHelpers().getGuiHelper())});
      registry.addRecipeCategories(new IRecipeCategory[]{new CuttingRecipeCategory(registry.getJeiHelpers().getGuiHelper())});
      registry.addRecipeCategories(new IRecipeCategory[]{new DecompositionRecipeCategory(registry.getJeiHelpers().getGuiHelper())});
   }

   public void registerRecipes(IRecipeRegistration registration) {
      FDRecipes modRecipes = new FDRecipes();
      registration.addRecipes(FDRecipeTypes.COOKING, modRecipes.getCookingPotRecipes());
      registration.addRecipes(FDRecipeTypes.CUTTING, modRecipes.getCuttingBoardRecipes());
      registration.addRecipes(FDRecipeTypes.DECOMPOSITION, ImmutableList.of(new DecompositionDummy()));
      registration.addRecipes(RecipeTypes.CRAFTING, modRecipes.getSpecialWheatDoughRecipe());
      registration.addIngredientInfo(
         new class_1799((class_1935)ModItems.WHEAT_DOUGH.get()), VanillaTypes.ITEM_STACK, new class_2561[]{TextUtils.getTranslation("jei.info.dough")}
      );
      registration.addIngredientInfo(
         new class_1799((class_1935)ModItems.STRAW.get()), VanillaTypes.ITEM_STACK, new class_2561[]{TextUtils.getTranslation("jei.info.straw")}
      );
      registration.addIngredientInfo(
         new class_1799((class_1935)ModItems.HAM.get()), VanillaTypes.ITEM_STACK, new class_2561[]{TextUtils.getTranslation("jei.info.ham")}
      );
      registration.addIngredientInfo(
         new class_1799((class_1935)ModItems.SMOKED_HAM.get()), VanillaTypes.ITEM_STACK, new class_2561[]{TextUtils.getTranslation("jei.info.ham")}
      );
      registration.addIngredientInfo(
         new class_1799((class_1935)ModItems.FLINT_KNIFE.get()), VanillaTypes.ITEM_STACK, new class_2561[]{TextUtils.getTranslation("jei.info.knife")}
      );
      registration.addIngredientInfo(
         new class_1799((class_1935)ModItems.IRON_KNIFE.get()), VanillaTypes.ITEM_STACK, new class_2561[]{TextUtils.getTranslation("jei.info.knife")}
      );
      registration.addIngredientInfo(
         new class_1799((class_1935)ModItems.DIAMOND_KNIFE.get()), VanillaTypes.ITEM_STACK, new class_2561[]{TextUtils.getTranslation("jei.info.knife")}
      );
      registration.addIngredientInfo(
         new class_1799((class_1935)ModItems.NETHERITE_KNIFE.get()), VanillaTypes.ITEM_STACK, new class_2561[]{TextUtils.getTranslation("jei.info.knife")}
      );
      registration.addIngredientInfo(
         new class_1799((class_1935)ModItems.GOLDEN_KNIFE.get()), VanillaTypes.ITEM_STACK, new class_2561[]{TextUtils.getTranslation("jei.info.knife")}
      );
      registration.addIngredientInfo(
         List.of(
            new class_1799((class_1935)ModItems.WILD_CABBAGES.get()),
            new class_1799((class_1935)ModItems.CABBAGE.get()),
            new class_1799((class_1935)ModItems.CABBAGE_LEAF.get())
         ),
         VanillaTypes.ITEM_STACK,
         new class_2561[]{TextUtils.getTranslation("jei.info.wild_cabbages")}
      );
      registration.addIngredientInfo(
         List.of(new class_1799((class_1935)ModItems.WILD_BEETROOTS.get()), new class_1799(class_1802.field_8186)),
         VanillaTypes.ITEM_STACK,
         new class_2561[]{TextUtils.getTranslation("jei.info.wild_beetroots")}
      );
      registration.addIngredientInfo(
         List.of(new class_1799((class_1935)ModItems.WILD_CARROTS.get()), new class_1799(class_1802.field_8179)),
         VanillaTypes.ITEM_STACK,
         new class_2561[]{TextUtils.getTranslation("jei.info.wild_carrots")}
      );
      registration.addIngredientInfo(
         List.of(new class_1799((class_1935)ModItems.WILD_ONIONS.get()), new class_1799((class_1935)ModItems.ONION.get())),
         VanillaTypes.ITEM_STACK,
         new class_2561[]{TextUtils.getTranslation("jei.info.wild_onions")}
      );
      registration.addIngredientInfo(
         List.of(new class_1799((class_1935)ModItems.WILD_POTATOES.get()), new class_1799(class_1802.field_8567)),
         VanillaTypes.ITEM_STACK,
         new class_2561[]{TextUtils.getTranslation("jei.info.wild_potatoes")}
      );
      registration.addIngredientInfo(
         List.of(new class_1799((class_1935)ModItems.WILD_TOMATOES.get()), new class_1799((class_1935)ModItems.TOMATO.get())),
         VanillaTypes.ITEM_STACK,
         new class_2561[]{TextUtils.getTranslation("jei.info.wild_tomatoes")}
      );
      registration.addIngredientInfo(
         List.of(
            new class_1799((class_1935)ModItems.WILD_RICE.get()),
            new class_1799((class_1935)ModItems.RICE.get()),
            new class_1799((class_1935)ModItems.RICE_PANICLE.get())
         ),
         VanillaTypes.ITEM_STACK,
         new class_2561[]{TextUtils.getTranslation("jei.info.wild_rice")}
      );
   }

   public void registerRecipeCatalysts(IRecipeCatalystRegistration registration) {
      registration.addRecipeCatalyst(new class_1799((class_1935)ModItems.COOKING_POT.get()), new RecipeType[]{FDRecipeTypes.COOKING});
      registration.addRecipeCatalyst(new class_1799((class_1935)ModItems.CUTTING_BOARD.get()), new RecipeType[]{FDRecipeTypes.CUTTING});
      registration.addRecipeCatalyst(new class_1799((class_1935)ModItems.STOVE.get()), new RecipeType[]{RecipeTypes.CAMPFIRE_COOKING});
      registration.addRecipeCatalyst(new class_1799((class_1935)ModItems.SKILLET.get()), new RecipeType[]{RecipeTypes.CAMPFIRE_COOKING});
      registration.addRecipeCatalyst(new class_1799((class_1935)ModBlocks.ORGANIC_COMPOST.get()), new RecipeType[]{FDRecipeTypes.DECOMPOSITION});
   }

   public void registerGuiHandlers(IGuiHandlerRegistration registration) {
      registration.addRecipeClickArea(CookingPotScreen.class, 89, 25, 24, 17, new RecipeType[]{FDRecipeTypes.COOKING});
   }

   public void registerRecipeTransferHandlers(IRecipeTransferRegistration registration) {
      registration.addRecipeTransferHandler(CookingPotMenu.class, ModMenuTypes.COOKING_POT.get(), FDRecipeTypes.COOKING, 0, 6, 9, 36);
   }

   public class_2960 getPluginUid() {
      return ID;
   }
}
