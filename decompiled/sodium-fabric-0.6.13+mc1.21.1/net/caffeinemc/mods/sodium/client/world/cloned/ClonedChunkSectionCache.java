package net.caffeinemc.mods.sodium.client.world.cloned;

import it.unimi.dsi.fastutil.longs.Long2ReferenceLinkedOpenHashMap;
import java.util.concurrent.TimeUnit;
import net.minecraft.class_1937;
import net.minecraft.class_2818;
import net.minecraft.class_2826;
import net.minecraft.class_4076;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class ClonedChunkSectionCache {
   private static final int MAX_CACHE_SIZE = 512;
   private static final long MAX_CACHE_DURATION = TimeUnit.SECONDS.toNanos(5L);
   private final class_1937 level;
   private final Long2ReferenceLinkedOpenHashMap<ClonedChunkSection> positionToEntry = new Long2ReferenceLinkedOpenHashMap();
   private long time;

   public ClonedChunkSectionCache(class_1937 level) {
      this.level = level;
      this.time = getMonotonicTimeSource();
   }

   public void cleanup() {
      this.time = getMonotonicTimeSource();
      this.positionToEntry.values().removeIf(entry -> this.time > entry.getLastUsedTimestamp() + MAX_CACHE_DURATION);
   }

   @Nullable
   public ClonedChunkSection acquire(int x, int y, int z) {
      long pos = class_4076.method_18685(x, y, z);
      ClonedChunkSection section = (ClonedChunkSection)this.positionToEntry.getAndMoveToLast(pos);
      if (section == null) {
         section = this.clone(x, y, z);

         while (this.positionToEntry.size() >= 512) {
            this.positionToEntry.removeFirst();
         }

         this.positionToEntry.putAndMoveToLast(pos, section);
      }

      section.setLastUsedTimestamp(this.time);
      return section;
   }

   @NotNull
   private ClonedChunkSection clone(int x, int y, int z) {
      class_2818 chunk = this.level.method_8497(x, z);
      if (chunk == null) {
         throw new RuntimeException("Chunk is not loaded at: " + class_4076.method_18685(x, y, z));
      } else {
         class_2826 section = null;
         if (!this.level.method_31601(class_4076.method_18688(y))) {
            section = chunk.method_12006()[this.level.method_31603(y)];
         }

         return new ClonedChunkSection(this.level, chunk, section, class_4076.method_18676(x, y, z));
      }
   }

   public void invalidate(int x, int y, int z) {
      this.positionToEntry.remove(class_4076.method_18685(x, y, z));
   }

   private static long getMonotonicTimeSource() {
      return System.nanoTime();
   }
}
