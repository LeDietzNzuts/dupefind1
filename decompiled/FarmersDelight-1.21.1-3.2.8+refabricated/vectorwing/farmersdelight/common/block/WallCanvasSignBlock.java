package vectorwing.farmersdelight.common.block;

import net.minecraft.class_1309;
import net.minecraft.class_1767;
import net.minecraft.class_1799;
import net.minecraft.class_1937;
import net.minecraft.class_2248;
import net.minecraft.class_2338;
import net.minecraft.class_2551;
import net.minecraft.class_2586;
import net.minecraft.class_2625;
import net.minecraft.class_2680;
import net.minecraft.class_4719;
import net.minecraft.class_4970.class_2251;
import org.jetbrains.annotations.Nullable;
import vectorwing.farmersdelight.common.block.state.CanvasSign;
import vectorwing.farmersdelight.common.registry.ModBlockEntityTypes;

public class WallCanvasSignBlock extends class_2551 implements CanvasSign {
   private final class_1767 backgroundColor;

   public WallCanvasSignBlock(class_2251 properties, @Nullable class_1767 backgroundColor) {
      super(class_4719.field_21677, properties);
      this.backgroundColor = backgroundColor;
   }

   @Nullable
   @Override
   public class_1767 getBackgroundColor() {
      return this.backgroundColor;
   }

   public class_2586 method_10123(class_2338 pos, class_2680 state) {
      return ModBlockEntityTypes.CANVAS_SIGN.get().method_11032(pos, state);
   }

   public void method_9567(class_1937 level, class_2338 pos, class_2680 state, @Nullable class_1309 placer, class_1799 stack) {
      class_2586 tileEntity = level.method_8321(pos);
      class_2248 block = state.method_26204();
      if (tileEntity instanceof class_2625 signEntity && block instanceof CanvasSign canvasSignBlock && canvasSignBlock.isDarkBackground()) {
         signEntity.method_49841(signText -> signText.method_49862(class_1767.field_7952), true);
      }
   }
}
