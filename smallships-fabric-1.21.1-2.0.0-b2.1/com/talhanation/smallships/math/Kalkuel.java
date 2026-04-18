package com.talhanation.smallships.math;

import net.minecraft.class_3532;

public class Kalkuel {
   public static float subtractToZero(float num, float sub) {
      float erg;
      if (num < 0.0F) {
         erg = num + sub;
         if (erg > 0.0F) {
            erg = 0.0F;
         }
      } else {
         erg = num - sub;
         if (erg < 0.0F) {
            erg = 0.0F;
         }
      }

      return erg;
   }

   public static float changeToSetPoint(float current, float positiveChange, float negativeChange, float setPoint) {
      if (current < setPoint) {
         current += positiveChange;
      } else {
         current -= negativeChange;
      }

      return current;
   }

   public static float addToSetPoint(float current, float positiveChange, float setPoint) {
      if (current < setPoint) {
         current += positiveChange;
      }

      return current;
   }

   public static double calculateMotionX(float speed, float rotationYaw) {
      return class_3532.method_15374(-rotationYaw * (float) (Math.PI / 180.0)) * speed;
   }

   public static double calculateMotionZ(float speed, float rotationYaw) {
      return class_3532.method_15362(rotationYaw * (float) (Math.PI / 180.0)) * speed;
   }

   public static float getKilometerPerHour(float speed) {
      return speed * 20.0F * 60.0F * 60.0F / 1000.0F;
   }

   public static float getKnots(float speed) {
      return getKilometerPerHour(speed) / 1.852F;
   }

   public static float getMeterPerSecond(float speed) {
      return getKilometerPerHour(speed) / 3.6F;
   }

   public static float getMilesPerHour(float speed) {
      return getKilometerPerHour(speed) / 1.609F;
   }
}
