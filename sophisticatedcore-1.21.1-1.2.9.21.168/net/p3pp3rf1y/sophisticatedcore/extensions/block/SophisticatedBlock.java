package net.p3pp3rf1y.sophisticatedcore.extensions.block;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.class_1297;
import net.minecraft.class_1309;
import net.minecraft.class_1937;
import net.minecraft.class_2338;
import net.minecraft.class_239;
import net.minecraft.class_2680;
import net.minecraft.class_3218;
import net.minecraft.class_702;

public interface SophisticatedBlock {
   default boolean sophisticatedCore_addLandingEffects(
      class_2680 state1, class_3218 level, class_2338 pos, class_2680 state2, class_1309 entity, int numberOfParticles
   ) {
      return false;
   }

   default boolean sophisticatedCore_addRunningEffects(class_2680 state, class_1937 level, class_2338 pos, class_1297 entity) {
      return false;
   }

   @Environment(EnvType.CLIENT)
   default boolean sophisticatedCore_addHitEffects(class_2680 state, class_1937 level, class_239 target, class_702 manager) {
      return false;
   }

   @Environment(EnvType.CLIENT)
   default boolean sophisticatedCore_addDestroyEffects(class_2680 state, class_1937 level, class_2338 pos, class_702 manager) {
      return !state.method_45475();
   }
}
