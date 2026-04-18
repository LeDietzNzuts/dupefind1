package com.talhanation.smallships.client.renderer.entity;

import com.talhanation.smallships.client.model.BriggModel;
import com.talhanation.smallships.world.entity.ship.BriggEntity;
import net.minecraft.class_4587;
import net.minecraft.class_4597;
import net.minecraft.class_7833;
import net.minecraft.class_1690.class_1692;
import net.minecraft.class_5617.class_5618;
import org.jetbrains.annotations.NotNull;

public class BriggRenderer extends ShipRenderer<BriggEntity> {
   public BriggRenderer(class_5618 context) {
      super(context);
   }

   protected BriggModel createBoatModel(class_5618 context, class_1692 type) {
      return new BriggModel(context.method_32167(BriggModel.LAYER_LOCATION));
   }

   @Override
   protected float getCannonHeightOffset() {
      return -0.25F;
   }

   public void render(
      @NotNull BriggEntity briggEntity,
      float entityYaw,
      float partialTicks,
      @NotNull class_4587 poseStack,
      @NotNull class_4597 multiBufferSource,
      int packedLight
   ) {
      poseStack.method_22903();
      poseStack.method_22907(class_7833.field_40716.rotationDegrees(180.0F - entityYaw));
      poseStack.method_22904(0.0, 2.0, 0.0);
      super.render(briggEntity, entityYaw, partialTicks, poseStack, multiBufferSource, packedLight);
   }
}
