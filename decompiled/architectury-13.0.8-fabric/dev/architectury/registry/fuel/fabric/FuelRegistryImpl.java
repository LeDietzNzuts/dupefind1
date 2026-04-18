package dev.architectury.registry.fuel.fabric;

import net.fabricmc.fabric.api.registry.FuelRegistry;
import net.minecraft.class_1799;
import net.minecraft.class_1935;

public class FuelRegistryImpl {
   public static void register(int time, class_1935... items) {
      for (class_1935 item : items) {
         if (time >= 0) {
            FuelRegistry.INSTANCE.add(item, time);
         } else {
            FuelRegistry.INSTANCE.remove(item);
         }
      }
   }

   public static int get(class_1799 stack) {
      Integer time = (Integer)FuelRegistry.INSTANCE.get(stack.method_7909());
      return time == null ? 0 : time;
   }
}
