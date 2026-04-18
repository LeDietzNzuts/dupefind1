package net.caffeinemc.mods.sodium.client.world.cloned;

import it.unimi.dsi.fastutil.ints.Int2ReferenceMap;
import it.unimi.dsi.fastutil.ints.Int2ReferenceMaps;
import it.unimi.dsi.fastutil.ints.Int2ReferenceOpenHashMap;
import it.unimi.dsi.fastutil.objects.ObjectIterator;
import java.util.Map.Entry;
import net.caffeinemc.mods.sodium.client.services.PlatformBlockAccess;
import net.caffeinemc.mods.sodium.client.services.PlatformLevelAccess;
import net.caffeinemc.mods.sodium.client.services.PlatformModelAccess;
import net.caffeinemc.mods.sodium.client.services.PlatformRuntimeInformation;
import net.caffeinemc.mods.sodium.client.services.SodiumModelDataContainer;
import net.caffeinemc.mods.sodium.client.util.iterator.WrappedIterator;
import net.caffeinemc.mods.sodium.client.world.LevelSlice;
import net.caffeinemc.mods.sodium.client.world.PalettedContainerROExtension;
import net.caffeinemc.mods.sodium.client.world.SodiumAuxiliaryLightManager;
import net.minecraft.class_1937;
import net.minecraft.class_1944;
import net.minecraft.class_1959;
import net.minecraft.class_2246;
import net.minecraft.class_2248;
import net.minecraft.class_2338;
import net.minecraft.class_2586;
import net.minecraft.class_2680;
import net.minecraft.class_2804;
import net.minecraft.class_2818;
import net.minecraft.class_2826;
import net.minecraft.class_2841;
import net.minecraft.class_2891;
import net.minecraft.class_3341;
import net.minecraft.class_4076;
import net.minecraft.class_6880;
import net.minecraft.class_7522;
import net.minecraft.class_2841.class_6563;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class ClonedChunkSection {
   private static final class_2804 DEFAULT_SKY_LIGHT_ARRAY = new class_2804(15);
   private static final class_2804 DEFAULT_BLOCK_LIGHT_ARRAY = new class_2804(0);
   private static final class_2841<class_2680> DEFAULT_STATE_CONTAINER = new class_2841(
      class_2248.field_10651, class_2246.field_10124.method_9564(), class_6563.field_34569
   );
   private final class_4076 pos;
   @Nullable
   private final Int2ReferenceMap<class_2586> blockEntityMap;
   @Nullable
   private final Int2ReferenceMap<Object> blockEntityRenderDataMap;
   @Nullable
   private final class_2804[] lightDataArrays;
   @Nullable
   private final SodiumAuxiliaryLightManager auxLightManager;
   @Nullable
   private final class_7522<class_2680> blockData;
   @Nullable
   private final class_7522<class_6880<class_1959>> biomeData;
   private final SodiumModelDataContainer modelMap;
   private long lastUsedTimestamp = Long.MAX_VALUE;

   public ClonedChunkSection(class_1937 level, class_2818 chunk, @Nullable class_2826 section, class_4076 pos) {
      this.pos = pos;
      class_7522<class_2680> blockData = null;
      class_7522<class_6880<class_1959>> biomeData = null;
      Int2ReferenceMap<class_2586> blockEntityMap = null;
      Int2ReferenceMap<Object> blockEntityRenderDataMap = null;
      SodiumModelDataContainer modelMap = PlatformModelAccess.getInstance().getModelDataContainer(level, pos);
      this.auxLightManager = PlatformLevelAccess.INSTANCE.getLightManager(chunk, pos);
      if (section != null) {
         if (!section.method_38292()) {
            if (!level.method_27982()) {
               blockData = PalettedContainerROExtension.clone(section.method_12265());
            } else {
               blockData = constructDebugWorldContainer(pos);
            }

            blockEntityMap = tryCopyBlockEntities(chunk, pos);
            if (blockEntityMap != null && PlatformBlockAccess.getInstance().platformHasBlockData()) {
               blockEntityRenderDataMap = copyBlockEntityRenderData(level, blockEntityMap);
            }
         }

         biomeData = PalettedContainerROExtension.clone(section.method_38294());
      }

      this.blockData = blockData;
      this.biomeData = biomeData;
      this.modelMap = modelMap;
      this.blockEntityMap = blockEntityMap;
      this.blockEntityRenderDataMap = blockEntityRenderDataMap;
      this.lightDataArrays = copyLightData(level, pos);
   }

   @NotNull
   private static class_2841<class_2680> constructDebugWorldContainer(class_4076 pos) {
      if (pos.method_10264() != 3 && pos.method_10264() != 4) {
         return DEFAULT_STATE_CONTAINER;
      } else {
         class_2841<class_2680> container = new class_2841(class_2248.field_10651, class_2246.field_10124.method_9564(), class_6563.field_34569);
         if (pos.method_10264() == 3) {
            class_2680 barrier = class_2246.field_10499.method_9564();

            for (int z = 0; z < 16; z++) {
               for (int x = 0; x < 16; x++) {
                  container.method_16678(x, 12, z, barrier);
               }
            }
         } else if (pos.method_10264() == 4) {
            for (int z = 0; z < 16; z++) {
               for (int x = 0; x < 16; x++) {
                  container.method_16678(
                     x, 6, z, class_2891.method_12578(class_4076.method_32205(pos.method_10263(), x), class_4076.method_32205(pos.method_10260(), z))
                  );
               }
            }
         }

         return container;
      }
   }

   @NotNull
   private static class_2804[] copyLightData(class_1937 level, class_4076 pos) {
      class_2804[] arrays = new class_2804[2];
      arrays[class_1944.field_9282.ordinal()] = copyLightArray(level, class_1944.field_9282, pos);
      if (level.method_8597().comp_642()) {
         arrays[class_1944.field_9284.ordinal()] = copyLightArray(level, class_1944.field_9284, pos);
      }

      return arrays;
   }

   @NotNull
   private static class_2804 copyLightArray(class_1937 level, class_1944 type, class_4076 pos) {
      class_2804 array = level.method_22336().method_15562(type).method_15544(pos);
      if (array == null) {
         array = switch (type) {
            case field_9284 -> DEFAULT_SKY_LIGHT_ARRAY;
            case field_9282 -> DEFAULT_BLOCK_LIGHT_ARRAY;
            default -> throw new MatchException(null, null);
         };
      }

      return array;
   }

   @Nullable
   private static Int2ReferenceMap<class_2586> tryCopyBlockEntities(class_2818 chunk, class_4076 chunkCoord) {
      try {
         return copyBlockEntities(chunk, chunkCoord);
      } catch (WrappedIterator.Exception var3) {
         if (PlatformRuntimeInformation.getInstance().isModInLoadingList("entityculling")) {
            throw new RuntimeException(
               "Failed to iterate block entities! This is *very likely* the fault of the Entity Culling mod, and cannot be fixed by Sodium. See here for more details: https://link.caffeinemc.net/help/sodium/mod-issue/entity-culling/gh-2985",
               var3
            );
         } else {
            throw new RuntimeException(
               "Failed to iterate block entities! This is *very likely* the fault of another misbehaving mod, not Sodium. Please check your mods list.", var3
            );
         }
      }
   }

   @Nullable
   private static Int2ReferenceMap<class_2586> copyBlockEntities(class_2818 chunk, class_4076 chunkCoord) {
      class_3341 box = new class_3341(
         chunkCoord.method_19527(),
         chunkCoord.method_19528(),
         chunkCoord.method_19529(),
         chunkCoord.method_19530(),
         chunkCoord.method_19531(),
         chunkCoord.method_19532()
      );
      Int2ReferenceOpenHashMap<class_2586> blockEntities = null;
      WrappedIterator<Entry<class_2338, class_2586>> it = WrappedIterator.create(chunk.method_12214().entrySet());

      while (it.hasNext()) {
         Entry<class_2338, class_2586> entry = it.next();
         class_2338 pos = entry.getKey();
         class_2586 entity = entry.getValue();
         if (box.method_14662(pos)) {
            if (blockEntities == null) {
               blockEntities = new Int2ReferenceOpenHashMap();
            }

            blockEntities.put(LevelSlice.getLocalBlockIndex(pos.method_10263() & 15, pos.method_10264() & 15, pos.method_10260() & 15), entity);
         }
      }

      if (blockEntities != null) {
         blockEntities.trim();
      }

      return blockEntities;
   }

   @Nullable
   private static Int2ReferenceMap<Object> copyBlockEntityRenderData(class_1937 level, Int2ReferenceMap<class_2586> blockEntities) {
      Int2ReferenceOpenHashMap<Object> blockEntityRenderDataMap = null;
      ObjectIterator var3 = Int2ReferenceMaps.fastIterable(blockEntities).iterator();

      while (var3.hasNext()) {
         it.unimi.dsi.fastutil.ints.Int2ReferenceMap.Entry<class_2586> entry = (it.unimi.dsi.fastutil.ints.Int2ReferenceMap.Entry<class_2586>)var3.next();
         Object data = PlatformLevelAccess.getInstance().getBlockEntityData((class_2586)entry.getValue());
         if (data != null) {
            if (blockEntityRenderDataMap == null) {
               blockEntityRenderDataMap = new Int2ReferenceOpenHashMap();
            }

            blockEntityRenderDataMap.put(entry.getIntKey(), data);
         }
      }

      if (blockEntityRenderDataMap != null) {
         blockEntityRenderDataMap.trim();
      }

      return blockEntityRenderDataMap;
   }

   public class_4076 getPosition() {
      return this.pos;
   }

   @Nullable
   public class_7522<class_2680> getBlockData() {
      return this.blockData;
   }

   @Nullable
   public class_7522<class_6880<class_1959>> getBiomeData() {
      return this.biomeData;
   }

   @Nullable
   public Int2ReferenceMap<class_2586> getBlockEntityMap() {
      return this.blockEntityMap;
   }

   @Nullable
   public Int2ReferenceMap<Object> getBlockEntityRenderDataMap() {
      return this.blockEntityRenderDataMap;
   }

   public SodiumModelDataContainer getModelMap() {
      return this.modelMap;
   }

   @Nullable
   public class_2804 getLightArray(class_1944 lightType) {
      return this.lightDataArrays[lightType.ordinal()];
   }

   public long getLastUsedTimestamp() {
      return this.lastUsedTimestamp;
   }

   public void setLastUsedTimestamp(long timestamp) {
      this.lastUsedTimestamp = timestamp;
   }

   public SodiumAuxiliaryLightManager getAuxLightManager() {
      return this.auxLightManager;
   }
}
