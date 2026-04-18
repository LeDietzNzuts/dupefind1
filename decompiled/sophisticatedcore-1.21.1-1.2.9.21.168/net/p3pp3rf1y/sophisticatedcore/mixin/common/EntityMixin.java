package net.p3pp3rf1y.sophisticatedcore.mixin.common;

import com.llamalad7.mixinextras.injector.v2.WrapWithCondition;
import com.llamalad7.mixinextras.sugar.Local;
import java.util.Collection;
import net.minecraft.class_1297;
import net.minecraft.class_1542;
import net.minecraft.class_1937;
import net.minecraft.class_2338;
import net.minecraft.class_2487;
import net.minecraft.class_2680;
import net.p3pp3rf1y.sophisticatedcore.extensions.entity.SophisticatedEntity;
import net.p3pp3rf1y.sophisticatedcore.util.MixinHelper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(class_1297.class)
public class EntityMixin implements SophisticatedEntity {
   @Unique
   private static final String SOPHISTICATEDCOREDATA_NBT_KEY = "SophisticatedCoreData";
   @Shadow
   private class_1937 field_6002;
   @Unique
   private Collection<class_1542> sophisticatedCore$captureDrops = null;
   @Unique
   private class_2487 sophisticatedCore$customData;

   @WrapWithCondition(
      method = "spawnAtLocation(Lnet/minecraft/world/item/ItemStack;F)Lnet/minecraft/world/entity/item/ItemEntity;",
      at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/Level;addFreshEntity(Lnet/minecraft/world/entity/Entity;)Z")
   )
   public boolean sophisticatedCore$captureDrops(class_1937 level, class_1297 entity) {
      if (this.sophisticatedCaptureDrops() != null && entity instanceof class_1542 item) {
         this.sophisticatedCaptureDrops().add(item);
         return false;
      } else {
         return true;
      }
   }

   @Unique
   @Override
   public Collection<class_1542> sophisticatedCaptureDrops() {
      return this.sophisticatedCore$captureDrops;
   }

   @Unique
   @Override
   public Collection<class_1542> sophisticatedCaptureDrops(Collection<class_1542> value) {
      Collection<class_1542> ret = this.sophisticatedCore$captureDrops;
      this.sophisticatedCore$captureDrops = value;
      return ret;
   }

   @Inject(
      method = "spawnSprintParticle",
      at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/block/state/BlockState;getRenderShape()Lnet/minecraft/world/level/block/RenderShape;"),
      cancellable = true
   )
   private void sophisticatedCore$addRunningEffects(CallbackInfo ci, @Local class_2338 blockPos, @Local class_2680 blockState) {
      if (blockState.sophisticatedCore_addRunningEffects(this.field_6002, blockPos, MixinHelper.cast(this))) {
         ci.cancel();
      }
   }

   @Override
   public class_2487 getSophisticatedCustomData() {
      if (this.sophisticatedCore$customData == null) {
         this.sophisticatedCore$customData = new class_2487();
      }

      return this.sophisticatedCore$customData;
   }

   @Inject(
      method = "saveWithoutId",
      at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/Entity;addAdditionalSaveData(Lnet/minecraft/nbt/CompoundTag;)V")
   )
   public void sophisticatedCore$saveAdditionalData(class_2487 compound, CallbackInfoReturnable<class_2487> cir) {
      if (this.sophisticatedCore$customData != null && !this.sophisticatedCore$customData.method_33133()) {
         compound.method_10566("SophisticatedCoreData", this.sophisticatedCore$customData);
      }
   }

   @Inject(method = "load", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/Entity;readAdditionalSaveData(Lnet/minecraft/nbt/CompoundTag;)V"))
   public void sophisticatedCore$readAdditionalData(class_2487 compound, CallbackInfo ci) {
      if (compound.method_10545("SophisticatedCoreData")) {
         this.sophisticatedCore$customData = compound.method_10562("SophisticatedCoreData");
      }
   }
}
