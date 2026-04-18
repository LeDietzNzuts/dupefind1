package dev.architectury.core.item;

import dev.architectury.platform.Platform;
import java.util.function.Supplier;
import net.minecraft.class_1299;
import net.minecraft.class_1785;
import net.minecraft.class_3414;
import net.minecraft.class_3611;
import net.minecraft.class_1792.class_1793;

public class ArchitecturyMobBucketItem extends class_1785 {
   public ArchitecturyMobBucketItem(
      Supplier<? extends class_1299<?>> entity, Supplier<? extends class_3611> fluid, Supplier<? extends class_3414> sound, class_1793 properties
   ) {
      super(checkPlatform(entity).get(), fluid.get(), sound.get(), properties);
   }

   private static <T> T checkPlatform(T obj) {
      if (Platform.isForgeLike()) {
         throw new IllegalStateException("This class should've been replaced on Forge!");
      } else {
         return obj;
      }
   }
}
