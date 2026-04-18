package org.embeddedt.modernfix.common.mixin.perf.dynamic_resources;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.collect.ImmutableList;
import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import it.unimi.dsi.fastutil.Pair;
import it.unimi.dsi.fastutil.objects.Object2IntMap;
import it.unimi.dsi.fastutil.objects.Object2IntMaps;
import it.unimi.dsi.fastutil.objects.ReferenceObjectImmutablePair;
import java.util.Collections;
import java.util.Iterator;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ExecutionException;
import java.util.function.BiConsumer;
import java.util.function.Predicate;
import net.minecraft.class_1091;
import net.minecraft.class_1100;
import net.minecraft.class_2248;
import net.minecraft.class_2680;
import net.minecraft.class_2689;
import net.minecraft.class_2960;
import net.minecraft.class_324;
import net.minecraft.class_3695;
import net.minecraft.class_790;
import net.minecraft.class_7922;
import net.minecraft.class_7923;
import net.minecraft.class_9824;
import net.minecraft.class_790.class_791;
import net.minecraft.class_9824.class_7777;
import org.embeddedt.modernfix.annotation.ClientOnlyMixin;
import org.embeddedt.modernfix.duck.IBlockStateModelLoader;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(class_9824.class)
@ClientOnlyMixin
public abstract class BlockStateModelLoaderMixin implements IBlockStateModelLoader {
   @Shadow
   @Mutable
   @Final
   private Object2IntMap<class_2680> field_52272;
   private ImmutableList<class_2680> filteredStates;
   private final Cache<ReferenceObjectImmutablePair<class_7777, class_2960>, class_790> cachedBlockModelDefs = CacheBuilder.newBuilder()
      .maximumSize(100L)
      .build();
   private static final Cache<Pair<class_2689<class_2248, class_2680>, String>, Predicate<class_2680>> cachedBlockStatePredicates = CacheBuilder.newBuilder()
      .maximumSize(100L)
      .build();

   @Shadow
   protected abstract void method_61053(class_2960 var1, class_2689<class_2248, class_2680> var2);

   @Inject(method = "<init>", at = @At("RETURN"))
   private void makeModelGroupsSynchronized(
      Map map, class_3695 profilerFiller, class_1100 unbakedModel, class_324 blockColors, BiConsumer biConsumer, CallbackInfo ci
   ) {
      this.field_52272 = Object2IntMaps.synchronize(this.field_52272);
   }

   @Override
   public void loadSpecificBlock(class_1091 location) {
      Optional<class_2248> optionalBlock = class_7923.field_41175.method_17966(location.comp_2875());
      if (optionalBlock.isPresent()) {
         try {
            this.method_61053(location.comp_2875(), optionalBlock.get().method_9595());
         } finally {
            this.filteredStates = null;
         }
      }
   }

   @Redirect(method = "loadAllBlockStates", at = @At(value = "INVOKE", target = "Lnet/minecraft/core/DefaultedRegistry;iterator()Ljava/util/Iterator;"))
   private Iterator<?> skipIteratingBlocks(class_7922 instance) {
      return Collections.emptyIterator();
   }

   @Redirect(
      method = "loadBlockStateDefinitions",
      at = @At(
         value = "INVOKE",
         target = "Lnet/minecraft/world/level/block/state/StateDefinition;getPossibleStates()Lcom/google/common/collect/ImmutableList;"
      )
   )
   private ImmutableList<class_2680> getFilteredStates(class_2689<class_2248, class_2680> instance) {
      return this.filteredStates != null ? this.filteredStates : instance.method_11662();
   }

   @WrapOperation(
      method = "loadBlockStateDefinitions",
      at = @At(
         value = "INVOKE",
         target = "Lnet/minecraft/client/resources/model/BlockStateModelLoader$LoadedJson;parse(Lnet/minecraft/resources/ResourceLocation;Lnet/minecraft/client/renderer/block/model/BlockModelDefinition$Context;)Lnet/minecraft/client/renderer/block/model/BlockModelDefinition;"
      )
   )
   private class_790 avoidMultipleParses(class_7777 instance, class_2960 blockStateId, class_791 context, Operation<class_790> original) {
      try {
         return (class_790)this.cachedBlockModelDefs
            .get(ReferenceObjectImmutablePair.of(instance, blockStateId), () -> (class_790)original.call(new Object[]{instance, blockStateId, context}));
      } catch (ExecutionException var6) {
         throw new RuntimeException(var6);
      }
   }

   @WrapMethod(method = "predicate")
   private static Predicate<class_2680> memoizePredicate(
      class_2689<class_2248, class_2680> stateDefentition, String properties, Operation<Predicate<class_2680>> original
   ) {
      try {
         return (Predicate<class_2680>)cachedBlockStatePredicates.get(
            Pair.of(stateDefentition, properties), () -> (Predicate)original.call(new Object[]{stateDefentition, properties})
         );
      } catch (ExecutionException var4) {
         throw new RuntimeException(var4);
      }
   }
}
