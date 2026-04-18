package vectorwing.farmersdelight.common.block;

import net.fabricmc.fabric.api.registry.FlammableBlockRegistry;
import net.minecraft.class_1291;
import net.minecraft.class_1750;
import net.minecraft.class_1922;
import net.minecraft.class_1937;
import net.minecraft.class_2248;
import net.minecraft.class_2256;
import net.minecraft.class_2338;
import net.minecraft.class_2350;
import net.minecraft.class_2356;
import net.minecraft.class_265;
import net.minecraft.class_2680;
import net.minecraft.class_3218;
import net.minecraft.class_3481;
import net.minecraft.class_3726;
import net.minecraft.class_4538;
import net.minecraft.class_5819;
import net.minecraft.class_6880;
import net.minecraft.class_4970.class_2251;

public class WildCropBlock extends class_2356 implements class_2256 {
   protected static final class_265 SHAPE = class_2248.method_9541(2.0, 0.0, 2.0, 14.0, 13.0, 14.0);

   public WildCropBlock(class_6880<class_1291> suspiciousStewEffect, int effectDuration, class_2251 properties) {
      super(suspiciousStewEffect, effectDuration, properties);
      FlammableBlockRegistry.getDefaultInstance().add(this, this.getFlammability(null, null, null, null), this.getFireSpreadSpeed(null, null, null, null));
   }

   public class_265 method_9530(class_2680 state, class_1922 level, class_2338 pos, class_3726 context) {
      return SHAPE;
   }

   public boolean method_9695(class_2680 state, class_1922 level, class_2338 pos) {
      return state.method_26164(class_3481.field_29822) || state.method_26164(class_3481.field_15466);
   }

   public boolean method_9616(class_2680 state, class_1750 useContext) {
      return false;
   }

   public int getFireSpreadSpeed(class_2680 state, class_1922 world, class_2338 pos, class_2350 face) {
      return 60;
   }

   public int getFlammability(class_2680 state, class_1922 world, class_2338 pos, class_2350 face) {
      return 100;
   }

   public boolean method_9651(class_4538 level, class_2338 pos, class_2680 state) {
      return true;
   }

   public boolean method_9650(class_1937 level, class_5819 rand, class_2338 pos, class_2680 state) {
      return rand.method_43057() < 0.8F;
   }

   public void method_9652(class_3218 level, class_5819 random, class_2338 pos, class_2680 state) {
      int wildCropLimit = 10;

      for (class_2338 nearbyPos : class_2338.method_10097(pos.method_10069(-4, -1, -4), pos.method_10069(4, 1, 4))) {
         if (level.method_8320(nearbyPos).method_27852(this)) {
            if (--wildCropLimit <= 0) {
               return;
            }
         }
      }

      class_2338 randomPos = pos.method_10069(random.method_43048(3) - 1, random.method_43048(2) - random.method_43048(2), random.method_43048(3) - 1);

      for (int k = 0; k < 4; k++) {
         if (level.method_22347(randomPos) && state.method_26184(level, randomPos)) {
            pos = randomPos;
         }

         randomPos = pos.method_10069(random.method_43048(3) - 1, random.method_43048(2) - random.method_43048(2), random.method_43048(3) - 1);
      }

      if (level.method_22347(randomPos) && state.method_26184(level, randomPos)) {
         level.method_8652(randomPos, state, 2);
      }
   }
}
