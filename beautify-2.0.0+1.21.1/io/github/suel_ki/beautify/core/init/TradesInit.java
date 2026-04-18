package io.github.suel_ki.beautify.core.init;

import net.fabricmc.fabric.api.object.builder.v1.trade.TradeOfferHelper;
import net.minecraft.class_1799;
import net.minecraft.class_1802;
import net.minecraft.class_1914;
import net.minecraft.class_9306;

public class TradesInit {
   public static void addCustomTrades() {
      TradeOfferHelper.registerVillagerOffers(
         ModVillagers.BOTANIST,
         1,
         factories -> factories.add(
            (trader, rand) -> new class_1914(new class_9306(class_1802.field_8687, 2), new class_1799(ItemInit.HANGING_POT_ITEM, 1), 16, 6, 0.02F)
         )
      );
      TradeOfferHelper.registerVillagerOffers(
         ModVillagers.BOTANIST,
         1,
         factories -> factories.add(
            (trader, rand) -> new class_1914(new class_9306(class_1802.field_8687, 3), new class_1799(class_1802.field_8074, 2), 12, 5, 0.02F)
         )
      );
      TradeOfferHelper.registerVillagerOffers(
         ModVillagers.BOTANIST,
         1,
         factories -> factories.add(
            (trader, rand) -> new class_1914(new class_9306(class_1802.field_8687, 2), new class_1799(class_1802.field_17523, 3), 16, 4, 0.02F)
         )
      );
      TradeOfferHelper.registerVillagerOffers(
         ModVillagers.BOTANIST,
         2,
         factories -> factories.add(
            (trader, rand) -> new class_1914(new class_9306(class_1802.field_8687, 1), new class_1799(ItemInit.OAK_TRELLIS_ITEM, 2), 16, 3, 0.02F)
         )
      );
      TradeOfferHelper.registerVillagerOffers(
         ModVillagers.BOTANIST,
         2,
         factories -> factories.add(
            (trader, rand) -> new class_1914(new class_9306(class_1802.field_8687, 1), new class_1799(ItemInit.SPRUCE_TRELLIS_ITEM, 2), 16, 3, 0.02F)
         )
      );
      TradeOfferHelper.registerVillagerOffers(
         ModVillagers.BOTANIST,
         2,
         factories -> factories.add(
            (trader, rand) -> new class_1914(new class_9306(class_1802.field_8687, 1), new class_1799(ItemInit.BIRCH_TRELLIS_ITEM, 2), 16, 3, 0.02F)
         )
      );
      TradeOfferHelper.registerVillagerOffers(
         ModVillagers.BOTANIST,
         2,
         factories -> factories.add(
            (trader, rand) -> new class_1914(new class_9306(class_1802.field_8687, 1), new class_1799(ItemInit.JUNGLE_TRELLIS_ITEM, 2), 16, 3, 0.02F)
         )
      );
      TradeOfferHelper.registerVillagerOffers(
         ModVillagers.BOTANIST,
         2,
         factories -> factories.add(
            (trader, rand) -> new class_1914(new class_9306(class_1802.field_8687, 1), new class_1799(ItemInit.ACACIA_TRELLIS_ITEM, 2), 16, 3, 0.02F)
         )
      );
      TradeOfferHelper.registerVillagerOffers(
         ModVillagers.BOTANIST,
         2,
         factories -> factories.add(
            (trader, rand) -> new class_1914(new class_9306(class_1802.field_8687, 1), new class_1799(ItemInit.DARK_OAK_TRELLIS_ITEM, 2), 16, 3, 0.02F)
         )
      );
      TradeOfferHelper.registerVillagerOffers(
         ModVillagers.BOTANIST,
         2,
         factories -> factories.add(
            (trader, rand) -> new class_1914(new class_9306(class_1802.field_8687, 1), new class_1799(ItemInit.MANGROVE_TRELLIS_ITEM, 2), 16, 3, 0.02F)
         )
      );
      TradeOfferHelper.registerVillagerOffers(
         ModVillagers.BOTANIST,
         2,
         factories -> factories.add(
            (trader, rand) -> new class_1914(new class_9306(class_1802.field_8687, 1), new class_1799(ItemInit.CRIMSON_TRELLIS_ITEM, 2), 16, 3, 0.02F)
         )
      );
      TradeOfferHelper.registerVillagerOffers(
         ModVillagers.BOTANIST,
         2,
         factories -> factories.add(
            (trader, rand) -> new class_1914(new class_9306(class_1802.field_8687, 1), new class_1799(ItemInit.CHERRY_TRELLIS_ITEM, 2), 16, 3, 0.02F)
         )
      );
      TradeOfferHelper.registerVillagerOffers(
         ModVillagers.BOTANIST,
         2,
         factories -> factories.add(
            (trader, rand) -> new class_1914(new class_9306(class_1802.field_8687, 1), new class_1799(ItemInit.WARPED_TRELLIS_ITEM, 2), 16, 3, 0.02F)
         )
      );
      TradeOfferHelper.registerVillagerOffers(
         ModVillagers.BOTANIST,
         2,
         factories -> factories.add(
            (trader, rand) -> new class_1914(new class_9306(class_1802.field_8687, 4), new class_1799(class_1802.field_28657, 3), 6, 9, 0.02F)
         )
      );
      TradeOfferHelper.registerVillagerOffers(
         ModVillagers.BOTANIST,
         2,
         factories -> factories.add(
            (trader, rand) -> new class_1914(new class_9306(class_1802.field_8687, 3), new class_1799(class_1802.field_28658, 4), 6, 8, 0.02F)
         )
      );
      TradeOfferHelper.registerVillagerOffers(
         ModVillagers.BOTANIST,
         3,
         factories -> factories.add(
            (trader, rand) -> new class_1914(new class_9306(class_1802.field_8687, 1), new class_1799(class_1802.field_17524, 4), 8, 5, 0.02F)
         )
      );
      TradeOfferHelper.registerVillagerOffers(
         ModVillagers.BOTANIST,
         3,
         factories -> factories.add(
            (trader, rand) -> new class_1914(new class_9306(class_1802.field_8687, 3), new class_1799(class_1802.field_28652, 1), 12, 10, 0.02F)
         )
      );
      TradeOfferHelper.registerVillagerOffers(
         ModVillagers.BOTANIST,
         4,
         factories -> factories.add(
            (trader, rand) -> new class_1914(new class_9306(class_1802.field_8687, 1), new class_1799(class_1802.field_28654, 2), 48, 3, 0.02F)
         )
      );
      TradeOfferHelper.registerVillagerOffers(
         ModVillagers.BOTANIST,
         4,
         factories -> factories.add(
            (trader, rand) -> new class_1914(new class_9306(class_1802.field_8687, 2), new class_1799(class_1802.field_28651, 1), 16, 7, 0.02F)
         )
      );
      TradeOfferHelper.registerVillagerOffers(
         ModVillagers.BOTANIST,
         5,
         factories -> factories.add(
            (trader, rand) -> new class_1914(new class_9306(class_1802.field_8687, 1), new class_1799(class_1802.field_28655, 4), 24, 3, 0.02F)
         )
      );
      TradeOfferHelper.registerVillagerOffers(
         ModVillagers.BOTANIST,
         5,
         factories -> factories.add(
            (trader, rand) -> new class_1914(new class_9306(class_1802.field_8687, 1), new class_1799(class_1802.field_28656, 3), 10, 7, 0.02F)
         )
      );
   }
}
