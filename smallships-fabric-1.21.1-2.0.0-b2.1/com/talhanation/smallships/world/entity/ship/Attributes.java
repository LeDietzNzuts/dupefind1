package com.talhanation.smallships.world.entity.ship;

import net.minecraft.class_2487;

public class Attributes {
   public float maxHealth;
   public float maxSpeed;
   public float maxReverseSpeed;
   public float maxRotationSpeed;
   public float acceleration;
   public float rotationAcceleration;
   public float friction;

   public void addSaveData(class_2487 tag) {
      class_2487 compoundtag = new class_2487();
      compoundtag.method_10548("maxHealth", this.maxHealth);
      compoundtag.method_10548("maxSpeed", this.maxSpeed);
      compoundtag.method_10548("maxReverseSpeed", this.maxReverseSpeed);
      compoundtag.method_10548("acceleration", this.acceleration);
      compoundtag.method_10548("rotationAcceleration", this.rotationAcceleration);
      compoundtag.method_10548("maxRotationSpeed", this.maxRotationSpeed);
      compoundtag.method_10548("friction", this.friction);
      tag.method_10566("Attributes", compoundtag);
   }

   public class_2487 getSaveData() {
      class_2487 compoundtag = new class_2487();
      this.addSaveData(compoundtag);
      return compoundtag;
   }

   public void loadSaveData(class_2487 tag) {
      if (tag.method_10573("Attributes", 10)) {
         class_2487 compoundtag = tag.method_10562("Attributes");
         this.maxHealth = compoundtag.method_10583("maxHealth");
         this.maxSpeed = compoundtag.method_10583("maxSpeed");
         this.maxReverseSpeed = compoundtag.method_10583("maxReverseSpeed");
         this.acceleration = compoundtag.method_10583("acceleration");
         this.rotationAcceleration = compoundtag.method_10583("rotationAcceleration");
         this.maxRotationSpeed = compoundtag.method_10583("maxRotationSpeed");
         this.friction = compoundtag.method_10583("friction");
      }
   }

   public void loadSaveData(class_2487 tag, Ship shipEntity) {
      if (tag.method_10573("Attributes", 10)) {
         this.loadSaveData(tag);
      } else {
         this.loadSaveData(shipEntity.createDefaultAttributes());
      }
   }

   @Override
   public String toString() {
      return "Attributes{maxHealth="
         + this.maxHealth
         + ", maxSpeed="
         + this.maxSpeed
         + ", maxReverseSpeed="
         + this.maxReverseSpeed
         + ", acceleration="
         + this.acceleration
         + ", maxRotationSpeed="
         + this.maxRotationSpeed
         + ", friction="
         + this.friction
         + "}";
   }
}
