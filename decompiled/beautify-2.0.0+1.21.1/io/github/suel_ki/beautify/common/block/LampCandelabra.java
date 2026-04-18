package io.github.suel_ki.beautify.common.block;

import java.util.List;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.class_124;
import net.minecraft.class_1268;
import net.minecraft.class_1309;
import net.minecraft.class_1657;
import net.minecraft.class_1676;
import net.minecraft.class_1750;
import net.minecraft.class_1799;
import net.minecraft.class_1802;
import net.minecraft.class_1836;
import net.minecraft.class_1922;
import net.minecraft.class_1936;
import net.minecraft.class_1937;
import net.minecraft.class_2248;
import net.minecraft.class_2338;
import net.minecraft.class_2350;
import net.minecraft.class_2394;
import net.minecraft.class_2398;
import net.minecraft.class_247;
import net.minecraft.class_2561;
import net.minecraft.class_259;
import net.minecraft.class_265;
import net.minecraft.class_2680;
import net.minecraft.class_2741;
import net.minecraft.class_2746;
import net.minecraft.class_2753;
import net.minecraft.class_2769;
import net.minecraft.class_3417;
import net.minecraft.class_3419;
import net.minecraft.class_3610;
import net.minecraft.class_3612;
import net.minecraft.class_3726;
import net.minecraft.class_3749;
import net.minecraft.class_3965;
import net.minecraft.class_437;
import net.minecraft.class_5712;
import net.minecraft.class_5819;
import net.minecraft.class_9062;
import net.minecraft.class_1792.class_9635;
import net.minecraft.class_2350.class_2351;
import net.minecraft.class_2689.class_2690;
import net.minecraft.class_4970.class_2251;

public class LampCandelabra extends class_3749 {
   public static final class_2746 ON = class_2746.method_11825("on");
   public static final class_2753 FACING = class_2741.field_12481;
   private static final class_265 SHAPE_HANGING = class_259.method_1072(
      class_2248.method_9541(0.0, 2.0, 6.5, 16.0, 16.0, 9.5), class_2248.method_9541(6.5, 2.0, 0.0, 9.5, 16.0, 16.0), class_247.field_1366
   );
   private static final class_265 SHAPE_STANDING = class_2248.method_9541(5.0, 0.0, 5.0, 11.0, 14.0, 11.0);

   public LampCandelabra(class_2251 properties) {
      super(properties);
      this.method_9590((class_2680)((class_2680)this.method_9564().method_11657(ON, false)).method_11657(FACING, class_2350.field_11043));
   }

   public boolean isOn(class_2680 state) {
      return (Boolean)state.method_11654(ON);
   }

   private static void setOn(class_1936 level, class_2680 state, class_2338 pos, boolean on) {
      level.method_8652(pos, (class_2680)state.method_11657(ON, on), 11);
      level.method_33596(null, class_5712.field_28733, pos);
      if (on) {
         level.method_8396(null, pos, class_3417.field_15145, class_3419.field_15245, 1.0F, 1.0F);
      } else {
         level.method_8396(null, pos, class_3417.field_26955, class_3419.field_15245, 1.0F, 1.0F);
      }
   }

   private static void extinguish(class_2680 state, class_1936 level, class_2338 pos) {
      setOn(level, state, pos, false);
      addParticle(class_2398.field_11251, state, level, pos);
   }

   private static void addParticle(class_2394 particle, class_2680 state, class_1936 level, class_2338 pos) {
      double d0 = pos.method_10263() + 0.5;
      double d1 = pos.method_10264() + 1.0;
      double d2 = pos.method_10260() + 0.5;
      if ((Boolean)state.method_11654(field_16545)) {
         level.method_8406(particle, d0 + 0.4, d1 - 0.15, d2, 0.0, 0.0, 0.0);
         level.method_8406(particle, d0 - 0.4, d1 - 0.15, d2, 0.0, 0.0, 0.0);
         level.method_8406(particle, d0, d1 - 0.15, d2 + 0.4, 0.0, 0.0, 0.0);
         level.method_8406(particle, d0, d1 - 0.15, d2 - 0.4, 0.0, 0.0, 0.0);
      } else if (state.method_11654(FACING) != class_2350.field_11034 && state.method_11654(FACING) != class_2350.field_11039) {
         level.method_8406(particle, d0, d1, d2, 0.0, 0.0, 0.0);
         level.method_8406(particle, d0 + 0.35, d1 + 0.05, d2, 0.0, 0.0, 0.0);
         level.method_8406(particle, d0 - 0.35, d1 + 0.05, d2, 0.0, 0.0, 0.0);
      } else {
         level.method_8406(particle, d0, d1, d2, 0.0, 0.0, 0.0);
         level.method_8406(particle, d0, d1 + 0.05, d2 + 0.35, 0.0, 0.0, 0.0);
         level.method_8406(particle, d0, d1 + 0.05, d2 - 0.35, 0.0, 0.0, 0.0);
      }
   }

