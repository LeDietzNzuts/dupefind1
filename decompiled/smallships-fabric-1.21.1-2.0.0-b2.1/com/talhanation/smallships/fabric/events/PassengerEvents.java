package com.talhanation.smallships.fabric.events;

import com.talhanation.smallships.world.entity.ship.Ship;
import net.fabricmc.fabric.api.event.player.UseEntityCallback;
import net.minecraft.class_1268;
import net.minecraft.class_1269;
import net.minecraft.class_1297;
import net.minecraft.class_1657;
import net.minecraft.class_1937;
import net.minecraft.class_3966;
import org.jetbrains.annotations.Nullable;

public class PassengerEvents implements UseEntityCallback {
   public class_1269 interact(class_1657 player, class_1937 world, class_1268 hand, class_1297 entity, @Nullable class_3966 hitResult) {
      if (!player.method_18276()
         && entity.method_5765()
         && !(entity instanceof class_1657)
         && (entity.method_5653() == null || !entity.method_5653().contains("captain"))
         && entity.method_5854() != null
         && entity.method_5854() instanceof Ship) {
         entity.method_5848();
         return class_1269.field_5812;
      } else if (player.method_5765()
         && player.method_5854() != null
         && player.method_5854() instanceof Ship ship
         && ship.method_5818(entity)
         && !(entity instanceof class_1657)) {
         entity.method_5804(ship);
         return class_1269.field_5812;
      } else {
         return class_1269.field_5811;
      }
   }
}
