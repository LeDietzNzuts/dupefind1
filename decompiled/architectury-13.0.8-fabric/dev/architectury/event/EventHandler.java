package dev.architectury.event;

import dev.architectury.event.fabric.EventHandlerImpl;
import dev.architectury.injectables.annotations.ExpectPlatform;
import dev.architectury.injectables.annotations.ExpectPlatform.Transformed;
import dev.architectury.platform.Platform;
import dev.architectury.utils.Env;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

public final class EventHandler {
   private static boolean initialized = false;

   private EventHandler() {
   }

   public static void init() {
      if (!initialized) {
         initialized = true;
         if (Platform.getEnvironment() == Env.CLIENT) {
            registerClient();
         }

         registerCommon();
         if (Platform.getEnvironment() == Env.SERVER) {
            registerServer();
         }
      }
   }

   @ExpectPlatform
   @Environment(EnvType.CLIENT)
   @Transformed
   private static void registerClient() {
      EventHandlerImpl.registerClient();
   }

   @ExpectPlatform
   @Transformed
   private static void registerCommon() {
      EventHandlerImpl.registerCommon();
   }

   @ExpectPlatform
   @Environment(EnvType.SERVER)
   @Transformed
   private static void registerServer() {
      EventHandlerImpl.registerServer();
   }
}
