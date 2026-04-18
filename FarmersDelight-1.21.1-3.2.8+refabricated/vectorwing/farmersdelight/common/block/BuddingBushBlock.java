package vectorwing.farmersdelight.common.block;

import com.mojang.serialization.MapCodec;
import net.minecraft.class_1297;
import net.minecraft.class_1584;
import net.minecraft.class_1799;
import net.minecraft.class_1922;
import net.minecraft.class_1928;
import net.minecraft.class_1935;
import net.minecraft.class_1937;
import net.minecraft.class_2248;
import net.minecraft.class_2261;
import net.minecraft.class_2338;
import net.minecraft.class_2344;
import net.minecraft.class_265;
import net.minecraft.class_2680;
import net.minecraft.class_2758;
import net.minecraft.class_2769;
import net.minecraft.class_3218;
import net.minecraft.class_3726;
import net.minecraft.class_4538;
import net.minecraft.class_5819;
import net.minecraft.class_2689.class_2690;
import net.minecraft.class_4970.class_2251;
import vectorwing.farmersdelight.common.registry.ModItems;

public class BuddingBushBlock extends class_2261 {
   public static final MapCodec<BuddingBushBlock> CODEC = method_54094(BuddingBushBlock::new);
   public static final int MAX_AGE = 3;
   public static final class_2758 AGE = class_2758.method_11867("age", 0, 4);
   private static final class_265[] SHAPE_BY_AGE = new class_265[]{
      class_2248.method_9541(0.0, 0.0, 0.0, 16.0, 2.0, 16.0),
      class_2248.method_9541(0.0, 0.0, 0.0, 16.0, 6.0, 16.0),
      class_2248.method_9541(0.0, 0.0, 0.0, 16.0, 10.0, 16.0),
      class_2248.method_9541(0.0, 0.0, 0.0, 16.0, 14.0, 16.0),
      class_2248.method_9541(0.0, 0.0, 0.0, 16.0, 14.0, 16.0)
   };

   public BuddingBushBlock(class_2251 properties) {
      super(properties);
   }

   protected MapCodec<? extends class_2261> method_53969() {
      return CODEC;
   }

   public class_265 method_9530(class_2680 state, class_1922 level, class_2338 pos, class_3726 context) {
      return SHAPE_BY_AGE[state.method_11654(this.getAgeProperty())];
   }

   protected boolean method_9695(class_2680 state, class_1922 level, class_2338 pos) {
      return state.method_26204() instanceof class_2344;
   }

   public class_2758 getAgeProperty() {
      return AGE;
   }

   public int getMaxAge() {
      return 3;
   }

   protected int getAge(class_2680 state) {
      return (Integer)state.method_11654(this.getAgeProperty());
   }

   public class_2680 getStateForAge(int age) {
      return (class_2680)this.method_9564().method_11657(this.getAgeProperty(), age);
   }

   public boolean isMaxAge(class_2680 state) {
      return (Integer)state.method_11654(this.getAgeProperty()) >= this.getMaxAge();
   }

   public boolean method_9542(class_2680 state) {
      return this.canGrowPastMaxAge() || !this.isMaxAge(state);
   }

   public void method_9514(class_2680 state, class_3218 level, class_2338 pos, class_5819 random) {
      if (level.method_22343(pos.method_10069(-1, -1, -1), pos.method_10069(1, 1, 1))) {
         if (level.method_22335(pos, 0) >= 9) {
            int age = this.getAge(state);
            if (age <= this.getMaxAge()) {
               float growthSpeed = getGrowthSpeed(state, level, pos);
               if (random.method_43048((int)(25.0F / growthSpeed) + 1) == 0) {
                  if (this.isMaxAge(state)) {
                     this.growPastMaxAge(state, level, pos, random);
                  } else {
                     level.method_8501(pos, this.getStateForAge(age + 1));
                  }
               }
            }
         }
      }
   }

   public boolean canGrowPastMaxAge() {
      return false;
   }

   public void growPastMaxAge(class_2680 state, class_3218 level, class_2338 pos, class_5819 random) {
   }

   protected static float getGrowthSpeed(class_2680 state, class_1922 level, class_2338 pos) {
      float speed = 1.0F;
      class_2338 posBelow = pos.method_10074();

      for (int posX = -1; posX <= 1; posX++) {
         for (int posZ = -1; posZ <= 1; posZ++) {
            float speedBonus = 1.0F;
            class_2680 stateBelow = level.method_8320(posBelow.method_10069(posX, 0, posZ));
            if (stateBelow.method_28498(class_2344.field_11009) && (Integer)stateBelow.method_11654(class_2344.field_11009) > 0) {
               speedBonus = 3.0F;
            }

            if (posX != 0 || posZ != 0) {
               speedBonus /= 4.0F;
            }

            speed += speedBonus;
         }
      }

      class_2338 posNorth = pos.method_10095();
      class_2338 posSouth = pos.method_10072();
      class_2338 posWest = pos.method_10067();
      class_2338 posEast = pos.method_10078();
      class_2248 block = state.method_26204();
      boolean matchesEastWestRow = level.method_8320(posWest).method_27852(block) || level.method_8320(posEast).method_27852(block);
      boolean matchesNorthSouthRow = level.method_8320(posNorth).method_27852(block) || level.method_8320(posSouth).method_27852(block);
      if (matchesEastWestRow && matchesNorthSouthRow) {
         speed /= 2.0F;
      } else {
         boolean matchesDiagonalRows = level.method_8320(posWest.method_10095()).method_27852(block)
            || level.method_8320(posEast.method_10095()).method_27852(block)
            || level.method_8320(posEast.method_10072()).method_27852(block)
            || level.method_8320(posWest.method_10072()).method_27852(block);
         if (matchesDiagonalRows) {
            speed /= 2.0F;
         }
      }

      return speed;
   }

   public boolean method_9558(class_2680 state, class_4538 level, class_2338 pos) {
      return hasSufficientLight(level, pos) && super.method_9558(state, level, pos);
   }

   public static boolean hasSufficientLight(class_4538 level, class_2338 pos) {
      return level.method_22335(pos, 0) >= 8;
   }

   public void method_9548(class_2680 state, class_1937 level, class_2338 pos, class_1297 entity) {
      if (entity instanceof class_1584 && level instanceof class_3218 serverLevel && serverLevel.method_8450().method_8355(class_1928.field_19388)) {
         level.method_8651(pos, true, entity);
      }

      super.method_9548(state, level, pos, entity);
   }

   protected class_1935 getBaseSeedId() {
      return (class_1935)ModItems.TOMATO_SEEDS.get();
   }

   public class_1799 method_9574(class_4538 level, class_2338 pos, class_2680 state) {
      return new class_1799(this.getBaseSeedId());
   }

   protected void method_9515(class_2690<class_2248, class_2680> builder) {
      builder.method_11667(new class_2769[]{AGE});
   }
}
