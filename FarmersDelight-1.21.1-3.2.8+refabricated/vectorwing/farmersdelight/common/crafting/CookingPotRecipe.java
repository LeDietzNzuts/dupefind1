package vectorwing.farmersdelight.common.crafting;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.class_1662;
import net.minecraft.class_1799;
import net.minecraft.class_1856;
import net.minecraft.class_1860;
import net.minecraft.class_1865;
import net.minecraft.class_1935;
import net.minecraft.class_1937;
import net.minecraft.class_2371;
import net.minecraft.class_3956;
import net.minecraft.class_9129;
import net.minecraft.class_9139;
import net.minecraft.class_7225.class_7874;
import org.jetbrains.annotations.Nullable;
import vectorwing.farmersdelight.client.recipebook.CookingPotRecipeBookTab;
import vectorwing.farmersdelight.common.registry.ModItems;
import vectorwing.farmersdelight.common.registry.ModRecipeSerializers;
import vectorwing.farmersdelight.common.registry.ModRecipeTypes;
import vectorwing.farmersdelight.refabricated.inventory.RecipeWrapper;

public class CookingPotRecipe implements class_1860<RecipeWrapper> {
   public static final int INPUT_SLOTS = 6;
   private final String group;
   private final CookingPotRecipeBookTab tab;
   private final class_2371<class_1856> inputItems;
   private final class_1799 output;
   private final class_1799 container;
   private final class_1799 containerOverride;
   private final float experience;
   private final int cookTime;

   public CookingPotRecipe(
      String group,
      @Nullable CookingPotRecipeBookTab tab,
      class_2371<class_1856> inputItems,
      class_1799 output,
      class_1799 container,
      float experience,
      int cookTime
   ) {
      this.group = group;
      this.tab = tab;
      this.inputItems = inputItems;
      this.output = output;
      if (!container.method_7960()) {
         this.container = container;
      } else if (output.getRecipeRemainder() != null) {
         this.container = output.getRecipeRemainder();
      } else {
         this.container = class_1799.field_8037;
      }

      this.containerOverride = container;
      this.experience = experience;
      this.cookTime = cookTime;
   }

   public String method_8112() {
      return this.group;
   }

   @Nullable
   public CookingPotRecipeBookTab getRecipeBookTab() {
      return this.tab;
   }

   public class_2371<class_1856> method_8117() {
      return this.inputItems;
   }

   public class_1799 method_8110(class_7874 provider) {
      return this.output;
   }

   public class_1799 getOutputContainer() {
      return this.container;
   }

   public class_1799 getContainerOverride() {
      return this.containerOverride;
   }

   public class_1799 assemble(RecipeWrapper inv, class_7874 provider) {
      return this.output.method_7972();
   }

   public float getExperience() {
      return this.experience;
   }

   public int getCookTime() {
      return this.cookTime;
   }

   public boolean matches(RecipeWrapper inv, class_1937 level) {
      class_1662 stackedContents = new class_1662();
      int i = 0;

      for (int j = 0; j < 6; j++) {
         class_1799 itemstack = inv.method_59984(j);
         if (!itemstack.method_7960()) {
            i++;
            stackedContents.method_7400(itemstack);
         }
      }

      return i == this.inputItems.size() && stackedContents.method_7402(this, null);
   }

   public boolean method_8113(int width, int height) {
      return width * height >= this.inputItems.size();
   }

   public class_1865<?> method_8119() {
      return ModRecipeSerializers.COOKING.get();
   }

   public class_3956<?> method_17716() {
      return ModRecipeTypes.COOKING.get();
   }

   public class_1799 method_17447() {
      return new class_1799((class_1935)ModItems.COOKING_POT.get());
   }

   @Override
   public boolean equals(Object o) {
      if (this == o) {
         return true;
      } else if (o != null && this.getClass() == o.getClass()) {
         CookingPotRecipe that = (CookingPotRecipe)o;
         if (Float.compare(that.getExperience(), this.getExperience()) != 0) {
            return false;
         } else if (this.getCookTime() != that.getCookTime()) {
            return false;
         } else if (!this.method_8112().equals(that.method_8112())) {
            return false;
         } else if (this.tab != that.tab) {
            return false;
         } else if (!this.inputItems.equals(that.inputItems)) {
            return false;
         } else {
            return !this.output.equals(that.output) ? false : this.container.equals(that.container);
         }
      } else {
         return false;
      }
   }

