package dev.architectury.mixin.fabric;

import dev.architectury.event.events.common.EntityEvent;
import dev.architectury.extensions.ItemExtension;
import net.minecraft.class_1282;
import net.minecraft.class_1304;
import net.minecraft.class_1309;
import net.minecraft.class_1657;
import net.minecraft.class_1799;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(class_1309.class)
public class MixinLivingEntity {
   @Inject(method = "hurt", at = @At("HEAD"), cancellable = true)
   private void hurt(class_1282 damageSource, float f, CallbackInfoReturnable<Boolean> cir) {
      if (!(this instanceof class_1657)) {
         if (EntityEvent.LIVING_HURT.invoker().hurt((class_1309)this, damageSource, f).isFalse()) {
            cir.setReturnValue(false);
         }
      }
   }

   @Inject(method = "getEquipmentSlotForItem", at = @At("HEAD"), cancellable = true)
   private void getEquipmentSlotForItem(class_1799 stack, CallbackInfoReturnable<class_1304> cir) {
      if (stack.method_7909() instanceof ItemExtension extension) {
         class_1304 slot = extension.getCustomEquipmentSlot(stack);
         if (slot != null) {
            cir.setReturnValue(slot);
         }
      }
   }
}
