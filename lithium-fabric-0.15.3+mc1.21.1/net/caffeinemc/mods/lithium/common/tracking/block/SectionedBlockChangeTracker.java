package net.caffeinemc.mods.lithium.common.tracking.block;

import java.util.ArrayList;
import java.util.Objects;
import net.caffeinemc.mods.lithium.common.block.BlockListeningSection;
import net.caffeinemc.mods.lithium.common.block.ListeningBlockStatePredicate;
import net.caffeinemc.mods.lithium.common.util.Pos;
import net.caffeinemc.mods.lithium.common.util.deduplication.LithiumInterner;
import net.caffeinemc.mods.lithium.common.util.tuples.WorldSectionBox;
import net.caffeinemc.mods.lithium.common.world.LithiumData;
import net.minecraft.class_1937;
import net.minecraft.class_238;
import net.minecraft.class_2791;
import net.minecraft.class_2806;
import net.minecraft.class_2826;
import net.minecraft.class_4076;

public class SectionedBlockChangeTracker {
   public final WorldSectionBox trackedWorldSections;
   public final ListeningBlockStatePredicate blockGroup;
   private long maxChangeTime;
   private int timesRegistered;
   boolean isListeningToAll = false;
   private ArrayList<class_4076> sectionsNotListeningTo = null;
   private ArrayList<BlockListeningSection> sectionsUnsubscribed = null;

   public SectionedBlockChangeTracker(WorldSectionBox trackedWorldSections, ListeningBlockStatePredicate blockGroup) {
      this.trackedWorldSections = trackedWorldSections;
      this.blockGroup = blockGroup;
      this.maxChangeTime = 0L;
   }

   public boolean matchesMovedBox(class_238 box) {
      return this.trackedWorldSections.matchesRelevantBlocksBox(box);
   }

   public static SectionedBlockChangeTracker registerAt(class_1937 world, class_238 entityBoundingBox, ListeningBlockStatePredicate blockGroup) {
      WorldSectionBox worldSectionBox = WorldSectionBox.relevantExpandedBlocksBox(world, entityBoundingBox);
      SectionedBlockChangeTracker tracker = new SectionedBlockChangeTracker(worldSectionBox, blockGroup);
      LithiumInterner<SectionedBlockChangeTracker> blockChangeTrackers = ((LithiumData)world).lithium$getData().blockChangeTrackers();
      tracker = blockChangeTrackers.getCanonical(tracker);
      tracker.register();
      return tracker;
   }

   long getWorldTime() {
      return this.trackedWorldSections.world().method_8510();
   }

   public void register() {
      if (this.timesRegistered == 0) {
         WorldSectionBox trackedSections = this.trackedWorldSections;

         for (int x = trackedSections.chunkX1(); x < trackedSections.chunkX2(); x++) {
            for (int z = trackedSections.chunkZ1(); z < trackedSections.chunkZ2(); z++) {
               class_1937 world = trackedSections.world();
               class_2791 chunk = world.method_8402(x, z, class_2806.field_12803, false);
               class_2826[] sectionArray = chunk == null ? null : chunk.method_12006();

               for (int y = trackedSections.chunkY1(); y < trackedSections.chunkY2(); y++) {
                  if (Pos.SectionYCoord.getMinYSection(world) <= y && Pos.SectionYCoord.getMaxYSectionExclusive(world) > y) {
                     class_4076 sectionPos = class_4076.method_18676(x, y, z);
                     if (sectionArray == null) {
                        if (this.sectionsNotListeningTo == null) {
                           this.sectionsNotListeningTo = new ArrayList<>();
                        }

                        this.sectionsNotListeningTo.add(sectionPos);
                     } else {
                        class_2826 section = sectionArray[Pos.SectionYIndex.fromSectionCoord(world, y)];
                        BlockListeningSection blockListeningSection = (BlockListeningSection)section;
                        blockListeningSection.lithium$addToCallback(this.blockGroup, this, class_4076.method_18685(x, y, z), world);
                     }
                  }
               }
            }
         }

         this.isListeningToAll = (this.sectionsNotListeningTo == null || this.sectionsNotListeningTo.isEmpty())
            && (this.sectionsUnsubscribed == null || this.sectionsUnsubscribed.isEmpty());
         this.setChanged(this.getWorldTime());
      }

      this.timesRegistered++;
   }

