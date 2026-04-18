package net.p3pp3rf1y.sophisticatedbackpacks.crafting;

import net.minecraft.class_1799;
import net.minecraft.class_1865;
import net.minecraft.class_1869;
import net.minecraft.class_9694;
import net.minecraft.class_7225.class_7874;
import net.p3pp3rf1y.sophisticatedbackpacks.backpack.wrapper.BackpackWrapper;
import net.p3pp3rf1y.sophisticatedbackpacks.init.ModItems;
import net.p3pp3rf1y.sophisticatedcore.crafting.IWrapperRecipe;
import net.p3pp3rf1y.sophisticatedcore.crafting.RecipeWrapperSerializer;

public class BasicBackpackRecipe extends class_1869 implements IWrapperRecipe<class_1869> {
   private final class_1869 compose;

   public BasicBackpackRecipe(class_1869 compose) {
      super(compose.method_8112(), compose.method_45441(), compose.field_47320, compose.field_9053);
      this.compose = compose;
   }

   public class_1869 getCompose() {
      return this.compose;
   }

   public class_1799 method_17727(class_9694 inv, class_7874 registries) {
      class_1799 result = super.method_17727(inv, registries);
      this.removeUuid(result);
      return result;
   }

   private void removeUuid(class_1799 backpack) {
      BackpackWrapper.fromStack(backpack).removeContentsUuid();
   }

   public class_1865<?> method_8119() {
      return ModItems.BASIC_BACKPACK_RECIPE_SERIALIZER.get();
   }

   public static class Serializer extends RecipeWrapperSerializer<class_1869, BasicBackpackRecipe> {
      public Serializer() {
         super(BasicBackpackRecipe::new, class_1865.field_9035);
      }
   }
}
