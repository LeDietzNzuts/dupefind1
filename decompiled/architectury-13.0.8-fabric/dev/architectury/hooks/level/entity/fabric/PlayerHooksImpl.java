package dev.architectury.hooks.level.entity.fabric;

import dev.architectury.event.EventResult;
import net.minecraft.class_1657;
import net.minecraft.class_3222;

public class PlayerHooksImpl {
   public static boolean isFake(class_1657 player) {
      EventResult result = FakePlayers.EVENT.invoker().isFakePlayer(player);
      return result.isPresent() ? result.isTrue() : player instanceof class_3222 && player.getClass() != class_3222.class;
   }
}
