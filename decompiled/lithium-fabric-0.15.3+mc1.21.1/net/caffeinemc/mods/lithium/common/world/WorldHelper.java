package net.caffeinemc.mods.lithium.common.world;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;
import net.caffeinemc.mods.lithium.common.client.ClientWorldAccessor;
import net.caffeinemc.mods.lithium.common.entity.EntityClassGroup;
import net.caffeinemc.mods.lithium.common.entity.pushable.EntityPushablePredicate;
import net.caffeinemc.mods.lithium.common.world.chunk.ClassGroupFilterableList;
import net.caffeinemc.mods.lithium.mixin.util.accessors.EntitySectionAccessor;
import net.caffeinemc.mods.lithium.mixin.util.accessors.PersistentEntitySectionManagerAccessor;
import net.caffeinemc.mods.lithium.mixin.util.accessors.ServerLevelAccessor;
import net.caffeinemc.mods.lithium.mixin.util.accessors.TransientEntitySectionManagerAccessor;
import net.minecraft.class_1297;
import net.minecraft.class_1924;
import net.minecraft.class_1937;
import net.minecraft.class_2338;
import net.minecraft.class_238;
import net.minecraft.class_3509;
import net.minecraft.class_5573;
import net.minecraft.class_7927.class_7928;
import org.jetbrains.annotations.Nullable;

public class WorldHelper {
   public static final boolean CUSTOM_TYPE_FILTERABLE_LIST_DISABLED = !ClassGroupFilterableList.class.isAssignableFrom(class_3509.class);

   public static List<class_1297> getEntitiesForCollision(class_1924 entityView, class_238 box, class_1297 collidingEntity) {
      if (!CUSTOM_TYPE_FILTERABLE_LIST_DISABLED
         && entityView instanceof class_1937 world
         && (collidingEntity == null || !EntityClassGroup.CUSTOM_COLLIDE_LIKE_MINECART_BOAT_WINDCHARGE.contains(collidingEntity))) {
         class_5573<class_1297> cache = getEntityCacheOrNull(world);
         if (cache != null) {
            world.method_16107().method_39278("getEntities");
            return getEntitiesOfEntityGroup(cache, collidingEntity, EntityClassGroup.NoDragonClassGroup.BOAT_SHULKER_LIKE_COLLISION, box, null);
         }
      }

      return entityView.method_8335(collidingEntity, box);
   }

   public static List<class_1297> getOtherEntitiesForCollision(
      class_1924 entityView, class_238 box, @Nullable class_1297 collidingEntity, Predicate<? super class_1297> entityFilter
   ) {
      if (!CUSTOM_TYPE_FILTERABLE_LIST_DISABLED
         && entityView instanceof class_1937 world
         && (collidingEntity == null || !EntityClassGroup.CUSTOM_COLLIDE_LIKE_MINECART_BOAT_WINDCHARGE.contains(collidingEntity))) {
         class_5573<class_1297> cache = getEntityCacheOrNull(world);
         if (cache != null) {
            world.method_16107().method_39278("getEntities");
            return getEntitiesOfEntityGroup(cache, collidingEntity, EntityClassGroup.NoDragonClassGroup.BOAT_SHULKER_LIKE_COLLISION, box, entityFilter);
         }
      }

      return entityView.method_8333(collidingEntity, box, entityFilter);
   }

   public static class_5573<class_1297> getEntityCacheOrNull(class_1937 world) {
      if (world instanceof ClientWorldAccessor) {
         return ((TransientEntitySectionManagerAccessor)((ClientWorldAccessor)world).lithium$getEntityManager()).getCache();
      } else {
         return world instanceof ServerLevelAccessor
            ? ((PersistentEntitySectionManagerAccessor)((ServerLevelAccessor)world).getEntityManager()).getCache()
            : null;
      }
   }

   public static List<class_1297> getEntitiesOfEntityGroup(
      class_5573<class_1297> cache,
      class_1297 collidingEntity,
      EntityClassGroup.NoDragonClassGroup entityClassGroup,
      class_238 box,
      Predicate<? super class_1297> entityFilter
   ) {
      ArrayList<class_1297> entities = new ArrayList<>();
      cache.method_31777(
         box,
         section -> {
            class_3509<class_1297> allEntities = ((EntitySectionAccessor)section).getCollection();
            Collection<class_1297> entitiesOfType = ((ClassGroupFilterableList)allEntities).lithium$getAllOfGroupType(entityClassGroup);
            if (!entitiesOfType.isEmpty()) {
               for (class_1297 entity : entitiesOfType) {
                  if (entity.method_5829().method_994(box)
                     && !entity.method_7325()
                     && entity != collidingEntity
                     && (entityFilter == null || entityFilter.test(entity))) {
                     entities.add(entity);
                  }
               }
            }

            return class_7928.field_41283;
         }
      );
      return entities;
   }

   public static List<class_1297> getPushableEntities(
      class_1937 world, class_5573<class_1297> cache, class_1297 except, class_238 box, EntityPushablePredicate<? super class_1297> entityPushablePredicate
   ) {
      ArrayList<class_1297> entities = new ArrayList<>();
      cache.method_31777(
         box, section -> ((ClimbingMobCachingSection)section).lithium$collectPushableEntities(world, except, box, entityPushablePredicate, entities)
      );
      return entities;
   }

   public static boolean areNeighborsWithinSameChunk(class_2338 pos) {
      int localX = pos.method_10263() & 15;
      int localZ = pos.method_10260() & 15;
      return localX > 0 && localZ > 0 && localX < 15 && localZ < 15;
   }

   public static boolean areNeighborsWithinSameChunkSection(int x, int y, int z) {
      int localX = x & 15;
      int localY = y & 15;
      int localZ = z & 15;
      return localX > 0 && localY > 0 && localZ > 0 && localX < 15 && localY < 15 && localZ < 15;
   }

   public static boolean arePosWithinSameChunk(class_2338 pos1, class_2338 pos2) {
      return pos1.method_10263() >> 4 == pos2.method_10263() >> 4 && pos1.method_10260() >> 4 == pos2.method_10260() >> 4;
   }
}
