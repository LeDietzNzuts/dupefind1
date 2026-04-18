package ewewukek.musketmod;

import com.mojang.serialization.Codec;
import net.minecraft.class_1268;
import net.minecraft.class_1271;
import net.minecraft.class_1304;
import net.minecraft.class_1306;
import net.minecraft.class_1309;
import net.minecraft.class_1657;
import net.minecraft.class_1792;
import net.minecraft.class_1799;
import net.minecraft.class_1839;
import net.minecraft.class_1893;
import net.minecraft.class_1937;
import net.minecraft.class_2338;
import net.minecraft.class_2398;
import net.minecraft.class_243;
import net.minecraft.class_2561;
import net.minecraft.class_266;
import net.minecraft.class_2680;
import net.minecraft.class_269;
import net.minecraft.class_274;
import net.minecraft.class_3218;
import net.minecraft.class_3414;
import net.minecraft.class_3486;
import net.minecraft.class_3532;
import net.minecraft.class_5819;
import net.minecraft.class_9014;
import net.minecraft.class_9135;
import net.minecraft.class_9331;
import net.minecraft.class_1792.class_1793;
import net.minecraft.class_274.class_275;
import net.minecraft.class_9331.class_9332;
import org.apache.commons.lang3.tuple.Pair;
import org.jetbrains.annotations.Nullable;

public abstract class GunItem extends class_1792 {
   public static final class_9331<Boolean> LOADED = new class_9332().method_57881(Codec.BOOL).method_57882(class_9135.field_48547).method_57880();
   public static final class_9331<Byte> LOADING_STAGE = new class_9332().method_57881(Codec.BYTE).method_57882(class_9135.field_48548).method_57880();
   public static class_1799 activeMainHandStack;
   public static class_1799 activeOffhandStack;

   public GunItem(class_1793 properties) {
      super(properties);
   }

   public abstract float bulletStdDev();

   public abstract float bulletSpeed();

   public abstract float damage();

   public abstract class_3414 fireSound(class_1799 var1);

   public int pelletCount() {
      return 1;
   }

   public boolean twoHanded() {
      return true;
   }

   public float bulletDropReduction() {
      return 0.0F;
   }

   public int hitDurabilityDamage() {
      return 1;
   }

   @Deprecated
   public class_3414 fireSound() {
      return this.fireSound(class_1799.field_8037);
   }

   public static boolean canUse(class_1309 entity) {
      boolean creative = entity instanceof class_1657 player && player.method_31549().field_7477;
      return creative || !entity.method_5777(class_3486.field_15517) && !entity.method_5777(class_3486.field_15518);
   }

   public boolean canUseFrom(class_1309 entity, class_1268 hand) {
      if (hand == class_1268.field_5808) {
         return true;
      } else if (this.twoHanded()) {
         return false;
      } else {
         class_1799 stack = entity.method_6047();
         return !stack.method_7960() && stack.method_7909() instanceof GunItem gun ? !gun.twoHanded() : true;
      }
   }

   public static boolean isInHand(class_1309 entity, class_1268 hand) {
      class_1799 stack = entity.method_5998(hand);
      if (stack.method_7960()) {
         return false;
      } else {
         return stack.method_7909() instanceof GunItem gun ? gun.canUseFrom(entity, hand) : false;
      }
   }

   public static boolean isHoldingGun(class_1309 entity) {
      return getHoldingHand(entity) != null;
   }

   @Nullable
   public static class_1268 getHoldingHand(class_1309 entity) {
      if (isInHand(entity, class_1268.field_5808)) {
         return class_1268.field_5808;
      } else {
         return isInHand(entity, class_1268.field_5810) ? class_1268.field_5810 : null;
      }
   }

   public class_243 smokeOffsetFor(class_1309 entity, class_1268 hand) {
      class_1306 arm = hand == class_1268.field_5808 ? entity.method_6068() : entity.method_6068().method_5928();
      return this.smokeOffsetFor(entity, arm);
   }

