package dev.architectury.event.events.common;

import dev.architectury.event.Event;
import dev.architectury.event.EventFactory;
import net.minecraft.class_2487;
import net.minecraft.class_2791;
import net.minecraft.class_3218;
import org.jetbrains.annotations.Nullable;

public interface ChunkEvent {
   Event<ChunkEvent.SaveData> SAVE_DATA = EventFactory.createLoop();
   Event<ChunkEvent.LoadData> LOAD_DATA = EventFactory.createLoop();

   public interface LoadData {
      void load(class_2791 var1, @Nullable class_3218 var2, class_2487 var3);
   }

   public interface SaveData {
      void save(class_2791 var1, class_3218 var2, class_2487 var3);
   }
}
