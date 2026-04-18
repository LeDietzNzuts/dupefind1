package net.p3pp3rf1y.sophisticatedcore.client.gui.controls;

import com.mojang.blaze3d.systems.RenderSystem;
import java.util.Collections;
import java.util.List;
import net.minecraft.class_286;
import net.minecraft.class_287;
import net.minecraft.class_289;
import net.minecraft.class_290;
import net.minecraft.class_310;
import net.minecraft.class_332;
import net.minecraft.class_362;
import net.minecraft.class_364;
import net.minecraft.class_4068;
import net.minecraft.class_437;
import net.minecraft.class_6379;
import net.minecraft.class_757;
import net.minecraft.class_293.class_5596;

public abstract class ScrollPanel extends class_362 implements class_4068, class_6379 {
   private final class_310 client;
   protected final int width;
   protected final int height;
   protected final int top;
   protected final int bottom;
   protected final int right;
   protected final int left;
   private boolean scrolling;
   protected float scrollDistance;
   protected boolean captureMouse = true;
   protected final int border;
   private final int barWidth;
   private final int barLeft;
   private final int bgColorFrom;
   private final int bgColorTo;
   private final int barBgColor;
   private final int barColor;
   private final int barBorderColor;

   public ScrollPanel(class_310 client, int width, int height, int top, int left) {
      this(client, width, height, top, left, 4);
   }

   public ScrollPanel(class_310 client, int width, int height, int top, int left, int border) {
      this(client, width, height, top, left, border, 6);
   }

   public ScrollPanel(class_310 client, int width, int height, int top, int left, int border, int barWidth) {
      this(client, width, height, top, left, border, barWidth, -1072689136, -804253680);
   }

   public ScrollPanel(class_310 client, int width, int height, int top, int left, int border, int barWidth, int bgColor) {
      this(client, width, height, top, left, border, barWidth, bgColor, bgColor);
   }

   public ScrollPanel(class_310 client, int width, int height, int top, int left, int border, int barWidth, int bgColorFrom, int bgColorTo) {
      this(client, width, height, top, left, border, barWidth, bgColorFrom, bgColorTo, -16777216, -8355712, -4144960);
   }

   public ScrollPanel(
      class_310 client,
      int width,
      int height,
      int top,
      int left,
      int border,
      int barWidth,
      int bgColorFrom,
      int bgColorTo,
      int barBgColor,
      int barColor,
      int barBorderColor
   ) {
      this.client = client;
      this.width = width;
      this.height = height;
      this.top = top;
      this.left = left;
      this.bottom = height + this.top;
      this.right = width + this.left;
      this.barLeft = this.left + this.width - barWidth;
      this.border = border;
      this.barWidth = barWidth;
      this.bgColorFrom = bgColorFrom;
      this.bgColorTo = bgColorTo;
      this.barBgColor = barBgColor;
      this.barColor = barColor;
      this.barBorderColor = barBorderColor;
   }

   protected abstract int getContentHeight();

   protected void drawBackground(class_332 guiGraphics, class_289 tess, float partialTick) {
      class_437.method_57737(guiGraphics, class_437.field_49511, this.left, this.top, 0.0F, 0.0F, this.width, this.height);
   }

   protected abstract void drawPanel(class_332 var1, int var2, int var3, class_289 var4, int var5, int var6);

   protected boolean clickPanel(double mouseX, double mouseY, int button) {
      return false;
   }

   private int getMaxScroll() {
      return this.getContentHeight() - (this.height - this.border);
   }

   private void applyScrollLimits() {
      int max = this.getMaxScroll();
      if (max < 0) {
         max /= 2;
      }

      if (this.scrollDistance < 0.0F) {
         this.scrollDistance = 0.0F;
      }

      if (this.scrollDistance > max) {
         this.scrollDistance = max;
      }
   }

   public boolean method_25401(double mouseX, double mouseY, double scrollX, double scrollY) {
      if (scrollY != 0.0) {
         this.scrollDistance = (float)(this.scrollDistance + -scrollY * this.getScrollAmount());
         this.applyScrollLimits();
         return true;
      } else {
         return false;
      }
   }

   protected int getScrollAmount() {
      return 20;
   }

   public boolean method_25405(double mouseX, double mouseY) {
      return mouseX >= this.left && mouseX <= this.left + this.width && mouseY >= this.top && mouseY <= this.bottom;
   }

   public boolean method_25402(double mouseX, double mouseY, int button) {
      if (super.method_25402(mouseX, mouseY, button)) {
         return true;
      } else {
         this.scrolling = button == 0 && mouseX >= this.barLeft && mouseX < this.barLeft + this.barWidth;
         if (this.scrolling) {
            return true;
         } else {
            int mouseListY = (int)mouseY - this.top - this.getContentHeight() + (int)this.scrollDistance - this.border;
            return mouseX >= this.left && mouseX <= this.right && mouseListY < 0
               ? this.clickPanel(mouseX - this.left, mouseY - this.top + (int)this.scrollDistance - this.border, button)
               : false;
         }
      }
   }

   public boolean method_25406(double mouseX, double mouseY, int button) {
      if (super.method_25406(mouseX, mouseY, button)) {
         return true;
      } else {
         boolean ret = this.scrolling;
         this.scrolling = false;
         return ret;
      }
   }

