package com.natamus.collective_common_forge.events;

import com.mojang.datafixers.util.Pair;
import java.util.concurrent.CopyOnWriteArrayList;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.DeathScreen;
import net.minecraft.server.TickTask;

public class CollectiveClientEvents {
   private static final Minecraft mc = Minecraft.getInstance();
   public static CopyOnWriteArrayList<Pair<Integer, Runnable>> scheduledClientRunnables = new CopyOnWriteArrayList<>();
   public static int clientTickCount = 0;

   public static void onClientTick() {
      if (!mc.isPaused() || mc.screen instanceof DeathScreen) {
         for (Pair<Integer, Runnable> pair : scheduledClientRunnables) {
            if ((Integer)pair.getFirst() <= clientTickCount) {
               Minecraft.getInstance().execute(new TickTask(clientTickCount, (Runnable)pair.getSecond()));
               scheduledClientRunnables.remove(pair);
            }
         }

         clientTickCount++;
      }
   }
}
