package net.p3pp3rf1y.sophisticatedcore.network;

import io.netty.buffer.ByteBuf;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking.Context;
import net.minecraft.class_1657;
import net.minecraft.class_2960;
import net.minecraft.class_8710;
import net.minecraft.class_9135;
import net.minecraft.class_9139;
import net.minecraft.class_8710.class_9154;
import net.p3pp3rf1y.sophisticatedcore.SophisticatedCore;
import net.p3pp3rf1y.sophisticatedcore.common.gui.IAdditionalSlotInfoMenu;
import net.p3pp3rf1y.sophisticatedcore.util.StreamCodecHelper;

public record SyncEmptySlotIconsPayload(Map<class_2960, Set<Integer>> emptySlotIcons) implements class_8710 {
   public static final class_9154<SyncEmptySlotIconsPayload> TYPE = new class_9154(SophisticatedCore.getRL("sync_empty_slot_icons"));
   public static final class_9139<ByteBuf, SyncEmptySlotIconsPayload> STREAM_CODEC = class_9139.method_56434(
      StreamCodecHelper.ofMap(class_2960.field_48267, StreamCodecHelper.ofCollection(class_9135.field_49675, HashSet::new), HashMap::new),
      SyncEmptySlotIconsPayload::emptySlotIcons,
      SyncEmptySlotIconsPayload::new
   );

   public class_9154<? extends class_8710> method_56479() {
      return TYPE;
   }

   @Environment(EnvType.CLIENT)
   public static void handlePayload(SyncEmptySlotIconsPayload payload, Context context) {
      class_1657 player = context.player();
      if (player.field_7512 instanceof IAdditionalSlotInfoMenu menu) {
         menu.updateEmptySlotIcons(payload.emptySlotIcons);
      }
   }
}
