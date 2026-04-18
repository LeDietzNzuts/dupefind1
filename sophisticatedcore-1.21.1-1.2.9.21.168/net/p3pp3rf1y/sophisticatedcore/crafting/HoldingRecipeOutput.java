package net.p3pp3rf1y.sophisticatedcore.crafting;

import javax.annotation.Nullable;
import net.minecraft.class_1860;
import net.minecraft.class_2960;
import net.minecraft.class_8779;
import net.minecraft.class_8790;
import net.minecraft.class_161.class_162;

public class HoldingRecipeOutput implements class_8790 {
   private final class_162 advancement;
   private class_1860<?> recipe;
   @Nullable
   private class_8779 advancementHolder;

   public HoldingRecipeOutput(class_162 advancement) {
      this.advancement = advancement;
   }

   public class_162 method_53818() {
      return this.advancement;
   }

   public void method_53819(class_2960 id, class_1860<?> recipe, @org.jetbrains.annotations.Nullable class_8779 advancement) {
      this.recipe = recipe;
      this.advancementHolder = advancement;
   }

   public class_1860<?> getRecipe() {
      return this.recipe;
   }

   @Nullable
   public class_8779 getAdvancementHolder() {
      return this.advancementHolder;
   }
}
