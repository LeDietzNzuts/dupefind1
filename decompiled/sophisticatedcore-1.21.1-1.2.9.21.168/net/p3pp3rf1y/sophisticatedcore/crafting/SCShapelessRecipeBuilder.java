package net.p3pp3rf1y.sophisticatedcore.crafting;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import net.minecraft.class_175;
import net.minecraft.class_1792;
import net.minecraft.class_1799;
import net.minecraft.class_1856;
import net.minecraft.class_1867;
import net.minecraft.class_1935;
import net.minecraft.class_2119;
import net.minecraft.class_2371;
import net.minecraft.class_2960;
import net.minecraft.class_5797;
import net.minecraft.class_6862;
import net.minecraft.class_7800;
import net.minecraft.class_8790;
import net.minecraft.class_161.class_162;
import net.minecraft.class_170.class_171;
import net.minecraft.class_8782.class_8797;
import org.jetbrains.annotations.Nullable;

public class SCShapelessRecipeBuilder implements class_5797 {
   private final class_7800 category;
   private final class_1792 result;
   private final class_1799 resultStack;
   private final class_2371<class_1856> ingredients = class_2371.method_10211();
   private final Map<String, class_175<?>> criteria = new LinkedHashMap<>();
   @Nullable
   private String group;

   public SCShapelessRecipeBuilder(class_7800 category, class_1935 result, int count) {
      this(category, new class_1799(result, count));
   }

   public SCShapelessRecipeBuilder(class_7800 category, class_1799 result) {
      this.category = category;
      this.result = result.method_7909();
      this.resultStack = result;
   }

   public static SCShapelessRecipeBuilder shapeless(class_7800 category, class_1935 result) {
      return new SCShapelessRecipeBuilder(category, result, 1);
   }

   public static SCShapelessRecipeBuilder shapeless(class_7800 category, class_1935 result, int count) {
      return new SCShapelessRecipeBuilder(category, result, count);
   }

   public static SCShapelessRecipeBuilder shapeless(class_7800 category, class_1799 result) {
      return new SCShapelessRecipeBuilder(category, result);
   }

   public SCShapelessRecipeBuilder requires(class_6862<class_1792> tag) {
      return this.requires(class_1856.method_8106(tag));
   }

   public SCShapelessRecipeBuilder requires(class_1935 item) {
      return this.requires(item, 1);
   }

   public SCShapelessRecipeBuilder requires(class_1935 item, int quantity) {
      for (int i = 0; i < quantity; i++) {
         this.requires(class_1856.method_8091(new class_1935[]{item}));
      }

      return this;
   }

   public SCShapelessRecipeBuilder requires(class_1856 ingredient) {
      return this.requires(ingredient, 1);
   }

   public SCShapelessRecipeBuilder requires(class_1856 ingredient, int quantity) {
      for (int i = 0; i < quantity; i++) {
         this.ingredients.add(ingredient);
      }

      return this;
   }

   public SCShapelessRecipeBuilder unlockedBy(String name, class_175<?> criterion) {
      this.criteria.put(name, criterion);
      return this;
   }

   public SCShapelessRecipeBuilder group(@Nullable String groupName) {
      this.group = groupName;
      return this;
   }

   public class_1792 method_36441() {
      return this.result;
   }

   public void method_17972(class_8790 recipeOutput, class_2960 id) {
      this.ensureValid(id);
      class_162 builder = recipeOutput.method_53818()
         .method_705("has_the_recipe", class_2119.method_27847(id))
         .method_703(class_171.method_753(id))
         .method_704(class_8797.field_1257);
      this.criteria.forEach(builder::method_705);
      class_1867 shapelessRecipe = new class_1867(
         Objects.requireNonNullElse(this.group, ""), class_5797.method_55308(this.category), this.resultStack, this.ingredients
      );
      recipeOutput.method_53819(id, shapelessRecipe, builder.method_695(id.method_45138("recipes/" + this.category.method_46203() + "/")));
   }

   private void ensureValid(class_2960 id) {
      if (this.criteria.isEmpty()) {
         throw new IllegalStateException("No way of obtaining recipe " + id);
      }
   }
}
