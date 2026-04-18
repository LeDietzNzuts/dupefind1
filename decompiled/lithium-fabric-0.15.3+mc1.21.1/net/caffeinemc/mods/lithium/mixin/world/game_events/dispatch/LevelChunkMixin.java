package net.caffeinemc.mods.lithium.mixin.world.game_events.dispatch;

import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import net.caffeinemc.mods.lithium.common.world.GameEventDispatcherStorage;
import net.caffeinemc.mods.lithium.common.world.LithiumData;
import net.caffeinemc.mods.lithium.common.world.chunk.ChunkStatusTracker;
import net.minecraft.class_1923;
import net.minecraft.class_1937;
import net.minecraft.class_1959;
import net.minecraft.class_2378;
import net.minecraft.class_2791;
import net.minecraft.class_2818;
import net.minecraft.class_2826;
import net.minecraft.class_2843;
import net.minecraft.class_5539;
import net.minecraft.class_5713;
import net.minecraft.class_6749;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.At.Shift;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(class_2818.class)
public abstract class LevelChunkMixin extends class_2791 {
   @Shadow
   @Final
   @Mutable
   private Int2ObjectMap<class_5713> field_28129;

   public LevelChunkMixin(
      class_1923 pos,
      class_2843 upgradeData,
      class_5539 heightLimitView,
      class_2378<class_1959> biomeRegistry,
      long inhabitedTime,
      @Nullable class_2826[] sectionArray,
      @Nullable class_6749 blendingData
   ) {
      super(pos, upgradeData, heightLimitView, biomeRegistry, inhabitedTime, sectionArray, blendingData);
   }

   @Shadow
   public abstract class_1937 method_12200();

   @Inject(
      method = "<init>(Lnet/minecraft/class_1937;Lnet/minecraft/class_1923;Lnet/minecraft/class_2843;Lnet/minecraft/class_6755;Lnet/minecraft/class_6755;J[Lnet/minecraft/class_2826;Lnet/minecraft/class_2818$class_6829;Lnet/minecraft/class_6749;)V",
      at = @At(value = "INVOKE", target = "Lnet/minecraft/class_2902$class_2903;values()[Lnet/minecraft/class_2902$class_2903;")
   )
   private void initGameEventDispatchers(CallbackInfo ci) {
      if (this.field_28129.isEmpty()) {
         this.field_28129 = null;
      }
   }

   @Inject(method = "method_51382(I)V", at = @At("RETURN"))
   private void removeGameEventDispatcher(int ySectionCoord, CallbackInfo ci) {
      if (this.field_28129 != null && this.field_28129.isEmpty()) {
         this.setGameEventListenerRegistrySections(null);
      }
   }

   @Inject(
      method = "method_32914(I)Lnet/minecraft/class_5713;",
      at = @At(value = "FIELD", shift = Shift.BEFORE, target = "Lnet/minecraft/class_2818;field_28129:Lit/unimi/dsi/fastutil/ints/Int2ObjectMap;")
   )
   private void initializeCollection(CallbackInfoReturnable<class_5713> cir) {
      if (this.field_28129 == null) {
         this.setGameEventListenerRegistrySections(new Int2ObjectOpenHashMap(4));
      }
   }

   @Unique
   public void setGameEventListenerRegistrySections(Int2ObjectMap<class_5713> gameEventListenerRegistrySections) {
      GameEventDispatcherStorage storage = ((LithiumData)this.method_12200()).lithium$getData().gameEventDispatchers();
      storage.replace(this.method_12004().method_8324(), gameEventListenerRegistrySections);
      this.field_28129 = gameEventListenerRegistrySections;
   }

   static {
      ChunkStatusTracker.registerLoadCallback((serverLevel, chunk) -> {
         GameEventDispatcherStorage dispatcherStorage = ((LithiumData)serverLevel).lithium$getData().gameEventDispatchers();
         dispatcherStorage.addChunk(chunk.method_12004().method_8324(), ((LevelChunkMixin)chunk).field_28129);
      });
      ChunkStatusTracker.registerUnloadCallback((serverLevel, pos) -> {
         GameEventDispatcherStorage dispatcherStorage = ((LithiumData)serverLevel).lithium$getData().gameEventDispatchers();
         dispatcherStorage.removeChunk(pos.method_8324());
      });
   }
}
