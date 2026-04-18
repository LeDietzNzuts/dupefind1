package ewewukek.musketmod;

import net.minecraft.class_1921;
import net.minecraft.class_2960;
import net.minecraft.class_4587;
import net.minecraft.class_4588;
import net.minecraft.class_4597;
import net.minecraft.class_4608;
import net.minecraft.class_7833;
import net.minecraft.class_897;
import net.minecraft.class_4587.class_4665;
import net.minecraft.class_5617.class_5618;

public class BulletRenderer extends class_897<BulletEntity> {
   public static final class_2960 TEXTURE = MusketMod.resource("textures/entity/bullet.png");
   public static final class_2960 TEXTURE_FIRE = MusketMod.resource("textures/entity/bullet_fire.png");

   public BulletRenderer(class_5618 context) {
      super(context);
   }

   public class_2960 getTextureLocation(BulletEntity bullet) {
      return TEXTURE;
   }

   public void render(BulletEntity bullet, float yaw, float dt, class_4587 poseStack, class_4597 bufferSource, int light) {
      if (!bullet.isFirstTick()) {
         poseStack.method_22903();
         if (bullet.pelletCount() == 1) {
            poseStack.method_22905(0.1F, 0.1F, 0.1F);
         } else {
            poseStack.method_22905(bullet.method_5809() ? 0.075F : 0.05F, 0.05F, 0.05F);
         }

         poseStack.method_22907(this.field_4676.method_24197());
         poseStack.method_22907(class_7833.field_40716.rotationDegrees(180.0F));
         class_4665 pose = poseStack.method_23760();
         class_1921 renderType = class_1921.method_23578(bullet.method_5809() ? TEXTURE_FIRE : TEXTURE);
         class_4588 builder = bufferSource.getBuffer(renderType);
         this.addVertex(builder, pose, -1.0F, -1.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 1.0F, light);
         this.addVertex(builder, pose, 1.0F, -1.0F, 0.0F, 1.0F, 1.0F, 0.0F, 0.0F, 1.0F, light);
         this.addVertex(builder, pose, 1.0F, 1.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 1.0F, light);
         this.addVertex(builder, pose, -1.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, light);
         poseStack.method_22909();
         super.method_3936(bullet, yaw, dt, poseStack, bufferSource, light);
      }
   }

   void addVertex(class_4588 builder, class_4665 pose, float x, float y, float z, float u, float v, float nx, float ny, float nz, int light) {
      builder.method_56824(pose, x, y, z)
         .method_1336(255, 255, 255, 255)
         .method_22913(u, v)
         .method_22922(class_4608.field_21444)
         .method_60803(light)
         .method_60831(pose, nx, ny, nz);
   }
}
