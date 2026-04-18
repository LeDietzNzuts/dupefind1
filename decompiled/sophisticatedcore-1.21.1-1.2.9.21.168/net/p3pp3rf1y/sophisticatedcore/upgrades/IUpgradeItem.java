package net.p3pp3rf1y.sophisticatedcore.upgrades;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Predicate;
import javax.annotation.Nullable;
import net.minecraft.class_1657;
import net.minecraft.class_1792;
import net.minecraft.class_1799;
import net.minecraft.class_2561;
import net.p3pp3rf1y.sophisticatedcore.api.IStorageWrapper;
import net.p3pp3rf1y.sophisticatedcore.client.gui.utils.TranslationHelper;
import net.p3pp3rf1y.sophisticatedcore.common.gui.UpgradeSlotChangeResult;
import net.p3pp3rf1y.sophisticatedcore.util.InventoryHelper;

public interface IUpgradeItem<T extends IUpgradeWrapper> {
   UpgradeType<T> getType();

   default UpgradeSlotChangeResult canAddUpgradeTo(IStorageWrapper storageWrapper, class_1799 upgradeStack, boolean firstLevelStorage, boolean isClientSide) {
      UpgradeSlotChangeResult result = this.checkUpgradePerStorageTypeLimit(storageWrapper);
      if (!result.successful()) {
         return result;
      } else {
         result = this.checkForConflictingUpgrades(storageWrapper, this.getUpgradeConflicts(), -1);
         if (!result.successful()) {
            return result;
         } else {
            result = this.checkThisForConflictsWithExistingUpgrades(upgradeStack, storageWrapper, -1);
            return !result.successful() ? result : this.checkExtraInsertConditions(upgradeStack, storageWrapper, isClientSide, null);
         }
      }
   }

   default UpgradeSlotChangeResult checkThisForConflictsWithExistingUpgrades(class_1799 upgradeStack, IStorageWrapper storageWrapper, int excludeUpgradeSlot) {
      AtomicReference<UpgradeSlotChangeResult> result = new AtomicReference<>(UpgradeSlotChangeResult.success());
      InventoryHelper.iterate(storageWrapper.getUpgradeHandler(), (slot, stack) -> {
         if (slot != excludeUpgradeSlot && stack.method_7909() instanceof IUpgradeItem<?> upgradeItem) {
            for (IUpgradeItem.UpgradeConflictDefinition conflictDefinition : upgradeItem.getUpgradeConflicts()) {
               if (conflictDefinition.maxConflictingAllowed() == 0 && conflictDefinition.isConflictingItem.test(upgradeStack.method_7909())) {
                  result.set(UpgradeSlotChangeResult.fail(conflictDefinition.otherBeingAddedErrorMessage, Set.of(slot), Set.of(), Set.of()));
                  return;
               }
            }
         }
      }, () -> !result.get().successful());
      return result.get();
   }

   private UpgradeSlotChangeResult checkForConflictingUpgrades(
      IStorageWrapper storageWrapper, List<IUpgradeItem.UpgradeConflictDefinition> upgradeConflicts, int excludeUpgradeSlot
   ) {
      for (IUpgradeItem.UpgradeConflictDefinition conflictDefinition : upgradeConflicts) {
         AtomicInteger conflictingCount = new AtomicInteger(0);
         Set<Integer> conflictingSlots = new HashSet<>();
         InventoryHelper.iterate(storageWrapper.getUpgradeHandler(), (slot, stack) -> {
            if (slot != excludeUpgradeSlot && !stack.method_7960() && conflictDefinition.isConflictingItem.test(stack.method_7909())) {
               conflictingCount.incrementAndGet();
               conflictingSlots.add(slot);
            }
         });
         if (conflictingCount.get() > conflictDefinition.maxConflictingAllowed) {
            return UpgradeSlotChangeResult.fail(conflictDefinition.errorMessage, conflictingSlots, Set.of(), Set.of());
         }
      }

      return UpgradeSlotChangeResult.success();
   }

   List<IUpgradeItem.UpgradeConflictDefinition> getUpgradeConflicts();

