package vectorwing.farmersdelight.common.block;

import com.google.common.base.Suppliers;
import java.util.List;
import java.util.function.Supplier;
import net.minecraft.class_1792;
import net.minecraft.class_1799;
import net.minecraft.class_1922;
import net.minecraft.class_1935;
import net.minecraft.class_2248;
import net.minecraft.class_2338;
import net.minecraft.class_247;
import net.minecraft.class_259;
import net.minecraft.class_265;
import net.minecraft.class_2680;
import net.minecraft.class_2758;
import net.minecraft.class_2769;
import net.minecraft.class_3726;
import net.minecraft.class_2689.class_2690;
import net.minecraft.class_4970.class_2251;
import vectorwing.farmersdelight.common.registry.ModItems;

public class RiceRollMedleyBlock extends FeastBlock {
   public static final class_2758 ROLL_SERVINGS = class_2758.method_11867("servings", 0, 8);
   protected static final class_265 PLATE_SHAPE = class_2248.method_9541(1.0, 0.0, 1.0, 15.0, 2.0, 15.0);
   protected static final class_265 FOOD_SHAPE = class_259.method_1082(
      PLATE_SHAPE, class_2248.method_9541(2.0, 2.0, 2.0, 14.0, 4.0, 14.0), class_247.field_1366
   );
   public final Supplier<List<class_1792>> riceRollServings = Suppliers.memoize(
      () -> List.of(
         ModItems.COD_ROLL.get(),
         ModItems.COD_ROLL.get(),
         ModItems.SALMON_ROLL.get(),
         ModItems.SALMON_ROLL.get(),
         ModItems.SALMON_ROLL.get(),
         ModItems.KELP_ROLL_SLICE.get(),
         ModItems.KELP_ROLL_SLICE.get(),
         ModItems.KELP_ROLL_SLICE.get()
      )
   );

   public RiceRollMedleyBlock(class_2251 properties) {
      super(properties, () -> ModItems.SALMON_ROLL.get(), true);
   }

   @Override
   public class_2758 getServingsProperty() {
      return ROLL_SERVINGS;
   }

   @Override
   public int getMaxServings() {
      return 8;
   }

   @Override
   public class_1799 getServingItem(class_2680 state) {
      return new class_1799((class_1935)this.riceRollServings.get().get((Integer)state.method_11654(this.getServingsProperty()) - 1));
   }

   @Override
   public class_265 method_9530(class_2680 state, class_1922 level, class_2338 pos, class_3726 context) {
      return state.method_11654(this.getServingsProperty()) == 0 ? PLATE_SHAPE : FOOD_SHAPE;
   }

   @Override
   protected void method_9515(class_2690<class_2248, class_2680> builder) {
      builder.method_11667(new class_2769[]{FACING, ROLL_SERVINGS});
   }
}
