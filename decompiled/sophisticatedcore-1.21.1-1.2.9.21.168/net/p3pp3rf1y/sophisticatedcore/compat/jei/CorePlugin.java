package net.p3pp3rf1y.sophisticatedcore.compat.jei;

import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.constants.RecipeTypes;
import mezz.jei.api.registration.IRecipeRegistration;
import net.minecraft.class_2960;
import net.minecraft.class_3956;
import net.p3pp3rf1y.sophisticatedcore.crafting.UpgradeNextTierRecipe;

@JeiPlugin
public class CorePlugin implements IModPlugin {
   public class_2960 getPluginUid() {
      return class_2960.method_60655("sophisticatedcore", "default");
   }

   public void registerRecipes(IRecipeRegistration registration) {
      registration.addRecipes(
         RecipeTypes.CRAFTING,
         ClientRecipeHelper.transformAllRecipesOfType(class_3956.field_17545, UpgradeNextTierRecipe.class, ClientRecipeHelper::copyShapedRecipe)
      );
   }
}