   public class_243 smokeOffsetFor(class_1309 entity, class_1306 arm) {
      boolean isRightHand = arm == class_1306.field_6183;
      class_243 side = class_243.method_1030(0.0F, entity.method_36454() + (isRightHand ? 90 : -90));
      class_243 down = class_243.method_1030(entity.method_36455() + 90.0F, entity.method_36454());
      return side.method_1019(down).method_1021(0.15);
   }

   public static boolean hasFlame(class_1799 stack) {
      return VanillaHelper.getEnchantmentLevel(stack, class_1893.field_9126) > 0;
   }

   public static boolean hasInfinity(class_1799 stack) {
      return VanillaHelper.getEnchantmentLevel(stack, class_1893.field_9125) > 0;
   }

   public static int getPowerLevel(class_1799 stack) {
      return VanillaHelper.getEnchantmentLevel(stack, class_1893.field_9103);
   }

   public static int getQuickChargeLevel(class_1799 stack) {
      return VanillaHelper.getEnchantmentLevel(stack, class_1893.field_9098);
   }

   public static Pair<Integer, Integer> getLoadingDuration(class_1799 stack) {
      int level = getQuickChargeLevel(stack);
      int stages = Config.loadingStages;
      float total = stages * Config.loadingStageDuration;
      float reduction = level * Config.reductionPerQuickChargeLevel;
      float duration = (total - reduction) / stages;
      if (duration < 0.25F) {
         duration = 0.25F;
      }

      if (level == 3) {
         stages--;
      }

      return Pair.of(stages, (int)(20.0F * duration));
   }

   public class_1271<class_1799> method_7836(class_1937 level, class_1657 player, class_1268 hand) {
      class_1799 stack = player.method_5998(hand);
      if (!canUse(player) || !this.canUseFrom(player, hand)) {
         return class_1271.method_22430(stack);
      } else if (isLoaded(stack)) {
         if (!level.field_9236) {
            class_243 direction = class_243.method_1030(player.method_36455(), player.method_36454());
            this.fire(player, stack, direction, this.smokeOffsetFor(player, hand));
         }

         player.method_5783(this.fireSound(stack), 3.5F, 1.0F);
         setLoaded(stack, false);
         stack.method_7970(1, player, class_1657.method_56079(hand));
         player.method_6075();
         if (level.field_9236) {
            setActiveStack(hand, stack);
         }

         return class_1271.method_22428(stack);
      } else {
         if (hand == class_1268.field_5808) {
            class_1799 offhandStack = player.method_6079();
            if (offhandStack.method_7909() instanceof GunItem offhandGun && isLoaded(offhandStack) && offhandGun.canUseFrom(player, class_1268.field_5810)) {
               return class_1271.method_22430(stack);
            }
         }

         if (getLoadingStage(stack) == 0) {
            if (!checkAmmo(player, stack)) {
               return class_1271.method_22431(stack);
            }

            setLoadingStage(stack, 1);
         } else {
            int loadingStages = (Integer)getLoadingDuration(stack).getLeft();
            if (getLoadingStage(stack) == loadingStages) {
               setLoadingStage(stack, loadingStages + 1);
            }
         }

         player.method_6019(hand);
         return class_1271.method_22428(stack);
      }
   }

   public class_1839 method_7853(class_1799 stack) {
      return class_1839.field_8947;
   }

   public static class_243 addSpread(class_243 direction, class_5819 random, float spreadStdDev) {
      float gaussian = Math.abs((float)random.method_43059());
      if (gaussian > 4.0F) {
         gaussian = 4.0F;
      }

      float error = (float)Math.toRadians(spreadStdDev) * gaussian;
      return applyError(direction, random, error);
   }

   public static class_243 addUniformSpread(class_243 direction, class_5819 random, float spread) {
      float error = (float)Math.toRadians(spread) * random.method_43057();
      return applyError(direction, random, error);
   }

