package net.p3pp3rf1y.sophisticatedcore.client.gui.controls;

import java.util.function.IntConsumer;
import net.minecraft.class_1799;
import net.minecraft.class_2561;
import net.minecraft.class_310;
import net.minecraft.class_332;
import net.minecraft.class_4587;
import net.minecraft.class_6381;
import net.minecraft.class_6382;
import net.p3pp3rf1y.sophisticatedcore.client.gui.utils.Dimension;
import net.p3pp3rf1y.sophisticatedcore.client.gui.utils.Position;

public class ItemButton extends ButtonBase {
   private final class_1799 stack;
   private final class_2561 narration;

   public ItemButton(Position position, IntConsumer onClick, class_1799 stack, class_2561 narration) {
      super(position, Dimension.SQUARE_16, onClick);
      this.stack = stack;
      this.narration = narration;
   }

   @Override
   protected void renderBg(class_332 guiGraphics, class_310 minecraft, int mouseX, int mouseY) {
   }

   @Override
   protected void renderWidget(class_332 guiGraphics, int mouseX, int mouseY, float partialTicks) {
      class_4587 pose = guiGraphics.method_51448();
      pose.method_22903();
      pose.method_46416(0.0F, 0.0F, -140.0F);
      guiGraphics.method_51427(this.stack, this.x, this.y);
      pose.method_22909();
   }

   @Override
   public void method_37020(class_6382 narrationElementOutput) {
      narrationElementOutput.method_37034(class_6381.field_33788, this.narration);
      narrationElementOutput.method_37034(class_6381.field_33791, class_2561.method_43471("narration.button.usage.focused"));
   }
}
