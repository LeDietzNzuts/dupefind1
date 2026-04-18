package net.p3pp3rf1y.sophisticatedcore.extensions.world;

import java.util.Collection;
import net.minecraft.class_2586;

public interface SophisticatedLevel {
   default void sophisticatedCore_addFreshBlockEntities(Collection<class_2586> beList) {
      throw new RuntimeException("Should have been overriden by mixin.");
   }
}
