package vectorwing.farmersdelight.integration.crafttweaker.handlers;

import com.blamejared.crafttweaker.api.ingredient.IIngredient;
import com.blamejared.crafttweaker.api.item.IItemStack;
import com.blamejared.crafttweaker.api.item.MCItemStack;
import com.blamejared.crafttweaker.api.item.MCItemStackMutable;
import com.blamejared.crafttweaker.api.recipe.component.IDecomposedRecipe;
import com.blamejared.crafttweaker.api.recipe.component.BuiltinRecipeComponents.Input;
import com.blamejared.crafttweaker.api.recipe.component.BuiltinRecipeComponents.Metadata;
import com.blamejared.crafttweaker.api.recipe.component.BuiltinRecipeComponents.Output;
import com.blamejared.crafttweaker.api.recipe.handler.IRecipeHandler;
import com.blamejared.crafttweaker.api.recipe.handler.IRecipeHandler.For;
import com.blamejared.crafttweaker.api.recipe.manager.base.IRecipeManager;
import com.blamejared.crafttweaker.api.util.StringUtil;
import com.blamejared.crafttweaker.api.util.random.Percentaged;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import net.minecraft.class_1856;
import net.minecraft.class_1860;
import net.minecraft.class_2371;
import net.minecraft.class_3414;
import net.minecraft.class_5455;
import net.minecraft.class_7923;
import net.minecraft.class_8786;
import vectorwing.farmersdelight.common.crafting.CuttingBoardRecipe;
import vectorwing.farmersdelight.common.crafting.ingredient.ChanceResult;

@For(CuttingBoardRecipe.class)
public class CuttingBoardRecipeHandler implements IRecipeHandler<CuttingBoardRecipe> {
   public String dumpToCommandString(IRecipeManager<? super CuttingBoardRecipe> manager, class_5455 registryAccess, class_8786<CuttingBoardRecipe> recipe) {
      return String.format(
         "%s.addRecipe(%s, %s, %s, %s, %s);",
         manager.getCommandString(),
         StringUtil.quoteAndEscape(recipe.comp_1932()),
         IIngredient.fromIngredient((class_1856)((CuttingBoardRecipe)recipe.comp_1933()).method_8117().get(0)).getCommandString(),
         ((CuttingBoardRecipe)recipe.comp_1933())
            .getResults()
            .stream()
            .map(MCItemStackMutable::new)
            .<CharSequence>map(IItemStack::getCommandString)
            .collect(Collectors.joining(", ", "[", "]")),
         IIngredient.fromIngredient(((CuttingBoardRecipe)recipe.comp_1933()).getTool()).getCommandString(),
         ((CuttingBoardRecipe)recipe.comp_1933()).getSoundEvent().map(class_7923.field_41172::method_10221)
      );
   }

   public <U extends class_1860<?>> boolean doesConflict(IRecipeManager<? super CuttingBoardRecipe> manager, CuttingBoardRecipe firstRecipe, U secondRecipe) {
      return firstRecipe.equals(secondRecipe);
   }

   public Optional<IDecomposedRecipe> decompose(IRecipeManager<? super CuttingBoardRecipe> manager, class_5455 registryAccess, CuttingBoardRecipe recipe) {
      IDecomposedRecipe decomposedRecipe = IDecomposedRecipe.builder()
         .with(Input.INGREDIENTS, recipe.method_8117().stream().map(IIngredient::fromIngredient).toList())
         .with(RecipeHandlerUtils.TOOL_COMPONENT, IIngredient.fromIngredient(recipe.getTool()))
         .with(Metadata.GROUP, recipe.method_8112())
         .with(
            Output.CHANCED_ITEMS,
            recipe.getRollableResults().stream().map(chanceResult -> new MCItemStack(chanceResult.stack()).percent(chanceResult.chance())).toList()
         )
         .build();
      if (recipe.getSoundEvent().isPresent()) {
         decomposedRecipe.set(RecipeHandlerUtils.SOUND_COMPONENT, recipe.getSoundEvent().get());
      }

      return Optional.of(decomposedRecipe);
   }

   public Optional<CuttingBoardRecipe> recompose(IRecipeManager<? super CuttingBoardRecipe> manager, class_5455 registryAccess, IDecomposedRecipe recipe) {
      String group = (String)recipe.getOrThrowSingle(Metadata.GROUP);
      List<IIngredient> ingredients = recipe.getOrThrow(Input.INGREDIENTS);
      IIngredient tool = (IIngredient)recipe.getOrThrowSingle(RecipeHandlerUtils.TOOL_COMPONENT);
      IIngredient[] ingredientArray = ingredients.toArray(IIngredient[]::new);
      List<Percentaged<IItemStack>> results = recipe.getOrThrow(Output.CHANCED_ITEMS);
      class_2371<ChanceResult> stackedResults = class_2371.method_10211();
      stackedResults.addAll(
         results.stream()
            .map(
               iItemStackPercentaged -> new ChanceResult(
                  ((IItemStack)iItemStackPercentaged.getData()).getInternal(), (float)iItemStackPercentaged.getPercentage()
               )
            )
            .toList()
      );
      List<class_3414> soundList = recipe.get(RecipeHandlerUtils.SOUND_COMPONENT);
      Optional<class_3414> sound = soundList == null ? Optional.empty() : Optional.of(soundList.get(0));
      class_1856 input = ingredientArray[0].asVanillaIngredient();
      return Optional.of(new CuttingBoardRecipe(group, input, tool.asVanillaIngredient(), stackedResults, sound));
   }
}
