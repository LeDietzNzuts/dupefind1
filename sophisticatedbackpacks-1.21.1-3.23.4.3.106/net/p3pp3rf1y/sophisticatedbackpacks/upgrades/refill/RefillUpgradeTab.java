package net.p3pp3rf1y.sophisticatedbackpacks.upgrades.refill;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import net.minecraft.class_124;
import net.minecraft.class_1767;
import net.minecraft.class_2561;
import net.minecraft.class_310;
import net.minecraft.class_332;
import net.minecraft.class_4587;
import net.minecraft.class_746;
import net.p3pp3rf1y.sophisticatedbackpacks.client.gui.SBPTranslationHelper;
import net.p3pp3rf1y.sophisticatedbackpacks.common.gui.BackpackContainer;
import net.p3pp3rf1y.sophisticatedcore.client.gui.StorageScreenBase;
import net.p3pp3rf1y.sophisticatedcore.client.gui.UpgradeSettingsTab;
import net.p3pp3rf1y.sophisticatedcore.client.gui.utils.Position;
import net.p3pp3rf1y.sophisticatedcore.upgrades.FilterLogic;
import net.p3pp3rf1y.sophisticatedcore.upgrades.FilterLogicContainer;
import net.p3pp3rf1y.sophisticatedcore.upgrades.FilterLogicControl;
import net.p3pp3rf1y.sophisticatedcore.upgrades.FilterLogicControlBase.MatchButton;

public abstract class RefillUpgradeTab extends UpgradeSettingsTab<RefillUpgradeContainer> {
   private static final class_2561 SCROLL_TOOLTIP = SBPTranslationHelper.INSTANCE
      .translUpgrade("refill.scroll.tooltip", new Object[0])
      .method_27695(new class_124[]{class_124.field_1056, class_124.field_1063});
   private final FilterLogicControl<FilterLogic, FilterLogicContainer<FilterLogic>> filterLogicControl;
   private int slotBeingChanged = -1;
   private RefillUpgradeWrapper.TargetSlot targetSlotBeingChanged = null;
   private static List<class_2561> additionalTooltip = new ArrayList<>();

   public static List<class_2561> getAdditionalTooltip() {
      if (!additionalTooltip.isEmpty()) {
         class_746 player = class_310.method_1551().field_1724;
         if (player != null && player.field_7512 instanceof BackpackContainer) {
            return additionalTooltip;
         }
      }

      return Collections.emptyList();
   }

   protected RefillUpgradeTab(RefillUpgradeContainer upgradeContainer, Position position, StorageScreenBase<?> screen, int slotsInRow, String upgradeName) {
      super(
         upgradeContainer,
         position,
         screen,
         SBPTranslationHelper.INSTANCE.translUpgrade(upgradeName, new Object[0]),
         SBPTranslationHelper.INSTANCE.translUpgradeTooltip(upgradeName)
      );
      this.filterLogicControl = (FilterLogicControl<FilterLogic, FilterLogicContainer<FilterLogic>>)this.addHideableChild(
         new RefillUpgradeTab.RefillFilterLogicControl(screen, slotsInRow)
      );
   }

   protected void moveSlotsToTab() {
      this.filterLogicControl.moveSlotsToView();
   }

   public void method_25394(class_332 guiGraphics, int mouseX, int mouseY, float partialTicks) {
      if (this.shouldRender.getAsBoolean()) {
         super.method_25394(guiGraphics, mouseX, mouseY, partialTicks);
         if (!this.filterLogicControl.method_25405(mouseX, mouseY)) {
            this.resetAdditionalTooltip();
            if (this.slotBeingChanged > -1) {
               this.saveTargetSlot();
            }
         }
      }
   }

   private void resetAdditionalTooltip() {
      if (!additionalTooltip.isEmpty()) {
         additionalTooltip = new ArrayList<>();
      }
   }

   protected void onTabClose() {
      super.onTabClose();
      this.resetAdditionalTooltip();
   }

   private void saveTargetSlot() {
      ((RefillUpgradeContainer)this.getContainer()).setTargetSlot(this.slotBeingChanged, this.targetSlotBeingChanged);
      this.slotBeingChanged = -1;
   }

   public static class Advanced extends RefillUpgradeTab {
      public Advanced(RefillUpgradeContainer upgradeContainer, Position position, StorageScreenBase<?> screen, int slotsInRow) {
         super(upgradeContainer, position, screen, slotsInRow, "advanced_refill");
      }
   }

   public static class Basic extends RefillUpgradeTab {
      public Basic(RefillUpgradeContainer upgradeContainer, Position position, StorageScreenBase<?> screen, int slotsInRow) {
         super(upgradeContainer, position, screen, slotsInRow, "refill");
      }
   }

   private class RefillFilterLogicControl extends FilterLogicControl<FilterLogic, FilterLogicContainer<FilterLogic>> {
      private final int slotsInRow;

