package com.talhanation.smallships.world.entity.cannon;

import com.talhanation.smallships.world.item.CannonBallItem;
import org.jetbrains.annotations.Nullable;

public interface ICannonBallSource {
   void consumeCannonBall();

   @Nullable
   CannonBallItem getCannonBallToShoot();
}
