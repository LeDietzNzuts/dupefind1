package net.p3pp3rf1y.sophisticatedbackpacks.upgrades.refill;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableMap.Builder;
import com.mojang.serialization.Codec;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Consumer;
import java.util.function.Supplier;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import net.minecraft.class_124;
import net.minecraft.class_1268;
import net.minecraft.class_1297;
import net.minecraft.class_1657;
import net.minecraft.class_1799;
import net.minecraft.class_1937;
import net.minecraft.class_2338;
import net.minecraft.class_2540;
import net.minecraft.class_2561;
import net.minecraft.class_3542;
import net.minecraft.class_9135;
import net.minecraft.class_9139;
import net.p3pp3rf1y.sophisticatedbackpacks.api.IBlockPickResponseUpgrade;
import net.p3pp3rf1y.sophisticatedbackpacks.client.gui.SBPTranslationHelper;
import net.p3pp3rf1y.sophisticatedbackpacks.init.ModDataComponents;
import net.p3pp3rf1y.sophisticatedcore.api.IStorageWrapper;
import net.p3pp3rf1y.sophisticatedcore.init.ModCoreDataComponents;
import net.p3pp3rf1y.sophisticatedcore.inventory.IInventoryHandlerHelper;
import net.p3pp3rf1y.sophisticatedcore.inventory.IItemHandlerSimpleInserter;
import net.p3pp3rf1y.sophisticatedcore.inventory.ITrackedContentsItemHandler;
import net.p3pp3rf1y.sophisticatedcore.upgrades.FilterLogic;
import net.p3pp3rf1y.sophisticatedcore.upgrades.IFilteredUpgrade;
import net.p3pp3rf1y.sophisticatedcore.upgrades.ITickableUpgrade;
import net.p3pp3rf1y.sophisticatedcore.upgrades.UpgradeWrapperBase;
import net.p3pp3rf1y.sophisticatedcore.upgrades.FilterLogic.ObservableFilterItemStackHandler;
import net.p3pp3rf1y.sophisticatedcore.util.CapabilityHelper;
import net.p3pp3rf1y.sophisticatedcore.util.CodecHelper;
import net.p3pp3rf1y.sophisticatedcore.util.InventoryHelper;
import net.p3pp3rf1y.sophisticatedcore.util.StreamCodecHelper;

