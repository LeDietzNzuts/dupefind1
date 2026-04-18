package io.github.suel_ki.beautify;

import com.mojang.datafixers.util.Pair;
import io.github.suel_ki.beautify.compat.every_compat.EveryCompatIntegration;
import io.github.suel_ki.beautify.core.init.BlockInit;
import io.github.suel_ki.beautify.core.init.ItemInit;
import io.github.suel_ki.beautify.core.init.TradesInit;
import io.github.suel_ki.beautify.particle.ParticleInit;
import io.github.suel_ki.beautify.util.BeautifyConfig;
import java.util.ArrayList;
import java.util.List;
import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.GsonConfigSerializer;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.class_1761;
import net.minecraft.class_1799;
import net.minecraft.class_2378;
import net.minecraft.class_2561;
import net.minecraft.class_2960;
import net.minecraft.class_3781;
import net.minecraft.class_3784;
import net.minecraft.class_3785;
import net.minecraft.class_5321;
import net.minecraft.class_5497;
import net.minecraft.class_6880;
import net.minecraft.class_7923;
import net.minecraft.class_7924;
import net.minecraft.class_3785.class_3786;
import net.minecraft.server.MinecraftServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Beautify implements ModInitializer {
   public static BeautifyConfig CONFIG;
   public static final String MODID = "beautify";
   public static final Logger LOGGER = LoggerFactory.getLogger("beautify");
   public static final class_1761 BEAUTIFY_TAB = FabricItemGroup.builder()
      .method_47321(class_2561.method_43471("itemGroup.beautify.group"))
      .method_47320(ItemInit.HANGING_POT_ITEM::method_7854)
      .method_47317((enabledFeatures, entries) -> entries.method_45423(ItemInit.ITEMS.keySet().stream().map(class_1799::new).toList()))
      .method_47324();
   private static final class_5321<class_5497> EMPTY_PROCESSOR_LIST_KEY = class_5321.method_29179(class_7924.field_41247, class_2960.method_60656("empty"));

   public void onInitialize() {
      AutoConfig.register(BeautifyConfig.class, GsonConfigSerializer::new);
      CONFIG = (BeautifyConfig)AutoConfig.getConfigHolder(BeautifyConfig.class).getConfig();
      ItemInit.registerFuel();
      TradesInit.addCustomTrades();
      BlockInit.registerFlammableBlock();
      ParticleInit.ensureLoadedServerside();
      ServerLifecycleEvents.SERVER_STARTED.register(this::addNewVillageBuilding);
      class_2378.method_10230(class_7923.field_44687, id("group"), BEAUTIFY_TAB);
      if (FabricLoader.getInstance().isModLoaded("everycomp")) {
         EveryCompatIntegration.register();
      }
   }

   public static class_2960 id(String name) {
      return class_2960.method_60655("beautify", name);
   }

   private static void addBuildingToPool(
      class_2378<class_3785> templatePoolRegistry, class_2378<class_5497> processorListRegistry, class_2960 poolRL, String nbtPieceRL, int weight
   ) {
      class_6880<class_5497> emptyProcessorList = processorListRegistry.method_40290(EMPTY_PROCESSOR_LIST_KEY);
      class_3785 pool = (class_3785)templatePoolRegistry.method_10223(poolRL);
      if (pool != null) {
         class_3781 piece = (class_3781)class_3781.method_30426(nbtPieceRL, emptyProcessorList).apply(class_3786.field_16687);

         for (int i = 0; i < weight; i++) {
            pool.field_16680.add(piece);
         }

         List<Pair<class_3784, Integer>> listOfPieceEntries = new ArrayList<>(pool.field_16864);
         listOfPieceEntries.add(new Pair(piece, weight));
         pool.field_16864 = listOfPieceEntries;
      }
   }

   public void addNewVillageBuilding(MinecraftServer server) {
      class_2378<class_3785> templatePoolRegistry = (class_2378<class_3785>)server.method_30611().method_33310(class_7924.field_41249).orElseThrow();
      class_2378<class_5497> processorListRegistry = (class_2378<class_5497>)server.method_30611().method_33310(class_7924.field_41247).orElseThrow();
      int weight = CONFIG.houses.botanistSpawnWeight;
      addBuildingToPool(templatePoolRegistry, processorListRegistry, class_2960.method_60656("village/plains/houses"), "beautify:botanist_house_plains", weight);
      addBuildingToPool(templatePoolRegistry, processorListRegistry, class_2960.method_60656("village/snowy/houses"), "beautify:botanist_house_snowy", weight);
      addBuildingToPool(
         templatePoolRegistry, processorListRegistry, class_2960.method_60656("village/savanna/houses"), "beautify:botanist_house_savanna", weight
      );
      addBuildingToPool(templatePoolRegistry, processorListRegistry, class_2960.method_60656("village/taiga/houses"), "beautify:botanist_house_taiga", weight);
      addBuildingToPool(templatePoolRegistry, processorListRegistry, class_2960.method_60656("village/desert/houses"), "beautify:botanist_house_desert", weight);
   }
}
