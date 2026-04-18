package ewewukek.musketmod;

import net.minecraft.class_1268;
import net.minecraft.class_1306;
import net.minecraft.class_1308;
import net.minecraft.class_1309;
import net.minecraft.class_1604;
import net.minecraft.class_1657;
import net.minecraft.class_1799;
import net.minecraft.class_243;
import net.minecraft.class_2960;
import net.minecraft.class_310;
import net.minecraft.class_3417;
import net.minecraft.class_3532;
import net.minecraft.class_4587;
import net.minecraft.class_4597;
import net.minecraft.class_5272;
import net.minecraft.class_572;
import net.minecraft.class_630;
import net.minecraft.class_638;
import net.minecraft.class_6395;
import net.minecraft.class_742;
import net.minecraft.class_759;
import net.minecraft.class_7833;
import net.minecraft.class_811;
import org.apache.commons.lang3.tuple.Pair;

public class ClientUtilities {
   public static boolean canUseScope;
   public static boolean attackKeyDown;
   public static boolean preventFiring;
   public static boolean disableMainHandEquipAnimation;
   public static boolean disableOffhandEquipAnimation;

   public static void registerItemProperties() {
      registerItemPredicate(MusketMod.resource("loaded"), (stack, level, entity, seed) -> GunItem.isLoaded(stack) ? 1.0F : 0.0F);
      registerItemPredicate(
         MusketMod.resource("loading"),
         (stack, level, entity, seed) -> entity != null && entity.method_6115() && entity.method_5998(entity.method_6058()) == stack ? 1.0F : 0.0F
      );
      registerItemPredicate(MusketMod.resource("aiming"), (stack, level, entity, seed) -> entity != null && shouldAim(entity, stack) ? 1.0F : 0.0F);
   }

   public static void registerItemPredicate(class_2960 location, class_6395 predicate) {
      class_5272.method_27879(Items.MUSKET, location, predicate);
      class_5272.method_27879(Items.MUSKET_WITH_BAYONET, location, predicate);
      class_5272.method_27879(Items.MUSKET_WITH_SCOPE, location, predicate);
      class_5272.method_27879(Items.BLUNDERBUSS, location, predicate);
      class_5272.method_27879(Items.PISTOL, location, predicate);
   }

   public static void handleSmokeEffectPacket(SmokeEffectPacket packet) {
      class_310 instance = class_310.method_1551();
      class_638 level = instance.field_1687;
      class_243 origin = new class_243(packet.origin());
      class_243 direction = new class_243(packet.direction());
      GunItem.fireParticles(level, origin, direction);
   }

   public static void setScoping(class_1657 player, boolean scoping) {
      if (scoping != ScopedMusketItem.isScoping) {
         player.method_5783(scoping ? class_3417.field_26972 : class_3417.field_26973, 1.0F, 1.0F);
         ScopedMusketItem.isScoping = scoping;
      }

      if (!scoping) {
         ScopedMusketItem.recoilTicks = 0;
      }
   }

   public static boolean poseArm(class_1309 entity, class_572<? extends class_1309> model, class_630 arm) {
      if (!entity.method_6115() && !(entity instanceof class_1308 mob && !mob.method_6510())) {
         boolean isRight = arm == model.field_3401;
         class_1268 hand = entity.method_6068() == (isRight ? class_1306.field_6183 : class_1306.field_6182) ? class_1268.field_5808 : class_1268.field_5810;
         class_1799 stack = entity.method_5998(hand);
         if (stack.method_7909() instanceof GunItem && shouldAim(entity, stack, hand)) {
            arm.field_3654 = model.field_3398.field_3654 + 0.1F - (float) (Math.PI / 2);
            if (model.field_3400) {
               arm.field_3654 -= 0.4F;
            }

            arm.field_3675 = model.field_3398.field_3675 + (isRight ? -0.3F : 0.3F);
            return true;
         } else {
            class_1268 hand2 = hand == class_1268.field_5808 ? class_1268.field_5810 : class_1268.field_5808;
            class_1799 stack2 = entity.method_5998(hand2);
            if (stack2.method_7909() instanceof GunItem gun2 && shouldAim(entity, stack2, hand2) && (gun2.twoHanded() || stack == class_1799.field_8037)) {
               arm.field_3654 = model.field_3398.field_3654 - 1.5F;
               if (model.field_3400) {
                  arm.field_3654 -= 0.4F;
               }

               arm.field_3675 = model.field_3398.field_3675 + (isRight ? -0.6F : 0.6F);
               return true;
            } else {
               return false;
            }
         }
      } else {
         return false;
      }
   }

   public static boolean shouldAim(class_1309 entity, class_1799 stack, class_1268 hand) {
      if (entity.method_6115()) {
         return false;
      } else {
         return entity instanceof class_1308 mob
            ? mob.method_6510() || mob instanceof class_1604
            : ((GunItem)stack.method_7909()).canUseFrom(entity, hand) && (GunItem.isLoaded(stack) || Config.alwaysAim);
      }
   }

   public static boolean shouldAim(class_1309 entity, class_1799 stack) {
      class_1268 hand = stack == entity.method_6047() ? class_1268.field_5808 : class_1268.field_5810;
      return shouldAim(entity, stack, hand);
   }

