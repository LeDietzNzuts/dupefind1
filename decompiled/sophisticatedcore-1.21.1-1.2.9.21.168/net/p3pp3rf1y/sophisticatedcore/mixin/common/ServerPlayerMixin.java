package net.p3pp3rf1y.sophisticatedcore.mixin.common;

import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.class_1661;
import net.minecraft.class_1799;
import net.minecraft.class_3222;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(class_3222.class)
public class ServerPlayerMixin {
   @Inject(
      method = "drop(Z)Z",
      at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/player/Inventory;removeFromSelected(Z)Lnet/minecraft/world/item/ItemStack;"),
      cancellable = true
   )
   private void sophisticatedcore$drop(boolean dropStack, CallbackInfoReturnable<Boolean> cir, @Local class_1661 inventory) {
      class_1799 selected = inventory.method_7391();
      if (selected.method_7960() || !selected.onDroppedByPlayer((class_3222)this)) {
         cir.setReturnValue(false);
         cir.cancel();
      }
   }
}
