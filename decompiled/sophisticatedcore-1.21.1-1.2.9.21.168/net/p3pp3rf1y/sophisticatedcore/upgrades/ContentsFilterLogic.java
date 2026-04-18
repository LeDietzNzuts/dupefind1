package net.p3pp3rf1y.sophisticatedcore.upgrades;

import io.github.fabricators_of_create.porting_lib.util.DeferredHolder;
import java.util.function.Consumer;
import java.util.function.Supplier;
import net.minecraft.class_1799;
import net.minecraft.class_9331;
import net.p3pp3rf1y.sophisticatedcore.inventory.InventoryHandler;
import net.p3pp3rf1y.sophisticatedcore.inventory.ItemStackKey;
import net.p3pp3rf1y.sophisticatedcore.settings.memory.MemorySettingsCategory;

public class ContentsFilterLogic extends FilterLogic {
   private final Supplier<InventoryHandler> getInventoryHandler;
   private final MemorySettingsCategory memorySettings;

   public ContentsFilterLogic(
      class_1799 upgrade,
      Consumer<class_1799> saveHandler,
      int filterSlotCount,
      Supplier<InventoryHandler> getInventoryHandler,
      MemorySettingsCategory memorySettings,
      DeferredHolder<class_9331<?>, class_9331<FilterAttributes>> filterAttributesComponent
   ) {
      super(upgrade, saveHandler, filterSlotCount, filterAttributesComponent);
      this.getInventoryHandler = getInventoryHandler;
      this.memorySettings = memorySettings;
   }

   public ContentsFilterType getFilterType() {
      if (this.shouldFilterByStorage()) {
         return ContentsFilterType.STORAGE;
      } else {
         return this.isAllowList() ? ContentsFilterType.ALLOW : ContentsFilterType.BLOCK;
      }
   }

   public void setDepositFilterType(ContentsFilterType contentsFilterType) {
      switch (contentsFilterType) {
         case ALLOW:
            this.setFilterByStorage(false);
            this.setAllowList(true);
            break;
         case BLOCK:
            this.setFilterByStorage(false);
            this.setAllowList(false);
            break;
         case STORAGE:
            this.setFilterByStorage(true);
            this.save();
      }
   }

   @Override
   public boolean matchesFilter(class_1799 stack) {
      if (!this.shouldFilterByStorage()) {
         return super.matchesFilter(stack);
      } else {
         for (ItemStackKey filterStack : this.getInventoryHandler.get().getSlotTracker().getFullStacks()) {
            if (this.stackMatchesFilter(stack, filterStack.getStack())) {
               return true;
            }
         }

         for (ItemStackKey filterStackx : this.getInventoryHandler.get().getSlotTracker().getPartialStacks()) {
            if (this.stackMatchesFilter(stack, filterStackx.getStack())) {
               return true;
            }
         }

         return this.memorySettings.matchesFilter(stack);
      }
   }

   private void setFilterByStorage(boolean filterByStorage) {
      this.setAttributes(attributes -> attributes.setFilterByStorage(filterByStorage));
      this.save();
   }

   protected boolean shouldFilterByStorage() {
      return this.getAttributes().filterByStorage();
   }
}
