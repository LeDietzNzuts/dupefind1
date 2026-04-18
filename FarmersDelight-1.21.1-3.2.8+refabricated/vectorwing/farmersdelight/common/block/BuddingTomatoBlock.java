package vectorwing.farmersdelight.common.block;

import net.minecraft.class_1936;
import net.minecraft.class_1937;
import net.minecraft.class_2256;
import net.minecraft.class_2338;
import net.minecraft.class_2350;
import net.minecraft.class_2680;
import net.minecraft.class_3218;
import net.minecraft.class_3532;
import net.minecraft.class_4538;
import net.minecraft.class_5819;
import net.minecraft.class_4970.class_2251;
import vectorwing.farmersdelight.common.registry.ModBlocks;

public class BuddingTomatoBlock extends BuddingBushBlock implements class_2256 {
   public BuddingTomatoBlock(class_2251 properties) {
      super(properties);
   }

   public class_2680 method_9559(class_2680 state, class_2350 facing, class_2680 facingState, class_1936 level, class_2338 currentPos, class_2338 facingPos) {
      if ((Integer)state.method_11654(BuddingBushBlock.AGE) == 4) {
         level.method_8652(currentPos, ModBlocks.TOMATO_CROP.get().method_9564(), 3);
      }

      return super.method_9559(state, facing, facingState, level, currentPos, facingPos);
   }

   @Override
   public boolean canGrowPastMaxAge() {
      return true;
   }

   @Override
   public void growPastMaxAge(class_2680 state, class_3218 level, class_2338 pos, class_5819 random) {
      level.method_8501(pos, ModBlocks.TOMATO_CROP.get().method_9564());
   }

   public boolean method_9651(class_4538 level, class_2338 pos, class_2680 state) {
      return true;
   }

   public boolean method_9650(class_1937 level, class_5819 random, class_2338 pos, class_2680 state) {
      return true;
   }

   protected int getBonemealAgeIncrease(class_1937 level) {
      return class_3532.method_15395(level.field_9229, 1, 4);
   }

   public void method_9652(class_3218 level, class_5819 random, class_2338 pos, class_2680 state) {
      int maxAge = this.getMaxAge();
      int ageGrowth = Math.min(this.getAge(state) + this.getBonemealAgeIncrease(level), 7);
      if (ageGrowth <= maxAge) {
         level.method_8501(pos, (class_2680)state.method_11657(AGE, ageGrowth));
      } else {
         int remainingGrowth = ageGrowth - maxAge - 1;
         level.method_8501(pos, (class_2680)ModBlocks.TOMATO_CROP.get().method_9564().method_11657(TomatoVineBlock.VINE_AGE, remainingGrowth));
      }
   }
}
