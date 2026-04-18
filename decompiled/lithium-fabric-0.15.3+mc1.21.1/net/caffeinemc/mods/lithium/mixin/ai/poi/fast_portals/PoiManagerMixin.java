package net.caffeinemc.mods.lithium.mixin.ai.poi.fast_portals;

import com.mojang.serialization.Codec;
import it.unimi.dsi.fastutil.longs.LongOpenHashSet;
import it.unimi.dsi.fastutil.longs.LongSet;
import java.util.Optional;
import java.util.function.Function;
import net.minecraft.class_1923;
import net.minecraft.class_2338;
import net.minecraft.class_2806;
import net.minecraft.class_4076;
import net.minecraft.class_4153;
import net.minecraft.class_4157;
import net.minecraft.class_4180;
import net.minecraft.class_4538;
import net.minecraft.class_5455;
import net.minecraft.class_5539;
import net.minecraft.class_9172;
import net.minecraft.class_9820;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;

@Mixin(class_4153.class)
public abstract class PoiManagerMixin extends class_4180<class_4157> {
   @Shadow
   @Final
   private LongSet field_20688;
   @Unique
   private final LongSet preloadedCenterChunks = new LongOpenHashSet();
   @Unique
   private int preloadRadius = 0;

   public PoiManagerMixin(
      class_9172 storageAccess,
      Function<Runnable, Codec<class_4157>> codecFactory,
      Function<Runnable, class_4157> factory,
      class_5455 registryManager,
      class_9820 errorHandler,
      class_5539 world
   ) {
      super(storageAccess, codecFactory, factory, registryManager, errorHandler, world);
   }

   @Overwrite
   public void method_22439(class_4538 worldView, class_2338 pos, int radius) {
      if (this.preloadRadius != radius) {
         this.preloadedCenterChunks.clear();
         this.preloadRadius = radius;
      }

      long chunkPos = class_1923.method_37232(pos);
      if (!this.preloadedCenterChunks.contains(chunkPos)) {
         int chunkX = class_4076.method_18675(pos.method_10263());
         int chunkZ = class_4076.method_18675(pos.method_10260());
         int chunkRadius = Math.floorDiv(radius, 16);
         int maxHeight = this.field_27240.method_31597() - 1;
         int minHeight = this.field_27240.method_32891();
         int x = chunkX - chunkRadius;

         for (int xMax = chunkX + chunkRadius; x <= xMax; x++) {
            int z = chunkZ - chunkRadius;

            for (int zMax = chunkZ + chunkRadius; z <= zMax; z++) {
               this.lithium$preloadChunkIfAnySubChunkContainsPOI(worldView, x, z, minHeight, maxHeight);
            }
         }

         this.preloadedCenterChunks.add(chunkPos);
      }
   }

   @Unique
   private void lithium$preloadChunkIfAnySubChunkContainsPOI(class_4538 worldView, int x, int z, int minSubChunk, int maxSubChunk) {
      class_1923 chunkPos = new class_1923(x, z);
      long longChunkPos = chunkPos.method_8324();
      if (!this.field_20688.contains(longChunkPos)) {
         for (int y = minSubChunk; y <= maxSubChunk; y++) {
            Optional<class_4157> section = this.method_19294(class_4076.method_18685(x, y, z));
            if (section.isPresent()) {
               boolean result = section.get().method_22444();
               if (result) {
                  if (this.field_20688.add(longChunkPos)) {
                     worldView.method_22342(x, z, class_2806.field_12798);
                  }
                  break;
               }
            }
         }
      }
   }
}
