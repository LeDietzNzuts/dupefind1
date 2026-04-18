package io.github.suel_ki.beautify.particle.custom;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.class_2400;
import net.minecraft.class_3999;
import net.minecraft.class_4002;
import net.minecraft.class_4003;
import net.minecraft.class_638;
import net.minecraft.class_703;
import net.minecraft.class_707;

@Environment(EnvType.CLIENT)
public class GlowEssenceParticles extends class_4003 {
   private static final float size = 0.07F;

   protected GlowEssenceParticles(class_638 level, double xCoord, double yCoord, double zCoord, class_4002 spriteSet, double xd, double yd, double zd) {
      super(level, xCoord, yCoord, zCoord, xd, yd, zd);
      this.field_28786 = 0.8F;
      this.field_3852 = xd;
      this.field_3869 = yd;
      this.field_3850 = zd;
      this.field_17867 = 0.0F;
      this.field_3847 = (int)(60.0 * (0.5 + Math.random() / 2.0));
      this.method_18142(spriteSet);
      this.field_3861 = 1.0F;
      this.field_3842 = 1.0F;
      this.field_3859 = 1.0F;
   }

   private void fadeOut() {
      float fadeValue = (float)Math.sin(Math.PI * ((float)this.field_3866 / this.field_3847));
      this.field_3841 = 1.0F * fadeValue;
      this.field_17867 = 0.07F * fadeValue;
   }

   private void move() {
      if (Math.random() <= 0.05) {
         this.field_3852 = (Math.random() * 2.0 - 1.0) / 70.0;
      }

      if (Math.random() <= 0.05) {
         this.field_3869 = (Math.random() * 2.0 - 1.0) / 70.0;
      }

      if (Math.random() <= 0.05) {
         this.field_3850 = (Math.random() * 2.0 - 1.0) / 70.0;
      }
   }

   public class_3999 method_18122() {
      return class_3999.field_17830;
   }

   public void method_3070() {
      super.method_3070();
      this.fadeOut();
      this.move();
   }

   @Environment(EnvType.CLIENT)
   public static class Provider implements class_707<class_2400> {
      private final class_4002 sprites;

      public Provider(class_4002 spriteSet) {
         this.sprites = spriteSet;
      }

      public class_703 createParticle(class_2400 particleType, class_638 level, double x, double y, double z, double dx, double dy, double dz) {
         return new GlowEssenceParticles(level, x, y, z, this.sprites, dx, dy, dz);
      }
   }
}
