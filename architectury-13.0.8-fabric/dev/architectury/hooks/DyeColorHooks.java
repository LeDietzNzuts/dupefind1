package dev.architectury.hooks;

import dev.architectury.hooks.fabric.DyeColorHooksImpl;
import dev.architectury.injectables.annotations.ExpectPlatform;
import dev.architectury.injectables.annotations.ExpectPlatform.Transformed;
import net.minecraft.class_1767;

public class DyeColorHooks {
   private DyeColorHooks() {
   }

   @ExpectPlatform
   @Transformed
   public static int getColorValue(class_1767 color) {
      return DyeColorHooksImpl.getColorValue(color);
   }
}
