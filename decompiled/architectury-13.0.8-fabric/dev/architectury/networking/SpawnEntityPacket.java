package dev.architectury.networking;

import dev.architectury.extensions.network.EntitySpawnExtension;
import io.netty.buffer.ByteBufUtil;
import io.netty.buffer.Unpooled;
import java.util.UUID;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.class_1297;
import net.minecraft.class_1299;
import net.minecraft.class_2338;
import net.minecraft.class_2540;
import net.minecraft.class_2596;
import net.minecraft.class_2602;
import net.minecraft.class_2960;
import net.minecraft.class_310;
import net.minecraft.class_3231;
import net.minecraft.class_7924;
import net.minecraft.class_8710;
import net.minecraft.class_9129;
import net.minecraft.class_9135;
import net.minecraft.class_9139;
import net.minecraft.class_8710.class_9154;

public class SpawnEntityPacket {
   private static final class_2960 PACKET_ID = class_2960.method_60655("architectury", "spawn_entity_packet");
   private static final class_9154<SpawnEntityPacket.PacketPayload> PACKET_TYPE = new class_9154(PACKET_ID);
   private static final class_9139<class_9129, SpawnEntityPacket.PacketPayload> PACKET_CODEC = class_8710.method_56484(
      SpawnEntityPacket.PacketPayload::write, SpawnEntityPacket.PacketPayload::new
   );

   public static class_2596<class_2602> create(class_1297 entity, class_3231 serverEntity) {
      if (entity.method_37908().method_8608()) {
         throw new IllegalStateException("SpawnPacketUtil.create called on the logical client!");
      } else {
         return (class_2596<class_2602>)NetworkManager.toPacket(
            NetworkManager.s2c(), new SpawnEntityPacket.PacketPayload(entity, serverEntity), entity.method_56673()
         );
      }
   }

   public static void register() {
      NetworkManager.registerS2CPayloadType(PACKET_TYPE, PACKET_CODEC);
   }

   @Environment(EnvType.CLIENT)
   public static class Client {
      @Environment(EnvType.CLIENT)
      public static void register() {
         NetworkManager.registerReceiver(NetworkManager.s2c(), SpawnEntityPacket.PACKET_TYPE, SpawnEntityPacket.PACKET_CODEC, SpawnEntityPacket.Client::receive);
      }

      @Environment(EnvType.CLIENT)
      private static void receive(SpawnEntityPacket.PacketPayload payload, NetworkManager.PacketContext context) {
         context.queue(() -> {
            if (class_310.method_1551().field_1687 == null) {
               throw new IllegalStateException("Client world is null!");
            } else {
               class_1297 entity = payload.entityType().method_5883(class_310.method_1551().field_1687);
               if (entity == null) {
                  throw new IllegalStateException("Created entity is null!");
               } else {
                  entity.method_5826(payload.uuid());
                  entity.method_5838(payload.id());
                  entity.method_43391(payload.x(), payload.y(), payload.z());
                  entity.method_24203(payload.x(), payload.y(), payload.z());
                  entity.method_36457(payload.xRot());
                  entity.method_36456(payload.yRot());
                  entity.method_5847(payload.yHeadRot());
                  entity.method_5636(payload.yHeadRot());
                  if (entity instanceof EntitySpawnExtension ext) {
                     class_9129 buf = new class_9129(Unpooled.wrappedBuffer(payload.data()), context.registryAccess());
                     ext.loadAdditionalSpawnData(buf);
                     buf.release();
                  }

                  class_310.method_1551().field_1687.method_53875(entity);
                  entity.method_5750(payload.deltaX(), payload.deltaY(), payload.deltaZ());
               }
            }
         });
      }
   }

   private record PacketPayload(
      class_1299<?> entityType,
      UUID uuid,
      int id,
      double x,
      double y,
      double z,
      float xRot,
      float yRot,
      float yHeadRot,
      double deltaX,
      double deltaY,
      double deltaZ,
      byte[] data
   ) implements class_8710 {
      public PacketPayload(class_9129 buf) {
         this(
            (class_1299<?>)class_9135.method_56365(class_7924.field_41266).decode(buf),
            buf.method_10790(),
            buf.method_10816(),
            buf.readDouble(),
            buf.readDouble(),
            buf.readDouble(),
            buf.readFloat(),
            buf.readFloat(),
            buf.readFloat(),
            buf.readDouble(),
            buf.readDouble(),
            buf.readDouble(),
            buf.method_10795()
         );
      }

      public PacketPayload(class_1297 entity, class_3231 serverEntity) {
         this(
            entity.method_5864(),
            entity.method_5667(),
            entity.method_5628(),
            serverEntity.method_60942().method_10216(),
            serverEntity.method_60942().method_10214(),
            serverEntity.method_60942().method_10215(),
            serverEntity.method_60944(),
            serverEntity.method_60945(),
            serverEntity.method_60946(),
            serverEntity.method_60943().field_1352,
            serverEntity.method_60943().field_1351,
            serverEntity.method_60943().field_1350,
            saveExtra(entity)
         );
      }

      public PacketPayload(class_1297 entity, class_2338 pos) {
         this(
            entity.method_5864(),
            entity.method_5667(),
            entity.method_5628(),
            pos.method_10263(),
            pos.method_10264(),
            pos.method_10260(),
            entity.method_36455(),
            entity.method_36454(),
            entity.method_5791(),
            entity.method_18798().field_1352,
            entity.method_18798().field_1351,
            entity.method_18798().field_1350,
            saveExtra(entity)
         );
      }

      private static byte[] saveExtra(class_1297 entity) {
         class_2540 buf = new class_2540(Unpooled.buffer());

         byte[] var6;
         try {
            if (entity instanceof EntitySpawnExtension ext) {
               ext.saveAdditionalSpawnData(buf);
            }

            var6 = ByteBufUtil.getBytes(buf);
         } finally {
            buf.release();
         }

         return var6;
      }

      public void write(class_9129 buf) {
         class_9135.method_56365(class_7924.field_41266).encode(buf, this.entityType);
         buf.method_10797(this.uuid);
         buf.method_10804(this.id);
         buf.method_52940(this.x);
         buf.method_52940(this.y);
         buf.method_52940(this.z);
         buf.method_52941(this.xRot);
         buf.method_52941(this.yRot);
         buf.method_52941(this.yHeadRot);
         buf.method_52940(this.deltaX);
         buf.method_52940(this.deltaY);
         buf.method_52940(this.deltaZ);
         buf.method_10813(this.data);
      }

      public class_9154<? extends class_8710> method_56479() {
         return SpawnEntityPacket.PACKET_TYPE;
      }
   }
}
