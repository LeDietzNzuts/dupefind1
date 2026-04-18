package net.p3pp3rf1y.sophisticatedcore.extensions.block.entity;

import net.fabricmc.fabric.impl.lookup.block.ServerWorldCache;
import net.minecraft.class_2586;
import net.minecraft.class_3218;

public interface SophisticatedBlockEntity {
   private class_2586 self() {
      return (class_2586)this;
   }

   default void sophisticatedCore_invalidateCapabilities() {
      class_2586 be = this.self();
      if (be.method_10997() instanceof class_3218 serverLevel) {
         ((ServerWorldCache)serverLevel).fabric_invalidateCache(be.method_11016());
      }
   }

   default void sophisticatedCore_onLoad() {
   }

   default void sophisticatedCore_onChunkUnloaded() {
   }
}
