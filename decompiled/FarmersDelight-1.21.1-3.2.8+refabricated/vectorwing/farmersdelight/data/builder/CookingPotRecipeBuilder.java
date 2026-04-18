package vectorwing.farmersdelight.data.builder;

import java.util.LinkedHashMap;
import java.util.Map;
import net.minecraft.class_175;
import net.minecraft.class_1792;
import net.minecraft.class_1799;
import net.minecraft.class_1856;
import net.minecraft.class_1935;
import net.minecraft.class_2073;
import net.minecraft.class_2119;
import net.minecraft.class_2371;
import net.minecraft.class_2960;
import net.minecraft.class_5797;
import net.minecraft.class_6328;
import net.minecraft.class_6862;
import net.minecraft.class_7923;
import net.minecraft.class_8790;
import net.minecraft.class_161.class_162;
import net.minecraft.class_170.class_171;
import net.minecraft.class_2066.class_2068;
import net.minecraft.class_2073.class_2074;
import net.minecraft.class_8782.class_8797;
import org.jetbrains.annotations.Nullable;
import vectorwing.farmersdelight.client.recipebook.CookingPotRecipeBookTab;
import vectorwing.farmersdelight.common.crafting.CookingPotRecipe;

@class_6328
public class CookingPotRecipeBuilder implements class_5797 {
   private CookingPotRecipeBookTab tab;
   private final class_2371<class_1856> ingredients = class_2371.method_10211();
   private final class_1792 result;
   private final class_1799 resultStack;
   private final int cookingTime;
   private final float experience;
   private final class_1799 container;
   private final Map<String, class_175<?>> criteria = new LinkedHashMap<>();

   public CookingPotRecipeBuilder(class_1935 result, int count, int cookingTime, float experience, @Nullable class_1935 container) {
      this(new class_1799(result, count), cookingTime, experience, container);
   }

   public CookingPotRecipeBuilder(class_1799 resultIn, int cookingTime, float experience, @Nullable class_1935 container) {
      this.result = resultIn.method_7909();
      this.resultStack = resultIn;
      this.cookingTime = cookingTime;
      this.experience = experience;
      this.container = container != null ? new class_1799(container) : class_1799.field_8037;
      this.tab = null;
   }

   public static CookingPotRecipeBuilder cookingPotRecipe(class_1935 mainResult, int count, int cookingTime, float experience) {
      return new CookingPotRecipeBuilder(mainResult, count, cookingTime, experience, null);
   }

   public static CookingPotRecipeBuilder cookingPotRecipe(class_1935 mainResult, int count, int cookingTime, float experience, class_1935 container) {
      return new CookingPotRecipeBuilder(mainResult, count, cookingTime, experience, container);
   }

   public CookingPotRecipeBuilder addIngredient(class_6862<class_1792> tagIn) {
      return this.addIngredient(class_1856.method_8106(tagIn));
   }

   public CookingPotRecipeBuilder addIngredient(class_1935 itemIn) {
      return this.addIngredient(itemIn, 1);
   }

   public CookingPotRecipeBuilder addIngredient(class_1935 itemIn, int quantity) {
      for (int i = 0; i < quantity; i++) {
         this.addIngredient(class_1856.method_8091(new class_1935[]{itemIn}));
      }

      return this;
   }

   public CookingPotRecipeBuilder addIngredient(class_1856 ingredientIn) {
      return this.addIngredient(ingredientIn, 1);
   }

   public CookingPotRecipeBuilder addIngredient(class_1856 ingredientIn, int quantity) {
      for (int i = 0; i < quantity; i++) {
         this.ingredients.add(ingredientIn);
      }

      return this;
   }

   public class_5797 method_33529(@Nullable String p_176495_) {
      return this;
   }

   public CookingPotRecipeBuilder setRecipeBookTab(CookingPotRecipeBookTab tab) {
      this.tab = tab;
      return this;
   }

   public class_1792 method_36441() {
      return this.result;
   }

   public CookingPotRecipeBuilder unlockedBy(String criterionName, class_175<?> criterionTrigger) {
      this.criteria.put(criterionName, criterionTrigger);
      return this;
   }

   public CookingPotRecipeBuilder unlockedByItems(String criterionName, class_1935... items) {
      return this.unlockedBy(criterionName, class_2068.method_8959(items));
   }

   public CookingPotRecipeBuilder unlockedByAnyIngredient(class_1935... items) {
      this.criteria.put("has_any_ingredient", class_2068.method_8957(new class_2073[]{class_2074.method_8973().method_8977(items).method_8976()}));
      return this;
   }

   public void build(class_8790 output) {
      class_2960 location = class_7923.field_41178.method_10221(this.result);
      this.method_17972(output, class_2960.method_60655("farmersdelight", location.method_12832()));
   }

   public void build(class_8790 outputIn, String save) {
      class_2960 resourcelocation = class_7923.field_41178.method_10221(this.result);
      if (class_2960.method_60654(save).equals(resourcelocation)) {
         throw new IllegalStateException("Cooking Recipe " + save + " should remove its 'save' argument");
      } else {
         this.method_17972(outputIn, class_2960.method_60654(save));
      }
   }

   public void method_17972(class_8790 output, class_2960 id) {
      class_2960 recipeId = id.method_45138("cooking/");
      class_162 advancementBuilder = output.method_53818()
         .method_705("has_the_recipe", class_2119.method_27847(recipeId))
         .method_703(class_171.method_753(recipeId))
         .method_704(class_8797.field_1257);
      this.criteria.forEach(advancementBuilder::method_705);
      CookingPotRecipe recipe = new CookingPotRecipe("", this.tab, this.ingredients, this.resultStack, this.container, this.experience, this.cookingTime);
      output.method_53819(recipeId, recipe, advancementBuilder.method_695(id.method_45138("recipes/cooking/")));
   }
}
