package noobanidus.mods.lootr.common.data;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Stream;
import net.minecraft.class_1657;
import net.minecraft.class_18;
import net.minecraft.class_1923;
import net.minecraft.class_2487;
import net.minecraft.class_2586;
import net.minecraft.class_26;
import net.minecraft.class_3215;
import net.minecraft.class_3218;
import net.minecraft.class_3222;
import net.minecraft.class_5218;
import net.minecraft.class_18.class_8645;
import net.minecraft.class_7225.class_7874;
import net.minecraft.server.MinecraftServer;
import noobanidus.mods.lootr.common.api.LootrAPI;
import noobanidus.mods.lootr.common.api.data.ILootrInfoProvider;
import noobanidus.mods.lootr.common.api.data.LootFiller;
import noobanidus.mods.lootr.common.api.data.NewTickingData;
import noobanidus.mods.lootr.common.api.data.TickingData;
import noobanidus.mods.lootr.common.api.data.blockentity.ILootrBlockEntity;
import noobanidus.mods.lootr.common.api.data.entity.ILootrEntity;
import noobanidus.mods.lootr.common.api.data.inventory.ILootrInventory;
import noobanidus.mods.lootr.common.chunk.LoadedChunks;
import noobanidus.mods.lootr.common.command.IOUtil;
import noobanidus.mods.lootr.common.mixin.accessor.AccessorMixinDimensionDataStorage;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.ApiStatus.Internal;

public class DataStorage {
   @Deprecated
   public static final String ADVANCEMENTS = "lootr/Lootr-AdvancementData";
   @Deprecated
   public static final String DECAYS = "lootr/Lootr-DecayData";
   @Deprecated
   public static final String REFRESHES = "lootr/Lootr-RefreshData";

   @Internal
   @Nullable
   public static class_26 getDataStorage() {
      MinecraftServer server = LootrAPI.getServer();
      if (server == null) {
         LootrAPI.LOG.error("MinecraftServer is null at this stage; Lootr cannot fetch data storage.");
         return null;
      } else {
         class_3218 overworld = server.method_30002();
         if (overworld == null) {
            LootrAPI.LOG.error("The Overworld is null at this stage; Lootr cannot fetch data storage.");
            return null;
         } else {
            return overworld.method_17983();
         }
      }
   }

   @Deprecated
   @Internal
   public static boolean isAwarded(ILootrInfoProvider provider, class_3222 player) {
      return isAwarded(provider.getInfoUUID(), player);
   }

   @Deprecated
   @Internal
   public static boolean isAwarded(UUID uuid, class_3222 player) {
      return false;
   }

   @Deprecated
   @Internal
   public static void award(ILootrInfoProvider provider, class_3222 player) {
      award(provider.getInfoUUID(), player);
   }

   @Deprecated
   @Internal
   public static void award(UUID id, class_3222 player) {
   }

   @Internal
   public static int getDecayValue(ILootrInfoProvider provider) {
      class_26 manager = getDataStorage();
      if (manager != null) {
         MinecraftServer server = LootrAPI.getServer();
         TickingData oldData = (TickingData)manager.method_17924(TickingData.FACTORY, "lootr/Lootr-DecayData");
         NewTickingData data = NewTickingData.getDecayData();
         data.migrateOldData(server, oldData);
         return (int)data.howLongUntilComplete(server, provider.getInfoUUID());
      } else if (provider.getInfoLevel() != null && (provider.getInfoLevel() == null || !provider.getInfoLevel().method_8608())) {
         LootrAPI.LOG.error("DataStorage is null at this stage; Lootr cannot determine the decay value for {}.", provider.getInfoUUID());
         return -1;
      } else {
         return -1;
      }
   }

   @Internal
   public static boolean isDecayed(ILootrInfoProvider provider) {
      return getDecayValue(provider) == 0;
   }

   @Internal
   public static void setDecaying(ILootrInfoProvider provider) {
      class_26 manager = getDataStorage();
      if (manager != null) {
         TickingData oldData = (TickingData)manager.method_17924(TickingData.FACTORY, "lootr/Lootr-DecayData");
         NewTickingData data = NewTickingData.getDecayData();
         MinecraftServer server = LootrAPI.getServer();
         data.migrateOldData(server, oldData);
         data.setCompletesIn(server, provider.getInfoUUID(), LootrAPI.getDecayValue());
      } else if (provider.getInfoLevel() != null && (provider.getInfoLevel() == null || !provider.getInfoLevel().method_8608())) {
         LootrAPI.LOG.error("DataStorage is null at this stage; Lootr cannot set the decay value for {}.", provider.getInfoUUID());
      }
   }

