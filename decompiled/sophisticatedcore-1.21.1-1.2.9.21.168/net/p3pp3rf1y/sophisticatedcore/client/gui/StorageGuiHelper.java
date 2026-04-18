package net.p3pp3rf1y.sophisticatedcore.client.gui;

import net.minecraft.class_2960;
import net.minecraft.class_332;
import net.p3pp3rf1y.sophisticatedcore.client.gui.utils.Position;

public class StorageGuiHelper {
   private StorageGuiHelper() {
   }

   public static void renderStorageBackground(Position position, class_332 guiGraphics, class_2960 textureName, int xSize, int slotsHeight) {
      int x = position.x();
      int y = position.y();
      int slotsTopBottomHeight = Math.min(slotsHeight / 2, 150);
      int yOffset = 0;
      guiGraphics.method_25290(textureName, x, y, 0.0F, 0.0F, xSize, 17 + slotsTopBottomHeight, 256, 256);
      if (slotsHeight / 2 > 150) {
         int middleHeight = (slotsHeight / 2 - 150) * 2;
         guiGraphics.method_25290(textureName, x, y + 17 + slotsTopBottomHeight, 0.0F, 17.0F, xSize, middleHeight, 256, 256);
         yOffset = middleHeight;
      }

      int playerInventoryHeight = 97;
      guiGraphics.method_25290(
         textureName,
         x,
         y + yOffset + 17 + slotsTopBottomHeight,
         0.0F,
         256.0F - (playerInventoryHeight + slotsTopBottomHeight),
         xSize,
         playerInventoryHeight + slotsTopBottomHeight,
         256,
         256
      );
   }
}
