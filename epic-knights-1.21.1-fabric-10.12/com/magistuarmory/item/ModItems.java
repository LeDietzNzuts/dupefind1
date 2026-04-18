package com.magistuarmory.item;

import com.magistuarmory.EpicKnights;
import com.magistuarmory.api.item.ModItemsProvider;
import com.magistuarmory.block.ModBlocks;
import com.magistuarmory.config.ShieldsConfig;
import com.magistuarmory.item.armor.ArmorTypes;
import com.magistuarmory.item.armor.DyeableMedievalArmorItem;
import com.magistuarmory.item.armor.DyeableWearableArmorDecorationItem;
import com.magistuarmory.item.armor.KnightItem;
import com.magistuarmory.item.armor.MedievalArmorItem;
import com.magistuarmory.item.armor.MedievalHorseArmorItem;
import com.magistuarmory.item.armor.WearableArmorDecorationItem;
import com.magistuarmory.misc.ModBannerPatternTags;
import dev.architectury.platform.Platform;
import dev.architectury.registry.registries.RegistrySupplier;
import java.util.List;
import java.util.function.BiFunction;
import me.shedaniel.cloth.clothconfig.shadowed.blue.endless.jankson.annotation.Nullable;
import net.minecraft.class_1740;
import net.minecraft.class_1745;
import net.minecraft.class_1767;
import net.minecraft.class_1792;
import net.minecraft.class_2561;
import net.minecraft.class_2960;
import net.minecraft.class_3620;
import net.minecraft.class_7696;
import net.minecraft.class_8052;
import net.minecraft.class_1738.class_8051;
import net.minecraft.class_1792.class_1793;

