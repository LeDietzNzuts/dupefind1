package org.embeddedt.modernfix.world;

import java.util.ArrayList;
import java.util.List;
import net.minecraft.class_18;
import net.minecraft.class_1923;
import net.minecraft.class_2487;
import net.minecraft.class_2874;
import net.minecraft.class_3218;
import net.minecraft.class_4284;
import net.minecraft.class_6880;
import net.minecraft.class_18.class_8645;
import net.minecraft.class_7225.class_7874;

public class StrongholdLocationCache extends class_18 {
   private List<class_1923> chunkPosList = new ArrayList<>();

   public static class_8645<StrongholdLocationCache> factory(class_3218 serverLevel) {
      return new class_8645(StrongholdLocationCache::new, StrongholdLocationCache::load, class_4284.field_45078);
   }

   public List<class_1923> getChunkPosList() {
      return new ArrayList<>(this.chunkPosList);
   }

   public void setChunkPosList(List<class_1923> positions) {
      this.chunkPosList = new ArrayList<>(positions);
      this.method_80();
   }

   public static StrongholdLocationCache load(class_2487 arg, class_7874 provider) {
      StrongholdLocationCache cache = new StrongholdLocationCache();
      if (arg.method_10573("Positions", 12)) {
         long[] positions = arg.method_10565("Positions");

         for (long position : positions) {
            cache.chunkPosList.add(new class_1923(position));
         }
      }

      return cache;
   }

   public class_2487 method_75(class_2487 compoundTag, class_7874 provider) {
      long[] serialized = new long[this.chunkPosList.size()];

      for (int i = 0; i < this.chunkPosList.size(); i++) {
         class_1923 thePos = this.chunkPosList.get(i);
         serialized[i] = thePos.method_8324();
      }

      compoundTag.method_10564("Positions", serialized);
      return compoundTag;
   }

   public static String getFileId(class_6880<class_2874> dimensionType) {
      return "mfix_strongholds";
   }
}
