package net.p3pp3rf1y.sophisticatedcore.client.gui;

import net.minecraft.class_2561;
import net.p3pp3rf1y.sophisticatedcore.client.gui.controls.ImageButton;
import net.p3pp3rf1y.sophisticatedcore.client.gui.utils.Dimension;
import net.p3pp3rf1y.sophisticatedcore.client.gui.utils.GuiHelper;
import net.p3pp3rf1y.sophisticatedcore.client.gui.utils.Position;
import net.p3pp3rf1y.sophisticatedcore.client.gui.utils.TextureBlitData;
import net.p3pp3rf1y.sophisticatedcore.client.gui.utils.UV;
import net.p3pp3rf1y.sophisticatedcore.common.gui.StorageContainerMenuBase;

public class StorageSettingsTab extends Tab {
   private static final TextureBlitData ICON = new TextureBlitData(GuiHelper.ICONS, Dimension.SQUARE_256, new UV(16, 96), Dimension.SQUARE_16);
   private final StorageScreenBase<?> screen;

   public StorageSettingsTab(Position position, StorageScreenBase<?> screen, String tabTooltip) {
      super(
         position,
         class_2561.method_43471(tabTooltip),
         onTabIconClicked -> new ImageButton(new Position(position.x() + 1, position.y() + 4), Dimension.SQUARE_16, ICON, onTabIconClicked)
      );
      this.screen = screen;
   }

   @Override
   protected void onTabIconClicked(int button) {
      ((StorageContainerMenuBase)this.screen.method_17577()).openSettings();
   }
}
