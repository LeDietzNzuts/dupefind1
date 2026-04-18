package net.p3pp3rf1y.sophisticatedcore.compat.jei;

import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking.Context;
import net.minecraft.class_1799;
import net.minecraft.class_8710;
import net.minecraft.class_9129;
import net.minecraft.class_9135;
import net.minecraft.class_9139;
import net.minecraft.class_8710.class_9154;
import net.p3pp3rf1y.sophisticatedcore.SophisticatedCore;
import net.p3pp3rf1y.sophisticatedcore.api.IStorageWrapper;
import net.p3pp3rf1y.sophisticatedcore.common.gui.SettingsContainerMenu;
import net.p3pp3rf1y.sophisticatedcore.settings.itemdisplay.ItemDisplaySettingsCategory;
import net.p3pp3rf1y.sophisticatedcore.settings.memory.MemorySettingsCategory;

public record SetMemorySlotPayload(class_1799 stack, int slotNumber) implements class_8710 {
   public static final class_9154<SetMemorySlotPayload> TYPE = new class_9154(SophisticatedCore.getRL("set_memory_slot"));
   public static final class_9139<class_9129, SetMemorySlotPayload> STREAM_CODEC = class_9139.method_56435(
      class_1799.field_48349, SetMemorySlotPayload::stack, class_9135.field_49675, SetMemorySlotPayload::slotNumber, SetMemorySlotPayload::new
   );

   public class_9154<? extends class_8710> method_56479() {
      return TYPE;
   }

   public static void handlePayload(SetMemorySlotPayload payload, Context context) {
      if (context.player().field_7512 instanceof SettingsContainerMenu<?> settingsContainerMenu) {
         IStorageWrapper storageWrapper = settingsContainerMenu.getStorageWrapper();
         storageWrapper.getSettingsHandler().getTypeCategory(MemorySettingsCategory.class).setFilter(payload.slotNumber, payload.stack);
         storageWrapper.getSettingsHandler().getTypeCategory(ItemDisplaySettingsCategory.class).itemChanged(payload.slotNumber);
         storageWrapper.getInventoryHandler().onSlotFilterChanged(payload.slotNumber);
         settingsContainerMenu.sendAdditionalSlotInfo();
      }
   }
}
