package net.p3pp3rf1y.sophisticatedcore.compat.emi;

import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking.Context;
import net.minecraft.class_1799;
import net.minecraft.class_8710;
import net.minecraft.class_9129;
import net.minecraft.class_9135;
import net.minecraft.class_9139;
import net.minecraft.class_8710.class_9154;
import net.p3pp3rf1y.sophisticatedcore.SophisticatedCore;
import net.p3pp3rf1y.sophisticatedcore.common.gui.StorageContainerMenuBase;

public record EmiSetGhostSlotPayload(class_1799 stack, int slotNumber) implements class_8710 {
   public static final class_9154<EmiSetGhostSlotPayload> TYPE = new class_9154(SophisticatedCore.getRL("emi_set_ghost_slot"));
   public static final class_9139<class_9129, EmiSetGhostSlotPayload> STREAM_CODEC = class_9139.method_56435(
      class_1799.field_48349, EmiSetGhostSlotPayload::stack, class_9135.field_49675, EmiSetGhostSlotPayload::slotNumber, EmiSetGhostSlotPayload::new
   );

   public class_9154<? extends class_8710> method_56479() {
      return TYPE;
   }

   public static void handlePayload(EmiSetGhostSlotPayload payload, Context context) {
      if (context.player().field_7512 instanceof StorageContainerMenuBase) {
         context.player().field_7512.method_7611(payload.slotNumber).method_7673(payload.stack);
      }
   }
}
