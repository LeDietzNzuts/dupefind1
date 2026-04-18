package net.p3pp3rf1y.sophisticatedbackpacks.crafting;

import java.util.List;
import net.minecraft.class_1767;
import net.minecraft.class_1799;
import net.minecraft.class_1865;
import net.minecraft.class_7710;
import net.p3pp3rf1y.sophisticatedbackpacks.backpack.BackpackItem;
import net.p3pp3rf1y.sophisticatedbackpacks.backpack.wrapper.BackpackWrapper;
import net.p3pp3rf1y.sophisticatedbackpacks.backpack.wrapper.IBackpackWrapper;
import net.p3pp3rf1y.sophisticatedbackpacks.init.ModItems;
import net.p3pp3rf1y.sophisticatedcore.crafting.StorageDyeRecipeBase;
import net.p3pp3rf1y.sophisticatedcore.util.ColorHelper;

public class BackpackDyeRecipe extends StorageDyeRecipeBase {
   public BackpackDyeRecipe(class_7710 category) {
      super(category);
   }

   public class_1865<?> method_8119() {
      return (class_1865<?>)ModItems.BACKPACK_DYE_RECIPE_SERIALIZER.get();
   }

   protected boolean isDyeableStorageItem(class_1799 stack) {
      return stack.method_7909() instanceof BackpackItem;
   }

   protected void applyColors(class_1799 coloredStorage, List<class_1767> mainDyes, List<class_1767> trimDyes) {
      IBackpackWrapper coloredWrapper = BackpackWrapper.fromStack(coloredStorage);
      coloredWrapper.setColors(
         ColorHelper.calculateColor(coloredWrapper.getMainColor(), -3382982, mainDyes),
         ColorHelper.calculateColor(coloredWrapper.getAccentColor(), -10342886, trimDyes)
      );
   }
}
