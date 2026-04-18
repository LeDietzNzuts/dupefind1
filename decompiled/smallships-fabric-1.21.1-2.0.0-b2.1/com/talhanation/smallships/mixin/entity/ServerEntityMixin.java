package com.talhanation.smallships.mixin.entity;

import com.talhanation.smallships.world.entity.IMixinEntity;
import java.util.List;
import java.util.stream.Stream;
import net.minecraft.class_1297;
import net.minecraft.class_3231;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(class_3231.class)
public class ServerEntityMixin {
   @Redirect(
      method = "sendChanges",
      at = @At(
         value = "INVOKE",
         target = "Lnet/minecraft/server/level/ServerEntity;removedPassengers(Ljava/util/List;Ljava/util/List;)Ljava/util/stream/Stream;"
      )
   )
   public Stream<class_1297> filterRemovedPassengers(List<class_1297> originalPassengers, List<class_1297> lastPassengers) {
      return method_49753(originalPassengers, lastPassengers).filter(entity -> {
         if (((IMixinEntity)entity).doNotTeleportOnNextPassengerSync()) {
            ((IMixinEntity)entity).setPreventTeleportOnNextPassengerSync(false);
            return false;
         } else {
            return true;
         }
      });
   }

   @Shadow
   private static Stream<class_1297> method_49753(List<class_1297> list, List<class_1297> list2) {
      return null;
   }
}
