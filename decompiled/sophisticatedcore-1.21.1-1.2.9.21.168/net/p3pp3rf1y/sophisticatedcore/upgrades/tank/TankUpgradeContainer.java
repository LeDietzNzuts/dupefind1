package net.p3pp3rf1y.sophisticatedcore.upgrades.tank;

import io.github.fabricators_of_create.porting_lib.fluids.FluidStack;
import io.github.fabricators_of_create.porting_lib.transfer.item.SlottedStackStorage;
import java.util.function.Supplier;
import net.minecraft.class_1657;
import net.minecraft.class_1723;
import net.minecraft.class_1799;
import net.minecraft.class_2487;
import net.minecraft.class_2561;
import net.minecraft.class_2960;
import net.p3pp3rf1y.sophisticatedcore.SophisticatedCore;
import net.p3pp3rf1y.sophisticatedcore.client.gui.INameableEmptySlot;
import net.p3pp3rf1y.sophisticatedcore.client.gui.utils.TranslationHelper;
import net.p3pp3rf1y.sophisticatedcore.common.gui.SlotSuppliedHandler;
import net.p3pp3rf1y.sophisticatedcore.common.gui.UpgradeContainerBase;
import net.p3pp3rf1y.sophisticatedcore.common.gui.UpgradeContainerType;

public class TankUpgradeContainer extends UpgradeContainerBase<TankUpgradeWrapper, TankUpgradeContainer> {
   public static final class_2960 EMPTY_TANK_INPUT_SLOT_BACKGROUND = SophisticatedCore.getRL("item/empty_tank_input_slot");
   public static final class_2960 EMPTY_TANK_OUTPUT_SLOT_BACKGROUND = SophisticatedCore.getRL("item/empty_tank_output_slot");

   public TankUpgradeContainer(
      class_1657 player, int upgradeContainerId, TankUpgradeWrapper upgradeWrapper, UpgradeContainerType<TankUpgradeWrapper, TankUpgradeContainer> type
   ) {
      super(player, upgradeContainerId, upgradeWrapper, type);
      this.slots
         .add(
            (new TankUpgradeContainer.TankIOSlot(
                  () -> this.upgradeWrapper.getInventory(), 0, -100, -100, TranslationHelper.INSTANCE.translUpgradeSlotTooltip("tank_input")
               ) {
                  @Override
                  public int method_7676(class_1799 stack) {
                     return 1;
                  }
               })
               .sophisticatedCore_setBackground(class_1723.field_21668, EMPTY_TANK_INPUT_SLOT_BACKGROUND)
         );
      this.slots
         .add(
            (new TankUpgradeContainer.TankIOSlot(
                  () -> this.upgradeWrapper.getInventory(), 1, -100, -100, TranslationHelper.INSTANCE.translUpgradeSlotTooltip("tank_output")
               ) {
                  @Override
                  public int method_7676(class_1799 stack) {
                     return 1;
                  }
               })
               .sophisticatedCore_setBackground(class_1723.field_21668, EMPTY_TANK_OUTPUT_SLOT_BACKGROUND)
         );
   }

   @Override
   public void handlePacket(class_2487 data) {
   }

   public FluidStack getContents() {
      return this.upgradeWrapper.getContents();
   }

   public long getTankCapacity() {
      return this.upgradeWrapper.getTankCapacity();
   }

   private static class TankIOSlot extends SlotSuppliedHandler implements INameableEmptySlot {
      private final class_2561 emptyTooltip;

      public TankIOSlot(Supplier<SlottedStackStorage> itemHandlerSupplier, int slot, int xPosition, int yPosition, class_2561 emptyTooltip) {
         super(itemHandlerSupplier, slot, xPosition, yPosition);
         this.emptyTooltip = emptyTooltip;
      }

      @Override
      public boolean hasEmptyTooltip() {
         return true;
      }

      @Override
      public class_2561 getEmptyTooltip() {
         return this.emptyTooltip;
      }
   }
}
