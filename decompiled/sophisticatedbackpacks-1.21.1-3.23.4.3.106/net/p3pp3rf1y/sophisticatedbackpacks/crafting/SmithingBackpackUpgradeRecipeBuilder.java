package net.p3pp3rf1y.sophisticatedbackpacks.crafting;

import java.util.function.Function;
import net.minecraft.class_1792;
import net.minecraft.class_1856;
import net.minecraft.class_1860;
import net.minecraft.class_2960;
import net.minecraft.class_7800;
import net.minecraft.class_8060;
import net.minecraft.class_8074;
import net.minecraft.class_8790;
import net.p3pp3rf1y.sophisticatedcore.crafting.HoldingRecipeOutput;

public class SmithingBackpackUpgradeRecipeBuilder extends class_8074 {
   private final Function<class_8060, ? extends class_8060> factory;

   public SmithingBackpackUpgradeRecipeBuilder(
      Function<class_8060, ? extends class_8060> factory, class_1856 template, class_1856 base, class_1856 addition, class_1792 result
   ) {
      super(template, base, addition, class_7800.field_40642, result);
      this.factory = factory;
   }

   public void method_48537(class_8790 recipeOutput, class_2960 id) {
      HoldingRecipeOutput holdingRecipeOutput = new HoldingRecipeOutput(recipeOutput.method_53818());
      super.method_48537(holdingRecipeOutput, id);
      if (holdingRecipeOutput.getRecipe() instanceof class_8060 compose) {
         recipeOutput.method_53819(id, (class_1860)this.factory.apply(compose), holdingRecipeOutput.getAdvancementHolder());
      }
   }
}
