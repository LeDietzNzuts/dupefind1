package net.p3pp3rf1y.sophisticatedcore.common.gui;

import io.github.fabricators_of_create.porting_lib.transfer.item.SlottedStackStorage;
import java.util.function.Supplier;
import net.minecraft.class_1657;
import net.minecraft.class_1799;

public class FilterSlotItemHandler extends SlotSuppliedHandler implements IFilterSlot {
   public FilterSlotItemHandler(Supplier<? extends SlottedStackStorage> itemHandlerSupplier, int slot, int xPosition, int yPosition) {
      super(itemHandlerSupplier, slot, xPosition, yPosition);
   }

   @Override
   public boolean method_7674(class_1657 playerIn) {
      return false;
   }

   @Override
   public int method_7676(class_1799 stack) {
      return 1;
   }
}
