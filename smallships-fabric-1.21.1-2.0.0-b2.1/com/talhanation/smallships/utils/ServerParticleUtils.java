package com.talhanation.smallships.utils;

import net.minecraft.class_2394;
import net.minecraft.class_3218;
import org.joml.Vector3d;

public class ServerParticleUtils {
   public static void sendParticle(class_3218 level, class_2394 options, Vector3d pos, Vector3d speed) {
      level.method_14199(options, pos.x, pos.y, pos.z, 0, speed.x, speed.y, speed.z, 1.0);
   }
}
