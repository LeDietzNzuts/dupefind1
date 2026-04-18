package net.caffeinemc.mods.lithium.mixin.util.inventory_change_listening;

import net.caffeinemc.mods.lithium.common.block.entity.inventory_change_tracking.InventoryChangeTracker;
import net.minecraft.class_1799;
import net.minecraft.class_2371;
import net.minecraft.class_2487;
import net.minecraft.class_2589;
import net.minecraft.class_2595;
import net.minecraft.class_2601;
import net.minecraft.class_2609;
import net.minecraft.class_2614;
import net.minecraft.class_2624;
import net.minecraft.class_2627;
import net.minecraft.class_3719;
import net.minecraft.class_7225.class_7874;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

public class StackListReplacementTracking {
   @Mixin(class_2609.class)
   public abstract static class AbstractFurnaceBlockEntityMixin implements InventoryChangeTracker {
   }

   @Mixin(class_3719.class)
   public abstract static class BarrelBlockEntityMixin implements InventoryChangeTracker {
      @Inject(method = "method_11281(Lnet/minecraft/class_2371;)V", at = @At("RETURN"))
      public void setInventoryStackListReplacement(class_2371<class_1799> list, CallbackInfo ci) {
         this.lithium$emitStackListReplaced();
      }
   }

   @Mixin(class_2624.class)
   public abstract static class BaseContainerBlockEntityMixin {
      @Inject(method = "method_11014(Lnet/minecraft/class_2487;Lnet/minecraft/class_7225$class_7874;)V", at = @At("RETURN"))
      public void readNbtStackListReplacement(class_2487 nbt, class_7874 registryLookup, CallbackInfo ci) {
         if (this instanceof InventoryChangeTracker inventoryChangeTracker) {
            inventoryChangeTracker.lithium$emitStackListReplaced();
         }
      }
   }

   @Mixin(class_2589.class)
   public abstract static class BrewingStandBlockEntityMixin implements InventoryChangeTracker {
   }

   @Mixin(class_2595.class)
   public abstract static class ChestBlockEntityMixin implements InventoryChangeTracker {
      @Inject(method = "method_11281(Lnet/minecraft/class_2371;)V", at = @At("RETURN"))
      public void setInventoryStackListReplacement(class_2371<class_1799> list, CallbackInfo ci) {
         this.lithium$emitStackListReplaced();
      }
   }

   @Mixin(class_2601.class)
   public abstract static class DispenserBlockEntityMixin implements InventoryChangeTracker {
      @Inject(method = "method_11281(Lnet/minecraft/class_2371;)V", at = @At("RETURN"))
      public void setInventoryStackListReplacement(class_2371<class_1799> list, CallbackInfo ci) {
         this.lithium$emitStackListReplaced();
      }
   }

   @Mixin(class_2614.class)
   public abstract static class HopperBlockEntityMixin implements InventoryChangeTracker {
      @Inject(method = "method_11281(Lnet/minecraft/class_2371;)V", at = @At("RETURN"))
      public void setInventoryStackListReplacement(class_2371<class_1799> list, CallbackInfo ci) {
         this.lithium$emitStackListReplaced();
      }
   }

   @Mixin(class_2627.class)
   public abstract static class ShulkerBoxBlockEntityMixin implements InventoryChangeTracker {
      @Inject(method = "method_11281(Lnet/minecraft/class_2371;)V", at = @At("RETURN"))
      public void setInventoryStackListReplacement(class_2371<class_1799> list, CallbackInfo ci) {
         this.lithium$emitStackListReplaced();
      }
   }
}
