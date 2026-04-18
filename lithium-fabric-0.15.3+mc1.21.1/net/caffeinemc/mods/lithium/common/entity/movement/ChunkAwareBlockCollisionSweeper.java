package net.caffeinemc.mods.lithium.common.entity.movement;

import com.google.common.collect.AbstractIterator;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import net.caffeinemc.mods.lithium.common.block.BlockCountingSection;
import net.caffeinemc.mods.lithium.common.block.BlockStateFlags;
import net.caffeinemc.mods.lithium.common.shapes.VoxelShapeCaster;
import net.caffeinemc.mods.lithium.common.util.Pos;
import net.minecraft.class_1297;
import net.minecraft.class_1937;
import net.minecraft.class_2246;
import net.minecraft.class_238;
import net.minecraft.class_247;
import net.minecraft.class_259;
import net.minecraft.class_265;
import net.minecraft.class_2680;
import net.minecraft.class_2791;
import net.minecraft.class_2806;
import net.minecraft.class_2826;
import net.minecraft.class_3532;
import net.minecraft.class_3726;
import net.minecraft.class_2338.class_2339;
import org.jetbrains.annotations.Nullable;

public class ChunkAwareBlockCollisionSweeper extends AbstractIterator<class_265> {
   private final class_2339 pos = new class_2339();
   private final class_238 box;
   private final class_265 shape;
   private final class_1937 world;
   private final class_3726 context;
   private final int minX;
   private final int minY;
   private final int minZ;
   private final int maxX;
   private final int maxY;
   private final int maxZ;
   private int chunkX;
   private int chunkYIndex;
   private int chunkZ;
   private int cStartX;
   private int cStartZ;
   private int cEndX;
   private int cEndZ;
   private int cX;
   private int cY;
   private int cZ;
   private int maxHitX;
   private int maxHitY;
   private int maxHitZ;
   private class_265 maxShape;
   private final boolean hideLastCollision;
   private int cTotalSize;
   private int cIterated;
   private boolean sectionOversizedBlocks;
   private class_2791 cachedChunk;
   private class_2826 cachedChunkSection;

   public ChunkAwareBlockCollisionSweeper(class_1937 world, @Nullable class_1297 entity, class_238 box) {
      this(world, entity, box, false);
   }

   public ChunkAwareBlockCollisionSweeper(class_1937 world, @Nullable class_1297 entity, class_238 box, boolean hideLastCollision) {
      this.box = box;
      this.shape = class_259.method_1078(box);
      this.context = entity == null ? class_3726.method_16194() : class_3726.method_16195(entity);
      this.world = world;
      this.minX = class_3532.method_15357(box.field_1323 - 1.0E-7);
      this.maxX = class_3532.method_15357(box.field_1320 + 1.0E-7);
      this.minY = class_3532.method_15340(
         class_3532.method_15357(box.field_1322 - 1.0E-7), Pos.BlockCoord.getMinY(this.world), Pos.BlockCoord.getMaxYInclusive(this.world)
      );
      this.maxY = class_3532.method_15340(
         class_3532.method_15357(box.field_1325 + 1.0E-7), Pos.BlockCoord.getMinY(this.world), Pos.BlockCoord.getMaxYInclusive(this.world)
      );
      this.minZ = class_3532.method_15357(box.field_1321 - 1.0E-7);
      this.maxZ = class_3532.method_15357(box.field_1324 + 1.0E-7);
      this.chunkX = Pos.ChunkCoord.fromBlockCoord(expandMin(this.minX));
      this.chunkZ = Pos.ChunkCoord.fromBlockCoord(expandMin(this.minZ));
      this.cIterated = 0;
      this.cTotalSize = 0;
      this.maxHitX = Integer.MIN_VALUE;
      this.maxHitY = Integer.MIN_VALUE;
      this.maxHitZ = Integer.MIN_VALUE;
      this.maxShape = null;
      this.hideLastCollision = hideLastCollision;
      this.chunkX--;
   }

   public class_265 getLastCollision() {
      return this.maxShape;
   }

   public Iterator<class_265> getLastCollisionIterator() {
      return new Iterator<class_265>() {
         @Override
         public boolean hasNext() {
            return ChunkAwareBlockCollisionSweeper.this.hideLastCollision && ChunkAwareBlockCollisionSweeper.this.maxShape != null;
         }

         public class_265 next() {
            if (this.hasNext()) {
               class_265 previousMaxShape = ChunkAwareBlockCollisionSweeper.this.maxShape;
               ChunkAwareBlockCollisionSweeper.this.maxShape = null;
               return previousMaxShape;
            } else {
               throw new NoSuchElementException();
            }
         }
      };
   }

