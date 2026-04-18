package com.magistuarmory.item;

import com.magistuarmory.block.PaviseBlock;
import com.magistuarmory.item.armor.ArmorType;
import com.magistuarmory.item.armor.DyeableMedievalArmorItem;
import com.magistuarmory.item.armor.DyeableWearableArmorDecorationItem;
import com.magistuarmory.item.armor.KnightItem;
import com.magistuarmory.item.armor.MedievalArmorItem;
import com.magistuarmory.item.armor.WearableArmorDecorationItem;
import com.magistuarmory.item.fabric.ItemRegistryHelperImpl;
import dev.architectury.injectables.annotations.ExpectPlatform;
import dev.architectury.injectables.annotations.ExpectPlatform.Transformed;
import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import java.util.function.Supplier;
import net.minecraft.class_1792;
import net.minecraft.class_2960;
import net.minecraft.class_1738.class_8051;
import net.minecraft.class_1792.class_1793;

public class ItemRegistryHelper {
   @ExpectPlatform
   @Transformed
   public static RegistrySupplier<KnightItem> registerKnightItem(
      DeferredRegister<class_1792> items, String id, ArmorType material, class_8051 type, class_1793 properties
   ) {
      return ItemRegistryHelperImpl.registerKnightItem(items, id, material, type, properties);
   }

   @ExpectPlatform
   @Transformed
   public static RegistrySupplier<MedievalArmorItem> registerJoustingItem(
      DeferredRegister<class_1792> items, String id, ArmorType material, class_8051 type, class_1793 properties
   ) {
      return ItemRegistryHelperImpl.registerJoustingItem(items, id, material, type, properties);
   }

   @ExpectPlatform
   @Transformed
   public static RegistrySupplier<DyeableMedievalArmorItem> registerDyeableMedievalArmorItem(
      DeferredRegister<class_1792> items, String id, ArmorType material, class_8051 type, class_1793 properties, int defaultcolor
   ) {
      return ItemRegistryHelperImpl.registerDyeableMedievalArmorItem(items, id, material, type, properties, defaultcolor);
   }

   @ExpectPlatform
   @Transformed
   public static RegistrySupplier<MedievalArmorItem> registerMedievalArmorItem(
      DeferredRegister<class_1792> items, String id, ArmorType material, class_8051 type, class_1793 properties
   ) {
      return ItemRegistryHelperImpl.registerMedievalArmorItem(items, id, material, type, properties);
   }

   @ExpectPlatform
   @Transformed
   public static RegistrySupplier<WearableArmorDecorationItem> registerWearableArmorDecorationItem(
      DeferredRegister<class_1792> items, String id, ArmorType material, class_8051 type, class_1793 properties
   ) {
      return ItemRegistryHelperImpl.registerWearableArmorDecorationItem(items, id, material, type, properties);
   }

   @ExpectPlatform
   @Transformed
   public static RegistrySupplier<DyeableWearableArmorDecorationItem> registerDyeableWearableArmorDecorationItem(
      DeferredRegister<class_1792> items, String id, ArmorType material, class_8051 type, class_1793 properties, int defaultcolor
   ) {
      return ItemRegistryHelperImpl.registerDyeableWearableArmorDecorationItem(items, id, material, type, properties, defaultcolor);
   }

   @ExpectPlatform
   @Transformed
   public static RegistrySupplier<MedievalWeaponItem> registerMedievalWeaponItem(
      DeferredRegister<class_1792> items, String id, class_1793 properties, ModItemTier material, WeaponType type
   ) {
      return ItemRegistryHelperImpl.registerMedievalWeaponItem(items, id, properties, material, type);
   }

   @ExpectPlatform
   @Transformed
   public static RegistrySupplier<MedievalWeaponItem> registerLanceItem(
      DeferredRegister<class_1792> items, String id, class_1793 properties, ModItemTier material, WeaponType type
   ) {
      return ItemRegistryHelperImpl.registerLanceItem(items, id, properties, material, type);
   }

   @ExpectPlatform
   @Transformed
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
      return ItemRegistryHelperImpl.registerMedievalShieldItem(items, id, location, properties, material, paintable, is3d, type);
   }

   @ExpectPlatform
   @Transformed
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
      return ItemRegistryHelperImpl.registerPaviseItem(items, id, location, properties, material, paintable, is3d, type, block);
   }
}
