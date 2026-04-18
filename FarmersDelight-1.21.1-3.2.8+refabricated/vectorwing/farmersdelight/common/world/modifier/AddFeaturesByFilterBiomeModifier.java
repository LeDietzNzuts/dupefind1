package vectorwing.farmersdelight.common.world.modifier;

import java.util.Optional;
import net.minecraft.class_1959;
import net.minecraft.class_6796;
import net.minecraft.class_6885;
import net.minecraft.class_2893.class_2895;

public record AddFeaturesByFilterBiomeModifier(
   class_6885<class_1959> allowedBiomes,
   Optional<class_6885<class_1959>> deniedBiomes,
   Optional<Float> minimumTemperature,
   Optional<Float> maximumTemperature,
   class_6885<class_6796> features,
   class_2895 step
) {
}
