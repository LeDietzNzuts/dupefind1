package net.p3pp3rf1y.sophisticatedcore.common.gui;

import io.github.fabricators_of_create.porting_lib.transfer.item.SlottedStackStorage;
import java.util.function.Supplier;
import net.fabricmc.fabric.api.transfer.v1.item.ItemVariant;
import net.fabricmc.fabric.api.transfer.v1.storage.SlottedStorage;
import net.minecraft.class_1799;

public class SlotSuppliedHandler extends SlotItemHandler {
   private final Supplier<? extends SlottedStackStorage> itemHandlerSupplier;
   private final int slot;

   public SlotSuppliedHandler(Supplier<? extends SlottedStackStorage> itemHandlerSupplier, int slot, int xPosition, int yPosition) {
      super((SlottedStorage)itemHandlerSupplier.get(), slot, xPosition, yPosition);
      this.itemHandlerSupplier = itemHandlerSupplier;
      this.slot = slot;
   }

   @Override
   public boolean method_7680(class_1799 stack) {
      return this.itemHandlerSupplier.get().isItemValid(this.slot, ItemVariant.of(stack), stack.method_7947());
   }

   @Override
   public int method_7675() {
      return this.itemHandlerSupplier.get().getSlotLimit(this.slot);
   }
}
