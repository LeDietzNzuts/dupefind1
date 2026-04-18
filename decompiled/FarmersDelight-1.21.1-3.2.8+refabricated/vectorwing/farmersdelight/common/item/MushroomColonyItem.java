package vectorwing.farmersdelight.common.item;

import net.minecraft.class_1747;
import net.minecraft.class_1750;
import net.minecraft.class_2248;
import net.minecraft.class_2680;
import net.minecraft.class_1792.class_1793;
import org.jetbrains.annotations.Nullable;
import vectorwing.farmersdelight.common.block.MushroomColonyBlock;

public class MushroomColonyItem extends class_1747 {
   public MushroomColonyItem(class_2248 blockIn, class_1793 properties) {
      super(blockIn, properties);
   }

   @Nullable
   protected class_2680 method_7707(class_1750 context) {
      class_2680 originalState = this.method_7711().method_9605(context);
      if (originalState != null) {
         class_2680 matureState = (class_2680)originalState.method_11657(MushroomColonyBlock.COLONY_AGE, 3);
         return this.method_7709(context, matureState) ? matureState : null;
      } else {
         return null;
      }
   }
}
