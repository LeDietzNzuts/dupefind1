package vectorwing.farmersdelight.common.block;

import net.minecraft.class_1297;
import net.minecraft.class_1750;
import net.minecraft.class_1937;
import net.minecraft.class_2246;
import net.minecraft.class_2248;
import net.minecraft.class_2256;
import net.minecraft.class_2338;
import net.minecraft.class_2344;
import net.minecraft.class_2521;
import net.minecraft.class_2680;
import net.minecraft.class_3218;
import net.minecraft.class_3486;
import net.minecraft.class_4538;
import net.minecraft.class_5712;
import net.minecraft.class_5819;
import net.minecraft.class_4970.class_2251;
import net.minecraft.class_5712.class_7397;
import org.jetbrains.annotations.Nullable;
import vectorwing.farmersdelight.common.Configuration;
import vectorwing.farmersdelight.common.registry.ModBlocks;
import vectorwing.farmersdelight.common.tag.ModTags;
import vectorwing.farmersdelight.common.utility.MathUtils;

public class RichSoilFarmlandBlock extends class_2344 {
   public RichSoilFarmlandBlock(class_2251 properties) {
      super(properties);
   }

   private static boolean isNearWater(class_4538 level, class_2338 pos) {
      class_2680 state = level.method_8320(pos);

      for (class_2338 nearbyPos : class_2338.method_10097(pos.method_10069(-4, 0, -4), pos.method_10069(4, 1, 4))) {
         if (level.method_8316(nearbyPos).method_15767(class_3486.field_15517)) {
            return true;
         }
      }

      return false;
   }

   public static void turnToRichSoil(@Nullable class_1297 entity, class_2680 state, class_1937 level, class_2338 pos) {
      level.method_8501(pos, method_9582(state, ModBlocks.RICH_SOIL.get().method_9564(), level, pos));
      level.method_43276(class_5712.field_28733, pos, class_7397.method_43286(entity, state));
   }

   public boolean method_9558(class_2680 state, class_4538 level, class_2338 pos) {
      class_2680 aboveState = level.method_8320(pos.method_10084());
      return super.method_9558(state, level, pos)
         || aboveState.method_26204().equals(class_2246.field_46283)
         || aboveState.method_26204().equals(class_2246.field_46282);
   }

   public void method_9588(class_2680 state, class_3218 level, class_2338 pos, class_5819 rand) {
      if (!state.method_26184(level, pos)) {
         turnToRichSoil(null, state, level, pos);
      }
   }

   public void method_9514(class_2680 state, class_3218 level, class_2338 pos, class_5819 random) {
      int moisture = (Integer)state.method_11654(field_11009);
      if (!isNearWater(level, pos) && !level.method_8520(pos.method_10084())) {
         if (moisture > 0) {
            level.method_8652(pos, (class_2680)state.method_11657(field_11009, moisture - 1), 2);
         }
      } else if (moisture < 7) {
         level.method_8652(pos, (class_2680)state.method_11657(field_11009, 7), 2);
      } else if (moisture == 7) {
         if (Configuration.RICH_SOIL_BOOST_CHANCE.get() == 0.0) {
            return;
         }

         class_2338 abovePos = pos.method_10084();
         class_2680 aboveState = level.method_8320(abovePos);
         class_2248 aboveBlock = aboveState.method_26204();
         if (aboveState.method_26164(ModTags.UNAFFECTED_BY_RICH_SOIL) || aboveBlock instanceof class_2521) {
            return;
         }

         if (aboveBlock instanceof class_2256 growable
            && MathUtils.RAND.nextFloat() <= Configuration.RICH_SOIL_BOOST_CHANCE.get()
            && growable.method_9651(level, abovePos, aboveState)) {
            growable.method_9652(level, level.field_9229, abovePos, aboveState);
         }
      }
   }

   public class_2680 method_9605(class_1750 context) {
      return !this.method_9564().method_26184(context.method_8045(), context.method_8037())
         ? ModBlocks.RICH_SOIL.get().method_9564()
         : super.method_9605(context);
   }

   public void method_9554(class_1937 level, class_2680 state, class_2338 pos, class_1297 entity, float fallDistance) {
      entity.method_5747(fallDistance, 1.0F, entity.method_48923().method_48827());
   }
}
