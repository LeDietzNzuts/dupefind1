package vectorwing.farmersdelight.common.block.entity;

import net.minecraft.class_2338;
import net.minecraft.class_2591;
import net.minecraft.class_2680;
import net.minecraft.class_7717;
import vectorwing.farmersdelight.common.registry.ModBlockEntityTypes;

public class HangingCanvasSignBlockEntity extends class_7717 {
   public HangingCanvasSignBlockEntity(class_2338 pos, class_2680 state) {
      super(pos, state);
   }

   public class_2591<?> method_11017() {
      return ModBlockEntityTypes.HANGING_CANVAS_SIGN.get();
   }

   public boolean method_61176(class_2680 state) {
      return this.method_11017().method_20526(state);
   }

   public int method_45469() {
      return 9;
   }

   public int method_45470() {
      return 60;
   }
}
