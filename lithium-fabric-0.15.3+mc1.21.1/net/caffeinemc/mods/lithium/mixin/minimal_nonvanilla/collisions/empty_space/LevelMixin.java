package net.caffeinemc.mods.lithium.mixin.minimal_nonvanilla.collisions.empty_space;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import net.caffeinemc.mods.lithium.common.entity.LithiumEntityCollisions;
import net.caffeinemc.mods.lithium.common.shapes.VoxelShapeHelper;
import net.minecraft.class_1297;
import net.minecraft.class_1936;
import net.minecraft.class_1937;
import net.minecraft.class_238;
import net.minecraft.class_243;
import net.minecraft.class_265;
import net.minecraft.class_2784;
import net.minecraft.class_3532;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(class_1937.class)
public abstract class LevelMixin implements class_1936 {
   public Optional<class_243> method_33594(
      @Nullable class_1297 collidingEntity, class_265 collidingShape, class_243 originalPosition, double maxXOffset, double maxYOffset, double maxZOffset
   ) {
      if (collidingShape.method_1110()) {
         return Optional.empty();
      } else {
         class_238 collidingBox = collidingShape.method_1107();
         class_238 searchBox = collidingBox.method_1009(maxXOffset, maxYOffset, maxZOffset);
         List<class_265> blockCollisions = LithiumEntityCollisions.getBlockCollisions((class_1937)this, collidingEntity, searchBox);
         if (blockCollisions.isEmpty()) {
            return collidingShape.method_33661(originalPosition);
         } else {
            class_2784 worldBorder = this.method_8621();
            if (worldBorder != null) {
               double sideLength = Math.max(searchBox.method_17939(), searchBox.method_17941());
               double centerX = class_3532.method_16436(0.5, searchBox.field_1323, searchBox.field_1320);
               double centerZ = class_3532.method_16436(0.5, searchBox.field_1321, searchBox.field_1324);
               boolean worldBorderIsNearby = 2.0 + 2.0 * sideLength >= worldBorder.method_11961(centerX, centerZ);
               if (worldBorderIsNearby) {
                  blockCollisions.removeIf(voxelShape -> !worldBorder.method_11966(voxelShape.method_1107()));
               }
            }

            List<class_238> allCollisionBoxes = new ArrayList<>();

            for (class_265 blockCollision : blockCollisions) {
               for (class_238 box : blockCollision.method_1090()) {
                  class_238 foldedBox = box.method_1009(maxXOffset / 2.0, maxYOffset / 2.0, maxZOffset / 2.0);
                  allCollisionBoxes.add(foldedBox);
               }
            }

            return VoxelShapeHelper.getClosestPointTo(originalPosition, collidingShape, allCollisionBoxes);
         }
      }
   }
}
