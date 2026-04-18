package vectorwing.farmersdelight.common.block.entity;

import net.minecraft.class_1937;
import net.minecraft.class_2338;
import net.minecraft.class_2680;
import net.minecraft.class_2741;
import vectorwing.farmersdelight.common.tag.ModTags;

public interface HeatableBlockEntity {
   default boolean isHeated(class_1937 level, class_2338 pos) {
      class_2680 stateBelow = level.method_8320(pos.method_10074());
      if (stateBelow.method_26164(ModTags.HEAT_SOURCES)) {
         return stateBelow.method_28498(class_2741.field_12548) ? (Boolean)stateBelow.method_11654(class_2741.field_12548) : true;
      } else {
         if (!this.requiresDirectHeat() && stateBelow.method_26164(ModTags.HEAT_CONDUCTORS)) {
            class_2680 stateFurtherBelow = level.method_8320(pos.method_10087(2));
            if (stateFurtherBelow.method_26164(ModTags.HEAT_SOURCES)) {
               if (stateFurtherBelow.method_28498(class_2741.field_12548)) {
                  return (Boolean)stateFurtherBelow.method_11654(class_2741.field_12548);
               }

               return true;
            }
         }

         return false;
      }
   }

   default boolean requiresDirectHeat() {
      return false;
   }
}
