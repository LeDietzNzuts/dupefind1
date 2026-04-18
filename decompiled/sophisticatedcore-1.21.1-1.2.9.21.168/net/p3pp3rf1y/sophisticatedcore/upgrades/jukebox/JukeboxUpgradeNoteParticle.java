package net.p3pp3rf1y.sophisticatedcore.upgrades.jukebox;

import javax.annotation.Nullable;
import net.minecraft.class_3532;
import net.minecraft.class_3999;
import net.minecraft.class_4002;
import net.minecraft.class_4003;
import net.minecraft.class_638;
import net.minecraft.class_703;
import net.minecraft.class_707;

public class JukeboxUpgradeNoteParticle extends class_4003 {
   protected JukeboxUpgradeNoteParticle(class_638 level, double x, double y, double z) {
      super(level, x, y, z, 0.0, 0.0, 0.0);
      this.field_3852 *= 0.01F;
      this.field_3869 *= 0.05F;
      this.field_3850 *= 0.01F;
      this.field_3869 += 0.01;
      double color = level.method_8409().method_43058();
      this.field_3861 = Math.max(0.0F, class_3532.method_15374(((float)color + 0.0F) * (float) (Math.PI * 2)) * 0.65F + 0.35F);
      this.field_3842 = Math.max(0.0F, class_3532.method_15374(((float)color + 0.33333334F) * (float) (Math.PI * 2)) * 0.65F + 0.35F);
      this.field_3859 = Math.max(0.0F, class_3532.method_15374(((float)color + 0.6666667F) * (float) (Math.PI * 2)) * 0.65F + 0.35F);
      this.field_17867 *= 1.5F;
      this.field_3847 = 20;
   }

   public class_3999 method_18122() {
      return class_3999.field_17828;
   }

   public float method_18132(float scaleFactor) {
      return this.field_17867 * class_3532.method_15363((this.field_3866 + scaleFactor) / this.field_3847 * 32.0F, 0.0F, 1.0F);
   }

   public void method_3070() {
      this.field_3858 = this.field_3874;
      this.field_3838 = this.field_3854;
      this.field_3856 = this.field_3871;
      if (this.field_3866++ >= this.field_3847) {
         this.method_3085();
      } else {
         this.method_3069(this.field_3852, this.field_3869, this.field_3850);
         if (this.field_3854 == this.field_3838) {
            this.field_3852 *= 1.1;
            this.field_3850 *= 1.1;
         }

         if (this.field_3845) {
            this.field_3852 *= 0.7F;
            this.field_3850 *= 0.7F;
         }
      }
   }

   public static class Factory implements class_707<JukeboxUpgradeNoteParticleData> {
      private final class_4002 spriteSet;

      public Factory(class_4002 spriteSet) {
         this.spriteSet = spriteSet;
      }

      @Nullable
      public class_703 createParticle(
         JukeboxUpgradeNoteParticleData type, class_638 level, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed
      ) {
         JukeboxUpgradeNoteParticle particle = new JukeboxUpgradeNoteParticle(level, x, y, z);
         particle.method_18140(this.spriteSet);
         return particle;
      }
   }
}
