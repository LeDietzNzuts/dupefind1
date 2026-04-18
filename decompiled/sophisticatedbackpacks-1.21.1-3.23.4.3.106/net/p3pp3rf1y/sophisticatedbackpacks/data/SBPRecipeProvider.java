package net.p3pp3rf1y.sophisticatedbackpacks.data;

import earth.terrarium.chipped.common.registry.ModBlocks;
import java.util.concurrent.CompletableFuture;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.fabricmc.fabric.api.resource.conditions.v1.ResourceCondition;
import net.fabricmc.fabric.api.resource.conditions.v1.ResourceConditions;
import net.fabricmc.fabric.api.tag.convention.v2.ConventionalItemTags;
import net.minecraft.class_175;
import net.minecraft.class_1792;
import net.minecraft.class_1802;
import net.minecraft.class_1856;
import net.minecraft.class_1935;
import net.minecraft.class_2073;
import net.minecraft.class_2246;
import net.minecraft.class_2248;
import net.minecraft.class_2456;
import net.minecraft.class_3489;
import net.minecraft.class_8790;
import net.minecraft.class_2073.class_2074;
import net.minecraft.class_7225.class_7874;
import net.p3pp3rf1y.sophisticatedbackpacks.SophisticatedBackpacks;
import net.p3pp3rf1y.sophisticatedbackpacks.compat.chipped.ChippedCompat;
import net.p3pp3rf1y.sophisticatedbackpacks.crafting.BackpackDyeRecipe;
import net.p3pp3rf1y.sophisticatedbackpacks.crafting.BackpackUpgradeRecipe;
import net.p3pp3rf1y.sophisticatedbackpacks.crafting.BasicBackpackRecipe;
import net.p3pp3rf1y.sophisticatedbackpacks.crafting.SmithingBackpackUpgradeRecipe;
import net.p3pp3rf1y.sophisticatedbackpacks.crafting.SmithingBackpackUpgradeRecipeBuilder;
import net.p3pp3rf1y.sophisticatedbackpacks.init.ModItems;
import net.p3pp3rf1y.sophisticatedcore.compat.chipped.BlockTransformationUpgradeItem;
import net.p3pp3rf1y.sophisticatedcore.crafting.ShapeBasedRecipeBuilder;
import net.p3pp3rf1y.sophisticatedcore.crafting.UpgradeNextTierRecipe;
import net.p3pp3rf1y.sophisticatedcore.util.RegistryHelper;

public class SBPRecipeProvider extends FabricRecipeProvider {
   private static final String HAS_UPGRADE_BASE = "has_upgrade_base";
   private static final String HAS_SMELTING_UPGRADE = "has_smelting_upgrade";

   public SBPRecipeProvider(FabricDataOutput output, CompletableFuture<class_7874> registriesFuture) {
      super(output, registriesFuture);
   }

