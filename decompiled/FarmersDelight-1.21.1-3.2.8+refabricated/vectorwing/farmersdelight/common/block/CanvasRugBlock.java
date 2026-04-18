package vectorwing.farmersdelight.common.block;

import net.minecraft.class_1922;
import net.minecraft.class_2248;
import net.minecraft.class_2338;
import net.minecraft.class_265;
import net.minecraft.class_2680;
import net.minecraft.class_3726;
import net.minecraft.class_4970.class_2251;

public class CanvasRugBlock extends class_2248 {
   protected static final class_265 SHAPE = class_2248.method_9541(0.0, 0.0, 0.0, 16.0, 1.0, 16.0);

   public CanvasRugBlock(class_2251 properties) {
      super(properties);
   }

   public class_265 method_9530(class_2680 state, class_1922 level, class_2338 pos, class_3726 context) {
      return SHAPE;
   }

   public boolean method_9579(class_2680 state, class_1922 reader, class_2338 pos) {
      return true;
   }
}
