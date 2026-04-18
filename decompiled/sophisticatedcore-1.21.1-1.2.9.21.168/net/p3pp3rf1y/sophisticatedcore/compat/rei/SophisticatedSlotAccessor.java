package net.p3pp3rf1y.sophisticatedcore.compat.rei;

import me.shedaniel.rei.api.common.transfer.info.stack.SlotAccessor;
import net.minecraft.class_1735;
import net.minecraft.class_1799;

public class SophisticatedSlotAccessor implements SlotAccessor {
   protected class_1735 slot;

   public static SlotAccessor fromSlot(class_1735 slot) {
      return new SophisticatedSlotAccessor(slot);
   }

   public SophisticatedSlotAccessor(class_1735 slot) {
      this.slot = slot;
   }

   public class_1799 getItemStack() {
      return this.slot.method_7677();
   }

   public void setItemStack(class_1799 stack) {
      this.slot.method_7673(stack);
   }

   public class_1799 takeStack(int amount) {
      return this.slot.method_7671(amount);
   }

   public class_1735 getSlot() {
      return this.slot;
   }
}
