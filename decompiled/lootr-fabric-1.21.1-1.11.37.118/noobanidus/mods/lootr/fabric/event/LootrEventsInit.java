package noobanidus.mods.lootr.fabric.event;

import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerChunkEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents.ServerStarting;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents.ServerStopped;
import net.fabricmc.fabric.api.event.player.PlayerBlockBreakEvents;
import net.fabricmc.fabric.api.resource.ResourceManagerHelper;
import net.fabricmc.fabric.api.resource.ResourcePackActivationType;
import net.fabricmc.fabric.impl.resource.loader.ResourceManagerHelperImpl;
import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.ModContainer;
import net.minecraft.class_2561;
import net.minecraft.class_2960;
import net.minecraft.server.MinecraftServer;
import noobanidus.mods.lootr.common.api.LootrAPI;
import noobanidus.mods.lootr.common.block.entity.BlockEntityTicker;
import noobanidus.mods.lootr.common.chunk.LoadedChunks;
import noobanidus.mods.lootr.common.command.CommandLootr;

public class LootrEventsInit {
   public static MinecraftServer serverInstance;

   public static void registerEvents() {
      ServerLifecycleEvents.SERVER_STARTING.register((ServerStarting)server -> {
         serverInstance = server;
         LoadedChunks.clear();
      });
      ServerLifecycleEvents.SERVER_STOPPED.register((ServerStopped)server -> {
         serverInstance = null;
         LoadedChunks.clear();
      });
      ServerTickEvents.END_SERVER_TICK.register(BlockEntityTicker::onServerTick);
      ServerChunkEvents.CHUNK_LOAD.register(LoadedChunks::onChunkLoad);
      ServerChunkEvents.CHUNK_UNLOAD.register(LoadedChunks::onChunkUnload);
      PlayerBlockBreakEvents.BEFORE.register(HandleBreak::beforeBlockBreak);
      PlayerBlockBreakEvents.CANCELED.register(HandleBreak::afterBlockBreak);
      CommandRegistrationCallback.EVENT.register((CommandRegistrationCallback)(dispatcher, reg, env) -> CommandLootr.register(dispatcher));
      ModContainer container = (ModContainer)FabricLoader.getInstance().getModContainer("lootr").orElseThrow();
      ResourceManagerHelper.registerBuiltinResourcePack(
         LootrAPI.rl("old_textures"), container, class_2561.method_43470("Lootr - Old Textures"), ResourcePackActivationType.NORMAL
      );
      registerPack(container, "lootr_no_advancements", class_2561.method_43470("Disable Lootr Advancements"));
      registerPack(container, "lootr_no_suspicious_blocks", class_2561.method_43470("Disable Lootr Converting Suspicious Blocks"));
   }

   private static void registerPack(ModContainer container, String name, class_2561 desc) {
      ResourceManagerHelperImpl.registerBuiltinResourcePack(
         class_2960.method_60655(container.getMetadata().getId(), name), "datapacks/" + name, container, desc, ResourcePackActivationType.NORMAL
      );
   }
}
