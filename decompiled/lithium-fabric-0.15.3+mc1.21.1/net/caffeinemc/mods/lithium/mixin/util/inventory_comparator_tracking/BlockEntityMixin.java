package net.caffeinemc.mods.lithium.mixin.util.inventory_comparator_tracking;

import net.caffeinemc.mods.lithium.common.block.entity.inventory_change_tracking.InventoryChangeTracker;
import net.caffeinemc.mods.lithium.common.block.entity.inventory_comparator_tracking.ComparatorTracker;
import net.caffeinemc.mods.lithium.common.block.entity.inventory_comparator_tracking.ComparatorTracking;
import net.minecraft.class_1937;
import net.minecraft.class_2338;
import net.minecraft.class_2350;
import net.minecraft.class_2586;
import net.minecraft.class_2591;
import net.minecraft.class_2680;
import net.minecraft.class_2350.class_2351;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(class_2586.class)
public class BlockEntityMixin implements ComparatorTracker {
   @Shadow
   @Nullable
   protected class_1937 field_11863;
   @Shadow
   @Final
   protected class_2338 field_11867;
   private static final byte UNKNOWN = -1;
   private static final byte COMPARATOR_PRESENT = 1;
   private static final byte COMPARATOR_ABSENT = 0;
   byte hasComparators;

   @Inject(method = "<init>(Lnet/minecraft/class_2591;Lnet/minecraft/class_2338;Lnet/minecraft/class_2680;)V", at = @At("RETURN"))
   private void init(class_2591<?> type, class_2338 pos, class_2680 state, CallbackInfo ci) {
      this.hasComparators = -1;
   }

   @Override
   public void lithium$onComparatorAdded(class_2350 direction, int offset) {
      byte hasComparators = this.hasComparators;
      if (direction.method_10166() != class_2351.field_11052 && hasComparators != 1 && offset >= 1 && offset <= 2) {
         this.hasComparators = 1;
         if (this instanceof InventoryChangeTracker inventoryChangeTracker) {
            inventoryChangeTracker.lithium$emitFirstComparatorAdded();
         }
      }
   }

   @Override
   public boolean lithium$hasAnyComparatorNearby() {
      if (this.hasComparators == -1) {
         this.hasComparators = (byte)(ComparatorTracking.findNearbyComparators(this.field_11863, this.field_11867) ? 1 : 0);
      }

      return this.hasComparators == 1;
   }

   @Inject(method = "method_11012()V", at = @At("HEAD"))
   private void forgetNearbyComparators(CallbackInfo ci) {
      this.hasComparators = -1;
   }
}
