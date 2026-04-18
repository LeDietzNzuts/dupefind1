package noobanidus.mods.lootr.common.chunk;

import java.util.Collections;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import net.minecraft.class_1923;
import net.minecraft.class_1936;
import net.minecraft.class_1937;
import net.minecraft.class_2818;
import net.minecraft.class_3194;
import net.minecraft.class_5321;

public class LoadedChunks {
   private static final Map<class_5321<class_1937>, Set<class_1923>> LOADED_CHUNKS = new ConcurrentHashMap<>();

   public static void onChunkLoad(class_1936 level, class_2818 chunk) {
      if (!level.method_8608() && chunk.method_12225().method_14014(class_3194.field_44855)) {
         class_5321<class_1937> dimension = chunk.method_12200().method_27983();
         Set<class_1923> chunkSet = LOADED_CHUNKS.computeIfAbsent(dimension, k -> ConcurrentHashMap.newKeySet());
         chunkSet.add(chunk.method_12004());
      }
   }

   public static void onChunkUnload(class_1936 level, class_2818 chunk) {
      if (!level.method_8608()) {
         class_5321<class_1937> dimension = chunk.method_12200().method_27983();
         Set<class_1923> chunkSet = LOADED_CHUNKS.get(dimension);
         if (chunkSet != null) {
            chunkSet.remove(chunk.method_12004());
         }
      }
   }

   public static Set<class_1923> getLoadedChunks(class_5321<class_1937> dimension) {
      Set<class_1923> set = LOADED_CHUNKS.computeIfAbsent(dimension, k -> ConcurrentHashMap.newKeySet());
      return Collections.unmodifiableSet(set);
   }

   public static void clear() {
      LOADED_CHUNKS.clear();
   }
}
