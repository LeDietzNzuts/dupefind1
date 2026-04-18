package net.p3pp3rf1y.sophisticatedbackpacks.init;

import com.mojang.serialization.MapCodec;
import io.github.fabricators_of_create.porting_lib.loot.IGlobalLootModifier;
import io.github.fabricators_of_create.porting_lib.loot.PortingLibLoot;
import io.github.fabricators_of_create.porting_lib.util.DeferredHolder;
import io.github.fabricators_of_create.porting_lib.util.DeferredRegister;
import java.util.List;
import java.util.function.Supplier;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidStorage;
import net.fabricmc.fabric.api.transfer.v1.item.ItemStorage;
import net.fabricmc.fabric.api.transfer.v1.storage.Storage;
import net.minecraft.class_1268;
import net.minecraft.class_1299;
import net.minecraft.class_1311;
import net.minecraft.class_1657;
import net.minecraft.class_1761;
import net.minecraft.class_1792;
import net.minecraft.class_1799;
import net.minecraft.class_1865;
import net.minecraft.class_1866;
import net.minecraft.class_1935;
import net.minecraft.class_1937;
import net.minecraft.class_2315;
import net.minecraft.class_2338;
import net.minecraft.class_2342;
import net.minecraft.class_2350;
import net.minecraft.class_2561;
import net.minecraft.class_2680;
import net.minecraft.class_2960;
import net.minecraft.class_2968;
import net.minecraft.class_2969;
import net.minecraft.class_3859;
import net.minecraft.class_3861;
import net.minecraft.class_3862;
import net.minecraft.class_3917;
import net.minecraft.class_5339;
import net.minecraft.class_5342;
import net.minecraft.class_5556;
import net.minecraft.class_5620;
import net.minecraft.class_6862;
import net.minecraft.class_7923;
import net.minecraft.class_7924;
import net.minecraft.class_9062;
import net.minecraft.class_1299.class_1300;
import net.minecraft.class_1792.class_1793;
import net.minecraft.class_2350.class_2351;
import net.p3pp3rf1y.sophisticatedbackpacks.Config;
import net.p3pp3rf1y.sophisticatedbackpacks.backpack.BackpackItem;
import net.p3pp3rf1y.sophisticatedbackpacks.backpack.wrapper.BackpackWrapper;
import net.p3pp3rf1y.sophisticatedbackpacks.backpack.wrapper.IBackpackWrapper;
import net.p3pp3rf1y.sophisticatedbackpacks.common.gui.BackpackContainer;
import net.p3pp3rf1y.sophisticatedbackpacks.common.gui.BackpackSettingsContainerMenu;
import net.p3pp3rf1y.sophisticatedbackpacks.crafting.BackpackDyeRecipe;
import net.p3pp3rf1y.sophisticatedbackpacks.crafting.BackpackUpgradeRecipe;
import net.p3pp3rf1y.sophisticatedbackpacks.crafting.BasicBackpackRecipe;
import net.p3pp3rf1y.sophisticatedbackpacks.crafting.SmithingBackpackUpgradeRecipe;
import net.p3pp3rf1y.sophisticatedbackpacks.data.CopyBackpackDataFunction;
import net.p3pp3rf1y.sophisticatedbackpacks.data.SBLootEnabledCondition;
import net.p3pp3rf1y.sophisticatedbackpacks.data.SBLootModifierProvider;
import net.p3pp3rf1y.sophisticatedbackpacks.upgrades.anvil.AnvilUpgradeContainer;
import net.p3pp3rf1y.sophisticatedbackpacks.upgrades.anvil.AnvilUpgradeItem;
import net.p3pp3rf1y.sophisticatedbackpacks.upgrades.anvil.AnvilUpgradeWrapper;
import net.p3pp3rf1y.sophisticatedbackpacks.upgrades.deposit.DepositUpgradeContainer;
import net.p3pp3rf1y.sophisticatedbackpacks.upgrades.deposit.DepositUpgradeItem;
import net.p3pp3rf1y.sophisticatedbackpacks.upgrades.deposit.DepositUpgradeWrapper;
import net.p3pp3rf1y.sophisticatedbackpacks.upgrades.everlasting.EverlastingBackpackItemEntity;
import net.p3pp3rf1y.sophisticatedbackpacks.upgrades.everlasting.EverlastingUpgradeItem;
import net.p3pp3rf1y.sophisticatedbackpacks.upgrades.inception.InceptionUpgradeContainer;
import net.p3pp3rf1y.sophisticatedbackpacks.upgrades.inception.InceptionUpgradeItem;
import net.p3pp3rf1y.sophisticatedbackpacks.upgrades.inception.InceptionUpgradeWrapper;
import net.p3pp3rf1y.sophisticatedbackpacks.upgrades.refill.RefillUpgradeContainer;
import net.p3pp3rf1y.sophisticatedbackpacks.upgrades.refill.RefillUpgradeItem;
import net.p3pp3rf1y.sophisticatedbackpacks.upgrades.refill.RefillUpgradeWrapper;
import net.p3pp3rf1y.sophisticatedbackpacks.upgrades.restock.RestockUpgradeItem;
import net.p3pp3rf1y.sophisticatedbackpacks.upgrades.restock.RestockUpgradeWrapper;
import net.p3pp3rf1y.sophisticatedbackpacks.upgrades.smithing.SmithingUpgradeContainer;
import net.p3pp3rf1y.sophisticatedbackpacks.upgrades.smithing.SmithingUpgradeItem;
import net.p3pp3rf1y.sophisticatedbackpacks.upgrades.smithing.SmithingUpgradeWrapper;
import net.p3pp3rf1y.sophisticatedbackpacks.upgrades.toolswapper.ToolSwapperUpgradeContainer;
import net.p3pp3rf1y.sophisticatedbackpacks.upgrades.toolswapper.ToolSwapperUpgradeItem;
import net.p3pp3rf1y.sophisticatedbackpacks.upgrades.toolswapper.ToolSwapperUpgradeWrapper;
import net.p3pp3rf1y.sophisticatedcore.api.IStorageWrapper;
import net.p3pp3rf1y.sophisticatedcore.common.gui.UpgradeContainerRegistry;
import net.p3pp3rf1y.sophisticatedcore.common.gui.UpgradeContainerType;
import net.p3pp3rf1y.sophisticatedcore.upgrades.ContentsFilteredUpgradeContainer;
import net.p3pp3rf1y.sophisticatedcore.upgrades.battery.BatteryUpgradeContainer;
import net.p3pp3rf1y.sophisticatedcore.upgrades.battery.BatteryUpgradeItem;
import net.p3pp3rf1y.sophisticatedcore.upgrades.battery.BatteryUpgradeWrapper;
import net.p3pp3rf1y.sophisticatedcore.upgrades.compacting.CompactingUpgradeContainer;
import net.p3pp3rf1y.sophisticatedcore.upgrades.compacting.CompactingUpgradeItem;
import net.p3pp3rf1y.sophisticatedcore.upgrades.compacting.CompactingUpgradeWrapper;
import net.p3pp3rf1y.sophisticatedcore.upgrades.cooking.AutoBlastingUpgradeItem;
import net.p3pp3rf1y.sophisticatedcore.upgrades.cooking.AutoCookingUpgradeContainer;
import net.p3pp3rf1y.sophisticatedcore.upgrades.cooking.AutoSmeltingUpgradeItem;
import net.p3pp3rf1y.sophisticatedcore.upgrades.cooking.AutoSmokingUpgradeItem;
import net.p3pp3rf1y.sophisticatedcore.upgrades.cooking.BlastingUpgradeItem;
import net.p3pp3rf1y.sophisticatedcore.upgrades.cooking.CookingUpgradeContainer;
import net.p3pp3rf1y.sophisticatedcore.upgrades.cooking.SmeltingUpgradeItem;
import net.p3pp3rf1y.sophisticatedcore.upgrades.cooking.SmokingUpgradeItem;
import net.p3pp3rf1y.sophisticatedcore.upgrades.cooking.AutoCookingUpgradeWrapper.AutoBlastingUpgradeWrapper;
import net.p3pp3rf1y.sophisticatedcore.upgrades.cooking.AutoCookingUpgradeWrapper.AutoSmeltingUpgradeWrapper;
import net.p3pp3rf1y.sophisticatedcore.upgrades.cooking.AutoCookingUpgradeWrapper.AutoSmokingUpgradeWrapper;
import net.p3pp3rf1y.sophisticatedcore.upgrades.cooking.CookingUpgradeWrapper.BlastingUpgradeWrapper;
import net.p3pp3rf1y.sophisticatedcore.upgrades.cooking.CookingUpgradeWrapper.SmeltingUpgradeWrapper;
import net.p3pp3rf1y.sophisticatedcore.upgrades.cooking.CookingUpgradeWrapper.SmokingUpgradeWrapper;
import net.p3pp3rf1y.sophisticatedcore.upgrades.crafting.CraftingUpgradeContainer;
import net.p3pp3rf1y.sophisticatedcore.upgrades.crafting.CraftingUpgradeItem;
import net.p3pp3rf1y.sophisticatedcore.upgrades.crafting.CraftingUpgradeWrapper;
import net.p3pp3rf1y.sophisticatedcore.upgrades.feeding.FeedingUpgradeContainer;
import net.p3pp3rf1y.sophisticatedcore.upgrades.feeding.FeedingUpgradeItem;
import net.p3pp3rf1y.sophisticatedcore.upgrades.feeding.FeedingUpgradeWrapper;
import net.p3pp3rf1y.sophisticatedcore.upgrades.filter.FilterUpgradeContainer;
import net.p3pp3rf1y.sophisticatedcore.upgrades.filter.FilterUpgradeItem;
import net.p3pp3rf1y.sophisticatedcore.upgrades.infinity.InfinityUpgradeItem;
import net.p3pp3rf1y.sophisticatedcore.upgrades.jukebox.JukeboxUpgradeContainer;
import net.p3pp3rf1y.sophisticatedcore.upgrades.jukebox.JukeboxUpgradeItem;
import net.p3pp3rf1y.sophisticatedcore.upgrades.jukebox.JukeboxUpgradeWrapper;
import net.p3pp3rf1y.sophisticatedcore.upgrades.magnet.MagnetUpgradeContainer;
import net.p3pp3rf1y.sophisticatedcore.upgrades.magnet.MagnetUpgradeItem;
import net.p3pp3rf1y.sophisticatedcore.upgrades.magnet.MagnetUpgradeWrapper;
import net.p3pp3rf1y.sophisticatedcore.upgrades.pickup.PickupUpgradeItem;
import net.p3pp3rf1y.sophisticatedcore.upgrades.pickup.PickupUpgradeWrapper;
import net.p3pp3rf1y.sophisticatedcore.upgrades.pump.PumpUpgradeContainer;
import net.p3pp3rf1y.sophisticatedcore.upgrades.pump.PumpUpgradeItem;
import net.p3pp3rf1y.sophisticatedcore.upgrades.pump.PumpUpgradeWrapper;
import net.p3pp3rf1y.sophisticatedcore.upgrades.stack.StackUpgradeItem;
import net.p3pp3rf1y.sophisticatedcore.upgrades.stonecutter.StonecutterUpgradeContainer;
import net.p3pp3rf1y.sophisticatedcore.upgrades.stonecutter.StonecutterUpgradeItem;
import net.p3pp3rf1y.sophisticatedcore.upgrades.stonecutter.StonecutterUpgradeWrapper;
import net.p3pp3rf1y.sophisticatedcore.upgrades.tank.TankUpgradeContainer;
import net.p3pp3rf1y.sophisticatedcore.upgrades.tank.TankUpgradeItem;
import net.p3pp3rf1y.sophisticatedcore.upgrades.tank.TankUpgradeWrapper;
import net.p3pp3rf1y.sophisticatedcore.upgrades.voiding.VoidUpgradeContainer;
import net.p3pp3rf1y.sophisticatedcore.upgrades.voiding.VoidUpgradeItem;
import net.p3pp3rf1y.sophisticatedcore.upgrades.voiding.VoidUpgradeWrapper;
import net.p3pp3rf1y.sophisticatedcore.upgrades.xppump.XpPumpUpgradeContainer;
import net.p3pp3rf1y.sophisticatedcore.upgrades.xppump.XpPumpUpgradeItem;
import net.p3pp3rf1y.sophisticatedcore.upgrades.xppump.XpPumpUpgradeWrapper;
import net.p3pp3rf1y.sophisticatedcore.util.EmptyItemHandler;
import net.p3pp3rf1y.sophisticatedcore.util.IMenuTypeExtension;
import net.p3pp3rf1y.sophisticatedcore.util.ItemBase;
import team.reborn.energy.api.EnergyStorage;

