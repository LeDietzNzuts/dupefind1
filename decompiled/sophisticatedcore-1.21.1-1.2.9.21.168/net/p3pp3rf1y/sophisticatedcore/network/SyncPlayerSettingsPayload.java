package net.p3pp3rf1y.sophisticatedcore.network;

import io.netty.buffer.ByteBuf;
import javax.annotation.Nullable;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking.Context;
import net.minecraft.class_2487;
import net.minecraft.class_8710;
import net.minecraft.class_9135;
import net.minecraft.class_9139;
import net.minecraft.class_8710.class_9154;
import net.p3pp3rf1y.sophisticatedcore.SophisticatedCore;
import net.p3pp3rf1y.sophisticatedcore.settings.SettingsManager;
import net.p3pp3rf1y.sophisticatedcore.util.StreamCodecHelper;

public record SyncPlayerSettingsPayload(String playerTagName, @Nullable class_2487 settingsNbt) implements class_8710 {
   public static final class_9154<SyncPlayerSettingsPayload> TYPE = new class_9154(SophisticatedCore.getRL("sync_player_settings"));
   public static final class_9139<ByteBuf, SyncPlayerSettingsPayload> STREAM_CODEC = class_9139.method_56435(
      class_9135.field_48554,
      SyncPlayerSettingsPayload::playerTagName,
      StreamCodecHelper.ofNullable(class_9135.field_48556),
      SyncPlayerSettingsPayload::settingsNbt,
      SyncPlayerSettingsPayload::new
   );

   public class_9154<? extends class_8710> method_56479() {
      return TYPE;
   }

   @Environment(EnvType.CLIENT)
   public static void handlePayload(SyncPlayerSettingsPayload payload, Context context) {
      if (payload.settingsNbt != null) {
         SettingsManager.setPlayerSettingsTag(context.player(), payload.playerTagName, payload.settingsNbt);
      }
   }
}
