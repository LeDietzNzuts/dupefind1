package noobanidus.mods.lootr.common.block.entity;

import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import net.minecraft.class_1799;
import net.minecraft.class_1923;
import net.minecraft.class_1937;
import net.minecraft.class_2338;
import net.minecraft.class_2378;
import net.minecraft.class_2586;
import net.minecraft.class_2680;
import net.minecraft.class_2802;
import net.minecraft.class_3195;
import net.minecraft.class_3218;
import net.minecraft.class_52;
import net.minecraft.class_5321;
import net.minecraft.class_7924;
import net.minecraft.server.MinecraftServer;
import noobanidus.mods.lootr.common.api.DataToCopy;
import noobanidus.mods.lootr.common.api.LootrAPI;
import noobanidus.mods.lootr.common.api.LootrTags;
import noobanidus.mods.lootr.common.api.PlatformAPI;
import noobanidus.mods.lootr.common.api.adapter.ILootrDataAdapter;
import noobanidus.mods.lootr.common.api.data.blockentity.ILootrBlockEntity;
import noobanidus.mods.lootr.common.chunk.LoadedChunks;
import org.jetbrains.annotations.Nullable;

public final class BlockEntityTicker {
   private static final Map<class_5321<class_1937>, BlockEntityTicker> TICKERS = new Object2ObjectOpenHashMap();
   private final class_5321<class_1937> levelKey;
   private final Map<class_1923, BlockEntityTicker.Entry> blockEntityEntries = new Object2ObjectOpenHashMap();
   private final Map<class_1923, BlockEntityTicker.Entry> pendingEntries = new Object2ObjectOpenHashMap();

   private BlockEntityTicker(class_5321<class_1937> levelKey) {
      this.levelKey = levelKey;
   }

   public static void addEntity(class_2586 entity, class_1937 level, class_1923 chunkPos) {
      if (!LootrAPI.isDisabled()) {
         class_5321<class_1937> dimension = getServerDimensionIfValid(level);
         if (dimension != null) {
            BlockEntityTicker ticker;
            synchronized (TICKERS) {
               ticker = TICKERS.computeIfAbsent(dimension, BlockEntityTicker::new);
            }

            ticker.addEntity(level, entity, chunkPos);
         }
      }
   }

   private void addEntity(class_1937 level, class_2586 entity, class_1923 chunkPos) {
      if (LootrAPI.isWorldBorderSafe(level, chunkPos)) {
         if (isValidEntity(entity)) {
            synchronized (this.pendingEntries) {
               BlockEntityTicker.Entry previousEntry = this.pendingEntries.get(chunkPos);
               if (previousEntry != null) {
                  previousEntry.entityPositions.add(entity.method_11016());
               } else {
                  HashSet<class_2338> entityPositions = new HashSet<>();
                  entityPositions.add(entity.method_11016());
                  BlockEntityTicker.Entry entry = new BlockEntityTicker.Entry(chunkPos, entityPositions);
                  this.pendingEntries.put(chunkPos, entry);
               }
            }
         }
      }
   }

   private static boolean isValidEntity(class_2586 entity) {
      if (LootrTags.BlockEntity.isTagged(entity, LootrTags.BlockEntity.CONVERT_BLACKLIST)) {
         return false;
      } else if (!(entity instanceof ILootrBlockEntity) && !(LootrAPI.resolveBlockEntity(entity) instanceof ILootrBlockEntity)) {
         ILootrDataAdapter<class_2586> adapter = LootrAPI.getAdapter(entity);
         return adapter != null;
      } else {
         return false;
      }
   }

