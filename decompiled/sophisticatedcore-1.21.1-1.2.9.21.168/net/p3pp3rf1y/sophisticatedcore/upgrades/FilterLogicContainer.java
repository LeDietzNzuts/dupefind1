package net.p3pp3rf1y.sophisticatedcore.upgrades;

import io.github.fabricators_of_create.porting_lib.transfer.item.ItemStackHandler;
import io.github.fabricators_of_create.porting_lib.transfer.item.SlottedStackStorage;
import java.util.function.Consumer;
import java.util.function.Supplier;
import net.minecraft.class_1735;
import net.p3pp3rf1y.sophisticatedcore.common.gui.FilterSlotItemHandler;
import net.p3pp3rf1y.sophisticatedcore.common.gui.IServerUpdater;
import net.p3pp3rf1y.sophisticatedcore.util.InventoryHelper;

public class FilterLogicContainer<T extends FilterLogic> extends FilterLogicContainerBase<T, FilterLogicContainer.FilterLogicSlot> {
   public FilterLogicContainer(Supplier<T> filterLogic, IServerUpdater serverUpdater, Consumer<class_1735> addSlot) {
      super(serverUpdater, filterLogic, addSlot);
      ItemStackHandler filterHandler = filterLogic.get().getFilterHandler();
      InventoryHelper.iterate(filterHandler, (slot, stack) -> {
         FilterLogicContainer.FilterLogicSlot filterSlot = new FilterLogicContainer.FilterLogicSlot(() -> filterLogic.get().getFilterHandler(), slot);
         addSlot.accept(filterSlot);
         this.filterSlots.add(filterSlot);
      });
   }

   public static class FilterLogicSlot extends FilterSlotItemHandler {
      private boolean enabled = true;

      public FilterLogicSlot(Supplier<SlottedStackStorage> filterHandler, Integer slot) {
         super(filterHandler, slot, -100, -100);
      }

      public void setEnabled(boolean enabled) {
         this.enabled = enabled;
      }

      public boolean method_7682() {
         return this.enabled;
      }
   }
}
