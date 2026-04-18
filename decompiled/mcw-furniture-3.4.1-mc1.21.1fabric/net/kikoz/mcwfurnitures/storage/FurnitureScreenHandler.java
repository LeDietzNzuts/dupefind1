package net.kikoz.mcwfurnitures.storage;

import net.kikoz.mcwfurnitures.init.ScreenHandlerInit;
import net.minecraft.class_1263;
import net.minecraft.class_1277;
import net.minecraft.class_1657;
import net.minecraft.class_1661;
import net.minecraft.class_1703;
import net.minecraft.class_1735;
import net.minecraft.class_1799;

public class FurnitureScreenHandler extends class_1703 {
   private final class_1263 inventory;

   public FurnitureScreenHandler(int syncId, class_1661 playerInventory) {
      this(syncId, playerInventory, new class_1277(27));
   }

   public FurnitureScreenHandler(int syncId, class_1661 playerInventory, class_1263 inventory) {
      super(ScreenHandlerInit.FURNITURE_SCREEN_HANDLER, syncId);
      method_17359(inventory, 27);
      this.inventory = inventory;
      inventory.method_5435(playerInventory.field_7546);
      int startX = 8;
      int startY = 18;
      int slotSizePlus2 = 18;

      for (int row = 0; row < 3; row++) {
         for (int column = 0; column < 9; column++) {
            this.method_7621(new class_1735(inventory, row * 9 + column, startX + column * slotSizePlus2, startY + row * slotSizePlus2));
         }
      }

      for (int m = 0; m < 3; m++) {
         for (int l = 0; l < 9; l++) {
            this.method_7621(new class_1735(playerInventory, l + m * 9 + 9, 8 + l * 18, 84 + m * 18));
         }
      }

      for (int var11 = 0; var11 < 9; var11++) {
         this.method_7621(new class_1735(playerInventory, var11, 8 + var11 * 18, 142));
      }
   }

   public class_1799 method_7601(class_1657 player, int invSlot) {
      class_1799 newStack = class_1799.field_8037;
      class_1735 slot = (class_1735)this.field_7761.get(invSlot);
      if (slot != null && slot.method_7681()) {
         class_1799 originalStack = slot.method_7677();
         newStack = originalStack.method_7972();
         if (invSlot < this.inventory.method_5439()) {
            if (!this.method_7616(originalStack, this.inventory.method_5439(), this.field_7761.size(), true)) {
               return class_1799.field_8037;
            }
         } else if (!this.method_7616(originalStack, 0, this.inventory.method_5439(), false)) {
            return class_1799.field_8037;
         }

         if (originalStack.method_7960()) {
            slot.method_53512(class_1799.field_8037);
         } else {
            slot.method_7668();
         }
      }

      return newStack;
   }

   public class_1263 getInventory() {
      return this.inventory;
   }

   public void method_7595(class_1657 player) {
      super.method_7595(player);
      if (this.inventory instanceof FurniturekEntity blockEntity) {
         blockEntity.method_5432(player);
      }
   }

   public boolean method_7597(class_1657 player) {
      return this.inventory.method_5443(player);
   }
}
