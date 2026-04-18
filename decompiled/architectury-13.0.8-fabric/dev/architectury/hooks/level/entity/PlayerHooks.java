package dev.architectury.hooks.level.entity;

import dev.architectury.hooks.level.entity.fabric.PlayerHooksImpl;
import dev.architectury.injectables.annotations.ExpectPlatform;
import dev.architectury.injectables.annotations.ExpectPlatform.Transformed;
import net.minecraft.class_1657;

public final class PlayerHooks {
   private PlayerHooks() {
   }

   @ExpectPlatform
   @Transformed
   public static boolean isFake(class_1657 player) {
      return PlayerHooksImpl.isFake(player);
   }
}
