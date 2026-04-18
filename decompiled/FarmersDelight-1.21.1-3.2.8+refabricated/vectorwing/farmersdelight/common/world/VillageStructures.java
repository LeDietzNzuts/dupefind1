package vectorwing.farmersdelight.common.world;

import com.mojang.datafixers.util.Pair;
import java.util.ArrayList;
import java.util.List;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.minecraft.class_2246;
import net.minecraft.class_2378;
import net.minecraft.class_2960;
import net.minecraft.class_3491;
import net.minecraft.class_3781;
import net.minecraft.class_3784;
import net.minecraft.class_3785;
import net.minecraft.class_3818;
import net.minecraft.class_3821;
import net.minecraft.class_3824;
import net.minecraft.class_3826;
import net.minecraft.class_5321;
import net.minecraft.class_5497;
import net.minecraft.class_6880;
import net.minecraft.class_7924;
import net.minecraft.class_3785.class_3786;
import net.minecraft.server.MinecraftServer;
import vectorwing.farmersdelight.common.Configuration;
import vectorwing.farmersdelight.common.registry.ModBlocks;

public class VillageStructures {
   public static void init() {
      ServerLifecycleEvents.SERVER_STARTING.register(VillageStructures::addNewVillageBuilding);
   }

   public static void addNewVillageBuilding(MinecraftServer server) {
      if (Configuration.GENERATE_VILLAGE_COMPOST_HEAPS.get()) {
         class_2378<class_3785> templatePools = server.method_30611().method_30530(class_7924.field_41249);
         class_2378<class_5497> processorLists = server.method_30611().method_30530(class_7924.field_41247);
         addBuildingToPool(
            templatePools, processorLists, class_2960.method_60654("minecraft:village/plains/houses"), "farmersdelight:village/houses/plains_compost_pile", 5
         );
         addBuildingToPool(
            templatePools, processorLists, class_2960.method_60654("minecraft:village/snowy/houses"), "farmersdelight:village/houses/snowy_compost_pile", 3
         );
         addBuildingToPool(
            templatePools, processorLists, class_2960.method_60654("minecraft:village/savanna/houses"), "farmersdelight:village/houses/savanna_compost_pile", 4
         );
         addBuildingToPool(
            templatePools, processorLists, class_2960.method_60654("minecraft:village/desert/houses"), "farmersdelight:village/houses/desert_compost_pile", 3
         );
         addBuildingToPool(
            templatePools, processorLists, class_2960.method_60654("minecraft:village/taiga/houses"), "farmersdelight:village/houses/taiga_compost_pile", 4
         );
      }

      if (Configuration.GENERATE_VILLAGE_FARM_FD_CROPS.get()) {
         class_2378<class_5497> processorLists = server.method_30611().method_30530(class_7924.field_41247);
         class_3491 temperateCropProcessor = new class_3826(
            List.of(
               new class_3821(new class_3824(class_2246.field_10293, 0.3F), class_3818.field_16868, ModBlocks.CABBAGE_CROP.get().method_9564()),
               new class_3821(new class_3824(class_2246.field_10293, 0.3F), class_3818.field_16868, ModBlocks.TOMATO_CROP.get().method_9564()),
               new class_3821(new class_3824(class_2246.field_10293, 0.3F), class_3818.field_16868, ModBlocks.ONION_CROP.get().method_9564())
            )
         );
         class_3491 coldCropProcessor = new class_3826(
            List.of(
               new class_3821(new class_3824(class_2246.field_10293, 0.3F), class_3818.field_16868, ModBlocks.CABBAGE_CROP.get().method_9564()),
               new class_3821(new class_3824(class_2246.field_10293, 0.3F), class_3818.field_16868, ModBlocks.ONION_CROP.get().method_9564()),
               new class_3821(new class_3824(class_2246.field_10247, 0.2F), class_3818.field_16868, ModBlocks.CABBAGE_CROP.get().method_9564()),
               new class_3821(new class_3824(class_2246.field_10247, 0.2F), class_3818.field_16868, ModBlocks.ONION_CROP.get().method_9564())
            )
         );
         class_3491 aridCropProcessor = new class_3826(
            List.of(
               new class_3821(new class_3824(class_2246.field_10293, 0.3F), class_3818.field_16868, ModBlocks.CABBAGE_CROP.get().method_9564()),
               new class_3821(new class_3824(class_2246.field_10293, 0.3F), class_3818.field_16868, ModBlocks.TOMATO_CROP.get().method_9564())
            )
         );
         addNewRuleToProcessorList(class_2960.method_60654("minecraft:farm_plains"), temperateCropProcessor, processorLists);
         addNewRuleToProcessorList(class_2960.method_60654("minecraft:farm_savanna"), aridCropProcessor, processorLists);
         addNewRuleToProcessorList(class_2960.method_60654("minecraft:farm_snowy"), coldCropProcessor, processorLists);
         addNewRuleToProcessorList(class_2960.method_60654("minecraft:farm_taiga"), temperateCropProcessor, processorLists);
         addNewRuleToProcessorList(class_2960.method_60654("minecraft:farm_desert"), aridCropProcessor, processorLists);
      }
   }

   public static void addBuildingToPool(
      class_2378<class_3785> templatePoolRegistry, class_2378<class_5497> processorListRegistry, class_2960 poolRL, String nbtPieceRL, int weight
   ) {
      class_3785 pool = (class_3785)templatePoolRegistry.method_10223(poolRL);
      if (pool != null) {
         class_2960 emptyProcessor = class_2960.method_60656("empty");
         class_6880<class_5497> processorHolder = processorListRegistry.method_40290(class_5321.method_29179(class_7924.field_41247, emptyProcessor));
         class_3781 piece = (class_3781)class_3781.method_30435(nbtPieceRL, processorHolder).apply(class_3786.field_16687);

         for (int i = 0; i < weight; i++) {
            pool.field_16680.add(piece);
         }

         List<Pair<class_3784, Integer>> listOfPieceEntries = new ArrayList<>(pool.field_16864);
         listOfPieceEntries.add(new Pair(piece, weight));
         pool.field_16864 = listOfPieceEntries;
      }
   }

   private static void addNewRuleToProcessorList(class_2960 targetProcessorList, class_3491 processorToAdd, class_2378<class_5497> processorListRegistry) {
      processorListRegistry.method_17966(targetProcessorList).ifPresent(processorList -> {
         List<class_3491> newSafeList = new ArrayList<>(processorList.method_31027());
         newSafeList.add(processorToAdd);
         processorList.field_26662 = newSafeList;
      });
   }
}
