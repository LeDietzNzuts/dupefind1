package net.p3pp3rf1y.sophisticatedcore.upgrades.tank;

import io.github.fabricators_of_create.porting_lib.fluids.FluidStack;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import net.fabricmc.fabric.api.transfer.v1.client.fluid.FluidVariantRendering;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidVariant;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidVariantAttributes;
import net.minecraft.class_1058;
import net.minecraft.class_1799;
import net.minecraft.class_2561;
import net.minecraft.class_332;
import net.minecraft.class_3611;
import net.minecraft.class_5250;
import net.p3pp3rf1y.sophisticatedcore.client.gui.StorageScreenBase;
import net.p3pp3rf1y.sophisticatedcore.client.gui.UpgradeInventoryPartBase;
import net.p3pp3rf1y.sophisticatedcore.client.gui.utils.Dimension;
import net.p3pp3rf1y.sophisticatedcore.client.gui.utils.GuiHelper;
import net.p3pp3rf1y.sophisticatedcore.client.gui.utils.Position;
import net.p3pp3rf1y.sophisticatedcore.client.gui.utils.TextureBlitData;
import net.p3pp3rf1y.sophisticatedcore.client.gui.utils.TranslationHelper;
import net.p3pp3rf1y.sophisticatedcore.client.gui.utils.UV;
import net.p3pp3rf1y.sophisticatedcore.common.gui.StorageContainerMenuBase;
import net.p3pp3rf1y.sophisticatedcore.fluid.FluidUtil;
import net.p3pp3rf1y.sophisticatedcore.init.ModFluids;
import net.p3pp3rf1y.sophisticatedcore.network.PacketDistributor;
import net.p3pp3rf1y.sophisticatedcore.util.XpHelper;

public class TankInventoryPart extends UpgradeInventoryPartBase<TankUpgradeContainer> {
   private static final TextureBlitData OVERLAY = new TextureBlitData(GuiHelper.GUI_CONTROLS, Dimension.SQUARE_256, new UV(47, 30), new Dimension(16, 18));
   private final Position pos;
   private final int height;
   private final StorageScreenBase<?> screen;

   public TankInventoryPart(int upgradeSlot, TankUpgradeContainer container, Position pos, int height, StorageScreenBase<?> screen) {
      super(upgradeSlot, container);
      this.pos = pos;
      this.height = height;
      this.screen = screen;
   }

   @Override
   public void render(class_332 guiGraphics, int mouseX, int mouseY) {
      GuiHelper.blit(guiGraphics, this.getTankLeft(), this.pos.y(), GuiHelper.BAR_BACKGROUND_TOP);
      int yOffset = 18;

      for (int i = 0; i < (this.height - 36) / 18; i++) {
         GuiHelper.blit(guiGraphics, this.getTankLeft(), this.pos.y() + yOffset, GuiHelper.BAR_BACKGROUND_MIDDLE);
         yOffset += 18;
      }

      GuiHelper.blit(guiGraphics, this.getTankLeft(), this.pos.y() + yOffset, GuiHelper.BAR_BACKGROUND_BOTTOM);
      this.renderFluid(guiGraphics);
      guiGraphics.method_51448().method_22903();
      guiGraphics.method_51448().method_46416(0.0F, 0.0F, 100.0F);
      int var6 = 0;

      for (int i = 0; i < this.height / 18; i++) {
         GuiHelper.blit(guiGraphics, this.getTankLeft() + 1, this.pos.y() + var6, OVERLAY);
         var6 += 18;
      }

      guiGraphics.method_51448().method_22909();
   }

   private int getTankLeft() {
      return this.pos.x() + 9;
   }

   @Override
   public boolean handleMouseReleased(double mouseX, double mouseY, int button) {
      if (!(mouseX < this.screen.sophisticatedCore_getGuiLeft() + this.getTankLeft())
         && !(mouseX >= this.screen.sophisticatedCore_getGuiLeft() + this.getTankLeft() + 18)
         && !(mouseY < this.screen.sophisticatedCore_getGuiTop() + this.pos.y())
         && !(mouseY >= this.screen.sophisticatedCore_getGuiTop() + this.pos.y() + this.height)) {
         class_1799 cursorStack = ((StorageContainerMenuBase)this.screen.method_17577()).method_34255();
         if (cursorStack.method_7947() <= 1 && FluidUtil.isFluidStorage(cursorStack)) {
            PacketDistributor.sendToServer(new TankClickPayload(this.upgradeSlot));
            return true;
         } else {
            return false;
         }
      } else {
         return false;
      }
   }

   @Override
   public void renderErrorOverlay(class_332 guiGraphics) {
      this.screen.renderOverlay(guiGraphics, StorageScreenBase.ERROR_SLOT_COLOR, this.getTankLeft() + 1, this.pos.y() + 1, 16, this.height - 2);
   }

   @Override
   public void renderTooltip(StorageScreenBase<?> screen, class_332 guiGraphics, int mouseX, int mouseY) {
      FluidStack contents = this.container.getContents();
      long capacity = this.container.getTankCapacity();
      if (contents.isEmpty()) {
         contents = FluidStack.EMPTY;
      }

      int screenX = screen.sophisticatedCore_getGuiLeft() + this.pos.x() + 10;
      int screenY = screen.sophisticatedCore_getGuiTop() + this.pos.y() + 1;
      if (mouseX >= screenX && mouseX < screenX + 16 && mouseY >= screenY && mouseY < screenY + this.height - 2) {
         List<class_2561> tooltip = new ArrayList<>();
         if (!contents.isEmpty()) {
            tooltip.add(FluidVariantAttributes.getName(contents.getVariant()));
         }

         tooltip.add(this.getContentsTooltip(contents, capacity));
         guiGraphics.method_51437(screen.field_22793, tooltip, Optional.empty(), mouseX, mouseY);
      }
   }

   private class_5250 getContentsTooltip(FluidStack contents, long capacity) {
      if (contents.getFluid().method_15791(ModFluids.EXPERIENCE_TAG)) {
         double contentsLevels = XpHelper.getLevelsForExperience((int)XpHelper.liquidToExperience(contents.getAmount()));
         double tankCapacityLevels = XpHelper.getLevelsForExperience((int)XpHelper.liquidToExperience(capacity));
         return class_2561.method_43469(
            TranslationHelper.INSTANCE.translUpgradeKey("tank.xp_contents_tooltip"),
            new Object[]{String.format("%.1f", contentsLevels), String.format("%.1f", tankCapacityLevels)}
         );
      } else {
         return class_2561.method_43469(
            TranslationHelper.INSTANCE.translUpgradeKey("tank.contents_tooltip"),
            new Object[]{String.format("%,d", FluidUtil.toBuckets(contents.getAmount())), String.format("%,d", FluidUtil.toBuckets(capacity))}
         );
      }
   }

   private void renderFluid(class_332 guiGraphics) {
      FluidStack contents = this.container.getContents();
      long capacity = this.container.getTankCapacity();
      if (!contents.isEmpty()) {
         class_3611 fluid = contents.getFluid();
         long fill = contents.getAmount();
         int displayLevel = (int)((this.height - 2) * ((float)fill / (float)capacity));
         FluidVariant fluidVariant = FluidVariant.of(fluid);
         class_1058 still = FluidVariantRendering.getSprite(fluidVariant);
         GuiHelper.renderTiledFluidTextureAtlas(
            guiGraphics,
            still,
            FluidVariantRendering.getColor(fluidVariant),
            this.pos.x() + 10,
            this.pos.y() + 1 + this.height - 2 - displayLevel,
            displayLevel
         );
      }
   }
}
