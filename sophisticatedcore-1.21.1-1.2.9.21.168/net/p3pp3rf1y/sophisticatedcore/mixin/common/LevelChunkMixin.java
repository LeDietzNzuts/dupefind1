package net.p3pp3rf1y.sophisticatedcore.mixin.common;

import net.minecraft.class_1923;
import net.minecraft.class_1937;
import net.minecraft.class_1959;
import net.minecraft.class_2378;
import net.minecraft.class_2586;
import net.minecraft.class_2791;
import net.minecraft.class_2818;
import net.minecraft.class_2826;
import net.minecraft.class_2843;
import net.minecraft.class_5539;
import net.minecraft.class_6749;
import net.p3pp3rf1y.sophisticatedcore.extensions.block.entity.SophisticatedBlockEntity;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.At.Shift;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(class_2818.class)
abstract class LevelChunkMixin extends class_2791 {
   @Shadow
   @Final
   private class_1937 field_12858;

   public LevelChunkMixin(
      class_1923 chunkPos,
      class_2843 upgradeData,
      class_5539 levelHeightAccessor,
      class_2378<class_1959> biomeRegistry,
      long inhabitedTime,
      @Nullable class_2826[] sections,
      @Nullable class_6749 blendingData
   ) {
      super(chunkPos, upgradeData, levelHeightAccessor, biomeRegistry, inhabitedTime, sections, blendingData);
   }

   @Inject(method = "clearAllBlockEntities", at = @At("HEAD"))
   private void sophisticatedCore$unloadChunk(CallbackInfo ci) {
      this.field_34543.values().forEach(SophisticatedBlockEntity::sophisticatedCore_onChunkUnloaded);
   }

   @Inject(
      method = "addAndRegisterBlockEntity",
      at = @At(
         value = "INVOKE",
         target = "Lnet/minecraft/world/level/chunk/LevelChunk;updateBlockEntityTicker(Lnet/minecraft/world/level/block/entity/BlockEntity;)V",
         shift = Shift.AFTER
      )
   )
   public void sophisticatedCore$onBlockEntityLoad(class_2586 blockEntity, CallbackInfo ci) {
      blockEntity.sophisticatedCore_onLoad();
   }

   @Inject(method = "registerAllBlockEntitiesAfterLevelLoad", at = @At("HEAD"))
   public void sophisticatedCore$addPendingBlockEntities(CallbackInfo ci) {
      this.field_12858.sophisticatedCore_addFreshBlockEntities(this.field_34543.values());
   }
}
