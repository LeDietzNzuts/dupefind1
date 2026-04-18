package net.caffeinemc.mods.sodium.client.gui.prompt;

import java.util.List;
import net.caffeinemc.mods.sodium.client.gui.widgets.AbstractWidget;
import net.caffeinemc.mods.sodium.client.gui.widgets.FlatButtonWidget;
import net.caffeinemc.mods.sodium.client.util.Dim2i;
import net.minecraft.class_2561;
import net.minecraft.class_310;
import net.minecraft.class_327;
import net.minecraft.class_332;
import net.minecraft.class_364;
import net.minecraft.class_4068;
import net.minecraft.class_4587;
import net.minecraft.class_5348;
import net.minecraft.class_5481;
import org.jetbrains.annotations.NotNull;

public class ScreenPrompt implements class_364, class_4068 {
   private final ScreenPromptable parent;
   private final List<class_5348> text;
   private final ScreenPrompt.Action action;
   private FlatButtonWidget closeButton;
   private FlatButtonWidget actionButton;
   private final int width;
   private final int height;

   public ScreenPrompt(ScreenPromptable parent, List<class_5348> text, int width, int height, ScreenPrompt.Action action) {
      this.parent = parent;
      this.text = text;
      this.width = width;
      this.height = height;
      this.action = action;
   }

   public void init() {
      Dim2i parentDimensions = this.parent.getDimensions();
      int boxX = parentDimensions.width() / 2 - this.width / 2;
      int boxY = parentDimensions.height() / 2 - this.height / 2;
      this.closeButton = new FlatButtonWidget(new Dim2i(boxX + this.width - 84, boxY + this.height - 24, 80, 20), class_2561.method_43470("Close"), this::close);
      this.closeButton.setStyle(createButtonStyle());
      this.actionButton = new FlatButtonWidget(new Dim2i(boxX + this.width - 198, boxY + this.height - 24, 110, 20), this.action.label, this::runAction);
      this.actionButton.setStyle(createButtonStyle());
   }

   public void method_25394(class_332 graphics, int mouseX, int mouseY, float delta) {
      class_4587 matrices = graphics.method_51448();
      matrices.method_22903();
      matrices.method_46416(0.0F, 0.0F, 1000.0F);
      Dim2i parentDimensions = this.parent.getDimensions();
      graphics.method_25294(0, 0, parentDimensions.width(), parentDimensions.height(), 1879640329);
      matrices.method_46416(0.0F, 0.0F, 50.0F);
      int boxX = parentDimensions.width() / 2 - this.width / 2;
      int boxY = parentDimensions.height() / 2 - this.height / 2;
      graphics.method_25294(boxX, boxY, boxX + this.width, boxY + this.height, -15263977);
      graphics.method_49601(boxX, boxY, this.width, this.height, -15592942);
      matrices.method_46416(0.0F, 0.0F, 50.0F);
      int padding = 5;
      int textX = boxX + padding;
      int textY = boxY + padding;
      int textMaxWidth = this.width - padding * 2;
      int textMaxHeight = this.height - padding * 2;
      class_327 font = class_310.method_1551().field_1772;

      for (class_5348 paragraph : this.text) {
         for (class_5481 line : font.method_1728(paragraph, textMaxWidth)) {
            graphics.method_51430(font, line, textX, textY, -1, true);
            textY += 9 + 2;
         }

         textY += 8;
      }

      for (AbstractWidget button : this.getWidgets()) {
         button.method_25394(graphics, mouseX, mouseY, delta);
      }

      matrices.method_22909();
   }

   private static FlatButtonWidget.Style createButtonStyle() {
      FlatButtonWidget.Style style = new FlatButtonWidget.Style();
      style.bgDefault = -13948117;
      style.bgHovered = -13027015;
      style.bgDisabled = style.bgDefault;
      style.textDefault = -1;
      style.textDisabled = style.textDefault;
      return style;
   }

   @NotNull
   public List<AbstractWidget> getWidgets() {
      return List.of(this.actionButton, this.closeButton);
   }

   public void method_25365(boolean focused) {
      if (focused) {
         this.parent.setPrompt(this);
      } else {
         this.parent.setPrompt(null);
      }
   }

   public boolean method_25402(double mouseX, double mouseY, int button) {
      for (AbstractWidget widget : this.getWidgets()) {
         if (widget.method_25402(mouseX, mouseY, button)) {
            return true;
         }
      }

      return false;
   }

   public boolean method_25404(int keyCode, int scanCode, int modifiers) {
      if (keyCode == 256) {
         this.close();
         return true;
      } else {
         return super.method_25404(keyCode, scanCode, modifiers);
      }
   }

   public boolean method_25370() {
      return this.parent.getPrompt() == this;
   }

   private void close() {
      this.parent.setPrompt(null);
   }

   private void runAction() {
      this.action.runnable.run();
      this.close();
   }

   public record Action(class_2561 label, Runnable runnable) {
   }
}
