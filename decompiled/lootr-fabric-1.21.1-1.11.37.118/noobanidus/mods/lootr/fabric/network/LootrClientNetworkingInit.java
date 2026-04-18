package noobanidus.mods.lootr.fabric.network;

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.class_1297;
import net.minecraft.class_2338;
import net.minecraft.class_2586;
import noobanidus.mods.lootr.common.api.LootrAPI;
import noobanidus.mods.lootr.common.api.data.blockentity.ILootrBlockEntity;
import noobanidus.mods.lootr.common.api.data.entity.ILootrEntity;
import noobanidus.mods.lootr.common.client.ClientHooks;
import noobanidus.mods.lootr.fabric.network.to_client.PacketCloseCart;
import noobanidus.mods.lootr.fabric.network.to_client.PacketCloseContainer;
import noobanidus.mods.lootr.fabric.network.to_client.PacketOpenCart;
import noobanidus.mods.lootr.fabric.network.to_client.PacketOpenContainer;
import noobanidus.mods.lootr.fabric.network.to_client.PacketPerformBreakEffect;
import noobanidus.mods.lootr.fabric.network.to_client.PacketRefreshSection;

public class LootrClientNetworkingInit {
   public static void register() {
      ClientPlayNetworking.registerGlobalReceiver(PacketCloseCart.TYPE, (payload, context) -> {
         int entityId = payload.entityId();
         context.client().execute(() -> {
            if (context.client().field_1724 != null && context.client().field_1724.method_37908() != null) {
               class_1297 potential = context.client().field_1724.method_37908().method_8469(entityId);
               ILootrEntity patt0$temp = LootrAPI.resolveEntity(potential);
               if (patt0$temp instanceof ILootrEntity) {
                  patt0$temp.setClientOpened(false);
               }
            }
         });
      });
      ClientPlayNetworking.registerGlobalReceiver(PacketOpenCart.TYPE, (payload, context) -> {
         int entityId = payload.entityId();
         context.client().execute(() -> {
            if (context.client().field_1724 != null && context.client().field_1724.method_37908() != null) {
               class_1297 potential = context.client().field_1724.method_37908().method_8469(entityId);
               ILootrEntity patt0$temp = LootrAPI.resolveEntity(potential);
               if (patt0$temp instanceof ILootrEntity) {
                  patt0$temp.setClientOpened(true);
               }
            }
         });
      });
      ClientPlayNetworking.registerGlobalReceiver(PacketOpenContainer.TYPE, (payload, context) -> {
         class_2338 position = payload.blockPos();
         context.client().execute(() -> {
            if (context.client().field_1724 != null && context.client().field_1724.method_37908() != null) {
               class_2586 potential = context.client().field_1724.method_37908().method_8321(position);
               ILootrBlockEntity patt0$temp = LootrAPI.resolveBlockEntity(potential);
               if (patt0$temp instanceof ILootrBlockEntity) {
                  patt0$temp.setClientOpened(true);
                  ClientHooks.clearCache(position);
               }
            }
         });
      });
      ClientPlayNetworking.registerGlobalReceiver(PacketRefreshSection.TYPE, (payload, context) -> context.client().execute(() -> {
         if (context.client().field_1724 != null && context.client().field_1724.method_37908() != null) {
            class_2338 position = context.client().field_1724.method_24515();
            ClientHooks.clearCache(position);
         }
      }));
      ClientPlayNetworking.registerGlobalReceiver(PacketCloseContainer.TYPE, (payload, context) -> {
         class_2338 position = payload.blockPos();
         context.client().execute(() -> {
            if (context.client().field_1724 != null && context.client().field_1724.method_37908() != null) {
               class_2586 potential = context.client().field_1724.method_37908().method_8321(position);
               ILootrBlockEntity patt0$temp = LootrAPI.resolveBlockEntity(potential);
               if (patt0$temp instanceof ILootrBlockEntity) {
                  patt0$temp.setClientOpened(false);
                  ClientHooks.clearCache(position);
               }
            }
         });
      });
      ClientPlayNetworking.registerGlobalReceiver(PacketPerformBreakEffect.TYPE, (payload, context) -> {
         int entityId = payload.entityId();
         class_2338 pos = payload.pos();
         context.client().execute(() -> ClientHooks.performBreakEffect(entityId, pos));
      });
   }
}
