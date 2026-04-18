package com.magistuarmory.api.item;

import com.magistuarmory.block.PaviseBlock;
import com.magistuarmory.client.render.ModRender;
import com.magistuarmory.item.ArmorDecoration;
import com.magistuarmory.item.ArmorDecorationItem;
import com.magistuarmory.item.DyeableArmorDecorationItem;
import com.magistuarmory.item.ItemRegistryHelper;
import com.magistuarmory.item.LanceItem;
import com.magistuarmory.item.MedievalBowItem;
import com.magistuarmory.item.MedievalCrossbowItem;
import com.magistuarmory.item.MedievalShieldItem;
import com.magistuarmory.item.MedievalWeaponItem;
import com.magistuarmory.item.ModItemTier;
import com.magistuarmory.item.RangedWeaponType;
import com.magistuarmory.item.ShieldType;
import com.magistuarmory.item.ShieldsSupply;
import com.magistuarmory.item.WeaponType;
import com.magistuarmory.item.WeaponsSupply;
import com.magistuarmory.item.armor.ArmorType;
import com.magistuarmory.item.armor.DyeableMedievalArmorItem;
import com.magistuarmory.item.armor.DyeableWearableArmorDecorationItem;
import com.magistuarmory.item.armor.KnightItem;
import com.magistuarmory.item.armor.MedievalArmorItem;
import com.magistuarmory.item.armor.WearableArmorDecorationItem;
import dev.architectury.event.events.common.LifecycleEvent;
import dev.architectury.platform.Platform;
import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Supplier;
import me.shedaniel.cloth.clothconfig.shadowed.blue.endless.jankson.annotation.Nullable;
import net.fabricmc.api.EnvType;
import net.minecraft.class_1792;
import net.minecraft.class_1799;
import net.minecraft.class_1935;
import net.minecraft.class_2960;
import net.minecraft.class_7924;
import net.minecraft.class_1738.class_8051;
import net.minecraft.class_1792.class_1793;

public abstract class ModItemsProvider {
   public final String modId;
   public final DeferredRegister<class_1792> items;
   public final List<RegistrySupplier<? extends MedievalShieldItem>> shieldItems = new ArrayList<>();
   public final List<RegistrySupplier<? extends MedievalWeaponItem>> weaponItems = new ArrayList<>();
   public final List<RegistrySupplier<? extends class_1792>> dyeableItems = new ArrayList<>();
   public final List<RegistrySupplier<? extends MedievalArmorItem>> armorItems = new ArrayList<>();
   public final List<RegistrySupplier<? extends class_1792>> ingredientItems = new ArrayList<>();
   public final List<RegistrySupplier<? extends ArmorDecoration>> armorDecorationItems = new ArrayList<>();
   public final List<RegistrySupplier<? extends class_1792>> rangedWeaponItems = new ArrayList<>();

   public ModItemsProvider(String modId) {
      this.modId = modId;
      this.items = DeferredRegister.create(modId, class_7924.field_41197);
   }

   public WeaponsSupply addWeaponsSupply(BiFunction<ModItemTier, class_1793, RegistrySupplier<MedievalWeaponItem>> workshop) {
      return new WeaponsSupply(workshop);
   }

   public ShieldsSupply addShieldsSupply(BiFunction<ModItemTier, class_1793, RegistrySupplier<MedievalShieldItem>> workshop, String shieldName) {
      return new ShieldsSupply(workshop, shieldName);
   }

   @Nullable
   public RegistrySupplier<KnightItem> addKnightItem(String id, ArmorType type, class_8051 slot, class_1793 properties) {
      if (type.isDisabled()) {
         return null;
      } else {
         RegistrySupplier<KnightItem> armor = ItemRegistryHelper.registerKnightItem(this.items, id, type, slot, properties);
         this.dyeableItems.add(armor);
         this.armorItems.add(armor);
         return armor;
      }
   }

   @Nullable
   public RegistrySupplier<MedievalArmorItem> addJoustingItem(String id, ArmorType type, class_8051 slot, class_1793 properties) {
      if (type.isDisabled()) {
         return null;
      } else {
         RegistrySupplier<MedievalArmorItem> armor = ItemRegistryHelper.registerJoustingItem(this.items, id, type, slot, properties);
         this.armorItems.add(armor);
         if (slot == class_8051.field_41934) {
            this.dyeableItems.add(armor);
         }

         return armor;
      }
   }

