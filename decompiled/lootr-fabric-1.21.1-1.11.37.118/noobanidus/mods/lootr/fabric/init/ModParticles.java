package noobanidus.mods.lootr.fabric.init;

import net.minecraft.class_2378;
import net.minecraft.class_2400;
import net.minecraft.class_7923;
import noobanidus.mods.lootr.common.api.LootrConstants;
import noobanidus.mods.lootr.fabric.mixin.accessor.AccessorMixinSimpleParticleType;

public class ModParticles {
   public static final class_2400 UNOPENED_PARTCLE = AccessorMixinSimpleParticleType.lootr$invokeConstructor(true);

   public static void register() {
      class_2378.method_10230(class_7923.field_41180, LootrConstants.UNOPENED_PARTICLE, UNOPENED_PARTCLE);
   }
}
