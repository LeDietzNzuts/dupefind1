package vectorwing.farmersdelight.common.block;

import java.util.function.Supplier;
import net.minecraft.class_1792;
import net.minecraft.class_1922;
import net.minecraft.class_2248;
import net.minecraft.class_2338;
import net.minecraft.class_247;
import net.minecraft.class_259;
import net.minecraft.class_265;
import net.minecraft.class_2680;
import net.minecraft.class_3726;
import net.minecraft.class_4970.class_2251;

public class HoneyGlazedHamBlock extends FeastBlock {
   protected static final class_265 PLATE_SHAPE = class_2248.method_9541(1.0, 0.0, 1.0, 15.0, 2.0, 15.0);
   protected static final class_265 ROAST_SHAPE = class_259.method_1082(
      PLATE_SHAPE, class_2248.method_9541(4.0, 2.0, 4.0, 12.0, 10.0, 12.0), class_247.field_1366
   );

   public HoneyGlazedHamBlock(class_2251 properties, Supplier<class_1792> servingItem, boolean hasLeftovers) {
      super(properties, servingItem, hasLeftovers);
   }

   @Override
   public class_265 method_9530(class_2680 state, class_1922 level, class_2338 pos, class_3726 context) {
      return state.method_11654(SERVINGS) == 0 ? PLATE_SHAPE : ROAST_SHAPE;
   }
}
