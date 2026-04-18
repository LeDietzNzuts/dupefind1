package dev.architectury.event.fabric;

import dev.architectury.event.events.common.LootEvent;
import net.minecraft.class_52.class_53;
import net.minecraft.class_55.class_56;

final class LootTableModificationContextImpl implements LootEvent.LootTableModificationContext {
   private final class_53 tableBuilder;

   LootTableModificationContextImpl(class_53 tableBuilder) {
      this.tableBuilder = tableBuilder;
   }

   @Override
   public void addPool(class_56 pool) {
      this.tableBuilder.method_336(pool);
   }
}
