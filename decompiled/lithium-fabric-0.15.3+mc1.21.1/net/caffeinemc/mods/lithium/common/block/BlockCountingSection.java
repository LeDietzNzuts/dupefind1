package net.caffeinemc.mods.lithium.common.block;

import net.minecraft.class_2680;

public interface BlockCountingSection {
   boolean lithium$mayContainAny(TrackedBlockStatePredicate var1);

   void lithium$trackBlockStateChange(class_2680 var1, class_2680 var2);
}
