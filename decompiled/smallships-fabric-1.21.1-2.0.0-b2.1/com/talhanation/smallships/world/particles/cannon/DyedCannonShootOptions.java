package com.talhanation.smallships.world.particles.cannon;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.talhanation.smallships.world.particles.ModParticleTypes;
import net.minecraft.class_1767;
import net.minecraft.class_2394;
import net.minecraft.class_2396;
import net.minecraft.class_9129;
import net.minecraft.class_9135;
import net.minecraft.class_9139;

public class DyedCannonShootOptions implements class_2394 {
   public static final class_9139<class_9129, DyedCannonShootOptions> STREAM_CODEC = class_9139.method_56434(
      class_9135.field_48554, DyedCannonShootOptions::getDyeColorName, DyedCannonShootOptions::new
   );
   public static final MapCodec<DyedCannonShootOptions> MAP_CODEC = RecordCodecBuilder.mapCodec(
      instance -> instance.group(Codec.STRING.fieldOf("dyeColor").forGetter(DyedCannonShootOptions::getDyeColorName))
         .apply(instance, DyedCannonShootOptions::new)
   );
   private final class_1767 dyeColor;

   public DyedCannonShootOptions(class_1767 dyeColor) {
      this.dyeColor = dyeColor;
   }

   protected DyedCannonShootOptions(String dyeColor) {
      this(class_1767.method_7793(dyeColor, null));
   }

   public class_1767 dyeColor() {
      return this.dyeColor;
   }

   public String getDyeColorName() {
      return this.dyeColor() == null ? "" : this.dyeColor().method_15434();
   }

   public class_2396<?> method_10295() {
      return ModParticleTypes.DYED_CANNON_SHOOT.get();
   }
}
