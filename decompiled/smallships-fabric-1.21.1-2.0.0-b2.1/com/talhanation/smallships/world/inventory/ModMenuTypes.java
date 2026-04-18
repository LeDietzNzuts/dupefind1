package com.talhanation.smallships.world.inventory;

import com.talhanation.smallships.world.entity.ship.ContainerShip;
import com.talhanation.smallships.world.inventory.fabric.ModMenuTypesImpl;
import dev.architectury.injectables.annotations.ExpectPlatform;
import dev.architectury.injectables.annotations.ExpectPlatform.Transformed;
import java.util.UUID;
import net.minecraft.class_1297;
import net.minecraft.class_1661;
import net.minecraft.class_1703;
import net.minecraft.class_3917;
import org.jetbrains.annotations.Nullable;

public class ModMenuTypes {
   public static final class_3917<ShipContainerMenu> SHIP_CONTAINER = getMenuType("ship_container");

   @ExpectPlatform
   @Transformed
   public static <T extends class_1703> class_3917<T> getMenuType(String id) {
      return ModMenuTypesImpl.getMenuType(id);
   }

   @Nullable
   public static ShipContainerMenu extendedShipContainerMenuTypeSupplier(int syncId, class_1661 inventory, UUID shipUUID) {
      ContainerShip containerShip = (ContainerShip)inventory.field_7546
         .method_37908()
         .method_8390(ContainerShip.class, inventory.field_7546.method_5830().method_1014(16.0), ship -> ship.method_5667().equals(shipUUID))
         .stream()
         .filter(class_1297::method_5805)
         .findAny()
         .orElse(null);
      if (containerShip == null) {
         return null;
      } else {
         if (containerShip.method_5439() != containerShip.method_42278().size()) {
            containerShip.resizeContainer(containerShip.method_5439());
         }

         return new ShipContainerMenu(SHIP_CONTAINER, syncId, inventory, containerShip);
      }
   }
}
