package net.caffeinemc.mods.lithium.mixin.block.hopper;

import net.caffeinemc.mods.lithium.api.inventory.LithiumInventory;
import net.caffeinemc.mods.lithium.common.hopper.InventoryHelper;
import net.minecraft.class_1263;
import net.minecraft.class_1703;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(class_1703.class)
public abstract class AbstractContainerMenuMixin {
   @Inject(
      method = "method_7618(Lnet/minecraft/class_1263;)I",
      at = @At(value = "INVOKE", target = "Lnet/minecraft/class_1263;method_5439()I", ordinal = 0),
      cancellable = true
   )
   private static void getFastOutputStrength(class_1263 inventory, CallbackInfoReturnable<Integer> cir) {
      if (inventory instanceof LithiumInventory optimizedInventory) {
         cir.setReturnValue(InventoryHelper.getLithiumStackList(optimizedInventory).getSignalStrength(inventory));
      }
   }
}
