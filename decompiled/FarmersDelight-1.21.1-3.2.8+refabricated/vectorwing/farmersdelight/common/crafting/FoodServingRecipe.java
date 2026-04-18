package vectorwing.farmersdelight.common.crafting;

import net.minecraft.class_1799;
import net.minecraft.class_1852;
import net.minecraft.class_1865;
import net.minecraft.class_1937;
import net.minecraft.class_2371;
import net.minecraft.class_7710;
import net.minecraft.class_9694;
import net.minecraft.class_7225.class_7874;
import vectorwing.farmersdelight.common.block.entity.CookingPotBlockEntity;
import vectorwing.farmersdelight.common.registry.ModItems;
import vectorwing.farmersdelight.common.registry.ModRecipeSerializers;

public class FoodServingRecipe extends class_1852 {
   public FoodServingRecipe(class_7710 category) {
      super(category);
   }

   public boolean matches(class_9694 input, class_1937 level) {
      class_1799 cookingPotStack = class_1799.field_8037;
      class_1799 containerStack = class_1799.field_8037;
      class_1799 secondStack = class_1799.field_8037;

      for (int index = 0; index < input.method_59983(); index++) {
         class_1799 selectedStack = input.method_59984(index);
         if (!selectedStack.method_7960()) {
            if (cookingPotStack.method_7960()) {
               class_1799 mealStack = CookingPotBlockEntity.getMealFromItem(selectedStack);
               if (!mealStack.method_7960()) {
                  cookingPotStack = selectedStack;
                  containerStack = CookingPotBlockEntity.getContainerFromItem(selectedStack);
                  continue;
               }
            }

            if (!secondStack.method_7960()) {
               return false;
            }

            secondStack = selectedStack;
         }
      }

      return !cookingPotStack.method_7960() && !secondStack.method_7960() && secondStack.method_31574(containerStack.method_7909());
   }

   public class_1799 assemble(class_9694 input, class_7874 access) {
      for (int i = 0; i < input.method_59983(); i++) {
         class_1799 selectedStack = input.method_59984(i);
         if (!selectedStack.method_7960() && selectedStack.method_31574(ModItems.COOKING_POT.get())) {
            class_1799 resultStack = CookingPotBlockEntity.getMealFromItem(selectedStack).method_7972();
            resultStack.method_7939(1);
            return resultStack;
         }
      }

      return class_1799.field_8037;
   }

   public class_2371<class_1799> getRemainingItems(class_9694 input) {
      class_2371<class_1799> remainders = class_2371.method_10213(input.method_59983(), class_1799.field_8037);

      for (int i = 0; i < remainders.size(); i++) {
         class_1799 selectedStack = input.method_59984(i);
         if (selectedStack.getRecipeRemainder() != null) {
            remainders.set(i, selectedStack.getRecipeRemainder());
         } else if (selectedStack.method_31574(ModItems.COOKING_POT.get())) {
            CookingPotBlockEntity.takeServingFromItem(selectedStack);
            class_1799 newCookingPotStack = selectedStack.method_7972();
            newCookingPotStack.method_7939(1);
            remainders.set(i, newCookingPotStack);
            break;
         }
      }

      return remainders;
   }

   public boolean method_8113(int width, int height) {
      return width >= 2 && height >= 2;
   }

   public class_1865<?> method_8119() {
      return ModRecipeSerializers.FOOD_SERVING.get();
   }
}