   public static class_243 applyError(class_243 direction, class_5819 random, float coneAngle) {
      class_243 n1;
      class_243 n2;
      if (Math.abs(direction.field_1352) < 1.0E-5 && Math.abs(direction.field_1350) < 1.0E-5) {
         n1 = new class_243(1.0, 0.0, 0.0);
         n2 = new class_243(0.0, 0.0, 1.0);
      } else {
         n1 = new class_243(-direction.field_1350, 0.0, direction.field_1352).method_1029();
         n2 = direction.method_1036(n1);
      }

      float angle = (float) (Math.PI * 2) * random.method_43057();
      return direction.method_1021(class_3532.method_15362(coneAngle))
         .method_1019(n1.method_1021(class_3532.method_15374(coneAngle) * class_3532.method_15374(angle)))
         .method_1019(n2.method_1021(class_3532.method_15374(coneAngle) * class_3532.method_15362(angle)));
   }

   public class_243 aimAt(class_1309 entity, class_1309 target) {
      double dist = entity.method_5739(target);
      double ticks = 20.0 * dist / this.bulletSpeed();
      double bulletDrop = 0.5 * ticks * ticks * 0.05;
      if (this == Items.MUSKET_WITH_SCOPE) {
         bulletDrop *= Config.bulletGravityMultiplier;
      }

      class_243 pos = new class_243(target.method_23317(), 0.5 * (target.method_23320() + target.method_23323(0.5)), target.method_23321());
      return new class_243(
            pos.method_10216() - entity.method_23317(), pos.method_10214() + bulletDrop - entity.method_23320(), pos.method_10215() - entity.method_23321()
         )
         .method_1029();
   }

   public static void mobReload(class_1309 entity, class_1268 hand) {
      if (!entity.method_6115()) {
         class_1937 level = entity.method_37908();
         if (!level.field_9236) {
            class_1799 stack = entity.method_5998(hand);
            if (!isLoaded(stack)) {
               setLoadingStage(stack, 1);
               entity.method_6019(hand);
            }
         }
      }
   }

   public void mobUse(class_1309 entity, class_1268 hand, class_243 direction) {
      class_1799 stack = entity.method_5998(hand);
      class_1306 arm = entity.method_6068();
      if (hand == class_1268.field_5810) {
         arm = arm.method_5928();
      }

      this.mobUse(entity, stack, direction, this.smokeOffsetFor(entity, arm));
   }

   public void mobUse(class_1309 entity, class_1799 stack, class_243 direction, class_243 smokeOffset) {
      class_1937 level = entity.method_37908();
      if (!level.field_9236) {
         if (isLoaded(stack)) {
            this.fire(entity, stack, direction, smokeOffset);
            entity.method_5783(this.fireSound(stack), 3.5F, 1.0F);
            setLoaded(stack, false);
         }
      }
   }

   public static int reloadDuration(class_1799 stack) {
      Pair<Integer, Integer> loadingDuration = getLoadingDuration(stack);
      int loadingStages = (Integer)loadingDuration.getLeft();
      int ticksPerLoadingStage = (Integer)loadingDuration.getRight();
      int loadingStagesRemaining = 1 + loadingStages - getLoadingStage(stack);
      return Math.max(0, loadingStagesRemaining) * ticksPerLoadingStage;
   }

   public static boolean checkAmmo(class_1657 player, class_1799 stack) {
      if (!player.method_31549().field_7477 && !hasInfinity(stack)) {
         class_1799 ammoStack = findAmmo(player);
         return !ammoStack.method_7960();
      } else {
         return true;
      }
   }

   public static void consumeAmmo(class_1657 player, class_1799 stack) {
      if (!player.method_31549().field_7477 && !hasInfinity(stack)) {
         class_1799 ammoStack = findAmmo(player);
         ammoStack.method_7934(1);
         if (ammoStack.method_7960()) {
            player.method_31548().method_7378(ammoStack);
         }
      }
   }

