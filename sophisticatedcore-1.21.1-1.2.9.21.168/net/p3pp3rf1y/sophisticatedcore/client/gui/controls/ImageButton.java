package net.p3pp3rf1y.sophisticatedcore.client.gui.controls;

import java.util.function.IntConsumer;
import net.minecraft.class_310;
import net.minecraft.class_332;
import net.minecraft.class_6382;
import net.p3pp3rf1y.sophisticatedcore.client.gui.utils.Dimension;
import net.p3pp3rf1y.sophisticatedcore.client.gui.utils.GuiHelper;
import net.p3pp3rf1y.sophisticatedcore.client.gui.utils.Position;
import net.p3pp3rf1y.sophisticatedcore.client.gui.utils.TextureBlitData;

public class ImageButton extends ButtonBase {
   private final TextureBlitData texture;

   public ImageButton(Position position, Dimension dimension, TextureBlitData texture, IntConsumer onClick) {
      super(position, dimension, onClick);
      this.texture = texture;
   }

   @Override
   protected void renderBg(class_332 guiGraphics, class_310 minecraft, int mouseX, int mouseY) {
   }

   @Override
   protected void renderWidget(class_332 guiGraphics, int mouseX, int mouseY, float partialTicks) {
      GuiHelper.blit(guiGraphics, this.x, this.y, this.texture);
   }

   @Override
   public void method_37020(class_6382 narrationElementOutput) {
   }
}
