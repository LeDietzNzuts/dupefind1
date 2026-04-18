package vectorwing.farmersdelight.common.utility;

import net.minecraft.class_2246;
import net.minecraft.class_2248;
import net.minecraft.class_2302;
import net.minecraft.class_2421;
import net.minecraft.class_2553;
import net.minecraft.class_8237;
import vectorwing.farmersdelight.common.tag.ModTags;

public class SoilUtils {
   public static boolean isAbleToPlaceRichSoil(class_2248 block) {
      if (block.method_40142().method_40220(ModTags.DOES_NOT_SURVIVE_RICH_SOIL)) {
         return false;
      } else {
         return block.method_40142().method_40220(ModTags.SURVIVES_RICH_SOIL_FARMLAND)
            ? true
            : !(block instanceof class_2302) && !(block instanceof class_8237) && !(block instanceof class_2421) && !(block instanceof class_2553);
      }
   }

   public static boolean isAbleToPlaceRichSoilFarmland(class_2248 block) {
      if (block.method_40142().method_40220(ModTags.DOES_NOT_SURVIVE_RICH_SOIL_FARMLAND)) {
         return false;
      } else {
         return block.method_40142().method_40220(ModTags.SURVIVES_RICH_SOIL_FARMLAND)
            ? true
            : block != class_2246.field_10428
               && block != class_2246.field_10588
               && block != class_2246.field_10559
               && block != class_2246.field_10251
               && block != class_2246.field_9974;
      }
   }
}
