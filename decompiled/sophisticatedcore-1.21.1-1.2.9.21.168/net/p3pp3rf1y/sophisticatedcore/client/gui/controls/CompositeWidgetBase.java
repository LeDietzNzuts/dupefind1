package net.p3pp3rf1y.sophisticatedcore.client.gui.controls;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.class_332;
import net.minecraft.class_364;
import net.minecraft.class_4069;
import net.minecraft.class_437;
import net.p3pp3rf1y.sophisticatedcore.client.gui.utils.Dimension;
import net.p3pp3rf1y.sophisticatedcore.client.gui.utils.Position;

public abstract class CompositeWidgetBase<T extends WidgetBase> extends WidgetBase implements class_4069 {
   protected final List<T> children = new ArrayList<>();
   private boolean dragging = false;
   @Nullable
   private class_364 listener;

   protected CompositeWidgetBase(Position position, Dimension dimension) {
      super(position, dimension);
   }

   @Override
   protected void renderWidget(class_332 guiGraphics, int mouseX, int mouseY, float partialTicks) {
      this.children.forEach(child -> child.method_25394(guiGraphics, mouseX, mouseY, partialTicks));
   }

   protected <U extends T> U addChild(U child) {
      this.children.add((T)child);
      return child;
   }

   public List<? extends class_364> method_25396() {
      return this.children;
   }

   public boolean method_25397() {
      for (T child : this.children) {
         if (child instanceof class_4069 containerEventHandler && containerEventHandler.method_25397()) {
            return true;
         }
      }

      return this.dragging;
   }

   public boolean method_25402(double mouseX, double mouseY, int button) {
      return this.method_19355(mouseX, mouseY).map(l -> {
         if (l.method_25402(mouseX, mouseY, button)) {
            this.method_25395(l);
            if (button == 0) {
               this.method_25398(true);
            }

            return true;
         } else {
            return false;
         }
      }).orElse(false);
   }

   public boolean method_25403(double mouseX, double mouseY, int button, double dragX, double dragY) {
      return this.method_19355(mouseX, mouseY).map(l -> l.method_25403(mouseX, mouseY, button, dragX, dragY)).orElse(false);
   }

   public void method_25398(boolean dragging) {
      this.dragging = dragging;
   }

   @Nullable
   public class_364 method_25399() {
      return this.listener;
   }

   public void method_25395(@Nullable class_364 listener) {
      this.listener = listener;
   }

   @Override
   public void renderTooltip(class_437 screen, class_332 guiGraphics, int mouseX, int mouseY) {
      super.renderTooltip(screen, guiGraphics, mouseX, mouseY);
      this.children.forEach(c -> c.renderTooltip(screen, guiGraphics, mouseX, mouseY));
   }
}
