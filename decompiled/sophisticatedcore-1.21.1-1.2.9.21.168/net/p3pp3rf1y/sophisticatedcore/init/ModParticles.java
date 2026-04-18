package net.p3pp3rf1y.sophisticatedcore.init;

import io.github.fabricators_of_create.porting_lib.util.DeferredRegister;
import java.util.function.Supplier;
import net.minecraft.class_2396;
import net.minecraft.class_7924;
import net.p3pp3rf1y.sophisticatedcore.upgrades.jukebox.JukeboxUpgradeNoteParticleData;

public class ModParticles {
   private static final DeferredRegister<class_2396<?>> PARTICLES = DeferredRegister.create(class_7924.field_41210, "sophisticatedcore");
   public static final Supplier<JukeboxUpgradeNoteParticleData> JUKEBOX_NOTE = PARTICLES.register("jukebox_note", JukeboxUpgradeNoteParticleData::new);

   private ModParticles() {
   }

   public static void registerParticles() {
      PARTICLES.register();
   }
}