public class ModItems {
   public static final DeferredRegister<class_1792> ITEMS = DeferredRegister.create(class_7923.field_41178, "sophisticatedbackpacks");
   public static final DeferredRegister<class_1761> CREATIVE_MODE_TABS = DeferredRegister.create(
      class_7924.field_44688.method_29177(), "sophisticatedbackpacks"
   );
   public static final DeferredRegister<class_5339<?>> LOOT_FUNCTION_TYPES = DeferredRegister.create(
      class_7924.field_41199.method_29177(), "sophisticatedbackpacks"
   );
   public static final DeferredRegister<class_5342> LOOT_CONDITION_TYPES = DeferredRegister.create(
      class_7924.field_41198.method_29177(), "sophisticatedbackpacks"
   );
   public static final DeferredRegister<MapCodec<? extends IGlobalLootModifier>> LOOT_MODIFIERS = DeferredRegister.create(
      PortingLibLoot.GLOBAL_LOOT_MODIFIER_SERIALIZERS, "sophisticatedbackpacks"
   );
   private static final DeferredRegister<class_3917<?>> MENU_TYPES = DeferredRegister.create(class_7923.field_41187, "sophisticatedbackpacks");
   private static final DeferredRegister<class_1299<?>> ENTITY_TYPES = DeferredRegister.create(class_7923.field_41177, "sophisticatedbackpacks");
   public static final Supplier<BackpackItem> BACKPACK = ITEMS.register(
      "backpack",
      () -> new BackpackItem(Config.SERVER.leatherBackpack.inventorySlotCount::get, Config.SERVER.leatherBackpack.upgradeSlotCount::get, ModBlocks.BACKPACK)
   );
   public static final Supplier<BackpackItem> COPPER_BACKPACK = ITEMS.register(
      "copper_backpack",
      () -> new BackpackItem(
         Config.SERVER.copperBackpack.inventorySlotCount::get, Config.SERVER.copperBackpack.upgradeSlotCount::get, ModBlocks.COPPER_BACKPACK
      )
   );
   public static final Supplier<BackpackItem> IRON_BACKPACK = ITEMS.register(
      "iron_backpack",
      () -> new BackpackItem(Config.SERVER.ironBackpack.inventorySlotCount::get, Config.SERVER.ironBackpack.upgradeSlotCount::get, ModBlocks.IRON_BACKPACK)
   );
   public static final Supplier<BackpackItem> GOLD_BACKPACK = ITEMS.register(
      "gold_backpack",
      () -> new BackpackItem(Config.SERVER.goldBackpack.inventorySlotCount::get, Config.SERVER.goldBackpack.upgradeSlotCount::get, ModBlocks.GOLD_BACKPACK)
   );
   public static final Supplier<BackpackItem> DIAMOND_BACKPACK = ITEMS.register(
      "diamond_backpack",
      () -> new BackpackItem(
         Config.SERVER.diamondBackpack.inventorySlotCount::get, Config.SERVER.diamondBackpack.upgradeSlotCount::get, ModBlocks.DIAMOND_BACKPACK
      )
   );
   public static final Supplier<BackpackItem> NETHERITE_BACKPACK = ITEMS.register(
      "netherite_backpack",
      () -> new BackpackItem(
         Config.SERVER.netheriteBackpack.inventorySlotCount::get,
         Config.SERVER.netheriteBackpack.upgradeSlotCount::get,
         ModBlocks.NETHERITE_BACKPACK,
         class_1793::method_24359
      )
   );
   public static final List<Supplier<BackpackItem>> BACKPACKS = List.of(
      BACKPACK, COPPER_BACKPACK, IRON_BACKPACK, GOLD_BACKPACK, DIAMOND_BACKPACK, NETHERITE_BACKPACK
   );
   public static final class_2960 BACKPACK_UPGRADE_TAG_NAME = class_2960.method_60655("sophisticatedbackpacks", "upgrade");
   public static final class_6862<class_1792> BACKPACK_UPGRADE_TAG = class_6862.method_40092(class_7924.field_41197, BACKPACK_UPGRADE_TAG_NAME);
   public static final DeferredHolder<class_1792, PickupUpgradeItem> PICKUP_UPGRADE = ITEMS.register(
      "pickup_upgrade", () -> new PickupUpgradeItem(Config.SERVER.pickupUpgrade.filterSlots::get, Config.SERVER.maxUpgradesPerStorage)
   );
   public static final DeferredHolder<class_1792, PickupUpgradeItem> ADVANCED_PICKUP_UPGRADE = ITEMS.register(
      "advanced_pickup_upgrade", () -> new PickupUpgradeItem(Config.SERVER.advancedPickupUpgrade.filterSlots::get, Config.SERVER.maxUpgradesPerStorage)
   );
   public static final DeferredHolder<class_1792, FilterUpgradeItem> FILTER_UPGRADE = ITEMS.register(
      "filter_upgrade", () -> new FilterUpgradeItem(Config.SERVER.filterUpgrade.filterSlots::get, Config.SERVER.maxUpgradesPerStorage)
   );
   public static final DeferredHolder<class_1792, FilterUpgradeItem> ADVANCED_FILTER_UPGRADE = ITEMS.register(
      "advanced_filter_upgrade", () -> new FilterUpgradeItem(Config.SERVER.advancedFilterUpgrade.filterSlots::get, Config.SERVER.maxUpgradesPerStorage)
   );
   public static final DeferredHolder<class_1792, MagnetUpgradeItem> MAGNET_UPGRADE = ITEMS.register(
      "magnet_upgrade",
      () -> new MagnetUpgradeItem(
         Config.SERVER.magnetUpgrade.magnetRange::get, Config.SERVER.magnetUpgrade.filterSlots::get, Config.SERVER.maxUpgradesPerStorage
      )
   );
   public static final DeferredHolder<class_1792, MagnetUpgradeItem> ADVANCED_MAGNET_UPGRADE = ITEMS.register(
      "advanced_magnet_upgrade",
      () -> new MagnetUpgradeItem(
         Config.SERVER.advancedMagnetUpgrade.magnetRange::get, Config.SERVER.advancedMagnetUpgrade.filterSlots::get, Config.SERVER.maxUpgradesPerStorage
      )
   );
   public static final DeferredHolder<class_1792, FeedingUpgradeItem> FEEDING_UPGRADE = ITEMS.register(
      "feeding_upgrade", () -> new FeedingUpgradeItem(Config.SERVER.feedingUpgrade.filterSlots::get, Config.SERVER.maxUpgradesPerStorage)
   );
   public static final DeferredHolder<class_1792, FeedingUpgradeItem> ADVANCED_FEEDING_UPGRADE = ITEMS.register(
      "advanced_feeding_upgrade", () -> new FeedingUpgradeItem(Config.SERVER.advancedFeedingUpgrade.filterSlots::get, Config.SERVER.maxUpgradesPerStorage)
   );
   public static final DeferredHolder<class_1792, CompactingUpgradeItem> COMPACTING_UPGRADE = ITEMS.register(
      "compacting_upgrade", () -> new CompactingUpgradeItem(false, Config.SERVER.compactingUpgrade.filterSlots::get, Config.SERVER.maxUpgradesPerStorage)
   );
   public static final DeferredHolder<class_1792, CompactingUpgradeItem> ADVANCED_COMPACTING_UPGRADE = ITEMS.register(
      "advanced_compacting_upgrade",
      () -> new CompactingUpgradeItem(true, Config.SERVER.advancedCompactingUpgrade.filterSlots::get, Config.SERVER.maxUpgradesPerStorage)
   );
   public static final DeferredHolder<class_1792, VoidUpgradeItem> VOID_UPGRADE = ITEMS.register(
      "void_upgrade", () -> new VoidUpgradeItem(Config.SERVER.voidUpgrade, Config.SERVER.maxUpgradesPerStorage)
   );
   public static final DeferredHolder<class_1792, VoidUpgradeItem> ADVANCED_VOID_UPGRADE = ITEMS.register(
      "advanced_void_upgrade", () -> new VoidUpgradeItem(Config.SERVER.advancedVoidUpgrade, Config.SERVER.maxUpgradesPerStorage)
   );
   public static final DeferredHolder<class_1792, RestockUpgradeItem> RESTOCK_UPGRADE = ITEMS.register(
      "restock_upgrade", () -> new RestockUpgradeItem(Config.SERVER.restockUpgrade.filterSlots::get)
   );
   public static final DeferredHolder<class_1792, RestockUpgradeItem> ADVANCED_RESTOCK_UPGRADE = ITEMS.register(
      "advanced_restock_upgrade", () -> new RestockUpgradeItem(Config.SERVER.advancedRestockUpgrade.filterSlots::get)
   );
   public static final DeferredHolder<class_1792, DepositUpgradeItem> DEPOSIT_UPGRADE = ITEMS.register(
      "deposit_upgrade", () -> new DepositUpgradeItem(Config.SERVER.depositUpgrade.filterSlots::get)
   );
   public static final DeferredHolder<class_1792, DepositUpgradeItem> ADVANCED_DEPOSIT_UPGRADE = ITEMS.register(
      "advanced_deposit_upgrade", () -> new DepositUpgradeItem(Config.SERVER.advancedDepositUpgrade.filterSlots::get)
   );
   public static final DeferredHolder<class_1792, RefillUpgradeItem> REFILL_UPGRADE = ITEMS.register(
      "refill_upgrade", () -> new RefillUpgradeItem(Config.SERVER.refillUpgrade.filterSlots::get, false, false)
   );
   public static final DeferredHolder<class_1792, RefillUpgradeItem> ADVANCED_REFILL_UPGRADE = ITEMS.register(
      "advanced_refill_upgrade", () -> new RefillUpgradeItem(Config.SERVER.advancedRefillUpgrade.filterSlots::get, true, true)
   );
   public static final DeferredHolder<class_1792, InceptionUpgradeItem> INCEPTION_UPGRADE = ITEMS.register("inception_upgrade", InceptionUpgradeItem::new);
   public static final DeferredHolder<class_1792, EverlastingUpgradeItem> EVERLASTING_UPGRADE = ITEMS.register(
      "everlasting_upgrade", EverlastingUpgradeItem::new
   );
   public static final DeferredHolder<class_1792, SmeltingUpgradeItem> SMELTING_UPGRADE = ITEMS.register(
      "smelting_upgrade", () -> new SmeltingUpgradeItem(Config.SERVER.smeltingUpgrade, Config.SERVER.maxUpgradesPerStorage)
   );
   public static final DeferredHolder<class_1792, AutoSmeltingUpgradeItem> AUTO_SMELTING_UPGRADE = ITEMS.register(
      "auto_smelting_upgrade", () -> new AutoSmeltingUpgradeItem(Config.SERVER.autoSmeltingUpgrade, Config.SERVER.maxUpgradesPerStorage)
   );
   public static final DeferredHolder<class_1792, SmokingUpgradeItem> SMOKING_UPGRADE = ITEMS.register(
      "smoking_upgrade", () -> new SmokingUpgradeItem(Config.SERVER.smokingUpgrade, Config.SERVER.maxUpgradesPerStorage)
   );
   public static final DeferredHolder<class_1792, AutoSmokingUpgradeItem> AUTO_SMOKING_UPGRADE = ITEMS.register(
      "auto_smoking_upgrade", () -> new AutoSmokingUpgradeItem(Config.SERVER.autoSmokingUpgrade, Config.SERVER.maxUpgradesPerStorage)
   );
   public static final DeferredHolder<class_1792, BlastingUpgradeItem> BLASTING_UPGRADE = ITEMS.register(
      "blasting_upgrade", () -> new BlastingUpgradeItem(Config.SERVER.blastingUpgrade, Config.SERVER.maxUpgradesPerStorage)
   );
   public static final DeferredHolder<class_1792, AutoBlastingUpgradeItem> AUTO_BLASTING_UPGRADE = ITEMS.register(
      "auto_blasting_upgrade", () -> new AutoBlastingUpgradeItem(Config.SERVER.autoBlastingUpgrade, Config.SERVER.maxUpgradesPerStorage)
   );
   public static final DeferredHolder<class_1792, CraftingUpgradeItem> CRAFTING_UPGRADE = ITEMS.register(
      "crafting_upgrade", () -> new CraftingUpgradeItem(Config.SERVER.maxUpgradesPerStorage)
   );
   public static final DeferredHolder<class_1792, StonecutterUpgradeItem> STONECUTTER_UPGRADE = ITEMS.register(
      "stonecutter_upgrade", () -> new StonecutterUpgradeItem(Config.SERVER.maxUpgradesPerStorage)
   );
   public static final DeferredHolder<class_1792, StackUpgradeItem> STACK_UPGRADE_STARTER_TIER = ITEMS.register(
      "stack_upgrade_starter_tier", () -> new StackUpgradeItem(1.5, Config.SERVER.maxUpgradesPerStorage)
   );
   public static final DeferredHolder<class_1792, StackUpgradeItem> STACK_UPGRADE_TIER_1 = ITEMS.register(
      "stack_upgrade_tier_1", () -> new StackUpgradeItem(2.0, Config.SERVER.maxUpgradesPerStorage)
   );
   public static final DeferredHolder<class_1792, StackUpgradeItem> STACK_UPGRADE_TIER_2 = ITEMS.register(
      "stack_upgrade_tier_2", () -> new StackUpgradeItem(4.0, Config.SERVER.maxUpgradesPerStorage)
   );
   public static final DeferredHolder<class_1792, StackUpgradeItem> STACK_UPGRADE_TIER_3 = ITEMS.register(
      "stack_upgrade_tier_3", () -> new StackUpgradeItem(8.0, Config.SERVER.maxUpgradesPerStorage)
   );
   public static final DeferredHolder<class_1792, StackUpgradeItem> STACK_UPGRADE_TIER_4 = ITEMS.register(
      "stack_upgrade_tier_4", () -> new StackUpgradeItem(16.0, Config.SERVER.maxUpgradesPerStorage)
   );
   public static final DeferredHolder<class_1792, StackUpgradeItem> STACK_DOWNGRADE_TIER_1 = ITEMS.register(
      "stack_downgrade_tier_1", () -> new StackUpgradeItem(0.125, Config.SERVER.maxUpgradesPerStorage)
   );
   public static final DeferredHolder<class_1792, StackUpgradeItem> STACK_DOWNGRADE_TIER_2 = ITEMS.register(
      "stack_downgrade_tier_2", () -> new StackUpgradeItem(0.0625, Config.SERVER.maxUpgradesPerStorage)
   );
   public static final DeferredHolder<class_1792, StackUpgradeItem> STACK_DOWNGRADE_TIER_3 = ITEMS.register(
      "stack_downgrade_tier_3", () -> new StackUpgradeItem(0.03125, Config.SERVER.maxUpgradesPerStorage)
   );
   public static final DeferredHolder<class_1792, StackUpgradeItem> STACK_UPGRADE_OMEGA_TIER = ITEMS.register(
      "stack_upgrade_omega_tier", () -> new StackUpgradeItem(2.147483647E9, Config.SERVER.maxUpgradesPerStorage)
   );
   public static final DeferredHolder<class_1792, JukeboxUpgradeItem> JUKEBOX_UPGRADE = ITEMS.register(
      "jukebox_upgrade", () -> new JukeboxUpgradeItem(Config.SERVER.maxUpgradesPerStorage, () -> 1, () -> 1)
   );
   public static final DeferredHolder<class_1792, JukeboxUpgradeItem> ADVANCED_JUKEBOX_UPGRADE = ITEMS.register(
      "advanced_jukebox_upgrade",
      () -> new JukeboxUpgradeItem(
         Config.SERVER.maxUpgradesPerStorage, Config.SERVER.advancedJukeboxUpgrade.numberOfSlots, Config.SERVER.advancedJukeboxUpgrade.slotsInRow
      )
   );
   public static final DeferredHolder<class_1792, ToolSwapperUpgradeItem> TOOL_SWAPPER_UPGRADE = ITEMS.register(
      "tool_swapper_upgrade", () -> new ToolSwapperUpgradeItem(false, false)
   );
   public static final DeferredHolder<class_1792, ToolSwapperUpgradeItem> ADVANCED_TOOL_SWAPPER_UPGRADE = ITEMS.register(
      "advanced_tool_swapper_upgrade", () -> new ToolSwapperUpgradeItem(true, true)
   );
   public static final DeferredHolder<class_1792, TankUpgradeItem> TANK_UPGRADE = ITEMS.register(
      "tank_upgrade", () -> new TankUpgradeItem(Config.SERVER.tankUpgrade, Config.SERVER.maxUpgradesPerStorage)
   );
   public static final DeferredHolder<class_1792, BatteryUpgradeItem> BATTERY_UPGRADE = ITEMS.register(
      "battery_upgrade", () -> new BatteryUpgradeItem(Config.SERVER.batteryUpgrade, Config.SERVER.maxUpgradesPerStorage)
   );
   public static final DeferredHolder<class_1792, PumpUpgradeItem> PUMP_UPGRADE = ITEMS.register(
      "pump_upgrade", () -> new PumpUpgradeItem(false, true, Config.SERVER.pumpUpgrade, Config.SERVER.maxUpgradesPerStorage)
   );
   public static final DeferredHolder<class_1792, PumpUpgradeItem> ADVANCED_PUMP_UPGRADE = ITEMS.register(
      "advanced_pump_upgrade", () -> new PumpUpgradeItem(true, true, Config.SERVER.pumpUpgrade, Config.SERVER.maxUpgradesPerStorage)
   );
   public static final DeferredHolder<class_1792, XpPumpUpgradeItem> XP_PUMP_UPGRADE = ITEMS.register(
      "xp_pump_upgrade", () -> new XpPumpUpgradeItem(Config.SERVER.xpPumpUpgrade, Config.SERVER.maxUpgradesPerStorage)
   );
   public static final DeferredHolder<class_1792, AnvilUpgradeItem> ANVIL_UPGRADE = ITEMS.register("anvil_upgrade", AnvilUpgradeItem::new);
   public static final DeferredHolder<class_1792, SmithingUpgradeItem> SMITHING_UPGRADE = ITEMS.register("smithing_upgrade", SmithingUpgradeItem::new);
   public static final DeferredHolder<class_1792, InfinityUpgradeItem> INFINITY_UPGRADE = ITEMS.register(
      "infinity_upgrade", () -> new InfinityUpgradeItem(Config.SERVER.maxUpgradesPerStorage, true)
   );
   public static final DeferredHolder<class_1792, InfinityUpgradeItem> SURVIVAL_INFINITY_UPGRADE = ITEMS.register(
      "survival_infinity_upgrade", () -> new InfinityUpgradeItem(Config.SERVER.maxUpgradesPerStorage, false)
   );
   public static final Supplier<ItemBase> UPGRADE_BASE = ITEMS.register("upgrade_base", () -> new ItemBase(new class_1793().method_7889(16)));
   public static final Supplier<class_1761> CREATIVE_TAB = CREATIVE_MODE_TABS.register(
      "main",
      () -> FabricItemGroup.builder()
         .method_47320(() -> new class_1799((class_1935)BACKPACK.get()))
         .method_47321(class_2561.method_43471("itemGroup.sophisticatedbackpacks"))
         .method_47317(
            (featureFlags, output) -> ITEMS.getEntries()
               .stream()
               .filter(i -> i.get() instanceof ItemBase)
               .forEach(i -> ((ItemBase)i.get()).addCreativeTabItems(output::method_45420))
         )
         .method_47324()
   );
   public static final Supplier<class_3917<BackpackContainer>> BACKPACK_CONTAINER_TYPE = MENU_TYPES.register(
      "backpack", () -> IMenuTypeExtension.create(BackpackContainer::fromBuffer)
   );
   public static final Supplier<class_3917<BackpackSettingsContainerMenu>> SETTINGS_CONTAINER_TYPE = MENU_TYPES.register(
      "settings", () -> IMenuTypeExtension.create(BackpackSettingsContainerMenu::fromBuffer)
   );
   public static final Supplier<class_1299<EverlastingBackpackItemEntity>> EVERLASTING_BACKPACK_ITEM_ENTITY = ENTITY_TYPES.register(
      "everlasting_backpack_item",
      () -> class_1300.method_5903(EverlastingBackpackItemEntity::new, class_1311.field_17715)
         .method_17687(0.25F, 0.25F)
         .method_27299(6)
         .method_27300(20)
         .method_5905("")
   );
   private static final DeferredRegister<class_1865<?>> RECIPE_SERIALIZERS = DeferredRegister.create(class_7923.field_41189, "sophisticatedbackpacks");
   public static final Supplier<class_1866<?>> BACKPACK_DYE_RECIPE_SERIALIZER = RECIPE_SERIALIZERS.register(
      "backpack_dye", () -> new class_1866(BackpackDyeRecipe::new)
   );
   public static final Supplier<class_1865<?>> BACKPACK_UPGRADE_RECIPE_SERIALIZER = RECIPE_SERIALIZERS.register(
      "backpack_upgrade", BackpackUpgradeRecipe.Serializer::new
   );
   public static final Supplier<class_1865<?>> SMITHING_BACKPACK_UPGRADE_RECIPE_SERIALIZER = RECIPE_SERIALIZERS.register(
      "smithing_backpack_upgrade", SmithingBackpackUpgradeRecipe.Serializer::new
   );
   public static final Supplier<class_1865<?>> BASIC_BACKPACK_RECIPE_SERIALIZER = RECIPE_SERIALIZERS.register(
      "basic_backpack", BasicBackpackRecipe.Serializer::new
   );
   public static final Supplier<class_5339<CopyBackpackDataFunction>> COPY_BACKPACK_DATA = LOOT_FUNCTION_TYPES.register(
      "copy_backpack_data", () -> new class_5339(CopyBackpackDataFunction.CODEC)
   );
   public static final Supplier<class_5342> LOOT_ENABLED_CONDITION = LOOT_CONDITION_TYPES.register(
      "loot_enabled", () -> new class_5342(SBLootEnabledCondition.CODEC)
   );
   public static final Supplier<MapCodec<SBLootModifierProvider.InjectLootModifier>> INJECT_LOOT = LOOT_MODIFIERS.register(
      "inject_loot", () -> SBLootModifierProvider.InjectLootModifier.CODEC
   );
   public static final UpgradeContainerType<PickupUpgradeWrapper, ContentsFilteredUpgradeContainer<PickupUpgradeWrapper>> PICKUP_BASIC_TYPE = new UpgradeContainerType(
      ContentsFilteredUpgradeContainer::new
   );
   public static final UpgradeContainerType<PickupUpgradeWrapper, ContentsFilteredUpgradeContainer<PickupUpgradeWrapper>> PICKUP_ADVANCED_TYPE = new UpgradeContainerType(
      ContentsFilteredUpgradeContainer::new
   );
   public static final UpgradeContainerType<MagnetUpgradeWrapper, MagnetUpgradeContainer> MAGNET_BASIC_TYPE = new UpgradeContainerType(
      MagnetUpgradeContainer::new
   );
   public static final UpgradeContainerType<MagnetUpgradeWrapper, MagnetUpgradeContainer> MAGNET_ADVANCED_TYPE = new UpgradeContainerType(
      MagnetUpgradeContainer::new
   );
   public static final UpgradeContainerType<FeedingUpgradeWrapper, FeedingUpgradeContainer> FEEDING_TYPE = new UpgradeContainerType(
      FeedingUpgradeContainer::new
   );
   public static final UpgradeContainerType<FeedingUpgradeWrapper, FeedingUpgradeContainer> ADVANCED_FEEDING_TYPE = new UpgradeContainerType(
      FeedingUpgradeContainer::new
   );
   public static final UpgradeContainerType<CompactingUpgradeWrapper, CompactingUpgradeContainer> COMPACTING_TYPE = new UpgradeContainerType(
      CompactingUpgradeContainer::new
   );
   public static final UpgradeContainerType<CompactingUpgradeWrapper, CompactingUpgradeContainer> ADVANCED_COMPACTING_TYPE = new UpgradeContainerType(
      CompactingUpgradeContainer::new
   );
   public static final UpgradeContainerType<VoidUpgradeWrapper, VoidUpgradeContainer> VOID_TYPE = new UpgradeContainerType(VoidUpgradeContainer::new);
   public static final UpgradeContainerType<VoidUpgradeWrapper, VoidUpgradeContainer> ADVANCED_VOID_TYPE = new UpgradeContainerType(VoidUpgradeContainer::new);
   public static final UpgradeContainerType<RestockUpgradeWrapper, ContentsFilteredUpgradeContainer<RestockUpgradeWrapper>> RESTOCK_TYPE = new UpgradeContainerType(
      ContentsFilteredUpgradeContainer::new
   );
   public static final UpgradeContainerType<RestockUpgradeWrapper, ContentsFilteredUpgradeContainer<RestockUpgradeWrapper>> ADVANCED_RESTOCK_TYPE = new UpgradeContainerType(
      ContentsFilteredUpgradeContainer::new
   );
   public static final UpgradeContainerType<DepositUpgradeWrapper, DepositUpgradeContainer> DEPOSIT_TYPE = new UpgradeContainerType(
      DepositUpgradeContainer::new
   );
   public static final UpgradeContainerType<DepositUpgradeWrapper, DepositUpgradeContainer> ADVANCED_DEPOSIT_TYPE = new UpgradeContainerType(
      DepositUpgradeContainer::new
   );
   public static final UpgradeContainerType<RefillUpgradeWrapper, RefillUpgradeContainer> REFILL_TYPE = new UpgradeContainerType(RefillUpgradeContainer::new);
   public static final UpgradeContainerType<RefillUpgradeWrapper, RefillUpgradeContainer> ADVANCED_REFILL_TYPE = new UpgradeContainerType(
      RefillUpgradeContainer::new
   );
   public static final UpgradeContainerType<SmeltingUpgradeWrapper, CookingUpgradeContainer<class_3861, SmeltingUpgradeWrapper>> SMELTING_TYPE = new UpgradeContainerType(
      CookingUpgradeContainer::new
   );
   public static final UpgradeContainerType<AutoSmeltingUpgradeWrapper, AutoCookingUpgradeContainer<class_3861, AutoSmeltingUpgradeWrapper>> AUTO_SMELTING_TYPE = new UpgradeContainerType(
      AutoCookingUpgradeContainer::new
   );
   public static final UpgradeContainerType<SmokingUpgradeWrapper, CookingUpgradeContainer<class_3862, SmokingUpgradeWrapper>> SMOKING_TYPE = new UpgradeContainerType(
      CookingUpgradeContainer::new
   );
   public static final UpgradeContainerType<AutoSmokingUpgradeWrapper, AutoCookingUpgradeContainer<class_3862, AutoSmokingUpgradeWrapper>> AUTO_SMOKING_TYPE = new UpgradeContainerType(
      AutoCookingUpgradeContainer::new
   );
   public static final UpgradeContainerType<BlastingUpgradeWrapper, CookingUpgradeContainer<class_3859, BlastingUpgradeWrapper>> BLASTING_TYPE = new UpgradeContainerType(
      CookingUpgradeContainer::new
   );
   public static final UpgradeContainerType<AutoBlastingUpgradeWrapper, AutoCookingUpgradeContainer<class_3859, AutoBlastingUpgradeWrapper>> AUTO_BLASTING_TYPE = new UpgradeContainerType(
      AutoCookingUpgradeContainer::new
   );
   public static final UpgradeContainerType<CraftingUpgradeWrapper, CraftingUpgradeContainer> CRAFTING_TYPE = new UpgradeContainerType(
      CraftingUpgradeContainer::new
   );
   public static final UpgradeContainerType<InceptionUpgradeWrapper, InceptionUpgradeContainer> INCEPTION_TYPE = new UpgradeContainerType(
      InceptionUpgradeContainer::new
   );
   public static final UpgradeContainerType<StonecutterUpgradeWrapper, StonecutterUpgradeContainer> STONECUTTER_TYPE = new UpgradeContainerType(
      StonecutterUpgradeContainer::new
   );
   public static final UpgradeContainerType<JukeboxUpgradeWrapper, JukeboxUpgradeContainer> JUKEBOX_TYPE = new UpgradeContainerType(
      JukeboxUpgradeContainer::new
   );
   public static final UpgradeContainerType<JukeboxUpgradeWrapper, JukeboxUpgradeContainer> ADVANCED_JUKEBOX_TYPE = new UpgradeContainerType(
      JukeboxUpgradeContainer::new
   );
   public static final UpgradeContainerType<ToolSwapperUpgradeWrapper, ToolSwapperUpgradeContainer> TOOL_SWAPPER_TYPE = new UpgradeContainerType(
      ToolSwapperUpgradeContainer::new
   );
   public static final UpgradeContainerType<TankUpgradeWrapper, TankUpgradeContainer> TANK_TYPE = new UpgradeContainerType(TankUpgradeContainer::new);
   public static final UpgradeContainerType<BatteryUpgradeWrapper, BatteryUpgradeContainer> BATTERY_TYPE = new UpgradeContainerType(
      BatteryUpgradeContainer::new
   );
   public static final UpgradeContainerType<PumpUpgradeWrapper, PumpUpgradeContainer> PUMP_TYPE = new UpgradeContainerType(PumpUpgradeContainer::new);
   public static final UpgradeContainerType<PumpUpgradeWrapper, PumpUpgradeContainer> ADVANCED_PUMP_TYPE = new UpgradeContainerType(PumpUpgradeContainer::new);
   public static final UpgradeContainerType<XpPumpUpgradeWrapper, XpPumpUpgradeContainer> XP_PUMP_TYPE = new UpgradeContainerType(XpPumpUpgradeContainer::new);
   public static final UpgradeContainerType<AnvilUpgradeWrapper, AnvilUpgradeContainer> ANVIL_TYPE = new UpgradeContainerType(AnvilUpgradeContainer::new);
   public static final UpgradeContainerType<SmithingUpgradeWrapper, SmithingUpgradeContainer> SMITHING_TYPE = new UpgradeContainerType(
      SmithingUpgradeContainer::new
   );

