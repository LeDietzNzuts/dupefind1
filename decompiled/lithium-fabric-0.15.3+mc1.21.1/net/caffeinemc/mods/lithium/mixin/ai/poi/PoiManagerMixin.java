package net.caffeinemc.mods.lithium.mixin.ai.poi;

import com.mojang.serialization.Codec;
import java.util.ArrayList;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;
import net.caffeinemc.mods.lithium.common.util.Distances;
import net.caffeinemc.mods.lithium.common.world.interests.PointOfInterestSetExtended;
import net.caffeinemc.mods.lithium.common.world.interests.PointOfInterestStorageExtended;
import net.caffeinemc.mods.lithium.common.world.interests.RegionBasedStorageSectionExtended;
import net.caffeinemc.mods.lithium.common.world.interests.iterator.NearbyPointOfInterestStream;
import net.caffeinemc.mods.lithium.common.world.interests.iterator.SinglePointOfInterestTypeFilter;
import net.caffeinemc.mods.lithium.common.world.interests.iterator.SphereChunkOrderedPoiSetSpliterator;
import net.minecraft.class_1923;
import net.minecraft.class_2338;
import net.minecraft.class_2784;
import net.minecraft.class_4153;
import net.minecraft.class_4156;
import net.minecraft.class_4157;
import net.minecraft.class_4158;
import net.minecraft.class_4180;
import net.minecraft.class_5455;
import net.minecraft.class_5539;
import net.minecraft.class_5819;
import net.minecraft.class_5996;
import net.minecraft.class_6880;
import net.minecraft.class_9172;
import net.minecraft.class_9820;
import net.minecraft.class_4153.class_4155;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(class_4153.class)
public abstract class PoiManagerMixin extends class_4180<class_4157> implements PointOfInterestStorageExtended {
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
   @class_5996
   public Stream<class_4156> method_19123(Predicate<class_6880<class_4158>> predicate, class_1923 pos, class_4155 status) {
      return ((RegionBasedStorageSectionExtended)this)
         .lithium$getWithinChunkColumn(pos.field_9181, pos.field_9180)
         .flatMap(set -> set.method_19150(predicate, status));
   }

   @Overwrite
   public Optional<class_2338> method_20005(
      Predicate<class_6880<class_4158>> typePredicate, Predicate<class_2338> posPredicate, class_4155 status, class_2338 pos, int radius, class_5819 rand
   ) {
      ArrayList<class_4156> list = this.withinSphereChunkSectionSorted(typePredicate, pos, radius, status);

      for (int i = list.size() - 1; i >= 0; i--) {
         class_4156 currentPOI = list.set(rand.method_43048(i + 1), list.get(i));
         list.set(i, currentPOI);
         if (posPredicate.test(currentPOI.method_19141())) {
            return Optional.of(currentPOI.method_19141());
         }
      }

      return Optional.empty();
   }

   @Overwrite
   public Optional<class_2338> method_20006(Predicate<class_6880<class_4158>> predicate, class_2338 pos, int radius, class_4155 status) {
      return this.method_34712(predicate, null, pos, radius, status);
   }

   @Overwrite
   public Optional<class_2338> method_34712(
      Predicate<class_6880<class_4158>> predicate, Predicate<class_2338> posPredicate, class_2338 pos, int radius, class_4155 status
   ) {
      Stream<class_4156> pointOfInterestStream = this.streamOutwards(
         pos, radius, status, true, false, predicate, posPredicate == null ? null : poi -> posPredicate.test(poi.method_19141())
      );
      return pointOfInterestStream.<class_2338>map(class_4156::method_19141).findFirst();
   }

   @Overwrite
   public long method_20252(Predicate<class_6880<class_4158>> predicate, class_2338 pos, int radius, class_4155 status) {
      return this.withinSphereChunkSectionSorted(predicate, pos, radius, status).size();
   }

   @Overwrite
   public Stream<class_4156> method_19125(Predicate<class_6880<class_4158>> predicate, class_2338 sphereOrigin, int radius, class_4155 status) {
      return this.withinSphereChunkSectionSortedStream(predicate, sphereOrigin, radius, status);
   }

   @Override
   public Optional<class_4156> lithium$findNearestForPortalLogic(
      class_2338 origin, int radius, class_6880<class_4158> type, class_4155 status, Predicate<class_4156> afterSortPredicate, class_2784 worldBorder
   ) {
      boolean worldBorderIsFarAway = worldBorder == null || worldBorder.method_11961(origin.method_10263(), origin.method_10260()) > radius + 3;
      Predicate<class_4156> poiPredicateAfterSorting;
      if (worldBorderIsFarAway) {
         poiPredicateAfterSorting = afterSortPredicate;
      } else {
         poiPredicateAfterSorting = poi -> worldBorder.method_11952(poi.method_19141()) && afterSortPredicate.test(poi);
      }

      return this.streamOutwards(origin, radius, status, true, true, new SinglePointOfInterestTypeFilter(type), poiPredicateAfterSorting).findFirst();
   }

   private Stream<class_4156> withinSphereChunkSectionSortedStream(
      Predicate<class_6880<class_4158>> predicate, class_2338 origin, int radius, class_4155 status
   ) {
      double radiusSq = radius * radius;
      RegionBasedStorageSectionExtended<class_4157> storage = (RegionBasedStorageSectionExtended<class_4157>)this;
      Stream<Stream<class_4157>> stream = StreamSupport.stream(new SphereChunkOrderedPoiSetSpliterator(radius, origin, storage), false);
      return stream.flatMap(
         setStream -> setStream.flatMap(
            set -> set.method_19150(predicate, status).filter(point -> Distances.isWithinCircleRadius(origin, radiusSq, point.method_19141()))
         )
      );
   }

   private ArrayList<class_4156> withinSphereChunkSectionSorted(Predicate<class_6880<class_4158>> predicate, class_2338 origin, int radius, class_4155 status) {
      double radiusSq = radius * radius;
      int minChunkX = origin.method_10263() - radius - 1 >> 4;
      int minChunkZ = origin.method_10260() - radius - 1 >> 4;
      int maxChunkX = origin.method_10263() + radius + 1 >> 4;
      int maxChunkZ = origin.method_10260() + radius + 1 >> 4;
      RegionBasedStorageSectionExtended<class_4157> storage = (RegionBasedStorageSectionExtended<class_4157>)this;
      ArrayList<class_4156> points = new ArrayList<>();
      Consumer<class_4156> collector = point -> {
         if (Distances.isWithinCircleRadius(origin, radiusSq, point.method_19141())) {
            points.add(point);
         }
      };

      for (int x = minChunkX; x <= maxChunkX; x++) {
         for (int z = minChunkZ; z <= maxChunkZ; z++) {
            for (class_4157 set : storage.lithium$getInChunkColumn(x, z)) {
               ((PointOfInterestSetExtended)set).lithium$collectMatchingPoints(predicate, status, collector);
            }
         }
      }

      return points;
   }

   private Stream<class_4156> streamOutwards(
      class_2338 origin,
      int radius,
      class_4155 status,
      boolean useSquareDistanceLimit,
      boolean preferNegativeY,
      Predicate<class_6880<class_4158>> typePredicate,
      @Nullable Predicate<class_4156> afterSortingPredicate
   ) {
      RegionBasedStorageSectionExtended<class_4157> storage = (RegionBasedStorageSectionExtended<class_4157>)this;
      return StreamSupport.stream(
         new NearbyPointOfInterestStream(typePredicate, status, useSquareDistanceLimit, preferNegativeY, afterSortingPredicate, origin, radius, storage), false
      );
   }
}