   public void unregister() {
      if (--this.timesRegistered <= 0) {
         WorldSectionBox trackedSections = this.trackedWorldSections;
         class_1937 world = trackedSections.world();

         for (int x = trackedSections.chunkX1(); x < trackedSections.chunkX2(); x++) {
            for (int z = trackedSections.chunkZ1(); z < trackedSections.chunkZ2(); z++) {
               class_2791 chunk = world.method_8402(x, z, class_2806.field_12803, false);
               class_2826[] sectionArray = chunk == null ? null : chunk.method_12006();

               for (int y = trackedSections.chunkY1(); y < trackedSections.chunkY2(); y++) {
                  if (sectionArray != null && Pos.SectionYCoord.getMinYSection(world) <= y && Pos.SectionYCoord.getMaxYSectionExclusive(world) > y) {
                     class_2826 section = sectionArray[Pos.SectionYIndex.fromSectionCoord(world, y)];
                     BlockListeningSection blockListeningSection = (BlockListeningSection)section;
                     blockListeningSection.lithium$removeFromCallback(this.blockGroup, this);
                  }
               }
            }
         }

         this.sectionsNotListeningTo = null;
         LithiumInterner<SectionedBlockChangeTracker> blockChangeTrackers = ((LithiumData)world).lithium$getData().blockChangeTrackers();
         blockChangeTrackers.deleteCanonical(this);
      }
   }

   public void listenToAllSections() {
      boolean changed = false;
      ArrayList<class_4076> notListeningTo = this.sectionsNotListeningTo;
      if (notListeningTo != null) {
         for (int i = notListeningTo.size() - 1; i >= 0; i--) {
            changed = true;
            class_4076 chunkSectionPos = notListeningTo.get(i);
            class_1937 world = this.trackedWorldSections.world();
            class_2791 chunk = world.method_8402(chunkSectionPos.method_10263(), chunkSectionPos.method_10260(), class_2806.field_12803, false);
            if (chunk == null) {
               return;
            }

            notListeningTo.remove(i);
            class_2826 section = chunk.method_12006()[Pos.SectionYIndex.fromSectionCoord(world, chunkSectionPos.method_10264())];
            BlockListeningSection blockListeningSection = (BlockListeningSection)section;
            blockListeningSection.lithium$addToCallback(this.blockGroup, this, chunkSectionPos.method_18694(), world);
         }
      }

      if (this.sectionsUnsubscribed != null) {
         ArrayList<BlockListeningSection> unsubscribed = this.sectionsUnsubscribed;

         for (int i = unsubscribed.size() - 1; i >= 0; i--) {
            changed = true;
            BlockListeningSection blockListeningSection = unsubscribed.remove(i);
            blockListeningSection.lithium$addToCallback(this.blockGroup, this, Long.MIN_VALUE, null);
         }
      }

      this.isListeningToAll = true;
      if (changed) {
         this.setChanged(this.getWorldTime());
      }
   }

   public void setChanged(BlockListeningSection section) {
      if (this.sectionsUnsubscribed == null) {
         this.sectionsUnsubscribed = new ArrayList<>();
      }

      this.sectionsUnsubscribed.add(section);
      this.setChanged(this.getWorldTime());
      this.isListeningToAll = false;
   }

   public void setChanged(long atTime) {
      if (atTime > this.maxChangeTime) {
         this.maxChangeTime = atTime;
      }
   }

   public boolean isUnchangedSince(long lastCheckedTime) {
      if (lastCheckedTime <= this.maxChangeTime) {
         return false;
      } else if (this.isListeningToAll) {
         return true;
      } else {
         this.listenToAllSections();
         return this.isListeningToAll && lastCheckedTime > this.maxChangeTime;
      }
   }

   @Override
   public boolean equals(Object obj) {
      if (obj == this) {
         return true;
      } else if (obj != null && obj.getClass() == this.getClass()) {
         SectionedBlockChangeTracker that = (SectionedBlockChangeTracker)obj;
         return Objects.equals(this.trackedWorldSections, that.trackedWorldSections) && Objects.equals(this.blockGroup, that.blockGroup);
      } else {
         return false;
      }
   }

   @Override
   public int hashCode() {
      return this.getClass().hashCode() ^ this.trackedWorldSections.hashCode() ^ this.blockGroup.hashCode();
   }

   public void onChunkSectionInvalidated(class_4076 sectionPos) {
      if (this.sectionsNotListeningTo == null) {
         this.sectionsNotListeningTo = new ArrayList<>();
      }

      this.sectionsNotListeningTo.add(sectionPos);
      this.setChanged(this.getWorldTime());
      this.isListeningToAll = false;
   }
}
