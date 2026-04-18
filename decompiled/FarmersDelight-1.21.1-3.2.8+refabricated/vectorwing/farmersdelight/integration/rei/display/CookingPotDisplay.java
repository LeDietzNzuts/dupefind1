package vectorwing.farmersdelight.integration.rei.display;

import java.util.List;
import java.util.Optional;
import me.shedaniel.rei.api.common.category.CategoryIdentifier;
import me.shedaniel.rei.api.common.display.basic.BasicDisplay;
import me.shedaniel.rei.api.common.display.basic.BasicDisplay.Serializer;
import me.shedaniel.rei.api.common.entry.EntryIngredient;
import me.shedaniel.rei.api.common.entry.EntryStack;
import me.shedaniel.rei.api.common.util.EntryIngredients;
import net.minecraft.class_2487;
import net.minecraft.class_2960;
import net.minecraft.class_8786;
import vectorwing.farmersdelight.common.crafting.CookingPotRecipe;
import vectorwing.farmersdelight.integration.rei.REICategoryIdentifiers;

public class CookingPotDisplay extends BasicDisplay {
   private EntryIngredient container;
   private int cookTime;
   private float experience;

   public CookingPotDisplay(class_8786<CookingPotRecipe> recipe) {
      this(
         EntryIngredients.ofIngredients(((CookingPotRecipe)recipe.comp_1933()).method_8117()),
         List.of(EntryIngredients.of(((CookingPotRecipe)recipe.comp_1933()).method_8110(registryAccess()))),
         Optional.of(recipe.comp_1932()),
         EntryIngredients.of(((CookingPotRecipe)recipe.comp_1933()).getOutputContainer()),
         ((CookingPotRecipe)recipe.comp_1933()).getCookTime(),
         ((CookingPotRecipe)recipe.comp_1933()).getExperience()
      );
   }

   public CookingPotDisplay(List<EntryIngredient> inputs, List<EntryIngredient> outputs, Optional<class_2960> location, class_2487 tag) {
      this(
         inputs,
         outputs,
         location,
         EntryIngredient.of(EntryStack.read(tag.method_10562("container"))),
         tag.method_10550("cook_time"),
         tag.method_10583("experience")
      );
   }

   public CookingPotDisplay(
      List<EntryIngredient> inputs, List<EntryIngredient> outputs, Optional<class_2960> location, EntryIngredient container, int cookTime, float experience
   ) {
      super(inputs, outputs, location);
      this.container = container;
      this.cookTime = cookTime;
      this.experience = experience;
   }

   public EntryIngredient getOutputContainer() {
      return this.container;
   }

   public int getCookTime() {
      return this.cookTime;
   }

   public float getExperience() {
      return this.experience;
   }

   public CategoryIdentifier<?> getCategoryIdentifier() {
      return REICategoryIdentifiers.COOKING;
   }

   public static Serializer<CookingPotDisplay> serializer() {
      return Serializer.of(CookingPotDisplay::new, (display, tag) -> {
         display.container = EntryIngredient.of(EntryStack.read(tag.method_10562("container")));
         display.cookTime = tag.method_10550("cook_time");
         display.experience = tag.method_10583("experience");
      });
   }
}
