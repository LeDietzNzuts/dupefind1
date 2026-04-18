package net.p3pp3rf1y.sophisticatedcore.crafting;

import com.google.common.base.Preconditions;
import java.util.function.Function;
import net.fabricmc.fabric.api.resource.conditions.v1.ResourceCondition;
import net.fabricmc.fabric.impl.datagen.FabricDataGenHelper;
import net.minecraft.class_1799;
import net.minecraft.class_1860;
import net.minecraft.class_1869;
import net.minecraft.class_1935;
import net.minecraft.class_2960;
import net.minecraft.class_7800;
import net.minecraft.class_7923;
import net.minecraft.class_8779;
import net.minecraft.class_8790;
import net.minecraft.class_161.class_162;
import org.jetbrains.annotations.Nullable;

public class ShapeBasedRecipeBuilder extends SCShapedRecipeBuilder {
   private final Function<class_1869, ? extends class_1869> factory;

   private ShapeBasedRecipeBuilder(class_1799 result, Function<class_1869, ? extends class_1869> factory) {
      super(class_7800.field_40642, result);
      this.factory = factory;
   }

   public static ShapeBasedRecipeBuilder shaped(class_1799 result) {
      return new ShapeBasedRecipeBuilder(result, r -> r);
   }

   public static ShapeBasedRecipeBuilder shaped(class_1935 result) {
      return shaped(new class_1799(result));
   }

   public static ShapeBasedRecipeBuilder shaped(class_1935 result, Function<class_1869, ? extends class_1869> factory) {
      return shaped(new class_1799(result, 1), factory);
   }

   public static ShapeBasedRecipeBuilder shaped(class_1799 result, Function<class_1869, ? extends class_1869> factory) {
      return new ShapeBasedRecipeBuilder(result, factory);
   }

   public void method_10431(class_8790 recipeOutput) {
      this.method_17972(recipeOutput, class_7923.field_41178.method_10221(this.method_36441().method_8389()));
   }

   @Override
   public void method_17972(class_8790 recipeOutput, class_2960 id) {
      HoldingRecipeOutput holdingRecipeOutput = new HoldingRecipeOutput(recipeOutput.method_53818());
      super.method_17972(holdingRecipeOutput, id);
      if (holdingRecipeOutput.getRecipe() instanceof class_1869 compose) {
         this.withConditions(recipeOutput, new ItemEnabledCondition(this.method_36441()))
            .method_53819(id, (class_1860)this.factory.apply(compose), holdingRecipeOutput.getAdvancementHolder());
      }
   }

   protected class_8790 withConditions(class_8790 exporter, ResourceCondition... conditions) {
      Preconditions.checkArgument(conditions.length > 0, "Must add at least one condition.");
      return new class_8790() {
         public void method_53819(class_2960 location, class_1860<?> recipe, @Nullable class_8779 advancement) {
            FabricDataGenHelper.addConditions(recipe, conditions);
            exporter.method_53819(location, recipe, advancement);
         }

         public class_162 method_53818() {
            return exporter.method_53818();
         }
      };
   }
}
