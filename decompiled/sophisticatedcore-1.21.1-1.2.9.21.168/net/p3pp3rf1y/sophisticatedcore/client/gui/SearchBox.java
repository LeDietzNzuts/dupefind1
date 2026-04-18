package net.p3pp3rf1y.sophisticatedcore.client.gui;

import java.util.List;
import java.util.Optional;
import net.minecraft.class_124;
import net.minecraft.class_2561;
import net.minecraft.class_310;
import net.minecraft.class_332;
import net.minecraft.class_437;
import net.p3pp3rf1y.sophisticatedcore.client.gui.controls.TextBox;
import net.p3pp3rf1y.sophisticatedcore.client.gui.utils.Dimension;
import net.p3pp3rf1y.sophisticatedcore.client.gui.utils.Position;
import net.p3pp3rf1y.sophisticatedcore.client.gui.utils.TranslationHelper;
import net.p3pp3rf1y.sophisticatedcore.util.Easing;

class SearchBox extends TextBox {
   private static final List<class_2561> TOOLTIP = List.of(
      class_2561.method_43471(TranslationHelper.INSTANCE.translGui("text_box.search_box")),
      class_2561.method_43471(TranslationHelper.INSTANCE.translGui("text_box.search_box_detail")).method_27692(class_124.field_1080)
   );
   public static final String MAGNIFYING_GLASS = "\ud83d\udd0d";
   public static final int UNFOCUSED_COLOR = 12303291;
   private final StorageScreenBase<?> screen;
   private long lastFocusChangeTime = 0L;
   private final int maximizedX;
   private final int maximizedWidth;

   public SearchBox(Position position, Dimension dimension, StorageScreenBase<?> screen) {
      super(position, dimension);
      this.screen = screen;
      this.setTextColor(12303291);
      this.setTextColorUneditable(12303291);
      this.setBordered(false);
      this.setMaxLength(50);
      this.setUnfocusedEmptyHint("\ud83d\udd0d");
      this.maximizedX = position.x();
      this.maximizedWidth = dimension.width();
   }

   public boolean method_25402(double mouseX, double mouseY, int button) {
      if (!this.method_25405(mouseX, mouseY)) {
         return false;
      } else if (this.isEditable()) {
         if (button == 0) {
            this.method_25365(true);
            this.screen.method_25395(this);
         } else if (button == 1) {
            this.setValue("");
         }

         return true;
      } else {
         return super.method_25402(mouseX, mouseY, button);
      }
   }

   @Override
   public void method_25365(boolean focused) {
      if (this.method_25370() != focused) {
         this.lastFocusChangeTime = System.currentTimeMillis();
      }

      super.method_25365(focused);
      if (focused) {
         this.setTextColor(-1);
      } else {
         this.setTextColor(12303291);
      }
   }

   @Override
   protected void renderBg(class_332 guiGraphics, class_310 minecraft, int mouseX, int mouseY) {
      int minWidth = this.getHeight();
      if (this.method_25370() && this.maximizedWidth > this.getWidth() || !this.method_25370() && this.getValue().isEmpty() && this.getWidth() > minWidth) {
         float ratio = Easing.EASE_IN_OUT_CUBIC.ease(Math.min((float)(System.currentTimeMillis() - this.lastFocusChangeTime) / 200.0F, 1.0F));
         int currentWidth = this.method_25370()
            ? (int)(minWidth + (this.maximizedWidth - minWidth) * ratio)
            : (int)(this.maximizedWidth - (this.maximizedWidth - minWidth) * ratio);
         this.setPosition(new Position(this.maximizedX + this.maximizedWidth - currentWidth, this.y));
         this.updateDimensions(currentWidth, this.getHeight());
      }

      guiGraphics.method_51448().method_22903();
      guiGraphics.method_51448().method_46416(0.0F, 0.0F, 100.0F);
      guiGraphics.method_25294(this.x, this.y, this.x + this.getWidth(), this.y + this.getHeight(), -8947849);
      guiGraphics.method_51448().method_22909();
   }

   @Override
   public void renderTooltip(class_437 screen, class_332 guiGraphics, int mouseX, int mouseY) {
      super.renderTooltip(screen, guiGraphics, mouseX, mouseY);
      if (!this.method_25370() && this.method_25405(mouseX, mouseY)) {
         guiGraphics.method_51437(screen.field_22793, TOOLTIP, Optional.empty(), mouseX, mouseY);
      }
   }
}
