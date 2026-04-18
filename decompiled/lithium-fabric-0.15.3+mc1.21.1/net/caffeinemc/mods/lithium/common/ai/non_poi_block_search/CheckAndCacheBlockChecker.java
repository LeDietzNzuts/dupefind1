package net.caffeinemc.mods.lithium.common.ai.non_poi_block_search;

import it.unimi.dsi.fastutil.ints.IntIterator;
import it.unimi.dsi.fastutil.longs.LongIterator;
import java.util.function.Consumer;
import java.util.function.Predicate;
import net.caffeinemc.mods.lithium.common.util.collections.FixedChunkAccessSectionBitBuffer;
import net.minecraft.class_1923;
import net.minecraft.class_2338;
import net.minecraft.class_2680;
import net.minecraft.class_2791;
import net.minecraft.class_2806;
import net.minecraft.class_2826;
import net.minecraft.class_4076;
import net.minecraft.class_4538;

public class CheckAndCacheBlockChecker {
   private final FixedChunkAccessSectionBitBuffer chunkSections2MaybeContainsMatchingBlock;
   private final class_4538 levelReader;
   public final boolean shouldChunkLoad;
   public final Predicate<class_2680> blockStatePredicate;
   private int unloadedPossibleChunkSections = 0;
   public final int minSectionY;

   public CheckAndCacheBlockChecker(
      class_2338 origin,
      int horizontalRangeInclusive,
      int verticalRangeInclusive,
      class_4538 levelReader,
      Predicate<class_2680> blockStatePredicate,
      boolean shouldChunkLoad
   ) {
      this.chunkSections2MaybeContainsMatchingBlock = new FixedChunkAccessSectionBitBuffer(origin, horizontalRangeInclusive, verticalRangeInclusive);
      this.levelReader = levelReader;
      this.shouldChunkLoad = shouldChunkLoad;
      this.blockStatePredicate = blockStatePredicate;
      this.minSectionY = levelReader.method_32891();
   }

   public void initializeChunks() {
      this.initializeChunks(null);
   }

   public void initializeChunks(Consumer<Long> chunkCollector) {
      boolean nullChunkCollector = chunkCollector == null;
      LongIterator var3 = this.chunkSections2MaybeContainsMatchingBlock.getChunkPosInRange().iterator();

      while (var3.hasNext()) {
         long chunkPos = (Long)var3.next();
         int x = class_1923.method_8325(chunkPos);
         int z = class_1923.method_8332(chunkPos);
         boolean chunkMaybeHas = false;
         class_2791 chunkAccess = this.levelReader.method_8402(x, z, class_2806.field_12803, false);
         if (chunkAccess != null) {
            this.chunkSections2MaybeContainsMatchingBlock.setChunkAccess(chunkPos, chunkAccess);
            IntIterator var12 = this.chunkSections2MaybeContainsMatchingBlock.getSectionYInRange().iterator();

            while (var12.hasNext()) {
               int y = (Integer)var12.next();
               chunkMaybeHas = this.checkChunkSection(chunkAccess, x, y, z) || chunkMaybeHas;
            }
         } else if (this.shouldChunkLoad) {
            for (IntIterator var10 = this.chunkSections2MaybeContainsMatchingBlock.getSectionYInRange().iterator();
               var10.hasNext();
               this.unloadedPossibleChunkSections++
            ) {
               int y = (Integer)var10.next();
               this.chunkSections2MaybeContainsMatchingBlock
                  .setChunkSectionStatus(class_4076.method_18685(x, y, z), !this.levelReader.method_31601(class_4076.method_18688(y)));
            }

            chunkMaybeHas = true;
         }

         if (!nullChunkCollector && chunkMaybeHas) {
            chunkCollector.accept(chunkPos);
         }
      }
   }

   public int getChunkSize() {
      return this.chunkSections2MaybeContainsMatchingBlock.numChunks;
   }

   public boolean hasUnloadedPossibleChunks() {
      return this.unloadedPossibleChunkSections > 0;
   }

   private boolean checkChunkSection(class_2791 chunkAccess, int chunkX, int chunkY, int chunkZ) {
      int chunkSectionYIndex = chunkY - this.minSectionY;
      class_2826[] chunkSections = chunkAccess.method_12006();
      if (chunkSectionYIndex >= 0 && chunkSectionYIndex < chunkSections.length && chunkSections[chunkSectionYIndex].method_19523(this.blockStatePredicate)) {
         this.chunkSections2MaybeContainsMatchingBlock.setChunkSectionStatus(class_4076.method_18685(chunkX, chunkY, chunkZ), true);
         return true;
      } else {
         return false;
      }
   }

   public boolean checkCachedSection(int chunkX, int chunkY, int chunkZ) {
      return this.chunkSections2MaybeContainsMatchingBlock.getChunkSectionBit(chunkX, chunkY, chunkZ);
   }

   public class_2791 getCachedChunkAccess(long chunkPos) {
      return this.chunkSections2MaybeContainsMatchingBlock.getChunkAccess(chunkPos);
   }

   public class_2791 getCachedChunkAccess(class_2338 blockPos) {
      return this.chunkSections2MaybeContainsMatchingBlock.getChunkAccess(blockPos);
   }

   public boolean shouldStop() {
      return this.chunkSections2MaybeContainsMatchingBlock.hasNoTrueChunkSections();
   }

   public boolean checkPosition(class_2338 blockPos) {
      if (!this.chunkSections2MaybeContainsMatchingBlock.getChunkSectionBit(blockPos)) {
         return false;
      } else {
         class_2791 chunkAccess = this.chunkSections2MaybeContainsMatchingBlock.getChunkAccess(blockPos);
         if (chunkAccess == null) {
            if (!this.shouldChunkLoad) {
               return false;
            }

            int chunkX = class_4076.method_18675(blockPos.method_10263());
            int chunkY = class_4076.method_18675(blockPos.method_10264());
            int chunkZ = class_4076.method_18675(blockPos.method_10260());
            chunkAccess = this.levelReader.method_8402(chunkX, chunkZ, class_2806.field_12803, true);

            assert chunkAccess != null;

            this.chunkSections2MaybeContainsMatchingBlock.setChunkAccess(blockPos, chunkAccess);
            if (!this.checkChunkSection(chunkAccess, chunkX, chunkY, chunkZ)) {
               this.unloadedPossibleChunkSections--;
               return false;
            }
         }

         return this.blockStatePredicate.test(chunkAccess.method_8320(blockPos));
      }
   }
}
