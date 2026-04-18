package net.caffeinemc.mods.lithium.mixin.ai.non_poi_block_search;

import java.util.Optional;
import java.util.function.Predicate;
import net.caffeinemc.mods.lithium.common.ai.non_poi_block_search.CommonBlockSearchesCheckAndCache;
import net.minecraft.class_1309;
import net.minecraft.class_2246;
import net.minecraft.class_2338;
import net.minecraft.class_2680;
import net.minecraft.class_3218;
import net.minecraft.class_3481;
import net.minecraft.class_3922;
import net.minecraft.class_4834;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(class_4834.class)
public abstract class PiglinSpecificSensorMixin {
   @Unique
   private static final Predicate<class_2680> IS_VALID_REPELLENT_PREDICATE = PiglinSpecificSensorMixin::lithium$isValidRepellent;

   @Redirect(
      method = "method_19101(Lnet/minecraft/class_3218;Lnet/minecraft/class_1309;)V",
      at = @At(value = "INVOKE", target = "Lnet/minecraft/class_4834;method_24649(Lnet/minecraft/class_3218;Lnet/minecraft/class_1309;)Ljava/util/Optional;")
   )
   public Optional<class_2338> redirectFindNearestRepellent(class_3218 serverLevel, class_1309 livingEntity) {
      return CommonBlockSearchesCheckAndCache.blockPosFindClosestMatch(serverLevel, livingEntity, 8, 4, IS_VALID_REPELLENT_PREDICATE, true);
   }

   @Unique
   private static boolean lithium$isValidRepellent(class_2680 blockState) {
      boolean isPiglinRepellent = blockState.method_26164(class_3481.field_22465);
      return isPiglinRepellent && blockState.method_27852(class_2246.field_23860) ? class_3922.method_23896(blockState) : isPiglinRepellent;
   }
}
