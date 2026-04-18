package net.caffeinemc.mods.lithium.mixin.entity.inactive_navigations;

import it.unimi.dsi.fastutil.objects.ObjectIterator;
import it.unimi.dsi.fastutil.objects.ReferenceOpenHashSet;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Executor;
import java.util.function.Supplier;
import net.caffeinemc.mods.lithium.common.entity.NavigatingEntity;
import net.caffeinemc.mods.lithium.common.world.LithiumData;
import net.caffeinemc.mods.lithium.common.world.ServerWorldExtended;
import net.minecraft.class_1308;
import net.minecraft.class_1408;
import net.minecraft.class_1937;
import net.minecraft.class_2338;
import net.minecraft.class_265;
import net.minecraft.class_2680;
import net.minecraft.class_2874;
import net.minecraft.class_3218;
import net.minecraft.class_3695;
import net.minecraft.class_3949;
import net.minecraft.class_5268;
import net.minecraft.class_5269;
import net.minecraft.class_5321;
import net.minecraft.class_5363;
import net.minecraft.class_5455;
import net.minecraft.class_6880;
import net.minecraft.class_8565;
import net.minecraft.class_32.class_5143;
import net.minecraft.server.MinecraftServer;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(class_3218.class)
public abstract class ServerLevelMixin extends class_1937 implements ServerWorldExtended {
   @Mutable
   @Shadow
   @Final
   Set<class_1308> field_26932;

   protected ServerLevelMixin(
      class_5269 properties,
      class_5321<class_1937> registryRef,
      class_5455 registryManager,
      class_6880<class_2874> dimensionEntry,
      Supplier<class_3695> profiler,
      boolean isClient,
      boolean debugWorld,
      long biomeAccess,
      int maxChainedNeighborUpdates
   ) {
      super(properties, registryRef, registryManager, dimensionEntry, profiler, isClient, debugWorld, biomeAccess, maxChainedNeighborUpdates);
   }

   @Redirect(
      method = "method_8413(Lnet/minecraft/class_2338;Lnet/minecraft/class_2680;Lnet/minecraft/class_2680;I)V",
      at = @At(value = "INVOKE", target = "Ljava/util/Set;iterator()Ljava/util/Iterator;")
   )
   private Iterator<class_1308> getActiveListeners(Set<class_1308> set) {
      return Collections.emptyIterator();
   }

   @Inject(
      method = "<init>(Lnet/minecraft/server/MinecraftServer;Ljava/util/concurrent/Executor;Lnet/minecraft/class_32$class_5143;Lnet/minecraft/class_5268;Lnet/minecraft/class_5321;Lnet/minecraft/class_5363;Lnet/minecraft/class_3949;ZJLjava/util/List;ZLnet/minecraft/class_8565;)V",
      at = @At("TAIL")
   )
   private void init(
      MinecraftServer server,
      Executor workerExecutor,
      class_5143 session,
      class_5268 properties,
      class_5321 worldKey,
      class_5363 dimensionOptions,
      class_3949 worldGenerationProgressListener,
      boolean debugWorld,
      long seed,
      List spawners,
      boolean shouldTickTime,
      class_8565 randomSequencesState,
      CallbackInfo ci
   ) {
      this.field_26932 = new ReferenceOpenHashSet(this.field_26932);
   }

   @Override
   public void lithium$setNavigationActive(class_1308 mobEntity) {
      ReferenceOpenHashSet<class_1408> activeNavigations = ((LithiumData)this).lithium$getData().activeNavigations();
      activeNavigations.add(((NavigatingEntity)mobEntity).lithium$getRegisteredNavigation());
   }

   @Override
   public void lithium$setNavigationInactive(class_1308 mobEntity) {
      ReferenceOpenHashSet<class_1408> activeNavigations = ((LithiumData)this).lithium$getData().activeNavigations();
      activeNavigations.remove(((NavigatingEntity)mobEntity).lithium$getRegisteredNavigation());
   }

   @Inject(
      method = "method_8413(Lnet/minecraft/class_2338;Lnet/minecraft/class_2680;Lnet/minecraft/class_2680;I)V",
      at = @At(value = "INVOKE", target = "Ljava/util/Set;iterator()Ljava/util/Iterator;"),
      locals = LocalCapture.CAPTURE_FAILHARD
   )
   private void updateActiveListeners(
      class_2338 pos, class_2680 oldState, class_2680 newState, int arg3, CallbackInfo ci, class_265 string, class_265 voxelShape, List<class_1408> list
   ) {
      ReferenceOpenHashSet<class_1408> activeNavigations = ((LithiumData)this).lithium$getData().activeNavigations();
      ObjectIterator var10 = activeNavigations.iterator();

      while (var10.hasNext()) {
         class_1408 entityNavigation = (class_1408)var10.next();
         if (entityNavigation.method_18053(pos)) {
            list.add(entityNavigation);
         }
      }
   }

   @Unique
   public boolean areEntityNavigationsConsistent() {
      ReferenceOpenHashSet<class_1408> activeNavigations = ((LithiumData)this).lithium$getData().activeNavigations();
      int i = 0;

      for (class_1308 mobEntity : this.field_26932) {
         class_1408 entityNavigation = mobEntity.method_5942();
         if ((entityNavigation.method_6345() != null && ((NavigatingEntity)mobEntity).lithium$isRegisteredToWorld())
            != activeNavigations.contains(entityNavigation)) {
            return false;
         }

         if (entityNavigation.method_6345() != null) {
            i++;
         }
      }

      return activeNavigations.size() == i;
   }
}
