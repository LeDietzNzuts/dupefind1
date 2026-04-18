package net.caffeinemc.mods.lithium.mixin.entity.collisions.intersection;

import java.util.List;
import java.util.function.Predicate;
import net.caffeinemc.mods.lithium.common.world.WorldHelper;
import net.minecraft.class_1297;
import net.minecraft.class_1924;
import net.minecraft.class_238;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(class_1924.class)
public interface EntityGetterMixin {
   @Redirect(
      method = "method_20743(Lnet/minecraft/class_1297;Lnet/minecraft/class_238;)Ljava/util/List;",
      at = @At(
         value = "INVOKE",
         target = "Lnet/minecraft/class_1924;method_8333(Lnet/minecraft/class_1297;Lnet/minecraft/class_238;Ljava/util/function/Predicate;)Ljava/util/List;"
      )
   )
   private List<class_1297> getCollisionEntities(class_1924 instance, @Nullable class_1297 entity, class_238 box, Predicate<? super class_1297> predicate) {
      return WorldHelper.getOtherEntitiesForCollision(instance, box, entity, predicate);
   }
}
