package vectorwing.farmersdelight.client.particle;

import net.minecraft.class_2400;
import net.minecraft.class_3532;
import net.minecraft.class_3999;
import net.minecraft.class_4002;
import net.minecraft.class_4003;
import net.minecraft.class_638;
import net.minecraft.class_703;
import net.minecraft.class_707;

public class StarParticle extends class_4003 {
   protected StarParticle(class_638 level, double posX, double posY, double posZ) {
      super(level, posX, posY, posZ, 0.0, 0.0, 0.0);
      this.field_3852 *= 0.01F;
      this.field_3869 *= 0.01F;
      this.field_3850 *= 0.01F;
      this.field_3869 += 0.1;
      this.field_17867 *= 1.5F;
      this.field_3847 = 16;
      this.field_3862 = false;
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

         this.field_3852 *= 0.86F;
         this.field_3869 *= 0.86F;
         this.field_3850 *= 0.86F;
         if (this.field_3845) {
            this.field_3852 *= 0.7F;
            this.field_3850 *= 0.7F;
         }
      }
   }

   public static class Factory implements class_707<class_2400> {
      private final class_4002 spriteSet;

      public Factory(class_4002 sprite) {
         this.spriteSet = sprite;
      }

      public class_703 createParticle(class_2400 typeIn, class_638 level, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
         StarParticle particle = new StarParticle(level, x, y + 0.3, z);
         particle.method_18140(this.spriteSet);
         particle.method_3084(1.0F, 1.0F, 1.0F);
         return particle;
      }
   }
}
