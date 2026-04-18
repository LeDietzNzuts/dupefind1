package ewewukek.musketmod.mixin;

import ewewukek.musketmod.Config;
import ewewukek.musketmod.MusketMod;
import ewewukek.musketmod.RangedGunAttackGoal;
import ewewukek.musketmod.VanillaHelper;
import net.minecraft.class_1267;
import net.minecraft.class_1304;
import net.minecraft.class_1308;
import net.minecraft.class_1309;
import net.minecraft.class_1547;
import net.minecraft.class_1799;
import net.minecraft.class_52;
import net.minecraft.class_5321;
import net.minecraft.class_7924;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(class_1547.class)
abstract class AbstractSkeletonMixin {
   private static final class_5321<class_52> LOOT_TABLE = class_5321.method_29179(class_7924.field_50079, MusketMod.resource("skeleton_weapon"));

   @Inject(method = "registerGoals", at = @At("TAIL"))
   private void registerGoals(CallbackInfo ci) {
      class_1547 skeleton = (class_1547)this;
      skeleton.field_6201.method_6277(4, new RangedGunAttackGoal<class_1547>(skeleton) {
         private static final double speedModifier = 1.0;
         private static final float attackRadius = 15.0F;
         private int seeTime;
         private int attackDelay;
         private boolean strafingClockwise;
         private boolean strafingBackwards;
         private int strafingTime = -1;

         public boolean method_6266() {
            return (this.isTargetValid() || !this.mob.method_5942().method_6357()) && this.canUseGun();
         }

         public void method_6269() {
            super.method_6269();
            this.mob.method_19540(true);
         }

         @Override
         public void method_6270() {
            super.method_6270();
            this.seeTime = 0;
            this.attackDelay = 0;
         }

         @Override
         public void method_6268() {
            super.method_6268();
            class_1309 target = this.mob.method_5968();
            if (target != null) {
               boolean canSee = this.mob.method_5985().method_6369(target);
               boolean wasSeeing = this.seeTime > 0;
               if (canSee != wasSeeing) {
                  this.seeTime = 0;
               }

               if (canSee) {
                  this.seeTime++;
               } else {
                  this.seeTime--;
               }

               float dist = this.mob.method_5739(target);
               if (dist < 15.0F && this.seeTime >= 20) {
                  this.mob.method_5942().method_6340();
                  this.strafingTime++;
               } else {
                  this.mob.method_5942().method_6335(target, 1.0);
                  this.strafingTime = -1;
               }

               if (this.strafingTime >= 20) {
                  if (this.mob.method_59922().method_43057() < 0.3F) {
                     this.strafingClockwise = !this.strafingClockwise;
                  }

                  if (this.mob.method_59922().method_43057() < 0.3F) {
                     this.strafingBackwards = !this.strafingBackwards;
                  }

                  this.strafingTime = 0;
               }

               if (this.strafingTime > -1) {
                  if (dist > 11.25F) {
                     this.strafingBackwards = false;
                  } else if (dist < 3.75F) {
                     this.strafingBackwards = true;
                  }

                  this.mob.method_5962().method_6243(this.strafingBackwards ? -0.5F : 0.5F, this.strafingClockwise ? 0.5F : -0.5F);
                  if (this.mob.method_49694() instanceof class_1308 vehicle) {
                     vehicle.method_5951(target, 30.0F, 30.0F);
                  }

                  this.mob.method_5951(target, 30.0F, 30.0F);
               } else {
                  this.mob.method_5988().method_6226(target, 30.0F, 30.0F);
               }

               if (this.seeTime < -60) {
                  this.attackDelay = Math.max(20, this.attackDelay);
               }

               if (this.attackDelay > 0) {
                  this.attackDelay--;
               } else if (this.isReady()) {
                  if (canSee) {
                     boolean isHard = this.mob.method_37908().method_8407() == class_1267.field_5807;
                     this.fire(isHard ? 2.0F : 6.0F);
                     this.attackDelay = 10;
                  }
               } else {
                  this.reload();
               }
            }
         }

         @Override
         public void onReady() {
            boolean isHard = this.mob.method_37908().method_8407() == class_1267.field_5807;
            this.attackDelay = Math.max(isHard ? 20 : 40, this.attackDelay);
         }
      });
   }

   @Inject(method = "populateDefaultEquipmentSlots", at = @At("TAIL"))
   private void populateDefaultEquipmentSlots(CallbackInfo ci) {
      class_1547 skeleton = (class_1547)this;
      if (skeleton.method_37908().method_8407() != class_1267.field_5805 && skeleton.method_59922().method_43057() < Config.musketSkeletonChance) {
         class_1799 weapon = VanillaHelper.getRandomWeapon(skeleton, LOOT_TABLE);
         if (!weapon.method_7960()) {
            skeleton.method_5673(class_1304.field_6173, weapon);
         }
      }
   }
}
