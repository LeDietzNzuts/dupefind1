package com.talhanation.smallships.world.particles.cannon;

import com.talhanation.smallships.utils.VectorMath;
import com.talhanation.smallships.world.particles.CompoundParticles;
import net.minecraft.class_2398;
import net.minecraft.class_2400;
import net.minecraft.class_638;
import net.minecraft.class_703;
import net.minecraft.class_707;
import org.jetbrains.annotations.Nullable;
import org.joml.Vector3d;

public class CannonBallShootParticles extends CompoundParticles {
   public CannonBallShootParticles(class_638 clientLevel, double x, double y, double z, double vx, double vy, double vz) {
      super(clientLevel, 1, x, y, z, vx, vy, vz);
   }

   @Override
   public void spawn() {
      this.addFlamesForwardParticles(100);
      this.addDarkSmokeParticles(50);
   }

   protected void addFlamesForwardParticles(int amount) {
      for (int i = 0; i < amount; i++) {
         Vector3d rand = VectorMath.getRandGaussian(this.field_3840);
         Vector3d pos = new Vector3d(rand).mul(0.2).add(this.getPos());
         Vector3d v = new Vector3d(rand).mul(0.02).add(this.getNormalizedDirection().mul(Math.abs(this.field_3840.method_43059()) * 0.2F));
         Vector3d n = this.getNormalizedDirection();
         if (v.dot(n) < 0.0) {
            v = VectorMath.projectOntoPlane(v, n);
         }

         this.field_3851.method_8406(class_2398.field_11240, pos.x, pos.y, pos.z, v.x, v.y, v.z);
      }
   }

   protected void addDarkSmokeParticles(int amount) {
      for (int i = 0; i < amount; i++) {
         Vector3d rand = VectorMath.getRandGaussian(this.field_3840);
         Vector3d pos = new Vector3d(rand).mul(0.2).add(this.getPos());
         Vector3d v = new Vector3d(rand).mul(0.02).add(this.getNormalizedDirection().mul(Math.max(0.0, this.field_3840.method_43059()) * 0.05F));
         Vector3d n = this.getNormalizedDirection();
         if (v.dot(n) < 0.0) {
            v = VectorMath.projectOntoPlane(v, n);
         }

         this.field_3851.method_8406(class_2398.field_11237, pos.x, pos.y, pos.z, v.x, v.y, v.z);
      }
   }

   public static class Provider implements class_707<class_2400> {
      @Nullable
      public class_703 createParticle(class_2400 particleOptions, class_638 clientLevel, double d, double e, double f, double g, double h, double i) {
         return new CannonBallShootParticles(clientLevel, d, e, f, g, h, i);
      }
   }
}