   @Internal
   public static void removeDecayed(ILootrInfoProvider provider) {
   }

   @Deprecated
   @Internal
   public static void doTick() {
   }

   @Internal
   public static int getRefreshValue(ILootrInfoProvider provider) {
      class_26 manager = getDataStorage();
      if (manager != null) {
         TickingData oldData = (TickingData)manager.method_17924(TickingData.FACTORY, "lootr/Lootr-RefreshData");
         NewTickingData data = NewTickingData.getRefreshData();
         MinecraftServer server = LootrAPI.getServer();
         data.migrateOldData(server, oldData);
         return (int)data.howLongUntilComplete(server, provider.getInfoUUID());
      } else if (provider.getInfoLevel() != null && (provider.getInfoLevel() == null || !provider.getInfoLevel().method_8608())) {
         LootrAPI.LOG.error("DataStorage is null at this stage; Lootr cannot determine the refresh value for {}.", provider.getInfoUUID());
         return -1;
      } else {
         return -1;
      }
   }

   @Internal
   public static boolean isRefreshed(ILootrInfoProvider provider) {
      return getRefreshValue(provider) == 0;
   }

   @Internal
   public static void setRefreshing(ILootrInfoProvider provider) {
      class_26 manager = getDataStorage();
      if (manager != null) {
         TickingData data = (TickingData)manager.method_17924(TickingData.FACTORY, "lootr/Lootr-RefreshData");
         NewTickingData newData = NewTickingData.getRefreshData();
         MinecraftServer server = LootrAPI.getServer();
         newData.migrateOldData(server, data);
         newData.setCompletesIn(server, provider.getInfoUUID(), LootrAPI.getRefreshValue());
      } else if (provider.getInfoLevel() != null && (provider.getInfoLevel() == null || !provider.getInfoLevel().method_8608())) {
         LootrAPI.LOG.error("DataStorage is null at this stage; Lootr cannot set the refresh value for {}.", provider.getInfoUUID());
      }
   }

   @Internal
   public static void removeRefreshed(ILootrInfoProvider provider) {
   }

   @Internal
   public static LootrSavedData getData(ILootrInfoProvider provider) {
      class_26 manager = getDataStorage();
      if (manager != null) {
         LootrSavedData result = (LootrSavedData)manager.method_17924(
            new class_8645(LootrSavedData.fromInfo(provider), LootrSavedData::load, null), provider.getInfoKey()
         );
         result.update(provider);
         return result;
      } else if (provider.getInfoLevel() != null && (provider.getInfoLevel() == null || !provider.getInfoLevel().method_8608())) {
         LootrAPI.LOG
            .error(
               "DataStorage is null at this stage; Lootr cannot fetch data for {} at {} with ID {} and cannot continue.",
               provider.getInfoDimension(),
               provider.getInfoPos(),
               provider.getInfoUUID()
            );
         return null;
      } else {
         return null;
      }
   }

   @Nullable
   public static ILootrInventory getInventory(ILootrInfoProvider provider, class_3222 player, LootFiller filler) {
      LootrSavedData data = getData(provider);
      return data == null ? null : data.getOrCreateInventory(provider, player, filler);
   }

   @Internal
   public static boolean clearInventories(class_1657 player) {
      return clearInventories(player.method_5667());
   }

   public static Set<String> getAllLootrFiles() {
      class_26 data = getDataStorage();
      if (data == null) {
         return Collections.emptySet();
      } else {
         MinecraftServer server = LootrAPI.getServer();
         if (server == null) {
            LootrAPI.LOG.error("MinecraftServer is null at this stage; Lootr cannot clear inventories.");
            return Collections.emptySet();
         } else {
            Path dataPath = server.method_27050(new class_5218("data")).resolve("lootr");
            Set<String> files = new HashSet<>();

            for (String cache : ((AccessorMixinDimensionDataStorage)data).getCache().keySet()) {
               if (cache.startsWith("lootr/") && !cache.startsWith("lootr/Lootr-") && !cache.startsWith("lootr/lootr-")) {
                  files.add(cache);
               }
            }

            try {
               try (Stream<Path> paths = Files.walk(dataPath)) {
                  paths.forEach(path -> {
                     if (Files.isRegularFile(path)) {
                        String fileName = path.getFileName().toString();
                        if (fileName.startsWith("lootr-") || fileName.startsWith("Lootr-")) {
                           return;
                        }

                        files.add("lootr/" + fileName.charAt(0) + "/" + fileName.substring(0, 2) + "/" + fileName.replace(".dat", ""));
                     }
                  });
               }

               return files;
            } catch (IOException var9) {
               return files;
            }
         }
      }
   }

