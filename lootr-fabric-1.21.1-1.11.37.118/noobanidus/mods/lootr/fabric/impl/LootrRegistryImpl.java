package noobanidus.mods.lootr.fabric.impl;

import net.minecraft.class_1299;
import net.minecraft.class_1533;
import net.minecraft.class_1761;
import net.minecraft.class_1792;
import net.minecraft.class_2248;
import net.minecraft.class_2400;
import net.minecraft.class_2591;
import net.minecraft.class_2595;
import net.minecraft.class_3445;
import net.minecraft.class_5342;
import noobanidus.mods.lootr.common.api.advancement.IAdvancementTrigger;
import noobanidus.mods.lootr.common.api.advancement.IContainerTrigger;
import noobanidus.mods.lootr.common.api.advancement.ILootedStatTrigger;
import noobanidus.mods.lootr.common.api.registry.ILootrRegistry;
import noobanidus.mods.lootr.fabric.init.ModAdvancements;
import noobanidus.mods.lootr.fabric.init.ModBlockEntities;
import noobanidus.mods.lootr.fabric.init.ModBlocks;
import noobanidus.mods.lootr.fabric.init.ModEntities;
import noobanidus.mods.lootr.fabric.init.ModItems;
import noobanidus.mods.lootr.fabric.init.ModLoot;
import noobanidus.mods.lootr.fabric.init.ModParticles;
import noobanidus.mods.lootr.fabric.init.ModStats;
import noobanidus.mods.lootr.fabric.init.ModTabs;

public class LootrRegistryImpl implements ILootrRegistry {
   @Override
   public class_2248 getBarrelBlock() {
      return ModBlocks.BARREL;
   }

   @Override
   public class_2248 getChestBlock() {
      return ModBlocks.CHEST;
   }

   @Override
   public class_2248 getTrappedChestBlock() {
      return ModBlocks.TRAPPED_CHEST;
   }

   @Override
   public class_2248 getInventoryBlock() {
      return ModBlocks.INVENTORY;
   }

   @Override
   public class_2248 getTrophyBlock() {
      return ModBlocks.TROPHY;
   }

   @Override
   public class_2248 getShulkerBlock() {
      return ModBlocks.SHULKER;
   }

   @Override
   public class_2248 getSuspiciousSandBlock() {
      return ModBlocks.SUSPICIOUS_SAND;
   }

   @Override
   public class_2248 getSuspiciousGravelBlock() {
      return ModBlocks.SUSPICIOUS_GRAVEL;
   }

   @Override
   public class_2248 getDecoratedPotBlock() {
      return ModBlocks.DECORATED_POT;
   }

   @Override
   public class_2591<?> getBarrelBlockEntity() {
      return ModBlockEntities.LOOTR_BARREL;
   }

   @Override
   public class_2591<? extends class_2595> getChestBlockEntity() {
      return ModBlockEntities.LOOTR_CHEST;
   }

   @Override
   public class_2591<? extends class_2595> getTrappedChestBlockEntity() {
      return ModBlockEntities.LOOTR_TRAPPED_CHEST;
   }

   @Override
   public class_2591<? extends class_2595> getInventoryBlockEntity() {
      return ModBlockEntities.LOOTR_INVENTORY;
   }

   @Override
   public class_2591<?> getShulkerBlockEntity() {
      return ModBlockEntities.LOOTR_SHULKER;
   }

   @Override
   public class_2591<?> getDecoratedPotBlockEntity() {
      return ModBlockEntities.LOOTR_DECORATED_POT;
   }

   @Override
   public class_1792 getBarrelItem() {
      return ModItems.BARREL;
   }

   @Override
   public class_1792 getChestItem() {
      return ModItems.CHEST;
   }

   @Override
   public class_1792 getTrappedChestItem() {
      return ModItems.TRAPPED_CHEST;
   }

   @Override
   public class_1792 getInventoryItem() {
      return ModItems.INVENTORY;
   }

   @Override
   public class_1792 getTrophyItem() {
      return ModItems.TROPHY;
   }

   @Override
   public class_1792 getShulkerItem() {
      return ModItems.SHULKER;
   }

   @Override
   public class_1792 getSuspiciousSandItem() {
      return ModItems.SUSPICIOUS_SAND;
   }

   @Override
   public class_1792 getSuspiciousGravelItem() {
      return ModItems.SUSPICIOUS_GRAVEL;
   }

   @Override
   public class_1792 getDecoratedPotItem() {
      return ModItems.DECORATED_POT;
   }

   @Override
   public class_1299<?> getMinecart() {
      return ModEntities.LOOTR_MINECART_ENTITY;
   }

   @Override
   public class_1299<? extends class_1533> getItemFrame() {
      return ModEntities.ITEM_FRAME;
   }

   @Override
   public class_2591<?> getBrushableBlockEntity() {
      return ModBlockEntities.LOOTR_BRUSHABLE_BLOCK;
   }

   @Override
   public IAdvancementTrigger getAdvancementTrigger() {
      return ModAdvancements.ADVANCEMENT;
   }

   @Override
   public IContainerTrigger getChestTrigger() {
      return ModAdvancements.CHEST;
   }

   @Override
   public IContainerTrigger getBarrelTrigger() {
      return ModAdvancements.BARREL;
   }

   @Override
   public IContainerTrigger getCartTrigger() {
      return ModAdvancements.CART;
   }

   @Override
   public IContainerTrigger getShulkerTrigger() {
      return ModAdvancements.SHULKER;
   }

   @Override
   public ILootedStatTrigger getStatTrigger() {
      return ModAdvancements.SCORE;
   }

   @Override
   public IContainerTrigger getSandTrigger() {
      return ModAdvancements.SAND;
   }

   @Override
   public IContainerTrigger getGravelTrigger() {
      return ModAdvancements.GRAVEL;
   }

   @Override
   public IContainerTrigger getPotTrigger() {
      return ModAdvancements.POT;
   }

   @Override
   public class_5342 getLootCount() {
      return ModLoot.LOOT_COUNT;
   }

   @Override
   public class_3445<?> getLootedStat() {
      if (ModStats.LOOTED_STAT == null) {
         ModStats.load();
      }

      return ModStats.LOOTED_STAT;
   }

   @Override
   public class_1761 getTab() {
      return ModTabs.LOOTR_TAB;
   }

   @Override
   public IContainerTrigger getItemFrameTrigger() {
      return ModAdvancements.ITEM_FRAME;
   }

   @Override
   public class_2400 getUnopenedParticleType() {
      return ModParticles.UNOPENED_PARTCLE;
   }
}
