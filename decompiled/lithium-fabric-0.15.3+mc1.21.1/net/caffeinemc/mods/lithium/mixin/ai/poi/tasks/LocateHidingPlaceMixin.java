package net.caffeinemc.mods.lithium.mixin.ai.poi.tasks;

import java.util.Optional;
import java.util.function.Predicate;
import net.caffeinemc.mods.lithium.common.util.POIRegistryEntries;
import net.caffeinemc.mods.lithium.common.world.interests.iterator.SinglePointOfInterestTypeFilter;
import net.minecraft.class_2338;
import net.minecraft.class_4153;
import net.minecraft.class_4158;
import net.minecraft.class_4246;
import net.minecraft.class_5819;
import net.minecraft.class_6880;
import net.minecraft.class_4153.class_4155;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(class_4246.class)
public class LocateHidingPlaceMixin {
   @Redirect(
      method = {"method_46979(Lnet/minecraft/class_3218;Lnet/minecraft/class_1309;I)Ljava/util/Optional;", "lambda$create$5"},
      at = @At(
         value = "INVOKE",
         target = "Lnet/minecraft/class_4153;method_20005(Ljava/util/function/Predicate;Ljava/util/function/Predicate;Lnet/minecraft/class_4153$class_4155;Lnet/minecraft/class_2338;ILnet/minecraft/class_5819;)Ljava/util/Optional;"
      )
   )
   private static Optional<class_2338> useFasterPOILookup(
      class_4153 pointOfInterestStorage,
      Predicate<class_6880<class_4158>> typePredicate,
      Predicate<class_2338> positionPredicate,
      class_4155 occupationStatus,
      class_2338 pos,
      int radius,
      class_5819 random
   ) {
      return pointOfInterestStorage.method_20005(
         new SinglePointOfInterestTypeFilter(POIRegistryEntries.HOME_ENTRY), positionPredicate, occupationStatus, pos, radius, random
      );
   }

   @Redirect(
      method = {
            "method_46978(IILnet/minecraft/class_7898$class_7900;Lnet/minecraft/class_7906;Lnet/minecraft/class_7906;Lnet/minecraft/class_7906;Lnet/minecraft/class_7906;Lnet/minecraft/class_7906;Lnet/minecraft/class_7906;Lnet/minecraft/class_7906;FLnet/minecraft/class_3218;Lnet/minecraft/class_1309;J)Z",
            "lambda$create$8"
      },
      at = @At(
         value = "INVOKE",
         target = "Lnet/minecraft/class_4153;method_19127(Ljava/util/function/Predicate;Ljava/util/function/Predicate;Lnet/minecraft/class_2338;ILnet/minecraft/class_4153$class_4155;)Ljava/util/Optional;"
      )
   )
   private static Optional<class_2338> useFasterPOILookup(
      class_4153 pointOfInterestStorage,
      Predicate<class_6880<class_4158>> typePredicate,
      Predicate<class_2338> posPredicate,
      class_2338 pos,
      int radius,
      class_4155 occupationStatus
   ) {
      return pointOfInterestStorage.method_19127(
         new SinglePointOfInterestTypeFilter(POIRegistryEntries.HOME_ENTRY), posPredicate, pos, radius, occupationStatus
      );
   }
}
