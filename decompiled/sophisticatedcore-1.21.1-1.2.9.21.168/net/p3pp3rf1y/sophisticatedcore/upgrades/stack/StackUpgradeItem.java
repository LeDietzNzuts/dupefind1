package net.p3pp3rf1y.sophisticatedcore.upgrades.stack;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Consumer;
import net.minecraft.class_1799;
import net.p3pp3rf1y.sophisticatedcore.api.IStorageWrapper;
import net.p3pp3rf1y.sophisticatedcore.client.gui.utils.TranslationHelper;
import net.p3pp3rf1y.sophisticatedcore.common.gui.UpgradeSlotChangeResult;
import net.p3pp3rf1y.sophisticatedcore.upgrades.IStackableContentsUpgrade;
import net.p3pp3rf1y.sophisticatedcore.upgrades.IUpgradeCountLimitConfig;
import net.p3pp3rf1y.sophisticatedcore.upgrades.IUpgradeItem;
import net.p3pp3rf1y.sophisticatedcore.upgrades.UpgradeGroup;
import net.p3pp3rf1y.sophisticatedcore.upgrades.UpgradeItemBase;
import net.p3pp3rf1y.sophisticatedcore.upgrades.UpgradeType;
import net.p3pp3rf1y.sophisticatedcore.upgrades.UpgradeWrapperBase;

public class StackUpgradeItem extends UpgradeItemBase<StackUpgradeItem.Wrapper> {
   public static final UpgradeType<StackUpgradeItem.Wrapper> TYPE = new UpgradeType<>(StackUpgradeItem.Wrapper::new);
   public static final UpgradeGroup UPGRADE_GROUP = new UpgradeGroup("stack_upgrades", TranslationHelper.INSTANCE.translUpgradeGroup("stack_upgrades"));
   private final double stackSizeMultiplier;

   public StackUpgradeItem(double stackSizeMultiplier, IUpgradeCountLimitConfig upgradeTypeLimitConfig) {
      super(upgradeTypeLimitConfig);
      this.stackSizeMultiplier = stackSizeMultiplier;
   }

   public static int getInventorySlotLimit(IStorageWrapper storageWrapper) {
      return getInventorySlotLimit(storageWrapper, 1.0);
   }

   private static int getInventorySlotLimit(IStorageWrapper storageWrapper, double skipMultiplier) {
      double multiplier = storageWrapper.getBaseStackSizeMultiplier();
      boolean multiplierSkipped = false;

      for (StackUpgradeItem.Wrapper stackWrapper : storageWrapper.getUpgradeHandler().getTypeWrappers(TYPE)) {
         if (!multiplierSkipped && stackWrapper.getStackSizeMultiplier() == skipMultiplier) {
            multiplierSkipped = true;
         } else {
            if (2.147483647E9 / stackWrapper.getStackSizeMultiplier() < multiplier) {
               return Integer.MAX_VALUE;
            }

            multiplier *= stackWrapper.getStackSizeMultiplier();
         }
      }

      return 3.3554431984375E7 < multiplier ? Integer.MAX_VALUE : (int)(multiplier * 64.0);
   }

   @Override
   public UpgradeType<StackUpgradeItem.Wrapper> getType() {
      return TYPE;
   }

   @Override
   public List<IUpgradeItem.UpgradeConflictDefinition> getUpgradeConflicts() {
      return List.of();
   }

   public double getStackSizeMultiplier() {
      return this.stackSizeMultiplier;
   }

   @Override
   public UpgradeSlotChangeResult canRemoveUpgradeFrom(IStorageWrapper storageWrapper, boolean isClientSide) {
      if (isClientSide) {
         return UpgradeSlotChangeResult.success();
      } else {
         double multiplierWhenRemoved = getInventorySlotLimit(storageWrapper, this.stackSizeMultiplier) / 64.0;
         return this.isMultiplierHighEnough(storageWrapper, multiplierWhenRemoved);
      }
   }

