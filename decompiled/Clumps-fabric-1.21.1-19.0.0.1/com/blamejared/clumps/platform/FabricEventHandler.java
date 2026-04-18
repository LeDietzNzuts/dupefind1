package com.blamejared.clumps.platform;

import com.blamejared.clumps.api.events.ClumpsEvents;
import com.blamejared.clumps.api.events.IEventHandler;
import com.blamejared.clumps.api.events.IRepairEvent;
import com.blamejared.clumps.api.events.IValueEvent;
import com.blamejared.clumps.api.events.RepairEvent;
import com.blamejared.clumps.api.events.ValueEvent;
import com.mojang.datafixers.util.Either;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.class_1657;

public class FabricEventHandler implements IEventHelper {
   @Override
   public Either<IValueEvent, Integer> fireValueEvent(class_1657 player, int value) {
      ValueEvent event = new ValueEvent(player, value);
      if (FabricLoader.getInstance().isModLoaded("fabric")) {
         ((IEventHandler)ClumpsEvents.VALUE_EVENT.invoker()).handle(event);
      }

      return Either.right(event.getValue());
   }

   @Override
   public Either<IRepairEvent, Integer> fireRepairEvent(class_1657 player, int value) {
      RepairEvent event = new RepairEvent(player, value);
      if (FabricLoader.getInstance().isModLoaded("fabric")) {
         ((IEventHandler)ClumpsEvents.REPAIR_EVENT.invoker()).handle(event);
      }

      return Either.right(event.getValue());
   }
}
