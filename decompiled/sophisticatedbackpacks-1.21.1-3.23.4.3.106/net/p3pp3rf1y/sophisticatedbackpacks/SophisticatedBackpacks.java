package net.p3pp3rf1y.sophisticatedbackpacks;

import fuzs.forgeconfigapiport.fabric.api.neoforge.v4.NeoForgeConfigRegistry;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.resource.ResourceManagerHelper;
import net.minecraft.class_2960;
import net.minecraft.class_3264;
import net.neoforged.fml.config.ModConfig.Type;
import net.p3pp3rf1y.sophisticatedbackpacks.command.SBPCommand;
import net.p3pp3rf1y.sophisticatedbackpacks.common.CommonEventHandler;
import net.p3pp3rf1y.sophisticatedbackpacks.init.ModItems;
import net.p3pp3rf1y.sophisticatedbackpacks.registry.RegistryLoader;
import net.p3pp3rf1y.sophisticatedcore.compat.CompatRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SophisticatedBackpacks implements ModInitializer {
   public static final String MOD_ID = "sophisticatedbackpacks";
   public static final Logger LOGGER = LoggerFactory.getLogger("sophisticatedbackpacks");
   private final RegistryLoader registryLoader = new RegistryLoader();
   public final CommonEventHandler commonEventHandler = new CommonEventHandler();

   public void onInitialize() {
      NeoForgeConfigRegistry.INSTANCE.register("sophisticatedbackpacks", Type.SERVER, Config.SERVER_SPEC);
      NeoForgeConfigRegistry.INSTANCE.register("sophisticatedbackpacks", Type.COMMON, Config.COMMON_SPEC);
      this.commonEventHandler.registerHandlers();
      setup();
      Config.SERVER.initListeners();
      SBPCommand.init();
      ResourceManagerHelper.get(class_3264.field_14190).registerReloadListener(this.registryLoader);
      CompatRegistry.getRegistry("sophisticatedbackpacks").setupCompats();
   }

   private static void setup() {
      ModItems.registerDispenseBehavior();
      ModItems.registerCauldronInteractions();
   }

   public static class_2960 getRL(String regName) {
      return class_2960.method_60655("sophisticatedbackpacks", regName);
   }

   public static String getRegistryName(String regName) {
      return "sophisticatedbackpacks:" + regName;
   }
}