      public RefillFilterLogicControl(StorageScreenBase<?> screen, int slotsInRow) {
         super(
            screen,
            new Position(RefillUpgradeTab.this.x + 3, RefillUpgradeTab.this.y + 24),
            ((RefillUpgradeContainer)RefillUpgradeTab.this.getContainer()).getFilterLogicContainer(),
            slotsInRow,
            new MatchButton[0]
         );
         this.slotsInRow = slotsInRow;
      }

      protected void renderWidget(class_332 guiGraphics, int mouseX, int mouseY, float partialTicks) {
         super.renderWidget(guiGraphics, mouseX, mouseY, partialTicks);
         if (((RefillUpgradeContainer)RefillUpgradeTab.this.getContainer()).allowsTargetSlotSelection()) {
            this.renderTargetSlotAcronyms(guiGraphics);
            this.updateTargetSlotTooltip(mouseX, mouseY);
         }
      }

      private void updateTargetSlotTooltip(int mouseX, int mouseY) {
         if (this.method_25405(mouseX, mouseY)) {
            int slot = this.getSlot(mouseX, mouseY);
            if (RefillUpgradeTab.this.slotBeingChanged > -1) {
               this.updateTooltip(RefillUpgradeTab.this.targetSlotBeingChanged);
            } else {
               RefillUpgradeWrapper.TargetSlot targetSlot = ((RefillUpgradeContainer)RefillUpgradeTab.this.getContainer()).getTargetSlot(slot);
               if (RefillUpgradeTab.additionalTooltip.isEmpty() || !RefillUpgradeTab.additionalTooltip.get(0).equals(targetSlot.getDescription())) {
                  this.updateTooltip(targetSlot);
               }
            }
         }
      }

      private void renderTargetSlotAcronyms(class_332 guiGraphics) {
         class_4587 poseStack = guiGraphics.method_51448();
         poseStack.method_22903();
         poseStack.method_46416(0.0F, 0.0F, 300.0F);
         ((RefillUpgradeContainer)RefillUpgradeTab.this.getContainer())
            .getSlots()
            .forEach(
               slot -> {
                  if (!slot.method_7677().method_7960()) {
                     int slotIndex = slot.sophisticatedCore_getSlotIndex();
                     RefillUpgradeWrapper.TargetSlot ts = ((RefillUpgradeContainer)RefillUpgradeTab.this.getContainer()).getTargetSlot(slotIndex);
                     RefillUpgradeWrapper.TargetSlot targetSlot = RefillUpgradeTab.this.slotBeingChanged == slotIndex
                        ? RefillUpgradeTab.this.targetSlotBeingChanged
                        : ts;
                     guiGraphics.method_27535(
                        this.font,
                        targetSlot.getAcronym(),
                        this.getX() + slotIndex % this.slotsInRow * 18 + 10,
                        this.getY() + slotIndex / this.slotsInRow * 18 + 2,
                        class_1767.field_7942.method_16357()
                     );
                  }
               }
            );
         poseStack.method_22909();
      }

      private void updateTooltip(RefillUpgradeWrapper.TargetSlot targetSlot) {
         RefillUpgradeTab.this.resetAdditionalTooltip();
         RefillUpgradeTab.additionalTooltip
            .add(
               SBPTranslationHelper.INSTANCE
                  .translUpgrade("refill.target_slot.tooltip", new Object[]{targetSlot.getDescription()})
                  .method_27692(class_124.field_1080)
            );
         RefillUpgradeTab.additionalTooltip.add(RefillUpgradeTab.SCROLL_TOOLTIP);
      }

      public void method_25394(class_332 guiGraphics, int mouseX, int mouseY, float partialTicks) {
         super.method_25394(guiGraphics, mouseX, mouseY, partialTicks);
         int slot = this.getSlot(mouseX, mouseY);
         if (RefillUpgradeTab.this.slotBeingChanged > -1 && RefillUpgradeTab.this.slotBeingChanged != slot) {
            RefillUpgradeTab.this.saveTargetSlot();
         }
      }

      public boolean method_25401(double mouseX, double mouseY, double scrollX, double scrollY) {
         int slot = this.getSlot(mouseX, mouseY);
         if (RefillUpgradeTab.this.slotBeingChanged == -1) {
            RefillUpgradeTab.this.slotBeingChanged = slot;
            RefillUpgradeTab.this.targetSlotBeingChanged = ((RefillUpgradeContainer)RefillUpgradeTab.this.getContainer()).getTargetSlot(slot);
         }

         RefillUpgradeTab.this.targetSlotBeingChanged = scrollY > 0.0
            ? RefillUpgradeTab.this.targetSlotBeingChanged.next()
            : RefillUpgradeTab.this.targetSlotBeingChanged.previous();
         return true;
      }

      private int getSlot(double mouseX, double mouseY) {
         return ((int)mouseX - this.getX()) / 18 + this.slotsInRow * (((int)mouseY - this.getY()) / 18);
      }
   }
}
