package com.magistuarmory.item.armor;

import com.magistuarmory.EpicKnights;
import com.magistuarmory.config.ArmorConfig;
import dev.architectury.registry.registries.DeferredRegister;
import java.util.function.Supplier;
import net.minecraft.class_1741;
import net.minecraft.class_1856;
import net.minecraft.class_2960;
import net.minecraft.class_3414;
import net.minecraft.class_3417;
import net.minecraft.class_6862;
import net.minecraft.class_6880;
import net.minecraft.class_7924;

public class ArmorTypes {
   public static final ArmorConfig ARMOR_CONFIG = EpicKnights.CONFIG.armor;
   public static DeferredRegister<class_1741> ARMOR_MATERIALS = DeferredRegister.create("magistuarmory", class_7924.field_48977);
   public static final ArmorType MINICROWN = create(
      ARMOR_MATERIALS,
      class_2960.method_60655("magistuarmory", "minicrown"),
      class_2960.method_60654("magistuarmory:minicrown"),
      ARMOR_CONFIG.get("minicrown"),
      class_3417.field_14761,
      false,
      "c:ingots/gold"
   );
   public static final ArmorType CROWN = create(
      ARMOR_MATERIALS,
      class_2960.method_60655("magistuarmory", "crown"),
      class_2960.method_60654("magistuarmory:crown"),
      ARMOR_CONFIG.get("crown"),
      class_3417.field_14761,
      true,
      "c:ingots/gold"
   );
   public static final ArmorType FLOWERCROWN = create(
      ARMOR_MATERIALS,
      class_2960.method_60655("magistuarmory", "flowercrown"),
      class_2960.method_60654("magistuarmory:flowercrown"),
      ARMOR_CONFIG.get("flowercrown"),
      class_3417.field_14581,
      true
   );
   public static final ArmorType KNIGHT = create(
      ARMOR_MATERIALS,
      class_2960.method_60655("magistuarmory", "knight"),
      class_2960.method_60656("default"),
      ARMOR_CONFIG.get("knight"),
      class_3417.field_14862,
      false,
      "c:ingots/steel"
   );
   public static final ArmorType ARMET = create(
      ARMOR_MATERIALS,
      class_2960.method_60655("magistuarmory", "armet"),
      class_2960.method_60654("magistuarmory:armet"),
      ARMOR_CONFIG.get("armet"),
      class_3417.field_14862,
      true,
      "c:ingots/steel"
   );
   public static final ArmorType STECHHELM = create(
      ARMOR_MATERIALS,
      class_2960.method_60655("magistuarmory", "stechhelm"),
      class_2960.method_60654("magistuarmory:stechhelm"),
      ARMOR_CONFIG.get("stechhelm"),
      class_3417.field_14862,
      true,
      "c:ingots/steel"
   );
   public static final ArmorType JOUSTING = create(
      ARMOR_MATERIALS,
      class_2960.method_60655("magistuarmory", "jousting"),
      class_2960.method_60656("default"),
      ARMOR_CONFIG.get("jousting"),
      class_3417.field_14862,
      false,
      "c:ingots/steel"
   );
   public static final ArmorType SALLET = create(
      ARMOR_MATERIALS,
      class_2960.method_60655("magistuarmory", "sallet"),
      class_2960.method_60654("magistuarmory:sallet"),
      ARMOR_CONFIG.get("sallet"),
      class_3417.field_14862,
      false,
      "c:ingots/steel"
   );
   public static final ArmorType GOTHIC = create(
      ARMOR_MATERIALS,
      class_2960.method_60655("magistuarmory", "gothic"),
      class_2960.method_60656("default"),
      ARMOR_CONFIG.get("gothic"),
      class_3417.field_14862,
      false,
      "c:ingots/steel"
   );
   public static final ArmorType MAXIMILIAN_HELMET = create(
      ARMOR_MATERIALS,
      class_2960.method_60655("magistuarmory", "maximilian_helmet"),
      class_2960.method_60654("magistuarmory:maximilian_helmet"),
      ARMOR_CONFIG.get("maximilianHelmet"),
      class_3417.field_14862,
      false,
      "c:ingots/steel"
   );
   public static final ArmorType MAXIMILIAN = create(
      ARMOR_MATERIALS,
      class_2960.method_60655("magistuarmory", "maximilian"),
      class_2960.method_60656("default"),
      ARMOR_CONFIG.get("maximilian"),
      class_3417.field_14862,
      false,
      "c:ingots/steel"
   );
   public static final ArmorType CHAINMAIL = create(
      ARMOR_MATERIALS,
      class_2960.method_60655("magistuarmory", "chainmail"),
      class_2960.method_60654("magistuarmory:conic_helmet"),
      ARMOR_CONFIG.get("chainmail"),
      class_3417.field_15191,
      false,
      "c:ingots/steel"
   );
   public static final ArmorType KETTLEHAT = create(
      ARMOR_MATERIALS,
      class_2960.method_60655("magistuarmory", "kettlehat"),
      class_2960.method_60654("magistuarmory:kettlehat"),
      ARMOR_CONFIG.get("kettlehat"),
      class_3417.field_15191,
      false,
      "c:ingots/steel"
   );
   public static final ArmorType PLATEMAIL = create(
      ARMOR_MATERIALS,
      class_2960.method_60655("magistuarmory", "platemail"),
      class_2960.method_60656("default"),
      ARMOR_CONFIG.get("platemail"),
      class_3417.field_15191,
      false,
      "c:ingots/steel"
   );
   public static final ArmorType BARBUTE = create(
      ARMOR_MATERIALS,
      class_2960.method_60655("magistuarmory", "barbute"),
      class_2960.method_60654("magistuarmory:barbute"),
      ARMOR_CONFIG.get("barbute"),
      class_3417.field_14862,
      false,
      "c:ingots/steel"
   );
   public static final ArmorType HALFARMOR = create(
      ARMOR_MATERIALS,
      class_2960.method_60655("magistuarmory", "halfarmor"),
      class_2960.method_60656("default"),
      ARMOR_CONFIG.get("halfarmor"),
      class_3417.field_14862,
      false,
      "c:ingots/steel"
   );
   public static final ArmorType GREATHELM = create(
      ARMOR_MATERIALS,
      class_2960.method_60655("magistuarmory", "greathelm"),
      class_2960.method_60654("magistuarmory:crusader"),
      ARMOR_CONFIG.get("crusader"),
      class_3417.field_15191,
      true,
      "c:ingots/steel"
   );
   public static final ArmorType CRUSADER = create(
      ARMOR_MATERIALS,
      class_2960.method_60655("magistuarmory", "crusader"),
      class_2960.method_60656("default"),
      ARMOR_CONFIG.get("crusader"),
      class_3417.field_15191,
      true,
      "c:ingots/steel"
   );
   public static final ArmorType BRIGANDINE = create(
      ARMOR_MATERIALS,
      class_2960.method_60655("magistuarmory", "brigandine"),
      class_2960.method_60656("default"),
      ARMOR_CONFIG.get("brigandine"),
      class_3417.field_14581,
      true,
      "c:ingots/steel"
   );
   public static final ArmorType GAMBESON = create(
      ARMOR_MATERIALS,
      class_2960.method_60655("magistuarmory", "gambeson"),
      class_2960.method_60656("default"),
      ARMOR_CONFIG.get("gambeson"),
      class_3417.field_14581,
      true,
      () -> class_1856.method_8106(class_6862.method_40092(class_7924.field_41197, class_2960.method_60654("magistuarmory:woolen_fabric")))
   );
   public static final ArmorType CEREMONIAL_ARMET = create(
      ARMOR_MATERIALS,
      class_2960.method_60655("magistuarmory", "ceremonialarmet"),
      class_2960.method_60654("magistuarmory:armet"),
      ARMOR_CONFIG.get("ceremonialArmet"),
      class_3417.field_14862,
      false,
      "c:ingots/steel"
   );
   public static final ArmorType CEREMONIAL = create(
      ARMOR_MATERIALS,
      class_2960.method_60655("magistuarmory", "ceremonial"),
      class_2960.method_60656("default"),
      ARMOR_CONFIG.get("ceremonial"),
      class_3417.field_14862,
      false,
      "c:ingots/steel"
   );
   public static final ArmorType SHISHAK = create(
      ARMOR_MATERIALS,
      class_2960.method_60655("magistuarmory", "shishak"),
      class_2960.method_60654("magistuarmory:conic_helmet"),
      ARMOR_CONFIG.get("shishak"),
      class_3417.field_14862,
      false,
      "c:ingots/steel"
   );
   public static final ArmorType NORMAN = create(
      ARMOR_MATERIALS,
      class_2960.method_60655("magistuarmory", "norman"),
      class_2960.method_60654("magistuarmory:conic_helmet"),
      ARMOR_CONFIG.get("norman"),
      class_3417.field_14862,
      true,
      "c:ingots/steel"
   );
   public static final ArmorType RUSTED_BARBUTE = create(
      ARMOR_MATERIALS,
      class_2960.method_60655("magistuarmory", "rustedbarbute"),
      class_2960.method_60654("magistuarmory:barbute"),
      ARMOR_CONFIG.get("rustedBarbute"),
      class_3417.field_14862,
      false,
      "c:ingots/steel"
   );
   public static final ArmorType RUSTED_HALFARMOR = create(
      ARMOR_MATERIALS,
      class_2960.method_60655("magistuarmory", "rustedhalfarmor"),
      class_2960.method_60656("default"),
      ARMOR_CONFIG.get("rustedHalfarmor"),
      class_3417.field_14862,
      false,
      "c:ingots/steel"
   );
   public static final ArmorType RUSTED_CHAINMAIL = create(
      ARMOR_MATERIALS,
      class_2960.method_60655("magistuarmory", "rustedchainmail"),
      class_2960.method_60654("magistuarmory:conic_helmet"),
      ARMOR_CONFIG.get("rustedChainmail"),
      class_3417.field_15191,
      false,
      "c:ingots/steel"
   );
   public static final ArmorType RUSTED_KETTLEHAT = create(
      ARMOR_MATERIALS,
      class_2960.method_60655("magistuarmory", "rustedkettlehat"),
      class_2960.method_60654("magistuarmory:kettlehat"),
      ARMOR_CONFIG.get("rustedKettlehat"),
      class_3417.field_15191,
      false,
      "c:ingots/steel"
   );
   public static final ArmorType RUSTED_NORMAN = create(
      ARMOR_MATERIALS,
      class_2960.method_60655("magistuarmory", "rustednorman"),
      class_2960.method_60654("magistuarmory:conic_helmet"),
      ARMOR_CONFIG.get("rustedNorman"),
      class_3417.field_15191,
      false,
      "c:ingots/steel"
   );
   public static final ArmorType RUSTED_GREATHELM = create(
      ARMOR_MATERIALS,
      class_2960.method_60655("magistuarmory", "rustedgreathelm"),
      class_2960.method_60654("magistuarmory:crusader"),
      ARMOR_CONFIG.get("rustedCrusader"),
      class_3417.field_15191,
      false,
      "c:ingots/steel"
   );
   public static final ArmorType RUSTED_CRUSADER = create(
      ARMOR_MATERIALS,
      class_2960.method_60655("magistuarmory", "rustedcrusader"),
      class_2960.method_60656("default"),
      ARMOR_CONFIG.get("rustedCrusader"),
      class_3417.field_15191,
      false,
      "c:ingots/steel"
   );
   public static final ArmorType BASCINET = create(
      ARMOR_MATERIALS,
      class_2960.method_60655("magistuarmory", "bascinet"),
      class_2960.method_60654("magistuarmory:bascinet"),
      ARMOR_CONFIG.get("bascinet"),
      class_3417.field_14862,
      false,
      "c:ingots/steel"
   );
   public static final ArmorType XIV_CENTURY_KNIGHT = create(
      ARMOR_MATERIALS,
      class_2960.method_60655("magistuarmory", "xivcenturyknight"),
      class_2960.method_60656("default"),
      ARMOR_CONFIG.get("xivCenturyKnight"),
      class_3417.field_14862,
      false,
      "c:ingots/steel"
   );
   public static final ArmorType WINGED_HUSSAR_CHESTPLATE = create(
      ARMOR_MATERIALS,
      class_2960.method_60655("magistuarmory", "wingedhussarchestplate"),
      class_2960.method_60654("magistuarmory:wingedhussarchestplate"),
      ARMOR_CONFIG.get("wingedHussarChestplate"),
      class_3417.field_14862,
      false,
      "c:ingots/steel"
   );
   public static final ArmorType CUIRASSIER = create(
      ARMOR_MATERIALS,
      class_2960.method_60655("magistuarmory", "cuirassier"),
      class_2960.method_60656("default"),
      ARMOR_CONFIG.get("cuirassier"),
      class_3417.field_14862,
      true,
      "c:ingots/steel"
   );
   public static final ArmorType KASTENBRUST = create(
      ARMOR_MATERIALS,
      class_2960.method_60655("magistuarmory", "kastenbrust"),
      class_2960.method_60656("default"),
      ARMOR_CONFIG.get("kastenbrust"),
      class_3417.field_14862,
      false,
      "c:ingots/steel"
   );
   public static final ArmorType GRAND_BASCINET = create(
      ARMOR_MATERIALS,
      class_2960.method_60655("magistuarmory", "grand_bascinet"),
      class_2960.method_60654("magistuarmory:grand_bascinet"),
      ARMOR_CONFIG.get("grandBascinet"),
      class_3417.field_14862,
      false,
      "c:ingots/steel"
   );
   public static final ArmorType LAMELLAR = create(
      ARMOR_MATERIALS,
      class_2960.method_60655("magistuarmory", "lamellar"),
      class_2960.method_60656("default"),
      ARMOR_CONFIG.get("lamellar"),
      class_3417.field_15191,
      false,
      "c:ingots/steel"
   );

