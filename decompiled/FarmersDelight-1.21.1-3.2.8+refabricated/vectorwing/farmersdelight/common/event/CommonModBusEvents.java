package vectorwing.farmersdelight.common.event;

import net.fabricmc.fabric.api.item.v1.DefaultItemComponentEvents;
import net.fabricmc.fabric.api.item.v1.DefaultItemComponentEvents.ModifyContext;
import net.minecraft.class_1792;
import net.minecraft.class_1802;
import net.minecraft.class_2960;
import net.minecraft.class_7923;
import net.minecraft.class_9334;
import vectorwing.farmersdelight.common.Configuration;
import vectorwing.farmersdelight.common.FoodValues;

public class CommonModBusEvents {
   public static void init() {
      DefaultItemComponentEvents.MODIFY.register(CommonModBusEvents::onModifyDefaultComponents);
   }

   public static void onModifyDefaultComponents(ModifyContext context) {
      if (Configuration.ENABLE_STACKABLE_SOUP_ITEMS.get()) {
         Configuration.SOUP_ITEM_LIST.get().forEach(key -> {
            class_1792 item = (class_1792)class_7923.field_41178.method_10223(class_2960.method_60654(key));
            context.modify(item, builder -> builder.method_57840(class_9334.field_50071, 16));
         });
      }

      if (Configuration.RABBIT_STEW_BUFF.get()) {
         context.modify(class_1802.field_8308, builder -> builder.method_57840(class_9334.field_50075, FoodValues.RABBIT_STEW_BUFF));
      }
   }
}
