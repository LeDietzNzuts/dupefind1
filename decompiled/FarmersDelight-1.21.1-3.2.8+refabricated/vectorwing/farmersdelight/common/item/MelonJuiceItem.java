package vectorwing.farmersdelight.common.item;

import net.minecraft.class_1309;
import net.minecraft.class_1799;
import net.minecraft.class_1937;
import net.minecraft.class_1792.class_1793;

public class MelonJuiceItem extends DrinkableItem {
   public MelonJuiceItem(class_1793 properties) {
      super(properties, false, true);
   }

   @Override
   public void affectConsumer(class_1799 stack, class_1937 level, class_1309 consumer) {
      consumer.method_6025(2.0F);
   }
}