   public class_2680 method_9605(class_1750 context) {
      class_3610 fluidstate = context.method_8045().method_8316(context.method_8037());

      for (class_2350 direction : context.method_7718()) {
         if (direction.method_10166() == class_2351.field_11052) {
            class_2680 blockstate = (class_2680)this.method_9564().method_11657(field_16545, direction == class_2350.field_11036);
            if (blockstate.method_26184(context.method_8045(), context.method_8037())) {
               return (class_2680)((class_2680)((class_2680)blockstate.method_11657(field_26441, fluidstate.method_15772() == class_3612.field_15910))
                     .method_11657(FACING, context.method_8042().method_10153()))
                  .method_11657(ON, false);
            }
         }
      }

      return null;
   }

   public class_265 method_9530(class_2680 state, class_1922 level, class_2338 pos, class_3726 context) {
      return state.method_11654(field_16545) ? SHAPE_HANGING : SHAPE_STANDING;
   }

   public void method_19286(class_1937 level, class_2680 state, class_3965 hitResult, class_1676 projectile) {
      if (!level.field_9236 && projectile.method_5809() && !this.isOn(state) && !(Boolean)state.method_11654(field_26441)) {
         setOn(level, state, hitResult.method_17777(), true);
      }
   }

   public class_9062 method_55765(class_1799 stack, class_2680 state, class_1937 level, class_2338 pos, class_1657 player, class_1268 hand, class_3965 result) {
      if (!level.method_8608()) {
         class_1799 playerStack = player.method_5998(hand);
         if ((Boolean)state.method_11654(field_26441)) {
            return class_9062.field_47731;
         }

         if (this.isOn(state) && !player.method_5715() && playerStack.method_7960()) {
            extinguish(state, level, pos);
            return class_9062.field_47728;
         }

         if (!this.isOn(state) && playerStack.method_31574(class_1802.field_8884)) {
            setOn(level, state, pos, true);
            playerStack.method_7970(1, player, class_1309.method_56079(hand));
            return class_9062.field_47728;
         }
      }

      return class_9062.field_47728;
   }

   @Environment(EnvType.CLIENT)
   public void method_9496(class_2680 state, class_1937 level, class_2338 pos, class_5819 rand) {
      if (this.isOn(state)) {
         addParticle(class_2398.field_27783, state, level, pos);
      }
   }

   protected void method_9515(class_2690<class_2248, class_2680> builder) {
      super.method_9515(builder);
      builder.method_11667(new class_2769[]{FACING, ON});
   }

   public boolean method_10311(class_1936 level, class_2338 pos, class_2680 state, class_3610 fluidState) {
      if (!(Boolean)state.method_11654(field_26441) && fluidState.method_15772() == class_3612.field_15910) {
         class_2680 state2 = (class_2680)state.method_11657(field_26441, true);
         if (this.isOn(state)) {
            extinguish(state2, level, pos);
         } else {
            level.method_8652(pos, state2, 3);
         }

         level.method_39281(pos, fluidState.method_15772(), fluidState.method_15772().method_15789(level));
         return true;
      } else {
         return false;
      }
   }

   public void method_9568(class_1799 stack, class_9635 tooltipContext, List<class_2561> component, class_1836 flag) {
      if (!class_437.method_25442()) {
         component.add(class_2561.method_43471("tooltip.beautify.shift").method_27692(class_124.field_1054));
      }

      if (class_437.method_25442()) {
         component.add(class_2561.method_43471("tooltip.beautify.candelabra.1").method_27692(class_124.field_1080));
         component.add(class_2561.method_43471("tooltip.beautify.candelabra.2").method_27692(class_124.field_1080));
         component.add(class_2561.method_43471("tooltip.beautify.candelabra.3").method_27692(class_124.field_1080));
      }

      super.method_9568(stack, tooltipContext, component, flag);
   }
}
