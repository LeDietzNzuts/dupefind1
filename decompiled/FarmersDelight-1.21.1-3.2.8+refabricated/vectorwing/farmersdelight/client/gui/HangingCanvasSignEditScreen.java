package vectorwing.farmersdelight.client.gui;

import net.minecraft.class_1767;
import net.minecraft.class_2561;
import net.minecraft.class_2625;
import net.minecraft.class_2680;
import net.minecraft.class_2960;
import net.minecraft.class_332;
import net.minecraft.class_7743;
import org.joml.Vector3f;
import vectorwing.farmersdelight.common.block.state.CanvasSign;

public class HangingCanvasSignEditScreen extends class_7743 {
   private static final Vector3f TEXT_SCALE = new Vector3f(0.9F, 0.9F, 0.9F);
   protected class_1767 dye;
   private final class_2960 texture;

   public HangingCanvasSignEditScreen(class_2625 signBlockEntity, boolean isFrontText, boolean isTextFilteringEnabled) {
      super(signBlockEntity, isFrontText, isTextFilteringEnabled, class_2561.method_43471("hanging_sign.edit"));
      if (signBlockEntity.method_11010().method_26204() instanceof CanvasSign canvasSign) {
         this.dye = canvasSign.getBackgroundColor();
      }

      String dyeName = this.dye != null ? "_" + this.dye.method_7792() : "";
      this.texture = class_2960.method_60655("farmersdelight", "canvas" + dyeName + ".png").method_45138("textures/gui/hanging_signs/");
   }

   protected void method_45654(class_332 gui, class_2680 state) {
      gui.method_51448().method_46416(this.field_22789 / 2.0F, 125.0F, 50.0F);
   }

   protected void method_45656(class_332 gui, class_2680 p_250054_) {
      gui.method_51448().method_46416(0.0F, -13.0F, 0.0F);
      gui.method_51448().method_22905(4.5F, 4.5F, 1.0F);
      gui.method_25290(this.texture, -8, -8, 0.0F, 0.0F, 16, 16, 16, 16);
   }

   protected Vector3f method_45661() {
      return TEXT_SCALE;
   }
}
