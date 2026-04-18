package dev.architectury.networking.transformers;

import dev.architectury.event.events.client.ClientPlayerEvent;
import dev.architectury.event.events.common.PlayerEvent;
import dev.architectury.networking.NetworkManager;
import dev.architectury.utils.Env;
import dev.architectury.utils.EnvExecutor;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.class_2960;
import net.minecraft.class_9129;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.ApiStatus.Experimental;

@Experimental
public class SplitPacketTransformer implements PacketTransformer {
   private static final Logger LOGGER = LogManager.getLogger(SplitPacketTransformer.class);
   private static final byte START = 0;
   private static final byte PART = 1;
   private static final byte END = 2;
   private static final byte ONLY = 3;
   private final Map<SplitPacketTransformer.PartKey, SplitPacketTransformer.PartData> cache = Collections.synchronizedMap(new HashMap<>());

   public SplitPacketTransformer() {
      PlayerEvent.PLAYER_QUIT.register(player -> this.cache.keySet().removeIf(key -> Objects.equals(key.playerUUID, player.method_5667())));
      EnvExecutor.runInEnv(Env.CLIENT, () -> new SplitPacketTransformer.Client()::init);
   }

   @Override
   public void inbound(NetworkManager.Side side, class_2960 id, class_9129 buf, NetworkManager.PacketContext context, PacketTransformer.TransformationSink sink) {
      SplitPacketTransformer.PartKey key = side == NetworkManager.Side.S2C
         ? new SplitPacketTransformer.PartKey(side, null)
         : new SplitPacketTransformer.PartKey(side, context.getPlayer().method_5667());
      switch (buf.readByte()) {
         case 0:
            SplitPacketTransformer.PartData data = new SplitPacketTransformer.PartData(id, buf.readInt());
            if (this.cache.put(key, data) != null) {
               LOGGER.warn("Received invalid START packet for SplitPacketTransformer with packet id " + id + " for side " + side);
            }

            buf.method_52938();
            data.parts.add(buf);
            break;
         case 1:
            SplitPacketTransformer.PartData datax;
            if ((datax = this.cache.get(key)) == null) {
               LOGGER.warn("Received invalid PART packet for SplitPacketTransformer with packet id " + id + " for side " + side);
               buf.release();
            } else if (!datax.id.equals(id)) {
               LOGGER.warn(
                  "Received invalid PART packet for SplitPacketTransformer with packet id " + id + " for side " + side + ", id in cache is " + datax.id
               );
               buf.release();

               for (class_9129 partxx : datax.parts) {
                  if (partxx != buf) {
                     partxx.release();
                  }
               }

               this.cache.remove(key);
            } else {
               buf.method_52938();
               datax.parts.add(buf);
            }
            break;
         case 2:
            SplitPacketTransformer.PartData data;
            if ((data = this.cache.get(key)) == null) {
               LOGGER.warn("Received invalid END packet for SplitPacketTransformer with packet id " + id + " for side " + side);
               buf.release();
            } else if (data.id.equals(id)) {
               buf.method_52938();
               data.parts.add(buf);
            } else {
               LOGGER.warn("Received invalid END packet for SplitPacketTransformer with packet id " + id + " for side " + side + ", id in cache is " + data.id);
               buf.release();

               for (class_9129 part : data.parts) {
                  if (part != buf) {
                     part.release();
                  }
               }

               this.cache.remove(key);
            }

            if (data.parts.size() != data.partsExpected) {
               LOGGER.warn(
                  "Received invalid END packet for SplitPacketTransformer with packet id "
                     + id
                     + " for side "
                     + side
                     + " with size "
                     + data.parts
                     + ", parts expected is "
                     + data.partsExpected
               );

               for (class_9129 partx : data.parts) {
                  if (partx != buf) {
                     partx.release();
                  }
               }
            } else {
               class_9129 byteBuf = new class_9129(Unpooled.wrappedBuffer(data.parts.toArray(new ByteBuf[0])), buf.method_56349());
               sink.accept(side, data.id, byteBuf);
               byteBuf.release();
            }

            this.cache.remove(key);
            break;
         case 3:
            sink.accept(side, id, buf);
            break;
         default:
            throw new IllegalStateException("Illegal split packet header!");
      }
   }

   @Override
   public void outbound(NetworkManager.Side side, class_2960 id, class_9129 buf, PacketTransformer.TransformationSink sink) {
      int maxSize = (side == NetworkManager.Side.C2S ? 32767 : 1048576) - 1 - 20 - id.toString().getBytes(StandardCharsets.UTF_8).length;
      if (buf.readableBytes() <= maxSize) {
         ByteBuf stateBuf = Unpooled.buffer(1);
         stateBuf.writeByte(3);
         class_9129 packetBuffer = new class_9129(Unpooled.wrappedBuffer(new ByteBuf[]{stateBuf, buf}), buf.method_56349());
         sink.accept(side, id, packetBuffer);
      } else {
         int partSize = maxSize - 4;
         int parts = (int)Math.ceil((float)buf.readableBytes() / partSize);

         for (int i = 0; i < parts; i++) {
            class_9129 packetBuffer = new class_9129(Unpooled.buffer(), buf.method_56349());
            if (i == 0) {
               packetBuffer.method_52997(0);
               packetBuffer.method_53002(parts);
            } else if (i == parts - 1) {
               packetBuffer.method_52997(2);
            } else {
               packetBuffer.method_52997(1);
            }

            int next = Math.min(buf.readableBytes(), partSize);
            packetBuffer.method_52975(buf.retainedSlice(buf.readerIndex(), next));
            buf.method_52994(next);
            sink.accept(side, id, packetBuffer);
         }

         buf.release();
      }
   }

   private class Client {
      @Environment(EnvType.CLIENT)
      private void init() {
         ClientPlayerEvent.CLIENT_PLAYER_QUIT
            .register(player -> SplitPacketTransformer.this.cache.keySet().removeIf(key -> key.side == NetworkManager.Side.S2C));
      }
   }

   private static class PartData {
      private final class_2960 id;
      private final int partsExpected;
      private final List<class_9129> parts;

      public PartData(class_2960 id, int partsExpected) {
         this.id = id;
         this.partsExpected = partsExpected;
         this.parts = new ArrayList<>();
      }
   }

   private static class PartKey {
      private final NetworkManager.Side side;
      @Nullable
      private final UUID playerUUID;

      public PartKey(NetworkManager.Side side, @Nullable UUID playerUUID) {
         this.side = side;
         this.playerUUID = playerUUID;
      }

      @Override
      public boolean equals(Object o) {
         if (this == o) {
            return true;
         } else {
            return !(o instanceof SplitPacketTransformer.PartKey key) ? false : this.side == key.side && Objects.equals(this.playerUUID, key.playerUUID);
         }
      }

      @Override
      public int hashCode() {
         return Objects.hash(this.side, this.playerUUID);
      }

      @Override
      public String toString() {
         return "PartKey{side=" + this.side + ", playerUUID=" + this.playerUUID + "}";
      }
   }
}
