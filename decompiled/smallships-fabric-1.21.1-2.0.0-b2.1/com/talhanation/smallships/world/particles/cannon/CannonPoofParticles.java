package com.talhanation.smallships.world.particles.cannon;

import com.talhanation.smallships.utils.Color;
import com.talhanation.smallships.utils.VectorMath;
import com.talhanation.smallships.world.particles.CompoundParticles;
import com.talhanation.smallships.world.particles.custom.CustomPoofParticleOptions;
import net.minecraft.class_1767;
import net.minecraft.class_2398;
import net.minecraft.class_2400;
import net.minecraft.class_638;
import net.minecraft.class_703;
import net.minecraft.class_707;
import org.jetbrains.annotations.Nullable;
import org.joml.Vector3d;

public class CannonPoofParticles extends CompoundParticles {
   @Nullable
   private final Integer color;

   public CannonPoofParticles(class_638 clientLevel, Integer color, double x, double y, double z, double vx, double vy, double vz) {
      super(clientLevel, 1, x, y, z, vx, vy, vz);
      this.color = color;
   }

   public CannonPoofParticles(class_638 clientLevel, double x, double y, double z, double vx, double vy, double vz) {
      this(clientLevel, null, x, y, z, vx, vy, vz);
   }

   @Override
   public void spawn() {
      this.addPoofForwardParticles(200);
      this.addStaticMainPoofParticles(100);
   }

   protected void addStaticMainPoofParticles(int amount) {
      for (int i = 0; i < amount; i++) {
         Vector3d rand = VectorMath.getRandGaussian(this.field_3840);
         Vector3d pos = new Vector3d(rand).mul(0.3).add(this.getPos());
         Vector3d v = new Vector3d(rand).mul(0.07);
         Vector3d n = this.getNormalizedDirection();
         if (v.dot(n) < 0.0) {
            v = VectorMath.projectOntoPlane(v, n);
         }

         if (this.color == null) {
            this.field_3851.method_8406(class_2398.field_11203, pos.x, pos.y, pos.z, v.x, v.y, v.z);
         } else {
            Color color = new Color(this.color);
            this.field_3851.method_8406(new CustomPoofParticleOptions(color.getAsVector3f()), pos.x, pos.y, pos.z, v.x, v.y, v.z);
         }
      }
   }

   protected void addPoofForwardParticles(int amount) {
      for (int i = 0; i < amount; i++) {
         Vector3d rand = VectorMath.getRandGaussian(this.field_3840);
         Vector3d pos = new Vector3d(rand).mul(0.2).add(this.getPos());
         Vector3d v = new Vector3d(rand).mul(0.03).add(this.getNormalizedDirection().mul(Math.abs(this.field_3840.method_43059()) * 0.3F));
         Vector3d n = this.getNormalizedDirection();
         if (v.dot(n) < 0.0) {
            v = VectorMath.projectOntoPlane(v, n);
         }

         if (this.color == null) {
            this.field_3851.method_8406(class_2398.field_11203, pos.x, pos.y, pos.z, v.x, v.y, v.z);
         } else {
            Color color = new Color(this.color);
            this.field_3851.method_8406(new CustomPoofParticleOptions(color.getAsVector3f()), pos.x, pos.y, pos.z, v.x, v.y, v.z);
         }
      }
   }

   public static class DyedProvider implements class_707<DyedCannonShootOptions> {
      @Nullable
      public class_703 createParticle(
         DyedCannonShootOptions particleOptions, class_638 clientLevel, double x, double y, double z, double vx, double vy, double vz
      ) {
         class_1767 color = particleOptions.dyeColor();
         Integer col = null;
         if (color != null) {
            col = color.method_7790();
         }

         return new CannonPoofParticles(clientLevel, col, x, y, z, vx, vy, vz);
      }
   }

   public static class Provider implements class_707<class_2400> {
      @Nullable
      public class_703 createParticle(class_2400 particleOptions, class_638 clientLevel, double x, double y, double z, double vx, double vy, double vz) {
         return new CannonPoofParticles(clientLevel, x, y, z, vx, vy, vz);
      }
   }
}
