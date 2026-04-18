package org.embeddedt.modernfix.platform;

import com.google.common.collect.Multimap;
import com.mojang.brigadier.CommandDispatcher;
import java.nio.file.Path;
import java.util.function.Consumer;
import net.minecraft.class_2168;
import net.minecraft.class_3222;
import net.minecraft.class_8710;
import net.minecraft.server.MinecraftServer;
import org.objectweb.asm.tree.ClassNode;

public interface ModernFixPlatformHooks {
   ModernFixPlatformHooks INSTANCE = PlatformHookLoader.findInstance();

   boolean isClient();

   boolean isDedicatedServer();

   String getVersionString();

   boolean modPresent(String var1);

   boolean isDevEnv();

   void injectPlatformSpecificHacks();

   void applyASMTransformers(String var1, ClassNode var2);

   MinecraftServer getCurrentServer();

   boolean isEarlyLoadingNormally();

   boolean isLoadingNormally();

   Path getGameDirectory();

   void sendPacket(class_3222 var1, class_8710 var2);

   Multimap<String, String> getCustomModOptions();

   void onServerCommandRegister(Consumer<CommandDispatcher<class_2168>> var1);

   void onLaunchComplete();

   String getPlatformName();
}
