package net.p3pp3rf1y.sophisticatedcore.common.gui;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;
import net.minecraft.class_1657;
import net.minecraft.class_1735;
import net.minecraft.class_1799;
import net.minecraft.class_2487;
import net.p3pp3rf1y.sophisticatedcore.network.PacketDistributor;
import net.p3pp3rf1y.sophisticatedcore.network.SyncContainerClientDataPayload;
import net.p3pp3rf1y.sophisticatedcore.upgrades.IUpgradeWrapper;
import net.p3pp3rf1y.sophisticatedcore.util.NBTHelper;

public abstract class UpgradeContainerBase<W extends IUpgradeWrapper, C extends UpgradeContainerBase<W, C>> implements IServerUpdater {
   protected final ArrayList<class_1735> slots = new ArrayList<>();
   private final int upgradeContainerId;
   protected W upgradeWrapper;
   protected final class_1657 player;
   private final UpgradeContainerType<W, C> type;
   private boolean isOpen = false;

   protected UpgradeContainerBase(class_1657 player, int upgradeContainerId, W upgradeWrapper, UpgradeContainerType<W, C> type) {
      this.upgradeContainerId = upgradeContainerId;
      this.upgradeWrapper = upgradeWrapper;
      this.player = player;
      this.type = type;
   }

   public List<class_1735> getSlots() {
      return this.slots;
   }

   public UpgradeContainerType<W, C> getType() {
      return this.type;
   }

   public void setIsOpen(boolean isOpen) {
      this.isOpen = isOpen;
   }

   public boolean isOpen() {
      return this.isOpen;
   }

   @Override
   public void sendBooleanToServer(String key, boolean value) {
      if (this.player.method_37908().field_9236) {
         this.sendDataToServer(() -> NBTHelper.putBoolean(new class_2487(), key, value));
      }
   }

   @Override
   public void sendDataToServer(Supplier<class_2487> supplyData) {
      if (this.player.method_37908().field_9236) {
         class_2487 data = supplyData.get();
         data.method_10569("containerId", this.upgradeContainerId);
         PacketDistributor.sendToServer(new SyncContainerClientDataPayload(data));
      }
   }

   public void onInit() {
   }

   public abstract void handlePacket(class_2487 var1);

   public class_1799 getUpgradeStack() {
      return this.upgradeWrapper.getUpgradeStack();
   }

   public W getUpgradeWrapper() {
      return this.upgradeWrapper;
   }

   public void setUpgradeWrapper(IUpgradeWrapper updatedUpgradeWrapper) {
      this.upgradeWrapper = (W)updatedUpgradeWrapper;
   }

   public boolean containsSlot(class_1735 slot) {
      for (class_1735 containerSlot : this.slots) {
         if (containerSlot == slot) {
            return true;
         }
      }

      return false;
   }

   public class_1799 getSlotStackToTransfer(class_1735 slot) {
      return slot.method_7677();
   }

   public void onTakeFromSlot(class_1735 slot, class_1657 player, class_1799 slotStack) {
      slot.method_7667(player, slotStack);
   }

   public boolean mergeIntoStorageFirst(class_1735 slot) {
      return true;
   }

   public boolean allowsPickupAll(class_1735 slot) {
      return true;
   }

   public int getUpgradeContainerId() {
      return this.upgradeContainerId;
   }
}
