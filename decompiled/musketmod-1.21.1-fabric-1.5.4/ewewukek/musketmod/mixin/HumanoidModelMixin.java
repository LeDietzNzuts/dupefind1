package ewewukek.musketmod.mixin;

import ewewukek.musketmod.ClientUtilities;
import net.minecraft.class_1309;
import net.minecraft.class_572;
import net.minecraft.class_572.class_573;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(class_572.class)
abstract class HumanoidModelMixin {
   private class_1309 entity;
   private class_573 leftArmPose;
   private class_573 rightArmPose;

   @Inject(method = "setupAnim", at = @At("HEAD"))
   private void setupAnimHead(class_1309 entity, float f, float g, float h, float i, float j, CallbackInfo ci) {
      this.entity = entity;
      class_572<?> model = (class_572<?>)this;
      this.leftArmPose = model.field_3399;
      this.rightArmPose = model.field_3395;
   }

   @Inject(method = "poseRightArm", at = @At("TAIL"))
   private void poseRightArm(CallbackInfo ci) {
      class_572<?> model = (class_572<?>)this;
      if (this.entity != null && ClientUtilities.poseArm(this.entity, (class_572<? extends class_1309>)model, model.field_3401)) {
         model.field_3395 = class_573.field_27434;
      }
   }

   @Inject(method = "poseLeftArm", at = @At("TAIL"))
   private void poseLeftArm(CallbackInfo ci) {
      class_572<?> model = (class_572<?>)this;
      if (this.entity != null && ClientUtilities.poseArm(this.entity, (class_572<? extends class_1309>)model, model.field_27433)) {
         model.field_3399 = class_573.field_27434;
      }
   }

   @Inject(method = "setupAnim", at = @At("TAIL"))
   private void setupAnimTail(CallbackInfo ci) {
      class_572<?> model = (class_572<?>)this;
      model.field_3395 = this.rightArmPose;
      model.field_3399 = this.leftArmPose;
   }
}
