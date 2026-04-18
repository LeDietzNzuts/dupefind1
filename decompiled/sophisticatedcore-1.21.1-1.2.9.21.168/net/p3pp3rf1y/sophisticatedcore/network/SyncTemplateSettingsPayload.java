package net.p3pp3rf1y.sophisticatedcore.network;

import io.netty.buffer.ByteBuf;
import java.util.HashMap;
import java.util.Map;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking.Context;
import net.minecraft.class_1657;
import net.minecraft.class_2487;
import net.minecraft.class_8710;
import net.minecraft.class_9135;
import net.minecraft.class_9139;
import net.minecraft.class_8710.class_9154;
import net.p3pp3rf1y.sophisticatedcore.SophisticatedCore;
import net.p3pp3rf1y.sophisticatedcore.common.gui.SettingsContainerMenu;
import net.p3pp3rf1y.sophisticatedcore.settings.SettingsTemplateStorage;
import net.p3pp3rf1y.sophisticatedcore.util.StreamCodecHelper;

public record SyncTemplateSettingsPayload(Map<Integer, class_2487> playerTemplates, Map<String, class_2487> playerNamedTemplates) implements class_8710 {
   public static final class_9154<SyncTemplateSettingsPayload> TYPE = new class_9154(SophisticatedCore.getRL("sync_template_settings"));
   public static final class_9139<ByteBuf, SyncTemplateSettingsPayload> STREAM_CODEC = class_9139.method_56435(
      StreamCodecHelper.ofMap(class_9135.field_49675, class_9135.field_48556, HashMap::new),
      SyncTemplateSettingsPayload::playerTemplates,
      StreamCodecHelper.ofMap(class_9135.field_48554, class_9135.field_48556, HashMap::new),
      SyncTemplateSettingsPayload::playerNamedTemplates,
      SyncTemplateSettingsPayload::new
   );

   public class_9154<? extends class_8710> method_56479() {
      return TYPE;
   }

   @Environment(EnvType.CLIENT)
   public static void handlePayload(SyncTemplateSettingsPayload payload, Context context) {
      class_1657 player = context.player();
      SettingsTemplateStorage settingsTemplateStorage = SettingsTemplateStorage.get();
      settingsTemplateStorage.clearPlayerTemplates(player);
      payload.playerTemplates.forEach((k, v) -> settingsTemplateStorage.putPlayerTemplate(player, k, v));
      payload.playerNamedTemplates.forEach((k, v) -> settingsTemplateStorage.putPlayerNamedTemplate(player, k, v));
      if (player.field_7512 instanceof SettingsContainerMenu<?> settingsContainerMenu) {
         settingsContainerMenu.refreshTemplateSlots();
      }
   }
}
