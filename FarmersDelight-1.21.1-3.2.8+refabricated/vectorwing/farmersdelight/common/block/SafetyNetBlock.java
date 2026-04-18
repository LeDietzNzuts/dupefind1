package vectorwing.farmersdelight.common.block;

import net.minecraft.class_1297;
import net.minecraft.class_1309;
import net.minecraft.class_1750;
import net.minecraft.class_1922;
import net.minecraft.class_1936;
import net.minecraft.class_1937;
import net.minecraft.class_2248;
import net.minecraft.class_2338;
import net.minecraft.class_2350;
import net.minecraft.class_243;
import net.minecraft.class_265;
import net.minecraft.class_2680;
import net.minecraft.class_2741;
import net.minecraft.class_2746;
import net.minecraft.class_2769;
import net.minecraft.class_3610;
import net.minecraft.class_3612;
import net.minecraft.class_3726;
import net.minecraft.class_3737;
import net.minecraft.class_2689.class_2690;
import net.minecraft.class_4970.class_2251;
import org.jetbrains.annotations.Nullable;

public class SafetyNetBlock extends class_2248 implements class_3737 {
   public static final class_2746 WATERLOGGED = class_2741.field_12508;
   protected static final class_265 SHAPE = class_2248.method_9541(0.0, 7.0, 0.0, 16.0, 9.0, 16.0);

   public SafetyNetBlock(class_2251 properties) {
      super(properties);
      this.method_9590((class_2680)((class_2680)this.method_9595().method_11664()).method_11657(WATERLOGGED, false));
   }

   protected void method_9515(class_2690<class_2248, class_2680> builder) {
      builder.method_11667(new class_2769[]{WATERLOGGED});
   }

   @Nullable
   public class_2680 method_9605(class_1750 context) {
      class_3610 fluid = context.method_8045().method_8316(context.method_8037());
      return (class_2680)this.method_9564().method_11657(WATERLOGGED, fluid.method_15772() == class_3612.field_15910);
   }

   public class_2680 method_9559(class_2680 stateIn, class_2350 facing, class_2680 facingState, class_1936 level, class_2338 currentPos, class_2338 facingPos) {
      if ((Boolean)stateIn.method_11654(WATERLOGGED)) {
         level.method_39281(currentPos, class_3612.field_15910, class_3612.field_15910.method_15789(level));
      }

      return super.method_9559(stateIn, facing, facingState, level, currentPos, facingPos);
   }

   public class_3610 method_9545(class_2680 state) {
      return state.method_11654(WATERLOGGED) ? class_3612.field_15910.method_15729(false) : super.method_9545(state);
   }

   public class_265 method_9530(class_2680 state, class_1922 level, class_2338 pos, class_3726 context) {
      return SHAPE;
   }

   public void method_9554(class_1937 level, class_2680 state, class_2338 pos, class_1297 entityIn, float fallDistance) {
      if (entityIn.method_21750()) {
         super.method_9554(level, state, pos, entityIn, fallDistance);
      } else {
         entityIn.method_5747(fallDistance, 0.0F, level.method_48963().method_48827());
      }
   }

   public void method_9502(class_1922 level, class_1297 entityIn) {
      if (entityIn.method_21750()) {
         super.method_9502(level, entityIn);
      } else {
         this.bounceEntity(entityIn);
      }
   }

   private void bounceEntity(class_1297 entityIn) {
      class_243 vec3d = entityIn.method_18798();
      if (vec3d.field_1351 < 0.0) {
         double entityWeightOffset = entityIn instanceof class_1309 ? 0.6 : 0.8;
         entityIn.method_18800(vec3d.field_1352, -vec3d.field_1351 * entityWeightOffset, vec3d.field_1350);
      }
   }
}
