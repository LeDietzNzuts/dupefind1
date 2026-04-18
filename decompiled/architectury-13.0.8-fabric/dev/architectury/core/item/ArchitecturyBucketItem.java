package dev.architectury.core.item;

import dev.architectury.hooks.fluid.FluidBucketHooks;
import dev.architectury.platform.Platform;
import java.util.function.Supplier;
import net.minecraft.class_1755;
import net.minecraft.class_3611;
import net.minecraft.class_1792.class_1793;

public class ArchitecturyBucketItem extends class_1755 {
   public ArchitecturyBucketItem(Supplier<? extends class_3611> fluid, class_1793 properties) {
      super(checkPlatform(fluid).get(), properties);
   }

   private static <T> T checkPlatform(T obj) {
      if (Platform.isForgeLike()) {
         throw new IllegalStateException("This class should've been replaced on Forge!");
      } else {
         return obj;
      }
   }

   public final class_3611 getContainedFluid() {
      return FluidBucketHooks.getFluid(this);
   }
}
