package net.caffeinemc.mods.lithium.common.world.interests;

import java.util.Optional;
import java.util.function.Predicate;
import net.minecraft.class_2338;
import net.minecraft.class_2784;
import net.minecraft.class_4156;
import net.minecraft.class_4158;
import net.minecraft.class_6880;
import net.minecraft.class_4153.class_4155;

public interface PointOfInterestStorageExtended {
   Optional<class_4156> lithium$findNearestForPortalLogic(
      class_2338 var1, int var2, class_6880<class_4158> var3, class_4155 var4, Predicate<class_4156> var5, class_2784 var6
   );
}