   @Nullable
   public RegistrySupplier<MedievalArmorItem> addMedievalArmorItem(String id, ArmorType type, class_8051 slot, class_1793 properties) {
      if (type.isDisabled()) {
         return null;
      } else {
         RegistrySupplier<MedievalArmorItem> armor = ItemRegistryHelper.registerMedievalArmorItem(this.items, id, type, slot, properties);
         this.armorItems.add(armor);
         return armor;
      }
   }

   @Nullable
   public RegistrySupplier<DyeableMedievalArmorItem> addDyeableMedievalArmorItem(
      String id, ArmorType type, class_8051 slot, class_1793 properties, int defaultcolor
   ) {
      if (type.isDisabled()) {
         return null;
      } else {
         RegistrySupplier<DyeableMedievalArmorItem> armor = ItemRegistryHelper.registerDyeableMedievalArmorItem(
            this.items, id, type, slot, properties, defaultcolor
         );
         this.dyeableItems.add(armor);
         this.armorItems.add(armor);
         return armor;
      }
   }

   @Nullable
   public RegistrySupplier<MedievalWeaponItem> addMedievalWeaponItem(String id, class_1793 properties, ModItemTier material, WeaponType type) {
      if (type.isDisabled()) {
         return null;
      } else {
         RegistrySupplier<MedievalWeaponItem> weapon = ItemRegistryHelper.registerMedievalWeaponItem(this.items, id, properties, material, type);
         this.weaponItems.add(weapon);
         return weapon;
      }
   }

   @Nullable
   public RegistrySupplier<MedievalWeaponItem> addLanceItem(String id, class_1793 properties, ModItemTier material, WeaponType type) {
      if (type.isDisabled()) {
         return null;
      } else {
         RegistrySupplier<MedievalWeaponItem> weapon = ItemRegistryHelper.registerLanceItem(this.items, id, properties, material, type);
         this.weaponItems.add(weapon);
         return weapon;
      }
   }

   public RegistrySupplier<class_1792> addIngredientItem(String id, Supplier<class_1792> supplier) {
      RegistrySupplier<class_1792> registrysupplier = this.items.register(id, supplier);
      this.ingredientItems.add(registrysupplier);
      return registrysupplier;
   }

   public RegistrySupplier<ArmorDecorationItem> addArmorDecorationItem(String id, Supplier<ArmorDecorationItem> supplier) {
      RegistrySupplier<ArmorDecorationItem> registrysupplier = this.items.register(id, supplier);
      this.armorDecorationItems.add(registrysupplier);
      return registrysupplier;
   }

   public RegistrySupplier<DyeableArmorDecorationItem> addDyeableArmorDecorationItem(String id, Supplier<DyeableArmorDecorationItem> supplier) {
      RegistrySupplier<DyeableArmorDecorationItem> registrysupplier = this.items.register(id, supplier);
      this.armorDecorationItems.add(registrysupplier);
      this.dyeableItems.add(registrysupplier);
      return registrysupplier;
   }

   public RegistrySupplier<WearableArmorDecorationItem> addWearableArmorDecorationItem(String id, ArmorType material, class_8051 type, class_1793 properties) {
      RegistrySupplier<WearableArmorDecorationItem> registrysupplier = ItemRegistryHelper.registerWearableArmorDecorationItem(
         this.items, id, material, type, properties
      );
      this.armorDecorationItems.add(registrysupplier);
      this.armorItems.add(registrysupplier);
      return registrysupplier;
   }

   public RegistrySupplier<DyeableWearableArmorDecorationItem> addDyeableWearableArmorDecorationItem(
      String id, ArmorType material, class_8051 type, class_1793 properties, int defaultcolor
   ) {
      RegistrySupplier<DyeableWearableArmorDecorationItem> registrysupplier = ItemRegistryHelper.registerDyeableWearableArmorDecorationItem(
         this.items, id, material, type, properties, defaultcolor
      );
      this.armorDecorationItems.add(registrysupplier);
      this.dyeableItems.add(registrysupplier);
      this.armorItems.add(registrysupplier);
      return registrysupplier;
   }

