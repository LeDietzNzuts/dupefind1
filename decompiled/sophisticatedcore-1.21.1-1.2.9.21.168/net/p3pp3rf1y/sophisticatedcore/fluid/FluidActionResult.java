package net.p3pp3rf1y.sophisticatedcore.fluid;

import net.minecraft.class_1799;

public class FluidActionResult {
   public static final FluidActionResult FAILURE = new FluidActionResult(false, class_1799.field_8037);
   private final boolean success;
   private final class_1799 result;

   public FluidActionResult(class_1799 result) {
      this(true, result);
   }

   public FluidActionResult(boolean success, class_1799 result) {
      this.success = success;
      this.result = result;
   }

   public boolean isSuccess() {
      return this.success;
   }

   public class_1799 getResult() {
      return this.result;
   }
}
