package net.p3pp3rf1y.sophisticatedcore.network;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking.Context;
import net.minecraft.class_1792;
import net.minecraft.class_6880;
import net.minecraft.class_7924;
import net.minecraft.class_8710;
import net.minecraft.class_9129;
import net.minecraft.class_9135;
import net.minecraft.class_9139;
import net.minecraft.class_8710.class_9154;
import net.p3pp3rf1y.sophisticatedcore.SophisticatedCore;
import net.p3pp3rf1y.sophisticatedcore.common.gui.IAdditionalSlotInfoMenu;
import net.p3pp3rf1y.sophisticatedcore.util.StreamCodecHelper;

public record SyncAdditionalSlotInfoPayload(
   Set<Integer> inaccessibleSlots, Map<Integer, Integer> slotLimitOverrides, Set<Integer> infiniteSlots, Map<Integer, class_6880<class_1792>> slotFilterItems
) implements class_8710 {
   public static final class_9154<SyncAdditionalSlotInfoPayload> TYPE = new class_9154(SophisticatedCore.getRL("sync_additional_slot_info"));
   private static final class_9139<class_9129, class_6880<class_1792>> ITEM_STREAM_CODEC = class_9135.method_56383(class_7924.field_41197);
   public static final class_9139<class_9129, SyncAdditionalSlotInfoPayload> STREAM_CODEC = class_9139.method_56905(
      StreamCodecHelper.ofCollection(class_9135.field_49675, HashSet::new),
      SyncAdditionalSlotInfoPayload::inaccessibleSlots,
      StreamCodecHelper.ofMap(class_9135.field_49675, class_9135.field_49675, HashMap::new),
      SyncAdditionalSlotInfoPayload::slotLimitOverrides,
      StreamCodecHelper.ofCollection(class_9135.field_49675, HashSet::new),
      SyncAdditionalSlotInfoPayload::infiniteSlots,
      StreamCodecHelper.ofMap(class_9135.field_49675, ITEM_STREAM_CODEC, HashMap::new),
      SyncAdditionalSlotInfoPayload::slotFilterItems,
      SyncAdditionalSlotInfoPayload::new
   );

   public class_9154<? extends class_8710> method_56479() {
      return TYPE;
   }

   @Environment(EnvType.CLIENT)
   public static void handlePayload(SyncAdditionalSlotInfoPayload payload, Context context) {
      if (context.player().field_7512 instanceof IAdditionalSlotInfoMenu menu) {
         menu.updateAdditionalSlotInfo(payload.inaccessibleSlots, payload.slotLimitOverrides, payload.infiniteSlots, payload.slotFilterItems);
      }
   }
}
