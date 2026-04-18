package net.caffeinemc.mods.lithium.mixin.ai.non_poi_block_search;

import java.util.Optional;
import java.util.function.Predicate;
import net.caffeinemc.mods.lithium.common.ai.non_poi_block_search.CommonBlockSearchesCheckAndCache;
import net.minecraft.class_2338;
import net.minecraft.class_2680;
import net.minecraft.class_3218;
import net.minecraft.class_3481;
import net.minecraft.class_4760;
import net.minecraft.class_4832;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(class_4832.class)
public abstract class HoglinSpecificSensorMixin {
   @Unique
   private static final Predicate<class_2680> IS_VALID_REPELLENT_PREDICATE = HoglinSpecificSensorMixin::lithium$isValidRepellent;

   @Redirect(
      method = "method_24639(Lnet/minecraft/class_3218;Lnet/minecraft/class_4760;)V",
      at = @At(value = "INVOKE", target = "Lnet/minecraft/class_4832;method_24641(Lnet/minecraft/class_3218;Lnet/minecraft/class_4760;)Ljava/util/Optional;")
   )
   private Optional<class_2338> redirectFindNearestRepellent(class_4832 instance, class_3218 serverLevel, class_4760 hoglin) {
      return CommonBlockSearchesCheckAndCache.blockPosFindClosestMatch(serverLevel, hoglin, 8, 4, IS_VALID_REPELLENT_PREDICATE, true);
   }

   @Unique
   private static boolean lithium$isValidRepellent(class_2680 blockState) {
      return blockState.method_26164(class_3481.field_22466);
   }
}
