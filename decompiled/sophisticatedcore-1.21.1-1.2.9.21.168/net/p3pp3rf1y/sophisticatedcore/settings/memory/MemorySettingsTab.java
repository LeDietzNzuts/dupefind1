package net.p3pp3rf1y.sophisticatedcore.settings.memory;

import com.google.common.collect.ImmutableList.Builder;
import com.mojang.blaze3d.systems.RenderSystem;
import java.util.Optional;
import net.minecraft.class_124;
import net.minecraft.class_1735;
import net.minecraft.class_1799;
import net.minecraft.class_2561;
import net.minecraft.class_332;
import net.minecraft.class_4587;
import net.p3pp3rf1y.sophisticatedcore.client.gui.SettingsScreen;
import net.p3pp3rf1y.sophisticatedcore.client.gui.controls.Button;
import net.p3pp3rf1y.sophisticatedcore.client.gui.controls.ButtonDefinition;
import net.p3pp3rf1y.sophisticatedcore.client.gui.controls.ButtonDefinitions;
import net.p3pp3rf1y.sophisticatedcore.client.gui.controls.ImageButton;
import net.p3pp3rf1y.sophisticatedcore.client.gui.controls.ToggleButton;
import net.p3pp3rf1y.sophisticatedcore.client.gui.utils.Dimension;
import net.p3pp3rf1y.sophisticatedcore.client.gui.utils.GuiHelper;
import net.p3pp3rf1y.sophisticatedcore.client.gui.utils.Position;
import net.p3pp3rf1y.sophisticatedcore.client.gui.utils.TextureBlitData;
import net.p3pp3rf1y.sophisticatedcore.client.gui.utils.TranslationHelper;
import net.p3pp3rf1y.sophisticatedcore.client.gui.utils.UV;
import net.p3pp3rf1y.sophisticatedcore.settings.SettingsTab;

public class MemorySettingsTab extends SettingsTab<MemorySettingsContainer> {
   private static final TextureBlitData ICON = new TextureBlitData(GuiHelper.ICONS, Dimension.SQUARE_256, new UV(128, 32), Dimension.SQUARE_16);
   private static final TextureBlitData SELECT_ALL_SLOTS_FOREGROUND = new TextureBlitData(
      GuiHelper.ICONS, new Position(1, 1), Dimension.SQUARE_256, new UV(16, 80), Dimension.SQUARE_16
   );
   public static final ButtonDefinition SELECT_ALL_SLOTS = new ButtonDefinition(
      Dimension.SQUARE_16,
      GuiHelper.DEFAULT_BUTTON_BACKGROUND,
      GuiHelper.DEFAULT_BUTTON_HOVERED_BACKGROUND,
      SELECT_ALL_SLOTS_FOREGROUND,
      class_2561.method_43471(TranslationHelper.INSTANCE.translSettingsButton("select_all_slots"))
   );
   private static final TextureBlitData UNSELECT_ALL_SLOTS_FOREGROUND = new TextureBlitData(
      GuiHelper.ICONS, new Position(1, 1), Dimension.SQUARE_256, new UV(48, 80), Dimension.SQUARE_16
   );
   public static final ButtonDefinition UNSELECT_ALL_SLOTS = new ButtonDefinition(
      Dimension.SQUARE_16,
      GuiHelper.DEFAULT_BUTTON_BACKGROUND,
      GuiHelper.DEFAULT_BUTTON_HOVERED_BACKGROUND,
      UNSELECT_ALL_SLOTS_FOREGROUND,
      class_2561.method_43471(TranslationHelper.INSTANCE.translSettingsButton("unselect_all_slots"))
   );

   public MemorySettingsTab(MemorySettingsContainer container, Position position, SettingsScreen screen) {
      super(
         container,
         position,
         screen,
         class_2561.method_43471(TranslationHelper.INSTANCE.translSettings("memory")),
         new Builder()
            .add(class_2561.method_43471(TranslationHelper.INSTANCE.translSettingsTooltip("memory")))
            .addAll(
               TranslationHelper.INSTANCE
                  .getTranslatedLines(TranslationHelper.INSTANCE.translSettingsTooltip("memory") + "_detail", null, class_124.field_1080)
            )
            .build(),
         new Builder()
            .add(class_2561.method_43471(TranslationHelper.INSTANCE.translSettingsTooltip("memory")))
            .addAll(
               TranslationHelper.INSTANCE
                  .getTranslatedLines(TranslationHelper.INSTANCE.translSettingsTooltip("memory") + "_open_detail", null, class_124.field_1080)
            )
            .build(),
         onTabIconClicked -> new ImageButton(new Position(position.x() + 1, position.y() + 4), Dimension.SQUARE_16, ICON, onTabIconClicked)
      );
      this.addHideableChild(new Button(new Position(this.x + 3, this.y + 24), SELECT_ALL_SLOTS, button -> container.selectAllSlots()));
      this.addHideableChild(new Button(new Position(this.x + 21, this.y + 24), UNSELECT_ALL_SLOTS, button -> container.unselectAllSlots()));
      this.addHideableChild(
         new ToggleButton<>(
            new Position(this.x + 39, this.y + 24),
            ButtonDefinitions.MATCH_NBT,
            button -> container.setIgnoreNbt(!container.ignoresNbt()),
            () -> !container.ignoresNbt()
         )
      );
   }

   @Override
   public Optional<Integer> getSlotOverlayColor(int slotNumber, boolean templateLoadHovered) {
      return Optional.empty();
   }

   @Override
   public void handleSlotClick(class_1735 slot, int mouseButton) {
      if (mouseButton == 0) {
         this.getSettingsContainer().selectSlot(slot.field_7874);
      } else if (mouseButton == 1) {
         this.getSettingsContainer().unselectSlot(slot.field_7874);
      }
   }

   @Override
   public class_1799 getItemDisplayOverride(int slotNumber, boolean templateLoadHovered) {
      if (templateLoadHovered) {
         class_1799 templatesMemorizedStack = this.getSettingsContainer().getSelectedTemplatesMemorizedStack(slotNumber);
         return !templatesMemorizedStack.method_7960() ? templatesMemorizedStack : class_1799.field_8037;
      } else {
         return this.getSettingsContainer().getMemorizedStack(slotNumber);
      }
   }

   @Override
   public void drawSlotStackOverlay(class_332 guiGraphics, class_1735 slot, boolean templateLoadHovered) {
      if (templateLoadHovered) {
         if (!this.getSettingsContainer().getSelectedTemplatesMemorizedStack(slot.sophisticatedCore_getSlotIndex()).method_7960()) {
            this.drawMemorizedStackOverlay(guiGraphics, slot);
         }
      } else if (this.getSettingsContainer().isSlotSelected(slot.sophisticatedCore_getSlotIndex())) {
         this.drawMemorizedStackOverlay(guiGraphics, slot);
      }
   }

   private void drawMemorizedStackOverlay(class_332 guiGraphics, class_1735 slot) {
      class_4587 poseStack = guiGraphics.method_51448();
      poseStack.method_22903();
      RenderSystem.enableBlend();
      RenderSystem.disableDepthTest();
      guiGraphics.method_25302(GuiHelper.GUI_CONTROLS, slot.field_7873, slot.field_7872, 77, 0, 16, 16);
      RenderSystem.enableDepthTest();
      RenderSystem.disableBlend();
      poseStack.method_22909();
   }
}
