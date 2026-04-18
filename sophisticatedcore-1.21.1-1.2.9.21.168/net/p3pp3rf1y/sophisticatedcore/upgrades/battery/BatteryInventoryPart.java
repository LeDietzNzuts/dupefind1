package net.p3pp3rf1y.sophisticatedcore.upgrades.battery;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import net.minecraft.class_2561;
import net.minecraft.class_332;
import net.p3pp3rf1y.sophisticatedcore.client.gui.StorageScreenBase;
import net.p3pp3rf1y.sophisticatedcore.client.gui.UpgradeInventoryPartBase;
import net.p3pp3rf1y.sophisticatedcore.client.gui.utils.Dimension;
import net.p3pp3rf1y.sophisticatedcore.client.gui.utils.GuiHelper;
import net.p3pp3rf1y.sophisticatedcore.client.gui.utils.Position;
import net.p3pp3rf1y.sophisticatedcore.client.gui.utils.TextureBlitData;
import net.p3pp3rf1y.sophisticatedcore.client.gui.utils.TranslationHelper;
import net.p3pp3rf1y.sophisticatedcore.client.gui.utils.UV;
import org.joml.Matrix4f;

public class BatteryInventoryPart extends UpgradeInventoryPartBase<BatteryUpgradeContainer> {
   private static final TextureBlitData TANK_BACKGROUND_TOP = new TextureBlitData(
      GuiHelper.GUI_CONTROLS, Dimension.SQUARE_256, new UV(29, 30), Dimension.SQUARE_18
   );
   private static final TextureBlitData TANK_BACKGROUND_MIDDLE = new TextureBlitData(
      GuiHelper.GUI_CONTROLS, Dimension.SQUARE_256, new UV(29, 48), Dimension.SQUARE_18
   );
   private static final TextureBlitData TANK_BACKGROUND_BOTTOM = new TextureBlitData(
      GuiHelper.GUI_CONTROLS, Dimension.SQUARE_256, new UV(29, 66), Dimension.SQUARE_18
   );
   private static final TextureBlitData OVERLAY = new TextureBlitData(GuiHelper.GUI_CONTROLS, Dimension.SQUARE_256, new UV(47, 56), new Dimension(16, 18));
   private static final TextureBlitData CHARGE_SEGMENT = new TextureBlitData(GuiHelper.GUI_CONTROLS, Dimension.SQUARE_256, new UV(47, 74), new Dimension(16, 6));
   private static final TextureBlitData CONNECTION_TOP = new TextureBlitData(GuiHelper.GUI_CONTROLS, Dimension.SQUARE_256, new UV(47, 48), new Dimension(16, 4));
   private static final TextureBlitData CONNECTION_BOTTOM = new TextureBlitData(
      GuiHelper.GUI_CONTROLS, Dimension.SQUARE_256, new UV(47, 52), new Dimension(16, 4)
   );
   private final Position pos;
   private final int height;
   private final StorageScreenBase<?> screen;
   private static final int TOP_BAR_COLOR = 16718362;
   private static final int BOTTOM_BAR_COLOR = 16777024;

   public BatteryInventoryPart(int upgradeSlot, BatteryUpgradeContainer container, Position pos, int height, StorageScreenBase<?> screen) {
      super(upgradeSlot, container);
      this.pos = pos;
      this.height = height;
      this.screen = screen;
   }

   @Override
   public void render(class_332 guiGraphics, int mouseX, int mouseY) {
      GuiHelper.blit(guiGraphics, this.getTankLeft(), this.pos.y(), TANK_BACKGROUND_TOP);
      int yOffset = 18;

      for (int i = 0; i < (this.height - 36) / 18; i++) {
         GuiHelper.blit(guiGraphics, this.getTankLeft(), this.pos.y() + yOffset, TANK_BACKGROUND_MIDDLE);
         yOffset += 18;
      }

      GuiHelper.blit(guiGraphics, this.getTankLeft(), this.pos.y() + yOffset, TANK_BACKGROUND_BOTTOM);
      int var6 = 0;

      for (int i = 0; i < this.height / 18; i++) {
         GuiHelper.blit(guiGraphics, this.getTankLeft() + 1, this.pos.y() + var6, OVERLAY);
         var6 += 18;
      }

      this.renderCharge(guiGraphics);
      GuiHelper.blit(guiGraphics, this.getTankLeft() + 1, this.pos.y(), CONNECTION_TOP);
      GuiHelper.blit(guiGraphics, this.getTankLeft() + 1, this.pos.y() + this.height - 4, CONNECTION_BOTTOM);
   }

   private int getTankLeft() {
      return this.pos.x() + 9;
   }

   @Override
   public boolean handleMouseReleased(double mouseX, double mouseY, int button) {
      return false;
   }

   @Override
   public void renderErrorOverlay(class_332 guiGraphics) {
      this.screen.renderOverlay(guiGraphics, StorageScreenBase.ERROR_SLOT_COLOR, this.getTankLeft() + 1, this.pos.y() + 1, 16, this.height - 2);
   }

   @Override
   public void renderTooltip(StorageScreenBase<?> screen, class_332 guiGraphics, int mouseX, int mouseY) {
      int screenX = screen.sophisticatedCore_getGuiLeft() + this.pos.x() + 10;
      int screenY = screen.sophisticatedCore_getGuiTop() + this.pos.y() + 1;
      if (mouseX >= screenX && mouseX < screenX + 16 && mouseY >= screenY && mouseY < screenY + this.height - 2) {
         long energyStored = this.container.getAmount();
         long maxEnergyStored = this.container.getCapacity();
         List<class_2561> tooltip = new ArrayList<>();
         tooltip.add(
            class_2561.method_43469(
               TranslationHelper.INSTANCE.translUpgradeKey("battery.contents_tooltip"),
               new Object[]{String.format("%,d", energyStored), String.format("%,d", maxEnergyStored)}
            )
         );
         guiGraphics.method_51437(screen.field_22793, tooltip, Optional.empty(), mouseX, mouseY);
      }
   }

   private void renderCharge(class_332 guiGraphics) {
      long energyStored = this.container.getAmount();
      long maxEnergyStored = this.container.getCapacity();
      int segmentHeight = CHARGE_SEGMENT.getHeight();
      int numberOfSegments = this.height / segmentHeight;
      int displayLevel = (int)(numberOfSegments * ((float)energyStored / (float)maxEnergyStored));
      int finalRed = 255;
      int finalGreen = 26;
      int finalBlue = 26;
      int initialRed = 255;
      int initialGreen = 255;
      int initialBlue = 64;
      Matrix4f matrix = guiGraphics.method_51448().method_23760().method_23761();

      for (int i = 0; i < displayLevel; i++) {
         float percentage = (float)i / (numberOfSegments - 1);
         int red = (int)(initialRed * (1.0F - percentage) + finalRed * percentage);
         int green = (int)(initialGreen * (1.0F - percentage) + finalGreen * percentage);
         int blue = (int)(initialBlue * (1.0F - percentage) + finalBlue * percentage);
         int color = red << 16 | green << 8 | blue | 0xFF000000;
         GuiHelper.coloredBlit(matrix, this.getTankLeft() + 1, this.pos.y() + this.height - (i + 1) * segmentHeight, CHARGE_SEGMENT, color);
      }
   }
}
