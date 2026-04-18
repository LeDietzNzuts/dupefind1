package net.caffeinemc.mods.lithium.mixin.ai.poi.fast_portals;

import java.util.Optional;
import net.caffeinemc.mods.lithium.common.util.POIRegistryEntries;
import net.caffeinemc.mods.lithium.common.world.interests.PointOfInterestStorageExtended;
import net.minecraft.class_1946;
import net.minecraft.class_2338;
import net.minecraft.class_2741;
import net.minecraft.class_2784;
import net.minecraft.class_3218;
import net.minecraft.class_4153;
import net.minecraft.class_4156;
import net.minecraft.class_4153.class_4155;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(class_1946.class)
public class PortalForcerMixin {
   @Shadow
   @Final
   private class_3218 field_9286;

   @Overwrite
   public Optional<class_2338> method_30483(class_2338 centerPos, boolean dstIsNether, class_2784 worldBorder) {
      int searchRadius = dstIsNether ? 16 : 128;
      class_4153 poiStorage = this.field_9286.method_19494();
      poiStorage.method_22439(this.field_9286, centerPos, searchRadius);
      Optional<class_4156> ret = ((PointOfInterestStorageExtended)poiStorage)
         .lithium$findNearestForPortalLogic(
            centerPos,
            searchRadius,
            POIRegistryEntries.NETHER_PORTAL_ENTRY,
            class_4155.field_18489,
            poi -> this.field_9286.method_8320(poi.method_19141()).method_28498(class_2741.field_12529),
            worldBorder
         );
      return ret.map(class_4156::method_19141);
   }
}
