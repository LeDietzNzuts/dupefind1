package com.magistuarmory.effects;

import dev.architectury.platform.Platform;
import dev.architectury.registry.registries.DeferredRegister;
import net.minecraft.class_1291;
import net.minecraft.class_6880;
import net.minecraft.class_7924;

public class ModEffects {
   public static final DeferredRegister<class_1291> EFFECTS = DeferredRegister.create("magistuarmory", class_7924.field_41208);
   public static class_6880<class_1291> LACERATION;

   public static void init() {
      if (Platform.isFabric()) {
         LACERATION = EFFECTS.register("laceration", LacerationEffect::new);
         EFFECTS.register();
      }
   }
}
