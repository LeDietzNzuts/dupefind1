package net.p3pp3rf1y.sophisticatedcore.mixin.common;

import net.minecraft.class_1297;
import net.minecraft.class_5568;
import net.minecraft.class_5579;
import net.p3pp3rf1y.sophisticatedcore.event.common.EntityEvents;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(class_5579.class)
public class PersistentEntitySectionManagerMixin<T extends class_5568> {
   @Inject(method = "addEntity", at = @At("HEAD"), cancellable = true)
   public void sophisticatedcore$addEntityEvent(T entityAccess, boolean loadedFromDisk, CallbackInfoReturnable<Boolean> cir) {
      if (entityAccess instanceof class_1297 entity
         && ((EntityEvents.JoinWorld)EntityEvents.ON_JOIN_WORLD.invoker()).onJoinWorld(entity, entity.method_37908(), loadedFromDisk)) {
         cir.setReturnValue(false);
      }
   }
}
