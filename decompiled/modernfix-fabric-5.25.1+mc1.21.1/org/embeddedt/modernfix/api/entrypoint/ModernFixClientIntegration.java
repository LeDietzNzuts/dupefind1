package org.embeddedt.modernfix.api.entrypoint;

import net.minecraft.class_1087;
import net.minecraft.class_1088;
import net.minecraft.class_1091;
import net.minecraft.class_1100;
import net.minecraft.class_3665;
import net.minecraft.class_1088.class_9826;

public interface ModernFixClientIntegration {
   default void onDynamicResourcesStatusChange(boolean enabled) {
   }

   default class_1087 onBakedModelLoad(
      class_1091 location, class_1100 baseModel, class_1087 originalModel, class_3665 state, class_1088 bakery, class_9826 textureGetter
   ) {
      return originalModel;
   }
}
