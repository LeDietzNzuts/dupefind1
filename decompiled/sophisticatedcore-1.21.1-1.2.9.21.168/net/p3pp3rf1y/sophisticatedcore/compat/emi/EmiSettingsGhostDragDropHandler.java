package net.p3pp3rf1y.sophisticatedcore.compat.emi;

import dev.emi.emi.api.EmiDragDropHandler.SlotBased;
import dev.emi.emi.api.stack.EmiStack;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.class_1735;
import net.minecraft.class_1799;
import net.p3pp3rf1y.sophisticatedcore.client.gui.SettingsScreen;
import net.p3pp3rf1y.sophisticatedcore.common.gui.SettingsContainerMenu;
import net.p3pp3rf1y.sophisticatedcore.network.PacketDistributor;
import net.p3pp3rf1y.sophisticatedcore.settings.memory.MemorySettingsTab;

public class EmiSettingsGhostDragDropHandler<T extends SettingsScreen> extends SlotBased<T> {
   public EmiSettingsGhostDragDropHandler() {
      super(screen -> {
         List<class_1735> slots = new ArrayList<>();
         screen.getSettingsTabControl().getOpenTab().ifPresent(tab -> {
            if (tab instanceof MemorySettingsTab) {
               ((SettingsContainerMenu)screen.method_17577()).getStorageInventorySlots().forEach(s -> {
                  if (s.method_7677().method_7960()) {
                     slots.add(s);
                  }
               });
            }
         });
         return slots;
      }, (screen, slot, ingredient) -> {
         List<EmiStack> stacks = ingredient.getEmiStacks();
         if (stacks.size() == 1) {
            class_1799 stack = stacks.get(0).getItemStack();
            if (slot.method_7680(stack)) {
               PacketDistributor.sendToServer(new EmiSetMemorySlotPayload(stack, slot.field_7874));
            }
         }
      });
   }
}