   public void method_10419(class_8790 recipeOutput) {
      ShapeBasedRecipeBuilder.shaped((class_1935)ModItems.BACKPACK.get(), BasicBackpackRecipe::new)
         .pattern("SLS")
         .pattern("SCS")
         .pattern("LLL")
         .define('L', ConventionalItemTags.LEATHERS)
         .define('C', ConventionalItemTags.WOODEN_CHESTS)
         .define('S', ConventionalItemTags.STRINGS)
         .unlockedBy("has_leather", hasLeather())
         .method_10431(recipeOutput);
      class_2456.method_10476(BackpackDyeRecipe::new).method_53820(recipeOutput, SophisticatedBackpacks.getRegistryName("backpack_dye"));
      ShapeBasedRecipeBuilder.shaped((class_1935)ModItems.DIAMOND_BACKPACK.get(), BackpackUpgradeRecipe::new)
         .pattern("DDD")
         .pattern("DBD")
         .pattern("DDD")
         .define('D', ConventionalItemTags.DIAMOND_GEMS)
         .define('B', (class_1935)ModItems.GOLD_BACKPACK.get())
         .unlockedBy("has_gold_backpack", method_10426((class_1935)ModItems.GOLD_BACKPACK.get()))
         .method_10431(recipeOutput);
      ShapeBasedRecipeBuilder.shaped((class_1935)ModItems.GOLD_BACKPACK.get(), BackpackUpgradeRecipe::new)
         .pattern("GGG")
         .pattern("GBG")
         .pattern("GGG")
         .define('G', ConventionalItemTags.GOLD_INGOTS)
         .define('B', (class_1935)ModItems.IRON_BACKPACK.get())
         .unlockedBy("has_iron_backpack", method_10426((class_1935)ModItems.IRON_BACKPACK.get()))
         .method_10431(recipeOutput);
      ShapeBasedRecipeBuilder.shaped((class_1935)ModItems.IRON_BACKPACK.get(), BackpackUpgradeRecipe::new)
         .pattern("III")
         .pattern("IBI")
         .pattern("III")
         .define('I', ConventionalItemTags.IRON_INGOTS)
         .define('B', (class_1935)ModItems.BACKPACK.get())
         .unlockedBy("has_backpack", method_10426((class_1935)ModItems.BACKPACK.get()))
         .method_10431(recipeOutput);
      ShapeBasedRecipeBuilder.shaped((class_1935)ModItems.IRON_BACKPACK.get(), BackpackUpgradeRecipe::new)
         .pattern(" I ")
         .pattern("IBI")
         .pattern(" I ")
         .define('I', ConventionalItemTags.IRON_INGOTS)
         .define('B', (class_1935)ModItems.COPPER_BACKPACK.get())
         .unlockedBy("has_copper_backpack", method_10426((class_1935)ModItems.COPPER_BACKPACK.get()))
         .method_17972(recipeOutput, SophisticatedBackpacks.getRL("iron_backpack_from_copper"));
      ShapeBasedRecipeBuilder.shaped((class_1935)ModItems.COPPER_BACKPACK.get(), BackpackUpgradeRecipe::new)
         .pattern("CCC")
         .pattern("CBC")
         .pattern("CCC")
         .define('C', ConventionalItemTags.COPPER_INGOTS)
         .define('B', (class_1935)ModItems.BACKPACK.get())
         .unlockedBy("has_backpack", method_10426((class_1935)ModItems.BACKPACK.get()))
         .method_10431(recipeOutput);
      ShapeBasedRecipeBuilder.shaped((class_1935)ModItems.PICKUP_UPGRADE.get())
         .pattern(" P ")
         .pattern("SBS")
         .pattern("RRR")
         .define('B', (class_1935)ModItems.UPGRADE_BASE.get())
         .define('R', ConventionalItemTags.REDSTONE_DUSTS)
         .define('S', ConventionalItemTags.STRINGS)
         .define('P', class_2246.field_10615)
         .unlockedBy("has_upgrade_base", method_10426((class_1935)ModItems.UPGRADE_BASE.get()))
         .method_10431(recipeOutput);
      ShapeBasedRecipeBuilder.shaped((class_1935)ModItems.UPGRADE_BASE.get())
         .pattern("SIS")
         .pattern("ILI")
         .pattern("SIS")
         .define('L', ConventionalItemTags.LEATHERS)
         .define('I', ConventionalItemTags.IRON_INGOTS)
         .define('S', ConventionalItemTags.STRINGS)
         .unlockedBy("has_leather", hasLeather())
         .method_10431(recipeOutput);
      ShapeBasedRecipeBuilder.shaped((class_1935)ModItems.ADVANCED_PICKUP_UPGRADE.get(), UpgradeNextTierRecipe::new)
         .pattern(" D ")
         .pattern("GPG")
         .pattern("RRR")
         .define('D', ConventionalItemTags.DIAMOND_GEMS)
         .define('G', ConventionalItemTags.GOLD_INGOTS)
         .define('R', ConventionalItemTags.REDSTONE_DUSTS)
         .define('P', (class_1935)ModItems.PICKUP_UPGRADE.get())
         .unlockedBy("has_pickup_upgrade", method_10426((class_1935)ModItems.PICKUP_UPGRADE.get()))
         .method_10431(recipeOutput);
      ShapeBasedRecipeBuilder.shaped((class_1935)ModItems.FILTER_UPGRADE.get())
         .pattern("RSR")
         .pattern("SBS")
         .pattern("RSR")
         .define('B', (class_1935)ModItems.UPGRADE_BASE.get())
         .define('R', ConventionalItemTags.REDSTONE_DUSTS)
         .define('S', ConventionalItemTags.STRINGS)
         .unlockedBy("has_upgrade_base", method_10426((class_1935)ModItems.UPGRADE_BASE.get()))
         .method_10431(recipeOutput);
      ShapeBasedRecipeBuilder.shaped((class_1935)ModItems.ADVANCED_FILTER_UPGRADE.get(), UpgradeNextTierRecipe::new)
         .pattern("GPG")
         .pattern("RRR")
         .define('G', ConventionalItemTags.GOLD_INGOTS)
         .define('R', ConventionalItemTags.REDSTONE_DUSTS)
         .define('P', (class_1935)ModItems.FILTER_UPGRADE.get())
         .unlockedBy("has_filter_upgrade", method_10426((class_1935)ModItems.FILTER_UPGRADE.get()))
         .method_10431(recipeOutput);
      ShapeBasedRecipeBuilder.shaped((class_1935)ModItems.MAGNET_UPGRADE.get(), UpgradeNextTierRecipe::new)
         .pattern("EIE")
         .pattern("IPI")
         .pattern("R L")
         .define('E', ConventionalItemTags.ENDER_PEARLS)
         .define('I', ConventionalItemTags.IRON_INGOTS)
         .define('R', ConventionalItemTags.REDSTONE_DUSTS)
         .define('L', ConventionalItemTags.LAPIS_GEMS)
         .define('P', (class_1935)ModItems.PICKUP_UPGRADE.get())
         .unlockedBy("has_pickup_upgrade", method_10426((class_1935)ModItems.PICKUP_UPGRADE.get()))
         .method_10431(recipeOutput);
      ShapeBasedRecipeBuilder.shaped((class_1935)ModItems.ADVANCED_MAGNET_UPGRADE.get(), UpgradeNextTierRecipe::new)
         .pattern("EIE")
         .pattern("IPI")
         .pattern("R L")
         .define('E', ConventionalItemTags.ENDER_PEARLS)
         .define('I', ConventionalItemTags.IRON_INGOTS)
         .define('R', ConventionalItemTags.REDSTONE_DUSTS)
         .define('L', ConventionalItemTags.LAPIS_GEMS)
         .define('P', (class_1935)ModItems.ADVANCED_PICKUP_UPGRADE.get())
         .unlockedBy("has_advanced_pickup_upgrade", method_10426((class_1935)ModItems.ADVANCED_PICKUP_UPGRADE.get()))
         .method_10431(recipeOutput);
      ShapeBasedRecipeBuilder.shaped((class_1935)ModItems.ADVANCED_MAGNET_UPGRADE.get(), UpgradeNextTierRecipe::new)
         .pattern(" D ")
         .pattern("GMG")
         .pattern("RRR")
         .define('D', ConventionalItemTags.DIAMOND_GEMS)
         .define('G', ConventionalItemTags.GOLD_INGOTS)
         .define('R', ConventionalItemTags.REDSTONE_DUSTS)
         .define('M', (class_1935)ModItems.MAGNET_UPGRADE.get())
         .unlockedBy("has_magnet_upgrade", method_10426((class_1935)ModItems.MAGNET_UPGRADE.get()))
         .method_17972(recipeOutput, SophisticatedBackpacks.getRL("advanced_magnet_upgrade_from_basic"));
      ShapeBasedRecipeBuilder.shaped((class_1935)ModItems.FEEDING_UPGRADE.get())
         .pattern(" C ")
         .pattern("ABM")
         .pattern(" E ")
         .define('B', (class_1935)ModItems.UPGRADE_BASE.get())
         .define('C', class_1802.field_8071)
         .define('A', class_1802.field_8463)
         .define('M', class_1802.field_8597)
         .define('E', ConventionalItemTags.ENDER_PEARLS)
         .unlockedBy("has_upgrade_base", method_10426((class_1935)ModItems.UPGRADE_BASE.get()))
         .method_10431(recipeOutput);
      ShapeBasedRecipeBuilder.shaped((class_1935)ModItems.COMPACTING_UPGRADE.get())
         .pattern("IPI")
         .pattern("PBP")
         .pattern("RPR")
         .define('B', (class_1935)ModItems.UPGRADE_BASE.get())
         .define('I', ConventionalItemTags.IRON_INGOTS)
         .define('P', class_1802.field_8249)
         .define('R', ConventionalItemTags.REDSTONE_DUSTS)
         .unlockedBy("has_upgrade_base", method_10426((class_1935)ModItems.UPGRADE_BASE.get()))
         .method_10431(recipeOutput);
      ShapeBasedRecipeBuilder.shaped((class_1935)ModItems.ADVANCED_COMPACTING_UPGRADE.get(), UpgradeNextTierRecipe::new)
         .pattern(" D ")
         .pattern("GCG")
         .pattern("RRR")
         .define('D', ConventionalItemTags.DIAMOND_GEMS)
         .define('G', ConventionalItemTags.GOLD_INGOTS)
         .define('R', ConventionalItemTags.REDSTONE_DUSTS)
         .define('C', (class_1935)ModItems.COMPACTING_UPGRADE.get())
         .unlockedBy("has_compacting_upgrade", method_10426((class_1935)ModItems.COMPACTING_UPGRADE.get()))
         .method_10431(recipeOutput);
      ShapeBasedRecipeBuilder.shaped((class_1935)ModItems.VOID_UPGRADE.get())
         .pattern(" E ")
         .pattern("OBO")
         .pattern("ROR")
         .define('B', (class_1935)ModItems.UPGRADE_BASE.get())
         .define('E', ConventionalItemTags.ENDER_PEARLS)
         .define('O', ConventionalItemTags.OBSIDIANS)
         .define('R', ConventionalItemTags.REDSTONE_DUSTS)
         .unlockedBy("has_upgrade_base", method_10426((class_1935)ModItems.UPGRADE_BASE.get()))
         .method_10431(recipeOutput);
      ShapeBasedRecipeBuilder.shaped((class_1935)ModItems.ADVANCED_VOID_UPGRADE.get(), UpgradeNextTierRecipe::new)
         .pattern(" D ")
         .pattern("GVG")
         .pattern("RRR")
         .define('D', ConventionalItemTags.DIAMOND_GEMS)
         .define('G', ConventionalItemTags.GOLD_INGOTS)
         .define('R', ConventionalItemTags.REDSTONE_DUSTS)
         .define('V', (class_1935)ModItems.VOID_UPGRADE.get())
         .unlockedBy("has_void_upgrade", method_10426((class_1935)ModItems.VOID_UPGRADE.get()))
         .method_10431(recipeOutput);
      ShapeBasedRecipeBuilder.shaped((class_1935)ModItems.RESTOCK_UPGRADE.get())
         .pattern(" P ")
         .pattern("IBI")
         .pattern("RCR")
         .define('B', (class_1935)ModItems.UPGRADE_BASE.get())
         .define('C', ConventionalItemTags.WOODEN_CHESTS)
         .define('I', ConventionalItemTags.IRON_INGOTS)
         .define('R', ConventionalItemTags.REDSTONE_DUSTS)
         .define('P', class_1802.field_8105)
         .unlockedBy("has_upgrade_base", method_10426((class_1935)ModItems.UPGRADE_BASE.get()))
         .method_10431(recipeOutput);
      ShapeBasedRecipeBuilder.shaped((class_1935)ModItems.ADVANCED_RESTOCK_UPGRADE.get(), UpgradeNextTierRecipe::new)
         .pattern(" D ")
         .pattern("GVG")
         .pattern("RRR")
         .define('D', ConventionalItemTags.DIAMOND_GEMS)
         .define('G', ConventionalItemTags.GOLD_INGOTS)
         .define('R', ConventionalItemTags.REDSTONE_DUSTS)
         .define('V', (class_1935)ModItems.RESTOCK_UPGRADE.get())
         .unlockedBy("has_restock_upgrade", method_10426((class_1935)ModItems.RESTOCK_UPGRADE.get()))
         .method_10431(recipeOutput);
      ShapeBasedRecipeBuilder.shaped((class_1935)ModItems.DEPOSIT_UPGRADE.get())
         .pattern(" P ")
         .pattern("IBI")
         .pattern("RCR")
         .define('B', (class_1935)ModItems.UPGRADE_BASE.get())
         .define('C', ConventionalItemTags.WOODEN_CHESTS)
         .define('I', ConventionalItemTags.IRON_INGOTS)
         .define('R', ConventionalItemTags.REDSTONE_DUSTS)
         .define('P', class_1802.field_8249)
         .unlockedBy("has_upgrade_base", method_10426((class_1935)ModItems.UPGRADE_BASE.get()))
         .method_10431(recipeOutput);
      ShapeBasedRecipeBuilder.shaped((class_1935)ModItems.ADVANCED_DEPOSIT_UPGRADE.get(), UpgradeNextTierRecipe::new)
         .pattern(" D ")
         .pattern("GVG")
         .pattern("RRR")
         .define('D', ConventionalItemTags.DIAMOND_GEMS)
         .define('G', ConventionalItemTags.GOLD_INGOTS)
         .define('R', ConventionalItemTags.REDSTONE_DUSTS)
         .define('V', (class_1935)ModItems.DEPOSIT_UPGRADE.get())
         .unlockedBy("has_deposit_upgrade", method_10426((class_1935)ModItems.DEPOSIT_UPGRADE.get()))
         .method_10431(recipeOutput);
      ShapeBasedRecipeBuilder.shaped((class_1935)ModItems.REFILL_UPGRADE.get())
         .pattern(" E ")
         .pattern("IBI")
         .pattern("RCR")
         .define('B', (class_1935)ModItems.UPGRADE_BASE.get())
         .define('C', ConventionalItemTags.WOODEN_CHESTS)
         .define('I', ConventionalItemTags.IRON_INGOTS)
         .define('R', ConventionalItemTags.REDSTONE_DUSTS)
         .define('E', ConventionalItemTags.ENDER_PEARLS)
         .unlockedBy("has_upgrade_base", method_10426((class_1935)ModItems.UPGRADE_BASE.get()))
         .method_10431(recipeOutput);
      ShapeBasedRecipeBuilder.shaped((class_1935)ModItems.ADVANCED_REFILL_UPGRADE.get(), UpgradeNextTierRecipe::new)
         .pattern(" D ")
         .pattern("GFG")
         .pattern("RRR")
         .define('D', ConventionalItemTags.DIAMOND_GEMS)
         .define('G', ConventionalItemTags.GOLD_INGOTS)
         .define('R', ConventionalItemTags.REDSTONE_DUSTS)
         .define('F', (class_1935)ModItems.REFILL_UPGRADE.get())
         .unlockedBy("has_refill_upgrade", method_10426((class_1935)ModItems.REFILL_UPGRADE.get()))
         .method_10431(recipeOutput);
      ShapeBasedRecipeBuilder.shaped((class_1935)ModItems.INCEPTION_UPGRADE.get())
         .pattern("ESE")
         .pattern("DBD")
         .pattern("EDE")
         .define('B', (class_1935)ModItems.UPGRADE_BASE.get())
         .define('S', class_1802.field_8137)
         .define('D', ConventionalItemTags.DIAMOND_GEMS)
         .define('E', class_1802.field_8449)
         .unlockedBy("has_upgrade_base", method_10426((class_1935)ModItems.UPGRADE_BASE.get()))
         .method_10431(recipeOutput);
      ShapeBasedRecipeBuilder.shaped((class_1935)ModItems.EVERLASTING_UPGRADE.get())
         .pattern("CSC")
         .pattern("SBS")
         .pattern("CSC")
         .define('B', (class_1935)ModItems.UPGRADE_BASE.get())
         .define('S', class_1802.field_8137)
         .define('C', class_1802.field_8301)
         .unlockedBy("has_upgrade_base", method_10426((class_1935)ModItems.UPGRADE_BASE.get()))
         .method_10431(recipeOutput);
      ShapeBasedRecipeBuilder.shaped((class_1935)ModItems.SMELTING_UPGRADE.get())
         .pattern("RIR")
         .pattern("IBI")
         .pattern("RFR")
         .define('B', (class_1935)ModItems.UPGRADE_BASE.get())
         .define('R', ConventionalItemTags.REDSTONE_DUSTS)
         .define('I', ConventionalItemTags.IRON_INGOTS)
         .define('F', class_1802.field_8732)
         .unlockedBy("has_upgrade_base", method_10426((class_1935)ModItems.UPGRADE_BASE.get()))
         .method_10431(recipeOutput);
      ShapeBasedRecipeBuilder.shaped((class_1935)ModItems.AUTO_SMELTING_UPGRADE.get(), UpgradeNextTierRecipe::new)
         .pattern("DHD")
         .pattern("RSH")
         .pattern("GHG")
         .define('D', ConventionalItemTags.DIAMOND_GEMS)
         .define('G', ConventionalItemTags.GOLD_INGOTS)
         .define('R', ConventionalItemTags.REDSTONE_DUSTS)
         .define('H', class_1802.field_8239)
         .define('S', (class_1935)ModItems.SMELTING_UPGRADE.get())
         .unlockedBy("has_smelting_upgrade", method_10426((class_1935)ModItems.SMELTING_UPGRADE.get()))
         .method_10431(recipeOutput);
      ShapeBasedRecipeBuilder.shaped((class_1935)ModItems.CRAFTING_UPGRADE.get())
         .pattern(" T ")
         .pattern("IBI")
         .pattern(" C ")
         .define('B', (class_1935)ModItems.UPGRADE_BASE.get())
         .define('C', ConventionalItemTags.CHESTS)
         .define('I', ConventionalItemTags.IRON_INGOTS)
         .define('T', class_1802.field_8465)
         .unlockedBy("has_upgrade_base", method_10426((class_1935)ModItems.UPGRADE_BASE.get()))
         .method_10431(recipeOutput);
      ShapeBasedRecipeBuilder.shaped((class_1935)ModItems.STONECUTTER_UPGRADE.get())
         .pattern(" S ")
         .pattern("IBI")
         .pattern(" R ")
         .define('B', (class_1935)ModItems.UPGRADE_BASE.get())
         .define('R', ConventionalItemTags.REDSTONE_DUSTS)
         .define('I', ConventionalItemTags.IRON_INGOTS)
         .define('S', class_1802.field_16305)
         .unlockedBy("has_upgrade_base", method_10426((class_1935)ModItems.UPGRADE_BASE.get()))
         .method_10431(recipeOutput);
      ShapeBasedRecipeBuilder.shaped((class_1935)ModItems.STACK_UPGRADE_STARTER_TIER.get())
         .pattern("CCC")
         .pattern("CBC")
         .pattern("CCC")
         .define('B', (class_1935)ModItems.UPGRADE_BASE.get())
         .define('C', ConventionalItemTags.STORAGE_BLOCKS_COPPER)
         .unlockedBy("has_upgrade_base", method_10426((class_1935)ModItems.UPGRADE_BASE.get()))
         .method_10431(recipeOutput);
      ShapeBasedRecipeBuilder.shaped((class_1935)ModItems.STACK_UPGRADE_TIER_1.get())
         .pattern("III")
         .pattern("IBI")
         .pattern("III")
         .define('B', (class_1935)ModItems.UPGRADE_BASE.get())
         .define('I', ConventionalItemTags.STORAGE_BLOCKS_IRON)
         .unlockedBy("has_upgrade_base", method_10426((class_1935)ModItems.UPGRADE_BASE.get()))
         .method_10431(recipeOutput);
      ShapeBasedRecipeBuilder.shaped((class_1935)ModItems.STACK_UPGRADE_TIER_1.get())
         .pattern(" I ")
         .pattern("ISI")
         .pattern(" I ")
         .define('S', (class_1935)ModItems.STACK_UPGRADE_STARTER_TIER.get())
         .define('I', ConventionalItemTags.STORAGE_BLOCKS_IRON)
         .unlockedBy("has_stack_upgrade_starter_tier", method_10426((class_1935)ModItems.STACK_UPGRADE_STARTER_TIER.get()))
         .method_17972(recipeOutput, SophisticatedBackpacks.getRL("stack_upgrade_tier_1_from_starter"));
      ShapeBasedRecipeBuilder.shaped((class_1935)ModItems.STACK_UPGRADE_TIER_2.get())
         .pattern("GGG")
         .pattern("GSG")
         .pattern("GGG")
         .define('S', (class_1935)ModItems.STACK_UPGRADE_TIER_1.get())
         .define('G', ConventionalItemTags.STORAGE_BLOCKS_GOLD)
         .unlockedBy("has_stack_upgrade_tier_1", method_10426((class_1935)ModItems.STACK_UPGRADE_TIER_1.get()))
         .method_10431(recipeOutput);
      ShapeBasedRecipeBuilder.shaped((class_1935)ModItems.STACK_UPGRADE_TIER_3.get())
         .pattern("DDD")
         .pattern("DSD")
         .pattern("DDD")
         .define('S', (class_1935)ModItems.STACK_UPGRADE_TIER_2.get())
         .define('D', ConventionalItemTags.STORAGE_BLOCKS_DIAMOND)
         .unlockedBy("has_stack_upgrade_tier_2", method_10426((class_1935)ModItems.STACK_UPGRADE_TIER_2.get()))
         .method_10431(recipeOutput);
      ShapeBasedRecipeBuilder.shaped((class_1935)ModItems.STACK_UPGRADE_TIER_4.get())
         .pattern("NNN")
         .pattern("NSN")
         .pattern("NNN")
         .define('S', (class_1935)ModItems.STACK_UPGRADE_TIER_3.get())
         .define('N', ConventionalItemTags.STORAGE_BLOCKS_NETHERITE)
         .unlockedBy("has_stack_upgrade_tier_3", method_10426((class_1935)ModItems.STACK_UPGRADE_TIER_3.get()))
         .method_10431(recipeOutput);
      ShapeBasedRecipeBuilder.shaped((class_1935)ModItems.STACK_UPGRADE_OMEGA_TIER.get())
         .pattern("SSS")
         .pattern("SSS")
         .pattern("SSS")
         .define('S', (class_1935)ModItems.STACK_UPGRADE_TIER_4.get())
         .unlockedBy("has_stack_upgrade_tier_4", method_10426((class_1935)ModItems.STACK_UPGRADE_TIER_4.get()))
         .method_10431(recipeOutput);
      ShapeBasedRecipeBuilder.shaped((class_1935)ModItems.STACK_DOWNGRADE_TIER_1.get())
         .pattern("SFS")
         .pattern("SBS")
         .pattern("FSF")
         .define('S', class_1802.field_8600)
         .define('F', class_1802.field_8145)
         .define('B', (class_1935)ModItems.UPGRADE_BASE.get())
         .unlockedBy("has_upgrade_base", method_10426((class_1935)ModItems.UPGRADE_BASE.get()))
         .method_10431(recipeOutput);
      ShapeBasedRecipeBuilder.shaped((class_1935)ModItems.STACK_DOWNGRADE_TIER_2.get())
         .pattern("FSF")
         .pattern("SBS")
         .pattern("FSF")
         .define('S', class_1802.field_8600)
         .define('F', class_1802.field_8145)
         .define('B', (class_1935)ModItems.UPGRADE_BASE.get())
         .unlockedBy("has_upgrade_base", method_10426((class_1935)ModItems.UPGRADE_BASE.get()))
         .method_10431(recipeOutput);
      ShapeBasedRecipeBuilder.shaped((class_1935)ModItems.STACK_DOWNGRADE_TIER_3.get())
         .pattern("SFS")
         .pattern("FBF")
         .pattern("FSF")
         .define('S', class_1802.field_8600)
         .define('F', class_1802.field_8145)
         .define('B', (class_1935)ModItems.UPGRADE_BASE.get())
         .unlockedBy("has_upgrade_base", method_10426((class_1935)ModItems.UPGRADE_BASE.get()))
         .method_10431(recipeOutput);
      ShapeBasedRecipeBuilder.shaped((class_1935)ModItems.JUKEBOX_UPGRADE.get())
         .pattern(" J ")
         .pattern("IBI")
         .pattern(" R ")
         .define('B', (class_1935)ModItems.UPGRADE_BASE.get())
         .define('R', ConventionalItemTags.REDSTONE_DUSTS)
         .define('I', ConventionalItemTags.IRON_INGOTS)
         .define('J', class_1802.field_8565)
         .unlockedBy("has_upgrade_base", method_10426((class_1935)ModItems.UPGRADE_BASE.get()))
         .method_10431(recipeOutput);
      ShapeBasedRecipeBuilder.shaped((class_1935)ModItems.ADVANCED_JUKEBOX_UPGRADE.get())
         .pattern(" D ")
         .pattern("GJG")
         .pattern("RRR")
         .define('D', ConventionalItemTags.DIAMOND_GEMS)
         .define('G', ConventionalItemTags.GOLD_INGOTS)
         .define('R', ConventionalItemTags.REDSTONE_DUSTS)
         .define('J', (class_1935)ModItems.JUKEBOX_UPGRADE.get())
         .unlockedBy("has_jukebox_upgrade", method_10426((class_1935)ModItems.JUKEBOX_UPGRADE.get()))
         .method_10431(recipeOutput);
      ShapeBasedRecipeBuilder.shaped((class_1935)ModItems.TOOL_SWAPPER_UPGRADE.get())
         .pattern("RWR")
         .pattern("PBA")
         .pattern("ISI")
         .define('B', (class_1935)ModItems.UPGRADE_BASE.get())
         .define('S', class_1802.field_8876)
         .define('P', class_1802.field_8647)
         .define('A', class_1802.field_8406)
         .define('W', class_1802.field_8091)
         .define('I', ConventionalItemTags.IRON_INGOTS)
         .define('R', ConventionalItemTags.REDSTONE_DUSTS)
         .unlockedBy("has_upgrade_base", method_10426((class_1935)ModItems.UPGRADE_BASE.get()))
         .method_10431(recipeOutput);
      ShapeBasedRecipeBuilder.shaped((class_1935)ModItems.ADVANCED_TOOL_SWAPPER_UPGRADE.get(), UpgradeNextTierRecipe::new)
         .pattern(" D ")
         .pattern("GVG")
         .pattern("RRR")
         .define('D', ConventionalItemTags.DIAMOND_GEMS)
         .define('G', ConventionalItemTags.GOLD_INGOTS)
         .define('R', ConventionalItemTags.REDSTONE_DUSTS)
         .define('V', (class_1935)ModItems.TOOL_SWAPPER_UPGRADE.get())
         .unlockedBy("has_tool_swapper_upgrade", method_10426((class_1935)ModItems.TOOL_SWAPPER_UPGRADE.get()))
         .method_10431(recipeOutput);
      ShapeBasedRecipeBuilder.shaped((class_1935)ModItems.TANK_UPGRADE.get(), UpgradeNextTierRecipe::new)
         .pattern("GGG")
         .pattern("GBG")
         .pattern("GGG")
         .define('G', ConventionalItemTags.GLASS_BLOCKS)
         .define('B', (class_1935)ModItems.UPGRADE_BASE.get())
         .unlockedBy("has_upgrade_base", method_10426((class_1935)ModItems.UPGRADE_BASE.get()))
         .method_10431(recipeOutput);
      ShapeBasedRecipeBuilder.shaped((class_1935)ModItems.ADVANCED_FEEDING_UPGRADE.get(), UpgradeNextTierRecipe::new)
         .pattern(" D ")
         .pattern("GVG")
         .pattern("RRR")
         .define('D', ConventionalItemTags.DIAMOND_GEMS)
         .define('G', ConventionalItemTags.GOLD_INGOTS)
         .define('R', ConventionalItemTags.REDSTONE_DUSTS)
         .define('V', (class_1935)ModItems.FEEDING_UPGRADE.get())
         .unlockedBy("has_feeding_upgrade", method_10426((class_1935)ModItems.FEEDING_UPGRADE.get()))
         .method_10431(recipeOutput);
      ShapeBasedRecipeBuilder.shaped((class_1935)ModItems.BATTERY_UPGRADE.get(), UpgradeNextTierRecipe::new)
         .pattern("GRG")
         .pattern("RBR")
         .pattern("GRG")
         .define('R', ConventionalItemTags.STORAGE_BLOCKS_REDSTONE)
         .define('G', ConventionalItemTags.GOLD_INGOTS)
         .define('B', (class_1935)ModItems.UPGRADE_BASE.get())
         .unlockedBy("has_upgrade_base", method_10426((class_1935)ModItems.UPGRADE_BASE.get()))
         .method_10431(recipeOutput);
      ShapeBasedRecipeBuilder.shaped((class_1935)ModItems.PUMP_UPGRADE.get(), UpgradeNextTierRecipe::new)
         .pattern("GUG")
         .pattern("PBS")
         .pattern("GUG")
         .define('U', class_1802.field_8550)
         .define('G', ConventionalItemTags.GLASS_BLOCKS)
         .define('P', class_1802.field_8249)
         .define('S', class_1802.field_8105)
         .define('B', (class_1935)ModItems.UPGRADE_BASE.get())
         .unlockedBy("has_upgrade_base", method_10426((class_1935)ModItems.UPGRADE_BASE.get()))
         .method_10431(recipeOutput);
      ShapeBasedRecipeBuilder.shaped((class_1935)ModItems.ADVANCED_PUMP_UPGRADE.get(), UpgradeNextTierRecipe::new)
         .pattern("DID")
         .pattern("GPG")
         .pattern("RRR")
         .define('I', class_1802.field_8357)
         .define('D', ConventionalItemTags.DIAMOND_GEMS)
         .define('G', ConventionalItemTags.GOLD_INGOTS)
         .define('R', ConventionalItemTags.REDSTONE_DUSTS)
         .define('P', (class_1935)ModItems.PUMP_UPGRADE.get())
         .unlockedBy("has_pump_upgrade", method_10426((class_1935)ModItems.PUMP_UPGRADE.get()))
         .method_10431(recipeOutput);
      ShapeBasedRecipeBuilder.shaped((class_1935)ModItems.XP_PUMP_UPGRADE.get())
         .pattern("RER")
         .pattern("CPC")
         .pattern("RER")
         .define('R', ConventionalItemTags.REDSTONE_DUSTS)
         .define('E', class_1802.field_8449)
         .define('C', class_1802.field_8287)
         .define('P', (class_1935)ModItems.ADVANCED_PUMP_UPGRADE.get())
         .unlockedBy("has_advanced_pump_upgrade", method_10426((class_1935)ModItems.ADVANCED_PUMP_UPGRADE.get()))
         .method_10431(recipeOutput);
      ShapeBasedRecipeBuilder.shaped((class_1935)ModItems.SMOKING_UPGRADE.get())
         .pattern("RIR")
         .pattern("IBI")
         .pattern("RSR")
         .define('B', (class_1935)ModItems.UPGRADE_BASE.get())
         .define('R', ConventionalItemTags.REDSTONE_DUSTS)
         .define('I', ConventionalItemTags.IRON_INGOTS)
         .define('S', class_1802.field_16309)
         .unlockedBy("has_upgrade_base", method_10426((class_1935)ModItems.UPGRADE_BASE.get()))
         .method_10431(recipeOutput);
      ShapeBasedRecipeBuilder.shaped((class_1935)ModItems.SMOKING_UPGRADE.get())
         .pattern(" L ")
         .pattern("LSL")
         .pattern(" L ")
         .define('S', (class_1935)ModItems.SMELTING_UPGRADE.get())
         .define('L', class_3489.field_15539)
         .unlockedBy("has_smelting_upgrade", method_10426((class_1935)ModItems.SMELTING_UPGRADE.get()))
         .method_17972(recipeOutput, SophisticatedBackpacks.getRL("smoking_upgrade_from_smelting_upgrade"));
      ShapeBasedRecipeBuilder.shaped((class_1935)ModItems.AUTO_SMOKING_UPGRADE.get(), UpgradeNextTierRecipe::new)
         .pattern("DHD")
         .pattern("RSH")
         .pattern("GHG")
         .define('D', ConventionalItemTags.DIAMOND_GEMS)
         .define('G', ConventionalItemTags.GOLD_INGOTS)
         .define('R', ConventionalItemTags.REDSTONE_DUSTS)
         .define('H', class_1802.field_8239)
         .define('S', (class_1935)ModItems.SMOKING_UPGRADE.get())
         .unlockedBy("has_smoking_upgrade", method_10426((class_1935)ModItems.SMOKING_UPGRADE.get()))
         .method_10431(recipeOutput);
      ShapeBasedRecipeBuilder.shaped((class_1935)ModItems.AUTO_SMOKING_UPGRADE.get())
         .pattern(" L ")
         .pattern("LSL")
         .pattern(" L ")
         .define('S', (class_1935)ModItems.AUTO_SMELTING_UPGRADE.get())
         .define('L', class_3489.field_15539)
         .unlockedBy("has_auto_smelting_upgrade", method_10426((class_1935)ModItems.AUTO_SMELTING_UPGRADE.get()))
         .method_17972(recipeOutput, SophisticatedBackpacks.getRL("auto_smoking_upgrade_from_auto_smelting_upgrade"));
      ShapeBasedRecipeBuilder.shaped((class_1935)ModItems.BLASTING_UPGRADE.get())
         .pattern("RIR")
         .pattern("IBI")
         .pattern("RFR")
         .define('B', (class_1935)ModItems.UPGRADE_BASE.get())
         .define('R', ConventionalItemTags.REDSTONE_DUSTS)
         .define('I', ConventionalItemTags.IRON_INGOTS)
         .define('F', class_1802.field_16306)
         .unlockedBy("has_upgrade_base", method_10426((class_1935)ModItems.UPGRADE_BASE.get()))
         .method_10431(recipeOutput);
      ShapeBasedRecipeBuilder.shaped((class_1935)ModItems.BLASTING_UPGRADE.get())
         .pattern("III")
         .pattern("ISI")
         .pattern("TTT")
         .define('S', (class_1935)ModItems.SMELTING_UPGRADE.get())
         .define('I', ConventionalItemTags.IRON_INGOTS)
         .define('T', class_1802.field_20389)
         .unlockedBy("has_smelting_upgrade", method_10426((class_1935)ModItems.SMELTING_UPGRADE.get()))
         .method_17972(recipeOutput, SophisticatedBackpacks.getRL("blasting_upgrade_from_smelting_upgrade"));
      ShapeBasedRecipeBuilder.shaped((class_1935)ModItems.AUTO_BLASTING_UPGRADE.get(), UpgradeNextTierRecipe::new)
         .pattern("DHD")
         .pattern("RSH")
         .pattern("GHG")
         .define('D', ConventionalItemTags.DIAMOND_GEMS)
         .define('G', ConventionalItemTags.GOLD_INGOTS)
         .define('R', ConventionalItemTags.REDSTONE_DUSTS)
         .define('H', class_1802.field_8239)
         .define('S', (class_1935)ModItems.BLASTING_UPGRADE.get())
         .unlockedBy("has_blasting_upgrade", method_10426((class_1935)ModItems.BLASTING_UPGRADE.get()))
         .method_10431(recipeOutput);
      ShapeBasedRecipeBuilder.shaped((class_1935)ModItems.AUTO_BLASTING_UPGRADE.get())
         .pattern("III")
         .pattern("ISI")
         .pattern("TTT")
         .define('S', (class_1935)ModItems.AUTO_SMELTING_UPGRADE.get())
         .define('I', ConventionalItemTags.IRON_INGOTS)
         .define('T', class_1802.field_20389)
         .unlockedBy("has_auto_smelting_upgrade", method_10426((class_1935)ModItems.AUTO_SMELTING_UPGRADE.get()))
         .method_17972(recipeOutput, SophisticatedBackpacks.getRL("auto_blasting_upgrade_from_auto_smelting_upgrade"));
      ShapeBasedRecipeBuilder.shaped((class_1935)ModItems.ANVIL_UPGRADE.get())
         .pattern("ADA")
         .pattern("IBI")
         .pattern(" C ")
         .define('A', class_1802.field_8782)
         .define('D', ConventionalItemTags.DIAMOND_GEMS)
         .define('I', ConventionalItemTags.IRON_INGOTS)
         .define('B', (class_1935)ModItems.UPGRADE_BASE.get())
         .define('C', ConventionalItemTags.WOODEN_CHESTS)
         .unlockedBy("has_upgrade_base", method_10426((class_1935)ModItems.UPGRADE_BASE.get()))
         .method_10431(recipeOutput);
      ShapeBasedRecipeBuilder.shaped((class_1935)ModItems.SMITHING_UPGRADE.get())
         .pattern(" S ")
         .pattern("IBI")
         .pattern(" C ")
         .define('S', class_1802.field_16308)
         .define('I', ConventionalItemTags.IRON_INGOTS)
         .define('B', (class_1935)ModItems.UPGRADE_BASE.get())
         .define('C', ConventionalItemTags.WOODEN_CHESTS)
         .unlockedBy("has_upgrade_base", method_10426((class_1935)ModItems.UPGRADE_BASE.get()))
         .method_10431(recipeOutput);
      new SmithingBackpackUpgradeRecipeBuilder(
            SmithingBackpackUpgradeRecipe::new,
            class_1856.method_8091(new class_1935[]{class_1802.field_41946}),
            class_1856.method_8091(new class_1935[]{(class_1935)ModItems.DIAMOND_BACKPACK.get()}),
            class_1856.method_8091(new class_1935[]{class_1802.field_22020}),
            (class_1792)ModItems.NETHERITE_BACKPACK.get()
         )
         .method_48536("has_diamond_backpack", method_10426((class_1935)ModItems.DIAMOND_BACKPACK.get()))
         .method_48537(recipeOutput, RegistryHelper.getItemKey((class_1792)ModItems.NETHERITE_BACKPACK.get()));
      this.addChippedUpgradeRecipes(recipeOutput);
   }

