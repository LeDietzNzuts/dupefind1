package org.embeddedt.modernfix;

import java.lang.ref.WeakReference;
import net.fabricmc.api.ModInitializer;
import net.minecraft.server.MinecraftServer;

public class ModernFixFabric implements ModInitializer {
   public static ModernFix commonMod;
   public static WeakReference<MinecraftServer> theServer = new WeakReference<>(null);

   public void onInitialize() {
      commonMod = new ModernFix();
   }
}
