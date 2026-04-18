package org.embeddedt.modernfix.blockstate;

import net.minecraft.class_2248;
import net.minecraft.class_2680;
import net.minecraft.class_4970.class_4971;
import org.embeddedt.modernfix.duck.IBlockState;

public class BlockStateCacheHandler {
   public static void rebuildParallel(boolean force) {
      synchronized (class_4971.class) {
         for (class_2680 blockState : class_2248.field_10651) {
            ((IBlockState)blockState).clearCache();
         }
      }
   }
}
