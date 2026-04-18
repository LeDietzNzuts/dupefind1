package com.talhanation.smallships.world.entity.projectile;

import com.talhanation.smallships.world.entity.ModEntityTypes;
import net.minecraft.class_1299;
import net.minecraft.class_1309;
import net.minecraft.class_1937;

public class CannonBallEntity extends AbstractCannonBall {
   public static final String ID = "cannon_ball";

   public static CannonBallEntity factory(class_1299<? extends AbstractCannonBall> entityType, class_1937 level) {
      return new CannonBallEntity(entityType, level);
   }

   public CannonBallEntity(class_1299<? extends AbstractCannonBall> type, class_1937 world) {
      super(type, world);
   }

   public CannonBallEntity(class_1937 world) {
      super(ModEntityTypes.CANNON_BALL, world);
   }

   public CannonBallEntity(class_1937 world, class_1309 owner, double d1, double d2, double d3) {
      super(ModEntityTypes.CANNON_BALL, owner, d1, d2, d3, world);
   }
}
