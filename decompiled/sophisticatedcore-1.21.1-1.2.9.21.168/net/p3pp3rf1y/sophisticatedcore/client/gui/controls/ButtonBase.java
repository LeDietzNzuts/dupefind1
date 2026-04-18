package net.p3pp3rf1y.sophisticatedcore.client.gui.controls;

import java.util.function.IntConsumer;
import net.minecraft.class_1109;
import net.minecraft.class_310;
import net.minecraft.class_3417;
import net.p3pp3rf1y.sophisticatedcore.Config;
import net.p3pp3rf1y.sophisticatedcore.client.gui.utils.Dimension;
import net.p3pp3rf1y.sophisticatedcore.client.gui.utils.Position;

public abstract class ButtonBase extends WidgetBase {
   protected IntConsumer onClick;

   protected ButtonBase(Position position, Dimension dimension, IntConsumer onClick) {
      super(position, dimension);
      this.onClick = onClick;
   }

   protected void setOnClick(IntConsumer onClick) {
      this.onClick = onClick;
   }

   public boolean method_25402(double mouseX, double mouseY, int button) {
      if (!this.method_25405(mouseX, mouseY)) {
         return false;
      } else {
         this.onClick.accept(button);
         if (Boolean.TRUE.equals(Config.CLIENT.playButtonSound.get())) {
            class_310.method_1551().method_1483().method_4873(class_1109.method_47978(class_3417.field_15015, 1.0F));
         }

         return true;
      }
   }
}