   @Internal
   public static boolean clearInventories(UUID id) {
      class_26 data = getDataStorage();
      if (data == null) {
         return false;
      } else {
         MinecraftServer server = LootrAPI.getServer();
         if (server == null) {
            LootrAPI.LOG.error("MinecraftServer is null at this stage; Lootr cannot clear inventories.");
            return false;
         } else {
            Set<String> files = getAllLootrFiles();
            int count = 0;

            for (String file : files) {
               class_18 datum = data.method_20786(new class_8645(() -> DataStorage.LootrDummyData.INSTANCE, LootrSavedData::load, null), file);
               if (datum == DataStorage.LootrDummyData.INSTANCE) {
                  LootrAPI.LOG.error("Failed to load data for {}, removing from cache.", file);
                  ((AccessorMixinDimensionDataStorage)data).getCache().remove(file);
               } else if (datum instanceof LootrSavedData lootrSavedData) {
                  if (lootrSavedData.hasBeenOpened() && lootrSavedData.clearInventories(id)) {
                     count++;
                     class_3218 level = server.method_3847(lootrSavedData.getInfoDimension());
                     if (level != null) {
                        class_3215 chunkCache = level.method_14178();
                        class_1923 chunkPos = new class_1923(lootrSavedData.getInfoPos());
                        if (chunkCache.method_12123(chunkPos.field_9181, chunkPos.field_9180)
                           && LoadedChunks.getLoadedChunks(lootrSavedData.getInfoDimension()).contains(chunkPos)) {
                           ILootrInfoProvider provider = null;
                           if (lootrSavedData.isEntity()) {
                              if (level.method_14190(lootrSavedData.getInfoUUID()) instanceof ILootrEntity cart) {
                                 provider = cart;
                              }
                           } else {
                              class_2586 entity = level.method_8321(lootrSavedData.getInfoPos());
                              ILootrBlockEntity var15 = LootrAPI.resolveBlockEntity(entity);
                              if (var15 instanceof ILootrBlockEntity) {
                                 provider = var15;
                              }
                           }

                           if (provider != null) {
                              provider.removeVisualOpener(id);
                              provider.performClose();
                              provider.performUpdate();
                           }
                        }
                     }
                  }
               } else {
                  LootrAPI.LOG.error("Data for {} is not a LootrSavedData instance.", file);
                  ((AccessorMixinDimensionDataStorage)data).getCache().remove(file);
               }
            }

            if (count > 0) {
               data.method_125();
               LootrAPI.LOG.info("Cleared {} inventories for player UUID {}", count, id.toString());
               return true;
            } else {
               return false;
            }
         }
      }
   }

   @Internal
   public static int cullInventories() {
      class_26 data = getDataStorage();
      if (data == null) {
         return 0;
      } else {
         MinecraftServer server = LootrAPI.getServer();
         if (server == null) {
            LootrAPI.LOG.error("MinecraftServer is null at this stage; Lootr cannot clear inventories.");
            return 0;
         } else {
            Set<String> files = getAllLootrFiles();
            Set<String> filesToDelete = new HashSet<>();

            for (String file : files) {
               class_18 datum = data.method_20786(new class_8645(() -> DataStorage.LootrDummyData.INSTANCE, LootrSavedData::load, null), file);
               if (datum == DataStorage.LootrDummyData.INSTANCE) {
                  LootrAPI.LOG.error("Failed to load data for {}, removing from cache.", file);
                  ((AccessorMixinDimensionDataStorage)data).getCache().remove(file);
               } else if (datum instanceof LootrSavedData lootrSavedData) {
                  if (lootrSavedData.canBeCulled()) {
                     filesToDelete.add(file);
                     ((AccessorMixinDimensionDataStorage)data).getCache().remove(file);
                  }
               } else {
                  LootrAPI.LOG.error("Data for {} is not a LootrSavedData instance.", file);
                  ((AccessorMixinDimensionDataStorage)data).getCache().remove(file);
               }
            }

            if (!filesToDelete.isEmpty()) {
               IOUtil.cullSavedDataAsync(server, filesToDelete);
               LootrAPI.LOG.info("Culling {} inventories.", filesToDelete.size());
            }

            return filesToDelete.size();
         }
      }
   }

   private static class LootrDummyData extends class_18 {
      public static final DataStorage.LootrDummyData INSTANCE = new DataStorage.LootrDummyData();

      public LootrDummyData() {
      }

      public class_2487 method_75(class_2487 p_77763_, class_7874 p_323640_) {
         return null;
      }
   }
}
