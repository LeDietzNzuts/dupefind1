package org.embeddedt.modernfix.common.mixin.perf.resourcepacks;

import java.nio.file.Path;
import net.minecraft.class_3259;
import net.minecraft.class_9224;
import org.embeddedt.modernfix.resources.ICachingResourcePack;
import org.embeddedt.modernfix.resources.PackResourcesCacheEngine;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = class_3259.class, priority = 1100)
public abstract class PathPackResourcesMixin implements ICachingResourcePack {
   @Shadow
   @Final
   private Path field_40001;
   private PackResourcesCacheEngine cacheEngine;

   @Inject(method = "<init>", at = @At("TAIL"))
   private void cacheResources(class_9224 location, Path root, CallbackInfo ci) {
      this.invalidateCache();
   }

   private PackResourcesCacheEngine generateResourceCache() {
      synchronized (this) {
         PackResourcesCacheEngine engine = this.cacheEngine;
         if (engine != null) {
            return engine;
         } else {
            this.cacheEngine = engine = new PackResourcesCacheEngine(type -> this.field_40001.resolve(type.method_14413()));
            return engine;
         }
      }
   }

   @Override
   public void invalidateCache() {
      this.cacheEngine = null;
   }
}