   public static boolean isValidEntityFull(class_2586 entity) {
      if (LootrAPI.isDisabled()) {
         return false;
      } else if (LootrTags.BlockEntity.isTagged(entity, LootrTags.BlockEntity.CONVERT_BLACKLIST)) {
         return false;
      } else {
         class_1937 level = entity.method_10997();
         class_2338 pos = entity.method_11016();
         if (level == null) {
            return false;
         } else if (!level.method_8608() && level.method_8503() != null && LootrAPI.getServer() != null && level instanceof class_3218 serverLevel) {
            if (!(entity instanceof ILootrBlockEntity) && !(LootrAPI.resolveBlockEntity(entity) instanceof ILootrBlockEntity)) {
               MinecraftServer server = level.method_8503();
               if (!server.method_18854()) {
                  LootrAPI.LOG.error("Called isValidEntityFull on a non-server thread for {} in {}", entity, level.method_27983());
                  return false;
               } else if (LootrAPI.isDimensionBlocked(serverLevel.method_27983())) {
                  return false;
               } else if (!LootrAPI.isWorldBorderSafe(level, pos)) {
                  return false;
               } else if (LootrAPI.replacementBlockState(entity.method_11010()) == null) {
                  return false;
               } else {
                  ILootrDataAdapter<class_2586> adapter = LootrAPI.getAdapter(entity);
                  if (adapter == null) {
                     return false;
                  } else {
                     class_5321<class_52> lootTable = adapter.getLootTable(entity);
                     if (lootTable == null) {
                        return false;
                     } else if (LootrAPI.isLootTableBlacklisted(lootTable)) {
                        return false;
                     } else {
                        BlockEntityTicker.Entry testEntry = new BlockEntityTicker.Entry(new class_1923(pos), Set.of(pos));
                        Set<class_1923> loadedChunks = LoadedChunks.getLoadedChunks(level.method_27983());
                        return testEntry.getChunkLoadStatus(serverLevel, loadedChunks) == BlockEntityTicker.ChunkLoadStatus.COMPLETE;
                     }
                  }
               }
            } else {
               return false;
            }
         } else {
            return false;
         }
      }
   }

   public static void onServerTick(MinecraftServer server) {
      if (!LootrAPI.isDisabled()) {
         for (BlockEntityTicker ticker : TICKERS.values()) {
            class_3218 level = server.method_3847(ticker.levelKey);
            if (level != null) {
               ticker.onServerLevelTick(level);
            }
         }
      }
   }

   private void onServerLevelTick(class_3218 level) {
      Set<class_1923> loadedChunks = LoadedChunks.getLoadedChunks(level.method_27983());
      Iterator<BlockEntityTicker.Entry> iterator = this.blockEntityEntries.values().iterator();

      while (iterator.hasNext()) {
         BlockEntityTicker.Entry entry = iterator.next();
         switch (entry.getChunkLoadStatus(level, loadedChunks)) {
            case UNLOADED:
               iterator.remove();
            case SURROUNDING_CHUNKS_NOT_LOADED:
            case NOT_FULLY_LOADED:
            default:
               break;
            case COMPLETE:
               replaceEntitiesInChunk(level, entry);
               iterator.remove();
         }
      }

      synchronized (this.pendingEntries) {
         for (BlockEntityTicker.Entry entry : this.pendingEntries.values()) {
            this.blockEntityEntries.merge(entry.chunkPos, entry, (entry1, entry2) -> {
               entry1.entityPositions.addAll(entry2.entityPositions);
               return (BlockEntityTicker.Entry)entry1;
            });
         }

         this.pendingEntries.clear();
      }
   }

   private static boolean checkStructureValidity(class_3218 level, class_1923 chunkPos, class_2338 position) {
      if (!level.method_8503().method_27728().method_28057().method_28029()) {
         return true;
      } else {
         class_2378<class_3195> registry = level.method_30349().method_30530(class_7924.field_41246);
         if (registry.method_40266(LootrTags.Structure.STRUCTURE_BLACKLIST).filter(tag -> tag.method_40247() != 0).isPresent()) {
            return !LootrAPI.isTaggedStructurePresent(level, chunkPos, LootrTags.Structure.STRUCTURE_BLACKLIST, position);
         } else {
            return registry.method_40266(LootrTags.Structure.STRUCTURE_WHITELIST).filter(tag -> tag.method_40247() != 0).isPresent()
               ? LootrAPI.isTaggedStructurePresent(level, chunkPos, LootrTags.Structure.STRUCTURE_WHITELIST, position)
               : true;
         }
      }
   }

