package net.p3pp3rf1y.sophisticatedbackpacks.upgrades.smithing;

import java.util.List;
import net.minecraft.class_1304;
import net.minecraft.class_1531;
import net.minecraft.class_1735;
import net.minecraft.class_1738;
import net.minecraft.class_1799;
import net.minecraft.class_2561;
import net.minecraft.class_310;
import net.minecraft.class_332;
import net.minecraft.class_437;
import net.minecraft.class_4895;
import net.minecraft.class_490;
import net.minecraft.class_8052;
import net.minecraft.class_8064;
import net.p3pp3rf1y.sophisticatedbackpacks.client.gui.SBPTranslationHelper;
import net.p3pp3rf1y.sophisticatedcore.client.gui.StorageScreenBase;
import net.p3pp3rf1y.sophisticatedcore.client.gui.UpgradeSettingsTab;
import net.p3pp3rf1y.sophisticatedcore.client.gui.utils.Dimension;
import net.p3pp3rf1y.sophisticatedcore.client.gui.utils.GuiHelper;
import net.p3pp3rf1y.sophisticatedcore.client.gui.utils.Position;
import net.p3pp3rf1y.sophisticatedcore.client.gui.utils.TextureBlitData;
import net.p3pp3rf1y.sophisticatedcore.client.gui.utils.UV;

public class SmithingUpgradeTab extends UpgradeSettingsTab<SmithingUpgradeContainer> {
   public static final TextureBlitData ARROW = new TextureBlitData(GuiHelper.GUI_CONTROLS, Dimension.SQUARE_256, new UV(56, 221), new Dimension(14, 15));
   public static final TextureBlitData RED_CROSS = new TextureBlitData(GuiHelper.GUI_CONTROLS, Dimension.SQUARE_256, new UV(113, 216), new Dimension(15, 15));
   private final class_8064 templateIcon;
   private final class_8064 baseIcon;
   private final class_8064 additionalIcon;
   private final class_1531 armorStandPreview;

   public SmithingUpgradeTab(SmithingUpgradeContainer upgradeContainer, Position position, StorageScreenBase<?> screen) {
      super(
         upgradeContainer,
         position,
         screen,
         SBPTranslationHelper.INSTANCE.translUpgrade("smithing", new Object[0]),
         SBPTranslationHelper.INSTANCE.translUpgradeTooltip("smithing")
      );
      this.openTabDimension = new Dimension(103, 100);
      this.armorStandPreview = new class_1531(this.minecraft.field_1687, 0.0, 0.0, 0.0);
      this.armorStandPreview.method_6907(true);
      this.armorStandPreview.method_6913(true);
      this.armorStandPreview.field_6283 = 210.0F;
      this.armorStandPreview.method_36457(25.0F);
      this.armorStandPreview.field_6241 = this.armorStandPreview.method_36454();
      this.armorStandPreview.field_6259 = this.armorStandPreview.method_36454();
      this.updateArmorStandPreview();
      this.templateIcon = new class_8064(((SmithingUpgradeContainer)this.getContainer()).getTemplateSlot().field_7874);
      this.baseIcon = new class_8064(((SmithingUpgradeContainer)this.getContainer()).getBaseSlot().field_7874);
      this.additionalIcon = new class_8064(((SmithingUpgradeContainer)this.getContainer()).getAdditionalSlot().field_7874);
      ((SmithingUpgradeContainer)this.getContainer()).setOnResultChangedHandler(this::updateArmorStandPreview);
   }

   private void updateArmorStandPreview() {
      class_1799 stack = ((SmithingUpgradeContainer)this.getContainer()).getResultSlot().method_7677();
      if (this.armorStandPreview != null) {
         for (class_1304 equipmentslot : class_1304.values()) {
            this.armorStandPreview.method_5673(equipmentslot, class_1799.field_8037);
         }

         if (!stack.method_7960()) {
            class_1799 itemstack = stack.method_7972();
            if (stack.method_7909() instanceof class_1738 armoritem) {
               this.armorStandPreview.method_5673(armoritem.method_7685(), itemstack);
            } else {
               this.armorStandPreview.method_5673(class_1304.field_6171, itemstack);
            }
         }
      }
   }

