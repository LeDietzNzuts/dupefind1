package net.kikoz.mcwfurnitures.objects.counters;

import net.minecraft.class_1268;
import net.minecraft.class_1657;
import net.minecraft.class_1750;
import net.minecraft.class_1792;
import net.minecraft.class_1799;
import net.minecraft.class_1802;
import net.minecraft.class_1844;
import net.minecraft.class_1847;
import net.minecraft.class_1922;
import net.minecraft.class_1937;
import net.minecraft.class_2248;
import net.minecraft.class_2338;
import net.minecraft.class_2350;
import net.minecraft.class_2383;
import net.minecraft.class_2398;
import net.minecraft.class_2464;
import net.minecraft.class_2470;
import net.minecraft.class_259;
import net.minecraft.class_265;
import net.minecraft.class_2680;
import net.minecraft.class_2746;
import net.minecraft.class_2753;
import net.minecraft.class_2769;
import net.minecraft.class_3417;
import net.minecraft.class_3419;
import net.minecraft.class_3726;
import net.minecraft.class_3965;
import net.minecraft.class_9062;
import net.minecraft.class_2689.class_2690;
import net.minecraft.class_4970.class_2251;

public class SinkCounter extends class_2248 {
   public static final class_2753 FACING = class_2383.field_11177;
   public static final class_2746 WATER = class_2746.method_11825("water");
   protected static final class_265 VNORTH = class_259.method_17786(class_2248.method_9541(0.0, 0.0, 0.0, 16.0, 16.0, 15.0), new class_265[0]);
   protected static final class_265 VEAST = class_259.method_17786(class_2248.method_9541(1.0, 0.0, 0.0, 16.0, 16.0, 16.0), new class_265[0]);
   protected static final class_265 VSOUTH = class_259.method_17786(class_2248.method_9541(0.0, 0.0, 1.0, 16.0, 16.0, 16.0), new class_265[0]);
   protected static final class_265 VWEST = class_259.method_17786(class_2248.method_9541(0.0, 0.0, 0.0, 15.0, 16.0, 16.0), new class_265[0]);

   public SinkCounter(class_2251 properties) {
      super(properties);
      this.method_9590((class_2680)((class_2680)this.method_9564().method_11657(FACING, class_2350.field_11043)).method_11657(WATER, false));
   }

   public class_265 method_9530(class_2680 state, class_1922 world, class_2338 pos, class_3726 context) {
      switch ((class_2350)state.method_11654(FACING)) {
         case field_11043:
            return VNORTH;
         case field_11034:
            return VEAST;
         case field_11035:
            return VSOUTH;
         case field_11039:
            return VWEST;
         default:
            return VNORTH;
      }
   }

   public class_9062 method_55765(class_1799 itemstack, class_2680 state, class_1937 level, class_2338 pos, class_1657 player, class_1268 hand, class_3965 hit) {
      class_1792 item = itemstack.method_7909();
      boolean hasWater = (Boolean)state.method_11654(WATER);
      class_2350 facing = (class_2350)state.method_11654(FACING);
      if (item == class_1802.field_8705 && !hasWater) {
         state = (class_2680)state.method_28493(WATER);
         level.method_8652(pos, state, 2);
         level.method_8396(null, pos, class_3417.field_14834, class_3419.field_15245, 1.0F, 1.0F);
         player.method_6122(hand, new class_1799(class_1802.field_8550));
         return class_9062.field_47728;
      } else if (item == class_1802.field_8550 && hasWater) {
         state = (class_2680)state.method_11657(WATER, false);
         level.method_8652(pos, state, 2);
         level.method_8396(null, pos, class_3417.field_15126, class_3419.field_15245, 1.0F, 1.0F);
         class_1799 filledBucket = new class_1799(class_1802.field_8705);
         if (!player.method_31549().field_7477) {
            itemstack.method_7934(1);
            if (itemstack.method_7960()) {
               player.method_6122(hand, filledBucket);
            } else if (!player.method_31548().method_7394(filledBucket)) {
               player.method_7328(filledBucket, false);
            }
         } else if (!player.method_31548().method_7394(filledBucket)) {
            player.method_7328(filledBucket, false);
         }

         return class_9062.field_47728;
      } else if (item == class_1802.field_8469 && hasWater) {
         state = (class_2680)state.method_28493(WATER);
         level.method_8652(pos, state, 2);
         level.method_8396(null, pos, class_3417.field_14779, class_3419.field_15245, 1.0F, 1.0F);
         class_1799 waterBottle = class_1844.method_57400(class_1802.field_8574, class_1847.field_8991);
         if (!player.method_31549().field_7477) {
            itemstack.method_7934(1);
            if (itemstack.method_7960()) {
               player.method_6122(hand, waterBottle);
            } else if (!player.method_31548().method_7394(waterBottle)) {
               player.method_7328(waterBottle, false);
            }
         }

         return class_9062.field_47728;
      } else if (!level.field_9236) {
         class_2680 newState = (class_2680)state.method_28493(WATER);
         level.method_8652(pos, newState, 3);
         level.method_8396(null, pos, class_3417.field_14834, class_3419.field_15245, 1.0F, 1.0F);
         return class_9062.field_47728;
      } else {
         if (!hasWater) {
            double baseX = pos.method_10263() + 0.5;
            double baseZ = pos.method_10260() + 0.5;
            double offset = 0.1;
            switch (facing) {
               case field_11043:
                  baseZ -= offset;
                  break;
               case field_11034:
                  baseX += offset;
                  break;
               case field_11035:
                  baseZ += offset;
                  break;
               case field_11039:
                  baseX -= offset;
            }

            for (int i = 0; i < 20; i++) {
               double y = pos.method_10264() + 1 - i * 0.001;
               level.method_8406(class_2398.field_28079, baseX, y, baseZ, 0.0, -1.0E-4, 0.0);
            }
         }

         return class_9062.field_47728;
      }
   }

   public class_2680 method_9605(class_1750 context) {
      return (class_2680)this.method_9564().method_11657(FACING, context.method_8042());
   }

   public class_2680 method_9598(class_2680 state, class_2470 rotation) {
      return (class_2680)state.method_11657(FACING, rotation.method_10503((class_2350)state.method_11654(FACING)));
   }

   public class_2464 method_9604(class_2680 state) {
      return class_2464.field_11458;
   }

   protected void method_9515(class_2690<class_2248, class_2680> builder) {
      builder.method_11667(new class_2769[]{FACING, WATER});
   }
}
