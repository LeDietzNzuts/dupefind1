package noobanidus.mods.lootr.common.client.entity;

import net.minecraft.class_1091;
import net.minecraft.class_1092;
import net.minecraft.class_1799;
import net.minecraft.class_2350;
import net.minecraft.class_243;
import net.minecraft.class_4587;
import net.minecraft.class_4597;
import net.minecraft.class_4608;
import net.minecraft.class_4722;
import net.minecraft.class_776;
import net.minecraft.class_7833;
import net.minecraft.class_811;
import net.minecraft.class_915;
import net.minecraft.class_918;
import net.minecraft.class_5617.class_5618;
import noobanidus.mods.lootr.common.api.LootrConstants;
import noobanidus.mods.lootr.common.entity.LootrItemFrame;

public class LootrItemFrameRenderer extends class_915<LootrItemFrame> {
   public static final class_1091 FRAME_LOCATION = new class_1091(LootrConstants.ITEM_FRAME.method_45138("block/"), "standalone");
   public static final class_1091 FRAME_OPEN_LOCATION = new class_1091(LootrConstants.ITEM_FRAME.method_45138("block/").method_48331("_open"), "standalone");
   private final class_918 itemRenderer;
   private final class_776 blockRenderer;

   public LootrItemFrameRenderer(class_5618 context) {
      super(context);
      this.itemRenderer = context.method_32168();
      this.blockRenderer = context.method_43337();
   }

   public void render(LootrItemFrame entity, float entityYaw, float partialTicks, class_4587 poseStack, class_4597 buffer, int packedLight) {
      if (this.method_23176(entity)) {
         this.method_23175(entity, entity.method_5476(), poseStack, buffer, packedLight, partialTicks);
      }

      poseStack.method_22903();
      class_2350 direction = entity.method_5735();
      class_243 vec3 = this.method_23174(entity, partialTicks);
      poseStack.method_22904(-vec3.method_10216(), -vec3.method_10214(), -vec3.method_10215());
      poseStack.method_22904(direction.method_10148() * 0.46875, direction.method_10164() * 0.46875, direction.method_10165() * 0.46875);
      poseStack.method_22907(class_7833.field_40714.rotationDegrees(entity.method_36455()));
      poseStack.method_22907(class_7833.field_40716.rotationDegrees(180.0F - entity.method_36454()));
      boolean flag = entity.method_5767();
      class_1799 itemstack = entity.method_6940();
      if (!flag) {
         class_1092 modelmanager = this.blockRenderer.method_3351().method_3333();
         class_1091 modelresourcelocation = entity.isClientOpened() ? FRAME_OPEN_LOCATION : FRAME_LOCATION;
         poseStack.method_22903();
         poseStack.method_46416(-0.5F, -0.5F, -0.5F);
         this.blockRenderer
            .method_3350()
            .method_3367(
               poseStack.method_23760(),
               buffer.getBuffer(class_4722.method_24073()),
               null,
               modelmanager.method_4742(modelresourcelocation),
               1.0F,
               1.0F,
               1.0F,
               packedLight,
               class_4608.field_21444
            );
         poseStack.method_22909();
      }

      if (!entity.isClientOpened()) {
         if (flag) {
            poseStack.method_46416(0.0F, 0.0F, 0.5F);
         } else {
            poseStack.method_46416(0.0F, 0.0F, 0.4375F);
         }

         int j = entity.method_6934();
         poseStack.method_22907(class_7833.field_40718.rotationDegrees(j * 360.0F / 8.0F));
         poseStack.method_22905(0.5F, 0.5F, 0.5F);
         this.itemRenderer
            .method_23178(itemstack, class_811.field_4319, packedLight, class_4608.field_21444, poseStack, buffer, entity.method_37908(), entity.method_5628());
      }

      poseStack.method_22909();
   }
}
