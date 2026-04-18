package net.p3pp3rf1y.sophisticatedcore.client.gui.controls;

import net.minecraft.class_2561;
import net.minecraft.class_310;
import net.minecraft.class_332;
import net.minecraft.class_6382;
import net.p3pp3rf1y.sophisticatedcore.client.gui.utils.Dimension;
import net.p3pp3rf1y.sophisticatedcore.client.gui.utils.Position;

public class Label extends WidgetBase {
   private static final int DEFAULT_GUI_TEXT_COLOR = 4210752;
   private final class_2561 labelText;
   private final int color;

   public Label(Position position, class_2561 labelText) {
      this(position, labelText, 4210752);
   }

   public Label(Position position, class_2561 labelText, int color) {
      super(position, new Dimension(class_310.method_1551().field_1772.method_27525(labelText), 8));
      this.labelText = labelText;
      this.color = color;
   }

   @Override
   protected void renderBg(class_332 guiGraphics, class_310 minecraft, int mouseX, int mouseY) {
   }

   @Override
   protected void renderWidget(class_332 guiGraphics, int mouseX, int mouseY, float partialTicks) {
      guiGraphics.method_51439(this.minecraft.field_1772, this.labelText, this.x, this.y, this.color, false);
   }

   @Override
   public void method_37020(class_6382 narrationElementOutput) {
   }
}
