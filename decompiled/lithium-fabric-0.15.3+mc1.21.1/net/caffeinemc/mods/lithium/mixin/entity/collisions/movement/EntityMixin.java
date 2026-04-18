package net.caffeinemc.mods.lithium.mixin.entity.collisions.movement;

import com.llamalad7.mixinextras.sugar.Share;
import com.llamalad7.mixinextras.sugar.ref.LocalBooleanRef;
import java.util.ArrayList;
import java.util.List;
import net.caffeinemc.mods.lithium.common.entity.LithiumEntityCollisions;
import net.caffeinemc.mods.lithium.common.entity.movement.ChunkAwareBlockCollisionSweeper;
import net.caffeinemc.mods.lithium.common.util.collections.LazyList;
import net.minecraft.class_1297;
import net.minecraft.class_1937;
import net.minecraft.class_238;
import net.minecraft.class_243;
import net.minecraft.class_259;
import net.minecraft.class_265;
import net.minecraft.class_2350.class_2351;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.At.Shift;

@Mixin(class_1297.class)
public abstract class EntityMixin {
   @Shadow
   public abstract class_1937 method_37908();

   @Shadow
   public abstract class_238 method_5829();

   @Redirect(
      method = "method_17835(Lnet/minecraft/class_243;)Lnet/minecraft/class_243;",
      at = @At(value = "INVOKE", target = "Lnet/minecraft/class_1937;method_20743(Lnet/minecraft/class_1297;Lnet/minecraft/class_238;)Ljava/util/List;")
   )
   private List<class_265> postponeGetEntities(
      class_1937 world, class_1297 entity, class_238 box, @Share("requireAddEntities") LocalBooleanRef requireAddEntities
   ) {
      requireAddEntities.set(true);
      return new ArrayList<>();
   }

   @Redirect(
      method = "method_17835(Lnet/minecraft/class_243;)Lnet/minecraft/class_243;",
      at = @At(
         value = "INVOKE",
         target = "Lnet/minecraft/class_1297;method_20736(Lnet/minecraft/class_1297;Lnet/minecraft/class_243;Lnet/minecraft/class_238;Lnet/minecraft/class_1937;Ljava/util/List;)Lnet/minecraft/class_243;"
      )
   )
   private class_243 collideMovementWithPostponedGetEntities(
      @Nullable class_1297 entity,
      class_243 movement,
      class_238 entityBoundingBox,
      class_1937 world,
      List<class_265> entityCollisions,
      @Share("requireAddEntities") LocalBooleanRef requireAddEntities
   ) {
      return lithium$CollideMovement(entity, movement, entityBoundingBox, world, entityCollisions, requireAddEntities);
   }

   @ModifyVariable(
      method = "method_17835(Lnet/minecraft/class_243;)Lnet/minecraft/class_243;",
      at = @At(
         value = "INVOKE",
         shift = Shift.BEFORE,
         target = "Lnet/minecraft/class_1297;method_59920(Lnet/minecraft/class_1297;Lnet/minecraft/class_1937;Ljava/util/List;Lnet/minecraft/class_238;)Ljava/util/List;"
      )
   )
   private List<class_265> collectEntities(List<class_265> entityCollisions, @Share("requireAddEntities") LocalBooleanRef requireAddEntities) {
      if (requireAddEntities.get()) {
         requireAddEntities.set(false);
         ArrayList<class_265> collisions = entityCollisions instanceof ArrayList ? (ArrayList)entityCollisions : new ArrayList<>(entityCollisions);
         LithiumEntityCollisions.appendEntityCollisions(collisions, this.method_37908(), (class_1297)this, this.method_5829());
         return collisions;
      } else {
         return entityCollisions;
      }
   }

   @Overwrite
   public static class_243 method_20736(
      @Nullable class_1297 entity, class_243 movement, class_238 entityBoundingBox, class_1937 world, List<class_265> entityCollisions
   ) {
      return lithium$CollideMovement(entity, movement, entityBoundingBox, world, entityCollisions, null);
   }

