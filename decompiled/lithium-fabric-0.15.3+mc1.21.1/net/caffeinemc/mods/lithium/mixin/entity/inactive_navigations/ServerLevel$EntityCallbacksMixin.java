package net.caffeinemc.mods.lithium.mixin.entity.inactive_navigations;

import java.util.Set;
import net.caffeinemc.mods.lithium.common.entity.NavigatingEntity;
import net.caffeinemc.mods.lithium.common.world.ServerWorldExtended;
import net.minecraft.class_1308;
import net.minecraft.class_1408;
import net.minecraft.class_3218;
import net.minecraft.class_3218.class_5526;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(class_5526.class)
public class ServerLevel$EntityCallbacksMixin {
   private class_3218 outer;

   @Inject(method = "<init>(Lnet/minecraft/class_3218;)V", at = @At("TAIL"))
   private void inj(class_3218 outer, CallbackInfo ci) {
      this.outer = outer;
   }

   @Redirect(method = "method_31436(Lnet/minecraft/class_1297;)V", at = @At(value = "INVOKE", target = "Ljava/util/Set;add(Ljava/lang/Object;)Z"))
   private boolean startListeningOnEntityLoad(Set<class_1308> set, Object mobEntityObj) {
      class_1308 mobEntity = (class_1308)mobEntityObj;
      class_1408 navigation = mobEntity.method_5942();
      ((NavigatingEntity)mobEntity).lithium$setRegisteredToWorld(navigation);
      if (navigation.method_6345() != null) {
         ((ServerWorldExtended)this.outer).lithium$setNavigationActive(mobEntity);
      }

      return set.add(mobEntity);
   }

   @Redirect(method = "method_31437(Lnet/minecraft/class_1297;)V", at = @At(value = "INVOKE", target = "Ljava/util/Set;remove(Ljava/lang/Object;)Z"))
   private boolean stopListeningOnEntityUnload(Set<class_1308> set, Object mobEntityObj) {
      class_1308 mobEntity = (class_1308)mobEntityObj;
      NavigatingEntity navigatingEntity = (NavigatingEntity)mobEntity;
      if (navigatingEntity.lithium$isRegisteredToWorld()) {
         class_1408 registeredNavigation = navigatingEntity.lithium$getRegisteredNavigation();
         if (registeredNavigation.method_6345() != null) {
            ((ServerWorldExtended)this.outer).lithium$setNavigationInactive(mobEntity);
         }

         navigatingEntity.lithium$setRegisteredToWorld(null);
      }

      return set.remove(mobEntity);
   }
}
