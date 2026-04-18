package net.p3pp3rf1y.sophisticatedcore.upgrades.cooking;

import net.minecraft.class_1874;
import net.minecraft.class_3859;
import net.minecraft.class_3861;
import net.minecraft.class_3862;
import net.p3pp3rf1y.sophisticatedcore.client.gui.StorageScreenBase;
import net.p3pp3rf1y.sophisticatedcore.client.gui.UpgradeSettingsTab;
import net.p3pp3rf1y.sophisticatedcore.client.gui.utils.Position;
import net.p3pp3rf1y.sophisticatedcore.client.gui.utils.TranslationHelper;

public abstract class CookingUpgradeTab<R extends class_1874, W extends CookingUpgradeWrapper<W, ?, R>>
   extends UpgradeSettingsTab<CookingUpgradeContainer<R, W>> {
   private final CookingLogicControl<R> cookingLogicControl = this.addHideableChild(
      new CookingLogicControl<>(new Position(this.x + 3, this.y + 24), this.getContainer().getSmeltingLogicContainer())
   );

   protected CookingUpgradeTab(
      CookingUpgradeContainer<R, W> upgradeContainer, Position position, StorageScreenBase<?> screen, String tabLabel, String closedTooltip
   ) {
      super(
         upgradeContainer, position, screen, TranslationHelper.INSTANCE.translUpgrade(tabLabel), TranslationHelper.INSTANCE.translUpgradeTooltip(closedTooltip)
      );
   }

   @Override
   protected void moveSlotsToTab() {
      this.cookingLogicControl.moveSlotsToView(this.screen.sophisticatedCore_getGuiLeft(), this.screen.sophisticatedCore_getGuiTop());
   }

   public static class BlastingUpgradeTab extends CookingUpgradeTab<class_3859, CookingUpgradeWrapper.BlastingUpgradeWrapper> {
      public BlastingUpgradeTab(
         CookingUpgradeContainer<class_3859, CookingUpgradeWrapper.BlastingUpgradeWrapper> upgradeContainer, Position position, StorageScreenBase<?> screen
      ) {
         super(upgradeContainer, position, screen, "blasting", "blasting");
      }
   }

   public static class SmeltingUpgradeTab extends CookingUpgradeTab<class_3861, CookingUpgradeWrapper.SmeltingUpgradeWrapper> {
      public SmeltingUpgradeTab(
         CookingUpgradeContainer<class_3861, CookingUpgradeWrapper.SmeltingUpgradeWrapper> upgradeContainer, Position position, StorageScreenBase<?> screen
      ) {
         super(upgradeContainer, position, screen, "smelting", "smelting");
      }
   }

   public static class SmokingUpgradeTab extends CookingUpgradeTab<class_3862, CookingUpgradeWrapper.SmokingUpgradeWrapper> {
      public SmokingUpgradeTab(
         CookingUpgradeContainer<class_3862, CookingUpgradeWrapper.SmokingUpgradeWrapper> upgradeContainer, Position position, StorageScreenBase<?> screen
      ) {
         super(upgradeContainer, position, screen, "smoking", "smoking");
      }
   }
}
