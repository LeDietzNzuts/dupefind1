package vectorwing.farmersdelight.common.block;

import net.minecraft.class_1268;
import net.minecraft.class_1269;
import net.minecraft.class_1309;
import net.minecraft.class_1657;
import net.minecraft.class_1799;
import net.minecraft.class_1802;
import net.minecraft.class_1922;
import net.minecraft.class_1935;
import net.minecraft.class_1936;
import net.minecraft.class_1937;
import net.minecraft.class_2248;
import net.minecraft.class_2302;
import net.minecraft.class_2338;
import net.minecraft.class_2350;
import net.minecraft.class_2586;
import net.minecraft.class_265;
import net.minecraft.class_2680;
import net.minecraft.class_2741;
import net.minecraft.class_2746;
import net.minecraft.class_2758;
import net.minecraft.class_2769;
import net.minecraft.class_2960;
import net.minecraft.class_3218;
import net.minecraft.class_3419;
import net.minecraft.class_3481;
import net.minecraft.class_3726;
import net.minecraft.class_3965;
import net.minecraft.class_4538;
import net.minecraft.class_5819;
import net.minecraft.class_7923;
import net.minecraft.class_9062;
import net.minecraft.class_2689.class_2690;
import net.minecraft.class_4970.class_2251;
import org.jetbrains.annotations.Nullable;
import vectorwing.farmersdelight.common.Configuration;
import vectorwing.farmersdelight.common.registry.ModBlocks;
import vectorwing.farmersdelight.common.registry.ModItems;
import vectorwing.farmersdelight.common.registry.ModSounds;
import vectorwing.farmersdelight.common.tag.ModTags;

public class TomatoVineBlock extends class_2302 {
   public static final class_2758 VINE_AGE = class_2741.field_12497;
   public static final class_2746 ROPELOGGED = class_2746.method_11825("ropelogged");
   private static final class_265 SHAPE = class_2248.method_9541(2.0, 0.0, 2.0, 14.0, 16.0, 14.0);

   public TomatoVineBlock(class_2251 properties) {
      super(properties);
      this.method_9590(
         (class_2680)((class_2680)((class_2680)this.field_10647.method_11664()).method_11657(this.method_9824(), 0)).method_11657(ROPELOGGED, false)
      );
   }

   protected class_9062 method_55765(
      class_1799 stack, class_2680 state, class_1937 level, class_2338 pos, class_1657 player, class_1268 hand, class_3965 hitResult
   ) {
      int age = (Integer)state.method_11654(this.method_9824());
      boolean isMature = age == this.method_9827();
      return !isMature && stack.method_31574(class_1802.field_8324)
         ? class_9062.field_47732
         : super.method_55765(stack, state, level, pos, player, hand, hitResult);
   }

   public class_1269 method_55766(class_2680 state, class_1937 level, class_2338 pos, class_1657 player, class_3965 hit) {
      int age = (Integer)state.method_11654(this.method_9824());
      boolean isMature = age == this.method_9827();
      if (isMature) {
         int quantity = 1 + level.field_9229.method_43048(2);
         method_9577(level, pos, new class_1799((class_1935)ModItems.TOMATO.get(), quantity));
         if (level.field_9229.method_43057() < 0.05) {
            method_9577(level, pos, new class_1799((class_1935)ModItems.ROTTEN_TOMATO.get()));
         }

         level.method_8396(null, pos, ModSounds.ITEM_TOMATO_PICK_FROM_BUSH.get(), class_3419.field_15245, 1.0F, 0.8F + level.field_9229.method_43057() * 0.4F);
         level.method_8652(pos, (class_2680)state.method_11657(this.method_9824(), 0), 2);
         return class_1269.field_5812;
      } else {
         return super.method_55766(state, level, pos, player, hit);
      }
   }

   public boolean method_9542(class_2680 state) {
      return true;
   }

   public void method_9514(class_2680 state, class_3218 level, class_2338 pos, class_5819 random) {
      if (level.method_22343(pos.method_10069(-1, -1, -1), pos.method_10069(1, 1, 1))) {
         if (level.method_22335(pos, 0) >= 9) {
            int age = this.method_9829(state);
            if (age < this.method_9827()) {
               float speed = method_9830(state.method_26204(), level, pos);
               if (random.method_43048((int)(25.0F / speed) + 1) == 0) {
                  level.method_8652(pos, (class_2680)state.method_11657(this.method_9824(), age + 1), 2);
               }
            }

            this.attemptRopeClimb(level, pos, random);
         }
      }
   }

