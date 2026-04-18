package net.p3pp3rf1y.sophisticatedcore.extensions.client.particle;

import javax.annotation.Nullable;
import net.minecraft.class_2338;
import net.minecraft.class_2680;
import net.minecraft.class_703;

public interface SophisticatedTerrainParticle {
   default class_703 sophisticatedCore_updateSprite(class_2680 state, @Nullable class_2338 pos) {
      throw new RuntimeException("Should have been overridden");
   }
}