   @Nullable
   public RegistrySupplier<MedievalShieldItem> addMedievalShieldItem(
      String id, String name, class_1793 properties, ModItemTier material, boolean paintable, boolean is3d, ShieldType type
   ) {
      if (type.isDisabled()) {
         return null;
      } else {
         RegistrySupplier<MedievalShieldItem> shield = ItemRegistryHelper.registerMedievalShieldItem(
            this.items, id, class_2960.method_60655(this.modId, name), properties, material, paintable, is3d, type
         );
         this.shieldItems.add(shield);
         return shield;
      }
   }

   @Nullable
   public RegistrySupplier<MedievalShieldItem> addPaviseItem(
      String id, String name, class_1793 properties, ModItemTier material, boolean paintable, boolean is3d, ShieldType type, Supplier<PaviseBlock> block
   ) {
      if (type.isDisabled()) {
         return null;
      } else {
         RegistrySupplier<MedievalShieldItem> shield = ItemRegistryHelper.registerPaviseItem(
            this.items, id, class_2960.method_60655(this.modId, name), properties, material, paintable, is3d, type, block
         );
         this.shieldItems.add(shield);
         return shield;
      }
   }

   @Nullable
   public RegistrySupplier<class_1792> addMedievalBowItem(String id, RangedWeaponType type) {
      if (type.isDisabled()) {
         return null;
      } else {
         RegistrySupplier<class_1792> bow = this.items
            .register(
               id, () -> new MedievalBowItem(new class_1793().method_7889(1).method_7895(type.getDurability()), type.getProjectileSpeed(), type.getPullTime())
            );
         this.rangedWeaponItems.add(bow);
         return bow;
      }
   }

   @Nullable
   public RegistrySupplier<class_1792> addMedievalCrossbowItem(String id, RangedWeaponType type) {
      if (type.isDisabled()) {
         return null;
      } else {
         RegistrySupplier<class_1792> crossbow = this.items
            .register(
               id,
               () -> new MedievalCrossbowItem(new class_1793().method_7889(1).method_7895(type.getDurability()), type.getProjectileSpeed(), type.getPullTime())
            );
         this.rangedWeaponItems.add(crossbow);
         return crossbow;
      }
   }

   @Nullable
   public RegistrySupplier<class_1792> addMedievalBowItem(String id, int durability, float projectileSpeed, int pullTime) {
      RegistrySupplier<class_1792> bow = this.items
         .register(id, () -> new MedievalBowItem(new class_1793().method_7889(1).method_7895(durability), projectileSpeed, pullTime));
      this.rangedWeaponItems.add(bow);
      return bow;
   }

   @Nullable
   public RegistrySupplier<class_1792> addMedievalCrossbowItem(String id, int durability, float projectileSpeed, int pullTime) {
      RegistrySupplier<class_1792> crossbow = this.items
         .register(id, () -> new MedievalCrossbowItem(new class_1793().method_7889(1).method_7895(durability), projectileSpeed, pullTime));
      this.rangedWeaponItems.add(crossbow);
      return crossbow;
   }

   public static class_1799 getDecoratedStack(RegistrySupplier<? extends class_1792> suppler, RegistrySupplier<? extends ArmorDecorationItem> decorationsuppler) {
      if (suppler == null) {
         return class_1799.field_8037;
      } else {
         class_1799 stack = new class_1799((class_1935)suppler.get());
         ArmorDecorationItem decorationitem = (ArmorDecorationItem)decorationsuppler.get();
         class_1799 decorationstack = new class_1799(decorationitem);
         decorationitem.decorate(stack, decorationstack);
         return stack;
      }
   }

   public void onSetup() {
      for (RegistrySupplier<? extends MedievalWeaponItem> supplier : this.weaponItems) {
         if (supplier.get() instanceof LanceItem lance) {
            lance.setupDropItems();
         }
      }
   }

   public void init() {
      this.items.register();
      LifecycleEvent.SETUP.register(this::onSetup);
      if (Platform.getEnv() == EnvType.CLIENT) {
         LifecycleEvent.SETUP.register((Runnable)() -> ModRender.setup(this));
      }
   }
}
