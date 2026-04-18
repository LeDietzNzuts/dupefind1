package vectorwing.farmersdelight.common.block;

import com.mojang.serialization.MapCodec;
import net.minecraft.class_1657;
import net.minecraft.class_1750;
import net.minecraft.class_1799;
import net.minecraft.class_1922;
import net.minecraft.class_1935;
import net.minecraft.class_1936;
import net.minecraft.class_1937;
import net.minecraft.class_2248;
import net.minecraft.class_2256;
import net.minecraft.class_2261;
import net.minecraft.class_2338;
import net.minecraft.class_2350;
import net.minecraft.class_2402;
import net.minecraft.class_265;
import net.minecraft.class_2680;
import net.minecraft.class_2741;
import net.minecraft.class_2746;
import net.minecraft.class_2758;
import net.minecraft.class_2769;
import net.minecraft.class_3218;
import net.minecraft.class_3481;
import net.minecraft.class_3486;
import net.minecraft.class_3532;
import net.minecraft.class_3610;
import net.minecraft.class_3611;
import net.minecraft.class_3612;
import net.minecraft.class_3726;
import net.minecraft.class_4538;
import net.minecraft.class_5819;
import net.minecraft.class_2689.class_2690;
import net.minecraft.class_4970.class_2251;
import org.jetbrains.annotations.Nullable;
import vectorwing.farmersdelight.common.registry.ModBlocks;
import vectorwing.farmersdelight.common.registry.ModItems;

public class RiceBlock extends class_2261 implements class_2256, class_2402 {
   public static final MapCodec<RiceBlock> CODEC = method_54094(RiceBlock::new);
   public static final class_2758 AGE = class_2741.field_12497;
   public static final class_2746 SUPPORTING = class_2746.method_11825("supporting");
   private static final class_265[] SHAPE_BY_AGE = new class_265[]{
      class_2248.method_9541(3.0, 0.0, 3.0, 13.0, 8.0, 13.0),
      class_2248.method_9541(3.0, 0.0, 3.0, 13.0, 10.0, 13.0),
      class_2248.method_9541(2.0, 0.0, 2.0, 14.0, 12.0, 14.0),
      class_2248.method_9541(1.0, 0.0, 1.0, 15.0, 16.0, 15.0)
   };

   public RiceBlock(class_2251 properties) {
      super(properties);
      this.method_9590((class_2680)((class_2680)this.method_9564().method_11657(AGE, 0)).method_11657(SUPPORTING, false));
   }

   protected MapCodec<? extends class_2261> method_53969() {
      return CODEC;
   }

   public void method_9514(class_2680 state, class_3218 level, class_2338 pos, class_5819 random) {
      super.method_9588(state, level, pos, random);
      if (level.method_22343(pos.method_10069(-1, -1, -1), pos.method_10069(1, 1, 1))) {
         if (level.method_22335(pos.method_10084(), 0) >= 6) {
            int age = this.getAge(state);
            if (age <= this.getMaxAge()) {
               float chance = 10.0F;
               if (random.method_43048((int)(25.0F / chance) + 1) == 0) {
                  if (age == this.getMaxAge()) {
                     RicePaniclesBlock riceUpper = (RicePaniclesBlock)ModBlocks.RICE_CROP_PANICLES.get();
                     if (riceUpper.method_9564().method_26184(level, pos.method_10084()) && level.method_22347(pos.method_10084())) {
                        level.method_8501(pos.method_10084(), riceUpper.method_9564());
                     }
                  } else {
                     level.method_8652(pos, this.withAge(age + 1), 2);
                  }
               }
            }
         }
      }
   }

   public class_265 method_9530(class_2680 state, class_1922 level, class_2338 pos, class_3726 context) {
      return SHAPE_BY_AGE[state.method_11654(this.getAgeProperty())];
   }

   public boolean method_9558(class_2680 state, class_4538 level, class_2338 pos) {
      class_3610 fluid = level.method_8316(pos);
      return super.method_9558(state, level, pos) && fluid.method_15767(class_3486.field_15517) && fluid.method_15761() == 8;
   }

   protected boolean method_9695(class_2680 state, class_1922 level, class_2338 pos) {
      return super.method_9695(state, level, pos) || state.method_26164(class_3481.field_29822);
   }

