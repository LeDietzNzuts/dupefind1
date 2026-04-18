package com.blamejared.clumps.platform;

import com.blamejared.clumps.api.events.IRepairEvent;
import com.blamejared.clumps.api.events.IValueEvent;
import com.mojang.datafixers.util.Either;
import net.minecraft.class_1657;

public interface IEventHelper {
   Either<IValueEvent, Integer> fireValueEvent(class_1657 var1, int var2);

   Either<IRepairEvent, Integer> fireRepairEvent(class_1657 var1, int var2);
}
