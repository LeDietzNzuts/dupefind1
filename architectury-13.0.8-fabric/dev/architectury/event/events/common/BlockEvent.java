package dev.architectury.event.events.common;

import dev.architectury.event.Event;
import dev.architectury.event.EventFactory;
import dev.architectury.event.EventResult;
import dev.architectury.utils.value.IntValue;
import net.minecraft.class_1297;
import net.minecraft.class_1540;
import net.minecraft.class_1937;
import net.minecraft.class_2338;
import net.minecraft.class_2680;
import net.minecraft.class_3222;
import org.jetbrains.annotations.Nullable;

public interface BlockEvent {
   Event<BlockEvent.Break> BREAK = EventFactory.createEventResult();
   Event<BlockEvent.Place> PLACE = EventFactory.createEventResult();
   Event<BlockEvent.FallingLand> FALLING_LAND = EventFactory.createLoop();

   public interface Break {
      EventResult breakBlock(class_1937 var1, class_2338 var2, class_2680 var3, class_3222 var4, @Nullable IntValue var5);
   }

   public interface FallingLand {
      void onLand(class_1937 var1, class_2338 var2, class_2680 var3, class_2680 var4, class_1540 var5);
   }

   public interface Place {
      EventResult placeBlock(class_1937 var1, class_2338 var2, class_2680 var3, @Nullable class_1297 var4);
   }
}
