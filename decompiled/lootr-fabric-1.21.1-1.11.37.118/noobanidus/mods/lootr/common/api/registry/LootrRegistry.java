package noobanidus.mods.lootr.common.api.registry;

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

public class LootrRegistry {
   public static ILootrRegistry INSTANCE = null;

   public static boolean isReady() {
      return INSTANCE != null;
   }

   public static class_2248 getBarrelBlock() {
      return INSTANCE.getBarrelBlock();
   }

   public static class_2248 getChestBlock() {
      return INSTANCE.getChestBlock();
   }

   public static class_2248 getTrappedChestBlock() {
      return INSTANCE.getTrappedChestBlock();
   }

   public static class_2248 getInventoryBlock() {
      return INSTANCE.getInventoryBlock();
   }

   public static class_2248 getTrophyBlock() {
      return INSTANCE.getTrophyBlock();
   }

   public static class_2248 getShulkerBlock() {
      return INSTANCE.getShulkerBlock();
   }

   public static class_2248 getDecoratedPotBlock() {
      return INSTANCE.getDecoratedPotBlock();
   }

   public static class_1792 getBarrelItem() {
      return INSTANCE.getBarrelItem();
   }

   public static class_1792 getChestItem() {
      return INSTANCE.getChestItem();
   }

   public static class_1792 getTrappedChestItem() {
      return INSTANCE.getTrappedChestItem();
   }

   public static class_1792 getInventoryItem() {
      return INSTANCE.getInventoryItem();
   }

   public static class_1792 getTrophyItem() {
      return INSTANCE.getTrophyItem();
   }

   public static class_1792 getShulkerItem() {
      return INSTANCE.getShulkerItem();
   }

   public static class_1792 getDecoratedPotItem() {
      return INSTANCE.getDecoratedPotItem();
   }

   public static class_1299<?> getMinecart() {
      return INSTANCE.getMinecart();
   }

   public static class_1299<? extends class_1533> getItemFrame() {
      return INSTANCE.getItemFrame();
   }

   public static class_2591<?> getBarrelBlockEntity() {
      return INSTANCE.getBarrelBlockEntity();
   }

   public static class_2591<? extends class_2595> getChestBlockEntity() {
      return INSTANCE.getChestBlockEntity();
   }

   public static class_2591<? extends class_2595> getTrappedChestBlockEntity() {
      return INSTANCE.getTrappedChestBlockEntity();
   }

   public static class_2591<? extends class_2595> getInventoryBlockEntity() {
      return INSTANCE.getInventoryBlockEntity();
   }

   public static class_2591<?> getShulkerBlockEntity() {
      return INSTANCE.getShulkerBlockEntity();
   }

   public static class_2591<?> getDecoratedPotBlockEntity() {
      return INSTANCE.getDecoratedPotBlockEntity();
   }

   public static class_5342 getLootCount() {
      return INSTANCE.getLootCount();
   }

   public static IAdvancementTrigger getAdvancementTrigger() {
      return INSTANCE.getAdvancementTrigger();
   }

   public static IContainerTrigger getChestTrigger() {
      return INSTANCE.getChestTrigger();
   }

   public static IContainerTrigger getBarrelTrigger() {
      return INSTANCE.getBarrelTrigger();
   }

   public static IContainerTrigger getCartTrigger() {
      return INSTANCE.getCartTrigger();
   }

   public static IContainerTrigger getShulkerTrigger() {
      return INSTANCE.getShulkerTrigger();
   }

   public static IContainerTrigger getSandTrigger() {
      return INSTANCE.getSandTrigger();
   }

   public static IContainerTrigger getGravelTrigger() {
      return INSTANCE.getGravelTrigger();
   }

   public static ILootedStatTrigger getStatTrigger() {
      return INSTANCE.getStatTrigger();
   }

   public static IContainerTrigger getPotTrigger() {
      return INSTANCE.getPotTrigger();
   }

   public static IContainerTrigger getItemFrameTrigger() {
      return INSTANCE.getItemFrameTrigger();
   }

   public static class_3445<?> getLootedStat() {
      return INSTANCE.getLootedStat();
   }

   public static class_1761 getTab() {
      return INSTANCE.getTab();
   }

   public static class_2591<?> getBrushableBlockEntity() {
      return INSTANCE.getBrushableBlockEntity();
   }

   public static class_2248 getSuspiciousSandBlock() {
      return INSTANCE.getSuspiciousSandBlock();
   }

   public static class_2248 getSuspiciousGravelBlock() {
      return INSTANCE.getSuspiciousGravelBlock();
   }

   public static class_1792 getSuspiciousGravelItem() {
      return INSTANCE.getSuspiciousGravelItem();
   }

   public static class_1792 getSuspiciousSandItem() {
      return INSTANCE.getSuspiciousSandItem();
   }

   public static class_2400 getUnopenedParticleType() {
      return INSTANCE.getUnopenedParticleType();
   }
}
