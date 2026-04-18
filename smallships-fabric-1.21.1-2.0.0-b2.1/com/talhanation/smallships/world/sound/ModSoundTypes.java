package com.talhanation.smallships.world.sound;

import com.talhanation.smallships.world.sound.fabric.ModSoundTypesImpl;
import dev.architectury.injectables.annotations.ExpectPlatform;
import dev.architectury.injectables.annotations.ExpectPlatform.Transformed;
import net.minecraft.class_3414;

public class ModSoundTypes {
   public static final class_3414 SAIL_MOVE = getSoundType("sail_move");
   public static final class_3414 SAIL_PULL = getSoundType("sail_pull");
   public static final class_3414 CANNON_SHOT = getSoundType("cannon_shot");
   public static final class_3414 SHIP_HIT = getSoundType("ship_hit");

   @ExpectPlatform
   @Transformed
   public static class_3414 getSoundType(String id) {
      return ModSoundTypesImpl.getSoundType(id);
   }
}
