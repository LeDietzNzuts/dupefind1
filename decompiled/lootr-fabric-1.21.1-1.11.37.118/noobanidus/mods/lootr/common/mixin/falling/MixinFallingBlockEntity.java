package noobanidus.mods.lootr.common.mixin.falling;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.class_1540;
import net.minecraft.class_2586;
import noobanidus.mods.lootr.common.api.LootrAPI;
import noobanidus.mods.lootr.common.api.data.blockentity.ILootrBlockEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(class_1540.class)
public class MixinFallingBlockEntity {
   @WrapOperation(method = "method_5773", at = @At(value = "INVOKE", target = "Lnet/minecraft/class_2586;method_5431()V"))
   private void lootr$onSetChanged(class_2586 instance, Operation<Void> original) {
      original.call(new Object[]{instance});
      ILootrBlockEntity resolved = null;
      ILootrBlockEntity var6 = LootrAPI.resolveBlockEntity(instance);
      if (var6 instanceof ILootrBlockEntity) {
         resolved = var6;
      } else if (instance instanceof ILootrBlockEntity resolved2) {
         resolved = resolved2;
      }

      if (resolved != null) {
         resolved.performUpdate();
      }
   }
}
