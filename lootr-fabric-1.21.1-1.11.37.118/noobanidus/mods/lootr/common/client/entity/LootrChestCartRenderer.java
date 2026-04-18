package noobanidus.mods.lootr.common.client.entity;

import net.minecraft.class_243;
import net.minecraft.class_3532;
import net.minecraft.class_4587;
import net.minecraft.class_4588;
import net.minecraft.class_4597;
import net.minecraft.class_4608;
import net.minecraft.class_5601;
import net.minecraft.class_7833;
import net.minecraft.class_925;
import net.minecraft.class_5617.class_5618;
import noobanidus.mods.lootr.common.client.item.LootrChestItemRenderer;
import noobanidus.mods.lootr.common.entity.LootrChestMinecartEntity;

public class LootrChestCartRenderer<T extends LootrChestMinecartEntity> extends class_925<T> {
   public LootrChestCartRenderer(class_5618 p_174300_, class_5601 p_174301_) {
      super(p_174300_, p_174301_);
   }

   public void render(T pEntity, float pEntityYaw, float pPartialTicks, class_4587 pMatrixStack, class_4597 pBuffer, int pPackedLight) {
      super.method_4063(pEntity, pEntityYaw, pPartialTicks, pMatrixStack, pBuffer, pPackedLight);
      pMatrixStack.method_22903();
      long i = pEntity.method_5628() * 493286711L;
      i = i * i * 4392167121L + i * 98761L;
      float f = (((float)(i >> 16 & 7L) + 0.5F) / 8.0F - 0.5F) * 0.004F;
      float f1 = (((float)(i >> 20 & 7L) + 0.5F) / 8.0F - 0.5F) * 0.004F;
      float f2 = (((float)(i >> 24 & 7L) + 0.5F) / 8.0F - 0.5F) * 0.004F;
      pMatrixStack.method_46416(f, f1, f2);
      double d0 = class_3532.method_16436(pPartialTicks, pEntity.field_6038, pEntity.method_23317());
      double d1 = class_3532.method_16436(pPartialTicks, pEntity.field_5971, pEntity.method_23318());
      double d2 = class_3532.method_16436(pPartialTicks, pEntity.field_5989, pEntity.method_23321());
      class_243 vec3 = pEntity.method_7508(d0, d1, d2);
      float f3 = class_3532.method_16439(pPartialTicks, pEntity.field_6004, pEntity.method_36455());
      if (vec3 != null) {
         class_243 vec31 = pEntity.method_7505(d0, d1, d2, 0.3F);
         class_243 vec32 = pEntity.method_7505(d0, d1, d2, -0.3F);
         if (vec31 == null) {
            vec31 = vec3;
         }

         if (vec32 == null) {
            vec32 = vec3;
         }

         pMatrixStack.method_22904(vec3.field_1352 - d0, (vec31.field_1351 + vec32.field_1351) / 2.0 - d1, vec3.field_1350 - d2);
         class_243 vec33 = vec32.method_1031(-vec31.field_1352, -vec31.field_1351, -vec31.field_1350);
         if (vec33.method_1033() != 0.0) {
            vec33 = vec33.method_1029();
            pEntityYaw = (float)(Math.atan2(vec33.field_1350, vec33.field_1352) * 180.0 / Math.PI);
            f3 = (float)(Math.atan(vec33.field_1351) * 73.0);
         }
      }

      pMatrixStack.method_22904(0.0, 0.375, 0.0);
      pMatrixStack.method_22907(class_7833.field_40716.rotationDegrees(180.0F - pEntityYaw));
      pMatrixStack.method_22907(class_7833.field_40718.rotationDegrees(-f3));
      float f5 = pEntity.method_54295() - pPartialTicks;
      float f6 = pEntity.method_54294() - pPartialTicks;
      if (f6 < 0.0F) {
         f6 = 0.0F;
      }

      if (f5 > 0.0F) {
         pMatrixStack.method_22907(class_7833.field_40714.rotationDegrees(class_3532.method_15374(f5) * f5 * f6 / 10.0F * pEntity.method_54296()));
      }

      int j = pEntity.method_7514();
      pMatrixStack.method_22903();
      pMatrixStack.method_22905(0.75F, 0.75F, 0.75F);
      pMatrixStack.method_22904(-0.5, (j - 8) / 16.0F, 0.5);
      pMatrixStack.method_22907(class_7833.field_40716.rotationDegrees(90.0F));
      LootrChestItemRenderer.getInstance().renderByMinecart(pEntity, pMatrixStack, pBuffer, pPackedLight);
      pMatrixStack.method_22909();
      pMatrixStack.method_22905(-1.0F, -1.0F, 1.0F);
      this.field_4747.method_2819(pEntity, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F);
      class_4588 vertexconsumer = pBuffer.getBuffer(this.field_4747.method_23500(this.method_4065(pEntity)));
      this.field_4747.method_60879(pMatrixStack, vertexconsumer, pPackedLight, class_4608.field_21444);
      pMatrixStack.method_22909();
   }
}