   private void addChippedUpgradeRecipes(class_8790 recipeOutput) {
      this.addChippedUpgradeRecipe(
         recipeOutput, (BlockTransformationUpgradeItem)ChippedCompat.BOTANIST_WORKBENCH_UPGRADE.get(), (class_2248)ModBlocks.BOTANIST_WORKBENCH.get()
      );
      this.addChippedUpgradeRecipe(
         recipeOutput, (BlockTransformationUpgradeItem)ChippedCompat.GLASSBLOWER_UPGRADE.get(), (class_2248)ModBlocks.GLASSBLOWER.get()
      );
      this.addChippedUpgradeRecipe(
         recipeOutput, (BlockTransformationUpgradeItem)ChippedCompat.CARPENTERS_TABLE_UPGRADE.get(), (class_2248)ModBlocks.CARPENTERS_TABLE.get()
      );
      this.addChippedUpgradeRecipe(recipeOutput, (BlockTransformationUpgradeItem)ChippedCompat.LOOM_TABLE_UPGRADE.get(), (class_2248)ModBlocks.LOOM_TABLE.get());
      this.addChippedUpgradeRecipe(
         recipeOutput, (BlockTransformationUpgradeItem)ChippedCompat.MASON_TABLE_UPGRADE.get(), (class_2248)ModBlocks.MASON_TABLE.get()
      );
      this.addChippedUpgradeRecipe(
         recipeOutput, (BlockTransformationUpgradeItem)ChippedCompat.ALCHEMY_BENCH_UPGRADE.get(), (class_2248)ModBlocks.ALCHEMY_BENCH.get()
      );
      this.addChippedUpgradeRecipe(
         recipeOutput, (BlockTransformationUpgradeItem)ChippedCompat.TINKERING_TABLE_UPGRADE.get(), (class_2248)ModBlocks.TINKERING_TABLE.get()
      );
   }

   private void addChippedUpgradeRecipe(class_8790 recipeOutput, BlockTransformationUpgradeItem upgrade, class_2248 workbench) {
      ShapeBasedRecipeBuilder.shaped(upgrade)
         .pattern(" W ")
         .pattern("IBI")
         .pattern(" R ")
         .define('B', (class_1935)ModItems.UPGRADE_BASE.get())
         .define('R', ConventionalItemTags.REDSTONE_DUSTS)
         .define('I', ConventionalItemTags.IRON_INGOTS)
         .define('W', workbench)
         .unlockedBy("has_upgrade_base", method_10426((class_1935)ModItems.UPGRADE_BASE.get()))
         .method_10431(this.withConditions(recipeOutput, new ResourceCondition[]{ResourceConditions.allModsLoaded(new String[]{"chipped"})}));
   }

   private static class_175<?> hasLeather() {
      return method_10423(new class_2073[]{class_2074.method_8973().method_8975(ConventionalItemTags.LEATHERS).method_8976()});
   }
}
