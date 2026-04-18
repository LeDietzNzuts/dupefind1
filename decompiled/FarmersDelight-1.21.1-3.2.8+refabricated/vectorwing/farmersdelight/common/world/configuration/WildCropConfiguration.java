package vectorwing.farmersdelight.common.world.configuration;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import java.util.Optional;
import net.minecraft.class_3037;
import net.minecraft.class_5699;
import net.minecraft.class_6796;
import net.minecraft.class_6880;
import org.jetbrains.annotations.Nullable;

public record WildCropConfiguration(
   int tries,
   int xzSpread,
   int ySpread,
   class_6880<class_6796> primaryFeature,
   class_6880<class_6796> secondaryFeature,
   @Nullable class_6880<class_6796> floorFeature
) implements class_3037 {
   public static final Codec<WildCropConfiguration> CODEC = RecordCodecBuilder.create(
      config -> config.group(
            class_5699.field_33442.fieldOf("tries").orElse(64).forGetter(WildCropConfiguration::tries),
            class_5699.field_33441.fieldOf("xz_spread").orElse(4).forGetter(WildCropConfiguration::xzSpread),
            class_5699.field_33441.fieldOf("y_spread").orElse(3).forGetter(WildCropConfiguration::ySpread),
            class_6796.field_35730.fieldOf("primary_feature").forGetter(WildCropConfiguration::primaryFeature),
            class_6796.field_35730.fieldOf("secondary_feature").forGetter(WildCropConfiguration::secondaryFeature),
            class_6796.field_35730.optionalFieldOf("floor_feature").forGetter(floorConfig -> Optional.ofNullable(floorConfig.floorFeature))
         )
         .apply(
            config,
            (tries, xzSpread, yspread, primary, secondary, floor) -> floor.<WildCropConfiguration>map(
                  placedFeatureHolder -> new WildCropConfiguration(tries, xzSpread, yspread, primary, secondary, (class_6880<class_6796>)placedFeatureHolder)
               )
               .orElseGet(() -> new WildCropConfiguration(tries, xzSpread, yspread, primary, secondary, null))
         )
   );
}
