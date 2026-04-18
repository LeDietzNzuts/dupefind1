package dev.architectury.impl;

import com.google.common.base.Suppliers;
import dev.architectury.networking.NetworkManager;
import dev.architectury.networking.transformers.PacketSink;
import dev.architectury.networking.transformers.PacketTransformer;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;
import io.netty.buffer.Unpooled;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Supplier;
import net.minecraft.class_2596;
import net.minecraft.class_2960;
import net.minecraft.class_5455;
import net.minecraft.class_8710;
import net.minecraft.class_9129;
import net.minecraft.class_9135;
import net.minecraft.class_9139;
import net.minecraft.class_8710.class_9154;
import org.jetbrains.annotations.ApiStatus.Internal;

@Internal
public class NetworkAggregator {
   public static final Supplier<NetworkAggregator.Adaptor> ADAPTOR = Suppliers.memoize(() -> {
      try {
         Method adaptor = NetworkManager.class.getDeclaredMethod("getAdaptor");
         adaptor.setAccessible(true);
         return (NetworkAggregator.Adaptor)adaptor.invoke(null);
      } catch (Throwable var1) {
         throw new RuntimeException(var1);
      }
   });
   public static final Map<class_2960, class_9154<NetworkAggregator.BufCustomPacketPayload>> C2S_TYPE = new HashMap<>();
   public static final Map<class_2960, class_9154<NetworkAggregator.BufCustomPacketPayload>> S2C_TYPE = new HashMap<>();
   public static final Map<class_2960, NetworkManager.NetworkReceiver<?>> C2S_RECEIVER = new HashMap<>();
   public static final Map<class_2960, NetworkManager.NetworkReceiver<?>> S2C_RECEIVER = new HashMap<>();
   public static final Map<class_2960, class_9139<ByteBuf, ?>> C2S_CODECS = new HashMap<>();
   public static final Map<class_2960, class_9139<ByteBuf, ?>> S2C_CODECS = new HashMap<>();
   public static final Map<class_2960, PacketTransformer> C2S_TRANSFORMERS = new HashMap<>();
   public static final Map<class_2960, PacketTransformer> S2C_TRANSFORMERS = new HashMap<>();

   public static void registerReceiver(
      NetworkManager.Side side, class_2960 id, List<PacketTransformer> packetTransformers, NetworkManager.NetworkReceiver<class_9129> receiver
   ) {
      class_9154<NetworkAggregator.BufCustomPacketPayload> type = new class_9154(id);
      if (side == NetworkManager.Side.C2S) {
         C2S_TYPE.put(id, type);
         registerC2SReceiver(type, NetworkAggregator.BufCustomPacketPayload.streamCodec(type), packetTransformers, (value, context) -> {
            class_9129 buf = new class_9129(Unpooled.wrappedBuffer(value.payload()), context.registryAccess());
            receiver.receive(buf, context);
            buf.release();
         });
      } else if (side == NetworkManager.Side.S2C) {
         S2C_TYPE.put(id, type);
         registerS2CReceiver(type, NetworkAggregator.BufCustomPacketPayload.streamCodec(type), packetTransformers, (value, context) -> {
            class_9129 buf = new class_9129(Unpooled.wrappedBuffer(value.payload()), context.registryAccess());
            receiver.receive(buf, context);
            buf.release();
         });
      }
   }

   public static <T extends class_8710> void registerReceiver(
      NetworkManager.Side side,
      class_9154<T> type,
      class_9139<? super class_9129, T> codec,
      List<PacketTransformer> packetTransformers,
      NetworkManager.NetworkReceiver<T> receiver
   ) {
      Objects.requireNonNull(type, "Cannot register receiver with a null type!");
      packetTransformers = Objects.requireNonNullElse(packetTransformers, List.of());
      Objects.requireNonNull(receiver, "Cannot register a null receiver!");
      if (side == NetworkManager.Side.C2S) {
         registerC2SReceiver(type, codec, packetTransformers, receiver);
      } else if (side == NetworkManager.Side.S2C) {
         registerS2CReceiver(type, codec, packetTransformers, receiver);
      }
   }

