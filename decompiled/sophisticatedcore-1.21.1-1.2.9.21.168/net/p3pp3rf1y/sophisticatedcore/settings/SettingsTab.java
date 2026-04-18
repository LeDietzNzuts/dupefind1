package net.p3pp3rf1y.sophisticatedcore.settings;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.IntConsumer;
import net.minecraft.class_1735;
import net.minecraft.class_1799;
import net.minecraft.class_2561;
import net.minecraft.class_332;
import net.p3pp3rf1y.sophisticatedcore.client.gui.SettingsScreen;
import net.p3pp3rf1y.sophisticatedcore.client.gui.SettingsTabBase;
import net.p3pp3rf1y.sophisticatedcore.client.gui.controls.ButtonBase;
import net.p3pp3rf1y.sophisticatedcore.client.gui.utils.Position;

public abstract class SettingsTab<C extends SettingsContainerBase<?>> extends SettingsTabBase<SettingsScreen> {
   private final C settingsContainer;

   protected SettingsTab(
      C settingsContainer,
      Position position,
      SettingsScreen screen,
      class_2561 tabLabel,
      List<class_2561> tooltip,
      List<class_2561> openTooltip,
      Function<IntConsumer, ButtonBase> getTabButton
   ) {
      super(position, screen, tabLabel, tooltip, openTooltip, getTabButton);
      this.settingsContainer = settingsContainer;
   }

   protected C getSettingsContainer() {
      return this.settingsContainer;
   }

   public abstract Optional<Integer> getSlotOverlayColor(int var1, boolean var2);

   public abstract void handleSlotClick(class_1735 var1, int var2);

   public int getItemRotation(int slotIndex, boolean templateLoadHovered) {
      return 0;
   }

   public void renderExtra(class_332 guiGraphics, class_1735 slot) {
   }

   public class_1799 getItemDisplayOverride(int slotNumber, boolean templateLoadHovered) {
      return class_1799.field_8037;
   }

   public void drawSlotStackOverlay(class_332 guiGraphics, class_1735 slot, boolean templateLoadHovered) {
   }
}
