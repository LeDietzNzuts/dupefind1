package net.p3pp3rf1y.sophisticatedcore.crafting;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import net.minecraft.class_175;
import net.minecraft.class_1792;
import net.minecraft.class_1799;
import net.minecraft.class_1856;
import net.minecraft.class_1869;
import net.minecraft.class_1935;
import net.minecraft.class_2119;
import net.minecraft.class_2960;
import net.minecraft.class_5797;
import net.minecraft.class_6862;
import net.minecraft.class_7800;
import net.minecraft.class_8790;
import net.minecraft.class_8957;
import net.minecraft.class_161.class_162;
import net.minecraft.class_170.class_171;
import net.minecraft.class_8782.class_8797;
import org.jetbrains.annotations.Nullable;

public class SCShapedRecipeBuilder implements class_5797 {
   private final class_7800 category;
   private final class_1792 result;
   private final class_1799 resultStack;
   private final List<String> rows = Lists.newArrayList();
   private final Map<Character, class_1856> key = Maps.newLinkedHashMap();
   private final Map<String, class_175<?>> criteria = new LinkedHashMap<>();
   @Nullable
   private String group;
   private boolean showNotification = true;

   public SCShapedRecipeBuilder(class_7800 category, class_1935 result, int count) {
      this(category, new class_1799(result, count));
   }

   public SCShapedRecipeBuilder(class_7800 category, class_1799 result) {
      this.category = category;
      this.result = result.method_7909();
      this.resultStack = result;
   }

   public static SCShapedRecipeBuilder shaped(class_7800 category, class_1935 result) {
      return shaped(category, result, 1);
   }

   public static SCShapedRecipeBuilder shaped(class_7800 category, class_1935 result, int count) {
      return new SCShapedRecipeBuilder(category, result, count);
   }

   public SCShapedRecipeBuilder define(Character symbol, class_6862<class_1792> tag) {
      return this.define(symbol, class_1856.method_8106(tag));
   }

   public SCShapedRecipeBuilder define(Character symbol, class_1935 item) {
      return this.define(symbol, class_1856.method_8091(new class_1935[]{item}));
   }

   public SCShapedRecipeBuilder define(Character symbol, class_1856 ingredient) {
      if (this.key.containsKey(symbol)) {
         throw new IllegalArgumentException("Symbol '" + symbol + "' is already defined!");
      } else if (symbol == ' ') {
         throw new IllegalArgumentException("Symbol ' ' (whitespace) is reserved and cannot be defined");
      } else {
         this.key.put(symbol, ingredient);
         return this;
      }
   }

   public SCShapedRecipeBuilder pattern(String pattern) {
      if (!this.rows.isEmpty() && pattern.length() != this.rows.get(0).length()) {
         throw new IllegalArgumentException("Pattern must be the same width on every line!");
      } else {
         this.rows.add(pattern);
         return this;
      }
   }

   public SCShapedRecipeBuilder unlockedBy(String name, class_175<?> criterion) {
      this.criteria.put(name, criterion);
      return this;
   }

   public SCShapedRecipeBuilder group(@Nullable String groupName) {
      this.group = groupName;
      return this;
   }

   public SCShapedRecipeBuilder showNotification(boolean showNotification) {
      this.showNotification = showNotification;
      return this;
   }

   public class_1792 method_36441() {
      return this.result;
   }

   public void method_17972(class_8790 recipeOutput, class_2960 id) {
      class_8957 shapedRecipePattern = this.ensureValid(id);
      class_162 builder = recipeOutput.method_53818()
         .method_705("has_the_recipe", class_2119.method_27847(id))
         .method_703(class_171.method_753(id))
         .method_704(class_8797.field_1257);
      this.criteria.forEach(builder::method_705);
      class_1869 shapedRecipe = new class_1869(
         Objects.requireNonNullElse(this.group, ""), class_5797.method_55308(this.category), shapedRecipePattern, this.resultStack, this.showNotification
      );
      recipeOutput.method_53819(id, shapedRecipe, builder.method_695(id.method_45138("recipes/" + this.category.method_46203() + "/")));
   }

   private class_8957 ensureValid(class_2960 loaction) {
      if (this.criteria.isEmpty()) {
         throw new IllegalStateException("No way of obtaining recipe " + loaction);
      } else {
         return class_8957.method_55085(this.key, this.rows);
      }
   }
}