   @Override
   public int hashCode() {
      int result = this.method_8112().hashCode();
      result = 31 * result + (this.getRecipeBookTab() != null ? this.getRecipeBookTab().hashCode() : 0);
      result = 31 * result + this.inputItems.hashCode();
      result = 31 * result + this.output.hashCode();
      result = 31 * result + this.container.hashCode();
      result = 31 * result + (this.getExperience() != 0.0F ? Float.floatToIntBits(this.getExperience()) : 0);
      return 31 * result + this.getCookTime();
   }

   public static class Serializer implements class_1865<CookingPotRecipe> {
      private static final MapCodec<CookingPotRecipe> CODEC = RecordCodecBuilder.mapCodec(
         inst -> inst.group(
               Codec.STRING.optionalFieldOf("group", "").forGetter(CookingPotRecipe::method_8112),
               CookingPotRecipeBookTab.CODEC.optionalFieldOf("recipe_book_tab", CookingPotRecipeBookTab.MISC).forGetter(CookingPotRecipe::getRecipeBookTab),
               class_1856.field_46096.listOf().fieldOf("ingredients").xmap(ingredients -> {
                  class_2371<class_1856> nonNullList = class_2371.method_10211();
                  nonNullList.addAll(ingredients);
                  return nonNullList;
               }, ingredients -> ingredients).forGetter(CookingPotRecipe::method_8117),
               class_1799.field_51397.fieldOf("result").forGetter(r -> r.output),
               class_1799.field_51397.optionalFieldOf("container", class_1799.field_8037).forGetter(CookingPotRecipe::getContainerOverride),
               Codec.FLOAT.optionalFieldOf("experience", 0.0F).forGetter(CookingPotRecipe::getExperience),
               Codec.INT.optionalFieldOf("cookingtime", 200).forGetter(CookingPotRecipe::getCookTime)
            )
            .apply(inst, CookingPotRecipe::new)
      );
      public static final class_9139<class_9129, CookingPotRecipe> STREAM_CODEC = class_9139.method_56437(
         CookingPotRecipe.Serializer::toNetwork, CookingPotRecipe.Serializer::fromNetwork
      );

      public MapCodec<CookingPotRecipe> method_53736() {
         return CODEC;
      }

      public class_9139<class_9129, CookingPotRecipe> method_56104() {
         return STREAM_CODEC;
      }

      private static CookingPotRecipe fromNetwork(class_9129 buffer) {
         String groupIn = buffer.method_19772();
         CookingPotRecipeBookTab tabIn = CookingPotRecipeBookTab.findByName(buffer.method_19772());
         int i = buffer.method_10816();
         class_2371<class_1856> inputItemsIn = class_2371.method_10213(i, class_1856.field_9017);
         inputItemsIn.replaceAll(ignored -> (class_1856)class_1856.field_48355.decode(buffer));
         class_1799 outputIn = (class_1799)class_1799.field_48349.decode(buffer);
         class_1799 container = (class_1799)class_1799.field_49268.decode(buffer);
         float experienceIn = buffer.readFloat();
         int cookTimeIn = buffer.method_10816();
         return new CookingPotRecipe(groupIn, tabIn, inputItemsIn, outputIn, container, experienceIn, cookTimeIn);
      }

      private static void toNetwork(class_9129 buffer, CookingPotRecipe recipe) {
         buffer.method_10814(recipe.group);
         buffer.method_10814(recipe.tab != null ? recipe.tab.toString() : "");
         buffer.method_10804(recipe.inputItems.size());

         for (class_1856 ingredient : recipe.inputItems) {
            class_1856.field_48355.encode(buffer, ingredient);
         }

         class_1799.field_48349.encode(buffer, recipe.output);
         class_1799.field_49268.encode(buffer, recipe.container);
         buffer.method_52941(recipe.experience);
         buffer.method_10804(recipe.cookTime);
      }
   }
}
