package io.github.suel_ki.beautify.common.block;

import io.github.suel_ki.beautify.particle.ParticleInit;
import java.util.List;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.class_124;
import net.minecraft.class_1268;
import net.minecraft.class_1657;
import net.minecraft.class_1799;
import net.minecraft.class_1802;
import net.minecraft.class_1836;
import net.minecraft.class_1937;
import net.minecraft.class_2248;
import net.minecraft.class_2338;
import net.minecraft.class_2561;
import net.minecraft.class_2680;
import net.minecraft.class_2758;
import net.minecraft.class_2769;
import net.minecraft.class_3417;
import net.minecraft.class_3419;
import net.minecraft.class_3749;
import net.minecraft.class_3965;
import net.minecraft.class_437;
import net.minecraft.class_5819;
import net.minecraft.class_9062;
import net.minecraft.class_1792.class_9635;
import net.minecraft.class_2689.class_2690;
import net.minecraft.class_4970.class_2251;

public class LampJar extends class_3749 {
   private static final int maxLevel = 15;
   public static final class_2758 FILL_LEVEL = class_2758.method_11867("fill_level", 0, 15);

   public LampJar(class_2251 p_153465_) {
      super(p_153465_);
      this.method_9590((class_2680)this.method_9564().method_11657(FILL_LEVEL, 0));
   }

   public class_9062 method_55765(class_1799 stack, class_2680 state, class_1937 level, class_2338 pos, class_1657 player, class_1268 hand, class_3965 result) {
      if (!level.method_8608()) {
         class_1799 playerStack = player.method_5998(hand);
         int increase = 5;
         int currentLevel = (Integer)state.method_11654(FILL_LEVEL);
         if (playerStack.method_7960() && currentLevel > 0) {
            player.method_6122(hand, new class_1799(class_1802.field_8601, currentLevel / 5));
            level.method_8652(pos, (class_2680)state.method_11657(FILL_LEVEL, 0), 3);
            level.method_8396(null, pos, class_3417.field_26942, class_3419.field_15245, 0.5F, 0.5F);
            return class_9062.field_47728;
         }

         if (playerStack.method_31574(class_1802.field_8601) && currentLevel + 5 <= 15) {
            playerStack.method_7934(1);
            level.method_8652(pos, (class_2680)state.method_11657(FILL_LEVEL, currentLevel + 5), 3);
            level.method_8396(null, pos, class_3417.field_26982, class_3419.field_15245, 0.5F, 0.5F);
            return class_9062.field_47728;
         }
      }

      return class_9062.field_47728;
   }

   protected void method_9515(class_2690<class_2248, class_2680> builder) {
      super.method_9515(builder);
      builder.method_11667(new class_2769[]{FILL_LEVEL});
   }

   @Environment(EnvType.CLIENT)
   public void method_9496(class_2680 state, class_1937 level, class_2338 pos, class_5819 rand) {
      int particleProbability = 5;
      double posX = pos.method_10263() + 0.35 + rand.method_43058() / 3.5;
      double posY = pos.method_10264() + 0.1 + rand.method_43058() / 3.5;
      double posZ = pos.method_10260() + 0.35 + rand.method_43058() / 3.5;
      if ((Integer)state.method_11654(FILL_LEVEL) >= 5 && (Integer)state.method_11654(FILL_LEVEL) < 10) {
         if (rand.method_43056()) {
            level.method_8406(ParticleInit.GLOWESSENCE_PARTICLES, posX, posY, posZ, randomDir(rand), 0.01, randomDir(rand));
         }
      } else if ((Integer)state.method_11654(FILL_LEVEL) >= 10 && (Integer)state.method_11654(FILL_LEVEL) < 15) {
         if (rand.method_43056()) {
            level.method_8406(ParticleInit.GLOWESSENCE_PARTICLES, posX, posY, posZ, randomDir(rand), 0.01, randomDir(rand));
         }
      } else if ((Integer)state.method_11654(FILL_LEVEL) == 15) {
         if (rand.method_43056()) {
            level.method_8406(ParticleInit.GLOWESSENCE_PARTICLES, posX, posY, posZ, randomDir(rand), 0.01, randomDir(rand));
         }

         posX = pos.method_10263() + 0.35 + rand.method_43058() / 3.5;
         posY = pos.method_10264() + 0.1 + rand.method_43058() / 3.5;
         posZ = pos.method_10260() + 0.35 + rand.method_43058() / 3.5;
         level.method_8406(ParticleInit.GLOWESSENCE_PARTICLES, posX, posY, posZ, randomDir(rand), 0.01, randomDir(rand));
      }
   }

   private static double randomDir(class_5819 rand) {
      return (rand.method_39332(0, 2) - 1) * rand.method_43057() / 34.0F;
   }

   public void method_9568(class_1799 stack, class_9635 tooltipContext, List<class_2561> component, class_1836 flag) {
      if (!class_437.method_25442()) {
         component.add(class_2561.method_43471("tooltip.beautify.shift").method_27692(class_124.field_1054));
      }

      if (class_437.method_25442()) {
         component.add(class_2561.method_43471("tooltip.beautify.lamp_jar.1").method_27692(class_124.field_1080));
         component.add(class_2561.method_43471("tooltip.beautify.lamp_jar.2").method_27692(class_124.field_1080));
         component.add(class_2561.method_43471("tooltip.beautify.lamp_jar.3").method_27692(class_124.field_1080));
      }

      super.method_9568(stack, tooltipContext, component, flag);
   }
}
