package net.p3pp3rf1y.sophisticatedbackpacks.data;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import io.github.fabricators_of_create.porting_lib.conditions.ICondition;
import io.github.fabricators_of_create.porting_lib.loot.GlobalLootModifierProvider;
import io.github.fabricators_of_create.porting_lib.loot.IGlobalLootModifier;
import io.github.fabricators_of_create.porting_lib.loot.LootModifier;
import io.github.fabricators_of_create.porting_lib.loot.LootTableIdCondition;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import java.util.concurrent.CompletableFuture;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.class_117;
import net.minecraft.class_131;
import net.minecraft.class_1799;
import net.minecraft.class_2405;
import net.minecraft.class_39;
import net.minecraft.class_47;
import net.minecraft.class_52;
import net.minecraft.class_5321;
import net.minecraft.class_5341;
import net.minecraft.class_55;
import net.minecraft.class_7924;
import net.minecraft.class_47.class_8487;
import net.minecraft.class_7225.class_7874;
import net.p3pp3rf1y.sophisticatedbackpacks.init.ModItems;

public class SBLootModifierProvider extends GlobalLootModifierProvider {
   SBLootModifierProvider(FabricDataOutput packOutput, CompletableFuture<class_7874> registries) {
      super(packOutput, registries, "sophisticatedbackpacks");
   }

   protected void start() {
      this.addInjectLootTableModifier(SBInjectLootSubProvider.SIMPLE_DUNGEON, class_39.field_356);
      this.addInjectLootTableModifier(SBInjectLootSubProvider.ABANDONED_MINESHAFT, class_39.field_472);
      this.addInjectLootTableModifier(SBInjectLootSubProvider.DESERT_PYRAMID, class_39.field_885);
      this.addInjectLootTableModifier(SBInjectLootSubProvider.WOODLAND_MANSION, class_39.field_484);
      this.addInjectLootTableModifier(SBInjectLootSubProvider.SHIPWRECK_TREASURE, class_39.field_665);
      this.addInjectLootTableModifier(SBInjectLootSubProvider.BASTION_TREASURE, class_39.field_24046);
      this.addInjectLootTableModifier(SBInjectLootSubProvider.END_CITY_TREASURE, class_39.field_274);
      this.addInjectLootTableModifier(SBInjectLootSubProvider.NETHER_BRIDGE, class_39.field_615);
   }

   private void addInjectLootTableModifier(class_5321<class_52> lootTable, class_5321<class_52> lootTableToInjectInto) {
      this.add(
         lootTableToInjectInto.method_29177().method_12832(),
         new SBLootModifierProvider.InjectLootModifier(lootTable, lootTableToInjectInto),
         new ICondition[0]
      );
   }

   public static class InjectLootModifier extends LootModifier {
      public static final MapCodec<SBLootModifierProvider.InjectLootModifier> CODEC = RecordCodecBuilder.mapCodec(
         inst -> LootModifier.codecStart(inst)
            .and(
               inst.group(
                  class_5321.method_39154(class_7924.field_50079).fieldOf("loot_table").forGetter(m -> m.lootTable),
                  class_5321.method_39154(class_7924.field_50079).fieldOf("loot_table_to_inject_into").forGetter(m -> m.lootTableToInjectInto)
               )
            )
            .apply(inst, SBLootModifierProvider.InjectLootModifier::new)
      );
      private final class_5321<class_52> lootTable;
      private final class_5321<class_52> lootTableToInjectInto;
      private BiFunction<class_1799, class_47, class_1799> compositeFunction;

      protected InjectLootModifier(class_5341[] conditions, class_5321<class_52> lootTable, class_5321<class_52> lootTableToInjectInto) {
         super(conditions);
         this.lootTable = lootTable;
         this.lootTableToInjectInto = lootTableToInjectInto;
      }

      protected InjectLootModifier(class_5321<class_52> lootTable, class_5321<class_52> lootTableToInjectInto) {
         this(
            new class_5341[]{SBLootEnabledCondition.builder().build(), LootTableIdCondition.builder(lootTableToInjectInto.method_29177()).build()},
            lootTable,
            lootTableToInjectInto
         );
      }

      protected ObjectArrayList<class_1799> doApply(ObjectArrayList<class_1799> generatedLoot, class_47 context) {
         context.method_51183()
            .method_58561(class_7924.field_50079, this.lootTable)
            .ifPresent(
               extraTable -> this.getRandomItemsRaw((class_52)extraTable.comp_349(), context, class_52.method_332(context.method_299(), generatedLoot::add))
            );
         return generatedLoot;
      }

      public void getRandomItemsRaw(class_52 table, class_47 context, Consumer<class_1799> lootConsumer) {
         if (this.compositeFunction == null) {
            this.compositeFunction = class_131.method_594(table.field_944);
         }

         class_8487<?> visitedEntry = class_47.method_51185(table);
         if (context.method_298(visitedEntry)) {
            Consumer<class_1799> consumer = class_117.method_513(this.compositeFunction, lootConsumer, context);

            for (class_55 lootPool : table.field_943) {
               lootPool.method_341(consumer, context);
            }

            context.method_295(visitedEntry);
         } else {
            class_2405.field_40831.warn("Detected infinite loop in loot tables");
         }
      }

      public MapCodec<? extends IGlobalLootModifier> codec() {
         return ModItems.INJECT_LOOT.get();
      }
   }
}
