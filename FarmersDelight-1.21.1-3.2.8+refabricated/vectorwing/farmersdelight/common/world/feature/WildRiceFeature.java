package vectorwing.farmersdelight.common.world.feature;

import com.mojang.serialization.Codec;
import net.minecraft.class_2246;
import net.minecraft.class_2320;
import net.minecraft.class_2338;
import net.minecraft.class_2680;
import net.minecraft.class_2756;
import net.minecraft.class_3031;
import net.minecraft.class_4638;
import net.minecraft.class_5281;
import net.minecraft.class_5819;
import net.minecraft.class_5821;
import net.minecraft.class_2338.class_2339;
import net.minecraft.class_2902.class_2903;
import vectorwing.farmersdelight.common.block.WildRiceBlock;
import vectorwing.farmersdelight.common.registry.ModBlocks;

public class WildRiceFeature extends class_3031<class_4638> {
   public WildRiceFeature(Codec<class_4638> configFactoryIn) {
      super(configFactoryIn);
   }

   public boolean method_13151(class_5821<class_4638> context) {
      class_5281 level = context.method_33652();
      class_2338 origin = context.method_33655();
      class_4638 config = (class_4638)context.method_33656();
      class_5819 rand = context.method_33654();
      class_2338 blockpos = level.method_8598(class_2903.field_13195, origin);
      int i = 0;
      class_2339 blockpos$mutable = new class_2339();

      for (int j = 0; j < config.comp_149(); j++) {
         blockpos$mutable.method_10101(blockpos)
            .method_10100(
               rand.method_43048(config.comp_150() + 1) - rand.method_43048(config.comp_150() + 1),
               rand.method_43048(config.comp_151() + 1) - rand.method_43048(config.comp_151() + 1),
               rand.method_43048(config.comp_150() + 1) - rand.method_43048(config.comp_150() + 1)
            );
         if (level.method_8320(blockpos$mutable).method_26204() == class_2246.field_10382
            && level.method_8320(blockpos$mutable.method_10084()).method_26204() == class_2246.field_10124) {
            class_2680 bottomRiceState = (class_2680)ModBlocks.WILD_RICE.get().method_9564().method_11657(WildRiceBlock.field_10929, class_2756.field_12607);
            if (bottomRiceState.method_26184(level, blockpos$mutable)) {
               class_2320.method_10021(level, bottomRiceState, blockpos$mutable, 2);
               i++;
            }
         }
      }

      return i > 0;
   }
}
