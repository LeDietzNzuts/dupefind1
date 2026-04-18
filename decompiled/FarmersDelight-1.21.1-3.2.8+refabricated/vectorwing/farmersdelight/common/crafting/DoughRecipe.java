package vectorwing.farmersdelight.common.crafting;

import net.fabricmc.fabric.api.tag.convention.v2.ConventionalItemTags;
import net.minecraft.class_1799;
import net.minecraft.class_1802;
import net.minecraft.class_1852;
import net.minecraft.class_1865;
import net.minecraft.class_1935;
import net.minecraft.class_1937;
import net.minecraft.class_2371;
import net.minecraft.class_7710;
import net.minecraft.class_9694;
import net.minecraft.class_7225.class_7874;
import vectorwing.farmersdelight.common.registry.ModItems;
import vectorwing.farmersdelight.common.registry.ModRecipeSerializers;

public class DoughRecipe extends class_1852 {
   public DoughRecipe(class_7710 category) {
      super(category);
   }

   public boolean matches(class_9694 container, class_1937 level) {
      class_1799 wheatStack = class_1799.field_8037;
      class_1799 waterStack = class_1799.field_8037;

      for (int index = 0; index < container.method_59983(); index++) {
         class_1799 selectedStack = container.method_59984(index);
         if (!selectedStack.method_7960()) {
            if (selectedStack.method_31574(class_1802.field_8861)) {
               if (!wheatStack.method_7960()) {
                  return false;
               }

               wheatStack = selectedStack;
            } else {
               if (!selectedStack.method_31573(ConventionalItemTags.WATER_BUCKETS)) {
                  return false;
               }

               waterStack = selectedStack;
            }
         }
      }

      return !wheatStack.method_7960() && !waterStack.method_7960();
   }

   public class_1799 assemble(class_9694 container, class_7874 registryAccess) {
      return new class_1799((class_1935)ModItems.WHEAT_DOUGH.get());
   }

   public class_2371<class_1799> getRemainingItems(class_9694 container) {
      class_2371<class_1799> remainders = class_2371.method_10213(container.method_59983(), class_1799.field_8037);

      for (int index = 0; index < remainders.size(); index++) {
         class_1799 selectedStack = container.method_59984(index);
         if (selectedStack.method_31573(ConventionalItemTags.WATER_BUCKETS)) {
            remainders.set(index, selectedStack.method_7972());
         }
      }

      return remainders;
   }

   public boolean method_8113(int width, int height) {
      return width >= 2 && height >= 2;
   }

   public class_1865<?> method_8119() {
      return ModRecipeSerializers.DOUGH.get();
   }
}
