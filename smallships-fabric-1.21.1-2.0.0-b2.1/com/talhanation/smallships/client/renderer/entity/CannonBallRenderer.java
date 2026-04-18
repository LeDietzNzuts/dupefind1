package com.talhanation.smallships.client.renderer.entity;

import com.talhanation.smallships.client.model.CannonBallModel;
import com.talhanation.smallships.world.entity.projectile.CannonBallEntity;
import net.minecraft.class_2960;
import net.minecraft.class_4587;
import net.minecraft.class_4588;
import net.minecraft.class_4597;
import net.minecraft.class_4608;
import net.minecraft.class_897;
import net.minecraft.class_5617.class_5618;
import org.jetbrains.annotations.NotNull;

public class CannonBallRenderer extends class_897<CannonBallEntity> {
   private final CannonBallModel model = new CannonBallModel();

   public CannonBallRenderer(class_5618 context) {
      super(context);
      this.field_4673 = 0.25F;
   }

   public void render(@NotNull CannonBallEntity entity, float entityYaw, float partialTicks, class_4587 poseStack, class_4597 bufferIn, int packedLightIn) {
      poseStack.method_22903();
      poseStack.method_22905(0.75F, 0.75F, 0.75F);
      poseStack.method_22904(0.0, -1.0, 0.0);
      class_4588 vertexConsumer = bufferIn.getBuffer(this.model.method_23500(this.getTextureLocation(entity)));
      this.model.method_2828(poseStack, vertexConsumer, packedLightIn, class_4608.field_21444, 16777215);
      poseStack.method_22909();
      super.method_3936(entity, entityYaw, partialTicks, poseStack, bufferIn, packedLightIn);
   }

   @NotNull
   public class_2960 getTextureLocation(@NotNull CannonBallEntity entity) {
      return class_2960.method_60655("smallships", "textures/entity/cannon/cannon_ball.png");
   }
}
