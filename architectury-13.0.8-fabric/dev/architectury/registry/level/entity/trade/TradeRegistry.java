package dev.architectury.registry.level.entity.trade;

import dev.architectury.injectables.annotations.ExpectPlatform;
import dev.architectury.injectables.annotations.ExpectPlatform.Transformed;
import dev.architectury.registry.level.entity.trade.fabric.TradeRegistryImpl;
import net.minecraft.class_3852;
import net.minecraft.class_3853.class_1652;

public class TradeRegistry {
   private TradeRegistry() {
   }

   public static void registerVillagerTrade(class_3852 profession, int level, class_1652... trades) {
      if (level < 1) {
         throw new IllegalArgumentException("Villager Trade level has to be at least 1!");
      } else {
         registerVillagerTrade0(profession, level, trades);
      }
   }

   @ExpectPlatform
   @Transformed
   private static void registerVillagerTrade0(class_3852 profession, int level, class_1652... trades) {
      TradeRegistryImpl.registerVillagerTrade0(profession, level, trades);
   }

   @ExpectPlatform
   @Transformed
   public static void registerTradeForWanderingTrader(boolean rare, class_1652... trades) {
      TradeRegistryImpl.registerTradeForWanderingTrader(rare, trades);
   }
}
