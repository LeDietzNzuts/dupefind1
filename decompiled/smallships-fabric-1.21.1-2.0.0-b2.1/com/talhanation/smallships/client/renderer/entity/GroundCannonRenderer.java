package com.talhanation.smallships.client.renderer.entity;

import com.talhanation.smallships.client.model.CannonModel;
import com.talhanation.smallships.world.entity.cannon.Cannon;
import com.talhanation.smallships.world.entity.cannon.GroundCannonEntity;
import net.minecraft.class_1921;
import net.minecraft.class_2960;
import net.minecraft.class_4587;
import net.minecraft.class_4588;
import net.minecraft.class_4597;
import net.minecraft.class_4608;
import net.minecraft.class_7833;
import net.minecraft.class_897;
import net.minecraft.class_5617.class_5618;
import org.jetbrains.annotations.NotNull;

public class GroundCannonRenderer extends class_897<GroundCannonEntity> {
   private final CannonModel model = new CannonModel();

   public GroundCannonRenderer(class_5618 context) {
      super(context);
   }

   @NotNull
   public class_2960 getTextureLocation(GroundCannonEntity entity) {
      return class_2960.method_60655("smallships", "textures/entity/cannon/ship_cannon.png");
   }

   public void render(
      GroundCannonEntity entity, float entityYaw, float partialTicks, @NotNull class_4587 poseStack, @NotNull class_4597 multiBufferSource, int packedLight
   ) {
      class_2960 resourceLocation = this.getTextureLocation(entity);
      poseStack.method_22903();
      poseStack.method_22905(-1.3F, -1.3F, 1.3F);
      Cannon cannon = entity.getCannon();
      float lerpYaw = -(cannon.getPrevYaw() + (cannon.getYaw() - cannon.getPrevYaw()) * partialTicks);
      poseStack.method_22907(class_7833.field_40716.rotationDegrees(lerpYaw));
      this.renderCannonModel(cannon, partialTicks, resourceLocation, poseStack, multiBufferSource, packedLight);
      poseStack.method_22909();
      super.method_3936(entity, entityYaw, partialTicks, poseStack, multiBufferSource, packedLight);
   }

   public void renderCannonModel(
      Cannon cannon, float partialTicks, class_2960 texture, @NotNull class_4587 poseStack, @NotNull class_4597 multiBufferSource, int packedLight
   ) {
      poseStack.method_22903();
      poseStack.method_22907(class_7833.field_40715.rotationDegrees(180.0F));
      poseStack.method_22905(0.6F, 0.6F, 0.6F);
      poseStack.method_22904(0.0, -1.5, 0.0);
      float pitch = cannon.getPrevPitch() + partialTicks * (cannon.getPitch() - cannon.getPrevPitch());
      this.model.setLaufPitch(pitch);
      class_4588 vertexConsumer = multiBufferSource.getBuffer(class_1921.method_23572(texture));
      this.model.method_60879(poseStack, vertexConsumer, packedLight, class_4608.field_21444);
      poseStack.method_22909();
   }
}