   private int getBarHeight() {
      int barHeight = this.height * this.height / this.getContentHeight();
      if (barHeight < 32) {
         barHeight = 32;
      }

      if (barHeight > this.height - this.border * 2) {
         barHeight = this.height - this.border * 2;
      }

      return barHeight;
   }

   public boolean method_25403(double mouseX, double mouseY, int button, double deltaX, double deltaY) {
      if (this.scrolling) {
         int maxScroll = this.height - this.getBarHeight();
         double moved = deltaY / maxScroll;
         this.scrollDistance = (float)(this.scrollDistance + this.getMaxScroll() * moved);
         this.applyScrollLimits();
         return true;
      } else {
         return false;
      }
   }

   public void method_25394(class_332 graphics, int mouseX, int mouseY, float partialTick) {
      class_289 tess = class_289.method_1348();
      double scale = this.client.method_22683().method_4495();
      RenderSystem.enableScissor(
         (int)(this.left * scale), (int)(this.client.method_22683().method_4506() - this.bottom * scale), (int)(this.width * scale), (int)(this.height * scale)
      );
      this.drawBackground(graphics, tess, partialTick);
      int baseY = this.top + this.border - (int)this.scrollDistance;
      this.drawPanel(graphics, this.right, baseY, tess, mouseX, mouseY);
      RenderSystem.disableDepthTest();
      int extraHeight = this.getContentHeight() + this.border - this.height;
      if (extraHeight > 0) {
         int barHeight = this.getBarHeight();
         int barTop = (int)this.scrollDistance * (this.height - barHeight) / extraHeight + this.top;
         if (barTop < this.top) {
            barTop = this.top;
         }

         int barBgAlpha = this.barBgColor >> 24 & 0xFF;
         int barBgRed = this.barBgColor >> 16 & 0xFF;
         int barBgGreen = this.barBgColor >> 8 & 0xFF;
         int barBgBlue = this.barBgColor & 0xFF;
         RenderSystem.setShader(class_757::method_34540);
         class_287 worldr = tess.method_60827(class_5596.field_27382, class_290.field_1576);
         worldr.method_22912(this.barLeft, this.bottom, 0.0F).method_1336(barBgRed, barBgGreen, barBgBlue, barBgAlpha);
         worldr.method_22912(this.barLeft + this.barWidth, this.bottom, 0.0F).method_1336(barBgRed, barBgGreen, barBgBlue, barBgAlpha);
         worldr.method_22912(this.barLeft + this.barWidth, this.top, 0.0F).method_1336(barBgRed, barBgGreen, barBgBlue, barBgAlpha);
         worldr.method_22912(this.barLeft, this.top, 0.0F).method_1336(barBgRed, barBgGreen, barBgBlue, barBgAlpha);
         class_286.method_43433(worldr.method_60800());
         int barAlpha = this.barColor >> 24 & 0xFF;
         int barRed = this.barColor >> 16 & 0xFF;
         int barGreen = this.barColor >> 8 & 0xFF;
         int barBlue = this.barColor & 0xFF;
         worldr = tess.method_60827(class_5596.field_27382, class_290.field_1576);
         worldr.method_22912(this.barLeft, barTop + barHeight, 0.0F).method_1336(barRed, barGreen, barBlue, barAlpha);
         worldr.method_22912(this.barLeft + this.barWidth, barTop + barHeight, 0.0F).method_1336(barRed, barGreen, barBlue, barAlpha);
         worldr.method_22912(this.barLeft + this.barWidth, barTop, 0.0F).method_1336(barRed, barGreen, barBlue, barAlpha);
         worldr.method_22912(this.barLeft, barTop, 0.0F).method_1336(barRed, barGreen, barBlue, barAlpha);
         class_286.method_43433(worldr.method_60800());
         int barBorderAlpha = this.barBorderColor >> 24 & 0xFF;
         int barBorderRed = this.barBorderColor >> 16 & 0xFF;
         int barBorderGreen = this.barBorderColor >> 8 & 0xFF;
         int barBorderBlue = this.barBorderColor & 0xFF;
         worldr = tess.method_60827(class_5596.field_27382, class_290.field_1576);
         worldr.method_22912(this.barLeft, barTop + barHeight - 1, 0.0F).method_1336(barBorderRed, barBorderGreen, barBorderBlue, barBorderAlpha);
         worldr.method_22912(this.barLeft + this.barWidth - 1, barTop + barHeight - 1, 0.0F)
            .method_1336(barBorderRed, barBorderGreen, barBorderBlue, barBorderAlpha);
         worldr.method_22912(this.barLeft + this.barWidth - 1, barTop, 0.0F).method_1336(barBorderRed, barBorderGreen, barBorderBlue, barBorderAlpha);
         worldr.method_22912(this.barLeft, barTop, 0.0F).method_1336(barBorderRed, barBorderGreen, barBorderBlue, barBorderAlpha);
         class_286.method_43433(worldr.method_60800());
      }

      RenderSystem.disableBlend();
      RenderSystem.disableScissor();
   }

   protected void drawGradientRect(class_332 guiGraphics, int left, int top, int right, int bottom, int startColor, int endColor) {
      guiGraphics.method_25296(left, top, right, bottom, startColor, endColor);
   }

   public List<? extends class_364> method_25396() {
      return Collections.emptyList();
   }
}
