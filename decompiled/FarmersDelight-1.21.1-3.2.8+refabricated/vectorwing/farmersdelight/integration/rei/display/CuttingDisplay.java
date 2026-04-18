package vectorwing.farmersdelight.integration.rei.display;

import com.google.common.collect.ImmutableList.Builder;
import it.unimi.dsi.fastutil.Pair;
import java.util.List;
import java.util.Optional;
import me.shedaniel.rei.api.common.category.CategoryIdentifier;
import me.shedaniel.rei.api.common.display.basic.BasicDisplay;
import me.shedaniel.rei.api.common.display.basic.BasicDisplay.Serializer;
import me.shedaniel.rei.api.common.entry.EntryIngredient;
import me.shedaniel.rei.api.common.entry.EntryStack;
import me.shedaniel.rei.api.common.util.EntryIngredients;
import net.minecraft.class_2487;
import net.minecraft.class_2499;
import net.minecraft.class_2960;
import net.minecraft.class_8786;
import vectorwing.farmersdelight.common.crafting.CuttingBoardRecipe;
import vectorwing.farmersdelight.integration.rei.REICategoryIdentifiers;

public class CuttingDisplay extends BasicDisplay {
   private EntryIngredient tool;
   private List<Pair<EntryIngredient, Float>> chanceResults;

   public CuttingDisplay(class_8786<CuttingBoardRecipe> recipe) {
      this(
         EntryIngredients.ofIngredients(((CuttingBoardRecipe)recipe.comp_1933()).method_8117()),
         ((CuttingBoardRecipe)recipe.comp_1933()).getRollableResults().stream().map(result -> EntryIngredients.of(result.stack())).toList(),
         Optional.of(recipe.comp_1932()),
         EntryIngredients.ofIngredient(((CuttingBoardRecipe)recipe.comp_1933()).getTool()),
         ((CuttingBoardRecipe)recipe.comp_1933())
            .getRollableResults()
            .stream()
            .map(result -> Pair.of(EntryIngredients.of(result.stack()), result.chance()))
            .toList()
      );
   }

   public CuttingDisplay(List<EntryIngredient> inputs, List<EntryIngredient> outputs, Optional<class_2960> location, class_2487 tag) {
      this(inputs, outputs, location, EntryIngredient.of(EntryStack.read(tag.method_10562("tool"))), deserializeChanceResults(tag));
   }

   public CuttingDisplay(
      List<EntryIngredient> inputs,
      List<EntryIngredient> outputs,
      Optional<class_2960> location,
      EntryIngredient tool,
      List<Pair<EntryIngredient, Float>> chanceResults
   ) {
      super(inputs, outputs, location);
      this.tool = tool;
      this.chanceResults = chanceResults;
   }

   public EntryIngredient getTool() {
      return this.tool;
   }

   public List<Pair<EntryIngredient, Float>> getRollableOutputs() {
      return this.chanceResults;
   }

   private static List<Pair<EntryIngredient, Float>> deserializeChanceResults(class_2487 tag) {
      Builder<Pair<EntryIngredient, Float>> builder = new Builder();
      class_2499 innerTag = tag.method_10554("chance_results", 10);

      for (int i = 0; i < innerTag.size(); i++) {
         class_2487 entry = innerTag.method_10602(i);
         builder.add(Pair.of(EntryIngredient.of(EntryStack.read(entry.method_10562("stack"))), entry.method_10583("chance")));
      }

      return builder.build();
   }

   public CategoryIdentifier<?> getCategoryIdentifier() {
      return REICategoryIdentifiers.CUTTING;
   }

   public static Serializer<CuttingDisplay> serializer() {
      return Serializer.of(CuttingDisplay::new, (display, tag) -> {
         display.tool = EntryIngredient.of(EntryStack.read(tag.method_10562("tool")));
         display.chanceResults = deserializeChanceResults(tag);
      });
   }
}
