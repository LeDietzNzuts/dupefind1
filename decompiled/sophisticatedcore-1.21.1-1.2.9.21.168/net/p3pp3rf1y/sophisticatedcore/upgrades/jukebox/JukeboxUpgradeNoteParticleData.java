package net.p3pp3rf1y.sophisticatedcore.upgrades.jukebox;

import com.mojang.serialization.MapCodec;
import net.minecraft.class_2394;
import net.minecraft.class_2396;
import net.minecraft.class_9129;
import net.minecraft.class_9139;
import net.p3pp3rf1y.sophisticatedcore.init.ModParticles;

public class JukeboxUpgradeNoteParticleData extends class_2396<JukeboxUpgradeNoteParticleData> implements class_2394 {
   private final MapCodec<JukeboxUpgradeNoteParticleData> codec = MapCodec.unit(this::getType);
   private final class_9139<class_9129, JukeboxUpgradeNoteParticleData> streamCodec = class_9139.method_56431(this);

   public JukeboxUpgradeNoteParticleData() {
      super(false);
   }

   public JukeboxUpgradeNoteParticleData getType() {
      return ModParticles.JUKEBOX_NOTE.get();
   }

   public MapCodec<JukeboxUpgradeNoteParticleData> method_29138() {
      return this.codec;
   }

   public class_9139<? super class_9129, JukeboxUpgradeNoteParticleData> method_56179() {
      return this.streamCodec;
   }
}
