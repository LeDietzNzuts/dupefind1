package net.caffeinemc.mods.lithium.common.block.entity;

import net.minecraft.class_2338;
import net.minecraft.class_2680;
import net.minecraft.class_4538;

public interface ShapeUpdateHandlingBlockBehaviour {
   default void lithium$handleShapeUpdate(class_4538 world, class_2680 myBlockState, class_2338 myPos, class_2338 posFrom, class_2680 newState) {
   }
}
