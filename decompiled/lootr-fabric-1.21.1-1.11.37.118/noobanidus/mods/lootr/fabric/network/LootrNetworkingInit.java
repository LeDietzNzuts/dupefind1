package noobanidus.mods.lootr.fabric.network;

import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.class_2586;
import net.minecraft.class_2596;
import net.minecraft.class_3218;
import net.minecraft.class_4208;
import noobanidus.mods.lootr.common.api.LootrAPI;
import noobanidus.mods.lootr.common.api.data.blockentity.ILootrBlockEntity;
import noobanidus.mods.lootr.fabric.network.to_client.PacketCloseContainer;
import noobanidus.mods.lootr.fabric.network.to_client.PacketOpenContainer;
import noobanidus.mods.lootr.fabric.network.to_server.PacketRequestUpdate;

public class LootrNetworkingInit {
   public static void register() {
      ServerPlayNetworking.registerGlobalReceiver(PacketRequestUpdate.TYPE, (payload, context) -> context.server().execute(() -> {
         class_4208 pos = payload.position();
         class_3218 level = context.server().method_3847(pos.comp_2207());
         if (level != null) {
            class_2586 blockEntity = level.method_8321(pos.comp_2208());
            if (blockEntity != null) {
               ILootrBlockEntity resolved = LootrAPI.resolveBlockEntity(blockEntity);
               if (resolved != null) {
                  class_2596<?> packet = blockEntity.method_38235();
                  if (packet != null) {
                     context.player().field_13987.method_14364(packet);
                  }

                  if (resolved.hasVisualOpened(context.player())) {
                     ServerPlayNetworking.send(context.player(), new PacketOpenContainer(pos.comp_2208()));
                  } else {
                     ServerPlayNetworking.send(context.player(), new PacketCloseContainer(pos.comp_2208()));
                  }
               }
            }
         }
      }));
   }
}
