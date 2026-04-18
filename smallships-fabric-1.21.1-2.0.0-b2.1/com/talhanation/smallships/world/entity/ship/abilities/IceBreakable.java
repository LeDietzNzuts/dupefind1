package com.talhanation.smallships.world.entity.ship.abilities;

import net.minecraft.class_1937;
import net.minecraft.class_2246;
import net.minecraft.class_2338;
import net.minecraft.class_238;
import net.minecraft.class_2386;
import net.minecraft.class_2680;
import net.minecraft.class_3417;
import net.minecraft.class_3419;
import net.minecraft.class_2338.class_2339;

public interface IceBreakable extends Ability {
   default void tickIceBreakable() {
      if (this.self().field_6012 % 15 == 0) {
         class_1937 level = this.self().method_5770();
         class_238 boundingBox = this.self().method_5829().method_1014(1.5);
         double offset = 0.75;
         class_2338 start = new class_2338(
            (int)(boundingBox.field_1323 - offset), (int)(boundingBox.field_1322 - offset), (int)(boundingBox.field_1321 - offset)
         );
         class_2338 end = new class_2338((int)(boundingBox.field_1320 + offset), (int)(boundingBox.field_1325 + offset), (int)(boundingBox.field_1324 + offset));
         class_2339 pos = new class_2339();
         boolean hasBroken = false;

         for (int i = start.method_10263(); i <= end.method_10263(); i++) {
            for (int j = start.method_10264(); j <= end.method_10264(); j++) {
               for (int k = start.method_10260(); k <= end.method_10260(); k++) {
                  pos.method_10103(i, j, k);
                  class_2680 blockstate = level.method_8320(pos);
                  if (blockstate.method_26204() instanceof class_2386) {
                     level.method_8652(pos, class_2246.field_10382.method_9564(), 3);
                     hasBroken = true;
                  }
               }
            }
         }

         if (hasBroken) {
            level.method_43128(
               null,
               start.method_10263(),
               start.method_10264(),
               start.method_10260(),
               class_3417.field_15081,
               class_3419.field_15245,
               1.0F,
               0.9F + 0.2F * level.method_8409().method_43057()
            );
         }
      }
   }
}
