package vectorwing.farmersdelight.client.renderer;

import java.util.Random;
import net.minecraft.class_1792;
import net.minecraft.class_1799;
import net.minecraft.class_2350;
import net.minecraft.class_310;
import net.minecraft.class_4587;
import net.minecraft.class_4597;
import net.minecraft.class_7833;
import net.minecraft.class_811;
import net.minecraft.class_827;
import net.minecraft.class_5614.class_5615;
import vectorwing.farmersdelight.common.block.StoveBlock;
import vectorwing.farmersdelight.common.block.entity.SkilletBlockEntity;
import vectorwing.farmersdelight.refabricated.inventory.ItemStackHandler;

public class SkilletRenderer implements class_827<SkilletBlockEntity> {
   private final Random random = new Random();

   public SkilletRenderer(class_5615 context) {
   }

   public void render(SkilletBlockEntity skilletEntity, float partialTicks, class_4587 poseStack, class_4597 buffer, int combinedLight, int combinedOverlay) {
      class_2350 direction = (class_2350)skilletEntity.method_11010().method_11654(StoveBlock.FACING);
      ItemStackHandler inventory = skilletEntity.getInventory();
      int posLong = (int)skilletEntity.method_11016().method_10063();
      class_1799 stack = inventory.getStackInSlot(0);
      int seed = stack.method_7960() ? 187 : class_1792.method_7880(stack.method_7909()) + stack.method_7919();
      this.random.setSeed(seed);
      if (!stack.method_7960()) {
         int itemRenderCount = this.getModelCount(stack);

         for (int i = 0; i < itemRenderCount; i++) {
            poseStack.method_22903();
            float xOffset = (this.random.nextFloat() * 2.0F - 1.0F) * 0.15F * 0.5F;
            float zOffset = (this.random.nextFloat() * 2.0F - 1.0F) * 0.15F * 0.5F;
            poseStack.method_22904(0.5 + xOffset, 0.1 + 0.03 * (i + 1), 0.5 + zOffset);
            float degrees = -direction.method_10144();
            poseStack.method_22907(class_7833.field_40716.rotationDegrees(degrees));
            poseStack.method_22907(class_7833.field_40714.rotationDegrees(90.0F));
            poseStack.method_22905(0.5F, 0.5F, 0.5F);
            if (skilletEntity.method_10997() != null) {
               class_310.method_1551()
                  .method_1480()
                  .method_23178(stack, class_811.field_4319, combinedLight, combinedOverlay, poseStack, buffer, skilletEntity.method_10997(), posLong);
            }

            poseStack.method_22909();
         }
      }
   }

   protected int getModelCount(class_1799 stack) {
      if (stack.method_7947() > 48) {
         return 5;
      } else if (stack.method_7947() > 32) {
         return 4;
      } else if (stack.method_7947() > 16) {
         return 3;
      } else {
         return stack.method_7947() > 1 ? 2 : 1;
      }
   }
}
