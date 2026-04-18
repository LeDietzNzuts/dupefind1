package vectorwing.farmersdelight.common.block;

import net.minecraft.class_10;
import net.minecraft.class_1269;
import net.minecraft.class_1657;
import net.minecraft.class_1750;
import net.minecraft.class_1799;
import net.minecraft.class_1922;
import net.minecraft.class_1936;
import net.minecraft.class_1937;
import net.minecraft.class_2246;
import net.minecraft.class_2248;
import net.minecraft.class_2310;
import net.minecraft.class_2338;
import net.minecraft.class_2350;
import net.minecraft.class_2389;
import net.minecraft.class_259;
import net.minecraft.class_265;
import net.minecraft.class_2680;
import net.minecraft.class_2746;
import net.minecraft.class_2769;
import net.minecraft.class_3612;
import net.minecraft.class_3709;
import net.minecraft.class_3726;
import net.minecraft.class_3965;
import net.minecraft.class_2338.class_2339;
import net.minecraft.class_2689.class_2690;
import net.minecraft.class_4970.class_2251;
import vectorwing.farmersdelight.common.Configuration;
import vectorwing.farmersdelight.common.registry.ModBlocks;

public class RopeBlock extends class_2389 {
   public static final class_2746 TIED_TO_BELL = class_2746.method_11825("tied_to_bell");
   protected static final class_265 LOWER_SUPPORT_AABB = class_2248.method_9541(7.0, 0.0, 7.0, 9.0, 1.0, 9.0);

   public RopeBlock(class_2251 properties) {
      super(properties);
      this.method_9590(
         (class_2680)((class_2680)((class_2680)((class_2680)((class_2680)((class_2680)((class_2680)this.field_10647.method_11664())
                           .method_11657(class_2310.field_10905, false))
                        .method_11657(class_2310.field_10904, false))
                     .method_11657(class_2310.field_10907, false))
                  .method_11657(class_2310.field_10903, false))
               .method_11657(TIED_TO_BELL, false))
            .method_11657(class_2310.field_10900, false)
      );
   }

   public boolean method_9516(class_2680 state, class_10 type) {
      return true;
   }

   public class_2680 method_9605(class_1750 context) {
      class_1922 world = context.method_8045();
      class_2338 posAbove = context.method_8037().method_10084();
      class_2680 state = super.method_9605(context);
      return state != null ? (class_2680)state.method_11657(TIED_TO_BELL, world.method_8320(posAbove).method_26204() == class_2246.field_16332) : null;
   }

   public class_1269 method_55766(class_2680 state, class_1937 level, class_2338 pos, class_1657 player, class_3965 hit) {
      if (Configuration.ENABLE_ROPE_REELING.get() && player.method_21823()) {
         if (player.method_31549().field_7476 && (player.method_31549().field_7477 || player.method_31548().method_7394(new class_1799(this.method_8389())))) {
            class_2339 reelingPos = pos.method_25503().method_10098(class_2350.field_11033);
            int minBuildHeight = level.method_31607();

            while (reelingPos.method_10264() >= minBuildHeight) {
               class_2680 blockStateBelow = level.method_8320(reelingPos);
               if (!blockStateBelow.method_27852(this)) {
                  reelingPos.method_10098(class_2350.field_11036);
                  level.method_8651(reelingPos, false, player);
                  return class_1269.method_29236(level.field_9236);
               }

               reelingPos.method_10098(class_2350.field_11033);
            }
         }
      } else {
         class_2339 bellRingingPos = pos.method_25503().method_10098(class_2350.field_11036);

         for (int i = 0; i < 24; i++) {
            class_2680 blockStateAbove = level.method_8320(bellRingingPos);
            class_2248 blockAbove = blockStateAbove.method_26204();
            if (blockAbove == class_2246.field_16332) {
               ((class_3709)blockAbove).method_33600(level, bellRingingPos, ((class_2350)blockStateAbove.method_11654(class_3709.field_16324)).method_10170());
               return class_1269.field_5812;
            }

            if (blockAbove != ModBlocks.ROPE.get()) {
               return class_1269.field_5811;
            }

            bellRingingPos.method_10098(class_2350.field_11036);
         }
      }

      return class_1269.field_5811;
   }

   public class_265 method_9549(class_2680 state, class_1922 level, class_2338 pos, class_3726 context) {
      return class_259.method_1073();
   }

   public class_265 method_25959(class_2680 pState, class_1922 pReader, class_2338 pPos) {
      return LOWER_SUPPORT_AABB;
   }

   public boolean method_9616(class_2680 state, class_1750 useContext) {
      return useContext.method_8041().method_7909() == this.method_8389();
   }

   public class_2680 method_9559(class_2680 state, class_2350 facing, class_2680 facingState, class_1936 level, class_2338 currentPos, class_2338 facingPos) {
      if ((Boolean)state.method_11654(class_2310.field_10900)) {
         level.method_39281(currentPos, class_3612.field_15910, class_3612.field_15910.method_15789(level));
      }

      boolean tiedToBell = (Boolean)state.method_11654(TIED_TO_BELL);
      if (facing == class_2350.field_11036) {
         tiedToBell = level.method_8320(facingPos).method_26204() == class_2246.field_16332;
      }

      return facing.method_10166().method_10179()
         ? (class_2680)((class_2680)state.method_11657(TIED_TO_BELL, tiedToBell))
            .method_11657(
               (class_2769)class_2310.field_10902.get(facing),
               this.method_10281(facingState, facingState.method_26206(level, facingPos, facing.method_10153()))
            )
         : super.method_9559((class_2680)state.method_11657(TIED_TO_BELL, tiedToBell), facing, facingState, level, currentPos, facingPos);
   }

   protected void method_9515(class_2690<class_2248, class_2680> builder) {
      builder.method_11667(
         new class_2769[]{class_2310.field_10905, class_2310.field_10907, class_2310.field_10903, class_2310.field_10904, class_2310.field_10900, TIED_TO_BELL}
      );
   }
}
