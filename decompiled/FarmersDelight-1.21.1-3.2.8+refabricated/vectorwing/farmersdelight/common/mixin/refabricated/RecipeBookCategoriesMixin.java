package vectorwing.farmersdelight.common.mixin.refabricated;

import java.util.List;
import net.minecraft.class_314;
import net.minecraft.class_5421;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import vectorwing.farmersdelight.refabricated.FDRecipeBookTypes;
import vectorwing.farmersdelight.refabricated.client.FDRecipeCategories;

@Mixin(class_314.class)
public class RecipeBookCategoriesMixin {
   @Inject(method = "method_30285(Lnet/minecraft/class_5421;)Ljava/util/List;", at = @At("HEAD"), cancellable = true)
   private static void fdrf$getCustomCategories(class_5421 recipeBookType, CallbackInfoReturnable<List<class_314>> cir) {
      if (recipeBookType == FDRecipeBookTypes.COOKING) {
         cir.setReturnValue(
            List.of(FDRecipeCategories.COOKING_SEARCH, FDRecipeCategories.COOKING_MEALS, FDRecipeCategories.COOKING_DRINKS, FDRecipeCategories.COOKING_MISC)
         );
      }
   }
}
