package net.p3pp3rf1y.sophisticatedbackpacks.network;

import io.netty.buffer.ByteBuf;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking.Context;
import net.minecraft.class_1657;
import net.minecraft.class_1723;
import net.minecraft.class_8710;
import net.minecraft.class_9135;
import net.minecraft.class_9139;
import net.minecraft.class_8710.class_9154;
import net.p3pp3rf1y.sophisticatedbackpacks.SophisticatedBackpacks;
import net.p3pp3rf1y.sophisticatedbackpacks.common.gui.BackpackContainer;
import net.p3pp3rf1y.sophisticatedbackpacks.common.gui.BackpackContext;
import net.p3pp3rf1y.sophisticatedbackpacks.common.gui.IContextAwareContainer;
import net.p3pp3rf1y.sophisticatedbackpacks.util.PlayerInventoryProvider;
import net.p3pp3rf1y.sophisticatedcore.common.gui.SophisticatedMenuProvider;

public record BackpackOpenPayload(int slotIndex, String identifier, String handlerName) implements class_8710 {
   public static final class_9154<BackpackOpenPayload> TYPE = new class_9154(SophisticatedBackpacks.getRL("backpack_open"));
   private static final int CHEST_SLOT = 38;
   private static final int OFFHAND_SLOT = 40;
   public static final class_9139<ByteBuf, BackpackOpenPayload> STREAM_CODEC = class_9139.method_56436(
      class_9135.field_49675,
      BackpackOpenPayload::slotIndex,
      class_9135.field_48554,
      BackpackOpenPayload::identifier,
      class_9135.field_48554,
      BackpackOpenPayload::handlerName,
      BackpackOpenPayload::new
   );

   public BackpackOpenPayload() {
      this(-1);
   }

   public BackpackOpenPayload(int backpackSlot) {
      this(backpackSlot, "");
   }

   public BackpackOpenPayload(int backpackSlot, String identifier) {
      this(backpackSlot, identifier, "");
   }

   public class_9154<? extends class_8710> method_56479() {
      return TYPE;
   }

   public static void handlePayload(BackpackOpenPayload payload, Context context) {
      class_1657 player = context.player();
      if (!payload.handlerName.isEmpty()) {
         int adjustedSlotIndex = payload.slotIndex;
         if (adjustedSlotIndex == 38) {
            adjustedSlotIndex -= 36;
         } else if (adjustedSlotIndex == 40) {
            adjustedSlotIndex = 0;
         }

         BackpackContext.Item backpackContext = new BackpackContext.Item(
            payload.handlerName,
            payload.identifier,
            adjustedSlotIndex,
            player.field_7512 instanceof class_1723
               || player.field_7512 instanceof BackpackContainer backpackContainer && backpackContainer.getBackpackContext().wasOpenFromInventory()
         );
         openBackpack(player, backpackContext);
      } else if (player.field_7512 instanceof BackpackContainer backpackContainer) {
         BackpackContext backpackContext = backpackContainer.getBackpackContext();
         if (payload.slotIndex == -1) {
            openBackpack(player, backpackContext.getParentBackpackContext());
         } else if (backpackContainer.isStorageInventorySlot(payload.slotIndex)) {
            openBackpack(player, backpackContext.getSubBackpackContext(payload.slotIndex));
         }
      } else if (player.field_7512 instanceof IContextAwareContainer contextAwareContainer) {
         BackpackContext backpackContext = contextAwareContainer.getBackpackContext();
         openBackpack(player, backpackContext);
      } else {
         findAndOpenFirstBackpack(player);
      }
   }

   private static void findAndOpenFirstBackpack(class_1657 player) {
      PlayerInventoryProvider.get()
         .runOnBackpacks(
            player,
            (backpack, inventoryName, identifier, slot) -> {
               BackpackContext.Item backpackContext = new BackpackContext.Item(inventoryName, identifier, slot);
               player.sophisticatedCore_openMenu(
                  new SophisticatedMenuProvider((w, p, pl) -> new BackpackContainer(w, pl, backpackContext), backpack.method_7964(), false),
                  backpackContext::toBuffer
               );
               return true;
            }
         );
   }

   private static void openBackpack(class_1657 player, BackpackContext backpackContext) {
      player.sophisticatedCore_openMenu(
         new SophisticatedMenuProvider((w, p, pl) -> new BackpackContainer(w, pl, backpackContext), backpackContext.getDisplayName(player), false),
         backpackContext::toBuffer
      );
   }
}
