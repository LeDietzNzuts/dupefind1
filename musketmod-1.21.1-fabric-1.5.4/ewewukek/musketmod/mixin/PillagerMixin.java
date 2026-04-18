package ewewukek.musketmod.mixin;

import ewewukek.musketmod.Config;
import ewewukek.musketmod.GunItem;
import ewewukek.musketmod.MusketMod;
import ewewukek.musketmod.RangedGunAttackGoal;
import ewewukek.musketmod.VanillaHelper;
import net.minecraft.class_1304;
import net.minecraft.class_1309;
import net.minecraft.class_1383;
import net.minecraft.class_1604;
import net.minecraft.class_1799;
import net.minecraft.class_52;
import net.minecraft.class_5321;
import net.minecraft.class_7924;
import net.minecraft.class_1543.class_1544;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(class_1604.class)
abstract class PillagerMixin {
   private static final class_5321<class_52> LOOT_TABLE = class_5321.method_29179(class_7924.field_50079, MusketMod.resource("pillager_weapon"));

   @Inject(method = "registerGoals", at = @At("TAIL"))
   private void registerGoals(CallbackInfo ci) {
      class_1604 pillager = (class_1604)this;
      pillager.field_6201.method_6277(3, new RangedGunAttackGoal<class_1604>(pillager) {
         private static final double speedModifier = 1.0;
         private static final float attackRadius = 8.0F;
         private int seeTime;
         private int attackDelay;
         private int updatePathDelay;

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
               boolean walk = (dist > 8.0F || this.seeTime < 5) && this.attackDelay == 0;
               if (walk) {
                  this.updatePathDelay--;
                  if (this.updatePathDelay <= 0) {
                     boolean canRun = !this.isReady() && !this.isLoading();
                     this.mob.method_5942().method_6335(target, canRun ? 1.0 : 0.5);
                     this.updatePathDelay = class_1383.field_25696.method_35008(this.mob.method_59922());
                  }
               } else {
                  this.updatePathDelay = 0;
                  this.mob.method_5942().method_6340();
               }

               this.mob.method_5988().method_6226(target, 30.0F, 30.0F);
               if (this.isReady()) {
                  if (this.attackDelay > 0) {
                     this.attackDelay--;
                  } else if (canSee) {
                     this.fire(2.0F);
                  }
               } else if (!walk) {
                  this.reload();
               }
            }
         }

         @Override
         public void onReady() {
            this.attackDelay = 20 + this.mob.method_59922().method_43048(20);
         }

         @Override
         public void method_6270() {
            super.method_6270();
            this.seeTime = 0;
         }
      });
   }

   @Inject(method = "populateDefaultEquipmentSlots", at = @At("TAIL"))
   private void populateDefaultEquipmentSlots(CallbackInfo ci) {
      class_1604 pillager = (class_1604)this;
      if (pillager.method_59922().method_43057() < Config.pistolPillagerChance) {
         class_1799 weapon = VanillaHelper.getRandomWeapon(pillager, LOOT_TABLE);
         if (!weapon.method_7960()) {
            pillager.method_5673(class_1304.field_6173, weapon);
         }
      }
   }

   @Inject(method = "getArmPose", at = @At("HEAD"), cancellable = true)
   private void getArmPose(CallbackInfoReturnable<class_1544> ci) {
      class_1604 pillager = (class_1604)this;
      if (GunItem.isHoldingGun(pillager)) {
         ci.setReturnValue(pillager.method_6115() ? class_1544.field_7210 : class_1544.field_7213);
         ci.cancel();
      }
   }
}
