package dev.architectury.hooks.level.biome;

import java.util.Optional;
import java.util.OptionalInt;
import net.minecraft.class_3414;
import net.minecraft.class_4761;
import net.minecraft.class_4967;
import net.minecraft.class_4968;
import net.minecraft.class_5195;
import net.minecraft.class_6880;
import net.minecraft.class_4763.class_5486;
import org.jetbrains.annotations.Nullable;

public interface EffectsProperties {
   int getFogColor();

   int getWaterColor();

   int getWaterFogColor();

   int getSkyColor();

   OptionalInt getFoliageColorOverride();

   OptionalInt getGrassColorOverride();

   class_5486 getGrassColorModifier();

   Optional<class_4761> getAmbientParticle();

   Optional<class_6880<class_3414>> getAmbientLoopSound();

   Optional<class_4968> getAmbientMoodSound();

   Optional<class_4967> getAmbientAdditionsSound();

   Optional<class_5195> getBackgroundMusic();

   public interface Mutable extends EffectsProperties {
      EffectsProperties.Mutable setFogColor(int var1);

      EffectsProperties.Mutable setWaterColor(int var1);

      EffectsProperties.Mutable setWaterFogColor(int var1);

      EffectsProperties.Mutable setSkyColor(int var1);

      EffectsProperties.Mutable setFoliageColorOverride(@Nullable Integer var1);

      EffectsProperties.Mutable setGrassColorOverride(@Nullable Integer var1);

      EffectsProperties.Mutable setGrassColorModifier(class_5486 var1);

      EffectsProperties.Mutable setAmbientParticle(@Nullable class_4761 var1);

      EffectsProperties.Mutable setAmbientLoopSound(@Nullable class_6880<class_3414> var1);

      EffectsProperties.Mutable setAmbientMoodSound(@Nullable class_4968 var1);

      EffectsProperties.Mutable setAmbientAdditionsSound(@Nullable class_4967 var1);

      EffectsProperties.Mutable setBackgroundMusic(@Nullable class_5195 var1);
   }
}
