package vectorwing.farmersdelight.client.particle;

import net.minecraft.class_2400;
import net.minecraft.class_3999;
import net.minecraft.class_4002;
import net.minecraft.class_4003;
import net.minecraft.class_638;
import net.minecraft.class_703;
import net.minecraft.class_707;
import org.jetbrains.annotations.NotNull;

public class SteamParticle extends class_4003 {
   protected SteamParticle(class_638 level, double x, double y, double z, double motionX, double motionY, double motionZ) {
      super(level, x, y, z);
      this.method_3087(2.0F);
      this.method_3080(0.25F, 0.25F);
      this.field_3847 = this.field_3840.method_43048(50) + 80;
      this.field_3844 = 3.0E-6F;
      this.field_3852 = motionX;
      this.field_3869 = motionY + this.field_3840.method_43057() / 500.0F;
      this.field_3850 = motionZ;
   }

   @NotNull
   public class_3999 method_18122() {
      return class_3999.field_17829;
   }

   public void method_3070() {
      this.field_3858 = this.field_3874;
      this.field_3838 = this.field_3854;
      this.field_3856 = this.field_3871;
      if (this.field_3866++ < this.field_3847 && !(this.field_3841 <= 0.0F)) {
         this.field_3852 = this.field_3852 + this.field_3840.method_43057() / 5000.0F * (this.field_3840.method_43056() ? 1 : -1);
         this.field_3850 = this.field_3850 + this.field_3840.method_43057() / 5000.0F * (this.field_3840.method_43056() ? 1 : -1);
         this.field_3869 = this.field_3869 - this.field_3844;
         this.method_3069(this.field_3852, this.field_3869, this.field_3850);
         if (this.field_3866 >= this.field_3847 - 60 && this.field_3841 > 0.01F) {
            this.field_3841 -= 0.02F;
         }
      } else {
         this.method_3085();
      }
   }

   public static class Factory implements class_707<class_2400> {
      private final class_4002 spriteSet;

      public Factory(class_4002 sprite) {
         this.spriteSet = sprite;
      }

      public class_703 createParticle(class_2400 typeIn, class_638 level, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
         SteamParticle particle = new SteamParticle(level, x, y + 0.3, z, xSpeed, ySpeed, zSpeed);
         particle.method_3083(0.6F);
         particle.method_18140(this.spriteSet);
         return particle;
      }
   }
}
