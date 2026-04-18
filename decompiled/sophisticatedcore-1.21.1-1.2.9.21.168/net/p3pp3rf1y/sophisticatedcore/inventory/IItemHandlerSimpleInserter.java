package net.p3pp3rf1y.sophisticatedcore.inventory;

import io.github.fabricators_of_create.porting_lib.transfer.item.SlottedStackStorage;
import net.fabricmc.fabric.api.transfer.v1.item.ItemVariant;
import net.minecraft.class_1799;

public interface IItemHandlerSimpleInserter extends SlottedStackStorage, IInventoryHandlerHelper {
   default boolean isItemValid(int slot, class_1799 stack) {
      return this.isItemValid(slot, ItemVariant.of(stack), stack.method_7947());
   }

   default boolean isItemValid(int slot, ItemVariant resource, int count) {
      return this.isItemValid(slot, resource.toStack(count));
   }
}
