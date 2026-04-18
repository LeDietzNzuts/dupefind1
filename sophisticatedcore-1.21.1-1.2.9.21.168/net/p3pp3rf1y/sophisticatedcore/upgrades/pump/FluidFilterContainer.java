package net.p3pp3rf1y.sophisticatedcore.upgrades.pump;

import io.github.fabricators_of_create.porting_lib.fluids.FluidStack;
import io.github.fabricators_of_create.porting_lib.transfer.TransferUtil;
import java.util.function.Supplier;
import net.minecraft.class_1657;
import net.minecraft.class_1799;
import net.minecraft.class_2487;
import net.p3pp3rf1y.sophisticatedcore.common.gui.IServerUpdater;
import net.p3pp3rf1y.sophisticatedcore.util.CapabilityHelper;

public class FluidFilterContainer {
   private final class_1657 player;
   private final IServerUpdater serverUpdater;
   private final Supplier<FluidFilterLogic> fluidFilterLogic;
   private static final String DATA_FLUID = "setFluid";

   public FluidFilterContainer(class_1657 player, IServerUpdater serverUpdater, Supplier<FluidFilterLogic> fluidFilterLogic) {
      this.player = player;
      this.serverUpdater = serverUpdater;
      this.fluidFilterLogic = fluidFilterLogic;
   }

   public FluidStack getFluid(int index) {
      return this.fluidFilterLogic.get().getFluid(index);
   }

   public void setFluid(int index, FluidStack fluid) {
      this.fluidFilterLogic.get().setFluid(index, fluid);
      this.serverUpdater.sendDataToServer(() -> this.serializeSetFluidData(index, fluid));
   }

   private class_2487 serializeSetFluidData(int index, FluidStack fluid) {
      class_2487 ret = new class_2487();
      class_2487 fluidNbt = new class_2487();
      fluidNbt.method_10569("index", index);
      fluidNbt.method_10566("fluid", fluid.saveOptional(this.player.method_37908().method_30349()));
      ret.method_10566("setFluid", fluidNbt);
      return ret;
   }

   public boolean handlePacket(class_2487 data) {
      if (data.method_10545("setFluid")) {
         class_2487 fluidData = data.method_10562("setFluid");
         FluidStack fluid = FluidStack.parseOptional(this.player.method_37908().method_30349(), fluidData.method_10562("fluid"));
         this.setFluid(fluidData.method_10550("index"), fluid);
         return true;
      } else {
         return false;
      }
   }

   public int getNumberOfFluidFilters() {
      return this.fluidFilterLogic.get().getNumberOfFluidFilters();
   }

   public void slotClick(int index) {
      class_1799 carried = this.player.field_7512.method_34255();
      if (carried.method_7960()) {
         this.setFluid(index, FluidStack.EMPTY);
      } else {
         CapabilityHelper.runOnFluidHandler(carried, (cic, itemFluidHandler) -> {
            FluidStack containedFluid = TransferUtil.simulateExtractAnyFluid(itemFluidHandler, 81000L);
            if (!containedFluid.isEmpty()) {
               this.setFluid(index, containedFluid);
            }
         });
      }
   }
}
