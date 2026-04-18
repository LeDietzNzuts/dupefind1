package noobanidus.mods.lootr.common.api.data;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.class_1263;
import net.minecraft.class_1657;
import net.minecraft.class_173;
import net.minecraft.class_174;
import net.minecraft.class_1799;
import net.minecraft.class_181;
import net.minecraft.class_1937;
import net.minecraft.class_2338;
import net.minecraft.class_3218;
import net.minecraft.class_3222;
import net.minecraft.class_47;
import net.minecraft.class_52;
import net.minecraft.class_5321;
import net.minecraft.class_8567;
import net.minecraft.class_47.class_48;
import net.minecraft.class_8567.class_8568;
import noobanidus.mods.lootr.common.api.LootrAPI;
import noobanidus.mods.lootr.common.api.filter.ILootrFilter;
import noobanidus.mods.lootr.common.mixin.accessor.AccessorMixinLootTable;
import org.jetbrains.annotations.NotNull;

public class DefaultBrushableLootFiller implements LootFiller {
   private static final DefaultBrushableLootFiller INSTANCE = new DefaultBrushableLootFiller();

   public static DefaultBrushableLootFiller getInstance() {
      return INSTANCE;
   }

   @Override
   public void unpackLootTable(@NotNull ILootrInfoProvider provider, @NotNull class_1657 player, class_1263 inventory) {
      class_1937 level = provider.getInfoLevel();
      if (level != null && !level.method_8608() && level.method_8503() != null) {
         class_2338 pos = provider.getInfoPos();
         class_5321<class_52> lootTable = provider.getInfoLootTable();
         if (provider.isInfoReferenceInventory()) {
            LootrAPI.LOG
               .error(
                  "Unable to fill loot brushable in {} at {} as the provider is marked as a reference inventory, which is not supported for brushables!",
                  level.method_27983().method_29177(),
                  pos
               );
         } else {
            if (lootTable == null) {
               LootrAPI.LOG.error("Unable to fill loot container in {} at {} as the loot table is null!", level.method_27983().method_29177(), pos);
            } else {
               long seed = LootrAPI.getLootSeed(provider.getInfoLootSeed());
               class_52 loottable = level.method_8503().method_58576().method_58295(lootTable);
               if (loottable == class_52.field_948) {
                  LootrAPI.LOG
                     .error(
                        "Unable to fill loot container in {} at {} as the loot table '{}' couldn't be resolved! Please search the loot table in `latest.log` to see if there are errors in loading.",
                        level.method_27983().method_29177(),
                        pos,
                        lootTable.method_29177()
                     );
                  if (LootrAPI.reportUnresolvedTables()) {
                     player.method_7353(LootrAPI.getInvalidTableComponent(lootTable), false);
                  }
               }

               if (player instanceof class_3222 sPlayer) {
                  class_174.field_24479.method_27993(sPlayer, lootTable);
               }

               class_8567 params = new class_8568((class_3218)level)
                  .method_51874(class_181.field_24424, provider.getInfoVec())
                  .method_51871(player.method_7292())
                  .method_51874(class_181.field_1226, player)
                  .method_51875(class_173.field_1179);
               class_47 context = new class_48(params).method_304(seed).method_309(((AccessorMixinLootTable)loottable).getRandomSequence());
               ObjectArrayList<class_1799> items = new ObjectArrayList();
               loottable.method_320(context, items::add);
               LootFiller.LootFillerState state = new LootFiller.LootFillerState(provider, player, lootTable, loottable, inventory, params, seed);

               for (ILootrFilter filter : LootrAPI.getFilters()) {
                  if (filter.mutate(items, state, context, context.method_294())) {
                     break;
                  }
               }
               class_1799 theItem = switch (items.size()) {
                  case 0 -> class_1799.field_8037;
                  case 1 -> (class_1799)items.getFirst();
                  default -> {
                     LootrAPI.LOG
                        .error(
                           "Brushable loot table '{}' in {} at {} returned multiple items, only one item is expected! Using the first item.",
                           lootTable.method_29177(),
                           level.method_27983().method_29177(),
                           pos
                        );
                     yield (class_1799)items.getFirst();
                  }
               };
               inventory.method_5447(0, theItem);
            }
         }
      } else {
         LootrAPI.LOG.error("Unable to fill loot brushable as the level is null, client-side, or the server is null!");
      }
   }
}