   @Unique
   private static class_243 lithium$CollideMovement(
      @Nullable class_1297 entity,
      class_243 movement,
      class_238 entityBoundingBox,
      class_1937 world,
      List<class_265> entityCollisions,
      LocalBooleanRef requireAddEntities
   ) {
      double movementX = movement.field_1352;
      double movementY = movement.field_1351;
      double movementZ = movement.field_1350;
      boolean isSingleAxisMovement = (movementX == 0.0 ? 0 : 1) + (movementY == 0.0 ? 0 : 1) + (movementZ == 0.0 ? 0 : 1) == 1;
      if (movementY < 0.0) {
         class_265 voxelShape = LithiumEntityCollisions.getSupportingCollisionForEntity(world, entity, entityBoundingBox);
         if (voxelShape != null) {
            double v = voxelShape.method_1108(class_2351.field_11052, entityBoundingBox, movementY);
            if (v == 0.0) {
               if (isSingleAxisMovement) {
                  return class_243.field_1353;
               }

               movementY = 0.0;
               isSingleAxisMovement = (movementX == 0.0 ? 0 : 1) + (movementZ == 0.0 ? 0 : 1) == 1;
            }
         }
      }

      class_238 movementSpace;
      if (isSingleAxisMovement) {
         movementSpace = LithiumEntityCollisions.getSmallerBoxForSingleAxisMovement(movement, entityBoundingBox, movementY, movementX, movementZ);
      } else {
         movementSpace = entityBoundingBox.method_18804(movement);
      }

      boolean shouldAddEntities = requireAddEntities != null && requireAddEntities.get();
      boolean shouldAddWorldBorder = true;
      boolean shouldAddLastBlock = true;
      ChunkAwareBlockCollisionSweeper blockCollisionSweeper = new ChunkAwareBlockCollisionSweeper(world, entity, movementSpace, true);
      LazyList<class_265> blockCollisions = new LazyList<>(new ArrayList<>(), blockCollisionSweeper);
      ArrayList<class_265> worldBorderAndLastBlockCollision = new ArrayList<>(2);
      if (movementY != 0.0) {
         movementY = class_259.method_1085(class_2351.field_11052, entityBoundingBox, blockCollisions, movementY);
         if (movementY != 0.0) {
            shouldAddEntities = LithiumEntityCollisions.addEntityCollisionsIfRequired(shouldAddEntities, entity, world, entityCollisions, movementSpace);
            shouldAddWorldBorder = LithiumEntityCollisions.addWorldBorderCollisionIfRequired(
               shouldAddWorldBorder, entity, worldBorderAndLastBlockCollision, movementSpace
            );
            shouldAddLastBlock = LithiumEntityCollisions.addLastBlockCollisionIfRequired(
               shouldAddLastBlock, blockCollisionSweeper, worldBorderAndLastBlockCollision
            );
            if (!entityCollisions.isEmpty()) {
               movementY = class_259.method_1085(class_2351.field_11052, entityBoundingBox, entityCollisions, movementY);
            }

            if (!worldBorderAndLastBlockCollision.isEmpty()) {
               movementY = class_259.method_1085(class_2351.field_11052, entityBoundingBox, worldBorderAndLastBlockCollision, movementY);
            }

            if (movementY != 0.0) {
               entityBoundingBox = entityBoundingBox.method_989(0.0, movementY, 0.0);
            }
         }
      }

      boolean zMovementBiggerThanXMovement = Math.abs(movementX) < Math.abs(movementZ);
      if (zMovementBiggerThanXMovement) {
         movementZ = class_259.method_1085(class_2351.field_11051, entityBoundingBox, blockCollisions, movementZ);
         if (movementZ != 0.0) {
            shouldAddEntities = LithiumEntityCollisions.addEntityCollisionsIfRequired(shouldAddEntities, entity, world, entityCollisions, movementSpace);
            shouldAddWorldBorder = LithiumEntityCollisions.addWorldBorderCollisionIfRequired(
               shouldAddWorldBorder, entity, worldBorderAndLastBlockCollision, movementSpace
            );
            shouldAddLastBlock = LithiumEntityCollisions.addLastBlockCollisionIfRequired(
               shouldAddLastBlock, blockCollisionSweeper, worldBorderAndLastBlockCollision
            );
            if (!entityCollisions.isEmpty()) {
               movementZ = class_259.method_1085(class_2351.field_11051, entityBoundingBox, entityCollisions, movementZ);
            }

            if (!worldBorderAndLastBlockCollision.isEmpty()) {
               movementZ = class_259.method_1085(class_2351.field_11051, entityBoundingBox, worldBorderAndLastBlockCollision, movementZ);
            }

            if (movementZ != 0.0) {
               entityBoundingBox = entityBoundingBox.method_989(0.0, 0.0, movementZ);
            }
         }
      }

      if (movementX != 0.0) {
         movementX = class_259.method_1085(class_2351.field_11048, entityBoundingBox, blockCollisions, movementX);
         if (movementX != 0.0) {
            shouldAddEntities = LithiumEntityCollisions.addEntityCollisionsIfRequired(shouldAddEntities, entity, world, entityCollisions, movementSpace);
            shouldAddWorldBorder = LithiumEntityCollisions.addWorldBorderCollisionIfRequired(
               shouldAddWorldBorder, entity, worldBorderAndLastBlockCollision, movementSpace
            );
            shouldAddLastBlock = LithiumEntityCollisions.addLastBlockCollisionIfRequired(
               shouldAddLastBlock, blockCollisionSweeper, worldBorderAndLastBlockCollision
            );
            if (!entityCollisions.isEmpty()) {
               movementX = class_259.method_1085(class_2351.field_11048, entityBoundingBox, entityCollisions, movementX);
            }

            if (!worldBorderAndLastBlockCollision.isEmpty()) {
               movementX = class_259.method_1085(class_2351.field_11048, entityBoundingBox, worldBorderAndLastBlockCollision, movementX);
            }

            if (movementX != 0.0) {
               entityBoundingBox = entityBoundingBox.method_989(movementX, 0.0, 0.0);
            }
         }
      }

      if (!zMovementBiggerThanXMovement && movementZ != 0.0) {
         movementZ = class_259.method_1085(class_2351.field_11051, entityBoundingBox, blockCollisions, movementZ);
         if (movementZ != 0.0) {
            shouldAddEntities = LithiumEntityCollisions.addEntityCollisionsIfRequired(shouldAddEntities, entity, world, entityCollisions, movementSpace);
            shouldAddWorldBorder = LithiumEntityCollisions.addWorldBorderCollisionIfRequired(
               shouldAddWorldBorder, entity, worldBorderAndLastBlockCollision, movementSpace
            );
            shouldAddLastBlock = LithiumEntityCollisions.addLastBlockCollisionIfRequired(
               shouldAddLastBlock, blockCollisionSweeper, worldBorderAndLastBlockCollision
            );
            if (!entityCollisions.isEmpty()) {
               movementZ = class_259.method_1085(class_2351.field_11051, entityBoundingBox, entityCollisions, movementZ);
            }

            if (!worldBorderAndLastBlockCollision.isEmpty()) {
               movementZ = class_259.method_1085(class_2351.field_11051, entityBoundingBox, worldBorderAndLastBlockCollision, movementZ);
            }
         }
      }

      if (requireAddEntities != null && !shouldAddEntities) {
         requireAddEntities.set(false);
      }

      return new class_243(movementX, movementY, movementZ);
   }
}
