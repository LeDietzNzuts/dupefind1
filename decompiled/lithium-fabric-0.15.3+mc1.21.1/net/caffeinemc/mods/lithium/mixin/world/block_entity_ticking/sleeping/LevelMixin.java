package net.caffeinemc.mods.lithium.mixin.world.block_entity_ticking.sleeping;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.class_1937;
import net.minecraft.class_2338;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(class_1937.class)
public class LevelMixin {
   @WrapOperation(
      method = "method_18471()V",
      at = @At(value = "INVOKE", target = "Lnet/minecraft/class_1937;method_41411(Lnet/minecraft/class_2338;)Z"),
      require = 0
   )
   private boolean shouldTickBlockPosFilterNull(class_1937 instance, class_2338 pos, Operation<Boolean> original) {
      return pos == null ? false : (Boolean)original.call(new Object[]{instance, pos});
   }
}
