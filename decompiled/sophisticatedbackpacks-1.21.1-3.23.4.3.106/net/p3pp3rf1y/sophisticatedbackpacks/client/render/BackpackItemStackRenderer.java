package net.p3pp3rf1y.sophisticatedbackpacks.client.render;

import net.fabricmc.fabric.api.client.rendering.v1.BuiltinItemRendererRegistry.DynamicItemRenderer;
import net.minecraft.class_1087;
import net.minecraft.class_1799;
import net.minecraft.class_1921;
import net.minecraft.class_310;
import net.minecraft.class_4587;
import net.minecraft.class_4588;
import net.minecraft.class_4597;
import net.minecraft.class_4696;
import net.minecraft.class_7833;
import net.minecraft.class_811;
import net.minecraft.class_918;
import net.p3pp3rf1y.sophisticatedbackpacks.backpack.wrapper.BackpackWrapper;
import net.p3pp3rf1y.sophisticatedbackpacks.backpack.wrapper.IBackpackWrapper;

public class BackpackItemStackRenderer implements DynamicItemRenderer {
   private final class_310 minecraft = class_310.method_1551();

   public void render(class_1799 stack, class_811 mode, class_4587 matrixStack, class_4597 buffer, int combinedLight, int combinedOverlay) {
      class_918 itemRenderer = this.minecraft.method_1480();
      class_1087 model = itemRenderer.method_4019(stack, null, this.minecraft.field_1724, 0);
      class_1921 rendertype = class_4696.method_23678(stack, true);
      class_4588 ivertexbuilder = class_918.method_29711(buffer, rendertype, true, stack.method_7958());
      itemRenderer.method_23182(model, stack, combinedLight, combinedOverlay, matrixStack, ivertexbuilder);
      IBackpackWrapper backpackWrapper = BackpackWrapper.fromStack(stack);
      backpackWrapper.getRenderInfo()
         .getItemDisplayRenderInfo()
         .getDisplayItem()
         .ifPresent(
            displayItem -> {
               matrixStack.method_22904(0.5, 0.6, 0.25);
               matrixStack.method_22905(0.5F, 0.5F, 0.5F);
               matrixStack.method_22907(class_7833.field_40718.rotationDegrees(displayItem.getRotation()));
               itemRenderer.method_23178(
                  displayItem.getItem(), class_811.field_4319, combinedLight, combinedOverlay, matrixStack, buffer, this.minecraft.field_1687, 0
               );
            }
         );
   }
}
