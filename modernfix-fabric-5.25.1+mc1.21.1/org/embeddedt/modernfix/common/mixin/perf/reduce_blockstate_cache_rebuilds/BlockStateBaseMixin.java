package org.embeddedt.modernfix.common.mixin.perf.reduce_blockstate_cache_rebuilds;

import com.mojang.serialization.MapCodec;
import it.unimi.dsi.fastutil.objects.Reference2ObjectArrayMap;
import net.minecraft.class_2248;
import net.minecraft.class_2680;
import net.minecraft.class_2688;
import net.minecraft.class_2769;
import net.minecraft.class_3610;
import net.minecraft.class_3612;
import net.minecraft.class_4970.class_4971;
import net.minecraft.class_4970.class_4971.class_3752;
import org.embeddedt.modernfix.duck.IBlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(class_4971.class)
public abstract class BlockStateBaseMixin extends class_2688<class_2248, class_2680> implements IBlockState {
   private static final class_3610 MFIX$VANILLA_DEFAULT_FLUID = class_3612.field_15906.method_15785();
   @Shadow
   private class_3752 field_23166;
   @Shadow
   private class_3610 field_40339;
   @Shadow
   private boolean field_40340;
   @Shadow
   @Deprecated
   private boolean field_44624;
   private volatile boolean cacheInvalid = false;
   private static boolean buildingCache = false;

   protected BlockStateBaseMixin(class_2248 object, Reference2ObjectArrayMap<class_2769<?>, Comparable<?>> immutableMap, MapCodec<class_2680> mapCodec) {
      super(object, immutableMap, mapCodec);
   }

   @Shadow
   public abstract void method_26200();

   @Shadow
   protected abstract class_2680 method_26233();

   @Override
   public void clearCache() {
      this.cacheInvalid = true;
   }

   @Override
   public boolean isCacheInvalid() {
      return this.cacheInvalid;
   }

   private void mfix$generateCache() {
      if (this.cacheInvalid) {
         synchronized (class_4971.class) {
            if (this.cacheInvalid && !buildingCache) {
               buildingCache = true;

               try {
                  this.method_26200();
                  this.cacheInvalid = false;
               } finally {
                  buildingCache = false;
               }
            }
         }
      }
   }

   @Redirect(
      method = "*",
      at = @At(
         value = "FIELD",
         opcode = 180,
         target = "Lnet/minecraft/world/level/block/state/BlockBehaviour$BlockStateBase;cache:Lnet/minecraft/world/level/block/state/BlockBehaviour$BlockStateBase$Cache;",
         ordinal = 0
      )
   )
   private class_3752 dynamicCacheGen(class_4971 base) {
      this.mfix$generateCache();
      return this.field_23166;
   }

   @Redirect(
      method = "*",
      at = @At(
         value = "FIELD",
         opcode = 180,
         target = "Lnet/minecraft/world/level/block/state/BlockBehaviour$BlockStateBase;fluidState:Lnet/minecraft/world/level/material/FluidState;",
         ordinal = 0
      ),
      require = 0
   )
   private class_3610 genCacheBeforeGettingFluid(class_4971 base) {
      if (this.cacheInvalid && this.field_40339 == MFIX$VANILLA_DEFAULT_FLUID) {
         synchronized (class_4971.class) {
            if (!buildingCache) {
               buildingCache = true;

               try {
                  this.field_40339 = ((BlockBehaviourInvoker)this.field_24739).invokeGetFluidState(this.method_26233());
               } finally {
                  buildingCache = false;
               }
            }
         }
      }

      return this.field_40339;
   }

   @Redirect(
      method = "*",
      at = @At(value = "FIELD", opcode = 180, target = "Lnet/minecraft/world/level/block/state/BlockBehaviour$BlockStateBase;isRandomlyTicking:Z", ordinal = 0)
   )
   private boolean genCacheBeforeGettingTicking(class_4971 base) {
      return this.cacheInvalid ? ((BlockBehaviourInvoker)this.field_24739).invokeIsRandomlyTicking(this.method_26233()) : this.field_40340;
   }

   @Redirect(
      method = "*",
      at = @At(value = "FIELD", opcode = 180, target = "Lnet/minecraft/world/level/block/state/BlockBehaviour$BlockStateBase;legacySolid:Z", ordinal = 0)
   )
   private boolean genCacheBeforeCheckingSolid(class_4971 base) {
      this.mfix$generateCache();
      return this.field_44624;
   }
}
