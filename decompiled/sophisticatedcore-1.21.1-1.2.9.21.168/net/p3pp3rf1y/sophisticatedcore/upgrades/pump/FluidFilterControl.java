package net.p3pp3rf1y.sophisticatedcore.upgrades.pump;

import io.github.fabricators_of_create.porting_lib.fluids.FluidStack;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import net.fabricmc.fabric.api.client.render.fluid.v1.FluidRenderHandler;
import net.fabricmc.fabric.api.client.render.fluid.v1.FluidRenderHandlerRegistry;
import net.minecraft.class_1058;
import net.minecraft.class_310;
import net.minecraft.class_332;
import net.minecraft.class_437;
import net.minecraft.class_6382;
import net.p3pp3rf1y.sophisticatedcore.client.gui.controls.WidgetBase;
import net.p3pp3rf1y.sophisticatedcore.client.gui.utils.Dimension;
import net.p3pp3rf1y.sophisticatedcore.client.gui.utils.GuiHelper;
import net.p3pp3rf1y.sophisticatedcore.client.gui.utils.Position;

public class FluidFilterControl extends WidgetBase {
   private final FluidFilterContainer container;
   private final List<Position> slotTopLeftPositions = new ArrayList<>();

   protected FluidFilterControl(Position position, FluidFilterContainer container) {
      super(position, new Dimension(container.getNumberOfFluidFilters() * 18, 18));
      this.container = container;

      for (int i = 0; i < container.getNumberOfFluidFilters(); i++) {
         this.slotTopLeftPositions.add(new Position(this.x + i * 18 + 1, this.y + 1));
      }
   }

   @Override
   protected void renderBg(class_332 guiGraphics, class_310 minecraft, int mouseX, int mouseY) {
      GuiHelper.renderSlotsBackground(guiGraphics, this.x, this.y, this.container.getNumberOfFluidFilters(), 1);
   }

   @Override
   protected void renderWidget(class_332 guiGraphics, int mouseX, int mouseY, float partialTicks) {
      for (int i = 0; i < this.container.getNumberOfFluidFilters(); i++) {
         FluidStack fluid = this.container.getFluid(i);
         if (!fluid.isEmpty()) {
            FluidRenderHandler handler = FluidRenderHandlerRegistry.INSTANCE.get(fluid.getFluid());
            class_1058[] sprites = handler.getFluidSprites(null, null, fluid.getFluid().method_15785());
            int tint = handler.getFluidColor(null, null, fluid.getFluid().method_15785());
            GuiHelper.renderTiledFluidTextureAtlas(guiGraphics, sprites[0], tint, this.x + i * 18 + 1, this.y + 1, 16);
         }
      }
   }

   public boolean method_25402(double mouseX, double mouseY, int button) {
      if (!this.method_25405(mouseX, mouseY)) {
         return false;
      } else {
         this.getSlotClicked(mouseX, mouseY).ifPresent(this.container::slotClick);
         return true;
      }
   }

   @Override
   public void renderTooltip(class_437 screen, class_332 guiGraphics, int mouseX, int mouseY) {
      this.getSlotClicked(mouseX, mouseY).ifPresent(slot -> {
         FluidStack fluid = this.container.getFluid(slot);
         if (!fluid.isEmpty()) {
            GuiHelper.renderTooltip(screen, guiGraphics, List.of(fluid.getHoverName()), mouseX, mouseY);
         }
      });
   }

   public List<Position> getSlotTopLeftPositions() {
      return this.slotTopLeftPositions;
   }

   private Optional<Integer> getSlotClicked(double mouseX, double mouseY) {
      if (!(mouseY < this.y + 1) && !(mouseY >= this.y + 17)) {
         int index = (int)((mouseX - this.x) / 18.0);
         return index >= 0 && index < this.container.getNumberOfFluidFilters() ? Optional.of(index) : Optional.empty();
      } else {
         return Optional.empty();
      }
   }

   @Override
   public void method_37020(class_6382 narrationElementOutput) {
   }

   public void setFluid(int index, FluidStack fluid) {
      this.container.setFluid(index, fluid);
   }
}
