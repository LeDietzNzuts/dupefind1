package net.caffeinemc.mods.sodium.client.model.light.data;

import it.unimi.dsi.fastutil.longs.Long2IntLinkedOpenHashMap;
import net.minecraft.class_1920;
import net.minecraft.class_2338;

public class HashLightDataCache extends LightDataAccess {
   private final Long2IntLinkedOpenHashMap map = new Long2IntLinkedOpenHashMap(1024, 0.5F);

   public HashLightDataCache(class_1920 level) {
      this.level = level;
   }

   @Override
   public int get(int x, int y, int z) {
      long key = class_2338.method_10064(x, y, z);
      int word = this.map.getAndMoveToFirst(key);
      if (word == 0) {
         if (this.map.size() > 1024) {
            this.map.removeLastInt();
         }

         this.map.put(key, word = this.compute(x, y, z));
      }

      return word;
   }

   public void clearCache() {
      this.map.clear();
   }
}
