package dev.architectury.utils.fabric;

import dev.architectury.event.EventHandler;
import dev.architectury.event.events.common.LifecycleEvent;
import dev.architectury.platform.Platform;
import dev.architectury.utils.Env;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.class_310;
import net.minecraft.server.MinecraftServer;

public class GameInstanceImpl {
   private static MinecraftServer server = null;

   public static MinecraftServer getServer() {
      MinecraftServer server = null;
      if (GameInstanceImpl.server != null) {
         server = GameInstanceImpl.server;
      }

      if (Platform.getEnvironment() == Env.CLIENT) {
         server = getServerFromClient();
      }

      return server;
   }

   public static void init() {
      EventHandler.init();
      LifecycleEvent.SERVER_BEFORE_START.register(server -> GameInstanceImpl.server = server);
      LifecycleEvent.SERVER_STOPPED.register(server -> GameInstanceImpl.server = null);
   }

   @Environment(EnvType.CLIENT)
   private static MinecraftServer getServerFromClient() {
      return class_310.method_1551().method_1576();
   }
}
