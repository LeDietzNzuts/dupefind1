package net.p3pp3rf1y.sophisticatedbackpacks.data;

import java.util.Set;
import java.util.function.BiConsumer;
import net.minecraft.class_1792;
import net.minecraft.class_2960;
import net.minecraft.class_39;
import net.minecraft.class_44;
import net.minecraft.class_52;
import net.minecraft.class_5321;
import net.minecraft.class_55;
import net.minecraft.class_73;
import net.minecraft.class_77;
import net.minecraft.class_7791;
import net.minecraft.class_7924;
import net.minecraft.class_52.class_53;
import net.minecraft.class_55.class_56;
import net.minecraft.class_7225.class_7874;
import net.minecraft.class_79.class_80;
import net.p3pp3rf1y.sophisticatedbackpacks.init.ModItems;

public class SBInjectLootSubProvider implements class_7791 {
   private static final String INJECT_FOLDER = "inject/";
   public static final class_5321<class_52> ABANDONED_MINESHAFT = createInjectLootTableRegistryKey(class_39.field_472);
   public static final class_5321<class_52> BASTION_TREASURE = createInjectLootTableRegistryKey(class_39.field_24046);
   public static final class_5321<class_52> DESERT_PYRAMID = createInjectLootTableRegistryKey(class_39.field_885);
   public static final class_5321<class_52> END_CITY_TREASURE = createInjectLootTableRegistryKey(class_39.field_274);
   public static final class_5321<class_52> NETHER_BRIDGE = createInjectLootTableRegistryKey(class_39.field_615);
   public static final class_5321<class_52> SHIPWRECK_TREASURE = createInjectLootTableRegistryKey(class_39.field_665);
   public static final class_5321<class_52> SIMPLE_DUNGEON = createInjectLootTableRegistryKey(class_39.field_356);
   public static final class_5321<class_52> WOODLAND_MANSION = createInjectLootTableRegistryKey(class_39.field_484);
   public static final Set<class_5321<class_52>> ALL_TABLES = Set.of(
      ABANDONED_MINESHAFT, BASTION_TREASURE, DESERT_PYRAMID, END_CITY_TREASURE, NETHER_BRIDGE, SHIPWRECK_TREASURE, SIMPLE_DUNGEON, WOODLAND_MANSION
   );

   private static class_5321<class_52> createInjectLootTableRegistryKey(class_5321<class_52> vanillaLootTable) {
      class_2960 location = class_2960.method_60655("sophisticatedbackpacks", "inject/" + vanillaLootTable.method_29177().method_12832());
      return class_5321.method_29179(class_7924.field_50079, location);
   }

   public SBInjectLootSubProvider(class_7874 registries) {
   }

   public void method_10399(BiConsumer<class_5321<class_52>, class_53> tables) {
      tables.accept(
         SIMPLE_DUNGEON,
         getLootTable(
            90,
            this.getItemLootEntry((class_1792)ModItems.BACKPACK.get(), 5),
            this.getItemLootEntry((class_1792)ModItems.COPPER_BACKPACK.get(), 3),
            this.getItemLootEntry((class_1792)ModItems.PICKUP_UPGRADE.get(), 2)
         )
      );
      tables.accept(
         ABANDONED_MINESHAFT,
         getLootTable(
            84,
            this.getItemLootEntry((class_1792)ModItems.BACKPACK.get(), 7),
            this.getItemLootEntry((class_1792)ModItems.COPPER_BACKPACK.get(), 5),
            this.getItemLootEntry((class_1792)ModItems.IRON_BACKPACK.get(), 3),
            this.getItemLootEntry((class_1792)ModItems.GOLD_BACKPACK.get(), 1),
            this.getItemLootEntry((class_1792)ModItems.MAGNET_UPGRADE.get(), 2)
         )
      );
      tables.accept(
         DESERT_PYRAMID,
         getLootTable(
            89,
            this.getItemLootEntry((class_1792)ModItems.COPPER_BACKPACK.get(), 5),
            this.getItemLootEntry((class_1792)ModItems.IRON_BACKPACK.get(), 3),
            this.getItemLootEntry((class_1792)ModItems.GOLD_BACKPACK.get(), 1),
            this.getItemLootEntry((class_1792)ModItems.MAGNET_UPGRADE.get(), 2)
         )
      );
      tables.accept(
         SHIPWRECK_TREASURE,
         getLootTable(
            92,
            this.getItemLootEntry((class_1792)ModItems.IRON_BACKPACK.get(), 4),
            this.getItemLootEntry((class_1792)ModItems.GOLD_BACKPACK.get(), 2),
            this.getItemLootEntry((class_1792)ModItems.ADVANCED_MAGNET_UPGRADE.get(), 2)
         )
      );
      tables.accept(
         WOODLAND_MANSION,
         getLootTable(
            92,
            this.getItemLootEntry((class_1792)ModItems.IRON_BACKPACK.get(), 4),
            this.getItemLootEntry((class_1792)ModItems.GOLD_BACKPACK.get(), 2),
            this.getItemLootEntry((class_1792)ModItems.ADVANCED_MAGNET_UPGRADE.get(), 2)
         )
      );
      tables.accept(
         NETHER_BRIDGE,
         getLootTable(
            90,
            this.getItemLootEntry((class_1792)ModItems.IRON_BACKPACK.get(), 5),
            this.getItemLootEntry((class_1792)ModItems.GOLD_BACKPACK.get(), 3),
            this.getItemLootEntry((class_1792)ModItems.FEEDING_UPGRADE.get(), 2)
         )
      );
      tables.accept(
         BASTION_TREASURE,
         getLootTable(
            90,
            this.getItemLootEntry((class_1792)ModItems.IRON_BACKPACK.get(), 3),
            this.getItemLootEntry((class_1792)ModItems.GOLD_BACKPACK.get(), 5),
            this.getItemLootEntry((class_1792)ModItems.FEEDING_UPGRADE.get(), 2)
         )
      );
      tables.accept(
         END_CITY_TREASURE,
         getLootTable(
            90,
            this.getItemLootEntry((class_1792)ModItems.DIAMOND_BACKPACK.get(), 3),
            this.getItemLootEntry((class_1792)ModItems.GOLD_BACKPACK.get(), 5),
            this.getItemLootEntry((class_1792)ModItems.ADVANCED_MAGNET_UPGRADE.get(), 2)
         )
      );
   }

   private class_80<?> getItemLootEntry(class_1792 item, int weight) {
      return class_77.method_411(item).method_437(weight);
   }

   private static class_53 getLootTable(int emptyWeight, class_80<?>... entries) {
      class_56 pool = class_55.method_347().name("main").method_352(class_44.method_32448(1.0F));

      for (class_80<?> entry : entries) {
         pool.method_351(entry);
      }

      pool.method_351(class_73.method_401().method_437(emptyWeight));
      return class_52.method_324().method_336(pool);
   }
}
