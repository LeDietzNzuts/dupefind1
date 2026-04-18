package net.p3pp3rf1y.sophisticatedcore.init;

import net.fabricmc.api.EnvType;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking.PlayPayloadHandler;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.class_8710;
import net.minecraft.class_9129;
import net.minecraft.class_9139;
import net.minecraft.class_8710.class_9154;
import net.p3pp3rf1y.sophisticatedcore.compat.litematica.network.LitematicaPayloads;
import net.p3pp3rf1y.sophisticatedcore.network.SyncAdditionalSlotInfoPayload;
import net.p3pp3rf1y.sophisticatedcore.network.SyncContainerClientDataPayload;
import net.p3pp3rf1y.sophisticatedcore.network.SyncContainerStacksPayload;
import net.p3pp3rf1y.sophisticatedcore.network.SyncDatapackSettingsTemplatePayload;
import net.p3pp3rf1y.sophisticatedcore.network.SyncEmptySlotIconsPayload;
import net.p3pp3rf1y.sophisticatedcore.network.SyncPlayerSettingsPayload;
import net.p3pp3rf1y.sophisticatedcore.network.SyncSlotChangeErrorPayload;
import net.p3pp3rf1y.sophisticatedcore.network.SyncSlotStackPayload;
import net.p3pp3rf1y.sophisticatedcore.network.SyncTemplateSettingsPayload;
import net.p3pp3rf1y.sophisticatedcore.network.TransferFullSlotPayload;
import net.p3pp3rf1y.sophisticatedcore.network.TransferItemsPayload;
import net.p3pp3rf1y.sophisticatedcore.upgrades.jukebox.PlayDiscPayload;
import net.p3pp3rf1y.sophisticatedcore.upgrades.jukebox.SoundFinishedNotificationPayload;
import net.p3pp3rf1y.sophisticatedcore.upgrades.jukebox.StopDiscPlaybackPayload;
import net.p3pp3rf1y.sophisticatedcore.upgrades.tank.TankClickPayload;

public class ModPayloads {
   private ModPayloads() {
   }

   public static void registerPayloads() {
      registerC2S(SyncContainerClientDataPayload.TYPE, SyncContainerClientDataPayload.STREAM_CODEC, SyncContainerClientDataPayload::handlePayload);
      registerC2S(TransferFullSlotPayload.TYPE, TransferFullSlotPayload.STREAM_CODEC, TransferFullSlotPayload::handlePayload);
      registerC2S(SoundFinishedNotificationPayload.TYPE, SoundFinishedNotificationPayload.STREAM_CODEC, SoundFinishedNotificationPayload::handlePayload);
      registerC2S(TankClickPayload.TYPE, TankClickPayload.STREAM_CODEC, TankClickPayload::handlePayload);
      registerC2S(TransferItemsPayload.TYPE, TransferItemsPayload.STREAM_CODEC, TransferItemsPayload::handlePayload);
      PayloadTypeRegistry.playS2C().register(SyncContainerStacksPayload.TYPE, SyncContainerStacksPayload.STREAM_CODEC);
      PayloadTypeRegistry.playS2C().register(SyncSlotStackPayload.TYPE, SyncSlotStackPayload.STREAM_CODEC);
      PayloadTypeRegistry.playS2C().register(SyncPlayerSettingsPayload.TYPE, SyncPlayerSettingsPayload.STREAM_CODEC);
      PayloadTypeRegistry.playS2C().register(PlayDiscPayload.TYPE, PlayDiscPayload.STREAM_CODEC);
      PayloadTypeRegistry.playS2C().register(StopDiscPlaybackPayload.TYPE, StopDiscPlaybackPayload.STREAM_CODEC);
      PayloadTypeRegistry.playS2C().register(SyncTemplateSettingsPayload.TYPE, SyncTemplateSettingsPayload.STREAM_CODEC);
      PayloadTypeRegistry.playS2C().register(SyncAdditionalSlotInfoPayload.TYPE, SyncAdditionalSlotInfoPayload.STREAM_CODEC);
      PayloadTypeRegistry.playS2C().register(SyncEmptySlotIconsPayload.TYPE, SyncEmptySlotIconsPayload.STREAM_CODEC);
      PayloadTypeRegistry.playS2C().register(SyncSlotChangeErrorPayload.TYPE, SyncSlotChangeErrorPayload.STREAM_CODEC);
      PayloadTypeRegistry.playS2C().register(SyncDatapackSettingsTemplatePayload.TYPE, SyncDatapackSettingsTemplatePayload.STREAM_CODEC);
      if (FabricLoader.getInstance().getEnvironmentType() == EnvType.CLIENT) {
         ClientPlayNetworking.registerGlobalReceiver(SyncContainerStacksPayload.TYPE, SyncContainerStacksPayload::handlePayload);
         ClientPlayNetworking.registerGlobalReceiver(SyncSlotStackPayload.TYPE, SyncSlotStackPayload::handlePayload);
         ClientPlayNetworking.registerGlobalReceiver(SyncPlayerSettingsPayload.TYPE, SyncPlayerSettingsPayload::handlePayload);
         ClientPlayNetworking.registerGlobalReceiver(PlayDiscPayload.TYPE, PlayDiscPayload::handlePayload);
         ClientPlayNetworking.registerGlobalReceiver(StopDiscPlaybackPayload.TYPE, StopDiscPlaybackPayload::handlePayload);
         ClientPlayNetworking.registerGlobalReceiver(SyncTemplateSettingsPayload.TYPE, SyncTemplateSettingsPayload::handlePayload);
         ClientPlayNetworking.registerGlobalReceiver(SyncAdditionalSlotInfoPayload.TYPE, SyncAdditionalSlotInfoPayload::handlePayload);
         ClientPlayNetworking.registerGlobalReceiver(SyncEmptySlotIconsPayload.TYPE, SyncEmptySlotIconsPayload::handlePayload);
         ClientPlayNetworking.registerGlobalReceiver(SyncSlotChangeErrorPayload.TYPE, SyncSlotChangeErrorPayload::handlePayload);
         ClientPlayNetworking.registerGlobalReceiver(SyncDatapackSettingsTemplatePayload.TYPE, SyncDatapackSettingsTemplatePayload::handlePayload);
      }

      LitematicaPayloads.registerPackets();
   }

   public static <T extends class_8710> void registerC2S(class_9154<T> id, class_9139<? super class_9129, T> codec, PlayPayloadHandler<T> handler) {
      PayloadTypeRegistry.playC2S().register(id, codec);
      ServerPlayNetworking.registerGlobalReceiver(id, handler);
   }
}