   public class_2758 getAgeProperty() {
      return AGE;
   }

   protected int getAge(class_2680 state) {
      return (Integer)state.method_11654(this.getAgeProperty());
   }

   public int getMaxAge() {
      return 3;
   }

   public class_1799 method_9574(class_4538 level, class_2338 pos, class_2680 state) {
      return new class_1799((class_1935)ModItems.RICE.get());
   }

   public class_2680 withAge(int age) {
      return (class_2680)this.method_9564().method_11657(this.getAgeProperty(), age);
   }

   protected void method_9515(class_2690<class_2248, class_2680> builder) {
      builder.method_11667(new class_2769[]{AGE, SUPPORTING});
   }

   public class_2680 method_9559(class_2680 stateIn, class_2350 facing, class_2680 facingState, class_1936 level, class_2338 currentPos, class_2338 facingPos) {
      class_2680 state = super.method_9559(stateIn, facing, facingState, level, currentPos, facingPos);
      if (!state.method_26215()) {
         level.method_39281(currentPos, class_3612.field_15910, class_3612.field_15910.method_15789(level));
         if (facing == class_2350.field_11036) {
            return (class_2680)state.method_11657(SUPPORTING, this.isSupportingRiceUpper(facingState));
         }
      }

      return state;
   }

   public boolean isSupportingRiceUpper(class_2680 topState) {
      return topState.method_26204() == ModBlocks.RICE_CROP_PANICLES.get();
   }

   @Nullable
   public class_2680 method_9605(class_1750 context) {
      class_3610 fluid = context.method_8045().method_8316(context.method_8037());
      return fluid.method_15767(class_3486.field_15517) && fluid.method_15761() == 8 ? super.method_9605(context) : null;
   }

   public boolean method_9651(class_4538 level, class_2338 pos, class_2680 state) {
      class_2680 upperState = level.method_8320(pos.method_10084());
      return upperState.method_26204() instanceof RicePaniclesBlock ? !((RicePaniclesBlock)upperState.method_26204()).method_9825(upperState) : true;
   }

   public boolean method_9650(class_1937 level, class_5819 rand, class_2338 pos, class_2680 state) {
      return true;
   }

   protected int getBonemealAgeIncrease(class_1937 level) {
      return class_3532.method_15395(level.field_9229, 1, 4);
   }

   public void method_9652(class_3218 level, class_5819 rand, class_2338 pos, class_2680 state) {
      int ageGrowth = Math.min(this.getAge(state) + this.getBonemealAgeIncrease(level), 7);
      if (ageGrowth <= this.getMaxAge()) {
         level.method_8501(pos, (class_2680)state.method_11657(AGE, ageGrowth));
      } else {
         class_2680 top = level.method_8320(pos.method_10084());
         if (top.method_26204() == ModBlocks.RICE_CROP_PANICLES.get()) {
            class_2256 growable = (class_2256)level.method_8320(pos.method_10084()).method_26204();
            if (growable.method_9651(level, pos.method_10084(), top)) {
               growable.method_9652(level, level.field_9229, pos.method_10084(), top);
            }
         } else {
            RicePaniclesBlock riceUpper = (RicePaniclesBlock)ModBlocks.RICE_CROP_PANICLES.get();
            int remainingGrowth = ageGrowth - this.getMaxAge() - 1;
            if (riceUpper.method_9564().method_26184(level, pos.method_10084()) && level.method_22347(pos.method_10084())) {
               level.method_8501(pos, (class_2680)state.method_11657(AGE, this.getMaxAge()));
               level.method_8652(pos.method_10084(), (class_2680)riceUpper.method_9564().method_11657(RicePaniclesBlock.RICE_AGE, remainingGrowth), 2);
            }
         }
      }
   }

   public class_3610 method_9545(class_2680 state) {
      return class_3612.field_15910.method_15729(false);
   }

   public boolean method_10310(@Nullable class_1657 player, class_1922 level, class_2338 pos, class_2680 state, class_3611 fluidIn) {
      return false;
   }

   public boolean method_10311(class_1936 level, class_2338 pos, class_2680 state, class_3610 fluidStateIn) {
      return false;
   }
}
