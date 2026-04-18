package vectorwing.farmersdelight.common.block.state;

import net.minecraft.class_1767;
import org.jetbrains.annotations.Nullable;
import vectorwing.farmersdelight.common.Configuration;

public interface CanvasSign {
   @Nullable
   class_1767 getBackgroundColor();

   default boolean isDarkBackground() {
      class_1767 backgroundDye = this.getBackgroundColor();
      return backgroundDye != null && Configuration.CANVAS_SIGN_DARK_BACKGROUND_LIST.get().contains(backgroundDye.method_7792());
   }
}
