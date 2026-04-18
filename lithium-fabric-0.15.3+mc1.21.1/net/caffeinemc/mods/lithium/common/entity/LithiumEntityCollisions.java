package net.caffeinemc.mods.lithium.common.entity;

import com.google.common.collect.AbstractIterator;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import net.caffeinemc.mods.lithium.common.entity.movement.ChunkAwareBlockCollisionSweeper;
import net.caffeinemc.mods.lithium.common.util.Pos;
import net.caffeinemc.mods.lithium.common.world.WorldHelper;
import net.minecraft.class_1297;
import net.minecraft.class_1924;
import net.minecraft.class_1937;
import net.minecraft.class_1941;
import net.minecraft.class_2338;
import net.minecraft.class_238;
import net.minecraft.class_243;
import net.minecraft.class_247;
import net.minecraft.class_259;
import net.minecraft.class_265;
import net.minecraft.class_2784;
import net.minecraft.class_2791;
import net.minecraft.class_2806;
import net.minecraft.class_2826;
import net.minecraft.class_3532;
import net.minecraft.class_3726;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class LithiumEntityCollisions {
   public static final double EPSILON = 1.0E-7;

   public static List<class_265> getBlockCollisions(class_1937 world, class_1297 entity, class_238 box) {
      return new ChunkAwareBlockCollisionSweeper(world, entity, box).collectAll();
   }

   public static boolean doesBoxCollideWithBlocks(class_1937 world, @Nullable class_1297 entity, class_238 box) {
      ChunkAwareBlockCollisionSweeper sweeper = new ChunkAwareBlockCollisionSweeper(world, entity, box);
      class_265 shape = sweeper.computeNext();
      return shape != null && !shape.method_1110();
   }

   public static boolean doesBoxCollideWithHardEntities(class_1924 view, @Nullable class_1297 entity, class_238 box) {
      return isBoxEmpty(box) ? false : getEntityWorldBorderCollisionIterable(view, entity, box.method_1014(1.0E-7), false).iterator().hasNext();
   }

   public static void appendEntityCollisions(List<class_265> entityCollisions, class_1937 world, class_1297 entity, class_238 box) {
      if (!isBoxEmpty(box)) {
         class_238 expandedBox = box.method_1014(1.0E-7);

         for (class_1297 otherEntity : WorldHelper.getEntitiesForCollision(world, expandedBox, entity)) {
            if (entity == null ? otherEntity.method_30948() : entity.method_30949(otherEntity)) {
               entityCollisions.add(class_259.method_1078(otherEntity.method_5829()));
            }
         }
      }
   }

   public static void appendWorldBorderCollision(ArrayList<class_265> worldBorderCollisions, class_1297 entity, class_238 box) {
      class_2784 worldBorder = entity.method_37908().method_8621();
      if (!isWithinWorldBorder(worldBorder, box) && isWithinWorldBorder(worldBorder, entity.method_5829())) {
         worldBorderCollisions.add(worldBorder.method_17903());
      }
   }

   public static Iterable<class_265> getEntityWorldBorderCollisionIterable(
      class_1924 view, @Nullable class_1297 entity, class_238 box, boolean includeWorldBorder
   ) {
      assert !includeWorldBorder || entity != null;

      return new Iterable<class_265>() {
         private List<class_1297> entityList;
         private int nextFilterIndex;

         @NotNull
         @Override
         public Iterator<class_265> iterator() {
            return new AbstractIterator<class_265>() {
               int index = 0;
               boolean consumedWorldBorder = false;

               protected class_265 computeNext() {
                  if (entityList == null) {
                     entityList = WorldHelper.getEntitiesForCollision(view, box, entity);
                     nextFilterIndex = 0;
                  }

                  List<class_1297> list = entityList;

                  while (this.index < list.size()) {
                     class_1297 otherEntity = list.get(this.index);
                     if (this.index >= nextFilterIndex) {
                        if (entity == null) {
                           if (!otherEntity.method_30948()) {
                              otherEntity = null;
                           }
                        } else if (!entity.method_30949(otherEntity)) {
                           otherEntity = null;
                        }

                        nextFilterIndex++;
                     }

                     this.index++;
                     if (otherEntity != null) {
                        return class_259.method_1078(otherEntity.method_5829());
                     }
                  }

                  if (includeWorldBorder && !this.consumedWorldBorder) {
                     this.consumedWorldBorder = true;
                     class_2784 worldBorder = entity.method_37908().method_8621();
                     if (!LithiumEntityCollisions.isWithinWorldBorder(worldBorder, box)
                        && LithiumEntityCollisions.isWithinWorldBorder(worldBorder, entity.method_5829())) {
                        return worldBorder.method_17903();
                     }
                  }

                  return (class_265)this.endOfData();
               }
            };
         }
      };
   }

   public static boolean isWithinWorldBorder(class_2784 border, class_238 box) {
      double wboxMinX = Math.floor(border.method_11976());
      double wboxMinZ = Math.floor(border.method_11958());
      double wboxMaxX = Math.ceil(border.method_11963());
      double wboxMaxZ = Math.ceil(border.method_11977());
      return box.field_1323 >= wboxMinX
         && box.field_1323 <= wboxMaxX
         && box.field_1321 >= wboxMinZ
         && box.field_1321 <= wboxMaxZ
         && box.field_1320 >= wboxMinX
         && box.field_1320 <= wboxMaxX
         && box.field_1324 >= wboxMinZ
         && box.field_1324 <= wboxMaxZ;
   }

   private static boolean isBoxEmpty(class_238 box) {
      return box.method_995() <= 1.0E-7;
   }

   public static boolean doesBoxCollideWithWorldBorder(class_1941 collisionView, class_1297 entity, class_238 box) {
      if (isWithinWorldBorder(collisionView.method_8621(), box)) {
         return false;
      } else {
         class_265 worldBorderShape = getWorldBorderCollision(collisionView, entity, box);
         return worldBorderShape != null && class_259.method_1074(worldBorderShape, class_259.method_1078(box), class_247.field_16896);
      }
   }

   public static class_265 getWorldBorderCollision(class_1941 collisionView, @Nullable class_1297 entity, class_238 box) {
      class_2784 worldBorder = collisionView.method_8621();
      return worldBorder.method_39459(entity, box) ? worldBorder.method_17903() : null;
   }

   @Nullable
   public static class_265 getSupportingCollisionForEntity(class_1937 world, @Nullable class_1297 entity, class_238 entityBoundingBox) {
      if (entity instanceof LithiumEntityCollisions.SupportingBlockCollisionShapeProvider supportingBlockCollisionShapeProvider) {
         class_265 voxelShape = supportingBlockCollisionShapeProvider.lithium$getCollisionShapeBelow();
         if (voxelShape != null) {
            return voxelShape;
         }
      }

      return getCollisionShapeBelowEntityFallback(world, entity, entityBoundingBox);
   }

   @Nullable
   private static class_265 getCollisionShapeBelowEntityFallback(class_1937 world, class_1297 entity, class_238 entityBoundingBox) {
      int x = class_3532.method_15357(entityBoundingBox.field_1323 + (entityBoundingBox.field_1320 - entityBoundingBox.field_1323) / 2.0);
      int y = class_3532.method_15357(entityBoundingBox.field_1322);
      int z = class_3532.method_15357(entityBoundingBox.field_1321 + (entityBoundingBox.field_1324 - entityBoundingBox.field_1321) / 2.0);
      if (world.method_31601(y)) {
         return null;
      } else {
         class_2791 chunk = world.method_8402(Pos.ChunkCoord.fromBlockCoord(x), Pos.ChunkCoord.fromBlockCoord(z), class_2806.field_12803, false);
         if (chunk != null) {
            class_2826 cachedChunkSection = chunk.method_12006()[Pos.SectionYIndex.fromBlockCoord(world, y)];
            return cachedChunkSection.method_12254(x & 15, y & 15, z & 15)
               .method_26194(world, new class_2338(x, y, z), entity == null ? class_3726.method_16194() : class_3726.method_16195(entity));
         } else {
            return null;
         }
      }
   }

   public static boolean addLastBlockCollisionIfRequired(
      boolean addLastBlockCollision, ChunkAwareBlockCollisionSweeper blockCollisionSweeper, List<class_265> list
   ) {
      if (addLastBlockCollision) {
         class_265 lastCollision = blockCollisionSweeper.getLastCollision();
         if (lastCollision != null) {
            list.add(lastCollision);
         }
      }

      return false;
   }

   public static class_238 getSmallerBoxForSingleAxisMovement(class_243 movement, class_238 entityBoundingBox, double velY, double velX, double velZ) {
      double minX = entityBoundingBox.field_1323;
      double minY = entityBoundingBox.field_1322;
      double minZ = entityBoundingBox.field_1321;
      double maxX = entityBoundingBox.field_1320;
      double maxY = entityBoundingBox.field_1325;
      double maxZ = entityBoundingBox.field_1324;
      if (velY > 0.0) {
         minY = maxY;
         maxY += velY;
      } else if (velY < 0.0) {
         maxY = minY;
         minY += velY;
      } else if (velX > 0.0) {
         minX = maxX;
         maxX += velX;
      } else if (velX < 0.0) {
         maxX = minX;
         minX += velX;
      } else if (velZ > 0.0) {
         minZ = maxZ;
         maxZ += velZ;
      } else {
         if (!(velZ < 0.0)) {
            return entityBoundingBox.method_18804(movement);
         }

         maxZ = minZ;
         minZ += velZ;
      }

      return new class_238(minX, minY, minZ, maxX, maxY, maxZ);
   }

   public static boolean addEntityCollisionsIfRequired(
      boolean getEntityCollisions, @Nullable class_1297 entity, class_1937 world, List<class_265> entityCollisions, class_238 movementSpace
   ) {
      if (getEntityCollisions) {
         appendEntityCollisions(entityCollisions, world, entity, movementSpace);
      }

      return false;
   }

   public static boolean addWorldBorderCollisionIfRequired(
      boolean getWorldBorderCollision, @Nullable class_1297 entity, ArrayList<class_265> worldBorderCollisions, class_238 movementSpace
   ) {
      if (getWorldBorderCollision && entity != null) {
         appendWorldBorderCollision(worldBorderCollisions, entity, movementSpace);
      }

      return false;
   }

   public interface SupportingBlockCollisionShapeProvider {
      @Nullable
      class_265 lithium$getCollisionShapeBelow();
   }
}
