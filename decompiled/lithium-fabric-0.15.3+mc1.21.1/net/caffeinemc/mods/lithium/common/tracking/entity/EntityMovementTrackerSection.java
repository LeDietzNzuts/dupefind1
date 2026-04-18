package net.caffeinemc.mods.lithium.common.tracking.entity;

import net.minecraft.class_5568;
import net.minecraft.class_5573;

public interface EntityMovementTrackerSection {
   void lithium$addListener(SectionedEntityMovementTracker<?, ?> var1);

   void lithium$removeListener(class_5573<?> var1, SectionedEntityMovementTracker<?, ?> var2);

   void lithium$trackEntityMovement(int var1, long var2);

   long lithium$getChangeTime(int var1);

   <S, E extends class_5568> void lithium$listenToMovementOnce(SectionedEntityMovementTracker<E, S> var1, int var2);

   <S, E extends class_5568> void lithium$removeListenToMovementOnce(SectionedEntityMovementTracker<E, S> var1, int var2);
}
