package vectorwing.farmersdelight.client.renderer;

import net.minecraft.class_1799;
import net.minecraft.class_2350;
import net.minecraft.class_241;
import net.minecraft.class_310;
import net.minecraft.class_4587;
import net.minecraft.class_4597;
import net.minecraft.class_761;
import net.minecraft.class_7833;
import net.minecraft.class_811;
import net.minecraft.class_827;
import net.minecraft.class_5614.class_5615;
import vectorwing.farmersdelight.common.block.StoveBlock;
import vectorwing.farmersdelight.common.block.entity.StoveBlockEntity;
import vectorwing.farmersdelight.refabricated.inventory.ItemStackHandler;

public class StoveRenderer implements class_827<StoveBlockEntity> {
   public StoveRenderer(class_5615 context) {
   }

   public void render(StoveBlockEntity stoveEntity, float partialTicks, class_4587 poseStack, class_4597 buffer, int combinedLightIn, int combinedOverlayIn) {
      class_2350 direction = ((class_2350)stoveEntity.method_11010().method_11654(StoveBlock.FACING)).method_10153();
      ItemStackHandler inventory = stoveEntity.getInventory();
      int posLong = (int)stoveEntity.method_11016().method_10063();

      for (int i = 0; i < inventory.getSlotCount(); i++) {
         class_1799 stoveStack = inventory.getStackInSlot(i);
         if (!stoveStack.method_7960()) {
            poseStack.method_22903();
            poseStack.method_22904(0.5, 1.02, 0.5);
            float f = -direction.method_10144();
            poseStack.method_22907(class_7833.field_40716.rotationDegrees(f));
            poseStack.method_22907(class_7833.field_40714.rotationDegrees(90.0F));
            class_241 itemOffset = stoveEntity.getStoveItemOffset(i);
            poseStack.method_22904(itemOffset.field_1343, itemOffset.field_1342, 0.0);
            poseStack.method_22905(0.375F, 0.375F, 0.375F);
            if (stoveEntity.method_10997() != null) {
               class_310.method_1551()
                  .method_1480()
                  .method_23178(
                     stoveStack,
                     class_811.field_4319,
                     class_761.method_23794(stoveEntity.method_10997(), stoveEntity.method_11016().method_10084()),
                     combinedOverlayIn,
                     poseStack,
                     buffer,
                     stoveEntity.method_10997(),
                     posLong + i
                  );
            }

            poseStack.method_22909();
         }
      }
   }
}
