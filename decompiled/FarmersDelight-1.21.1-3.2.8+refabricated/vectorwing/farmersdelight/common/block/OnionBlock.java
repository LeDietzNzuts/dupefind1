package vectorwing.farmersdelight.common.block;

import net.minecraft.class_1922;
import net.minecraft.class_1935;
import net.minecraft.class_2248;
import net.minecraft.class_2302;
import net.minecraft.class_2338;
import net.minecraft.class_265;
import net.minecraft.class_2680;
import net.minecraft.class_3726;
import net.minecraft.class_4970.class_2251;
import vectorwing.farmersdelight.common.registry.ModItems;

public class OnionBlock extends class_2302 {
   private static final class_265[] SHAPE_BY_AGE = new class_265[]{
      class_2248.method_9541(0.0, 0.0, 0.0, 16.0, 2.0, 16.0),
      class_2248.method_9541(0.0, 0.0, 0.0, 16.0, 3.0, 16.0),
      class_2248.method_9541(0.0, 0.0, 0.0, 16.0, 4.0, 16.0),
      class_2248.method_9541(0.0, 0.0, 0.0, 16.0, 5.0, 16.0),
      class_2248.method_9541(0.0, 0.0, 0.0, 16.0, 6.0, 16.0),
      class_2248.method_9541(0.0, 0.0, 0.0, 16.0, 7.0, 16.0),
      class_2248.method_9541(0.0, 0.0, 0.0, 16.0, 8.0, 16.0),
      class_2248.method_9541(0.0, 0.0, 0.0, 16.0, 9.0, 16.0)
   };

   public OnionBlock(class_2251 properties) {
      super(properties);
   }

   protected class_1935 method_9832() {
      return (class_1935)ModItems.ONION.get();
   }

   public class_265 method_9530(class_2680 state, class_1922 level, class_2338 pos, class_3726 context) {
      return SHAPE_BY_AGE[state.method_11654(this.method_9824())];
   }
}
