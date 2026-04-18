package vectorwing.farmersdelight.data;

import java.util.concurrent.CompletableFuture;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.class_6328;
import net.minecraft.class_8790;
import net.minecraft.class_7225.class_7874;
import vectorwing.farmersdelight.data.recipe.CookingRecipes;
import vectorwing.farmersdelight.data.recipe.CraftingRecipes;
import vectorwing.farmersdelight.data.recipe.CuttingRecipes;
import vectorwing.farmersdelight.data.recipe.SmeltingRecipes;

@class_6328
public class Recipes extends FabricRecipeProvider {
   public Recipes(FabricDataOutput output, CompletableFuture<class_7874> registriesFuture) {
      super(output, registriesFuture);
   }

   public void method_10419(class_8790 exporter) {
      CraftingRecipes.register(exporter);
      SmeltingRecipes.register(exporter);
      CookingRecipes.register(exporter);
      CuttingRecipes.register(exporter);
   }

   public String method_10321() {
      return "Farmer's Delight Recipes";
   }
}
