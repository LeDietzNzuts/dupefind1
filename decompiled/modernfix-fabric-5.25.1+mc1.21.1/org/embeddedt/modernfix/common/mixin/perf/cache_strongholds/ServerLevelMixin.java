package org.embeddedt.modernfix.common.mixin.perf.cache_strongholds;

import java.util.function.Supplier;
import net.minecraft.class_1937;
import net.minecraft.class_26;
import net.minecraft.class_2874;
import net.minecraft.class_3215;
import net.minecraft.class_3218;
import net.minecraft.class_3695;
import net.minecraft.class_5269;
import net.minecraft.class_5321;
import net.minecraft.class_5455;
import net.minecraft.class_6880;
import net.minecraft.class_7869;
import org.embeddedt.modernfix.duck.IChunkGenerator;
import org.embeddedt.modernfix.duck.IServerLevel;
import org.embeddedt.modernfix.world.StrongholdLocationCache;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(class_3218.class)
public abstract class ServerLevelMixin extends class_1937 implements IServerLevel {
   @Shadow
   @Final
   private class_3215 field_24624;
   private StrongholdLocationCache mfix$strongholdCache;

   protected ServerLevelMixin(
      class_5269 arg,
      class_5321<class_1937> arg2,
      class_5455 arg3,
      class_6880<class_2874> arg4,
      Supplier<class_3695> supplier,
      boolean bl,
      boolean bl2,
      long l,
      int i
   ) {
      super(arg, arg2, arg3, arg4, supplier, bl, bl2, l, i);
   }

   @Shadow
   public abstract class_26 method_17983();

   @Redirect(
      method = "<init>",
      at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/chunk/ChunkGeneratorStructureState;ensureStructuresGenerated()V")
   )
   private void hookStrongholdCache(class_7869 generator) {
      ((IChunkGenerator)generator).mfix$setAssociatedServerLevel((class_3218)this);
   }

   @Inject(method = "<init>", at = @At("TAIL"))
   private void ensureGeneration(CallbackInfo ci) {
      this.mfix$strongholdCache = (StrongholdLocationCache)this.method_17983()
         .method_17924(StrongholdLocationCache.factory((class_3218)this), StrongholdLocationCache.getFileId(this.method_40134()));
      this.field_24624.method_46642().method_46712();
   }

   @Override
   public StrongholdLocationCache mfix$getStrongholdCache() {
      return this.mfix$strongholdCache;
   }
}
