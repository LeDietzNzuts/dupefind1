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
import org.jetbrains.annotations.ApiStatus.Internal;

@Internal
public interface ILootrRegistry {
   class_2248 getBarrelBlock();

   class_2248 getChestBlock();

   class_2248 getTrappedChestBlock();

   class_2248 getInventoryBlock();

   class_2248 getTrophyBlock();

   default class_2248 getShulker() {
      return this.getShulkerBlock();
   }

   class_2248 getShulkerBlock();

   class_2248 getSuspiciousSandBlock();

   class_2248 getSuspiciousGravelBlock();

   class_2248 getDecoratedPotBlock();

   class_1792 getBarrelItem();

   class_1792 getChestItem();

   class_1792 getTrappedChestItem();

   class_1792 getInventoryItem();

   class_1792 getTrophyItem();

   class_1792 getShulkerItem();

   class_1792 getSuspiciousSandItem();

   class_1792 getSuspiciousGravelItem();

   class_1792 getDecoratedPotItem();

   class_1299<?> getMinecart();

   class_1299<? extends class_1533> getItemFrame();

   class_2591<?> getBrushableBlockEntity();

   class_2591<?> getBarrelBlockEntity();

   class_2591<? extends class_2595> getChestBlockEntity();

   class_2591<? extends class_2595> getTrappedChestBlockEntity();

   class_2591<? extends class_2595> getInventoryBlockEntity();

   class_2591<?> getShulkerBlockEntity();

   class_2591<?> getDecoratedPotBlockEntity();

   IAdvancementTrigger getAdvancementTrigger();

   IContainerTrigger getChestTrigger();

   IContainerTrigger getBarrelTrigger();

   IContainerTrigger getCartTrigger();

   IContainerTrigger getShulkerTrigger();

   ILootedStatTrigger getStatTrigger();

   IContainerTrigger getSandTrigger();

   IContainerTrigger getGravelTrigger();

   IContainerTrigger getPotTrigger();

   class_5342 getLootCount();

   class_3445<?> getLootedStat();

   class_1761 getTab();

   IContainerTrigger getItemFrameTrigger();

   class_2400 getUnopenedParticleType();
}
