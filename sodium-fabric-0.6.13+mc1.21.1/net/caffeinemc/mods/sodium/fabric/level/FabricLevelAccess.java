package net.caffeinemc.mods.sodium.fabric.level;

import net.caffeinemc.mods.sodium.client.services.PlatformLevelAccess;
import net.caffeinemc.mods.sodium.client.world.SodiumAuxiliaryLightManager;
import net.minecraft.class_2586;
import net.minecraft.class_2818;
import net.minecraft.class_4076;
import org.jetbrains.annotations.Nullable;

public class FabricLevelAccess implements PlatformLevelAccess {
   @Nullable
   @Override
   public Object getBlockEntityData(class_2586 blockEntity) {
      return blockEntity.getRenderData();
   }

   @Nullable
   @Override
   public SodiumAuxiliaryLightManager getLightManager(class_2818 chunk, class_4076 pos) {
      return null;
   }
}
