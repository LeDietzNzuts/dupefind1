package net.caffeinemc.mods.lithium.mixin.util.inventory_change_listening;

import net.caffeinemc.mods.lithium.common.block.entity.inventory_change_tracking.InventoryChangeTracker;
import net.minecraft.class_1937;
import net.minecraft.class_2586;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(class_2586.class)
public class BlockEntityMixin {
   @Shadow
   @Nullable
   protected class_1937 field_11863;

   @Inject(method = "method_11012()V", at = @At("RETURN"))
   private void updateStackListTracking(CallbackInfo ci) {
      if (this.field_11863 != null && !this.field_11863.method_8608() && this instanceof InventoryChangeTracker inventoryChangeTracker) {
         inventoryChangeTracker.lithium$emitRemoved();
      }
   }
}
