package vectorwing.farmersdelight.integration.crafttweaker.managers;

import com.blamejared.crafttweaker.api.CraftTweakerAPI;
import com.blamejared.crafttweaker.api.CraftTweakerConstants;
import com.blamejared.crafttweaker.api.action.recipe.ActionAddRecipe;
import com.blamejared.crafttweaker.api.annotation.ZenRegister;
import com.blamejared.crafttweaker.api.ingredient.IIngredient;
import com.blamejared.crafttweaker.api.item.IItemStack;
import com.blamejared.crafttweaker.api.recipe.manager.base.IRecipeManager;
import com.blamejared.crafttweaker.api.util.random.Percentaged;
import com.blamejared.crafttweaker_annotations.annotations.Document;
import net.minecraft.class_2371;
import net.minecraft.class_2960;
import net.minecraft.class_3956;
import net.minecraft.class_7923;
import net.minecraft.class_8786;
import org.openzen.zencode.java.ZenCodeType.Method;
import org.openzen.zencode.java.ZenCodeType.Name;
import org.openzen.zencode.java.ZenCodeType.OptionalString;
import vectorwing.farmersdelight.common.crafting.CuttingBoardRecipe;
import vectorwing.farmersdelight.common.crafting.ingredient.ChanceResult;
import vectorwing.farmersdelight.common.registry.ModRecipeTypes;
import vectorwing.farmersdelight.common.utility.ListUtils;
import vectorwing.farmersdelight.integration.crafttweaker.actions.ActionRemoveCuttingBoardRecipe;

@Document("mods/FarmersDelight/CuttingBoard")
@ZenRegister
@Name("mods.farmersdelight.CuttingBoard")
public class CuttingBoardRecipeManager implements IRecipeManager {
   @Method
   public void addRecipe(String name, IIngredient input, Percentaged<IItemStack>[] results, IIngredient tool, @OptionalString String sound) {
      CraftTweakerAPI.apply(
         new ActionAddRecipe(
            this,
            new class_8786(
               CraftTweakerConstants.rl(name),
               new CuttingBoardRecipe(
                  "",
                  input.asVanillaIngredient(),
                  tool.asVanillaIngredient(),
                  ListUtils.mapArrayIndexSet(
                     results,
                     stack -> new ChanceResult(((IItemStack)stack.getData()).getInternal(), (float)stack.getPercentage()),
                     class_2371.method_10213(results.length, ChanceResult.EMPTY)
                  ),
                  class_7923.field_41172.method_17966(class_2960.method_60654(sound))
               )
            ),
            ""
         )
      );
   }

   @Method
   public void removeRecipe(IItemStack[] outputs) {
      CraftTweakerAPI.apply(new ActionRemoveCuttingBoardRecipe(this, outputs));
   }

   public class_3956<CuttingBoardRecipe> getRecipeType() {
      return ModRecipeTypes.CUTTING.get();
   }
}
