package net.caffeinemc.mods.lithium.common.block;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Predicate;
import net.minecraft.class_2680;

public abstract class TrackedBlockStatePredicate implements Predicate<class_2680> {
   public static final AtomicBoolean FULLY_INITIALIZED = new AtomicBoolean(false);
   private final int index;

   public TrackedBlockStatePredicate(int index) {
      if (FULLY_INITIALIZED.get()) {
         throw new IllegalStateException("Lithium Cached BlockState Flags: Cannot register more flags after assuming to be fully initialized.");
      } else {
         this.index = index;
      }
   }

   public int getIndex() {
      return this.index;
   }

   static {
      if (!BlockStateFlags.ENABLED) {
         System.out.println("Lithium Cached BlockState Flags are disabled!");
      }
   }
}
