package net.p3pp3rf1y.sophisticatedbackpacks.settings;

import net.minecraft.class_2561;
import net.p3pp3rf1y.sophisticatedbackpacks.client.gui.SBPTranslationHelper;
import net.p3pp3rf1y.sophisticatedbackpacks.network.BackpackOpenPayload;
import net.p3pp3rf1y.sophisticatedcore.client.gui.Tab;
import net.p3pp3rf1y.sophisticatedcore.client.gui.controls.ImageButton;
import net.p3pp3rf1y.sophisticatedcore.client.gui.utils.Dimension;
import net.p3pp3rf1y.sophisticatedcore.client.gui.utils.GuiHelper;
import net.p3pp3rf1y.sophisticatedcore.client.gui.utils.Position;
import net.p3pp3rf1y.sophisticatedcore.client.gui.utils.TextureBlitData;
import net.p3pp3rf1y.sophisticatedcore.client.gui.utils.UV;
import net.p3pp3rf1y.sophisticatedcore.network.PacketDistributor;

public class BackToBackpackTab extends Tab {
   private static final TextureBlitData ICON = new TextureBlitData(GuiHelper.ICONS, Dimension.SQUARE_256, new UV(64, 80), Dimension.SQUARE_16);

   protected BackToBackpackTab(Position position) {
      super(
         position,
         class_2561.method_43471(SBPTranslationHelper.INSTANCE.translGui("back_to_backpack.tooltip")),
         onTabIconClicked -> new ImageButton(new Position(position.x() + 1, position.y() + 4), Dimension.SQUARE_16, ICON, onTabIconClicked)
      );
   }

   protected void onTabIconClicked(int button) {
      PacketDistributor.sendToServer(new BackpackOpenPayload());
   }
}
