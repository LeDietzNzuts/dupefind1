package dev.architectury.registry.level.entity.trade.fabric;

import java.util.Collections;
import net.fabricmc.fabric.api.object.builder.v1.trade.TradeOfferHelper;
import net.minecraft.class_3852;
import net.minecraft.class_3853.class_1652;

public class TradeRegistryImpl {
   public static void registerVillagerTrade0(class_3852 profession, int level, class_1652... trades) {
      TradeOfferHelper.registerVillagerOffers(profession, level, allTradesList -> Collections.addAll(allTradesList, trades));
   }

   public static void registerTradeForWanderingTrader(boolean rare, class_1652... trades) {
      TradeOfferHelper.registerWanderingTraderOffers(rare ? 2 : 1, allTradesList -> Collections.addAll(allTradesList, trades));
   }
}
