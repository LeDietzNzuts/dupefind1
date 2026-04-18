package net.caffeinemc.mods.lithium.mixin.ai.poi.tasks;

import java.util.Optional;
import java.util.function.Predicate;
import net.caffeinemc.mods.lithium.common.util.POIRegistryEntries;
import net.caffeinemc.mods.lithium.common.world.interests.iterator.SinglePointOfInterestTypeFilter;
import net.minecraft.class_2338;
import net.minecraft.class_4153;
import net.minecraft.class_4158;
import net.minecraft.class_5819;
import net.minecraft.class_6880;
import net.minecraft.class_4153.class_4155;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(targets = "net/minecraft/class_3763$class_4261")
public class RaiderEntityAttackHomeGoalMixin {
   @Redirect(
      method = "method_20040()Z",
      at = @At(
         value = "INVOKE",
         target = "Lnet/minecraft/class_4153;method_20005(Ljava/util/function/Predicate;Ljava/util/function/Predicate;Lnet/minecraft/class_4153$class_4155;Lnet/minecraft/class_2338;ILnet/minecraft/class_5819;)Ljava/util/Optional;"
      )
   )
   private Optional<class_2338> redirect(
      class_4153 instance,
      Predicate<class_6880<class_4158>> typePredicate,
      Predicate<class_2338> positionPredicate,
      class_4155 occupationStatus,
      class_2338 pos,
      int radius,
      class_5819 random
   ) {
      return instance.method_20005(new SinglePointOfInterestTypeFilter(POIRegistryEntries.HOME_ENTRY), positionPredicate, occupationStatus, pos, radius, random);
   }
}