   private static ArmorType create(
      DeferredRegister<class_1741> armorMaterial,
      class_2960 location,
      class_2960 modelLocation,
      ArmorConfig.ArmorTypeConfig config,
      class_6880<class_3414> equipSound,
      boolean dyeable,
      String repairItemTag
   ) {
      return new ArmorType(
         armorMaterial,
         location,
         modelLocation,
         config.toughness,
         config.knockbackResistance,
         new Integer[]{config.bootsDurability, config.leggingsDurability, config.chestplateDurability, config.helmetDurability},
         new Integer[]{config.bootsDefense, config.leggingsDefense, config.chestplateDefense, config.helmetDefense},
         config.enchantmentValue,
         equipSound,
         config.enabled,
         dyeable,
         repairItemTag
      );
   }

   private static ArmorType create(
      DeferredRegister<class_1741> armorMaterial,
      class_2960 location,
      class_2960 modelLocation,
      ArmorConfig.ArmorTypeConfig config,
      class_6880<class_3414> equipSound,
      boolean dyeable,
      Supplier<class_1856> ingredientSupplier
   ) {
      return new ArmorType(
         armorMaterial,
         location,
         modelLocation,
         config.toughness,
         config.knockbackResistance,
         new Integer[]{config.bootsDurability, config.leggingsDurability, config.chestplateDurability, config.helmetDurability},
         new Integer[]{config.bootsDefense, config.leggingsDefense, config.chestplateDefense, config.helmetDefense},
         config.enchantmentValue,
         equipSound,
         config.enabled,
         dyeable,
         ingredientSupplier
      );
   }

   private static ArmorType create(
      DeferredRegister<class_1741> armorMaterial,
      class_2960 location,
      class_2960 modelLocation,
      ArmorConfig.ArmorTypeConfig config,
      class_6880<class_3414> equipSound,
      boolean dyeable
   ) {
      return new ArmorType(
         armorMaterial,
         location,
         modelLocation,
         config.toughness,
         config.knockbackResistance,
         new Integer[]{config.bootsDurability, config.leggingsDurability, config.chestplateDurability, config.helmetDurability},
         new Integer[]{config.bootsDefense, config.leggingsDefense, config.chestplateDefense, config.helmetDefense},
         config.enchantmentValue,
         equipSound,
         dyeable,
         config.enabled
      );
   }

   public static void init() {
      ARMOR_MATERIALS.register();
   }
}
