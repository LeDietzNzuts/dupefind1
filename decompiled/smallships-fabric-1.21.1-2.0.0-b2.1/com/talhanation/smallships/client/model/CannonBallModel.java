package com.talhanation.smallships.client.model;

import com.talhanation.smallships.world.entity.projectile.CannonBallEntity;
import net.minecraft.class_2960;
import net.minecraft.class_4587;
import net.minecraft.class_4588;
import net.minecraft.class_5601;
import net.minecraft.class_5603;
import net.minecraft.class_5606;
import net.minecraft.class_5607;
import net.minecraft.class_5609;
import net.minecraft.class_5610;
import net.minecraft.class_583;
import net.minecraft.class_630;
import org.jetbrains.annotations.NotNull;

public class CannonBallModel extends class_583<CannonBallEntity> {
   public static final class_5601 LAYER_LOCATION = new class_5601(class_2960.method_60655("smallships", "model_cannonball"), "main");
   private final class_630 cannonball;

   public CannonBallModel() {
      class_630 root = createBodyLayer().method_32109();
      this.cannonball = root.method_32086("cannonball");
   }

   public static class_5607 createBodyLayer() {
      class_5609 meshdefinition = new class_5609();
      class_5610 partdefinition = meshdefinition.method_32111();
      class_5610 cannonball = partdefinition.method_32117(
         "cannonball",
         class_5606.method_32108()
            .method_32097(1.5F, -4.0F, -1.0F, 1.0F, 2.0F, 2.0F)
            .method_32097(-3.0F, -1.0F, -2.0F, 4.0F, 1.0F, 4.0F)
            .method_32097(-3.0F, -6.0F, -2.0F, 4.0F, 1.0F, 4.0F)
            .method_32097(-2.0F, -6.5F, -1.0F, 2.0F, 1.0F, 2.0F)
            .method_32097(-2.0F, -0.5F, -1.0F, 2.0F, 1.0F, 2.0F)
            .method_32097(-3.0F, -5.0F, 2.0F, 4.0F, 4.0F, 1.0F)
            .method_32097(-2.0F, -4.0F, 2.5F, 2.0F, 2.0F, 1.0F)
            .method_32097(-2.0F, -4.0F, -3.5F, 2.0F, 2.0F, 1.0F)
            .method_32097(-3.0F, -5.0F, -3.0F, 4.0F, 4.0F, 1.0F)
            .method_32097(1.0F, -5.0F, -2.0F, 1.0F, 4.0F, 4.0F)
            .method_32097(-4.0F, -5.0F, -2.0F, 1.0F, 4.0F, 4.0F)
            .method_32097(-4.5F, -4.0F, -1.0F, 1.0F, 2.0F, 2.0F),
         class_5603.method_32090(0.0F, 23.5F, 0.0F)
      );
      return class_5607.method_32110(meshdefinition, 16, 16);
   }

   public void setupAnim(@NotNull CannonBallEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
   }

   public void method_2828(class_4587 poseStack, class_4588 buffer, int packedLight, int packedOverlay, int color) {
      this.cannonball.method_22699(poseStack, buffer, packedLight, packedOverlay, color);
   }
}
