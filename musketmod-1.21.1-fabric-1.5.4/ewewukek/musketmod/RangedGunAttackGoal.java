package ewewukek.musketmod;

import java.util.EnumSet;
import net.minecraft.class_1268;
import net.minecraft.class_1352;
import net.minecraft.class_1588;
import net.minecraft.class_1799;
import net.minecraft.class_243;
import net.minecraft.class_1352.class_4134;

public class RangedGunAttackGoal<T extends class_1588> extends class_1352 {
   public final T mob;

   public RangedGunAttackGoal(T mob) {
      this(mob, EnumSet.of(class_4134.field_18405, class_4134.field_18406));
   }

   public RangedGunAttackGoal(T mob, EnumSet<class_4134> flags) {
      this.mob = mob;
      this.method_6265(flags);
   }

   public boolean method_6264() {
      return this.isTargetValid() && this.canUseGun();
   }

   public boolean isTargetValid() {
      return this.mob.method_5968() != null && this.mob.method_5968().method_5805();
   }

   public boolean canUseGun() {
      return GunItem.isHoldingGun(this.mob) && GunItem.canUse(this.mob);
   }

   public boolean isReady() {
      class_1268 hand = GunItem.getHoldingHand(this.mob);
      if (hand == null) {
         return false;
      } else {
         class_1799 stack = this.mob.method_5998(hand);
         return GunItem.isReady(stack);
      }
   }

   public boolean isLoading() {
      return this.mob.method_6115();
   }

   public void onReady() {
   }

   public void reload() {
      class_1268 hand = GunItem.getHoldingHand(this.mob);
      if (hand != null) {
         class_1799 stack = this.mob.method_5998(hand);
         if (!this.isLoading() && !GunItem.isLoaded(stack)) {
            GunItem.mobReload(this.mob, hand);
         }
      }
   }

   public void fire(float spread) {
      class_1268 hand = GunItem.getHoldingHand(this.mob);
      if (hand != null) {
         class_1799 stack = this.mob.method_5998(hand);
         GunItem gun = (GunItem)stack.method_7909();
         class_243 direction = gun.aimAt(this.mob, this.mob.method_5968());
         if (spread > 0.0F) {
            direction = GunItem.addUniformSpread(direction, this.mob.method_59922(), spread);
         }

         gun.mobUse(this.mob, hand, direction);
      }
   }

   public boolean method_38846() {
      return true;
   }

   public void method_6268() {
      if (this.isLoading() && GunItem.isLoaded(this.mob.method_6030())) {
         this.mob.method_6075();
         this.onReady();
      }
   }

   public void method_6270() {
      super.method_6270();
      this.mob.method_19540(false);
      this.mob.method_5980(null);
      if (this.mob.method_6115()) {
         this.mob.method_6021();
      }
   }
}
