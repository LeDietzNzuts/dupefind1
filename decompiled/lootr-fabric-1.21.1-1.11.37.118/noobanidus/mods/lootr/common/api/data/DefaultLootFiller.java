package noobanidus.mods.lootr.common.api.data;

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
import net.minecraft.class_52;
import net.minecraft.class_5321;
import net.minecraft.class_8567;
import net.minecraft.class_8567.class_8568;
import noobanidus.mods.lootr.common.api.LootrAPI;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class DefaultLootFiller implements LootFiller {
   private static final DefaultLootFiller INSTANCE = new DefaultLootFiller();
   private static LootFiller.LootFillerState state = null;

   public static DefaultLootFiller getInstance() {
      return INSTANCE;
   }

   @Nullable
   public static LootFiller.LootFillerState getFillerState() {
      return state;
   }

   @Override
   public void unpackLootTable(ILootrInfoProvider provider, @NotNull class_1657 player, class_1263 inventory) {
      class_1937 level = provider.getInfoLevel();
      if (level != null && !level.method_8608() && level.method_8503() != null) {
         class_2338 pos = provider.getInfoPos();
         class_5321<class_52> lootTable = provider.getInfoLootTable();
         if (provider.isInfoReferenceInventory() && provider.getInfoReferenceInventory() != null) {
            for (int i = 0; i < provider.getInfoReferenceInventory().size(); i++) {
               inventory.method_5447(i, ((class_1799)provider.getInfoReferenceInventory().get(i)).method_7972());
            }
         } else if (lootTable == null) {
            LootrAPI.LOG
               .error(
                  "Unable to fill loot container in {} at {} as the loot table is null and the provider is not a reference inventory!",
                  level.method_27983().method_29177(),
                  pos
               );
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

            class_8568 builder = new class_8568((class_3218)level)
               .method_51874(class_181.field_24424, provider.getInfoVec())
               .method_51871(player.method_7292())
               .method_51874(class_181.field_1226, player);
            this.fill(provider, player, lootTable, loottable, inventory, builder.method_51875(class_173.field_1179), seed);
         }
      } else {
         LootrAPI.LOG.error("Unable to fill loot container as the level is null, client-side, or the server is null!");
      }
   }

   public static void performFill(
      ILootrInfoProvider provider,
      class_1657 player,
      class_5321<class_52> lootTableKey,
      class_52 lootTable,
      class_1263 container,
      class_8567 parameters,
      long seed
   ) {
      setFillerState(new LootFiller.LootFillerState(provider, player, lootTableKey, lootTable, container, parameters, seed));
      lootTable.method_329(container, parameters, seed);
      setFillerState(null);
   }

   public static void setFillerState(@Nullable LootFiller.LootFillerState newState) {
      state = newState;
   }
}
