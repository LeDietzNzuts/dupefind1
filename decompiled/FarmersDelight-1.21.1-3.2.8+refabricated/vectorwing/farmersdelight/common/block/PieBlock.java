package vectorwing.farmersdelight.common.block;

import java.util.function.Supplier;
import net.minecraft.class_10;
import net.minecraft.class_1268;
import net.minecraft.class_1269;
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
import net.minecraft.class_3417;
import net.minecraft.class_3419;
import net.minecraft.class_3726;
import net.minecraft.class_3965;
import net.minecraft.class_4174;
import net.minecraft.class_4538;
import net.minecraft.class_9062;
import net.minecraft.class_9334;
import net.minecraft.class_2689.class_2690;
import net.minecraft.class_4174.class_9423;
import net.minecraft.class_4970.class_2251;
import vectorwing.farmersdelight.common.tag.ModTags;
import vectorwing.farmersdelight.common.utility.ItemUtils;

public class PieBlock extends class_2248 {
   public static final class_2753 FACING = class_2741.field_12481;
   public static final class_2758 BITES = class_2758.method_11867("bites", 0, 3);
   protected static final class_265 SHAPE = class_2248.method_9541(2.0, 0.0, 2.0, 14.0, 4.0, 14.0);
   public final Supplier<class_1792> pieSlice;

   public PieBlock(class_2251 properties, Supplier<class_1792> pieSlice) {
      super(properties);
      this.pieSlice = pieSlice;
      this.method_9590(
         (class_2680)((class_2680)((class_2680)this.field_10647.method_11664()).method_11657(FACING, class_2350.field_11043)).method_11657(BITES, 0)
      );
   }

   public class_1799 getPieSliceItem() {
      return new class_1799((class_1935)this.pieSlice.get());
   }

   public int getMaxBites() {
      return 4;
   }

   public class_265 method_9530(class_2680 state, class_1922 level, class_2338 pos, class_3726 context) {
      return SHAPE;
   }

   public class_2680 method_9605(class_1750 context) {
      return (class_2680)this.method_9564().method_11657(FACING, context.method_8042());
   }

   public class_9062 method_55765(class_1799 heldStack, class_2680 state, class_1937 level, class_2338 pos, class_1657 player, class_1268 hand, class_3965 hit) {
      return heldStack.method_31573(ModTags.KNIVES) ? this.cutSlice(level, pos, state, player) : class_9062.field_47731;
   }

   protected class_1269 method_55766(class_2680 state, class_1937 level, class_2338 pos, class_1657 player, class_3965 hitResult) {
      if (level.field_9236) {
         if (this.consumeBite(level, pos, state, player).method_23665()) {
            return class_1269.field_5812;
         }

         if (player.method_5998(class_1268.field_5808).method_7960()) {
            return class_1269.field_21466;
         }
      }

      return this.consumeBite(level, pos, state, player);
   }

   protected class_1269 consumeBite(class_1937 level, class_2338 pos, class_2680 state, class_1657 playerIn) {
      if (!playerIn.method_7332(false)) {
         return class_1269.field_5811;
      } else {
         class_1799 sliceStack = this.getPieSliceItem();
         class_4174 sliceFood = (class_4174)sliceStack.method_57824(class_9334.field_50075);
         if (sliceFood != null) {
            playerIn.method_7344().method_7579(sliceFood);

            for (class_9423 effect : sliceFood.comp_2495()) {
               if (!level.field_9236 && effect != null && level.field_9229.method_43057() < effect.comp_2497()) {
                  playerIn.method_6092(effect.comp_2496());
               }
            }
         }

         int bites = (Integer)state.method_11654(BITES);
         if (bites < this.getMaxBites() - 1) {
            level.method_8652(pos, (class_2680)state.method_11657(BITES, bites + 1), 3);
         } else {
            level.method_8650(pos, false);
         }

         level.method_8396(null, pos, class_3417.field_20614, class_3419.field_15248, 0.8F, 0.8F);
         return class_1269.field_5812;
      }
   }

   protected class_9062 cutSlice(class_1937 level, class_2338 pos, class_2680 state, class_1657 player) {
      int bites = (Integer)state.method_11654(BITES);
      if (bites < this.getMaxBites() - 1) {
         level.method_8652(pos, (class_2680)state.method_11657(BITES, bites + 1), 3);
      } else {
         level.method_8650(pos, false);
      }

      class_2350 direction = player.method_5735().method_10153();
      ItemUtils.spawnItemEntity(
         level,
         this.getPieSliceItem(),
         pos.method_10263() + 0.5,
         pos.method_10264() + 0.3,
         pos.method_10260() + 0.5,
         direction.method_10148() * 0.15,
         0.05,
         direction.method_10165() * 0.15
      );
      level.method_8396(null, pos, class_3417.field_14983, class_3419.field_15248, 0.8F, 0.8F);
      return class_9062.field_47728;
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
      builder.method_11667(new class_2769[]{FACING, BITES});
   }

   public int method_9572(class_2680 blockState, class_1937 level, class_2338 pos) {
      return this.getMaxBites() - (Integer)blockState.method_11654(BITES);
   }

   public boolean method_9498(class_2680 state) {
      return true;
   }

   public boolean method_9516(class_2680 state, class_10 type) {
      return false;
   }
}
