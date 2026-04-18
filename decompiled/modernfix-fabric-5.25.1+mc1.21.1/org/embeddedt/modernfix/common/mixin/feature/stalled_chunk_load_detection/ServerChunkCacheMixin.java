package org.embeddedt.modernfix.common.mixin.feature.stalled_chunk_load_detection;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import net.minecraft.class_1923;
import net.minecraft.class_1959;
import net.minecraft.class_1972;
import net.minecraft.class_2791;
import net.minecraft.class_2806;
import net.minecraft.class_2812;
import net.minecraft.class_3215;
import net.minecraft.class_3218;
import net.minecraft.class_6880;
import net.minecraft.class_7924;
import net.minecraft.class_9259;
import net.minecraft.class_3215.class_4212;
import org.embeddedt.modernfix.ModernFix;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = class_3215.class, priority = 1100)
public abstract class ServerChunkCacheMixin {
   @Shadow
   @Final
   private Thread field_17253;
   @Shadow
   @Final
   public class_3218 field_13945;
   @Shadow
   @Final
   private class_4212 field_18809;
   private final boolean debugDeadServerAccess = Boolean.getBoolean("modernfix.debugBadChunkloading");

   @Shadow
   protected abstract CompletableFuture<class_9259<class_2791>> method_14134(int var1, int var2, class_2806 var3, boolean var4);

   @Inject(method = "getChunk", at = @At("HEAD"), cancellable = true)
   private void bailIfServerDead(int chunkX, int chunkZ, class_2806 requiredStatus, boolean load, CallbackInfoReturnable<class_2791> cir) {
      if (!this.field_13945.method_8503().method_3806() && !this.field_17253.isAlive()) {
         ModernFix.LOGGER.fatal("A mod is accessing chunks from a stopped server (this will also cause memory leaks)");
         if (this.debugDeadServerAccess) {
            new Exception().printStackTrace();
         }

         class_6880<class_1959> plains = this.field_13945.method_30349().method_30530(class_7924.field_41236).method_40290(class_1972.field_9451);
         cir.setReturnValue(new class_2812(this.field_13945, new class_1923(chunkX, chunkZ), plains));
      } else if (Thread.currentThread() != this.field_17253) {
         CompletableFuture<class_9259<class_2791>> future = CompletableFuture.<CompletableFuture<class_9259<class_2791>>>supplyAsync(
               () -> this.method_14134(chunkX, chunkZ, requiredStatus, false), this.field_18809
            )
            .join();
         if (!future.isDone()) {
            class_9259<class_2791> resultingChunk = null;

            try {
               resultingChunk = future.get(500L, TimeUnit.MILLISECONDS);
            } catch (ExecutionException | TimeoutException | InterruptedException var10) {
            }

            if (resultingChunk != null && resultingChunk.method_57122()) {
               cir.setReturnValue((class_2791)resultingChunk.method_57130(null));
               return;
            }

            if (this.debugDeadServerAccess) {
               ModernFix.LOGGER.warn("Async loading of a chunk was requested, this might not be desirable", new Exception());
            }

            try {
               resultingChunk = future.get(10L, TimeUnit.SECONDS);
               if (resultingChunk.method_57122()) {
                  cir.setReturnValue((class_2791)resultingChunk.method_57130(null));
                  return;
               }
            } catch (ExecutionException | TimeoutException | InterruptedException var9) {
               ModernFix.LOGGER.error("Async chunk load took way too long, this needs to be reported to the appropriate mod.", var9);
            }
         }
      }
   }
}
