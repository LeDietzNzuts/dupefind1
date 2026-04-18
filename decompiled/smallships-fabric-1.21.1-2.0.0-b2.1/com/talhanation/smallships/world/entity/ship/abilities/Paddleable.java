package com.talhanation.smallships.world.entity.ship.abilities;

import com.talhanation.smallships.config.SmallShipsConfig;
import com.talhanation.smallships.mixin.controlling.BoatAccessor;
import net.minecraft.class_3532;
import net.minecraft.class_630;

public interface Paddleable extends Ability {
   default void tickPaddleShip() {
   }

   default float getPaddlingModifier() {
      return this.self().isForward() ? ((Double)SmallShipsConfig.Common.shipGeneralPaddlingModifier.get()).floatValue() : 0.0F;
   }

   default void controlBoatPaddleShip() {
      if (this.self().method_5787()) {
         this.self().method_7538(this.shouldPaddleLeft(), this.shouldPaddleRight());
      }
   }

   default void animatePaddle(Paddleable.PaddleSide side, class_630 modelPart, float f) {
      float f2 = this.self().method_7551(side.ordinal(), f);
      float xRotChange = class_3532.method_37166((float) (-Math.PI / 3), (float) (-Math.PI / 12), (class_3532.method_15374(-f2) + 1.0F) / 2.0F);
      float yRotChange = class_3532.method_37166((float) (-Math.PI / 4), (float) (Math.PI / 4), (class_3532.method_15374(-f2 + 1.0F) + 1.0F) / 2.0F);
      if (side.equals(Paddleable.PaddleSide.LEFT)) {
         modelPart.field_3675 = -yRotChange;
         modelPart.field_3654 = 4.55F - ((float) Math.PI - xRotChange);
      } else {
         modelPart.field_3675 = (float) Math.PI + yRotChange;
         modelPart.field_3654 = 4.5415926F + xRotChange;
      }
   }

   private boolean shouldPaddleLeft() {
      return ((BoatAccessor)this.self()).isInputRight() && !((BoatAccessor)this.self()).isInputLeft() || ((BoatAccessor)this.self()).isInputUp();
   }

   private boolean shouldPaddleRight() {
      return ((BoatAccessor)this.self()).isInputLeft() && !((BoatAccessor)this.self()).isInputRight() || ((BoatAccessor)this.self()).isInputUp();
   }

   public static enum PaddleSide {
      LEFT,
      RIGHT;
   }
}
