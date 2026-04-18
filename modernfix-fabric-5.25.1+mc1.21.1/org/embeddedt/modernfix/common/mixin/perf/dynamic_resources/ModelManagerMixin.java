package org.embeddedt.modernfix.common.mixin.perf.dynamic_resources;

import com.google.common.cache.LoadingCache;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Maps;
import com.llamalad7.mixinextras.sugar.Local;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.Executor;
import java.util.function.Function;
import java.util.stream.Collectors;
import net.minecraft.class_1087;
import net.minecraft.class_1088;
import net.minecraft.class_1092;
import net.minecraft.class_2248;
import net.minecraft.class_2680;
import net.minecraft.class_2689;
import net.minecraft.class_2960;
import net.minecraft.class_3298;
import net.minecraft.class_3300;
import net.minecraft.class_3518;
import net.minecraft.class_3695;
import net.minecraft.class_793;
import net.minecraft.class_9824;
import net.minecraft.class_4724.class_7774;
import net.minecraft.class_9824.class_7777;
import org.embeddedt.modernfix.ModernFix;
import org.embeddedt.modernfix.annotation.ClientOnlyMixin;
import org.embeddedt.modernfix.duck.IExtendedModelBakery;
import org.embeddedt.modernfix.duck.IExtendedModelManager;
import org.embeddedt.modernfix.util.CacheUtil;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Coerce;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(class_1092.class)
@ClientOnlyMixin
public class ModelManagerMixin implements IExtendedModelManager {
   @Shadow
   private Map<class_2960, class_1087> field_5408;
   @Unique
   private Runnable tickHandler = () -> {};

   @Inject(method = "<init>", at = @At("RETURN"))
   private void injectDummyBakedRegistry(CallbackInfo ci) {
      if (this.field_5408 == null) {
         this.field_5408 = new HashMap<>();
      }
   }

   @ModifyArg(
      method = "loadBlockModels",
      at = @At(
         value = "INVOKE",
         target = "Ljava/util/concurrent/CompletableFuture;thenCompose(Ljava/util/function/Function;)Ljava/util/concurrent/CompletableFuture;",
         ordinal = 0
      ),
      index = 0
   )
   private static Function<Map<class_2960, class_3298>, ? extends CompletionStage<Map<class_2960, class_793>>> deferBlockModelLoad(
      Function<Map<class_2960, class_3298>, ? extends CompletionStage<Map<class_2960, class_793>>> fn, @Local(ordinal = 0, argsOnly = true) class_3300 manager
   ) {
      return resourceMap -> {
         LoadingCache<class_2960, class_793> cache = CacheUtil.simpleCacheForLambda(location -> loadSingleBlockModel(manager, location), 100L);
         return CompletableFuture.completedFuture(Maps.asMap(Set.copyOf(resourceMap.keySet()), location -> (class_793)cache.getUnchecked(location)));
      };
   }

   @Redirect(
      method = "reload",
      at = @At(
         value = "INVOKE",
         target = "Lnet/minecraft/client/resources/model/ModelManager;loadBlockStates(Lnet/minecraft/server/packs/resources/ResourceManager;Ljava/util/concurrent/Executor;)Ljava/util/concurrent/CompletableFuture;"
      )
   )
   private CompletableFuture<Map<class_2960, List<class_7777>>> deferBlockStateLoad(class_3300 manager, Executor executor) {
      LoadingCache<class_2960, List<class_7777>> cache = CacheUtil.simpleCacheForLambda(location -> this.loadSingleBlockState(manager, location), 100L);
      Set<class_2960> blockStateKeys = Set.copyOf(class_9824.field_52260.method_45116(manager).keySet());
      return CompletableFuture.completedFuture(Maps.asMap(blockStateKeys, location -> (List)cache.getUnchecked(location)));
   }

   @Redirect(
      method = "loadModels",
      at = @At(
         value = "INVOKE",
         target = "Lnet/minecraft/world/level/block/state/StateDefinition;getPossibleStates()Lcom/google/common/collect/ImmutableList;"
      )
   )
   private ImmutableList<class_2680> skipCollection(class_2689<class_2248, class_2680> definition) {
      return ImmutableList.of();
   }

   private static class_793 loadSingleBlockModel(class_3300 manager, class_2960 location) {
      return manager.method_14486(location).map(resource -> {
         try {
            class_793 var2;
            try (BufferedReader reader = resource.method_43039()) {
               var2 = class_793.method_3437(reader);
            }

            return var2;
         } catch (IOException var6) {
            ModernFix.LOGGER.error("Couldn't load model", var6);
            return null;
         }
      }).orElse(null);
   }

   private List<class_7777> loadSingleBlockState(class_3300 manager, class_2960 location) {
      return manager.method_14489(location).stream().map(resource -> {
         try {
            class_7777 var2;
            try (BufferedReader reader = resource.method_43039()) {
               var2 = new class_7777(resource.method_14480(), class_3518.method_15255(reader));
            }

            return var2;
         } catch (IOException var6) {
            ModernFix.LOGGER.error("Couldn't load blockstate", var6);
            return null;
         }
      }).filter(Objects::nonNull).collect(Collectors.toList());
   }

   @Inject(method = "loadModels", at = @At("RETURN"))
   private void storeTicker(class_3695 profilerFiller, Map<class_2960, class_7774> map, class_1088 modelBakery, CallbackInfoReturnable<?> cir) {
      this.tickHandler = ((IExtendedModelBakery)modelBakery)::mfix$tick;
   }

   @Inject(method = "apply", at = @At("RETURN"))
   private void freezeBakery(@Coerce Object reloadState, class_3695 profilerFiller, CallbackInfo ci, @Local(ordinal = 0) class_1088 bakery) {
      ((IExtendedModelBakery)bakery).mfix$finishLoading();
   }

   @Override
   public void mfix$tick() {
      this.tickHandler.run();
   }
}