   private ModItems() {
   }

   public static void registerHandlers() {
      ITEMS.register();
      ModDataComponents.register();
      CREATIVE_MODE_TABS.register();
      MENU_TYPES.register();
      ENTITY_TYPES.register();
      RECIPE_SERIALIZERS.register();
      LOOT_FUNCTION_TYPES.register();
      LOOT_CONDITION_TYPES.register();
      LOOT_MODIFIERS.register();
      registerContainers();
      registerCapabilities();
   }

   public static void registerContainers() {
      UpgradeContainerRegistry.register(PICKUP_UPGRADE.getId(), PICKUP_BASIC_TYPE);
      UpgradeContainerRegistry.register(ADVANCED_PICKUP_UPGRADE.getId(), PICKUP_ADVANCED_TYPE);
      UpgradeContainerRegistry.register(FILTER_UPGRADE.getId(), FilterUpgradeContainer.BASIC_TYPE);
      UpgradeContainerRegistry.register(ADVANCED_FILTER_UPGRADE.getId(), FilterUpgradeContainer.ADVANCED_TYPE);
      UpgradeContainerRegistry.register(MAGNET_UPGRADE.getId(), MAGNET_BASIC_TYPE);
      UpgradeContainerRegistry.register(ADVANCED_MAGNET_UPGRADE.getId(), MAGNET_ADVANCED_TYPE);
      UpgradeContainerRegistry.register(FEEDING_UPGRADE.getId(), FEEDING_TYPE);
      UpgradeContainerRegistry.register(ADVANCED_FEEDING_UPGRADE.getId(), ADVANCED_FEEDING_TYPE);
      UpgradeContainerRegistry.register(COMPACTING_UPGRADE.getId(), COMPACTING_TYPE);
      UpgradeContainerRegistry.register(ADVANCED_COMPACTING_UPGRADE.getId(), ADVANCED_COMPACTING_TYPE);
      UpgradeContainerRegistry.register(VOID_UPGRADE.getId(), VOID_TYPE);
      UpgradeContainerRegistry.register(ADVANCED_VOID_UPGRADE.getId(), ADVANCED_VOID_TYPE);
      UpgradeContainerRegistry.register(RESTOCK_UPGRADE.getId(), RESTOCK_TYPE);
      UpgradeContainerRegistry.register(ADVANCED_RESTOCK_UPGRADE.getId(), ADVANCED_RESTOCK_TYPE);
      UpgradeContainerRegistry.register(DEPOSIT_UPGRADE.getId(), DEPOSIT_TYPE);
      UpgradeContainerRegistry.register(ADVANCED_DEPOSIT_UPGRADE.getId(), ADVANCED_DEPOSIT_TYPE);
      UpgradeContainerRegistry.register(REFILL_UPGRADE.getId(), REFILL_TYPE);
      UpgradeContainerRegistry.register(ADVANCED_REFILL_UPGRADE.getId(), ADVANCED_REFILL_TYPE);
      UpgradeContainerRegistry.register(SMELTING_UPGRADE.getId(), SMELTING_TYPE);
      UpgradeContainerRegistry.register(AUTO_SMELTING_UPGRADE.getId(), AUTO_SMELTING_TYPE);
      UpgradeContainerRegistry.register(SMOKING_UPGRADE.getId(), SMOKING_TYPE);
      UpgradeContainerRegistry.register(AUTO_SMOKING_UPGRADE.getId(), AUTO_SMOKING_TYPE);
      UpgradeContainerRegistry.register(BLASTING_UPGRADE.getId(), BLASTING_TYPE);
      UpgradeContainerRegistry.register(AUTO_BLASTING_UPGRADE.getId(), AUTO_BLASTING_TYPE);
      UpgradeContainerRegistry.register(CRAFTING_UPGRADE.getId(), CRAFTING_TYPE);
      UpgradeContainerRegistry.register(INCEPTION_UPGRADE.getId(), INCEPTION_TYPE);
      UpgradeContainerRegistry.register(STONECUTTER_UPGRADE.getId(), STONECUTTER_TYPE);
      UpgradeContainerRegistry.register(JUKEBOX_UPGRADE.getId(), JUKEBOX_TYPE);
      UpgradeContainerRegistry.register(ADVANCED_JUKEBOX_UPGRADE.getId(), ADVANCED_JUKEBOX_TYPE);
      UpgradeContainerRegistry.register(ADVANCED_TOOL_SWAPPER_UPGRADE.getId(), TOOL_SWAPPER_TYPE);
      UpgradeContainerRegistry.register(TANK_UPGRADE.getId(), TANK_TYPE);
      UpgradeContainerRegistry.register(BATTERY_UPGRADE.getId(), BATTERY_TYPE);
      UpgradeContainerRegistry.register(PUMP_UPGRADE.getId(), PUMP_TYPE);
      UpgradeContainerRegistry.register(ADVANCED_PUMP_UPGRADE.getId(), ADVANCED_PUMP_TYPE);
      UpgradeContainerRegistry.register(XP_PUMP_UPGRADE.getId(), XP_PUMP_TYPE);
      UpgradeContainerRegistry.register(ANVIL_UPGRADE.getId(), ANVIL_TYPE);
      UpgradeContainerRegistry.register(SMITHING_UPGRADE.getId(), SMITHING_TYPE);
   }

