package net.caffeinemc.mods.lithium.common.world.chunk;

import java.util.ArrayList;
import java.util.function.BiConsumer;
import net.caffeinemc.mods.lithium.common.tracking.block.ChunkSectionChangeCallback;
import net.caffeinemc.mods.lithium.mixin.util.accessors.LevelAccessor;
import net.minecraft.class_1923;
import net.minecraft.class_2818;
import net.minecraft.class_3218;

public class ChunkStatusTracker {
   private static final ArrayList<BiConsumer<class_3218, class_1923>> UNLOAD_CALLBACKS = new ArrayList<>();
   private static final ArrayList<BiConsumer<class_3218, class_2818>> LOAD_CALLBACKS = new ArrayList<>();

   public static void registerLoadCallback(BiConsumer<class_3218, class_2818> callback) {
      synchronized (LOAD_CALLBACKS) {
         LOAD_CALLBACKS.add(callback);
      }
   }

   public static void registerUnloadCallback(BiConsumer<class_3218, class_1923> callback) {
      synchronized (UNLOAD_CALLBACKS) {
         UNLOAD_CALLBACKS.add(callback);
      }
   }

   public static void onChunkAccessible(class_3218 serverLevel, class_2818 levelChunk) {
      if (((LevelAccessor)serverLevel).getField_17086() != Thread.currentThread()) {
         throw new IllegalStateException("ChunkStatusTracker.onChunkAccessible called on wrong thread!");
      } else {
         for (int i = 0; i < LOAD_CALLBACKS.size(); i++) {
            LOAD_CALLBACKS.get(i).accept(serverLevel, levelChunk);
         }
      }
   }

   public static void onChunkInaccessible(class_3218 serverLevel, class_1923 pos) {
      if (((LevelAccessor)serverLevel).getField_17086() != Thread.currentThread()) {
         throw new IllegalStateException("ChunkStatusTracker.onChunkInaccessible called on wrong thread!");
      } else {
         for (int i = 0; i < UNLOAD_CALLBACKS.size(); i++) {
            UNLOAD_CALLBACKS.get(i).accept(serverLevel, pos);
         }
      }
   }

   static {
      ChunkSectionChangeCallback.init();
   }
}
