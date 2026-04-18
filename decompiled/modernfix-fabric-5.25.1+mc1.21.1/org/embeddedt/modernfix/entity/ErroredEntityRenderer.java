package org.embeddedt.modernfix.entity;

import net.minecraft.class_1059;
import net.minecraft.class_1297;
import net.minecraft.class_2960;
import net.minecraft.class_4587;
import net.minecraft.class_4597;
import net.minecraft.class_4604;
import net.minecraft.class_897;
import net.minecraft.class_5617.class_5618;

public class ErroredEntityRenderer<T extends class_1297> extends class_897<T> {
   public ErroredEntityRenderer(class_5618 arg) {
      super(arg);
   }

   public class_2960 method_3931(T entity) {
      return class_1059.field_5275;
   }

   public boolean method_3933(T livingEntity, class_4604 camera, double camX, double camY, double camZ) {
      return false;
   }

   public void method_3936(T entity, float entityYaw, float partialTick, class_4587 poseStack, class_4597 buffer, int packedLight) {
   }
}
