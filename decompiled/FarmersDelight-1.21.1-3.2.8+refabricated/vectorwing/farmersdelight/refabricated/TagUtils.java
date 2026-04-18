package vectorwing.farmersdelight.refabricated;

import com.google.common.collect.Maps;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.mojang.serialization.Dynamic;
import com.mojang.serialization.JsonOps;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import net.minecraft.class_1299;
import net.minecraft.class_2248;
import net.minecraft.class_2960;
import net.minecraft.class_3298;
import net.minecraft.class_3300;
import net.minecraft.class_3503;
import net.minecraft.class_5321;
import net.minecraft.class_6862;
import net.minecraft.class_6880;
import net.minecraft.class_7225;
import net.minecraft.class_7475;
import net.minecraft.class_7654;
import net.minecraft.class_7924;
import net.minecraft.class_3503.class_5145;
import vectorwing.farmersdelight.common.tag.ModTags;

public class TagUtils {
   private static class_3300 resourceManager;
   private static Collection<class_6880<class_2248>> earlyDropsCakeTag;
   private static Collection<class_6880<class_1299<?>>> earlyDropsLeatherTag;

   public static boolean isCandleDropsCakeSliceTag(class_6880<class_2248> block, class_7225<class_2248> lookup) {
      if (earlyDropsCakeTag == null) {
         class_3503<class_6880<class_2248>> loader = new class_3503(
            rl -> lookup.method_46746(class_5321.method_29179(class_7924.field_41254, rl)), "tags/block"
         );
         Map<class_2960, List<class_5145>> dropsLeatherMap = loadTag(ModTags.DROPS_CAKE_SLICE);
         Map<class_2960, Collection<class_6880<class_2248>>> loaded = loader.method_18242(dropsLeatherMap);
         earlyDropsCakeTag = loaded.get(ModTags.DROPS_CAKE_SLICE.comp_327());
         if (earlyDropsCakeTag == null) {
            earlyDropsCakeTag = List.of();
         }
      }

      return earlyDropsCakeTag.contains(block);
   }

   public static boolean isDropsLeatherTag(class_6880<class_1299<?>> entityType, class_7225<class_1299<?>> lookup) {
      if (earlyDropsLeatherTag == null) {
         class_3503<class_6880<class_1299<?>>> loader = new class_3503(
            rl -> lookup.method_46746(class_5321.method_29179(class_7924.field_41266, rl)), "tags/entity_type"
         );
         Map<class_2960, List<class_5145>> dropsLeatherMap = loadTag(ModTags.DROPS_LEATHER);
         Map<class_2960, Collection<class_6880<class_1299<?>>>> loaded = loader.method_18242(dropsLeatherMap);
         earlyDropsLeatherTag = loaded.get(ModTags.DROPS_LEATHER.comp_327());
         if (earlyDropsLeatherTag == null) {
            earlyDropsLeatherTag = List.of();
         }
      }

      return earlyDropsLeatherTag.contains(entityType);
   }

   public static <T> Map<class_2960, List<class_5145>> loadTag(class_6862<T> tagKey) {
      Map<class_2960, List<class_5145>> map = Maps.newHashMap();
      String tagRegistryLocation = (
            tagKey.comp_326().method_29177().method_12836().equals("minecraft") ? "" : tagKey.comp_326().method_29177().method_12836() + "/"
         )
         + tagKey.comp_326().method_29177().method_12832();
      class_2960 jsonPath = class_2960.method_60655(
         tagKey.comp_327().method_12836(), "tags/" + tagRegistryLocation + "/" + tagKey.comp_327().method_12832() + ".json"
      );

      for (class_3298 entry : resourceManager.method_14489(jsonPath)) {
         loadIndividualTag(tagRegistryLocation, jsonPath, entry, map);
      }

      return map;
   }

   private static void loadIndividualTag(String tagRegistryLocation, class_2960 fileLocation, class_3298 resource, Map<class_2960, List<class_5145>> map) {
      class_7654 converter = class_7654.method_45114("tags/" + tagRegistryLocation);
      class_2960 fileToId = converter.method_45115(fileLocation);

      try (Reader reader = resource.method_43039()) {
         JsonElement jsonElement = JsonParser.parseReader(reader);
         List<class_5145> list = map.getOrDefault(fileToId, new ArrayList<>());
         class_7475 tagFile = (class_7475)class_7475.field_39269.parse(new Dynamic(JsonOps.INSTANCE, jsonElement)).getOrThrow();
         if (tagFile.comp_812()) {
            list.clear();
         }

         tagFile.comp_811().forEach(tagEntry -> tagEntry.method_32832(resourceLocation -> {
            list.add(new class_5145(tagEntry, resource.method_14480()));
            return false;
         }, resourceLocation -> {
            for (class_3298 innerEntry : resourceManager.method_14489(converter.method_45112(resourceLocation))) {
               loadIndividualTag(tagRegistryLocation, resourceLocation, innerEntry, map);
            }

            list.add(new class_5145(tagEntry, resource.method_14480()));
            return false;
         }));
         map.putIfAbsent(fileToId, list);
      } catch (Exception var12) {
      }
   }

   public static void setLootTableResourceManager(class_3300 manager) {
      resourceManager = manager;
   }

   public static void resetEarlyTagCollections() {
      resourceManager = null;
      earlyDropsCakeTag = null;
      earlyDropsLeatherTag = null;
   }
}