   @Override
   public UpgradeSlotChangeResult canAddUpgradeTo(IStorageWrapper storageWrapper, class_1799 upgradeStack, boolean firstLevelStorage, boolean isClientSide) {
      double multiplierWhenAdded = getInventorySlotLimit(storageWrapper) / 64.0 * this.stackSizeMultiplier;
      UpgradeSlotChangeResult result = this.isMultiplierHighEnough(storageWrapper, multiplierWhenAdded);
      return !result.successful() ? result : super.canAddUpgradeTo(storageWrapper, upgradeStack, firstLevelStorage, isClientSide);
   }

   @Override
   public UpgradeSlotChangeResult canSwapUpgradeFor(class_1799 upgradeStackToPut, int upgradeSlot, IStorageWrapper storageWrapper, boolean isClientSide) {
      UpgradeSlotChangeResult result = super.canSwapUpgradeFor(upgradeStackToPut, upgradeSlot, storageWrapper, isClientSide);
      if (!result.successful()) {
         return result;
      } else if (isClientSide) {
         return UpgradeSlotChangeResult.success();
      } else if (upgradeStackToPut.method_7909() instanceof StackUpgradeItem otherStackUpgradeItem) {
         if (otherStackUpgradeItem.stackSizeMultiplier >= this.stackSizeMultiplier) {
            return UpgradeSlotChangeResult.success();
         } else {
            double multiplierWhenRemoved = getInventorySlotLimit(storageWrapper, this.stackSizeMultiplier) / 64.0;
            return this.isMultiplierHighEnough(storageWrapper, multiplierWhenRemoved * otherStackUpgradeItem.stackSizeMultiplier);
         }
      } else {
         return this.canRemoveUpgradeFrom(storageWrapper, isClientSide);
      }
   }

   private UpgradeSlotChangeResult isMultiplierHighEnough(IStorageWrapper storageWrapper, double multiplier) {
      Set<Integer> slotsOverMultiplier = new HashSet<>();

      for (int slot = 0; slot < storageWrapper.getInventoryHandler().getSlotCount(); slot++) {
         class_1799 stack = storageWrapper.getInventoryHandler().getSlotStack(slot);
         if (stack.method_7947() > 1) {
            double stackMultiplierNeeded = (double)stack.method_7947() / stack.method_7914();
            if (stackMultiplierNeeded > multiplier) {
               slotsOverMultiplier.add(slot);
            }
         }
      }

      Set<Integer> errorInventoryParts = new HashSet<>();
      storageWrapper.getUpgradeHandler().getSlotWrappers().forEach((slotx, wrapper) -> {
         if (wrapper instanceof IStackableContentsUpgrade stackableContentsUpgrade && stackableContentsUpgrade.getMinimumMultiplierRequired() > multiplier) {
            errorInventoryParts.add(slotx);
         }
      });
      return slotsOverMultiplier.isEmpty() && errorInventoryParts.isEmpty()
         ? UpgradeSlotChangeResult.success()
         : UpgradeSlotChangeResult.fail(
            TranslationHelper.INSTANCE.translError("remove.stack_low_multiplier", multiplier), Collections.emptySet(), slotsOverMultiplier, errorInventoryParts
         );
   }

   @Override
   public UpgradeGroup getUpgradeGroup() {
      return UPGRADE_GROUP;
   }

   public static class Wrapper extends UpgradeWrapperBase<StackUpgradeItem.Wrapper, StackUpgradeItem> {
      protected Wrapper(IStorageWrapper storageWrapper, class_1799 upgrade, Consumer<class_1799> upgradeSaveHandler) {
         super(storageWrapper, upgrade, upgradeSaveHandler);
      }

      public double getStackSizeMultiplier() {
         return this.upgradeItem.getStackSizeMultiplier();
      }

      @Override
      public boolean hideSettingsTab() {
         return true;
      }

      @Override
      public boolean canBeDisabled() {
         return false;
      }
   }
}
