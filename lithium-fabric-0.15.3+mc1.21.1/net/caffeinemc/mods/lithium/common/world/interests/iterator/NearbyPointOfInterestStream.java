package net.caffeinemc.mods.lithium.common.world.interests.iterator;

import it.unimi.dsi.fastutil.longs.LongArrayList;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Spliterators.AbstractSpliterator;
import java.util.function.Consumer;
import java.util.function.Predicate;
import net.caffeinemc.mods.lithium.common.util.Distances;
import net.caffeinemc.mods.lithium.common.util.tuples.SortedPointOfInterest;
import net.caffeinemc.mods.lithium.common.world.interests.PointOfInterestSetExtended;
import net.caffeinemc.mods.lithium.common.world.interests.RegionBasedStorageSectionExtended;
import net.minecraft.class_1923;
import net.minecraft.class_2338;
import net.minecraft.class_4076;
import net.minecraft.class_4156;
import net.minecraft.class_4157;
import net.minecraft.class_4158;
import net.minecraft.class_6880;
import net.minecraft.class_4153.class_4155;
import org.jetbrains.annotations.Nullable;

public class NearbyPointOfInterestStream extends AbstractSpliterator<class_4156> {
   private final RegionBasedStorageSectionExtended<class_4157> storage;
   private final Predicate<class_6880<class_4158>> typeSelector;
   private final class_4155 occupationStatus;
   private final LongArrayList chunksSortedByMinDistance;
   private final ArrayList<SortedPointOfInterest> points;
   private final Predicate<class_4156> afterSortingPredicate;
   private final Consumer<class_4156> collector;
   private final class_2338 origin;
   private int chunkIndex;
   private double currChunkMinDistanceSq;
   private int pointIndex;
   private final Comparator<? super SortedPointOfInterest> pointComparator;

   public NearbyPointOfInterestStream(
      Predicate<class_6880<class_4158>> typeSelector,
      class_4155 status,
      boolean useSquareDistanceLimit,
      boolean preferNegativeY,
      @Nullable Predicate<class_4156> afterSortingPredicate,
      class_2338 origin,
      int radius,
      RegionBasedStorageSectionExtended<class_4157> storage
   ) {
      super(Long.MAX_VALUE, 16);
      this.storage = storage;
      this.chunkIndex = 0;
      this.pointIndex = 0;
      this.points = new ArrayList<>();
      this.occupationStatus = status;
      this.typeSelector = typeSelector;
      this.origin = origin;
      if (useSquareDistanceLimit) {
         this.collector = point -> {
            if (Distances.isWithinSquareRadius(this.origin, radius, point.method_19141())) {
               this.points.add(new SortedPointOfInterest(point, this.origin));
            }
         };
      } else {
         double radiusSq = radius * radius;
         this.collector = point -> {
            if (Distances.isWithinCircleRadius(this.origin, radiusSq, point.method_19141())) {
               this.points.add(new SortedPointOfInterest(point, this.origin));
            }
         };
      }

      double distanceLimitL2Sq = useSquareDistanceLimit ? radius * radius * 2 : radius * radius;
      this.chunksSortedByMinDistance = initChunkPositions(origin, radius, distanceLimitL2Sq);
      this.afterSortingPredicate = afterSortingPredicate;
      this.pointComparator = preferNegativeY ? (o1, o2) -> {
         int cmp = Double.compare(o1.distanceSq(), o2.distanceSq());
         if (cmp != 0) {
            return cmp;
         } else {
            int negativeY = Integer.compare(o1.getY(), o2.getY());
            if (negativeY != 0) {
               return negativeY;
            } else {
               int cmp3 = Integer.compare(class_4076.method_18675(o1.getX()), class_4076.method_18675(o2.getX()));
               return cmp3 != 0 ? cmp3 : Integer.compare(class_4076.method_18675(o1.getZ()), class_4076.method_18675(o2.getZ()));
            }
         }
      } : (o1, o2) -> {
         int cmp = Double.compare(o1.distanceSq(), o2.distanceSq());
         if (cmp != 0) {
            return cmp;
         } else {
            int cmp2 = Integer.compare(class_4076.method_18675(o1.getX()), class_4076.method_18675(o2.getX()));
            if (cmp2 != 0) {
               return cmp2;
            } else {
               int cmp3 = Integer.compare(class_4076.method_18675(o1.getZ()), class_4076.method_18675(o2.getZ()));
               return cmp3 != 0 ? cmp3 : Integer.compare(class_4076.method_18675(o1.getY()), class_4076.method_18675(o2.getY()));
            }
         }
      };
   }

