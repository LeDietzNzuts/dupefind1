package net.p3pp3rf1y.sophisticatedbackpacks.upgrades.anvil;

import io.github.fabricators_of_create.porting_lib.transfer.item.SlottedStackStorage;
import java.util.function.Supplier;
import javax.annotation.Nullable;
import net.minecraft.class_1263;
import net.minecraft.class_1277;
import net.minecraft.class_1657;
import net.minecraft.class_1661;
import net.minecraft.class_1706;
import net.minecraft.class_1734;
import net.minecraft.class_1735;
import net.minecraft.class_1799;
import net.minecraft.class_2487;
import net.minecraft.class_3914;
import net.minecraft.class_8047;
import net.minecraft.class_8047.class_8049;
import net.p3pp3rf1y.sophisticatedcore.common.gui.SlotSuppliedHandler;
import net.p3pp3rf1y.sophisticatedcore.common.gui.UpgradeContainerBase;
import net.p3pp3rf1y.sophisticatedcore.common.gui.UpgradeContainerType;
import net.p3pp3rf1y.sophisticatedcore.upgrades.IUpgradeWrapper;
import net.p3pp3rf1y.sophisticatedcore.util.NBTHelper;

public class AnvilUpgradeContainer extends UpgradeContainerBase<AnvilUpgradeWrapper, AnvilUpgradeContainer> {
   private static final String DATA_SHIFT_CLICK_INTO_STORAGE = "shiftClickIntoStorage";
   private final class_1735 resultSlot;
   private final AnvilUpgradeContainer.PersistableAnvilMenu anvilMenuDelegate;
   private Runnable nameChangeListener = () -> {};
   private boolean processingOnTakeLogic = false;

   public AnvilUpgradeContainer(
      class_1657 player, int upgradeContainerId, AnvilUpgradeWrapper upgradeWrapper, UpgradeContainerType<AnvilUpgradeWrapper, AnvilUpgradeContainer> type
   ) {
      super(player, upgradeContainerId, upgradeWrapper, type);
      this.anvilMenuDelegate = new AnvilUpgradeContainer.PersistableAnvilMenu(new class_1661(player));
      this.slots.add(this.anvilMenuDelegate.method_7611(0));
      this.slots.add(this.anvilMenuDelegate.method_7611(1));
      this.resultSlot = this.anvilMenuDelegate.method_7611(2);
      this.slots.add(this.resultSlot);
   }

   public void setNameChangeListener(Runnable nameChangeListener) {
      this.nameChangeListener = nameChangeListener;
   }

   public void handlePacket(class_2487 data) {
      if (data.method_10545("shiftClickIntoStorage")) {
         this.setShiftClickIntoStorage(data.method_10577("shiftClickIntoStorage"));
      } else if (data.method_10545("itemName")) {
         this.setItemName(data.method_10558("itemName"));
      }
   }

   public void setUpgradeWrapper(IUpgradeWrapper updatedUpgradeWrapper) {
      super.setUpgradeWrapper(updatedUpgradeWrapper);
      this.anvilMenuDelegate.method_7625(((AnvilUpgradeWrapper)this.upgradeWrapper).getItemName());
      this.anvilMenuDelegate.method_24928();
      this.nameChangeListener.run();
   }

   public boolean shouldShiftClickIntoStorage() {
      return ((AnvilUpgradeWrapper)this.upgradeWrapper).shouldShiftClickIntoStorage();
   }

   public boolean isProcessingOnTakeLogic() {
      return this.processingOnTakeLogic;
   }

   public void setShiftClickIntoStorage(boolean shiftClickIntoStorage) {
      ((AnvilUpgradeWrapper)this.upgradeWrapper).setShiftClickIntoStorage(shiftClickIntoStorage);
      this.sendDataToServer(() -> NBTHelper.putBoolean(new class_2487(), "shiftClickIntoStorage", shiftClickIntoStorage));
   }

