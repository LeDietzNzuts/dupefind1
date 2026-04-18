package net.p3pp3rf1y.sophisticatedcore.common.gui;

import net.minecraft.class_1703;
import net.minecraft.class_1799;
import net.minecraft.class_2371;
import net.minecraft.class_2653;
import net.minecraft.class_3222;
import net.minecraft.class_5916;
import net.p3pp3rf1y.sophisticatedcore.network.PacketDistributor;
import net.p3pp3rf1y.sophisticatedcore.network.SyncContainerStacksPayload;
import net.p3pp3rf1y.sophisticatedcore.network.SyncSlotStackPayload;

public class HighStackCountSynchronizer implements class_5916 {
   private final class_3222 player;

   public HighStackCountSynchronizer(class_3222 player) {
      this.player = player;
   }

   public void method_34263(class_1703 containerMenu, class_2371<class_1799> stacks, class_1799 carriedStack, int[] dataSlots) {
      PacketDistributor.sendToPlayer(this.player, new SyncContainerStacksPayload(containerMenu.field_7763, containerMenu.method_37422(), stacks, carriedStack));
   }

   public void method_34261(class_1703 containerMenu, int slotInd, class_1799 stack) {
      PacketDistributor.sendToPlayer(this.player, new SyncSlotStackPayload(containerMenu.field_7763, containerMenu.method_37422(), slotInd, stack));
   }

   public void method_34262(class_1703 containerMenu, class_1799 stack) {
      this.player.field_13987.method_14364(new class_2653(-1, containerMenu.method_37422(), -1, stack));
   }

   public void method_34260(class_1703 containerMenu, int slotInd, int data) {
   }
}
