package net.p3pp3rf1y.sophisticatedbackpacks.client.gui;

import net.minecraft.class_1661;
import net.minecraft.class_1735;
import net.minecraft.class_2561;
import net.minecraft.class_310;
import net.minecraft.class_332;
import net.minecraft.class_490;
import net.p3pp3rf1y.sophisticatedbackpacks.backpack.BackpackItem;
import net.p3pp3rf1y.sophisticatedbackpacks.client.KeybindHandler;
import net.p3pp3rf1y.sophisticatedbackpacks.common.gui.BackpackContainer;
import net.p3pp3rf1y.sophisticatedbackpacks.network.BackpackOpenPayload;
import net.p3pp3rf1y.sophisticatedcore.client.gui.StorageScreenBase;
import net.p3pp3rf1y.sophisticatedcore.network.PacketDistributor;

public class BackpackScreen extends StorageScreenBase<BackpackContainer> {
   public static BackpackScreen constructScreen(BackpackContainer screenContainer, class_1661 inv, class_2561 title) {
      return new BackpackScreen(screenContainer, inv, title);
   }

   public BackpackScreen(BackpackContainer screenContainer, class_1661 inv, class_2561 titleIn) {
      super(screenContainer, inv, titleIn);
   }

   public boolean method_25404(int keyCode, int scanCode, int modifiers) {
      if (keyCode == 256 || KeybindHandler.BACKPACK_OPEN_KEYBIND.method_1417(keyCode, scanCode)) {
         if (((BackpackContainer)this.method_17577()).isFirstLevelStorage() && (keyCode == 256 || this.mouseNotOverBackpack())) {
            if (((BackpackContainer)this.method_17577()).getBackpackContext().wasOpenFromInventory()) {
               this.field_22787.field_1724.method_7346();
               this.field_22787.method_1507(new class_490(this.field_22787.field_1724));
            } else {
               this.method_25419();
            }

            return true;
         }

         if (!((BackpackContainer)this.method_17577()).isFirstLevelStorage()) {
            PacketDistributor.sendToServer(new BackpackOpenPayload());
            return true;
         }
      }

      return super.method_25404(keyCode, scanCode, modifiers);
   }

   private boolean mouseNotOverBackpack() {
      class_1735 selectedSlot = this.sophisticatedCore_getSlotUnderMouse();
      return selectedSlot == null || !(selectedSlot.method_7677().method_7909() instanceof BackpackItem);
   }

   protected String getStorageSettingsTabTooltip() {
      return SBPTranslationHelper.INSTANCE.translGui("settings.tooltip");
   }

   public void method_25394(class_332 guiGraphics, int mouseX, int mouseY, float partialTicks) {
      super.method_25394(guiGraphics, mouseX, mouseY, partialTicks);
      if (((BackpackContainer)this.method_17577()).getNumberOfStorageInventorySlots() == 0 && class_310.method_1551().field_1724 != null) {
         class_310.method_1551().field_1724.method_7346();
      }
   }
}
