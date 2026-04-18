package net.p3pp3rf1y.sophisticatedcore.compat.litematica.network;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import io.netty.buffer.ByteBuf;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking.Context;
import net.minecraft.class_1262;
import net.minecraft.class_1263;
import net.minecraft.class_1747;
import net.minecraft.class_1799;
import net.minecraft.class_2371;
import net.minecraft.class_2480;
import net.minecraft.class_3222;
import net.minecraft.class_8710;
import net.minecraft.class_9139;
import net.minecraft.class_9279;
import net.minecraft.class_9334;
import net.minecraft.class_7225.class_7874;
import net.minecraft.class_8710.class_9154;
import net.p3pp3rf1y.sophisticatedcore.SophisticatedCore;
import net.p3pp3rf1y.sophisticatedcore.api.IStorageWrapper;
import net.p3pp3rf1y.sophisticatedcore.compat.litematica.LitematicaCompat;
import net.p3pp3rf1y.sophisticatedcore.inventory.InventoryHandler;
import net.p3pp3rf1y.sophisticatedcore.network.PacketDistributor;
import net.p3pp3rf1y.sophisticatedcore.util.StreamCodecHelper;

public record RequestContentsPayload() implements class_8710 {
   public static final class_9154<RequestContentsPayload> TYPE = new class_9154(SophisticatedCore.getRL("litematica_request_contents"));
   public static final class_9139<ByteBuf, RequestContentsPayload> STREAM_CODEC = StreamCodecHelper.singleton(RequestContentsPayload::new);

   public class_9154<? extends class_8710> method_56479() {
      return TYPE;
   }

   public static void handlePayload(RequestContentsPayload payload, Context context) {
      List<class_1799> stacks = Lists.newArrayList();
      class_3222 player = context.player();
      class_1263 inv = player.method_31548();
      int size = inv.method_5439();

      for (int slot = 0; slot < size; slot++) {
         class_1799 stack = inv.method_5438(slot);
         if (!stack.method_7960()) {
            stacks.add(stack);
         }
      }

      Map<IStorageWrapper, class_8710> requested = Maps.newHashMap();
      requestContents(stacks, requested, context.server().method_30611());
      if (!requested.isEmpty()) {
         PacketDistributor.sendToPlayer(player, new UpdateMaterialListPayload(requested.size()));
         requested.forEach((wrapper, packet) -> PacketDistributor.sendToPlayer(player, packet));
      }
   }

   public static void requestContents(List<class_1799> stacks, Map<IStorageWrapper, class_8710> requested, class_7874 levelRegistry) {
      for (class_1799 stack : stacks) {
         LitematicaCompat.LitematicaWrapper litematicaWrapper = (LitematicaCompat.LitematicaWrapper)LitematicaCompat.LITEMATICA_CAPABILITY.find(stack, null);
         if (litematicaWrapper != null) {
            IStorageWrapper wrapper = litematicaWrapper.wrapper();
            UUID uuid = wrapper.getContentsUuid().orElse(null);
            if (uuid != null) {
               requested.put(wrapper, litematicaWrapper.packetGenerator().apply(uuid));
               List<class_1799> wrapperStacks = Lists.newArrayList();
               InventoryHandler handler = wrapper.getInventoryHandler();

               for (int slot = 0; slot < handler.getSlotCount(); slot++) {
                  class_1799 wrapperStack = handler.getSlotStack(slot);
                  if (!wrapperStack.method_7960()) {
                     wrapperStacks.add(wrapperStack);
                  }
               }

               requestContents(wrapperStacks, requested, levelRegistry);
            }
         } else if (stack.method_7909() instanceof class_1747
            && ((class_1747)stack.method_7909()).method_7711() instanceof class_2480
            && shulkerBoxHasItems(stack)) {
            requestContents(getStoredItems(stack, levelRegistry), requested, levelRegistry);
         }
      }
   }

   public static boolean shulkerBoxHasItems(class_1799 stackShulkerBox) {
      class_9279 data = (class_9279)stackShulkerBox.method_57824(class_9334.field_49611);
      return data != null && data.method_57450("Items") ? !data.method_57461().method_10554("Items", 10).isEmpty() : false;
   }

   public static class_2371<class_1799> getStoredItems(class_1799 stackIn, class_7874 levelRegistry) {
      class_2371<class_1799> items = class_2371.method_10211();
      class_9279 data = (class_9279)stackIn.method_57824(class_9334.field_49611);
      if (data != null && data.method_57450("Items")) {
         class_1262.method_5429(data.method_57461(), items, levelRegistry);
      }

      return items;
   }
}
