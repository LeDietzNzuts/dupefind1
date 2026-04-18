package noobanidus.mods.lootr.common.mixin.chest_blocking;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.class_1922;
import net.minecraft.class_2281;
import net.minecraft.class_2338;
import net.minecraft.class_2680;
import noobanidus.mods.lootr.common.api.LootrTags;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(class_2281.class)
public class MixinChestBlock {
   @WrapOperation(
      method = "method_9757",
      at = @At(value = "INVOKE", target = "Lnet/minecraft/class_2680;method_26212(Lnet/minecraft/class_1922;Lnet/minecraft/class_2338;)Z")
   )
   private static boolean LootrIsChestBlocked(
      class_2680 instance, class_1922 blockGetter, class_2338 blockPos, Operation<Boolean> original, @Local(argsOnly = true) class_2338 pos
   ) {
      if (instance.method_26164(LootrTags.Blocks.NON_BLOCKING)) {
         class_2680 blockState = blockGetter.method_8320(pos);
         if (blockState.method_26164(LootrTags.Blocks.CONTAINERS)) {
            return false;
         }
      }

      return (Boolean)original.call(new Object[]{instance, blockGetter, blockPos});
   }
}
