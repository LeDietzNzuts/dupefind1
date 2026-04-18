package net.p3pp3rf1y.sophisticatedbackpacks.api;

import net.minecraft.class_1297;
import net.minecraft.class_1657;
import net.minecraft.class_1937;

public interface IEntityToolSwapUpgrade {
   default boolean canProcessEntityInteract() {
      return true;
   }

   boolean onEntityInteract(class_1937 var1, class_1297 var2, class_1657 var3);
}
