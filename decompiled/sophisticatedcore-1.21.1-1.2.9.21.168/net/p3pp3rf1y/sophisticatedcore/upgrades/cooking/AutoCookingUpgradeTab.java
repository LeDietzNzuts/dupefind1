package net.p3pp3rf1y.sophisticatedcore.upgrades.cooking;

import net.minecraft.class_1874;
import net.minecraft.class_3859;
import net.minecraft.class_3861;
import net.minecraft.class_3862;
import net.p3pp3rf1y.sophisticatedcore.client.gui.StorageScreenBase;
import net.p3pp3rf1y.sophisticatedcore.client.gui.UpgradeSettingsTab;
import net.p3pp3rf1y.sophisticatedcore.client.gui.utils.Position;
import net.p3pp3rf1y.sophisticatedcore.client.gui.utils.TranslationHelper;
import net.p3pp3rf1y.sophisticatedcore.upgrades.FilterLogic;
import net.p3pp3rf1y.sophisticatedcore.upgrades.FilterLogicContainer;
import net.p3pp3rf1y.sophisticatedcore.upgrades.FilterLogicControl;

public abstract class AutoCookingUpgradeTab<R extends class_1874, W extends AutoCookingUpgradeWrapper<W, ?, R>>
   extends UpgradeSettingsTab<AutoCookingUpgradeContainer<R, W>> {
   private final FilterLogicControl<FilterLogic, FilterLogicContainer<FilterLogic>> inputFilterLogicControl;
   private final FilterLogicControl<FilterLogic, FilterLogicContainer<FilterLogic>> fuelFilterLogicControl;
   private final CookingLogicControl<R> cookingLogicControl;

   protected AutoCookingUpgradeTab(
      AutoCookingUpgradeContainer<R, W> upgradeContainer,
      Position position,
      StorageScreenBase<?> screen,
      String tabLabel,
      String closedTooltip,
      int inputFilterSlotsPerRow,
      int fuelFilterSlotsPerRow
   ) {
      super(
         upgradeContainer, position, screen, TranslationHelper.INSTANCE.translUpgrade(tabLabel), TranslationHelper.INSTANCE.translUpgradeTooltip(closedTooltip)
      );
      this.inputFilterLogicControl = this.addHideableChild(
         new FilterLogicControl.Advanced(
            screen, new Position(this.x + 3, this.y + 24), this.getContainer().getInputFilterLogicContainer(), inputFilterSlotsPerRow
         )
      );
      this.cookingLogicControl = this.addHideableChild(
         new CookingLogicControl<>(new Position(this.x + 3, this.y + 84), this.getContainer().getCookingLogicContainer())
      );
      this.fuelFilterLogicControl = this.addHideableChild(
         new FilterLogicControl<>(screen, new Position(this.x + 3, this.y + 142), this.getContainer().getFuelFilterLogicContainer(), fuelFilterSlotsPerRow)
      );
   }

   @Override
   protected void moveSlotsToTab() {
      this.inputFilterLogicControl.moveSlotsToView();
      this.cookingLogicControl.moveSlotsToView(this.screen.sophisticatedCore_getGuiLeft(), this.screen.sophisticatedCore_getGuiTop());
      this.fuelFilterLogicControl.moveSlotsToView();
   }

   public static class AutoBlastingUpgradeTab extends AutoCookingUpgradeTab<class_3859, AutoCookingUpgradeWrapper.AutoBlastingUpgradeWrapper> {
      public AutoBlastingUpgradeTab(
         AutoCookingUpgradeContainer<class_3859, AutoCookingUpgradeWrapper.AutoBlastingUpgradeWrapper> upgradeContainer,
         Position position,
         StorageScreenBase<?> screen,
         int inputFilterSlotsPerRow,
         int fuelFilterSlotsPerRow
      ) {
         super(upgradeContainer, position, screen, "auto_blasting", "auto_blasting", inputFilterSlotsPerRow, fuelFilterSlotsPerRow);
      }
   }

   public static class AutoSmeltingUpgradeTab extends AutoCookingUpgradeTab<class_3861, AutoCookingUpgradeWrapper.AutoSmeltingUpgradeWrapper> {
      public AutoSmeltingUpgradeTab(
         AutoCookingUpgradeContainer<class_3861, AutoCookingUpgradeWrapper.AutoSmeltingUpgradeWrapper> upgradeContainer,
         Position position,
         StorageScreenBase<?> screen,
         int inputFilterSlotsPerRow,
         int fuelFilterSlotsPerRow
      ) {
         super(upgradeContainer, position, screen, "auto_smelting", "auto_smelting", inputFilterSlotsPerRow, fuelFilterSlotsPerRow);
      }
   }

   public static class AutoSmokingUpgradeTab extends AutoCookingUpgradeTab<class_3862, AutoCookingUpgradeWrapper.AutoSmokingUpgradeWrapper> {
      public AutoSmokingUpgradeTab(
         AutoCookingUpgradeContainer<class_3862, AutoCookingUpgradeWrapper.AutoSmokingUpgradeWrapper> upgradeContainer,
         Position position,
         StorageScreenBase<?> screen,
         int inputFilterSlotsPerRow,
         int fuelFilterSlotsPerRow
      ) {
         super(upgradeContainer, position, screen, "auto_smoking", "auto_smoking", inputFilterSlotsPerRow, fuelFilterSlotsPerRow);
      }
   }
}
