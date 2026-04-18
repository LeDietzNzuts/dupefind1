package dev.architectury.networking.fabric;

import com.mojang.logging.LogUtils;
import dev.architectury.impl.NetworkAggregator;
import dev.architectury.networking.NetworkManager;
import dev.architectury.networking.SpawnEntityPacket;
import dev.architectury.utils.Env;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking.Context;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking.PlayPayloadHandler;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.class_1255;
import net.minecraft.class_1297;
import net.minecraft.class_1657;
import net.minecraft.class_2596;
import net.minecraft.class_2602;
import net.minecraft.class_2960;
import net.minecraft.class_3222;
import net.minecraft.class_3231;
import net.minecraft.class_5455;
import net.minecraft.class_8710;
import net.minecraft.class_9129;
import net.minecraft.class_9139;
import net.minecraft.class_8710.class_9154;
import org.slf4j.Logger;

public class NetworkManagerImpl {
   private static final Logger LOGGER = LogUtils.getLogger();

   public static NetworkAggregator.Adaptor getAdaptor() {
      return new NetworkAggregator.Adaptor() {
         @Override
         public <T extends class_8710> void registerC2S(class_9154<T> type, class_9139<? super class_9129, T> codec, NetworkManager.NetworkReceiver<T> receiver) {
            NetworkManagerImpl.LOGGER.info("Registering C2S receiver with id {}", type.comp_2242());
            PayloadTypeRegistry.playC2S().register(type, codec);
            ServerPlayNetworking.registerGlobalReceiver(type, (payload, fabricContext) -> {
               NetworkManager.PacketContext context = NetworkManagerImpl.context(fabricContext.player(), fabricContext.player().field_13995, false);
               receiver.receive((T)payload, context);
            });
         }

         @Environment(EnvType.CLIENT)
         @Override
         public <T extends class_8710> void registerS2C(class_9154<T> type, class_9139<? super class_9129, T> codec, NetworkManager.NetworkReceiver<T> receiver) {
            NetworkManagerImpl.LOGGER.info("Registering S2C receiver with id {}", type.comp_2242());
            PayloadTypeRegistry.playS2C().register(type, codec);
            ClientPlayNetworking.registerGlobalReceiver(type, new ClientPlayPayloadHandler<>(receiver));
         }

         @Override
         public <T extends class_8710> class_2596<?> toC2SPacket(T payload) {
            return ClientPlayNetworking.createC2SPacket(payload);
         }

         @Override
         public <T extends class_8710> class_2596<?> toS2CPacket(T payload) {
            return ServerPlayNetworking.createS2CPacket(payload);
         }

         @Override
         public <T extends class_8710> void registerS2CType(class_9154<T> type, class_9139<? super class_9129, T> codec) {
            PayloadTypeRegistry.playS2C().register(type, codec);
         }

         @Environment(EnvType.CLIENT)
         class ClientPlayPayloadHandler<T extends class_8710> implements PlayPayloadHandler<T> {
            private final NetworkManager.NetworkReceiver<T> receiver;

            ClientPlayPayloadHandler(NetworkManager.NetworkReceiver<T> receiver) {
               this.receiver = receiver;
            }

            public void receive(T payload, Context fabricContext) {
               NetworkManager.PacketContext context = NetworkManagerImpl.context(fabricContext.player(), fabricContext.client(), true);
               this.receiver.receive(payload, context);
            }
         }
      };
   }

   private static NetworkManager.PacketContext context(class_1657 player, class_1255<?> taskQueue, boolean client) {
      return new NetworkManager.PacketContext() {
         @Override
         public class_1657 getPlayer() {
            return player;
         }

         @Override
         public void queue(Runnable runnable) {
            taskQueue.execute(runnable);
         }

         @Override
         public Env getEnvironment() {
            return client ? Env.CLIENT : Env.SERVER;
         }

         @Override
         public class_5455 registryAccess() {
            return player.method_56673();
         }
      };
   }

   @Environment(EnvType.CLIENT)
   public static boolean canServerReceive(class_2960 id) {
      return ClientPlayNetworking.canSend(id);
   }

   public static boolean canPlayerReceive(class_3222 player, class_2960 id) {
      return ServerPlayNetworking.canSend(player, id);
   }

   public static class_2596<class_2602> createAddEntityPacket(class_1297 entity, class_3231 serverEntity) {
      return SpawnEntityPacket.create(entity, serverEntity);
   }
}
