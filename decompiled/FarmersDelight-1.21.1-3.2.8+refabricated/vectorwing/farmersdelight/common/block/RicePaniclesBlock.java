package vectorwing.farmersdelight.common.block;

import net.minecraft.class_1922;
import net.minecraft.class_1935;
import net.minecraft.class_1937;
import net.minecraft.class_2248;
import net.minecraft.class_2302;
import net.minecraft.class_2338;
import net.minecraft.class_265;
import net.minecraft.class_2680;
import net.minecraft.class_2741;
import net.minecraft.class_2758;
import net.minecraft.class_2769;
import net.minecraft.class_3726;
import net.minecraft.class_4538;
import net.minecraft.class_2689.class_2690;
import net.minecraft.class_4970.class_2251;
import vectorwing.farmersdelight.common.registry.ModBlocks;
import vectorwing.farmersdelight.common.registry.ModItems;

public class RicePaniclesBlock extends class_2302 {
   public static final class_2758 RICE_AGE = class_2741.field_12497;
   private static final class_265[] SHAPE_BY_AGE = new class_265[]{
      class_2248.method_9541(3.0, 0.0, 3.0, 13.0, 8.0, 13.0),
      class_2248.method_9541(3.0, 0.0, 3.0, 13.0, 10.0, 13.0),
      class_2248.method_9541(2.0, 0.0, 2.0, 14.0, 12.0, 14.0),
      class_2248.method_9541(1.0, 0.0, 1.0, 15.0, 16.0, 15.0)
   };

   public RicePaniclesBlock(class_2251 properties) {
      super(properties);
   }

   public class_2758 method_9824() {
      return RICE_AGE;
   }

   public class_265 method_9530(class_2680 state, class_1922 level, class_2338 pos, class_3726 context) {
      return SHAPE_BY_AGE[state.method_11654(this.method_9824())];
   }

   public int method_9827() {
      return 3;
   }

   protected class_1935 method_9832() {
      return (class_1935)ModItems.RICE.get();
   }

   protected void method_9515(class_2690<class_2248, class_2680> builder) {
      builder.method_11667(new class_2769[]{RICE_AGE});
   }

   protected int method_9831(class_1937 level) {
      return super.method_9831(level) / 3;
   }

   public boolean method_9695(class_2680 state, class_1922 level, class_2338 pos) {
      return state.method_27852(ModBlocks.RICE_CROP.get());
   }

   public boolean method_9558(class_2680 state, class_4538 level, class_2338 pos) {
      return (level.method_22335(pos, 0) >= 8 || level.method_8311(pos)) && this.method_9695(level.method_8320(pos.method_10074()), level, pos);
   }
}
