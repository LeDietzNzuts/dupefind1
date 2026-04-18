package net.caffeinemc.mods.sodium.client.services;

import it.unimi.dsi.fastutil.longs.Long2ObjectMap;
import net.minecraft.class_2338;

public class SodiumModelDataContainer {
   private final Long2ObjectMap<SodiumModelData> modelDataMap;
   private final boolean isEmpty;

   public SodiumModelDataContainer(Long2ObjectMap<SodiumModelData> modelDataMap) {
      this.modelDataMap = modelDataMap;
      this.isEmpty = modelDataMap.isEmpty();
   }

   public SodiumModelData getModelData(class_2338 pos) {
      return (SodiumModelData)this.modelDataMap.getOrDefault(pos.method_10063(), SodiumModelData.EMPTY);
   }

   public boolean isEmpty() {
      return this.isEmpty;
   }
}
