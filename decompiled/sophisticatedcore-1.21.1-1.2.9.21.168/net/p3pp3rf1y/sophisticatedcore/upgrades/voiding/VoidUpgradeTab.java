package net.p3pp3rf1y.sophisticatedcore.upgrades.voiding;

import java.util.Map;
import net.minecraft.class_124;
import net.minecraft.class_2561;
import net.minecraft.class_5250;
import net.p3pp3rf1y.sophisticatedcore.client.gui.StorageScreenBase;
import net.p3pp3rf1y.sophisticatedcore.client.gui.UpgradeSettingsTab;
import net.p3pp3rf1y.sophisticatedcore.client.gui.controls.ButtonDefinition;
import net.p3pp3rf1y.sophisticatedcore.client.gui.controls.ButtonDefinitions;
import net.p3pp3rf1y.sophisticatedcore.client.gui.controls.ToggleButton;
import net.p3pp3rf1y.sophisticatedcore.client.gui.utils.Dimension;
import net.p3pp3rf1y.sophisticatedcore.client.gui.utils.GuiHelper;
import net.p3pp3rf1y.sophisticatedcore.client.gui.utils.Position;
import net.p3pp3rf1y.sophisticatedcore.client.gui.utils.TranslationHelper;
import net.p3pp3rf1y.sophisticatedcore.client.gui.utils.UV;
import net.p3pp3rf1y.sophisticatedcore.upgrades.FilterLogic;
import net.p3pp3rf1y.sophisticatedcore.upgrades.FilterLogicContainer;
import net.p3pp3rf1y.sophisticatedcore.upgrades.FilterLogicControl;

public class VoidUpgradeTab extends UpgradeSettingsTab<VoidUpgradeContainer> {
   private static final class_5250 VOID_OVERFLOW_TOOLTIP = class_2561.method_43471(TranslationHelper.INSTANCE.translUpgradeButton("void_overflow"));
   private static final class_5250 VOID_OVERFLOW_TOOLTIP_DETAIL = class_2561.method_43471(
         TranslationHelper.INSTANCE.translUpgradeButton("void_overflow.detail")
      )
      .method_27692(class_124.field_1080);
   private static final class_5250 VOID_ANYTHING_DISABLED_TOOLTIP = class_2561.method_43471(
         TranslationHelper.INSTANCE.translUpgradeButton("void_anything_disabled")
      )
      .method_27692(class_124.field_1061);
   private static final ButtonDefinition.Toggle<Boolean> VOID_OVERFLOW = ButtonDefinitions.createToggleButtonDefinition(
      Map.of(
         true,
         GuiHelper.getButtonStateData(new UV(224, 16), Dimension.SQUARE_16, new Position(1, 1), VOID_OVERFLOW_TOOLTIP, VOID_OVERFLOW_TOOLTIP_DETAIL),
         false,
         GuiHelper.getButtonStateData(new UV(208, 16), TranslationHelper.INSTANCE.translUpgradeButton("void_any"), Dimension.SQUARE_16, new Position(1, 1))
      )
   );
   private static final ButtonDefinition.Toggle<Boolean> VOID_OVERFLOW_ONLY = ButtonDefinitions.createToggleButtonDefinition(
      Map.of(
         true,
         GuiHelper.getButtonStateData(
            new UV(224, 16), Dimension.SQUARE_16, new Position(1, 1), VOID_OVERFLOW_TOOLTIP, VOID_OVERFLOW_TOOLTIP_DETAIL, VOID_ANYTHING_DISABLED_TOOLTIP
         ),
         false,
         GuiHelper.getButtonStateData(new UV(208, 16), TranslationHelper.INSTANCE.translUpgradeButton("void_any"), Dimension.SQUARE_16, new Position(1, 1))
      )
   );
   protected FilterLogicControl<FilterLogic, FilterLogicContainer<FilterLogic>> filterLogicControl;

   protected VoidUpgradeTab(
      VoidUpgradeContainer upgradeContainer, Position position, StorageScreenBase<?> screen, class_2561 tabLabel, class_2561 closedTooltip
   ) {
      super(upgradeContainer, position, screen, tabLabel, closedTooltip);
      this.addHideableChild(
         new ToggleButton<>(
            new Position(this.x + 3, this.y + 24),
            ButtonDefinitions.WORK_IN_GUI,
            button -> this.getContainer().setShouldWorkdInGUI(!this.getContainer().shouldWorkInGUI()),
            this.getContainer()::shouldWorkInGUI
         )
      );
      this.addHideableChild(
         new ToggleButton<>(
            new Position(this.x + 21, this.y + 24),
            this.getContainer().getUpgradeWrapper().isVoidAnythingEnabled() ? VOID_OVERFLOW : VOID_OVERFLOW_ONLY,
            button -> this.getContainer().setShouldVoidOverflow(!this.getContainer().shouldVoidOverflow()),
            this.getContainer()::shouldVoidOverflow
         )
      );
   }

   @Override
   protected void moveSlotsToTab() {
      this.filterLogicControl.moveSlotsToView();
   }

   public static class Advanced extends VoidUpgradeTab {
      public Advanced(VoidUpgradeContainer upgradeContainer, Position position, StorageScreenBase<?> screen, int slotsPerRow) {
         super(
            upgradeContainer,
            position,
            screen,
            TranslationHelper.INSTANCE.translUpgrade("advanced_void"),
            TranslationHelper.INSTANCE.translUpgradeTooltip("advanced_void")
         );
         this.filterLogicControl = this.addHideableChild(
            new FilterLogicControl.Advanced(screen, new Position(this.x + 3, this.y + 44), this.getContainer().getFilterLogicContainer(), slotsPerRow)
         );
      }
   }

   public static class Basic extends VoidUpgradeTab {
      public Basic(VoidUpgradeContainer upgradeContainer, Position position, StorageScreenBase<?> screen, int slotsPerRow) {
         super(upgradeContainer, position, screen, TranslationHelper.INSTANCE.translUpgrade("void"), TranslationHelper.INSTANCE.translUpgradeTooltip("void"));
         this.filterLogicControl = this.addHideableChild(
            new FilterLogicControl.Basic(screen, new Position(this.x + 3, this.y + 44), this.getContainer().getFilterLogicContainer(), slotsPerRow)
         );
      }
   }
}
