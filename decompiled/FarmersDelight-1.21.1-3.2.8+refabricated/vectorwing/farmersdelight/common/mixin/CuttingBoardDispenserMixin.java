package vectorwing.farmersdelight.common.mixin;

import net.minecraft.class_1799;
import net.minecraft.class_1937;
import net.minecraft.class_2315;
import net.minecraft.class_2338;
import net.minecraft.class_2342;
import net.minecraft.class_2350;
import net.minecraft.class_2357;
import net.minecraft.class_2601;
import net.minecraft.class_2680;
import net.minecraft.class_3218;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;
import vectorwing.farmersdelight.common.Configuration;
import vectorwing.farmersdelight.common.block.entity.dispenser.CuttingBoardDispenseBehavior;
import vectorwing.farmersdelight.common.registry.ModBlocks;

@Mixin(class_2315.class)
public abstract class CuttingBoardDispenserMixin {
   @Shadow
   protected abstract class_2357 method_10011(class_1937 var1, class_1799 var2);

   @Inject(
      method = "method_10012(Lnet/minecraft/class_3218;Lnet/minecraft/class_2680;Lnet/minecraft/class_2338;)V",
      at = @At(
         value = "INVOKE",
         target = "Lnet/minecraft/class_2315;method_10011(Lnet/minecraft/class_1937;Lnet/minecraft/class_1799;)Lnet/minecraft/class_2357;"
      ),
      locals = LocalCapture.CAPTURE_FAILHARD,
      cancellable = true
   )
   public void onCuttingBoardDispenseFromInject(
      class_3218 level, class_2680 state, class_2338 pos, CallbackInfo ci, class_2601 dispenser, class_2342 source, int slot, class_1799 stack
   ) {
      class_2680 facingState = level.method_8320(pos.method_10093((class_2350)state.method_11654(class_2315.field_10918)));
      if (Configuration.DISPENSER_TOOLS_CUTTING_BOARD.get() && facingState.method_27852(ModBlocks.CUTTING_BOARD.get())) {
         dispenser.method_5447(slot, CuttingBoardDispenseBehavior.INSTANCE.dispense(source, stack));
         ci.cancel();
      }
   }
}
