package com.talhanation.smallships.world.entity.projectile;

import com.talhanation.smallships.world.entity.cannon.Cannon;
import net.minecraft.class_1297;
import net.minecraft.class_2394;
import org.jetbrains.annotations.Nullable;
import org.joml.Vector3d;
import org.joml.Vector3f;

public interface ICannonProjectile {
   void shootAndSpawn(Cannon var1, Vector3d var2, Vector3f var3, float var4, float var5, class_1297 var6);

   @Nullable
   default class_2394 getAdditionalCannonShootParticles() {
      return null;
   }
}