   public void attemptRopeClimb(class_3218 level, class_2338 pos, class_5819 random) {
      if (random.method_43057() < 0.3F) {
         class_2338 posAbove = pos.method_10084();
         class_2680 stateAbove = level.method_8320(posAbove);
         boolean canClimb = Configuration.ENABLE_TOMATO_VINE_CLIMBING_TAGGED_ROPES.get()
            ? stateAbove.method_26164(ModTags.ROPES)
            : stateAbove.method_27852(ModBlocks.ROPE.get());
         if (canClimb) {
            int vineHeight = 1;

            while (level.method_8320(pos.method_10087(vineHeight)).method_27852(this)) {
               vineHeight++;
            }

            if (vineHeight < 3) {
               level.method_8501(posAbove, (class_2680)this.method_9564().method_11657(ROPELOGGED, true));
            }
         }
      }
   }

   public class_2680 method_9828(int age) {
      return (class_2680)this.method_9564().method_11657(this.method_9824(), age);
   }

   public class_2758 method_9824() {
      return VINE_AGE;
   }

   public class_265 method_9530(class_2680 state, class_1922 level, class_2338 pos, class_3726 context) {
      return SHAPE;
   }

   public int method_9827() {
      return 3;
   }

   protected class_1935 method_9832() {
      return (class_1935)ModItems.TOMATO_SEEDS.get();
   }

   protected void method_9515(class_2690<class_2248, class_2680> builder) {
      builder.method_11667(new class_2769[]{VINE_AGE, ROPELOGGED});
   }

   protected int method_9831(class_1937 level) {
      return super.method_9831(level) / 2;
   }

   public void method_9652(class_3218 level, class_5819 random, class_2338 pos, class_2680 state) {
      int newAge = this.method_9829(state) + this.method_9831(level);
      int maxAge = this.method_9827();
      if (newAge > maxAge) {
         newAge = maxAge;
      }

      level.method_8501(pos, (class_2680)state.method_11657(this.method_9824(), newAge));
      this.attemptRopeClimb(level, pos, random);
   }

   public boolean isLadder(class_2680 state, class_4538 level, class_2338 pos, class_1309 entity) {
      return (Boolean)state.method_11654(ROPELOGGED) && state.method_26164(class_3481.field_22414);
   }

   public boolean method_9558(class_2680 state, class_4538 level, class_2338 pos) {
      class_2338 belowPos = pos.method_10074();
      class_2680 belowState = level.method_8320(belowPos);
      return !state.method_11654(ROPELOGGED)
         ? super.method_9558(state, level, pos)
         : belowState.method_27852(ModBlocks.TOMATO_CROP.get()) && this.hasGoodCropConditions(level, pos);
   }

   public boolean hasGoodCropConditions(class_4538 level, class_2338 pos) {
      return level.method_22335(pos, 0) >= 8 || level.method_8311(pos);
   }

   public void method_9556(class_1937 level, class_1657 player, class_2338 pos, class_2680 state, @Nullable class_2586 blockEntity, class_1799 stack) {
      boolean isRopelogged = (Boolean)state.method_11654(ROPELOGGED);
      super.method_9556(level, player, pos, state, blockEntity, stack);
      if (isRopelogged) {
         destroyAndPlaceRope(level, pos);
      }
   }

   public class_2680 method_9559(class_2680 state, class_2350 facing, class_2680 facingState, class_1936 level, class_2338 currentPos, class_2338 facingPos) {
      if (!state.method_26184(level, currentPos)) {
         level.method_39279(currentPos, this, 1);
      }

      return state;
   }

   public static void destroyAndPlaceRope(class_1937 level, class_2338 pos) {
      class_2248 configuredRopeBlock = (class_2248)class_7923.field_41175.method_10223(class_2960.method_60654(Configuration.DEFAULT_TOMATO_VINE_ROPE.get()));
      class_2248 finalRopeBlock = configuredRopeBlock != null ? configuredRopeBlock : ModBlocks.ROPE.get();
      level.method_8501(pos, finalRopeBlock.method_9564());
   }

   public void method_9588(class_2680 state, class_3218 level, class_2338 pos, class_5819 random) {
      if (!state.method_26184(level, pos)) {
         level.method_22352(pos, true);
         if ((Boolean)state.method_11654(ROPELOGGED)) {
            destroyAndPlaceRope(level, pos);
         }
      }
   }
}
