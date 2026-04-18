package net.p3pp3rf1y.sophisticatedcore.upgrades.pump;

import net.minecraft.class_1657;
import net.minecraft.class_2487;
import net.p3pp3rf1y.sophisticatedcore.common.gui.UpgradeContainerBase;
import net.p3pp3rf1y.sophisticatedcore.common.gui.UpgradeContainerType;

public class PumpUpgradeContainer extends UpgradeContainerBase<PumpUpgradeWrapper, PumpUpgradeContainer> {
   private static final String DATA_IS_INPUT = "isInput";
   private static final String DATA_INTERACT_WITH_HAND = "interactWithHand";
   private static final String DATA_INTERACT_WITH_WORLD = "interactWithWorld";
   private final FluidFilterContainer fluidFilterContainer;

   public PumpUpgradeContainer(
      class_1657 player, int upgradeContainerId, PumpUpgradeWrapper upgradeWrapper, UpgradeContainerType<PumpUpgradeWrapper, PumpUpgradeContainer> type
   ) {
      super(player, upgradeContainerId, upgradeWrapper, type);
      this.fluidFilterContainer = new FluidFilterContainer(player, this, upgradeWrapper::getFluidFilterLogic);
   }

   public void setIsInput(boolean input) {
      this.upgradeWrapper.setIsInput(input);
      this.sendBooleanToServer("isInput", input);
   }

   public boolean isInput() {
      return this.upgradeWrapper.isInput();
   }

   @Override
   public void handlePacket(class_2487 data) {
      if (data.method_10545("isInput")) {
         this.setIsInput(data.method_10577("isInput"));
      } else if (data.method_10545("interactWithHand")) {
         this.setInteractWithHand(data.method_10577("interactWithHand"));
      } else if (data.method_10545("interactWithWorld")) {
         this.setInteractWithWorld(data.method_10577("interactWithWorld"));
      }

      this.fluidFilterContainer.handlePacket(data);
   }

   public FluidFilterContainer getFluidFilterContainer() {
      return this.fluidFilterContainer;
   }

   public void setInteractWithHand(boolean interactWithHand) {
      this.upgradeWrapper.setInteractWithHand(interactWithHand);
      this.sendBooleanToServer("interactWithHand", interactWithHand);
   }

   public boolean shouldInteractWithHand() {
      return this.upgradeWrapper.shouldInteractWithHand();
   }

   public void setInteractWithWorld(boolean interactWithWorld) {
      this.upgradeWrapper.setInteractWithWorld(interactWithWorld);
      this.sendBooleanToServer("interactWithWorld", interactWithWorld);
   }

   public boolean shouldInteractWithWorld() {
      return this.upgradeWrapper.shouldInteractWithWorld();
   }
}
