package net.caffeinemc.mods.lithium.mixin.entity.collisions.unpushable_cramming;

import com.google.common.base.Predicates;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;
import net.caffeinemc.mods.lithium.common.entity.pushable.EntityPushablePredicate;
import net.caffeinemc.mods.lithium.common.world.WorldHelper;
import net.minecraft.class_1297;
import net.minecraft.class_1690;
import net.minecraft.class_1937;
import net.minecraft.class_238;
import net.minecraft.class_5573;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(class_1690.class)
public class BoatMixin {
   @Redirect(
      method = "method_5773()V",
      at = @At(
         value = "INVOKE",
         target = "Lnet/minecraft/class_1937;method_8333(Lnet/minecraft/class_1297;Lnet/minecraft/class_238;Ljava/util/function/Predicate;)Ljava/util/List;"
      )
   )
   private List<class_1297> getOtherPushableEntities(class_1937 world, @Nullable class_1297 except, class_238 box, Predicate<? super class_1297> predicate) {
      if (predicate == Predicates.alwaysFalse()) {
         return Collections.emptyList();
      } else {
         if (predicate instanceof EntityPushablePredicate<?> entityPushablePredicate) {
            class_5573<class_1297> cache = WorldHelper.getEntityCacheOrNull(world);
            if (cache != null) {
               return WorldHelper.getPushableEntities(world, cache, except, box, (EntityPushablePredicate<? super class_1297>)entityPushablePredicate);
            }
         }

         return world.method_8333(except, box, predicate);
      }
   }
}
