package noobanidus.mods.lootr.common.block;

import net.minecraft.class_1750;
import net.minecraft.class_1922;
import net.minecraft.class_2248;
import net.minecraft.class_2338;
import net.minecraft.class_2350;
import net.minecraft.class_2383;
import net.minecraft.class_2415;
import net.minecraft.class_2470;
import net.minecraft.class_265;
import net.minecraft.class_2680;
import net.minecraft.class_2769;
import net.minecraft.class_3726;
import net.minecraft.class_2689.class_2690;
import net.minecraft.class_4970.class_2251;

public class TrophyBlock extends class_2248 {
   private static final class_265 EAST_WEST = class_2248.method_9541(1.5, 0.0, 4.0, 14.5, 14.5, 12.0);
   private static final class_265 NORTH_SOUTH = class_2248.method_9541(4.0, 0.0, 1.5, 12.0, 14.5, 14.5);

   public TrophyBlock(class_2251 properties) {
      super(properties);
   }

   public class_2680 method_9605(class_1750 context) {
      return (class_2680)this.method_9564().method_11657(class_2383.field_11177, context.method_8042().method_10153());
   }

   protected void method_9515(class_2690<class_2248, class_2680> builder) {
      super.method_9515(builder);
      builder.method_11667(new class_2769[]{class_2383.field_11177});
   }

   public class_265 method_9530(class_2680 state, class_1922 worldIn, class_2338 pos, class_3726 context) {
      class_2350 facing = (class_2350)state.method_11654(class_2383.field_11177);
      return facing != class_2350.field_11034 && facing != class_2350.field_11039 ? NORTH_SOUTH : EAST_WEST;
   }

   public class_2680 method_9598(class_2680 p_60530_, class_2470 p_60531_) {
      return (class_2680)p_60530_.method_11657(class_2383.field_11177, p_60531_.method_10503((class_2350)p_60530_.method_11654(class_2383.field_11177)));
   }

   public class_2680 method_9569(class_2680 p_60528_, class_2415 p_60529_) {
      return p_60528_.method_26186(p_60529_.method_10345((class_2350)p_60528_.method_11654(class_2383.field_11177)));
   }
}
