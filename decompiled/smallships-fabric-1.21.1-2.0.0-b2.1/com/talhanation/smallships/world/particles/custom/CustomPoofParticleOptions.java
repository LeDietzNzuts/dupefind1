package com.talhanation.smallships.world.particles.custom;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.talhanation.smallships.world.particles.ModParticleTypes;
import net.minecraft.class_2394;
import net.minecraft.class_2396;
import net.minecraft.class_5699;
import net.minecraft.class_9129;
import net.minecraft.class_9135;
import net.minecraft.class_9139;
import org.joml.Vector3f;

public record CustomPoofParticleOptions(Vector3f color) implements class_2394 {
   public static final class_9139<class_9129, CustomPoofParticleOptions> STREAM_CODEC = class_9139.method_56434(
      class_9135.field_48558, CustomPoofParticleOptions::color, CustomPoofParticleOptions::new
   );
   public static final MapCodec<CustomPoofParticleOptions> MAP_CODEC = RecordCodecBuilder.mapCodec(
      instance -> instance.group(class_5699.field_40723.fieldOf("color").forGetter(CustomPoofParticleOptions::color))
         .apply(instance, CustomPoofParticleOptions::new)
   );

   public class_2396<?> method_10295() {
      return ModParticleTypes.COLORED_POOF.get();
   }
}
