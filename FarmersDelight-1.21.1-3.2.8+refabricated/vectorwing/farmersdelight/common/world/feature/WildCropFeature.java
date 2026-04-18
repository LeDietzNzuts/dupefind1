package vectorwing.farmersdelight.common.world.feature;

import com.mojang.serialization.Codec;
import net.minecraft.class_2338;
import net.minecraft.class_3031;
import net.minecraft.class_5281;
import net.minecraft.class_5819;
import net.minecraft.class_5821;
import net.minecraft.class_6796;
import net.minecraft.class_6880;
import net.minecraft.class_2338.class_2339;
import vectorwing.farmersdelight.common.world.configuration.WildCropConfiguration;

public class WildCropFeature extends class_3031<WildCropConfiguration> {
   public WildCropFeature(Codec<WildCropConfiguration> codec) {
      super(codec);
   }

   public boolean method_13151(class_5821<WildCropConfiguration> context) {
      WildCropConfiguration config = (WildCropConfiguration)context.method_33656();
      class_2338 origin = context.method_33655();
      class_5281 level = context.method_33652();
      class_5819 random = context.method_33654();
      int i = 0;
      int tries = config.tries();
      int xzSpread = config.xzSpread() + 1;
      int ySpread = config.ySpread() + 1;
      class_2339 mutablePos = new class_2339();
      class_6880<class_6796> floorFeature = config.floorFeature();
      if (floorFeature != null) {
         for (int j = 0; j < tries; j++) {
            mutablePos.method_25504(
               origin,
               random.method_43048(xzSpread) - random.method_43048(xzSpread),
               random.method_43048(ySpread) - random.method_43048(ySpread),
               random.method_43048(xzSpread) - random.method_43048(xzSpread)
            );
            if (((class_6796)config.floorFeature().comp_349()).method_39644(level, context.method_33653(), random, mutablePos)) {
               i++;
            }
         }
      }

      for (int k = 0; k < tries; k++) {
         int shorterXZ = xzSpread - 2;
         mutablePos.method_25504(
            origin,
            random.method_43048(shorterXZ) - random.method_43048(shorterXZ),
            random.method_43048(ySpread) - random.method_43048(ySpread),
            random.method_43048(shorterXZ) - random.method_43048(shorterXZ)
         );
         if (((class_6796)config.primaryFeature().comp_349()).method_39644(level, context.method_33653(), random, mutablePos)) {
            i++;
         }
      }

      for (int l = 0; l < tries; l++) {
         mutablePos.method_25504(
            origin,
            random.method_43048(xzSpread) - random.method_43048(xzSpread),
            random.method_43048(ySpread) - random.method_43048(ySpread),
            random.method_43048(xzSpread) - random.method_43048(xzSpread)
         );
         if (((class_6796)config.secondaryFeature().comp_349()).method_39644(level, context.method_33653(), random, mutablePos)) {
            i++;
         }
      }

      return i > 0;
   }
}
