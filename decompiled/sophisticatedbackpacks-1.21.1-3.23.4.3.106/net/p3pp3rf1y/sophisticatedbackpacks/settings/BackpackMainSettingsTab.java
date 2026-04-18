package net.p3pp3rf1y.sophisticatedbackpacks.settings;

import java.util.List;
import java.util.Map;
import net.minecraft.class_124;
import net.minecraft.class_2561;
import net.p3pp3rf1y.sophisticatedbackpacks.Config;
import net.p3pp3rf1y.sophisticatedbackpacks.client.gui.SBPTranslationHelper;
import net.p3pp3rf1y.sophisticatedcore.client.gui.SettingsScreen;
import net.p3pp3rf1y.sophisticatedcore.client.gui.controls.ButtonDefinitions;
import net.p3pp3rf1y.sophisticatedcore.client.gui.controls.ImageButton;
import net.p3pp3rf1y.sophisticatedcore.client.gui.controls.ToggleButton;
import net.p3pp3rf1y.sophisticatedcore.client.gui.controls.ButtonDefinition.Toggle;
import net.p3pp3rf1y.sophisticatedcore.client.gui.utils.Dimension;
import net.p3pp3rf1y.sophisticatedcore.client.gui.utils.GuiHelper;
import net.p3pp3rf1y.sophisticatedcore.client.gui.utils.Position;
import net.p3pp3rf1y.sophisticatedcore.client.gui.utils.TextureBlitData;
import net.p3pp3rf1y.sophisticatedcore.client.gui.utils.UV;
import net.p3pp3rf1y.sophisticatedcore.settings.main.MainSettingsTab;

public class BackpackMainSettingsTab extends MainSettingsTab<BackpackMainSettingsContainer> {
   private static final TextureBlitData ICON = new TextureBlitData(GuiHelper.ICONS, Dimension.SQUARE_256, new UV(64, 48), Dimension.SQUARE_16);
   private static final List<class_2561> BACKPACK_CONTEXT_TOOLTIP = List.of(
      class_2561.method_43471(SBPTranslationHelper.INSTANCE.translSettingsButton("context_backpack.tooltip")),
      class_2561.method_43471(SBPTranslationHelper.INSTANCE.translSettingsButton("context_backpack.tooltip_detail")).method_27692(class_124.field_1080)
   );
   private static final Toggle<Boolean> ANOTHER_PLAYER_CAN_OPEN = ButtonDefinitions.createToggleButtonDefinition(
      Map.of(
         true,
         GuiHelper.getButtonStateData(
            new UV(176, 32),
            Dimension.SQUARE_16,
            new Position(1, 1),
            List.of(
               class_2561.method_43471(SBPTranslationHelper.INSTANCE.translSettingsButton("another_player_can_open.on")),
               class_2561.method_43471(SBPTranslationHelper.INSTANCE.translSettingsButton("another_player_can_open.on.tooltip"))
                  .method_27692(class_124.field_1080)
            )
         ),
         false,
         GuiHelper.getButtonStateData(
            new UV(192, 32),
            Dimension.SQUARE_16,
            new Position(1, 1),
            List.of(
               class_2561.method_43471(SBPTranslationHelper.INSTANCE.translSettingsButton("another_player_can_open.off")),
               class_2561.method_43471(SBPTranslationHelper.INSTANCE.translSettingsButton("another_player_can_open.off.tooltip"))
                  .method_27692(class_124.field_1080)
            )
         )
      )
   );

   public BackpackMainSettingsTab(BackpackMainSettingsContainer container, Position position, SettingsScreen screen) {
      super(
         container,
         position,
         screen,
         BACKPACK_CONTEXT_TOOLTIP,
         class_2561.method_43471(SBPTranslationHelper.INSTANCE.translSettingsButton("context_backpack")),
         SBPTranslationHelper.INSTANCE.translSettings("backpack"),
         SBPTranslationHelper.INSTANCE.translSettingsTooltip("backpack"),
         onTabIconClicked -> new ImageButton(new Position(position.x() + 1, position.y() + 4), Dimension.SQUARE_16, ICON, onTabIconClicked)
      );
      if (Boolean.TRUE.equals(Config.SERVER.allowOpeningOtherPlayerBackpacks.get())) {
         this.addHideableChild(
            new ToggleButton(
               new Position(this.x + 57, this.y + 46),
               ANOTHER_PLAYER_CAN_OPEN,
               button -> container.toggleAnotherPlayerCanOpen(),
               container::canAnotherPlayerOpen
            )
         );
      }
   }
}