   public void method_7840(class_1799 stack, class_1937 level, class_1309 entity, int ticksLeft) {
      if (isLoaded(stack)) {
         setLoadingStage(stack, 0);
      } else {
         int usingTicks = this.method_7881(stack, entity) - ticksLeft;
         int ticksPerLoadingStage = (Integer)getLoadingDuration(stack).getRight();
         int prevLoadingStage = getLoadingStage(stack);
         int loadingStage = prevLoadingStage + usingTicks / ticksPerLoadingStage;
         if (prevLoadingStage == 1) {
            if (loadingStage == 1) {
               setLoadingStage(stack, 0);
            } else if (!isLoaded(stack) && entity instanceof class_1657 player) {
               consumeAmmo(player, stack);
            }
         }

         setLoadingStage(stack, loadingStage);
      }
   }

   public void method_7852(class_1937 level, class_1309 entity, class_1799 stack, int ticksLeft) {
      Pair<Integer, Integer> loadingDuration = getLoadingDuration(stack);
      int loadingStages = (Integer)loadingDuration.getLeft();
      int ticksPerLoadingStage = (Integer)loadingDuration.getRight();
      int usingTicks = this.method_7881(stack, entity) - ticksLeft;
      int prevLoadingStage = getLoadingStage(stack);
      int loadingStage = prevLoadingStage + usingTicks / ticksPerLoadingStage;
      if (loadingStage < loadingStages && usingTicks == ticksPerLoadingStage / 2) {
         entity.method_5783(Sounds.MUSKET_LOAD_0, 0.8F, 1.0F);
      }

      if (usingTicks > 0 && usingTicks % ticksPerLoadingStage == 0) {
         if (loadingStage < loadingStages) {
            entity.method_5783(Sounds.MUSKET_LOAD_1, 0.8F, 1.0F);
         } else if (loadingStage == loadingStages) {
            entity.method_5783(Sounds.MUSKET_LOAD_2, 0.8F, 1.0F);
         }
      }

      if (level.field_9236 && entity instanceof class_1657) {
         setActiveStack(entity.method_6058(), stack);
      } else {
         if (loadingStage > loadingStages && !isLoaded(stack)) {
            level.method_43128(null, entity.method_23317(), entity.method_23318(), entity.method_23321(), Sounds.MUSKET_READY, entity.method_5634(), 0.8F, 1.0F);
            if (prevLoadingStage == 1 && entity instanceof class_1657 player) {
               consumeAmmo(player, stack);
            }

            setLoaded(stack, true);
         }
      }
   }

   public boolean method_7873(class_1799 stack, class_1309 target, class_1309 entity) {
      stack.method_7970(this.hitDurabilityDamage(), entity, class_1304.field_6173);
      return false;
   }

   public boolean method_7879(class_1799 stack, class_1937 level, class_2680 blockState, class_2338 blockPos, class_1309 entity) {
      if (blockState.method_26214(level, blockPos) != 0.0F) {
         stack.method_7970(this.hitDurabilityDamage(), entity, class_1304.field_6173);
      }

      return false;
   }

   public int method_7881(class_1799 stack, class_1309 entity) {
      return 72000;
   }

   public int method_7837() {
      return 14;
   }

   @Deprecated
   public void fire(class_1309 entity, class_243 direction) {
      class_1799 stack = entity.method_6047();
      class_1268 hand = class_1268.field_5808;
      if (!(stack.method_7909() instanceof GunItem)) {
         stack = entity.method_6079();
         hand = class_1268.field_5810;
      }

      this.fire(entity, stack, direction, this.smokeOffsetFor(entity, hand));
   }

