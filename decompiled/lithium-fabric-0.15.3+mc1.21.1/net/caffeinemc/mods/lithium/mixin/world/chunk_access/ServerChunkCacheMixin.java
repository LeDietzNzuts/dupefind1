package net.caffeinemc.mods.lithium.mixin.world.chunk_access;

import java.util.Arrays;
import java.util.concurrent.CompletableFuture;
import java.util.function.BooleanSupplier;
import net.caffeinemc.mods.lithium.common.world.ChunkLoadTricks;
import net.caffeinemc.mods.lithium.common.world.chunk.ChunkHolderExtended;
import net.minecraft.class_156;
import net.minecraft.class_1923;
import net.minecraft.class_2791;
import net.minecraft.class_2806;
import net.minecraft.class_3193;
import net.minecraft.class_3204;
import net.minecraft.class_3215;
import net.minecraft.class_3230;
import net.minecraft.class_3898;
import net.minecraft.class_8563;
import net.minecraft.class_9259;
import net.minecraft.class_3215.class_4212;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(class_3215.class)
public abstract class ServerChunkCacheMixin {
   @Shadow
   @Final
   private class_4212 field_18809;
   @Shadow
   @Final
   private class_3204 field_17252;
   @Shadow
   @Final
   public class_3898 field_17254;
   @Shadow
   @Final
   Thread field_17253;
   private long time;
   private final long[] cacheKeys = new long[4];
   private final class_2791[] cacheChunks = new class_2791[4];

   @Shadow
   protected abstract class_3193 method_14131(long var1);

   @Shadow
   protected abstract boolean method_18752(class_3193 var1, int var2);

   @Shadow
   public abstract void method_12127(BooleanSupplier var1, boolean var2);

   @Shadow
   abstract boolean method_16155();

   @Inject(method = "method_12127(Ljava/util/function/BooleanSupplier;Z)V", at = @At("HEAD"))
   private void preTick(BooleanSupplier shouldKeepTicking, boolean tickChunks, CallbackInfo ci) {
      this.time++;
   }

   @Overwrite
   public class_2791 method_12121(int x, int z, class_2806 status, boolean create) {
      if (Thread.currentThread() != this.field_17253) {
         return this.getChunkOffThread(x, z, status, create);
      } else {
         long[] cacheKeys = this.cacheKeys;
         long key = createCacheKey(x, z, status);

         for (int i = 0; i < 4; i++) {
            if (key == cacheKeys[i]) {
               class_2791 chunk = this.cacheChunks[i];
               if (chunk != null || !create) {
                  return chunk;
               }
            }
         }

         class_2791 chunk = this.getChunkBlocking(x, z, status, create);
         if (chunk != null) {
            this.addToCache(key, chunk);
         } else if (create) {
            throw new IllegalStateException("Chunk not there when requested");
         }

         return chunk;
      }
   }

   @Unique
   private class_2791 getChunkOffThread(int x, int z, class_2806 status, boolean create) {
      return CompletableFuture.<class_2791>supplyAsync(() -> this.method_12121(x, z, status, create), this.field_18809).join();
   }

   @Unique
   private class_2791 getChunkBlocking(int x, int z, class_2806 leastStatus, boolean create) {
      long key = class_1923.method_8331(x, z);
      int level = class_8563.method_51829(leastStatus);
      class_3193 holder = this.method_14131(key);
      class_2791 chunkAccess = ChunkLoadTricks.tryRetrieveCurrentlyLoading(holder);
      if (chunkAccess != null) {
         return chunkAccess;
      } else {
         if (this.method_18752(holder, level)) {
            if (!create) {
               return null;
            }

            this.createChunkLoadTicket(x, z, level);
            this.method_16155();
            holder = this.method_14131(key);
            if (this.method_18752(holder, level)) {
               throw (IllegalStateException)class_156.method_22320(new IllegalStateException("No chunk holder after ticket has been added"));
            }
         } else if (create && ((ChunkHolderExtended)holder).lithium$updateLastAccessTime(this.time)) {
            this.createChunkLoadTicket(x, z, level);
         }

         if (!((GenerationChunkHolderAccessor)holder).invokeCannotBeLoaded(leastStatus)) {
            CompletableFuture<class_9259<class_2791>> directlyAccessedFuture = ((GenerationChunkHolderAccessor)holder)
               .lithium$getChunkFuturesByStatus()
               .get(leastStatus.method_16559());
            if (directlyAccessedFuture != null && directlyAccessedFuture.isDone()) {
               class_2791 chunk = (class_2791)directlyAccessedFuture.join().method_57130(null);
               if (chunk != null) {
                  return chunk;
               }
            }
         }

         CompletableFuture<class_9259<class_2791>> loadFuture = holder.method_60458(leastStatus, this.field_17254);
         if (!loadFuture.isDone()) {
            this.field_18809.method_18857(loadFuture::isDone);
         }

         return (class_2791)loadFuture.join().method_57130(null);
      }
   }

   private void createChunkLoadTicket(int x, int z, int level) {
      class_1923 chunkPos = new class_1923(x, z);
      this.field_17252.method_17290(class_3230.field_14032, chunkPos, level, chunkPos);
   }

   private static long createCacheKey(int chunkX, int chunkZ, class_2806 status) {
      return chunkX & 268435455L | (chunkZ & 268435455L) << 28 | (long)status.method_16559() << 56;
   }

   private void addToCache(long key, class_2791 chunk) {
      for (int i = 3; i > 0; i--) {
         this.cacheKeys[i] = this.cacheKeys[i - 1];
         this.cacheChunks[i] = this.cacheChunks[i - 1];
      }

      this.cacheKeys[0] = key;
      this.cacheChunks[0] = chunk;
   }

   @Inject(method = "method_20587()V", at = @At("HEAD"))
   private void onCachesCleared(CallbackInfo ci) {
      Arrays.fill(this.cacheKeys, Long.MAX_VALUE);
      Arrays.fill(this.cacheChunks, null);
   }
}
