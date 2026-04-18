package net.p3pp3rf1y.sophisticatedbackpacks.api;

import javax.annotation.Nullable;
import net.minecraft.class_1657;
import net.minecraft.class_1799;
import net.minecraft.class_1937;
import net.minecraft.class_2338;
import net.minecraft.class_5147;

public interface SophisticatedShearable {
   default boolean sophisticatedBackpacks_isShearable(@Nullable class_1657 player, class_1799 item, class_1937 level, class_2338 pos) {
      return !(this instanceof class_5147 shearable && !shearable.method_27072());
   }
}
