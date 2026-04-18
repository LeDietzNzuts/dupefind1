package com.talhanation.smallships.world.sound.fabric;

import java.util.HashMap;
import java.util.Map;
import net.minecraft.class_2378;
import net.minecraft.class_2960;
import net.minecraft.class_3414;
import net.minecraft.class_7923;

public class ModSoundTypesImpl {
   private static final Map<String, class_3414> entries = new HashMap<>();

   public static class_3414 getSoundType(String id) {
      return entries.get(id);
   }

   private static class_3414 register(String id) {
      class_2960 location = class_2960.method_60655("smallships", id);
      return (class_3414)class_2378.method_10230(class_7923.field_41172, location, class_3414.method_47908(location));
   }

   static {
      entries.put("sail_move", register("sail_move"));
      entries.put("sail_pull", register("sail_pull"));
      entries.put("cannon_shot", register("cannon_shot"));
      entries.put("ship_hit", register("ship_hit"));
   }
}
