package com.blamejared.clumps.platform;

import net.minecraft.class_1799;

public interface IPlatformHelper {
   default float getRepairRatio(class_1799 stack) {
      return 1.0F;
   }
}
