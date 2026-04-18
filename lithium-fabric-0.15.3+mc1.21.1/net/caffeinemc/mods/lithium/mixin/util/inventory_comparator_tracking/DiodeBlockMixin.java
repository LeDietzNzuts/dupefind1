package net.caffeinemc.mods.lithium.mixin.util.inventory_comparator_tracking;

import net.caffeinemc.mods.lithium.common.block.entity.inventory_comparator_tracking.ComparatorTracking;
import net.minecraft.class_1937;
import net.minecraft.class_2246;
import net.minecraft.class_2286;
import net.minecraft.class_2312;
import net.minecraft.class_2338;
import net.minecraft.class_2680;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(class_2312.class)
public abstract class DiodeBlockMixin {
   @Inject(
      method = "method_9615(Lnet/minecraft/class_2680;Lnet/minecraft/class_1937;Lnet/minecraft/class_2338;Lnet/minecraft/class_2680;Z)V",
      at = @At("RETURN")
   )
   private void notifyOnBlockAdded(class_2680 state, class_1937 world, class_2338 pos, class_2680 oldState, boolean notify, CallbackInfo ci) {
      if (this instanceof class_2286 && !oldState.method_27852(class_2246.field_10377)) {
         ComparatorTracking.notifyNearbyBlockEntitiesAboutNewComparator(world, pos);
      }
   }
}
