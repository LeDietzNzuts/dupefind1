package net.p3pp3rf1y.sophisticatedcore.mixin.common;

import net.minecraft.class_1799;
import net.minecraft.class_2371;
import net.minecraft.class_9288;
import net.p3pp3rf1y.sophisticatedcore.extensions.item.component.SophisticatedItemContainerContents;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;

@Mixin(class_9288.class)
public class ItemContainerContentsMixin implements SophisticatedItemContainerContents {
   @Shadow
   @Final
   private class_2371<class_1799> field_49338;

   @Override
   public int sophisticatedCore_getSlots() {
      return this.field_49338.size();
   }

   @Override
   public class_1799 sophisticatedCore_getStackInSlot(int slot) {
      this.sophisticatedCore_validateSlotIndex(slot);
      return ((class_1799)this.field_49338.get(slot)).method_7972();
   }

   @Unique
   private void sophisticatedCore_validateSlotIndex(int slot) {
      if (slot < 0 || slot >= this.sophisticatedCore_getSlots()) {
         throw new UnsupportedOperationException("Slot " + slot + " not in valid range - [0," + this.sophisticatedCore_getSlots() + ")");
      }
   }
}
