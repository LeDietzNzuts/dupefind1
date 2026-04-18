package net.p3pp3rf1y.sophisticatedcore.event.common;

import javax.annotation.Nullable;
import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.class_1266;
import net.minecraft.class_1297;
import net.minecraft.class_1315;
import net.minecraft.class_3730;
import net.minecraft.class_5425;

public interface MobSpawnEvents {
   Event<MobSpawnEvents.After> AFTER_FINALIZE_SPAWN = EventFactory.createArrayBacked(MobSpawnEvents.After.class, callbacks -> finalizeEvent -> {
      for (MobSpawnEvents.After event : callbacks) {
         event.onAfterFinalizeSpawn(finalizeEvent);
      }
   });

   @FunctionalInterface
   public interface After {
      void onAfterFinalizeSpawn(MobSpawnEvents.FinalizeSpawn var1);
   }

   public static class FinalizeSpawn {
      private final class_1297 entity;
      private final class_5425 level;
      private final class_1266 difficulty;
      private final class_3730 spawnType;
      private final class_1315 spawnGroupData;

      public FinalizeSpawn(class_1297 entity, class_5425 level, class_1266 difficulty, class_3730 spawnType, @Nullable class_1315 spawnGroupData) {
         this.entity = entity;
         this.level = level;
         this.difficulty = difficulty;
         this.spawnType = spawnType;
         this.spawnGroupData = spawnGroupData;
      }

      public class_1297 getEntity() {
         return this.entity;
      }

      public class_5425 getLevel() {
         return this.level;
      }

      public class_1266 getDifficulty() {
         return this.difficulty;
      }

      public class_3730 getMobSpawnType() {
         return this.spawnType;
      }

      @Nullable
      public class_1315 getSpawnGroupData() {
         return this.spawnGroupData;
      }
   }
}
