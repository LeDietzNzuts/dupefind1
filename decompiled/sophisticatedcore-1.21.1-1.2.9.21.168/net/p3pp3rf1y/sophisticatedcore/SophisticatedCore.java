package net.p3pp3rf1y.sophisticatedcore;

import fuzs.forgeconfigapiport.fabric.api.neoforge.v4.NeoForgeConfigRegistry;
import io.github.fabricators_of_create.porting_lib.models.geometry.RegisterGeometryLoadersCallback;
import javax.annotation.Nullable;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents.ServerStarting;
import net.fabricmc.fabric.api.resource.ResourceManagerHelper;
import net.minecraft.class_1937;
import net.minecraft.class_2960;
import net.minecraft.class_3218;
import net.minecraft.class_3264;
import net.minecraft.server.MinecraftServer;
import net.neoforged.fml.config.ModConfig.Type;
import net.p3pp3rf1y.sophisticatedcore.common.CommonEventHandler;
import net.p3pp3rf1y.sophisticatedcore.compat.CompatRegistry;
import net.p3pp3rf1y.sophisticatedcore.init.ModCompat;
import net.p3pp3rf1y.sophisticatedcore.init.ModCoreDataComponents;
import net.p3pp3rf1y.sophisticatedcore.inventory.StorageWrapperRepository;
import net.p3pp3rf1y.sophisticatedcore.settings.DatapackSettingsTemplateManager;
import net.p3pp3rf1y.sophisticatedcore.util.RecipeHelper;
import net.p3pp3rf1y.sophisticatedcore.util.model.DynamicFluidContainerModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SophisticatedCore implements ModInitializer {
   public static final String MOD_ID = "sophisticatedcore";
   public static final Logger LOGGER = LoggerFactory.getLogger("sophisticatedcore");
   public final CommonEventHandler commonEventHandler = new CommonEventHandler();
   private static MinecraftServer currentServer = null;

   @Nullable
   public static MinecraftServer getCurrentServer() {
      return currentServer;
   }

   public static boolean isLogicalServerThread() {
      Thread currentThread = Thread.currentThread();
      if (currentServer != null && currentServer.method_3777() == currentThread) {
         return true;
      } else {
         String name = currentThread.getName();
         return name.startsWith("Netty Server IO") || name.startsWith("Netty Epoll Server IO");
      }
   }

   public void onInitialize() {
      NeoForgeConfigRegistry.INSTANCE.register("sophisticatedcore", Type.CLIENT, Config.CLIENT_SPEC);
      NeoForgeConfigRegistry.INSTANCE.register("sophisticatedcore", Type.COMMON, Config.COMMON_SPEC);
      this.commonEventHandler.registerHandlers();
      ModCompat.register();
      CompatRegistry.getRegistry("sophisticatedcore").initCompats();
      Config.COMMON.initListeners();
      ModCoreDataComponents.register();
      ServerLifecycleEvents.SERVER_STARTING.register((ServerStarting)server -> currentServer = server);
      ServerLifecycleEvents.SERVER_STARTED.register(SophisticatedCore::serverStarted);
      ServerLifecycleEvents.SERVER_STOPPED.register(SophisticatedCore::serverStopped);
      ResourceManagerHelper.get(class_3264.field_14190).registerReloadListener(DatapackSettingsTemplateManager.Loader.INSTANCE);
      CompatRegistry.getRegistry("sophisticatedcore").setupCompats();
      RegisterGeometryLoadersCallback.EVENT
         .register((RegisterGeometryLoadersCallback)loaders -> loaders.put(getRL("fluid_container"), DynamicFluidContainerModel.Loader.INSTANCE));
   }

   private static void serverStarted(MinecraftServer server) {
      class_3218 world = server.method_3847(class_1937.field_25179);
      if (world != null) {
         RecipeHelper.setLevel(world);
         StorageWrapperRepository.clearCache();
         Config.COMMON.saveIfChanged();
      }
   }

   private static void serverStopped(MinecraftServer server) {
      StorageWrapperRepository.clearCache();
      currentServer = null;
   }

   public static class_2960 getRL(String regName) {
      return class_2960.method_60654(getRegistryName(regName));
   }

   public static String getRegistryName(String regName) {
      return "sophisticatedcore:" + regName;
   }
}
