package org.embeddedt.modernfix.common.mixin.perf.cache_strongholds;

import java.lang.ref.WeakReference;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import net.minecraft.class_156;
import net.minecraft.class_1923;
import net.minecraft.class_3218;
import net.minecraft.class_6871;
import net.minecraft.class_6880;
import net.minecraft.class_7059;
import net.minecraft.class_7869;
import org.embeddedt.modernfix.ModernFix;
import org.embeddedt.modernfix.duck.IChunkGenerator;
import org.embeddedt.modernfix.duck.IServerLevel;
import org.embeddedt.modernfix.world.StrongholdLocationCache;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(class_7869.class)
public class ChunkGeneratorMixin implements IChunkGenerator {
   private WeakReference<class_3218> mfix$serverLevel;

   @Override
   public void mfix$setAssociatedServerLevel(class_3218 level) {
      this.mfix$serverLevel = new WeakReference<>(level);
   }

   @Inject(method = "generateRingPositions", at = @At("HEAD"), cancellable = true)
   private void useCachedDataIfAvailable(
      class_6880<class_7059> structureSet, class_6871 placement, CallbackInfoReturnable<CompletableFuture<List<class_1923>>> cir
   ) {
      if (placement.method_41630() != 0) {
         class_3218 level = this.searchLevel();
         if (level != null) {
            StrongholdLocationCache cache = ((IServerLevel)level).mfix$getStrongholdCache();
            List<class_1923> positions = cache.getChunkPosList();
            if (!positions.isEmpty()) {
               ModernFix.LOGGER.debug("Loaded stronghold cache for dimension {} with {} positions", level.method_27983().method_29177(), positions.size());
               cir.setReturnValue(CompletableFuture.completedFuture(positions));
            }
         }
      }
   }

   private class_3218 searchLevel() {
      return this.mfix$serverLevel != null ? this.mfix$serverLevel.get() : null;
   }

   @Inject(method = "generateRingPositions", at = @At("RETURN"), cancellable = true)
   private void saveCachedData(class_6880<class_7059> structureSet, class_6871 placement, CallbackInfoReturnable<CompletableFuture<List<class_1923>>> cir) {
      cir.setReturnValue(((CompletableFuture)cir.getReturnValue()).thenApplyAsync(list -> {
         if (list.size() == 0) {
            return (List)list;
         } else {
            class_3218 level = this.searchLevel();
            if (level != null) {
               StrongholdLocationCache cache = ((IServerLevel)level).mfix$getStrongholdCache();
               cache.setChunkPosList((List<class_1923>)list);
               ModernFix.LOGGER.debug("Saved stronghold cache for dimension {}", level.method_27983().method_29177());
            }

            return (List)list;
         }
      }, class_156.method_18349()));
   }
}
