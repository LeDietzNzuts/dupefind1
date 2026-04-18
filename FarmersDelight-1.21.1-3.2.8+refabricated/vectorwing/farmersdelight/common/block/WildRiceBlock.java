package vectorwing.farmersdelight.common.block;

import net.minecraft.class_1309;
import net.minecraft.class_1657;
import net.minecraft.class_1750;
import net.minecraft.class_1799;
import net.minecraft.class_1922;
import net.minecraft.class_1936;
import net.minecraft.class_1937;
import net.minecraft.class_2246;
import net.minecraft.class_2248;
import net.minecraft.class_2256;
import net.minecraft.class_2320;
import net.minecraft.class_2338;
import net.minecraft.class_2350;
import net.minecraft.class_2680;
import net.minecraft.class_2741;
import net.minecraft.class_2746;
import net.minecraft.class_2756;
import net.minecraft.class_2769;
import net.minecraft.class_3218;
import net.minecraft.class_3481;
import net.minecraft.class_3486;
import net.minecraft.class_3610;
import net.minecraft.class_3611;
import net.minecraft.class_3612;
import net.minecraft.class_3737;
import net.minecraft.class_4538;
import net.minecraft.class_5819;
import net.minecraft.class_2350.class_2351;
import net.minecraft.class_2689.class_2690;
import net.minecraft.class_4970.class_2251;
import org.jetbrains.annotations.Nullable;
import vectorwing.farmersdelight.common.registry.ModBlocks;

public class WildRiceBlock extends class_2320 implements class_3737, class_2256 {
   public static final class_2746 WATERLOGGED = class_2741.field_12508;

   public WildRiceBlock(class_2251 properties) {
      super(properties);
      this.method_9590((class_2680)((class_2680)this.method_9564().method_11657(WATERLOGGED, true)).method_11657(field_10929, class_2756.field_12607));
   }

   protected void method_9515(class_2690<class_2248, class_2680> builder) {
      builder.method_11667(new class_2769[]{field_10929, WATERLOGGED});
   }

   public boolean method_9558(class_2680 state, class_4538 level, class_2338 pos) {
      class_3610 fluid = level.method_8316(pos);
      class_2338 floorPos = pos.method_10074();
      return state.method_11654(class_2320.field_10929) == class_2756.field_12607
         ? super.method_9558(state, level, pos)
            && this.method_9695(level.method_8320(floorPos), level, floorPos)
            && fluid.method_15767(class_3486.field_15517)
            && fluid.method_15761() == 8
         : super.method_9558(state, level, pos) && level.method_8320(pos.method_10074()).method_26204() == ModBlocks.WILD_RICE.get();
   }

   public boolean method_9695(class_2680 state, class_1922 getter, class_2338 pos) {
      return state.method_26164(class_3481.field_29822) || state.method_27852(class_2246.field_10102);
   }

   public boolean method_9616(class_2680 state, class_1750 useContext) {
      return false;
   }

   public void method_9567(class_1937 level, class_2338 pos, class_2680 state, class_1309 placer, class_1799 stack) {
      level.method_8652(
         pos.method_10084(), (class_2680)((class_2680)this.method_9564().method_11657(WATERLOGGED, false)).method_11657(field_10929, class_2756.field_12609), 3
      );
   }

   public class_2680 method_9559(class_2680 stateIn, class_2350 facing, class_2680 facingState, class_1936 level, class_2338 currentPos, class_2338 facingPos) {
      class_2680 currentState = super.method_9559(stateIn, facing, facingState, level, currentPos, facingPos);
      class_2756 half = (class_2756)stateIn.method_11654(field_10929);
      if (!currentState.method_26215()) {
         level.method_39281(currentPos, class_3612.field_15910, class_3612.field_15910.method_15789(level));
      }

      if (facing.method_10166() != class_2351.field_11052
         || half == class_2756.field_12607 != (facing == class_2350.field_11036)
         || facingState.method_26204() == this && facingState.method_11654(field_10929) != half) {
         return half == class_2756.field_12607 && facing == class_2350.field_11033 && !stateIn.method_26184(level, currentPos)
            ? class_2246.field_10124.method_9564()
            : stateIn;
      } else {
         return class_2246.field_10124.method_9564();
      }
   }

   @Nullable
   public class_2680 method_9605(class_1750 context) {
      class_2338 pos = context.method_8037();
      class_3610 fluid = context.method_8045().method_8316(context.method_8037());
      return pos.method_10264() < context.method_8045().method_31600() - 1
            && fluid.method_15767(class_3486.field_15517)
            && fluid.method_15761() == 8
            && context.method_8045().method_8320(pos.method_10084()).method_26215()
         ? super.method_9605(context)
         : null;
   }

   public boolean method_10310(@Nullable class_1657 player, class_1922 level, class_2338 pos, class_2680 state, class_3611 fluidIn) {
      return state.method_11654(field_10929) == class_2756.field_12607;
   }

   public class_3610 method_9545(class_2680 state) {
      return state.method_11654(field_10929) == class_2756.field_12607 ? class_3612.field_15910.method_15729(false) : class_3612.field_15906.method_15785();
   }

   public boolean method_9651(class_4538 level, class_2338 pos, class_2680 state) {
      return true;
   }

   public boolean method_9650(class_1937 level, class_5819 rand, class_2338 pos, class_2680 state) {
      return rand.method_43057() < 0.3F;
   }

   public void method_9652(class_3218 level, class_5819 random, class_2338 pos, class_2680 state) {
      method_9577(level, pos, new class_1799(this));
   }
}
