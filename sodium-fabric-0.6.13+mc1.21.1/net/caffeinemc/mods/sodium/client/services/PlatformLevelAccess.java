package net.caffeinemc.mods.sodium.client.services;

import net.caffeinemc.mods.sodium.client.world.SodiumAuxiliaryLightManager;
import net.minecraft.class_2586;
import net.minecraft.class_2818;
import net.minecraft.class_4076;
import org.jetbrains.annotations.Nullable;

public interface PlatformLevelAccess {
   PlatformLevelAccess INSTANCE = Services.load(PlatformLevelAccess.class);

   static PlatformLevelAccess getInstance() {
      return INSTANCE;
   }

   @Nullable
   Object getBlockEntityData(class_2586 var1);

   @Nullable
   SodiumAuxiliaryLightManager getLightManager(class_2818 var1, class_4076 var2);
}
