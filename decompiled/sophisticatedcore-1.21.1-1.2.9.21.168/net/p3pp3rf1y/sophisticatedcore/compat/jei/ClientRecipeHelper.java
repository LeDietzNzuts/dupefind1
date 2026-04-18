package net.p3pp3rf1y.sophisticatedcore.compat.jei;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import net.minecraft.class_1799;
import net.minecraft.class_1860;
import net.minecraft.class_1867;
import net.minecraft.class_1869;
import net.minecraft.class_2960;
import net.minecraft.class_310;
import net.minecraft.class_3955;
import net.minecraft.class_3956;
import net.minecraft.class_5455;
import net.minecraft.class_638;
import net.minecraft.class_8786;
import net.minecraft.class_9695;

public class ClientRecipeHelper {
   private ClientRecipeHelper() {
   }

   public static <T extends class_1860<?>> Optional<class_8786<T>> getCraftingRecipeByKey(class_3956<T> type, class_2960 recipeKey) {
      class_310 minecraft = class_310.method_1551();
      class_638 world = minecraft.field_1687;
      if (world == null) {
         return Optional.empty();
      } else {
         class_8786<?> recipeHolder = (class_8786<?>)world.method_8433().method_8130(recipeKey).orElse(null);
         return recipeHolder != null && recipeHolder.comp_1933().method_17716().equals(type) ? Optional.of((class_8786<T>)recipeHolder) : Optional.empty();
      }
   }

   public static <I extends class_9695, T extends class_1860<I>, U extends class_1860<?>> List<class_8786<T>> transformAllRecipesOfType(
      class_3956<T> recipeType, Class<U> filterRecipeClass, Function<U, T> transformRecipe
   ) {
      class_310 minecraft = class_310.method_1551();
      class_638 level = minecraft.field_1687;
      return level == null
         ? Collections.emptyList()
         : level.method_8433()
            .method_30027(recipeType)
            .stream()
            .filter(r -> filterRecipeClass.isInstance(r.comp_1933()))
            .map(r -> new class_8786(r.comp_1932(), transformRecipe.apply(filterRecipeClass.cast(r.comp_1933()))))
            .toList();
   }

   public static <I extends class_9695, T extends class_1860<I>, U extends class_1860<?>> List<class_8786<T>> transformAllRecipesOfTypeIntoMultiple(
      class_3956<T> recipeType, Class<U> filterRecipeClass, Function<U, List<class_8786<T>>> transformRecipe
   ) {
      class_310 minecraft = class_310.method_1551();
      class_638 level = minecraft.field_1687;
      return level == null
         ? Collections.emptyList()
         : level.method_8433()
            .method_30027(recipeType)
            .stream()
            .filter(r -> filterRecipeClass.isInstance(r.comp_1933()))
            .map(r -> transformRecipe.apply(filterRecipeClass.cast(r.comp_1933())))
            .collect(ArrayList::new, List::addAll, List::addAll);
   }

   public static class_3955 copyShapedRecipe(class_1869 recipe) {
      return new class_1869("", recipe.method_45441(), recipe.field_47320, getResultItem(recipe));
   }

   public static class_3955 copyShapelessRecipe(class_1867 recipe) {
      return new class_1867("", recipe.method_45441(), getResultItem(recipe), recipe.method_8117());
   }

   private static class_1799 registryAccessAware(Function<class_5455, class_1799> func) {
      class_310 minecraft = class_310.method_1551();
      class_638 level = minecraft.field_1687;
      if (level == null) {
         throw new NullPointerException("level must not be null.");
      } else {
         class_5455 registryAccess = level.method_30349();
         return func.apply(registryAccess);
      }
   }

   public static class_1799 getResultItem(class_1860<?> recipe) {
      return registryAccessAware(recipe::method_8110);
   }

   public static <I extends class_9695> class_1799 assemble(class_1860<I> recipe, I container) {
      return registryAccessAware(registry -> recipe.method_8116(container, registry));
   }
}
