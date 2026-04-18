package net.caffeinemc.mods.lithium.common.block.entity.inventory_comparator_tracking;

import net.caffeinemc.mods.lithium.common.util.DirectionConstants;
import net.caffeinemc.mods.lithium.common.world.blockentity.BlockEntityGetter;
import net.minecraft.class_1263;
import net.minecraft.class_1937;
import net.minecraft.class_2246;
import net.minecraft.class_2338;
import net.minecraft.class_2343;
import net.minecraft.class_2350;
import net.minecraft.class_2586;
import net.minecraft.class_2680;
import net.minecraft.class_2338.class_2339;

public class ComparatorTracking {
   public static void notifyNearbyBlockEntitiesAboutNewComparator(class_1937 world, class_2338 pos) {
      class_2339 searchPos = new class_2339();

      for (class_2350 searchDirection : DirectionConstants.HORIZONTAL) {
         for (int searchOffset = 1; searchOffset <= 2; searchOffset++) {
            searchPos.method_10101(pos);
            searchPos.method_10104(searchDirection, searchOffset);
            class_2680 blockState = world.method_8320(searchPos);
            if (blockState.method_26204() instanceof class_2343) {
               class_2586 blockEntity = ((BlockEntityGetter)world).lithium$getLoadedExistingBlockEntity(searchPos);
               if (blockEntity instanceof class_1263 && blockEntity instanceof ComparatorTracker comparatorTracker) {
                  comparatorTracker.lithium$onComparatorAdded(searchDirection, searchOffset);
               }
            }
         }
      }
   }

   public static boolean findNearbyComparators(class_1937 world, class_2338 pos) {
      class_2339 searchPos = new class_2339();

      for (class_2350 searchDirection : DirectionConstants.HORIZONTAL) {
         for (int searchOffset = 1; searchOffset <= 2; searchOffset++) {
            searchPos.method_10101(pos);
            searchPos.method_10104(searchDirection, searchOffset);
            class_2680 blockState = world.method_8320(searchPos);
            if (blockState.method_27852(class_2246.field_10377)) {
               return true;
            }
         }
      }

      return false;
   }
}
