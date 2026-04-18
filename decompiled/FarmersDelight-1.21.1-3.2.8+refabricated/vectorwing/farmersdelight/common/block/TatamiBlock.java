package vectorwing.farmersdelight.common.block;

import net.minecraft.class_1309;
import net.minecraft.class_1750;
import net.minecraft.class_1799;
import net.minecraft.class_1936;
import net.minecraft.class_1937;
import net.minecraft.class_2246;
import net.minecraft.class_2248;
import net.minecraft.class_2338;
import net.minecraft.class_2350;
import net.minecraft.class_2415;
import net.minecraft.class_2470;
import net.minecraft.class_2680;
import net.minecraft.class_2741;
import net.minecraft.class_2746;
import net.minecraft.class_2753;
import net.minecraft.class_2769;
import net.minecraft.class_2689.class_2690;
import net.minecraft.class_4970.class_2251;
import org.jetbrains.annotations.Nullable;

public class TatamiBlock extends class_2248 {
   public static final class_2753 FACING = class_2741.field_12525;
   public static final class_2746 PAIRED = class_2746.method_11825("paired");

   public TatamiBlock(class_2251 properties) {
      super(properties);
      this.method_9590(
         (class_2680)((class_2680)((class_2680)this.method_9595().method_11664()).method_11657(FACING, class_2350.field_11033)).method_11657(PAIRED, false)
      );
   }

   public class_2680 method_9605(class_1750 context) {
      class_2350 face = context.method_8038();
      class_2338 targetPos = context.method_8037().method_10093(face.method_10153());
      class_2680 targetState = context.method_8045().method_8320(targetPos);
      boolean pairing = false;
      if (context.method_8036() != null
         && !context.method_8036().method_5715()
         && targetState.method_26204() == this
         && !(Boolean)targetState.method_11654(PAIRED)) {
         pairing = true;
      }

      return (class_2680)((class_2680)this.method_9564().method_11657(FACING, context.method_8038().method_10153())).method_11657(PAIRED, pairing);
   }

   public void method_9567(class_1937 level, class_2338 pos, class_2680 state, @Nullable class_1309 placer, class_1799 stack) {
      super.method_9567(level, pos, state, placer, stack);
      if (!level.field_9236) {
         if (placer != null && placer.method_5715()) {
            return;
         }

         class_2338 facingPos = pos.method_10093((class_2350)state.method_11654(FACING));
         class_2680 facingState = level.method_8320(facingPos);
         if (facingState.method_26204() == this && !(Boolean)facingState.method_11654(PAIRED)) {
            level.method_8652(
               facingPos,
               (class_2680)((class_2680)state.method_11657(FACING, ((class_2350)state.method_11654(FACING)).method_10153())).method_11657(PAIRED, true),
               3
            );
            level.method_8408(pos, class_2246.field_10124);
            state.method_30101(level, pos, 3);
         }
      }
   }

   public class_2680 method_9559(class_2680 stateIn, class_2350 facing, class_2680 facingState, class_1936 level, class_2338 currentPos, class_2338 facingPos) {
      return facing.equals(stateIn.method_11654(FACING)) && stateIn.method_11654(PAIRED) && level.method_8320(facingPos).method_26204() != this
         ? (class_2680)stateIn.method_11657(PAIRED, false)
         : super.method_9559(stateIn, facing, facingState, level, currentPos, facingPos);
   }

   protected void method_9515(class_2690<class_2248, class_2680> builder) {
      builder.method_11667(new class_2769[]{FACING, PAIRED});
   }

   public class_2680 method_9598(class_2680 state, class_2470 rot) {
      return (class_2680)state.method_11657(FACING, rot.method_10503((class_2350)state.method_11654(FACING)));
   }

   public class_2680 method_9569(class_2680 state, class_2415 mirrorIn) {
      return state.method_26186(mirrorIn.method_10345((class_2350)state.method_11654(FACING)));
   }
}
