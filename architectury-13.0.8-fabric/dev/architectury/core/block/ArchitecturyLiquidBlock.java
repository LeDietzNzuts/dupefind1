package dev.architectury.core.block;

import dev.architectury.platform.Platform;
import java.util.function.Supplier;
import net.minecraft.class_2404;
import net.minecraft.class_3609;
import net.minecraft.class_4970.class_2251;

public class ArchitecturyLiquidBlock extends class_2404 {
   public ArchitecturyLiquidBlock(Supplier<? extends class_3609> fluid, class_2251 properties) {
      super(checkPlatform(fluid).get(), properties);
   }

   private static <T> T checkPlatform(T obj) {
      if (Platform.isForgeLike()) {
         throw new IllegalStateException("This class should've been replaced on Forge!");
      } else {
         return obj;
      }
   }
}
