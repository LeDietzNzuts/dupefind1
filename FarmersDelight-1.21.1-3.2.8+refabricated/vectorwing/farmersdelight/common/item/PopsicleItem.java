package vectorwing.farmersdelight.common.item;

import net.minecraft.class_1309;
import net.minecraft.class_1799;
import net.minecraft.class_1937;
import net.minecraft.class_1792.class_1793;

public class PopsicleItem extends ConsumableItem {
   public PopsicleItem(class_1793 properties) {
      super(properties);
   }

   @Override
   public void affectConsumer(class_1799 stack, class_1937 level, class_1309 consumer) {
      consumer.method_5646();
   }
}
