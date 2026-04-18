package net.p3pp3rf1y.sophisticatedcore.network;

import javax.annotation.Nullable;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking.Context;
import net.minecraft.class_2487;
import net.minecraft.class_8710;
import net.minecraft.class_9129;
import net.minecraft.class_9135;
import net.minecraft.class_9139;
import net.minecraft.class_8710.class_9154;
import net.p3pp3rf1y.sophisticatedcore.SophisticatedCore;
import net.p3pp3rf1y.sophisticatedcore.common.gui.SettingsContainerMenu;
import net.p3pp3rf1y.sophisticatedcore.settings.DatapackSettingsTemplateManager;
import net.p3pp3rf1y.sophisticatedcore.util.StreamCodecHelper;

public record SyncDatapackSettingsTemplatePayload(String datapack, String templateName, @Nullable class_2487 settingsNbt) implements class_8710 {
   public static final class_9154<SyncDatapackSettingsTemplatePayload> TYPE = new class_9154(SophisticatedCore.getRL("sync_datapack_settings_template"));
   public static final class_9139<class_9129, SyncDatapackSettingsTemplatePayload> STREAM_CODEC = class_9139.method_56436(
      class_9135.field_48554,
      SyncDatapackSettingsTemplatePayload::datapack,
      class_9135.field_48554,
      SyncDatapackSettingsTemplatePayload::templateName,
      StreamCodecHelper.ofNullable(class_9135.field_48556),
      SyncDatapackSettingsTemplatePayload::settingsNbt,
      SyncDatapackSettingsTemplatePayload::new
   );

   public class_9154<? extends class_8710> method_56479() {
      return TYPE;
   }

   @Environment(EnvType.CLIENT)
   public static void handlePayload(SyncDatapackSettingsTemplatePayload payload, Context context) {
      if (payload.settingsNbt != null) {
         DatapackSettingsTemplateManager.putTemplate(payload.datapack, payload.templateName, payload.settingsNbt);
         if (context.player().field_7512 instanceof SettingsContainerMenu<?> settingsContainerMenu) {
            settingsContainerMenu.refreshTemplateSlots();
         }
      }
   }
}
