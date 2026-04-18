package net.p3pp3rf1y.sophisticatedbackpacks.upgrades.toolswapper;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.util.concurrent.AtomicDouble;
import io.github.fabricators_of_create.porting_lib.tool.ItemAbilities;
import io.github.fabricators_of_create.porting_lib.tool.ItemAbility;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Consumer;
import java.util.function.Predicate;
import javax.annotation.Nullable;
import net.fabricmc.fabric.api.tag.convention.v2.ConventionalItemTags;
import net.minecraft.class_1268;
import net.minecraft.class_1297;
import net.minecraft.class_1304;
import net.minecraft.class_1324;
import net.minecraft.class_1657;
import net.minecraft.class_1792;
import net.minecraft.class_1799;
import net.minecraft.class_1820;
import net.minecraft.class_1838;
import net.minecraft.class_1937;
import net.minecraft.class_2246;
import net.minecraft.class_2248;
import net.minecraft.class_2338;
import net.minecraft.class_2350;
import net.minecraft.class_243;
import net.minecraft.class_2680;
import net.minecraft.class_2960;
import net.minecraft.class_3965;
import net.minecraft.class_4481;
import net.minecraft.class_5134;
import net.minecraft.class_7923;
import net.p3pp3rf1y.sophisticatedbackpacks.Config;
import net.p3pp3rf1y.sophisticatedbackpacks.api.IAttackEntityResponseUpgrade;
import net.p3pp3rf1y.sophisticatedbackpacks.api.IBlockClickResponseUpgrade;
import net.p3pp3rf1y.sophisticatedbackpacks.api.IBlockToolSwapUpgrade;
import net.p3pp3rf1y.sophisticatedbackpacks.api.IEntityToolSwapUpgrade;
import net.p3pp3rf1y.sophisticatedbackpacks.api.SophisticatedShearable;
import net.p3pp3rf1y.sophisticatedbackpacks.backpack.BackpackItem;
import net.p3pp3rf1y.sophisticatedbackpacks.init.ModDataComponents;
import net.p3pp3rf1y.sophisticatedbackpacks.registry.tool.SwordRegistry;
import net.p3pp3rf1y.sophisticatedbackpacks.registry.tool.ToolRegistry;
import net.p3pp3rf1y.sophisticatedcore.api.IStorageWrapper;
import net.p3pp3rf1y.sophisticatedcore.init.ModCoreDataComponents;
import net.p3pp3rf1y.sophisticatedcore.inventory.IItemHandlerSimpleInserter;
import net.p3pp3rf1y.sophisticatedcore.upgrades.FilterLogic;
import net.p3pp3rf1y.sophisticatedcore.upgrades.UpgradeWrapperBase;
import net.p3pp3rf1y.sophisticatedcore.util.InventoryHelper;

