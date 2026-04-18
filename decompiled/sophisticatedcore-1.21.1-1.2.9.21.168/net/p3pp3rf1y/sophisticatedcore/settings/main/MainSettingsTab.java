package net.p3pp3rf1y.sophisticatedcore.settings.main;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.IntConsumer;
import net.minecraft.class_124;
import net.minecraft.class_1735;
import net.minecraft.class_2561;
import net.p3pp3rf1y.sophisticatedcore.client.gui.SettingsScreen;
import net.p3pp3rf1y.sophisticatedcore.client.gui.controls.ButtonBase;
import net.p3pp3rf1y.sophisticatedcore.client.gui.controls.ButtonDefinition;
import net.p3pp3rf1y.sophisticatedcore.client.gui.controls.ButtonDefinitions;
import net.p3pp3rf1y.sophisticatedcore.client.gui.controls.ToggleButton;
import net.p3pp3rf1y.sophisticatedcore.client.gui.utils.Dimension;
import net.p3pp3rf1y.sophisticatedcore.client.gui.utils.GuiHelper;
import net.p3pp3rf1y.sophisticatedcore.client.gui.utils.Position;
import net.p3pp3rf1y.sophisticatedcore.client.gui.utils.TranslationHelper;
import net.p3pp3rf1y.sophisticatedcore.client.gui.utils.UV;
import net.p3pp3rf1y.sophisticatedcore.settings.SettingsTab;

public class MainSettingsTab<T extends MainSettingsContainer> extends SettingsTab<T> {
   private static final ButtonDefinition.Toggle<Boolean> SHIFT_CLICK_INTO_OPEN_TAB = ButtonDefinitions.createToggleButtonDefinition(
      Map.of(
         true,
         GuiHelper.getButtonStateData(
            new UV(80, 32),
            Dimension.SQUARE_16,
            new Position(1, 1),
            List.of(
               class_2561.method_43471(TranslationHelper.INSTANCE.translSettingsButton("shift_click_open_tab.on")),
               class_2561.method_43471(TranslationHelper.INSTANCE.translSettingsButton("shift_click_open_tab.on.tooltip")).method_27692(class_124.field_1080)
            )
         ),
         false,
         GuiHelper.getButtonStateData(
            new UV(64, 96),
            Dimension.SQUARE_16,
            new Position(1, 1),
            List.of(
               class_2561.method_43471(TranslationHelper.INSTANCE.translSettingsButton("shift_click_open_tab.off")),
               class_2561.method_43471(TranslationHelper.INSTANCE.translSettingsButton("shift_click_open_tab.off.tooltip")).method_27692(class_124.field_1080)
            )
         )
      )
   );
   private static final ButtonDefinition.Toggle<Boolean> KEEP_TAB_OPEN = ButtonDefinitions.createToggleButtonDefinition(
      Map.of(
         true,
         GuiHelper.getButtonStateData(
            new UV(80, 80),
            Dimension.SQUARE_16,
            new Position(1, 1),
            List.of(
               class_2561.method_43471(TranslationHelper.INSTANCE.translSettingsButton("keep_tab_open.on")),
               class_2561.method_43471(TranslationHelper.INSTANCE.translSettingsButton("keep_tab_open.on.tooltip")).method_27692(class_124.field_1080)
            )
         ),
         false,
         GuiHelper.getButtonStateData(
            new UV(80, 96),
            Dimension.SQUARE_16,
            new Position(1, 1),
            List.of(
               class_2561.method_43471(TranslationHelper.INSTANCE.translSettingsButton("keep_tab_open.off")),
               class_2561.method_43471(TranslationHelper.INSTANCE.translSettingsButton("keep_tab_open.off.tooltip")).method_27692(class_124.field_1080)
            )
         )
      )
   );
   private static final ButtonDefinition.Toggle<Boolean> KEEP_SEARCH_PHRASE = ButtonDefinitions.createToggleButtonDefinition(
      Map.of(
         true,
         GuiHelper.getButtonStateData(
            new UV(208, 32),
            Dimension.SQUARE_16,
            new Position(1, 1),
            List.of(
               class_2561.method_43471(TranslationHelper.INSTANCE.translSettingsButton("keep_search_phrase.on")),
               class_2561.method_43471(TranslationHelper.INSTANCE.translSettingsButton("keep_search_phrase.on.tooltip")).method_27692(class_124.field_1080)
            )
         ),
         false,
         GuiHelper.getButtonStateData(
            new UV(224, 32),
            Dimension.SQUARE_16,
            new Position(1, 1),
            List.of(
               class_2561.method_43471(TranslationHelper.INSTANCE.translSettingsButton("keep_search_phrase.off")),
               class_2561.method_43471(TranslationHelper.INSTANCE.translSettingsButton("keep_search_phrase.off.tooltip")).method_27692(class_124.field_1080)
            )
         )
      )
   );
   private static final List<class_2561> PLAYER_CONTEXT_TOOLTIP = List.of(
      class_2561.method_43471(TranslationHelper.INSTANCE.translSettingsButton("context_player.tooltip")),
      class_2561.method_43471(TranslationHelper.INSTANCE.translSettingsButton("context_player.tooltip_detail")).method_27692(class_124.field_1080)
   );

   public MainSettingsTab(
      T container,
      Position position,
      SettingsScreen screen,
      List<class_2561> storageContextTooltip,
      class_2561 storageContextTitle,
      String tabLabelTranslKey,
      String tabTooltipTranslKey,
      Function<IntConsumer, ButtonBase> getTabButton
   ) {
      super(
         container,
         position,
         screen,
         class_2561.method_43471(tabLabelTranslKey),
         List.of(class_2561.method_43471(tabTooltipTranslKey)),
         Collections.emptyList(),
         getTabButton
      );
      this.addHideableChild(
         new ContextButton(
            new Position(this.x + 3, this.y + 24),
            button -> container.toggleContext(),
            () -> (class_2561)(container.getContext() == Context.PLAYER
               ? class_2561.method_43471(TranslationHelper.INSTANCE.translSettingsButton("context_player"))
               : storageContextTitle),
            () -> container.getContext() == Context.PLAYER ? PLAYER_CONTEXT_TOOLTIP : storageContextTooltip
         )
      );
      this.addHideableChild(
         new ToggleButton<>(
            new Position(this.x + 3, this.y + 46),
            SHIFT_CLICK_INTO_OPEN_TAB,
            button -> container.toggleShiftClickIntoOpenTab(),
            container::shouldShiftClickIntoOpenTab
         )
      );
      this.addHideableChild(
         new ToggleButton<>(new Position(this.x + 21, this.y + 46), KEEP_TAB_OPEN, button -> container.toggleKeepTabOpen(), container::shouldKeepTabOpen)
      );
      this.addHideableChild(
         new ToggleButton<>(
            new Position(this.x + 39, this.y + 46), KEEP_SEARCH_PHRASE, button -> container.toggleKeepSearchPhrase(), container::shouldKeepSearchPhrase
         )
      );
   }

   @Override
   public Optional<Integer> getSlotOverlayColor(int slotNumber, boolean templateLoadHovered) {
      return Optional.empty();
   }

   @Override
   public void handleSlotClick(class_1735 slot, int mouseButton) {
   }
}