   private static void replaceEntitiesInChunk(class_3218 level, BlockEntityTicker.Entry entry) {
      for (class_2338 entityPos : entry.entityPositions()) {
         if (checkStructureValidity(level, entry.chunkPos(), entityPos)) {
            class_2586 blockEntity = level.method_8321(entityPos);
            if (!(LootrAPI.resolveBlockEntity(blockEntity) instanceof ILootrBlockEntity)) {
               ILootrDataAdapter<class_2586> adapter = LootrAPI.getAdapter(blockEntity);
               if (adapter != null) {
                  class_5321<class_52> table = adapter.getLootTable(blockEntity);
                  long seed = adapter.getLootSeed(blockEntity);
                  if (table == null) {
                     if (LootrAPI.shouldWarnNoLootTables()) {
                        LootrAPI.LOG.warn("Potential block entity {} has no loot table in {} ({})", blockEntity, level.method_27983(), entityPos);
                     }
                  } else if (!LootrAPI.isLootTableBlacklisted(table)) {
                     class_2680 stateAt = level.method_8320(entityPos);
                     class_2680 replacement = LootrAPI.replacementBlockState(stateAt);
                     if (replacement != null) {
                        replaceEntity(level, entityPos, adapter, blockEntity, replacement, table, seed);
                     }
                  }
               }
            }
         }
      }
   }

   private static void replaceEntity(
      class_3218 level,
      class_2338 entityPos,
      ILootrDataAdapter<class_2586> adapter,
      class_2586 be,
      class_2680 replacement,
      class_5321<class_52> table,
      long seed
   ) {
      LootrAPI.preProcess(level, entityPos, be, replacement, table, seed);
      DataToCopy data = PlatformAPI.copySpecificData(be);
      class_1799 itemCopy = class_1799.field_8037;
      if (adapter.hasCopyableComponentsViaItem(be)) {
         itemCopy = new class_1799(be.method_11010().method_26204());
         be.method_38240(itemCopy, level.method_30349());
      }

      adapter.setLootTable(be, null, 0L);
      level.method_8652(entityPos, replacement, 2);
      class_2586 newBlockEntity = level.method_8321(entityPos);
      ILootrBlockEntity var12 = LootrAPI.resolveBlockEntity(newBlockEntity);
      if (var12 instanceof ILootrBlockEntity) {
         if (!itemCopy.method_7960()) {
            var12.asBlockEntity().method_58683(itemCopy);
         }

         PlatformAPI.restoreSpecificData(data, newBlockEntity);
         var12.setLootTableInternal(table, seed);
         if (PlatformAPI.shouldDoInitialSave()) {
            var12.performUpdate();
         }

         LootrAPI.postProcess(level, entityPos, var12.asBlockEntity(), replacement, table, seed);
      } else {
         LootrAPI.LOG.error("Somehow, replacement result {} is not an ILootrBlockEntity {} at {}", replacement, level.method_27983(), entityPos);
      }
   }

   @Nullable
   private static class_5321<class_1937> getServerDimensionIfValid(class_1937 level) {
      if (LootrAPI.getServer() != null && !level.method_8608()) {
         class_5321<class_1937> dimension = level.method_27983();
         return LootrAPI.isDimensionBlocked(dimension) ? null : dimension;
      } else {
         return null;
      }
   }

   public static enum ChunkLoadStatus {
      UNLOADED,
      SURROUNDING_CHUNKS_NOT_LOADED,
      NOT_FULLY_LOADED,
      COMPLETE;
   }

   public record Entry(class_1923 chunkPos, Set<class_2338> entityPositions) {
      public BlockEntityTicker.ChunkLoadStatus getChunkLoadStatus(class_3218 level, Set<class_1923> loadedChunks) {
         class_2802 chunkSource = level.method_14178();
         if (LootrAPI.isWorldBorderSafe(level, this.chunkPos) && chunkSource.method_12123(this.chunkPos.field_9181, this.chunkPos.field_9180)) {
            if (!loadedChunks.contains(this.chunkPos)) {
               return BlockEntityTicker.ChunkLoadStatus.NOT_FULLY_LOADED;
            } else {
               for (int x = this.chunkPos.field_9181 - 2; x <= this.chunkPos.field_9181 + 2; x++) {
                  for (int z = this.chunkPos.field_9180 - 2; z <= this.chunkPos.field_9180 + 2; z++) {
                     if (x != this.chunkPos.field_9181 || z != this.chunkPos.field_9180) {
                        class_1923 pos = new class_1923(x, z);
                        if (LootrAPI.isWorldBorderSafe(level, pos) && !loadedChunks.contains(pos)) {
                           return BlockEntityTicker.ChunkLoadStatus.SURROUNDING_CHUNKS_NOT_LOADED;
                        }
                     }
                  }
               }

               return BlockEntityTicker.ChunkLoadStatus.COMPLETE;
            }
         } else {
            return BlockEntityTicker.ChunkLoadStatus.UNLOADED;
         }
      }
   }
}