   public static void renderGunInHand(
      class_759 renderer,
      class_742 player,
      class_1268 hand,
      float dt,
      float pitch,
      float swingProgress,
      float equipProgress,
      class_1799 stack,
      class_4587 poseStack,
      class_4597 bufferSource,
      int light
   ) {
      if (!player.method_31550()) {
         class_1306 arm = hand == class_1268.field_5808 ? player.method_6068() : player.method_6068().method_5928();
         boolean isRightHand = arm == class_1306.field_6183;
         float sign = isRightHand ? 1.0F : -1.0F;
         GunItem gun = (GunItem)stack.method_7909();
         if (!gun.canUseFrom(player, hand)) {
            poseStack.method_22903();
            poseStack.method_22904(sign * 0.5, -0.5 - 0.6 * equipProgress, -0.7);
            poseStack.method_22907(class_7833.field_40714.rotationDegrees(70.0F));
            renderer.method_3233(player, stack, isRightHand ? class_811.field_4322 : class_811.field_4321, !isRightHand, poseStack, bufferSource, light);
            poseStack.method_22909();
         } else {
            class_1799 activeStack = GunItem.getActiveStack(hand);
            if (stack == activeStack) {
               setEquipAnimationDisabled(hand, true);
            } else if (activeStack != null && activeStack.method_7909() != gun) {
               setEquipAnimationDisabled(hand, false);
            }

            poseStack.method_22903();
            poseStack.method_22904(sign * 0.15, -0.25, -0.35);
            if (swingProgress > 0.0F) {
               float swingSharp = class_3532.method_15374(class_3532.method_15355(swingProgress) * (float) Math.PI);
               float swingNormal = class_3532.method_15374(swingProgress * (float) Math.PI);
               if (gun == Items.MUSKET_WITH_BAYONET) {
                  poseStack.method_22904(sign * -0.05 * swingNormal, 0.0, 0.05 - 0.3 * swingSharp);
                  poseStack.method_22907(class_7833.field_40716.rotationDegrees(5.0F * swingSharp));
               } else {
                  poseStack.method_22904(sign * 0.05 * (1.0F - swingNormal), 0.05 * (1.0F - swingNormal), 0.05 - 0.4 * swingSharp);
                  poseStack.method_22907(class_7833.field_40714.rotationDegrees(180.0F + sign * 20.0F * (1.0F - swingSharp)));
               }
            } else if (player.method_6115() && player.method_6058() == hand) {
               Pair<Integer, Integer> loadingDuration = GunItem.getLoadingDuration(stack);
               int loadingStages = (Integer)loadingDuration.getLeft();
               int ticksPerLoadingStage = (Integer)loadingDuration.getRight();
               float usingTicks = player.method_6048() + dt - 1.0F;
               int loadingStage = GunItem.getLoadingStage(stack) + (int)(usingTicks / ticksPerLoadingStage);
               int reloadDuration = GunItem.reloadDuration(stack);
               if (reloadDuration > 0 && usingTicks < reloadDuration + 5) {
                  poseStack.method_22904(0.0, -0.3, 0.05);
                  poseStack.method_22907(class_7833.field_40714.rotationDegrees(60.0F));
                  poseStack.method_22907(class_7833.field_40718.rotationDegrees(sign * 10.0F));
                  float t = 0.0F;
                  if (usingTicks >= ticksPerLoadingStage && loadingStage <= loadingStages) {
                     usingTicks %= ticksPerLoadingStage;
                     if (usingTicks < 4.0F) {
                        t = (4.0F - usingTicks) / 4.0F;
                     }
                  }

                  if (usingTicks >= ticksPerLoadingStage - 2 && loadingStage < loadingStages) {
                     t = (usingTicks - ticksPerLoadingStage + 2.0F) / 2.0F;
                     t = class_3532.method_15374((float) (Math.PI / 2) * class_3532.method_15355(t));
                  }

                  poseStack.method_22904(0.0, 0.0, 0.025 * t);
                  if (gun == Items.BLUNDERBUSS) {
                     poseStack.method_22904(0.0, 0.0, -0.06);
                  } else if (gun == Items.PISTOL) {
                     poseStack.method_22904(0.0, 0.0, -0.12);
                  }
               }
            } else if (isEquipAnimationDisabled(hand)) {
               if (equipProgress == 0.0F) {
                  setEquipAnimationDisabled(hand, false);
                  GunItem.setActiveStack(hand, null);
               }
            } else {
               poseStack.method_22904(0.0, -0.6 * equipProgress, 0.0);
            }

            renderer.method_3233(player, stack, isRightHand ? class_811.field_4322 : class_811.field_4321, !isRightHand, poseStack, bufferSource, light);
            poseStack.method_22909();
         }
      }
   }

   public static boolean isEquipAnimationDisabled(class_1268 hand) {
      return hand == class_1268.field_5808 ? disableMainHandEquipAnimation : disableOffhandEquipAnimation;
   }

   public static void setEquipAnimationDisabled(class_1268 hand, boolean disabled) {
      if (hand == class_1268.field_5808) {
         disableMainHandEquipAnimation = disabled;
      } else {
         disableOffhandEquipAnimation = disabled;
      }
   }
}
