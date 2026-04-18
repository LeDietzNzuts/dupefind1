package dev.architectury.registry.fuel;

import dev.architectury.injectables.annotations.ExpectPlatform;
import dev.architectury.injectables.annotations.ExpectPlatform.Transformed;
import dev.architectury.registry.fuel.fabric.FuelRegistryImpl;
import net.minecraft.class_1799;
import net.minecraft.class_1935;

public final class FuelRegistry {
   private FuelRegistry() {
   }

   @ExpectPlatform
   @Transformed
   public static void register(int time, class_1935... items) {
      FuelRegistryImpl.register(time, items);
   }

   @ExpectPlatform
   @Transformed
   public static int get(class_1799 stack) {
      return FuelRegistryImpl.get(stack);
   }
}
