package vectorwing.farmersdelight.common.block;

import java.util.function.Supplier;
import net.minecraft.class_10;
import net.minecraft.class_1268;
import net.minecraft.class_1657;
import net.minecraft.class_1750;
import net.minecraft.class_1792;
import net.minecraft.class_1799;
import net.minecraft.class_1922;
import net.minecraft.class_1935;
import net.minecraft.class_1936;
import net.minecraft.class_1937;
import net.minecraft.class_2246;
import net.minecraft.class_2248;
import net.minecraft.class_2338;
import net.minecraft.class_2350;
import net.minecraft.class_265;
import net.minecraft.class_2680;
import net.minecraft.class_2741;
import net.minecraft.class_2753;
import net.minecraft.class_2758;
import net.minecraft.class_2769;
import net.minecraft.class_3414;
import net.minecraft.class_3417;
import net.minecraft.class_3419;
import net.minecraft.class_3726;
import net.minecraft.class_3965;
import net.minecraft.class_4538;
import net.minecraft.class_9062;
import net.minecraft.class_2689.class_2690;
import net.minecraft.class_4970.class_2251;
import vectorwing.farmersdelight.common.utility.TextUtils;

public class FeastBlock extends class_2248 {
   public static final class_2753 FACING = class_2741.field_12481;
   public static final class_2758 SERVINGS = class_2758.method_11867("servings", 0, 4);
   public final Supplier<class_1792> servingItem;
   public final boolean hasLeftovers;
   protected static final class_265[] SHAPES = new class_265[]{
      class_2248.method_9541(2.0, 0.0, 2.0, 14.0, 1.0, 14.0),
      class_2248.method_9541(2.0, 0.0, 2.0, 14.0, 3.0, 14.0),
      class_2248.method_9541(2.0, 0.0, 2.0, 14.0, 6.0, 14.0),
      class_2248.method_9541(2.0, 0.0, 2.0, 14.0, 8.0, 14.0),
      class_2248.method_9541(2.0, 0.0, 2.0, 14.0, 10.0, 14.0)
   };

   public FeastBlock(class_2251 properties, Supplier<class_1792> servingItem, boolean hasLeftovers) {
      super(properties);
      this.servingItem = servingItem;
      this.hasLeftovers = hasLeftovers;
      this.method_9590(
         (class_2680)((class_2680)((class_2680)this.method_9595().method_11664()).method_11657(FACING, class_2350.field_11043))
            .method_11657(this.getServingsProperty(), this.getMaxServings())
      );
   }

   public class_2758 getServingsProperty() {
      return SERVINGS;
   }

   public int getMaxServings() {
      return 4;
   }

   public class_1799 getServingItem(class_2680 state) {
      return new class_1799((class_1935)this.servingItem.get());
   }

   public class_265 method_9530(class_2680 state, class_1922 level, class_2338 pos, class_3726 context) {
      return SHAPES[state.method_11654(SERVINGS)];
   }

   public class_9062 method_55765(class_1799 heldStack, class_2680 state, class_1937 level, class_2338 pos, class_1657 player, class_1268 hand, class_3965 hit) {
      return level.field_9236 && this.takeServing(level, pos, state, player, hand).method_55643()
         ? class_9062.field_47728
         : this.takeServing(level, pos, state, player, hand);
   }

   protected class_9062 takeServing(class_1936 level, class_2338 pos, class_2680 state, class_1657 player, class_1268 hand) {
      int servings = (Integer)state.method_11654(this.getServingsProperty());
      if (servings == 0) {
         level.method_8396(null, pos, class_3417.field_15215, class_3419.field_15248, 0.8F, 0.8F);
         level.method_22352(pos, true);
         return class_9062.field_47728;
      } else {
         class_1799 serving = this.getServingItem(state);
         class_1799 heldStack = player.method_5998(hand);
         if (servings > 0) {
            if (serving.getRecipeRemainder().method_7960() || class_1799.method_7984(heldStack, serving.getRecipeRemainder())) {
               level.method_8652(pos, (class_2680)state.method_11657(this.getServingsProperty(), servings - 1), 3);
               if (!player.method_31549().field_7477 && !serving.getRecipeRemainder().method_7960()) {
                  heldStack.method_7934(1);
               }

               if (!player.method_31548().method_7394(serving)) {
                  player.method_7328(serving, false);
               }

               if ((Integer)level.method_8320(pos).method_11654(this.getServingsProperty()) == 0 && !this.hasLeftovers) {
                  level.method_8650(pos, false);
               }

               level.method_8396(null, pos, (class_3414)class_3417.field_14883.comp_349(), class_3419.field_15245, 1.0F, 1.0F);
               return class_9062.field_47728;
            }

            player.method_7353(TextUtils.getTranslation("block.feast.use_container", serving.getRecipeRemainder().method_7964()), true);
         }

         return class_9062.field_47731;
      }
   }

   public class_2680 method_9605(class_1750 context) {
      return (class_2680)this.method_9564().method_11657(FACING, context.method_8042().method_10153());
   }

   public class_2680 method_9559(class_2680 stateIn, class_2350 facing, class_2680 facingState, class_1936 level, class_2338 currentPos, class_2338 facingPos) {
      return facing == class_2350.field_11033 && !stateIn.method_26184(level, currentPos)
         ? class_2246.field_10124.method_9564()
         : super.method_9559(stateIn, facing, facingState, level, currentPos, facingPos);
   }

   public boolean method_9558(class_2680 state, class_4538 level, class_2338 pos) {
      return level.method_8320(pos.method_10074()).method_51367();
   }

   protected void method_9515(class_2690<class_2248, class_2680> builder) {
      builder.method_11667(new class_2769[]{FACING, SERVINGS});
   }

   public int method_9572(class_2680 blockState, class_1937 level, class_2338 pos) {
      return (Integer)blockState.method_11654(this.getServingsProperty());
   }

   public boolean method_9498(class_2680 state) {
      return true;
   }

   public boolean method_9516(class_2680 state, class_10 type) {
      return false;
   }
}
