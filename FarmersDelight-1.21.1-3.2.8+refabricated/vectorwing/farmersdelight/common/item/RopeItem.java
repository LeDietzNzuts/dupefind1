package vectorwing.farmersdelight.common.item;

import net.minecraft.class_1750;
import net.minecraft.class_1937;
import net.minecraft.class_2248;
import net.minecraft.class_2338;
import net.minecraft.class_2350;
import net.minecraft.class_2680;
import net.minecraft.class_3486;
import net.minecraft.class_3610;
import net.minecraft.class_1792.class_1793;
import net.minecraft.class_2338.class_2339;
import org.jetbrains.annotations.Nullable;

public class RopeItem extends FuelBlockItem {
   public RopeItem(class_2248 block, class_1793 properties) {
      super(block, properties, 200);
   }

   @Nullable
   public class_1750 method_16356(class_1750 context) {
      class_2338 pos = context.method_8037();
      class_1937 level = context.method_8045();
      class_2680 state = level.method_8320(pos);
      class_2248 block = this.method_7711();
      if (state.method_26204() != block) {
         return context;
      } else {
         class_2350 direction;
         if (context.method_8046()) {
            direction = context.method_8038();
         } else {
            direction = class_2350.field_11033;
         }

         int i = 0;

         for (class_2339 blockpos$mutable = new class_2339(pos.method_10263(), pos.method_10264(), pos.method_10260()).method_10098(direction); i < 256; i++) {
            state = level.method_8320(blockpos$mutable);
            if (state.method_26204() != this.method_7711()) {
               class_3610 fluid = state.method_26227();
               if (!fluid.method_15767(class_3486.field_15517) && !fluid.method_15769()) {
                  return null;
               }

               if (state.method_26166(context)) {
                  return class_1750.method_16355(context, blockpos$mutable, direction);
               }
               break;
            }

            if (direction != class_2350.field_11033) {
               return context;
            }

            blockpos$mutable.method_10098(direction);
         }

         return null;
      }
   }

   protected boolean method_20360() {
      return false;
   }
}
