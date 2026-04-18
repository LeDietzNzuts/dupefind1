package net.caffeinemc.mods.sodium.client.gui.console;

import net.caffeinemc.mods.sodium.client.console.Console;
import net.minecraft.class_332;

public class ConsoleHooks {
   public static void render(class_332 graphics, double currentTime) {
      ConsoleRenderer.INSTANCE.update(Console.INSTANCE, currentTime);
      ConsoleRenderer.INSTANCE.draw(graphics);
   }
}
