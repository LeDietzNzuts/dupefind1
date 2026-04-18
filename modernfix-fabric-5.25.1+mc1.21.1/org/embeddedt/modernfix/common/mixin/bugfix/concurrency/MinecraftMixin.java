package org.embeddedt.modernfix.common.mixin.bugfix.concurrency;

import java.util.function.BooleanSupplier;
import net.minecraft.class_1255;
import net.minecraft.class_310;
import org.embeddedt.modernfix.ModernFix;
import org.embeddedt.modernfix.annotation.ClientOnlyMixin;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(class_310.class)
@ClientOnlyMixin
public abstract class MinecraftMixin<R extends Runnable> extends class_1255<R> {
   protected MinecraftMixin(String p_i50403_1_) {
      super(p_i50403_1_);
   }

   public void method_18857(BooleanSupplier pIsDone) {
      if (!this.method_18854()) {
         ModernFix.LOGGER.warn("A mod is calling Minecraft.managedBlock from the wrong thread. This is most likely related to one of our parallelizations.");
         ModernFix.LOGGER.warn("ModernFix will work around this, however ideally the issue should be patched in the other mod.");
         ModernFix.LOGGER.warn("Stacktrace", new IllegalThreadStateException());

         while (!pIsDone.getAsBoolean()) {
            try {
               Thread.sleep(100L);
            } catch (InterruptedException var3) {
               Thread.currentThread().interrupt();
            }
         }
      } else {
         super.method_18857(pIsDone);
      }
   }
}
