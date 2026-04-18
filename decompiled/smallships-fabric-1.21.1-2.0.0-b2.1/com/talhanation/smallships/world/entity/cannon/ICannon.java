package com.talhanation.smallships.world.entity.cannon;

import net.minecraft.class_1937;
import net.minecraft.class_2394;
import net.minecraft.class_3414;

public interface ICannon extends ICannonBallSource {
   class_2394 provideShootParticles();

   void playSoundAt(class_3414 var1, float var2, float var3);

   class_1937 getLevel();
}
