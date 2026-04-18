package com.talhanation.smallships.world.particles;

import net.minecraft.class_3999;
import net.minecraft.class_4184;
import net.minecraft.class_4588;
import net.minecraft.class_638;
import net.minecraft.class_703;
import org.joml.Vector3d;

public abstract class CompoundParticles extends class_703 {
   private final Vector3d speed;

   public CompoundParticles(class_638 clientLevel, int lifetime, double x, double y, double z) {
      this(clientLevel, lifetime, x, y, z, 0.0, 0.0, 0.0);
   }

   public CompoundParticles(class_638 clientLevel, int lifetime, double x, double y, double z, double vx, double vy, double vz) {
      super(clientLevel, x, y, z, 0.0, 0.0, 0.0);
      this.speed = new Vector3d(vx, vy, vz);
      this.field_3847 = lifetime;
   }

   public Vector3d getPos() {
      return new Vector3d(this.field_3874, this.field_3854, this.field_3871);
   }

   public Vector3d getNormalizedDirection() {
      return this.speed.length() == 0.0 ? new Vector3d(0.0) : new Vector3d(this.speed).normalize();
   }

   public Vector3d getSpeed() {
      return new Vector3d(this.speed);
   }

   public final void method_3070() {
      if (this.field_3866 == 0) {
         this.spawn();
      } else {
         this.update();
      }

      if (this.field_3866++ >= this.field_3847) {
         this.method_3085();
      }
   }

   public abstract void spawn();

   public void update() {
   }

   public final void method_3074(class_4588 vertexConsumer, class_4184 camera, float f) {
   }

   public final class_3999 method_18122() {
      return class_3999.field_17832;
   }
}
