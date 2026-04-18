package com.magistuarmory.misc;

import dev.architectury.registry.ReloadListenerRegistry;
import net.minecraft.class_2960;
import net.minecraft.class_3264;

public class ModReloadListenerRegistry {
   public static void init() {
      ReloadListenerRegistry.register(class_3264.field_14190, new HeraldryReloadListener(), class_2960.method_60655("magistuarmory", "heraldry"));
   }
}