   private boolean nextSection() {
      while (true) {
         if (this.cachedChunk != null
            && this.chunkYIndex < Pos.SectionYIndex.getMaxYSectionIndexInclusive(this.world)
            && this.chunkYIndex < Pos.SectionYIndex.fromBlockCoord(this.world, expandMax(this.maxY))) {
            this.chunkYIndex++;
            this.cachedChunkSection = this.cachedChunk.method_12006()[this.chunkYIndex];
         } else {
            if (this.chunkX < Pos.ChunkCoord.fromBlockCoord(expandMax(this.maxX))) {
               this.chunkX++;
            } else {
               if (this.chunkZ >= Pos.ChunkCoord.fromBlockCoord(expandMax(this.maxZ))) {
                  return false;
               }

               this.chunkX = Pos.ChunkCoord.fromBlockCoord(expandMin(this.minX));
               this.chunkZ++;
            }

            this.cachedChunk = this.world.method_8402(this.chunkX, this.chunkZ, class_2806.field_12803, false);
            if (this.cachedChunk != null) {
               this.chunkYIndex = class_3532.method_15340(
                  Pos.SectionYIndex.fromBlockCoord(this.world, expandMin(this.minY)),
                  Pos.SectionYIndex.getMinYSectionIndex(this.world),
                  Pos.SectionYIndex.getMaxYSectionIndexInclusive(this.world)
               );
               this.cachedChunkSection = this.cachedChunk.method_12006()[this.chunkYIndex];
            }
         }

         if (this.cachedChunk != null && this.cachedChunkSection != null && !this.cachedChunkSection.method_38292()) {
            this.sectionOversizedBlocks = hasChunkSectionOversizedBlocks(this.cachedChunk, this.chunkYIndex);
            int sizeExtension = this.sectionOversizedBlocks ? 1 : 0;
            this.cEndX = Math.min(this.maxX + sizeExtension, Pos.BlockCoord.getMaxInSectionCoord(this.chunkX));
            int cEndY = Math.min(this.maxY + sizeExtension, Pos.BlockCoord.getMaxYInSectionIndex(this.world, this.chunkYIndex));
            this.cEndZ = Math.min(this.maxZ + sizeExtension, Pos.BlockCoord.getMaxInSectionCoord(this.chunkZ));
            this.cStartX = Math.max(this.minX - sizeExtension, Pos.BlockCoord.getMinInSectionCoord(this.chunkX));
            int cStartY = Math.max(this.minY - sizeExtension, Pos.BlockCoord.getMinYInSectionIndex(this.world, this.chunkYIndex));
            this.cStartZ = Math.max(this.minZ - sizeExtension, Pos.BlockCoord.getMinInSectionCoord(this.chunkZ));
            this.cX = this.cStartX;
            this.cY = cStartY;
            this.cZ = this.cStartZ;
            this.cTotalSize = (this.cEndX - this.cStartX + 1) * (cEndY - cStartY + 1) * (this.cEndZ - this.cStartZ + 1);
            if (this.cTotalSize != 0) {
               this.cIterated = 0;
               return true;
            }
         }
      }
   }

   public class_265 computeNext() {
      while (this.cIterated < this.cTotalSize || this.nextSection()) {
         this.cIterated++;
         int x = this.cX;
         int y = this.cY;
         int z = this.cZ;
         if (this.cX < this.cEndX) {
            this.cX++;
         } else if (this.cZ < this.cEndZ) {
            this.cX = this.cStartX;
            this.cZ++;
         } else {
            this.cX = this.cStartX;
            this.cZ = this.cStartZ;
            this.cY++;
         }

         int edgesHit = this.sectionOversizedBlocks
            ? (x >= this.minX && x <= this.maxX ? 0 : 1) + (y >= this.minY && y <= this.maxY ? 0 : 1) + (z >= this.minZ && z <= this.maxZ ? 0 : 1)
            : 0;
         if (edgesHit != 3) {
            class_2680 state = this.cachedChunkSection.method_12254(x & 15, y & 15, z & 15);
            if (canInteractWithBlock(state, edgesHit)) {
               this.pos.method_10103(x, y, z);
               class_265 collisionShape = state.method_26194(this.world, this.pos, this.context);
               if (collisionShape != class_259.method_1073() && collisionShape != null) {
                  class_265 collidedShape = getCollidedShape(this.box, this.shape, collisionShape, x, y, z);
                  if (collidedShape != null) {
                     if (z >= this.maxHitZ && (z > this.maxHitZ || y >= this.maxHitY && (y > this.maxHitY || x > this.maxHitX))) {
                        this.maxHitX = x;
                        this.maxHitY = y;
                        this.maxHitZ = z;
                        class_265 previousMaxShape = this.maxShape;
                        this.maxShape = collidedShape;
                        if (previousMaxShape == null) {
                           continue;
                        }

                        return previousMaxShape;
                     }

                     return collidedShape;
                  }
               }
            }
         }
      }

      if (!this.hideLastCollision && this.maxShape != null) {
         class_265 previousMaxShape = this.maxShape;
         this.maxShape = null;
         return previousMaxShape;
      } else {
         return (class_265)this.endOfData();
      }
   }

   private static boolean canInteractWithBlock(class_2680 state, int edgesHit) {
      return (edgesHit != 1 || state.method_26209()) && (edgesHit != 2 || state.method_26204() == class_2246.field_10008);
   }

   private static class_265 getCollidedShape(class_238 entityBox, class_265 entityShape, class_265 shape, int x, int y, int z) {
      if (shape == class_259.method_1077()) {
         return entityBox.method_1003(x, y, z, x + 1.0, y + 1.0, z + 1.0) ? shape.method_1096(x, y, z) : null;
      } else if (shape instanceof VoxelShapeCaster) {
         return ((VoxelShapeCaster)shape).intersects(entityBox, x, y, z) ? shape.method_1096(x, y, z) : null;
      } else {
         shape = shape.method_1096(x, y, z);
         return class_259.method_1074(shape, entityShape, class_247.field_16896) ? shape : null;
      }
   }

   private static int expandMin(int coord) {
      return coord - 1;
   }

   private static int expandMax(int coord) {
      return coord + 1;
   }

   private static boolean hasChunkSectionOversizedBlocks(class_2791 chunk, int chunkY) {
      if (!BlockStateFlags.ENABLED) {
         return true;
      } else {
         class_2826 section = chunk.method_12006()[chunkY];
         return section != null && ((BlockCountingSection)section).lithium$mayContainAny(BlockStateFlags.OVERSIZED_SHAPE);
      }
   }

   public List<class_265> collectAll() {
      ArrayList<class_265> collisions = new ArrayList<>();

      while (this.hasNext()) {
         collisions.add((class_265)this.next());
      }

      return collisions;
   }
}
