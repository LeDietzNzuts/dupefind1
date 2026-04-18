package vectorwing.farmersdelight.client.renderer;

import net.minecraft.class_1087;
import net.minecraft.class_1792;
import net.minecraft.class_1794;
import net.minecraft.class_1799;
import net.minecraft.class_1810;
import net.minecraft.class_1835;
import net.minecraft.class_2350;
import net.minecraft.class_310;
import net.minecraft.class_4587;
import net.minecraft.class_4597;
import net.minecraft.class_7833;
import net.minecraft.class_811;
import net.minecraft.class_827;
import net.minecraft.class_918;
import net.minecraft.class_5614.class_5615;
import vectorwing.farmersdelight.common.block.CuttingBoardBlock;
import vectorwing.farmersdelight.common.block.entity.CuttingBoardBlockEntity;
import vectorwing.farmersdelight.common.tag.ModTags;

public class CuttingBoardRenderer implements class_827<CuttingBoardBlockEntity> {
   public CuttingBoardRenderer(class_5615 pContext) {
   }

   public void render(
      CuttingBoardBlockEntity cuttingBoardEntity, float partialTicks, class_4587 poseStack, class_4597 buffer, int combinedLight, int combinedOverlay
   ) {
      class_2350 direction = ((class_2350)cuttingBoardEntity.method_11010().method_11654(CuttingBoardBlock.FACING)).method_10153();
      class_1799 boardStack = cuttingBoardEntity.getStoredItem();
      int posLong = (int)cuttingBoardEntity.method_11016().method_10063();
      if (!boardStack.method_7960()) {
         poseStack.method_22903();
         class_918 itemRenderer = class_310.method_1551().method_1480();
         poseStack.method_22903();
         class_1087 model = itemRenderer.method_4019(boardStack, cuttingBoardEntity.method_10997(), null, 0);
         model.method_4709().method_3503(class_811.field_4319).method_23075(false, poseStack);
         boolean isBlockItem = model.method_4712();
         poseStack.method_22909();
         if (cuttingBoardEntity.isItemCarvingBoard()) {
            this.renderItemCarved(poseStack, direction, boardStack);
         } else if (isBlockItem && !boardStack.method_31573(ModTags.FLAT_ON_CUTTING_BOARD)) {
            this.renderBlock(poseStack, direction);
         } else {
            this.renderItemLayingDown(poseStack, direction);
         }

         class_310.method_1551()
            .method_1480()
            .method_23178(boardStack, class_811.field_4319, combinedLight, combinedOverlay, poseStack, buffer, cuttingBoardEntity.method_10997(), posLong);
         poseStack.method_22909();
      }
   }

   public void renderItemLayingDown(class_4587 matrixStackIn, class_2350 direction) {
      matrixStackIn.method_22904(0.5, 0.08, 0.5);
      float f = -direction.method_10144();
      matrixStackIn.method_22907(class_7833.field_40716.rotationDegrees(f));
      matrixStackIn.method_22907(class_7833.field_40714.rotationDegrees(90.0F));
      matrixStackIn.method_22905(0.6F, 0.6F, 0.6F);
   }

   public void renderBlock(class_4587 matrixStackIn, class_2350 direction) {
      matrixStackIn.method_22904(0.5, 0.27, 0.5);
      float f = -direction.method_10144();
      matrixStackIn.method_22907(class_7833.field_40716.rotationDegrees(f));
      matrixStackIn.method_22905(0.8F, 0.8F, 0.8F);
   }

   public void renderItemCarved(class_4587 matrixStackIn, class_2350 direction, class_1799 itemStack) {
      matrixStackIn.method_22904(0.5, 0.23, 0.5);
      float f = -direction.method_10144() + 180.0F;
      matrixStackIn.method_22907(class_7833.field_40716.rotationDegrees(f));
      class_1792 toolItem = itemStack.method_7909();
      float poseAngle;
      if (toolItem instanceof class_1810 || toolItem instanceof class_1794) {
         poseAngle = 225.0F;
      } else if (toolItem instanceof class_1835) {
         poseAngle = 135.0F;
      } else {
         poseAngle = 180.0F;
      }

      matrixStackIn.method_22907(class_7833.field_40718.rotationDegrees(poseAngle));
      matrixStackIn.method_22905(0.6F, 0.6F, 0.6F);
   }
}
