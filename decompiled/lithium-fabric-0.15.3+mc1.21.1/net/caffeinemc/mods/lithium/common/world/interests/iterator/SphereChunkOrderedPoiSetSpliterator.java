package net.caffeinemc.mods.lithium.common.world.interests.iterator;

import java.util.Spliterators.AbstractSpliterator;
import java.util.function.Consumer;
import java.util.stream.Stream;
import net.caffeinemc.mods.lithium.common.util.Distances;
import net.caffeinemc.mods.lithium.common.world.interests.RegionBasedStorageSectionExtended;
import net.minecraft.class_2338;
import net.minecraft.class_4157;

public class SphereChunkOrderedPoiSetSpliterator extends AbstractSpliterator<Stream<class_4157>> {
   private final int limit;
   private final int minChunkZ;
   private final class_2338 origin;
   private final double radiusSq;
   private final RegionBasedStorageSectionExtended<class_4157> storage;
   private final int maxChunkZ;
   int chunkX;
   int chunkZ;
   int iterated;

   public SphereChunkOrderedPoiSetSpliterator(int radius, class_2338 origin, RegionBasedStorageSectionExtended<class_4157> storage) {
      super(
         (long)((origin.method_10263() + radius + 1 >> 4) - (origin.method_10263() - radius - 1 >> 4) + 1)
            * ((origin.method_10260() + radius + 1 >> 4) - (origin.method_10260() - radius - 1 >> 4) + 1),
         16
      );
      this.origin = origin;
      this.radiusSq = radius * radius;
      this.storage = storage;
      int minChunkX = origin.method_10263() - radius - 1 >> 4;
      int maxChunkX = origin.method_10263() + radius + 1 >> 4;
      this.minChunkZ = origin.method_10260() - radius - 1 >> 4;
      this.maxChunkZ = origin.method_10260() + radius + 1 >> 4;
      this.limit = (maxChunkX - minChunkX + 1) * (this.maxChunkZ - this.minChunkZ + 1);
      this.chunkX = minChunkX;
      this.chunkZ = this.minChunkZ;
      this.iterated = 0;
   }

   @Override
   public boolean tryAdvance(Consumer<? super Stream<class_4157>> action) {
      while (this.iterated < this.limit) {
         this.iterated++;
         boolean progress = false;
         if (Distances.getMinChunkToBlockDistanceL2Sq(this.origin, this.chunkX, this.chunkZ) <= this.radiusSq) {
            action.accept(this.storage.lithium$getWithinChunkColumn(this.chunkX, this.chunkZ));
            progress = true;
         }

         this.chunkZ++;
         if (this.chunkZ > this.maxChunkZ) {
            this.chunkX++;
            this.chunkZ = this.minChunkZ;
         }

         if (progress) {
            return true;
         }
      }

      return false;
   }
}
