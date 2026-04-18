package vectorwing.farmersdelight.client.renderer;

import net.minecraft.class_1767;
import net.minecraft.class_243;
import net.minecraft.class_2478;
import net.minecraft.class_2625;
import net.minecraft.class_2680;
import net.minecraft.class_3879;
import net.minecraft.class_4587;
import net.minecraft.class_4588;
import net.minecraft.class_4597;
import net.minecraft.class_4719;
import net.minecraft.class_4730;
import net.minecraft.class_5602;
import net.minecraft.class_7833;
import net.minecraft.class_5614.class_5615;
import net.minecraft.class_7761.class_7762;
import org.jetbrains.annotations.Nullable;
import vectorwing.farmersdelight.common.block.state.CanvasSign;
import vectorwing.farmersdelight.common.registry.ModAtlases;

public class HangingCanvasSignRenderer extends CanvasSignRenderer {
   private static final class_243 TEXT_OFFSET = new class_243(0.0, -0.32F, 0.073F);
   private final class_7762 signModel;

   public HangingCanvasSignRenderer(class_5615 context) {
      super(context);
      this.signModel = new class_7762(context.method_32140(class_5602.method_45719(class_4719.field_21677)));
   }

   public float method_51272() {
      return 1.0F;
   }

   public float method_51273() {
      return 0.8F;
   }

   @Override
   public void method_23083(class_2625 blockEntity, float partialTick, class_4587 poseStack, class_4597 bufferSource, int packedLight, int packedOverlay) {
      class_2680 state = blockEntity.method_11010();
      class_2478 block = (class_2478)state.method_26204();
      class_7762 model = this.signModel;
      model.method_45797(state);
      class_1767 dye = null;
      if (block instanceof CanvasSign canvasSign) {
         dye = canvasSign.getBackgroundColor();
      }

      this.renderSignWithText(blockEntity, poseStack, bufferSource, packedLight, packedOverlay, state, block, dye, model);
   }

   @Override
   protected void method_49918(class_4587 poseStack, float angle, class_2680 state) {
      poseStack.method_22904(0.5, 0.9375, 0.5);
      poseStack.method_22907(class_7833.field_40716.rotationDegrees(angle));
      poseStack.method_46416(0.0F, -0.3125F, 0.0F);
   }

   @Override
   protected void method_45793(class_4587 poseStack, int packedLight, int packedOverlay, class_3879 model, class_4588 vertexConsumer) {
      class_7762 hangingSignModel = (class_7762)model;
      hangingSignModel.field_40525.method_22698(poseStack, vertexConsumer, packedLight, packedOverlay);
   }

   @Override
   public class_4730 getCanvasSignMaterial(@Nullable class_1767 dyeColor) {
      return ModAtlases.getHangingCanvasSignMaterial(dyeColor);
   }

   @Override
   public int getCustomVerticalOffset() {
      return 0;
   }

   @Override
   class_243 method_45790() {
      return TEXT_OFFSET;
   }
}
