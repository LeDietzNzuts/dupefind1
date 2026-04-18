package net.caffeinemc.mods.lithium.common.util;

import java.lang.reflect.Field;
import net.minecraft.class_2812;
import net.minecraft.class_2818;
import sun.misc.Unsafe;

public class ChunkConstants {
   public static final class_2818 DUMMY_CHUNK;

   static {
      try {
         Field f = Unsafe.class.getDeclaredField("theUnsafe");
         f.setAccessible(true);
         Unsafe unsafe = (Unsafe)f.get(null);
         DUMMY_CHUNK = (class_2818)unsafe.allocateInstance(class_2812.class);
      } catch (Exception var2) {
         throw new RuntimeException(var2);
      }
   }
}
