package dev.architectury.utils;

import dev.architectury.injectables.annotations.ExpectPlatform;
import dev.architectury.injectables.annotations.ExpectPlatform.Transformed;
import dev.architectury.utils.fabric.GameInstanceImpl;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.class_310;
import net.minecraft.server.MinecraftServer;
import org.jetbrains.annotations.Nullable;

public final class GameInstance {
   @Environment(EnvType.CLIENT)
   public static class_310 getClient() {
      return class_310.method_1551();
   }

   @Nullable
   @ExpectPlatform
   @Transformed
   public static MinecraftServer getServer() {
      return GameInstanceImpl.getServer();
   }
}
