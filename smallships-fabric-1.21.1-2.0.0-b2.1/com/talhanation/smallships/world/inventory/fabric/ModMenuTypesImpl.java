package com.talhanation.smallships.world.inventory.fabric;

import com.talhanation.smallships.world.inventory.ModMenuTypes;
import java.util.HashMap;
import java.util.Map;
import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerType;
import net.minecraft.class_1703;
import net.minecraft.class_2378;
import net.minecraft.class_3917;
import net.minecraft.class_7923;

public class ModMenuTypesImpl {
   private static final Map<String, class_3917<? extends class_1703>> entries = new HashMap<>();

   public static <T extends class_1703> class_3917<T> getMenuType(String id) {
      return (class_3917<T>)entries.get(id);
   }

   private static <T extends class_1703> class_3917<T> register(String id, class_3917<T> menuType) {
      return (class_3917<T>)class_2378.method_10226(class_7923.field_41187, id, menuType);
   }

   static {
      entries.put(
         "ship_container",
         register(
            "ship_container",
            new ExtendedScreenHandlerType(
               (syncId, inventory, data) -> ModMenuTypes.extendedShipContainerMenuTypeSupplier(syncId, inventory, data.ship()),
               ContainerUtilityImpl.ContainerMenuData.PACKET_CODEC
            )
         )
      );
   }
}
