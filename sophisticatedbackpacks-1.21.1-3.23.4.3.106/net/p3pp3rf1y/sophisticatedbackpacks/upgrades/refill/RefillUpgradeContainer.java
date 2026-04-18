package net.p3pp3rf1y.sophisticatedbackpacks.upgrades.refill;

import java.util.Optional;
import net.minecraft.class_1657;
import net.minecraft.class_2487;
import net.p3pp3rf1y.sophisticatedcore.common.gui.UpgradeContainerBase;
import net.p3pp3rf1y.sophisticatedcore.common.gui.UpgradeContainerType;
import net.p3pp3rf1y.sophisticatedcore.upgrades.FilterLogic;
import net.p3pp3rf1y.sophisticatedcore.upgrades.FilterLogicContainer;
import net.p3pp3rf1y.sophisticatedcore.util.NBTHelper;

public class RefillUpgradeContainer extends UpgradeContainerBase<RefillUpgradeWrapper, RefillUpgradeContainer> {
   private static final String DATA_SET_TARGET_SLOT = "setTargetSlot";
   private final FilterLogicContainer<FilterLogic> filterLogicContainer = new FilterLogicContainer(
      () -> ((RefillUpgradeWrapper)this.upgradeWrapper).getFilterLogic(), this, this.slots::add
   );

   public RefillUpgradeContainer(
      class_1657 player, int containerId, RefillUpgradeWrapper wrapper, UpgradeContainerType<RefillUpgradeWrapper, RefillUpgradeContainer> type
   ) {
      super(player, containerId, wrapper, type);
   }

   public FilterLogicContainer<FilterLogic> getFilterLogicContainer() {
      return this.filterLogicContainer;
   }

   public void setTargetSlot(int slot, RefillUpgradeWrapper.TargetSlot targetSlot) {
      ((RefillUpgradeWrapper)this.upgradeWrapper).setTargetSlot(slot, targetSlot);
      this.sendDataToServer(() -> {
         class_2487 tag = new class_2487();
         tag.method_10566("setTargetSlot", NBTHelper.putInt(NBTHelper.putEnumConstant(new class_2487(), "targetSlot", targetSlot), "slot", slot));
         return tag;
      });
   }

   public RefillUpgradeWrapper.TargetSlot getTargetSlot(int slot) {
      RefillUpgradeWrapper.TargetSlot targetSlot = ((RefillUpgradeWrapper)this.upgradeWrapper).getTargetSlots().get(slot);
      return targetSlot != null ? targetSlot : RefillUpgradeWrapper.TargetSlot.ANY;
   }

   public void handlePacket(class_2487 data) {
      this.filterLogicContainer.handlePacket(data);
      if (data.method_10545("setTargetSlot")) {
         class_2487 tag = data.method_10562("setTargetSlot");
         Optional<Integer> slot = NBTHelper.getInt(tag, "slot");
         Optional<RefillUpgradeWrapper.TargetSlot> targetSlot = NBTHelper.getEnumConstant(tag, "targetSlot", RefillUpgradeWrapper.TargetSlot::fromName);
         if (slot.isPresent() && targetSlot.isPresent()) {
            this.setTargetSlot(slot.get(), targetSlot.get());
         }
      }
   }

   public boolean allowsTargetSlotSelection() {
      return ((RefillUpgradeWrapper)this.upgradeWrapper).allowsTargetSlotSelection();
   }
}
