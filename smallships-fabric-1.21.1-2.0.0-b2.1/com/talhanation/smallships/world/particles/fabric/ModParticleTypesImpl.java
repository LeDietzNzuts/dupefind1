package com.talhanation.smallships.world.particles.fabric;

import com.talhanation.smallships.world.particles.ModParticleTypes;
import java.util.function.Supplier;
import net.minecraft.class_2378;
import net.minecraft.class_2394;
import net.minecraft.class_2396;
import net.minecraft.class_2960;
import net.minecraft.class_7923;
import org.jetbrains.annotations.NotNull;

public class ModParticleTypesImpl extends ModParticleTypes {
   @NotNull
   public static <T extends class_2394> Supplier<class_2396<T>> register(String string, class_2396<T> particleType) {
      class_2396<T> type = (class_2396<T>)class_2378.method_10230(class_7923.field_41180, class_2960.method_60655("smallships", string), particleType);
      return () -> type;
   }
}
