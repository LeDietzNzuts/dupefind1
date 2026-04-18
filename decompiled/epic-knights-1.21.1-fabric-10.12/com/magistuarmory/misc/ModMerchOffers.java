package com.magistuarmory.misc;

import com.magistuarmory.item.ModItems;
import dev.architectury.registry.level.entity.trade.SimpleTrade;
import dev.architectury.registry.level.entity.trade.TradeRegistry;
import java.util.Optional;
import net.minecraft.class_1799;
import net.minecraft.class_1802;
import net.minecraft.class_1935;
import net.minecraft.class_3852;
import net.minecraft.class_9306;
import net.minecraft.class_3853.class_1652;

public class ModMerchOffers {
   public static class_1652[] SHEPHERD_TRADES;
   public static class_1652[] WANDERING_TRADER_TRADES;

   public static void setup() {
      SHEPHERD_TRADES = new class_1652[]{
         new SimpleTrade(
            new class_9306(class_1802.field_8687), Optional.empty(), new class_1799((class_1935)ModItems.APOSTOLIC_CROSS_PATTERN.get()), 7, 0, 1.0F
         ),
         new SimpleTrade(new class_9306(class_1802.field_8687), Optional.empty(), new class_1799((class_1935)ModItems.BOWL_PATTERN.get()), 7, 0, 1.0F),
         new SimpleTrade(new class_9306(class_1802.field_8687), Optional.empty(), new class_1799((class_1935)ModItems.BULL_PATTERN.get()), 7, 0, 1.0F),
         new SimpleTrade(new class_9306(class_1802.field_8687), Optional.empty(), new class_1799((class_1935)ModItems.CHESS_PATTERN.get()), 7, 0, 1.0F),
         new SimpleTrade(new class_9306(class_1802.field_8687), Optional.empty(), new class_1799((class_1935)ModItems.CRUSADER_CROSS_PATTERN.get()), 7, 0, 1.0F),
         new SimpleTrade(new class_9306(class_1802.field_8687), Optional.empty(), new class_1799((class_1935)ModItems.DRAGON_PATTERN.get()), 7, 0, 1.0F),
         new SimpleTrade(new class_9306(class_1802.field_8687), Optional.empty(), new class_1799((class_1935)ModItems.EAGLE_PATTERN.get()), 7, 0, 1.0F),
         new SimpleTrade(new class_9306(class_1802.field_8687), Optional.empty(), new class_1799((class_1935)ModItems.HORSE_PATTERN.get()), 7, 0, 1.0F),
         new SimpleTrade(new class_9306(class_1802.field_8687), Optional.empty(), new class_1799((class_1935)ModItems.LILY_PATTERN.get()), 7, 0, 1.0F),
         new SimpleTrade(new class_9306(class_1802.field_8687), Optional.empty(), new class_1799((class_1935)ModItems.LION1_PATTERN.get()), 7, 0, 1.0F),
         new SimpleTrade(new class_9306(class_1802.field_8687), Optional.empty(), new class_1799((class_1935)ModItems.LION2_PATTERN.get()), 7, 0, 1.0F),
         new SimpleTrade(new class_9306(class_1802.field_8687), Optional.empty(), new class_1799((class_1935)ModItems.ORTHODOX_CROSS_PATTERN.get()), 7, 0, 1.0F),
         new SimpleTrade(new class_9306(class_1802.field_8687), Optional.empty(), new class_1799((class_1935)ModItems.SNAKE_PATTERN.get()), 7, 0, 1.0F),
         new SimpleTrade(new class_9306(class_1802.field_8687), Optional.empty(), new class_1799((class_1935)ModItems.SUN_PATTERN.get()), 7, 0, 1.0F),
         new SimpleTrade(new class_9306(class_1802.field_8687), Optional.empty(), new class_1799((class_1935)ModItems.SWORDS_PATTERN.get()), 7, 0, 1.0F),
         new SimpleTrade(new class_9306(class_1802.field_8687), Optional.empty(), new class_1799((class_1935)ModItems.TOWER_PATTERN.get()), 7, 0, 1.0F),
         new SimpleTrade(new class_9306(class_1802.field_8687), Optional.empty(), new class_1799((class_1935)ModItems.TREE_PATTERN.get()), 7, 0, 1.0F),
         new SimpleTrade(
            new class_9306(class_1802.field_8687), Optional.empty(), new class_1799((class_1935)ModItems.TWOHEADED_EAGLE_PATTERN.get()), 7, 0, 1.0F
         )
      };
      WANDERING_TRADER_TRADES = new class_1652[]{
         new SimpleTrade(
            new class_9306(class_1802.field_8687), Optional.empty(), new class_1799((class_1935)ModItems.GAZELLE_HORNS_DECORATION.get()), 5, 0, 1.0F
         ),
         new SimpleTrade(new class_9306(class_1802.field_8687), Optional.empty(), new class_1799((class_1935)ModItems.DUCK_DECORATION.get()), 5, 0, 1.0F),
         new SimpleTrade(new class_9306(class_1802.field_8687), Optional.empty(), new class_1799((class_1935)ModItems.HORSE_DECORATION.get()), 5, 0, 1.0F),
         new SimpleTrade(new class_9306(class_1802.field_8687, 10), Optional.empty(), new class_1799((class_1935)ModItems.CROWN_DECORATION.get()), 1, 0, 1.0F),
         new SimpleTrade(new class_9306(class_1802.field_8687), Optional.empty(), new class_1799((class_1935)ModItems.UNICORN_DECORATION.get()), 5, 0, 1.0F),
         new SimpleTrade(new class_9306(class_1802.field_8687), Optional.empty(), new class_1799((class_1935)ModItems.BULLHORNS_DECORATION.get()), 5, 0, 1.0F),
         new SimpleTrade(new class_9306(class_1802.field_8687), Optional.empty(), new class_1799((class_1935)ModItems.DRAGON_DECORATION.get()), 5, 0, 1.0F),
         new SimpleTrade(
            new class_9306(class_1802.field_8687, 8), Optional.empty(), new class_1799((class_1935)ModItems.MINICROWN_DECORATION.get()), 1, 0, 1.0F
         ),
         new SimpleTrade(new class_9306(class_1802.field_8687), Optional.empty(), new class_1799((class_1935)ModItems.ANTLERS_DECORATION.get()), 5, 0, 1.0F),
         new SimpleTrade(new class_9306(class_1802.field_8687), Optional.empty(), new class_1799((class_1935)ModItems.BEAR_DECORATION.get()), 5, 0, 1.0F),
         new SimpleTrade(new class_9306(class_1802.field_8687), Optional.empty(), new class_1799((class_1935)ModItems.LILY_DECORATION.get()), 5, 0, 1.0F),
         new SimpleTrade(new class_9306(class_1802.field_8687), Optional.empty(), new class_1799((class_1935)ModItems.LION_DECORATION.get()), 5, 0, 1.0F),
         new SimpleTrade(new class_9306(class_1802.field_8687), Optional.empty(), new class_1799((class_1935)ModItems.DEMON_HORNS_DECORATION.get()), 5, 0, 1.0F),
         new SimpleTrade(new class_9306(class_1802.field_8687), Optional.empty(), new class_1799((class_1935)ModItems.GRIFFIN_DECORATION.get()), 5, 0, 1.0F)
      };
      TradeRegistry.registerVillagerTrade(class_3852.field_17063, 1, SHEPHERD_TRADES);
      TradeRegistry.registerTradeForWanderingTrader(false, WANDERING_TRADER_TRADES);
   }
}