public class ModItems extends ModItemsProvider {
   private static final ShieldsConfig SHIELDS_CONFIG = EpicKnights.CONFIG.shields;
   public static ModItems INSTANCE = new ModItems();
   @Nullable
   public static final RegistrySupplier<KnightItem> ARMET = INSTANCE.addKnightItem("armet", ArmorTypes.ARMET, class_8051.field_41934, new class_1793());
   @Nullable
   public static final RegistrySupplier<MedievalArmorItem> KNIGHT_CHESTPLATE = INSTANCE.addMedievalArmorItem(
      "knight_chestplate", ArmorTypes.KNIGHT, class_8051.field_41935, new class_1793()
   );
   @Nullable
   public static final RegistrySupplier<MedievalArmorItem> KNIGHT_LEGGINGS = INSTANCE.addMedievalArmorItem(
      "knight_leggings", ArmorTypes.KNIGHT, class_8051.field_41936, new class_1793()
   );
   @Nullable
   public static final RegistrySupplier<MedievalArmorItem> KNIGHT_BOOTS = INSTANCE.addMedievalArmorItem(
      "knight_boots", ArmorTypes.KNIGHT, class_8051.field_41937, new class_1793()
   );
   @Nullable
   public static final RegistrySupplier<MedievalArmorItem> STECHHELM = INSTANCE.addJoustingItem(
      "stechhelm", ArmorTypes.STECHHELM, class_8051.field_41934, new class_1793()
   );
   @Nullable
   public static final RegistrySupplier<MedievalArmorItem> JOUSTING_CHESTPLATE = INSTANCE.addJoustingItem(
      "jousting_chestplate", ArmorTypes.JOUSTING, class_8051.field_41935, new class_1793()
   );
   @Nullable
   public static final RegistrySupplier<MedievalArmorItem> JOUSTING_LEGGINGS = INSTANCE.addJoustingItem(
      "jousting_leggings", ArmorTypes.JOUSTING, class_8051.field_41936, new class_1793()
   );
   @Nullable
   public static final RegistrySupplier<MedievalArmorItem> JOUSTING_BOOTS = INSTANCE.addJoustingItem(
      "jousting_boots", ArmorTypes.JOUSTING, class_8051.field_41937, new class_1793()
   );
   @Nullable
   public static final RegistrySupplier<MedievalArmorItem> SALLET = INSTANCE.addMedievalArmorItem(
      "sallet", ArmorTypes.SALLET, class_8051.field_41934, new class_1793()
   );
   @Nullable
   public static final RegistrySupplier<MedievalArmorItem> GOTHIC_CHESTPLATE = INSTANCE.addMedievalArmorItem(
      "gothic_chestplate", ArmorTypes.GOTHIC, class_8051.field_41935, new class_1793()
   );
   @Nullable
   public static final RegistrySupplier<MedievalArmorItem> GOTHIC_LEGGINGS = INSTANCE.addMedievalArmorItem(
      "gothic_leggings", ArmorTypes.GOTHIC, class_8051.field_41936, new class_1793()
   );
   @Nullable
   public static final RegistrySupplier<MedievalArmorItem> GOTHIC_BOOTS = INSTANCE.addMedievalArmorItem(
      "gothic_boots", ArmorTypes.GOTHIC, class_8051.field_41937, new class_1793()
   );
   @Nullable
   public static final RegistrySupplier<MedievalArmorItem> MAXIMILIAN_HELMET = INSTANCE.addMedievalArmorItem(
      "maximilian_helmet", ArmorTypes.MAXIMILIAN_HELMET, class_8051.field_41934, new class_1793()
   );
   @Nullable
   public static final RegistrySupplier<MedievalArmorItem> MAXIMILIAN_CHESTPLATE = INSTANCE.addMedievalArmorItem(
      "maximilian_chestplate", ArmorTypes.MAXIMILIAN, class_8051.field_41935, new class_1793()
   );
   @Nullable
   public static final RegistrySupplier<MedievalArmorItem> MAXIMILIAN_LEGGINGS = INSTANCE.addMedievalArmorItem(
      "maximilian_leggings", ArmorTypes.MAXIMILIAN, class_8051.field_41936, new class_1793()
   );
   @Nullable
   public static final RegistrySupplier<MedievalArmorItem> MAXIMILIAN_BOOTS = INSTANCE.addMedievalArmorItem(
      "maximilian_boots", ArmorTypes.MAXIMILIAN, class_8051.field_41937, new class_1793()
   );
   @Nullable
   public static final RegistrySupplier<MedievalArmorItem> CHAINMAIL_HELMET = INSTANCE.addMedievalArmorItem(
      "chainmail_helmet", ArmorTypes.CHAINMAIL, class_8051.field_41934, new class_1793()
   );
   @Nullable
   public static final RegistrySupplier<MedievalArmorItem> CHAINMAIL_CHESTPLATE = INSTANCE.addMedievalArmorItem(
      "chainmail_chestplate", ArmorTypes.CHAINMAIL, class_8051.field_41935, new class_1793()
   );
   @Nullable
   public static final RegistrySupplier<MedievalArmorItem> CHAINMAIL_LEGGINGS = INSTANCE.addMedievalArmorItem(
      "chainmail_leggings", ArmorTypes.CHAINMAIL, class_8051.field_41936, new class_1793()
   );
   @Nullable
   public static final RegistrySupplier<MedievalArmorItem> CHAINMAIL_BOOTS = INSTANCE.addMedievalArmorItem(
      "chainmail_boots", ArmorTypes.CHAINMAIL, class_8051.field_41937, new class_1793()
   );
   @Nullable
   public static final RegistrySupplier<MedievalArmorItem> KETTLEHAT = INSTANCE.addMedievalArmorItem(
      "kettlehat", ArmorTypes.KETTLEHAT, class_8051.field_41934, new class_1793()
   );
   @Nullable
   public static final RegistrySupplier<MedievalArmorItem> PLATEMAIL_CHESTPLATE = INSTANCE.addMedievalArmorItem(
      "platemail_chestplate", ArmorTypes.PLATEMAIL, class_8051.field_41935, new class_1793()
   );
   @Nullable
   public static final RegistrySupplier<MedievalArmorItem> PLATEMAIL_LEGGINGS = INSTANCE.addMedievalArmorItem(
      "platemail_leggings", ArmorTypes.PLATEMAIL, class_8051.field_41936, new class_1793()
   );
   @Nullable
   public static final RegistrySupplier<MedievalArmorItem> PLATEMAIL_BOOTS = INSTANCE.addMedievalArmorItem(
      "platemail_boots", ArmorTypes.PLATEMAIL, class_8051.field_41937, new class_1793()
   );
   @Nullable
   public static final RegistrySupplier<MedievalArmorItem> BARBUTE = INSTANCE.addMedievalArmorItem(
      "barbute", ArmorTypes.BARBUTE, class_8051.field_41934, new class_1793()
   );
   @Nullable
   public static final RegistrySupplier<MedievalArmorItem> HALFARMOR_CHESTPLATE = INSTANCE.addMedievalArmorItem(
      "halfarmor_chestplate", ArmorTypes.HALFARMOR, class_8051.field_41935, new class_1793()
   );
   @Nullable
   public static final RegistrySupplier<DyeableMedievalArmorItem> GREATHELM = INSTANCE.addDyeableMedievalArmorItem(
      "greathelm", ArmorTypes.GREATHELM, class_8051.field_41934, new class_1793(), 13882323
   );
   @Nullable
   public static final RegistrySupplier<DyeableMedievalArmorItem> CRUSADER_CHESTPLATE = INSTANCE.addDyeableMedievalArmorItem(
      "crusader_chestplate", ArmorTypes.CRUSADER, class_8051.field_41935, new class_1793(), -3227226
   );
   @Nullable
   public static final RegistrySupplier<MedievalArmorItem> CRUSADER_LEGGINGS = INSTANCE.addMedievalArmorItem(
      "crusader_leggings", ArmorTypes.CRUSADER, class_8051.field_41936, new class_1793()
   );
   @Nullable
   public static final RegistrySupplier<DyeableMedievalArmorItem> CRUSADER_BOOTS = INSTANCE.addDyeableMedievalArmorItem(
      "crusader_boots", ArmorTypes.CRUSADER, class_8051.field_41937, new class_1793(), -3227226
   );
   @Nullable
   public static final RegistrySupplier<KnightItem> CEREMONIAL_ARMET = INSTANCE.addKnightItem(
      "ceremonialarmet", ArmorTypes.CEREMONIAL_ARMET, class_8051.field_41934, new class_1793()
   );
   @Nullable
   public static final RegistrySupplier<MedievalArmorItem> CEREMONIAL_CHESTPLATE = INSTANCE.addMedievalArmorItem(
      "ceremonial_chestplate", ArmorTypes.CEREMONIAL, class_8051.field_41935, new class_1793()
   );
   @Nullable
   public static final RegistrySupplier<MedievalArmorItem> CEREMONIAL_BOOTS = INSTANCE.addMedievalArmorItem(
      "ceremonial_boots", ArmorTypes.CEREMONIAL, class_8051.field_41937, new class_1793()
   );
   @Nullable
   public static final RegistrySupplier<DyeableMedievalArmorItem> COIF = INSTANCE.addDyeableMedievalArmorItem(
      "coif", ArmorTypes.GAMBESON, class_8051.field_41934, new class_1793(), -4280691
   );
   @Nullable
   public static final RegistrySupplier<DyeableMedievalArmorItem> GAMBESON = INSTANCE.addDyeableMedievalArmorItem(
      "gambeson_chestplate", ArmorTypes.GAMBESON, class_8051.field_41935, new class_1793(), -4280691
   );
   @Nullable
   public static final RegistrySupplier<DyeableMedievalArmorItem> PANTYHOSE = INSTANCE.addDyeableMedievalArmorItem(
      "pantyhose", ArmorTypes.GAMBESON, class_8051.field_41936, new class_1793(), 2246188
   );
   @Nullable
   public static final RegistrySupplier<DyeableMedievalArmorItem> GAMBESON_BOOTS = INSTANCE.addDyeableMedievalArmorItem(
      "gambeson_boots", ArmorTypes.GAMBESON, class_8051.field_41937, new class_1793(), -4280691
   );
   @Nullable
   public static final RegistrySupplier<DyeableMedievalArmorItem> BRIGANDINE = INSTANCE.addDyeableMedievalArmorItem(
      "brigandine_chestplate", ArmorTypes.BRIGANDINE, class_8051.field_41935, new class_1793(), 10511680
   );
   @Nullable
   public static final RegistrySupplier<DyeableMedievalArmorItem> NORMAN_HELMET = INSTANCE.addDyeableMedievalArmorItem(
      "norman_helmet", ArmorTypes.NORMAN, class_8051.field_41934, new class_1793(), 15856113
   );
   @Nullable
   public static final RegistrySupplier<MedievalArmorItem> SHISHAK = INSTANCE.addMedievalArmorItem(
      "shishak", ArmorTypes.SHISHAK, class_8051.field_41934, new class_1793()
   );
   @Nullable
   public static final RegistrySupplier<MedievalArmorItem> RUSTED_BARBUTE = INSTANCE.addMedievalArmorItem(
      "rustedbarbute", ArmorTypes.RUSTED_BARBUTE, class_8051.field_41934, new class_1793()
   );
   @Nullable
   public static final RegistrySupplier<MedievalArmorItem> RUSTED_HALFARMOR_CHESTPLATE = INSTANCE.addMedievalArmorItem(
      "rustedhalfarmor_chestplate", ArmorTypes.RUSTED_HALFARMOR, class_8051.field_41935, new class_1793()
   );
   @Nullable
   public static final RegistrySupplier<MedievalArmorItem> RUSTED_GREATHELM = INSTANCE.addMedievalArmorItem(
      "rustedgreathelm", ArmorTypes.RUSTED_GREATHELM, class_8051.field_41934, new class_1793()
   );
   @Nullable
   public static final RegistrySupplier<MedievalArmorItem> RUSTED_CRUSADER_CHESTPLATE = INSTANCE.addMedievalArmorItem(
      "rustedcrusader_chestplate", ArmorTypes.RUSTED_CRUSADER, class_8051.field_41935, new class_1793()
   );
   @Nullable
   public static final RegistrySupplier<MedievalArmorItem> RUSTED_CRUSADER_BOOTS = INSTANCE.addMedievalArmorItem(
      "rustedcrusader_boots", ArmorTypes.RUSTED_CRUSADER, class_8051.field_41937, new class_1793()
   );
   @Nullable
   public static final RegistrySupplier<MedievalArmorItem> RUSTED_NORMAN_HELMET = INSTANCE.addMedievalArmorItem(
      "rustednorman_helmet", ArmorTypes.RUSTED_NORMAN, class_8051.field_41934, new class_1793()
   );
   @Nullable
   public static final RegistrySupplier<MedievalArmorItem> RUSTED_CHAINMAIL_HELMET = INSTANCE.addMedievalArmorItem(
      "rustedchainmail_helmet", ArmorTypes.RUSTED_CHAINMAIL, class_8051.field_41934, new class_1793()
   );
   @Nullable
   public static final RegistrySupplier<MedievalArmorItem> RUSTED_CHAINMAIL_CHESTPLATE = INSTANCE.addMedievalArmorItem(
      "rustedchainmail_chestplate", ArmorTypes.RUSTED_CHAINMAIL, class_8051.field_41935, new class_1793()
   );
   @Nullable
   public static final RegistrySupplier<MedievalArmorItem> RUSTED_CHAINMAIL_LEGGINGS = INSTANCE.addMedievalArmorItem(
      "rustedchainmail_leggings", ArmorTypes.RUSTED_CHAINMAIL, class_8051.field_41936, new class_1793()
   );
   @Nullable
   public static final RegistrySupplier<MedievalArmorItem> RUSTED_CHAINMAIL_BOOTS = INSTANCE.addMedievalArmorItem(
      "rustedchainmail_boots", ArmorTypes.RUSTED_CHAINMAIL, class_8051.field_41937, new class_1793()
   );
   @Nullable
   public static final RegistrySupplier<MedievalArmorItem> RUSTED_KETTLEHAT = INSTANCE.addMedievalArmorItem(
      "rustedkettlehat", ArmorTypes.RUSTED_KETTLEHAT, class_8051.field_41934, new class_1793()
   );
   @Nullable
   public static final RegistrySupplier<MedievalArmorItem> BASCINET = INSTANCE.addMedievalArmorItem(
      "bascinet", ArmorTypes.BASCINET, class_8051.field_41934, new class_1793()
   );
   @Nullable
   public static final RegistrySupplier<MedievalArmorItem> XIV_CENTURY_KNIGHT_CHESTPLATE = INSTANCE.addMedievalArmorItem(
      "xivcenturyknight_chestplate", ArmorTypes.XIV_CENTURY_KNIGHT, class_8051.field_41935, new class_1793()
   );
   @Nullable
   public static final RegistrySupplier<MedievalArmorItem> XIV_CENTURY_KNIGHT_LEGGINGS = INSTANCE.addMedievalArmorItem(
      "xivcenturyknight_leggings", ArmorTypes.XIV_CENTURY_KNIGHT, class_8051.field_41936, new class_1793()
   );
   @Nullable
   public static final RegistrySupplier<MedievalArmorItem> XIV_CENTURY_KNIGHT_BOOTS = INSTANCE.addMedievalArmorItem(
      "xivcenturyknight_boots", ArmorTypes.XIV_CENTURY_KNIGHT, class_8051.field_41937, new class_1793()
   );
   @Nullable
   public static final RegistrySupplier<MedievalArmorItem> WINGED_HUSSAR_CHESTPLATE = INSTANCE.addMedievalArmorItem(
      "wingedhussar_chestplate", ArmorTypes.WINGED_HUSSAR_CHESTPLATE, class_8051.field_41935, new class_1793()
   );
   @Nullable
   public static final RegistrySupplier<MedievalArmorItem> BURGONET = INSTANCE.addMedievalArmorItem(
      "cuirassier_helmet", ArmorTypes.CUIRASSIER, class_8051.field_41934, new class_1793()
   );
   @Nullable
   public static final RegistrySupplier<DyeableMedievalArmorItem> CUIRASSIER_CHESTPLATE = INSTANCE.addDyeableMedievalArmorItem(
      "cuirassier_chestplate", ArmorTypes.CUIRASSIER, class_8051.field_41935, new class_1793(), -5465480
   );
   @Nullable
   public static final RegistrySupplier<DyeableMedievalArmorItem> CUIRASSIER_LEGGINGS = INSTANCE.addDyeableMedievalArmorItem(
      "cuirassier_leggings", ArmorTypes.CUIRASSIER, class_8051.field_41936, new class_1793(), -5465480
   );
   @Nullable
   public static final RegistrySupplier<MedievalArmorItem> CUIRASSIER_BOOTS = INSTANCE.addMedievalArmorItem(
      "cuirassier_boots", ArmorTypes.CUIRASSIER, class_8051.field_41937, new class_1793()
   );
   @Nullable
   public static final RegistrySupplier<MedievalArmorItem> GRAND_BASCINET = INSTANCE.addMedievalArmorItem(
      "grand_bascinet", ArmorTypes.GRAND_BASCINET, class_8051.field_41934, new class_1793()
   );
   @Nullable
   public static final RegistrySupplier<MedievalArmorItem> KASTENBRUST_CHESTPLATE = INSTANCE.addMedievalArmorItem(
      "kastenbrust_chestplate", ArmorTypes.KASTENBRUST, class_8051.field_41935, new class_1793()
   );
   @Nullable
   public static final RegistrySupplier<MedievalArmorItem> KASTENBRUST_LEGGINGS = INSTANCE.addMedievalArmorItem(
      "kastenbrust_leggings", ArmorTypes.KASTENBRUST, class_8051.field_41936, new class_1793()
   );
   @Nullable
   public static final RegistrySupplier<MedievalArmorItem> KASTENBRUST_BOOTS = INSTANCE.addMedievalArmorItem(
      "kastenbrust_boots", ArmorTypes.KASTENBRUST, class_8051.field_41937, new class_1793()
   );
   @Nullable
   public static final RegistrySupplier<MedievalArmorItem> FACE_HELMET = INSTANCE.addMedievalArmorItem(
      "face_helmet", ArmorTypes.LAMELLAR, class_8051.field_41934, new class_1793()
   );
   @Nullable
   public static final RegistrySupplier<MedievalArmorItem> LAMELLAR_CHESTPLATE = INSTANCE.addMedievalArmorItem(
      "lamellar_chestplate", ArmorTypes.LAMELLAR, class_8051.field_41935, new class_1793()
   );
   @Nullable
   public static final RegistrySupplier<MedievalArmorItem> LAMELLAR_BOOTS = INSTANCE.addMedievalArmorItem(
      "lamellar_boots", ArmorTypes.LAMELLAR, class_8051.field_41937, new class_1793()
   );
   public static final RegistrySupplier<MedievalHorseArmorItem> BARDING = INSTANCE.items
      .register(
         "barding",
         () -> new MedievalHorseArmorItem(
            class_1740.field_7889, class_2960.method_60655("magistuarmory", "textures/entity/horse/armor/barding.png"), false, new class_1793().method_7889(1)
         )
      );
   public static final RegistrySupplier<MedievalHorseArmorItem> CHAINMAIL_HORSE_ARMOR = INSTANCE.items
      .register(
         "chainmail_horse_armor",
         () -> new MedievalHorseArmorItem(
            class_1740.field_7892,
            class_2960.method_60655("magistuarmory", "textures/entity/horse/armor/horse_armor_chainmail.png"),
            false,
            new class_1793().method_7889(1)
         )
      );
   public static final BiFunction<ModItemTier, class_1793, RegistrySupplier<MedievalShieldItem>> HEATER_SHIELD_SUPPLY = (material, prop) -> INSTANCE.addMedievalShieldItem(
      material.getMaterialName() + "_heatershield", "heatershield", prop, material, true, true, SHIELDS_CONFIG.get("heaterShield")
   );
   public static final BiFunction<ModItemTier, class_1793, RegistrySupplier<MedievalShieldItem>> TARGET_SUPPLY = (material, prop) -> INSTANCE.addMedievalShieldItem(
      material.getMaterialName() + "_target", "target", prop, material, false, true, SHIELDS_CONFIG.get("target")
   );
   public static final BiFunction<ModItemTier, class_1793, RegistrySupplier<MedievalShieldItem>> BUCKLER_SUPPLY = (material, prop) -> INSTANCE.addMedievalShieldItem(
      material.getMaterialName() + "_buckler", "buckler", prop, material, false, true, SHIELDS_CONFIG.get("buckler")
   );
   public static final BiFunction<ModItemTier, class_1793, RegistrySupplier<MedievalShieldItem>> RONDACHE_SUPPLY = (material, prop) -> INSTANCE.addMedievalShieldItem(
      material.getMaterialName() + "_rondache", "rondache", prop, material, false, true, SHIELDS_CONFIG.get("rondache")
   );
   public static final BiFunction<ModItemTier, class_1793, RegistrySupplier<MedievalShieldItem>> TARTSCHE_SUPPLY = (material, prop) -> INSTANCE.addMedievalShieldItem(
      material.getMaterialName() + "_tartsche", "tartsche", prop, material, true, true, SHIELDS_CONFIG.get("tartsche")
   );
   public static final BiFunction<ModItemTier, class_1793, RegistrySupplier<MedievalShieldItem>> ELLIPTICAL_SHIELD_SUPPLY = (material, prop) -> INSTANCE.addMedievalShieldItem(
      material.getMaterialName() + "_ellipticalshield", "ellipticalshield", prop, material, true, true, SHIELDS_CONFIG.get("ellipticalShield")
   );
   public static final BiFunction<ModItemTier, class_1793, RegistrySupplier<MedievalShieldItem>> ROUND_SHIELD_SUPPLY = (material, prop) -> INSTANCE.addMedievalShieldItem(
      material.getMaterialName() + "_roundshield", "roundshield", prop, material, true, true, SHIELDS_CONFIG.get("roundShield")
   );
   public static final BiFunction<ModItemTier, class_1793, RegistrySupplier<MedievalShieldItem>> PAVISE_SUPPLY = (material, prop) -> INSTANCE.addPaviseItem(
      material.getMaterialName() + "_pavese", "pavese", prop, material, true, true, SHIELDS_CONFIG.get("pavise"), ModBlocks.getPaviseByMaterialName(material)
   );
   public static final BiFunction<ModItemTier, class_1793, RegistrySupplier<MedievalShieldItem>> KITE_SHIELD_SUPPLY = (material, prop) -> INSTANCE.addMedievalShieldItem(
      material.getMaterialName() + "_kiteshield", "kiteshield", prop, material, true, true, SHIELDS_CONFIG.get("kiteShield")
   );
   public static final BiFunction<ModItemTier, class_1793, RegistrySupplier<MedievalWeaponItem>> STILETTO_SUPPLY = (material, prop) -> INSTANCE.addMedievalWeaponItem(
      material.getMaterialName() + "_stylet", prop, material, WeaponTypes.STILETTO
   );
   public static final BiFunction<ModItemTier, class_1793, RegistrySupplier<MedievalWeaponItem>> SHORT_SWORD_SUPPLY = (material, prop) -> INSTANCE.addMedievalWeaponItem(
      material.getMaterialName() + "_shortsword", prop, material, WeaponTypes.SHORT_SWORD
   );
   public static final BiFunction<ModItemTier, class_1793, RegistrySupplier<MedievalWeaponItem>> KATZBALGER_SUPPLY = (material, prop) -> INSTANCE.addMedievalWeaponItem(
      material.getMaterialName() + "_katzbalger", prop, material, WeaponTypes.KATZBALGER
   );
   public static final BiFunction<ModItemTier, class_1793, RegistrySupplier<MedievalWeaponItem>> PIKE_SUPPLY = (material, prop) -> INSTANCE.addMedievalWeaponItem(
      material.getMaterialName() + "_pike", prop, material, WeaponTypes.PIKE
   );
   public static final BiFunction<ModItemTier, class_1793, RegistrySupplier<MedievalWeaponItem>> RANSEUR_SUPPLY = (material, prop) -> INSTANCE.addMedievalWeaponItem(
      material.getMaterialName() + "_ranseur", prop, material, WeaponTypes.RANSEUR
   );
   public static final BiFunction<ModItemTier, class_1793, RegistrySupplier<MedievalWeaponItem>> AHLSPIESS_SUPPLY = (material, prop) -> INSTANCE.addMedievalWeaponItem(
      material.getMaterialName() + "_ahlspiess", prop, material, WeaponTypes.AHLSPIESS
   );
   public static final BiFunction<ModItemTier, class_1793, RegistrySupplier<MedievalWeaponItem>> GIANT_LANCE_SUPPLY = (material, prop) -> INSTANCE.addLanceItem(
      material.getMaterialName() + "_chivalrylance", prop, material, WeaponTypes.GIANT_LANCE
   );
   public static final BiFunction<ModItemTier, class_1793, RegistrySupplier<MedievalWeaponItem>> BASTARD_SWORD_SUPPLY = (material, prop) -> INSTANCE.addMedievalWeaponItem(
      material.getMaterialName() + "_bastardsword", prop, material, WeaponTypes.BASTARD_SWORD
   );
   public static final BiFunction<ModItemTier, class_1793, RegistrySupplier<MedievalWeaponItem>> ESTOC_SUPPLY = (material, prop) -> INSTANCE.addMedievalWeaponItem(
      material.getMaterialName() + "_estoc", prop, material, WeaponTypes.ESTOC
   );
   public static final BiFunction<ModItemTier, class_1793, RegistrySupplier<MedievalWeaponItem>> CLAYMORE_SUPPLY = (material, prop) -> INSTANCE.addMedievalWeaponItem(
      material.getMaterialName() + "_claymore", prop, material, WeaponTypes.CLAYMORE
   );
   public static final BiFunction<ModItemTier, class_1793, RegistrySupplier<MedievalWeaponItem>> ZWEIHANDER_SUPPLY = (material, prop) -> INSTANCE.addMedievalWeaponItem(
      material.getMaterialName() + "_zweihander", prop, material, WeaponTypes.ZWEIHANDER
   );
   public static final BiFunction<ModItemTier, class_1793, RegistrySupplier<MedievalWeaponItem>> FlAME_BLADED_SWORD_SUPPLY = (material, prop) -> INSTANCE.addMedievalWeaponItem(
      material.getMaterialName() + "_flamebladedsword", prop, material, WeaponTypes.FLAME_BLADED_SWORD
   );
   public static final BiFunction<ModItemTier, class_1793, RegistrySupplier<MedievalWeaponItem>> LOCHABER_AXE_SUPPLY = (material, prop) -> INSTANCE.addMedievalWeaponItem(
      material.getMaterialName() + "_lochaberaxe", prop, material, WeaponTypes.LOCHABER_AXE
   );
   public static final BiFunction<ModItemTier, class_1793, RegistrySupplier<MedievalWeaponItem>> CONCAVE_EDGED_HALBERD_SUPPLY = (material, prop) -> INSTANCE.addMedievalWeaponItem(
      material.getMaterialName() + "_concavehalberd", prop, material, WeaponTypes.CONCAVE_EDGED_HALBERD
   );
   public static final BiFunction<ModItemTier, class_1793, RegistrySupplier<MedievalWeaponItem>> HEAVY_MACE_SUPPLY = (material, prop) -> INSTANCE.addMedievalWeaponItem(
      material.getMaterialName() + "_heavymace", prop, material, WeaponTypes.HEAVY_MACE
   );
   public static final BiFunction<ModItemTier, class_1793, RegistrySupplier<MedievalWeaponItem>> HEAVY_WAR_HAMMER_SUPPLY = (material, prop) -> INSTANCE.addMedievalWeaponItem(
      material.getMaterialName() + "_heavywarhammer", prop, material, WeaponTypes.HEAVY_WAR_HAMMER
   );
   public static final BiFunction<ModItemTier, class_1793, RegistrySupplier<MedievalWeaponItem>> LUCERNE_HAMMER_SUPPLY = (material, prop) -> INSTANCE.addMedievalWeaponItem(
      material.getMaterialName() + "_lucernhammer", prop, material, WeaponTypes.LUCERNE_HAMMER
   );
   public static final BiFunction<ModItemTier, class_1793, RegistrySupplier<MedievalWeaponItem>> MORNINGSTAR_SUPPLY = (material, prop) -> INSTANCE.addMedievalWeaponItem(
      material.getMaterialName() + "_morgenstern", prop, material, WeaponTypes.MORNINGSTAR
   );
   public static final BiFunction<ModItemTier, class_1793, RegistrySupplier<MedievalWeaponItem>> FLAIL_SUPPLY = (material, prop) -> INSTANCE.addMedievalWeaponItem(
      material.getMaterialName() + "_chainmorgenstern", prop, material, WeaponTypes.FLAIL
   );
   public static final BiFunction<ModItemTier, class_1793, RegistrySupplier<MedievalWeaponItem>> GUISARME_SUPPLY = (material, prop) -> INSTANCE.addMedievalWeaponItem(
      material.getMaterialName() + "_guisarme", prop, material, WeaponTypes.GUISARME
   );
   public static final WeaponsSupply STILETTOS = INSTANCE.addWeaponsSupply(STILETTO_SUPPLY);
   public static final WeaponsSupply SHORT_SWORDS = INSTANCE.addWeaponsSupply(SHORT_SWORD_SUPPLY);
   public static final WeaponsSupply KATZBALGERS = INSTANCE.addWeaponsSupply(KATZBALGER_SUPPLY);
   public static final WeaponsSupply PIKES = INSTANCE.addWeaponsSupply(PIKE_SUPPLY);
   public static final WeaponsSupply RANSEURS = INSTANCE.addWeaponsSupply(RANSEUR_SUPPLY);
   public static final WeaponsSupply AHLSPIESSES = INSTANCE.addWeaponsSupply(AHLSPIESS_SUPPLY);
   public static final WeaponsSupply GIANT_LANCES = INSTANCE.addWeaponsSupply(GIANT_LANCE_SUPPLY);
   public static final WeaponsSupply BASTARD_SWORDS = INSTANCE.addWeaponsSupply(BASTARD_SWORD_SUPPLY);
   public static final WeaponsSupply ESTOCS = INSTANCE.addWeaponsSupply(ESTOC_SUPPLY);
   public static final WeaponsSupply CLAYMORS = INSTANCE.addWeaponsSupply(CLAYMORE_SUPPLY);
   public static final WeaponsSupply ZWEIHANDERS = INSTANCE.addWeaponsSupply(ZWEIHANDER_SUPPLY);
   public static final WeaponsSupply FLAME_BLADED_SWORDS = INSTANCE.addWeaponsSupply(FlAME_BLADED_SWORD_SUPPLY);
   public static final WeaponsSupply LOCHABER_AXES = INSTANCE.addWeaponsSupply(LOCHABER_AXE_SUPPLY);
   public static final WeaponsSupply CONCAVE_EDGED_HALBERDS = INSTANCE.addWeaponsSupply(CONCAVE_EDGED_HALBERD_SUPPLY);
   public static final WeaponsSupply HEAVY_MACES = INSTANCE.addWeaponsSupply(HEAVY_MACE_SUPPLY);
   public static final WeaponsSupply HEAVY_WAR_HAMMERS = INSTANCE.addWeaponsSupply(HEAVY_WAR_HAMMER_SUPPLY);
   public static final WeaponsSupply LUCERNE_HAMMERS = INSTANCE.addWeaponsSupply(LUCERNE_HAMMER_SUPPLY);
   public static final WeaponsSupply MORNINGSTARS = INSTANCE.addWeaponsSupply(MORNINGSTAR_SUPPLY);
   public static final WeaponsSupply FLAILS = INSTANCE.addWeaponsSupply(FLAIL_SUPPLY);
   public static final WeaponsSupply GUISARMES = INSTANCE.addWeaponsSupply(GUISARME_SUPPLY);
   @Nullable
   public static final RegistrySupplier<MedievalWeaponItem> BLACKSMITH_HAMMER = INSTANCE.addMedievalWeaponItem(
      "blacksmith_hammer", new class_1793(), ModItemTier.STEEL, WeaponTypes.BLACKSMITH_HAMMER
   );
   @Nullable
   public static final RegistrySupplier<MedievalWeaponItem> BARBED_CLUB = INSTANCE.addMedievalWeaponItem(
      "barbedclub", new class_1793(), ModItemTier.IRON, WeaponTypes.BARBED_CLUB
   );
   @Nullable
   public static final RegistrySupplier<MedievalWeaponItem> PITCHFORK = INSTANCE.addMedievalWeaponItem(
      "pitchfork", new class_1793(), ModItemTier.IRON, WeaponTypes.PITCHFORK
   );
   @Nullable
   public static final RegistrySupplier<MedievalWeaponItem> NOBLE_SWORD = INSTANCE.addMedievalWeaponItem(
      "noble_sword", new class_1793(), ModItemTier.IRON, WeaponTypes.NOBLE_SWORD
   );
   @Nullable
   public static final RegistrySupplier<MedievalWeaponItem> RUSTED_BASTARD_SWORD = INSTANCE.addMedievalWeaponItem(
      "rusted_bastardsword", new class_1793(), ModItemTier.IRON, WeaponTypes.RUSTED_BASTARD_SWORD
   );
   @Nullable
   public static final RegistrySupplier<MedievalWeaponItem> RUSTED_HEAVY_MACE = INSTANCE.addMedievalWeaponItem(
      "rusted_heavymace", new class_1793(), ModItemTier.IRON, WeaponTypes.RUSTED_HEAVY_MACE
   );
   @Nullable
   public static final RegistrySupplier<MedievalWeaponItem> CLUB = INSTANCE.addMedievalWeaponItem("club", new class_1793(), ModItemTier.WOOD, WeaponTypes.CLUB);
   @Nullable
   public static final RegistrySupplier<MedievalWeaponItem> MESSER_SWORD = INSTANCE.addMedievalWeaponItem(
      "messer_sword", new class_1793(), ModItemTier.IRON, WeaponTypes.MESSER_SWORD
   );
   @Nullable
   public static final RegistrySupplier<class_1792> LONGBOW = INSTANCE.addMedievalBowItem("longbow", WeaponTypes.LONGBOW);
   @Nullable
   public static final RegistrySupplier<class_1792> HEAVY_CROSSBOW = INSTANCE.addMedievalCrossbowItem("heavy_crossbow", WeaponTypes.HEAVY_CROSSBOW);
   public static final ShieldsSupply HEATER_SHIELDS = INSTANCE.addShieldsSupply(HEATER_SHIELD_SUPPLY, "heatershield");
   public static final ShieldsSupply TARGETS = INSTANCE.addShieldsSupply(TARGET_SUPPLY, "target");
   public static final ShieldsSupply BUCKLERS = INSTANCE.addShieldsSupply(BUCKLER_SUPPLY, "buckler");
   public static final ShieldsSupply RONDACHES = INSTANCE.addShieldsSupply(RONDACHE_SUPPLY, "rondache");
   public static final ShieldsSupply TARTSCHES = INSTANCE.addShieldsSupply(TARTSCHE_SUPPLY, "tartsche");
   public static final ShieldsSupply ELLIPTICAL_SHIELDS = INSTANCE.addShieldsSupply(ELLIPTICAL_SHIELD_SUPPLY, "ellipticalshield");
   public static final ShieldsSupply ROUND_SHIELDS = INSTANCE.addShieldsSupply(ROUND_SHIELD_SUPPLY, "roundshield");
   public static final ShieldsSupply PAVISES = INSTANCE.addShieldsSupply(PAVISE_SUPPLY, "pavese");
   public static final ShieldsSupply KITE_SHIELDS = INSTANCE.addShieldsSupply(KITE_SHIELD_SUPPLY, "kiteshield");
   public static final RegistrySupplier<MedievalShieldItem> CORRUPTED_ROUND_SHIELD = INSTANCE.addMedievalShieldItem(
      "corruptedroundshield", "corruptedroundshield", new class_1793(), ModItemTier.WOOD, false, true, SHIELDS_CONFIG.get("corruptedRoundShield")
   );
   public static final RegistrySupplier<class_1792> APOSTOLIC_CROSS_PATTERN = INSTANCE.addIngredientItem(
      "apostolic_cross_pattern", () -> new class_1745(ModBannerPatternTags.APOSTOLIC_CROSS_PATTERN, new class_1793().method_7889(1))
   );
   public static final RegistrySupplier<class_1792> BOWL_PATTERN = INSTANCE.addIngredientItem(
      "bowl_pattern", () -> new class_1745(ModBannerPatternTags.BOWL_PATTERN, new class_1793().method_7889(1))
   );
   public static final RegistrySupplier<class_1792> BULL_PATTERN = INSTANCE.addIngredientItem(
      "bull_pattern", () -> new class_1745(ModBannerPatternTags.BULL_PATTERN, new class_1793().method_7889(1))
   );
   public static final RegistrySupplier<class_1792> CHESS_PATTERN = INSTANCE.addIngredientItem(
      "chess_pattern", () -> new class_1745(ModBannerPatternTags.CHESS_PATTERN, new class_1793().method_7889(1))
   );
   public static final RegistrySupplier<class_1792> CRUSADER_CROSS_PATTERN = INSTANCE.addIngredientItem(
      "crusader_cross_pattern", () -> new class_1745(ModBannerPatternTags.CRUSADER_CROSS_PATTERN, new class_1793().method_7889(1))
   );
   public static final RegistrySupplier<class_1792> DRAGON_PATTERN = INSTANCE.addIngredientItem(
      "dragon_pattern", () -> new class_1745(ModBannerPatternTags.DRAGON_PATTERN, new class_1793().method_7889(1))
   );
   public static final RegistrySupplier<class_1792> EAGLE_PATTERN = INSTANCE.addIngredientItem(
      "eagle_pattern", () -> new class_1745(ModBannerPatternTags.EAGLE_PATTERN, new class_1793().method_7889(1))
   );
   public static final RegistrySupplier<class_1792> HORSE_PATTERN = INSTANCE.addIngredientItem(
      "horse_pattern", () -> new class_1745(ModBannerPatternTags.HORSE_PATTERN, new class_1793().method_7889(1))
   );
   public static final RegistrySupplier<class_1792> LILY_PATTERN = INSTANCE.addIngredientItem(
      "lily_pattern", () -> new class_1745(ModBannerPatternTags.LILY_PATTERN, new class_1793().method_7889(1))
   );
   public static final RegistrySupplier<class_1792> LION1_PATTERN = INSTANCE.addIngredientItem(
      "lion1_pattern", () -> new class_1745(ModBannerPatternTags.LION1_PATTERN, new class_1793().method_7889(1))
   );
   public static final RegistrySupplier<class_1792> LION2_PATTERN = INSTANCE.addIngredientItem(
      "lion2_pattern", () -> new class_1745(ModBannerPatternTags.LION2_PATTERN, new class_1793().method_7889(1))
   );
   public static final RegistrySupplier<class_1792> ORTHODOX_CROSS_PATTERN = INSTANCE.addIngredientItem(
      "orthodox_cross_pattern", () -> new class_1745(ModBannerPatternTags.ORTHODOX_CROSS_PATTERN, new class_1793().method_7889(1))
   );
   public static final RegistrySupplier<class_1792> SNAKE_PATTERN = INSTANCE.addIngredientItem(
      "snake_pattern", () -> new class_1745(ModBannerPatternTags.SNAKE_PATTERN, new class_1793().method_7889(1))
   );
   public static final RegistrySupplier<class_1792> SUN_PATTERN = INSTANCE.addIngredientItem(
      "sun_pattern", () -> new class_1745(ModBannerPatternTags.SUN_PATTERN, new class_1793().method_7889(1))
   );
   public static final RegistrySupplier<class_1792> SWORDS_PATTERN = INSTANCE.addIngredientItem(
      "swords_pattern", () -> new class_1745(ModBannerPatternTags.SWORDS_PATTERN, new class_1793().method_7889(1))
   );
   public static final RegistrySupplier<class_1792> TOWER_PATTERN = INSTANCE.addIngredientItem(
      "tower_pattern", () -> new class_1745(ModBannerPatternTags.TOWER_PATTERN, new class_1793().method_7889(1))
   );
   public static final RegistrySupplier<class_1792> TREE_PATTERN = INSTANCE.addIngredientItem(
      "tree_pattern", () -> new class_1745(ModBannerPatternTags.TREE_PATTERN, new class_1793().method_7889(1))
   );
   public static final RegistrySupplier<class_1792> TWOHEADED_EAGLE_PATTERN = INSTANCE.addIngredientItem(
      "two_headed_eagle_pattern", () -> new class_1745(ModBannerPatternTags.TWOHEADED_EAGLE_PATTERN, new class_1793().method_7889(1))
   );
   public static final RegistrySupplier<class_1792> STEEL_INGOT = INSTANCE.addIngredientItem("steel_ingot", () -> new class_1792(new class_1793()));
   public static final RegistrySupplier<class_1792> STEEL_NUGGET = INSTANCE.addIngredientItem("steel_nugget", () -> new class_1792(new class_1793()));
   public static final RegistrySupplier<class_1792> STEEL_RING = INSTANCE.addIngredientItem("steel_ring", () -> new class_1792(new class_1793()));
   public static final RegistrySupplier<class_1792> STEEL_CHAINMAIL = INSTANCE.addIngredientItem("steel_chainmail", () -> new class_1792(new class_1793()));
   public static final RegistrySupplier<class_1792> STEEL_PLATE = INSTANCE.addIngredientItem("steel_plate", () -> new class_1792(new class_1793()));
   public static final RegistrySupplier<class_1792> LEATHER_STRIP = INSTANCE.addIngredientItem("leather_strip", () -> new class_1792(new class_1793()));
   public static final RegistrySupplier<class_1792> HILT = INSTANCE.addIngredientItem("hilt", () -> new class_1792(new class_1793()));
   public static final RegistrySupplier<class_1792> POLE = INSTANCE.addIngredientItem("pole", () -> new class_1792(new class_1793()));
   public static final RegistrySupplier<class_1792> STEEL_CHAIN = INSTANCE.addIngredientItem("steel_chain", () -> new class_1792(new class_1793()));
   public static final RegistrySupplier<class_1792> WOOLEN_FABRIC = INSTANCE.addIngredientItem("woolen_fabric", () -> new class_1792(new class_1793()));
   public static final RegistrySupplier<class_1792> SMALL_STEEL_PLATE = INSTANCE.addIngredientItem("small_steel_plate", () -> new class_1792(new class_1793()));
   public static final RegistrySupplier<class_1792> LAMELLAR_ROWS = INSTANCE.addIngredientItem("lamellar_rows", () -> new class_1792(new class_1793()));
   @Nullable
   public static final RegistrySupplier<class_1792> DARKENING_TEMPLATE = INSTANCE.addIngredientItem(
      "darkening_template",
      () -> new class_8052(
         class_2561.method_43471("magistuarmory.darkening_template.applies_to"),
         class_2561.method_43471("magistuarmory.darkening_template.ingredients"),
         class_2561.method_43471("magistuarmory.darkening_template.upgrade_description"),
         class_2561.method_43471("magistuarmory.darkening_template.base_slot_description"),
         class_2561.method_43471("magistuarmory.darkening_template.additions_slot_description"),
         List.of(),
         List.of(),
         new class_7696[0]
      )
   );
   @Nullable
   public static final RegistrySupplier<class_1792> GILDING_TEMPLATE = INSTANCE.addIngredientItem(
      "gilding_template",
      () -> new class_8052(
         class_2561.method_43471("magistuarmory.gilding_template.applies_to"),
         class_2561.method_43471("magistuarmory.gilding_template.ingredients"),
         class_2561.method_43471("magistuarmory.gilding_template.upgrade_description"),
         class_2561.method_43471("magistuarmory.gilding_template.base_slot_description"),
         class_2561.method_43471("magistuarmory.gilding_template.additions_slot_description"),
         List.of(),
         List.of(),
         new class_7696[0]
      )
   );
   public static final RegistrySupplier<DyeableArmorDecorationItem> TORSE_AND_MANTLE_DECORATION = INSTANCE.addDyeableArmorDecorationItem(
      "torse_and_mantle_decoration",
      () -> new DyeableArmorDecorationItem(class_2960.method_60655("magistuarmory", "torse_and_mantle"), new class_1793(), class_8051.field_41934)
   );
   public static final RegistrySupplier<ArmorDecorationItem> GAZELLE_HORNS_DECORATION = INSTANCE.addArmorDecorationItem(
      "gazelle_horns_decoration",
      () -> new ArmorDecorationItem(class_2960.method_60655("magistuarmory", "gazelle_horns"), new class_1793(), class_8051.field_41934)
   );
   public static final RegistrySupplier<ArmorDecorationItem> DUCK_DECORATION = INSTANCE.addArmorDecorationItem(
      "duck_decoration", () -> new ArmorDecorationItem(class_2960.method_60655("magistuarmory", "duck"), new class_1793(), class_8051.field_41934)
   );
   public static final RegistrySupplier<DyeableArmorDecorationItem> SPIKE_DECORATION = INSTANCE.addDyeableArmorDecorationItem(
      "spike_decoration", () -> new DyeableArmorDecorationItem(class_2960.method_60655("magistuarmory", "spike"), new class_1793(), class_8051.field_41934)
   );
   public static final RegistrySupplier<DyeableArmorDecorationItem> HORSE_DECORATION = INSTANCE.addDyeableArmorDecorationItem(
      "horse_decoration",
      () -> new DyeableArmorDecorationItem(
         class_2960.method_60655("magistuarmory", "horse"), new class_1793(), class_8051.field_41934, class_3620.field_15981.field_16011
      )
   );
   public static final RegistrySupplier<DyeableWearableArmorDecorationItem> CROWN_DECORATION = INSTANCE.addDyeableWearableArmorDecorationItem(
      "crown_decoration", ArmorTypes.CROWN, class_8051.field_41934, new class_1793(), class_3620.field_16026.field_16011
   );
   public static final RegistrySupplier<DyeableWearableArmorDecorationItem> FLOWERCROWN_DECORATION = INSTANCE.addDyeableWearableArmorDecorationItem(
      "flowercrown_decoration", ArmorTypes.FLOWERCROWN, class_8051.field_41934, new class_1793(), class_1767.field_7952.method_7794().field_16011
   );
   public static final RegistrySupplier<DyeableArmorDecorationItem> UNICORN_DECORATION = INSTANCE.addDyeableArmorDecorationItem(
      "unicorn_decoration", () -> new DyeableArmorDecorationItem(class_2960.method_60655("magistuarmory", "unicorn"), new class_1793(), class_8051.field_41934)
   );
   public static final RegistrySupplier<ArmorDecorationItem> BULLHORNS_DECORATION = INSTANCE.addArmorDecorationItem(
      "bullhorns_decoration", () -> new ArmorDecorationItem(class_2960.method_60655("magistuarmory", "bullhorns"), new class_1793(), class_8051.field_41934)
   );
   public static final RegistrySupplier<DyeableArmorDecorationItem> DRAGON_DECORATION = INSTANCE.addDyeableArmorDecorationItem(
      "dragon_decoration",
      () -> new DyeableArmorDecorationItem(
         class_2960.method_60655("magistuarmory", "dragon"), new class_1793(), class_8051.field_41934, class_3620.field_15978.field_16011
      )
   );
   public static final RegistrySupplier<WearableArmorDecorationItem> MINICROWN_DECORATION = INSTANCE.addWearableArmorDecorationItem(
      "minicrown_decoration", ArmorTypes.MINICROWN, class_8051.field_41934, new class_1793()
   );
   public static final RegistrySupplier<DyeableArmorDecorationItem> TORSE_DECORATION = INSTANCE.addDyeableArmorDecorationItem(
      "torse_decoration", () -> new DyeableArmorDecorationItem(class_2960.method_60655("magistuarmory", "torse"), new class_1793(), class_8051.field_41934)
   );
   public static final RegistrySupplier<DyeableArmorDecorationItem> TWO_PLUMES_DECORATION = INSTANCE.addDyeableArmorDecorationItem(
      "two_plumes_decoration",
      () -> new DyeableArmorDecorationItem(class_2960.method_60655("magistuarmory", "two_plumes"), new class_1793(), class_8051.field_41934)
   );
   public static final RegistrySupplier<ArmorDecorationItem> ANTLERS_DECORATION = INSTANCE.addArmorDecorationItem(
      "antlers_decoration", () -> new ArmorDecorationItem(class_2960.method_60655("magistuarmory", "antlers"), new class_1793(), class_8051.field_41934)
   );
   public static final RegistrySupplier<DyeableArmorDecorationItem> BIG_PLUME_DECORATION = INSTANCE.addDyeableArmorDecorationItem(
      "big_plume_decoration",
      () -> new DyeableArmorDecorationItem(class_2960.method_60655("magistuarmory", "big_plume"), new class_1793(), class_8051.field_41934, -10092544)
   );
   public static final RegistrySupplier<DyeableArmorDecorationItem> PLUME_LEFT_DECORATION = INSTANCE.addDyeableArmorDecorationItem(
      "plume_left_decoration",
      () -> new DyeableArmorDecorationItem(class_2960.method_60655("magistuarmory", "plume_left"), new class_1793(), class_8051.field_41934)
   );
   public static final RegistrySupplier<DyeableArmorDecorationItem> PLUME_MIDDLE_DECORATION = INSTANCE.addDyeableArmorDecorationItem(
      "plume_middle_decoration",
      () -> new DyeableArmorDecorationItem(class_2960.method_60655("magistuarmory", "plume_middle"), new class_1793(), class_8051.field_41934)
   );
   public static final RegistrySupplier<DyeableArmorDecorationItem> PLUME_RIGHT_DECORATION = INSTANCE.addDyeableArmorDecorationItem(
      "plume_right_decoration",
      () -> new DyeableArmorDecorationItem(class_2960.method_60655("magistuarmory", "plume_right"), new class_1793(), class_8051.field_41934)
   );
   public static final RegistrySupplier<DyeableArmorDecorationItem> BEAR_DECORATION = INSTANCE.addDyeableArmorDecorationItem(
      "bear_decoration",
      () -> new DyeableArmorDecorationItem(
         class_2960.method_60655("magistuarmory", "bear"), new class_1793(), class_8051.field_41934, class_3620.field_15977.field_16011
      )
   );
   public static final RegistrySupplier<DyeableArmorDecorationItem> LILY_DECORATION = INSTANCE.addDyeableArmorDecorationItem(
      "lily_decoration",
      () -> new DyeableArmorDecorationItem(
         class_2960.method_60655("magistuarmory", "lily"), new class_1793(), class_8051.field_41934, class_3620.field_15994.field_16011
      )
   );
   public static final RegistrySupplier<DyeableArmorDecorationItem> LION_DECORATION = INSTANCE.addDyeableArmorDecorationItem(
      "lion_decoration",
      () -> new DyeableArmorDecorationItem(class_2960.method_60655("magistuarmory", "lion"), new class_1793(), class_8051.field_41934, 16499255)
   );
   public static final RegistrySupplier<ArmorDecorationItem> DEMON_HORNS_DECORATION = INSTANCE.addArmorDecorationItem(
      "demon_horns_decoration",
      () -> new ArmorDecorationItem(class_2960.method_60655("magistuarmory", "demon_horns"), new class_1793(), class_8051.field_41934)
   );
   public static final RegistrySupplier<DyeableArmorDecorationItem> FEATHERS_DECORATION = INSTANCE.addDyeableArmorDecorationItem(
      "feathers_decoration",
      () -> new DyeableArmorDecorationItem(class_2960.method_60655("magistuarmory", "feathers"), new class_1793(), class_8051.field_41934)
   );
   public static final RegistrySupplier<DyeableArmorDecorationItem> VIKING_HORNS_DECORATION = INSTANCE.addDyeableArmorDecorationItem(
      "viking_horns_decoration",
      () -> new DyeableArmorDecorationItem(class_2960.method_60655("magistuarmory", "viking_horns"), new class_1793(), class_8051.field_41934)
   );
   public static final RegistrySupplier<DyeableArmorDecorationItem> GRIFFIN_DECORATION = INSTANCE.addDyeableArmorDecorationItem(
      "griffin_decoration",
      () -> new DyeableArmorDecorationItem(class_2960.method_60655("magistuarmory", "griffin"), new class_1793(), class_8051.field_41934, 16499255)
   );
   public static final RegistrySupplier<DyeableArmorDecorationItem> HOOD_DECORATION = INSTANCE.addDyeableArmorDecorationItem(
      "hood_decoration", () -> new DyeableArmorDecorationItem(class_2960.method_60655("magistuarmory", "hood"), new class_1793(), class_8051.field_41935)
   );
   public static final RegistrySupplier<DyeableArmorDecorationItem> ECRANCHE_DECORATION = INSTANCE.addDyeableArmorDecorationItem(
      "ecranche_decoration",
      () -> new DyeableArmorDecorationItem(class_2960.method_60655("magistuarmory", "ecranche"), new class_1793(), class_8051.field_41935, 14671839)
   );
   public static final RegistrySupplier<ArmorDecorationItem> RONDEL_DECORATION = INSTANCE.addArmorDecorationItem(
      "rondel_decoration", () -> new ArmorDecorationItem(class_2960.method_60655("magistuarmory", "rondel"), new class_1793(), class_8051.field_41935)
   );
   public static final RegistrySupplier<ArmorDecorationItem> CAT_EARS_DECORATION = INSTANCE.addArmorDecorationItem(
      "cat_ears_decoration", () -> new ArmorDecorationItem(class_2960.method_60655("magistuarmory", "cat_ears"), new class_1793(), class_8051.field_41934)
   );
   public static final RegistrySupplier<MedievalBagItem> MEDIEVAL_BAG = INSTANCE.items.register("medieval_bag", MedievalBagItem::new);

   public ModItems() {
      super("magistuarmory");
      if (Platform.isFabric()) {
         this.items.register("tin_ingot", () -> new class_1792(new class_1793()));
         this.items.register("silver_ingot", () -> new class_1792(new class_1793()));
         this.items.register("bronze_ingot", () -> new class_1792(new class_1793()));
      }
   }
}
