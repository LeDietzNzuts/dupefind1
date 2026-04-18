package net.p3pp3rf1y.sophisticatedcore.client.gui.controls;

import java.util.function.Consumer;
import javax.annotation.Nullable;
import net.minecraft.class_2561;
import net.minecraft.class_310;
import net.minecraft.class_332;
import net.minecraft.class_342;
import net.minecraft.class_4587;
import net.minecraft.class_6382;
import net.p3pp3rf1y.sophisticatedcore.client.gui.utils.Dimension;
import net.p3pp3rf1y.sophisticatedcore.client.gui.utils.Position;

public class TextBox extends WidgetBase {
   private final class_342 editBox;
   @Nullable
   private String unfocusedEmptyHint = null;

   public TextBox(Position position, Dimension dimension) {
      super(position, dimension);
      this.editBox = new class_342(this.minecraft.field_1772, position.x(), position.y(), dimension.width(), dimension.height(), class_2561.method_43473());
   }

   @Override
   protected void renderBg(class_332 guiGraphics, class_310 minecraft, int mouseX, int mouseY) {
   }

   @Override
   protected void renderWidget(class_332 guiGraphics, int mouseX, int mouseY, float partialTicks) {
      class_4587 poseStack = guiGraphics.method_51448();
      poseStack.method_22903();
      poseStack.method_46416(0.0F, 0.0F, 100.0F);
      this.editBox.method_48579(guiGraphics, mouseX, mouseY, partialTicks);
      if (this.editBox.method_1882().isEmpty() && this.unfocusedEmptyHint != null && !this.editBox.method_25370()) {
         int x = this.editBox.method_46426() + this.editBox.method_25368() / 2 + 2;
         int y = this.editBox.method_1851() ? this.editBox.method_46427() + (this.editBox.method_25364() - 8) / 2 : this.editBox.method_46427();
         guiGraphics.method_25300(this.font, this.unfocusedEmptyHint, x, y, this.editBox.field_2100);
      }

      poseStack.method_22909();
   }

   @Override
   public void method_25365(boolean focused) {
      if (this.editBox.method_25370() != focused) {
         this.editBox.method_25365(focused);
      }

      super.method_25365(focused);
   }

   public boolean method_25404(int keyCode, int scanCode, int modifiers) {
      if (!this.editBox.method_25370()) {
         return false;
      } else {
         this.editBox.method_25404(keyCode, scanCode, modifiers);
         if (keyCode == 257) {
            this.onEnterPressed();
         }

         return keyCode != 256;
      }
   }

   protected void onEnterPressed() {
   }

   public String getValue() {
      return this.editBox.method_1882();
   }

   public boolean method_25400(char codePoint, int modifiers) {
      return this.editBox.method_25400(codePoint, modifiers);
   }

   @Override
   public void method_37020(class_6382 narrationElementOutput) {
      this.editBox.method_37020(narrationElementOutput);
   }

   public void setValue(String value) {
      this.editBox.method_1852(value);
   }

   public void setValueWithoutNotification(String value) {
      Consumer<String> temp = this.editBox.field_2088;
      this.editBox.method_1863(null);
      this.editBox.method_1852(value);
      this.editBox.method_1863(temp);
   }

   public void setTextColor(int color) {
      this.editBox.method_1868(color);
   }

   public void setTextColorUneditable(int color) {
      this.editBox.method_1860(color);
   }

   public void setBordered(boolean bordered) {
      this.editBox.method_1858(bordered);
      if (!bordered) {
         this.editBox.method_46421(this.x + 1);
         this.editBox.method_46419(this.y + 1);
         this.editBox.method_25358(this.getWidth() - 6);
      } else {
         this.editBox.method_46421(this.x);
         this.editBox.method_46419(this.y);
         this.editBox.method_25358(this.getWidth());
      }
   }

   public void setMaxLength(int maxLength) {
      this.editBox.method_1880(maxLength);
   }

   public void setResponder(Consumer<String> responder) {
      this.editBox.method_1863(responder);
   }

   public void setEditable(boolean editable) {
      this.editBox.method_1888(editable);
   }

   public boolean isEditable() {
      return this.editBox.method_20316();
   }

   public void setUnfocusedEmptyHint(String hint) {
      this.unfocusedEmptyHint = hint;
   }

   @Override
   public void setPosition(Position position) {
      super.setPosition(position);
      this.editBox.method_46421(position.x());
      this.editBox.method_46419(position.y());
      this.setBordered(this.editBox.method_1851());
   }

   @Override
   protected void updateDimensions(int width, int height) {
      super.updateDimensions(width, height);
      this.editBox.method_25358(width);
      this.setBordered(this.editBox.method_1851());
   }
}
