package ewewukek.musketmod;

import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import net.minecraft.class_156;
import net.minecraft.class_1761;
import net.minecraft.class_1792;
import net.minecraft.class_2561;
import net.minecraft.class_2960;
import net.minecraft.class_5321;
import net.minecraft.class_7696;
import net.minecraft.class_7706;
import net.minecraft.class_8052;
import net.minecraft.class_9331;
import net.minecraft.class_1792.class_1793;

public class Items {
   public static final class_1792 MUSKET = new MusketItem(new class_1793().method_7895(Config.musketDurability));
   public static final class_1792 MUSKET_WITH_BAYONET = new MusketItem(
      new class_1793().method_7895(Config.musketDurability).method_57348(MusketItem.createBayonetAttributes())
   );
   public static final class_1792 MUSKET_WITH_SCOPE = new ScopedMusketItem(new class_1793().method_7895(Config.scopedMusketDurability));
   public static final class_1792 BLUNDERBUSS = new BlunderbussItem(new class_1793().method_7895(Config.blunderbussDurability));
   public static final class_1792 PISTOL = new PistolItem(new class_1793().method_7895(Config.pistolDurability));
   public static final class_1792 CARTRIDGE = new CartridgeItem(new class_1793());
   public static final class_2960 EMPTY_SLOT_MUSKET = MusketMod.resource("item/empty_slot_musket");
   public static final class_2960 EMPTY_SLOT_SPYGLASS = class_2960.method_60656("item/empty_slot_spyglass");
   public static final class_1792 MUSKET_UPGRADE = new class_8052(
      class_2561.method_43471(class_156.method_646("item", MusketMod.resource("musket"))).method_27692(class_8052.field_41975),
      class_2561.method_43471(class_156.method_646("item", MusketMod.resource("musket_upgrade.ingredients"))).method_27692(class_8052.field_41975),
      class_2561.method_43471(class_156.method_646("item", MusketMod.resource("musket_upgrade"))).method_27692(class_8052.field_41974),
      class_2561.method_43471(class_156.method_646("item", MusketMod.resource("musket_upgrade.base_slot_description"))),
      class_2561.method_43471(class_156.method_646("item", MusketMod.resource("musket_upgrade.additions_slot_description"))),
      List.of(EMPTY_SLOT_MUSKET),
      List.of(class_8052.field_41958, EMPTY_SLOT_SPYGLASS),
      new class_7696[0]
   );

   public static void registerDataComponentTypes(BiConsumer<String, class_9331<?>> helper) {
      helper.accept("loaded", GunItem.LOADED);
      helper.accept("loading_stage", GunItem.LOADING_STAGE);
   }

   public static void register(BiConsumer<String, class_1792> helper) {
      helper.accept("musket", MUSKET);
      helper.accept("musket_with_bayonet", MUSKET_WITH_BAYONET);
      helper.accept("musket_with_scope", MUSKET_WITH_SCOPE);
      helper.accept("blunderbuss", BLUNDERBUSS);
      helper.accept("pistol", PISTOL);
      helper.accept("cartridge", CARTRIDGE);
      helper.accept("musket_upgrade_smithing_template", MUSKET_UPGRADE);
   }

   public static void addToCreativeTab(class_5321<class_1761> tab, Consumer<class_1792> helper) {
      if (tab == class_7706.field_40202) {
         helper.accept(MUSKET);
         helper.accept(MUSKET_WITH_BAYONET);
         helper.accept(MUSKET_WITH_SCOPE);
         helper.accept(BLUNDERBUSS);
         helper.accept(PISTOL);
         helper.accept(CARTRIDGE);
      }

      if (tab == class_7706.field_41062) {
         helper.accept(MUSKET_UPGRADE);
      }
   }
}