   public static void registerDispenseBehavior() {
      BACKPACKS.forEach(backpack -> class_2315.method_10009((class_1935)backpack.get(), new ModItems.BackpackDispenseBehavior()));
   }

   public static void registerCauldronInteractions() {
      BACKPACKS.forEach(backpack -> class_5620.field_27776.comp_1982().put((class_1792)backpack.get(), new ModItems.BackpackCauldronInteraction()));
   }

   private static void registerCapabilities() {
      BackpackItem[] backpacks = BACKPACKS.stream().map(Supplier::get).toArray(BackpackItem[]::new);
      ItemStorage.ITEM.registerForItems((stack, ctx) -> {
         IBackpackWrapper backpackWrapper = BackpackWrapper.fromStack(stack);
         return (Storage)(backpackWrapper.getContentsUuid().isEmpty() ? EmptyItemHandler.INSTANCE : backpackWrapper.getInventoryForInputOutput());
      }, backpacks);
      FluidStorage.ITEM
         .registerForItems(
            (stack, ctx) -> Boolean.FALSE.equals(Config.SERVER.itemFluidHandlerEnabled.get())
               ? null
               : (Storage)BackpackWrapper.fromStack(stack).getItemFluidHandler().orElse(null),
            backpacks
         );
      EnergyStorage.ITEM.registerForItems((stack, ctx) -> (EnergyStorage)BackpackWrapper.fromStack(stack).getEnergyStorage().orElse(null), backpacks);
   }

