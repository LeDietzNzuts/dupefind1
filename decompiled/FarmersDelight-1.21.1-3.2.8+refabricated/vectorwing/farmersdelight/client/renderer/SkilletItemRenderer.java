package vectorwing.farmersdelight.client.renderer;

import net.fabricmc.fabric.api.client.rendering.v1.BuiltinItemRendererRegistry.DynamicItemRenderer;
import net.minecraft.class_1747;
import net.minecraft.class_1799;
import net.minecraft.class_2680;
import net.minecraft.class_310;
import net.minecraft.class_3532;
import net.minecraft.class_4587;
import net.minecraft.class_4597;
import net.minecraft.class_7833;
import net.minecraft.class_811;
import net.minecraft.class_918;
import vectorwing.farmersdelight.common.item.component.ItemStackWrapper;
import vectorwing.farmersdelight.common.registry.ModDataComponents;

public class SkilletItemRenderer implements DynamicItemRenderer {
   public void render(class_1799 stack, class_811 mode, class_4587 poseStack, class_4597 buffer, int packedLight, int packedOverlay) {
      class_1747 item = (class_1747)stack.method_7909();
      class_2680 state = item.method_7711().method_9564();
      class_310 mc = class_310.method_1551();
      ItemStackWrapper stackWrapper = (ItemStackWrapper)stack.method_57825(ModDataComponents.SKILLET_INGREDIENT.get(), ItemStackWrapper.EMPTY);
      class_1799 ingredientStack = stackWrapper.getStack();
      float animation = 0.0F;
      if (!ingredientStack.method_7960()) {
         poseStack.method_22903();
         poseStack.method_22904(0.5, 0.0625, 0.5);
         long gameTime = mc.field_1687.method_8510();
         if (stack.method_57826(ModDataComponents.SKILLET_FLIP_TIMESTAMP.get()) && mode != class_811.field_4317) {
            long time = (Long)stack.method_57824(ModDataComponents.SKILLET_FLIP_TIMESTAMP.get());
            float partialTicks = mc.method_60646().method_60637(false);
            float var20 = ((float)(gameTime - time) + partialTicks) / 12.0F;
            animation = class_3532.method_15363(var20, 0.0F, 1.0F);
            float maxH = 0.4F;
            poseStack.method_46416(0.0F, maxH * class_3532.method_15374(animation * (float) Math.PI), 0.0F);
            float rotationAnimation = stack.method_57825(ModDataComponents.SKILLET_FLIPPED.get(), false) ? animation + 1.0F : animation;
            poseStack.method_22907(class_7833.field_40714.rotationDegrees(180.0F * rotationAnimation));
         } else {
            poseStack.method_22907(class_7833.field_40714.rotationDegrees(stack.method_57825(ModDataComponents.SKILLET_FLIPPED.get(), false) ? 180.0F : 0.0F));
         }

         poseStack.method_22907(class_7833.field_40714.rotationDegrees(90.0F));
         poseStack.method_22905(0.5F, 0.5F, 0.5F);
         if (mode != class_811.field_4317) {
            class_918 itemRenderer = mc.method_1480();
            itemRenderer.method_23178(ingredientStack, class_811.field_4319, packedLight, packedOverlay, poseStack, buffer, null, 0);
         }

         poseStack.method_22909();
      }

      poseStack.method_22903();
      if (animation != 0.0F && mode.method_29998()) {
         poseStack.method_46416(0.0F, 0.0F, 1.0F);
         poseStack.method_22907(class_7833.field_40713.rotationDegrees(class_3532.method_15374(animation * (float) (Math.PI * 2)) * 15.0F));
         poseStack.method_46416(0.0F, 0.0F, -1.0F);
         poseStack.method_22904(0.0, 0.0, -class_3532.method_15374(animation * (float) Math.PI) * 0.2);
      }

      mc.method_1541().method_3353(state, poseStack, buffer, packedLight, packedOverlay);
      poseStack.method_22909();
   }
}
