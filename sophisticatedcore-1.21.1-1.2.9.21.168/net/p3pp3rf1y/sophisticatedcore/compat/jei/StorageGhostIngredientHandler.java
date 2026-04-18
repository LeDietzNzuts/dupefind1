package net.p3pp3rf1y.sophisticatedcore.compat.jei;

import io.github.fabricators_of_create.porting_lib.fluids.FluidStack;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.fabric.constants.FabricTypes;
import mezz.jei.api.gui.handlers.IGhostIngredientHandler;
import mezz.jei.api.gui.handlers.IGhostIngredientHandler.Target;
import mezz.jei.api.ingredients.ITypedIngredient;
import net.fabricmc.fabric.api.transfer.v1.storage.StorageUtil;
import net.minecraft.class_768;
import net.p3pp3rf1y.sophisticatedcore.client.gui.StorageScreenBase;
import net.p3pp3rf1y.sophisticatedcore.client.gui.utils.Position;
import net.p3pp3rf1y.sophisticatedcore.common.gui.IFilterSlot;
import net.p3pp3rf1y.sophisticatedcore.common.gui.StorageContainerMenuBase;
import net.p3pp3rf1y.sophisticatedcore.network.PacketDistributor;
import net.p3pp3rf1y.sophisticatedcore.upgrades.pump.PumpUpgradeTab;
import net.p3pp3rf1y.sophisticatedcore.util.CapabilityHelper;

public class StorageGhostIngredientHandler<S extends StorageScreenBase<?>> implements IGhostIngredientHandler<S> {
   public <I> List<Target<I>> getTargetsTyped(S gui, ITypedIngredient<I> ingredient, boolean doStart) {
      List<Target<I>> targets = new ArrayList<>();
      if (ingredient.getType() == VanillaTypes.ITEM_STACK) {
         StorageContainerMenuBase<?> container = (StorageContainerMenuBase<?>)gui.method_17577();
         ingredient.getItemStack()
            .ifPresent(
               ghostStack -> {
                  FluidStack fluidStack = CapabilityHelper.getFromFluidHandler(
                     ghostStack,
                     fluidHandler -> Optional.ofNullable(StorageUtil.findExtractableContent(fluidHandler, null))
                        .<FluidStack>map(FluidStack::new)
                        .orElse(FluidStack.EMPTY),
                     FluidStack.EMPTY
                  );
                  if (!fluidStack.isEmpty()) {
                     gui.getUpgradeSettingsControl()
                        .getOpenTab()
                        .filter(tab -> tab instanceof PumpUpgradeTab.Advanced)
                        .map(PumpUpgradeTab.Advanced.class::cast)
                        .ifPresent(pumpUpgradeTab -> this.addFluidTargets(pumpUpgradeTab, fluidStack, targets));
                  } else {
                     container.getOpenContainer().ifPresent(c -> c.getSlots().forEach(s -> {
                        if (s instanceof IFilterSlot && s.method_7680(ghostStack)) {
                           targets.add(new Target<I>() {
                              public class_768 getArea() {
                                 return new class_768(
                                    gui.sophisticatedCore_getGuiLeft() + s.field_7873, gui.sophisticatedCore_getGuiTop() + s.field_7872, 17, 17
                                 );
                              }

                              public void accept(I i) {
                                 PacketDistributor.sendToServer(new SetGhostSlotPayload(ghostStack, s.field_7874));
                              }
                           });
                        }
                     }));
                  }
               }
            );
      } else if (ingredient.getType() == FabricTypes.FLUID_STACK) {
         gui.getUpgradeSettingsControl()
            .getOpenTab()
            .filter(tab -> tab instanceof PumpUpgradeTab.Advanced)
            .map(PumpUpgradeTab.Advanced.class::cast)
            .ifPresent(
               pumpUpgradeTab -> FabricTypes.FLUID_STACK
                  .castIngredient(ingredient.getIngredient())
                  .ifPresent(ghostFluid -> this.addFluidTargets(pumpUpgradeTab, new FluidStack(ghostFluid.getFluidVariant(), ghostFluid.getAmount()), targets))
            );
      }

      return targets;
   }

   private <I> void addFluidTargets(PumpUpgradeTab.Advanced pumpUpgradeTab, FluidStack ghostFluid, List<Target<I>> targets) {
      List<Position> slotTopLeftPositions = pumpUpgradeTab.getFluidFilterControl().getSlotTopLeftPositions();
      final AtomicInteger slot = new AtomicInteger();
      slot.set(0);

      while (slot.get() < slotTopLeftPositions.size()) {
         final Position position = slotTopLeftPositions.get(slot.get());
         targets.add(new Target<I>() {
            private final int slotIndex = slot.get();

            public class_768 getArea() {
               return new class_768(position.x(), position.y(), 17, 17);
            }

            public void accept(I i) {
               pumpUpgradeTab.getFluidFilterControl().setFluid(this.slotIndex, ghostFluid);
            }
         });
         slot.incrementAndGet();
      }
   }

   public void onComplete() {
   }
}
