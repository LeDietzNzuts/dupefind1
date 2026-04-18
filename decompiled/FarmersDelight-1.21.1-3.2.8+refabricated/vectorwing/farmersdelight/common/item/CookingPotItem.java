package vectorwing.farmersdelight.common.item;

import java.util.Optional;
import net.minecraft.class_1747;
import net.minecraft.class_1799;
import net.minecraft.class_2248;
import net.minecraft.class_3532;
import net.minecraft.class_5632;
import net.minecraft.class_1792.class_1793;
import vectorwing.farmersdelight.client.gui.CookingPotTooltip;
import vectorwing.farmersdelight.common.block.entity.CookingPotBlockEntity;

public class CookingPotItem extends class_1747 {
   private static final int BAR_COLOR = class_3532.method_15353(0.4F, 0.4F, 1.0F);

   public CookingPotItem(class_2248 block, class_1793 properties) {
      super(block, properties);
   }

   public boolean method_31567(class_1799 stack) {
      return getServingCount(stack) > 0;
   }

   public int method_31569(class_1799 stack) {
      return Math.min(1 + 12 * getServingCount(stack) / 64, 13);
   }

   public int method_31571(class_1799 stack) {
      return BAR_COLOR;
   }

   public Optional<class_5632> method_32346(class_1799 stack) {
      class_1799 mealStack = CookingPotBlockEntity.getMealFromItem(stack);
      return Optional.of(new CookingPotTooltip.CookingPotTooltipComponent(mealStack));
   }

   private static int getServingCount(class_1799 stack) {
      class_1799 mealStack = CookingPotBlockEntity.getMealFromItem(stack);
      return mealStack.method_7947();
   }
}
