package net.caffeinemc.mods.lithium.mixin.block.hopper;

import net.caffeinemc.mods.lithium.common.block.entity.ShapeUpdateHandlingBlockBehaviour;
import net.caffeinemc.mods.lithium.common.hopper.UpdateReceiver;
import net.caffeinemc.mods.lithium.common.world.blockentity.BlockEntityGetter;
import net.minecraft.class_1937;
import net.minecraft.class_2237;
import net.minecraft.class_2248;
import net.minecraft.class_2338;
import net.minecraft.class_2350;
import net.minecraft.class_2377;
import net.minecraft.class_2680;
import net.minecraft.class_3954;
import net.minecraft.class_4538;
import net.minecraft.class_4970.class_2251;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.At.Shift;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(class_2377.class)
public abstract class HopperBlockMixin extends class_2237 implements ShapeUpdateHandlingBlockBehaviour {
   protected HopperBlockMixin(class_2251 settings) {
      super(settings);
   }

   @Override
   public void lithium$handleShapeUpdate(class_4538 levelReader, class_2680 myBlockState, class_2338 myPos, class_2338 posFrom, class_2680 newState) {
      if (!levelReader.method_8608() && newState.method_26204() instanceof class_3954) {
         this.updateHopper(levelReader, myBlockState, myPos, posFrom);
      }
   }

   @Inject(
      method = "method_9612(Lnet/minecraft/class_2680;Lnet/minecraft/class_1937;Lnet/minecraft/class_2338;Lnet/minecraft/class_2248;Lnet/minecraft/class_2338;Z)V",
      at = @At("HEAD")
   )
   private void updateBlockEntity(
      class_2680 blockState, class_1937 world, class_2338 myPos, class_2248 block, class_2338 blockPos2, boolean bl, CallbackInfo ci
   ) {
      if (!world.method_8608() && ((BlockEntityGetter)world).lithium$getLoadedExistingBlockEntity(myPos) instanceof UpdateReceiver updateReceiver) {
         updateReceiver.lithium$invalidateCacheOnUndirectedNeighborUpdate();
      }
   }

   private void updateHopper(class_4538 world, class_2680 myBlockState, class_2338 myPos, class_2338 posFrom) {
      class_2350 facing = (class_2350)myBlockState.method_11654(class_2377.field_11129);
      boolean above = posFrom.method_10264() == myPos.method_10264() + 1;
      if ((
            above
               || posFrom.method_10263() == myPos.method_10263() + facing.method_10148()
                  && posFrom.method_10264() == myPos.method_10264() + facing.method_10164()
                  && posFrom.method_10260() == myPos.method_10260() + facing.method_10165()
         )
         && ((BlockEntityGetter)world).lithium$getLoadedExistingBlockEntity(myPos) instanceof UpdateReceiver updateReceiver) {
         updateReceiver.lithium$invalidateCacheOnNeighborUpdate(above);
      }
   }

   @Inject(
      method = "method_9615(Lnet/minecraft/class_2680;Lnet/minecraft/class_1937;Lnet/minecraft/class_2338;Lnet/minecraft/class_2680;Z)V",
      at = @At(
         value = "INVOKE",
         target = "Lnet/minecraft/class_2377;method_10217(Lnet/minecraft/class_1937;Lnet/minecraft/class_2338;Lnet/minecraft/class_2680;)V",
         shift = Shift.AFTER
      )
   )
   private void workAroundVanillaUpdateSuppression(class_2680 state, class_1937 world, class_2338 pos, class_2680 oldState, boolean notify, CallbackInfo ci) {
      if (world.method_8320(pos) != state) {
         for (class_2350 direction : field_23157) {
            if (((BlockEntityGetter)world).lithium$getLoadedExistingBlockEntity(pos.method_10093(direction)) instanceof UpdateReceiver updateReceiver) {
               updateReceiver.lithium$invalidateCacheOnNeighborUpdate(direction == class_2350.field_11033);
            }
         }
      }
   }
}
