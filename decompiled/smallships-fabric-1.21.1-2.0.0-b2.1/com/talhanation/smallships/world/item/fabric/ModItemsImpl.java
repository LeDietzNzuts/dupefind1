package com.talhanation.smallships.world.item.fabric;

import com.talhanation.smallships.config.SmallShipsConfig;
import com.talhanation.smallships.world.item.BriggItem;
import com.talhanation.smallships.world.item.CannonBallItem;
import com.talhanation.smallships.world.item.CannonItem;
import com.talhanation.smallships.world.item.CogItem;
import com.talhanation.smallships.world.item.DrakkarItem;
import com.talhanation.smallships.world.item.GalleyItem;
import com.talhanation.smallships.world.item.ModItems;
import com.talhanation.smallships.world.item.SailItem;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents.ModifyEntries;
import net.minecraft.class_1747;
import net.minecraft.class_1761;
import net.minecraft.class_1792;
import net.minecraft.class_1799;
import net.minecraft.class_1802;
import net.minecraft.class_1935;
import net.minecraft.class_2378;
import net.minecraft.class_2561;
import net.minecraft.class_2960;
import net.minecraft.class_5321;
import net.minecraft.class_7706;
import net.minecraft.class_7923;
import net.minecraft.class_7924;
import net.minecraft.class_1690.class_1692;
import net.minecraft.class_1792.class_1793;

public class ModItemsImpl {
   private static final Map<String, class_1792> entries = new HashMap<>();

   public static class_1792 getItem(String id) {
      return entries.get(id);
   }

   private static void register(String id, class_1792 item) {
      entries.put(id, register(class_2960.method_60655("smallships", id), item));
   }

   private static class_1792 register(class_2960 id, class_1792 item) {
      if (item instanceof class_1747 blockItem) {
         blockItem.method_7713(class_1792.field_8003, blockItem);
      }

      return (class_1792)class_2378.method_10230(class_7923.field_41178, id, item);
   }

   static {
      if ((Boolean)SmallShipsConfig.Common.smallshipsItemGroupEnable.get()) {
         class_5321<class_1761> creativeModeTab = class_5321.method_29179(class_7924.field_44688, class_2960.method_60655("smallships", "creative_mode_tab"));
         class_1761 customCreativeModeTab = FabricItemGroup.builder()
            .method_47321(class_2561.method_43471(creativeModeTab.method_29177().toString().replace(":", ".")))
            .method_47320(() -> new class_1799(ModItems.CANNON))
            .method_47317(
               (itemDisplayParameters, output) -> itemDisplayParameters.comp_1253()
                  .method_46759(class_7924.field_41197)
                  .ifPresent(
                     registryLookup -> registryLookup.method_46754()
                        .filter(itemResourceKey -> "smallships".equals(itemResourceKey.method_29177().method_12836()))
                        .forEach(itemResourceKey -> output.method_45421((class_1935)class_7923.field_41178.method_31140(itemResourceKey)))
                  )
            )
            .method_47324();
         class_2378.method_39197(class_7923.field_44687, creativeModeTab, customCreativeModeTab);
      } else {
         ItemGroupEvents.modifyEntriesEvent(class_7706.field_41059)
            .register((ModifyEntries)entries -> entries.addBefore(class_1802.field_8539, new class_1935[]{ModItems.SAIL}));
         ItemGroupEvents.modifyEntriesEvent(class_7706.field_40202)
            .register((ModifyEntries)entries -> entries.addAfter(class_1802.field_8399, new class_1935[]{ModItems.CANNON, ModItems.CANNON_BALL}));
         ItemGroupEvents.modifyEntriesEvent(class_7706.field_41060).register((ModifyEntries)entries -> {
            List<class_1792> shipItems = new ArrayList<>();

            for (class_1692 typex : class_1692.values()) {
               shipItems.add(ModItems.COG_ITEMS.get(typex));
               shipItems.add(ModItems.BRIGG_ITEMS.get(typex));
               shipItems.add(ModItems.GALLEY_ITEMS.get(typex));
               shipItems.add(ModItems.DRAKKAR_ITEMS.get(typex));
            }

            entries.addBefore(class_1802.field_8129, (class_1935[])shipItems.toArray(class_1792[]::new));
         });
      }

      register("sail", new SailItem(new class_1793().method_7889(16)));
      register("cannon", new CannonItem(new class_1793().method_7889(1)));
      register("cannon_ball", new CannonBallItem(new class_1793().method_7889(16)));

      for (class_1692 type : class_1692.values()) {
         String name = type.method_7559().replaceAll("[^a-z0-9_.-]", "_");
         register(name + "_cog", new CogItem(type, new class_1793().method_7889(1)));
         register(name + "_brigg", new BriggItem(type, new class_1793().method_7889(1)));
         register(name + "_galley", new GalleyItem(type, new class_1793().method_7889(1)));
         register(name + "_drakkar", new DrakkarItem(type, new class_1793().method_7889(1)));
      }
   }
}