   public void fire(class_1309 entity, class_1799 stack, class_243 direction, class_243 smokeOffset) {
      class_1937 level = entity.method_37908();
      class_243 origin = new class_243(entity.method_23317(), entity.method_23320(), entity.method_23321());
      boolean flame = hasFlame(stack);
      float damage = this.damage() + Config.damagePerPowerLevel * getPowerLevel(stack);

      for (int i = 0; i < this.pelletCount(); i++) {
         BulletEntity bullet = new BulletEntity(level);
         bullet.method_7432(entity);
         bullet.method_33574(origin);
         bullet.setVelocity(this.bulletSpeed(), addSpread(direction, entity.method_59922(), this.bulletStdDev()));
         bullet.damage = damage;
         bullet.setDropReduction(this.bulletDropReduction());
         bullet.setPelletCount(this.pelletCount());
         if (flame) {
            bullet.method_5639(100.0F);
            bullet.method_33572(true);
         }

         level.method_8649(bullet);
      }

      MusketMod.sendSmokeEffect((class_3218)level, origin.method_1019(smokeOffset), direction);
   }

   public static void fireParticles(class_1937 level, class_243 origin, class_243 direction) {
      class_5819 random = class_5819.method_43047();

      for (int i = 0; i < 10; i++) {
         double t = Math.pow(random.method_43057(), 1.5);
         class_243 p = origin.method_1019(direction.method_1021(1.25 + t));
         p = p.method_1019(new class_243(random.method_43057() - 0.5, random.method_43057() - 0.5, random.method_43057() - 0.5).method_1021(0.1));
         class_243 v = direction.method_1021(0.1 * (1.0 - t));
         level.method_8406(class_2398.field_11203, p.field_1352, p.field_1351, p.field_1350, v.field_1352, v.field_1351, v.field_1350);
      }
   }

   public static void increaseGunExperience(class_1657 player) {
      String NAME = "gun_experience";
      class_269 board = player.method_7327();
      class_266 objective = board.method_1170("gun_experience");
      if (objective == null) {
         objective = board.method_1168("gun_experience", class_274.field_1468, class_2561.method_43470("gun_experience"), class_275.field_1472, true, null);
      }

      class_9014 score = board.method_1180(player, objective);
      score.method_55413();
   }

   public static class_1799 getActiveStack(class_1268 hand) {
      return hand == class_1268.field_5808 ? activeMainHandStack : activeOffhandStack;
   }

   public static void setActiveStack(class_1268 hand, class_1799 stack) {
      if (hand == class_1268.field_5808) {
         activeMainHandStack = stack;
      } else {
         activeOffhandStack = stack;
      }
   }

   public static boolean isAmmo(class_1799 stack) {
      return stack.method_7909() == Items.CARTRIDGE;
   }

   public static class_1799 findAmmo(class_1657 player) {
      class_1799 stack = player.method_6118(class_1304.field_6171);
      if (isAmmo(stack)) {
         return stack;
      } else {
         stack = player.method_6118(class_1304.field_6173);
         if (isAmmo(stack)) {
            return stack;
         } else {
            int size = player.method_31548().method_5439();

            for (int i = 0; i < size; i++) {
               stack = player.method_31548().method_5438(i);
               if (isAmmo(stack)) {
                  return stack;
               }
            }

            return class_1799.field_8037;
         }
      }
   }

   public static boolean isReady(class_1799 stack) {
      return isLoaded(stack) && getLoadingStage(stack) == 0;
   }

   public static boolean isLoaded(class_1799 stack) {
      Boolean loaded = (Boolean)stack.method_57824(LOADED);
      return loaded != null && loaded;
   }

   public static void setLoaded(class_1799 stack, boolean loaded) {
      if (loaded) {
         stack.method_57379(LOADED, true);
      } else {
         stack.method_57381(LOADED);
      }
   }

   public static int getLoadingStage(class_1799 stack) {
      Byte loadingStage = (Byte)stack.method_57824(LOADING_STAGE);
      return loadingStage != null ? loadingStage : 0;
   }

   public static void setLoadingStage(class_1799 stack, int loadingStage) {
      if (loadingStage > 0) {
         stack.method_57379(LOADING_STAGE, (byte)loadingStage);
      } else {
         stack.method_57381(LOADING_STAGE);
      }
   }
}
