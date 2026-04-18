package vectorwing.farmersdelight.common.mixin.refabricated;

import com.llamalad7.mixinextras.sugar.Local;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;
import net.minecraft.class_299;
import net.minecraft.class_314;
import net.minecraft.class_516;
import net.minecraft.class_5455;
import net.minecraft.class_8786;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import vectorwing.farmersdelight.client.recipebook.CookingPotRecipeBookTab;
import vectorwing.farmersdelight.common.crafting.CookingPotRecipe;
import vectorwing.farmersdelight.refabricated.client.FDRecipeCategories;

@Mixin(class_299.class)
public class ClientRecipeBookMixin {
   @Inject(
      method = "method_1401(Ljava/lang/Iterable;Lnet/minecraft/class_5455;)V",
      at = @At(value = "INVOKE", target = "Lcom/google/common/collect/ImmutableMap;copyOf(Ljava/util/Map;)Lcom/google/common/collect/ImmutableMap;")
   )
   private void fdrf$setupAggregateCategories(
      Iterable<class_8786<?>> iterable, class_5455 registryAccess, CallbackInfo ci, @Local(ordinal = 1) Map<class_314, List<class_516>> aggregateCategories
   ) {
      aggregateCategories.put(
         FDRecipeCategories.COOKING_SEARCH,
         Stream.of(FDRecipeCategories.COOKING_MEALS, FDRecipeCategories.COOKING_DRINKS, FDRecipeCategories.COOKING_MISC)
            .flatMap(categories -> aggregateCategories.getOrDefault(categories, List.of()).stream())
            .toList()
      );
   }

   @Inject(
      method = "method_1400(Lnet/minecraft/class_8786;)Lnet/minecraft/class_314;",
      at = @At(value = "INVOKE", target = "Lcom/mojang/logging/LogUtils;defer(Ljava/util/function/Supplier;)Ljava/lang/Object;", ordinal = 0),
      cancellable = true
   )
   private static void fdrf$getCustomRecipeCategory(class_8786<?> recipe, CallbackInfoReturnable<class_314> cir) {
      if (recipe.comp_1933() instanceof CookingPotRecipe cookingRecipe) {
         CookingPotRecipeBookTab tab = cookingRecipe.getRecipeBookTab();
         if (tab != null) {
            cir.setReturnValue(switch (tab) {
               case MEALS -> FDRecipeCategories.COOKING_MEALS;
               case DRINKS -> FDRecipeCategories.COOKING_DRINKS;
               case MISC -> FDRecipeCategories.COOKING_MISC;
            });
         }
      }
   }
}
