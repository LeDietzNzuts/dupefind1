package net.p3pp3rf1y.sophisticatedbackpacks.upgrades.everlasting;

import net.minecraft.class_1282;
import net.minecraft.class_1299;
import net.minecraft.class_1542;
import net.minecraft.class_1927;
import net.minecraft.class_1937;
import net.minecraft.class_2398;
import net.minecraft.class_243;
import net.minecraft.class_2487;
import net.minecraft.class_3218;

public class EverlastingBackpackItemEntity extends class_1542 {
   private boolean wasFloatingUp = false;
   private int age = 0;

   public EverlastingBackpackItemEntity(class_1299<? extends class_1542> type, class_1937 level) {
      super(type, level);
      this.method_35190();
   }

   public void method_5773() {
      if (!this.method_37908().field_9236) {
         double d0 = this.method_23317() + 0.5 - this.field_5974.method_43057();
         double d1 = this.method_23318() + this.field_5974.method_43057() * 0.5F;
         double d2 = this.method_23321() + 0.5 - this.field_5974.method_43057();
         class_3218 serverLevel = (class_3218)this.method_37908();
         if (this.field_5974.method_43048(20) == 0) {
            serverLevel.method_14199(class_2398.field_11211, d0, d1, d2, 0, 0.0, 0.1, 0.0, 1.0);
         }
      }

      if (!this.method_5740()) {
         if (this.method_5799() || this.method_5771()) {
            this.method_5764(false);
            this.wasFloatingUp = true;
         } else if (this.wasFloatingUp) {
            this.method_5875(true);
            this.method_18799(class_243.field_1353);
         }
      }

      this.age++;
      super.method_5773();
   }

   public boolean method_5799() {
      return this.method_23318() < this.method_37908().method_31607() + 1 || super.method_5799();
   }

   public boolean method_5753() {
      return true;
   }

   public boolean method_5659(class_1927 explosion) {
      return true;
   }

   public boolean method_5679(class_1282 source) {
      return true;
   }

   protected void method_5825() {
   }

   public int method_6985() {
      return this.age;
   }

   public void method_5652(class_2487 compound) {
      super.method_5652(compound);
      compound.method_10569("EverlastingAge", this.age);
   }

   public void method_5749(class_2487 compound) {
      super.method_5749(compound);
      this.age = compound.method_10550("EverlastingAge");
   }
}
