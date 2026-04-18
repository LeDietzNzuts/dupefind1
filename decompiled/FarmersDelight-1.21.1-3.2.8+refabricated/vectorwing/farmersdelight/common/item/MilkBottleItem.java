package vectorwing.farmersdelight.common.item;

import java.util.ArrayList;
import java.util.Iterator;
import net.minecraft.class_1291;
import net.minecraft.class_1293;
import net.minecraft.class_1309;
import net.minecraft.class_1799;
import net.minecraft.class_1937;
import net.minecraft.class_6880;
import net.minecraft.class_1792.class_1793;
import vectorwing.farmersdelight.common.tag.ModTags;

public class MilkBottleItem extends DrinkableItem {
   public MilkBottleItem(class_1793 properties) {
      super(properties, false, true);
   }

   @Override
   public void affectConsumer(class_1799 stack, class_1937 level, class_1309 consumer) {
      Iterator<class_1293> itr = consumer.method_6026().iterator();
      ArrayList<class_6880<class_1291>> compatibleEffects = new ArrayList<>();

      while (itr.hasNext()) {
         class_1293 effect = itr.next();
         if (!effect.method_5579().method_40220(ModTags.MILK_BOTTLE_IGNORED)) {
            compatibleEffects.add(effect.method_5579());
         }
      }

      if (!compatibleEffects.isEmpty()) {
         class_1293 selectedEffect = consumer.method_6112(compatibleEffects.get(level.field_9229.method_43048(compatibleEffects.size())));
         if (selectedEffect != null) {
            consumer.method_6016(selectedEffect.method_5579());
         }
      }
   }
}
