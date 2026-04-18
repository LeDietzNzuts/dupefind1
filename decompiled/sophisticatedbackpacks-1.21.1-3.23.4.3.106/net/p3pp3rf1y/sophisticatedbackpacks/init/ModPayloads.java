package net.p3pp3rf1y.sophisticatedbackpacks.init;

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
import net.p3pp3rf1y.sophisticatedbackpacks.compat.litematica.LitematicaPayloads;
import net.p3pp3rf1y.sophisticatedbackpacks.network.AnotherPlayerBackpackOpenPayload;
import net.p3pp3rf1y.sophisticatedbackpacks.network.BackpackClosePayload;
import net.p3pp3rf1y.sophisticatedbackpacks.network.BackpackContentsPayload;
import net.p3pp3rf1y.sophisticatedbackpacks.network.BackpackOpenPayload;
import net.p3pp3rf1y.sophisticatedbackpacks.network.BlockPickPayload;
import net.p3pp3rf1y.sophisticatedbackpacks.network.BlockToolSwapPayload;
import net.p3pp3rf1y.sophisticatedbackpacks.network.EntityToolSwapPayload;
import net.p3pp3rf1y.sophisticatedbackpacks.network.InventoryInteractionPayload;
import net.p3pp3rf1y.sophisticatedbackpacks.network.RequestBackpackInventoryContentsPayload;
import net.p3pp3rf1y.sophisticatedbackpacks.network.RequestPlayerSettingsPayload;
import net.p3pp3rf1y.sophisticatedbackpacks.network.SyncClientInfoPayload;
import net.p3pp3rf1y.sophisticatedbackpacks.network.UpgradeTogglePayload;

public class ModPayloads {
   private ModPayloads() {
   }

   public static void registerPayloads() {
      registerC2S(BackpackOpenPayload.TYPE, BackpackOpenPayload.STREAM_CODEC, BackpackOpenPayload::handlePayload);
      registerC2S(UpgradeTogglePayload.TYPE, UpgradeTogglePayload.STREAM_CODEC, UpgradeTogglePayload::handlePayload);
      registerC2S(
         RequestBackpackInventoryContentsPayload.TYPE,
         RequestBackpackInventoryContentsPayload.STREAM_CODEC,
         RequestBackpackInventoryContentsPayload::handlePayload
      );
      registerC2S(InventoryInteractionPayload.TYPE, InventoryInteractionPayload.STREAM_CODEC, InventoryInteractionPayload::handlePayload);
      registerC2S(BlockToolSwapPayload.TYPE, BlockToolSwapPayload.STREAM_CODEC, BlockToolSwapPayload::handlePayload);
      registerC2S(EntityToolSwapPayload.TYPE, EntityToolSwapPayload.STREAM_CODEC, EntityToolSwapPayload::handlePayload);
      registerC2S(BackpackClosePayload.TYPE, BackpackClosePayload.STREAM_CODEC, BackpackClosePayload::handlePayload);
      registerC2S(AnotherPlayerBackpackOpenPayload.TYPE, AnotherPlayerBackpackOpenPayload.STREAM_CODEC, AnotherPlayerBackpackOpenPayload::handlePayload);
      registerC2S(BlockPickPayload.TYPE, BlockPickPayload.STREAM_CODEC, BlockPickPayload::handlePayload);
      registerC2S(RequestPlayerSettingsPayload.TYPE, RequestPlayerSettingsPayload.STREAM_CODEC, RequestPlayerSettingsPayload::handlePayload);
      PayloadTypeRegistry.playS2C().register(BackpackContentsPayload.TYPE, BackpackContentsPayload.STREAM_CODEC);
      PayloadTypeRegistry.playS2C().register(SyncClientInfoPayload.TYPE, SyncClientInfoPayload.STREAM_CODEC);
      if (FabricLoader.getInstance().getEnvironmentType() == EnvType.CLIENT) {
         ClientPlayNetworking.registerGlobalReceiver(BackpackContentsPayload.TYPE, BackpackContentsPayload::handlePayload);
         ClientPlayNetworking.registerGlobalReceiver(SyncClientInfoPayload.TYPE, SyncClientInfoPayload::handlePayload);
      }

      LitematicaPayloads.registerPackets();
   }

   public static <T extends class_8710> void registerC2S(class_9154<T> id, class_9139<? super class_9129, T> codec, PlayPayloadHandler<T> handler) {
      PayloadTypeRegistry.playC2S().register(id, codec);
      ServerPlayNetworking.registerGlobalReceiver(id, handler);
   }
}
