package net.caffeinemc.mods.lithium.common.util.tuples;

import net.minecraft.class_2338;
import net.minecraft.class_4156;

public record SortedPointOfInterest(class_4156 poi, double distanceSq) {
   public SortedPointOfInterest(class_4156 poi, class_2338 origin) {
      this(poi, poi.method_19141().method_10262(origin));
   }

   public class_2338 getPos() {
      return this.poi.method_19141();
   }

   public int getX() {
      return this.getPos().method_10263();
   }

   public int getY() {
      return this.getPos().method_10264();
   }

   public int getZ() {
      return this.getPos().method_10260();
   }
}
