package vectorwing.farmersdelight.integration.crafttweaker.managers;

import com.blamejared.crafttweaker.api.CraftTweakerAPI;
import com.blamejared.crafttweaker.api.CraftTweakerConstants;
import com.blamejared.crafttweaker.api.action.recipe.ActionAddRecipe;
import com.blamejared.crafttweaker.api.annotation.ZenRegister;
import com.blamejared.crafttweaker.api.ingredient.IIngredient;
import com.blamejared.crafttweaker.api.item.IItemStack;
import com.blamejared.crafttweaker.api.recipe.manager.base.IRecipeManager;
import com.blamejared.crafttweaker_annotations.annotations.Document;
import net.minecraft.class_1799;
import net.minecraft.class_1856;
import net.minecraft.class_2371;
import net.minecraft.class_3956;
import net.minecraft.class_8786;
import org.openzen.zencode.java.ZenCodeType.Method;
import org.openzen.zencode.java.ZenCodeType.Name;
import org.openzen.zencode.java.ZenCodeType.Optional;
import org.openzen.zencode.java.ZenCodeType.OptionalFloat;
import org.openzen.zencode.java.ZenCodeType.OptionalInt;
import vectorwing.farmersdelight.client.recipebook.CookingPotRecipeBookTab;
import vectorwing.farmersdelight.common.crafting.CookingPotRecipe;
import vectorwing.farmersdelight.common.registry.ModRecipeTypes;
import vectorwing.farmersdelight.common.utility.ListUtils;
import vectorwing.farmersdelight.integration.crafttweaker.FarmersDelightCrTPlugin;

@Document("mods/FarmersDelight/CookingPot")
@ZenRegister
@Name("mods.farmersdelight.CookingPot")
public class CookingPotRecipeManager implements IRecipeManager {
   @Method
   public void addRecipe(
      String name,
      IItemStack output,
      IIngredient[] inputs,
      @Optional CookingPotRecipeBookTab cookingPotRecipeBookTab,
      @Optional IItemStack container,
      @OptionalFloat float experience,
      @OptionalInt(200) int cookTime
   ) {
      if (this.validateInputs(inputs)) {
         CraftTweakerAPI.apply(
            new ActionAddRecipe(
               this,
               new class_8786(
                  CraftTweakerConstants.rl(name),
                  new CookingPotRecipe(
                     "",
                     cookingPotRecipeBookTab,
                     ListUtils.mapArrayIndexSet(inputs, IIngredient::asVanillaIngredient, class_2371.method_10213(inputs.length, class_1856.field_9017)),
                     output.getInternal(),
                     container == null ? class_1799.field_8037 : container.getInternal(),
                     experience,
                     cookTime
                  )
               ),
               ""
            )
         );
      }
   }

   private boolean validateInputs(IIngredient[] inputs) {
      if (inputs.length == 0) {
         FarmersDelightCrTPlugin.LOGGER_CT.error("No ingredients for cooking recipe");
         return false;
      } else if (inputs.length > 6) {
         FarmersDelightCrTPlugin.LOGGER_CT.error("Too many ingredients for cooking recipe! The max is %s", 6);
         return false;
      } else {
         return true;
      }
   }

   public class_3956<CookingPotRecipe> getRecipeType() {
      return ModRecipeTypes.COOKING.get();
   }
}
