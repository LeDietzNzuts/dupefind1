package net.caffeinemc.mods.lithium.mixin.entity.collisions.unpushable_cramming;

import java.util.function.Predicate;
import net.caffeinemc.mods.lithium.common.entity.pushable.EntityPushablePredicate;
import net.minecraft.class_1301;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(class_1301.class)
public class EntitySelectorMixin {
   @Redirect(
      method = "method_5911(Lnet/minecraft/class_1297;)Ljava/util/function/Predicate;",
      at = @At(value = "INVOKE", target = "Ljava/util/function/Predicate;and(Ljava/util/function/Predicate;)Ljava/util/function/Predicate;")
   )
   private static <T> Predicate<T> getEntityPushablePredicate(Predicate<T> first, Predicate<? super T> second) {
      return EntityPushablePredicate.and(first, second);
   }
}
