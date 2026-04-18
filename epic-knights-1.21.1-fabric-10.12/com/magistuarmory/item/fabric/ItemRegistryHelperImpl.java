package com.magistuarmory.item.fabric;

import com.magistuarmory.block.PaviseBlock;
import com.magistuarmory.item.LanceItem;
import com.magistuarmory.item.MedievalShieldItem;
import com.magistuarmory.item.MedievalWeaponItem;
import com.magistuarmory.item.ModItemTier;
import com.magistuarmory.item.PaviseItem;
import com.magistuarmory.item.ShieldType;
import com.magistuarmory.item.WeaponType;
import com.magistuarmory.item.armor.ArmorType;
import com.magistuarmory.item.armor.DyeableMedievalArmorItem;
import com.magistuarmory.item.armor.DyeableWearableArmorDecorationItem;
import com.magistuarmory.item.armor.JoustingItem;
import com.magistuarmory.item.armor.KnightItem;
import com.magistuarmory.item.armor.MedievalArmorItem;
import com.magistuarmory.item.armor.WearableArmorDecorationItem;
import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import java.util.function.Supplier;
import net.minecraft.class_1792;
import net.minecraft.class_2960;
import net.minecraft.class_1738.class_8051;
import net.minecraft.class_1792.class_1793;

public class ItemRegistryHelperImpl {
   public static RegistrySupplier<MedievalArmorItem> registerKnightItem(
      DeferredRegister<class_1792> items, String id, ArmorType material, class_8051 type, class_1793 properties
   ) {
      return items.register(id, () -> new KnightItem(material, type, properties));
   }

   public static RegistrySupplier<MedievalArmorItem> registerJoustingItem(
      DeferredRegister<class_1792> items, String id, ArmorType material, class_8051 type, class_1793 properties
   ) {
      return items.register(id, () -> new JoustingItem(material, type, properties));
   }

   public static RegistrySupplier<MedievalArmorItem> registerMedievalArmorItem(
      DeferredRegister<class_1792> items, String id, ArmorType material, class_8051 type, class_1793 properties
   ) {
      return items.register(id, () -> new MedievalArmorItem(material, type, properties));
   }

   public static RegistrySupplier<MedievalArmorItem> registerDyeableMedievalArmorItem(
      DeferredRegister<class_1792> items, String id, ArmorType material, class_8051 type, class_1793 properties, int defaultcolor
   ) {
      return items.register(id, () -> new DyeableMedievalArmorItem(material, type, properties, defaultcolor));
   }

   public static RegistrySupplier<WearableArmorDecorationItem> registerWearableArmorDecorationItem(
      DeferredRegister<class_1792> items, String id, ArmorType material, class_8051 type, class_1793 properties
   ) {
      return items.register(id, () -> new WearableArmorDecorationItem(material, type, properties));
   }

   public static RegistrySupplier<DyeableWearableArmorDecorationItem> registerDyeableWearableArmorDecorationItem(
      DeferredRegister<class_1792> items, String id, ArmorType material, class_8051 type, class_1793 properties, int defaultcolor
   ) {
      return items.register(id, () -> new DyeableWearableArmorDecorationItem(material, type, properties, defaultcolor));
   }

   public static RegistrySupplier<MedievalWeaponItem> registerMedievalWeaponItem(
      DeferredRegister<class_1792> items, String id, class_1793 properties, ModItemTier material, WeaponType type
   ) {
      return items.register(id, () -> new MedievalWeaponItem(properties, material, type));
   }

   public static RegistrySupplier<MedievalWeaponItem> registerLanceItem(
      DeferredRegister<class_1792> items, String id, class_1793 properties, ModItemTier material, WeaponType type
   ) {
      return items.register(id, () -> new LanceItem(properties, material, type));
   }

   public static RegistrySupplier<MedievalShieldItem> registerMedievalShieldItem(
      DeferredRegister<class_1792> items,
      String id,
      class_2960 location,
      class_1793 properties,
      ModItemTier material,
      boolean paintable,
      boolean is3d,
      ShieldType type
   ) {
      return items.register(id, () -> new MedievalShieldItem(id, location, properties, material, paintable, is3d, type));
   }

   public static RegistrySupplier<MedievalShieldItem> registerPaviseItem(
      DeferredRegister<class_1792> items,
      String id,
      class_2960 location,
      class_1793 properties,
      ModItemTier material,
      boolean paintable,
      boolean is3d,
      ShieldType type,
      Supplier<PaviseBlock> block
   ) {
      return items.register(id, () -> new PaviseItem(id, location, properties, material, paintable, is3d, type, block));
   }
}