   private static <T extends class_8710> void registerC2SReceiver(
      class_9154<T> type, class_9139<? super class_9129, T> codec, List<PacketTransformer> packetTransformers, NetworkManager.NetworkReceiver<T> receiver
   ) {
      PacketTransformer transformer = PacketTransformer.concat(packetTransformers);
      C2S_RECEIVER.put(type.comp_2242(), receiver);
      C2S_CODECS.put(type.comp_2242(), codec);
      C2S_TRANSFORMERS.put(type.comp_2242(), transformer);
      ADAPTOR.get()
         .registerC2S(
            type,
            NetworkAggregator.BufCustomPacketPayload.streamCodec(type),
            (payload, context) -> {
               class_9129 buf = new class_9129(Unpooled.wrappedBuffer(payload.payload()), context.registryAccess());
               transformer.inbound(
                  NetworkManager.Side.C2S,
                  type.comp_2242(),
                  buf,
                  context,
                  (side, id1, buf1) -> {
                     NetworkManager.NetworkReceiver<T> networkReceiver = side == NetworkManager.Side.C2S
                        ? (NetworkManager.NetworkReceiver)C2S_RECEIVER.get(id1)
                        : (NetworkManager.NetworkReceiver)S2C_RECEIVER.get(id1);
                     if (networkReceiver == null) {
                        throw new IllegalArgumentException("Network Receiver not found! " + id1);
                     } else {
                        T actualPayload = (T)codec.decode(buf1);
                        networkReceiver.receive(actualPayload, context);
                     }
                  }
               );
               buf.release();
            }
         );
   }

   private static <T extends class_8710> void registerS2CReceiver(
      class_9154<T> type, class_9139<? super class_9129, T> codec, List<PacketTransformer> packetTransformers, NetworkManager.NetworkReceiver<T> receiver
   ) {
      PacketTransformer transformer = PacketTransformer.concat(packetTransformers);
      S2C_RECEIVER.put(type.comp_2242(), receiver);
      S2C_CODECS.put(type.comp_2242(), codec);
      S2C_TRANSFORMERS.put(type.comp_2242(), transformer);
      ADAPTOR.get()
         .registerS2C(
            type,
            NetworkAggregator.BufCustomPacketPayload.streamCodec(type),
            (payload, context) -> {
               class_9129 buf = new class_9129(Unpooled.wrappedBuffer(payload.payload()), context.registryAccess());
               transformer.inbound(
                  NetworkManager.Side.S2C,
                  type.comp_2242(),
                  buf,
                  context,
                  (side, id1, buf1) -> {
                     NetworkManager.NetworkReceiver<T> networkReceiver = side == NetworkManager.Side.C2S
                        ? (NetworkManager.NetworkReceiver)C2S_RECEIVER.get(id1)
                        : (NetworkManager.NetworkReceiver)S2C_RECEIVER.get(id1);
                     if (networkReceiver == null) {
                        throw new IllegalArgumentException("Network Receiver not found! " + id1);
                     } else {
                        T actualPayload = (T)codec.decode(buf1);
                        networkReceiver.receive(actualPayload, context);
                     }
                  }
               );
               buf.release();
            }
         );
   }

   public static void collectPackets(PacketSink sink, NetworkManager.Side side, class_2960 id, class_9129 buf) {
      if (side == NetworkManager.Side.C2S) {
         collectPackets(sink, side, new NetworkAggregator.BufCustomPacketPayload(C2S_TYPE.get(id), ByteBufUtil.getBytes(buf)), buf.method_56349());
      } else {
         collectPackets(sink, side, new NetworkAggregator.BufCustomPacketPayload(S2C_TYPE.get(id), ByteBufUtil.getBytes(buf)), buf.method_56349());
      }
   }

