package net.caffeinemc.mods.lithium.common.services;

import net.minecraft.class_2614;
import net.minecraft.class_2680;

public interface PlatformModCompat {
   PlatformModCompat INSTANCE = Services.load(PlatformModCompat.class);

   boolean canHopperInteractWithApiBlockInventory(class_2614 var1, class_2680 var2, boolean var3);
}