public class ToolSwapperUpgradeWrapper
   extends UpgradeWrapperBase<ToolSwapperUpgradeWrapper, ToolSwapperUpgradeItem>
   implements IBlockClickResponseUpgrade,
   IAttackEntityResponseUpgrade,
   IBlockToolSwapUpgrade,
   IEntityToolSwapUpgrade {
   private static final LoadingCache<class_1799, Boolean> isToolCache = CacheBuilder.newBuilder()
      .expireAfterAccess(1L, TimeUnit.MINUTES)
      .build(new CacheLoader<class_1799, Boolean>() {
         public Boolean load(class_1799 key) {
            return ToolSwapperUpgradeWrapper.canPerformToolAction(key);
         }
      });
   private final FilterLogic filterLogic;
   @Nullable
   private class_2960 toolCacheFor = null;
   private final Queue<class_1799> toolCache = new LinkedList<>();
   private class_2248 lastMinedBlock = class_2246.field_10124;
   private static final Set<ItemAbility> BLOCK_MODIFICATION_ACTIONS = Set.of(
      ItemAbilities.AXE_STRIP,
      ItemAbilities.AXE_SCRAPE,
      ItemAbilities.AXE_WAX_OFF,
      ItemAbilities.SHOVEL_FLATTEN,
      ItemAbilities.SHEARS_CARVE,
      ItemAbilities.SHEARS_HARVEST
   );

   protected ToolSwapperUpgradeWrapper(IStorageWrapper backpackWrapper, class_1799 upgrade, Consumer<class_1799> upgradeSaveHandler) {
      super(backpackWrapper, upgrade, upgradeSaveHandler);
      this.filterLogic = new FilterLogic(
         upgrade, upgradeSaveHandler, (Integer)Config.SERVER.toolSwapperUpgrade.filterSlots.get(), ModCoreDataComponents.FILTER_ATTRIBUTES
      );
   }

   @Override
   public boolean onBlockClick(class_1657 player, class_2338 pos) {
      ToolSwapMode toolSwapMode = this.getToolSwapMode();
      if (!player.method_7337() && !player.method_7325() && toolSwapMode != ToolSwapMode.NO_SWAP) {
         class_1799 mainHandItem = player.method_6047();
         if (!(mainHandItem.method_7909() instanceof BackpackItem)
            && (toolSwapMode != ToolSwapMode.ONLY_TOOLS || !this.isSword(mainHandItem, player))
            && (this.isSword(mainHandItem, player) || !this.isNotTool(mainHandItem))
            && this.filterLogic.matchesFilter(mainHandItem)) {
            class_2680 state = player.method_37908().method_8320(pos);
            class_2248 block = state.method_26204();
            double mainToolSpeed = 0.0;
            if (this.isGoodAtBreakingBlock(state, mainHandItem)) {
               if (this.lastMinedBlock == block) {
                  return true;
               }

               mainToolSpeed = mainHandItem.method_7924(state);
            }

            this.lastMinedBlock = block;
            return this.tryToSwapTool(player, state, mainToolSpeed, mainHandItem);
         } else {
            return false;
         }
      } else {
         return false;
      }
   }

   private boolean tryToSwapTool(class_1657 player, class_2680 state, double mainHandItemSpeed, class_1799 mainHandItem) {
      AtomicReference<class_1799> selectedTool = new AtomicReference<>(class_1799.field_8037);
      AtomicInteger selectedSlot = new AtomicInteger(-1);
      AtomicDouble bestSpeed = new AtomicDouble(mainHandItemSpeed);
      IItemHandlerSimpleInserter backpackInventory = this.storageWrapper.getInventoryHandler();
      InventoryHelper.iterate(backpackInventory, (slot, stack) -> {
         if (!stack.method_7960()) {
            if (this.isAllowedAndGoodAtBreakingBlock(state, stack)) {
               float destroySpeed = stack.method_7924(state);
               if (bestSpeed.get() < destroySpeed) {
                  bestSpeed.set(destroySpeed);
                  selectedSlot.set(slot);
                  selectedTool.set(stack);
               }
            }
         }
      });
      class_1799 tool = selectedTool.get();
      if (!tool.method_7960() && this.hasSpaceInBackpackOrCanPlaceInTheSlotOfSwappedTool(backpackInventory, mainHandItem, tool, selectedSlot.get())) {
         player.method_6122(class_1268.field_5808, backpackInventory.extractItem(selectedSlot.get(), 1, false));
         backpackInventory.insertItem(mainHandItem, false);
         return true;
      } else {
         return false;
      }
   }

   private boolean hasSpaceInBackpackOrCanPlaceInTheSlotOfSwappedTool(
      IItemHandlerSimpleInserter backpackInventory, class_1799 mainHandItem, class_1799 tool, int selectedSlot
   ) {
      return backpackInventory.insertItem(mainHandItem, true).method_7960()
         || tool.method_7947() == 1 && backpackInventory.isItemValid(selectedSlot, mainHandItem);
   }

   private boolean isAllowedAndGoodAtBreakingBlock(class_2680 state, class_1799 stack) {
      return this.filterLogic.matchesFilter(stack) && this.isGoodAtBreakingBlock(state, stack);
   }

   private boolean isGoodAtBreakingBlock(class_2680 state, class_1799 stack) {
      return stack.method_7951(state) && stack.method_7924(state) > 1.5;
   }

   @Override
   public boolean onAttackEntity(class_1657 player) {
      if (!this.shouldSwapWeapon()) {
         return false;
      } else {
         class_1799 mainHandItem = player.method_6047();
         if (this.isSword(mainHandItem, player)) {
            return true;
         } else {
            return !(mainHandItem.method_7909() instanceof BackpackItem) && !this.isNotTool(mainHandItem) && this.filterLogic.matchesFilter(mainHandItem)
               ? this.tryToSwapInWeapon(player, mainHandItem)
               : false;
         }
      }
   }

   private boolean isNotTool(class_1799 stack) {
      return !(Boolean)isToolCache.getUnchecked(stack);
   }

   private static boolean canPerformToolAction(class_1799 stack) {
      return canPerformAnyAction(stack, ItemAbilities.DEFAULT_AXE_ACTIONS)
         || canPerformAnyAction(stack, ItemAbilities.DEFAULT_HOE_ACTIONS)
         || canPerformAnyAction(stack, ItemAbilities.DEFAULT_PICKAXE_ACTIONS)
         || canPerformAnyAction(stack, ItemAbilities.DEFAULT_SHOVEL_ACTIONS)
         || canPerformAnyAction(stack, ItemAbilities.DEFAULT_SHEARS_ACTIONS);
   }

   private static boolean canPerformAnyAction(class_1799 stack, Set<ItemAbility> toolActions) {
      for (ItemAbility toolAction : toolActions) {
         if (stack.canPerformAction(toolAction)) {
            return true;
         }
      }

      return false;
   }

   private boolean isSword(class_1799 stack, class_1657 player) {
      if (SwordRegistry.isSword(stack)) {
         return true;
      } else {
         class_1324 attackDamage = player.method_5996(class_5134.field_23721);
         return !stack.method_7960() && stack.canPerformAction(ItemAbilities.SWORD_SWEEP)
            ? attackDamage != null && attackDamage.method_6199(class_1792.field_8006) != null
            : false;
      }
   }

   private boolean tryToSwapInWeapon(class_1657 player, class_1799 mainHandItem) {
      AtomicReference<class_1799> bestAxe = new AtomicReference<>(class_1799.field_8037);
      AtomicDouble bestAxeDamage = new AtomicDouble(0.0);
      AtomicReference<class_1799> bestSword = new AtomicReference<>(class_1799.field_8037);
      AtomicDouble bestSwordDamage = new AtomicDouble(0.0);
      this.updateBestWeapons(bestAxe, bestAxeDamage, bestSword, bestSwordDamage, mainHandItem);
      IItemHandlerSimpleInserter backpackInventory = this.storageWrapper.getInventoryForUpgradeProcessing();
      InventoryHelper.iterate(backpackInventory, (slot, stack) -> {
         if (this.filterLogic.matchesFilter(stack)) {
            this.updateBestWeapons(bestAxe, bestAxeDamage, bestSword, bestSwordDamage, stack);
         }
      });
      if (!bestSword.get().method_7960()) {
         return this.swapWeapon(player, mainHandItem, backpackInventory, bestSword.get());
      } else {
         return !bestAxe.get().method_7960() ? this.swapWeapon(player, mainHandItem, backpackInventory, bestAxe.get()) : false;
      }
   }

   private void updateBestWeapons(
      AtomicReference<class_1799> bestAxe, AtomicDouble bestAxeDamage, AtomicReference<class_1799> bestSword, AtomicDouble bestSwordDamage, class_1799 stack
   ) {
      class_1324 attribute = new class_1324(class_5134.field_23721, a -> {});
      stack.method_57354(class_1304.field_6173, (att, m) -> {
         if (att.equals(class_5134.field_23721)) {
            attribute.method_6202(m);
            attribute.method_26835(m);
         }
      });
      double damageValue = attribute.method_6194();
      if (stack.canPerformAction(ItemAbilities.AXE_DIG)) {
         if (damageValue > bestAxeDamage.get()) {
            bestAxe.set(stack);
            bestAxeDamage.set(damageValue);
         }
      } else if ((SwordRegistry.isSword(stack) || stack.canPerformAction(ItemAbilities.SWORD_SWEEP)) && damageValue > bestSwordDamage.get()) {
         bestSword.set(stack);
         bestSwordDamage.set(damageValue);
      }
   }

   private boolean swapWeapon(class_1657 player, class_1799 mainHandItem, IItemHandlerSimpleInserter backpackInventory, class_1799 sword) {
      if (sword == mainHandItem) {
         return true;
      } else {
         class_1799 swordCopy = sword.method_7972();
         swordCopy.method_7939(1);
         InventoryHelper.extractFromInventory(swordCopy, backpackInventory, false);
         if (backpackInventory.insertItem(mainHandItem, true).method_7960()) {
            player.method_6122(class_1268.field_5808, swordCopy);
            backpackInventory.insertItem(mainHandItem, false);
            return true;
         } else {
            backpackInventory.insertItem(swordCopy, false);
            return false;
         }
      }
   }

   public boolean hideSettingsTab() {
      return !((ToolSwapperUpgradeItem)this.upgradeItem).hasSettingsTab();
   }

   public FilterLogic getFilterLogic() {
      return this.filterLogic;
   }

   public boolean shouldSwapWeapon() {
      return (Boolean)this.upgrade.sophisticatedCore_getOrDefault(ModDataComponents.SHOULD_SWAP_WEAPON, true);
   }

   public void setSwapWeapon(boolean shouldSwapWeapon) {
      this.upgrade.sophisticatedCore_set(ModDataComponents.SHOULD_SWAP_WEAPON, shouldSwapWeapon);
      this.save();
   }

   public ToolSwapMode getToolSwapMode() {
      return (ToolSwapMode)this.upgrade.sophisticatedCore_getOrDefault(ModDataComponents.TOOL_SWAP_MODE, ToolSwapMode.ANY);
   }

   public void setToolSwapMode(ToolSwapMode toolSwapMode) {
      this.upgrade.sophisticatedCore_set(ModDataComponents.TOOL_SWAP_MODE, toolSwapMode);
      this.save();
   }

   @Override
   public boolean onEntityInteract(class_1937 level, class_1297 entity, class_1657 player) {
      return !((ToolSwapperUpgradeItem)this.upgradeItem).shouldSwapToolOnKeyPress()
         ? false
         : this.tryToSwapTool(player, stack -> this.itemWorksOnEntity(stack, entity), class_7923.field_41177.method_10221(entity.method_5864()));
   }

   private boolean itemWorksOnEntity(class_1799 stack, class_1297 entity) {
      return this.isShearableEntity(entity, stack) && this.isShearsItem(stack) ? true : ToolRegistry.isToolForEntity(stack, entity);
   }

   @Override
   public boolean onBlockInteract(class_1937 level, class_2338 pos, class_2680 blockState, class_1657 player) {
      return !((ToolSwapperUpgradeItem)this.upgradeItem).shouldSwapToolOnKeyPress()
         ? false
         : this.tryToSwapTool(
            player, stack -> this.itemWorksOnBlock(level, pos, blockState, player, stack), class_7923.field_41175.method_10221(blockState.method_26204())
         );
   }

   private boolean tryToSwapTool(class_1657 player, Predicate<class_1799> isToolValid, @Nullable class_2960 targetRegistryName) {
      class_1799 mainHandStack = player.method_6047();
      if (mainHandStack.method_7909() instanceof BackpackItem) {
         return false;
      } else {
         if (this.toolCacheFor == null || !this.toolCacheFor.equals(targetRegistryName)) {
            this.toolCache.clear();
            this.toolCacheFor = targetRegistryName;
         }

         boolean itemInHandIsValid = isToolValid.test(mainHandStack);
         IItemHandlerSimpleInserter backpackInventory = this.storageWrapper.getInventoryForUpgradeProcessing();
         if (itemInHandIsValid && this.toolCache.stream().noneMatch(st -> class_1799.method_7984(st, mainHandStack))) {
            this.toolCache.offer(mainHandStack);
         }

         class_1799 tool = this.findToolToSwap(backpackInventory, isToolValid);
         if (tool.method_7960()) {
            return false;
         } else {
            tool = tool.method_7972().method_7971(1);
            if (tool.method_7947() == 1 || backpackInventory.insertItem(mainHandStack, true).method_7960()) {
               player.method_6122(class_1268.field_5808, InventoryHelper.extractFromInventory(tool, backpackInventory, false));
               backpackInventory.insertItem(mainHandStack, false);
               this.toolCache.offer(tool);
            }

            return true;
         }
      }
   }

   private class_1799 findToolToSwap(IItemHandlerSimpleInserter backpackInventory, Predicate<class_1799> isValidTool) {
      Set<class_1799> alreadyGivenBefore = new HashSet<>();
      AtomicReference<class_1799> toolFound = new AtomicReference<>(class_1799.field_8037);
      InventoryHelper.iterate(backpackInventory, (slot, stack) -> {
         if (!stack.method_7960()) {
            if (!this.hasEquivalentItem(this.toolCache, stack)) {
               if (isValidTool.test(stack)) {
                  toolFound.set(stack);
               }
            } else {
               alreadyGivenBefore.add(stack);
            }
         }
      }, () -> !toolFound.get().method_7960());
      if (toolFound.get().method_7960() && !alreadyGivenBefore.isEmpty()) {
         while (this.toolCache.peek() != null) {
            class_1799 itemStack = this.toolCache.poll();
            if (this.hasEquivalentItem(alreadyGivenBefore, itemStack)) {
               toolFound.set(itemStack);
               break;
            }
         }
      }

      return toolFound.get();
   }

   private boolean hasEquivalentItem(Collection<class_1799> alreadyGivenBefore, class_1799 stack) {
      for (class_1799 givenTool : alreadyGivenBefore) {
         if (class_1799.method_7984(givenTool, stack)) {
            return true;
         }
      }

      return false;
   }

   private boolean itemWorksOnBlock(class_1937 level, class_2338 pos, class_2680 blockState, class_1657 player, class_1799 stack) {
      for (ItemAbility action : BLOCK_MODIFICATION_ACTIONS) {
         if (stack.canPerformAction(action)
            && blockState.getToolModifiedState(
                  new class_1838(level, player, class_1268.field_5808, stack, new class_3965(class_243.method_24953(pos), class_2350.field_11036, pos, true)),
                  action,
                  true
               )
               != null) {
            return true;
         }
      }

      class_2248 block = blockState.method_26204();
      return this.isShearInteractionBlock(level, pos, stack, block) && this.isShearsItem(stack)
         ? true
         : ToolRegistry.isToolForBlock(stack, block, level, blockState, pos);
   }

   private boolean isShearsItem(class_1799 stack) {
      return stack.method_7909() instanceof class_1820 || stack.method_31573(ConventionalItemTags.SHEAR_TOOLS);
   }

   private boolean isShearInteractionBlock(class_1937 level, class_2338 pos, class_1799 stack, class_2248 block) {
      return block instanceof SophisticatedShearable shearable && shearable.sophisticatedBackpacks_isShearable(null, stack, level, pos)
         || block instanceof class_4481;
   }

   private boolean isShearableEntity(class_1297 entity, class_1799 stack) {
      return entity instanceof SophisticatedShearable shearable
         && shearable.sophisticatedBackpacks_isShearable(null, stack, entity.method_37908(), entity.method_24515());
   }

   @Override
   public boolean canProcessBlockInteract() {
      return ((ToolSwapperUpgradeItem)this.upgradeItem).shouldSwapToolOnKeyPress();
   }

   @Override
   public boolean canProcessEntityInteract() {
      return ((ToolSwapperUpgradeItem)this.upgradeItem).shouldSwapToolOnKeyPress();
   }
}