   public static <T extends class_8710> void collectPackets(PacketSink sink, NetworkManager.Side side, T payload, class_5455 access) {
      class_9154<T> type = payload.method_56479();
      PacketTransformer transformer = side == NetworkManager.Side.C2S ? C2S_TRANSFORMERS.get(type.comp_2242()) : S2C_TRANSFORMERS.get(type.comp_2242());
      class_9139<ByteBuf, T> codec = side == NetworkManager.Side.C2S
         ? (class_9139)C2S_CODECS.get(type.comp_2242())
         : (class_9139)S2C_CODECS.get(type.comp_2242());
      class_9129 buf = new class_9129(Unpooled.buffer(), access);
      codec.encode(buf, payload);
      if (transformer != null) {
         transformer.outbound(side, type.comp_2242(), buf, (side1, id1, buf1) -> {
            if (side == NetworkManager.Side.C2S) {
               class_9154<NetworkAggregator.BufCustomPacketPayload> type1 = C2S_TYPE.getOrDefault(id1, type);
               sink.accept(toPacket(side1, new NetworkAggregator.BufCustomPacketPayload(type1, ByteBufUtil.getBytes(buf1))));
            } else if (side == NetworkManager.Side.S2C) {
               class_9154<NetworkAggregator.BufCustomPacketPayload> type1 = S2C_TYPE.getOrDefault(id1, type);
               sink.accept(toPacket(side1, new NetworkAggregator.BufCustomPacketPayload(type1, ByteBufUtil.getBytes(buf1))));
            }
         });
      } else {
         sink.accept(toPacket(side, new NetworkAggregator.BufCustomPacketPayload(type, ByteBufUtil.getBytes(buf))));
      }

      buf.release();
   }

   public static <T extends class_8710> class_2596<?> toPacket(NetworkManager.Side side, T payload) {
      if (side == NetworkManager.Side.C2S) {
         return ADAPTOR.get().toC2SPacket(payload);
      } else if (side == NetworkManager.Side.S2C) {
         return ADAPTOR.get().toS2CPacket(payload);
      } else {
         throw new IllegalArgumentException("Invalid side: " + side);
      }
   }

   public static void registerS2CType(class_2960 id, List<PacketTransformer> packetTransformers) {
      class_9154<NetworkAggregator.BufCustomPacketPayload> type = new class_9154(id);
      S2C_TYPE.put(id, type);
      registerS2CType(type, NetworkAggregator.BufCustomPacketPayload.streamCodec(type), packetTransformers);
   }

   public static <T extends class_8710> void registerS2CType(
      class_9154<T> type, class_9139<? super class_9129, T> codec, List<PacketTransformer> packetTransformers
   ) {
      Objects.requireNonNull(type, "Cannot register a null type!");
      packetTransformers = Objects.requireNonNullElse(packetTransformers, List.of());
      S2C_CODECS.put(type.comp_2242(), codec);
      S2C_TRANSFORMERS.put(type.comp_2242(), PacketTransformer.concat(packetTransformers));
      ADAPTOR.get().registerS2CType(type, (class_9139<? super class_9129, T>)NetworkAggregator.BufCustomPacketPayload.streamCodec(type));
   }

   public interface Adaptor {
      <T extends class_8710> void registerC2S(class_9154<T> var1, class_9139<? super class_9129, T> var2, NetworkManager.NetworkReceiver<T> var3);

      <T extends class_8710> void registerS2C(class_9154<T> var1, class_9139<? super class_9129, T> var2, NetworkManager.NetworkReceiver<T> var3);

      <T extends class_8710> class_2596<?> toC2SPacket(T var1);

      <T extends class_8710> class_2596<?> toS2CPacket(T var1);

      <T extends class_8710> void registerS2CType(class_9154<T> var1, class_9139<? super class_9129, T> var2);
   }

   public record BufCustomPacketPayload(class_9154<NetworkAggregator.BufCustomPacketPayload> _type, byte[] payload) implements class_8710 {
      public class_9154<? extends class_8710> method_56479() {
         return this._type();
      }

      public static class_9139<ByteBuf, NetworkAggregator.BufCustomPacketPayload> streamCodec(class_9154<NetworkAggregator.BufCustomPacketPayload> type) {
         return class_9135.field_48987
            .method_56432(bytes -> new NetworkAggregator.BufCustomPacketPayload(type, bytes), NetworkAggregator.BufCustomPacketPayload::payload);
      }
   }
}