   private UpgradeSlotChangeResult checkUpgradePerStorageTypeLimit(IStorageWrapper storageWrapper) {
      int upgradesPerStorage = this.getUpgradesPerStorage(storageWrapper.getStorageType());
      int upgradesInGroupPerStorage = this.getUpgradesInGroupPerStorage(storageWrapper.getStorageType());
      if (upgradesPerStorage == Integer.MAX_VALUE && upgradesInGroupPerStorage == Integer.MAX_VALUE) {
         return UpgradeSlotChangeResult.success();
      } else if (upgradesPerStorage == 0) {
         return UpgradeSlotChangeResult.fail(
            TranslationHelper.INSTANCE.translError("add.upgrade_not_allowed", this.getName(), storageWrapper.getDisplayName()), Set.of(), Set.of(), Set.of()
         );
      } else if (upgradesInGroupPerStorage == 0) {
         return UpgradeSlotChangeResult.fail(
            TranslationHelper.INSTANCE
               .translError("add.upgrade_not_allowed", class_2561.method_43471(this.getUpgradeGroup().translName()), storageWrapper.getDisplayName()),
            Set.of(),
            Set.of(),
            Set.of()
         );
      } else {
         Set<Integer> slotsWithUpgrade = new HashSet<>();
         InventoryHelper.iterate(storageWrapper.getUpgradeHandler(), (slot, stack) -> {
            if (stack.method_7909() == this) {
               slotsWithUpgrade.add(slot);
            }
         });
         if (slotsWithUpgrade.size() >= upgradesPerStorage) {
            return UpgradeSlotChangeResult.fail(
               TranslationHelper.INSTANCE
                  .translError("add.only_x_upgrades_allowed", upgradesPerStorage, this.getName(), storageWrapper.getDisplayName(), upgradesPerStorage),
               slotsWithUpgrade,
               Set.of(),
               Set.of()
            );
         } else {
            Set<Integer> slotsWithUgradeGroup = new HashSet<>();
            InventoryHelper.iterate(storageWrapper.getUpgradeHandler(), (slot, stack) -> {
               if (stack.method_7909() instanceof IUpgradeItem<?> upgradeItem && upgradeItem.getUpgradeGroup() == this.getUpgradeGroup()) {
                  slotsWithUgradeGroup.add(slot);
               }
            });
            return slotsWithUgradeGroup.size() >= upgradesInGroupPerStorage
               ? UpgradeSlotChangeResult.fail(
                  TranslationHelper.INSTANCE
                     .translError(
                        "add.only_x_upgrades_allowed",
                        upgradesInGroupPerStorage,
                        class_2561.method_43471(this.getUpgradeGroup().translName()),
                        storageWrapper.getDisplayName()
                     ),
                  slotsWithUgradeGroup,
                  Set.of(),
                  Set.of()
               )
               : UpgradeSlotChangeResult.success();
         }
      }
   }

   default UpgradeSlotChangeResult canRemoveUpgradeFrom(IStorageWrapper storageWrapper, boolean isClientSide, class_1657 player) {
      return this.canRemoveUpgradeFrom(storageWrapper, isClientSide);
   }

   default UpgradeSlotChangeResult canRemoveUpgradeFrom(IStorageWrapper storageWrapper, boolean isClientSide) {
      return UpgradeSlotChangeResult.success();
   }

   default UpgradeSlotChangeResult canSwapUpgradeFor(class_1799 upgradeStackToPut, int upgradeSlot, IStorageWrapper storageWrapper, boolean isClientSide) {
      if (upgradeStackToPut.method_7909() == this) {
         return UpgradeSlotChangeResult.success();
      } else if (upgradeStackToPut.method_7909() instanceof IUpgradeItem<?> upgradeToPut) {
         int upgradesPerStorage = upgradeToPut.getUpgradesPerStorage(storageWrapper.getStorageType());
         int upgradesInGroupPerStorage = upgradeToPut.getUpgradesInGroupPerStorage(storageWrapper.getStorageType());
         if (upgradesPerStorage < upgradesInGroupPerStorage) {
            UpgradeSlotChangeResult result = upgradeToPut.checkUpgradePerStorageTypeLimit(storageWrapper);
            if (!result.successful()) {
               return result;
            }
         } else if (upgradeToPut.getUpgradeGroup() != this.getUpgradeGroup()) {
            UpgradeSlotChangeResult result = upgradeToPut.checkUpgradePerStorageTypeLimit(storageWrapper);
            if (!result.successful()) {
               return result;
            }
         }

         UpgradeSlotChangeResult result = this.checkForConflictingUpgrades(storageWrapper, upgradeToPut.getUpgradeConflicts(), upgradeSlot);
         return !result.successful() ? result : upgradeToPut.checkExtraInsertConditions(upgradeStackToPut, storageWrapper, isClientSide, this);
      } else {
         return UpgradeSlotChangeResult.success();
      }
   }

   default UpgradeSlotChangeResult checkExtraInsertConditions(
      class_1799 upgradeStack, IStorageWrapper storageWrapper, boolean isClientSide, @Nullable IUpgradeItem<?> upgradeInSlot
   ) {
      return UpgradeSlotChangeResult.success();
   }

   default int getInventoryColumnsTaken() {
      return 0;
   }

   default class_1799 getCleanedUpgradeStack(class_1799 upgradeStack) {
      return upgradeStack;
   }

   int getUpgradesPerStorage(String var1);

   int getUpgradesInGroupPerStorage(String var1);

   default UpgradeGroup getUpgradeGroup() {
      return UpgradeGroup.NONE;
   }

   class_2561 getName();

   public record UpgradeConflictDefinition(
      Predicate<class_1792> isConflictingItem, int maxConflictingAllowed, class_2561 errorMessage, class_2561 otherBeingAddedErrorMessage
   ) {
      public UpgradeConflictDefinition(Predicate<class_1792> isConflictingItem, int maxConflictingAllowed, class_2561 errorMessage) {
         this(isConflictingItem, maxConflictingAllowed, errorMessage, errorMessage);
      }
   }
}
