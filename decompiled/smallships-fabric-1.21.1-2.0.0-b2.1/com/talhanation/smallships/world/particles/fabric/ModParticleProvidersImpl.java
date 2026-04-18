package com.talhanation.smallships.world.particles.fabric;

import com.talhanation.smallships.world.particles.ModParticleProviders;
import java.util.function.Function;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.minecraft.class_2394;
import net.minecraft.class_2396;
import net.minecraft.class_4002;
import net.minecraft.class_707;

public class ModParticleProvidersImpl extends ModParticleProviders {
   public static <T extends class_2394> void register(class_2396<T> type, class_707<T> provider) {
      ParticleFactoryRegistry.getInstance().register(type, provider);
   }

   public static <T extends class_2394> void register(class_2396<T> type, Function<class_4002, class_707<T>> provider) {
      ParticleFactoryRegistry.getInstance().register(type, provider::apply);
   }
}
