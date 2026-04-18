package vectorwing.farmersdelight.common.registry;

import java.util.function.Supplier;
import net.minecraft.class_3031;
import net.minecraft.class_4638;
import vectorwing.farmersdelight.common.world.configuration.WildCropConfiguration;
import vectorwing.farmersdelight.common.world.feature.WildCropFeature;
import vectorwing.farmersdelight.common.world.feature.WildRiceFeature;
import vectorwing.farmersdelight.refabricated.RegUtils;

public class ModBiomeFeatures {
   public static final Supplier<class_3031<class_4638>> WILD_RICE = RegUtils.regFeature("wild_rice", () -> new WildRiceFeature(class_4638.field_24902));
   public static final Supplier<class_3031<WildCropConfiguration>> WILD_CROP = RegUtils.regFeature(
      "wild_crop", () -> new WildCropFeature(WildCropConfiguration.CODEC)
   );

   public static void touch() {
   }
}
