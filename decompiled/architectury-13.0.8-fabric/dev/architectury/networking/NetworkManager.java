package dev.architectury.networking;

import dev.architectury.impl.NetworkAggregator;
import dev.architectury.injectables.annotations.ExpectPlatform;
import dev.architectury.injectables.annotations.ExpectPlatform.Transformed;
import dev.architectury.networking.fabric.NetworkManagerImpl;
import dev.architectury.networking.transformers.PacketCollector;
import dev.architectury.networking.transformers.PacketSink;
import dev.architectury.networking.transformers.PacketTransformer;
import dev.architectury.networking.transformers.SinglePacketCollector;
import dev.architectury.utils.Env;
import dev.architectury.utils.GameInstance;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.class_1297;
import net.minecraft.class_1657;
import net.minecraft.class_2596;
import net.minecraft.class_2602;
import net.minecraft.class_2960;
import net.minecraft.class_3222;
import net.minecraft.class_3231;
import net.minecraft.class_5455;
import net.minecraft.class_634;
import net.minecraft.class_8710;
import net.minecraft.class_9129;
import net.minecraft.class_9139;
import net.minecraft.class_8710.class_9154;
import org.jetbrains.annotations.ApiStatus.Experimental;

public final class NetworkManager {
   @Deprecated(forRemoval = true)
   public static void registerS2CPayloadType(class_2960 id) {
      NetworkAggregator.registerS2CType(id, List.of());
   }

   public static <T extends class_8710> void registerS2CPayloadType(class_9154<T> type, class_9139<? super class_9129, T> codec) {
      NetworkAggregator.registerS2CType(type, codec, List.of());
   }

   @Deprecated(forRemoval = true)
   public static void registerS2CPayloadType(class_2960 id, List<PacketTransformer> packetTransformers) {
      NetworkAggregator.registerS2CType(id, packetTransformers);
   }

   public static <T extends class_8710> void registerS2CPayloadType(
      class_9154<T> type, class_9139<? super class_9129, T> codec, List<PacketTransformer> packetTransformers
   ) {
      NetworkAggregator.registerS2CType(type, codec, packetTransformers);
   }

   @Deprecated(forRemoval = true)
   public static void registerReceiver(NetworkManager.Side side, class_2960 id, NetworkManager.NetworkReceiver<class_9129> receiver) {
      registerReceiver(side, id, Collections.emptyList(), receiver);
   }

   @Deprecated(forRemoval = true)
   @Experimental
   public static void registerReceiver(
      NetworkManager.Side side, class_2960 id, List<PacketTransformer> packetTransformers, NetworkManager.NetworkReceiver<class_9129> receiver
   ) {
      NetworkAggregator.registerReceiver(side, id, packetTransformers, receiver);
   }

   public static <T extends class_8710> void registerReceiver(
      NetworkManager.Side side, class_9154<T> id, class_9139<? super class_9129, T> codec, NetworkManager.NetworkReceiver<T> receiver
   ) {
      registerReceiver(side, id, codec, Collections.emptyList(), receiver);
   }

   @Experimental
   public static <T extends class_8710> void registerReceiver(
      NetworkManager.Side side,
      class_9154<T> id,
      class_9139<? super class_9129, T> codec,
      List<PacketTransformer> packetTransformers,
      NetworkManager.NetworkReceiver<T> receiver
   ) {
      NetworkAggregator.registerReceiver(side, id, codec, packetTransformers, receiver);
   }

   @Deprecated(forRemoval = true)
   public static class_2596<?> toPacket(NetworkManager.Side side, class_2960 id, class_9129 buf) {
      SinglePacketCollector sink = new SinglePacketCollector(null);
      collectPackets(sink, side, id, buf);
      return sink.getPacket();
   }

   @Deprecated(forRemoval = true)
   public static List<class_2596<?>> toPackets(NetworkManager.Side side, class_2960 id, class_9129 buf) {
      PacketCollector sink = new PacketCollector(null);
      collectPackets(sink, side, id, buf);
      return sink.collect();
   }

   public static <T extends class_8710> class_2596<?> toPacket(NetworkManager.Side side, T payload, class_5455 access) {
      SinglePacketCollector sink = new SinglePacketCollector(null);
      collectPackets(sink, side, payload, access);
      return sink.getPacket();
   }

