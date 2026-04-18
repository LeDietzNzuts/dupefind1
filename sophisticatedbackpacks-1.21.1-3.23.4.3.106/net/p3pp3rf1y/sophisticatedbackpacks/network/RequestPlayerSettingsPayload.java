package net.p3pp3rf1y.sophisticatedbackpacks.network;

import io.netty.buffer.ByteBuf;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking.Context;
import net.minecraft.class_1657;
import net.minecraft.class_3222;
import net.minecraft.class_8710;
import net.minecraft.class_9139;
import net.minecraft.class_8710.class_9154;
import net.p3pp3rf1y.sophisticatedbackpacks.SophisticatedBackpacks;
import net.p3pp3rf1y.sophisticatedcore.network.PacketDistributor;
import net.p3pp3rf1y.sophisticatedcore.network.SyncPlayerSettingsPayload;
import net.p3pp3rf1y.sophisticatedcore.settings.SettingsManager;
import net.p3pp3rf1y.sophisticatedcore.util.StreamCodecHelper;

public record RequestPlayerSettingsPayload() implements class_8710 {
   public static final class_9154<RequestPlayerSettingsPayload> TYPE = new class_9154(SophisticatedBackpacks.getRL("request_player_settings"));
   public static final class_9139<ByteBuf, RequestPlayerSettingsPayload> STREAM_CODEC = StreamCodecHelper.singleton(RequestPlayerSettingsPayload::new);

   public static void handlePayload(RequestPlayerSettingsPayload payload, Context context) {
      class_1657 player = context.player();
      String playerTagName = "sophisticatedBackpackSettings";
      if (player instanceof class_3222 serverPlayer) {
         PacketDistributor.sendToPlayer(serverPlayer, new SyncPlayerSettingsPayload(playerTagName, SettingsManager.getPlayerSettingsTag(player, playerTagName)));
      }
   }

   public class_9154<? extends class_8710> method_56479() {
      return TYPE;
   }
}
