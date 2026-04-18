package net.caffeinemc.mods.sodium.client.world;

import it.unimi.dsi.fastutil.ints.Int2ReferenceMap;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import net.caffeinemc.mods.sodium.client.services.PlatformLevelRenderHooks;
import net.caffeinemc.mods.sodium.client.services.SodiumModelData;
import net.caffeinemc.mods.sodium.client.services.SodiumModelDataContainer;
import net.caffeinemc.mods.sodium.client.world.biome.LevelBiomeSlice;
import net.caffeinemc.mods.sodium.client.world.biome.LevelColorCache;
import net.caffeinemc.mods.sodium.client.world.cloned.ChunkRenderContext;
import net.caffeinemc.mods.sodium.client.world.cloned.ClonedChunkSection;
import net.caffeinemc.mods.sodium.client.world.cloned.ClonedChunkSectionCache;
import net.fabricmc.fabric.api.blockview.v2.FabricBlockView;
import net.fabricmc.fabric.api.rendering.data.v1.RenderAttachedBlockView;
import net.minecraft.class_1920;
import net.minecraft.class_1937;
import net.minecraft.class_1944;
import net.minecraft.class_1959;
import net.minecraft.class_2246;
import net.minecraft.class_2338;
import net.minecraft.class_2350;
import net.minecraft.class_2586;
import net.minecraft.class_2680;
import net.minecraft.class_2804;
import net.minecraft.class_2818;
import net.minecraft.class_2826;
import net.minecraft.class_310;
import net.minecraft.class_3341;
import net.minecraft.class_3532;
import net.minecraft.class_3568;
import net.minecraft.class_3610;
import net.minecraft.class_4076;
import net.minecraft.class_638;
import net.minecraft.class_6539;
import net.minecraft.class_6880;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class LevelSlice implements class_1920, RenderAttachedBlockView, FabricBlockView {
   private static final class_1944[] LIGHT_TYPES = class_1944.values();
   private static final int SECTION_BLOCK_COUNT = 4096;
   private static final int NEIGHBOR_BLOCK_RADIUS = 2;
   private static final int NEIGHBOR_CHUNK_RADIUS = class_3532.method_28139(2, 16) >> 4;
   private static final int SECTION_ARRAY_LENGTH = 1 + NEIGHBOR_CHUNK_RADIUS * 2;
   private static final int SECTION_ARRAY_SIZE = SECTION_ARRAY_LENGTH * SECTION_ARRAY_LENGTH * SECTION_ARRAY_LENGTH;
   private static final int LOCAL_XYZ_BITS = 4;
   private static final class_2680 EMPTY_BLOCK_STATE = class_2246.field_10124.method_9564();
   private final class_638 level;
   private final LevelBiomeSlice biomeSlice;
   private final LevelColorCache biomeColors;
   private final class_2680[][] blockArrays;
   private final SodiumAuxiliaryLightManager[] auxLightManager;
   @Nullable
   private final class_2804[][] lightArrays;
   @Nullable
   private final Int2ReferenceMap<class_2586>[] blockEntityArrays;
   @Nullable
   private final Int2ReferenceMap<Object>[] blockEntityRenderDataArrays;
   private final SodiumModelDataContainer[] modelMapArrays;
   private int originBlockX;
   private int originBlockY;
   private int originBlockZ;
   private class_3341 volume;

   public static ChunkRenderContext prepare(class_1937 level, class_4076 pos, ClonedChunkSectionCache cache) {
      class_2818 chunk = level.method_8497(pos.method_10263(), pos.method_10260());
      class_2826 section = chunk.method_12006()[level.method_31603(pos.method_10264())];
      if (section != null && !section.method_38292()) {
         class_3341 box = new class_3341(
            pos.method_19527() - 2, pos.method_19528() - 2, pos.method_19529() - 2, pos.method_19530() + 2, pos.method_19531() + 2, pos.method_19532() + 2
         );
         int minChunkX = pos.method_10263() - NEIGHBOR_CHUNK_RADIUS;
         int minChunkY = pos.method_10264() - NEIGHBOR_CHUNK_RADIUS;
         int minChunkZ = pos.method_10260() - NEIGHBOR_CHUNK_RADIUS;
         int maxChunkX = pos.method_10263() + NEIGHBOR_CHUNK_RADIUS;
         int maxChunkY = pos.method_10264() + NEIGHBOR_CHUNK_RADIUS;
         int maxChunkZ = pos.method_10260() + NEIGHBOR_CHUNK_RADIUS;
         ClonedChunkSection[] sections = new ClonedChunkSection[SECTION_ARRAY_SIZE];

         for (int chunkX = minChunkX; chunkX <= maxChunkX; chunkX++) {
            for (int chunkZ = minChunkZ; chunkZ <= maxChunkZ; chunkZ++) {
               for (int chunkY = minChunkY; chunkY <= maxChunkY; chunkY++) {
                  sections[getLocalSectionIndex(chunkX - minChunkX, chunkY - minChunkY, chunkZ - minChunkZ)] = cache.acquire(chunkX, chunkY, chunkZ);
               }
            }
         }

         List<?> renderers = PlatformLevelRenderHooks.getInstance().retrieveChunkMeshAppenders(level, pos.method_19767());
         return new ChunkRenderContext(pos, sections, box, renderers);
      } else {
         return null;
      }
   }

   public LevelSlice(class_638 level) {
      this.level = level;
      this.blockArrays = new class_2680[SECTION_ARRAY_SIZE][4096];
      this.lightArrays = new class_2804[SECTION_ARRAY_SIZE][LIGHT_TYPES.length];
      this.blockEntityArrays = new Int2ReferenceMap[SECTION_ARRAY_SIZE];
      this.blockEntityRenderDataArrays = new Int2ReferenceMap[SECTION_ARRAY_SIZE];
      this.auxLightManager = new SodiumAuxiliaryLightManager[SECTION_ARRAY_SIZE];
      this.modelMapArrays = new SodiumModelDataContainer[SECTION_ARRAY_SIZE];
      this.biomeSlice = new LevelBiomeSlice();
      this.biomeColors = new LevelColorCache(this.biomeSlice, (Integer)class_310.method_1551().field_1690.method_41805().method_41753());

      for (class_2680[] blockArray : this.blockArrays) {
         Arrays.fill(blockArray, EMPTY_BLOCK_STATE);
      }
   }

   public void copyData(ChunkRenderContext context) {
      this.originBlockX = class_4076.method_18688(context.getOrigin().method_10263() - NEIGHBOR_CHUNK_RADIUS);
      this.originBlockY = class_4076.method_18688(context.getOrigin().method_10264() - NEIGHBOR_CHUNK_RADIUS);
      this.originBlockZ = class_4076.method_18688(context.getOrigin().method_10260() - NEIGHBOR_CHUNK_RADIUS);
      this.volume = context.getVolume();

      for (int x = 0; x < SECTION_ARRAY_LENGTH; x++) {
         for (int y = 0; y < SECTION_ARRAY_LENGTH; y++) {
            for (int z = 0; z < SECTION_ARRAY_LENGTH; z++) {
               this.copySectionData(context, getLocalSectionIndex(x, y, z));
            }
         }
      }

      this.biomeSlice.update(this.level, context);
      this.biomeColors.update(context);
   }

   private void copySectionData(ChunkRenderContext context, int sectionIndex) {
      ClonedChunkSection section = context.getSections()[sectionIndex];
      Objects.requireNonNull(section, "Chunk section must be non-null");
      this.unpackBlockData(this.blockArrays[sectionIndex], context, section);
      this.lightArrays[sectionIndex][class_1944.field_9282.ordinal()] = section.getLightArray(class_1944.field_9282);
      this.lightArrays[sectionIndex][class_1944.field_9284.ordinal()] = section.getLightArray(class_1944.field_9284);
      this.blockEntityArrays[sectionIndex] = section.getBlockEntityMap();
      this.auxLightManager[sectionIndex] = section.getAuxLightManager();
      this.blockEntityRenderDataArrays[sectionIndex] = section.getBlockEntityRenderDataMap();
      this.modelMapArrays[sectionIndex] = section.getModelMap();
   }

   private void unpackBlockData(class_2680[] blockArray, ChunkRenderContext context, ClonedChunkSection section) {
      if (section.getBlockData() == null) {
         Arrays.fill(blockArray, EMPTY_BLOCK_STATE);
      } else {
         PalettedContainerROExtension<class_2680> container = PalettedContainerROExtension.of(section.getBlockData());
         class_4076 sectionPos = section.getPosition();
         if (sectionPos.equals(context.getOrigin())) {
            container.sodium$unpack(blockArray);
         } else {
            class_3341 bounds = context.getVolume();
            int minBlockX = Math.max(bounds.method_35415(), sectionPos.method_19527());
            int maxBlockX = Math.min(bounds.method_35418(), sectionPos.method_19530());
            int minBlockY = Math.max(bounds.method_35416(), sectionPos.method_19528());
            int maxBlockY = Math.min(bounds.method_35419(), sectionPos.method_19531());
            int minBlockZ = Math.max(bounds.method_35417(), sectionPos.method_19529());
            int maxBlockZ = Math.min(bounds.method_35420(), sectionPos.method_19532());
            container.sodium$unpack(blockArray, minBlockX & 15, minBlockY & 15, minBlockZ & 15, maxBlockX & 15, maxBlockY & 15, maxBlockZ & 15);
         }
      }
   }

   public void reset() {
      for (int sectionIndex = 0; sectionIndex < SECTION_ARRAY_LENGTH; sectionIndex++) {
         Arrays.fill(this.lightArrays[sectionIndex], null);
         this.blockEntityArrays[sectionIndex] = null;
         this.auxLightManager[sectionIndex] = null;
         this.blockEntityRenderDataArrays[sectionIndex] = null;
      }
   }

   @NotNull
   public class_2680 method_8320(class_2338 pos) {
      return this.getBlockState(pos.method_10263(), pos.method_10264(), pos.method_10260());
   }

   public class_2680 getBlockState(int blockX, int blockY, int blockZ) {
      if (!this.volume.method_47593(blockX, blockY, blockZ)) {
         return EMPTY_BLOCK_STATE;
      } else {
         int relBlockX = blockX - this.originBlockX;
         int relBlockY = blockY - this.originBlockY;
         int relBlockZ = blockZ - this.originBlockZ;
         return this.blockArrays[getLocalSectionIndex(relBlockX >> 4, relBlockY >> 4, relBlockZ >> 4)][getLocalBlockIndex(
            relBlockX & 15, relBlockY & 15, relBlockZ & 15
         )];
      }
   }

   @NotNull
   public class_3610 method_8316(class_2338 pos) {
      return this.method_8320(pos).method_26227();
   }

   public float method_24852(class_2350 direction, boolean shaded) {
      return this.level.method_24852(direction, shaded);
   }

   @NotNull
   public class_3568 method_22336() {
      throw new UnsupportedOperationException();
   }

   public int method_8314(class_1944 type, class_2338 pos) {
      if (!this.volume.method_47593(pos.method_10263(), pos.method_10264(), pos.method_10260())) {
         return 0;
      } else {
         int relBlockX = pos.method_10263() - this.originBlockX;
         int relBlockY = pos.method_10264() - this.originBlockY;
         int relBlockZ = pos.method_10260() - this.originBlockZ;
         class_2804 lightArray = this.lightArrays[getLocalSectionIndex(relBlockX >> 4, relBlockY >> 4, relBlockZ >> 4)][type.ordinal()];
         return lightArray == null ? 0 : lightArray.method_12139(relBlockX & 15, relBlockY & 15, relBlockZ & 15);
      }
   }

   public int method_22335(class_2338 pos, int ambientDarkness) {
      if (!this.volume.method_47593(pos.method_10263(), pos.method_10264(), pos.method_10260())) {
         return 0;
      } else {
         int relBlockX = pos.method_10263() - this.originBlockX;
         int relBlockY = pos.method_10264() - this.originBlockY;
         int relBlockZ = pos.method_10260() - this.originBlockZ;
         class_2804[] lightArrays = this.lightArrays[getLocalSectionIndex(relBlockX >> 4, relBlockY >> 4, relBlockZ >> 4)];
         class_2804 skyLightArray = lightArrays[class_1944.field_9284.ordinal()];
         class_2804 blockLightArray = lightArrays[class_1944.field_9282.ordinal()];
         int localBlockX = relBlockX & 15;
         int localBlockY = relBlockY & 15;
         int localBlockZ = relBlockZ & 15;
         int skyLight = skyLightArray == null ? 0 : skyLightArray.method_12139(localBlockX, localBlockY, localBlockZ) - ambientDarkness;
         int blockLight = blockLightArray == null ? 0 : blockLightArray.method_12139(localBlockX, localBlockY, localBlockZ);
         return Math.max(blockLight, skyLight);
      }
   }

   public class_2586 method_8321(class_2338 pos) {
      return this.getBlockEntity(pos.method_10263(), pos.method_10264(), pos.method_10260());
   }

   public class_2586 getBlockEntity(int blockX, int blockY, int blockZ) {
      if (!this.volume.method_47593(blockX, blockY, blockZ)) {
         return null;
      } else {
         int relBlockX = blockX - this.originBlockX;
         int relBlockY = blockY - this.originBlockY;
         int relBlockZ = blockZ - this.originBlockZ;
         Int2ReferenceMap<class_2586> blockEntities = this.blockEntityArrays[getLocalSectionIndex(relBlockX >> 4, relBlockY >> 4, relBlockZ >> 4)];
         return blockEntities == null ? null : (class_2586)blockEntities.get(getLocalBlockIndex(relBlockX & 15, relBlockY & 15, relBlockZ & 15));
      }
   }

   public int method_23752(class_2338 pos, class_6539 resolver) {
      return this.biomeColors.getColor(resolver, pos.method_10263(), pos.method_10264(), pos.method_10260());
   }

   public int method_31605() {
      return this.level.method_31605();
   }

   public int method_31607() {
      return this.level.method_31607();
   }

   @Nullable
   public Object getBlockEntityRenderData(class_2338 pos) {
      if (!this.volume.method_47593(pos.method_10263(), pos.method_10264(), pos.method_10260())) {
         return null;
      } else {
         int relBlockX = pos.method_10263() - this.originBlockX;
         int relBlockY = pos.method_10264() - this.originBlockY;
         int relBlockZ = pos.method_10260() - this.originBlockZ;
         Int2ReferenceMap<Object> blockEntityRenderDataMap = this.blockEntityRenderDataArrays[getLocalSectionIndex(
            relBlockX >> 4, relBlockY >> 4, relBlockZ >> 4
         )];
         return blockEntityRenderDataMap == null ? null : blockEntityRenderDataMap.get(getLocalBlockIndex(relBlockX & 15, relBlockY & 15, relBlockZ & 15));
      }
   }

   public SodiumModelData getPlatformModelData(class_2338 pos) {
      if (!this.volume.method_47593(pos.method_10263(), pos.method_10264(), pos.method_10260())) {
         return SodiumModelData.EMPTY;
      } else {
         int relBlockX = pos.method_10263() - this.originBlockX;
         int relBlockY = pos.method_10264() - this.originBlockY;
         int relBlockZ = pos.method_10260() - this.originBlockZ;
         SodiumModelDataContainer modelMap = this.modelMapArrays[getLocalSectionIndex(relBlockX >> 4, relBlockY >> 4, relBlockZ >> 4)];
         return modelMap.isEmpty() ? SodiumModelData.EMPTY : modelMap.getModelData(pos);
      }
   }

   public boolean hasBiomes() {
      return true;
   }

   public class_6880<class_1959> getBiomeFabric(class_2338 pos) {
      return this.biomeSlice.getBiome(pos.method_10263(), pos.method_10264(), pos.method_10260());
   }

   public static int getLocalBlockIndex(int blockX, int blockY, int blockZ) {
      return blockY << 4 << 4 | blockZ << 4 | blockX;
   }

   public static int getLocalSectionIndex(int sectionX, int sectionY, int sectionZ) {
      return sectionY * SECTION_ARRAY_LENGTH * SECTION_ARRAY_LENGTH + sectionZ * SECTION_ARRAY_LENGTH + sectionX;
   }

   @Nullable
   public Object getBlockEntityRenderAttachment(class_2338 pos) {
      if (!this.volume.method_47593(pos.method_10263(), pos.method_10264(), pos.method_10260())) {
         return null;
      } else {
         int relBlockX = pos.method_10263() - this.originBlockX;
         int relBlockY = pos.method_10264() - this.originBlockY;
         int relBlockZ = pos.method_10260() - this.originBlockZ;
         Int2ReferenceMap<Object> blockEntityRenderDataMap = this.blockEntityRenderDataArrays[getLocalSectionIndex(
            relBlockX >> 4, relBlockY >> 4, relBlockZ >> 4
         )];
         return blockEntityRenderDataMap == null ? null : blockEntityRenderDataMap.get(getLocalBlockIndex(relBlockX & 15, relBlockY & 15, relBlockZ & 15));
      }
   }
}
