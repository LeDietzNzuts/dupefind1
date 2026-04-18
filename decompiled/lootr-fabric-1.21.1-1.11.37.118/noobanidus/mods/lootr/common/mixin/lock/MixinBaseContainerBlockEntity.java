package noobanidus.mods.lootr.common.mixin.lock;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.class_1657;
import net.minecraft.class_2561;
import net.minecraft.class_2624;
import net.minecraft.class_3414;
import net.minecraft.class_3419;
import noobanidus.mods.lootr.common.api.data.blockentity.LockMessageSuppression;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(class_2624.class)
public class MixinBaseContainerBlockEntity {
   @WrapOperation(method = "method_17487", at = @At(value = "INVOKE", target = "Lnet/minecraft/class_1657;method_7353(Lnet/minecraft/class_2561;Z)V"))
   private static void lootr$suppressLockMessage(class_1657 instance, class_2561 chatComponent, boolean actionBar, Operation<Void> original) {
      if (!LockMessageSuppression.isSuppressed()) {
         original.call(new Object[]{instance, chatComponent, actionBar});
      }
   }

   @WrapOperation(
      method = "method_17487",
      at = @At(value = "INVOKE", target = "Lnet/minecraft/class_1657;method_17356(Lnet/minecraft/class_3414;Lnet/minecraft/class_3419;FF)V")
   )
   private static void lootr$suppressLockSound(
      class_1657 instance, class_3414 soundEvent, class_3419 soundSource, float volume, float pitch, Operation<Void> original
   ) {
      if (!LockMessageSuppression.isSuppressed()) {
         original.call(new Object[]{instance, soundEvent, soundSource, volume, pitch});
      }
   }
}