   protected void renderBg(class_332 guiGraphics, class_310 minecraft, int mouseX, int mouseY) {
      super.renderBg(guiGraphics, minecraft, mouseX, mouseY);
      if (((SmithingUpgradeContainer)this.getContainer()).isOpen()) {
         this.renderSlotBg(guiGraphics, ((SmithingUpgradeContainer)this.getContainer()).getTemplateSlot());
         this.renderSlotBg(guiGraphics, ((SmithingUpgradeContainer)this.getContainer()).getBaseSlot());
         this.renderSlotBg(guiGraphics, ((SmithingUpgradeContainer)this.getContainer()).getAdditionalSlot());
         this.renderSlotBg(guiGraphics, ((SmithingUpgradeContainer)this.getContainer()).getResultSlot());
         this.templateIcon
            .method_48469(
               ((StorageScreenBase)this.screen).method_17577(),
               guiGraphics,
               0.0F,
               ((StorageScreenBase)this.screen).getLeftX(),
               ((StorageScreenBase)this.screen).getTopY()
            );
         this.baseIcon
            .method_48469(
               ((StorageScreenBase)this.screen).method_17577(),
               guiGraphics,
               0.0F,
               ((StorageScreenBase)this.screen).getLeftX(),
               ((StorageScreenBase)this.screen).getTopY()
            );
         this.additionalIcon
            .method_48469(
               ((StorageScreenBase)this.screen).method_17577(),
               guiGraphics,
               0.0F,
               ((StorageScreenBase)this.screen).getLeftX(),
               ((StorageScreenBase)this.screen).getTopY()
            );
      }
   }

   private void renderSlotBg(class_332 guiGraphics, class_1735 slot) {
      GuiHelper.renderSlotsBackground(
         guiGraphics,
         slot.field_7873 + ((StorageScreenBase)this.screen).sophisticatedCore_getGuiLeft() - 1,
         slot.field_7872 + ((StorageScreenBase)this.screen).sophisticatedCore_getGuiTop() - 1,
         1,
         1
      );
   }

   public void renderTooltip(class_437 screen, class_332 guiGraphics, int mouseX, int mouseY) {
      super.renderTooltip(screen, guiGraphics, mouseX, mouseY);
      this.renderOnboardingTooltips(guiGraphics, mouseX, mouseY);
   }

   protected void renderWidget(class_332 guiGraphics, int mouseX, int mouseY, float partialTicks) {
      super.renderWidget(guiGraphics, mouseX, mouseY, partialTicks);
      if (this.isOpen) {
         class_1735 resultSlot = ((SmithingUpgradeContainer)this.getContainer()).getResultSlot();
         int inputSlotsY = resultSlot.field_7872 + ((StorageScreenBase)this.screen).sophisticatedCore_getGuiTop();
         int additionalSlotX = ((SmithingUpgradeContainer)this.getContainer()).getAdditionalSlot().field_7873
            + ((StorageScreenBase)this.screen).sophisticatedCore_getGuiLeft();
         int resultSlotX = resultSlot.field_7873 + ((StorageScreenBase)this.screen).sophisticatedCore_getGuiLeft();
         int arrowX = this.getArrowX(additionalSlotX, resultSlotX);
         int arrowY = this.getArrowY(inputSlotsY);
         GuiHelper.blit(guiGraphics, arrowX, arrowY, ARROW);
         if (this.hasRecipeError()) {
            GuiHelper.blit(guiGraphics, arrowX, arrowY, RED_CROSS);
         }

         class_490.method_48472(
            guiGraphics,
            this.getX() + this.getWidth() / 2.0F,
            this.getTopY() + this.getHeight() - 10,
            25.0F,
            class_4895.field_45497,
            class_4895.field_42048,
            null,
            this.armorStandPreview
         );
      }
   }

   private int getArrowY(int inputSlotsY) {
      return inputSlotsY + 1;
   }

   private int getArrowX(int additionalSlotX, int resultSlotX) {
      return additionalSlotX + 18 + (resultSlotX - (additionalSlotX + 18)) / 2 - ARROW.getWidth() / 2 - 1;
   }

   public void tick() {
      super.tick();
      this.templateIcon.method_48471(class_4895.field_42056);
      class_1799 templateItem = ((SmithingUpgradeContainer)this.getContainer()).getTemplateSlot().method_7677();
      if (templateItem.method_7909() instanceof class_8052 smithingTemplate) {
         this.baseIcon.method_48471(smithingTemplate.method_48423());
         this.additionalIcon.method_48471(smithingTemplate.method_48413());
      } else {
         this.baseIcon.method_48471(List.of());
         this.additionalIcon.method_48471(List.of());
      }
   }

