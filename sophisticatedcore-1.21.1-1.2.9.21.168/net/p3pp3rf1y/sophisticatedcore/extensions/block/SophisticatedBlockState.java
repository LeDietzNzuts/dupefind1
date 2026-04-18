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

public interface SophisticatedBlockState {
   private class_2680 self() {
      return (class_2680)this;
   }

   default boolean sophisticatedCore_addLandingEffects(class_3218 level, class_2338 pos, class_2680 state2, class_1309 entity, int numberOfParticles) {
      return this.self().method_26204().sophisticatedCore_addLandingEffects(this.self(), level, pos, state2, entity, numberOfParticles);
   }

   default boolean sophisticatedCore_addRunningEffects(class_1937 level, class_2338 pos, class_1297 entity) {
      return this.self().method_26204().sophisticatedCore_addRunningEffects(this.self(), level, pos, entity);
   }

   @Environment(EnvType.CLIENT)
   default boolean sophisticatedCore_addHitEffects(class_1937 level, class_239 target, class_702 manager) {
      return this.self().method_26204().sophisticatedCore_addHitEffects(this.self(), level, target, manager);
   }

   @Environment(EnvType.CLIENT)
   default boolean sophisticatedCore_addDestroyEffects(class_1937 level, class_2338 pos, class_702 manager) {
      return this.self().method_26204().sophisticatedCore_addDestroyEffects(this.self(), level, pos, manager);
   }
}
