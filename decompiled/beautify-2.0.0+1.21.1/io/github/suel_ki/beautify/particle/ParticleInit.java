package io.github.suel_ki.beautify.particle;

import io.github.suel_ki.beautify.Beautify;
import io.github.suel_ki.beautify.particle.custom.GlowEssenceParticles;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.minecraft.class_2378;
import net.minecraft.class_2396;
import net.minecraft.class_2400;
import net.minecraft.class_7923;

public class ParticleInit {
   public static final class_2400 GLOWESSENCE_PARTICLES = register("glowessence_particles", FabricParticleTypes.simple());

   private static <T extends class_2396<?>> T register(String name, T particle) {
      return (T)class_2378.method_10230(class_7923.field_41180, Beautify.id(name), particle);
   }

   public static void ensureLoadedServerside() {
   }

   public static void registerParticle() {
      ParticleFactoryRegistry.getInstance().register(GLOWESSENCE_PARTICLES, GlowEssenceParticles.Provider::new);
   }
}
