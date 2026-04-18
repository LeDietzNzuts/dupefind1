package vectorwing.farmersdelight.common.block;

import net.minecraft.class_1937;
import net.minecraft.class_1944;
import net.minecraft.class_2248;
import net.minecraft.class_2338;
import net.minecraft.class_2398;
import net.minecraft.class_2680;
import net.minecraft.class_2758;
import net.minecraft.class_2769;
import net.minecraft.class_3218;
import net.minecraft.class_3486;
import net.minecraft.class_5819;
import net.minecraft.class_2689.class_2690;
import net.minecraft.class_4970.class_2251;
import vectorwing.farmersdelight.common.registry.ModBlocks;
import vectorwing.farmersdelight.common.tag.ModTags;

public class OrganicCompostBlock extends class_2248 {
   public static class_2758 COMPOSTING = class_2758.method_11867("composting", 0, 7);

   public OrganicCompostBlock(class_2251 properties) {
      super(properties);
      this.method_9590((class_2680)super.method_9564().method_11657(COMPOSTING, 0));
   }

   public boolean method_9542(class_2680 state) {
      return true;
   }

   protected void method_9515(class_2690<class_2248, class_2680> builder) {
      builder.method_11667(new class_2769[]{COMPOSTING});
      super.method_9515(builder);
   }

   public int getMaxCompostingStage() {
      return 7;
   }

   public void method_9514(class_2680 state, class_3218 level, class_2338 pos, class_5819 random) {
      if (!level.field_9236) {
         float chance = 0.0F;
         boolean hasWater = false;
         int maxLight = 0;

         for (class_2338 neighborPos : class_2338.method_10097(pos.method_10069(-1, -1, -1), pos.method_10069(1, 1, 1))) {
            class_2680 neighborState = level.method_8320(neighborPos);
            if (neighborState.method_26164(ModTags.COMPOST_ACTIVATORS)) {
               chance += 0.02F;
            }

            if (neighborState.method_26227().method_15767(class_3486.field_15517)) {
               hasWater = true;
            }

            int light = level.method_8314(class_1944.field_9284, neighborPos.method_10084());
            if (light > maxLight) {
               maxLight = light;
            }
         }

         chance += maxLight > 12 ? 0.1F : 0.05F;
         chance += hasWater ? 0.1F : 0.0F;
         if (level.method_8409().method_43057() <= chance) {
            if ((Integer)state.method_11654(COMPOSTING) == this.getMaxCompostingStage()) {
               level.method_8652(pos, ModBlocks.RICH_SOIL.get().method_9564(), 3);
            } else {
               level.method_8652(pos, (class_2680)state.method_11657(COMPOSTING, (Integer)state.method_11654(COMPOSTING) + 1), 3);
            }
         }
      }
   }

   public boolean method_9498(class_2680 state) {
      return true;
   }

   public int method_9572(class_2680 blockState, class_1937 level, class_2338 pos) {
      return this.getMaxCompostingStage() + 1 - (Integer)blockState.method_11654(COMPOSTING);
   }

   public void method_9496(class_2680 state, class_1937 level, class_2338 pos, class_5819 random) {
      super.method_9496(state, level, pos, random);
      if (random.method_43048(10) == 0) {
         level.method_8406(
            class_2398.field_11219,
            (double)pos.method_10263() + random.method_43057(),
            pos.method_10264() + 1.1,
            (double)pos.method_10260() + random.method_43057(),
            0.0,
            0.0,
            0.0
         );
      }
   }
}
