package net.p3pp3rf1y.sophisticatedcore.crafting;

import com.google.common.base.Preconditions;
import java.util.function.Function;
import net.fabricmc.fabric.api.resource.conditions.v1.ResourceCondition;
import net.fabricmc.fabric.impl.datagen.FabricDataGenHelper;
import net.minecraft.class_1799;
import net.minecraft.class_1860;
import net.minecraft.class_1867;
import net.minecraft.class_1935;
import net.minecraft.class_2960;
import net.minecraft.class_7800;
import net.minecraft.class_8779;
import net.minecraft.class_8790;
import net.minecraft.class_161.class_162;
import org.jetbrains.annotations.Nullable;

public class ShapelessBasedRecipeBuilder extends SCShapelessRecipeBuilder {
   private final Function<class_1867, ? extends class_1867> factory;

   public ShapelessBasedRecipeBuilder(class_1799 result, Function<class_1867, ? extends class_1867> factory) {
      super(class_7800.field_40642, result);
      this.factory = factory;
   }

   public ShapelessBasedRecipeBuilder(class_1935 result, int count, Function<class_1867, ? extends class_1867> factory) {
      this(new class_1799(result, count), factory);
   }

   public static ShapelessBasedRecipeBuilder shapeless(class_1799 result, Function<class_1867, ? extends class_1867> factory) {
      return new ShapelessBasedRecipeBuilder(result, factory);
   }

   public static ShapelessBasedRecipeBuilder shapeless(class_1799 result) {
      return new ShapelessBasedRecipeBuilder(result, r -> r);
   }

   public static ShapelessBasedRecipeBuilder shapeless(class_1935 result) {
      return shapeless(result, 1);
   }

   public static ShapelessBasedRecipeBuilder shapeless(class_1935 result, int count) {
      return shapeless(new class_1799(result, count));
   }

   public static ShapelessBasedRecipeBuilder shapeless(class_1935 result, Function<class_1867, ? extends class_1867> factory) {
      return shapeless(result, 1, factory);
   }

   public static ShapelessBasedRecipeBuilder shapeless(class_1935 result, int count, Function<class_1867, ? extends class_1867> factory) {
      return new ShapelessBasedRecipeBuilder(result, count, factory);
   }

   @Override
   public void method_17972(class_8790 recipeOutput, class_2960 id) {
      HoldingRecipeOutput holdingRecipeOutput = new HoldingRecipeOutput(recipeOutput.method_53818());
      super.method_17972(holdingRecipeOutput, id);
      if (holdingRecipeOutput.getRecipe() instanceof class_1867 compose) {
         this.withConditions(recipeOutput, new ItemEnabledCondition(this.method_36441()))
            .method_53819(id, (class_1860)this.factory.apply(compose), holdingRecipeOutput.getAdvancementHolder());
      }
   }

   protected class_8790 withConditions(class_8790 exporter, ResourceCondition... conditions) {
      Preconditions.checkArgument(conditions.length > 0, "Must add at least one condition.");
      return new class_8790() {
         public void method_53819(class_2960 identifier, class_1860<?> recipe, @Nullable class_8779 advancementEntry) {
            FabricDataGenHelper.addConditions(recipe, conditions);
            exporter.method_53819(identifier, recipe, advancementEntry);
         }

         public class_162 method_53818() {
            return exporter.method_53818();
         }
      };
   }
}
