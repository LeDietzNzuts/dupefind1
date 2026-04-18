package vectorwing.farmersdelight.common.block;

import com.mojang.serialization.MapCodec;
import net.minecraft.class_1309;
import net.minecraft.class_1657;
import net.minecraft.class_1750;
import net.minecraft.class_1799;
import net.minecraft.class_1922;
import net.minecraft.class_1936;
import net.minecraft.class_1937;
import net.minecraft.class_2246;
import net.minecraft.class_2248;
import net.minecraft.class_2338;
import net.minecraft.class_2350;
import net.minecraft.class_2383;
import net.minecraft.class_2464;
import net.minecraft.class_265;
import net.minecraft.class_2680;
import net.minecraft.class_2741;
import net.minecraft.class_2742;
import net.minecraft.class_2754;
import net.minecraft.class_2769;
import net.minecraft.class_3726;
import net.minecraft.class_4538;
import net.minecraft.class_2689.class_2690;
import net.minecraft.class_4970.class_2251;
import org.jetbrains.annotations.Nullable;

public class TatamiMatBlock extends class_2383 {
   public static final MapCodec<TatamiMatBlock> CODEC = method_54094(TatamiMatBlock::new);
   public static final class_2754<class_2742> PART = class_2741.field_12483;
   protected static final class_265 SHAPE = class_2248.method_9541(0.0, 0.0, 0.0, 16.0, 2.0, 16.0);

   public TatamiMatBlock(class_2251 properties) {
      super(properties);
      this.method_9590((class_2680)((class_2680)this.method_9595().method_11664()).method_11657(PART, class_2742.field_12557));
   }

   protected MapCodec<? extends class_2383> method_53969() {
      return CODEC;
   }

   private static class_2350 getDirectionToOther(class_2742 part, class_2350 direction) {
      return part == class_2742.field_12557 ? direction : direction.method_10153();
   }

   protected void method_9515(class_2690<class_2248, class_2680> builder) {
      builder.method_11667(new class_2769[]{class_2383.field_11177, PART});
   }

   public class_265 method_9530(class_2680 state, class_1922 level, class_2338 pos, class_3726 context) {
      return SHAPE;
   }

   public class_2464 method_9604(class_2680 state) {
      return class_2464.field_11458;
   }

   public class_2680 method_9559(class_2680 stateIn, class_2350 facing, class_2680 facingState, class_1936 level, class_2338 currentPos, class_2338 facingPos) {
      if (facing != getDirectionToOther((class_2742)stateIn.method_11654(PART), (class_2350)stateIn.method_11654(class_2383.field_11177))) {
         return !stateIn.method_26184(level, currentPos)
            ? class_2246.field_10124.method_9564()
            : super.method_9559(stateIn, facing, facingState, level, currentPos, facingPos);
      } else {
         return stateIn.method_26184(level, currentPos) && facingState.method_27852(this) && facingState.method_11654(PART) != stateIn.method_11654(PART)
            ? stateIn
            : class_2246.field_10124.method_9564();
      }
   }

   public boolean method_9558(class_2680 state, class_4538 level, class_2338 pos) {
      return !level.method_22347(pos.method_10074());
   }

   public class_2680 method_9576(class_1937 level, class_2338 pos, class_2680 state, class_1657 player) {
      if (!level.field_9236 && player.method_7337()) {
         class_2742 part = (class_2742)state.method_11654(PART);
         if (part == class_2742.field_12557) {
            class_2338 pairPos = pos.method_10093(getDirectionToOther(part, (class_2350)state.method_11654(class_2383.field_11177)));
            class_2680 pairState = level.method_8320(pairPos);
            if (pairState.method_26204() == this && pairState.method_11654(PART) == class_2742.field_12560) {
               level.method_8652(pairPos, class_2246.field_10124.method_9564(), 35);
               level.method_8444(player, 2001, pairPos, class_2248.method_9507(pairState));
            }
         }
      }

      return super.method_9576(level, pos, state, player);
   }

   public void method_9567(class_1937 level, class_2338 pos, class_2680 state, @Nullable class_1309 placer, class_1799 stack) {
      super.method_9567(level, pos, state, placer, stack);
      if (!level.field_9236) {
         class_2338 facingPos = pos.method_10093((class_2350)state.method_11654(class_2383.field_11177));
         level.method_8652(facingPos, (class_2680)state.method_11657(PART, class_2742.field_12560), 3);
         level.method_8408(pos, class_2246.field_10124);
         state.method_30101(level, pos, 3);
      }
   }

   @Nullable
   public class_2680 method_9605(class_1750 context) {
      class_1937 level = context.method_8045();
      class_2350 facing = context.method_8042();
      class_2338 pairPos = context.method_8037().method_10093(facing);
      class_2680 pairState = context.method_8045().method_8320(pairPos);
      return pairState.method_26166(context) && this.method_9558(pairState, level, pairPos)
         ? (class_2680)this.method_9564().method_11657(class_2383.field_11177, facing)
         : null;
   }
}
