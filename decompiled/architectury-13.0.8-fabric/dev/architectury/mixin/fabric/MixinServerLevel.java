package dev.architectury.mixin.fabric;

import dev.architectury.event.events.common.LifecycleEvent;
import dev.architectury.hooks.fabric.PersistentEntitySectionManagerHooks;
import net.minecraft.class_1297;
import net.minecraft.class_3218;
import net.minecraft.class_3536;
import net.minecraft.class_5579;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(class_3218.class)
public class MixinServerLevel {
   @Shadow
   @Final
   private class_5579<class_1297> field_26935;

   @Inject(method = "save", at = @At(value = "INVOKE", target = "Lnet/minecraft/server/level/ServerChunkCache;save(Z)V"))
   private void save(class_3536 progressListener, boolean bl, boolean bl2, CallbackInfo ci) {
      LifecycleEvent.SERVER_LEVEL_SAVE.invoker().act((class_3218)this);
   }

   @Inject(
      method = "addEntity",
      at = @At(
         value = "INVOKE",
         target = "Lnet/minecraft/world/level/entity/PersistentEntitySectionManager;addNewEntity(Lnet/minecraft/world/level/entity/EntityAccess;)Z"
      ),
      cancellable = true
   )
   private void addEntity(class_1297 entity, CallbackInfoReturnable<Boolean> cir) {
      ((PersistentEntitySectionManagerHooks)this.field_26935).architectury_attachLevel((class_3218)this);
   }
}
