package noobanidus.mods.lootr.common.mixin.structure_saving;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.class_2487;
import net.minecraft.class_2586;
import net.minecraft.class_3499;
import net.minecraft.class_7225.class_7874;
import noobanidus.mods.lootr.common.api.LootrAPI;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(class_3499.class)
public class MixinStructureTemplate {
   @WrapOperation(
      method = "method_15174",
      at = @At(value = "INVOKE", target = "Lnet/minecraft/class_2586;method_38243(Lnet/minecraft/class_7225$class_7874;)Lnet/minecraft/class_2487;")
   )
   private class_2487 LootrInjectStructureSavingStart(class_2586 instance, class_7874 provider, Operation<class_2487> original) {
      LootrAPI.shouldDiscardIdAndOpeners = true;
      class_2487 result = (class_2487)original.call(new Object[]{instance, provider});
      LootrAPI.shouldDiscardIdAndOpeners = false;
      return result;
   }
}
