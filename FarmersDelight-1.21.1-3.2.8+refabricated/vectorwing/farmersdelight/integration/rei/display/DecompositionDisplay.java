package vectorwing.farmersdelight.integration.rei.display;

import java.util.List;
import me.shedaniel.rei.api.common.category.CategoryIdentifier;
import me.shedaniel.rei.api.common.display.Display;
import me.shedaniel.rei.api.common.entry.EntryIngredient;
import me.shedaniel.rei.api.common.util.EntryIngredients;
import net.minecraft.class_1935;
import vectorwing.farmersdelight.common.registry.ModItems;
import vectorwing.farmersdelight.integration.rei.REICategoryIdentifiers;

public class DecompositionDisplay implements Display {
   public List<EntryIngredient> getInputEntries() {
      return List.of(EntryIngredients.of((class_1935)ModItems.ORGANIC_COMPOST.get()));
   }

   public List<EntryIngredient> getOutputEntries() {
      return List.of(EntryIngredients.of((class_1935)ModItems.RICH_SOIL.get()));
   }

   public CategoryIdentifier<?> getCategoryIdentifier() {
      return REICategoryIdentifiers.DECOMPOSITION;
   }
}
