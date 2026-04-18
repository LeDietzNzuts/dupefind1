package net.p3pp3rf1y.sophisticatedcore.upgrades.battery;

import java.util.List;
import net.minecraft.class_1735;
import net.minecraft.class_310;
import net.minecraft.class_332;
import net.p3pp3rf1y.sophisticatedcore.client.gui.StorageScreenBase;
import net.p3pp3rf1y.sophisticatedcore.client.gui.UpgradeSettingsTab;
import net.p3pp3rf1y.sophisticatedcore.client.gui.utils.Dimension;
import net.p3pp3rf1y.sophisticatedcore.client.gui.utils.GuiHelper;
import net.p3pp3rf1y.sophisticatedcore.client.gui.utils.Position;
import net.p3pp3rf1y.sophisticatedcore.client.gui.utils.TranslationHelper;

public class BatteryUpgradeTab extends UpgradeSettingsTab<BatteryUpgradeContainer> {
   public BatteryUpgradeTab(BatteryUpgradeContainer upgradeContainer, Position position, StorageScreenBase<?> screen) {
      super(upgradeContainer, position, screen, TranslationHelper.INSTANCE.translUpgrade("battery"), TranslationHelper.INSTANCE.translUpgradeTooltip("battery"));
      this.openTabDimension = new Dimension(48, 48);
   }

   @Override
   protected void renderBg(class_332 guiGraphics, class_310 minecraft, int mouseX, int mouseY) {
      super.renderBg(guiGraphics, minecraft, mouseX, mouseY);
      if (this.getContainer().isOpen()) {
         GuiHelper.renderSlotsBackground(guiGraphics, this.x + 3, this.y + 24, 1, 1);
         GuiHelper.renderSlotsBackground(guiGraphics, this.x + 24, this.y + 24, 1, 1);
      }
   }

   @Override
   protected void moveSlotsToTab() {
      List<class_1735> slots = this.getContainer().getSlots();
      this.positionSlot(slots.get(0), this.screen.sophisticatedCore_getGuiLeft(), this.screen.sophisticatedCore_getGuiTop(), 4);
      this.positionSlot(slots.get(1), this.screen.sophisticatedCore_getGuiLeft(), this.screen.sophisticatedCore_getGuiTop(), 25);
   }

   private void positionSlot(class_1735 slot, int screenGuiLeft, int screenGuiTop, int xOffset) {
      slot.field_7873 = this.x - screenGuiLeft + xOffset;
      slot.field_7872 = this.y - screenGuiTop + 25;
   }
}
