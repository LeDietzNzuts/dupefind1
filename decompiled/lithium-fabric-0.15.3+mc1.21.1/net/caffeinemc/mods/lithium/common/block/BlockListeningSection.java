package net.caffeinemc.mods.lithium.common.block;

import net.caffeinemc.mods.lithium.common.tracking.block.SectionedBlockChangeTracker;
import net.minecraft.class_1937;

public interface BlockListeningSection {
   void lithium$addToCallback(ListeningBlockStatePredicate var1, SectionedBlockChangeTracker var2, long var3, class_1937 var5);

   void lithium$removeFromCallback(ListeningBlockStatePredicate var1, SectionedBlockChangeTracker var2);
}
