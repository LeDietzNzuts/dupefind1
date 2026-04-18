package dev.architectury.event.events.common;

import dev.architectury.event.Event;
import dev.architectury.event.EventFactory;
import net.minecraft.class_52;
import net.minecraft.class_5321;
import net.minecraft.class_55.class_56;
import org.jetbrains.annotations.ApiStatus.NonExtendable;

public interface LootEvent {
   Event<LootEvent.ModifyLootTable> MODIFY_LOOT_TABLE = EventFactory.createLoop();

   @NonExtendable
   public interface LootTableModificationContext {
      void addPool(class_56 var1);
   }

   @FunctionalInterface
   public interface ModifyLootTable {
      void modifyLootTable(class_5321<class_52> var1, LootEvent.LootTableModificationContext var2, boolean var3);
   }
}
