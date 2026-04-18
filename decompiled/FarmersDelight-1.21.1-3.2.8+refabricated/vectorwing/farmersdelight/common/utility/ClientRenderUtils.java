package vectorwing.farmersdelight.common.utility;

public class ClientRenderUtils {
   public static boolean isCursorInsideBounds(int iconX, int iconY, int iconWidth, int iconHeight, double cursorX, double cursorY) {
      return iconX <= cursorX && cursorX < iconX + iconWidth && iconY <= cursorY && cursorY < iconY + iconHeight;
   }
}
