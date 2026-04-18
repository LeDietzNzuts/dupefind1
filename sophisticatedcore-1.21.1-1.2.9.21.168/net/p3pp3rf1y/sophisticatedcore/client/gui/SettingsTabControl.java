package net.p3pp3rf1y.sophisticatedcore.client.gui;

import com.mojang.blaze3d.systems.RenderSystem;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.annotation.Nullable;
import net.minecraft.class_310;
import net.minecraft.class_332;
import net.minecraft.class_437;
import net.minecraft.class_4587;
import net.minecraft.class_465;
import net.minecraft.class_6382;
import net.minecraft.class_768;
import net.minecraft.class_6379.class_6380;
import net.p3pp3rf1y.sophisticatedcore.client.gui.controls.CompositeWidgetBase;
import net.p3pp3rf1y.sophisticatedcore.client.gui.utils.Dimension;
import net.p3pp3rf1y.sophisticatedcore.client.gui.utils.Position;
import org.apache.commons.lang3.mutable.MutableInt;

public abstract class SettingsTabControl<C extends class_465<?>, T extends SettingsTabBase<C>> extends CompositeWidgetBase<Tab> {
   private static final int VERTICAL_SPACE = 1;
   @Nullable
   private T openTab = (T)null;

   protected SettingsTabControl(Position position) {
      super(position, new Dimension(0, 0));
   }

   protected <U extends T> U addSettingsTab(Runnable onTabOpenContainerAction, Runnable onTabCloseContainerAction, U tab) {
      U settingsTab = (U)this.addChild(tab);
      settingsTab.setHandlers(
         () -> {
            if (this.openTab != null && this.differentTabIsOpen(settingsTab)) {
               this.openTab.close();
            }

            this.openTab = (T)settingsTab;
            onTabOpenContainerAction.run();
         },
         () -> {
            if (this.openTab != null) {
               this.openTab = null;
               onTabCloseContainerAction.run();
            }
         },
         () -> this.openTab == null || !this.differentTabIsOpen(settingsTab) || this.isNotCovered(this.openTab, settingsTab, true),
         () -> this.openTab == null || this.isNotCovered(this.openTab, settingsTab, false)
      );
      return settingsTab;
   }

   @Override
   protected void renderWidget(class_332 guiGraphics, int mouseX, int mouseY, float partialTicks) {
      class_4587 pose = guiGraphics.method_51448();
      pose.method_22903();
      pose.method_46416(0.0F, 0.0F, -11.0F);
      this.children.forEach(child -> {
         if (child != this.openTab) {
            child.method_25394(guiGraphics, mouseX, mouseY, partialTicks);
         }
      });
      pose.method_22909();
      if (this.openTab != null) {
         this.openTab.method_25394(guiGraphics, mouseX, mouseY, partialTicks);
      }

      RenderSystem.enableDepthTest();
   }

   private boolean isNotCovered(T open, Tab t, boolean checkFullyCovered) {
      return checkFullyCovered
         ? open.getBottomY() < t.getBottomY() || open.getTopY() > t.getTopY()
         : open.getBottomY() < t.getTopY() || open.getTopY() > t.getTopY();
   }

   private boolean differentTabIsOpen(Tab tab) {
      return this.openTab != tab;
   }

   public Optional<T> getOpenTab() {
      return Optional.ofNullable(this.openTab);
   }

   @Override
   protected void renderBg(class_332 guiGraphics, class_310 minecraft, int mouseX, int mouseY) {
   }

   @Override
   public void renderTooltip(class_437 screen, class_332 guiGraphics, int mouseX, int mouseY) {
      this.children.forEach(tab -> tab.renderTooltip(screen, guiGraphics, mouseX, mouseY));
   }

   protected int getTopY() {
      return this.y + this.children.size() * 25;
   }

   @Override
   public int getHeight() {
      MutableInt maxY = new MutableInt(0);
      this.children.forEach(tab -> {
         int bottomY = tab.getBottomY();
         if (bottomY > maxY.getValue()) {
            maxY.setValue(bottomY);
         }
      });
      return maxY.getValue() - this.y;
   }

   @Override
   public int getWidth() {
      MutableInt maxWidth = new MutableInt(0);
      this.children.forEach(tab -> {
         int width = tab.getWidth();
         if (width > maxWidth.getValue()) {
            maxWidth.setValue(width);
         }
      });
      return maxWidth.getValue();
   }

   public List<class_768> getTabRectangles() {
      List<class_768> ret = new ArrayList<>();
      this.children.forEach(child -> child.getTabRectangle().ifPresent(ret::add));
      return ret;
   }

   @Override
   public class_6380 method_37018() {
      return class_6380.field_33784;
   }

   @Override
   public void method_37020(class_6382 narrationElementOutput) {
   }
}