   public static <T extends class_8710> List<class_2596<?>> toPackets(NetworkManager.Side side, T payload, class_5455 access) {
      PacketCollector sink = new PacketCollector(null);
      collectPackets(sink, side, payload, access);
      return sink.collect();
   }

   @Deprecated(forRemoval = true)
   public static void collectPackets(PacketSink sink, NetworkManager.Side side, class_2960 id, class_9129 buf) {
      NetworkAggregator.collectPackets(sink, side, id, buf);
   }

   public static <T extends class_8710> void collectPackets(PacketSink sink, NetworkManager.Side side, T payload, class_5455 access) {
      NetworkAggregator.collectPackets(sink, side, payload, access);
   }

   @Deprecated(forRemoval = true)
   public static void sendToPlayer(class_3222 player, class_2960 id, class_9129 buf) {
      collectPackets(PacketSink.ofPlayer(player), serverToClient(), id, buf);
   }

   @Deprecated(forRemoval = true)
   public static void sendToPlayers(Iterable<class_3222> players, class_2960 id, class_9129 buf) {
      collectPackets(PacketSink.ofPlayers(players), serverToClient(), id, buf);
   }

   @Deprecated(forRemoval = true)
   @Environment(EnvType.CLIENT)
   public static void sendToServer(class_2960 id, class_9129 buf) {
      collectPackets(PacketSink.client(), clientToServer(), id, buf);
   }

   public static <T extends class_8710> void sendToPlayer(class_3222 player, T payload) {
      collectPackets(PacketSink.ofPlayer(player), serverToClient(), payload, player.method_56673());
   }

   public static <T extends class_8710> void sendToPlayers(Iterable<class_3222> players, T payload) {
      Iterator<class_3222> iterator = players.iterator();
      if (iterator.hasNext()) {
         collectPackets(PacketSink.ofPlayers(players), serverToClient(), payload, iterator.next().method_56673());
      }
   }

   @Environment(EnvType.CLIENT)
   public static <T extends class_8710> void sendToServer(T payload) {
      class_634 connection = GameInstance.getClient().method_1562();
      if (connection != null) {
         collectPackets(PacketSink.client(), clientToServer(), payload, connection.method_29091());
      }
   }

   @Environment(EnvType.CLIENT)
   @ExpectPlatform
   @Transformed
   public static boolean canServerReceive(class_2960 id) {
      return NetworkManagerImpl.canServerReceive(id);
   }

   @ExpectPlatform
   @Transformed
   public static boolean canPlayerReceive(class_3222 player, class_2960 id) {
      return NetworkManagerImpl.canPlayerReceive(player, id);
   }

   @Environment(EnvType.CLIENT)
   public static boolean canServerReceive(class_9154<?> type) {
      return canServerReceive(type.comp_2242());
   }

   public static boolean canPlayerReceive(class_3222 player, class_9154<?> type) {
      return canPlayerReceive(player, type.comp_2242());
   }

   @ExpectPlatform
   @Transformed
   public static class_2596<class_2602> createAddEntityPacket(class_1297 entity, class_3231 serverEntity) {
      return NetworkManagerImpl.createAddEntityPacket(entity, serverEntity);
   }

   @ExpectPlatform
   @Transformed
   private static NetworkAggregator.Adaptor getAdaptor() {
      return NetworkManagerImpl.getAdaptor();
   }

   public static NetworkManager.Side s2c() {
      return NetworkManager.Side.S2C;
   }

   public static NetworkManager.Side c2s() {
      return NetworkManager.Side.C2S;
   }

   public static NetworkManager.Side serverToClient() {
      return NetworkManager.Side.S2C;
   }

   public static NetworkManager.Side clientToServer() {
      return NetworkManager.Side.C2S;
   }

   @FunctionalInterface
   public interface NetworkReceiver<T> {
      void receive(T var1, NetworkManager.PacketContext var2);
   }

   public interface PacketContext {
      class_1657 getPlayer();

      void queue(Runnable var1);

      Env getEnvironment();

      class_5455 registryAccess();

      default EnvType getEnv() {
         return this.getEnvironment().toPlatform();
      }
   }

   public static enum Side {
      S2C,
      C2S;
   }
}
