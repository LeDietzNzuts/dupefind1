package net.p3pp3rf1y.sophisticatedcore.client.gui.controls;

import java.util.function.Supplier;
import net.minecraft.class_310;
import net.minecraft.class_332;
import net.minecraft.class_6382;
import net.p3pp3rf1y.sophisticatedcore.client.gui.utils.Dimension;
import net.p3pp3rf1y.sophisticatedcore.client.gui.utils.Position;
import net.p3pp3rf1y.sophisticatedcore.client.gui.utils.TextureBlitData;

public class ProgressBar extends WidgetBase {
   private final TextureBlitData progressTexture;
   private final Supplier<Float> getProgress;
   private final ProgressBar.ProgressDirection dir;

   public ProgressBar(Position position, TextureBlitData progressTexture, Supplier<Float> getProgress, ProgressBar.ProgressDirection dir) {
      super(position, new Dimension(progressTexture.getWidth(), progressTexture.getHeight()));
      this.progressTexture = progressTexture;
      this.getProgress = getProgress;
      this.dir = dir;
   }

   @Override
   protected void renderBg(class_332 guiGraphics, class_310 minecraft, int mouseX, int mouseY) {
   }

   @Override
   protected void renderWidget(class_332 guiGraphics, int mouseX, int mouseY, float partialTicks) {
      int height = this.progressTexture.getHeight();
      int width = this.progressTexture.getWidth();
      float progress = this.getProgress.get();
      if (!(progress <= 0.0F)) {
         int yOffset = 0;
         if (this.dir == ProgressBar.ProgressDirection.BOTTOM_UP) {
            yOffset = (int)(height * progress);
            height -= yOffset;
         } else if (this.dir == ProgressBar.ProgressDirection.LEFT_RIGHT) {
            width = (int)(width * progress);
         }

         guiGraphics.method_25290(
            this.progressTexture.getTextureName(),
            this.x,
            this.y + yOffset,
            this.progressTexture.getU(),
            (float)this.progressTexture.getV() + yOffset,
            width,
            height,
            this.progressTexture.getTextureWidth(),
            this.progressTexture.getTextureHeight()
         );
      }
   }

   @Override
   public void method_37020(class_6382 narrationElementOutput) {
   }

   public static enum ProgressDirection {
      LEFT_RIGHT,
      BOTTOM_UP;
   }
}