public class RefillUpgradeWrapper
   extends UpgradeWrapperBase<RefillUpgradeWrapper, RefillUpgradeItem>
   implements IFilteredUpgrade,
   ITickableUpgrade,
   IBlockPickResponseUpgrade {
   private static final int COOLDOWN = 5;
   public static final Codec<Map<Integer, RefillUpgradeWrapper.TargetSlot>> TARGET_SLOTS_CODEC = Codec.unboundedMap(
      CodecHelper.STRING_ENCODED_INT, RefillUpgradeWrapper.TargetSlot.CODEC
   );
   public static final class_9139<class_2540, Map<Integer, RefillUpgradeWrapper.TargetSlot>> TARGET_SLOTS_STREAM_CODEC = StreamCodecHelper.ofMap(
      class_9135.field_49675, RefillUpgradeWrapper.TargetSlot.STREAM_CODEC, HashMap::new
   );
   private final Map<Integer, RefillUpgradeWrapper.TargetSlot> targetSlots;
   private final FilterLogic filterLogic;

   public RefillUpgradeWrapper(IStorageWrapper backpackWrapper, class_1799 upgrade, Consumer<class_1799> upgradeSaveHandler) {
      super(backpackWrapper, upgrade, upgradeSaveHandler);
      this.filterLogic = new FilterLogic(
         upgrade, upgradeSaveHandler, ((RefillUpgradeItem)this.upgradeItem).getFilterSlotCount(), ModCoreDataComponents.FILTER_ATTRIBUTES
      );
      this.targetSlots = new HashMap<>(
         (Map<? extends Integer, ? extends RefillUpgradeWrapper.TargetSlot>)upgrade.sophisticatedCore_getOrDefault(
            ModDataComponents.TARGET_SLOTS, new HashMap()
         )
      );
      if (((RefillUpgradeItem)this.upgradeItem).allowsTargetSlotSelection()) {
         ObservableFilterItemStackHandler filterHandler = this.filterLogic.getFilterHandler();
         filterHandler.setOnSlotChange(s -> this.onFilterChange(filterHandler, s));
      }

      this.filterLogic.setAllowByDefault(true);
   }

   private void onFilterChange(ObservableFilterItemStackHandler filterHandler, int slot) {
      if (filterHandler.getStackInSlot(slot).method_7960()) {
         this.targetSlots.remove(slot);
         this.saveTargetSlots();
      } else if (!this.targetSlots.containsKey(slot)) {
         this.setTargetSlot(slot, RefillUpgradeWrapper.TargetSlot.ANY);
      }
   }

   public Map<Integer, RefillUpgradeWrapper.TargetSlot> getTargetSlots() {
      return this.targetSlots;
   }

   public void setTargetSlot(int slot, RefillUpgradeWrapper.TargetSlot targetSlot) {
      this.targetSlots.put(slot, targetSlot);
      this.saveTargetSlots();
   }

   private void saveTargetSlots() {
      this.upgrade.sophisticatedCore_set(ModDataComponents.TARGET_SLOTS, ImmutableMap.copyOf(this.targetSlots));
      this.save();
   }

   public FilterLogic getFilterLogic() {
      return this.filterLogic;
   }

   public void tick(@Nullable class_1297 entity, class_1937 level, class_2338 pos) {
      if (entity != null && !this.isInCooldown(level)) {
         CapabilityHelper.runOnItemHandler(entity, inventoryHandler -> InventoryHelper.iterate(this.filterLogic.getFilterHandler(), (slot, filter) -> {
            if (!filter.method_7960()) {
               this.tryRefillFilter(entity, inventoryHandler, filter, this.getTargetSlots().getOrDefault(slot, RefillUpgradeWrapper.TargetSlot.ANY));
            }
         }));
         this.setCooldown(level, 5);
      }
   }

   private void tryRefillFilter(
      @Nonnull class_1297 entity, IInventoryHandlerHelper playerInvHandler, class_1799 filter, RefillUpgradeWrapper.TargetSlot targetSlot
   ) {
      if (entity instanceof class_1657 player) {
         int missingCount = targetSlot.missingCountGetter.getMissingCount(player, playerInvHandler, filter);
         if (class_1799.method_31577(player.field_7512.method_34255(), filter)) {
            missingCount -= Math.min(missingCount, player.field_7512.method_34255().method_7947());
         }

         if (missingCount != 0) {
            IItemHandlerSimpleInserter extractFromHandler = this.storageWrapper.getInventoryForUpgradeProcessing();
            class_1799 toMove = filter.method_7972();
            toMove.method_7939(missingCount);
            class_1799 extracted = InventoryHelper.extractFromInventory(toMove, extractFromHandler, true);
            if (!extracted.method_7960()) {
               class_1799 remaining = targetSlot.filler.fill(player, playerInvHandler, extracted);
               if (remaining.method_7947() != extracted.method_7947()) {
                  class_1799 toExtract = extracted.method_7972();
                  toExtract.method_7939(extracted.method_7947() - remaining.method_7947());
                  InventoryHelper.extractFromInventory(toExtract, extractFromHandler, false);
               }
            }
         }
      }
   }

   public boolean allowsTargetSlotSelection() {
      return ((RefillUpgradeItem)this.upgradeItem).allowsTargetSlotSelection();
   }

   @Override
   public boolean pickBlock(class_1657 player, class_1799 filter) {
      if (!((RefillUpgradeItem)this.upgradeItem).supportsBlockPick()) {
         return false;
      } else {
         AtomicInteger stashSlot = new AtomicInteger(-1);
         AtomicBoolean hasItemInBackpack = new AtomicBoolean(false);
         ITrackedContentsItemHandler inventoryHandler = this.storageWrapper.getInventoryForUpgradeProcessing();
         InventoryHelper.iterate(inventoryHandler, (slot, stack) -> {
            if (class_1799.method_31577(stack, filter)) {
               hasItemInBackpack.set(true);
               stashSlot.set(slot);
            }
         }, () -> stashSlot.get() > -1);
         class_1799 mainHandItem = player.method_6047();
         class_1799 toExtract = filter.method_7972();
         toExtract.method_7939(filter.method_7914());
         if (hasItemInBackpack.get() && !InventoryHelper.extractFromInventory(toExtract, inventoryHandler, true).method_7960()) {
            if ((
                  inventoryHandler.getStackInSlot(stashSlot.get()).method_7947() > filter.method_7914()
                     || !inventoryHandler.isItemValid(stashSlot.get(), mainHandItem)
               )
               && !inventoryHandler.insertItem(mainHandItem, true).method_7960()) {
               if (this.canMoveMainHandToInventory(player)) {
                  class_1799 extracted = InventoryHelper.extractFromInventory(toExtract, inventoryHandler, false);
                  player.method_6122(class_1268.field_5808, extracted);
                  player.method_31548().method_7394(mainHandItem);
                  return true;
               } else {
                  player.method_7353(class_2561.method_43471("gui.sophisticatedbackpacks.status.no_space_for_mainhand_item"), true);
                  return false;
               }
            } else {
               class_1799 extracted = InventoryHelper.extractFromInventory(toExtract, inventoryHandler, false);
               inventoryHandler.insertItem(mainHandItem, false);
               player.method_6122(class_1268.field_5808, extracted);
               return true;
            }
         } else {
            return false;
         }
      }
   }

   private boolean canMoveMainHandToInventory(class_1657 player) {
      int countToAdd = player.method_6047().method_7947();

      for (int slot = 0; slot < player.method_31548().method_5439() - 5; slot++) {
         if (slot != player.method_31548().field_7545) {
            class_1799 slotStack = player.method_31548().method_5438(slot);
            if (slotStack.method_7960()) {
               return true;
            }

            if (class_1799.method_31577(slotStack, player.method_6047())) {
               countToAdd -= slotStack.method_7914() - slotStack.method_7947();
               if (countToAdd <= 0) {
                  return true;
               }
            }
         }
      }

      return false;
   }

   public static enum TargetSlot implements class_3542 {
      ANY(
         "any",
         SBPTranslationHelper.INSTANCE.translUpgrade("refill.target_slot.any", new Object[0]),
         SBPTranslationHelper.INSTANCE.translUpgrade("refill.target_slot.any.tooltip", new Object[0]).method_27692(class_124.field_1077),
         (player, playerInvHandler, filter) -> InventoryHelper.getCountMissingInHandler(playerInvHandler, filter, filter.method_7914()),
         (player, playerInvHandler, stackToAdd) -> refillAnywhereInInventory(playerInvHandler, stackToAdd)
      ),
      MAIN_HAND(
         "main_hand",
         SBPTranslationHelper.INSTANCE.translUpgrade("refill.target_slot.main_hand", new Object[0]),
         SBPTranslationHelper.INSTANCE.translUpgrade("refill.target_slot.main_hand.tooltip", new Object[0]).method_27692(class_124.field_1077),
         (player, playerInvHandler, filter) -> getMissingCount(player.method_6047(), filter),
         (player, playerInvHandler, stackToAdd) -> refillSlot(player::method_6047, stackToAdd, stack -> player.method_6122(class_1268.field_5808, stack))
      ),
      OFF_HAND(
         "off_hand",
         SBPTranslationHelper.INSTANCE.translUpgrade("refill.target_slot.off_hand", new Object[0]),
         SBPTranslationHelper.INSTANCE.translUpgrade("refill.target_slot.off_hand.tooltip", new Object[0]).method_27692(class_124.field_1077),
         (player, playerInvHandler, filter) -> getMissingCount(player.method_6079(), filter),
         (player, playerInvHandler, stackToAdd) -> refillSlot(player::method_6079, stackToAdd, stack -> player.method_6122(class_1268.field_5810, stack))
      ),
      TOOLBAR_1(
         "toolbar_1",
         class_2561.method_43470("1"),
         SBPTranslationHelper.INSTANCE.translUpgrade("refill.target_slot.hotbar.tooltip", new Object[]{1}).method_27692(class_124.field_1077),
         (player, playerInvHandler, filter) -> getMissingCount(player.method_31548().method_5438(0), filter),
         (player, playerInvHandler, stackToAdd) -> refillSlot(
            () -> player.method_31548().method_5438(0), stackToAdd, stack -> player.method_31548().method_5447(0, stack)
         )
      ),
      TOOLBAR_2(
         "toolbar_2",
         class_2561.method_43470("2"),
         SBPTranslationHelper.INSTANCE.translUpgrade("refill.target_slot.hotbar.tooltip", new Object[]{2}).method_27692(class_124.field_1077),
         (player, playerInvHandler, filter) -> getMissingCount(player.method_31548().method_5438(1), filter),
         (player, playerInvHandler, stackToAdd) -> refillSlot(
            () -> player.method_31548().method_5438(1), stackToAdd, stack -> player.method_31548().method_5447(1, stack)
         )
      ),
      TOOLBAR_3(
         "toolbar_3",
         class_2561.method_43470("3"),
         SBPTranslationHelper.INSTANCE.translUpgrade("refill.target_slot.hotbar.tooltip", new Object[]{3}).method_27692(class_124.field_1077),
         (player, playerInvHandler, filter) -> getMissingCount(player.method_31548().method_5438(2), filter),
         (player, playerInvHandler, stackToAdd) -> refillSlot(
            () -> player.method_31548().method_5438(2), stackToAdd, stack -> player.method_31548().method_5447(2, stack)
         )
      ),
      TOOLBAR_4(
         "toolbar_4",
         class_2561.method_43470("4"),
         SBPTranslationHelper.INSTANCE.translUpgrade("refill.target_slot.hotbar.tooltip", new Object[]{4}).method_27692(class_124.field_1077),
         (player, playerInvHandler, filter) -> getMissingCount(player.method_31548().method_5438(3), filter),
         (player, playerInvHandler, stackToAdd) -> refillSlot(
            () -> player.method_31548().method_5438(3), stackToAdd, stack -> player.method_31548().method_5447(3, stack)
         )
      ),
      TOOLBAR_5(
         "toolbar_5",
         class_2561.method_43470("5"),
         SBPTranslationHelper.INSTANCE.translUpgrade("refill.target_slot.hotbar.tooltip", new Object[]{5}).method_27692(class_124.field_1077),
         (player, playerInvHandler, filter) -> getMissingCount(player.method_31548().method_5438(4), filter),
         (player, playerInvHandler, stackToAdd) -> refillSlot(
            () -> player.method_31548().method_5438(4), stackToAdd, stack -> player.method_31548().method_5447(4, stack)
         )
      ),
      TOOLBAR_6(
         "toolbar_6",
         class_2561.method_43470("6"),
         SBPTranslationHelper.INSTANCE.translUpgrade("refill.target_slot.hotbar.tooltip", new Object[]{6}).method_27692(class_124.field_1077),
         (player, playerInvHandler, filter) -> getMissingCount(player.method_31548().method_5438(5), filter),
         (player, playerInvHandler, stackToAdd) -> refillSlot(
            () -> player.method_31548().method_5438(5), stackToAdd, stack -> player.method_31548().method_5447(5, stack)
         )
      ),
      TOOLBAR_7(
         "toolbar_7",
         class_2561.method_43470("7"),
         SBPTranslationHelper.INSTANCE.translUpgrade("refill.target_slot.hotbar.tooltip", new Object[]{7}).method_27692(class_124.field_1077),
         (player, playerInvHandler, filter) -> getMissingCount(player.method_31548().method_5438(6), filter),
         (player, playerInvHandler, stackToAdd) -> refillSlot(
            () -> player.method_31548().method_5438(6), stackToAdd, stack -> player.method_31548().method_5447(6, stack)
         )
      ),
      TOOLBAR_8(
         "toolbar_8",
         class_2561.method_43470("8"),
         SBPTranslationHelper.INSTANCE.translUpgrade("refill.target_slot.hotbar.tooltip", new Object[]{8}).method_27692(class_124.field_1077),
         (player, playerInvHandler, filter) -> getMissingCount(player.method_31548().method_5438(7), filter),
         (player, playerInvHandler, stackToAdd) -> refillSlot(
            () -> player.method_31548().method_5438(7), stackToAdd, stack -> player.method_31548().method_5447(7, stack)
         )
      ),
      TOOLBAR_9(
         "toolbar_9",
         class_2561.method_43470("9"),
         SBPTranslationHelper.INSTANCE.translUpgrade("refill.target_slot.hotbar.tooltip", new Object[]{9}).method_27692(class_124.field_1077),
         (player, playerInvHandler, filter) -> getMissingCount(player.method_31548().method_5438(8), filter),
         (player, playerInvHandler, stackToAdd) -> refillSlot(
            () -> player.method_31548().method_5438(8), stackToAdd, stack -> player.method_31548().method_5447(8, stack)
         )
      );

      private final String name;
      private final class_2561 acronym;
      private final class_2561 description;
      private final RefillUpgradeWrapper.TargetSlot.MissingCountGetter missingCountGetter;
      private final RefillUpgradeWrapper.TargetSlot.Filler filler;
      public static final Codec<RefillUpgradeWrapper.TargetSlot> CODEC = class_3542.method_28140(RefillUpgradeWrapper.TargetSlot::values);
      public static final class_9139<class_2540, RefillUpgradeWrapper.TargetSlot> STREAM_CODEC = StreamCodecHelper.enumCodec(
         RefillUpgradeWrapper.TargetSlot.class
      );
      private static final Map<String, RefillUpgradeWrapper.TargetSlot> NAME_VALUES;
      private static final RefillUpgradeWrapper.TargetSlot[] VALUES;

      private TargetSlot(
         String name,
         class_2561 acronym,
         class_2561 description,
         RefillUpgradeWrapper.TargetSlot.MissingCountGetter missingCountGetter,
         RefillUpgradeWrapper.TargetSlot.Filler filler
      ) {
         this.name = name;
         this.acronym = acronym;
         this.description = description;
         this.missingCountGetter = missingCountGetter;
         this.filler = filler;
      }

      public String method_15434() {
         return this.name;
      }

      public RefillUpgradeWrapper.TargetSlot next() {
         return VALUES[(this.ordinal() + 1) % VALUES.length];
      }

      public RefillUpgradeWrapper.TargetSlot previous() {
         return VALUES[Math.floorMod(this.ordinal() - 1, VALUES.length)];
      }

      public static RefillUpgradeWrapper.TargetSlot fromName(String name) {
         return NAME_VALUES.getOrDefault(name, ANY);
      }

      public class_2561 getAcronym() {
         return this.acronym;
      }

      public class_2561 getDescription() {
         return this.description;
      }

      private static class_1799 refillAnywhereInInventory(IInventoryHandlerHelper playerInvHandler, class_1799 extracted) {
         AtomicReference<class_1799> remainingStack = new AtomicReference<>(extracted);
         InventoryHelper.iterate(playerInvHandler, (slot, stack) -> {
            if (class_1799.method_31577(stack, remainingStack.get())) {
               remainingStack.set(playerInvHandler.insertItem(slot, remainingStack.get(), false));
            }
         }, () -> remainingStack.get().method_7960());
         class_1799 remaining = remainingStack.get();
         if (!remaining.method_7960()) {
            class_1799 afterInsert = InventoryHelper.insertIntoInventory(remaining, playerInvHandler, true);
            if (afterInsert.method_7947() == remaining.method_7947()) {
               return remaining;
            } else {
               class_1799 toInsert = remaining.method_7972();
               toInsert.method_7939(remaining.method_7947() - afterInsert.method_7947());
               return InventoryHelper.insertIntoInventory(toInsert, playerInvHandler, false);
            }
         } else {
            return remaining;
         }
      }

      private static int getMissingCount(class_1799 stack, class_1799 filter) {
         return class_1799.method_31577(stack, filter) ? filter.method_7914() - stack.method_7947() : filter.method_7914();
      }

      private static class_1799 refillSlot(Supplier<class_1799> getSlotContents, class_1799 stackToAdd, Consumer<class_1799> setSlotContents) {
         class_1799 contents = getSlotContents.get();
         if (contents.method_7960()) {
            setSlotContents.accept(stackToAdd);
            return class_1799.field_8037;
         } else if (class_1799.method_31577(contents, stackToAdd)) {
            contents.method_7933(stackToAdd.method_7947());
            return class_1799.field_8037;
         } else {
            return stackToAdd;
         }
      }

      static {
         Builder<String, RefillUpgradeWrapper.TargetSlot> builder = new Builder();

         for (RefillUpgradeWrapper.TargetSlot value : values()) {
            builder.put(value.method_15434(), value);
         }

         NAME_VALUES = builder.build();
         VALUES = values();
      }

      private static class Constants {
         private static final String HOTBAR_TRANSL = "refill.target_slot.hotbar.tooltip";
      }

      private interface Filler {
         class_1799 fill(class_1657 var1, IInventoryHandlerHelper var2, class_1799 var3);
      }

      private interface MissingCountGetter {
         int getMissingCount(class_1657 var1, IInventoryHandlerHelper var2, class_1799 var3);
      }
   }
}
