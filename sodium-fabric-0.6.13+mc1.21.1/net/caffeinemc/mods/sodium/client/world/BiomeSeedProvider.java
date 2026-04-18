package net.caffeinemc.mods.sodium.client.world;

import net.minecraft.class_638;

public interface BiomeSeedProvider {
   static long getBiomeZoomSeed(class_638 level) {
      return ((BiomeSeedProvider)level).sodium$getBiomeZoomSeed();
   }

   long sodium$getBiomeZoomSeed();
}
