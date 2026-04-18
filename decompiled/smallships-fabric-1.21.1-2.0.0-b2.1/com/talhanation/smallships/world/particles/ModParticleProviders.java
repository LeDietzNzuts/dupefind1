package com.talhanation.smallships.world.particles;

import com.talhanation.smallships.world.particles.cannon.CannonBallShootParticles;
import com.talhanation.smallships.world.particles.cannon.CannonPoofParticles;
import com.talhanation.smallships.world.particles.custom.CustomPoofParticle;
import com.talhanation.smallships.world.particles.fabric.ModParticleProvidersImpl;
import dev.architectury.injectables.annotations.ExpectPlatform;
import dev.architectury.injectables.annotations.ExpectPlatform.Transformed;
import java.util.function.Function;
import java.util.function.Supplier;
import net.minecraft.class_2394;
import net.minecraft.class_2396;
import net.minecraft.class_4002;
import net.minecraft.class_707;

public class ModParticleProviders {
   public ModParticleProviders() {
      register((class_2396)ModParticleTypes.CANNON_SHOOT.get(), CannonPoofParticles.Provider::new);
      register(ModParticleTypes.DYED_CANNON_SHOOT.get(), CannonPoofParticles.DyedProvider::new);
      register(ModParticleTypes.COLORED_POOF.get(), CustomPoofParticle.Provider::new);
      register((class_2396)ModParticleTypes.CANNON_BALL_SHOOT.get(), CannonBallShootParticles.Provider::new);
   }

   public static <T extends class_2394> void register(class_2396<T> type, Supplier<class_707<T>> providerConstructor) {
      register(type, providerConstructor.get());
   }

   @ExpectPlatform
   @Transformed
   public static <T extends class_2394> void register(class_2396<T> type, class_707<T> provider) {
      ModParticleProvidersImpl.register(type, provider);
   }

   @ExpectPlatform
   @Transformed
   public static <T extends class_2394> void register(class_2396<T> type, Function<class_4002, class_707<T>> provider) {
      ModParticleProvidersImpl.register(type, provider);
   }
}
