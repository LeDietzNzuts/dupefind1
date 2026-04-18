package vectorwing.farmersdelight.client.gui;

import java.util.List;
import net.minecraft.class_1735;
import net.minecraft.class_1799;
import net.minecraft.class_1856;
import net.minecraft.class_2561;
import net.minecraft.class_2960;
import net.minecraft.class_507;
import net.minecraft.class_8666;
import net.minecraft.class_8786;
import org.jetbrains.annotations.NotNull;
import vectorwing.farmersdelight.common.crafting.CookingPotRecipe;
import vectorwing.farmersdelight.common.utility.TextUtils;

public class CookingPotRecipeBookComponent extends class_507 {
   protected static final class_8666 RECIPE_BOOK_BUTTONS = new class_8666(
      class_2960.method_60655("farmersdelight", "recipe_book/cooking_pot_enabled"),
      class_2960.method_60655("farmersdelight", "recipe_book/cooking_pot_disabled"),
      class_2960.method_60655("farmersdelight", "recipe_book/cooking_pot_enabled_highlighted"),
      class_2960.method_60655("farmersdelight", "recipe_book/cooking_pot_disabled_highlighted")
   );

   protected void method_2585() {
      this.field_3088.method_1962(RECIPE_BOOK_BUTTONS);
   }

   public void hide() {
      this.method_2593(false);
   }

   @NotNull
   protected class_2561 method_17064() {
      return TextUtils.getTranslation("container.recipe_book.cookable");
   }

   public void method_2596(class_8786<?> recipe, List<class_1735> slots) {
      class_1799 resultStack = recipe.comp_1933().method_8110(this.field_3091.field_1687.method_30349());
      this.field_3092.method_2565(recipe);
      if (slots.get(6).method_7677().method_7960()) {
         this.field_3092.method_2569(class_1856.method_8101(new class_1799[]{resultStack}), slots.get(6).field_7873, slots.get(6).field_7872);
      }

      if (recipe.comp_1933() instanceof CookingPotRecipe cookingRecipe) {
         class_1799 containerStack = cookingRecipe.getOutputContainer();
         if (!containerStack.method_7960()) {
            this.field_3092.method_2569(class_1856.method_8101(new class_1799[]{containerStack}), slots.get(7).field_7873, slots.get(7).field_7872);
         }
      }

      this.method_12816(
         this.field_3095.method_7653(), this.field_3095.method_7656(), this.field_3095.method_7655(), recipe, recipe.comp_1933().method_8117().iterator(), 0
      );
   }
}
