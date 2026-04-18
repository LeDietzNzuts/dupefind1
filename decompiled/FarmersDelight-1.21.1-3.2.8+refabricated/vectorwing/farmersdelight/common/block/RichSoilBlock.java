package vectorwing.farmersdelight.common.block;

import net.fabricmc.fabric.api.registry.TillableBlockRegistry;
import net.minecraft.class_1794;
import net.minecraft.class_2246;
import net.minecraft.class_2248;
import net.minecraft.class_2256;
import net.minecraft.class_2338;
import net.minecraft.class_2680;
import net.minecraft.class_3218;
import net.minecraft.class_5819;
import net.minecraft.class_4970.class_2251;
import vectorwing.farmersdelight.common.Configuration;
import vectorwing.farmersdelight.common.registry.ModBlocks;
import vectorwing.farmersdelight.common.tag.ModTags;
import vectorwing.farmersdelight.common.utility.MathUtils;

public class RichSoilBlock extends class_2248 {
   public RichSoilBlock(class_2251 properties) {
      super(properties);
   }

   public static void init() {
      TillableBlockRegistry.register(
         ModBlocks.RICH_SOIL.get(), class_1794::method_36987, class_1794.method_36988(ModBlocks.RICH_SOIL_FARMLAND.get().method_9564())
      );
   }

   public void method_9514(class_2680 state, class_3218 level, class_2338 pos, class_5819 rand) {
      if (!level.field_9236) {
         class_2338 abovePos = pos.method_10084();
         class_2680 aboveState = level.method_8320(abovePos);
         class_2248 aboveBlock = aboveState.method_26204();
         if (aboveState.method_26164(ModTags.UNAFFECTED_BY_RICH_SOIL)) {
            return;
         }

         if (aboveBlock == class_2246.field_10251) {
            level.method_8501(pos.method_10084(), ModBlocks.BROWN_MUSHROOM_COLONY.get().method_9564());
            return;
         }

         if (aboveBlock == class_2246.field_10559) {
            level.method_8501(pos.method_10084(), ModBlocks.RED_MUSHROOM_COLONY.get().method_9564());
            return;
         }

         if (Configuration.RICH_SOIL_BOOST_CHANCE.get() == 0.0) {
            return;
         }

         if (aboveBlock instanceof class_2256 growable
            && MathUtils.RAND.nextFloat() <= Configuration.RICH_SOIL_BOOST_CHANCE.get()
            && growable.method_9651(level, pos.method_10084(), aboveState)) {
            growable.method_9652(level, level.field_9229, pos.method_10084(), aboveState);
         }
      }
   }
}
