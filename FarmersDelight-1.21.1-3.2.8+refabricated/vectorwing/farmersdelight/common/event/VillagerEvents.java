package vectorwing.farmersdelight.common.event;

import java.util.function.Supplier;
import net.fabricmc.fabric.api.object.builder.v1.trade.TradeOfferHelper;
import net.minecraft.class_1297;
import net.minecraft.class_1799;
import net.minecraft.class_1914;
import net.minecraft.class_1935;
import net.minecraft.class_3852;
import net.minecraft.class_5819;
import net.minecraft.class_3853.class_1652;
import net.minecraft.class_3853.class_4161;
import net.minecraft.class_3853.class_4165;
import vectorwing.farmersdelight.common.Configuration;
import vectorwing.farmersdelight.common.registry.ModItems;

public class VillagerEvents {
   public static void init() {
      onVillagerTrades();
      onWandererTrades();
   }

   public static void onVillagerTrades() {
      TradeOfferHelper.registerVillagerOffers(class_3852.field_17056, 1, trades -> {
         trades.add(emeraldForItemsTrade((class_1935)ModItems.ONION.get(), 26, 16, 2, Configuration.FARMERS_BUY_FD_CROPS));
         trades.add(emeraldForItemsTrade((class_1935)ModItems.TOMATO.get(), 26, 16, 2, Configuration.FARMERS_BUY_FD_CROPS));
      });
      TradeOfferHelper.registerVillagerOffers(class_3852.field_17056, 2, trades -> {
         trades.add(emeraldForItemsTrade((class_1935)ModItems.CABBAGE.get(), 16, 16, 5, Configuration.FARMERS_BUY_FD_CROPS));
         trades.add(emeraldForItemsTrade((class_1935)ModItems.RICE.get(), 20, 16, 5, Configuration.FARMERS_BUY_FD_CROPS));
      });
   }

   public static void onWandererTrades() {
      TradeOfferHelper.registerWanderingTraderOffers(1, trades -> {
         trades.add(itemForEmeraldTrade((class_1935)ModItems.CABBAGE_SEEDS.get(), 1, 12, Configuration.WANDERING_TRADER_SELLS_FD_ITEMS));
         trades.add(itemForEmeraldTrade((class_1935)ModItems.TOMATO_SEEDS.get(), 1, 12, Configuration.WANDERING_TRADER_SELLS_FD_ITEMS));
         trades.add(itemForEmeraldTrade((class_1935)ModItems.RICE.get(), 1, 12, Configuration.WANDERING_TRADER_SELLS_FD_ITEMS));
         trades.add(itemForEmeraldTrade((class_1935)ModItems.ONION.get(), 1, 12, Configuration.WANDERING_TRADER_SELLS_FD_ITEMS));
      });
   }

   public static class_1652 emeraldForItemsTrade(class_1935 item, int count, int maxTrades, int xp, Supplier<Boolean> predicate) {
      return new VillagerEvents.FDItemListing(new class_4161(item, count, maxTrades, xp), predicate);
   }

   public static class_1652 itemForEmeraldTrade(class_1935 item, int maxTrades, int xp, Supplier<Boolean> predicate) {
      return new VillagerEvents.FDItemListing(new class_4165(new class_1799(item), 1, 1, maxTrades, xp, 0.05F), predicate);
   }

   private record FDItemListing(class_1652 listing, Supplier<Boolean> predicate) implements class_1652 {
      public class_1914 method_7246(class_1297 trader, class_5819 random) {
         return !this.predicate.get() ? null : this.listing.method_7246(trader, random);
      }
   }
}
