package noobanidus.mods.lootr.common.client.particle;

import net.minecraft.class_2400;
import net.minecraft.class_3999;
import net.minecraft.class_4002;
import net.minecraft.class_4003;
import net.minecraft.class_638;
import net.minecraft.class_703;
import net.minecraft.class_707;

public class UnopenedParticle extends class_4003 {
   public UnopenedParticle(class_638 level, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
      super(level, x, y, z, xSpeed, ySpeed, zSpeed);
      this.field_3847 = 30;
      this.field_3841 = 0.8F;
      this.field_3852 = 0.0;
      this.field_3869 = ySpeed;
      this.field_3850 = 0.0;
      this.field_3862 = false;
      this.field_17867 = 0.12F;
      this.field_28786 = 1.0F;
      int c1 = 16438858;
      this.field_3861 = (c1 >> 16 & 0xFF) / 255.0F;
      this.field_3842 = (c1 >> 8 & 0xFF) / 255.0F;
      this.field_3859 = (c1 & 0xFF) / 255.0F;
   }

   protected int method_3068(float partialTick) {
      return 15728880 | super.method_3068(partialTick) & 0xFF0000;
   }

   public void method_3070() {
      super.method_3070();
      if (!this.field_3843) {
         float f = (float)this.field_3866 / this.field_3847;
         f *= f;
         this.field_3841 = Math.max(0.0F, 0.8F - f);
      }
   }

   public class_3999 method_18122() {
      return class_3999.field_17829;
   }

   public record Provider(class_4002 spriteSet) implements class_707<class_2400> {
      public class_703 createParticle(class_2400 type, class_638 level, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
         UnopenedParticle particle = new UnopenedParticle(level, x, y, z, xSpeed, ySpeed, zSpeed);
         particle.method_18140(this.spriteSet);
         return particle;
      }
   }
}
