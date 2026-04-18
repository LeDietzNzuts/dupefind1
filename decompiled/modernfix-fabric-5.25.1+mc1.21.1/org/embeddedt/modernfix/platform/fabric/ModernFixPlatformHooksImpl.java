package org.embeddedt.modernfix.platform.fabric;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import com.mojang.brigadier.CommandDispatcher;
import java.nio.file.Path;
import java.util.Map.Entry;
import java.util.function.Consumer;
import net.fabricmc.api.EnvType;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.ModContainer;
import net.fabricmc.loader.api.metadata.CustomValue;
import net.fabricmc.loader.api.metadata.ModMetadata;
import net.fabricmc.loader.api.metadata.CustomValue.CvType;
import net.minecraft.class_2168;
import net.minecraft.class_3222;
import net.minecraft.class_8710;
import net.minecraft.server.MinecraftServer;
import org.embeddedt.modernfix.ModernFixFabric;
import org.embeddedt.modernfix.core.ModernFixMixinPlugin;
import org.embeddedt.modernfix.platform.ModernFixPlatformHooks;
import org.embeddedt.modernfix.spark.SparkLaunchProfiler;
import org.embeddedt.modernfix.util.CommonModUtil;
import org.objectweb.asm.tree.ClassNode;

public class ModernFixPlatformHooksImpl implements ModernFixPlatformHooks {
   private static final String verString = FabricLoader.getInstance()
      .getModContainer("modernfix")
      .map(mfModContainer -> mfModContainer.getMetadata().getVersion().getFriendlyString())
      .orElse("[unknown]");
   private static Multimap<String, String> modOptions;

   @Override
   public boolean isClient() {
      return FabricLoader.getInstance().getEnvironmentType() == EnvType.CLIENT;
   }

   @Override
   public boolean isDedicatedServer() {
      return FabricLoader.getInstance().getEnvironmentType() == EnvType.SERVER;
   }

   @Override
   public String getVersionString() {
      return verString;
   }

   @Override
   public boolean modPresent(String modId) {
      return FabricLoader.getInstance().getModContainer(modId).isPresent();
   }

   @Override
   public boolean isDevEnv() {
      return FabricLoader.getInstance().isDevelopmentEnvironment();
   }

   @Override
   public MinecraftServer getCurrentServer() {
      return ModernFixFabric.theServer.get();
   }

   @Override
   public boolean isEarlyLoadingNormally() {
      return true;
   }

   @Override
   public boolean isLoadingNormally() {
      return true;
   }

   @Override
   public Path getGameDirectory() {
      return FabricLoader.getInstance().getGameDir();
   }

   @Override
   public void sendPacket(class_3222 player, class_8710 packet) {
   }

   @Override
   public void injectPlatformSpecificHacks() {
   }

   @Override
   public void applyASMTransformers(String mixinClassName, ClassNode targetClass) {
   }

   @Override
   public void onServerCommandRegister(Consumer<CommandDispatcher<class_2168>> handler) {
      if (FabricLoader.getInstance().isModLoaded("fabric-command-api-v2")) {
         CommandRegistrationCallback.EVENT.register((CommandRegistrationCallback)(dispatcher, arg, env) -> handler.accept(dispatcher));
      }
   }

   @Override
   public Multimap<String, String> getCustomModOptions() {
      if (modOptions == null) {
         modOptions = ArrayListMultimap.create();

         for (ModContainer container : FabricLoader.getInstance().getAllMods()) {
            ModMetadata meta = container.getMetadata();
            if (meta.containsCustomValue("modernfix:integration")) {
               CustomValue integrations = meta.getCustomValue("modernfix:integration");
               if (integrations.getType() == CvType.OBJECT) {
                  for (Entry<String, CustomValue> entry : integrations.getAsObject()) {
                     if (entry.getValue().getType() == CvType.STRING) {
                        modOptions.put(entry.getKey(), entry.getValue().getAsString());
                     }
                  }
               }
            }
         }
      }

      return modOptions;
   }

   @Override
   public void onLaunchComplete() {
      if (ModernFixMixinPlugin.instance.isOptionEnabled("feature.spark_profile_launch.OnFabric")) {
         CommonModUtil.runWithoutCrash(() -> SparkLaunchProfiler.stop("launch"), "Failed to stop profiler");
      }
   }

   @Override
   public String getPlatformName() {
      return "Fabric";
   }
}
