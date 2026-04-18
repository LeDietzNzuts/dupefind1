package net.p3pp3rf1y.sophisticatedcore.settings;

import com.google.common.collect.ImmutableMap.Builder;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.class_1735;
import net.minecraft.class_1799;
import net.minecraft.class_332;
import net.minecraft.class_918;
import net.p3pp3rf1y.sophisticatedcore.client.gui.SettingsScreen;
import net.p3pp3rf1y.sophisticatedcore.client.gui.SettingsTabControl;
import net.p3pp3rf1y.sophisticatedcore.client.gui.Tab;
import net.p3pp3rf1y.sophisticatedcore.client.gui.utils.GuiHelper;
import net.p3pp3rf1y.sophisticatedcore.client.gui.utils.Position;
import net.p3pp3rf1y.sophisticatedcore.common.gui.SettingsContainerMenu;

public abstract class StorageSettingsTabControlBase extends SettingsTabControl<SettingsScreen, SettingsTab<?>> {
   private final List<SettingsTab<?>> settingsTabs = new ArrayList<>();
   protected final SettingsScreen screen;

   protected static <C extends SettingsContainerBase<?>, T extends SettingsTab<C>> void addFactory(
      Builder<String, StorageSettingsTabControlBase.ISettingsTabFactory<?, ?>> builder,
      String categoryName,
      StorageSettingsTabControlBase.ISettingsTabFactory<C, T> factory
   ) {
      builder.put(categoryName, factory);
   }

   protected StorageSettingsTabControlBase(SettingsScreen screen, Position position) {
      super(position);
      this.screen = screen;
      this.addChild(this.instantiateReturnBackTab());
      ((SettingsContainerMenu)screen.method_17577())
         .forEachSettingsContainer(
            (categoryName, settingsContainer) -> {
               if (!this.isSettingsCategoryDisabled(categoryName)) {
                  this.settingsTabs
                     .add(
                        this.addSettingsTab(
                           () -> {}, () -> {}, this.instantiateContainer(categoryName, settingsContainer, new Position(this.x, this.getTopY()), screen)
                        )
                     );
               }
            }
         );
   }

   protected boolean isSettingsCategoryDisabled(String categoryName) {
      return false;
   }

   protected abstract Tab instantiateReturnBackTab();

   public void renderSlotOverlays(
      class_332 guiGraphics, class_1735 slot, StorageSettingsTabControlBase.ISlotOverlayRenderer overlayRenderer, boolean templateLoadHovered
   ) {
      List<Integer> colors = new ArrayList<>();
      this.settingsTabs.forEach(tab -> tab.getSlotOverlayColor(slot.field_7874, templateLoadHovered).ifPresent(colors::add));
      if (!colors.isEmpty()) {
         int stripeHeight = 16 / colors.size();
         int i = 0;

         for (int color : colors) {
            int yOffset = i * stripeHeight;
            color = color & 16777215 | 1342177280;
            overlayRenderer.renderSlotOverlay(
               guiGraphics, slot.field_7873, slot.field_7872 + yOffset, i == colors.size() - 1 ? 16 - yOffset : stripeHeight, color
            );
            i++;
         }
      }
   }

   public class_1799 getSlotStackDisplayOverride(int slotNumber, boolean isTemplateLoadHovered) {
      for (SettingsTab<?> settingsTab : this.settingsTabs) {
         class_1799 stack = settingsTab.getItemDisplayOverride(slotNumber, isTemplateLoadHovered);
         if (!stack.method_7960()) {
            return stack;
         }
      }

      return class_1799.field_8037;
   }

   public void renderSlotExtra(class_332 guiGraphics, class_1735 slot) {
      this.settingsTabs.forEach(tab -> tab.renderExtra(guiGraphics, slot));
   }

   public void handleSlotClick(class_1735 slot, int mouseButton) {
      this.getOpenTab().ifPresent(tab -> tab.handleSlotClick(slot, mouseButton));
   }

   public boolean renderGuiItem(class_332 guiGraphics, class_918 itemRenderer, class_1799 itemstack, class_1735 slot, boolean templateLoadHovered) {
      for (SettingsTab<?> tab : this.settingsTabs) {
         int rotation = tab.getItemRotation(slot.field_7874, templateLoadHovered);
         if (rotation != 0) {
            GuiHelper.tryRenderGuiItem(guiGraphics, itemRenderer, this.minecraft.field_1724, itemstack, slot.field_7873, slot.field_7872, rotation);
            return true;
         }
      }

      if (!itemstack.method_7960()) {
         guiGraphics.method_51427(itemstack, slot.field_7873, slot.field_7872);
         return true;
      } else {
         return false;
      }
   }

   public void drawSlotStackOverlay(class_332 guiGraphics, class_1735 slot, boolean templateLoadHovered) {
      for (SettingsTab<?> tab : this.settingsTabs) {
         tab.drawSlotStackOverlay(guiGraphics, slot, templateLoadHovered);
      }
   }

   private <C extends SettingsContainerBase<?>> SettingsTab<C> instantiateContainer(String categoryName, C container, Position position, SettingsScreen screen) {
      return this.<C, SettingsTab<C>>getSettingsTabFactory(categoryName).create(container, position, screen);
   }

   protected abstract <C extends SettingsContainerBase<?>, T extends SettingsTab<C>> StorageSettingsTabControlBase.ISettingsTabFactory<C, T> getSettingsTabFactory(
      String var1
   );

   public interface ISettingsTabFactory<C extends SettingsContainerBase<?>, T extends SettingsTab<C>> {
      T create(C var1, Position var2, SettingsScreen var3);
   }

   public interface ISlotOverlayRenderer {
      void renderSlotOverlay(class_332 var1, int var2, int var3, int var4, int var5);
   }
}
