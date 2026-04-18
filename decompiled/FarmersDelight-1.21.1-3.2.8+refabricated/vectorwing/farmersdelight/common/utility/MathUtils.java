package vectorwing.farmersdelight.common.utility;

import java.util.Random;
import net.minecraft.class_1799;
import net.minecraft.class_3532;
import org.jetbrains.annotations.Nullable;
import vectorwing.farmersdelight.refabricated.inventory.ItemHandler;

public class MathUtils {
   public static final Random RAND = new Random();

   public static int calcRedstoneFromItemHandler(@Nullable ItemHandler handler) {
      if (handler == null) {
         return 0;
      } else {
         int i = 0;
         float f = 0.0F;

         for (int j = 0; j < handler.getSlotCount(); j++) {
            class_1799 itemstack = handler.getStackInSlot(j);
            if (!itemstack.method_7960()) {
               f += (float)itemstack.method_7947() / Math.min(handler.getSlotLimit(j), itemstack.method_7914());
               i++;
            }
         }

         f /= handler.getSlotCount();
         return class_3532.method_15375(f * 14.0F) + (i > 0 ? 1 : 0);
      }
   }
}
