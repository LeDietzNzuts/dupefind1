package net.p3pp3rf1y.sophisticatedcore.util;

import java.util.Optional;
import net.fabricmc.api.EnvType;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.class_1792;
import net.minecraft.class_2378;
import net.minecraft.class_2960;
import net.minecraft.class_5455;
import net.minecraft.class_7923;
import net.minecraft.server.MinecraftServer;
import net.p3pp3rf1y.sophisticatedcore.SophisticatedCore;
import org.apache.commons.lang3.Validate;

public class RegistryHelper {
   private RegistryHelper() {
   }

   public static class_2960 getItemKey(class_1792 item) {
      class_2960 itemKey = class_7923.field_41178.method_10221(item);
      Validate.notNull(itemKey, "itemKey", new Object[0]);
      return itemKey;
   }

   public static <V> Optional<class_2960> getRegistryName(class_2378<V> registry, V registryEntry) {
      return Optional.ofNullable(registry.method_10221(registryEntry));
   }

   public static Optional<class_5455> getRegistryAccess() {
      if (!SophisticatedCore.isLogicalServerThread() && FabricLoader.getInstance().getEnvironmentType() == EnvType.CLIENT) {
         return ClientRegistryHelper.getRegistryAccess();
      } else {
         MinecraftServer currentServer = SophisticatedCore.getCurrentServer();
         return currentServer == null ? Optional.empty() : Optional.of(currentServer.method_30611());
      }
   }
}
