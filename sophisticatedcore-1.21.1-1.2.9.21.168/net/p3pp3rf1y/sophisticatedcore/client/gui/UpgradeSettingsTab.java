package net.p3pp3rf1y.sophisticatedcore.client.gui;

import net.minecraft.class_1735;
import net.minecraft.class_2561;
import net.p3pp3rf1y.sophisticatedcore.client.gui.controls.ItemButton;
import net.p3pp3rf1y.sophisticatedcore.client.gui.utils.Position;
import net.p3pp3rf1y.sophisticatedcore.common.gui.UpgradeContainerBase;

public abstract class UpgradeSettingsTab<C extends UpgradeContainerBase<?, ?>> extends SettingsTabBase<StorageScreenBase<?>> {
   private final C upgradeContainer;

   protected UpgradeSettingsTab(C upgradeContainer, Position position, StorageScreenBase<?> screen, class_2561 tabLabel, class_2561 closedTooltip) {
      super(
         position,
         screen,
         tabLabel,
         closedTooltip,
         onTabIconClicked -> new ItemButton(
            new Position(position.x() + 1, position.y() + 4),
            onTabIconClicked,
            upgradeContainer.getUpgradeStack(),
            class_2561.method_43471("gui.sophisticatedcore.narrate.tab_button")
         )
      );
      this.upgradeContainer = upgradeContainer;
      this.moveSlotsOutOfView();
   }

   protected C getContainer() {
      return this.upgradeContainer;
   }

   protected abstract void moveSlotsToTab();

   protected void moveSlotsOutOfView() {
      this.getContainer().getSlots().forEach(slot -> slot.field_7873 = -2000);
   }

   @Override
   protected void onTabOpen() {
      super.onTabOpen();
      this.moveSlotsToTab();
   }

   @Override
   protected void onTabClose() {
      super.onTabClose();
      this.moveSlotsOutOfView();
   }

   @Override
   protected void setOpen(boolean isOpen) {
      this.upgradeContainer.setIsOpen(isOpen);
      super.setOpen(isOpen);
   }

   public void onAfterInit() {
      if (this.upgradeContainer.isOpen()) {
         this.setOpen(true);
      }
   }

   public boolean slotIsNotCoveredAt(class_1735 slot, double mouseX, double mouseY) {
      return true;
   }
}