   public boolean mergeIntoStorageFirst(class_1735 slot) {
      return !(slot instanceof class_1734) || this.shouldShiftClickIntoStorage();
   }

   public boolean allowsPickupAll(class_1735 slot) {
      return slot != this.resultSlot;
   }

   public void setItemName(String name) {
      this.anvilMenuDelegate.method_7625(name);
      ((AnvilUpgradeWrapper)this.upgradeWrapper).setItemName(name);
      this.sendDataToServer(() -> NBTHelper.putString(new class_2487(), "itemName", name));
   }

   public int getCost() {
      return this.anvilMenuDelegate.method_17369();
   }

   @Nullable
   public String getItemName() {
      return ((AnvilUpgradeWrapper)this.upgradeWrapper).getItemName();
   }

   private class PersistableAnvilMenu extends class_1706 {
      public PersistableAnvilMenu(class_1661 playerInventory) {
         super(
            0,
            playerInventory,
            playerInventory.field_7546.method_37908().field_9236
               ? class_3914.field_17304
               : class_3914.method_17392(playerInventory.field_7546.method_37908(), playerInventory.field_7546.method_24515())
         );
         super.method_7625(((AnvilUpgradeWrapper)AnvilUpgradeContainer.this.upgradeWrapper).getItemName());
      }

      protected void method_48354(class_8047 itemCombinerMenuSlotDefinition) {
         for (final class_8049 slotDefinition : itemCombinerMenuSlotDefinition.method_48368()) {
            this.method_7621(
               new SlotSuppliedHandler(((AnvilUpgradeWrapper)AnvilUpgradeContainer.this.upgradeWrapper)::getInventory, slotDefinition.comp_1204(), 0, 0) {
                  public void method_7668() {
                     super.method_7668();
                     PersistableAnvilMenu.this.method_7609(PersistableAnvilMenu.this.field_22480);
                     if (slotDefinition.comp_1204() == 0) {
                        if (((AnvilUpgradeWrapper)AnvilUpgradeContainer.this.upgradeWrapper).getItemName().isEmpty() != this.method_7677().method_7960()) {
                           String newItemName = this.method_7677().method_7960() ? "" : this.method_7677().method_7964().getString();
                           ((AnvilUpgradeWrapper)AnvilUpgradeContainer.this.upgradeWrapper).setItemName(newItemName);
                           PersistableAnvilMenu.this.method_7625(newItemName);
                           AnvilUpgradeContainer.this.nameChangeListener.run();
                        }

                        if (this.method_7677().method_7960()) {
                           PersistableAnvilMenu.this.method_7625("");
                           ((AnvilUpgradeWrapper)AnvilUpgradeContainer.this.upgradeWrapper).setItemName("");
                        }
                     }
                  }

                  public boolean method_7680(class_1799 p_267156_) {
                     return slotDefinition.comp_1207().test(p_267156_);
                  }
               }
            );
         }
      }

      protected class_1277 method_48358(int size) {
         return new class_1277(size) {
            public void method_5431() {
               super.method_5431();
               PersistableAnvilMenu.this.method_7609(this);
            }

            public class_1799 method_5438(int pIndex) {
               return ((AnvilUpgradeWrapper)AnvilUpgradeContainer.this.upgradeWrapper).getInventory().getStackInSlot(pIndex);
            }

            public void method_5447(int pIndex, class_1799 pStack) {
               ((AnvilUpgradeWrapper)AnvilUpgradeContainer.this.upgradeWrapper).getInventory().setStackInSlot(pIndex, pStack);
            }
         };
      }

      public void method_7609(class_1263 pInventory) {
         this.method_24928();
      }

      protected void method_24923(class_1657 player, class_1799 stack) {
         AnvilUpgradeContainer.this.processingOnTakeLogic = true;
         super.method_24923(player, stack);
         AnvilUpgradeContainer.this.processingOnTakeLogic = false;
      }
   }
}
