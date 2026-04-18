package net.caffeinemc.mods.lithium.common.util.collections;

import it.unimi.dsi.fastutil.ints.IntIterable;
import it.unimi.dsi.fastutil.ints.IntIterator;
import it.unimi.dsi.fastutil.longs.LongIterable;
import it.unimi.dsi.fastutil.longs.LongIterator;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.Collections;
import net.minecraft.class_1923;
import net.minecraft.class_2338;
import net.minecraft.class_2791;
import net.minecraft.class_4076;
import org.jetbrains.annotations.NotNull;

public class FixedChunkAccessSectionBitBuffer {
   public final int xMin;
   public final int yMin;
   public final int zMin;
   public final int xLength;
   public final int yLength;
   public final int zLength;
   public final int numChunks;
   public final int numSections;
   public final BitSet chunkSectionBits;
   public final ArrayList<class_2791> chunkAccesses;

   public FixedChunkAccessSectionBitBuffer(int x0, int x1, int y0, int y1, int z0, int z1) {
      this.xMin = Math.min(x0, x1);
      this.yMin = Math.min(y0, y1);
      this.zMin = Math.min(z0, z1);
      this.xLength = Math.max(x0, x1) - this.xMin + 1;
      this.yLength = Math.max(y0, y1) - this.yMin + 1;
      this.zLength = Math.max(z0, z1) - this.zMin + 1;
      this.numChunks = this.xLength * this.zLength;
      this.numSections = this.yLength * this.xLength * this.zLength;
      this.chunkSectionBits = new BitSet(this.numSections);
      this.chunkAccesses = new ArrayList<>(Collections.nCopies(this.xLength * this.zLength, null));
   }

   public FixedChunkAccessSectionBitBuffer(class_2338 center, int horizontalRangeInclusive, int verticalRangeInclusive) {
      this(
         class_4076.method_18675(center.method_10263() - horizontalRangeInclusive),
         class_4076.method_18675(center.method_10263() + horizontalRangeInclusive),
         class_4076.method_18675(center.method_10264() - verticalRangeInclusive),
         class_4076.method_18675(center.method_10264() + verticalRangeInclusive),
         class_4076.method_18675(center.method_10260() - horizontalRangeInclusive),
         class_4076.method_18675(center.method_10260() + horizontalRangeInclusive)
      );
   }

   public int getSectionIndex(int x, int y, int z) {
      int dx = x - this.xMin;
      int dy = y - this.yMin;
      int dz = z - this.zMin;
      return (dx * this.zLength + dz) * this.yLength + dy;
   }

   public int getSectionIndex(long sectionPos) {
      return this.getSectionIndex(class_4076.method_18686(sectionPos), class_4076.method_18689(sectionPos), class_4076.method_18690(sectionPos));
   }

   public boolean getChunkSectionBit(class_2338 blockPos) {
      return this.getChunkSectionBit(
         class_4076.method_18675(blockPos.method_10263()), class_4076.method_18675(blockPos.method_10264()), class_4076.method_18675(blockPos.method_10260())
      );
   }

   public boolean getChunkSectionBit(int chunkX, int chunkY, int chunkZ) {
      return this.chunkSectionBits.get(this.getSectionIndex(chunkX, chunkY, chunkZ));
   }

   public void setChunkSectionStatus(long sectionPos, boolean value) {
      this.chunkSectionBits.set(this.getSectionIndex(sectionPos), value);
   }

   public int getChunkIndex(int x, int z) {
      int dx = x - this.xMin;
      int dz = z - this.zMin;
      return dx * this.zLength + dz;
   }

   public int getChunkIndex(long chunkPos) {
      return this.getChunkIndex(class_1923.method_8325(chunkPos), class_1923.method_8332(chunkPos));
   }

   public class_2791 getChunkAccess(long chunkPos) {
      return this.chunkAccesses.get(this.getChunkIndex(chunkPos));
   }

   public class_2791 getChunkAccess(class_2338 blockPos) {
      return this.getChunkAccess(class_1923.method_37232(blockPos));
   }

   public void setChunkAccess(long chunkPos, class_2791 chunkAccess) {
      this.chunkAccesses.set(this.getChunkIndex(chunkPos), chunkAccess);
   }

   public void setChunkAccess(class_2338 blockPos, class_2791 chunkAccess) {
      this.setChunkAccess(class_1923.method_37232(blockPos), chunkAccess);
   }

   public boolean hasNoTrueChunkSections() {
      return this.chunkSectionBits.nextSetBit(0) == -1;
   }

   public LongIterable getChunkPosInRange() {
      return new LongIterable() {
         @NotNull
         public LongIterator iterator() {
            return FixedChunkAccessSectionBitBuffer.this.getChunkPosInRangeIterator();
         }
      };
   }

   public LongIterator getChunkPosInRangeIterator() {
      final int xMin = this.xMin;
      final int xMax = this.xMin + this.xLength - 1;
      final int zMin = this.zMin;
      final int zMax = this.zMin + this.zLength - 1;
      return new LongIterator() {
         int x = xMin;
         int z = zMin;

         public long nextLong() {
            long result = class_1923.method_8331(this.x, this.z);
            if (this.z < zMax) {
               this.z++;
            } else {
               this.z = zMin;
               this.x++;
            }

            return result;
         }

         public boolean hasNext() {
            return this.x <= xMax;
         }
      };
   }

   public IntIterable getSectionYInRange() {
      return new IntIterable() {
         @NotNull
         public IntIterator iterator() {
            return FixedChunkAccessSectionBitBuffer.this.getSectionYInRangeIterator();
         }
      };
   }

   public IntIterator getSectionYInRangeIterator() {
      final int yMin = this.yMin;
      final int yLimit = yMin + this.yLength;
      return new IntIterator() {
         int y = yMin;

         public int nextInt() {
            return this.y++;
         }

         public boolean hasNext() {
            return this.y < yLimit;
         }
      };
   }
}
