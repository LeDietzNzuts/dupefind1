package dev.architectury.event.events.common;

import dev.architectury.event.Event;
import dev.architectury.event.EventFactory;
import dev.architectury.event.EventResult;
import net.minecraft.class_1282;
import net.minecraft.class_1297;
import net.minecraft.class_1309;
import net.minecraft.class_1429;
import net.minecraft.class_1657;
import net.minecraft.class_1917;
import net.minecraft.class_1936;
import net.minecraft.class_1937;
import net.minecraft.class_3730;
import org.jetbrains.annotations.Nullable;

public interface EntityEvent {
   Event<EntityEvent.LivingDeath> LIVING_DEATH = EventFactory.createEventResult();
   Event<EntityEvent.LivingHurt> LIVING_HURT = EventFactory.createEventResult();
   Event<EntityEvent.LivingCheckSpawn> LIVING_CHECK_SPAWN = EventFactory.createEventResult();
   Event<EntityEvent.Add> ADD = EventFactory.createEventResult();
   Event<EntityEvent.EnterSection> ENTER_SECTION = EventFactory.createLoop();
   Event<EntityEvent.AnimalTame> ANIMAL_TAME = EventFactory.createEventResult();

   public interface Add {
      EventResult add(class_1297 var1, class_1937 var2);
   }

   public interface AnimalTame {
      EventResult tame(class_1429 var1, class_1657 var2);
   }

   public interface EnterSection {
      void enterSection(class_1297 var1, int var2, int var3, int var4, int var5, int var6, int var7);
   }

   public interface LivingCheckSpawn {
      EventResult canSpawn(class_1309 var1, class_1936 var2, double var3, double var5, double var7, class_3730 var9, @Nullable class_1917 var10);
   }

   public interface LivingDeath {
      EventResult die(class_1309 var1, class_1282 var2);
   }

   public interface LivingHurt {
      EventResult hurt(class_1309 var1, class_1282 var2, float var3);
   }
}
