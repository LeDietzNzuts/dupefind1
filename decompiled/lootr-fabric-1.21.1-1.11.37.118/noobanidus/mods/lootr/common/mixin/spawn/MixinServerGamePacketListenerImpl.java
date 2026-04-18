package noobanidus.mods.lootr.common.mixin.spawn;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.class_1657;
import net.minecraft.class_2338;
import net.minecraft.class_3218;
import net.minecraft.class_3244;
import noobanidus.mods.lootr.common.api.LootrAPI;
import noobanidus.mods.lootr.common.api.LootrTags;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(class_3244.class)
public class MixinServerGamePacketListenerImpl {
   @WrapOperation(
      method = "method_12046",
      at = @At(value = "INVOKE", target = "Lnet/minecraft/class_3218;method_8505(Lnet/minecraft/class_1657;Lnet/minecraft/class_2338;)Z")
   )
   private boolean LootrAllowInteractSpawnProtection(class_3218 instance, class_1657 player, class_2338 blockPos, Operation<Boolean> original) {
      return LootrAPI.shouldBypassSpawnProtection()
            && instance.method_8320(blockPos).method_26164(LootrTags.Blocks.INTERACT_WHITELIST_BLOCKS)
            && instance.method_8503().method_3785(instance, blockPos, player)
         ? instance.method_8621().method_11952(blockPos)
         : (Boolean)original.call(new Object[]{instance, player, blockPos});
   }
}
