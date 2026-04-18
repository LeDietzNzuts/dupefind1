package com.talhanation.smallships.world.inventory.fabric;

import com.talhanation.smallships.world.entity.ship.ContainerShip;
import com.talhanation.smallships.world.inventory.ModMenuTypes;
import com.talhanation.smallships.world.inventory.ShipContainerMenu;
import java.util.UUID;
import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerFactory;
import net.minecraft.class_1657;
import net.minecraft.class_1661;
import net.minecraft.class_1703;
import net.minecraft.class_2561;
import net.minecraft.class_3222;
import net.minecraft.class_4844;
import net.minecraft.class_9129;
import net.minecraft.class_9139;
import org.jetbrains.annotations.NotNull;

public class ContainerUtilityImpl {
   public static void openShipMenu(class_1657 player, ContainerShip containerShip) {
      player.method_17355(new ExtendedScreenHandlerFactory<ContainerUtilityImpl.ContainerMenuData>() {
         public ContainerUtilityImpl.ContainerMenuData getScreenOpeningData(class_3222 player) {
            return new ContainerUtilityImpl.ContainerMenuData(containerShip.method_5667());
         }

         @NotNull
         public class_1703 createMenu(int syncId, @NotNull class_1661 inventory, @NotNull class_1657 playerx) {
            return new ShipContainerMenu(ModMenuTypes.SHIP_CONTAINER, syncId, inventory, containerShip);
         }

         @NotNull
         public class_2561 method_5476() {
            return containerShip.method_5477();
         }
      });
   }

   public record ContainerMenuData(UUID ship) {
      public static final class_9139<class_9129, ContainerUtilityImpl.ContainerMenuData> PACKET_CODEC = class_9139.method_56434(
         class_4844.field_48453, ContainerUtilityImpl.ContainerMenuData::ship, ContainerUtilityImpl.ContainerMenuData::new
      );
   }
}
