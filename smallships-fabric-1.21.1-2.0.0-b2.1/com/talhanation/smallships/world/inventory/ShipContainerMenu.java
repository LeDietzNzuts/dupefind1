package com.talhanation.smallships.world.inventory;

import com.talhanation.smallships.network.ModPackets;
import com.talhanation.smallships.network.packet.ServerboundOpenShipScreenPacket;
import com.talhanation.smallships.world.entity.ship.ContainerShip;
import java.util.List;
import net.minecraft.class_1263;
import net.minecraft.class_1657;
import net.minecraft.class_1661;
import net.minecraft.class_1703;
import net.minecraft.class_1712;
import net.minecraft.class_1735;
import net.minecraft.class_1799;
import net.minecraft.class_3532;
import net.minecraft.class_3913;
import net.minecraft.class_3917;
import org.jetbrains.annotations.NotNull;

public class ShipContainerMenu extends class_1703 {
   public static final int COLUMNS = 9;
   private final class_1661 inventory;
   private final class_1263 container;
   private final class_3913 containerData;

   public ShipContainerMenu(class_3917<ShipContainerMenu> type, int syncId, class_1661 inventory, ContainerShip containerShip) {
      super(type, syncId);
      this.containerData = containerShip.containerData;
      method_17359(containerShip, this.getRowCount() * 9);
      this.container = containerShip;
      this.inventory = inventory;
      this.method_17360(this.containerData);
      this.openPage();
   }

   private void openPage() {
      this.container.method_5435(this.inventory.field_7546);
      int k = (this.getRowCount() - 4) * 18;

      for (int l = 0; l < this.getRowCount(); l++) {
         for (int m = 0; m < 9; m++) {
            this.method_7621(new class_1735(this.container, m + l * 9 + this.getPageIndex() * this.getRowCount() * 9, 8 + m * 18, 18 + l * 18));
         }
      }

      for (int l = 0; l < 3; l++) {
         for (int m = 0; m < 9; m++) {
            this.method_7621(new class_1735(this.inventory, m + l * 9 + 9, 8 + m * 18, 103 + l * 18 + k));
         }
      }

      for (int l = 0; l < 9; l++) {
         this.method_7621(new class_1735(this.inventory, l, 8 + l * 18, 161 + k));
      }
   }

   public void method_7610(int stateId, @NotNull List<class_1799> list, @NotNull class_1799 itemStack) {
      super.method_7610(stateId, list, itemStack);
   }

   public boolean method_7604(@NotNull class_1657 player, int i) {
      int pageIndex = class_3532.method_15340(this.getPageIndex() + i, 0, this.getPageCount() - 1);
      ModPackets.clientSendPacket(new ServerboundOpenShipScreenPacket(this.getContainerShip().method_5667(), pageIndex));
      this.updatePaging(this.getRowCount(), this.getPageCount(), pageIndex);
      return true;
   }

   public boolean method_7597(@NotNull class_1657 player) {
      return this.container.method_5443(player);
   }

   @NotNull
   public class_1799 method_7601(@NotNull class_1657 player, int i) {
      class_1799 itemStack = class_1799.field_8037;
      class_1735 slot = (class_1735)this.field_7761.get(i);
      if (slot.method_7681()) {
         class_1799 slotItemStack = slot.method_7677();
         itemStack = slotItemStack.method_7972();
         if (i < this.getRowCount() * 9) {
            if (!this.method_7616(slotItemStack, this.getRowCount() * 9, this.field_7761.size(), true)) {
               return class_1799.field_8037;
            }
         } else if (!this.method_7616(slotItemStack, 0, this.getRowCount() * 9, false)) {
            return class_1799.field_8037;
         }

         if (slotItemStack.method_7960()) {
            slot.method_7673(class_1799.field_8037);
         } else {
            slot.method_7668();
         }
      }

      return itemStack;
   }

   public void method_7595(@NotNull class_1657 player) {
      super.method_7595(player);
      this.container.method_5432(player);
   }

   public int getRowCount() {
      return this.containerData.method_17390(0);
   }

   public int getPageCount() {
      return this.containerData.method_17390(1);
   }

   public int getPageIndex() {
      return this.containerData.method_17390(2);
   }

   public void updatePaging(int rows, int pages, int pageIndex) {
      this.containerData.method_17391(0, rows);
      this.containerData.method_17391(1, pages);
      this.containerData.method_17391(2, pageIndex);
   }

   public ContainerShip getContainerShip() {
      return (ContainerShip)this.container;
   }

   public void method_7596(class_1712 containerListener) {
      super.method_7596(containerListener);
   }

   public String toString() {
      return "ShipContainerMenu{rows="
         + this.getRowCount()
         + ", pages="
         + this.getPageCount()
         + ", pageIndex="
         + this.getPageIndex()
         + ", itemStacksSize="
         + this.getContainerShip().method_42278().size()
         + "}";
   }
}