   private static LongArrayList initChunkPositions(class_2338 origin, int radius, double distanceLimitL2Sq) {
      int minChunkX = origin.method_10263() - radius - 1 >> 4;
      int minChunkZ = origin.method_10260() - radius - 1 >> 4;
      int maxChunkX = origin.method_10263() + radius + 1 >> 4;
      int maxChunkZ = origin.method_10260() + radius + 1 >> 4;
      LongArrayList chunkPositions = new LongArrayList();

      for (int chunkX = minChunkX; chunkX <= maxChunkX; chunkX++) {
         for (int chunkZ = minChunkZ; chunkZ <= maxChunkZ; chunkZ++) {
            if (distanceLimitL2Sq >= Distances.getMinChunkToBlockDistanceL2Sq(origin, chunkX, chunkZ)) {
               chunkPositions.add(class_1923.method_8331(chunkX, chunkZ));
            }
         }
      }

      chunkPositions.sort(
         (c1, c2) -> Double.compare(
            Distances.getMinChunkToBlockDistanceL2Sq(origin, class_1923.method_8325(c1), class_1923.method_8332(c1)),
            Distances.getMinChunkToBlockDistanceL2Sq(origin, class_1923.method_8325(c2), class_1923.method_8332(c2))
         )
      );
      return chunkPositions;
   }

   @Override
   public boolean tryAdvance(Consumer<? super class_4156> action) {
      if (this.pointIndex < this.points.size() && this.tryAdvancePoint(action)) {
         return true;
      } else {
         while (this.chunkIndex < this.chunksSortedByMinDistance.size()) {
            long chunkPos = this.chunksSortedByMinDistance.getLong(this.chunkIndex);
            int chunkPosX = class_1923.method_8325(chunkPos);
            int chunkPosZ = class_1923.method_8332(chunkPos);
            this.currChunkMinDistanceSq = Distances.getMinChunkToBlockDistanceL2Sq(this.origin, chunkPosX, chunkPosZ);
            this.chunkIndex++;
            if (this.chunkIndex == this.chunksSortedByMinDistance.size()) {
               this.currChunkMinDistanceSq = Double.POSITIVE_INFINITY;
            }

            int previousSize = this.points.size();

            for (class_4157 set : this.storage.lithium$getInChunkColumn(chunkPosX, chunkPosZ)) {
               ((PointOfInterestSetExtended)set).lithium$collectMatchingPoints(this.typeSelector, this.occupationStatus, this.collector);
            }

            if (this.points.size() != previousSize) {
               this.points.subList(this.pointIndex, this.points.size()).sort(this.pointComparator);
               if (this.tryAdvancePoint(action)) {
                  return true;
               }
            }
         }

         return this.tryAdvancePoint(action);
      }
   }

   private boolean tryAdvancePoint(Consumer<? super class_4156> action) {
      while (this.pointIndex < this.points.size()) {
         SortedPointOfInterest next = this.points.get(this.pointIndex);
         if (next.distanceSq() >= this.currChunkMinDistanceSq) {
            return false;
         }

         this.pointIndex++;
         if (this.afterSortingPredicate == null || this.afterSortingPredicate.test(next.poi())) {
            action.accept(next.poi());
            return true;
         }
      }

      return false;
   }
}
