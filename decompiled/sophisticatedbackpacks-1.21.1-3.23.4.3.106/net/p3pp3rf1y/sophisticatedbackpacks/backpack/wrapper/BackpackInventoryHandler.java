package net.p3pp3rf1y.sophisticatedbackpacks.backpack.wrapper;

import net.minecraft.class_1799;
import net.minecraft.class_2487;
import net.p3pp3rf1y.sophisticatedbackpacks.Config;
import net.p3pp3rf1y.sophisticatedbackpacks.backpack.BackpackItem;
import net.p3pp3rf1y.sophisticatedbackpacks.upgrades.inception.InceptionUpgradeItem;
import net.p3pp3rf1y.sophisticatedcore.api.IStorageWrapper;
import net.p3pp3rf1y.sophisticatedcore.inventory.InventoryHandler;

public class BackpackInventoryHandler extends InventoryHandler {
   public BackpackInventoryHandler(int numberOfInventorySlots, IStorageWrapper storageWrapper, class_2487 contentsNbt, Runnable saveHandler, int slotLimit) {
      super(numberOfInventorySlots, storageWrapper, contentsNbt, saveHandler, slotLimit, Config.SERVER.stackUpgrade);
   }

   protected boolean isAllowed(class_1799 stack) {
      return !Config.SERVER.disallowedItems.isItemDisallowed(stack.method_7909())
         && (!(stack.method_7909() instanceof BackpackItem) || this.hasInceptionUpgrade() && this.isBackpackWithoutInceptionUpgrade(stack));
   }

   private boolean hasInceptionUpgrade() {
      return this.storageWrapper.getUpgradeHandler().hasUpgrade(InceptionUpgradeItem.TYPE);
   }

   private boolean isBackpackWithoutInceptionUpgrade(class_1799 stack) {
      return stack.method_7909() instanceof BackpackItem && !BackpackWrapper.fromStack(stack).getUpgradeHandler().hasUpgrade(InceptionUpgradeItem.TYPE);
   }
}
