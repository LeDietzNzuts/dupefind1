package vectorwing.farmersdelight.common.block.entity;

import net.minecraft.class_2338;
import net.minecraft.class_2591;
import net.minecraft.class_2625;
import net.minecraft.class_2680;
import vectorwing.farmersdelight.common.registry.ModBlockEntityTypes;

public class CanvasSignBlockEntity extends class_2625 {
   public CanvasSignBlockEntity(class_2338 pos, class_2680 state) {
      super(pos, state);
   }

   public boolean method_61176(class_2680 blockState) {
      return this.method_11017().method_20526(blockState);
   }

   public class_2591<?> method_11017() {
      return ModBlockEntityTypes.CANVAS_SIGN.get();
   }
}