   protected void moveSlotsToTab() {
      class_1735 templateSlot = ((SmithingUpgradeContainer)this.getContainer()).getTemplateSlot();
      templateSlot.field_7873 = this.x - ((StorageScreenBase)this.screen).sophisticatedCore_getGuiLeft() + 4;
      templateSlot.field_7872 = this.y - ((StorageScreenBase)this.screen).sophisticatedCore_getGuiTop() + 1 + 24;
      class_1735 baseSlot = ((SmithingUpgradeContainer)this.getContainer()).getBaseSlot();
      baseSlot.field_7873 = templateSlot.field_7873 + 18;
      baseSlot.field_7872 = this.y - ((StorageScreenBase)this.screen).sophisticatedCore_getGuiTop() + 1 + 24;
      class_1735 additionalSlot = ((SmithingUpgradeContainer)this.getContainer()).getAdditionalSlot();
      additionalSlot.field_7873 = baseSlot.field_7873 + 18;
      additionalSlot.field_7872 = this.y - ((StorageScreenBase)this.screen).sophisticatedCore_getGuiTop() + 1 + 24;
      class_1735 resultSlot = ((SmithingUpgradeContainer)this.getContainer()).getResultSlot();
      resultSlot.field_7873 = this.x - ((StorageScreenBase)this.screen).sophisticatedCore_getGuiLeft() + this.getWidth() - 2 - 3 - 18;
      resultSlot.field_7872 = this.y - ((StorageScreenBase)this.screen).sophisticatedCore_getGuiTop() + 1 + 24;
   }

   private boolean isHoveringRedCross(int mouseX, int mouseY) {
      class_1735 additionalSlot = ((SmithingUpgradeContainer)this.getContainer()).getAdditionalSlot();
      int arrowX = this.getArrowX(
         additionalSlot.field_7873 + ((StorageScreenBase)this.screen).sophisticatedCore_getGuiLeft(),
         ((SmithingUpgradeContainer)this.getContainer()).getResultSlot().field_7873 + ((StorageScreenBase)this.screen).sophisticatedCore_getGuiLeft()
      );
      int arrowY = this.getArrowY(additionalSlot.field_7872 + ((StorageScreenBase)this.screen).sophisticatedCore_getGuiTop());
      return mouseX >= arrowX && mouseX < arrowX + RED_CROSS.getWidth() && mouseY >= arrowY && mouseY < arrowY + RED_CROSS.getHeight();
   }

   private boolean isHoveringEmptySlot(class_1735 slot, int mouseX, int mouseY) {
      return mouseX >= slot.field_7873 + ((StorageScreenBase)this.screen).sophisticatedCore_getGuiLeft()
         && mouseX < slot.field_7873 + ((StorageScreenBase)this.screen).sophisticatedCore_getGuiLeft() + 16
         && mouseY >= slot.field_7872 + ((StorageScreenBase)this.screen).sophisticatedCore_getGuiTop()
         && mouseY < slot.field_7872 + ((StorageScreenBase)this.screen).sophisticatedCore_getGuiTop() + 16
         && slot.method_7677().method_7960();
   }

   private void renderOnboardingTooltips(class_332 guiGraphics, int mouseX, int mouseY) {
      if (this.hasRecipeError() && this.isHoveringRedCross(mouseX, mouseY)) {
         class_2561 tooltip = class_4895.field_42055;
         this.renderOnboardingTooltip(guiGraphics, mouseX, mouseY, tooltip);
      } else if (this.isHoveringEmptySlot(((SmithingUpgradeContainer)this.getContainer()).getTemplateSlot(), mouseX, mouseY)) {
         this.renderOnboardingTooltip(guiGraphics, mouseX, mouseY, class_4895.field_42054);
      } else if (((SmithingUpgradeContainer)this.getContainer()).getTemplateSlot().method_7677().method_7909() instanceof class_8052 smithingTemplate) {
         if (this.isHoveringEmptySlot(((SmithingUpgradeContainer)this.getContainer()).getBaseSlot(), mouseX, mouseY)) {
            this.renderOnboardingTooltip(guiGraphics, mouseX, mouseY, smithingTemplate.method_48421());
         } else if (this.isHoveringEmptySlot(((SmithingUpgradeContainer)this.getContainer()).getAdditionalSlot(), mouseX, mouseY)) {
            this.renderOnboardingTooltip(guiGraphics, mouseX, mouseY, smithingTemplate.method_48422());
         }
      }
   }

   private void renderOnboardingTooltip(class_332 guiGraphics, int mouseX, int mouseY, class_2561 tooltip) {
      guiGraphics.method_51448().method_22903();
      guiGraphics.method_51448().method_46416(0.0F, 0.0F, 410.0F);
      guiGraphics.method_51447(this.font, this.font.method_1728(tooltip, 115), mouseX, mouseY);
      guiGraphics.method_51448().method_22909();
   }

   private boolean hasRecipeError() {
      return ((SmithingUpgradeContainer)this.getContainer()).getTemplateSlot().method_7681()
         && ((SmithingUpgradeContainer)this.getContainer()).getBaseSlot().method_7681()
         && ((SmithingUpgradeContainer)this.getContainer()).getAdditionalSlot().method_7681()
         && !((SmithingUpgradeContainer)this.getContainer()).getResultSlot().method_7681();
   }
}
