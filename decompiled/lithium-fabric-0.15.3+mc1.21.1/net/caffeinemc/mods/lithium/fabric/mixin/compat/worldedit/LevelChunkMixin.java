package net.caffeinemc.mods.lithium.fabric.mixin.compat.worldedit;

import net.caffeinemc.mods.lithium.common.hopper.UpdateReceiver;
import net.caffeinemc.mods.lithium.common.util.DirectionConstants;
import net.caffeinemc.mods.lithium.common.world.WorldHelper;
import net.caffeinemc.mods.lithium.common.world.blockentity.BlockEntityGetter;
import net.minecraft.class_1937;
import net.minecraft.class_2338;
import net.minecraft.class_2350;
import net.minecraft.class_2680;
import net.minecraft.class_2818;
import net.minecraft.class_3954;
import net.minecraft.class_2338.class_2339;
import net.minecraft.class_2818.class_2819;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.At.Shift;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(class_2818.class)
public abstract class LevelChunkMixin {
   @Shadow
   public abstract class_1937 method_12200();

   @Inject(
      method = "method_12010(Lnet/minecraft/class_2338;Lnet/minecraft/class_2680;Z)Lnet/minecraft/class_2680;",
      at = @At(
         value = "INVOKE",
         target = "Lnet/minecraft/class_2680;method_26182(Lnet/minecraft/class_1937;Lnet/minecraft/class_2338;Lnet/minecraft/class_2680;Z)V",
         shift = Shift.BEFORE
      )
   )
   private void updateHoppersIfWorldEditPresent(class_2338 pos, class_2680 state, boolean moved, CallbackInfoReturnable<class_2680> cir) {
      if (state.method_26204() instanceof class_3954 || state.method_31709()) {
         lithium$updateHopperCachesOnNewInventoryAdded((class_2818)this, pos, this.method_12200());
      }
   }

   @Unique
   private static void lithium$updateHopperCachesOnNewInventoryAdded(class_2818 worldChunk, class_2338 pos, class_1937 world) {
      class_2339 neighborPos = new class_2339();

      for (class_2350 offsetDirection : DirectionConstants.ALL) {
         neighborPos.method_25505(pos, offsetDirection);
         if ((
            WorldHelper.arePosWithinSameChunk(pos, neighborPos)
               ? worldChunk.method_12201(neighborPos, class_2819.field_12859)
               : ((BlockEntityGetter)world).lithium$getLoadedExistingBlockEntity(neighborPos)
         ) instanceof UpdateReceiver updateReceiver) {
            updateReceiver.lithium$invalidateCacheOnNeighborUpdate(offsetDirection.method_10153());
         }
      }
   }
}
