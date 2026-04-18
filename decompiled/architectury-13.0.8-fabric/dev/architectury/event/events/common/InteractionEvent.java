package dev.architectury.event.events.common;

import dev.architectury.event.CompoundEventResult;
import dev.architectury.event.Event;
import dev.architectury.event.EventFactory;
import dev.architectury.event.EventResult;
import net.minecraft.class_1268;
import net.minecraft.class_1297;
import net.minecraft.class_1657;
import net.minecraft.class_1799;
import net.minecraft.class_1937;
import net.minecraft.class_2338;
import net.minecraft.class_2350;
import net.minecraft.class_2680;

public interface InteractionEvent {
   Event<InteractionEvent.LeftClickBlock> LEFT_CLICK_BLOCK = EventFactory.createEventResult();
   Event<InteractionEvent.RightClickBlock> RIGHT_CLICK_BLOCK = EventFactory.createEventResult();
   Event<InteractionEvent.RightClickItem> RIGHT_CLICK_ITEM = EventFactory.createCompoundEventResult();
   Event<InteractionEvent.ClientLeftClickAir> CLIENT_LEFT_CLICK_AIR = EventFactory.createLoop();
   Event<InteractionEvent.ClientRightClickAir> CLIENT_RIGHT_CLICK_AIR = EventFactory.createLoop();
   Event<InteractionEvent.InteractEntity> INTERACT_ENTITY = EventFactory.createEventResult();
   Event<InteractionEvent.FarmlandTrample> FARMLAND_TRAMPLE = EventFactory.createEventResult();

   public interface ClientLeftClickAir {
      void click(class_1657 var1, class_1268 var2);
   }

   public interface ClientRightClickAir {
      void click(class_1657 var1, class_1268 var2);
   }

   public interface FarmlandTrample {
      EventResult trample(class_1937 var1, class_2338 var2, class_2680 var3, float var4, class_1297 var5);
   }

   public interface InteractEntity {
      EventResult interact(class_1657 var1, class_1297 var2, class_1268 var3);
   }

   public interface LeftClickBlock {
      EventResult click(class_1657 var1, class_1268 var2, class_2338 var3, class_2350 var4);
   }

   public interface RightClickBlock {
      EventResult click(class_1657 var1, class_1268 var2, class_2338 var3, class_2350 var4);
   }

   public interface RightClickItem {
      CompoundEventResult<class_1799> click(class_1657 var1, class_1268 var2);
   }
}
