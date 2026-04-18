package com.talhanation.smallships.world.particles.custom;

import net.minecraft.class_3999;
import net.minecraft.class_4002;
import net.minecraft.class_638;
import net.minecraft.class_673;
import net.minecraft.class_703;
import net.minecraft.class_707;
import org.jetbrains.annotations.Nullable;
import org.joml.Vector3f;

public class CustomPoofParticle extends class_673 {
   protected CustomPoofParticle(class_638 clientLevel, Vector3f col, double d, double e, double f, double g, double h, double i, class_4002 spriteSet) {
      super(clientLevel, d, e, f, g, h, i, spriteSet);
      float brightness = this.field_3840.method_43057() * 0.3F + 0.7F;
      this.field_3861 = col.x * brightness;
      this.field_3842 = col.y * brightness;
      this.field_3859 = col.z * brightness;
   }

   public int method_3068(float partialTicks) {
      return 15728880;
   }

   public class_3999 method_18122() {
      return class_3999.field_17830;
   }

   public static class Provider implements class_707<CustomPoofParticleOptions> {
      private final class_4002 sprites;

      public Provider(class_4002 spriteSet) {
         this.sprites = spriteSet;
      }

      @Nullable
      public class_703 createParticle(
         CustomPoofParticleOptions particleOptions, class_638 clientLevel, double d, double e, double f, double g, double h, double i
      ) {
         return new CustomPoofParticle(clientLevel, particleOptions.color(), d, e, f, g, h, i, this.sprites);
      }
   }
}
