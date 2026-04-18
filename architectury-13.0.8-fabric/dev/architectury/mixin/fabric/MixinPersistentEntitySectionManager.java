package dev.architectury.mixin.fabric;

import dev.architectury.event.events.common.EntityEvent;
import dev.architectury.hooks.fabric.PersistentEntitySectionManagerHooks;
import java.lang.ref.WeakReference;
import net.minecraft.class_1297;
import net.minecraft.class_3218;
import net.minecraft.class_5568;
import net.minecraft.class_5579;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(class_5579.class)
public class MixinPersistentEntitySectionManager<T extends class_5568> implements PersistentEntitySectionManagerHooks {
   @Unique
   private WeakReference<class_3218> levelRef;

   @Override
   public void architectury_attachLevel(class_3218 level) {
      this.levelRef = new WeakReference<>(level);
   }

   @Inject(
      method = "addEntity",
      at = @At(value = "INVOKE", target = "Lnet/minecraft/core/SectionPos;asLong(Lnet/minecraft/core/BlockPos;)J"),
      cancellable = true
   )
   private void addEntity(T entityAccess, boolean bl, CallbackInfoReturnable<Boolean> cir) {
      if (entityAccess instanceof class_1297 entity && this.levelRef != null) {
         class_3218 level = this.levelRef.get();
         this.levelRef = null;
         if (level != null && EntityEvent.ADD.invoker().add(entity, level).isFalse()) {
            cir.setReturnValue(false);
         }
      }
   }
}
