package vectorwing.farmersdelight.refabricated;

import com.chocohead.mm.api.ClassTinkerers;
import java.util.function.Supplier;
import net.fabricmc.api.EnvType;
import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.MappingResolver;
import net.minecraft.class_1799;
import net.minecraft.class_1802;
import net.minecraft.class_1935;
import vectorwing.farmersdelight.common.registry.ModItems;

public class FarmersDelightASM implements Runnable {
   public static final String COOKING_RECIPE_BOOK_TYPE = "FARMERSDELIGHT_COOKING";
   public static final String COOKING_SEARCH_RECIPE_BOOK_CATEGORY = "FARMERSDELIGHT_COOKING_SEARCH";
   public static final String COOKING_MEALS_RECIPE_BOOK_CATEGORY = "FARMERSDELIGHT_COOKING_MEALS";
   public static final String COOKING_DRINKS_RECIPE_BOOK_CATEGORY = "FARMERSDELIGHT_COOKING_DRINKS";
   public static final String COOKING_MISC_RECIPE_BOOK_CATEGORY = "FARMERSDELIGHT_COOKING_MISC";

   @Override
   public void run() {
      MappingResolver remapper = FabricLoader.getInstance().getMappingResolver();
      String recipeBookTypeTarget = remapper.mapClassName("intermediary", "net.minecraft.class_5421");
      ClassTinkerers.enumBuilder(recipeBookTypeTarget).addEnum("FARMERSDELIGHT_COOKING", new Object[0]).build();
      if (FabricLoader.getInstance().getEnvironmentType() == EnvType.CLIENT) {
         String recipeBookCategoriesTarget = remapper.mapClassName("intermediary", "net.minecraft.class_314");
         String itemStackParamType = "[L" + remapper.mapClassName("intermediary", "net.minecraft.class_1799") + ";";
         ClassTinkerers.enumBuilder(recipeBookCategoriesTarget, new String[]{itemStackParamType})
            .addEnum("FARMERSDELIGHT_COOKING_SEARCH", getSearchCategoryStacks())
            .build();
         ClassTinkerers.enumBuilder(recipeBookCategoriesTarget, new String[]{itemStackParamType})
            .addEnum("FARMERSDELIGHT_COOKING_MEALS", getMealsCategoryStacks())
            .build();
         ClassTinkerers.enumBuilder(recipeBookCategoriesTarget, new String[]{itemStackParamType})
            .addEnum("FARMERSDELIGHT_COOKING_DRINKS", getDrinksCategoryStacks())
            .build();
         ClassTinkerers.enumBuilder(recipeBookCategoriesTarget, new String[]{itemStackParamType})
            .addEnum("FARMERSDELIGHT_COOKING_MISC", getMiscCategoryStacks())
            .build();
      }
   }

   public static Supplier<Object[]> getSearchCategoryStacks() {
      return () -> new Object[]{new class_1799[]{new class_1799((class_1935)(() -> class_1802.field_8251).get())}};
   }

   public static Supplier<Object[]> getMealsCategoryStacks() {
      return () -> new Object[]{new class_1799[]{new class_1799((class_1935)ModItems.VEGETABLE_NOODLES.get())}};
   }

   public static Supplier<Object[]> getDrinksCategoryStacks() {
      return () -> new Object[]{new class_1799[]{new class_1799((class_1935)ModItems.APPLE_CIDER.get())}};
   }

   public static Supplier<Object[]> getMiscCategoryStacks() {
      return () -> new Object[]{
         new class_1799[]{new class_1799((class_1935)ModItems.DUMPLINGS.get()), new class_1799((class_1935)ModItems.TOMATO_SAUCE.get())}
      };
   }
}
