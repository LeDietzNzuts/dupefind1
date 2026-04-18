package net.p3pp3rf1y.sophisticatedcore.client.gui.controls;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.class_310;
import net.minecraft.class_327;
import net.minecraft.class_332;
import net.minecraft.class_364;
import net.minecraft.class_4068;
import net.minecraft.class_437;
import net.minecraft.class_6379;
import net.minecraft.class_6382;
import net.minecraft.class_6379.class_6380;
import net.p3pp3rf1y.sophisticatedcore.client.gui.utils.Dimension;
import net.p3pp3rf1y.sophisticatedcore.client.gui.utils.Position;

public abstract class WidgetBase implements class_4068, class_364, class_6379 {
   protected int x;
   protected int y;
   protected final class_310 minecraft;
   protected final class_327 font;
   private int height;
   private int width;
   protected boolean isHovered;
   protected boolean visible = true;
   private boolean focused = false;

   protected WidgetBase(Position position, Dimension dimension) {
      this.x = position.x();
      this.y = position.y();
      this.width = dimension.width();
      this.height = dimension.height();
      this.minecraft = class_310.method_1551();
      this.font = this.minecraft.field_1772;
   }

   public void setVisible(boolean visible) {
      this.visible = visible;
   }

   public void method_25394(class_332 guiGraphics, int mouseX, int mouseY, float partialTicks) {
      if (this.visible) {
         this.isHovered = mouseX >= this.x && mouseY >= this.y && mouseX < this.x + this.width && mouseY < this.y + this.height;
         RenderSystem.enableDepthTest();
         this.renderBg(guiGraphics, this.minecraft, mouseX, mouseY);
         this.renderWidget(guiGraphics, mouseX, mouseY, partialTicks);
      }
   }

   public class_6380 method_37018() {
      return this.isHovered ? class_6380.field_33785 : class_6380.field_33784;
   }

   protected abstract void renderBg(class_332 var1, class_310 var2, int var3, int var4);

   protected abstract void renderWidget(class_332 var1, int var2, int var3, float var4);

   public int getWidth() {
      return this.width;
   }

   public int getHeight() {
      return this.height;
   }

   protected void updateDimensions(int width, int height) {
      this.width = width;
      this.height = height;
   }

   public boolean method_25405(double mouseX, double mouseY) {
      return mouseX >= this.x && mouseX < this.x + this.getWidth() && mouseY >= this.y && mouseY < this.y + this.getHeight();
   }

   public int getX() {
      return this.x;
   }

   public int getY() {
      return this.y;
   }

   public void setPosition(Position position) {
      this.x = position.x();
      this.y = position.y();
   }

   protected int getCenteredX(int elementWidth) {
      return (this.getWidth() - elementWidth) / 2;
   }

   public void renderTooltip(class_437 screen, class_332 guiGraphics, int mouseX, int mouseY) {
   }

   public void method_25365(boolean focused) {
      this.focused = focused;
   }

   public boolean method_25370() {
      return this.focused;
   }

   public void method_37020(class_6382 narrationElementOutput) {
   }
}
