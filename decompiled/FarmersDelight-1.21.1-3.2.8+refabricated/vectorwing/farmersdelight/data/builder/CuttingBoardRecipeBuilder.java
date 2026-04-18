package vectorwing.farmersdelight.data.builder;

import java.util.Optional;
import net.minecraft.class_175;
import net.minecraft.class_1792;
import net.minecraft.class_1799;
import net.minecraft.class_1856;
import net.minecraft.class_1935;
import net.minecraft.class_2371;
import net.minecraft.class_2960;
import net.minecraft.class_3414;
import net.minecraft.class_5797;
import net.minecraft.class_6328;
import net.minecraft.class_7923;
import net.minecraft.class_8790;
import org.jetbrains.annotations.Nullable;
import vectorwing.farmersdelight.common.crafting.CuttingBoardRecipe;
import vectorwing.farmersdelight.common.crafting.ingredient.ChanceResult;

@class_6328
public class CuttingBoardRecipeBuilder implements class_5797 {
   private final class_2371<ChanceResult> results = class_2371.method_37434(4);
   private final class_1856 ingredient;
   private final class_1856 tool;
   private class_3414 soundEvent;

   private CuttingBoardRecipeBuilder(class_1856 ingredient, class_1856 tool, class_1935 mainResult, int count, float chance) {
      this.results.add(new ChanceResult(new class_1799(mainResult.method_8389(), count), chance));
      this.ingredient = ingredient;
      this.tool = tool;
   }

   public static CuttingBoardRecipeBuilder cuttingRecipe(class_1856 ingredient, class_1856 tool, class_1935 mainResult, int count) {
      return new CuttingBoardRecipeBuilder(ingredient, tool, mainResult, count, 1.0F);
   }

   public static CuttingBoardRecipeBuilder cuttingRecipe(class_1856 ingredient, class_1856 tool, class_1935 mainResult, int count, float chance) {
      return new CuttingBoardRecipeBuilder(ingredient, tool, mainResult, count, chance);
   }

   public static CuttingBoardRecipeBuilder cuttingRecipe(class_1856 ingredient, class_1856 tool, class_1935 mainResult) {
      return new CuttingBoardRecipeBuilder(ingredient, tool, mainResult, 1, 1.0F);
   }

   public CuttingBoardRecipeBuilder addResult(class_1935 result) {
      return this.addResult(result, 1);
   }

   public CuttingBoardRecipeBuilder addResult(class_1935 result, int count) {
      this.results.add(new ChanceResult(new class_1799(result.method_8389(), count), 1.0F));
      return this;
   }

   public CuttingBoardRecipeBuilder addResultWithChance(class_1935 result, float chance) {
      return this.addResultWithChance(result, chance, 1);
   }

   public CuttingBoardRecipeBuilder addResultWithChance(class_1935 result, float chance, int count) {
      this.results.add(new ChanceResult(new class_1799(result.method_8389(), count), chance));
      return this;
   }

   public CuttingBoardRecipeBuilder addSound(class_3414 soundEvent) {
      this.soundEvent = soundEvent;
      return this;
   }

   public class_5797 method_33530(String p_176496_, class_175<?> p_301065_) {
      return this;
   }

   public class_5797 method_33529(@Nullable String p_176495_) {
      return this;
   }

   public class_1792 method_36441() {
      return this.ingredient.method_8105()[0].method_7909();
   }

   public void build(class_8790 output) {
      class_2960 location = class_7923.field_41178.method_10221(this.method_36441());
      this.method_17972(output, class_2960.method_60655("farmersdelight", location.method_12832()));
   }

   public void build(class_8790 outputIn, String save) {
      class_2960 resourcelocation = class_7923.field_41178.method_10221(this.method_36441());
      if (class_2960.method_60654(save).equals(resourcelocation)) {
         throw new IllegalStateException("Cutting Recipe " + save + " should remove its 'save' argument");
      } else {
         this.build(outputIn, class_2960.method_60654(save));
      }
   }

   public void build(class_8790 output, class_2960 id) {
      this.method_17972(output, id);
   }

   public void method_17972(class_8790 output, class_2960 id) {
      CuttingBoardRecipe recipe = new CuttingBoardRecipe(
         "", this.ingredient, this.tool, this.results, this.soundEvent == null ? Optional.empty() : Optional.of(this.soundEvent)
      );
      output.method_53819(id.method_45138("cutting/"), recipe, null);
   }
}
