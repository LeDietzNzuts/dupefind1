package net.caffeinemc.mods.lithium.mixin.experimental.entity.block_caching.block_support;

import com.llamalad7.mixinextras.sugar.Local;
import java.util.Optional;
import net.caffeinemc.mods.lithium.common.entity.LithiumEntityCollisions;
import net.caffeinemc.mods.lithium.common.tracking.block.BlockCache;
import net.caffeinemc.mods.lithium.common.tracking.block.BlockCacheProvider;
import net.minecraft.class_1297;
import net.minecraft.class_1937;
import net.minecraft.class_2338;
import net.minecraft.class_243;
import net.minecraft.class_265;
import net.minecraft.class_2680;
import net.minecraft.class_3726;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.At.Shift;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(class_1297.class)
public abstract class EntityMixin implements BlockCacheProvider, LithiumEntityCollisions.SupportingBlockCollisionShapeProvider {
   @Shadow
   public Optional<class_2338> field_44784;

   @Shadow
   public abstract class_1937 method_37908();

   @Shadow
   protected abstract double method_56989();

   @Inject(
      method = "method_51703(ZLnet/minecraft/class_243;)V",
      cancellable = true,
      at = @At(value = "INVOKE", shift = Shift.BEFORE, target = "Lnet/minecraft/class_1297;method_5829()Lnet/minecraft/class_238;")
   )
   private void cancelIfSkippable(boolean onGround, class_243 movement, CallbackInfo ci) {
      if (movement == null || movement.field_1352 == 0.0 && movement.field_1350 == 0.0) {
         BlockCache bc = this.getUpdatedBlockCache((class_1297)this);
         if (bc.canSkipSupportingBlockSearch()) {
            ci.cancel();
         }
      }
   }

   @Inject(
      method = "method_51703(ZLnet/minecraft/class_243;)V",
      at = @At(
         value = "INVOKE_ASSIGN",
         ordinal = 0,
         shift = Shift.AFTER,
         target = "Lnet/minecraft/class_1937;method_51718(Lnet/minecraft/class_1297;Lnet/minecraft/class_238;)Ljava/util/Optional;"
      )
   )
   private void cacheSupportingBlockSearch(CallbackInfo ci, @Local Optional<class_2338> pos) {
      BlockCache bc = this.lithium$getBlockCache();
      if (bc.isTracking()) {
         bc.setCanSkipSupportingBlockSearch(true);
         if (pos.isPresent() && this.method_56989() > 0.0) {
            bc.cacheSupportingBlock(this.method_37908().method_8320(pos.get()));
         }
      }
   }

   @Inject(
      method = "method_51703(ZLnet/minecraft/class_243;)V",
      at = @At(
         value = "INVOKE",
         ordinal = 1,
         target = "Lnet/minecraft/class_1937;method_51718(Lnet/minecraft/class_1297;Lnet/minecraft/class_238;)Ljava/util/Optional;"
      )
   )
   private void uncacheSupportingBlockSearch(CallbackInfo ci) {
      BlockCache bc = this.lithium$getBlockCache();
      if (bc.isTracking()) {
         bc.setCanSkipSupportingBlockSearch(false);
      }
   }

   @Inject(
      method = "method_51703(ZLnet/minecraft/class_243;)V",
      at = @At(value = "INVOKE", target = "Ljava/util/Optional;empty()Ljava/util/Optional;", remap = false)
   )
   private void uncacheSupportingBlockSearch1(boolean onGround, class_243 movement, CallbackInfo ci) {
      BlockCache bc = this.lithium$getBlockCache();
      if (bc.isTracking()) {
         bc.setCanSkipSupportingBlockSearch(false);
      }
   }

   @Nullable
   @Override
   public class_265 lithium$getCollisionShapeBelow() {
      BlockCache bc = this.getUpdatedBlockCache((class_1297)this);
      if (bc.isTracking()) {
         class_2680 cachedSupportingBlock = bc.getCachedSupportingBlock();
         if (cachedSupportingBlock != null && this.field_44784.isPresent()) {
            class_2338 blockPos = this.field_44784.get();
            return cachedSupportingBlock.method_26194(this.method_37908(), blockPos, class_3726.method_16195((class_1297)this))
               .method_1096(blockPos.method_10263(), blockPos.method_10264(), blockPos.method_10260());
         }
      }

      return null;
   }
}