   private static class BackpackCauldronInteraction implements class_5620 {
      private static boolean hasDefaultColor(IStorageWrapper wrapper) {
         return wrapper.getAccentColor() == -10342886 && wrapper.getMainColor() == -3382982;
      }

      public class_9062 interact(class_2680 state, class_1937 level, class_2338 pos, class_1657 player, class_1268 hand, class_1799 stack) {
         IBackpackWrapper backpackWrapper = BackpackWrapper.fromStack(stack);
         if (hasDefaultColor(backpackWrapper)) {
            return class_9062.field_47733;
         } else {
            if (!level.field_9236) {
               backpackWrapper.setColors(-3382982, -10342886);
               class_5556.method_31650(state, level, pos);
            }

            return class_9062.method_55644(level.field_9236);
         }
      }
   }

   private static class BackpackDispenseBehavior extends class_2969 {
      protected class_1799 method_10135(class_2342 source, class_1799 stack) {
         this.method_27955(false);
         if (stack.method_7909() instanceof BackpackItem backpackItem) {
            class_2350 dispenserDirection = (class_2350)source.comp_1969().method_11654(class_2315.field_10918);
            class_2338 blockpos = source.comp_1968().method_10093(dispenserDirection);
            class_2350 against = source.comp_1967().method_22347(blockpos.method_10074()) ? dispenserDirection.method_10153() : class_2350.field_11036;
            this.method_27955(
               backpackItem.tryPlace(
                     null,
                     dispenserDirection.method_10166() == class_2351.field_11052 ? class_2350.field_11043 : dispenserDirection.method_10153(),
                     new class_2968(source.comp_1967(), blockpos, dispenserDirection, stack, against)
                  )
                  .method_23665()
            );
         }

         return stack;
      }
   }
}
