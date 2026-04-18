package net.p3pp3rf1y.sophisticatedbackpacks.network;

import io.netty.buffer.ByteBuf;
import javax.annotation.Nullable;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking.Context;
import net.minecraft.class_1657;
import net.minecraft.class_1799;
import net.minecraft.class_2487;
import net.minecraft.class_8710;
import net.minecraft.class_9135;
import net.minecraft.class_9139;
import net.minecraft.class_8710.class_9154;
import net.p3pp3rf1y.sophisticatedbackpacks.SophisticatedBackpacks;
import net.p3pp3rf1y.sophisticatedbackpacks.backpack.wrapper.BackpackWrapper;
import net.p3pp3rf1y.sophisticatedbackpacks.backpack.wrapper.IBackpackWrapper;
import net.p3pp3rf1y.sophisticatedbackpacks.common.gui.BackpackContainer;
import net.p3pp3rf1y.sophisticatedcore.util.StreamCodecHelper;

public record SyncClientInfoPayload(int slotIndex, @Nullable class_2487 renderInfoNbt, int columnsTaken) implements class_8710 {
   public static final class_9154<SyncClientInfoPayload> TYPE = new class_9154(SophisticatedBackpacks.getRL("sync_client_info"));
   public static final class_9139<ByteBuf, SyncClientInfoPayload> STREAM_CODEC = class_9139.method_56436(
      class_9135.field_49675,
      SyncClientInfoPayload::slotIndex,
      StreamCodecHelper.ofNullable(class_9135.field_48556),
      SyncClientInfoPayload::renderInfoNbt,
      class_9135.field_49675,
      SyncClientInfoPayload::columnsTaken,
      SyncClientInfoPayload::new
   );

   public class_9154<? extends class_8710> method_56479() {
      return TYPE;
   }

   @Environment(EnvType.CLIENT)
   public static void handlePayload(SyncClientInfoPayload payload, Context context) {
      class_1657 player = context.player();
      if (payload.renderInfoNbt != null && player.field_7512 instanceof BackpackContainer) {
         class_1799 backpack = (class_1799)player.method_31548().field_7547.get(payload.slotIndex);
         IBackpackWrapper backpackWrapper = BackpackWrapper.fromStack(backpack);
         backpackWrapper.getRenderInfo().deserializeFrom(payload.renderInfoNbt);
         backpackWrapper.setColumnsTaken(payload.columnsTaken, false);
      }
   }
}
