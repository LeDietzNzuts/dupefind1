package net.p3pp3rf1y.sophisticatedcore.common;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerWorldEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents.EndTick;
import net.fabricmc.fabric.api.event.player.UseBlockCallback;
import net.minecraft.class_1268;
import net.minecraft.class_1269;
import net.minecraft.class_1657;
import net.minecraft.class_1799;
import net.minecraft.class_1838;
import net.minecraft.class_1937;
import net.minecraft.class_3965;
import net.p3pp3rf1y.sophisticatedcore.init.ModFluids;
import net.p3pp3rf1y.sophisticatedcore.init.ModParticles;
import net.p3pp3rf1y.sophisticatedcore.init.ModPayloads;
import net.p3pp3rf1y.sophisticatedcore.init.ModRecipes;
import net.p3pp3rf1y.sophisticatedcore.inventory.ItemStackKey;
import net.p3pp3rf1y.sophisticatedcore.upgrades.jukebox.ServerStorageSoundHandler;
import net.p3pp3rf1y.sophisticatedcore.util.RecipeHelper;

public class CommonEventHandler {
   public void registerHandlers() {
      ModFluids.registerHandlers();
      ModParticles.registerParticles();
      ModRecipes.registerHandlers();
      ModPayloads.registerPayloads();
      ServerTickEvents.END_SERVER_TICK.register((EndTick)server -> ItemStackKey.clearCacheOnTickEnd());
      ServerLifecycleEvents.SYNC_DATA_PACK_CONTENTS.register(RecipeHelper::onDataPackSync);
      UseBlockCallback.EVENT.register(this::onUseBlock);
      ServerWorldEvents.UNLOAD.register(ServerStorageSoundHandler::onWorldUnload);
      ServerTickEvents.END_WORLD_TICK.register(ServerStorageSoundHandler::tick);
   }

   private class_1269 onUseBlock(class_1657 player, class_1937 level, class_1268 hand, class_3965 hitResult) {
      if (player.method_7325()) {
         return class_1269.field_5811;
      } else {
         class_1799 stack = player.method_5998(hand);
         if (stack.method_7960()) {
            return class_1269.field_5811;
         } else {
            class_1838 context = new class_1838(player, hand, hitResult);
            return stack.onItemUseFirst(context);
         }
      }
   }
}
