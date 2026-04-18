package com.talhanation.smallships.client.renderer.entity;

import com.talhanation.smallships.client.model.GalleyModel;
import com.talhanation.smallships.world.entity.ship.GalleyEntity;
import net.minecraft.class_4587;
import net.minecraft.class_4597;
import net.minecraft.class_7833;
import net.minecraft.class_1690.class_1692;
import net.minecraft.class_5617.class_5618;
import org.jetbrains.annotations.NotNull;

public class GalleyRenderer extends ShipRenderer<GalleyEntity> {
   public GalleyRenderer(class_5618 context) {
      super(context);
   }

   protected GalleyModel createBoatModel(class_5618 context, class_1692 type) {
      return new GalleyModel(context.method_32167(GalleyModel.LAYER_LOCATION));
   }

   @Override
   protected float getCannonHeightOffset() {
      return 0.25F;
   }

   public void render(
      @NotNull GalleyEntity galleyEntity,
      float entityYaw,
      float partialTicks,
      @NotNull class_4587 poseStack,
      @NotNull class_4597 multiBufferSource,
      int packedLight
   ) {
      poseStack.method_22903();
      poseStack.method_22907(class_7833.field_40716.rotationDegrees(180.0F - entityYaw));
      poseStack.method_22904(0.0, 2.7, 0.0);
      super.render(galleyEntity, entityYaw, partialTicks, poseStack, multiBufferSource, packedLight);
   }
}
