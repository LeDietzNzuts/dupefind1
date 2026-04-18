package vectorwing.farmersdelight.common.block;

import com.mojang.serialization.MapCodec;
import net.minecraft.class_1750;
import net.minecraft.class_1922;
import net.minecraft.class_1936;
import net.minecraft.class_2246;
import net.minecraft.class_2248;
import net.minecraft.class_2338;
import net.minecraft.class_2350;
import net.minecraft.class_2383;
import net.minecraft.class_265;
import net.minecraft.class_2680;
import net.minecraft.class_2769;
import net.minecraft.class_3726;
import net.minecraft.class_4538;
import net.minecraft.class_2689.class_2690;
import net.minecraft.class_4970.class_2251;

public class TatamiHalfMatBlock extends class_2383 {
   public static final MapCodec<TatamiHalfMatBlock> CODEC = method_54094(TatamiHalfMatBlock::new);
   protected static final class_265 SHAPE = class_2248.method_9541(0.0, 0.0, 0.0, 16.0, 2.0, 16.0);

   public TatamiHalfMatBlock(class_2251 properties) {
      super(properties);
   }

   protected MapCodec<? extends class_2383> method_53969() {
      return CODEC;
   }

   public class_265 method_9530(class_2680 state, class_1922 level, class_2338 pos, class_3726 context) {
      return SHAPE;
   }

   public class_2680 method_9559(class_2680 stateIn, class_2350 facing, class_2680 facingState, class_1936 level, class_2338 currentPos, class_2338 facingPos) {
      return !stateIn.method_26184(level, currentPos)
         ? class_2246.field_10124.method_9564()
         : super.method_9559(stateIn, facing, facingState, level, currentPos, facingPos);
   }

   public boolean method_9558(class_2680 state, class_4538 level, class_2338 pos) {
      return !level.method_22347(pos.method_10074());
   }

   public class_2680 method_9605(class_1750 context) {
      return (class_2680)this.method_9564().method_11657(class_2383.field_11177, context.method_8042().method_10153());
   }

   protected void method_9515(class_2690<class_2248, class_2680> builder) {
      builder.method_11667(new class_2769[]{class_2383.field_11177});
   }
}
